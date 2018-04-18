/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.upload;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
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
public class GetFileAction extends SpineAction {

    @Override
    protected boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            int ftype = NumberUtil.parseInt(MCUtil.getParameter("ftype", request), 0);
            int pid = NumberUtil.parseInt(MCUtil.getParameter("pid", request), 0);
            int fid = NumberUtil.parseInt(MCUtil.getParameter("fid", request), 0);
            String comments = MCUtil.getParameter("comments", request);
            String furl = "";
            if (fid > 0) {
                if (UploadFileUtil.isValidFType(ftype)) {
                    FileAttachmentDAO fileAttachmentDAO = new FileAttachmentDAO();
                    FileAttachmentBean fileBean = fileAttachmentDAO.getAttachmentFile(ftype, fid);
                    if (fileBean != null) {
                        furl = fileBean.getHref();
                    }
                }
            } else {
                if (UploadFileUtil.isValidFType(ftype)) {
                    FileAttachmentDAO fileAttachmentDAO = new FileAttachmentDAO();
                    FileAttachmentBean fileBean = fileAttachmentDAO.getAttachmentAFile(ftype, pid, comments);
                    if (fileBean != null) {
                        furl = fileBean.getHref();
                    }
                }
            }
            if (!GenericValidator.isBlankOrNull(furl)) {
                request.setAttribute("furl", request.getContextPath() + "/" + furl);
                return true;
            }
        } catch (Exception ex) {
            LogUtil.error(GetFileAction.class.getName() + ": " + ex.getMessage());
        }
        return false;
    }
}
