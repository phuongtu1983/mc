/*
 *
 * Created on April 14, 2007, 8:49 AM
 */
package com.venus.mc.vendor.contact;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author HOANG DIEU
 * @version
 */
public class SearchAdvVendorContactFormAction extends SpineAction {

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
        VendorContactFormBean bean = new VendorContactFormBean();
        String venId = request.getParameter("venId");
        if (!GenericValidator.isBlankOrNull(venId)) {
            bean.setVenId(Integer.parseInt(venId));
        }
        request.setAttribute(Constants.VENDOR_CONTACT, bean);
        return true;
    }
}
