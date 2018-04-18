/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.surveyreport;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.bean.SurveyReportBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.SurveyReportDAO;
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
public class SurveyReportFormAction extends SpineAction {

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
        SurveyReportFormBean formBean = null;
        SurveyReportBean bean = null;
        String srId = request.getParameter("srId");
        Integer id = (Integer) session.getAttribute("id");
        session.removeAttribute("id");
        if (id != null) {
            srId = id + "";
        }
        
        if (!GenericValidator.isBlankOrNull(srId)) {
            SurveyReportDAO requestDAO = new SurveyReportDAO();
            try {
                bean = requestDAO.getSurveyReport(Integer.parseInt(srId));
                if (bean != null) {
                    formBean = new SurveyReportFormBean(bean);
                }

            } catch (Exception ex) {
            }
        }
        if (formBean == null) {
            formBean = new SurveyReportFormBean();
            formBean.setCreatedEmp(MCUtil.getMemberID(session));
            formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
            formBean.setSurveyDate(DateUtil.today("dd/MM/yyyy"));
        }

        request.setAttribute(Constants.SURVEYREPORT, formBean);

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


        ArrayList arrRequest = new ArrayList();
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.request.select"));
        value.setValue("0");
        arrRequest.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.surveyreport.request1"));
        value.setValue(SurveyReportBean.REQUEST1 + "");
        arrRequest.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.surveyreport.request2"));
        value.setValue(SurveyReportBean.REQUEST2 + "");
        arrRequest.add(value);
        request.setAttribute(Constants.REQUEST_LIST, arrRequest);

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
