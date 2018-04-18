/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.permission;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.PermissionBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.PermissionDAO;
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
public class DeletePermissionEmpAction extends SpineAction {

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
        String[] arrDetId = request.getParameterValues("permissionEmpChk");
        try {
            OnlineUser user = MCUtil.getOnlineUser(session);
            LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
            int length = 0;
            PermissionDAO permissionDAO = new PermissionDAO();
            if (arrDetId != null) {
                length = arrDetId.length;
            }
            String perID = request.getParameter("perId");
            PermissionBean bean = null;
            try {
                bean = permissionDAO.getPermission(NumberUtil.parseInt(perID, 0));
            } catch (Exception ex) {
            }
            if (bean == null) {
                bean = new PermissionBean();
            }

            String[] arrEmp = bean.getEmployees().split(",");
            String emps = "0";
            for (int i = 0; i < arrEmp.length; i++) {
                boolean existed = false;
                for (int j = 0; j < length; j++) {
                    if (arrEmp[i].equals(arrDetId[j])) {
                        existed = true;
                        break;
                    }
                }
                if (!existed) {
                    emps += "," + arrEmp[i];
                }
            }
            if (!emps.equals("0")) {
                bean.setEmployees(emps.substring(2));
                try {
                    permissionDAO.updatePermission(bean);
                } catch (Exception ex) {

                }
            }
            OutputUtil.sendStringToOutput(response, "deleted");
        } catch (Exception ex) {
            LogUtil.error("FAILED:PermissionEmp:delete-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
