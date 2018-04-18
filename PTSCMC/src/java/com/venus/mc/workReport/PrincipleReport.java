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

public class PrincipleReport
  extends SpineReportParser
{
  private ArrayList arrDAO;
  private String tableRow = "matRow";
  private boolean permission = false;
  
  public PrincipleReport(String xmlTemplate, String wordTemplate, String resultFileName)
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
    Date date = DateUtil.transformerStringEnDate(bean.getSignDate(), "/");
    Date date2 = DateUtil.transformerStringEnDate(bean.getEffectedDate(), "/");
    Date date3 = DateUtil.transformerStringEnDate(bean.getExpireDate(), "/");
    
    setText("mcrp_01", "ng�y " + DateUtil.formatDate(date, "dd") + " th�ng " + DateUtil.formatDate(date, "MM") + " n?m " + DateUtil.formatDate(date, "yyyy"));
    setText("mcrp_02", "ng�y " + DateUtil.formatDate(date2, "dd") + " th�ng " + DateUtil.formatDate(date2, "MM") + " n?m " + DateUtil.formatDate(date2, "yyyy"));
    setText("mcrp_expireDate", DateUtil.formatDate(date3, "dd/MM/yyyy"));
    setText("mcrp_effectedDate", DateUtil.formatDate(date2, "dd/MM/yyyy"));
    
    setText("mcrp_number", bean.getContractNumber());
    setText("mcrp_cer", bean.getCertificate());
    setText("mcrp_year", DateUtil.formatDate(date, "yyyy"));
    setText("mcrp_delivery", bean.getDelivery());
    setText("mcrp_currency", bean.getCurrency());
    setText("mcrp_content", bean.getNote());
    setText("mcrp_vendor", bean.getVendorName());
    setText("mcrp_03", bean.getPackName());
    setText("mcrp_04", bean.getVendorName().toUpperCase());
    setText("mcrp_05", bean.getAddress());
    setText("mcrp_06", bean.getPhone());
    setText("mcrp_07", bean.getFax());
    setText("mcrp_08", bean.getLicense());
    setText("mcrp_09", bean.getField());
    setText("mcrp_10", bean.getPresenter() + "    - Ch?c v?: " + bean.getPosPresenter());
    setText("mcrp_11", DateUtil.formatDate(date2, "dd/MM/yyyy"));
    setText("mcrp_12", DateUtil.formatDate(date3, "dd/MM/yyyy"));
    setText("mcrp_13", bean.getCertificate());
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
      if (contractDAO.isPermissionOnContractInfo(conId) > 0) {
        this.permission = true;
      }
    }
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
      } else if (tab.equals("mcrp_d"))
      {
        if (this.permission) {
          text = NumberUtil.formatMoneyDefault(bean.getPrice(), bean.getCurrency());
        } else {
          text = "xxx";
        }
      }
      else if (tab.equals("mcrp_e")) {
        if (this.permission) {
          text = bean.getVat() + "";
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
