package com.venus.mc.process.store.report;

import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class MCProjectStoreReportFormAction
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
      LabelValueBean value = new LabelValueBean("All", "0");
      orgList.add(0, value);
      request.setAttribute("proList", orgList);
      
      ArrayList arrStore = new ArrayList();
      request.setAttribute("storeList", arrStore);
      
      ArrayList arrOrg = new ArrayList();
      try
      {
        arrOrg = orgDAO.getOrgExceptKind(OrganizationBean.KIND_TO + "");
      }
      catch (Exception localException1) {}
      OrganizationBean orgBean = new OrganizationBean(0);
      arrOrg.add(0, orgBean);
      request.setAttribute("orgList", arrOrg);
    }
    catch (Exception ex)
    {
      Logger.getLogger(MCProjectStoreReportFormAction.class.getName()).log(Level.SEVERE, null, ex);
    }
    return true;
  }
}
