/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.requireverified;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequireRepairDAO;
import com.venus.mc.dao.RequireVerifiedDAO;
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
public class ListRequireVerifiedDetailAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String rvId = request.getParameter("rvId");
        Integer id = (Integer) session.getAttribute("id");
        if (id != null) {
            rvId = id + "";
        }
        session.removeAttribute("id");
        ArrayList arrRequireVerifiedDetail = null;
        if (!GenericValidator.isBlankOrNull(rvId)) {
            try {
                RequireVerifiedDAO requestDAO = new RequireVerifiedDAO();
                arrRequireVerifiedDetail = requestDAO.getRequireVerifiedDetails(Integer.parseInt(rvId));
            } catch (Exception ex) {
            }
        }
        if (arrRequireVerifiedDetail == null) {
            arrRequireVerifiedDetail = new ArrayList();
        }
        request.setAttribute(Constants.REQUIREVERIFIED_DETAIL_LIST, arrRequireVerifiedDetail);        
        return true;
    }
}
