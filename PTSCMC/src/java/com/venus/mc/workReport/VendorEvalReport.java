package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.mc.bean.VendorEvalDetailBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.VendorDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class VendorEvalReport
  extends SpineReportParser
{
  private ArrayList arrDAO;
  private String tableRow = "matRow";
  
  public VendorEvalReport(String xmlTemplate, String wordTemplate, String resultFileName)
  {
    super(xmlTemplate, wordTemplate, resultFileName);
  }
  
  protected void parse(Object object)
    throws Exception
  {
    Integer venEvalIdObject = (Integer)object;
    int venEvalId = venEvalIdObject.intValue();
    VendorEvalDetailBean bean = null;
    VendorDAO vendorDAO = new VendorDAO();
    try
    {
      bean = vendorDAO.getVendorEvalDetail(venEvalId);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (bean == null) {
      return;
    }
    Date date = DateUtil.transformerStringEnDate(bean.getFromDate(), "/");
    Date date2 = DateUtil.transformerStringEnDate(bean.getToDate(), "/");
    
    setText("mcrp_d1", "ng�y " + DateUtil.formatDate(date, "dd") + " th�ng " + DateUtil.formatDate(date, "MM") + " n?m " + DateUtil.formatDate(date, "yyyy"));
    setText("mcrp_d2", "ng�y " + DateUtil.formatDate(date2, "dd") + " th�ng " + DateUtil.formatDate(date2, "MM") + " n?m " + DateUtil.formatDate(date2, "yyyy"));
    setText("mcrp_2", "" + DateUtil.today("dd") + " th�ng " + DateUtil.today("MM") + " n?m " + DateUtil.today("yyyy"));
    
    setText("mcrp_1", bean.getEvalNumber());
    setText("mcrp_3", bean.getOrgName());
    setText("mcrp_4", bean.getVendorName());
    setText("mcrp_5", bean.getAddress());
    setText("mcrp_6", bean.getPresenter());
    setText("mcrp_7", bean.getPhone() + "/" + bean.getFax());
    for (int i = 0; i < bean.getResult().length; i++) {
      setText("mcrp_" + (8 + i), bean.getResult()[i]);
    }
    for (int i = 0; i < bean.getResult().length; i++) {
      setText("mcrp_" + (18 + i), bean.getNotes()[i]);
    }
    setText("mcrp_17", bean.getResultString());
    
    Hashtable map = new Hashtable();
    RowSAXHandler row = null;
    row = new RowSAXHandler(this.tableRow, this);
    try
    {
      this.arrDAO = vendorDAO.getVendorEvals(venEvalId);
    }
    catch (Exception localException1) {}
    if (this.arrDAO == null) {
      this.arrDAO = new ArrayList();
    }
    row.setRowCount(this.arrDAO.size());
    map.put(this.tableRow, row);
    
    setArrTable(map);
  }
  
  private String getVendorMaterialText(int i, String tab)
  {
    String text = "";
    VendorEvalDetailBean bean = null;
    if (i < this.arrDAO.size()) {
      bean = (VendorEvalDetailBean)this.arrDAO.get(i);
    }
    return text;
  }
  
  public String getTabText(String rowId, int i, String tab)
  {
    if (rowId.equals(this.tableRow)) {
      return getVendorMaterialText(i, tab);
    }
    return "";
  }
}
