package com.venus.mc.request;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.BaseAction;
import com.venus.mc.workReport.RequestReport;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RequestPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    if (!GenericValidator.isBlankOrNull(request.getParameter("reqId")))
    {
      String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.01.02.TM-Phieu de xuat_xml.xml");
      String wordTemplate = "/templates/BM.01.02.TM-Phieu de xuat_new.xml";
      if ((!GenericValidator.isBlankOrNull(request.getParameter("kind"))) && 
        (request.getParameter("kind").equals("CCDC"))) {}
      wordTemplate = request.getSession().getServletContext().getRealPath(wordTemplate);
      String result = "BM.01.02.TM-Phieu de xuat.doc";
      RequestReport report = new RequestReport(xmlTemplate, wordTemplate, result);
      try
      {
        report.executeParse(request, response, Integer.valueOf(Integer.parseInt(request.getParameter("reqId"))));
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
