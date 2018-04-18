package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.InvoiceContractBean;
import com.venus.mc.bean.PaymentReportBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.contract.ContractFormBean;
import com.venus.mc.contract.InvoiceContractFormBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.payment.PaymentFormBean;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;

public class PaymentReport
  extends SpineReportParser
{
  private ArrayList arrDetail;
  private String tableRow = "matRow";
  private boolean permission = false;
  private String currency = "";
  
  public PaymentReport(String xmlTemplate, String wordTemplate, String resultFileName)
  {
    super(xmlTemplate, wordTemplate, resultFileName);
  }
  
  protected void parse(Object object)
    throws Exception
  {
    Integer payIdObject = (Integer)object;
    int payId = payIdObject.intValue();
    PaymentFormBean bean = null;
    ContractDAO contractDAO = new ContractDAO();
    try
    {
      bean = contractDAO.getPayment(payId + "");
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (bean == null) {
      return;
    }
    VendorBean vendor = null;
    try
    {
      VendorDAO venDAO = new VendorDAO();
      vendor = venDAO.getVendor(bean.getVenId());
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (vendor == null) {
      vendor = new VendorBean();
    }
    ArrayList arrInvoice = null;
    try
    {
      arrInvoice = contractDAO.getContractInvoicesByPayment(payId);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (arrInvoice == null) {
      arrInvoice = new ArrayList();
    }
    ArrayList arrContract = null;
    try
    {
      arrContract = contractDAO.getContactForPayment(payId + "");
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (arrContract == null) {
      arrContract = new ArrayList();
    }
    double contractTotal = 0.0D;
    ContractFormBean cBean = null;
    for (int i = 0; i < arrContract.size(); i++)
    {
      cBean = (ContractFormBean)arrContract.get(i);
      contractTotal += cBean.getTotal();
    }
    Date date = DateUtil.transformerStringEnDate(bean.getPayDate(), "/");
    if (date != null)
    {
      SimpleDateFormat sdf = null;
      sdf = new SimpleDateFormat("dd");
      setText("mcrp_day", sdf.format(date));
      sdf = new SimpleDateFormat("MM");
      setText("mcrp_month", sdf.format(date));
      sdf = new SimpleDateFormat("yyyy");
      setText("mcrp_year", sdf.format(date));
    }
    setText("mcrp_number", bean.getPayNumber());
    setText("mcrp_vendor", bean.getVendorName());
    if (bean.getConKind() == ContractBean.KIND_PRINCIPLE)
    {
      setText("mcrp_kind_abb", MCUtil.getBundleString("message.principle.abb"));
      setText("mcrp_kind", MCUtil.getBundleString("message.contract.l_principle"));
    }
    else if (bean.getConKind() == ContractBean.KIND_ORDER)
    {
      setText("mcrp_kind_abb", MCUtil.getBundleString("message.order.abb"));
      setText("mcrp_kind", MCUtil.getBundleString("message.l_order"));
    }
    else if (bean.getConKind() == ContractBean.KIND_CONTRACT)
    {
      setText("mcrp_kind_abb", MCUtil.getBundleString("message.contract.abb"));
      setText("mcrp_kind", MCUtil.getBundleString("message.l_contract"));
    }
    else if (bean.getConKind() == ContractBean.KIND_DELIVERY_REQUEST)
    {
      setText("mcrp_kind_abb", MCUtil.getBundleString("message.deliveryrequest.abb"));
      setText("mcrp_kind", MCUtil.getBundleString("message.l_deliveryrequest"));
    }
    else if (bean.getConKind() == ContractBean.KIND_APPENDIX)
    {
      setText("mcrp_kind_abb", MCUtil.getBundleString("message.appendix.abb"));
      setText("mcrp_kind", MCUtil.getBundleString("message.l_appendix"));
    }
    setText("mcrp_contract", bean.getContractNumber());
    setText("mcrp_contractDate", bean.getContractDate());
    setText("mcrp_contract_relation", bean.getContractNumber());
    String invoice = "";
    String invoiceDate = "";
    String contractNumber = "";
    String contractDate = "";
    InvoiceContractFormBean invoiceBean = null;
    double rates = bean.getRates();
    for (int i = 0; i < arrInvoice.size(); i++)
    {
      invoiceBean = (InvoiceContractFormBean)arrInvoice.get(i);
      invoice = invoice + "," + invoiceBean.getInvoice();
      invoiceDate = invoiceDate + "," + invoiceBean.getInvoiceDate();
      contractNumber = contractNumber + "," + invoiceBean.getContractNumber();
      contractDate = contractDate + "," + invoiceBean.getContractPaymentDate();
    }
    if (invoice.length() > 0)
    {
      invoice = invoice.substring(1);
      invoiceDate = invoiceDate.substring(1);
      contractNumber = contractNumber.substring(1);
      contractDate = contractDate.substring(1);
    }
    setText("mcrp_sum_invoice", invoice);
    setText("mcrp_sum_invoiceDate", invoiceDate);
    HttpServletRequest request1 = getRequest();
    if (PermissionUtil.hasPermission(request1, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE))
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      int orgId = MCUtil.getOrganizationID(getRequest().getSession());
      String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgId;
      contractDAO.setRequestOrg(orgs);
      contractDAO.setRequestEmp(MCUtil.getMemberID(getRequest().getSession()));
      
      this.permission = true;
      this.currency = "VN?";
      if (!GenericValidator.isBlankOrNull(bean.getCurrency())) {
        this.currency = bean.getCurrency();
      }
      if ((!this.currency.contains("VN")) && 
        (rates != 1.0D)) {
        this.currency = "VN?";
      }
      setText("mcrp_total", NumberUtil.formatMoneyDefault(bean.getTotalPay() * bean.getRates(), this.currency));
      setText("mcrp_total1", NumberUtil.formatMoneyDefault(contractTotal * bean.getRates(), this.currency));
      if (rates != 1.0D) {}
      setText("mcrp_currency", this.currency);
      setText("mcrp_total_text", NumberUtil.textMoney(Double.valueOf(bean.getTotalPay() * bean.getRates()), this.currency));
      setText("mcrp_account", vendor.getField());
    }
    else
    {
      setText("mcrp_total", "xxx");
      setText("mcrp_currency", "xxx");
      setText("mcrp_total_text", "xxx");
      setText("mcrp_account", "xxx");
    }
    ArrayList arrBean = null;
    try
    {
      arrBean = contractDAO.getInformationForPaymentReport(payId);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (arrBean == null) {
      arrBean = new ArrayList();
    }
    String project = ",";
    String request = ",";
    String mrir = ",";
    String msv = ",";
    String msvDate = ",";
    String mrirDate = ",";
    
    PaymentReportBean pBean = null;
    for (int i = 0; i < arrBean.size(); i++)
    {
      pBean = (PaymentReportBean)arrBean.get(i);
      if (project.indexOf("," + pBean.getProject() + "") == -1) {
        project = project + pBean.getProject() + ",";
      }
      if (request.indexOf("," + pBean.getRequest() + "") == -1) {
        request = request + pBean.getRequest() + ",";
      }
      if (mrir.indexOf("," + pBean.getMrir() + "") == -1)
      {
        mrir = mrir + pBean.getMrir() + ",";
        mrirDate = mrirDate + pBean.getMrirDate() + ",";
      }
      if (msv.indexOf("," + pBean.getMsv() + "") == -1)
      {
        msv = msv + pBean.getMsv() + ",";
        msvDate = msvDate + pBean.getMsvDate() + ",";
      }
    }
    if (project.length() > 1)
    {
      project = project.substring(1, project.length() - 1);
      request = request.substring(1, request.length() - 1);
      mrir = mrir.substring(1, mrir.length() - 1);
      mrirDate = mrirDate.substring(1, mrirDate.length() - 1);
      msv = msv.substring(1, msv.length() - 1);
      msvDate = msvDate.substring(1, msvDate.length() - 1);
    }
    setText("mcrp_project", project);
    setText("mcrp_request", request);
    setText("mcrp_mrir", mrir);
    setText("mcrp_msv", msv);
    setText("mcrp_msv_date", msvDate);
    setText("mcrp_deliveryDate", mrirDate);
    
    Hashtable map = new Hashtable();
    RowSAXHandler row = null;
    row = new RowSAXHandler(this.tableRow, this);
    try
    {
      this.arrDetail = contractDAO.getInvoiceContracts(0, payId);
    }
    catch (Exception localException1) {}
    if (this.arrDetail == null) {
      this.arrDetail = new ArrayList();
    }
    row.setRowCount(this.arrDetail.size());
    map.put(this.tableRow, row);
    
    setArrTable(map);
    
    InvoiceContractBean icBean = null;
    String txt = "";
    String txt2 = "";
    String txt3 = "";
    RequestDAO reqDAO = new RequestDAO();
    for (int i = 0; i < this.arrDetail.size(); i++)
    {
      icBean = (InvoiceContractBean)this.arrDetail.get(i);
      txt = txt + " ";
      if (txt2.isEmpty()) {
        txt2 = txt2 + " ";
      } else {
        txt2 = txt2 + "; ";
      }
      if (txt3.isEmpty()) {
        txt3 = txt3 + " ";
      } else {
        txt3 = txt3 + "; ";
      }
      if (icBean.getContractKind() == ContractBean.KIND_CONTRACT)
      {
        txt = txt + MCUtil.getBundleString("message.contract.abb");
        txt3 = txt3 + MCUtil.getBundleString("message.contract.abb");
      }
      else if (icBean.getContractKind() == ContractBean.KIND_PRINCIPLE)
      {
        txt = txt + MCUtil.getBundleString("message.principle.abb");
        txt3 = txt3 + MCUtil.getBundleString("message.principle.abb");
      }
      else if (icBean.getContractKind() == ContractBean.KIND_APPENDIX)
      {
        txt = txt + MCUtil.getBundleString("message.appendix.abb");
        txt3 = txt3 + MCUtil.getBundleString("message.appendix.abb");
      }
      else if (icBean.getContractKind() == ContractBean.KIND_ORDER)
      {
        txt = txt + MCUtil.getBundleString("message.order.abb");
        txt3 = txt3 + MCUtil.getBundleString("message.order.abb");
      }
      else if (icBean.getContractKind() == ContractBean.KIND_DELIVERY_REQUEST)
      {
        txt = txt + MCUtil.getBundleString("message.deliveryrequest.abb");
        txt3 = txt3 + MCUtil.getBundleString("message.deliveryrequest.abb");
      }
      txt = txt + " " + icBean.getContractNumber() + " ng�y " + icBean.getContractDate();
      txt3 = txt3 + " " + icBean.getContractNumber();
      try
      {
        String reqNum = reqDAO.getRequestOfContract(icBean.getConId());
        txt = txt + " theo RQ s? " + reqNum + ";";
      }
      catch (Exception localException2) {}
      if (icBean.getContractKind() == ContractBean.KIND_CONTRACT) {
        txt2 = txt2 + MCUtil.getBundleString("message.l_contract");
      } else if (icBean.getContractKind() == ContractBean.KIND_PRINCIPLE) {
        txt2 = txt2 + MCUtil.getBundleString("message.contract.l_principle");
      } else if (icBean.getContractKind() == ContractBean.KIND_APPENDIX) {
        txt2 = txt2 + MCUtil.getBundleString("message.l_appendix");
      } else if (icBean.getContractKind() == ContractBean.KIND_ORDER) {
        txt2 = txt2 + MCUtil.getBundleString("message.l_order");
      } else if (icBean.getContractKind() == ContractBean.KIND_DELIVERY_REQUEST) {
        txt2 = txt2 + MCUtil.getBundleString("message.principle.abb");
      }
      if (icBean.getContractKind() == ContractBean.KIND_DELIVERY_REQUEST) {
        txt2 = txt2 + " " + icBean.getParentNumber();
      } else {
        txt2 = txt2 + " " + icBean.getContractNumber();
      }
    }
    setText("mcrp_baseon", txt);
    setText("mcrp_contract_relation_baseon", txt2);
    setText("mcrp_contract_baseon", txt3);
  }
  
  private String getContractMaterialText(int i, String tab)
  {
    String text = "";
    InvoiceContractBean bean = null;
    if (i < this.arrDetail.size())
    {
      bean = (InvoiceContractBean)this.arrDetail.get(i);
      if (tab.equals("mcrp_n1"))
      {
        text = i + 1 + "";
      }
      else if (tab.equals("mcrp_invoice"))
      {
        text = bean.getInvoice();
      }
      else if (tab.equals("mcrp_invoiceDate"))
      {
        text = bean.getInvoiceDate();
      }
      else if (tab.equals("mcrp_note"))
      {
        text = MCUtil.getBundleString("message.contract.bill.export.note");
        if (bean.getContractKind() == ContractBean.KIND_CONTRACT) {
          text = text + " " + MCUtil.getBundleString("message.l_contract");
        } else if (bean.getContractKind() == ContractBean.KIND_PRINCIPLE) {
          text = text + " " + MCUtil.getBundleString("message.contract.l_principle");
        } else if (bean.getContractKind() == ContractBean.KIND_APPENDIX) {
          text = text + " " + MCUtil.getBundleString("message.l_appendix");
        } else if (bean.getContractKind() == ContractBean.KIND_ORDER) {
          text = text + " " + MCUtil.getBundleString("message.l_order");
        } else if (bean.getContractKind() == ContractBean.KIND_DELIVERY_REQUEST) {
          text = text + " " + MCUtil.getBundleString("message.l_deliveryrequest");
        }
        text = text + " " + bean.getContractNumber();
      }
      else if (tab.equals("mcrp_amount"))
      {
        if (this.permission) {
          text = NumberUtil.formatMoneyDefault(bean.getAmount() * bean.getRates(), this.currency) + "";
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
