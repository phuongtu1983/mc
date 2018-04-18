/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.groupmaterial;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.GroupMaterialDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class ListGroupMaterialAction extends SpineAction {

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
        GroupMaterialDAO groupMaterialDAO = new GroupMaterialDAO();
        ArrayList groupMaterialList = null;
        try {
            groupMaterialList = groupMaterialDAO.getGroupMaterials();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        request.setAttribute(Constants.GROUPMATERIAL_LIST, groupMaterialList);
        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_LIST + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_LIBRARY_VENDOR;
    }
}
