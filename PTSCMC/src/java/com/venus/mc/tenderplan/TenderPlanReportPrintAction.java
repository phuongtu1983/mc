package com.venus.mc.tenderplan;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.MCUtil;
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

public class TenderPlanReportPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    int searchid = NumberUtil.parseInt(request.getParameter("searchid"), 0);
    String searchvalue = request.getParameter("searchvalue");
    ArrayList tender = null;
    TenderPlanDAO tenderDAO = new TenderPlanDAO();
    try
    {
      tenderDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
      int orgId = MCUtil.getOrganizationID(request.getSession());
      OrganizationDAO orgDAO = new OrganizationDAO();
      String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgId;
      tenderDAO.setRequestOrg(orgs);
      tender = tenderDAO.searchSimpleTenderPlanForPrint(searchid, StringUtil.encodeHTML(searchvalue));
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:TenderPlan:print-" + ex.getMessage());
      ex.printStackTrace();
    }
    if (tender == null) {
      tender = new ArrayList();
    }
    String templateFileName = request.getSession().getServletContext().getRealPath("/templates/tenderplan_exported_template.xls");
    try
    {
      Map beans = new HashMap();
      beans.put("dulieu", tender);
      ExcelExport exporter = new ExcelExport();
      exporter.setBeans(beans);
      long milis = System.currentTimeMillis();
      exporter.export(request, response, templateFileName, "tennderplan.xls");
      milis = System.currentTimeMillis() - milis;
      System.out.println("tenderplan.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:TenderPlanReportPrint:print-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
