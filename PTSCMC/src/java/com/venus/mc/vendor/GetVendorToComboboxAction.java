/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.vendor;

import com.venus.mc.bean.VendorBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VendorDAO;
import java.util.ArrayList;

/**
 *
 * @author phuongtu
 * @version
 */
public class GetVendorToComboboxAction extends SpineAction {

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
        String name = request.getParameter("name");
        ArrayList vendorList = null;
        try {
            VendorDAO vendorDAO = new VendorDAO();
            VendorBean bean = new VendorBean();
            bean.setName(name);
            vendorList = vendorDAO.searchAdvVendor(bean);
        } catch (Exception ex) {
        }
        if (vendorList == null) {
            vendorList = new ArrayList();
        }
        vendorList.add(0, new VendorBean(0));
        request.setAttribute(Constants.VENDOR_LIST, vendorList);
        return true;
    }
}
