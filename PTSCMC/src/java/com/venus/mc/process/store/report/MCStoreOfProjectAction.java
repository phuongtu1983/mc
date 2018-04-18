package com.venus.mc.process.store.report;

import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.StoreDAO;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MCStoreOfProjectAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    try
    {
      int proId = NumberUtil.parseInt(request.getParameter("proId"), 0);
      StoreDAO stoDAO = new StoreDAO();
      ArrayList stoList = null;
      if (proId != 0) {
        stoList = stoDAO.getStoresOfProject(proId);
      } else {
        stoList = new ArrayList();
      }
      request.setAttribute("storeList", stoList);
    }
    catch (Exception ex)
    {
      Logger.getLogger(MCStoreOfProjectAction.class.getName()).log(Level.SEVERE, null, ex);
    }
    return true;
  }
}
