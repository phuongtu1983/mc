/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.upload;

import com.venus.core.util.FileUtil;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.FileAttachmentBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.FileAttachmentDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author thcao
 */
public class RemoveFileAction extends SpineAction {

    @Override
    protected boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        int res = 0;
        try {
            int ftype = NumberUtil.parseInt(MCUtil.getParameter("ftype", request), 0);
            int pid = NumberUtil.parseInt(MCUtil.getParameter("pid", request), 0);
            int fid = NumberUtil.parseInt(MCUtil.getParameter("fid", request), 0);
            if (UploadFileUtil.isValidFType(ftype)) {
                FileAttachmentDAO fileAttachmentDAO = new FileAttachmentDAO();
                FileAttachmentBean fileBean = fileAttachmentDAO.getAttachmentFile(ftype,fid);
                FileUtil.deleteFile(fileBean.getSource());
                res = fileAttachmentDAO.deleteAttachmentFile(ftype,fid);
            }
        } catch (Exception ex) {
            LogUtil.error(GetFileAction.class.getName() + ": " +  ex.getMessage());          
        }
        if (res > 0) {
            OutputUtil.sendStringToOutput(response, "success");
            return true;
        } else {            
            return false;
        }
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
