package com.venus.mc.invoice;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.ContractBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.TenderPlanDAO;

/**
 *
 * @author Mai Vinh Loc
 * @version
 */
public class InvoiceContractDetailAction extends SpineAction {

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
                ContractBean bean = null;
                ContractDAO contractDAO = new ContractDAO();
                bean = contractDAO.getContract(Integer.parseInt(conId));
                double rate=1;
                if(!bean.getCurrency().contains("VN")){
                    TenderPlanDAO tenDAO=new TenderPlanDAO();
                    
                }
                String json = "";
                if (bean == null) {
                    json = "{\"currency\":\"\"";
                    json += ",\"rates\":\"\"";
                    json += ",\"otherAmount\":\"\"";
                    json += ",\"transportAmount\":\"\"";
                    json += "}";
                } else {
                    json = "{\"currency\":\"" + bean.getCurrency() + "\"";
                    json += ",\"rates\":\"" + rate + "\"";
                    json += ",\"otherAmount\":\"" + bean.getOtherFee() + "\"";
                    json += ",\"transportAmount\":\"" + bean.getTransport() + "\"";
                    json += "}";
                }
                OutputUtil.sendStringToOutput(response, json);
            } catch (Exception ex) {
            }
        }
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
