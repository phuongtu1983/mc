package com.venus.mc.process.store.report;

import com.venus.mc.bean.MaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
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

public class MCReportFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    try
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      ArrayList orgList = orgDAO.getProjectList();
      LabelValueBean value = new LabelValueBean("", "0");
      orgList.add(0, value);
      request.setAttribute("proList", orgList);
      
      StoreDAO storeDAO = new StoreDAO();
      ArrayList arrStore = storeDAO.getStoresOfProject(0);
      request.setAttribute("storeList", arrStore);
      
      MaterialDAO materialDAO = new MaterialDAO();
      ArrayList arrType = materialDAO.getMaterialTypes();
      MaterialBean material = new MaterialBean(0);
      arrType.add(0, material);
      request.setAttribute("materialKindList", arrType);
    }
    catch (Exception ex)
    {
      Logger.getLogger(MCReportFormAction.class.getName()).log(Level.SEVERE, null, ex);
    }
    return true;
  }
}
