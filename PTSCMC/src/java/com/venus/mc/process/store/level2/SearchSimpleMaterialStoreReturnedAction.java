package com.venus.mc.process.store.level2;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchSimpleMaterialStoreReturnedAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchFormBean vendorForm = (SearchFormBean)form;
    int fieldid = vendorForm.getSearchid();
    String strFieldvalue = vendorForm.getSearchvalue();
    ArrayList storeList = null;
    int stoId = 0;
    if (!GenericValidator.isBlankOrNull(request.getParameter("stoId"))) {
      stoId = NumberUtil.parseInt(request.getParameter("stoId"), 0);
    }
    try
    {
      StoreDAO storeDAO = new StoreDAO();
      storeList = storeDAO.searchReturnedMaterialStores(fieldid, strFieldvalue, stoId);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Request:searchReturnedMaterialStores-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("storeList", storeList);
    return true;
  }
  
  protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    return "";
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
