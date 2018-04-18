package com.venus.mc.process.store.level2;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.EmployeeDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class GetEmployeeOfOrgAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String ids = "0";
    if (!GenericValidator.isBlankOrNull(request.getParameter("orgId"))) {
      ids = request.getParameter("orgId");
    }
    ArrayList arrEmployee = null;
    try
    {
      EmployeeDAO empDAO = new EmployeeDAO();
      arrEmployee = empDAO.getEmployeeByOrgIds(ids);
    }
    catch (Exception localException) {}
    if (arrEmployee == null) {
      arrEmployee = new ArrayList();
    }
    request.setAttribute("employeeList", arrEmployee);
    
    return true;
  }
}
