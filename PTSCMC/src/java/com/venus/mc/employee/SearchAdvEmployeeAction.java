/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.employee;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;
/**
 *
 * @author kngo
 */
public class SearchAdvEmployeeAction extends SpineAction {

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
        SearchAdvEmployeeFormBean formBean = (SearchAdvEmployeeFormBean) form;
        EmployeeBean bean = new EmployeeBean();

        bean.setOrgId(formBean.getOrgId());
        bean.setPosId(formBean.getPosId());
        bean.setFullname(formBean.getFullname());
        bean.setNickname(formBean.getNickname());
        bean.setPassword(formBean.getPassword());
        bean.setEmail(formBean.getEmail());
        bean.setOfficePhone(formBean.getOfficePhone());
        bean.setHandPhone(formBean.getHandPhone());
        bean.setHomePhone(formBean.getHomePhone());
        bean.setCreatedDate(formBean.getCreatedDate());
        bean.setModifiedDate(formBean.getModifiedDate());
        bean.setLastLogonDate(formBean.getLastLogonDate());
        bean.setFirstIp(formBean.getFirstIp());
        bean.setLastIp(formBean.getLastIp());
        
        ArrayList employeeList = null;
        EmployeeDAO employeeDAO = new EmployeeDAO();
        try {
            employeeList = employeeDAO.searchAdvEmployee(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED: searchAdvEmployee-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.EMPLOYEE_LIST, employeeList);
        return true;
    }
}
