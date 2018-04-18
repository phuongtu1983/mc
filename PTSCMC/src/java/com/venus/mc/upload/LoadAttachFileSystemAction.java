/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.upload;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.dao.FileAttachmentDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author thcao
 */
public class LoadAttachFileSystemAction extends org.apache.struts.action.Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int ftype = NumberUtil.parseInt(MCUtil.getParameter("ftype", request), 0);
        int pid = NumberUtil.parseInt(MCUtil.getParameter("pid", request), 0);        
        if (UploadFileUtil.isValidFType(ftype)) {
            try {
                FileAttachmentDAO fileAttachmentDAO = new FileAttachmentDAO();
                ArrayList files = fileAttachmentDAO.getAttachmentFiles(ftype, pid);
                request.setAttribute(Constants.ATTACH_FILE_LIST, files);
            } catch (Exception ex) {
                LogUtil.error(LoadAttachFileSystemAction.class.getName() + ": " +  ex.getMessage());                          
            }
        } else {
            ArrayList files = new ArrayList();
            request.setAttribute(Constants.ATTACH_FILE_LIST, files);            
        }
        return mapping.findForward("success");

    }
}
