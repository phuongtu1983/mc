package com.venus.mc.contract;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListContractDetailAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    ArrayList arrContractDetail = null;
    if (!GenericValidator.isBlankOrNull(request.getParameter("conId")))
    {
      try
      {
        ContractDAO contractDAO = new ContractDAO();
        arrContractDetail = contractDAO.getContractDetails(Integer.parseInt(request.getParameter("conId")));
      }
      catch (Exception localException) {}
    }
    else if (!GenericValidator.isBlankOrNull(request.getParameter("tenId")))
    {
      try
      {
        ContractDAO contractDAO = new ContractDAO();
        arrContractDetail = contractDAO.getContractDetailFromBidEvalSumDetails(Integer.parseInt(request.getParameter("tenId")));
      }
      catch (Exception localException1) {}
    }
    else if (!GenericValidator.isBlankOrNull(request.getParameter("matIds")))
    {
      String matIds = request.getParameter("matIds");
      String conIds = request.getParameter("conIds");
      String detIds = request.getParameter("detIds");
      try
      {
        ContractDAO contractDAO = new ContractDAO();
        arrContractDetail = contractDAO.getContractDetailsByMaterialIds(matIds, conIds);
      }
      catch (Exception localException2) {}
    }
    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {
      try
      {
        ContractDAO contractDAO = new ContractDAO();
        OrganizationDAO orgDAO = new OrganizationDAO();
        int orgId = MCUtil.getOrganizationID(request.getSession());
        String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
        orgs = orgs + "," + orgDAO.getnestedParentOfOrg(new StringBuilder().append(orgId).append("").toString());
        orgs = orgs + "," + orgId;
        contractDAO.setRequestOrg(orgs);
        contractDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
        if (contractDAO.isPermissionOnContractInfo(Integer.parseInt(request.getParameter("conId"))) > 0) {
          request.setAttribute("permission", Integer.valueOf(1));
        }
      }
      catch (Exception localException3) {}
    }
    if (arrContractDetail == null) {
      arrContractDetail = new ArrayList();
    }
    request.setAttribute("contractDetailList", arrContractDetail);
    int kind = 0;
    if (!GenericValidator.isBlankOrNull(request.getParameter("kind"))) {
      kind = Integer.parseInt(request.getParameter("kind"));
    }
    if (kind == ContractBean.KIND_CONTRACT) {
      this.actionForwardResult = "contract";
    } else if (kind == ContractBean.KIND_PRINCIPLE) {
      this.actionForwardResult = "contractPrinciple";
    } else if (kind == ContractBean.KIND_ORDER) {
      this.actionForwardResult = "order";
    }
    return true;
  }
}
