/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.edmv.report;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EdmvBean;
import com.venus.mc.bean.EdmvMaterialBean;
import com.venus.mc.core.SpineReportParser;

import com.venus.mc.dao.EdmvDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author phuongtu
 */
public class EdmvFormReport extends SpineReportParser {

    private ArrayList arrEdmvMaterial;
    private String edmvMaterialRow = "edmvMaterialRow";

    public EdmvFormReport(String xmlTemplate, String wordTemplate, String resultFileName) {
        super(xmlTemplate, wordTemplate, resultFileName);
    }

    @Override
    protected void parse(Object object) throws Exception {
        Integer edmvIdObject = (Integer) object;
        int edmvId = edmvIdObject.intValue();
        EdmvBean bean = null;
        EdmvDAO edmvDAO = new EdmvDAO();
        try {
            bean = edmvDAO.getEdmv(edmvId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (bean == null) {
            return;
        }

        Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
        setText("mcrp_edmv_day", DateUtil.formatDate(date, "dd"));
        setText("mcrp_edmv_month", DateUtil.formatDate(date, "MM"));
        setText("mcrp_edmv_year", DateUtil.formatDate(date, "yyyy"));
        date = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "/");
        setText("mcrp_day", DateUtil.formatDate(date, "dd"));
        setText("mcrp_month", DateUtil.formatDate(date, "MM"));
        setText("mcrp_year", DateUtil.formatDate(date, "yyyy"));
        setText("mcrp_number", bean.getEdmvNumber());
        setText("mcrp_deliverer", bean.getDeliverer());
        setText("mcrp_contract", bean.getContract());
        setText("mcrp_customer", bean.getVendor());
        setText("mcrp_receiver", bean.getReceiveEmpName());
        setText("mcrp_org", bean.getOrgName());
        setText("mcrp_edmvorder", bean.getDmvOrder());
        setText("mcrp_description", bean.getDescription());
        setText("mcrp_stock", bean.getStoName());
        if (bean.getKind() == 0) {
            setCheck("checkbox3", true);
        } else if (bean.getKind() == 1) {
            setCheck("checkbox2", true);
        } else if (bean.getKind() == 2) {
            setCheck("checkbox1", true);
        }
        setText("mcrp_total", bean.getTotal() + "");
        setText("mcrp_total_str", NumberUtil.textMoney(bean.getTotal(), "VN"));
        Hashtable map = new Hashtable();
        RowSAXHandler row = null;
        row = new RowSAXHandler(edmvMaterialRow, this);
        try {
            arrEdmvMaterial = edmvDAO.getMaterialsFromEdmv(edmvId);
        } catch (Exception ex) {
        }
        if (arrEdmvMaterial == null) {
            arrEdmvMaterial = new ArrayList();
        }

        row.setRowCount(arrEdmvMaterial.size());
        map.put(edmvMaterialRow, row);

        this.setArrTable(map);
    }

    private String getMaterialText(int i, String tab) {
        String text = "";
        EdmvMaterialBean bean = null;
        if (i < arrEdmvMaterial.size()) {
            bean = (EdmvMaterialBean) arrEdmvMaterial.get(i);
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
        if (rowId.equals(edmvMaterialRow)) {
            return getMaterialText(i, tab);
        }
        return "";
    }
}
