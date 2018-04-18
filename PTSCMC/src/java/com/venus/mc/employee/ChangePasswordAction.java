/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.employee;

import com.venus.core.auth.OnlineUser;
import com.venus.core.sercurity.Encoder;
import com.venus.core.util.LogUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 *
 * @author Admin
 */
public class ChangePasswordAction extends SpineAction {

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
        PasswordFormBean memberForm = (PasswordFormBean) form;

        String username = MCUtil.getMemberName(request.getSession());
        String password = Encoder.getMD5_Base64(memberForm.getPassword());
        String newpassword = Encoder.getMD5_Base64(memberForm.getNewpassword());
        EmployeeDAO memberDAO = new EmployeeDAO();
        try {
            LogUtil.info("Username : " + username + " - Form : " + mapping.getPath() + " - EDIT");
            int ret = memberDAO.changePassword(username, password, newpassword);
            if (ret == 0) {
                ActionMessages errors = new ActionMessages();
                errors.add("employeeExisted", new ActionMessage("errors.employee.existed"));
                saveErrors(request, errors);
                return false;
            }
            request.setAttribute("success", "ok");
        } catch (Exception ex) {
            LogUtil.error("FAILED: MemberBean:ChangePassword-"
                    + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }
}
