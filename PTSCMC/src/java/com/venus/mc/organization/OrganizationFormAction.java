/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.organization;

import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
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
public class OrganizationFormAction extends SpineAction {

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
        value.setLabel(MCUtil.getBundleString("message.organization.status1"));
        value.setValue("1");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.organization.status2"));
        value.setValue("2");
        arrStatus.add(value);
        request.setAttribute(Constants.ORGANIZATION_STATUS_LIST, arrStatus);

        arrStatus = new ArrayList();
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.organization.kind1"));
        value.setValue("1");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.organization.kind2"));
        value.setValue("2");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.organization.kind3"));
        value.setValue("3");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.organization.kind4"));
        value.setValue("4");
        arrStatus.add(value);
        request.setAttribute(Constants.ORGANIZATION_KIND_LIST, arrStatus);

        OrganizationBean bean = new OrganizationBean();
        String organizationid = request.getParameter("orgId");
        if (!GenericValidator.isBlankOrNull(organizationid)) {
            OrganizationDAO organizationDAO = new OrganizationDAO();
            try {
                bean = organizationDAO.getOrganization(Integer.parseInt(organizationid));
            } catch (Exception ex) {
            }
        }
        request.setAttribute(Constants.ORGANIZATION, bean);

        ArrayList arrParent = new ArrayList();
        try {
            OrganizationDAO parentDAO = new OrganizationDAO();
            arrParent = parentDAO.getOrgByKind(99);
        } catch (Exception ex) {
        }
        OrganizationBean orgBean = new OrganizationBean();
        orgBean.setOrgId(0);
        orgBean.setName("");
        arrParent.add(0, orgBean);
        request.setAttribute(Constants.ORGANIZATION_PARENT, arrParent);

//        ArrayList arrProject = new ArrayList();
//        try {
//            ProjectDAO projectDAO = new ProjectDAO();
//            arrProject = projectDAO.getProjects();
//        } catch (Exception ex) {
//
//        }
//        request.setAttribute(Constants.PROJECT_LIST, arrProject);
        return true;
    }

    @Override
    protected String getXmlMessage() {
        return "organizationDetail";
    }

    @Override
    protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("orgId"))) {
            return request.getParameter("orgId");
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
