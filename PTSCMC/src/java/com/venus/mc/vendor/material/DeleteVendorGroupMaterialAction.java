/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.vendor.material;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class DeleteVendorGroupMaterialAction extends SpineAction {

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
        String[] arrGroupMaterialid = request.getParameterValues("vengroupmaterialid");
        try {
            OnlineUser user = MCUtil.getOnlineUser(session);
            LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
            int length = 0;
            VendorDAO vendorDAO = new VendorDAO();
            if (arrGroupMaterialid != null) {
                length = arrGroupMaterialid.length;
            }
            for (int i = 0; i < length; i++) {
                vendorDAO.deleteVendorGroupMaterial(arrGroupMaterialid[i]);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:VenderGroupMaterial:delete-" + ex.getMessage());
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
        return PermissionUtil.PER_LIBRARY_VENDOR;
    }
}
