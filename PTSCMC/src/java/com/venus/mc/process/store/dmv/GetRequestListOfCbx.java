/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.process.store.dmv;

import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author thcao
 */
public class GetRequestListOfCbx extends SpineAction {

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
        int dnId = NumberUtil.parseInt(request.getParameter("dnId"), 0);
        try {
            RequestDAO empDAO = new RequestDAO();
            ArrayList dnList = empDAO.getRequestListByDnId(dnId);            
            request.setAttribute(Constants.REQUEST_LIST, dnList);
        } catch (Exception ex) {
            Logger.getLogger(GetRequestListOfCbx.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
