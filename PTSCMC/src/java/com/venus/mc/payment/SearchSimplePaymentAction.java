/*
 *
 * Created on April 13, 2007, 2:23 PM
 */
package com.venus.mc.payment;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.venus.core.util.StringUtil;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author HOANG DIEU
 * @version
 */
public class SearchSimplePaymentAction extends SpineAction {

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
        SearchFormBean paymentForm = (SearchFormBean) form;
        int fieldid = paymentForm.getSearchid();
        String strFieldvalue = paymentForm.getSearchvalue();
        ArrayList paymentList = null;
        ContractDAO contractDAO = new ContractDAO();
        contractDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
        try {
            OrganizationDAO orgDAO = new OrganizationDAO();
            int orgId = MCUtil.getOrganizationID(request.getSession());
            String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
            orgs += "," + orgId;
            contractDAO.setRequestOrg(orgs);
            paymentList = contractDAO.searchSimplePayment(fieldid, StringUtil.encodeHTML(strFieldvalue));
        } catch (Exception ex) {
            LogUtil.error("FAILED:Request:searchSimple-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.PAYMENT_LIST, paymentList);
        return true;
    }
}
