/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.mc.bean.EdnBean;
import com.venus.mc.bean.EdnDetailBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.EdnDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author mai vinh loc
 */
public class EdnReport extends SpineReportParser {

    private ArrayList arrDAO;
    private String tableRow = "matRow";

    public EdnReport(String xmlTemplate, String wordTemplate, String resultFileName) {
        super(xmlTemplate, wordTemplate, resultFileName);
    }

    @Override
    protected void parse(Object object) throws Exception {
        Integer dnIdObject = (Integer) object;
        int dnId = dnIdObject.intValue();
        EdnBean bean = null;
        EdnDAO dnDAO = new EdnDAO();
        try {
            bean = dnDAO.getDn(dnId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (bean == null) {
            return;
        }

        Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
        Date date2 = DateUtil.transformerStringEnDate(bean.getExpectedDate(), "/");

        setText("mcrp_date1", "ngày "+ DateUtil.formatDate(date, "dd")+" tháng " + DateUtil.formatDate(date, "MM") + " năm " + DateUtil.formatDate(date, "yyyy"));
        setText("mcrp_3", DateUtil.formatDate(date2, "dd") + "/" + DateUtil.formatDate(date2, "MM") + "/" + DateUtil.formatDate(date2, "yyyy"));

        setText("mcrp_number", bean.getDnNumber());
        setText("mcrp_0", "đơn vị ngoài");
        setText("mcrp_1", bean.getOrgName().toUpperCase());
        setText("mcrp_2", bean.getProName().replaceAll("Dự án", "").toUpperCase());
        setText("mcrp_4", bean.getDeliveryPlace());
        setText("mcrp_5", bean.getDeliveryPresenter());
        
        Hashtable map = new Hashtable();
        RowSAXHandler row = null;
        row = new RowSAXHandler(tableRow, this);
        try {
            arrDAO = dnDAO.getDnDetails(dnId);
        } catch (Exception ex) {
        }
        if (arrDAO == null) {
            arrDAO = new ArrayList();
        }
        row.setRowCount(arrDAO.size());
        map.put(tableRow, row);

        this.setArrTable(map);
    }

    private String getEdnMaterialText(int i, String tab) {
        String text = "";
        EdnDetailBean bean = null;
        if (i < arrDAO.size()) {
            bean = (EdnDetailBean) arrDAO.get(i);
            if (tab.equals("mcrp_a")) {
                text = (i + 1) + "";
            } else if (tab.equals("mcrp_b")) {
                text = bean.getMatName();
            } else if (tab.equals("mcrp_c")) {
                text = bean.getUnit();
            } else if (tab.equals("mcrp_d")) {
                text = bean.getQuantity() + "";
            } else if (tab.equals("mcrp_e")) {
                text = bean.getPrice() + "";
            } else if (tab.equals("mcrp_f")) {
                text = (bean.getNote()) + "";
            }
        }
        return text;
    }

    @Override
    public String getTabText(String rowId, int i, String tab) {
        if (rowId.equals(tableRow)) {
            return getEdnMaterialText(i, tab);
        }
        return "";
    }
}
