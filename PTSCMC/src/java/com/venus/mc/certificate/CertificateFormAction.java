/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.certificate;

import com.venus.mc.bean.CertificateBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.CertificateDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Mai Vinh Loc
 */
public class CertificateFormAction extends SpineAction {

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
        CertificateBean bean = new CertificateBean();
        String cerId = request.getParameter("cerId");
        if (!GenericValidator.isBlankOrNull(cerId)) {
            CertificateDAO cerDAO = new CertificateDAO();
            try {
                bean = cerDAO.getCertificate(Integer.parseInt(cerId));
            } catch (Exception ex) {
            }
        }
        request.setAttribute(Constants.CER, bean);
        return true;
    }

    @Override
    protected String getXmlMessage() {
        return "cerDetail";
    }

    @Override
    protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("cerId"))) {
            return request.getParameter("cerId");
        } else {
            return "";
        }
    }
}
