/*
 *
 * Created on April 14, 2007, 8:49 AM
 */
package com.venus.mc.contract;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.bean.ProjectBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.ProjectDAO;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author HOANG DIEU
 * @version
 */
public class SearchAdvContractFormAction extends SpineAction {

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

        LabelValueBean value;

        ArrayList arrProcessStatus = new ArrayList();
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.select"));
        value.setValue("0");
        arrProcessStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.contract.process.status.notDeliver"));
        value.setValue(ContractFormBean.PROCESS_STATUS_NOTDELIVER + "");
        arrProcessStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.contract.process.status.notEnough"));
        value.setValue(ContractFormBean.PROCESS_STATUS_NOTENOUGH + "");
        arrProcessStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.contract.process.status.deliveried"));
        value.setValue(ContractFormBean.PROCESS_STATUS_DELIVERIED + "");
        arrProcessStatus.add(value);
        request.setAttribute(Constants.STATUS_LIST, arrProcessStatus);

        ArrayList arrEmp = new ArrayList();
        try {
            String orgId = "";
            orgId = MCUtil.getOrganizationID(request.getSession()) + "";
            String orgs = "";
            try {
                OrganizationDAO orgDAO = new OrganizationDAO();
                orgs = orgDAO.getnestedChildOfOrg(orgId + "");
            } catch (Exception ex) {
            }
            orgs += "," + orgId;
            EmployeeDAO empDAO = new EmployeeDAO();
            empDAO.setRequestOrg(orgs);
//            arrEmp = empDAO.getEmployees();
            arrEmp = empDAO.getEmployeesHasPermission(PermissionUtil.PER_PURCHASING_CONTRACT + "," + PermissionUtil.PER_PURCHASING_PRINCIPLE 
                    + "," + PermissionUtil.PER_PURCHASING_CONTRACT_FOLLOW + "," + PermissionUtil.PER_PURCHASING_CONTRACT_EXECUTE);
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
