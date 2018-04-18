package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.MivBean;
import com.venus.mc.bean.MivDetailBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.MivDAO;
import com.venus.mc.process.store.miv.MivFormBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class MivReport
  extends SpineReportParser
{
  private ArrayList arrMivMaterial;
  private String mivMaterialRow = "mivMaterialRow";
  
  public MivReport(String xmlTemplate, String wordTemplate, String resultFileName)
  {
    super(xmlTemplate, wordTemplate, resultFileName);
  }
  
  protected void parse(Object object)
    throws Exception
  {
    MivFormBean mivObject = (MivFormBean)object;
    int mivId = mivObject.getMivId();
    MivFormBean bean = null;
    MivDAO mivDAO = new MivDAO();
    try
    {
      if (mivObject.getMivKind() == MivBean.KIND_COMPANY) {
        bean = mivDAO.getMivToPrint(mivId);
      } else {
        bean = mivDAO.getEMivToPrint(mivId);
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (bean == null) {
      return;
    }
    Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
    setText("mcrp_miv_date", DateUtil.formatDate(date, "dd"));
    setText("mcrp_miv_month", DateUtil.formatDate(date, "MM"));
    setText("mcrp_miv_year", DateUtil.formatDate(date, "yyyy"));
    date = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "/");
    setText("mcrp_date", DateUtil.formatDate(date, "dd"));
    setText("mcrp_month", DateUtil.formatDate(date, "MM"));
    setText("mcrp_year", DateUtil.formatDate(date, "yyyy"));
    setText("mcrp_number", bean.getMivNumber());
    setText("mcrp_receiver", bean.getReceiverName());
    setText("mcrp_customer", bean.getWhichUseName());
    setText("mcrp_rfm", bean.getRfmNumber());
    setText("mcrp_description", bean.getDescription());
    setText("mcrp_stock", bean.getStoreName());
    if (bean.getType() == MivBean.TYPE_CCDC) {
      setCheck("checkbox_ccdc", true);
    } else if (bean.getType() == MivBean.TYPE_OTHER) {
      setCheck("checkbox_other", true);
    }
    setText("mcrp_total", NumberUtil.formatMoneyDefault(Double.valueOf(bean.getTotal())) + "");
    switch (bean.getType())
    {
    case 1: 
      setCheck("checkbox1", true);
      break;
    case 2: 
      setCheck("checkbox2", true);
      break;
    case 3: 
      setCheck("checkbox2", true);
    }
    Hashtable map = new Hashtable();
    RowSAXHandler row = null;
    row = new RowSAXHandler(this.mivMaterialRow, this);
    try
    {
      this.arrMivMaterial = mivDAO.getMivDetails(mivId);
    }
    catch (Exception localException1) {}
    if (this.arrMivMaterial == null) {
      this.arrMivMaterial = new ArrayList();
    }
    String contract = "";
    String vendor = "";
    String mivIds = "0";
    MivDetailBean detail = null;
    for (int i = 0; i < this.arrMivMaterial.size(); i++)
    {
      detail = (MivDetailBean)this.arrMivMaterial.get(i);
      mivIds = mivIds + "," + detail.getMivId();
    }
    if (!mivIds.equals("0")) {
      try
      {
        ContractDAO conDAO = new ContractDAO();
        ArrayList cons = conDAO.getContactByMivIds(mivIds);
        ContractBean conBean = null;
        for (int i = 0; i < cons.size(); i++)
        {
          conBean = (ContractBean)cons.get(i);
          if (contract.equals("")) {
            contract = contract + conBean.getContractNumber();
          } else {
            contract = contract + "," + conBean.getContractNumber();
          }
          if (vendor.equals("")) {
            vendor = vendor + conBean.getVendorName();
          } else {
            vendor = vendor + "," + conBean.getVendorName();
          }
        }
      }
      catch (Exception localException2) {}
    }
    setText("mcrp_contract", contract);
    setText("mcrp_vendor", vendor);
    
    row.setRowCount(this.arrMivMaterial.size());
    map.put(this.mivMaterialRow, row);
    
    setArrTable(map);
  }
  
  private String getMaterialText(int i, String tab)
  {
    String text = "";
    MivDetailBean bean = null;
    if (i < this.arrMivMaterial.size())
    {
      bean = (MivDetailBean)this.arrMivMaterial.get(i);
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
    if (rowId.equals(this.mivMaterialRow)) {
      return getMaterialText(i, tab);
    }
    return "";
  }
}
