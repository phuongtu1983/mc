package com.venus.mc.process.store.msv;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.BaseAction;
import com.venus.mc.process.store.msv.report.MsvFormReport;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MsvFormPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    if (!GenericValidator.isBlankOrNull(request.getParameter("msvId")))
    {
      int msvId = NumberUtil.parseInt(request.getParameter("msvId"), 0);
      String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.04.02.KH (Phieu nhap kho-MSV)_xml.xml");
      String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.04.02.KH (Phieu nhap kho-MSV).xml");
      String result = "BM.04.02.KH (Phieu nhap kho-MSV).doc";
      MsvFormReport report = new MsvFormReport(xmlTemplate, wordTemplate, result);
      try
      {
        report.executeParse(request, response, Integer.valueOf(msvId));
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
