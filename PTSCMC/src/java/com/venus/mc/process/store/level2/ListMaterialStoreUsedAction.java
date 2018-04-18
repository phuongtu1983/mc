package com.venus.mc.process.store.level2;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListMaterialStoreUsedAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    StoreDAO storeDAO = new StoreDAO();
    storeDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
    ArrayList storeList = null;
    int stoId = 0;
    if (!GenericValidator.isBlankOrNull(request.getParameter("stoId"))) {
      stoId = NumberUtil.parseInt(request.getParameter("stoId"), 0);
    }
    try
    {
      storeList = storeDAO.getUsedMaterialStores(stoId);
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
