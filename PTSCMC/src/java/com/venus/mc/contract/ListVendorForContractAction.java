/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.contract;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class ListVendorForContractAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        ArrayList arrVendor = null;
        if (!GenericValidator.isBlankOrNull(request.getParameter("tenId"))) {
            try {
                TenderPlanDAO tenderDAO = new TenderPlanDAO();
                arrVendor = tenderDAO.getVendorOfTenderPlan(request.getParameter("tenId"));
            } catch (Exception ex) {
            }
        }
        if (arrVendor == null) {
            arrVendor = new ArrayList();
        }
        VendorBean vendor = new VendorBean(0);
        vendor.setName("");
        arrVendor.add(0, vendor);
        request.setAttribute(Constants.VENDOR_LIST, arrVendor);
        return true;
    }
}
