package com.venus.mc.process.purchasing.deliverynotice;

import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.DnBean;
import com.venus.mc.bean.DnDetailBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.dao.DnDAO;
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

public class DnPrintAction
  extends BaseAction
{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    try
    {
      int dnId = NumberUtil.parseInt(request.getParameter("dnId"), 0);
      String wordDir = "C:";String fileName = "Yeu cau kiem tra tiep nhan VTTB";
      String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/" + fileName + ".docx");
      
      InputStream in = new FileInputStream(wordTemplate);
      IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
      
      IContext context = report.createContext();
      
      List<DnBean> docs = new ArrayList();
      
      DnDAO dnDAO = new DnDAO();
      DnBean bean = null;
      DnBean bean2 = null;
      bean = dnDAO.getDn(dnId, DnBean.KIND1);
      bean2 = dnDAO.getOrgNameEmpCreatedContract(dnId);
      
      Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
      Date date1 = DateUtil.transformerStringEnDate(bean.getExpectedDate(), "/");
      String dnNumber = bean.getDnNumber();
      String expectedDate = DateUtil.formatDate(date1, "dd") + "/" + DateUtil.formatDate(date1, "MM") + "/" + DateUtil.formatDate(date1, "yyyy");
      String day = DateUtil.formatDate(date, "dd");
      String month = DateUtil.formatDate(date, "MM");
      String year = DateUtil.formatDate(date, "yyyy");
      String abbreviate = bean2.getAbbreviate();
      
      String orgName = bean2.getOrgName().toUpperCase();
      String proName = "D? �N " + bean.getProName().toUpperCase();
      String deliveryPlace = bean.getDeliveryPlace();
      String deliveryPresenter = bean.getDeliveryPresenter();
      
      String text = "";
      DnDetailBean beanDetails = null;
      ArrayList arrDAO = null;
      List<DnDetailBean> docs1 = new ArrayList();
      try
      {
        arrDAO = dnDAO.getDnDetails(dnId);
      }
      catch (Exception localException) {}
      if (arrDAO == null) {
        arrDAO = new ArrayList();
      }
      for (int i = 0; i < arrDAO.size(); i++)
      {
        beanDetails = (DnDetailBean)arrDAO.get(i);
        
        int stt = i + 1;
        
        String matName = beanDetails.getMatName();
        String matCode = beanDetails.getMatCode();
        if (matCode == null) {
          matCode = "";
        } else {
          matCode = beanDetails.getMatCode();
        }
        String unit = beanDetails.getUnit();
        double quantity = beanDetails.getQuantity();
        String note = beanDetails.getNote();
        if (note == null) {
          note = "";
        } else {
          note = beanDetails.getNote();
        }
        String requestNumber = beanDetails.getRequestNumber();
        if (requestNumber == null) {
          requestNumber = "";
        } else {
          requestNumber = beanDetails.getRequestNumber();
        }
        docs1.add(new DnDetailBean(matName, matCode, note + requestNumber, unit, quantity, stt));
        context.put("ptscmc1", docs1);
      }
      docs.add(new DnBean(dnId, expectedDate, dnId, dnNumber, dnId, dnId, expectedDate, expectedDate, deliveryPresenter, deliveryPlace, dnNumber, dnId, dnId, orgName, proName, dnId, dnId, dnId, dnId, abbreviate, dnId, dnId, abbreviate, day, month, year));
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
