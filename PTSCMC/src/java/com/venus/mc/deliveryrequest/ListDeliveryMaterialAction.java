package com.venus.mc.deliveryrequest;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListDeliveryMaterialAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String conId = request.getParameter("conId");
    String matId = request.getParameter("matId");
    String reqDetailIds = request.getParameter("reqDetailIds");
    ArrayList materialList = null;
    if ((!GenericValidator.isBlankOrNull(conId)) && (!GenericValidator.isBlankOrNull(matId)))
    {
      ContractDAO contractDAO = new ContractDAO();
      try
      {
        materialList = contractDAO.getMaterialsOfContract(matId, conId, reqDetailIds);
      }
      catch (Exception localException) {}
    }
    if (materialList == null) {
      materialList = new ArrayList();
    }
    request.setAttribute("materialList", materialList);
    return true;
  }
}
