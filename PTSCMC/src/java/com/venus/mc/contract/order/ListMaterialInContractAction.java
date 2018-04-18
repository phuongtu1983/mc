package com.venus.mc.contract.order;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
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
    String conId = request.getParameter("conId");
    String matIds = request.getParameter("matIds");
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    ArrayList materialList = null;
    if (!GenericValidator.isBlankOrNull(conId))
    {
      ContractDAO contractDAO = new ContractDAO();
      try
      {
        int id = contractDAO.getContractId(conId);
        if (GenericValidator.isBlankOrNull(matIds))
        {
          if (ContractBean.KIND_ADJUSTMENT == kind) {
            materialList = contractDAO.getContractMaterialsAdjustmentNew(conId);
          }
          if (id > 0) {
            materialList = contractDAO.getContractMaterialsAdjustmentNew2(conId);
          } else {
            materialList = contractDAO.getContractMaterialsForAdjustment(conId);
          }
        }
        else
        {
          materialList = contractDAO.getContractMaterials(conId, matIds);
        }
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
