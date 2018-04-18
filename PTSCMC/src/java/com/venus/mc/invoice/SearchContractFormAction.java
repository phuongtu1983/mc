/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.invoice;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.bean.ProjectBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.contract.ContractFormBean;
import com.venus.mc.contract.SearchAdvContractFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.ProjectDAO;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author phuongtu
 */
public class SearchContractFormAction extends SpineAction {

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
        SearchAdvContractFormBean formBean = new SearchAdvContractFormBean();
        int kind = 0;
        if (!GenericValidator.isBlankOrNull(request.getParameter("kind"))) {
            kind = Integer.parseInt(request.getParameter("kind"));
        }
        formBean.setKind(kind);
        if (!GenericValidator.isBlankOrNull(request.getParameter("isprint"))) {
            formBean.setIsPrint(Integer.parseInt(request.getParameter("isprint")));
        }
        request.setAttribute(Constants.CONTRACT, formBean);

        ArrayList vendorList = null;
        try {
            VendorDAO vendorDAO = new VendorDAO();
            vendorList = vendorDAO.getVendors();
        } catch (Exception ex) {
        }
        if (vendorList == null) {
            vendorList = new ArrayList();
        }
        vendorList.add(0, new VendorBean(0));
        request.setAttribute(Constants.VENDOR_LIST, vendorList);

        ArrayList arrStatus = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.select"));
        value.setValue("0");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.contract.status.approved"));
        value.setValue(ContractFormBean.STATUS_APPROVED + "");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.contract.status.approving"));
        value.setValue(ContractFormBean.STATUS_APPROVING + "");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.contract.status.processing"));
        value.setValue(ContractFormBean.STATUS_PROCESSING + "");
        arrStatus.add(value);
        request.setAttribute(Constants.STATUS_LIST, arrStatus);

        ArrayList arrEmp = new ArrayList();
        try {
            EmployeeDAO empDAO = new EmployeeDAO();
            arrEmp = empDAO.getEmployees();
        } catch (Exception ex) {
        }
        if (arrEmp == null) {
            arrEmp = new ArrayList();
        }
        arrEmp.add(0, new EmployeeBean());
        request.setAttribute(Constants.EMPLOYEE_LIST, arrEmp);

        ArrayList arrProject = new ArrayList();
        try {
            ProjectDAO projectDAO = new ProjectDAO();
            arrProject = projectDAO.getProjectsForStore();
        } catch (Exception ex) {
        }
        if (arrProject == null) {
            arrProject = new ArrayList();
        }
        arrProject.add(0, new ProjectBean());
        request.setAttribute(Constants.PROJECT_LIST, arrProject);

        return true;
    }
}
