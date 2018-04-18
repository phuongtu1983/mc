/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.handedreport;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.HandedReportDAO;
import com.venus.mc.dao.RequireTransferDAO;
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
public class ListHandedReportDetailAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String hrId = request.getParameter("hrId");
        Integer id = (Integer) session.getAttribute("id");
        if (id != null) {
            hrId = id + "";
        }
        session.removeAttribute("id");
        ArrayList arrRes = null;
        HandedReportDAO requestDAO = new HandedReportDAO();
        if (!GenericValidator.isBlankOrNull(hrId)) {
            try {                
                arrRes = requestDAO.getHandedReportDetails(Integer.parseInt(hrId));
            } catch (Exception ex) {
            }
        }
        if (arrRes == null) {
            arrRes = new ArrayList();
        }
        request.setAttribute(Constants.HANDEDREPORT_DETAIL_LIST, arrRes);        
        return true;
    }
}
