/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.handedreport;

import com.venus.core.util.DateUtil;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.HandedReportBean;
import com.venus.mc.core.SpineAction;

import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.HandedReportDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.RequireTransferDAO;
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
public class HandedReportFormAction extends SpineAction {

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
        HandedReportFormBean formBean = null;
        HandedReportBean bean = null;
        String hrId = request.getParameter("hrId");
        Integer id = (Integer) session.getAttribute("id");
        //session.removeAttribute("id");
        if (id != null) {
            hrId = id + "";
        }
        
        if (!GenericValidator.isBlankOrNull(hrId)) {
            HandedReportDAO hrDAO = new HandedReportDAO();
            try {
                bean = hrDAO.getHandedReport(Integer.parseInt(hrId));
                if (bean != null) {
                    formBean = new HandedReportFormBean(bean);
                }

            } catch (Exception ex) {
                LogUtil.error(getClass(), ex.getMessage());
            }
        }
        if (formBean == null) {
            formBean = new HandedReportFormBean();
            formBean.setCreatedEmpId(MCUtil.getMemberID(session));
            formBean.setCreatedEmpName(MCUtil.getMemberFullName(session));
            formBean.setOrgId(MCUtil.getOrganizationID(session));
            formBean.setOrgName(MCUtil.getOrganizationName(session));
            formBean.setOrgReceiveId(MCUtil.getOrganizationID(session));
            formBean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
        }
        OrganizationDAO orgDAO = new OrganizationDAO();
        try {
            ArrayList orgList = orgDAO.getOrganizationList();
            orgList.add(0, new LabelValueBean(MCUtil.getBundleString("message.organization.select"), "0"));
            request.setAttribute(Constants.ORG_LIST, orgList);
        } catch (Exception ex) {
            LogUtil.error(getClass(), ex.getMessage());
        }
        ArrayList rtList;
        RequireTransferDAO rtDAO = new RequireTransferDAO();
        try {
            rtList = rtDAO.getRequireTransferListOfOrg(formBean.getOrgReceiveId());
            rtList.add(0, new LabelValueBean(MCUtil.getBundleString("message.handedreport.selectrtnumber"), "0"));
            request.setAttribute(Constants.REQUIRETRANSFER_LIST, rtList);
        } catch (Exception ex) {
            LogUtil.error(getClass(), ex.getMessage());
        }
        ArrayList empList;
        EmployeeDAO empDAO = new EmployeeDAO();
        try {
            empList = empDAO.getEmployeeListByOrg(formBean.getOrgReceiveId());
            empList.add(0, new LabelValueBean(MCUtil.getBundleString("message.employee.select"), "0"));
            request.setAttribute(Constants.EMPLOYEE_LIST, empList);
        } catch (Exception ex) {
            LogUtil.error(getClass(), ex.getMessage());
        }
        request.setAttribute(Constants.HANDEDREPORT, formBean);
        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST;
    }
}
