/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.workReport;
import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.mc.bean.EmcoBean;
import com.venus.mc.bean.EmcoDetailBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.EmcoDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
/**
/**
 *
 * @author kngo
 */
public class EmcoReport extends SpineReportParser {

    private ArrayList arrEmcoDetail;

    public EmcoReport(String xmlTemplate, String wordTemplate, String resultFileName) {
        super(xmlTemplate, wordTemplate, resultFileName);
    }

    @Override
    protected void parse(Object object) throws Exception {
        Integer emcoIdObject = (Integer) object;
        int emcoId = emcoIdObject.intValue();

        EmcoDAO emcoDAO = new EmcoDAO();
        EmcoBean bean = null;

        try {
            bean = emcoDAO.getEmco(emcoId);
            if (bean == null) {
                return;
            }

            setText("mcrp_orgName", bean.getDepartment());
            setText("mcrp_explanation", bean.getExplanation());
            setText("mcrp_carryOnDatePlan", "");
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

            // eVT/TTBDC Cong ty
            setCheck("checkbox3", true);

            // Result
            if (bean.getResult() == 1) {
                setCheck("checkbox5", true);
            } else {
                setCheck("checkbox6", true);
            }
            
            arrEmcoDetail = emcoDAO.getEmcoDetailsByEmco(emcoId);
            if (arrEmcoDetail == null) {
                arrEmcoDetail = new ArrayList();
            }

            Hashtable map = new Hashtable();
            RowSAXHandler row1 = null;
            row1 = new RowSAXHandler("mcoDetailRow1", this);
            row1.setRowCount(arrEmcoDetail.size());
            map.put("mcoDetailRow1", row1);
            this.setArrTable(map);
        } catch (Exception ex) {
        }
    }

    @Override
    public String getTabText(
            String rowId, int i, String tab) {
        if (rowId.equals("mcoDetailRow1")) {
            return getEmcoDetail1Text(i, tab);
        }
        return "";
    }

    private String getEmcoDetail1Text(int i, String tab) {
        String text = "";
        EmcoDetailBean bean = null;
        if (i < arrEmcoDetail.size()) {
            bean = (EmcoDetailBean) arrEmcoDetail.get(i);
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
