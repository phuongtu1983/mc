/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.comclarification;

import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ComClarificationBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ComClarificationDAO;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.util.Constants;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author mai vinh loc
 */
public class ComClarificationFormAction extends SpineAction {

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

        HttpSession session = request.getSession();
        ComClarificationBean bean = null;
        String venId = request.getParameter("venId");
        String tenId = request.getParameter("tenId");

        if (!GenericValidator.isBlankOrNull(tenId)) {
            ComClarificationDAO comDAO = new ComClarificationDAO();
            try {
                bean = comDAO.getComClarification(NumberUtil.parseInt(tenId, 0));
                if (bean == null) {
                    bean = new ComClarificationBean();
                    bean.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
                }
            } catch (Exception ex) {
            }
        }

        bean.setTenId(Integer.parseInt(tenId));
        bean.setVendorName(venId);

        request.setAttribute(Constants.COM_CLARIFICATION, bean);

        //Vendor
        VendorDAO vendorDAO = new VendorDAO();
        ArrayList vendorList = null;
        try {
            vendorList = vendorDAO.getVendorsForComClarification();
        } catch (Exception ex) {
        }
        ArrayList arrVendor = new ArrayList();
        LabelValueBean value;
        for (int i = 0; i < vendorList.size(); i++) {
            VendorBean vendor = (VendorBean) vendorList.get(i);
            value = new LabelValueBean();
            value.setLabel(String.valueOf(StringUtil.decodeString(vendor.getName())));
            value.setValue(String.valueOf(vendor.getVenId()));
            arrVendor.add(value);
        }
        request.setAttribute(Constants.VENDOR_LIST, arrVendor);

        //Status
        ArrayList arrStatus = new ArrayList();
        //LabelValueBean value;
        value = new LabelValueBean();
        value.setLabel("Đóng");
        value.setValue("0");
        arrStatus.add(value);
        value = new LabelValueBean();
        value.setLabel("Mở");
        value.setValue("1");
        arrStatus.add(value);

        request.setAttribute(Constants.STATUS_LIST, arrStatus);

        return true;
    }
}
