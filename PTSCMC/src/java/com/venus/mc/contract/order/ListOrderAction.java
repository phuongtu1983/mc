package com.venus.mc.contract.order;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListOrderAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    ContractDAO contractDAO = new ContractDAO();
    contractDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
    ArrayList contractList = null;
    try
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      int orgId = MCUtil.getOrganizationID(request.getSession());
      String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgDAO.getnestedParentOfOrg(new StringBuilder().append(orgId).append("").toString());
      orgs = orgs + "," + orgId;
      contractDAO.setRequestOrg(orgs);
      
      contractList = contractDAO.getContracts(ContractBean.KIND_ORDER);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Order:list-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("contractList", contractList);
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_LIST + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_PURCHASING_ORDER;
  }
}
