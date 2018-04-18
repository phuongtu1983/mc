package com.venus.mc.deliveryrequest;

import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.PermissionUtil;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import java.io.BufferedInputStream;
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
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DeliveryRequestPrintAction
  extends BaseAction
{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    try
    {
      int drId = NumberUtil.parseInt(request.getParameter("drId"), 0);
      String wordDir = "C:";String fileName = "De nghi giao hang";
      String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/" + fileName + ".docx");
      
      InputStream in = new FileInputStream(wordTemplate);
      IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
      
      IContext context = report.createContext();
      
      List<DeliveryRequestFormBean> docs = new ArrayList();
      
      ContractDAO contractDAO = new ContractDAO();
      DeliveryRequestFormBean bean = null;
      ContractBean bean2 = null;
      bean = contractDAO.getDeliveryRequestPrint(drId);
      bean2 = contractDAO.getOrgNameEmpCreatedContract(drId);
      
      Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
      Date date1 = DateUtil.transformerStringEnDate(bean.getSignDate(), "/");
      String effectedDate = DateUtil.formatDate(DateUtil.transformerStringEnDate(bean.getSignDate(), "/"), "dd/MM/yyyy");
      String day = DateUtil.formatDate(date1, "dd");
      String month = DateUtil.formatDate(date1, "MM");
      String year = DateUtil.formatDate(date1, "yyyy");
      String abbreviate = bean2.getAbbreviate();
      String note = bean.getNote();
      String orgName = bean2.getOrgName().toUpperCase();
      String deliveryNumber = bean.getDeliveryNumber();
      String conNumber = bean.getConNumber();
      String conDate = "ng�y " + DateUtil.formatDate(date, "dd") + " th�ng " + DateUtil.formatDate(date, "MM") + " n?m " + DateUtil.formatDate(date, "yyyy");
      String vendorName = bean.getVendorName();
      String phone = bean.getPhone() + " - Fax: " + bean.getFax();
      String address = bean.getAddress();
      String followEmpName = bean.getVendorName().toUpperCase();
      String currency = bean.getCurrency();
      double totalNotVAT = bean.getTotalNotVAT();
      double sumVAT = bean.getSumVAT();
      double total = bean.getTotal();
      String delivery = bean.getDelivery();
      String certificate = bean.getCertificate();
      String textTotal = NumberUtil.textMoney(Double.valueOf(bean.getTotal()), bean.getCurrency());
      
      String text = "";
      DeliveryRequestDetailFormBean beanDetails = null;
      ArrayList arrDAO = null;
      List<DeliveryRequestDetailFormBean> docs1 = new ArrayList();
      try
      {
        arrDAO = contractDAO.getDeliveryRequestDetails(drId);
      }
      catch (Exception localException) {}
      if (arrDAO == null) {
        arrDAO = new ArrayList();
      }
      for (int i = 0; i < arrDAO.size(); i++)
      {
        beanDetails = (DeliveryRequestDetailFormBean)arrDAO.get(i);
        
        int stt = i + 1;
        double quantity = beanDetails.getQuantity();
        double price = beanDetails.getPrice();
        double totals = quantity * price;
        String matName = beanDetails.getMatName();
        String requestNumbers = beanDetails.getRequestNumbers();
        String unit = beanDetails.getUnit();
        if (!PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE))
        {
          price = 0.0D;
          total = 0.0D;
        }
        docs1.add(new DeliveryRequestDetailFormBean(stt, matName, unit, quantity, price, totals, requestNumbers));
        context.put("ptscmc1", docs1);
      }
      docs.add(new DeliveryRequestFormBean(phone, address, followEmpName, day, month, year, effectedDate, abbreviate, note, orgName, deliveryNumber, conNumber, vendorName, currency, totalNotVAT, sumVAT, total, delivery, certificate, textTotal, conDate, deliveryNumber));
      context.put("ptscmc", docs);
      
      OutputStream out = new FileOutputStream(new File(wordDir + "/" + fileName));
      report.process(context, out);
      ServletOutputStream stream = null;
      BufferedInputStream buf = null;
      try
      {
        stream = response.getOutputStream();
        File doc = new File(wordDir + "/" + fileName);
        response.setContentType("application/msword");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentLength((int)doc.length());
        FileInputStream input = new FileInputStream(doc);
        buf = new BufferedInputStream(input);
        int readBytes = 0;
        while ((readBytes = buf.read()) != -1) {
          stream.write(readBytes);
        }
      }
      catch (IOException ioe)
      {
        throw new ServletException(ioe.getMessage());
      }
      finally
      {
        if (stream != null) {
          stream.close();
        }
        if (buf != null) {
          buf.close();
        }
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (XDocReportException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
