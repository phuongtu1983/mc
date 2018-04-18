package com.venus.mc.process.purchasing.deliverynotice;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.DnDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListDnMaterialAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String detIds = request.getParameter("detIds");
    int conId = NumberUtil.parseInt(request.getParameter("conId"), 0);
    int drId = NumberUtil.parseInt(request.getParameter("drId"), 0);
    if (drId > 0) {
      conId = drId;
    }
    ArrayList materialList = null;
    if (!GenericValidator.isBlankOrNull(detIds))
    {
      DnDAO dnDAO = new DnDAO();
      try
      {
        ContractDAO conDAO = new ContractDAO();
        int id = 0;
        id = conDAO.getContractId(conId + "");
        if (drId > 0) {
          materialList = dnDAO.getMaterialsOfContract(detIds, conId);
        } else if (conId > 0)
        {
          if (id > 0)
          {
            materialList = dnDAO.getMaterialsOfDn(conId, detIds);
            if (materialList.isEmpty()) {
              materialList = dnDAO.getMaterialsOfDn(id, detIds);
            }
          }
          else
          {
            materialList = dnDAO.getMaterialsOfDn(conId, detIds);
          }
        }
        else {
          materialList = dnDAO.getMaterials(detIds);
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
