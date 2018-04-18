package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.TenderEvaluateVendorBean;
import com.venus.mc.bean.TenderLetterDetailBean;
import com.venus.mc.contract.ContractDetailFormBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;

public class ContractReport
  extends SpineReportParser
{
  private ArrayList arrDAO;
  private String tableRow = "matRow";
  private boolean permission = false;
  
  public ContractReport(String xmlTemplate, String wordTemplate, String resultFileName)
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
    double other_cost = 0.0D;
    try
    {
      other_cost = contractDAO.getOtherCost(conId, bean.getVenId());
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    TenderEvaluateVendorBean info = new TenderEvaluateVendorBean();
    try
    {
      info = contractDAO.getInforPrint(conId);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (info == null) {
      info = new TenderEvaluateVendorBean();
    }
    TenderLetterDetailBean info1 = new TenderLetterDetailBean();
    try
    {
      info1 = contractDAO.getSufix(conId, info.getTevId());
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (info1 == null) {
      info1 = new TenderLetterDetailBean();
    }
    int eval_kind = 0;
    TenderPlanDAO ten = new TenderPlanDAO();
    try
    {
      eval_kind = ten.getEvalKindTenderPlan(info.getTenId() + "");
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    Date date = DateUtil.transformerStringEnDate(bean.getSignDate(), "/");
    Date date2 = DateUtil.transformerStringEnDate(bean.getEffectedDate(), "/");
    
    setText("mcrp_01", "ng�y " + DateUtil.formatDate(date, "dd") + " th�ng " + DateUtil.formatDate(date, "MM") + " n?m " + DateUtil.formatDate(date, "yyyy"));
    setText("mcrp_02", "ng�y " + DateUtil.formatDate(date2, "dd") + " th�ng " + DateUtil.formatDate(date2, "MM") + " n?m " + DateUtil.formatDate(date2, "yyyy"));
    
    setText("mcrp_01_1", bean.getSignDate());
    
    setText("mcrp_year", "");
    setText("mcrp_number", bean.getContractNumber());
    setText("mcrp_vendor", bean.getVendorName());
    setText("mcrp_pro", "");
    setText("mcrp_03", bean.getPackName());
    setText("mcrp_1company1", bean.getVendorName().toUpperCase());
    setText("mcrp_04_1", bean.getVendorName());
    setText("mcrp_05", bean.getAddress());
    setText("mcrp_06", bean.getPhone());
    setText("mcrp_07", bean.getFax());
    setText("mcrp_08", bean.getLicense());
    setText("mcrp_09", bean.getField());
    setText("mcrp_10", bean.getPresenter() + "    - Ch?c v?: " + bean.getPosPresenter());
    setText("mcrp_11", bean.getDelivery());
    setText("mcrp_cur", bean.getCurrency());
    setText("mcrp_h", bean.getCertificate());
    
    setText("mcrp_letter", info1.getSubfix());
    setText("mcrp_date_letter", info1.getDate());
    setText("mcrp_baogia", info.getQuoNo());
    setText("mcrp_date_open", info.getStartDate());
    double sum_other_cost = 0.0D;
    sum_other_cost = other_cost + bean.getTransport() + bean.getOtherFee();
    if (sum_other_cost != 0.0D) {
      setText("mcrp_17", NumberUtil.formatMoneyDefault(sum_other_cost, bean.getCurrency()));
    } else {
      setText("mcrp_17", "?� bao g?m");
    }
    if (eval_kind == 0) {
      setText("mcrp_18", NumberUtil.formatMoneyDefault(sum_other_cost + bean.getTotalNotVAT(), bean.getCurrency()));
    } else {
      setText("mcrp_18", NumberUtil.formatMoneyDefault(bean.getTotalNotVAT(), bean.getCurrency()));
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
      if (contractDAO.isPermissionOnContractInfo(conId) > 0)
      {
        this.permission = true;
        
        setText("mcrp_14", NumberUtil.formatMoneyDefault(bean.getTotalNotVAT(), bean.getCurrency()));
        setText("mcrp_15", NumberUtil.formatMoneyDefault(bean.getSumVAT(), bean.getCurrency()));
        if (bean.getCurrency() != null)
        {
          if (!bean.getCurrency().contains("null"))
          {
            setText("mcrp_16", bean.getCurrency());
            setText("mcrp_13", NumberUtil.textMoney(Double.valueOf(other_cost + bean.getTotal()), bean.getCurrency()));
            
            setText("mcrp_12", NumberUtil.formatMoneyDefault(other_cost + bean.getTotal(), bean.getCurrency()));
          }
          else
          {
            setText("mcrp_16", bean.getCurrency());
            setText("mcrp_13", NumberUtil.textMoney(Double.valueOf(other_cost + bean.getTotal()), bean.getCurrency()));
            setText("mcrp_12", NumberUtil.formatMoneyDefault(other_cost + bean.getTotal(), bean.getCurrency()));
          }
          if (bean.getCurrency().trim().length() < 1)
          {
            setText("mcrp_16", bean.getCurrency());
            setText("mcrp_13", NumberUtil.textMoney(Double.valueOf(other_cost + bean.getTotal()), bean.getCurrency()));
            setText("mcrp_12", NumberUtil.formatMoneyDefault(other_cost + bean.getTotal(), bean.getCurrency()));
          }
        }
        else
        {
          setText("mcrp_16", bean.getCurrency());
          setText("mcrp_13", NumberUtil.textMoney(Double.valueOf(other_cost + bean.getTotal()), bean.getCurrency()));
          
          setText("mcrp_12", NumberUtil.formatMoneyDefault(other_cost + bean.getTotal(), bean.getCurrency()));
        }
      }
      else
      {
        setText("mcrp_16", "xxx");
        setText("mcrp_13", "xxx");
        setText("mcrp_12", "xxx");
        setText("mcrp_14", "xxx");
        setText("mcrp_15", "xxx");
      }
    }
    else
    {
      setText("mcrp_16", "xxx");
      setText("mcrp_13", "xxx");
      setText("mcrp_12", "xxx");
      setText("mcrp_14", "xxx");
      setText("mcrp_15", "xxx");
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
    ContractDetailFormBean beanDetail = null;
    if (i < this.arrDAO.size())
    {
      beanDetail = (ContractDetailFormBean)this.arrDAO.get(i);
      if (tab.equals("mcrp_a")) {
        text = i + 1 + "";
      } else if (tab.equals("mcrp_b")) {
        text = beanDetail.getMatName();
      } else if (tab.equals("mcrp_c")) {
        text = beanDetail.getUnit();
      } else if (tab.equals("mcrp_d")) {
        text = NumberUtil.formatMoneyDefault(beanDetail.getQuantity(), "VND");
      } else if (tab.equals("mcrp_e"))
      {
        if (this.permission) {
          text = NumberUtil.formatMoneyDefault(beanDetail.getPrice(), beanDetail.getCurrency()) + "";
        } else {
          text = "xxx";
        }
      }
      else if (tab.equals("mcrp_f"))
      {
        if (this.permission) {
          text = NumberUtil.formatMoneyDefault(beanDetail.getQuantity() * beanDetail.getPrice(), beanDetail.getCurrency()) + "";
        } else {
          text = "xxx";
        }
      }
      else if (tab.equals("mcrp_g")) {
        text = beanDetail.getRequestNumber();
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
