/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.upload;

import com.venus.core.util.FileUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.FileAttachmentBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.FileAttachmentDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author thcao
 */
public class UploadFileAction extends SpineAction {

    @Deprecated
    public static boolean isValidFType(int ftype) {
        return (ftype > UploadFileUtil.ATTACH_FILE_START
                && ftype < UploadFileUtil.ATTACH_FILE_END);
    }

    @Deprecated
    public static String getUploadFolder(int ftype) {
        String strFolder = "private";
        switch (ftype) {
            case UploadFileUtil.ATTACH_FILE_REQUEST:
                strFolder = "request";
                break;
            case UploadFileUtil.ATTACH_FILE_TENDERPLAN:
                strFolder = "tenderplan";
                break;
            case UploadFileUtil.ATTACH_FILE_TENDERLETTER:
                strFolder = "tenderletter";
                break;
            case UploadFileUtil.ATTACH_FILE_CONTRACT:
                strFolder = "contract";
                break;
            case UploadFileUtil.ATTACH_FILE_BIDOPEN:
                strFolder = "bidopen";
                break;
            case UploadFileUtil.ATTACH_FILE_BIDCLOSE:
                strFolder = "bidclose";
                break;
            case UploadFileUtil.ATTACH_FILE_TECHEVAL:
                strFolder = "techeval";
                break;
            case UploadFileUtil.ATTACH_FILE_COMEVAL:
                strFolder = "comeval";
                break;
            case UploadFileUtil.ATTACH_FILE_BIDSUM:
                strFolder = "bidsum";
                break;
            case UploadFileUtil.ATTACH_FILE_REQUIREVERIFIED:
                strFolder = "requireverified";
            case UploadFileUtil.ATTACH_FILE_EQUIPMENT:
                strFolder = "equipment";
                break;
            case UploadFileUtil.ATTACH_FILE_HANDED_REPORT:
                strFolder = "handed_report";
                break;
            case UploadFileUtil.ATTACH_FILE_REPORT_DAMAGE:
                strFolder = "report_damage";
                break;
            case UploadFileUtil.ATTACH_FILE_REQUIRE_REPAIR:
                strFolder = "require_repair";
                break;
            case UploadFileUtil.ATTACH_FILE_SURVEY_REPORT:
                strFolder = "survey_report";
                break;
            case UploadFileUtil.ATTACH_FILE_REQUIRE_SETTLING:
                strFolder = "require_settling";
                break;
            case UploadFileUtil.ATTACH_FILE_ACCEPTANCE_TEST:
                strFolder = "acceptance_test";
                break;
        }
        return strFolder;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean result = doAction(mapping, form, request, response);
        if (!result) {
            UploadFormBean formBean = (UploadFormBean) form;
            FormFile fileForm = formBean.getTheFile0();
            if (fileForm != null) {
                OutputUtil.sendStringToOutput(response, "<textarea>[{\"file\":\"" + fileForm.getFileName() + "\",\"type\":\"error\"}]</textarea>");
            } else {
                OutputUtil.sendStringToOutput(response, "<textarea>[{\"file\":\"undefined\",\"type\":\"error\"}]</textarea>");
            }
            //OutputUtil.sendStringToOutput(response, "<textarea>[{file:\"" + fileForm.getFileName() + "\",type:\"error\"}]</textarea>");
            return null;
        }
        return doActionResult(result, mapping, request, response);

    }

    @Override
    protected boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            UploadFormBean formBean = (UploadFormBean) form;
            FormFile formFile = formBean.getTheFile0();
            String userName = MCUtil.getMemberName(session);
            if (formFile == null
                    || GenericValidator.isBlankOrNull(userName)) {
                return false;
            }
            String strUploadFolder = UploadFileUtil.getUploadFolder(formBean.ftype);
            String fileName = formFile.getFileName();
            String pathName = request.getSession().getServletContext().getRealPath("/")
                    + MCUtil.storageRoot + "\\" + userName;
            if (!GenericValidator.isBlankOrNull(strUploadFolder)) {
                pathName += "\\" + strUploadFolder;
            }
            //create folder
            MCUtil.createFullFolder(pathName);
            pathName += "\\" + fileName;
            String fullFileName = FileUtil.getFileName(pathName);
            //retrieve the file data
            InputStream stream = formFile.getInputStream();
            //save the file to the file specified
            FileUtil.saveFile(stream, fullFileName);
            //save in database
            FileAttachmentBean fileBean = new FileAttachmentBean();
            fileBean.setFtype(formBean.getFtype());
            fileBean.setPid(formBean.getPid());
            fileBean.setCreatedEmp(MCUtil.getMemberID(session));
            fileBean.setAttName(fileName);
            fileBean.setHref(fullFileName.substring(fullFileName.indexOf(MCUtil.storageRoot)));
            fileBean.setSource(fullFileName);
            fileBean.setFileSize(formFile.getFileSize());
            fileBean.setComments(formBean.getComments());
            FileAttachmentDAO fileAttachmentDAO = new FileAttachmentDAO();
            if (formBean.getComments() != null)
            if (formBean.getComments().length() > 1) {                
                String source = "";
                source = fileAttachmentDAO.deleteAttachmentAFile(formBean.getFtype(), formBean.getPid(), formBean.getComments());
                FileUtil.deleteFile(source);
                //save the file to the file specified
                stream = formFile.getInputStream();
                FileUtil.saveFile(stream, fullFileName);
            }
            fileAttachmentDAO.insertAttachmentFile(fileBean);
            String json = "<textarea>[{\"file\":\"" + formFile.getFileName() + "\""
                    + ",\"name\":\"" + formFile.getFileName() + "\""
                    + ",\"type\":\"" + FileUtil.getFileExt(formFile.getFileName()) + "\""
                    + ",\"size\":" + formFile.getFileSize()
                    + "}]</textarea>";
            OutputUtil.sendStringToOutput(response, json);
            return true;
        } catch (Exception ex) {
            LogUtil.error(UploadFileAction.class.getName() + ": " + ex.getMessage());
        }
        return false;
    }

    protected String saveFile(FormFile formFile) throws Exception {
        //FileUtil.createFullFolder(strName);
        //Following code can be used to save the uploaded file
        String fullFileName = FileUtil.getFileName(formFile.getFileName());
        //retrieve the file representation
        try {
            //retrieve the file data            
            InputStream stream = formFile.getInputStream();
            //write the file to the file specified
            OutputStream bos = new FileOutputStream(fullFileName);
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = stream.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.close();
            //close the stream
            stream.close();
        } catch (FileNotFoundException fnfe) {
            //throw new UploadException(fnfe.getMessage());
            //LogUtil.error(fnfe.getMessage());
        } catch (IOException ioe) {
            //throw new UploadException(ioe.getMessage());
            //LogUtil.error(ioe.getMessage());
        }
        return fullFileName;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
