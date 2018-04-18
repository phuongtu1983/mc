package com.venus.mc.contract.appendix;

import com.venus.core.util.DateUtil;
import com.venus.core.util.FileUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.ContractPrintBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.contract.ContractDetailFormBean;
import com.venus.mc.contract.ContractFormBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.dao.ContractDAO;
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

public class ContractAppendixRequestPrintAction
  extends BaseAction
{
  private ArrayList arrFiles = null;
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    this.arrFiles = new ArrayList();
    String type = "docx";
    String fileName = "De xuat ky phu luc hop dong";
    String fileName1 = "De xuat ky phu luc hop dong (TA)";
    String userName = MCUtil.getMemberName(request.getSession());
    try
    {
      ContractDAO conDAO = new ContractDAO();
      int id = Integer.parseInt(request.getParameter("conId"));
      int kind = 0;
      try
      {
        kind = conDAO.getVendorKind(id);
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
      if (kind == VendorBean.KIND_NATIONAL) {
        print(request, response, fileName, type, id, userName);
      } else {
        print(request, response, fileName, type, id, userName);
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
      ContractBean parentContract = null;
      ContractDAO contractDAO = new ContractDAO();
      String orgNames = "";
      String createdOrgs = "";
      try
      {
        bean = contractDAO.getContractAppendix(id);
        bean2 = contractDAO.getOrgNameEmpCreatedContract(id);
        parentContract = contractDAO.getContract(bean.getParentId());
        orgNames = contractDAO.getOrgNames(id);
        createdOrgs = contractDAO.getCreatedOrgs(id);
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
      if (bean == null) {
        bean = new ContractBean();
      }
      int kind = 0;
      
      Date date = DateUtil.transformerStringEnDate(parentContract.getSignDate(), "/");
      Date date1 = DateUtil.transformerStringEnDate(bean.getAppendixDate(), "/");
      Date date2 = DateUtil.transformerStringEnDate(parentContract.getEffectedDate(), "/");
      String day = DateUtil.formatDate(date1, "dd");
      String month = DateUtil.formatDate(date1, "MM");
      String year = DateUtil.formatDate(date1, "yyyy");
      String placeDelivery = "";
      String orgNameUpper = bean2.getOrgName().toUpperCase();
      String orgName = bean2.getOrgName();
      String abbreviate = bean2.getAbbreviate();
      String contractNumber = bean.getContractNumber();
      String appendixNumber = bean.getAppendixNumber().replaceFirst("PL", "");
      String packName = StringUtil.decodeString(bean.getPackName());
      String packNameUpcase = "";
      packNameUpcase = packName.toUpperCase();
      String note = bean.getNote();
      String vendorName = bean.getVendorName();
      String signDate = "ng�y " + DateUtil.formatDate(date, "dd") + " th�ng " + DateUtil.formatDate(date, "MM") + " n?m " + DateUtil.formatDate(date, "yyyy");
      String license2 = DateUtil.formatDate(date, "dd") + "/" + DateUtil.formatDate(date, "MM") + "/" + DateUtil.formatDate(date, "yyyy");
      String effectedDate = "ng�y " + DateUtil.formatDate(date2, "dd") + " th�ng " + DateUtil.formatDate(date2, "MM") + " n?m " + DateUtil.formatDate(date2, "yyyy");
      String effectDate = DateUtil.formatDate(date2, "dd") + "/" + DateUtil.formatDate(date2, "MM") + "/" + DateUtil.formatDate(date2, "yyyy");
      String vendorNameUpcase = bean.getVendorName().toUpperCase();
      String address = bean.getAddress();
      String phone = bean.getPhone();
      String fax = bean.getFax();
      String license = bean.getLicense();
      String field = bean.getField();
      String presenter = bean.getPresenter() + " - Ch?c v?: " + bean.getPosPresenter();
      String delivery = bean.getDelivery();
      String certificate = bean.getCertificate();
      String currency = bean.getCurrency();
      double total = bean.getTotal() + bean.getOtherFee() + bean.getTransport();
      double sumVAT = bean.getSumVAT();
      double totalNotVAT = bean.getTotalNotVAT();
      String textTotal = NumberUtil.textMoney(Double.valueOf(total), parentContract.getCurrency());
      String requestNumber = "";
      try
      {
        ArrayList details = contractDAO.getContractDetails(bean.getConId());
        ContractDetailFormBean detail = null;
        for (int i = 0; i < details.size(); i++)
        {
          detail = (ContractDetailFormBean)details.get(i);
          requestNumber = requestNumber + ";" + detail.getRequestNumber();
        }
        if (!StringUtil.isBlankOrNull(requestNumber)) {
          requestNumber = requestNumber.substring(1);
        }
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
      docs.add(new ContractPrintBean(kind, fax, contractNumber, effectedDate, effectedDate, effectDate, total, total, total, total, currency, currency, delivery, delivery, presenter, note, fileName, vendorName, vendorName, address, phone, fax, presenter, presenter, field, license, totalNotVAT, sumVAT, totalNotVAT, totalNotVAT, sumVAT, sumVAT, total, month, appendixNumber, id, signDate, note, presenter, packName, delivery, appendixNumber, certificate, delivery, appendixNumber, signDate, vendorName, field, license2, contractNumber, vendorName, field, license, contractNumber, delivery, delivery, appendixNumber, note, sumVAT, total, requestNumber, packName, id, day, month, year, vendorNameUpcase, textTotal, contractNumber, effectDate, effectDate, orgNameUpper, orgName, abbreviate, orgNames, effectDate, packNameUpcase, packName, createdOrgs));
      
      context.put("ptscmc", docs);
      
      String text = "";
      ContractFormBean beanDetails = null;
      ArrayList arrDAO = null;
      List<ContractFormBean> docs1 = new ArrayList();
      try
      {
        arrDAO = contractDAO.getAppendixsOfContract(bean.getParentId(), id);
      }
      catch (Exception localException1) {}
      if (arrDAO == null) {
        arrDAO = new ArrayList();
      }
      double sum = parentContract.getTotal();
      String appNumbers = "1";
      String PL = " v� c�c";
      int stt = 1;
      
      docs1.add(new ContractFormBean(stt, contractNumber + " ng�y " + license2, parentContract.getTotal() + parentContract.getOtherFee() + parentContract.getTransport()));
      context.put("ptscmc1", docs1);
      for (int i = 0; i < arrDAO.size(); i++)
      {
        beanDetails = (ContractFormBean)arrDAO.get(i);
        stt++;
        sum += beanDetails.getTotal();
        if (i > 0) {
          PL = PL + ",";
        }
        PL = PL + " PL s? " + beanDetails.getContractNumber().split(" ")[1] + " ";
        appNumbers = appNumbers + " + " + stt;
        docs1.add(new ContractFormBean(stt, "Ph? l?c s? " + beanDetails.getContractNumber().replaceFirst("PL", ""), beanDetails.getTotal() + beanDetails.getTransport() + beanDetails.getOtherFee()));
        context.put("ptscmc1", docs1);
      }
      PL = PL.substring(0, PL.length() - 1);
      stt++;
      docs1.add(new ContractFormBean(stt, "T?ng gi� tr? c?a H? " + contractNumber + " ng�y " + license2 + PL + " (" + stt + " = " + appNumbers + ")", sum));
      context.put("ptscmc1", docs1);
      
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
