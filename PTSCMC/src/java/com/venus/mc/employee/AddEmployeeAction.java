/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.employee;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kngo
 */
public class AddEmployeeAction extends SpineAction {

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
        EmployeeFormBean formBean = (EmployeeFormBean) form;
        EmployeeDAO employeeDAO = new EmployeeDAO();
        EmployeeBean bean = null;
        boolean bNew = false;
        boolean isExist = false;

        try {
            bean = employeeDAO.getEmployeeByName(formBean.getFullname());
        } catch (Exception ex) {
        }

        int empId = formBean.getEmpId();
        if (empId == 0) {
            bNew = true;
            if (bean != null) {
                isExist = true;
            }
        } else {
            bNew = false;
            if (bean != null && bean.getEmpId() != empId) {
                isExist = true;
            }
        }
     
        bean = new EmployeeBean();
        bean.setEmpId(formBean.getEmpId());
        bean.setOrgId(formBean.getOrgId());
        bean.setPosId(formBean.getPosId());
        bean.setCode(formBean.getCode());
        bean.setFullname(formBean.getFullname());
        bean.setNickname(formBean.getNickname());
        if (bean.getEmpId() == 0) {
            bean.setPassword(formBean.getPassword());
        }
        bean.setEmail(formBean.getEmail());
        bean.setOfficePhone(formBean.getOfficePhone());
        bean.setHandPhone(formBean.getHandPhone());
        bean.setHomePhone(formBean.getHomePhone());
        bean.setStatus(formBean.getStatus());
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setLastLogonDate(formBean.getLastLogonDate());
        bean.setFirstIp(formBean.getFirstIp());
        bean.setLastIp(formBean.getLastIp());

        try {
            if (bNew) {
                employeeDAO.insertEmployee(bean);
            } else {
                HttpSession session = request.getSession();
                OnlineUser user = MCUtil.getOnlineUser(session);
                LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
                employeeDAO.updateEmployee(bean);
            }
        } catch (Exception ex) {
            LogUtil.error("FAILED:Employee:add-" + ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_EDIT + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_SYSTEM;
    }
}
