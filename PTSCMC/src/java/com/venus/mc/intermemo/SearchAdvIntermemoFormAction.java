/*
 *
 * Created on April 14, 2007, 8:49 AM
 */
package com.venus.mc.intermemo;

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
 * @author HOANG DIEU
 * @version
 */
public class SearchAdvIntermemoFormAction extends SpineAction {

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
        ArrayList empList = null;
        try {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            empList = employeeDAO.getEmployees();
            EmployeeBean empBean = new EmployeeBean(0);
            empBean.setFullname("");
            empList.add(0, empBean);
        } catch (Exception ex) {
        }
        if (empList == null) {
            empList = new ArrayList();
        }
        request.setAttribute(Constants.EMPLOYEE_LIST, empList);
        request.setAttribute(Constants.REQUEST, new SearchAdvIntermemoFormBean());
        return true;
    }
}
