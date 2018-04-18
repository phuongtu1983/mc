/*
 *
 * Created on April 13, 2007, 2:23 PM
 */
package com.venus.mc.vendor;

import com.venus.core.util.LogUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author HOANG DIEU
 * @version
 */
public class SearchSimpleVendorAction extends SpineAction {

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
        SearchFormBean vendorForm = (SearchFormBean) form;
        int fieldid = vendorForm.getSearchid();
        String strFieldvalue = vendorForm.getSearchvalue();
        ArrayList vendorList = null;
        VendorDAO vendorDAO = new VendorDAO();
        try {
            vendorList = vendorDAO.searchSimpleVendor(fieldid, StringUtil.encodeHTML(strFieldvalue));
        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:searchSimple-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.VENDOR_LIST, vendorList);
        return true;
    }
}
