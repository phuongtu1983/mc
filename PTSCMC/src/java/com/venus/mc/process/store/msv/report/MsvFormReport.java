package com.venus.mc.process.store.msv.report;

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

public class MsvFormReport
  extends SpineReportParser
{
  private ArrayList arrMsvMaterial;
  private String msvMaterialRow = "msvMaterialRow";
  
  public MsvFormReport(String xmlTemplate, String wordTemplate, String resultFileName)
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
    setText("mcrp_msv_day", DateUtil.formatDate(date, "dd"));
    setText("mcrp_msv_month", DateUtil.formatDate(date, "MM"));
    setText("mcrp_msv_year", DateUtil.formatDate(date, "yyyy"));
    date = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "/");
    setText("mcrp_day", DateUtil.formatDate(date, "dd"));
    setText("mcrp_month", DateUtil.formatDate(date, "MM"));
    setText("mcrp_year", DateUtil.formatDate(date, "yyyy"));
    setText("mcrp_number", bean.getMsvNumber());
    setText("mcrp_deliverer", bean.getDeliverer());
    setText("mcrp_mrir", bean.getMrirNumber());
    setText("mcrp_add", bean.getAddress());
    setText("mcrp_vendor", bean.getVendorName());
    setText("mcrp_store", bean.getStoName());
    setText("mcrp_content", bean.getDescription());
    if (bean.getKind() == 1) {
      setCheck("checkbox1", true);
    } else if (bean.getKind() == 0) {
      setCheck("checkbox2", true);
    }
    setText("mcrp_total", NumberUtil.formatMoneyDefault(Double.valueOf(bean.getTotal())) + "");
    setText("mcrp_total_str", NumberUtil.textMoney(Double.valueOf(bean.getTotal()), bean.getCurrency()));
    Hashtable map = new Hashtable();
    RowSAXHandler row = null;
    row = new RowSAXHandler(this.msvMaterialRow, this);
    try
    {
      this.arrMsvMaterial = msvDAO.getMaterialsFromMsv(msvId);
    }
    catch (Exception localException1) {}
    if (this.arrMsvMaterial == null) {
      this.arrMsvMaterial = new ArrayList();
    }
    row.setRowCount(this.arrMsvMaterial.size());
    map.put(this.msvMaterialRow, row);
    
    setArrTable(map);
  }
  
  private String getMaterialText(int i, String tab)
  {
    String text = "";
    MsvMaterialBean bean = null;
    if (i < this.arrMsvMaterial.size())
    {
      bean = (MsvMaterialBean)this.arrMsvMaterial.get(i);
      if (tab.equals("mcrp_no")) {
        text = i + 1 + "";
      } else if (tab.equals("mcrp_material")) {
        text = bean.getMatName();
      } else if (tab.equals("mcrp_code")) {
        text = bean.getMatCode();
      } else if (tab.equals("mcrp_unit")) {
        text = bean.getUnit();
      } else if (tab.equals("mcrp_quantity")) {
        text = NumberUtil.formatMoneyDefault(Double.valueOf(bean.getQuantity())) + "";
      } else if (tab.equals("mcrp_price")) {
        text = NumberUtil.formatMoneyDefault(Double.valueOf(bean.getPrice())) + "";
      } else if (tab.equals("mcrp_amount")) {
        text = NumberUtil.formatMoneyDefault(Double.valueOf(bean.getTotal())) + "";
      }
    }
    return text;
  }
  
  public String getTabText(String rowId, int i, String tab)
  {
    if (rowId.equals(this.msvMaterialRow)) {
      return getMaterialText(i, tab);
    }
    return "";
  }
}
