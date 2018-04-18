package com.venus.mc.deliveryrequest;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListMaterialDeliveryContractAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    ArrayList list = null;
    ContractDAO contractDAO = new ContractDAO();
    if (!GenericValidator.isBlankOrNull(request.getParameter("conId"))) {
      try
      {
        String matIds = request.getParameter("matIds");
        if (GenericValidator.isBlankOrNull(matIds)) {
          list = contractDAO.getContractMaterials(request.getParameter("conId"));
        } else {
          list = contractDAO.getContractMaterials(request.getParameter("conId"), matIds);
        }
      }
      catch (Exception localException) {}
    }
    if (list == null) {
      list = new ArrayList();
    }
    request.setAttribute("materialList", list);
    return true;
  }
}
