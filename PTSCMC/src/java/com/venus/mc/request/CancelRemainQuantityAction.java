package com.venus.mc.request;

import com.venus.core.util.NumberUtil;
import com.venus.mc.core.BaseAction;
import com.venus.mc.dao.RequestDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CancelRemainQuantityAction
  extends BaseAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int reqDetId = NumberUtil.parseInt(request.getParameter("reqDetId"), 0);
    RequestDAO reqDAO = new RequestDAO();
    try
    {
      reqDAO.cancalRequestRemainQuantity(reqDetId);
    }
    catch (Exception localException) {}
    return true;
  }
}
