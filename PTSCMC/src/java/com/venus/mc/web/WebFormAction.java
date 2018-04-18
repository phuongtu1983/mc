/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.web;

import com.venus.mc.bean.WebBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.WebDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kngo
 */
public class WebFormAction extends SpineAction {

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
        WebBean bean = new WebBean();
        String webid = request.getParameter("webId");
        if (!GenericValidator.isBlankOrNull(webid)) {
            WebDAO webDAO = new WebDAO();
            try {
                bean = webDAO.getWeb(Integer.parseInt(webid));
            } catch (Exception ex) {
            }
        }
        request.setAttribute(Constants.WEB, bean);
        return true;
    }

    @Override
    protected String getXmlMessage() {
        return "webDetail";
    }

    @Override
    protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("webId"))) {
            return request.getParameter("webId");
        } else {
            return "";
        }
    }
}
