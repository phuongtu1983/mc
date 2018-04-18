/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.mc.bean.RfmBean;
import com.venus.mc.bean.RfmDetailBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.RfmDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author mai vinh loc
 */
public class ErfmReport extends SpineReportParser {

    private ArrayList arrDAO;
    private String tableRow = "matRow";

    public ErfmReport(String xmlTemplate, String wordTemplate, String resultFileName) {
        super(xmlTemplate, wordTemplate, resultFileName);
    }

    @Override
    protected void parse(Object object) throws Exception {
        Integer rfmIdObject = (Integer) object;
        int rfmId = rfmIdObject.intValue();
        RfmBean bean = null;
        RfmDAO rfmDAO = new RfmDAO();
        try {
            bean = rfmDAO.getRfm(rfmId,1,0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (bean == null) {
            return;
        }

        Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
        Date date2 = DateUtil.transformerStringEnDate(bean.getDeliveryDate(), "/");

        setText("mcrp_date1", DateUtil.formatDate(date, "dd") + "/" + DateUtil.formatDate(date, "MM") + "/" + DateUtil.formatDate(date, "yyyy"));
        setText("mcrp_5", DateUtil.formatDate(date2, "dd") + "/" + DateUtil.formatDate(date2, "MM") + "/" + DateUtil.formatDate(date2, "yyyy"));

        setText("mcrp_number", bean.getRfmNumber());
        setText("mcrp_1", bean.getReqOrgName());
        setText("mcrp_2", "");
        setText("mcrp_3", "");
        setText("mcrp_4", bean.getOrgName());
        setText("mcrp_6", bean.getDeliveryPlace());
        setText("mcrp_7", bean.getStoreName() + "");

        switch (bean.getReqType()) {
            case 1:
                setCheck("checkbox1", true);
                break;
            case 2:
                setCheck("checkbox2", true);
                break;
            case 3:
                setCheck("checkbox3", true);
                break;
        }

        Hashtable map = new Hashtable();
        RowSAXHandler row = null;
        row = new RowSAXHandler(tableRow, this);
        try {
            arrDAO = rfmDAO.getRfmDetails(rfmId,1);
        } catch (Exception ex) {
        }
        if (arrDAO == null) {
            arrDAO = new ArrayList();
        }
        row.setRowCount(arrDAO.size());
        map.put(tableRow, row);

        this.setArrTable(map);
    }

    private String getRfmMaterialText(int i, String tab) {
        String text = "";
        RfmDetailBean bean = null;
        if (i < arrDAO.size()) {
            bean = (RfmDetailBean) arrDAO.get(i);
            if (tab.equals("mcrp_a")) {
                text = (i + 1) + "";
            } else if (tab.equals("mcrp_b")) {
                text = bean.getMatName();
            } else if (tab.equals("mcrp_c")) {
                text = bean.getUnit();
            } else if (tab.equals("mcrp_d")) {
                text = bean.getQuantity() + "";
            } else if (tab.equals("mcrp_e")) {
                text = bean.getMsvNumber() + "";
            } else if (tab.equals("mcrp_f")) {
                text = (bean.getComment()) + "";
            }
        }
        return text;
    }

    @Override
    public String getTabText(String rowId, int i, String tab) {
        if (rowId.equals(tableRow)) {
            return getRfmMaterialText(i, tab);
        }
        return "";
    }
}
