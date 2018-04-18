package com.venus.mc.contract;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchSimpleContractAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchFormBean contractForm = (SearchFormBean)form;
    ArrayList contractList = null;
    ContractDAO contractDAO = new ContractDAO();
    int kind = 0;
    if (!GenericValidator.isBlankOrNull(request.getParameter("kind"))) {
      kind = Integer.parseInt(request.getParameter("kind"));
    }
    String strKind = "0";
    try
    {
      if (kind == ContractBean.KIND_CONTRACT) {
        strKind = kind + "," + ContractBean.KIND_APPENDIX;
      } else {
        strKind = kind + "";
      }
      OrganizationDAO orgDAO = new OrganizationDAO();
      int orgId = MCUtil.getOrganizationID(request.getSession());
      String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgDAO.getnestedParentOfOrg(new StringBuilder().append(orgId).append("").toString());
      orgs = orgs + "," + orgId;
      contractDAO.setRequestOrg(orgs);
      contractDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
      contractList = contractDAO.searchSimpleContract(contractForm.getSearchid(), contractForm.getSearchvalue(), strKind);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Contract:searchSimple-" + ex.getMessage());
      ex.printStackTrace();
    }
    if (contractList == null) {
      contractList = new ArrayList();
    }
    ContractFormBean contractBean = null;
    long MILLSECS_PER_DAY = 86400000L;
    long delta = 0L;
    Date today = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "dd/MM/yyyy");
    for (int i = 0; i < contractList.size(); i++)
    {
      contractBean = (ContractFormBean)contractList.get(i);
      contractBean.setColor("");
      if (contractBean.getProcessStatus() != ContractFormBean.PROCESS_STATUS_DELIVERIED)
      {
        Date deliveryDate = DateUtil.transformerStringEnDate(contractBean.getDeliveryDate(), "dd/MM/yyyy");
        if (deliveryDate != null)
        {
          delta = deliveryDate.getTime() - today.getTime();
          delta /= MILLSECS_PER_DAY;
          if (delta >= 0L)
          {
            if (delta <= 7L) {
              contractBean.setColor("yellow");
            }
          }
          else {
            contractBean.setColor("red");
          }
        }
      }
    }
    request.setAttribute("contractList", contractList);
    if (kind == ContractBean.KIND_CONTRACT) {
      this.actionForwardResult = "contract";
    } else if (kind == ContractBean.KIND_PRINCIPLE) {
      this.actionForwardResult = "principle";
    } else if (kind == ContractBean.KIND_ORDER) {
      this.actionForwardResult = "order";
    }
    return true;
  }
}
