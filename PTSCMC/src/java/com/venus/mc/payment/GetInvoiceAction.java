/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.payment;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
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
public class GetInvoiceAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String conId = request.getParameter("conId");
        String invId = request.getParameter("invId");
        ArrayList arrInvoice = null;
        if (!GenericValidator.isBlankOrNull(invId)) {
            try {
                ContractDAO contractDAO = new ContractDAO();
                arrInvoice = contractDAO.getContractInvoice(NumberUtil.parseInt(conId, 0), NumberUtil.parseInt(invId, 0));
            } catch (Exception ex) {
            }
        }
        if (arrInvoice == null) {
            arrInvoice = new ArrayList();
        }
        request.setAttribute(Constants.CONTRACT_BILL_LIST, arrInvoice);
        return true;
    }
}
