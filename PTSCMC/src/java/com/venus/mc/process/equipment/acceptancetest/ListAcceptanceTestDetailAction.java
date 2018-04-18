/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.acceptancetest;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.AcceptanceTestDAO;
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
public class ListAcceptanceTestDetailAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String atId = request.getParameter("atId");
        Integer id = (Integer) session.getAttribute("id");
        if (id != null) {
            atId = id + "";
        }
        session.removeAttribute("id");
        ArrayList arrAcceptanceTestDetail = null;
        if (!GenericValidator.isBlankOrNull(atId)) {
            try {
                AcceptanceTestDAO requestDAO = new AcceptanceTestDAO();
                arrAcceptanceTestDetail = requestDAO.getAcceptanceTestDetails(Integer.parseInt(atId));
            } catch (Exception ex) {
            }
        }
        if (arrAcceptanceTestDetail == null) {
            arrAcceptanceTestDetail = new ArrayList();
        }
        request.setAttribute(Constants.ACCEPTANCETEST_DETAIL_LIST, arrAcceptanceTestDetail);
        
        return true;
    }
}
