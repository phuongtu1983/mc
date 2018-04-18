package com.venus.mc.contract;

import com.venus.core.util.DateUtil;
import com.venus.core.util.FileUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.ContractPrintBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
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

public class ContractPrintAction
  extends BaseAction
{
  private ArrayList arrFiles = null;
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    this.arrFiles = new ArrayList();
    String type = "docx";
    String fileName = "Hop dong mua hang hoa";
    String fileName1 = "Hop dong mua hang hoa nn";
    String userName = MCUtil.getMemberName(request.getSession());
    try
    {
      ContractDAO conDAO = new ContractDAO();
      int conId = Integer.parseInt(request.getParameter("conId"));
      int kind = 0;
      try
      {
        kind = conDAO.getVendorKind(conId);
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
      if (kind == 1) {
        print(request, response, fileName, type, conId, userName);
      } else {
        print(request, response, fileName1, type, conId, userName);
      }
    }
    catch (Exception localException1) {}
    if (type.equals("zip"))
    {
      String outputFileName = fileName + "-" + userName + ".zip";
      FileUtil.zipFile(outputFileName, this.arrFiles);
      OutputUtil.sendZipFileToOutput(response, outputFileName);
    }
    return null;
  }
  
  private void print(HttpServletRequest request, HttpServletResponse response, String fileName, String type, int id, String userName)
  {
    try
    {
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
      
      List<ContractPrintBean> docs = new ArrayList();
      
      ContractBean bean = null;
      ContractBean bean2 = null;
      ContractDAO contractDAO = new ContractDAO();
      try
      {
        bean = contractDAO.getContract(id);
        bean2 = contractDAO.getOrgNameEmpCreatedContract(id);
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
      if (bean == null) {
        bean = new ContractBean();
      }
      int kind = 0;
      
      Date date = DateUtil.transformerStringEnDate(bean.getSignDate(), "/");
      Date date2 = DateUtil.transformerStringEnDate(bean.getEffectedDate(), "/");
      String year = DateUtil.formatDate(date, "yyyy");
      String placeDelivery = "";
      String orgNameUpper = bean2.getOrgName().toUpperCase();
      String orgName = bean2.getOrgName();
      String abbreviate = bean2.getAbbreviate();
      String contractNumber = bean.getContractNumber();
      String packName = bean.getPackName();
      String packNameUpcase = "";
      packNameUpcase = packName.toUpperCase();
      String vendorName = bean.getVendorName();
      String signDate = "ng�y " + DateUtil.formatDate(date, "dd") + " th�ng " + DateUtil.formatDate(date, "MM") + " n?m " + DateUtil.formatDate(date, "yyyy");
      String license1 = DateUtil.formatDate(date, "dd") + "/" + DateUtil.formatDate(date, "MM") + "/" + DateUtil.formatDate(date, "yyyy");
      String effectedDate = "ng�y " + DateUtil.formatDate(date2, "dd") + " th�ng " + DateUtil.formatDate(date2, "MM") + " n?m " + DateUtil.formatDate(date2, "yyyy");
      String effectDate = DateUtil.formatDate(date2, "dd") + "/" + DateUtil.formatDate(date2, "MM") + "/" + DateUtil.formatDate(date2, "yyyy");
      
      String field2 = DateUtil.formatDate(date, "dd") + "/" + DateUtil.formatDate(date, "MM") + "/" + DateUtil.formatDate(date, "yyyy");
      String vendorNameUpcase = bean.getVendorName().toUpperCase();
      String address = bean.getAddress();
      String phone = bean.getPhone();
      String fax = bean.getFax();
      String license = bean.getLicense();
      String field = bean.getField();
      String presenter = bean.getPresenter() + "- Ch?c v?: " + bean.getPosPresenter();
      String delivery = bean.getDelivery();
      String certificate = bean.getCertificate();
      String currency = bean.getCurrency();
      
      double otherFee = bean.getOtherFee() + bean.getTransport();
      String payment = "0";
      if (otherFee == 0.0D) {
        payment = "?� bao g?m";
      } else {
        payment = NumberUtil.formatMoneyDefault(otherFee, currency) + "";
      }
      double sumVAT = NumberUtil.parseDouble(NumberUtil.reformatMoneyDefault(bean.getSumVAT(), bean.getCurrency()), 0.0D);
      double totalNotVAT = bean.getTotalNotVAT();
      totalNotVAT = NumberUtil.parseDouble(NumberUtil.reformatMoneyDefault(totalNotVAT, bean.getCurrency()), 0.0D);
      double total = bean.getTotalNotVAT() + bean.getSumVAT() + bean.getOtherFee() + bean.getTransport();
      total = NumberUtil.parseDouble(NumberUtil.reformatMoneyDefault(total, bean.getCurrency()), 0.0D);
      String textTotal = "";
      if (currency == "VND") {
        textTotal = NumberUtil.textMoney(Double.valueOf(total), currency);
      } else {
        textTotal = NumberUtil.textMoney(Double.valueOf(total), currency);
      }
      docs.add(new ContractPrintBean(kind, fax, contractNumber, effectedDate, effectedDate, effectDate, total, total, otherFee, total, currency, currency, delivery, delivery, payment, phone, fileName, vendorName, vendorName, address, phone, fax, presenter, presenter, field, license, totalNotVAT, sumVAT, totalNotVAT, totalNotVAT, sumVAT, sumVAT, total, license, vendorName, id, signDate, phone, presenter, packName, delivery, contractNumber, certificate, delivery, effectDate, signDate, vendorName, field2, license, contractNumber, vendorName, field, license1, contractNumber, delivery, delivery, contractNumber, fax, sumVAT, total, contractNumber, packName, id, fax, phone, year, vendorNameUpcase, textTotal, contractNumber, effectDate, effectDate, orgNameUpper, orgName, abbreviate, orgName, effectDate, packNameUpcase, packName));
      
      context.put("ptscmc", docs);
      
      String text = "";
      ContractDetailFormBean beanDetails = null;
      ArrayList arrDAO = null;
      List<ContractDetailFormBean> docs1 = new ArrayList();
      try
      {
        arrDAO = contractDAO.getContractDetails(id);
      }
      catch (Exception localException1) {}
      if (arrDAO == null) {
        arrDAO = new ArrayList();
      }
      for (int i = 0; i < arrDAO.size(); i++)
      {
        beanDetails = (ContractDetailFormBean)arrDAO.get(i);
        
        int stt = i + 1;
        double quantity = beanDetails.getQuantity();
        double price = beanDetails.getPrice();
        double totals = quantity * price;
        String matName = beanDetails.getMatName();
        String requestNumbers = beanDetails.getRequestNumber();
        String unit = beanDetails.getUnit();
        if (!PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE))
        {
          price = 0.0D;
          totals = 0.0D;
        }
        docs1.add(new ContractDetailFormBean(stt, kind, stt, unit, quantity, price, total, totalNotVAT, currency, total, delivery, matName, matName, kind, packName, presenter, delivery, presenter, delivery, unit, requestNumbers, requestNumbers, stt, quantity, i, matName, i, stt, contractNumber, matName, matName, quantity, price, unit, total, totals, totalNotVAT, quantity, stt, fax, unit, totals));
        
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
