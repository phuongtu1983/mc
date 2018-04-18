package com.venus.mc.process.store.mrir;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.MCUtil;
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
      
      ArrayList arrStatus = new ArrayList();
      value = new LabelValueBean("All", "2");
      arrStatus.add(0, value);
      value = new LabelValueBean();
      value.setLabel(MCUtil.getBundleString("message.osd.status0"));
      value.setValue("0");
      arrStatus.add(value);
      value = new LabelValueBean();
      value.setLabel(MCUtil.getBundleString("message.osd.status1"));
      value.setValue("1");
      arrStatus.add(value);
      request.setAttribute("osDStatusList", arrStatus);
    }
    catch (Exception ex)
    {
      Logger.getLogger(MCProjectStoreReportFormAction.class.getName()).log(Level.SEVERE, null, ex);
    }
    return true;
  }
}
