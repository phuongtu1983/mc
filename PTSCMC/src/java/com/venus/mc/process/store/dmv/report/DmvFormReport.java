/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.dmv.report;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MsvMaterialBean;
import com.venus.mc.bean.MsvBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.MsvDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author phuongtu
 */
public class DmvFormReport extends SpineReportParser {

    private ArrayList arrDmvMaterial;
    private String dmvMaterialRow = "dmvMaterialRow";
    private String currency = "";

    public DmvFormReport(String xmlTemplate, String wordTemplate, String resultFileName) {
        super(xmlTemplate, wordTemplate, resultFileName);
    }

    @Override
    protected void parse(Object object) throws Exception {
        Integer dmvIdObject = (Integer) object;
        int msvId = dmvIdObject.intValue();
        MsvBean bean = null;
        MsvDAO msvDAO = new MsvDAO();
        try {
            bean = msvDAO.getMsv(msvId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (bean == null) {
            return;
        }

        Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
        setText("mcrp_dmv_day", DateUtil.formatDate(date, "dd"));
        setText("mcrp_dmv_month", DateUtil.formatDate(date, "MM"));
        setText("mcrp_dmv_year", DateUtil.formatDate(date, "yyyy"));
        date = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "/");
        setText("mcrp_day", DateUtil.formatDate(date, "dd"));
        setText("mcrp_month", DateUtil.formatDate(date, "MM"));
        setText("mcrp_year", DateUtil.formatDate(date, "yyyy"));
        setText("mcrp_number", bean.getMsvNumber());
        setText("mcrp_deliverer", bean.getDeliverer());
        setText("mcrp_contract", bean.getConNumber());
        setText("mcrp_customer", bean.getVendorName());
        setText("mcrp_receiver", bean.getReceiveEmpName());
        setText("mcrp_org", bean.getOrgName());
        setText("mcrp_dmvorder", bean.getDmvOrder());
        setText("mcrp_description", bean.getDescription());        
        setText("mcrp_stock", bean.getStoName());        
        if (bean.getKind() == 0) {
            setCheck("checkbox3", true);
        } else if (bean.getKind()==1) {
            setCheck("checkbox2", true);
        } else if (bean.getKind()==2) {
            setCheck("checkbox1", true);
        }
        setText("mcrp_total", NumberUtil.formatMoneyDefault(bean.getTotal(), bean.getCurrency()));
        setText("mcrp_total_str", NumberUtil.textMoney(bean.getTotal(), bean.getCurrency()));
        currency = bean.getCurrency();
        Hashtable map = new Hashtable();
        RowSAXHandler row = null;
        row = new RowSAXHandler(dmvMaterialRow, this);
        try {
            arrDmvMaterial = msvDAO.getMaterialsFromMsv(msvId);
        } catch (Exception ex) {
        }
        if (arrDmvMaterial == null) {
            arrDmvMaterial = new ArrayList();
        }
        
        row.setRowCount(arrDmvMaterial.size());
        map.put(dmvMaterialRow, row);

        this.setArrTable(map);
    }

    private String getMaterialText(int i, String tab) {
        String text = "";
        MsvMaterialBean bean = null;
        if (i < arrDmvMaterial.size()) {
            bean = (MsvMaterialBean) arrDmvMaterial.get(i);
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
                text = NumberUtil.formatMoneyDefault(bean.getPrice(), currency);
            } else if (tab.equals("mcrp_amount")) {
                text = NumberUtil.formatMoneyDefault(bean.getTotal(), currency);
            }
        }
        return text;
    }

    @Override
    public String getTabText(String rowId, int i, String tab) {
        if (rowId.equals(dmvMaterialRow)) {
            return getMaterialText(i, tab);
        }
        return "";
    }
}
