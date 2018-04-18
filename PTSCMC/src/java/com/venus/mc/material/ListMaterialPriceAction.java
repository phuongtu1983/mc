package com.venus.mc.material;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListMaterialPriceAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    MaterialDAO materialDAO = new MaterialDAO();
    ArrayList materialList = null;
    try
    {
      materialDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
      int orgId = MCUtil.getOrganizationID(request.getSession());
      OrganizationDAO orgDAO = new OrganizationDAO();
      String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgDAO.getnestedParentOfOrg(new StringBuilder().append(orgId).append("").toString());
      orgs = orgs + "," + orgId;
      materialDAO.setRequestOrg(orgs);
      
      String fromDate = request.getParameter("fromDate");
      String toDate = request.getParameter("toDate");
      materialList = materialDAO.searchMaterialPrices(0, "", fromDate, toDate);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (materialList == null) {
      materialList = new ArrayList();
    }
    request.setAttribute("materialList", materialList);
    return true;
  }
}
