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
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;

/**
 *
 * @author phuontu
 * @version
 */
public class ListVendorEvalAction extends SpineAction {

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
        ArrayList vendorEvalList = null;
        String venId = request.getParameter("venId");
        if (!GenericValidator.isBlankOrNull(venId)) {
            try {
                vendorEvalList = vendorDAO.getVendorEvals(Integer.parseInt(venId));
            } catch (Exception ex) {
                LogUtil.error("FAILED:VenderEval:list-" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        request.setAttribute(Constants.VENDOR_EVAL_LIST, vendorEvalList);
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
