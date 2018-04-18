package com.venus.mc.tenderplan;

import com.venus.core.util.DateUtil;
import com.venus.core.util.LogUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchSimpleTenderPlanAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchFormBean tenderForm = (SearchFormBean)form;
    int fieldid = tenderForm.getSearchid();
    String strFieldvalue = tenderForm.getSearchvalue();
    ArrayList tender = null;
    TenderPlanDAO tenderDAO = new TenderPlanDAO();
    try
    {
      tenderDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
      int orgId = MCUtil.getOrganizationID(request.getSession());
      OrganizationDAO orgDAO = new OrganizationDAO();
      String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgId;
      tenderDAO.setRequestOrg(orgs);
      tender = tenderDAO.searchSimpleTenderPlan(fieldid, StringUtil.encodeHTML(strFieldvalue));
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:TenderPlan:searchSimple-" + ex.getMessage());
      ex.printStackTrace();
    }
    if (tender == null) {
      tender = new ArrayList();
    }
    TenderPlanBean tenderBean = null;
    long MILLSECS_PER_DAY = 86400000L;
    long delta = 0L;
    Date today = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "dd/MM/yyyy");
    for (int i = 0; i < tender.size(); i++)
    {
      tenderBean = (TenderPlanBean)tender.get(i);
      tenderBean.setColor("");
      if (!MCUtil.getBundleString("message.tenderplan.status.ordered").equals(tenderBean.getStatus()))
      {
        Date deadline = DateUtil.transformerStringEnDate(tenderBean.getBidDeadline(), "dd/MM/yyyy");
        if (deadline != null)
        {
          delta = today.getTime() - deadline.getTime();
          delta /= MILLSECS_PER_DAY;
          if (delta > 0L) {
            if (delta <= 14L) {
              tenderBean.setColor("yellow");
            } else {
              tenderBean.setColor("red");
            }
          }
        }
      }
    }
    request.setAttribute("tenderPlanList", tender);
    return true;
  }
}
