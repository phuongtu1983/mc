package com.venus.mc.process.store.rfm;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.RfmDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchSimpleRfmAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchFormBean formBean = (SearchFormBean)form;
    int fieldid = formBean.getSearchid();
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    String strFieldvalue = formBean.getSearchvalue();
    String strFieldvalues = formBean.getSearchvalues();
    RfmDAO rfmDAO = new RfmDAO();
    ArrayList rfmList = null;
    boolean ok = false;
    try
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      int orgId = MCUtil.getOrganizationID(request.getSession());
      String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgId;
      rfmDAO.setRequestOrg(orgs);
      rfmDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
      if (PermissionUtil.getEmployeesHasPermission(request, PermissionUtil.PER_STOCK_ORG)) {
        ok = true;
      }
      rfmList = rfmDAO.searchSimpleRfm(fieldid, StringUtil.encodeHTML(strFieldvalue), strFieldvalues, kind, ok);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Rfm:searchSimple-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("rfmList", rfmList);
    return true;
  }
}
