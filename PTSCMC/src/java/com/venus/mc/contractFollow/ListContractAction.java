package com.venus.mc.contractFollow;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.ContractBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;


/**
 *
 * @author Mai Vinh Loc
 * @version
 */
public class ListContractAction extends SpineAction {

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
                String json="";
                if (bean == null) {
                    json = "{\"createdTime\":\"\"";
                    json += ",\"startTime\":\"\"";
                    json += ",\"endTime\":\"\"";
                    json += "}";
                } else {
                    json = "{\"createdTime\":\"" + bean.getCreatedDate() + "\"";
                    json += ",\"startTime\":\"" + bean.getEffectedDate() + "\"";
                    json += ",\"endTime\":\"" + bean.getExpireDate() + "\"";
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
