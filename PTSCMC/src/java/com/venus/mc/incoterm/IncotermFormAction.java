/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.incoterm;

import com.venus.mc.bean.IncotermBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.IncotermDAO;
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
public class IncotermFormAction extends SpineAction {

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
        IncotermBean bean = new IncotermBean();
        String incId = request.getParameter("incId");
        if (!GenericValidator.isBlankOrNull(incId)) {
            IncotermDAO incDAO = new IncotermDAO();
            try {
                bean = incDAO.getIncoterm(Integer.parseInt(incId));
            } catch (Exception ex) {
            }
        }
        request.setAttribute(Constants.INC, bean);
        return true;
    }

    @Override
    protected String getXmlMessage() {
        return "incDetail";
    }

    @Override
    protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("incId"))) {
            return request.getParameter("incId");
        } else {
            return "";
        }
    }
}
