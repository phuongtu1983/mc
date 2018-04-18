package com.venus.mc.process.store.mrir;

import com.venus.core.util.DateUtil;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.OsDBean;
import com.venus.mc.bean.ProjectBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.ProjectDAO;
import com.venus.mc.dao.ReportDAO;
import java.io.PrintStream;
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

public class MCProjectStoreMrirReportPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int proId = NumberUtil.parseInt(request.getParameter("mcReportProject"), 0);
    int stoId = NumberUtil.parseInt(request.getParameter("mcStore"), 0);
    int status = NumberUtil.parseInt(request.getParameter("status"), 2);
    String osdFromDate = request.getParameter("osdFromDate");
    String osdEndDate = request.getParameter("osdEndDate");
    ArrayList list = null;
    ReportDAO dao = new ReportDAO();
    try
    {
      list = dao.getMCProjectStoreMrirReport(proId, stoId, status, osdFromDate, osdEndDate);
    }
    catch (Exception localException1) {}
    if (list == null) {
      list = new ArrayList();
    }
    for (int i = 0; i < list.size(); i++)
    {
      OsDBean OsDBean = (OsDBean)list.get(i);
      OsDBean.setStt(i + 1);
    }
    String templateFileName = request.getSession().getServletContext().getRealPath("/templates/Register OSD-BD-PTSC.xls");
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
        catch (Exception localException2) {}
        if (proBean == null) {
          proBean = new ProjectBean();
        }
        beans.put("mcrp_project", proBean.getName());
      }
      else
      {
        beans.put("mcrp_project", "T?t c?");
      }
      beans.put("mcrp_day", DateUtil.formatDate(date, "dd") + "/" + DateUtil.formatDate(date, "MM") + "/" + DateUtil.formatDate(date, "yyyy"));
      
      beans.put("vattu", list);
      ExcelExport exporter = new ExcelExport();
      exporter.setBeans(beans);
      long milis = System.currentTimeMillis();
      exporter.export(request, response, templateFileName, "Register OSD-BD-PTSC.xls");
      milis = System.currentTimeMillis() - milis;
      System.out.println("Register OSD-BD-PTSC.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " giay");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:MCProjectStoreMrirReportPrint:print-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
