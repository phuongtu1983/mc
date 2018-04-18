package com.venus.mc.request;

import com.venus.core.util.LogUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListRequestAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    RequestDAO requestDAO = new RequestDAO();
    requestDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
    ArrayList requestList = null;
    try
    {
      requestList = requestDAO.getRequests();
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Request:list-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("requestList", requestList);
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
