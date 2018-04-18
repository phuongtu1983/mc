/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.techclarification;

import com.venus.mc.tenderletter.*;
import com.venus.mc.tenderletter.*;
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
public class ListTechClarificationListAction extends SpineAction {

    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String venIds = request.getParameter("venIds");
        ArrayList arrVendor = null;
        if (!GenericValidator.isBlankOrNull(venIds)) {
            try {
                VendorDAO vendorDAO = new VendorDAO();
                arrVendor = vendorDAO.getVendorsForTenderLetter(venIds);
            } catch (Exception ex) {
            }
        }
        if (arrVendor == null) {
            arrVendor = new ArrayList();
        }
        request.setAttribute(Constants.VENDOR_LIST, arrVendor);
        return true;
    }
}
