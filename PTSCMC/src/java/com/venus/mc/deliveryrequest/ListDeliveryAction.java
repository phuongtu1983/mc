package com.venus.mc.deliveryrequest;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListDeliveryAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    ContractDAO contractDAO = new ContractDAO();
    contractDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
    ArrayList deliveryList = null;
    try
    {
      deliveryList = contractDAO.searchSimpleContract(0, "", ContractBean.KIND_DELIVERY_REQUEST + "");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:DeliveryRequest:list-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("contractList", deliveryList);
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_LIST + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_PURCHASING_DELIVERYREQUEST;
  }
}
