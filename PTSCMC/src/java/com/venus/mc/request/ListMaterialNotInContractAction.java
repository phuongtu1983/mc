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

public class ListMaterialNotInContractAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    ArrayList materialList = null;
    int fieldid = 0;
    String strFieldvalue = "";
    if (form != null)
    {
      SearchFormBean formBean = (SearchFormBean)form;
      fieldid = formBean.getSearchid();
      strFieldvalue = formBean.getSearchvalue();
    }
    if (GenericValidator.isBlankOrNull(request.getParameter("reload"))) {
      try
      {
        MaterialDAO materialDAO = new MaterialDAO();
        String reqDetailIds = "";
        materialDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_PURCHASING_REQUEST_MANAGER))
        {
          OrganizationDAO orgDAO = new OrganizationDAO();
          int orgId = MCUtil.getOrganizationID(request.getSession());
          String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
          orgs = orgs + "," + orgId;
          materialDAO.setRequestOrg(orgs);
        }
        materialList = materialDAO.getMaterialInContract(ContractBean.KIND_PRINCIPLE + "," + ContractBean.KIND_ADJUSTMENT + "," + ContractBean.KIND_CONTRACT + "," + ContractBean.KIND_ORDER, fieldid, strFieldvalue, false);
        
        reqDetailIds = reqDetailIds + getReqDetailIds(materialList);
        
        materialList = materialDAO.getMaterialNotInContract(reqDetailIds, fieldid, strFieldvalue);
        
        MaterialInContractFormBean bean = null;
        for (int i = 0; i < materialList.size(); i++)
        {
          bean = (MaterialInContractFormBean)materialList.get(i);
          
          bean.setPrice(materialDAO.getNearestPriceOfMaterial(bean.getMatId()));
        }
        String name = "";
        for (int i = materialList.size() - 1; i >= 0; i--)
        {
          bean = (MaterialInContractFormBean)materialList.get(i);
          name = bean.getAssignedEmployeeName();
          if (!GenericValidator.isBlankOrNull(name)) {
            bean.setAssignedEmployeeName(name.substring(name.lastIndexOf(" ")));
          }
        }
      }
      catch (Exception ex)
      {
        LogUtil.error("FAILED:NotInContract:listMaterial-" + ex.getMessage());
        ex.printStackTrace();
      }
    }
    if (materialList == null) {
      materialList = new ArrayList();
    }
    request.setAttribute("materialNotInContractList", materialList);
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
