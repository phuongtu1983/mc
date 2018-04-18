package com.venus.mc.process.store.report;

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
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MCRequestReportPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int proId = NumberUtil.parseInt(request.getParameter("mcReportProject"), 0);
    int reqId = NumberUtil.parseInt(request.getParameter("mcReportRequest"), 0);
    String deliveryFromDate = request.getParameter("deliveryFromDate");
    String deliveryEndDate = request.getParameter("deliveryEndDate");
    
    String contractFromDate = request.getParameter("contractFromDate");
    String contractEndDate = request.getParameter("contractEndDate");
    String mrirFromDate = request.getParameter("mrirFromDate");
    String mrirEndDate = request.getParameter("mrirEndDate");
    String msvFromDate = request.getParameter("msvFromDate");
    String msvEndDate = request.getParameter("msvEndDate");
    String rfmFromDate = request.getParameter("rfmFromDate");
    String rfmEndDate = request.getParameter("rfmEndDate");
    String mivFromDate = request.getParameter("mivFromDate");
    String mivEndDate = request.getParameter("mivEndDate");
    String deliveryDateAsContractFromDate = request.getParameter("deliveryDateAsContractFromDate");
    String deliveryDateAsContractToDate = request.getParameter("deliveryDateAsContractToDate");
    String nameVn = request.getParameter("nameVn");
    String code = request.getParameter("code");
    try
    {
      nameVn = URLDecoder.decode(nameVn, "UTF8");
    }
    catch (Exception localException1) {}
    ArrayList list = null;
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
    ReportDAO dao = new ReportDAO();
    try
    {
      list = dao.getMCRequestReport(proId, reqId, deliveryFromDate, deliveryEndDate, contractFromDate, contractEndDate, mrirFromDate, mrirEndDate, msvFromDate, msvEndDate, rfmFromDate, rfmEndDate, mivFromDate, mivEndDate, deliveryDateAsContractFromDate, deliveryDateAsContractToDate);
    }
    catch (Exception localException3) {}
    if (list == null) {
      list = new ArrayList();
    }
    String templateFileName = request.getSession().getServletContext().getRealPath("/templates/Theo_Doi_Phieu_De_Xuat.xls");
    try
    {
      Map beans = new HashMap();
      beans.put("vattu", list);
      ExcelExport exporter = new ExcelExport();
      exporter.setBeans(beans);
      long milis = System.currentTimeMillis();
      exporter.export(request, response, templateFileName, "Theo_Doi_Phieu_De_Xuat.xls");
      milis = System.currentTimeMillis() - milis;
      System.out.println("Theo_Doi_Phieu_De_Xuat.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:MCRequestReportPrint:print-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
