/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.invoice;

import com.venus.core.util.LogUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;

/**
 *
 * @author phuontu
 * @version
 */
public class ListInvoiceAction extends SpineAction {

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
        ContractDAO contractDAO = new ContractDAO();
        contractDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
        ArrayList invoiceList = null;
        try {
            invoiceList = contractDAO.getInvoices();
        } catch (Exception ex) {
            LogUtil.error("FAILED:Invoice:list-" + ex.getMessage());
            ex.printStackTrace();
        }
        if (invoiceList == null) {
            invoiceList = new ArrayList();
        }
        request.setAttribute(Constants.INVOICE_LIST, invoiceList);
        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_LIST + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_PURCHASING_INVOICE;
    }
}
