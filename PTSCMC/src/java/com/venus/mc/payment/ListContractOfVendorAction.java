/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.payment;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.contract.ContractFormBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;

/**
 *
 * @author phuongtu
 * @version
 */
public class ListContractOfVendorAction extends SpineAction {

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
        String venId = request.getParameter("venId");
        ArrayList arrContract = null;
        if (!GenericValidator.isBlankOrNull(venId)) {
            ContractDAO contractDAO = new ContractDAO();
            try {
//                int id = contractDAO.getVenIdbyNote(venId);
                int id = NumberUtil.parseInt(venId, 0);
                contractDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
                if (id > 0) {
                    arrContract = contractDAO.getContractsNotPaymentYetAndHasInvoice(0, id);
                } else {
                    arrContract = contractDAO.getContractsNotPaymentYetAndHasInvoice(0, NumberUtil.parseInt(venId, 0));
                }

            } catch (Exception ex) {
            }
        }
        if (arrContract == null) {
            arrContract = new ArrayList();
        }
        ContractFormBean contract = new ContractFormBean();
        arrContract.add(0, contract);
        request.setAttribute(Constants.CONTRACT_LIST, arrContract);
        return true;
    }
}
