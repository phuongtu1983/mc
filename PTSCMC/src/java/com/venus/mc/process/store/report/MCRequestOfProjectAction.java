package com.venus.mc.process.store.report;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MCRequestOfProjectAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    try
    {
      int proId = NumberUtil.parseInt(request.getParameter("proId"), 0);
      RequestDAO reqDAO = new RequestDAO();
      reqDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
      ArrayList reqList = null;
      reqDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
      if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_MANAGER)) {
        reqDAO.setInvolvedEmps(MCUtil.getMemberID(request.getSession()) + "");
      }
      if (proId == 0) {
        reqList = new ArrayList();
      } else {
        reqList = reqDAO.getRequestsOfProject(proId);
      }
      RequestBean req = new RequestBean(0);
      reqList.add(0, req);
      request.setAttribute("requestList", reqList);
    }
    catch (Exception ex)
    {
      Logger.getLogger(MCRequestOfProjectAction.class.getName()).log(Level.SEVERE, null, ex);
    }
    return true;
  }
}
