package com.venus.mc.deliveryrequest;

import com.venus.core.util.DateUtil;
import com.venus.core.util.LogUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.contract.ContractFormBean;
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

public class SearchSimpleDeliveryRequestAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SearchFormBean vendorForm = (SearchFormBean)form;
    int fieldid = vendorForm.getSearchid();
    String strFieldvalue = vendorForm.getSearchvalue();
    ContractDAO contractDAO = new ContractDAO();
    ArrayList deliveryList = null;
    try
    {
      OrganizationDAO orgDAO = new OrganizationDAO();
      int orgId = MCUtil.getOrganizationID(request.getSession());
      String orgs = orgDAO.getnestedChildOfOrg(orgId + "");
      orgs = orgs + "," + orgId;
      contractDAO.setRequestOrg(orgs);
      
      contractDAO.setRequestEmp(MCUtil.getMemberID(request.getSession()));
      deliveryList = contractDAO.searchSimpleContract(fieldid, StringUtil.encodeHTML(strFieldvalue), ContractBean.KIND_DELIVERY_REQUEST + "");
      ContractFormBean contractBean = null;
      long MILLSECS_PER_DAY = 86400000L;
      long delta = 0L;
      Date today = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "dd/MM/yyyy");
      for (int i = 0; i < deliveryList.size(); i++)
      {
        contractBean = (ContractFormBean)deliveryList.get(i);
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
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:DeliveryRequest:searchSimple-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("contractList", deliveryList);
    return true;
  }
}
