package com.venus.mc.contract;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
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

public class PrintContractProcessFollowAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchAdvContractFormBean formBean = new SearchAdvContractFormBean();
    String contractNumber = request.getParameter("contractNumber");
    String effectedFromDate = request.getParameter("effectedFromDate");
    String effectedToDate = request.getParameter("effectedToDate");
    String moveAccountingFromDate = request.getParameter("moveAccountingFromDate");
    String moveAccountingToDate = request.getParameter("moveAccountingToDate");
    String deliveryDateAsContractFromDate = request.getParameter("deliveryDateAsContractFromDate");
    String deliveryDateAsContractToDate = request.getParameter("deliveryDateAsContractToDate");
    
    String venId = request.getParameter("venId");
    String vendorName = request.getParameter("vendorName");
    String handleEmpName = request.getParameter("handleEmpName");
    String followEmpName = request.getParameter("followEmpName");
    
    String paymentStatus = request.getParameter("paymentStatus");
    String kind = request.getParameter("kind");
    String proId = request.getParameter("proId");
    String handleEmp = request.getParameter("handleEmp");
    String followEmp = request.getParameter("followEmp");
    ArrayList contractList = null;
    ContractDAO contractDAO = new ContractDAO();
    try
    {
      formBean.setContractNumber(contractNumber);
      formBean.setEffectedFromDate(effectedFromDate);
      formBean.setEffectedToDate(effectedToDate);
      formBean.setMoveAccountingFromDate(moveAccountingFromDate);
      formBean.setMoveAccountingToDate(moveAccountingToDate);
      formBean.setDeliveryDateAsContractFromDate(deliveryDateAsContractFromDate);
      formBean.setDeliveryDateAsContractToDate(deliveryDateAsContractToDate);
      
      formBean.setVenId(NumberUtil.parseInt(venId, 0));
      formBean.setVendorName(vendorName);
      formBean.setHandleEmpName(handleEmpName);
      formBean.setFollowEmpName(followEmpName);
      
      formBean.setPaymentStatus(NumberUtil.parseInt(paymentStatus, 0));
      formBean.setKind(NumberUtil.parseInt(kind, 0));
      formBean.setProId(NumberUtil.parseInt(proId, 0));
      formBean.setHandleEmp(NumberUtil.parseInt(handleEmp, 0));
      formBean.setFollowEmp(NumberUtil.parseInt(followEmp, 0));
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Contract:printProcessFollow-" + ex.getMessage());
      ex.printStackTrace();
    }
    if (contractList == null) {
      contractList = new ArrayList();
    }
    try
    {
      String templateFileName = "";
      if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE))
      {
        int orgId = MCUtil.getOrganizationID(request.getSession());
        String orgs = "";
        try
        {
          OrganizationDAO orgDAO = new OrganizationDAO();
          orgs = orgDAO.getnestedChildOfOrg(orgId + "");
        }
        catch (Exception localException1) {}
        orgs = orgs + "," + orgId;
        contractDAO.setRequestOrg(orgs);
        contractDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
        templateFileName = request.getSession().getServletContext().getRealPath("/templates/Theo doi thuc hien hop dong-gui VN.xls");
      }
      else
      {
        templateFileName = request.getSession().getServletContext().getRealPath("/templates/Theo doi thuc hien hop dong-gui VN_NoPrice.xls");
      }
      contractList = contractDAO.getContractForPrintProcessFollow(formBean);
      Map beans = new HashMap();
      beans.put("hopdong", contractList);
      ExcelExport exporter = new ExcelExport();
      exporter.setBeans(beans);
      long milis = System.currentTimeMillis();
      exporter.export(request, response, templateFileName, "Theo Doi Thuc Hien Hop Dong.xls");
      milis = System.currentTimeMillis() - milis;
      System.out.println("Theo Doi Thuc Hien Hop Dong.xls : time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Contract:printProcessFollow-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
