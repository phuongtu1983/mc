package com.venus.mc.process.store.rfm;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.BaseAction;
import com.venus.mc.workReport.ErfmReport;
import com.venus.mc.workReport.RfmReport;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RfmPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    String fileName = "";
    if (kind == 0) {
      fileName = "BM.05.02.KH-YCXK";
    } else {
      fileName = "BM.12.02.KH -YCXK Don vi ngoai";
    }
    if (!GenericValidator.isBlankOrNull(request.getParameter("rfmId")))
    {
      String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/" + fileName + "_xml.xml");
      String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/" + fileName + ".xml");
      String result = fileName + ".doc";
      RfmReport report = new RfmReport(xmlTemplate, wordTemplate, result);
      ErfmReport ereport = new ErfmReport(xmlTemplate, wordTemplate, result);
      try
      {
        if (kind == 0) {
          report.executeParse(request, response, Integer.valueOf(Integer.parseInt(request.getParameter("rfmId"))));
        } else {
          ereport.executeParse(request, response, Integer.valueOf(Integer.parseInt(request.getParameter("rfmId"))));
        }
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
