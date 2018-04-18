package com.venus.mc.process.store.report;

import com.venus.mc.bean.StoreLevel2Bean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.StoreDAO;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class StoreLevel2ReportFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    try
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      ArrayList orgList = orgDAO.getProjectList();
      if (orgList == null) {
        orgList = new ArrayList();
      }
      request.setAttribute("proList", orgList);
      
      String proId = "0";
      LabelValueBean organization = null;
      int i = 0;
      if (i < orgList.size())
      {
        organization = (LabelValueBean)orgList.get(i);
        if (proId.equals("0")) {
          proId = organization.getValue();
        }
      }
      StoreDAO storeDAO = new StoreDAO();
      ArrayList storeList = null;
      if (!proId.equals("0"))
      {
        String permissionOrg = "";
        
        storeList = storeDAO.getStoreLevel2s(0, permissionOrg, proId);
      }
      if (storeList == null) {
        storeList = new ArrayList();
      }
      storeList.add(0, new StoreLevel2Bean());
      request.setAttribute("storeList", storeList);
    }
    catch (Exception ex)
    {
      Logger.getLogger(StoreLevel2ReportFormAction.class.getName()).log(Level.SEVERE, null, ex);
    }
    return true;
  }
}
