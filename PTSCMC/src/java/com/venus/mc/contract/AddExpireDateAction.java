package com.venus.mc.contract;

import com.venus.mc.bean.ContractBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/**
 *
 * @author mai vinh loc
 */
public class AddExpireDateAction extends SpineAction {

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
        ContractFormBean formBean = (ContractFormBean) form;
        ContractDAO contractDAO = new ContractDAO();

        try {
            contractDAO.updateContractExpireDate(formBean.getExpireDate(),formBean.getConId());
        } catch (Exception ex) {
        }
        
        if (formBean.getKind() == ContractBean.KIND_CONTRACT) {
            this.actionForwardResult = "contract";
        } else if (formBean.getKind() == ContractBean.KIND_PRINCIPLE) {
            this.actionForwardResult = "contractPrinciple";
        } else if (formBean.getKind() == ContractBean.KIND_ORDER) {
            this.actionForwardResult = "order";
        } else if (formBean.getKind() == ContractBean.KIND_APPENDIX) {
            this.actionForwardResult = "appendix";
        }

        return true;
    }
}
