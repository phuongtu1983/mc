package com.venus.mc.payment;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.BaseAction;
import com.venus.mc.workReport.PaymentReport;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PaymentPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    if (!GenericValidator.isBlankOrNull(request.getParameter("payId")))
    {
      String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/de_xuat_thanh_toan_xml.xml");
      String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/de_xuat_thanh_toan.xml");
      String result = "De xuat thanh toan.doc";
      PaymentReport report = new PaymentReport(xmlTemplate, wordTemplate, result);
      try
      {
        report.setRequest(request);
        report.executeParse(request, response, Integer.valueOf(Integer.parseInt(request.getParameter("payId"))));
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
