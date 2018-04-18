package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.deliveryrequest.DeliveryRequestDetailFormBean;
import com.venus.mc.deliveryrequest.DeliveryRequestFormBean;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;

public class DeliveryRequestReport
  extends SpineReportParser
{
  private ArrayList arrDAO;
  private String tableRow = "matRow";
  private boolean permission = false;
  
  public DeliveryRequestReport(String xmlTemplate, String wordTemplate, String resultFileName)
  {
    super(xmlTemplate, wordTemplate, resultFileName);
  }
  
  protected void parse(Object object)
    throws Exception
  {
    Integer drIdObject = (Integer)object;
    int drId = drIdObject.intValue();
    DeliveryRequestFormBean bean = null;
    ContractDAO contractDAO = new ContractDAO();
    try
    {
      bean = contractDAO.getDeliveryRequestPrint(drId);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (bean == null) {
      return;
    }
    Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
    Date date2 = DateUtil.transformerStringEnDate(bean.getEffectedDate(), "/");
    Date date3 = DateUtil.transformerStringEnDate(bean.getSignDate(), "/");
    
    setText("mcrp_d2", "ng�y " + DateUtil.formatDate(date, "dd") + " th�ng " + DateUtil.formatDate(date, "MM") + " n?m " + DateUtil.formatDate(date, "yyyy"));
    setText("mcrp_d1", "ng�y " + DateUtil.formatDate(date3, "dd") + " th�ng " + DateUtil.formatDate(date3, "MM") + " n?m " + DateUtil.formatDate(date3, "yyyy"));
    
    setText("mcrp_number1", bean.getDeliveryNumber());
    setText("mcrp_number", bean.getConNumber());
    setText("mcrp_content", bean.getNote());
    setText("mcrp_year", DateUtil.today("yyyy"));
    setText("mcrp_1", bean.getVendorName().toUpperCase());
    setText("mcrp_vendor", bean.getVendorName());
    setText("mcrp_2", "??a ch? : " + bean.getAddress());
    setText("mcrp_3", "?i?n tho?i : " + bean.getPhone() + "     Fax: " + bean.getFax());
    double other_cost = 0.0D;
    other_cost = bean.getTransport() + bean.getOther_fee();
    if (other_cost != 0.0D) {
      setText("mcrp_12", NumberUtil.formatMoneyDefault(other_cost, bean.getCurrency()));
    } else {
      setText("mcrp_12", "?� bao g?m");
    }
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
      if (contractDAO.isPermissionOnContractInfo(drId) > 0)
      {
        this.permission = true;
        setText("mcrp_5", NumberUtil.formatMoneyDefault(bean.getTotalNotVAT(), bean.getCurrency()));
        setText("mcrp_6", NumberUtil.formatMoneyDefault(bean.getSumVAT(), bean.getCurrency()));
        if (bean.getCurrency() != null)
        {
          if (!bean.getCurrency().contains("null"))
          {
            setText("mcrp_8", bean.getCurrency());
            setText("mcrp_9", NumberUtil.textMoney(Double.valueOf(bean.getTotal()), bean.getCurrency()));
          }
          else
          {
            setText("mcrp_8", bean.getCurrency());
            setText("mcrp_9", NumberUtil.textMoney(Double.valueOf(bean.getTotal()), bean.getCurrency()));
          }
          if (bean.getCurrency().trim().length() < 1)
          {
            setText("mcrp_8", bean.getCurrency());
            setText("mcrp_9", NumberUtil.textMoney(Double.valueOf(bean.getTotal()), bean.getCurrency()));
          }
        }
        else
        {
          setText("mcrp_8", bean.getCurrency());
          setText("mcrp_9", NumberUtil.textMoney(Double.valueOf(bean.getTotal()), bean.getCurrency()));
        }
        setText("mcrp_7", NumberUtil.formatMoneyDefault(bean.getTotal(), bean.getCurrency()) + "");
      }
      else
      {
        setText("mcrp_5", "xxx");
        setText("mcrp_6", "xxx");
        setText("mcrp_7", "xxx");
        setText("mcrp_8", "xxx");
        setText("mcrp_9", "xxx");
      }
    }
    else
    {
      setText("mcrp_5", "xxx");
      setText("mcrp_6", "xxx");
      setText("mcrp_7", "xxx");
      setText("mcrp_8", "xxx");
      setText("mcrp_9", "xxx");
    }
    setText("mcrp_10", bean.getDelivery());
    setText("mcrp_11", bean.getCertificate().replace(";", "\n"));
    
    Hashtable map = new Hashtable();
    RowSAXHandler row = null;
    row = new RowSAXHandler(this.tableRow, this);
    try
    {
      this.arrDAO = contractDAO.getDeliveryRequestDetails(drId);
    }
    catch (Exception localException1) {}
    if (this.arrDAO == null) {
      this.arrDAO = new ArrayList();
    }
    row.setRowCount(this.arrDAO.size());
    map.put(this.tableRow, row);
    
    setArrTable(map);
  }
  
  private String getDeliveryRequestMaterialText(int i, String tab)
  {
    String text = "";
    DeliveryRequestDetailFormBean bean = null;
    if (i < this.arrDAO.size())
    {
      bean = (DeliveryRequestDetailFormBean)this.arrDAO.get(i);
      if (tab.equals("mcrp_a")) {
        text = i + 1 + "";
      } else if (tab.equals("mcrp_b")) {
        text = bean.getMatName();
      } else if (tab.equals("mcrp_c")) {
        text = bean.getUnit();
      } else if (tab.equals("mcrp_quantity")) {
        text = NumberUtil.formatMoneyDefault(Double.valueOf(bean.getQuantity()));
      } else if (tab.equals("mcrp_e"))
      {
        if (this.permission) {
          text = NumberUtil.formatMoneyDefault(bean.getPrice(), bean.getCurrency()) + "";
        } else {
          text = "xxx";
        }
      }
      else if (tab.equals("mcrp_f"))
      {
        if (this.permission) {
          text = NumberUtil.formatMoneyDefault(bean.getQuantity() * bean.getPrice(), bean.getCurrency()) + "";
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
      return getDeliveryRequestMaterialText(i, tab);
    }
    return "";
  }
}
