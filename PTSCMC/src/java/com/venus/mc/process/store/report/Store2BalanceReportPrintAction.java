package com.venus.mc.process.store.report;

import com.venus.core.util.DateUtil;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.StoreBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.ReportDAO;
import com.venus.mc.dao.StoreDAO;
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

public class Store2BalanceReportPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int proId = NumberUtil.parseInt(request.getParameter("mcReportProject"), 0);
    int stoId = NumberUtil.parseInt(request.getParameter("mcStore"), 0);
    
    String nameVn = request.getParameter("nameVn");
    String code = request.getParameter("code");
    
    String templateFileName = request.getSession().getServletContext().getRealPath("/templates/Bao cao ton kho cap 2 Xuong2.xls");
    try
    {
      Map beans = new HashMap();
      Date date = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "/");
      beans.put("mcrp_day", DateUtil.formatDate(date, "dd"));
      beans.put("mcrp_month", DateUtil.formatDate(date, "MM"));
      beans.put("mcrp_year", DateUtil.formatDate(date, "yyyy"));
      StoreDAO stoDAO = new StoreDAO();
      StoreBean stoBean = stoDAO.getStore(stoId);
      String workshop = "";
      if (stoBean != null)
      {
        OrganizationDAO orgDAO = new OrganizationDAO();
        OrganizationBean orgBean = orgDAO.getOrganization(stoBean.getUsedOrg());
        if (orgBean != null) {
          workshop = orgBean.getName();
        }
      }
      beans.put("mcrp_workshop", workshop);
      ArrayList list = null;
      ReportDAO dao = new ReportDAO();
      try
      {
        list = dao.getStore2BalanceReport2(proId, stoId, nameVn, code);
      }
      catch (Exception localException1) {}
      if (list == null) {
        list = new ArrayList();
      }
      beans.put("vattu", list);
      ExcelExport exporter = new ExcelExport();
      exporter.setBeans(beans);
      long milis = System.currentTimeMillis();
      exporter.export(request, response, templateFileName, "Bao Cao Ton Kho Cap 2.xls");
      milis = System.currentTimeMillis() - milis;
      System.out.println("Bao Cao Ton Kho Cap 2.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
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
