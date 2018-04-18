package com.venus.mc.contract.order;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.contract.ContractFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class ListOrderContractMaterialNotInvoiceAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String conId = request.getParameter("conId");
    String matId = request.getParameter("matId");
    String reqDetailIds = request.getParameter("reqDetailIds");
    String orderMaterialSource = request.getParameter("orderMaterialSource");
    ArrayList materialList = null;
    if ((!GenericValidator.isBlankOrNull(conId)) && (!GenericValidator.isBlankOrNull(matId)))
    {
      ContractDAO contractDAO = new ContractDAO();
      try
      {
        materialList = contractDAO.getContractDetailsByMaterialIdsNotInvoice(matId, conId, reqDetailIds);
        String[] conIds = conId.split(",");
        int c = 0;
        if (conIds.length > 0) {
          c = Integer.parseInt(conIds[0]);
        }
        ContractBean conBean = contractDAO.getContract(c);
        if (conBean == null) {
          conBean = new ContractBean();
        }
        request.setAttribute("contract", conBean);
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
    }
    if (materialList == null) {
      materialList = new ArrayList();
    }
    request.setAttribute("materialList", materialList);
    
    request.setAttribute("orderSource", orderMaterialSource);
    
    ArrayList arrMaterialStatus = new ArrayList();
    LabelValueBean value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.empty"));
    value.setValue(ContractFormBean.MATERIAL_STATUS_NORMAL + "");
    arrMaterialStatus.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.cancel"));
    value.setValue(ContractFormBean.MATERIAL_STATUS_CANCEL + "");
    arrMaterialStatus.add(value);
    request.setAttribute("principleMaterialKindList", arrMaterialStatus);
    
    ArrayList arrMaterialCancelStatus = new ArrayList();
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.cancel"));
    value.setValue(ContractFormBean.MATERIAL_STATUS_CANCEL + "");
    arrMaterialCancelStatus.add(value);
    request.setAttribute("principleMaterialCancelKindList", arrMaterialCancelStatus);
    
    return true;
  }
}
