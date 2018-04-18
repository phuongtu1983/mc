/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.vendor.criterion;

import com.venus.mc.bean.EvaluateCriterionBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.PermissionUtil;
import com.venus.mc.vendor.evaluate.EvaluateCriterionFormBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class CriterionFormAction extends SpineAction {

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
        VendorDAO vendorDAO = new VendorDAO();
        EvaluateCriterionFormBean criFormBean = null;
        String criterionId = request.getParameter("criterionId");
        if (!GenericValidator.isBlankOrNull(criterionId)) {
            try {
                EvaluateCriterionBean criterion = new EvaluateCriterionBean();
                criterion = vendorDAO.getEvaluateCriterion(Integer.parseInt(criterionId));
                if (criterion != null) {
                    criFormBean = new EvaluateCriterionFormBean(criterion);
                }
            } catch (Exception ex) {
            }
        }
        if (criFormBean == null) {
            criFormBean = new EvaluateCriterionFormBean();
        }
        request.setAttribute(Constants.EVAL_CRITERION, criFormBean);
        return true;
    }

    @Override
    protected String getXmlMessage() {
        return "criterionDetail";
    }

    @Override
    protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("criterionId"))) {
            return request.getParameter("criterionId");
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
