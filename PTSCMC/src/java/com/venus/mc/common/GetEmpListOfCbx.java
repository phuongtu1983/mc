/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.common;

import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author thcao
 */
public class GetEmpListOfCbx extends SpineAction {

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
        int orgId = NumberUtil.parseInt(request.getParameter("orgId"), 0);
        try {
            EmployeeDAO empDAO = new EmployeeDAO();
            ArrayList empList = empDAO.getEmployeeListByOrg(orgId);
            request.setAttribute(Constants.EMP_LIST, empList);
        } catch (Exception ex) {
            Logger.getLogger(GetEmpListOfCbx.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
