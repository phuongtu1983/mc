package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.contract.ContractDetailFormBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;

public class OrderReport
  extends SpineReportParser
{
  private ArrayList arrDAO;
  private String tableRow = "matRow";
  private boolean permission = false;
  private boolean vnd = false;
  
  public OrderReport(String xmlTemplate, String wordTemplate, String resultFileName)
  {
    super(xmlTemplate, wordTemplate, resultFileName);
  }
  
  protected void parse(Object object)
    throws Exception
  {
    Integer conIdObject = (Integer)object;
    int conId = conIdObject.intValue();
    ContractBean bean = null;
    ContractDAO contractDAO = new ContractDAO();
    try
    {
      bean = contractDAO.getContract(conId);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (bean == null) {
      return;
    }
    double other_cost = 0.0D;
    try
    {
      other_cost = contractDAO.getOtherCost(conId, bean.getVenId());
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    double sum_other_cost = 0.0D;
    sum_other_cost = other_cost + bean.getTransport() + bean.getOtherFee();
    if (sum_other_cost != 0.0D) {
      setText("mcrp_13", NumberUtil.formatMoneyDefault(sum_other_cost, bean.getCurrency()));
    } else {
      setText("mcrp_13", "?� bao g?m");
    }
    Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
    Date date2 = DateUtil.transformerStringEnDate(bean.getEffectedDate(), "/");
    Date date3 = DateUtil.transformerStringEnDate(bean.getSignDate(), "/");
    
    setText("mcrp_d1", "ng�y " + DateUtil.formatDate(date3, "dd") + " th�ng " + DateUtil.formatDate(date3, "MM") + " n?m " + DateUtil.formatDate(date3, "yyyy"));
    
    setText("mcrp_number", bean.getContractNumber());
    
    setText("mcrp_year", "");
    setText("mcrp_0", bean.getNote());
    setText("mcrp_1", bean.getVendorName());
    setText("mcrp_2", "??a ch? : " + bean.getAddress());
    setText("mcrp_3", "?i?n tho?i : " + bean.getPhone() + "     Fax: " + bean.getFax());
    HttpServletRequest request = getRequest();
    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE))
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      int orgId = MCUtil.getOrganizationID(request.getSession());
      String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgDAO.getnestedParentOfOrg(new StringBuilder().append(orgId).append("").toString());
      orgs = orgs + "," + orgId;
      contractDAO.setRequestOrg(orgs);
      contractDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
      if (contractDAO.isPermissionOnContractInfo(conId) > 0)
      {
        this.permission = true;
        
        setText("mcrp_4", NumberUtil.formatMoneyDefault(bean.getTotalNotVAT(), bean.getCurrency()));
        setText("mcrp_5", NumberUtil.formatMoneyDefault(bean.getSumVAT(), bean.getCurrency()));
        if (bean.getCurrency() != null) {
          if (!bean.getCurrency().contains("null"))
          {
            setText("mcrp_7", bean.getCurrency());
            setText("mcrp_8", NumberUtil.textMoney(Double.valueOf(other_cost + bean.getTotal()), bean.getCurrency()));
          }
          else
          {
            setText("mcrp_7", bean.getCurrency());
            setText("mcrp_8", NumberUtil.textMoney(Double.valueOf(other_cost + bean.getTotal()), bean.getCurrency()));
          }
        }
        setText("mcrp_6", NumberUtil.formatMoneyDefault(other_cost + bean.getTotal(), bean.getCurrency()));
      }
      else
      {
        setText("mcrp_6", "xxx");
        setText("mcrp_7", "xxx");
        setText("mcrp_8", "xxx");
        setText("mcrp_4", "xxx");
        setText("mcrp_5", "xxx");
      }
    }
    else
    {
      setText("mcrp_6", "xxx");
      setText("mcrp_7", "xxx");
      setText("mcrp_8", "xxx");
      setText("mcrp_4", "xxx");
      setText("mcrp_5", "xxx");
    }
    setText("mcrp_9", bean.getDelivery());
    if (bean.getVenId() != 0) {
      setText("mcrp_10", bean.getVendorName().toUpperCase());
    } else {
      setText("mcrp_10", "");
    }
    setText("mcrp_11", bean.getNote());
    setText("mcrp_12", bean.getCertificate());
    
    Hashtable map = new Hashtable();
    RowSAXHandler row = null;
    row = new RowSAXHandler(this.tableRow, this);
    try
    {
      this.arrDAO = contractDAO.getContractDetails(conId);
    }
    catch (Exception localException1) {}
    if (this.arrDAO == null) {
      this.arrDAO = new ArrayList();
    }
    row.setRowCount(this.arrDAO.size());
    map.put(this.tableRow, row);
    
    setArrTable(map);
  }
  
  private String getContractMaterialText(int i, String tab)
  {
    String text = "";
    ContractDetailFormBean bean = null;
    if (i < this.arrDAO.size())
    {
      bean = (ContractDetailFormBean)this.arrDAO.get(i);
      if (tab.equals("mcrp_a")) {
        text = i + 1 + "";
      } else if (tab.equals("mcrp_b")) {
        text = bean.getMaterialName();
      } else if (tab.equals("mcrp_c")) {
        text = bean.getUnit();
      } else if (tab.equals("mcrp_quantity"))
      {
        if (this.vnd) {
          text = NumberUtil.formatNumberDefault(bean.getQuantity());
        } else {
          text = NumberUtil.formatMoneyDefault(Double.valueOf(bean.getQuantity()));
        }
      }
      else if (tab.equals("mcrp_e"))
      {
        if (this.permission)
        {
          if (this.vnd) {
            text = NumberUtil.formatNumberDefault(bean.getPrice()) + "";
          } else {
            text = NumberUtil.formatMoneyDefault(bean.getPrice(), bean.getCurrency()) + "";
          }
        }
        else {
          text = "xxx";
        }
      }
      else if (tab.equals("mcrp_f"))
      {
        if (this.permission)
        {
          if (this.vnd) {
            text = NumberUtil.formatNumberDefault(bean.getQuantity() * bean.getPrice()) + "";
          } else {
            text = NumberUtil.formatMoneyDefault(bean.getQuantity() * bean.getPrice(), bean.getCurrency()) + "";
          }
        }
        else {
          text = "xxx";
        }
      }
      else if (tab.equals("mcrp_g")) {
        text = bean.getRequestNumber();
      }
    }
    return text;
  }
  
  public String getTabText(String rowId, int i, String tab)
  {
    if (rowId.equals(this.tableRow)) {
      return getContractMaterialText(i, tab);
    }
    return "";
  }
}
