package com.venus.mc.request;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ProjectBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.dao.ProjectDAO;
import com.venus.mc.dao.ReportDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RequestReportShowAction
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
    
    ArrayList list = null;
    ProjectDAO proDAO = new ProjectDAO();
    ProjectBean proBean = null;
    try
    {
      proBean = proDAO.getProject(proId);
    }
    catch (Exception localException) {}
    if (proBean == null) {
      proBean = new ProjectBean();
    }
    ReportDAO dao = new ReportDAO();
    try
    {
      list = new ArrayList();
    }
    catch (Exception localException1) {}
    if (list == null) {
      list = new ArrayList();
    }
    request.setAttribute("requestList", list);
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_LIST + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_PURCHASING_REQUEST;
  }
}
