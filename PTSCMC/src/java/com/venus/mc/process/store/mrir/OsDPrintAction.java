package com.venus.mc.process.store.mrir;

import com.venus.core.util.NumberUtil;
import com.venus.mc.core.BaseAction;
import com.venus.mc.workReport.OsDReport;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OsDPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int osdId = NumberUtil.parseInt(request.getParameter("osdId"), 0);
    if (osdId > 0)
    {
      String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.03.02.KH_BCThuaThieuSaiKhac_xml.xml");
      String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.03.02.KH_BCThuaThieuSaiKhac.xml");
      String result = "BM.03.02.KH_BCThuaThieuSaiKhac.doc";
      OsDReport report = new OsDReport(xmlTemplate, wordTemplate, result);
      try
      {
        report.executeParse(request, response, Integer.valueOf(osdId));
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
