/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.project.store;

import com.venus.mc.bean.StoreBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.StoreDAO;
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
public class ProjectStoreFormAction extends SpineAction {

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
        StoreBean bean = null;
        String stoId = request.getParameter("stoId");
        if (!GenericValidator.isBlankOrNull(stoId)) {
            StoreDAO storeDAO = new StoreDAO();
            try {
                bean = storeDAO.getStore(Integer.parseInt(stoId));
            } catch (Exception ex) {
            }
        }
        if (bean == null) {
            String proId = request.getParameter("proId");
            if (!GenericValidator.isBlankOrNull(proId)) {
                bean = new StoreBean();
                bean.setProId(Integer.parseInt(proId));
            }
        }
        request.setAttribute(Constants.PROJECT_STORE, bean);

//        ArrayList arrProject = new ArrayList();
//        try {
//            ProjectDAO projectDAO = new ProjectDAO();
//            arrProject = projectDAO.getProjects();
//        } catch (Exception ex) {
//
//        }
//
//        request.setAttribute(Constants.PROJECT_LIST, arrProject);
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
        return PermissionUtil.PER_LIBRARY_PROJECT;
    }
}
