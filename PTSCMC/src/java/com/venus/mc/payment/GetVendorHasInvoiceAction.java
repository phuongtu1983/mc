/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.payment;

import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class GetVendorHasInvoiceAction extends SpineAction {

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
        String name = request.getParameter("name");
        ArrayList vendorList = null;
        try {
            VendorDAO venDAO = new VendorDAO();
            venDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
            vendorList = venDAO.getVendorsNotPaymentYetAndHasInvoice(0, name);
            vendorList.add(0, new VendorBean());
        } catch (Exception ex) {
            vendorList = new ArrayList();
        }
        request.setAttribute(Constants.VENDOR_LIST, vendorList);
        return true;
    }
}
