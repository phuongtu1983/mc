package com.venus.mc.tenderplan;

import com.venus.core.util.DateUtil;
import com.venus.core.util.FileUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.bean.TenderEvaluateGroupBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.bean.TenderPlanDetailBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.MCUtil;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TenderPlanPrintAction
  extends BaseAction
{
  private ArrayList arrFiles = null;
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    this.arrFiles = new ArrayList();
    String type = "zip";
    
    String fileName = "BM-02-02-KeHoachGoiChaoHangFax";
    String fileName1 = "PhuLucChaoGiaFAX";
    String fileName3 = "DanhMucVTChaoGiaFAX";
    
    String fileName2 = "BM-02-02-KeHoachGoiChaoHangPBK";
    String fileName4 = "PhuLucChaoGiaPBK";
    String fileName5 = "DanhMucVTChaoGiaPBK";
    
    String userName = MCUtil.getMemberName(request.getSession());
    try
    {
      TenderPlanDAO tenderDAO = new TenderPlanDAO();
      int tenId = NumberUtil.parseInt(request.getParameter("tenId"), 0);
      if (tenderDAO.getTenderPlanForm(tenId) == TenderPlanFormBean.FORM_FAX)
      {
        print(request, response, fileName, type);
        print1(request, response, fileName1, type);
        print3(request, response, fileName3, type);
      }
      else
      {
        print2(request, response, fileName2, type);
        print4(request, response, fileName4, type);
        print5(request, response, fileName5, type);
      }
    }
    catch (Exception localException) {}
    if (type.equals("zip"))
    {
      String outputFileName = fileName + "-" + userName + ".zip";
      FileUtil.zipFile(outputFileName, this.arrFiles);
      OutputUtil.sendZipFileToOutput(response, outputFileName);
    }
    return null;
  }
  
  private void print(HttpServletRequest request, HttpServletResponse response, String fileName, String type)
  {
    try
    {
      int tenId = NumberUtil.parseInt(request.getParameter("tenId"), 0);
      String userName = MCUtil.getMemberName(request.getSession());
      if (type.equals("docx")) {
        type = "";
      } else {
        type = ".docx";
      }
      String wordDir = "C:";
      this.arrFiles.add(wordDir + "/" + fileName + "-" + userName + type);
      String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/" + fileName + ".docx");
      
      InputStream in = new FileInputStream(wordTemplate);
      IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
      
      IContext context = report.createContext();
      
      List<TenderPlanBean> docs = new ArrayList();
      
      TenderPlanDAO tenderDAO = new TenderPlanDAO();
      TenderPlanBean bean = null;
      TenderPlanBean bean1 = null;
      String reqNumber = "";
      String whichuse = "";
      String reqs = "";
      try
      {
        bean = tenderDAO.getTenderPlan(Integer.parseInt(request.getParameter("tenId")));
        bean1 = tenderDAO.getOrgNameEmpCreatedTenderPlan(Integer.parseInt(request.getParameter("tenId")));
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
      catch (Exception localException) {}
      try
      {
        ArrayList arrDetail = tenderDAO.getTenderPlanDetails(bean.getTenId());
        if (arrDetail.size() > 0)
        {
          TenderPlanDetailBean detail = (TenderPlanDetailBean)arrDetail.get(0);
          MaterialDAO matDAO = new MaterialDAO();
          MaterialBean material = matDAO.getMaterial(detail.getMatId() + "");
          if (material == null) {}
        }
        offerType = bean.getOfferType();
      }
      catch (Exception localException1) {}
      int offerType;
      String offerTypeStr = "";
      if (offerType == TenderPlanFormBean.OFFER_IN) {
        offerTypeStr = MCUtil.getBundleString("message.tenderplan.offer.in");
      } else if (offerType == TenderPlanFormBean.OFFER_OUT) {
        offerTypeStr = MCUtil.getBundleString("message.tenderplan.offer.out");
      } else if (offerType == TenderPlanFormBean.OFFER_INOUT) {
        offerTypeStr = MCUtil.getBundleString("message.tenderplan.offer.inout");
      }
      Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
      
      String day = DateUtil.formatDate(date, "dd");
      String month = DateUtil.formatDate(date, "MM");
      String year = DateUtil.formatDate(date, "yyyy");
      String orgName = bean1.getOrgName();
      String orgNameUpcase = orgName.toUpperCase();
      String abbreviate = bean1.getAbbreviate();
      String requestNumber = reqs;
      String currency = bean.getCurrency();
      String tenderNumber = bean.getTenderNumber();
      
      String packName = bean.getPackName();
      
      String foundation = bean.getFoundation();
      
      offerTypeStr = offerTypeStr;
      String financial = bean.getFinancial();
      String bidSendingDate = bean.getBidSendingDate();
      String bidDeadline = bean.getBidDeadline();
      String bidOpeningDate = bean.getBidOpeningDate();
      String evaluatedDate = bean.getEvaluatedDate();
      String approvedDate = bean.getApprovedDate();
      String contractDate = bean.getContractDate();
      String contractExecDate = bean.getContractExecDate();
      String bidSendingTime = bean.getBidSendingTime();
      String bidDeadlineTime = bean.getBidDeadlineTime();
      String bidOpeningTime = bean.getBidOpeningTime();
      String evaluatedTime = bean.getEvaluatedTime();
      String approvedTime = bean.getApprovedTime();
      String contractTime = bean.getContractTime();
      String contractExecTime = bean.getContractExecTime();
      String techEvalStandard = "";
      String comEvalStandard = "";
      if (bean.getTechEvalStandard().trim().length() > 0) {
        techEvalStandard = bean.getTechEvalStandard();
      } else {
        techEvalStandard = "H? s? ch�o h�ng ph?i ?�p ?ng c? b?n n?i dung v? y�u c?u k? thu?t c?a C�ng Ty DVCKHH nh? quy ??nh trong h? s? m?i ch�o h�ng. C�ng Ty DVCKHH s? ?�nh gi� ??t ho?c kh�ng ??t v? m?t k? thu?t";
      }
      if (bean.getComEvalStandard().trim().length() > 0) {
        comEvalStandard = bean.getComEvalStandard();
      } else {
        comEvalStandard = "C?n c? k?t qu? ?�nh gi� k? thu?t, nh?ng h?ng m?c h�ng ho� tho? m�n y�u c?u v? k? thu?t, ?�p ?ng y�u c?u v? ti?n ?? cung c?p s? ???c ti?p t?c ?�nh gi�, xem x�t l?a ch?n. CTDVCKHH s? ch?n k� h?p ??ng h�ng ho� tho? m�n y�u c?u k? thu?t, ti?n ?? giao h�ng v� c� ??n gi� ch�o c?nh tranh nh?t";
      }
      docs.add(new TenderPlanBean(tenId, tenId, tenderNumber, "", "", packName, "", foundation, "", year, offerType, financial, bidSendingDate, bidDeadline, bidOpeningDate, evaluatedDate, approvedDate, contractDate, contractExecDate, bidSendingTime, bidDeadlineTime, bidOpeningTime, evaluatedTime, approvedTime, contractTime, contractExecTime, techEvalStandard, comEvalStandard, tenId, requestNumber, tenId, wordTemplate, tenId, year, approvedTime, tenId, offerType, offerType, "", currency, orgName, orgName, orgNameUpcase, offerTypeStr, tenId, day, month, year, abbreviate));
      
      context.put("ptscmc", docs);
      
      String text = "";
      TenderEvaluateVendorFormBean beanDetails = null;
      ArrayList arrVendor = null;
      List<TenderEvaluateVendorFormBean> docs1 = new ArrayList();
      try
      {
        arrVendor = tenderDAO.getTenderPlanVendorDetail(bean.getTenId());
      }
      catch (Exception localException2) {}
      if (arrVendor == null) {
        arrVendor = new ArrayList();
      }
      for (int i = 0; i < arrVendor.size(); i++)
      {
        beanDetails = (TenderEvaluateVendorFormBean)arrVendor.get(i);
        
        int stt = i + 1;
        String venName = beanDetails.getVenName();
        String venPhone = beanDetails.getVenPhone();
        String venFax = beanDetails.getVenFax();
        String attn = "";
        String venEmail = beanDetails.getVenEmail();
        String venAddress = beanDetails.getVenAddress();
        
        docs1.add(new TenderEvaluateVendorFormBean(tenId, tenId, tenId, stt, i, bidDeadline, foundation, stt, attn, venName, bidDeadline, stt, venName, venPhone, venFax, venEmail, venAddress, attn, stt, attn));
        context.put("ptscmc1", docs1);
      }
      OutputStream out = new FileOutputStream(new File(wordDir + "/" + fileName + "-" + userName + type));
      
      report.process(context, out);
      try
      {
        if (!type.equals(".docx")) {
          OutputUtil.sendWordFileToOutput(response, wordDir + "/", fileName + "-" + userName);
        }
      }
      catch (Exception localException3) {}
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (XDocReportException e)
    {
      e.printStackTrace();
    }
  }
  
  private void print1(HttpServletRequest request, HttpServletResponse response, String fileName, String type)
  {
    try
    {
      int tenId = NumberUtil.parseInt(request.getParameter("tenId"), 0);
      String userName = MCUtil.getMemberName(request.getSession());
      if (type.equals("docx")) {
        type = "";
      } else {
        type = ".docx";
      }
      String wordDir = "C:";
      this.arrFiles.add(wordDir + "/" + fileName + "-" + userName + type);
      String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/" + fileName + ".docx");
      
      InputStream in = new FileInputStream(wordTemplate);
      IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
      
      IContext context = report.createContext();
      
      List<TenderPlanBean> docs = new ArrayList();
      
      TenderPlanDAO tenderDAO1 = new TenderPlanDAO();
      TenderPlanBean bean1 = null;
      String cerText = "";
      try
      {
        bean1 = tenderDAO1.getTenderPlan(Integer.parseInt(request.getParameter("tenId")));
        cerText = tenderDAO1.getTenderPlanCertificateText(Integer.parseInt(request.getParameter("tenId")));
      }
      catch (Exception localException) {}
      Date date = DateUtil.transformerStringEnDate(bean1.getCreatedDate(), "/");
      
      String packName = bean1.getPackName();
      String deliveryTime = bean1.getDeliveryTime();
      
      docs.add(new TenderPlanBean(tenId, tenId, userName, cerText, wordTemplate, packName, cerText, wordDir, type, type, tenId, fileName, wordTemplate, wordTemplate, wordTemplate, userName, wordTemplate, packName, wordTemplate, deliveryTime, deliveryTime, deliveryTime, deliveryTime, deliveryTime, deliveryTime, deliveryTime, deliveryTime, packName, tenId, userName, tenId, wordTemplate, tenId, type, deliveryTime, tenId, tenId, tenId, cerText, cerText, packName, packName, wordTemplate, cerText, tenId, type, type, type, userName));
      
      context.put("ptscmc", docs);
      
      String text = "";
      TenderPlanDetailBean beanDetail = null;
      ArrayList arrMaterial = null;
      List<TenderPlanDetailBean> docs1 = new ArrayList();
      try
      {
        arrMaterial = tenderDAO1.getTenderPlanDetailsForExport(bean1.getTenId());
      }
      catch (Exception localException1) {}
      if (arrMaterial == null) {
        arrMaterial = new ArrayList();
      }
      for (int i = 0; i < arrMaterial.size(); i++)
      {
        beanDetail = (TenderPlanDetailBean)arrMaterial.get(i);
        
        int stt = i + 1;
        String matName = beanDetail.getMatName();
        String unit = beanDetail.getUnit();
        double quantity = beanDetail.getQuantity();
        
        docs1.add(new TenderPlanDetailBean(tenId, tenId, tenId, unit, quantity, fileName, tenId, matName, matName, quantity, quantity, stt));
        context.put("ptscmc1", docs1);
      }
      OutputStream out = new FileOutputStream(new File(wordDir + "/" + fileName + "-" + userName + type));
      
      report.process(context, out);
      try
      {
        if (!type.equals(".docx")) {
          OutputUtil.sendWordFileToOutput(response, wordDir + "/", fileName + "-" + userName);
        }
      }
      catch (Exception localException2) {}
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (XDocReportException e)
    {
      e.printStackTrace();
    }
  }
  
  private void print2(HttpServletRequest request, HttpServletResponse response, String fileName, String type)
  {
    try
    {
      int tenId = NumberUtil.parseInt(request.getParameter("tenId"), 0);
      String userName = MCUtil.getMemberName(request.getSession());
      if (type.equals("docx")) {
        type = "";
      } else {
        type = ".docx";
      }
      String wordDir = "C:";
      this.arrFiles.add(wordDir + "/" + fileName + "-" + userName + type);
      String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/" + fileName + ".docx");
      
      InputStream in = new FileInputStream(wordTemplate);
      IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
      
      IContext context = report.createContext();
      
      List<TenderPlanBean> docs = new ArrayList();
      
      TenderPlanDAO tenderDAO = new TenderPlanDAO();
      TenderPlanBean bean = null;
      TenderPlanBean bean1 = null;
      String reqNumber = "";
      String whichuse = "";
      String reqs = "";
      try
      {
        bean = tenderDAO.getTenderPlan(Integer.parseInt(request.getParameter("tenId")));
        bean1 = tenderDAO.getOrgNameEmpCreatedTenderPlan(Integer.parseInt(request.getParameter("tenId")));
        
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
      catch (Exception localException) {}
      try
      {
        ArrayList arrDetail = tenderDAO.getTenderPlanDetails(bean.getTenId());
        if (arrDetail.size() > 0)
        {
          TenderPlanDetailBean detail = (TenderPlanDetailBean)arrDetail.get(0);
          MaterialDAO matDAO = new MaterialDAO();
          MaterialBean material = matDAO.getMaterial(detail.getMatId() + "");
          if (material == null) {}
        }
        offerType = bean.getOfferType();
      }
      catch (Exception localException1) {}
      int offerType;
      String offerTypeStr = "";
      if (offerType == TenderPlanFormBean.OFFER_IN) {
        offerTypeStr = MCUtil.getBundleString("message.tenderplan.offer.in");
      } else if (offerType == TenderPlanFormBean.OFFER_OUT) {
        offerTypeStr = MCUtil.getBundleString("message.tenderplan.offer.out");
      } else if (offerType == TenderPlanFormBean.OFFER_INOUT) {
        offerTypeStr = MCUtil.getBundleString("message.tenderplan.offer.inout");
      }
      Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
      
      String day = DateUtil.formatDate(date, "dd");
      String month = DateUtil.formatDate(date, "MM");
      String year = DateUtil.formatDate(date, "yyyy");
      String orgName = bean1.getOrgName();
      String orgNameUpcase = orgName.toUpperCase();
      String abbreviate = bean1.getAbbreviate();
      String requestNumber = reqs;
      String currency = bean.getCurrency();
      String tenderNumber = bean.getTenderNumber();
      
      String packName = bean.getPackName();
      
      String foundation = bean.getFoundation();
      
      offerTypeStr = offerTypeStr;
      String financial = bean.getFinancial();
      String bidSendingDate = bean.getBidSendingDate();
      String bidDeadline = bean.getBidDeadline();
      String bidOpeningDate = bean.getBidOpeningDate();
      String evaluatedDate = bean.getEvaluatedDate();
      String approvedDate = bean.getApprovedDate();
      String contractDate = bean.getContractDate();
      String contractExecDate = bean.getContractExecDate();
      String bidSendingTime = bean.getBidSendingTime();
      String bidDeadlineTime = bean.getBidDeadlineTime();
      String bidOpeningTime = bean.getBidOpeningTime();
      String evaluatedTime = bean.getEvaluatedTime();
      String approvedTime = bean.getApprovedTime();
      String contractTime = bean.getContractTime();
      String contractExecTime = bean.getContractExecTime();
      String techEvalStandard = "";
      String comEvalStandard = "";
      if (bean.getTechEvalStandard().trim().length() > 0) {
        techEvalStandard = bean.getTechEvalStandard();
      } else {
        techEvalStandard = "H? s? ch�o h�ng ph?i ?�p ?ng c? b?n n?i dung v? y�u c?u k? thu?t c?a C�ng Ty DVCKHH nh? quy ??nh trong h? s? m?i ch�o h�ng. C�ng Ty DVCKHH s? ?�nh gi� ??t ho?c kh�ng ??t v? m?t k? thu?t";
      }
      if (bean.getComEvalStandard().trim().length() > 0) {
        comEvalStandard = bean.getComEvalStandard();
      } else {
        comEvalStandard = "C?n c? k?t qu? ?�nh gi� k? thu?t, nh?ng h?ng m?c h�ng ho� tho? m�n y�u c?u v? k? thu?t, ?�p ?ng y�u c?u v? ti?n ?? cung c?p s? ???c ti?p t?c ?�nh gi�, xem x�t l?a ch?n. CTDVCKHH s? ch?n k� h?p ??ng h�ng ho� tho? m�n y�u c?u k? thu?t, ti?n ?? giao h�ng v� c� ??n gi� ch�o c?nh tranh nh?t";
      }
      docs.add(new TenderPlanBean(tenId, tenId, tenderNumber, "", "", packName, "", foundation, "", year, offerType, financial, bidSendingDate, bidDeadline, bidOpeningDate, evaluatedDate, approvedDate, contractDate, contractExecDate, bidSendingTime, bidDeadlineTime, bidOpeningTime, evaluatedTime, approvedTime, contractTime, contractExecTime, techEvalStandard, comEvalStandard, tenId, requestNumber, tenId, wordTemplate, tenId, year, approvedTime, tenId, offerType, offerType, "", currency, orgName, orgName, orgNameUpcase, offerTypeStr, tenId, day, month, year, abbreviate));
      
      context.put("ptscmc", docs);
      
      String text = "";
      TenderEvaluateVendorFormBean beanDetails = null;
      ArrayList arrVendor = null;
      List<TenderEvaluateVendorFormBean> docs1 = new ArrayList();
      try
      {
        arrVendor = tenderDAO.getTenderPlanVendorDetail(bean.getTenId());
      }
      catch (Exception localException2) {}
      if (arrVendor == null) {
        arrVendor = new ArrayList();
      }
      for (int i = 0; i < arrVendor.size(); i++)
      {
        beanDetails = (TenderEvaluateVendorFormBean)arrVendor.get(i);
        
        int stt = i + 1;
        String venName = beanDetails.getVenName();
        String venPhone = beanDetails.getVenPhone();
        String venFax = beanDetails.getVenFax();
        String attn = "";
        String venEmail = beanDetails.getVenEmail();
        String venAddress = beanDetails.getVenAddress();
        
        docs1.add(new TenderEvaluateVendorFormBean(tenId, tenId, tenId, stt, i, bidDeadline, foundation, stt, attn, venName, bidDeadline, stt, venName, venPhone, venFax, venEmail, venAddress, attn, stt, attn));
        context.put("ptscmc1", docs1);
      }
      TenderEvaluateGroupBean beanDetails2 = null;
      ArrayList arrGroup = null;
      List<TenderEvaluateGroupBean> docs2 = new ArrayList();
      try
      {
        arrGroup = tenderDAO.getTenderPlanEvalGroup(bean.getTenId());
      }
      catch (Exception localException3) {}
      if (arrGroup == null) {
        arrGroup = new ArrayList();
      }
      for (int i = 0; i < arrGroup.size(); i++)
      {
        beanDetails2 = (TenderEvaluateGroupBean)arrGroup.get(i);
        
        int stt = i + 1;
        String employee = beanDetails2.getEmployee();
        String position = beanDetails2.getPosition();
        String note = beanDetails2.getNote();
        
        docs2.add(new TenderEvaluateGroupBean(tenId, tenId, employee, position, orgName, note, stt));
        context.put("ptscmc2", docs2);
      }
      OutputStream out = new FileOutputStream(new File(wordDir + "/" + fileName + "-" + userName + type));
      
      report.process(context, out);
      try
      {
        if (!type.equals(".docx")) {
          OutputUtil.sendWordFileToOutput(response, wordDir + "/", fileName + "-" + userName);
        }
      }
      catch (Exception localException4) {}
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (XDocReportException e)
    {
      e.printStackTrace();
    }
  }
  
  private void print3(HttpServletRequest request, HttpServletResponse response, String fileName, String type)
  {
    try
    {
      int tenId = NumberUtil.parseInt(request.getParameter("tenId"), 0);
      String userName = MCUtil.getMemberName(request.getSession());
      if (type.equals("docx")) {
        type = "";
      } else {
        type = ".docx";
      }
      String wordDir = "C:";
      this.arrFiles.add(wordDir + "/" + fileName + "-" + userName + type);
      String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/" + fileName + ".docx");
      
      InputStream in = new FileInputStream(wordTemplate);
      IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
      
      IContext context = report.createContext();
      
      List<TenderPlanBean> docs = new ArrayList();
      
      TenderPlanDAO tenderDAO1 = new TenderPlanDAO();
      TenderPlanBean bean1 = null;
      TenderPlanBean bean2 = null;
      String cerText = "";
      try
      {
        bean1 = tenderDAO1.getTenderPlan(Integer.parseInt(request.getParameter("tenId")));
        cerText = tenderDAO1.getTenderPlanCertificateText(Integer.parseInt(request.getParameter("tenId")));
        bean2 = tenderDAO1.getOrgNameEmpCreatedTenderPlan(Integer.parseInt(request.getParameter("tenId")));
      }
      catch (Exception localException) {}
      Date date = DateUtil.transformerStringEnDate(bean1.getCreatedDate(), "/");
      
      String packName = bean1.getPackName().toUpperCase();
      String year = DateUtil.formatDate(date, "yyyy");
      String tenderNumber = bean1.getTenderNumber();
      String abbreviate = bean2.getAbbreviate();
      
      docs.add(new TenderPlanBean(tenId, tenId, tenderNumber, abbreviate, tenderNumber, packName, cerText, wordDir, type, year, tenId, fileName, abbreviate, wordTemplate, abbreviate, abbreviate, abbreviate, abbreviate, abbreviate, fileName, fileName, fileName, userName, abbreviate, packName, packName, tenderNumber, abbreviate, tenId, tenderNumber, tenId, wordTemplate, tenId, year, userName, tenId, tenId, tenId, cerText, cerText, packName, packName, wordTemplate, cerText, tenId, year, year, year, abbreviate, packName));
      
      context.put("ptscmc", docs);
      
      String text = "";
      TenderPlanDetailBean beanDetail = null;
      ArrayList arrMaterial = null;
      List<TenderPlanDetailBean> docs1 = new ArrayList();
      try
      {
        arrMaterial = tenderDAO1.getTenderPlanDetailsForExport(bean1.getTenId());
      }
      catch (Exception localException1) {}
      if (arrMaterial == null) {
        arrMaterial = new ArrayList();
      }
      for (int i = 0; i < arrMaterial.size(); i++)
      {
        beanDetail = (TenderPlanDetailBean)arrMaterial.get(i);
        
        int stt = i + 1;
        String matName = beanDetail.getMatName();
        String unit = beanDetail.getUnit();
        double quantity = beanDetail.getQuantity();
        
        docs1.add(new TenderPlanDetailBean(tenId, tenId, tenId, unit, quantity, fileName, tenId, matName, matName, quantity, quantity, stt));
        context.put("ptscmc1", docs1);
      }
      OutputStream out = new FileOutputStream(new File(wordDir + "/" + fileName + "-" + userName + type));
      
      report.process(context, out);
      try
      {
        if (!type.equals(".docx")) {
          OutputUtil.sendWordFileToOutput(response, wordDir + "/", fileName + "-" + userName);
        }
      }
      catch (Exception localException2) {}
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (XDocReportException e)
    {
      e.printStackTrace();
    }
  }
  
  private void print4(HttpServletRequest request, HttpServletResponse response, String fileName, String type)
  {
    try
    {
      int tenId = NumberUtil.parseInt(request.getParameter("tenId"), 0);
      String userName = MCUtil.getMemberName(request.getSession());
      if (type.equals("docx")) {
        type = "";
      } else {
        type = ".docx";
      }
      String wordDir = "C:";
      this.arrFiles.add(wordDir + "/" + fileName + "-" + userName + type);
      String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/" + fileName + ".docx");
      
      InputStream in = new FileInputStream(wordTemplate);
      IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
      
      IContext context = report.createContext();
      
      List<TenderPlanBean> docs = new ArrayList();
      
      TenderPlanDAO tenderDAO1 = new TenderPlanDAO();
      TenderPlanBean bean1 = null;
      try
      {
        bean1 = tenderDAO1.getTenderPlan(Integer.parseInt(request.getParameter("tenId")));
      }
      catch (Exception localException) {}
      Date date = DateUtil.transformerStringEnDate(bean1.getCreatedDate(), "/");
      
      String packName = bean1.getPackName();
      String bidDeadline = bean1.getBidDeadline();
      String bidDeadlineTime = bean1.getBidDeadlineTime();
      
      docs.add(new TenderPlanBean(tenId, tenId, userName, packName, wordTemplate, packName, type, wordDir, type, type, tenId, fileName, bidDeadlineTime, bidDeadline, bidDeadlineTime, userName, wordTemplate, packName, wordTemplate, bidDeadlineTime, bidDeadlineTime, bidDeadlineTime, bidDeadline, bidDeadline, packName, bidDeadlineTime, bidDeadline, bidDeadline, tenId, userName, tenId, wordTemplate, tenId, type, bidDeadlineTime, tenId, tenId, tenId, wordDir, wordDir, packName, packName, wordTemplate, userName, tenId, type, type, type, userName));
      
      context.put("ptscmc", docs);
      
      OutputStream out = new FileOutputStream(new File(wordDir + "/" + fileName + "-" + userName + type));
      
      report.process(context, out);
      try
      {
        if (!type.equals(".docx")) {
          OutputUtil.sendWordFileToOutput(response, wordDir + "/", fileName + "-" + userName);
        }
      }
      catch (Exception localException1) {}
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (XDocReportException e)
    {
      e.printStackTrace();
    }
  }
  
  private void print5(HttpServletRequest request, HttpServletResponse response, String fileName, String type)
  {
    try
    {
      int tenId = NumberUtil.parseInt(request.getParameter("tenId"), 0);
      String userName = MCUtil.getMemberName(request.getSession());
      if (type.equals("docx")) {
        type = "";
      } else {
        type = ".docx";
      }
      String wordDir = "C:";
      this.arrFiles.add(wordDir + "/" + fileName + "-" + userName + type);
      String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/" + fileName + ".docx");
      
      InputStream in = new FileInputStream(wordTemplate);
      IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
      
      IContext context = report.createContext();
      
      List<TenderPlanBean> docs = new ArrayList();
      
      TenderPlanDAO tenderDAO1 = new TenderPlanDAO();
      TenderPlanBean bean1 = null;
      TenderPlanBean bean2 = null;
      String cerText = "";
      try
      {
        bean1 = tenderDAO1.getTenderPlan(Integer.parseInt(request.getParameter("tenId")));
        cerText = tenderDAO1.getTenderPlanCertificateText(Integer.parseInt(request.getParameter("tenId")));
        bean2 = tenderDAO1.getOrgNameEmpCreatedTenderPlan(Integer.parseInt(request.getParameter("tenId")));
      }
      catch (Exception localException) {}
      Date date = DateUtil.transformerStringEnDate(bean1.getCreatedDate(), "/");
      
      String packName = bean1.getPackName();
      String packNameUpcase = bean1.getPackName().toUpperCase();
      String deliveryTime = bean1.getDeliveryTime();
      String abbreviate = bean2.getAbbreviate();
      String year = DateUtil.formatDate(date, "yyyy");
      String tenderNumber = bean1.getTenderNumber();
      
      docs.add(new TenderPlanBean(tenId, tenId, tenderNumber, abbreviate, tenderNumber, packName, cerText, wordDir, type, year, tenId, fileName, abbreviate, wordTemplate, abbreviate, abbreviate, abbreviate, abbreviate, abbreviate, deliveryTime, deliveryTime, deliveryTime, deliveryTime, deliveryTime, deliveryTime, deliveryTime, tenderNumber, year, tenId, tenderNumber, tenId, wordTemplate, tenId, year, deliveryTime, tenId, tenId, tenId, cerText, cerText, packName, packName, packNameUpcase, cerText, tenId, year, year, year, abbreviate, packNameUpcase));
      
      context.put("ptscmc", docs);
      
      String text = "";
      TenderPlanDetailBean beanDetail = null;
      ArrayList arrMaterial = null;
      List<TenderPlanDetailBean> docs1 = new ArrayList();
      try
      {
        arrMaterial = tenderDAO1.getTenderPlanDetailsForExport(bean1.getTenId());
      }
      catch (Exception localException1) {}
      if (arrMaterial == null) {
        arrMaterial = new ArrayList();
      }
      for (int i = 0; i < arrMaterial.size(); i++)
      {
        beanDetail = (TenderPlanDetailBean)arrMaterial.get(i);
        
        int stt = i + 1;
        String matName = beanDetail.getMatName();
        String unit = beanDetail.getUnit();
        double quantity = beanDetail.getQuantity();
        
        docs1.add(new TenderPlanDetailBean(tenId, tenId, tenId, unit, quantity, fileName, tenId, matName, matName, quantity, quantity, stt));
        context.put("ptscmc1", docs1);
      }
      OutputStream out = new FileOutputStream(new File(wordDir + "/" + fileName + "-" + userName + type));
      
      report.process(context, out);
      try
      {
        if (!type.equals(".docx")) {
          OutputUtil.sendWordFileToOutput(response, wordDir + "/", fileName + "-" + userName);
        }
      }
      catch (Exception localException2) {}
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (XDocReportException e)
    {
      e.printStackTrace();
    }
  }
}
