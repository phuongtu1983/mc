package com.venus.mc.contract;

import com.venus.core.util.DateUtil;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListContractAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    ContractDAO contractDAO = new ContractDAO();
    contractDAO.setInvolvedEmps(PermissionUtil.getEmployeesHasPermission(request));
    ArrayList contractList = null;
    try
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      int orgId = MCUtil.getOrganizationID(request.getSession());
      String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgDAO.getnestedParentOfOrg(new StringBuilder().append(orgId).append("").toString());
      orgs = orgs + "," + orgId;
      contractDAO.setRequestOrg(orgs);
      contractDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
      contractList = contractDAO.getContracts(ContractBean.KIND_CONTRACT);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Contract:list-" + ex.getMessage());
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
      Date deliveryDate = DateUtil.transformerStringEnDate(contractBean.getDeliveryDate(), "dd/MM/yyyy");
      if (deliveryDate != null)
      {
        delta = deliveryDate.getTime() - today.getTime();
        if (delta >= 0L)
        {
          delta /= MILLSECS_PER_DAY;
          if (delta <= 2L) {
            contractBean.setIsNeedHighLight(1);
          }
        }
      }
    }
    request.setAttribute("contractList", contractList);
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_LIST + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_PURCHASING_CONTRACT;
  }
}
