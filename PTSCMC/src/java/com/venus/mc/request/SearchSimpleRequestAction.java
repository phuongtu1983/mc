package com.venus.mc.request;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchSimpleRequestAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchFormBean vendorForm = (SearchFormBean)form;
    int fieldid = vendorForm.getSearchid();
    String strFieldvalue = vendorForm.getSearchvalue();
    ArrayList requestList = null;
    RequestDAO requestDAO = new RequestDAO();
    try
    {
      requestDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
      
      int status = 0;
      if (PermissionUtil.hasOneOfPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_VIEW + ""))
      {
        status = RequestBean.STATUS_MANAGER;
      }
      else if (!PermissionUtil.hasOneOfPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_CHECK + "," + PermissionUtil.PER_PURCHASING_REQUEST_STORE + "," + PermissionUtil.PER_PURCHASING_REQUEST_PLANDEP))
      {
        status = RequestBean.STATUS_CREATE;
      }
      else if (PermissionUtil.hasOneOfPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_STORE + ""))
      {
        status = RequestBean.STATUS_STORE;
        try
        {
          OrganizationDAO orgDAO = new OrganizationDAO();
          int orgId = MCUtil.getOrganizationID(request.getSession());
          String orgs = orgDAO.getnestedParentOfOrg(orgId + "");
          if (orgs.equals("0")) {
            orgs = orgId + "";
          }
          if (!("," + orgs + ",").contains(",4,")) {
            requestDAO.setRequestOrg(orgs);
          } else {
            requestDAO.setRequestOrg("0");
          }
        }
        catch (Exception localException1) {}
      }
      else if (PermissionUtil.hasOneOfPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_CHECK + ""))
      {
        status = RequestBean.STATUS_CHECK;
      }
      else if (PermissionUtil.hasOneOfPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_PLANDEP + ""))
      {
        status = RequestBean.STATUS_PLANDEP;
      }
      if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_MANAGER)) {
        try
        {
          OrganizationDAO orgDAO = new OrganizationDAO();
          int orgId = MCUtil.getOrganizationID(request.getSession());
          String orgs = orgDAO.getnestedParentOfOrg(orgId + "");
          if (orgs.equals("0")) {
            orgs = orgId + "";
          }
          requestDAO.setInvolvedOrgs(orgs);
        }
        catch (Exception localException2) {}
      }
      requestList = requestDAO.searchSimpleRequest(fieldid, strFieldvalue, status);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Request:searchSimple-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("requestList", requestList);
    return true;
  }
  
  protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    return "";
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
