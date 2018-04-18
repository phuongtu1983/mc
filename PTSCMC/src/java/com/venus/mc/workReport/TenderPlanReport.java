package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.bean.TenderEvaluateGroupBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.bean.TenderPlanDetailBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.tenderplan.TenderEvaluateVendorFormBean;
import com.venus.mc.tenderplan.TenderPlanFormBean;
import com.venus.mc.util.MCUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class TenderPlanReport
  extends SpineReportParser
{
  private ArrayList arrEvalVenGroup;
  private ArrayList arrVendor;
  private String evalVenGroupRow = "evalVenGroupRow";
  private String vendorRow = "vendorRow";
  
  public TenderPlanReport(String xmlTemplate, String wordTemplate, String resultFileName)
  {
    super(xmlTemplate, wordTemplate, resultFileName);
  }
  
  protected void parse(Object object)
    throws Exception
  {
    TenderPlanBean bean = (TenderPlanBean)object;
    OrganizationDAO orgDAO = new OrganizationDAO();
    OrganizationBean orgBean = null;
    try
    {
      orgBean = orgDAO.getOrganizationOfEmployee(bean.getCreatedEmp());
      if (orgBean.getParentId() > 0) {
        orgBean = orgDAO.getOrganization(orgBean.getParentId());
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (orgBean == null) {
      orgBean = new OrganizationBean();
    }
    String reqNumber = "";
    String whichuse = "";
    String reqs = "";
    TenderPlanDAO tenderDAO = new TenderPlanDAO();
    try
    {
      ArrayList arrReq = tenderDAO.getRequestsOfTenderPlan(bean.getTenId());
      RequestBean reqBean = null;
      for (int i = 0; i < arrReq.size(); i++)
      {
        reqBean = (RequestBean)arrReq.get(i);
        reqNumber = reqNumber + ", " + reqBean.getRequestNumber();
        whichuse = whichuse + ", " + reqBean.getWhichUseName();
        reqs = reqs + ", " + reqBean.getRequestNumber() + " ng�y " + reqBean.getCreatedDate();
      }
      if (!reqNumber.equals(""))
      {
        reqNumber = reqNumber.substring(2);
        whichuse = whichuse.substring(2);
      }
      if (!reqs.equals("")) {
        reqs = reqs.substring(2);
      }
    }
    catch (Exception localException1) {}
    try
    {
      ArrayList arrDetail = tenderDAO.getTenderPlanDetails(bean.getTenId());
      if (arrDetail.size() > 0)
      {
        TenderPlanDetailBean detail = (TenderPlanDetailBean)arrDetail.get(0);
        MaterialDAO matDAO = new MaterialDAO();
        MaterialBean material = matDAO.getMaterial(detail.getMatId() + "");
        if (material != null) {
          setText("mcrp_materialType", material.getGroName());
        } else {
          setText("mcrp_materialType", "");
        }
      }
    }
    catch (Exception localException2) {}
    Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
    SimpleDateFormat sdf = null;
    setText("mcrp_orgName", orgBean.getName().toUpperCase());
    setText("mcrp_tenderNumber", bean.getTenderNumber());
    setText("mcrp_requestNumber", reqs);
    sdf = new SimpleDateFormat("dd");
    setText("mcrp_day", sdf.format(date));
    sdf = new SimpleDateFormat("MM");
    setText("mcrp_month", sdf.format(date));
    sdf = new SimpleDateFormat("yyyy");
    setText("mcrp_year", sdf.format(date));
    
    setText("mcrp_packName", bean.getPackName());
    setText("mcrp_pro", "");
    setText("mcrp_foundation", bean.getFoundation());
    int offerType = bean.getOfferType();
    String offerTypeStr = "";
    if (offerType == TenderPlanFormBean.OFFER_IN) {
      offerTypeStr = MCUtil.getBundleString("message.tenderplan.offer.in");
    } else if (offerType == TenderPlanFormBean.OFFER_OUT) {
      offerTypeStr = MCUtil.getBundleString("message.tenderplan.offer.out");
    } else if (offerType == TenderPlanFormBean.OFFER_INOUT) {
      offerTypeStr = MCUtil.getBundleString("message.tenderplan.offer.inout");
    }
    setText("mcrp_offerType", offerTypeStr);
    setText("mcrp_financial1", NumberUtil.formatNumberDefault(bean.getFinancialPreTax()));
    setText("mcrp_financial", bean.getFinancial());
    setText("mcrp_sendingDate", bean.getBidSendingDate());
    setText("mcrp_sendingTime", bean.getBidSendingTime());
    setText("mcrp_deadline", bean.getBidDeadline());
    setText("mcrp_deadlineTime", bean.getBidDeadlineTime());
    setText("mcrp_openingDate", bean.getBidOpeningDate());
    setText("mcrp_openingTime", bean.getBidOpeningTime());
    setText("mcrp_evaluatedDate", bean.getEvaluatedDate());
    setText("mcrp_evaluatedTime", bean.getEvaluatedTime());
    setText("mcrp_approvedDate", bean.getApprovedDate());
    setText("mcrp_approvedTime", bean.getApprovedTime());
    setText("mcrp_contractDate", bean.getContractDate());
    setText("mcrp_contractTime", bean.getContractTime());
    setText("mcrp_contractExecDate", bean.getContractExecDate());
    setText("mcrp_contractExecTime", bean.getContractExecTime());
    if (bean.getTechEvalStandard().trim().length() > 0) {
      setText("mcrp_techStandard", bean.getTechEvalStandard());
    } else {
      setText("mcrp_techStandard", "H? s? ch�o h�ng ph?i ?�p ?ng c? b?n n?i dung v? y�u c?u k? thu?t c?a C�ng Ty DVCKHH nh? quy ??nh trong h? s? m?i ch�o h�ng. C�ng Ty DVCKHH s? ?�nh gi� ??t ho?c kh�ng ??t v? m?t k? thu?t");
    }
    if (bean.getComEvalStandard().trim().length() > 0) {
      setText("mcrp_comStandard", bean.getComEvalStandard());
    } else {
      setText("mcrp_comStandard", "C?n c? k?t qu? ?�nh gi� k? thu?t, nh?ng h?ng m?c h�ng ho� tho? m�n y�u c?u v? k? thu?t, ?�p ?ng y�u c?u v? ti?n ?? cung c?p s? ???c ti?p t?c ?�nh gi�, xem x�t l?a ch?n. CTDVCKHH s? ch?n k� h?p ??ng h�ng ho� tho? m�n y�u c?u k? thu?t, ti?n ?? giao h�ng v� c� ??n gi� ch�o c?nh tranh nh?t");
    }
    Hashtable map = new Hashtable();
    RowSAXHandler row = null;
    row = new RowSAXHandler(this.evalVenGroupRow, this);
    try
    {
      this.arrEvalVenGroup = tenderDAO.getTenderPlanEvalGroup(bean.getTenId());
    }
    catch (Exception localException3) {}
    if (this.arrEvalVenGroup == null) {
      this.arrEvalVenGroup = new ArrayList();
    }
    row.setRowCount(this.arrEvalVenGroup.size());
    map.put(this.evalVenGroupRow, row);
    
    row = new RowSAXHandler(this.vendorRow, this);
    try
    {
      this.arrVendor = tenderDAO.getTenderPlanVendorDetail(bean.getTenId());
    }
    catch (Exception localException4) {}
    if (this.arrVendor == null) {
      this.arrVendor = new ArrayList();
    }
    row.setRowCount(this.arrVendor.size());
    map.put(this.vendorRow, row);
    
    setArrTable(map);
  }
  
  private String getEvalVenGroupText(int i, String tab)
  {
    String text = "";
    TenderEvaluateGroupBean bean = null;
    if (i < this.arrEvalVenGroup.size())
    {
      bean = (TenderEvaluateGroupBean)this.arrEvalVenGroup.get(i);
      if (tab.equals("mcrp_n1")) {
        text = i + 1 + "";
      } else if (tab.equals("mcrp_empName")) {
        text = bean.getEmployee();
      } else if (tab.equals("mcrp_position")) {
        text = bean.getPosition();
      } else if (tab.equals("mcrp_org")) {
        text = bean.getNote();
      }
    }
    return text;
  }
  
  private String getVendorText(int i, String tab)
  {
    String text = "";
    TenderEvaluateVendorFormBean bean = null;
    if (i < this.arrVendor.size())
    {
      bean = (TenderEvaluateVendorFormBean)this.arrVendor.get(i);
      if (tab.equals("mcrp_n2")) {
        text = i + 1 + "";
      } else if (tab.equals("mcrp_vendor")) {
        text = bean.getVenName();
      } else if (tab.equals("mcrp_phone")) {
        text = bean.getVenPhone();
      } else if (tab.equals("mcrp_fax")) {
        text = bean.getVenFax();
      } else if (tab.equals("mcrp_email")) {
        text = bean.getVenEmail();
      } else if (tab.equals("mcrp_attn")) {
        text = "";
      } else if (tab.equals("mcrp_address")) {
        text = bean.getVenAddress();
      }
    }
    return text;
  }
  
  public String getTabText(String rowId, int i, String tab)
  {
    if (rowId.equals(this.evalVenGroupRow)) {
      return getEvalVenGroupText(i, tab);
    }
    if (rowId.equals(this.vendorRow)) {
      return getVendorText(i, tab);
    }
    return "";
  }
}
