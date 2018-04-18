/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.techeval;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TechEvalDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
public class ListEmployeeAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String tenId = request.getParameter("tenId");
        ArrayList arrEmployee = null;
        if (!GenericValidator.isBlankOrNull(tenId)) {
            try {
                TechEvalDAO techDAO = new TechEvalDAO();
                arrEmployee = techDAO.getTechEvalGroup(Integer.parseInt(tenId));
            } catch (Exception ex) {
            }
        }
        if (arrEmployee == null) {
            arrEmployee = new ArrayList();
        }
        request.setAttribute(Constants.EMPLOYEE_LIST, arrEmployee);
        return true;
    }
}
