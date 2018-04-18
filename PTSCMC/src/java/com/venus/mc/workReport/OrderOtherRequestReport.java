package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.contract.ContractDetailFormBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.request.RequestDetailFormBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class OrderOtherRequestReport
  extends SpineReportParser
{
  private ArrayList arrMat;
  private String tableRow1 = "tableRow1";
  private String tableRow2 = "tableRow2";
  
  public OrderOtherRequestReport(String xmlTemplate, String wordTemplate, String resultFileName)
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
    ContractDetailFormBean contractDetail = null;
    
    ArrayList reqDetails = null;
    if (reqDetails == null) {
      reqDetails = new ArrayList();
    }
    RequestDetailFormBean request = null;
    String orgs = ", ";
    String pros = "";
    for (int i = 0; i < this.arrMat.size(); i++)
    {
      contractDetail = (ContractDetailFormBean)this.arrMat.get(i);
      reqDetIds = reqDetIds + "," + contractDetail.getReqDetailId();
    }
    try
    {
      RequestDAO requestDAO = new RequestDAO();
      reqDetails = requestDAO.getRequestDetailsByDetIds(reqDetIds);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    for (int i = 0; i < this.arrMat.size(); i++)
    {
      contractDetail = (ContractDetailFormBean)this.arrMat.get(i);
      request = getRequestDetail(contractDetail, reqDetails);
      if (orgs.indexOf(", " + request.getReqOrg() + ",") == -1) {
        orgs = orgs + request.getReqOrg() + ", ";
      }
      pros = pros.replace(request.getReqProject(), "");
      pros = pros + ", " + request.getReqProject();
      if (reqNumbers.indexOf(", " + contractDetail.getRequestNumber() + " ng�y ") == -1) {
        reqNumbers = reqNumbers + ", " + contractDetail.getRequestNumber() + " ng�y " + contractDetail.getRequestDate();
      }
    }
    if (reqNumbers.length() != 0) {
      reqNumbers = reqNumbers.substring(2);
    }
    if (orgs.length() != 0)
    {
      orgs = orgs.substring(2);
      orgs = orgs.substring(0, orgs.length() - 2);
      pros = pros.substring(2);
    }
    Date date = DateUtil.transformerStringEnDate(bean.getSignDate(), "/");
    
    setText("mcrp_day", DateUtil.formatDate(date, "dd"));
    setText("mcrp_month", DateUtil.formatDate(date, "MM"));
    setText("mcrp_year", DateUtil.formatDate(date, "yyyy"));
    setText("mcrp_content", bean.getNote());
    setText("mcrp_material", "");
    setText("mcrp_request_number", reqNumbers);
    setText("mcrp_request_date", reqDates);
    if (parent.getConId() != 0)
    {
      setText("mcrp_parentOrder_number", parent.getContractNumber());
      setText("mcrp_parentOrder_date", parent.getCreatedDate());
      setText("mcrp_vendor", parent.getVendorName());
      setText("mcrp_cer", parent.getNote());
      setText("mcrp_delivery_time", parent.getDelivery());
    }
    else
    {
      setText("mcrp_vendor", bean.getVendorName());
    }
    double other_cost = 0.0D;
    other_cost = bean.getTransport() + bean.getOtherFee();
    if (other_cost != 0.0D) {
      setText("mcrp_fee", NumberUtil.formatMoneyDefault(other_cost, bean.getCurrency()));
    } else {
      setText("mcrp_fee", "?� bao g?m");
    }
    setText("mcrp_org", orgs);
    setText("mcrp_project", pros);
    setText("mcrp_user", contractDetail.getOrgName());
    setText("mcrp_request", reqNumbers);
    setText("mcrp_currency", bean.getCurrency());
    setText("mcrp_cer", bean.getCertificate());
    setText("mcrp_delivery_time", bean.getDelivery());
    
    setText("mcrp_total_not_vat", NumberUtil.formatMoneyDefault(bean.getTotalNotVAT(), bean.getCurrency()));
    
    setText("mcrp_total", NumberUtil.formatMoneyDefault(bean.getTotal(), bean.getCurrency()));
    
    setText("mcrp_vat", NumberUtil.formatMoneyDefault(bean.getTotal() - bean.getTotalNotVAT(), bean.getCurrency()));
    setText("mcrp_total_text", NumberUtil.textMoney(Double.valueOf(bean.getTotal()), bean.getCurrency()));
    
    Hashtable map = new Hashtable();
    RowSAXHandler row = null;
    row = new RowSAXHandler(this.tableRow1, this);
    row.setRowCount(this.arrMat.size());
    map.put(this.tableRow1, row);
    
    row = new RowSAXHandler(this.tableRow2, this);
    row.setRowCount(this.arrMat.size());
    map.put(this.tableRow2, row);
    
    setArrTable(map);
  }
  
  private String getContractMaterialText1(int i, String tab)
  {
    String text = "";
    ContractDetailFormBean bean = null;
    if (i < this.arrMat.size())
    {
      bean = (ContractDetailFormBean)this.arrMat.get(i);
      if (tab.equals("mcrp_n1")) {
        text = i + 1 + "";
      } else if (tab.equals("mcrp_mat_name1")) {
        text = bean.getName1();
      } else if (tab.equals("mcrp_mat_unit1")) {
        text = bean.getUnit();
      } else if (tab.equals("mcrp_mat_quantity1")) {
        text = NumberUtil.formatMoneyDefault(bean.getQuantity(), "USD");
      } else if (tab.equals("mcrp_mat_price1")) {
        text = NumberUtil.formatMoneyDefault(bean.getPrice(), bean.getCurrency());
      }
    }
    return text;
  }
  
  private String getContractMaterialText2(int i, String tab)
  {
    String text = "";
    ContractDetailFormBean bean = null;
    if (i < this.arrMat.size())
    {
      bean = (ContractDetailFormBean)this.arrMat.get(i);
      if (tab.equals("mcrp_n2")) {
        text = i + 1 + "";
      } else if (tab.equals("mcrp_mat_name2")) {
        text = bean.getMaterialName();
      } else if (tab.equals("mcrp_mat_unit2")) {
        text = bean.getUnit();
      } else if (tab.equals("mcrp_mat_quantity2")) {
        text = NumberUtil.formatMoneyDefault(Double.valueOf(bean.getQuantity()));
      } else if (tab.equals("mcrp_mat_price2")) {
        text = NumberUtil.formatMoneyDefault(bean.getPrice(), bean.getCurrency());
      } else if (tab.equals("mcrp_mat_total2")) {
        text = NumberUtil.formatMoneyDefault(bean.getQuantity() * bean.getPrice(), bean.getCurrency());
      } else if (tab.equals("mcrp_request2")) {
        text = bean.getRequestNumber();
      }
    }
    return text;
  }
  
  private RequestDetailFormBean getRequestDetail(ContractDetailFormBean conDet, ArrayList reqDetails)
  {
    RequestDetailFormBean det = null;
    for (int i = 0; i < reqDetails.size(); i++)
    {
      det = (RequestDetailFormBean)reqDetails.get(i);
      if (det.getDetId() == conDet.getReqDetailId()) {
        break;
      }
    }
    if (det == null) {
      det = new RequestDetailFormBean();
    }
    return det;
  }
  
  public String getTabText(String rowId, int i, String tab)
  {
    if (rowId.equals(this.tableRow1)) {
      return getContractMaterialText1(i, tab);
    }
    if (rowId.equals(this.tableRow2)) {
      return getContractMaterialText2(i, tab);
    }
    return "";
  }
}
