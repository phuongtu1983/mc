package com.venus.mc.contract.order;

import com.venus.core.util.DateUtil;
import com.venus.core.util.FileUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.ContractPrintBean;
import com.venus.mc.contract.ContractDetailFormBean;
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

public class OrderPrintAction
  extends BaseAction
{
  private ArrayList arrFiles = null;
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    this.arrFiles = new ArrayList();
    String type = "docx";
    String fileName = "Don hang mua hang hoa";
    String userName = MCUtil.getMemberName(request.getSession());
    try
    {
      int conId = Integer.parseInt(request.getParameter("conId"));
      
      print(request, response, fileName, type, conId, userName);
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
      
      String orderNumber = bean.getContractNumber();
      String packName = bean.getPackName();
      String packNameUpcase = "";
      if (bean.getPackName() == null) {
        packName = bean.getNote();
      }
      packNameUpcase = packName.toUpperCase();
      String orgNameUpper = bean2.getOrgName().toUpperCase();
      String orgName = bean2.getOrgName();
      String abbreviate = bean2.getAbbreviate();
      String vendorName = bean.getVendorName();
      String signDate = "ng�y " + DateUtil.formatDate(date, "dd") + " th�ng " + DateUtil.formatDate(date, "MM") + " n?m " + DateUtil.formatDate(date, "yyyy");
      String effectedDate = "ng�y " + DateUtil.formatDate(date2, "dd") + " th�ng " + DateUtil.formatDate(date2, "MM") + " n?m " + DateUtil.formatDate(date2, "yyyy");
      String effectDate = DateUtil.formatDate(date2, "dd") + "/" + DateUtil.formatDate(date2, "MM") + "/" + DateUtil.formatDate(date2, "yyyy");
      String vendorNameUpcase = bean.getVendorName().toUpperCase();
      String address = bean.getAddress();
      String phone = bean.getPhone();
      String fax = bean.getFax();
      String license = bean.getLicense();
      String field = bean.getField();
      String presenter = bean.getPresenter();
      String delivery = bean.getDelivery();
      String certificate = bean.getCertificate();
      String currency = bean.getCurrency();
      double total = bean.getTotal();
      double sumVAT = bean.getSumVAT();
      double totalNotVAT = bean.getTotalNotVAT();
      String textTotal = NumberUtil.textMoney(Double.valueOf(bean.getTotal()), bean.getCurrency());
      
      docs.add(new ContractPrintBean(kind, fax, orderNumber, effectedDate, effectedDate, effectDate, total, total, total, total, currency, currency, delivery, delivery, presenter, phone, fileName, orderNumber, vendorName, address, phone, fax, presenter, presenter, field, license, totalNotVAT, sumVAT, totalNotVAT, totalNotVAT, sumVAT, sumVAT, total, license, orderNumber, id, signDate, phone, presenter, packName, delivery, vendorName, certificate, delivery, effectDate, signDate, vendorName, field, license, orderNumber, vendorName, field, license, orderNumber, delivery, delivery, orderNumber, fax, sumVAT, total, orderNumber, orgName, id, fax, phone, year, vendorNameUpcase, textTotal, orderNumber, effectDate, effectDate, orgNameUpper, orgName, abbreviate, orgName, effectDate, packNameUpcase, packName));
      
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
        String matName = beanDetails.getNameTemp();
        String requestNumbers = beanDetails.getRequestNumber();
        String unit = beanDetails.getUnit();
        if (!PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE))
        {
          price = 0.0D;
          totals = 0.0D;
        }
        docs1.add(new ContractDetailFormBean(stt, kind, stt, unit, quantity, price, total, totalNotVAT, currency, total, delivery, matName, matName, kind, packName, presenter, delivery, presenter, delivery, unit, requestNumbers, requestNumbers, stt, quantity, i, matName, i, stt, orderNumber, matName, matName, quantity, price, unit, total, totals, totalNotVAT, quantity, stt, fax, unit, totals));
        
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
