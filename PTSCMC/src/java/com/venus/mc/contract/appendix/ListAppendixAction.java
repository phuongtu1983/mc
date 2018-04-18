package com.venus.mc.contract.appendix;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.mc.contract.ContractFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListAppendixAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String conId = request.getParameter("conId");
    ArrayList contractList = null;
    if (!GenericValidator.isBlankOrNull(conId)) {
      try
      {
        ContractDAO contractDAO = new ContractDAO();
        contractDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
        contractList = contractDAO.getAppendixs(conId);
        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE))
        {
          ContractFormBean contract = null;
          int orgId = MCUtil.getOrganizationID(request.getSession());
          for (int i = 0; i < contractList.size(); i++)
          {
            contract = (ContractFormBean)contractList.get(i);
            if (contract.getFollowOrg() == orgId) {
              contract.setIsPermissionPrice(1);
            }
          }
        }
      }
      catch (Exception ex)
      {
        LogUtil.error("FAILED:Appendix:list-" + ex.getMessage());
        ex.printStackTrace();
      }
    }
    if (contractList == null) {
      contractList = new ArrayList();
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
    return PermissionUtil.PER_PURCHASING_CONTRACT;
  }
}
