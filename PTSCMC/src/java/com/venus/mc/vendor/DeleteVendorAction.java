/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.vendor;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VendorDAO;
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
public class DeleteVendorAction extends SpineAction {

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
        String[] arrVenId = request.getParameterValues("venId");
        try {
            OnlineUser user = MCUtil.getOnlineUser(session);
            LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
            int length = 0;
            VendorDAO vendorDAO = new VendorDAO();
            if (arrVenId != null) {
                length = arrVenId.length;
            }
            result = "";
            String messages = "";
            String tempStr = "";

            for (int i = 0; i < length; i++) {
                tempStr = vendorDAO.getDeliveryRequestRelationVendor(arrVenId[i]);
                if (!tempStr.equals("")) {
                    messages += ", " + tempStr;
                }
            }
            if (!messages.equals("")) {
                result += "\n " + MCUtil.getBundleString("message.deliveryrequest") + " : " + messages.substring(2);
            }

            messages = "";
            for (int i = 0; i < length; i++) {
                tempStr = vendorDAO.getContractRelationVendor(arrVenId[i]);
                if (!tempStr.equals("")) {
                    messages += ", " + tempStr;
                }
            }
            if (!messages.equals("")) {
                result += "\n " + MCUtil.getBundleString("message.contract") + " : " + messages.substring(2);
            }

            messages = "";
            for (int i = 0; i < length; i++) {
                tempStr = vendorDAO.getOrderRelationVendor(arrVenId[i]);
                if (!tempStr.equals("")) {
                    messages += ", " + tempStr;
                }
            }
            if (!messages.equals("")) {
                result += "\n " + MCUtil.getBundleString("message.order") + " : " + messages.substring(2);
            }

            messages = "";
            for (int i = 0; i < length; i++) {
                tempStr = vendorDAO.getBidEvalSumRelationVendor(arrVenId[i]);
                if (!tempStr.equals("")) {
                    messages += ", " + tempStr;
                }
            }
            if (!messages.equals("")) {
                result += "\n " + MCUtil.getBundleString("message.bidevalsum") + " : " + messages.substring(2);
            }

            messages = "";
            for (int i = 0; i < length; i++) {
                tempStr = vendorDAO.getTenderPlanRelationVendor(arrVenId[i]);
                if (!tempStr.equals("")) {
                    messages += ", " + tempStr;
                }
            }
            if (!messages.equals("")) {
                result += "\n " + MCUtil.getBundleString("message.tenderplan") + " : " + messages.substring(2);
            }

            if (result.equals("")) {
                for (int i = 0; i < length; i++) {
                    vendorDAO.deleteVendor(arrVenId[i]);
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:delete-" + ex.getMessage());
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
        return PermissionUtil.PER_LIBRARY_VENDOR;
    }
}
