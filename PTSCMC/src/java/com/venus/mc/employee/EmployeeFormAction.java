/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.employee;

import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.PositionDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author kngo
 */
public class EmployeeFormAction extends SpineAction {

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
        String employeeid = request.getParameter("empId");
        if (!GenericValidator.isBlankOrNull(employeeid)) {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            try {
                bean = employeeDAO.getEmployee(Integer.parseInt(employeeid));
            } catch (Exception ex) {
            }
        }
        request.setAttribute(Constants.EMPLOYEE, bean);

        ArrayList arrOrganization = new ArrayList();
        try {
            OrganizationDAO organizationDAO = new OrganizationDAO();
            arrOrganization = organizationDAO.getOrgExceptKind(OrganizationBean.KIND_PROJECT + "");
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

        ArrayList arrStatus = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.employee.status1"));
        value.setValue("1");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.employee.status2"));
        value.setValue("2");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.employee.status3"));
        value.setValue("3");
        arrStatus.add(value);
        request.setAttribute(Constants.EMPLOYEE_STATUS_LIST, arrStatus);
        return true;
    }

    @Override
    protected String getXmlMessage() {
        return "employeeDetail";
    }

    @Override
    protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("empId"))) {
            return request.getParameter("empId");
        } else {
            return "";
        }
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_SYSTEM;
    }
}
