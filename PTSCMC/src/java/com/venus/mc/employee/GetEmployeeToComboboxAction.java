/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;

/**
 *
 * @author phuongtu
 * @version
 */
public class GetEmployeeToComboboxAction extends SpineAction {

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
        String name = request.getParameter("name");
        EmployeeDAO empDAO = new EmployeeDAO();
        OrganizationDAO orgDAO = new OrganizationDAO();
        ArrayList employeeList = null;
        try {
            int orgId = MCUtil.getOrganizationID(request.getSession());
            String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
            orgs += "," + orgId;
            empDAO.setRequestOrg(orgs);
            employeeList = empDAO.getEmployeesByNames(name);
        } catch (Exception ex) {
        }
        if (employeeList == null) {
            employeeList = new ArrayList();
        }
        request.setAttribute(Constants.EMPLOYEE_LIST, employeeList);
        return true;
    }
}
