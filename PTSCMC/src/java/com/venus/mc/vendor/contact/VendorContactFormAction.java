/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.vendor.contact;

import com.venus.mc.bean.VendorBean;
import com.venus.mc.bean.VendorContactBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.PermissionUtil;
import com.venus.mc.vendor.VendorFormBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class VendorContactFormAction extends SpineAction {

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
        VendorContactFormBean contactFormBean = null;
        VendorFormBean venFormBean = null;
        VendorDAO vendorDAO = new VendorDAO();
        String venContactId = request.getParameter("venContactId");
        int venId = 0;
        if (!GenericValidator.isBlankOrNull(venContactId)) {
            try {
                VendorContactBean bean = null;
                bean = vendorDAO.getVendorContact(Integer.parseInt(venContactId));
                if (bean != null) {
                    contactFormBean = new VendorContactFormBean(bean);
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
        if (contactFormBean == null) {
            contactFormBean = new VendorContactFormBean();
        }
        if (venFormBean == null) {
            venFormBean = new VendorFormBean();
        }
        contactFormBean.setVenId(venFormBean.getVenId());
        contactFormBean.setVendorName(venFormBean.getName());
        request.setAttribute(Constants.VENDOR_CONTACT, contactFormBean);
        return true;
    }

    @Override
    protected String getXmlMessage() {
        return "vendorContactDetail";
    }

    @Override
    protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("venContactId"))) {
            return request.getParameter("venContactId");
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
