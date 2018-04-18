/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.vendor.material;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class VendorGroupMaterialPanelAction extends SpineAction {

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
        ArrayList arrGroupMaterial = new ArrayList();
        VendorBean vendor = null;
        String venId = request.getParameter("venId");
        try {
            VendorDAO venDAO = new VendorDAO();
            if (GenericValidator.isBlankOrNull(venId)) {
                venId = "0";
            }
            arrGroupMaterial = venDAO.getGroupMaterialsNotBelongVendor(venId);
            vendor = venDAO.getVendor(Integer.parseInt(venId));
        } catch (Exception ex) {
            LogUtil.error("FAILED:VenderGroupMaterial:panel-" + ex.getMessage());
            ex.printStackTrace();
        }
        if (vendor == null) {
            vendor = new VendorBean();
        }
        request.setAttribute(Constants.VENDOR, vendor);
        if (arrGroupMaterial.size() > 0) {
            request.setAttribute(Constants.GROUP_MATERIAL_LIST, arrGroupMaterial);
        }
        return true;
    }

    @Override
    protected String getXmlMessage() {
        return "vendorGroupMaterialList";
    }

    @Override
    protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("venId"))) {
            return request.getParameter("venId");
        } else {
            return "";
        }
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
