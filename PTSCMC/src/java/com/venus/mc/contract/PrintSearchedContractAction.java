package com.venus.mc.contract;

import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.OrganizationDAO;
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

public class PrintSearchedContractAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int fieldid = NumberUtil.parseInt(request.getParameter("searchId"), 0);
    String strFieldvalue = request.getParameter("searchValue");
    ContractDAO contractDAO = new ContractDAO();
    try
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      int orgId = MCUtil.getOrganizationID(request.getSession());
      String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgId;
      contractDAO.setRequestOrg(orgs);
      contractDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
      ArrayList deliveryList = contractDAO.searchSimpleContract(fieldid, StringUtil.encodeHTML(strFieldvalue), request.getParameter("kind"));
      String templateFileName = "";
      if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {
        templateFileName = request.getSession().getServletContext().getRealPath("/templates/Theo doi thuc hien hop dong-gui VN.xls");
      } else {
        templateFileName = request.getSession().getServletContext().getRealPath("/templates/Theo doi thuc hien hop dong-gui VN_NoPrice.xls");
      }
      Map beans = new HashMap();
      beans.put("hopdong", deliveryList);
      ExcelExport exporter = new ExcelExport();
      exporter.setBeans(beans);
      long milis = System.currentTimeMillis();
      exporter.export(request, response, templateFileName, "Ket Qua Tim Kiem.xls");
      milis = System.currentTimeMillis() - milis;
      System.out.println("Ket Qua Tim Kiem.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
    }
    catch (Exception localException) {}
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
