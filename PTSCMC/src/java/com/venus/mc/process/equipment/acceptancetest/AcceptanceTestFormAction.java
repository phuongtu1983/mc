/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.acceptancetest;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.AcceptanceTestBean;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.AcceptanceTestDAO;
import com.venus.mc.dao.EmployeeDAO;
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
 * @author mai vinh loc
 */
public class AcceptanceTestFormAction extends SpineAction {

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
        AcceptanceTestFormBean formBean = null;
        AcceptanceTestBean bean = null;
        String atId = request.getParameter("atId");
        Integer id = (Integer) session.getAttribute("id");
        //session.removeAttribute("id");
        if (id != null) {
            atId = id + "";
        }
        if (!GenericValidator.isBlankOrNull(atId)) {
            AcceptanceTestDAO requestDAO = new AcceptanceTestDAO();
            try {
                bean = requestDAO.getAcceptanceTest(Integer.parseInt(atId));
                if (bean != null) {
                    formBean = new AcceptanceTestFormBean(bean);
                }

            } catch (Exception ex) {
            }
        }
        if (formBean == null) {
            formBean = new AcceptanceTestFormBean();
            formBean.setCreatedEmp(MCUtil.getMemberID(session));
            formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
            formBean.setTestDate(DateUtil.today("dd/MM/yyyy"));
        }

        request.setAttribute(Constants.ACCEPTANCETEST, formBean);

        //Employee
        EmployeeDAO employeeDAO = new EmployeeDAO();
        ArrayList employeeList = null;
        try {
            employeeList = employeeDAO.getEmployees();
        } catch (Exception ex) {
        }
        ArrayList arrEmployee = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.employee.select"));
        value.setValue("0");
        arrEmployee.add(value);
        for (int i = 0; i < employeeList.size(); i++) {
            EmployeeBean employee = (EmployeeBean) employeeList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(employee.getFullname())));
            value.setValue(String.valueOf(employee.getEmpId()));
            arrEmployee.add(value);
        }
        request.setAttribute(Constants.EMPLOYEE_LIST, arrEmployee);


        ArrayList arrStatus = new ArrayList();
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.result.select"));
        value.setValue("0");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.acceptancetest.result1"));
        value.setValue(AcceptanceTestBean.RESULT1 + "");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.acceptancetest.result2"));
        value.setValue(AcceptanceTestBean.RESULT2 + "");
        arrStatus.add(value);
        request.setAttribute(Constants.RESULT_LIST, arrStatus);

        ArrayList srList;
        AcceptanceTestDAO requestDAO = new AcceptanceTestDAO();
        try {
            srList = requestDAO.getSrListOfOrg(MCUtil.getOrganizationID(session));
            srList.add(0, new LabelValueBean(MCUtil.getBundleString("message.surveyreport.select"), "0"));
            request.setAttribute(Constants.SURVEYREPORT_LIST, srList);
        } catch (Exception ex) {
        }

        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_EQUIPMENT_SURVEYREPORT;
    }
}
