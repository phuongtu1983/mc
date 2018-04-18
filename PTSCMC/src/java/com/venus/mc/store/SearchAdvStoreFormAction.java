/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.store;

import com.venus.mc.bean.ProjectBean;
import com.venus.mc.bean.StoreBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ProjectDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author kngo
 */
public class SearchAdvStoreFormAction extends SpineAction {

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
        value.setLabel("");
        value.setValue("0");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.store.kind1"));
        value.setValue("1");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.store.kind2"));
        value.setValue("2");
        arrStatus.add(value);
        request.setAttribute(Constants.STORE_KIND_LIST, arrStatus);

        ArrayList arrProject = new ArrayList();
        ProjectDAO projectDAO = new ProjectDAO();
        try {
            arrProject = projectDAO.getProjects();
        } catch (Exception ex) {
        }
        
        ProjectBean proBean = new ProjectBean();
        proBean.setProId(0);
        proBean.setName("");
        arrProject.add(0, proBean);
        request.setAttribute(Constants.PROJECT_LIST, arrProject);

        StoreBean bean = new StoreBean();
        request.setAttribute(Constants.STORE, bean);
        return true;
    }
}
