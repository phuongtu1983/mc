/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.handedreport.report;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.HandedReportBean;
import com.venus.mc.bean.HandedReportDetailBean;
import com.venus.mc.bean.MsvBean;
import com.venus.mc.bean.MsvMaterialBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.HandedReportDAO;
import com.venus.mc.dao.MsvDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author phuongtu
 */
public class HandedReportFormReport extends SpineReportParser {

    private ArrayList arrEquipment;
    private String hdEquipmentRow = "hdEquipmentRow";

    public HandedReportFormReport(String xmlTemplate, String wordTemplate, String resultFileName) {
        super(xmlTemplate, wordTemplate, resultFileName);
    }

    @Override
    protected void parse(Object object) throws Exception {
        Integer hrIdObject = (Integer) object;
        int hrId = hrIdObject.intValue();
        HandedReportBean bean = null;
        HandedReportDAO hdDAO = new HandedReportDAO();
        try {
            bean = hdDAO.getHandedReport(hrId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (bean == null) {
            return;
        }

        Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
        setText("mcrp_dd", DateUtil.formatDate(date, "dd"));
        setText("mcrp_mm", DateUtil.formatDate(date, "MM"));
        setText("mcrp_yyyy", DateUtil.formatDate(date, "yyyy"));
        setText("mcrp_time", bean.getCreatedTime());
        setText("mcrp_delivery", bean.getCreatedEmpName());
        setText("mcrp_receive", bean.getEmpReceiveName());
        Hashtable map = new Hashtable();
        RowSAXHandler row = null;
        row = new RowSAXHandler(hdEquipmentRow, this);
        try {
            arrEquipment = hdDAO.getHandedReportDetails(hrId);
        } catch (Exception ex) {
        }
        if (arrEquipment == null) {
            arrEquipment = new ArrayList();
        }

        row.setRowCount(arrEquipment.size());
        map.put(hdEquipmentRow, row);

        this.setArrTable(map);
    }

    private String getMaterialText(int i, String tab) {
        String text = "";
        HandedReportDetailBean bean = null;
        if (i < arrEquipment.size()) {
            bean = (HandedReportDetailBean) arrEquipment.get(i);
            if (tab.equals("mcrp_no")) {
                text = (i + 1) + "";
            } else if (tab.equals("mcrp_equipment")) {
                text = bean.getEquipmentName();
            } else if (tab.equals("mcrp_unit")) {
                text = bean.getUnitName();
            } else if (tab.equals("mcrp_quantity")) {
                text = bean.getQuantity() + "";
            } else if (tab.equals("mcrp_description")) {
                text = bean.getSpecifications() + "";
            } else if (tab.equals("mcrp_comment")) {
                text = bean.getComment() + "";
            }
        }
        return text;
    }

    @Override
    public String getTabText(String rowId, int i, String tab) {
        if (rowId.equals(hdEquipmentRow)) {
            return getMaterialText(i, tab);
        }
        return "";
    }
}
