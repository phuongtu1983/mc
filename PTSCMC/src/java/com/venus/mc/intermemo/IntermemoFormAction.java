/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.intermemo;

import com.venus.mc.bean.RequestBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.RequestDAO;
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

/**
 *
 * @author phuongtu
 */
public class IntermemoFormAction extends SpineAction {

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
        IntermemoFormBean formBean = null;
        String reqId = request.getParameter("reqId");
        Integer id = (Integer) session.getAttribute("id");
        //session.removeAttribute("id");
        if (id != null) {
            reqId = id + "";
        }
        if (!GenericValidator.isBlankOrNull(reqId)) {
            RequestDAO requestDAO = new RequestDAO();
            try {
                RequestBean bean = requestDAO.getRequest(Integer.parseInt(reqId));
                if (bean != null) {
                    formBean = new IntermemoFormBean(bean);
                }
            } catch (Exception ex) {
            }
        }
        if (formBean == null) {
            formBean = new IntermemoFormBean();
            formBean.setCreatedEmp(MCUtil.getMemberID(session));
            formBean.setCreatedOrg(MCUtil.getOrganizationID(session));
            formBean.setStatusSuggest(1);
        }

        ArrayList empList = null;
        try {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            empList = employeeDAO.getEmployees();
        } catch (Exception ex) {
        }
        if (empList == null) {
            empList = new ArrayList();
        }
        request.setAttribute(Constants.REQUEST, formBean);
        request.setAttribute(Constants.EMPLOYEE_LIST, empList);

        ArrayList arrOrg = new ArrayList();
        try {
            OrganizationDAO orgDAO = new OrganizationDAO();
            arrOrg = orgDAO.getOrgByKind(0);
        } catch (Exception ex) {
        }
        if (arrOrg == null) {
            arrOrg = new ArrayList();
        }
        request.setAttribute(Constants.ORG_LIST, arrOrg);

        ArrayList arrProject = new ArrayList();
        try {
            OrganizationDAO projectDAO = new OrganizationDAO();
            arrProject = projectDAO.getProjectList();
        } catch (Exception ex) {
        }
        if (arrProject == null) {
            arrProject = new ArrayList();
        }
        request.setAttribute(Constants.PROJECT_LIST, arrProject);

        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_PURCHASING_INTERMEMO;
    }
}
