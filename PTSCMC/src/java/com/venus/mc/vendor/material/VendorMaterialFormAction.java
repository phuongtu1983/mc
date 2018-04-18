/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.vendor.material;

import com.venus.mc.bean.VendorBean;
import com.venus.mc.bean.VendorMaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OriginDAO;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.PermissionUtil;
import com.venus.mc.vendor.VendorFormBean;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class VendorMaterialFormAction extends SpineAction {

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
        VendorMaterialFormBean materialFormBean = null;
        VendorFormBean venFormBean = null;
        VendorDAO vendorDAO = new VendorDAO();
        int venId = 0;
        String venMaterialId = request.getParameter("venMaterialId");
        if (!GenericValidator.isBlankOrNull(venMaterialId)) {
            try {
                VendorMaterialBean bean = null;
                bean = vendorDAO.getVendorMaterial(Integer.parseInt(venMaterialId));
                if (bean != null) {
                    materialFormBean = new VendorMaterialFormBean(bean);
                    venId = bean.getVenId();
                }
            } catch (Exception ex) {
            }
        }
        if (venId == 0) {
            if (!GenericValidator.isBlankOrNull(request.getParameter("venId"))) {
                venId = Integer.parseInt(request.getParameter("venId"));
            }
        }
        if (venId != 0) {
            try {
                VendorBean vendor = null;
                vendor = vendorDAO.getVendor(venId);
                if (vendor != null) {
                    venFormBean = new VendorFormBean(vendor);
                }
            } catch (Exception ex) {
            }
        }
        if (materialFormBean == null) {
            materialFormBean = new VendorMaterialFormBean();
        }
        if (venFormBean == null) {
            venFormBean = new VendorFormBean();
        }
        ArrayList arrGroupMaterial = null;
        try {
            arrGroupMaterial = vendorDAO.getVendorGroupMaterials(venId);
        } catch (Exception ex) {
        }
        if (arrGroupMaterial == null) {
            arrGroupMaterial = new ArrayList();
        }
        request.setAttribute(Constants.VENDOR_GROUP_MATERIAL_LIST, arrGroupMaterial);

        ArrayList arrOrigin = new ArrayList();
        try {
            OriginDAO originDAO = new OriginDAO();
            arrOrigin = originDAO.getOrigins();
        } catch (Exception ex) {
        }
        if (arrOrigin == null) {
            arrOrigin = new ArrayList();
        }
        request.setAttribute(Constants.ORIGIN_LIST, arrOrigin);

        materialFormBean.setVenId(venFormBean.getVenId());
        materialFormBean.setVendorName(venFormBean.getName());
        request.setAttribute(Constants.VENDOR_MATERIAL, materialFormBean);

        return true;
    }

    @Override
    protected String getXmlMessage() {
        return "vendorMaterialDetail";
    }

    @Override
    protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("venMaterialId"))) {
            return request.getParameter("venMaterialId");
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
