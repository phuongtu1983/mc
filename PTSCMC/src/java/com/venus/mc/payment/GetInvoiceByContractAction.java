/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.payment;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import java.util.ArrayList;

/**
 *
 * @author phuontu
 * @version
 */
public class GetInvoiceByContractAction extends SpineAction {

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
        String conId = request.getParameter("conId");
        if (!GenericValidator.isBlankOrNull(conId)) {
            try {
                if (!GenericValidator.isBlankOrNull(request.getParameter("isPayment"))) {
                    ArrayList arrInvoice = null;
                    try {
                        ContractDAO contractDAO = new ContractDAO();
                        arrInvoice = contractDAO.getInvoiceContractsByContract(NumberUtil.parseInt(conId, 0));
                    } catch (Exception ex) {
                    }
                    if (arrInvoice == null) {
                        arrInvoice = new ArrayList();
                    }
                    request.setAttribute(Constants.INVOICE_LIST, arrInvoice);
                }
            } catch (Exception ex) {
                LogUtil.error("FAILED:Payment:vendorDetail-" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return true;
    }
}
