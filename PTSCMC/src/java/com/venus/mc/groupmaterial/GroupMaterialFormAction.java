/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.groupmaterial;

import com.venus.mc.bean.GroupMaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.GroupMaterialDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/**
 *
 * @author kngo
 */
public class GroupMaterialFormAction extends SpineAction {

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
        GroupMaterialBean bean = new GroupMaterialBean();
        String groId = request.getParameter("groId");
        if (!GenericValidator.isBlankOrNull(groId)) {
            GroupMaterialDAO groupMaterialDAO = new GroupMaterialDAO();
            try {
                bean = groupMaterialDAO.getGroupMaterial(Integer.parseInt(groId));
            } catch (Exception ex) {
            }
        }
        request.setAttribute(Constants.GROUPMATERIAL, bean);
        return true;
    }

    @Override
    protected String getXmlMessage() {
        return "groupMaterialDetail";
    }

    @Override
    protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("groId"))) {
            return request.getParameter("groId");
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
        return PermissionUtil.PER_LIBRARY_VENDOR;
    }
}
