package com.venus.mc.deliveryrequest;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListDeliveryContractAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String venId = request.getParameter("venId");
    boolean isExpire = false;
    if (!GenericValidator.isBlankOrNull(request.getParameter("expire"))) {
      isExpire = true;
    }
    ArrayList arrContract = null;
    if (!GenericValidator.isBlankOrNull(venId))
    {
      ContractDAO contractDAO = new ContractDAO();
      try
      {
        arrContract = contractDAO.getContractPrinciplesOfVendor(venId, isExpire);
      }
      catch (Exception localException) {}
    }
    if (arrContract == null) {
      arrContract = new ArrayList();
    }
    if ((arrContract.size() > 0) && 
      (GenericValidator.isBlankOrNull(request.getParameter("select")))) {
      arrContract.add(0, new ContractBean(0));
    }
    request.setAttribute("contractList", arrContract);
    return true;
  }
}
