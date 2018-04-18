package com.venus.mc.payment;

import com.venus.core.util.LogUtil;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.OrganizationDAO;
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

public class PaymentReportPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String paymentFromDate = request.getParameter("paymentFromDate");
    String paymentEndDate = request.getParameter("paymentEndDate");
    ArrayList paymentList = null;
    ContractDAO contractDAO = new ContractDAO();
    contractDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
    try
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      int orgId = MCUtil.getOrganizationID(request.getSession());
      String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgId;
      contractDAO.setRequestOrg(orgs);
      paymentList = contractDAO.searchSimplePayment(paymentFromDate, paymentEndDate);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Payment:report-" + ex.getMessage());
      ex.printStackTrace();
    }
    ReportDAO dao = new ReportDAO();
    String templateFileName = request.getSession().getServletContext().getRealPath("/templates/dexuatthanhtoan.xls");
    if (paymentList == null) {
      paymentList = new ArrayList();
    }
    try
    {
      Map beans = new HashMap();
      beans.put("dexuat", paymentList);
      ExcelExport exporter = new ExcelExport();
      exporter.setBeans(beans);
      long milis = System.currentTimeMillis();
      exporter.export(request, response, templateFileName, "Danh Sach De Xuat Thanh Toan.xls");
      milis = System.currentTimeMillis() - milis;
      System.out.println("Danh Sach De Xuat Thanh Toan.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:PaymentReportPrint:print-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
