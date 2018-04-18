/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.purchasing.edeliverynotice;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EdnDAO;
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
public class ListEdnDetailAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String reqId = request.getParameter("reqId");
        ArrayList arrRequestDetail = null;
        if (!GenericValidator.isBlankOrNull(reqId)) {
            try {
                EdnDAO dnDAO = new EdnDAO();
                arrRequestDetail = dnDAO.getDnDetails(Integer.parseInt(reqId));
            } catch (Exception ex) {
            }
        }
        if (arrRequestDetail == null) {
            arrRequestDetail = new ArrayList();
        }
        request.setAttribute(Constants.DN_DETAIL_LIST, arrRequestDetail);
        return true;
    }
}
