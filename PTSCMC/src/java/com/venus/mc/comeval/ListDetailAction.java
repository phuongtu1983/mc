/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.comeval;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TenderPlanDetailDAO;
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
public class ListDetailAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String cevId = request.getParameter("cevId");
        //cevId="1";
        ArrayList arrEmployee = null;
        if (!GenericValidator.isBlankOrNull(cevId)) {
            try {
                TenderPlanDetailDAO techDAO = new TenderPlanDetailDAO();
                arrEmployee = techDAO.getComEvalDetail(Integer.parseInt(cevId));
            } catch (Exception ex) {
            }
        }
        if (arrEmployee == null) {
            arrEmployee = new ArrayList();
        }
        request.setAttribute(Constants.COM_EVAL_DETAIL_LIST, arrEmployee);

        
        return true;
    }
}
