package com.venus.mc.contract;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.OrganizationDAO;
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

public class DeliveryRequestReportPrintAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    int searchid = NumberUtil.parseInt(request.getParameter("searchid"), 0);
    String searchvalue = request.getParameter("searchvalue");
    ArrayList list = null;
    
    ContractDAO contractDAO = new ContractDAO();
    try
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      int orgId = MCUtil.getOrganizationID(request.getSession());
      String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgId;
      contractDAO.setRequestOrg(orgs);
      
      contractDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
      list = contractDAO.searchSimpleContract(searchid, StringUtil.encodeHTML(searchvalue), ContractBean.KIND_DELIVERY_REQUEST + "");
    }
    catch (Exception localException1) {}
    if (list == null) {
      list = new ArrayList();
    }
    ContractFormBean contractBean = null;
    for (int i = 0; i < list.size(); i++)
    {
      contractBean = (ContractFormBean)list.get(i);
      contractBean.setConId(i + 1);
    }
    String templateFileName = request.getSession().getServletContext().getRealPath("/templates/delivery_request_exported_template.xls");
    try
    {
      Map beans = new HashMap();
      beans.put("hopdong", list);
      ExcelExport exporter = new ExcelExport();
      exporter.setBeans(beans);
      long milis = System.currentTimeMillis();
      exporter.export(request, response, templateFileName, "contract.xls");
      milis = System.currentTimeMillis() - milis;
      System.out.println("contract.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:DeliveryRequestReportPrint:print-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
