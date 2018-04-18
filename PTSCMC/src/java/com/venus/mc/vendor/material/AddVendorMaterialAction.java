/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.vendor.material;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.VendorMaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VendorDAO;
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
 * @author phuongtu
 */
public class AddVendorMaterialAction extends SpineAction {

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
        VendorMaterialFormBean formBean = (VendorMaterialFormBean) form;
        VendorDAO vendorDAO = new VendorDAO();
        VendorMaterialBean bean = null;
        int vmid = formBean.getVmId();
        boolean bNew = false;
        boolean isExist = false;
        try {
            bean = vendorDAO.getVendorMaterialByName(formBean.getVenId(), formBean.getNameVn(), formBean.getNameEn());
        } catch (Exception ex) {
        }
        if (vmid == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getVmId() != vmid) {
                isExist = true;
            }
        }
        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("vendoMaterialrExisted", new ActionMessage("errors.vendor.material.existed"));
            saveErrors(request, errors);
            return false;
        }
        HttpSession session = request.getSession();
        bean = new VendorMaterialBean();
        bean.setVmId(formBean.getVmId());
        bean.setVenId(formBean.getVenId());
        bean.setOriId(formBean.getOriId());
        bean.setNameVn(formBean.getNameVn());
        bean.setNameEn(formBean.getNameEn());
        bean.setGroId(formBean.getGroId());
        bean.setManufacturer(formBean.getManufacturer());
        bean.setNote(formBean.getNote());

        try {
            if (bNew) {
                vendorDAO.insertVendorMaterial(bean);
            } else {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                vendorDAO.updateVendorMaterial(bean);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:VenderMaterial:add-" + ex.getMessage());
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
        return PermissionUtil.PER_LIBRARY_VENDOR;
    }
}
