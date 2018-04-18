/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.web;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.WebBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.WebDAO;
import com.venus.mc.dao.WebDAO;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
/**
 *
 * @author kngo
 */
public class AddWebAction extends SpineAction {

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        WebFormBean formBean = (WebFormBean) form;
        WebDAO webDAO = new WebDAO();
        WebBean bean = null;
        boolean bNew = false;
        boolean isExist = false;

        try {
            bean = webDAO.getWebByAddress(formBean.getAddress());
        } catch (Exception ex) {
        }

        int webId = formBean.getWebId();
        if (webId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getWebId() != webId) {
                isExist = true;
            }
       }
        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("webExisted", new ActionMessage("errors.web.existed"));
            saveErrors(request, errors);
            return false;
        }

        bean = new WebBean();
        bean.setWebId(formBean.getWebId());
        bean.setAddress(formBean.getAddress());
        bean.setContent(formBean.getContent());
        bean.setComment(formBean.getComment());
        bean.setEvaluation(formBean.getEvaluation());

        try {
            if (bNew) {
                webDAO.insertWeb(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                webDAO.updateWeb(bean);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Web:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }
}
