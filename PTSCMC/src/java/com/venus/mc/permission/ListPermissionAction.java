/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.permission;

import com.venus.core.util.LogUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.PermissionDAO;
import java.util.ArrayList;

/**
 *
 * @author phuontu
 * @version
 */
public class ListPermissionAction extends SpineAction {

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
        PermissionDAO permissionDAO = new PermissionDAO();
        ArrayList permissionList = null;
        try {
            permissionList = permissionDAO.getPermissions();
        } catch (Exception ex) {
            LogUtil.error("FAILED:Permission:list-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.PERMISSION_LIST, permissionList);
        return true;
    }
}
