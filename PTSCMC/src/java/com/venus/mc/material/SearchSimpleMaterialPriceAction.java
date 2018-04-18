package com.venus.mc.material;

import com.venus.core.util.LogUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchSimpleMaterialPriceAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchFormBean mpForm = (SearchFormBean)form;
    int fieldid = mpForm.getSearchid();
    String strFieldvalue = mpForm.getSearchvalue();
    ArrayList materialList = null;
    MaterialDAO materialDAO = new MaterialDAO();
    try
    {
      int orgId = MCUtil.getOrganizationID(request.getSession());
      OrganizationDAO orgDAO = new OrganizationDAO();
      String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgDAO.getnestedParentOfOrg(new StringBuilder().append(orgId).append("").toString());
      orgs = orgs + "," + orgId;
      materialDAO.setRequestOrg(orgs);
      String fromDate = request.getParameter("fromDate");
      String toDate = request.getParameter("toDate");
      materialList = materialDAO.searchMaterialPrices(fieldid, StringUtil.encodeHTML(strFieldvalue), fromDate, toDate);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:MaterialPrice:searchSimple-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("materialList", materialList);
    return true;
  }
}
