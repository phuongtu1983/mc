/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.project;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.ProjectBean;
import com.venus.mc.bean.StoreBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ProjectDAO;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 *
 * @author kngo
 */
public class AddProjectAction extends SpineAction {

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
        ProjectFormBean formBean = (ProjectFormBean) form;
        ProjectDAO projectDAO = new ProjectDAO();
        ProjectBean bean = null;
        boolean bNew = false;
        boolean isExist = false;

        try {
            bean = projectDAO.getProjectByName(formBean.getName());
        } catch (Exception ex) {
        }

        int proId = formBean.getProId();
        if (proId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getProId() != proId) {
                isExist = true;
            }
        }

        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("projectExisted", new ActionMessage("errors.project.existed"));
            saveErrors(request, errors);
            return false;
        }
        bean = new ProjectBean();
        bean.setProId(formBean.getProId());
        bean.setName(formBean.getName());
        bean.setName_en(formBean.getName_en());
        bean.setStartDate(formBean.getStartDate());
        bean.setEndDate(formBean.getEndDate());
        bean.setQc_name(formBean.getQc_name());
        bean.setInvestorsName(formBean.getInvestorsName());
        bean.setComments(formBean.getComments());
        bean.setStatus(formBean.getStatus());
        bean.setAbbreviate(formBean.getAbbreviate());

        try {
            if (bNew) {
                proId = projectDAO.insertProject(bean);

                StoreDAO storeDAO = new StoreDAO();
                StoreBean storeBean = new StoreBean();
                storeBean.setProId(proId);
                storeBean.setName("Kho " + formBean.getName());
                storeBean.setKind(1);
                storeDAO.insertStore(storeBean);
            } else {
                HttpSession session = request.getSession();
                StoreDAO storeDAO = new StoreDAO();
                StoreBean storeBean = new StoreBean();
                storeBean.setProId(proId);
                storeBean.setName("Kho " + formBean.getName());
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                projectDAO.updateProject(bean);
                storeDAO.updateNameStore(storeBean);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Project:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_EDIT + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_LIBRARY_PROJECT;
    }
}
