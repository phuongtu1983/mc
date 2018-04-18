/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emsv.report;

import com.venus.mc.process.store.msv.report.*;
import com.venus.mc.workReport.*;
import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmsvBean;
import com.venus.mc.bean.EmsvMaterialBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.EmsvDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author phuongtu
 */
public class EmsvFormReport extends SpineReportParser {

    private ArrayList arrEmsvMaterial;
    private String emsvMaterialRow = "emsvMaterialRow";

    public EmsvFormReport(String xmlTemplate, String wordTemplate, String resultFileName) {
        super(xmlTemplate, wordTemplate, resultFileName);
    }

    @Override
    protected void parse(Object object) throws Exception {
        Integer emsvIdObject = (Integer) object;
        int emsvId = emsvIdObject.intValue();
        EmsvBean bean = null;
        EmsvDAO emsvDAO = new EmsvDAO();
        try {
            bean = emsvDAO.getEmsv(emsvId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (bean == null) {
            return;
        }

        Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
        setText("mcrp_emsv_day", DateUtil.formatDate(date, "dd"));
        setText("mcrp_emsv_month", DateUtil.formatDate(date, "MM"));
        setText("mcrp_emsv_year", DateUtil.formatDate(date, "yyyy"));
        date = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "/");
        setText("mcrp_day", DateUtil.formatDate(date, "dd"));
        setText("mcrp_month", DateUtil.formatDate(date, "MM"));
        setText("mcrp_year", DateUtil.formatDate(date, "yyyy"));
        setText("mcrp_number", bean.getEmsvNumber());
        setText("mcrp_deliverer", bean.getDeliverer());        
        setText("mcrp_stock", bean.getStoName());
        setText("mcrp_customer", bean.getVendor());
        setText("mcrp_contract", bean.getContract());        
        
        setText("mcrp_total", bean.getTotal() + "");
        setText("mcrp_total_str", NumberUtil.textMoney(bean.getTotal(), "VN"));
        Hashtable map = new Hashtable();
        RowSAXHandler row = null;
        row = new RowSAXHandler(emsvMaterialRow, this);
        try {
            arrEmsvMaterial = emsvDAO.getMaterialsFromEmsv(emsvId);
        } catch (Exception ex) {
        }
        if (arrEmsvMaterial == null) {
            arrEmsvMaterial = new ArrayList();
        }
        
        row.setRowCount(arrEmsvMaterial.size());
        map.put(emsvMaterialRow, row);

        this.setArrTable(map);
    }

    private String getMaterialText(int i, String tab) {
        String text = "";
        EmsvMaterialBean bean = null;
        if (i < arrEmsvMaterial.size()) {
            bean = (EmsvMaterialBean) arrEmsvMaterial.get(i);
            if (tab.equals("mcrp_no")) {
                text = (i + 1) + "";
            } else if (tab.equals("mcrp_material")) {
                text = bean.getMatName();
            } else if (tab.equals("mcrp_code")) {
                text = bean.getMatCode();
            } else if (tab.equals("mcrp_unit")) {
                text = bean.getUnit();
            } else if (tab.equals("mcrp_quantity")) {
                text = bean.getQuantity() + "";
            } else if (tab.equals("mcrp_price")) {
                text = bean.getPrice() + "";
            } else if (tab.equals("mcrp_amount")) {
                text = bean.getTotal() + "";
            }
        }
        return text;
    }

    @Override
    public String getTabText(String rowId, int i, String tab) {
        if (rowId.equals(emsvMaterialRow)) {
            return getMaterialText(i, tab);
        }
        return "";
    }
}
