/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.invoice;

import com.venus.mc.contract.ContractFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class ContractOfInvoiceFormAction extends SpineAction {

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
        ContractDAO contractDAO = new ContractDAO();
        contractDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
        ArrayList contractList = null;
        try {
            contractDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
            contractList = contractDAO.getContractsForInvoice(0, name);
        } catch (Exception ex) {
            contractList = new ArrayList();
        }
        if (contractList == null) {
            contractList = new ArrayList();
        }
        ContractFormBean contract = new ContractFormBean();
        contract.setConId(0);
        contract.setContractNumber("");
        contractList.add(0, contract);
        request.setAttribute(Constants.CONTRACT_LIST, contractList);
        return true;
    }
}
