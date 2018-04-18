/*
 *
 * Created on April 13, 2007, 2:57 PM
 */
package com.venus.mc.vendor;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.VendorBean;
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
public class SearchAdvVendorAction extends SpineAction {

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
        SearchAdvVendorFormBean formBean = (SearchAdvVendorFormBean) form;
        VendorBean bean = new VendorBean();

        bean.setName(formBean.getName());
        bean.setPresenter(formBean.getPresenter());
        bean.setAddress(formBean.getAddress());
        bean.setPhone(formBean.getPhone());
        bean.setFax(formBean.getFax());
        bean.setEmail(formBean.getEmail());
        bean.setWeb(formBean.getWeb());
        bean.setCharterCapital(formBean.getCharterCapital());

        ArrayList vendorList = null;
        VendorDAO vendorDAO = new VendorDAO();
        try {
            vendorList = vendorDAO.searchAdvVendor(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:searchAdv-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.VENDOR_LIST, vendorList);
        return true;
    }
}
