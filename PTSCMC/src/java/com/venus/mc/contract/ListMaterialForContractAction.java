package com.venus.mc.contract;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListMaterialForContractAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    String conId = request.getParameter("conId");
    String tenId = request.getParameter("tenId");
    String venId = request.getParameter("venId");
    String detIds = (String)session.getAttribute("detIds");
    session.removeAttribute("detIds");
    Integer id = (Integer)session.getAttribute("id");
    Integer ten_id = (Integer)session.getAttribute("ten_id");
    Integer ven_id = (Integer)session.getAttribute("ven_id");
    if (id != null) {
      conId = id + "";
    }
    if (ten_id != null) {
      tenId = ten_id + "";
    }
    if (ven_id != null) {
      venId = ven_id + "";
    }
    session.removeAttribute("id");
    session.removeAttribute("ten_id");
    session.removeAttribute("ven_id");
    ArrayList list = null;
    ContractDAO contractDAO = new ContractDAO();
    if (!GenericValidator.isBlankOrNull(conId))
    {
      try
      {
        ContractBean contract = contractDAO.getContract(Integer.parseInt(conId));
        if (contract.getKind() == ContractBean.KIND_ORDER)
        {
          if (contract.getParentId() != 0) {
            list = contractDAO.getContractMaterials(contract.getParentId() + "");
          }
        }
        else {
          list = contractDAO.getMaterialOfVendorInTenderPlan(contract.getTenId() + "", contract.getVenId() + "");
        }
      }
      catch (Exception localException) {}
      this.actionForwardResult = "order";
    }
    else if ((!GenericValidator.isBlankOrNull(tenId)) && (!GenericValidator.isBlankOrNull(venId)))
    {
      try
      {
        list = contractDAO.getMaterialOfVendorInTenderPlanNon(detIds);
      }
      catch (Exception localException1) {}
      this.actionForwardResult = "contract";
    }
    if (list == null) {
      list = new ArrayList();
    }
    request.setAttribute("materialList", list);
    return true;
  }
}
