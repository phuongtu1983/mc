/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.requirerepair;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequireRepairDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 */
public class ListRequireRepairDetailAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String rrId = request.getParameter("rrId");
        Integer id = (Integer) session.getAttribute("id");
        if (id != null) {
            rrId = id + "";
        }
        session.removeAttribute("id");
        ArrayList arrRequireRepairDetail = null;
        if (!GenericValidator.isBlankOrNull(rrId)) {
            try {
                RequireRepairDAO requireRepair = new RequireRepairDAO();
                arrRequireRepairDetail = requireRepair.getRequireRepairDetails(Integer.parseInt(rrId));
            } catch (Exception ex) {
            }
        }
        if (arrRequireRepairDetail == null) {
            arrRequireRepairDetail = new ArrayList();
        }
        request.setAttribute(Constants.REQUIREREPAIR_DETAIL_LIST, arrRequireRepairDetail);
        
        return true;
    }
}
