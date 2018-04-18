/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.employee;

import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.PositionDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kngo
 */
public class SearchAdvEmployeeFormAction extends SpineAction {

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

        HttpSession session = request.getSession();
        EmployeeBean bean = new EmployeeBean();
        request.setAttribute(Constants.EMPLOYEE, bean);

        ArrayList arrOrganization = new ArrayList();
        try {
            OrganizationDAO organizationDAO = new OrganizationDAO();
            arrOrganization = organizationDAO.getOrganizations();
        } catch (Exception ex) {
        }
        request.setAttribute(Constants.ORGANIZATION_LIST, arrOrganization);

        ArrayList arrPosition = new ArrayList();
        try {
            PositionDAO positionDAO = new PositionDAO();
            arrPosition = positionDAO.getPositions();
        } catch (Exception ex) {
        }
        request.setAttribute(Constants.POSITION_LIST, arrPosition);

        return true;
    }
}
