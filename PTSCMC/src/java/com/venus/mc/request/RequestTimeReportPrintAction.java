package com.venus.mc.request;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ProjectBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.ProjectDAO;
import com.venus.mc.dao.ReportDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
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

public class RequestTimeReportPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int proId = NumberUtil.parseInt(request.getParameter("reportProject"), 0);
    
    String reportRequest = request.getParameter("reportRequest");
    String deliveryFromDate = request.getParameter("deliveryFromDate");
    String deliveryEndDate = request.getParameter("deliveryEndDate");
    
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
      dao.setRequestEmp(MCUtil.getMemberID(request.getSession()));
      if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_MANAGER))
      {
        dao.setInvolvedEmps(MCUtil.getMemberID(request.getSession()) + "");
        OrganizationDAO orgDAO = new OrganizationDAO();
        String orgIDs = "," + orgDAO.getnestedChildOfOrg(new StringBuilder().append(MCUtil.getOrganizationID(request.getSession())).append("").toString()) + "," + orgDAO.getnestedParentOfOrg(new StringBuilder().append(MCUtil.getOrganizationID(request.getSession())).append("").toString()) + "," + MCUtil.getOrganizationID(request.getSession()) + "";
        
        dao.setInvolvedOrgs(orgIDs);
      }
      list = dao.getRequestTimeReport(proId, reportRequest, deliveryFromDate, deliveryEndDate);
    }
    catch (Exception localException2) {}
    if (list == null) {
      list = new ArrayList();
    }
    String templateFileName = request.getSession().getServletContext().getRealPath("/templates/TapHopPDX.xls");
    try
    {
      Map beans = new HashMap();
      beans.put("dexuat", list);
      ExcelExport exporter = new ExcelExport();
      exporter.setBeans(beans);
      long milis = System.currentTimeMillis();
      exporter.export(request, response, templateFileName, "TapHopPDX.xls");
      milis = System.currentTimeMillis() - milis;
      System.out.println("TapHopPDX.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:RequestTimeReportPrint:print-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
