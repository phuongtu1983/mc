/*
 * LoginAction.java
 *
 * Created on December 8, 2006, 11:43 AM
 */
package com.venus.mc.auth;

import com.venus.core.util.GenericValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.util.Constants;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.dao.PermissionDAO;
import com.venus.mc.permission.ApplicationPermissionBean;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 * @version
 */
public class LoginAction extends Action {

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
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        LoginForm loginForm = (LoginForm) form;
        String nickname = loginForm.getNickname();
        String md5pw = loginForm.getMd5pw();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        OnlineUserImpl user = null;
        try {
            user = (OnlineUserImpl) employeeDAO.login(nickname, md5pw);
        } catch (Exception ex) {
            LogUtil.error("1");
            LogUtil.error(ex.getMessage());
        }
        if (user == null) {
            ActionMessages errors = new ActionMessages();
            errors.add("passwordnotmatch", new ActionMessage("errors.loginform.passwordnotmatch"));
            saveErrors(request, errors);
            return mapping.getInputForward();
        } else {
            try {
                employeeDAO.updateLastLogon(user.getID(), request.getRemoteHost());
            } catch (Exception ex) {
                LogUtil.error("2");
                LogUtil.error(ex.getMessage());
            }
            session.setAttribute(Constants.EMPLOYEE_OBJ, user);
            session.setAttribute(Constants.SESSION_ID, session.getId());
            String preLoginPage = (String) session.getAttribute("preLoginPage");
            if (preLoginPage != null) {
                session.removeAttribute("preLoginPage");
                if (preLoginPage.split("params=").length > 1) {//Loc: neu tham so = null thi bo qua
                    response.sendRedirect(request.getContextPath() + preLoginPage);
                    return null;
                }              
            }

            getPermission(session);
            if (user.getID() == 1) {
                return mapping.findForward("admin");
            }
//            WarningHandle warningHandler = new WarningHandle();
//            warningHandler.WarningMrir(2);
            return mapping.findForward(Constants.FORWARD_ACT_SUCCESS);
        }
    }

    private void getPermission(HttpSession session) {
        try {
            PermissionDAO permissionDAO = new PermissionDAO();
            EmployeeDAO empDAO = new EmployeeDAO();
            ArrayList<ApplicationPermissionBean> arrPer = permissionDAO.getPermissionsOfEmployee(MCUtil.getMemberID(session), MCUtil.getOrganizationID(session));
            ApplicationPermissionBean bean = null;
            for (int i = 0; i < arrPer.size(); i++) {
                String emps = "";
                bean = (ApplicationPermissionBean) arrPer.get(i);
                ArrayList empList = empDAO.getEmployeeByOrgIds(bean.getOrganizations());
                for (int j = 0; j < empList.size(); j++) {
                    EmployeeBean empBean = (EmployeeBean) empList.get(j);
                    emps += empBean.getEmpId() + ",";
                }
                if (!GenericValidator.isBlankOrNull(bean.getOrgEmployees())) {
                    emps += bean.getOrgEmployees();
                }
                if (!GenericValidator.isBlankOrNull(bean.getEmployees())) {
                    bean.setEmployees("0," + bean.getEmployees() + ",0");
                } else {
                    bean.setEmployees("0");
                }
                if (!GenericValidator.isBlankOrNull(bean.getOrganizations())) {
                    bean.setOrganizations("0," + bean.getOrganizations() + ",0");
                } else {
                    bean.setOrganizations("0");
                }
                if (!GenericValidator.isBlankOrNull(emps)) {
                    bean.setOrgEmployees("0," + emps + "0");
                } else {
                    bean.setOrgEmployees("0");
                }

                if (!GenericValidator.isBlankOrNull(bean.getPermit())) {
                    bean.setPermit("0," + bean.getPermit() + ",0");
                } else {
                    bean.setPermit("0");
                }
            }
            session.setAttribute(Constants.PERMISSION_USER_LIST, arrPer);
        } catch (Exception ex) {
        }
    }
}
