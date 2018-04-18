
package com.venus.mc.techclarification;

/**
 * @author Mai Vinh Loc
 */

import com.venus.core.util.NumberUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TechClarificationListDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

public class ListDisciplineAction extends SpineAction {

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
        HttpSession session = request.getSession();
        int tcId = NumberUtil.parseInt(session.getAttribute("tcId").toString(),0);
        TechClarificationListDAO tclDAO = new TechClarificationListDAO();
        ArrayList tclList = null;
        try {
            tclList = tclDAO.getTechClarificationListsTcId(tcId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        request.setAttribute(Constants.DISCIPLINE_LIST, tclList);
        return true;
    }
 
}
