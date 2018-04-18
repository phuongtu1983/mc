package com.venus.mc.request;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class RequestReportFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    try
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      ArrayList orgList = orgDAO.getProjectList();
      LabelValueBean organization = new LabelValueBean();
      organization.setValue("0");
      organization.setLabel("");
      orgList.add(0, organization);
      request.setAttribute("proList", orgList);
    }
    catch (Exception ex)
    {
      Logger.getLogger(RequestReportFormAction.class.getName()).log(Level.SEVERE, null, ex);
    }
    return true;
  }
}
