package com.venus.mc.tenderplan;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.request.MaterialInContractFormBean;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchMaterialAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchFormBean formBean = (SearchFormBean)form;
    int fieldid = formBean.getSearchid();
    String strFieldvalue = formBean.getSearchvalue();
    ArrayList materialList = null;
    String materialSource = request.getParameter("materialSource");
    if (GenericValidator.isBlankOrNull(materialSource)) {
      materialSource = "5";
    }
    try
    {
      MaterialDAO materialDAO = new MaterialDAO();
      materialDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
      if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_MANAGER))
      {
        OrganizationDAO orgDAO = new OrganizationDAO();
        int orgId = MCUtil.getOrganizationID(request.getSession());
        String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
        orgs = orgs + "," + orgId;
        materialDAO.setRequestOrg(orgs);
      }
      if (materialSource.equals("1"))
      {
        materialList = materialDAO.getMaterialInContractExpire(ContractBean.KIND_PRINCIPLE, fieldid, strFieldvalue);
      }
      else if (materialSource.equals("2"))
      {
        materialList = materialDAO.getMaterialInContract(ContractBean.KIND_CONTRACT + "", fieldid, strFieldvalue, true);
      }
      else if (materialSource.equals("3"))
      {
        materialList = materialDAO.getMaterialInContract(ContractBean.KIND_ORDER + "", fieldid, strFieldvalue, true);
      }
      else if (materialSource.equals("4"))
      {
        materialList = materialDAO.getMaterialInContract(ContractBean.KIND_PRINCIPLE + "", fieldid, strFieldvalue, true);
      }
      else
      {
        String reqDetailIds = "";
        materialList = materialDAO.getMaterialInContract(ContractBean.KIND_PRINCIPLE + "," + ContractBean.KIND_CONTRACT + "," + ContractBean.KIND_ORDER, fieldid, strFieldvalue, false);
        reqDetailIds = reqDetailIds + getReqDetailIds(materialList);
        
        materialList = materialDAO.getMaterialNotInContract(reqDetailIds, fieldid, strFieldvalue);
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:MaterialNotContract:search-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("materialList", materialList);
    return true;
  }
  
  private String getReqDetailIds(ArrayList materialList)
  {
    MaterialInContractFormBean bean = null;
    String result = "0";
    for (int i = 0; i < materialList.size(); i++)
    {
      bean = (MaterialInContractFormBean)materialList.get(i);
      result = result + "," + bean.getReqDetailId();
    }
    return result;
  }
}
