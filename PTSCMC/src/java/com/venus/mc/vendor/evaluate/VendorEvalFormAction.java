/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.vendor.evaluate;

import com.venus.mc.bean.VendorBean;
import com.venus.mc.bean.VendorEvaluateBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.OrganizationDAO;
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
public class VendorEvalFormAction extends SpineAction {

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
        VendorEvalFormBean evalFormBean = null;
        VendorFormBean venFormBean = null;
        VendorDAO vendorDAO = new VendorDAO();
        int venId = 0;
        String venEvalId = request.getParameter("venEvalId");
        if (!GenericValidator.isBlankOrNull(venEvalId)) {
            try {
                VendorEvaluateBean bean = null;
                bean = vendorDAO.getVendorEval(Integer.parseInt(venEvalId));
                if (bean != null) {
                    evalFormBean = new VendorEvalFormBean(bean);
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
        if (evalFormBean == null) {
            evalFormBean = new VendorEvalFormBean();
        }
        if (venFormBean == null) {
            venFormBean = new VendorFormBean();
        }
        ArrayList orgList = null;
        try {
            OrganizationDAO orgDAO = new OrganizationDAO();
            orgList = orgDAO.getOrganizations();
        } catch (Exception ex) {
        }
        ArrayList memList = null;
        try {
            EmployeeDAO empDAO = new EmployeeDAO();
            memList = empDAO.getEmployees();
        } catch (Exception ex) {
        }
        if (orgList == null) {
            orgList = new ArrayList();
        }
        if (memList == null) {
            memList = new ArrayList();
        }
        request.setAttribute(Constants.VENDOR_EVAL, evalFormBean);
        request.setAttribute(Constants.VENDOR, venFormBean);
        request.setAttribute(Constants.ORG_LIST, orgList);
        request.setAttribute(Constants.MEMBER_LIST, memList);
        return true;
    }

    @Override
    protected String getXmlMessage() {
        return "vendorEvaluateDetail";
    }

    @Override
    protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("venEvalId"))) {
            return request.getParameter("venEvalId");
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
        return PermissionUtil.PER_LIBRARY_VENDOR_EVAL;
    }
}
