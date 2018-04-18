package com.venus.mc.process.store.report;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ProjectBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.ProjectDAO;
import com.venus.mc.dao.ReportDAO;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MCMaterialBalanceReportPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int proId = NumberUtil.parseInt(request.getParameter("mcReportProject"), 0);
    int stoId = NumberUtil.parseInt(request.getParameter("mcStore"), 0);
    int matKind = NumberUtil.parseInt(request.getParameter("mcReportMaterialKind"), 0);
    String msvFromDate = request.getParameter("msvFromDate");
    String msvEndDate = request.getParameter("msvEndDate");
    String rfmFromDate = request.getParameter("rfmFromDate");
    String rfmEndDate = request.getParameter("rfmEndDate");
    String mivFromDate = request.getParameter("mivFromDate");
    String mivEndDate = request.getParameter("mivEndDate");
    
    ArrayList list = null;
    ProjectDAO proDAO = new ProjectDAO();
    ProjectBean proBean = null;
    try
    {
      proBean = proDAO.getProject(proId);
    }
    catch (Exception localException1) {}
    if (proBean == null) {
      proBean = new ProjectBean();
    }
    ReportDAO dao = new ReportDAO();
    try
    {
      list = dao.getMCMaterialBalanceReport(proId, stoId, matKind, msvFromDate, msvEndDate, rfmFromDate, rfmEndDate, mivFromDate, mivEndDate);
    }
    catch (Exception localException2) {}
    if (list == null) {
      list = new ArrayList();
    }
    String templateFileName = request.getSession().getServletContext().getRealPath("/templates/QuyetToanVTDuAn.xls");
    try
    {
      Map beans = new HashMap();
      beans.put("vattu", list);
      ExcelExport exporter = new ExcelExport();
      exporter.setBeans(beans);
      long milis = System.currentTimeMillis();
      exporter.export(request, response, templateFileName, "QuyetToanVTDuAn.xls");
      milis = System.currentTimeMillis() - milis;
      System.out.println("QuyetToanVTDuAn.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:MCMaterialBalanceReportPrint:print-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
