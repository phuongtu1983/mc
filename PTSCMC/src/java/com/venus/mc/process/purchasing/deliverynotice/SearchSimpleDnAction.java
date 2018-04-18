package com.venus.mc.process.purchasing.deliverynotice;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.DnBean;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchSimpleDnAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchFormBean formBean = (SearchFormBean)form;
    int fieldid = formBean.getSearchid();
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 1);
    String strFieldvalue = formBean.getSearchvalue();
    String strFieldvalues = formBean.getSearchvalues();
    DnDAO dnDAO = new DnDAO();
    ArrayList dnList = null;
    boolean ok = false;
    try
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      int orgId = MCUtil.getOrganizationID(request.getSession());
      String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgDAO.getnestedParentOfOrg(new StringBuilder().append(orgId).append("").toString());
      
      orgs = orgs + "," + orgId;
      dnDAO.setRequestOrg(orgs);
      dnDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
      if (PermissionUtil.getEmployeesHasPermission(request, PermissionUtil.PER_PURCHASING_DELIVERYNOTICE_ORG)) {
        ok = true;
      }
      if (kind == DnBean.KIND1) {
        dnList = dnDAO.searchSimpleDn(fieldid, StringUtil.encodeHTML(strFieldvalue), strFieldvalues, kind, ok);
      }
      if (kind == DnBean.KIND2) {
        dnList = dnDAO.searchSimpleDn2(fieldid, StringUtil.encodeHTML(strFieldvalue), kind);
      }
      if (kind == DnBean.KIND4) {
        dnList = dnDAO.searchSimpleDn4(fieldid, StringUtil.encodeHTML(strFieldvalue), kind);
      }
      if (kind == DnBean.KIND5) {
        dnList = dnDAO.searchSimpleDn5(fieldid, StringUtil.encodeHTML(strFieldvalue), kind);
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Dn:searchSimple-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("dnList", dnList);
    return true;
  }
}
