package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.mc.bean.DnBean;
import com.venus.mc.bean.DnDetailBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.DnDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class DnReport
  extends SpineReportParser
{
  private ArrayList arrDAO;
  private String tableRow = "matRow";
  
  public DnReport(String xmlTemplate, String wordTemplate, String resultFileName)
  {
    super(xmlTemplate, wordTemplate, resultFileName);
  }
  
  protected void parse(Object object)
    throws Exception
  {
    Integer dnIdObject = (Integer)object;
    int dnId = dnIdObject.intValue();
    DnBean bean = null;
    DnDAO dnDAO = new DnDAO();
    try
    {
      bean = dnDAO.getDn(dnId, DnBean.KIND1);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (bean == null) {
      return;
    }
    Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
    Date date2 = DateUtil.transformerStringEnDate(bean.getExpectedDate(), "/");
    
    setText("mcrp_date1", "ng�y " + DateUtil.formatDate(date, "dd") + " th�ng " + DateUtil.formatDate(date, "MM") + " n?m " + DateUtil.formatDate(date, "yyyy"));
    setText("mcrp_3", DateUtil.formatDate(date2, "dd") + "/" + DateUtil.formatDate(date2, "MM") + "/" + DateUtil.formatDate(date2, "yyyy"));
    
    setText("mcrp_number", bean.getDnNumber());
    setText("mcrp_1", bean.getOrgName().toUpperCase());
    setText("mcrp_2", bean.getProName().replaceAll("D? �n", "").toUpperCase());
    setText("mcrp_4", bean.getDeliveryPlace());
    setText("mcrp_5", bean.getDeliveryPresenter());
    
    Hashtable map = new Hashtable();
    RowSAXHandler row = null;
    row = new RowSAXHandler(this.tableRow, this);
    try
    {
      this.arrDAO = dnDAO.getDnDetails(dnId);
    }
    catch (Exception localException1) {}
    if (this.arrDAO == null) {
      this.arrDAO = new ArrayList();
    }
    row.setRowCount(this.arrDAO.size());
    map.put(this.tableRow, row);
    
    setArrTable(map);
  }
  
  private String getDnMaterialText(int i, String tab)
  {
    String text = "";
    DnDetailBean bean = null;
    if (i < this.arrDAO.size())
    {
      bean = (DnDetailBean)this.arrDAO.get(i);
      if (tab.equals("mcrp_a")) {
        text = i + 1 + "";
      } else if (tab.equals("mcrp_b")) {
        text = bean.getMatName();
      } else if (tab.equals("mcrp_c")) {
        text = bean.getMatCode();
      } else if (tab.equals("mcrp_d")) {
        text = bean.getUnit() + "";
      } else if (tab.equals("mcrp_e")) {
        text = bean.getQuantity() + "";
      } else if (tab.equals("mcrp_f")) {
        text = bean.getNote() + "";
      }
    }
    return text;
  }
  
  public String getTabText(String rowId, int i, String tab)
  {
    if (rowId.equals(this.tableRow)) {
      return getDnMaterialText(i, tab);
    }
    return "";
  }
}
