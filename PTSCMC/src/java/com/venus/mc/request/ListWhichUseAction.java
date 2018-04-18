package com.venus.mc.request;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.RequestDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class ListWhichUseAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String whichuse = request.getParameter("whichuse");
    if (GenericValidator.isBlankOrNull(whichuse)) {
      return true;
    }
    int which = Integer.parseInt(whichuse);
    if (which == RequestFormBean.WHICHUSE_PROJECT)
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      try
      {
        ArrayList projects = orgDAO.getProjectListInProcessing();
        if (projects == null) {
          projects = new ArrayList();
        }
        if (!GenericValidator.isBlankOrNull(request.getParameter("addFirst"))) {
          projects.add(0, new LabelValueBean());
        }
        request.setAttribute("whichUseList", projects);
        this.actionForwardResult = "projects";
      }
      catch (Exception localException) {}
    }
    else
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      ArrayList orgs = null;
      try
      {
        if (which == RequestFormBean.WHICHUSE_ORGANIZATION) {
          orgs = orgDAO.getOrganizations(1);
        } else if (which == RequestFormBean.WHICHUSE_WORKSHOP) {
          orgs = orgDAO.getOrganizations(2);
        } else if (which == RequestFormBean.WHICHUSE_TEAM) {
          orgs = orgDAO.getOrganizations(3);
        }
      }
      catch (Exception localException1) {}
      if (orgs == null) {
        orgs = new ArrayList();
      }
      if (!GenericValidator.isBlankOrNull(request.getParameter("addFirst"))) {
        orgs.add(0, new OrganizationBean(0));
      }
      request.setAttribute("whichUseList", orgs);
      this.actionForwardResult = "organizations";
    }
    String reqId = request.getParameter("reqId");
    RequestFormBean formBean = null;
    if (!GenericValidator.isBlankOrNull(reqId)) {
      try
      {
        RequestDAO requestDAO = new RequestDAO();
        RequestBean bean = requestDAO.getRequest(Integer.parseInt(reqId));
        if (bean != null) {
          formBean = new RequestFormBean(bean);
        }
      }
      catch (Exception localException2) {}
    }
    if (formBean == null) {
      formBean = new RequestFormBean();
    }
    request.setAttribute("request", formBean);
    return true;
  }
}
