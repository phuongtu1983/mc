/*
 *
 * Created on April 14, 2007, 8:49 AM
 */
package com.venus.mc.vendor;

import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author HOANG DIEU
 * @version
 */
public class SearchAdvVendorFormAction extends SpineAction {

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
        ArrayList arrStatus = new ArrayList();
        LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.vendor.status1"));
        value.setValue("1");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel(MCUtil.getBundleString("message.vendor.status2"));
        value.setValue("2");
        arrStatus.add(value);
        request.setAttribute(Constants.VENDOR_STATUS_LIST, arrStatus);
        request.setAttribute(Constants.VENDOR, new VendorBean());
        return true;
    }
}
