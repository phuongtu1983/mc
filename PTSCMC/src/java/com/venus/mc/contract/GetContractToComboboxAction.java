/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.contract;

import com.venus.core.util.LogUtil;
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
 * @author phuongtu
 * @version
 */
public class GetContractToComboboxAction extends SpineAction {

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
        SearchAdvContractFormBean formBean = new SearchAdvContractFormBean();
        ArrayList contractList = null;
        ContractDAO contractDAO = new ContractDAO();
        try {
            formBean.setContractNumber(name);
            contractList = contractDAO.searchAdvInvoiceContract(formBean);
        } catch (Exception ex) {
            LogUtil.error("FAILED:Contract:getContractToComboBoxAction-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.CONTRACT_LIST, contractList);
        return true;
    }
}
