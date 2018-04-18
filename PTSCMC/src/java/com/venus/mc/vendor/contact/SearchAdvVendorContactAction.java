/*
 *
 * Created on April 13, 2007, 2:57 PM
 */
package com.venus.mc.vendor.contact;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.VendorContactBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;

/**
 *
 * @author HOANG DIEU
 * @version
 */
public class SearchAdvVendorContactAction extends SpineAction {

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
        SearchAdvVendorContactFormBean formBean = (SearchAdvVendorContactFormBean) form;
        VendorContactBean bean = new VendorContactBean();

        bean.setVenId(formBean.getVenId());
        bean.setName(formBean.getName());
        bean.setPosition(formBean.getPosition());
        bean.setHandPhone(formBean.getHandPhone());
        bean.setHomePhone(formBean.getHomePhone());
        bean.setOfficePhone(formBean.getOfficePhone());
        bean.setEmail(formBean.getEmail());
//        bean.setBirthday(formBean.getBirthday());

        ArrayList vendorContactList = null;
        VendorDAO vendorDAO = new VendorDAO();
        try {
            vendorContactList = vendorDAO.searchAdvVendorContact(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED:VenderContact:searchAdv-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.VENDOR_CONTACT_LIST, vendorContactList);
        return true;
    }
}
