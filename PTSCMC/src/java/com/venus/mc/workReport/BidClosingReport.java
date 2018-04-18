/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.mc.bean.BidClosingGroupBean;
import com.venus.mc.bean.BidClosingReportBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.tenderplan.TenderEvaluateVendorFormBean;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author kngo
 */
public class BidClosingReport extends SpineReportParser {

    private ArrayList arrBidClosingGroup;
    private ArrayList arrVendor;

    public BidClosingReport(String xmlTemplate, String wordTemplate, String resultFileName) {
        super(xmlTemplate, wordTemplate, resultFileName);
    }

    @Override
    protected void parse(Object object) throws Exception {
        Integer bcrIdObject = (Integer) object;
        int bcrId = bcrIdObject.intValue();
        BidClosingReportBean bean = null;
        TenderPlanDAO bcrDAO = new TenderPlanDAO();
        try {
            bean = bcrDAO.getBidClosingReport(bcrId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (bean == null) {
            return;
        }

        TenderPlanBean tenderBean = null;
        try {
            tenderBean = bcrDAO.getTenderPlan(bean.getTenId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (tenderBean == null) {
            return;
        }

        Date date = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "/");
        SimpleDateFormat sdf = null;
        sdf = new SimpleDateFormat("dd");
        setText("mcrp_day", sdf.format(date));
        sdf = new SimpleDateFormat("MM");
        setText("mcrp_month", sdf.format(date));
        sdf = new SimpleDateFormat("yyyy");
        setText("mcrp_year", sdf.format(date));

        setText("mcrp_number", bean.getReportNumber());
        setText("mcrp_close_time", bean.getClosingTime() + " ngày " + bean.getClosingDate());
        setText("mcrp_end_time", bean.getEndClosingTime() + " ngày " + bean.getEndClosingDate());
        setText("mcrp_packName", tenderBean.getPackName());


        Hashtable map = new Hashtable();
        RowSAXHandler row = null;
        row = new RowSAXHandler("bidClosingGroupRow", this);
        try {
            arrBidClosingGroup = bcrDAO.getBidClosingGroup(bcrId);
        } catch (Exception ex) {
        }
        if (arrBidClosingGroup == null) {
            arrBidClosingGroup = new ArrayList();
        }
        row.setRowCount(arrBidClosingGroup.size());
        map.put("bidClosingGroupRow", row);


        row = new RowSAXHandler("vendorRow", this);
        try {
            arrVendor = bcrDAO.getTenderPlanVendorDetailBidded(bean.getTenId());
        } catch (Exception ex) {
        }
        if (arrVendor == null) {
            arrVendor = new ArrayList();
        }
        row.setRowCount(arrVendor.size());
        map.put("vendorRow", row);

        row = new RowSAXHandler("bidClosingGroupSignRow", this);
        row.setRowCount(arrBidClosingGroup.size());
        map.put("bidClosingGroupSignRow", row);

        this.setArrTable(map);
    }

    private String getBidClosingGroupText(int i, String tab) {
        String text = "";
        BidClosingGroupBean bean = null;
        if (i < arrBidClosingGroup.size()) {
            bean = (BidClosingGroupBean) arrBidClosingGroup.get(i);
            if (tab.equals("mcrp_n1")) {
                text = (i + 1) + "";
            } else if (tab.equals("mcrp_employee")) {
                text = bean.getEmployee();
            } else if (tab.equals("mcrp_org")) {
                text = bean.getPosition();
            } else if (tab.equals("mcrp_position")) {
                text = bean.getNote();
            }
        }
        return text;
    }

    private String getBidClosingGroupSignText(int i, String tab) {
        String text = "";
        BidClosingGroupBean bean = null;
        if (i < arrBidClosingGroup.size()) {
            bean = (BidClosingGroupBean) arrBidClosingGroup.get(i);
            if (tab.equals("mcrp_n3")) {
                text = (i + 1) + "";
            } else if (tab.equals("mcrp_employee_closing")) {
                text = bean.getEmployee();
            }
        }
        return text;
    }

    private String getVendorText(int i, String tab) {
        String text = "";
        if (i < arrVendor.size()) {
            TenderEvaluateVendorFormBean bean = (TenderEvaluateVendorFormBean) arrVendor.get(i);
            if (tab.equals("mcrp_n2")) {
                text = (i + 1) + "";
            } else if (tab.equals("mcrp_vendor")) {
                text = bean.getVenName();
            } else if (tab.equals("mcrp_vendor_address")) {
                text = bean.getVenAddress();
            }
        }
        return text;
    }

    @Override
    public String getTabText(String rowId, int i, String tab) {
        if (rowId.equals("bidClosingGroupRow")) {
            return getBidClosingGroupText(i, tab);
        } else if (rowId.equals("vendorRow")) {
            return getVendorText(i, tab);
        } else if (rowId.equals("bidClosingGroupSignRow")) {
            return getBidClosingGroupSignText(i, tab);
        }
        return "";
    }
}
