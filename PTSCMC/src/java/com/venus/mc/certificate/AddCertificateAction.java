/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.certificate;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.CertificateBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.CertificateDAO;
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
 * @author Mai Vinh Loc
 */
public class AddCertificateAction extends SpineAction {

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
        CertificateFormBean formBean = (CertificateFormBean) form;
        CertificateDAO cerDAO = new CertificateDAO();
        CertificateBean bean = null;
        boolean bNew = false;
        boolean isExist = false;

        try {
            bean = cerDAO.getCertificateByName(formBean.getCerName());
        } catch (Exception ex) {
        }

        int cerId = formBean.getCerId();
        if (cerId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getCerId() != cerId) {
                isExist = true;
            }
       }
        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("webExisted", new ActionMessage("errors.web.existed"));
            saveErrors(request, errors);
            return false;
        }

        bean = new CertificateBean();
        bean.setCerId(formBean.getCerId());
        bean.setCerName(formBean.getCerName());

        try {
            if (bNew) {
                cerDAO.insertCertificate(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                cerDAO.updateCertificate(bean);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Certificate:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }
}
