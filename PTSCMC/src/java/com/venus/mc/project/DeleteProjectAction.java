/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.project;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ProjectDAO;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.database.DBUtil;
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
public class DeleteProjectAction extends SpineAction {

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
        String[] arrProId = request.getParameterValues("proId");
        try {
            OnlineUser user = MCUtil.getOnlineUser(session);
            LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
            int length = 0, proId;
            ProjectDAO projectDAO = new ProjectDAO();
            if (arrProId != null) {
                length = arrProId.length;
            }
            for (int i = 0; i < length; i++) {
                proId = NumberUtil.parseInt(arrProId[i], 0);
                if (proId > 0) {
                    if (DBUtil.checkFieldExisted("request", "org_id", proId)) {
                        ActionMessages errors = new ActionMessages();
                        errors.add("delExisted", new ActionMessage("errors.project.delExisted"));
                        saveErrors(request, errors);
                        return false;
                    } else {
                        StoreDAO storeDAO = new StoreDAO();
                        storeDAO.deleteStoreByProId(proId);
                        projectDAO.deleteProject(proId);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_DELETE + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_LIBRARY_PROJECT;
    }
}
