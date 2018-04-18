/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.vendor.contact;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.VendorContactBean;
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
public class AddVendorContactAction extends SpineAction {

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
        VendorContactFormBean formBean = (VendorContactFormBean) form;
        VendorDAO vendorDAO = new VendorDAO();
        VendorContactBean bean = null;
        boolean bNew = false;
        boolean isExist = false;
        try {
            bean = vendorDAO.getVendorMaterialByName(formBean.getVenId(), formBean.getName());
        } catch (Exception ex) {
        }
        int contid = formBean.getContId();
        if (contid == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getContId() != contid) {
                isExist = true;
            }
        }
        if (isExist) {
            ActionMessages errors = new ActionMessages();
            errors.add("vendorContactExisted", new ActionMessage("errors.vendor.contact.existed"));
            saveErrors(request, errors);
            return false;
        }
        HttpSession session = request.getSession();
        bean = new VendorContactBean();
        bean.setContId(formBean.getContId());
        bean.setVenId(formBean.getVenId());
        bean.setBirthday(formBean.getBirthday());
        bean.setEmail(formBean.getEmail());
        bean.setHandPhone(formBean.getHandPhone());
        bean.setHomePhone(formBean.getHomePhone());
        bean.setName(formBean.getName());
        bean.setOfficePhone(formBean.getOfficePhone());
        bean.setPosition(formBean.getPosition());

        try {
            if (bNew) {
                vendorDAO.insertVendorContact(bean);
            } else {
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                vendorDAO.updateVendorContact(bean);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:VenderContact:add-" + ex.getMessage());
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
