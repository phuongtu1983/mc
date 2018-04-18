/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.techclarification;

import com.venus.mc.tenderletter.*;
import com.venus.mc.intermemo.*;
import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VendorDAO;
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
public class ListTechClarificationDetailAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String tenId = request.getParameter("tenId");
        tenId="1";
        ArrayList arrRequestDetail = null;
        if (!GenericValidator.isBlankOrNull(tenId)) {
            try {
                VendorDAO tenderDAO = new VendorDAO();
                arrRequestDetail = tenderDAO.getVendorsForTenderLetter(tenId);
            } catch (Exception ex) {
            }
        }
        if (arrRequestDetail == null) {
            arrRequestDetail = new ArrayList();
        }
        request.setAttribute(Constants.TENDER_LETTER_LIST, arrRequestDetail);
        return true;
    }
}
