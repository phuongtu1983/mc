package com.venus.mc.contract.principle;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.BaseAction;
import com.venus.mc.workReport.PrincipleReport;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PrinciplePrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String id = request.getParameter("conId");
    String doc = "Hop dong nguyen tac";
    if (!GenericValidator.isBlankOrNull(id))
    {
      String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/" + doc + "_xml.xml");
      String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/" + doc + ".xml");
      String result = doc + ".doc";
      PrincipleReport report = new PrincipleReport(xmlTemplate, wordTemplate, result);
      try
      {
        report.setRequest(request);
        report.executeParse(request, response, Integer.valueOf(Integer.parseInt(id)));
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
