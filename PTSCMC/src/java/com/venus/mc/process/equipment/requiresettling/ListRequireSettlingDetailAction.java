/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.requiresettling;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequireSettlingDAO;
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
public class ListRequireSettlingDetailAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String rsId = request.getParameter("rsId");
        Integer id = (Integer) session.getAttribute("id");
        if (id != null) {
            rsId = id + "";
        }
        session.removeAttribute("id");
        ArrayList arrDetail = null;
        if (!GenericValidator.isBlankOrNull(rsId)) {
            try {
                RequireSettlingDAO requireSettlingDAO = new RequireSettlingDAO();
                arrDetail = requireSettlingDAO.getRequireSettlingDetails(Integer.parseInt(rsId));
            } catch (Exception ex) {
            }
        }
        if (arrDetail == null) {
            arrDetail = new ArrayList();
        }
        request.setAttribute(Constants.REQUIRESETTLING_DETAIL_LIST, arrDetail);
        return true;
    }
}
