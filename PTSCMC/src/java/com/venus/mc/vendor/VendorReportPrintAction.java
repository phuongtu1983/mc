/*
 *
 * Created on April 13, 2007, 2:23 PM
 */
package com.venus.mc.vendor;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.VendorDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author HOANG DIEU
 * @version
 */
public class VendorReportPrintAction extends SpineAction {

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
        int searchid = NumberUtil.parseInt(request.getParameter("searchid"), 0);
        String searchvalue = request.getParameter("searchvalue");
        ArrayList vendorList = null;
        VendorDAO vendorDAO = new VendorDAO();
        try {
            vendorList = vendorDAO.searchSimpleVendorForExport(searchid, StringUtil.encodeHTML(searchvalue));
        } catch (Exception ex) {
            LogUtil.error("FAILED:Vender:searchSimple-" + ex.getMessage());
            ex.printStackTrace();
        }
        if (vendorList == null) {
            vendorList = new ArrayList();
        }
        String templateFileName = request.getSession().getServletContext().getRealPath("/templates/vendor_exported_template.xls");

        try {
            Map beans = new HashMap();
            beans.put("vendor", vendorList);
            ExcelExport exporter = new ExcelExport();
            exporter.setBeans(beans);
            long milis = System.currentTimeMillis();
            exporter.export(request, response, templateFileName, "vendor.xls");
            milis = System.currentTimeMillis() - milis;
            System.out.println("vendor.xls : " + "time : " + (int) ((milis / 1000) % 60) + " phut " + (int) ((milis / 1000) / 60) + " mili giay");
        } catch (Exception ex) {
            LogUtil.error("FAILED:PrincipleReportPrint:print-" + ex.getMessage());
            ex.printStackTrace();
        }

        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
