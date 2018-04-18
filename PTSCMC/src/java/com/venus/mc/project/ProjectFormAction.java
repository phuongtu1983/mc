/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.project;

import com.venus.mc.bean.ProjectBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ProjectDAO;
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
public class ProjectFormAction extends SpineAction {
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
        value.setLabel(MCUtil.getBundleString("message.project.status1"));
        value.setValue("0");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.project.status2"));
        value.setValue("1");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.project.status3"));
        value.setValue("2");
        arrStatus.add(value);
        request.setAttribute(Constants.PROJECT_STATUS_LIST, arrStatus);
        
        ProjectBean bean = new ProjectBean();
        String proId = request.getParameter("proId");
        if (!GenericValidator.isBlankOrNull(proId)) {
            ProjectDAO projectDAO = new ProjectDAO();
            try {
                bean = projectDAO.getProject(Integer.parseInt(proId));
            } catch (Exception ex) {
            }
        }
        request.setAttribute(Constants.PROJECT, bean);
        
//        OrganizationBean bean = new OrganizationBean();
//        String proId = request.getParameter("proId");
//        if (!GenericValidator.isBlankOrNull(proId)) {
//            OrganizationDAO organizationDAO = new OrganizationDAO();
//            try {
//                bean = organizationDAO.getOrganization(Integer.parseInt(proId));
//            } catch (Exception ex) {
//            }
//        }
//        request.setAttribute(Constants.PROJECT, bean);
        
        return true;
    }
    
      @Override
    protected String getXmlMessage() {
        return "projectDetail";
    }

    @Override
    protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("proId"))) {
            return request.getParameter("proId");
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
        return PermissionUtil.PER_LIBRARY_PROJECT;
    }
}
