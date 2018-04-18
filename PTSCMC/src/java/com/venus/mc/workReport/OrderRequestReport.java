package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.contract.ContractDetailFormBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.request.RequestDetailFormBean;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;

public class OrderRequestReport
  extends SpineReportParser
{
  private ArrayList arrMat;
  private String tableRow = "matRow";
  private boolean permission = false;
  
  public OrderRequestReport(String xmlTemplate, String wordTemplate, String resultFileName)
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
      bean = new ContractBean();
    }
    ContractBean parent = null;
    try
    {
      parent = contractDAO.getContract(bean.getParentId());
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (parent == null) {
      parent = new ContractBean();
    }
    try
    {
      this.arrMat = contractDAO.getContractDetails(bean.getConId());
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (this.arrMat == null) {
      this.arrMat = new ArrayList();
    }
    String reqDetIds = "0";
    String reqNumbers = "";
    String reqDates = "";
    String pros = "";
    String user = "";
    ContractDetailFormBean contractDetail = null;
    for (int i = 0; i < this.arrMat.size(); i++)
    {
      contractDetail = (ContractDetailFormBean)this.arrMat.get(i);
      reqDetIds = reqDetIds + "," + contractDetail.getReqDetailId();
      if (reqNumbers.indexOf("," + contractDetail.getRequestNumber() + ",") == -1)
      {
        reqNumbers = reqNumbers + ", phi?u ?? xu?t s?" + contractDetail.getRequestNumber() + " ng�y " + contractDetail.getRequestDate();
        
        pros = pros + "," + contractDetail.getProjectName() + ",";
        user = user + "," + contractDetail.getOrgName() + ",";
      }
    }
    if (reqNumbers.length() != 0) {
      reqNumbers = reqNumbers.substring(1);
    }
    ArrayList reqDetails = null;
    try
    {
      RequestDAO requestDAO = new RequestDAO();
      reqDetails = requestDAO.getRequestDetailsByDetIds(reqDetIds);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (reqDetails == null) {
      reqDetails = new ArrayList();
    }
    RequestDetailFormBean request = null;
    String orgs = "";
    for (int i = 0; i < reqDetails.size(); i++)
    {
      request = (RequestDetailFormBean)reqDetails.get(i);
      orgs = orgs + "," + request.getReqOrg();
    }
    if (orgs.length() != 0) {
      orgs = orgs.substring(1);
    }
    Date date = DateUtil.transformerStringEnDate(bean.getSignDate(), "/");
    
    setText("mcrp_day", DateUtil.formatDate(date, "dd"));
    setText("mcrp_month", DateUtil.formatDate(date, "MM"));
    setText("mcrp_year", DateUtil.formatDate(date, "yyyy"));
    setText("mcrp_material", bean.getNote());
    setText("mcrp_request_number", reqNumbers);
    
    setText("mcrp_pro", pros);
    if (parent.getConId() != 0)
    {
      setText("mcrp_parentOrder_number", parent.getContractNumber());
      setText("mcrp_parentOrder_date", parent.getSignDate());
      setText("mcrp_vendor", parent.getVendorName());
    }
    else
    {
      setText("mcrp_parentOrder_number", "");
      setText("mcrp_parentOrder_date", "");
      setText("mcrp_vendor", bean.getVendorName());
    }
    setText("mcrp_org", orgs);
    setText("mcrp_request", reqNumbers);
    setText("mcrp_project", "");
    setText("mcrp_user", user);
    setText("mcrp_cer", bean.getCertificate());
    setText("mcrp_delivery_time", bean.getDelivery());
    
    HttpServletRequest request1 = getRequest();
    if (PermissionUtil.hasPermission(request1, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE))
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      int orgId = MCUtil.getOrganizationID(getRequest().getSession());
      String orgss = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgDAO.getnestedParentOfOrg(new StringBuilder().append(orgId).append("").toString());
      orgss = orgss + "," + orgId;
      contractDAO.setRequestOrg(orgss);
      contractDAO.setRequestEmp(MCUtil.getMemberID(getRequest().getSession()));
      if (contractDAO.isPermissionOnContractInfo(conId) > 0)
      {
        this.permission = true;
        setText("mcrp_total_not_vat", NumberUtil.formatMoneyDefault(bean.getTotalNotVAT(), bean.getCurrency()));
        setText("mcrp_total", NumberUtil.formatMoneyDefault(bean.getTotal(), bean.getCurrency()));
        if (bean.getOtherFee() + bean.getTransport() != 0.0D) {
          setText("mcrp_fee", NumberUtil.formatMoneyDefault(bean.getOtherFee() + bean.getTransport(), bean.getCurrency()));
        } else {
          setText("mcrp_fee", "?� bao g?m");
        }
        setText("mcrp_vat", NumberUtil.formatMoneyDefault(bean.getTotal() - bean.getTotalNotVAT() - bean.getOtherFee() - bean.getTransport(), bean.getCurrency()));
        setText("mcrp_total_text", NumberUtil.textMoney(Double.valueOf(bean.getTotal()), bean.getCurrency()));
        setText("mcrp_currency", bean.getCurrency());
      }
      else
      {
        setText("mcrp_total_not_vat", "xxx");
        setText("mcrp_total", "xxx");
        setText("mcrp_vat", "xxx");
        setText("mcrp_total_text", "xxx");
        setText("mcrp_currency", "xxx");
      }
    }
    else
    {
      setText("mcrp_total_not_vat", "xxx");
      setText("mcrp_total", "xxx");
      setText("mcrp_vat", "xxx");
      setText("mcrp_total_text", "xxx");
      setText("mcrp_currency", "xxx");
    }
    Hashtable map = new Hashtable();
    RowSAXHandler row = null;
    row = new RowSAXHandler(this.tableRow, this);
    row.setRowCount(this.arrMat.size());
    map.put(this.tableRow, row);
    
    setArrTable(map);
  }
  
  private String getContractMaterialText(int i, String tab)
  {
    String text = "";
    ContractDetailFormBean bean = null;
    if (i < this.arrMat.size())
    {
      bean = (ContractDetailFormBean)this.arrMat.get(i);
      if (tab.equals("mcrp_n1")) {
        text = i + 1 + "";
      } else if (tab.equals("mcrp_mat_name")) {
        text = bean.getMatName();
      } else if (tab.equals("mcrp_mat_unit")) {
        text = bean.getUnit();
      } else if (tab.equals("mcrp_request")) {
        text = bean.getRequestNumber();
      } else if (tab.equals("mcrp_mat_quantity")) {
        text = NumberUtil.formatMoneyDefault(Double.valueOf(bean.getQuantity()));
      } else if (tab.equals("mcrp_mat_price"))
      {
        if (this.permission) {
          text = NumberUtil.formatMoneyDefault(bean.getPrice(), bean.getCurrency());
        } else {
          text = "xxx";
        }
      }
      else if (tab.equals("mcrp_mat_total")) {
        if (this.permission) {
          text = NumberUtil.formatMoneyDefault(bean.getQuantity() * bean.getPrice(), bean.getCurrency());
        } else {
          text = "xxx";
        }
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
