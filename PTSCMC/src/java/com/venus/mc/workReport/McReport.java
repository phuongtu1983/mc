package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.mc.bean.McBean;
import com.venus.mc.bean.McDetailBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.McDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class McReport
  extends SpineReportParser
{
  private ArrayList arrMcDetail;
  
  public McReport(String xmlTemplate, String wordTemplate, String resultFileName)
  {
    super(xmlTemplate, wordTemplate, resultFileName);
  }
  
  protected void parse(Object object)
    throws Exception
  {
    Integer mcIdObject = (Integer)object;
    int mcId = mcIdObject.intValue();
    
    McDAO mcDAO = new McDAO();
    McBean bean = null;
    try
    {
      bean = mcDAO.getMc(mcId);
      if (bean == null) {
        return;
      }
      setText("mcrp_orgName", bean.getOrgName());
      setText("mcrp_explanation", bean.getExplanation());
      setText("mcrp_carryOutDatePlan", "");
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
      if (bean.getKind() == 1) {
        setCheck("checkbox1", true);
      } else {
        setCheck("checkbox2", true);
      }
      if (bean.getResult() == 1) {
        setCheck("checkbox5", true);
      } else {
        setCheck("checkbox6", true);
      }
      this.arrMcDetail = mcDAO.getMcDetailsByMc(mcId);
      if (this.arrMcDetail == null) {
        this.arrMcDetail = new ArrayList();
      }
      Hashtable map = new Hashtable();
      RowSAXHandler row1 = null;
      row1 = new RowSAXHandler("mcDetailRow1", this);
      row1.setRowCount(this.arrMcDetail.size());
      map.put("mcDetailRow1", row1);
      setArrTable(map);
    }
    catch (Exception localException) {}
  }
  
  public String getTabText(String rowId, int i, String tab)
  {
    if (rowId.equals("mcDetailRow1")) {
      return getMcDetail1Text(i, tab);
    }
    return "";
  }
  
  private String getMcDetail1Text(int i, String tab)
  {
    String text = "";
    McDetailBean bean = null;
    if (i < this.arrMcDetail.size())
    {
      bean = (McDetailBean)this.arrMcDetail.get(i);
      if (tab.equals("mcrp_No1")) {
        text = i + 1 + "";
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
