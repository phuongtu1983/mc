/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.mc.bean.McoBean;
import com.venus.mc.bean.McoDetailBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.McoDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author kngo
 */
public class McoReport extends SpineReportParser {

    private ArrayList arrMcoDetail;

    public McoReport(String xmlTemplate, String wordTemplate, String resultFileName) {
        super(xmlTemplate, wordTemplate, resultFileName);
    }

    @Override
    protected void parse(Object object) throws Exception {
        Integer mcoIdObject = (Integer) object;
        int mcoId = mcoIdObject.intValue();

        McoDAO mcoDAO = new McoDAO();
        McoBean bean = null;

        try {
            bean = mcoDAO.getMco(mcoId);
            if (bean == null) {
                return;
            }

            setText("mcrp_orgName", bean.getOrgName());
            setText("mcrp_explanation", bean.getExplanation());
            setText("mcrp_carryOutHour", bean.getCarryOutHour());
            setText("mcrp_carryOutMinute", bean.getCarryOutMinute());
            setText("mcrp_carryOutDate", bean.getCarryOutDate());

            Date date = DateUtil.transformerStringEnDate(bean.getCarryOutDate(), "/");
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

            // VT/TTBDC Cong ty
            if (bean.getKind() == 1) {
                setCheck("checkbox1", true);
            } else {
                setCheck("checkbox2", true);
            }
            setCheck("checkbox4", true);
            setText("mcrp_carryOnDatePlan", bean.getCarryOnDatePlan());

            // Result
            if (bean.getResult() == 1) {
                setCheck("checkbox5", true);
            } else {
                setCheck("checkbox6", true);
            }

            arrMcoDetail = mcoDAO.getMcoDetailsByMco(mcoId);
            if (arrMcoDetail == null) {
                arrMcoDetail = new ArrayList();
            }

            Hashtable map = new Hashtable();
            RowSAXHandler row1 = null;
            row1 = new RowSAXHandler("mcoDetailRow1", this);
            row1.setRowCount(arrMcoDetail.size());
            map.put("mcoDetailRow1", row1);
            this.setArrTable(map);
        } catch (Exception ex) {
        }
    }

    @Override
    public String getTabText(
            String rowId, int i, String tab) {
        if (rowId.equals("mcoDetailRow1")) {
            return getMcoDetail1Text(i, tab);
        }
        return "";
    }

    private String getMcoDetail1Text(int i, String tab) {
        String text = "";
        McoDetailBean bean = null;
        if (i < arrMcoDetail.size()) {
            bean = (McoDetailBean) arrMcoDetail.get(i);
            if (tab.equals("mcrp_No1")) {
                text = (i + 1) + "";
            } else if (tab.equals("mcrp_equName1")) {
                text = bean.getEquName();
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
