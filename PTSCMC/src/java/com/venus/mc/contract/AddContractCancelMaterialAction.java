package com.venus.mc.contract;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.ContractDetailBean;
import com.venus.mc.bean.RequestDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AddContractCancelMaterialAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    ContractFormBean formBean = (ContractFormBean)form;
    try
    {
      if (formBean.getConId() != 0)
      {
        cancelMaterial(formBean);
        updateProcessStatusContract(formBean);
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Contract:update canceled material-" + ex.getMessage());
      ex.printStackTrace();
    }
    if (formBean.getKind() == ContractBean.KIND_CONTRACT) {
      this.actionForwardResult = "contract";
    } else if (formBean.getKind() == ContractBean.KIND_PRINCIPLE) {
      this.actionForwardResult = "contractPrinciple";
    } else if (formBean.getKind() == ContractBean.KIND_ORDER) {
      this.actionForwardResult = "order";
    } else if (formBean.getKind() == ContractBean.KIND_APPENDIX) {
      this.actionForwardResult = "appendix";
    }
    return true;
  }
  
  private ContractDetailBean detExisted(ArrayList arrDet, int detId)
  {
    ContractDetailFormBean formBean = null;
    for (int i = 0; i < arrDet.size(); i++)
    {
      formBean = (ContractDetailFormBean)arrDet.get(i);
      if (formBean.getDetId() == detId)
      {
        arrDet.remove(formBean);
        ContractDetailBean bean = new ContractDetailBean(detId);
        bean.setQuantity(formBean.getQuantity());
        
        bean.setStatus(formBean.getMatStatus());
        bean.setReqDetailId(formBean.getReqDetailId());
        return bean;
      }
    }
    return null;
  }
  
  private void cancelMaterial(ContractFormBean formBean)
  {
    if (formBean.getConDetId() != null)
    {
      ContractDAO contractDAO = new ContractDAO();
      ArrayList arrDet = null;
      try
      {
        arrDet = contractDAO.getContractDetails(formBean.getConId());
      }
      catch (Exception localException) {}
      if (arrDet == null) {
        arrDet = new ArrayList();
      }
      String[] detIds = formBean.getConDetId();
      ContractDetailBean detBean = null;
      boolean canUpdate = false;
      for (int i = 0; i < detIds.length; i++)
      {
        canUpdate = false;
        if (!detIds[i].equals("0")) {
          try
          {
            detBean = detExisted(arrDet, Integer.parseInt(detIds[i]));
            if (detBean != null)
            {
              if (formBean.getConfirm() != null) {
                detBean.setConfirm(NumberUtil.parseInt(formBean.getConfirm()[i], 0));
              }
              contractDAO.updateContractDetailConfirm(detBean);
              contractDAO.updateRequestDetail(formBean.getConId());
              int oldStatus = 0;
              if ((formBean.getMatStatus() != null) && 
                (detBean.getStatus() != Integer.parseInt(formBean.getMatStatus()[i])))
              {
                oldStatus = detBean.getStatus();
                detBean.setStatus(Integer.parseInt(formBean.getMatStatus()[i]));
                canUpdate = true;
              }
              if (canUpdate)
              {
                contractDAO.updateContractDetailStatus(detBean);
                if (formBean.getKind() == ContractBean.KIND_ORDER)
                {
                  if ((oldStatus == ContractFormBean.MATERIAL_STATUS_NORMAL) || (detBean.getStatus() == ContractFormBean.MATERIAL_STATUS_CANCEL)) {
                    updateRemainQuantityOfRequestDetail(detBean.getReqDetailId(), detBean.getQuantity(), 1);
                  }
                }
                else if (((formBean.getKind() == ContractBean.KIND_CONTRACT) || (formBean.getKind() == ContractBean.KIND_DELIVERY_REQUEST) || (formBean.getKind() == ContractBean.KIND_APPENDIX)) && (
                  (oldStatus == ContractFormBean.MATERIAL_STATUS_NORMAL) || (detBean.getStatus() == ContractFormBean.MATERIAL_STATUS_CANCEL))) {
                  updateRemainQuantityOfRequestDetail(detBean.getReqDetailId(), detBean.getQuantity(), 1);
                }
              }
            }
          }
          catch (Exception localException1) {}
        }
      }
    }
  }
  
  private void updateRemainQuantityOfRequestDetail(int reqDetId, double changedQuantity, int sign)
  {
    RequestDAO reqDAO = new RequestDAO();
    if (reqDetId != 0) {
      try
      {
        RequestDetailBean bean = reqDAO.getRequestDetail(reqDetId);
        if (bean != null)
        {
          if (sign < 0) {
            bean.setRemainQuantity(bean.getRemainQuantity() - changedQuantity);
          } else {
            bean.setRemainQuantity(bean.getRemainQuantity() + changedQuantity);
          }
          reqDAO.updateRequestDetail(bean);
        }
      }
      catch (Exception localException) {}
    }
  }
  
  private void updateProcessStatusContract(ContractFormBean formBean)
  {
    if (formBean != null) {
      try
      {
        ContractDAO conDAO = new ContractDAO();
        ContractBean bean = conDAO.getContract(formBean.getConId());
        bean.setProcessStatus(formBean.getProcessStatus());
        bean.setProcessStatusText(formBean.getProcessStatusText());
        bean.setFollowEmp(formBean.getFollowEmp());
        bean.setDeliveryDate(formBean.getDeliveryDate());
        conDAO.updateContract(bean);
      }
      catch (Exception localException) {}
    }
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_EDIT + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_PURCHASING_CONTRACT;
  }
}
