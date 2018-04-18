/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.payment;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuongtu
 */
public class DeletePaymentAction extends SpineAction {

    private String result = "";

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
        String[] arrPayId = request.getParameterValues("payId");
        try {
            OnlineUser user = MCUtil.getOnlineUser(session);
            LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
            int length = 0;
            ContractDAO contractDAO = new ContractDAO();
            if (arrPayId != null) {
                length = arrPayId.length;
            }
            result = "";
//            String messages = "";
//            String tempStr = "";

//            for (int i = 0; i < length; i++) {
//                tempStr = contractDAO.getInvoiceRelationPayment(arrPayId[i]);
//                if (!tempStr.equals("")) {
//                    messages += ", " + tempStr;
//                }
//            }
//            if (!messages.equals("")) {
//                result += "\n " + MCUtil.getBundleString("message.invoice") + " : " + messages.substring(2);
//            }
            if (result.equals("")) {
                for (int i = 0; i < length; i++) {
                    try {
                        contractDAO.deletePayment(arrPayId[i]);
//                        contractDAO.updatePaymentInInvoice(NumberUtil.parseInt(arrPayId[i], 0));
                    } catch (Exception ex) {
                    }
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Payment:delete-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    protected String getErrorsString(HttpServletRequest request) {
        return result;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_DELETE + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_PURCHASING_PAYMENT;
    }
}
