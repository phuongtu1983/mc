package com.venus.mc.request;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListMaterialInContractAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    MaterialDAO materialDAO = new MaterialDAO();
    ArrayList materialList = null;
    int fieldid = 0;
    String strFieldvalue = "";
    if (form != null)
    {
      SearchFormBean formBean = (SearchFormBean)form;
      fieldid = formBean.getSearchid();
      strFieldvalue = formBean.getSearchvalue();
    }
    try
    {
      materialDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
      if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_MANAGER))
      {
        OrganizationDAO orgDAO = new OrganizationDAO();
        int orgId = MCUtil.getOrganizationID(request.getSession());
        String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
        orgs = orgs + "," + orgId;
        materialDAO.setRequestOrg(orgs);
      }
      materialList = materialDAO.getMaterialInContract(ContractBean.KIND_CONTRACT + "", fieldid, strFieldvalue, true);
      MaterialInContractFormBean bean = null;
      String name = "";
      for (int i = 0; i < materialList.size(); i++)
      {
        bean = (MaterialInContractFormBean)materialList.get(i);
        name = bean.getResponseEmployeeName();
        if (!GenericValidator.isBlankOrNull(name)) {
          bean.setResponseEmployeeName(name.substring(name.lastIndexOf(" ")));
        }
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Contract:listMaterial-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("materialInContractList", materialList);
    return true;
  }
}
