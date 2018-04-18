package com.venus.mc.contract.order;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ComEvalMaterialDetailBean;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.contract.ContractFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class ListMaterialNotInContractAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    int matId = NumberUtil.parseInt(request.getParameter("matId"), 0);
    int detId = NumberUtil.parseInt(request.getParameter("detId"), 0);
    ArrayList materialList = null;
    if ((!GenericValidator.isBlankOrNull(request.getParameter("matIds"))) && (!GenericValidator.isBlankOrNull(request.getParameter("reqIds")))) {
      try
      {
        MaterialDAO materialDAO = new MaterialDAO();
        if (kind == ContractBean.KIND_ORDER) {
          materialDAO.updateContractDetailMatIdTempNew(matId, detId);
        } else if (kind == ContractBean.KIND_ADJUSTMENT) {
          materialDAO.updateContractDetailMatId(matId, detId);
        } else if (!GenericValidator.isBlankOrNull(request.getParameter("matId"))) {
          materialList = materialDAO.getMaterialsForOrder(request.getParameter("detIds"), request.getParameter("matIds"), request.getParameter("reqIds"), Integer.parseInt(request.getParameter("matId")), Integer.parseInt(request.getParameter("matIda")));
        } else {
          materialList = materialDAO.getMaterialsForOrder(request.getParameter("matIds"), request.getParameter("reqIds"));
        }
        ContractBean conBean = null;
        if (conBean == null) {
          conBean = new ContractBean();
        }
        conBean.setNote("");
        if (!GenericValidator.isBlankOrNull(request.getParameter("conKind")))
        {
          String kind1 = request.getParameter("conKind");
          if (kind1.equals("notincontract")) {
            conBean.setNote("muale");
          }
        }
        request.setAttribute("contract", conBean);
        
        materialDAO.deleteMaterialNotCode();
      }
      catch (Exception ex)
      {
        LogUtil.error("FAILED:NotInContract:listMaterial-" + ex.getMessage());
        ex.printStackTrace();
      }
    }
    if (materialList == null) {
      materialList = new ArrayList();
    }
    request.setAttribute("materialList", materialList);
    
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
    
    ArrayList arr = new ArrayList();
    
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.select"));
    value.setValue("0");
    arr.add(value);
    value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.confirm"));
    value.setValue(ComEvalMaterialDetailBean.RESULT1 + "");
    arr.add(value);
    
    request.setAttribute("evalTbeList", arr);
    
    request.setAttribute("detIds", request.getParameter("detIds"));
    
    request.setAttribute("matIds", request.getParameter("matIds"));
    
    request.setAttribute("reqIds", request.getParameter("reqIds"));
    return true;
  }
}
