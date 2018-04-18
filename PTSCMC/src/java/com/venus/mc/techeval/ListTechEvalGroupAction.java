/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.techeval;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import com.venus.mc.dao.TechEvalGroupDAO;
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
public class ListTechEvalGroupAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        ArrayList arrDetail = null;
        String teId = request.getParameter("teId");

        try {
            TechEvalGroupDAO tenderDAO = new TechEvalGroupDAO();
            arrDetail = tenderDAO.getEmployeeForTechEval(teId);
        } catch (Exception ex) {
        }

        if (arrDetail == null) {
            arrDetail = new ArrayList();
        }
        request.setAttribute(Constants.TECH_EVAL_GROUP_LIST, arrDetail);
        return true;
    }
}
