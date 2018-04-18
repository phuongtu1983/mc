package com.venus.mc.process.store.level2;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.StoreDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class GetReturnedMaterialAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    StoreDAO storeDAO = new StoreDAO();
    ArrayList storeList = null;
    String ids = "0";
    if (!GenericValidator.isBlankOrNull(request.getParameter("msr2Id"))) {
      ids = request.getParameter("msr2Id");
    }
    try
    {
      storeList = storeDAO.getReturnedMaterialsByMsrId(ids);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (storeList == null) {
      storeList = new ArrayList();
    }
    request.setAttribute("rmsDetailList", storeList);
    return true;
  }
}
