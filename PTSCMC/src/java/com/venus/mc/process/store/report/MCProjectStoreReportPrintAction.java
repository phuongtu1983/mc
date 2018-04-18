package com.venus.mc.process.store.report;

import com.venus.core.util.DateUtil;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ProjectBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.ProjectDAO;
import com.venus.mc.dao.ReportDAO;
import java.io.PrintStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MCProjectStoreReportPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int proId = NumberUtil.parseInt(request.getParameter("mcReportProject"), 0);
    int stoId = NumberUtil.parseInt(request.getParameter("mcStore"), 0);
    int orgList = NumberUtil.parseInt(request.getParameter("mcReportOrgList"), 0);
    String msvFromDate = request.getParameter("msvFromDate");
    String msvEndDate = request.getParameter("msvEndDate");
    String rfmFromDate = request.getParameter("rfmFromDate");
    String rfmEndDate = request.getParameter("rfmEndDate");
    String mivFromDate = request.getParameter("mivFromDate");
    String mivEndDate = request.getParameter("mivEndDate");
    
    String nameVn = request.getParameter("nameVn");
    String code = request.getParameter("code");
    try
    {
      nameVn = URLDecoder.decode(nameVn, "UTF8");
    }
    catch (Exception localException1) {}
    ArrayList list = null;
    ReportDAO dao = new ReportDAO();
    try
    {
      list = dao.getMCProjectStoreReport(proId, stoId, orgList, nameVn, code, msvFromDate, msvEndDate, rfmFromDate, rfmEndDate, mivFromDate, mivEndDate);
    }
    catch (Exception localException2) {}
    if (list == null) {
      list = new ArrayList();
    }
    String templateFileName = request.getSession().getServletContext().getRealPath("/templates/Material_Stock_Report.xls");
    try
    {
      Map beans = new HashMap();
      
      Date date = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "/");
      if (proId > 0)
      {
        ProjectDAO proDAO = new ProjectDAO();
        ProjectBean proBean = null;
        try
        {
          proBean = proDAO.getProject(proId);
        }
        catch (Exception localException3) {}
        if (proBean == null) {
          proBean = new ProjectBean();
        }
        beans.put("mcrp_project", proBean.getName());
      }
      else
      {
        beans.put("mcrp_project", "Tất cả");
      }
      beans.put("mcrp_day", DateUtil.formatDate(date, "dd"));
      beans.put("mcrp_month", DateUtil.formatDate(date, "MM"));
      beans.put("mcrp_year", DateUtil.formatDate(date, "yyyy"));
      beans.put("vattu", list);
      ExcelExport exporter = new ExcelExport();
      exporter.setBeans(beans);
      long milis = System.currentTimeMillis();
      exporter.export(request, response, templateFileName, "Material_Stock_Report.xls");
      milis = System.currentTimeMillis() - milis;
      System.out.println("Material_Stock_Report.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " giay");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:MCProjectStoreReportPrint:print-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
