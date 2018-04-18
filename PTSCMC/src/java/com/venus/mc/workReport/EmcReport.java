/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.mc.bean.EmcBean;
import com.venus.mc.bean.EmcDetailBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.EmcDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author kngo
 */
public class EmcReport extends SpineReportParser {

    private ArrayList arrEmcDetail;

    public EmcReport(String xmlTemplate, String wordTemplate, String resultFileName) {
        super(xmlTemplate, wordTemplate, resultFileName);
    }

    @Override
    protected void parse(Object object) throws Exception {
        Integer emcIdObject = (Integer) object;
        int emcId = emcIdObject.intValue();

        EmcDAO emcDAO = new EmcDAO();
        EmcBean bean = null;

        try {
            bean = emcDAO.getEmc(emcId);
            if (bean == null) {
                return;
            }

            setText("mcrp_orgName", bean.getDepartment());
            setText("mcrp_explanation", bean.getExplanation());
            setText("mcrp_carryOnHour", bean.getCarryOnHour());
            setText("mcrp_carryOnMinute", bean.getCarryOnMinute());
            setText("mcrp_carryOnDate", bean.getCarryOnDate());

            Date date = DateUtil.transformerStringEnDate(bean.getCarryOnDate(), "/");
            SimpleDateFormat sdf = null;
            sdf = new SimpleDateFormat("dd");
            setText("mcrp_day1", sdf.format(date));
            setText("mcrp_day2", sdf.format(date));
            sdf = new SimpleDateFormat("MM");
            setText("mcrp_month1", sdf.format(date));
            setText("mcrp_month2", sdf.format(date));
            sdf = new SimpleDateFormat("yyyy");
            setText("mcrp_year1", sdf.format(date));
            setText("mcrp_year2", sdf.format(date));

            // eVT/TTBDC Cong ty
            setCheck("checkbox3", true);

            setCheck("checkbox4", true);
            setText("mcrp_carryOutDatePlan", bean.getCarryOutDatePlan());

            // Result
            if (bean.getResult() == 1) {
                setCheck("checkbox5", true);
            } else {
                setCheck("checkbox6", true);
            }

            arrEmcDetail = emcDAO.getEmcDetailsByEmc(emcId);
            if (arrEmcDetail == null) {
                arrEmcDetail = new ArrayList();
            }

            Hashtable map = new Hashtable();
            RowSAXHandler row1 = null;
            row1 = new RowSAXHandler("mcDetailRow1", this);
            row1.setRowCount(arrEmcDetail.size());
            map.put("mcDetailRow1", row1);
            this.setArrTable(map);
        } catch (Exception ex) {
        }
    }

    @Override
    public String getTabText(
            String rowId, int i, String tab) {
        if (rowId.equals("mcDetailRow1")) {
            return getEmcDetail1Text(i, tab);
        }
        return "";
    }

    private String getEmcDetail1Text(int i, String tab) {
        String text = "";
        EmcDetailBean bean = null;
        if (i < arrEmcDetail.size()) {
            bean = (EmcDetailBean) arrEmcDetail.get(i);
            if (tab.equals("mcrp_No1")) {
                text = (i + 1) + "";
            } else if (tab.equals("mcrp_equName1")) {
                text = bean.getEquipment();
            } else if (tab.equals("mcrp_unit1")) {
                text = bean.getUnit();
            } else if (tab.equals("mcrp_quantity1")) {
                text = bean.getQuantity() + "";
            } else if (tab.equals("mcrp_spec1")) {
                text = bean.getSpec();
            }
        }
        return text;
    }
}
