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

public class ContractAdjustmentReport
  extends SpineReportParser
{
  private ArrayList arrDAO;
  private String tableRow = "matRow";
  private boolean permission = false;
  
  public ContractAdjustmentReport(String xmlTemplate, String wordTemplate, String resultFileName)
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
      bean = contractDAO.getContractAppendix(conId);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (bean == null) {
      return;
    }
    Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
    
    Date date1 = DateUtil.transformerStringEnDate(bean.getSignDate(), "/");
    Date date2 = DateUtil.transformerStringEnDate(bean.getAppendixDate(), "/");
    Date date3 = DateUtil.transformerStringEnDate(bean.getEffectedDate(), "/");
    Date date4 = DateUtil.transformerStringEnDate(bean.getExpireDate(), "/");
    
    setText("mcrp_d1", "ng�y " + DateUtil.formatDate(date, "dd") + " th�ng " + DateUtil.formatDate(date, "MM") + " n?m " + DateUtil.formatDate(date, "yyyy"));
    setText("mcrp_d2", "ng�y " + DateUtil.formatDate(date2, "dd") + " th�ng " + DateUtil.formatDate(date2, "MM") + " n?m " + DateUtil.formatDate(date2, "yyyy"));
    setText("mcrp_d3", "ng�y " + DateUtil.formatDate(date3, "dd") + " th�ng " + DateUtil.formatDate(date3, "MM") + " n?m " + DateUtil.formatDate(date3, "yyyy"));
    setText("mcrp_d4", "ng�y " + DateUtil.formatDate(date1, "dd") + " th�ng " + DateUtil.formatDate(date1, "MM") + " n?m " + DateUtil.formatDate(date1, "yyyy"));
    
    setText("mcrp_number", bean.getContractNumber());
    String temp = bean.getAppendixNumber();
    int ind = temp.indexOf(" H? ");
    if (ind > -1) {
      temp = temp.substring(0, ind);
    }
    bean.setAppendixNumber(temp);
    setText("mcrp_number1", bean.getAppendixNumber());
    setText("mcrp_content", bean.getNote());
    setText("mcrp_vendor", bean.getVendorName());
    setText("mcrp_cer", bean.getCertificate());
    setText("mcrp_0", bean.getNote());
    setText("mcrp_1", bean.getVendorName().toUpperCase());
    setText("mcrp_2", bean.getAddress());
    setText("mcrp_3", bean.getPhone());
    setText("mcrp_4", bean.getFax());
    setText("mcrp_6", bean.getLicense());
    setText("mcrp_5", bean.getField());
    setText("mcrp_7", bean.getPresenter() + "    - Ch?c v?: " + bean.getPosPresenter());
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
        setText("mcrp_8", NumberUtil.formatMoneyDefault(bean.getTotalNotVAT(), bean.getCurrency()));
        setText("mcrp_9", NumberUtil.formatMoneyDefault(bean.getSumVAT(), bean.getCurrency()));
        if (bean.getCurrency() != null) {
          if (!bean.getCurrency().contains("null"))
          {
            setText("mcrp_11", bean.getCurrency());
            setText("mcrp_12", NumberUtil.textMoney(Double.valueOf(bean.getTotal()), bean.getCurrency()));
          }
          else
          {
            setText("mcrp_11", bean.getCurrency());
            setText("mcrp_12", NumberUtil.textMoney(Double.valueOf(bean.getTotal()), bean.getCurrency()));
          }
        }
        setText("mcrp_10", NumberUtil.formatMoneyDefault(bean.getTotal(), bean.getCurrency()));
      }
      else
      {
        setText("mcrp_8", "xxx");
        setText("mcrp_9", "xxx");
        setText("mcrp_10", "xxx");
        setText("mcrp_11", "xxx");
        setText("mcrp_12", "xxx");
      }
    }
    else
    {
      setText("mcrp_8", "xxx");
      setText("mcrp_9", "xxx");
      setText("mcrp_10", "xxx");
      setText("mcrp_11", "xxx");
      setText("mcrp_12", "xxx");
    }
    setText("mcrp_13", bean.getDelivery());
    setText("mcrp_14", bean.getVendorName().toUpperCase() + "");
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
        text = bean.getMatName();
      } else if (tab.equals("mcrp_c")) {
        text = bean.getUnit();
      } else if (tab.equals("mcrp_d")) {
        text = NumberUtil.formatMoneyDefault(bean.getQuantity(), bean.getCurrency());
      } else if (tab.equals("mcrp_e"))
      {
        if (this.permission) {
          text = NumberUtil.formatMoneyDefault(bean.getPrice(), bean.getCurrency());
        } else {
          text = "xxx";
        }
      }
      else if (tab.equals("mcrp_f"))
      {
        if (this.permission) {
          text = NumberUtil.formatMoneyDefault(bean.getQuantity() * bean.getPrice(), bean.getCurrency());
        } else {
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
