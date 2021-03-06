/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.contract;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class SaveContractMaterialPriceAction extends SpineAction {

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
        HttpSession session = request.getSession();
        String conId = request.getParameter("conId");
        if (!GenericValidator.isBlankOrNull(conId)) {
            try {
                OnlineUser user = MCUtil.getOnlineUser(session);
                ContractDAO contractDAO = new ContractDAO();
                contractDAO.saveContractMaterialPrice(Integer.parseInt(conId));
            } catch (Exception ex) {
                LogUtil.error("FAILED:Contract:contract material price-" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return true;
    }
}
