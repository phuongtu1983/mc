package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.contract.ContractDetailFormBean;
import com.venus.mc.contract.ContractFormBean;
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

public class ContractAdjustmentRequestReport
  extends SpineReportParser
{
  private ArrayList arrAppendixs;
  private String tableRow = "matRow";
  private boolean permission = false;
  
  public ContractAdjustmentRequestReport(String xmlTemplate, String wordTemplate, String resultFileName)
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
      bean = new ContractBean();
    }
    ContractBean parentContract = null;
    ArrayList conDetails = null;
    try
    {
      parentContract = contractDAO.getContract(bean.getParentId());
      conDetails = contractDAO.getContractDetails(bean.getConId());
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (conDetails == null) {
      conDetails = new ArrayList();
    }
    String reqDetIds = "0";
    ContractDetailFormBean contractDetail = null;
    for (int i = 0; i < conDetails.size(); i++)
    {
      contractDetail = (ContractDetailFormBean)conDetails.get(i);
      reqDetIds = reqDetIds + "," + contractDetail.getReqDetailId();
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
    String material = "";
    String requestNumber = "";
    String requestDate = "";
    String requestOrg = "";
    String requestProject = "";
    for (int i = 0; i < reqDetails.size(); i++)
    {
      request = (RequestDetailFormBean)reqDetails.get(i);
      material = material + "," + request.getMatName();
      requestNumber = requestNumber + "," + request.getReqNumber();
      requestDate = requestDate + "," + request.getReqDate();
      requestOrg = requestOrg + "," + request.getReqOrg();
      requestProject = requestProject + "," + request.getReqProject();
    }
    if (material.length() != 0)
    {
      material = material.substring(1);
      requestNumber = requestNumber.substring(1);
      requestDate = requestDate.substring(1);
      requestOrg = requestOrg.substring(1);
      requestProject = requestProject.substring(1);
    }
    Date date = DateUtil.transformerStringEnDate(bean.getEffectedDate(), "/");
    String temp = bean.getAppendixNumber();
    int ind = temp.indexOf(" H? ");
    if (ind > -1) {
      temp = temp.substring(0, ind);
    }
    bean.setAppendixNumber(temp);
    setText("mcrp_appendix_number", bean.getAppendixNumber());
    setText("mcrp_day", DateUtil.formatDate(date, "dd"));
    setText("mcrp_month", DateUtil.formatDate(date, "MM"));
    setText("mcrp_year", DateUtil.formatDate(date, "yyyy"));
    setText("mcrp_contract", bean.getContractNumber());
    setText("mcrp_contract_date", parentContract.getSignDate());
    setText("mcrp_vendor", bean.getVendorName());
    setText("mcrp_material", material);
    setText("mcrp_content", bean.getNote());
    setText("mcrp_delivery", bean.getDelivery());
    
    setText("mcrp_request", requestNumber);
    setText("mcrp_request_date", requestDate);
    setText("mcrp_request_org", requestOrg);
    setText("mcrp_preAppendix", "");
    setText("mcrp_project", requestProject);
    setText("mcrp_currency", bean.getCurrency());
    setText("mcrp_total_text", NumberUtil.textMoney(Double.valueOf(bean.getTotal()), bean.getCurrency()));
    if (PermissionUtil.hasPermission(getRequest(), PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE))
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      int orgId = MCUtil.getOrganizationID(getRequest().getSession());
      String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgDAO.getnestedParentOfOrg(new StringBuilder().append(orgId).append("").toString());
      orgs = orgs + "," + orgId;
      contractDAO.setRequestOrg(orgs);
      contractDAO.setRequestEmp(MCUtil.getMemberID(getRequest().getSession()));
      if (contractDAO.isPermissionOnContractInfo(conId) > 0)
      {
        this.permission = true;
        setText("mcrp_total", NumberUtil.formatMoneyDefault(bean.getTotal(), bean.getCurrency()));
        try
        {
          try
          {
            this.arrAppendixs = contractDAO.getAppendixsOfContract(bean.getParentId(), bean.getConId());
          }
          catch (Exception localException1) {}
          if (this.arrAppendixs == null) {
            this.arrAppendixs = new ArrayList();
          }
          ContractFormBean app = null;
          String appNumbers = "";
          String counti = "";
          ind = -1;
          double sum = parentContract.getTotal();
          for (int i = 0; i < this.arrAppendixs.size(); i++)
          {
            app = (ContractFormBean)this.arrAppendixs.get(i);
            sum += app.getTotal();
            temp = app.getContractNumber();
            ind = temp.indexOf(" H? ");
            if (ind > -1)
            {
              temp = temp.substring(0, ind);
              appNumbers = appNumbers + "," + temp;
              app.setContractNumber("BS s? " + temp);
            }
            counti = counti + " + " + (i + 2);
          }
          if (!GenericValidator.isBlankOrNull(appNumbers)) {
            appNumbers = appNumbers.substring(1);
          }
          app = new ContractFormBean();
          app.setContractNumber("H? " + parentContract.getContractNumber() + " ng�y " + parentContract.getSignDate());
          app.setTotal(parentContract.getTotal());
          app.setCurrency(parentContract.getCurrency());
          this.arrAppendixs.add(0, app);
          int count = this.arrAppendixs.size() + 1;
          setText("mcrp_no2", count + "");
          setText("mcrp_appendixs", appNumbers);
          setText("mcrp_function", count + " = 1 " + counti);
          setText("mcrp_sum", NumberUtil.formatMoneyDefault(sum, bean.getCurrency()));
          Hashtable map = new Hashtable();
          RowSAXHandler row = null;
          row = new RowSAXHandler(this.tableRow, this);
          row.setRowCount(this.arrAppendixs.size());
          map.put(this.tableRow, row);
          setArrTable(map);
        }
        catch (Exception localException2) {}
      }
      else
      {
        setText("mcrp_total", "xxx");
        setText("mcrp_sum", "xxx");
        setText("mcrp_no1", "");
        setText("mcrp_no2", "");
        setText("mcrp_row_content", "");
        setText("mcrp_row_total", "");
        setText("mcrp_appendixs", bean.getAppendixNumber());
        setText("mcrp_function", "1");
      }
    }
    else
    {
      setText("mcrp_total", "xxx");
      setText("mcrp_sum", "xxx");
      setText("mcrp_no1", "");
      setText("mcrp_no2", "");
      setText("mcrp_row_content", "");
      setText("mcrp_row_total", "");
      setText("mcrp_appendixs", bean.getAppendixNumber());
      setText("mcrp_function", "1");
    }
  }
  
  private String getContractMaterialText(int i, String tab)
  {
    String text = "";
    ContractFormBean bean = null;
    if (i < this.arrAppendixs.size())
    {
      bean = (ContractFormBean)this.arrAppendixs.get(i);
      if (tab.equals("mcrp_no1")) {
        text = i + 1 + "";
      } else if (tab.equals("mcrp_row_content")) {
        text = bean.getContractNumber();
      } else if (tab.equals("mcrp_row_total")) {
        if (this.permission) {
          text = NumberUtil.formatMoneyDefault(bean.getTotal(), bean.getCurrency());
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
