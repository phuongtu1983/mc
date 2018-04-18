/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.vendor.evaluate;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author phuontu
 * @version
 */
public class ListVendorCriterionAction extends SpineAction {

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
        String venEvalId = request.getParameter("venEvalId");
        ArrayList arrCriterion = new ArrayList();
        VendorDAO vendorDAO = new VendorDAO();
        if (!GenericValidator.isBlankOrNull(venEvalId)) {
            try {
                arrCriterion = vendorDAO.getEvaluteDetail(Integer.parseInt(venEvalId));
            } catch (Exception ex) {
            }
        } else {
            try {
                arrCriterion = vendorDAO.getEvalCrisDefault();
            } catch (Exception ex) {
                LogUtil.error("FAILED:VenderEval:listCriterion-" + ex.getMessage());
            }
        }
        request.setAttribute(Constants.VENDOR_CRI_LIST, arrCriterion);
        
        ArrayList arrResult = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.vendor.evaluate.reach"));
        value.setValue("1");
        arrResult.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.vendor.evaluate.notreach"));
        value.setValue("0");
        arrResult.add(value);
        request.setAttribute(Constants.RESULT_LIST, arrResult);
        return true;
    }
    
        @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_LIST + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_LIBRARY_VENDOR_EVAL;
    }
}