package com.venus.mc.process.store.level2;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.PermissionDAO;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.permission.ApplicationPermissionBean;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListStoreLevel2Action
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    StoreDAO storeDAO = new StoreDAO();
    ArrayList storeList = null;
    try
    {
      int orgId = MCUtil.getOrganizationID(request.getSession());
      int empId = MCUtil.getMemberID(request.getSession());
      PermissionDAO permissionDAO = new PermissionDAO();
      ArrayList<ApplicationPermissionBean> arrPermission = permissionDAO.getPermissionsOfEmployee(empId, 0);
      String refOrgIds = orgId + "";
      for (int i = 0; i < arrPermission.size(); i++)
      {
        ApplicationPermissionBean per = (ApplicationPermissionBean)arrPermission.get(i);
        per.setPermit("0," + per.getPermit() + ",0");
        if (per.getPermit().indexOf("," + getPermit() + ",") > -1)
        {
          String function = getFunction();
          String[] p = function.split(";");
          boolean hasPermission = false;
          for (int j = 0; j < p.length; j++) {
            if (per.getFunction().equals(p[j]))
            {
              hasPermission = true;
              break;
            }
          }
          if ((hasPermission) && 
            (!GenericValidator.isBlankOrNull(per.getOrganizations()))) {
            refOrgIds = refOrgIds + "," + per.getOrganizations();
          }
        }
      }
      OrganizationDAO orgDAO = new OrganizationDAO();
      String orgs = orgDAO.getnestedChildOfOrg(refOrgIds);
      orgs = orgs + "," + orgDAO.getnestedParentOfOrg(refOrgIds);
      orgs = orgs + "," + refOrgIds;
      storeList = storeDAO.getStoreLevel2s(0, orgs);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (storeList == null) {
      storeList = new ArrayList();
    }
    request.setAttribute("storeList", storeList);
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_LIST + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_STOCK_STORE2;
  }
}
