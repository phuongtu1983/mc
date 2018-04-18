package com.venus.mc.contract;

import com.venus.core.util.LogUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class UpdateContractStatusAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    try
    {
      int conId = Integer.parseInt(request.getParameter("conId"));
      int status = Integer.parseInt(request.getParameter("status"));
      ContractDAO contractDAO = new ContractDAO();
      contractDAO.updateContractStatus(status, conId);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Contract:add-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_EDIT + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_PURCHASING_CONTRACT_UPDATE_STATUS;
  }
}
