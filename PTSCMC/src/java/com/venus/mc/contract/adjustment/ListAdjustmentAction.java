/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.contract.adjustment;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.mc.contract.ContractFormBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mai vinh loc
 * @version
 */
public class ListAdjustmentAction extends SpineAction {

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
        int parentId = 0;
        try {
            parentId = (Integer) session.getAttribute("parent_id");
        } catch (Exception ex) {
        }
        if (parentId > 0) {
            conId = parentId + "";
        }
        session.removeAttribute("parent_id");
        ArrayList contractList = null;
        if (!GenericValidator.isBlankOrNull(conId)) {
            try {
                ContractDAO contractDAO = new ContractDAO();
                contractDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
                contractList = contractDAO.getAdjustments(conId);
                if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {
                    ContractFormBean contract = null;
                    int orgId = MCUtil.getOrganizationID(request.getSession());
                    for (int i = 0; i < contractList.size(); i++) {
                        contract = (ContractFormBean) contractList.get(i);
                        if (contract.getFollowOrg() == orgId) {
                            contract.setIsPermissionPrice(1);
                        }
                    }
                }
            } catch (Exception ex) {
                LogUtil.error("FAILED:Adjustment:list-" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        if (contractList == null) {
            contractList = new ArrayList();
        }
        request.setAttribute(Constants.CONTRACT_LIST, contractList);
        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_LIST + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_PURCHASING_CONTRACT;
    }
}
