/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.certificate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.CertificateDAO;
import java.util.ArrayList;
/**
 *
 * @author Mai Vinh Loc
 */
public class ListCertificateAction extends SpineAction {

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
        CertificateDAO cerDAO = new CertificateDAO();
        ArrayList cerList = null;
        try {
            cerList = cerDAO.getCertificates();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        request.setAttribute(Constants.CER_LIST, cerList);
        return true;
    }
}
