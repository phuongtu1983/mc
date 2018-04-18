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

public class RequestReportPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int proId = NumberUtil.parseInt(request.getParameter("reportProject"), 0);
    String reqNumber = request.getParameter("reportRequest");
    String mcReportContract = request.getParameter("mcReportContract");
    String mcReportVendor = request.getParameter("mcReportVendor");
    String deliveryFromDate = request.getParameter("deliveryFromDate");
    String deliveryEndDate = request.getParameter("deliveryEndDate");
    
    String contractFromDate = request.getParameter("contractFromDate");
    String contractEndDate = request.getParameter("contractEndDate");
    String mrirFromDate = request.getParameter("mrirFromDate");
    String mrirEndDate = request.getParameter("mrirEndDate");
    String msvFromDate = request.getParameter("msvFromDate");
    String msvEndDate = request.getParameter("msvEndDate");
    String deliveryDateAsContractFromDate = request.getParameter("deliveryDateAsContractFromDate");
    String deliveryDateAsContractToDate = request.getParameter("deliveryDateAsContractToDate");
    
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
    String templateFileName = "";
    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE))
    {
      dao.setRequestEmp(MCUtil.getMemberID(request.getSession()));
      int orgId = MCUtil.getOrganizationID(request.getSession());
      String orgs = "";
      try
      {
        OrganizationDAO orgDAO = new OrganizationDAO();
        orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      }
      catch (Exception localException2) {}
      orgs = orgs + "," + orgId;
      dao.setInvolvedOrgs(orgs);
      dao.setRequestOrg(orgId + "");
      templateFileName = request.getSession().getServletContext().getRealPath("/templates/TheoDoiPhieuDeXuat(QuanLyChiTiet).xls");
    }
    else
    {
      templateFileName = request.getSession().getServletContext().getRealPath("/templates/TheoDoiPhieuDeXuat(QuanLyChiTiet)_NoPrice.xls");
    }
    dao.setRequestEmp(MCUtil.getMemberID(request.getSession()));
    try
    {
      list = dao.getRequestReport(proId, reqNumber, deliveryFromDate, deliveryEndDate, contractFromDate, contractEndDate, mrirFromDate, mrirEndDate, msvFromDate, msvEndDate, 0, mcReportContract, mcReportVendor, deliveryDateAsContractFromDate, deliveryDateAsContractToDate);
    }
    catch (Exception localException3) {}
    if (list == null) {
      list = new ArrayList();
    }
    try
    {
      Map beans = new HashMap();
      beans.put("dexuat", list);
      ExcelExport exporter = new ExcelExport();
      exporter.setBeans(beans);
      long milis = System.currentTimeMillis();
      String exportFile = request.getSession().getServletContext().getRealPath("/templates/temp/Bang Theo Doi Mua Sam.xls");
      exporter.export(request, response, templateFileName, exportFile);
      milis = System.currentTimeMillis() - milis;
      System.out.println("Bang Theo Doi Mua Sam.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:RequestReportPrint:print-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
