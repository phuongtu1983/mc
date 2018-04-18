package com.venus.mc.process.store.miv;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MivBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.dao.MivDAO;
import com.venus.mc.workReport.MivReport;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MivPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    if (!GenericValidator.isBlankOrNull(request.getParameter("mivId")))
    {
      int mivId = NumberUtil.parseInt(request.getParameter("mivId"), 0);
      int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
      MivFormBean bean = null;
      MivDAO mivDAO = new MivDAO();
      String xmlTemplate = "";
      String wordTemplate = "";
      String result = "";
      if (kind == MivBean.KIND_PARTNER)
      {
        xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.13.02.KH (Phieu xuat kho HH don vi ngoai)_xml.xml");
        wordTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.13.02.KH (Phieu xuat kho HH don vi ngoai).xml");
        result = "BM.13.02.KH (Phieu xuat kho HH don vi ngoai).doc";
        try
        {
          bean = mivDAO.getEMivToPrint(mivId);
        }
        catch (Exception ex)
        {
          ex.printStackTrace();
        }
      }
      else if (kind == MivBean.KIND_COMPANY)
      {
        xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.06.02.KH (Phieu nhap xuat kho kiem van chuyen noi bo)_xml.xml");
        wordTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.06.02.KH (Phieu nhap xuat kho kiem van chuyen noi bo).xml");
        result = "BM.06.02.KH (Phieu nhap xuat kho kiem van chuyen noi bo).doc";
        try
        {
          bean = mivDAO.getMivToPrint(mivId);
        }
        catch (Exception ex)
        {
          ex.printStackTrace();
        }
      }
      if (bean == null) {
        bean = new MivFormBean();
      }
      bean.setMivKind(kind);
      MivReport report = new MivReport(xmlTemplate, wordTemplate, result);
      try
      {
        report.executeParse(request, response, bean);
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
