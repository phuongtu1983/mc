/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.store;

import com.venus.mc.bean.StoreBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.ProjectDAO;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author kngo
 */
public class StoreFormAction extends SpineAction {

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

//        HttpSession session = request.getSession();
        ArrayList arrStatus = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.store.kind1"));
        value.setValue("1");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.store.kind2"));
        value.setValue("2");
        arrStatus.add(value);
        request.setAttribute(Constants.STORE_KIND_LIST, arrStatus);
        
        StoreBean bean = new StoreBean();
        String storeid = request.getParameter("stoId");
        if (!GenericValidator.isBlankOrNull(storeid)) {
            StoreDAO storeDAO = new StoreDAO();
            try {
                bean = storeDAO.getStore(Integer.parseInt(storeid));
            } catch (Exception ex) {
            }
        }
        request.setAttribute(Constants.STORE, bean);

        ArrayList arrProject = new ArrayList();
        try {
            ProjectDAO projectDAO = new ProjectDAO();
            arrProject = projectDAO.getProjectsForStore();
        } catch (Exception ex) {
        }
        request.setAttribute(Constants.PROJECT_LIST, arrProject);
        
        ArrayList arrEmployee = new ArrayList();
        try {
            EmployeeDAO empDAO = new EmployeeDAO();
            arrEmployee = empDAO.getEmployees();
        } catch (Exception ex) {
        }
        request.setAttribute(Constants.EMPLOYEE_LIST, arrEmployee);
        
        return true;
    }

    @Override
    protected String getXmlMessage() {
        return "storeDetail";
    }

    @Override
    protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("stoId"))) {
            return request.getParameter("stoId");
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
        return PermissionUtil.PER_LIBRARY_STORE;
    }
}


