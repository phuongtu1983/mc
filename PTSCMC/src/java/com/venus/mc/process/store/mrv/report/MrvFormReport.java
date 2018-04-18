package com.venus.mc.process.store.mrv.report;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MsvBean;
import com.venus.mc.bean.MsvMaterialBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.MsvDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class MrvFormReport
  extends SpineReportParser
{
  private ArrayList arrMrvMaterial;
  private String mrvMaterialRow = "mrvMaterialRow";
  
  public MrvFormReport(String xmlTemplate, String wordTemplate, String resultFileName)
  {
    super(xmlTemplate, wordTemplate, resultFileName);
  }
  
  protected void parse(Object object)
    throws Exception
  {
    Integer mivIdObject = (Integer)object;
    int msvId = mivIdObject.intValue();
    MsvBean bean = null;
    MsvDAO msvDAO = new MsvDAO();
    try
    {
      bean = msvDAO.getMsv(msvId);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (bean == null) {
      return;
    }
    Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
    setText("mcrp_mrv_day", DateUtil.formatDate(date, "dd"));
    setText("mcrp_mrv_month", DateUtil.formatDate(date, "MM"));
    setText("mcrp_mrv_year", DateUtil.formatDate(date, "yyyy"));
    date = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "/");
    setText("mcrp_day", DateUtil.formatDate(date, "dd"));
    setText("mcrp_month", DateUtil.formatDate(date, "MM"));
    setText("mcrp_year", DateUtil.formatDate(date, "yyyy"));
    setText("mcrp_number", bean.getMsvNumber());
    setText("mcrp_deliverer", bean.getDeliveryEmpName());
    setText("mcrp_miv", bean.getMivNumbers());
    setText("mcrp_stock", bean.getStoName());
    setText("mcrp_description", bean.getDescription());
    setText("mcrp_total", bean.getTotal() + "");
    setText("mcrp_total_str", NumberUtil.textMoney(Double.valueOf(bean.getTotal()), "VN"));
    Hashtable map = new Hashtable();
    RowSAXHandler row = null;
    row = new RowSAXHandler(this.mrvMaterialRow, this);
    try
    {
      this.arrMrvMaterial = msvDAO.getMaterialsFromMsv(msvId);
    }
    catch (Exception localException1) {}
    if (this.arrMrvMaterial == null) {
      this.arrMrvMaterial = new ArrayList();
    }
    row.setRowCount(this.arrMrvMaterial.size());
    map.put(this.mrvMaterialRow, row);
    
    setArrTable(map);
  }
  
  private String getMaterialText(int i, String tab)
  {
    String text = "";
    MsvMaterialBean bean = null;
    if (i < this.arrMrvMaterial.size())
    {
      bean = (MsvMaterialBean)this.arrMrvMaterial.get(i);
      if (tab.equals("mcrp_no")) {
        text = i + 1 + "";
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
  
  public String getTabText(String rowId, int i, String tab)
  {
    if (rowId.equals(this.mrvMaterialRow)) {
      return getMaterialText(i, tab);
    }
    return "";
  }
}
