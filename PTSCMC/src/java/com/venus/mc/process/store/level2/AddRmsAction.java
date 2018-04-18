package com.venus.mc.process.store.level2;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.ReturnedMaterialDiaryBean;
import com.venus.mc.bean.ReturnedMaterialDiaryDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AddRmsAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    ReturnedMaterialStore2FormBean formBean = (ReturnedMaterialStore2FormBean)form;
    HttpSession session = request.getSession();
    ReturnedMaterialDiaryBean bean = null;
    boolean bNew = false;
    
    int rmsId = formBean.getRmsId();
    if (rmsId == 0) {
      bNew = true;
    } else {
      bNew = false;
    }
    bean = new ReturnedMaterialDiaryBean();
    bean.setRmsId(rmsId);
    bean.setSto2Id(formBean.getSto2Id());
    bean.setUpdateDate(formBean.getUpdateDate());
    bean.setOrgId(formBean.getOrgId());
    bean.setProId(formBean.getProId());
    bean.setCreatedEmp(formBean.getCreatedEmp());
    bean.setRsmNumber(formBean.getRsmNumber());
    try
    {
      StoreDAO storeDAO = new StoreDAO();
      if (bNew)
      {
        rmsId = storeDAO.insertReturnedMaterialStore(bean);
      }
      else
      {
        OnlineUser user = MCUtil.getOnlineUser(session);
        LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
        storeDAO.updateReturnedMaterialStore(bean);
      }
      session.setAttribute("id", Integer.valueOf(rmsId));
      if (rmsId != 0)
      {
        ArrayList arrDet = null;
        try
        {
          arrDet = storeDAO.getReturnedMaterialStoreDetails(rmsId);
        }
        catch (Exception ex)
        {
          arrDet = new ArrayList();
        }
        if (formBean.getRmsDetId() != null)
        {
          String[] detIds = formBean.getRmsDetId();
          String[] matIds = formBean.getMatId();
          ReturnedMaterialDiaryDetailBean detBean = null;
          double quantity = 0.0D;
          double curquantity = 0.0D;
          boolean canUpdate = false;
          for (int i = 0; i < detIds.length; i++) {
            if (!detIds[i].equals("0")) {
              try
              {
                detBean = detExisted(arrDet, Integer.parseInt(detIds[i]));
                if (detBean != null)
                {
                  quantity = Double.parseDouble(formBean.getReturnedQuantity()[i]);
                  if (quantity != detBean.getReturnedQuantity())
                  {
                    detBean.setReturnedQuantity(quantity);
                    canUpdate = true;
                  }
                  if (!detBean.getNote().equals(formBean.getNote()[i]))
                  {
                    detBean.setNote(formBean.getNote()[i]);
                    canUpdate = true;
                  }
                  if (!detBean.getStatus().equals(formBean.getStatus()[i]))
                  {
                    detBean.setStatus(formBean.getStatus()[i]);
                    canUpdate = true;
                  }
                  if (canUpdate) {
                    storeDAO.updateReturnedMaterialStoreDetail(detBean);
                  }
                }
              }
              catch (Exception localException1) {}
            } else {
              try
              {
                if (GenericValidator.isBlankOrNull(formBean.getCurrentQuantity()[i])) {
                  curquantity = 0.0D;
                } else {
                  curquantity = Double.parseDouble(formBean.getCurrentQuantity()[i]);
                }
                if (GenericValidator.isBlankOrNull(formBean.getReturnedQuantity()[i])) {
                  quantity = 0.0D;
                } else {
                  quantity = Double.parseDouble(formBean.getReturnedQuantity()[i]);
                }
                detBean = new ReturnedMaterialDiaryDetailBean(0, quantity);
                detBean.setMatId(Integer.parseInt(matIds[i]));
                detBean.setReqDetailId(Integer.parseInt(formBean.getReqDetailId()[i]));
                detBean.setRmsId(rmsId);
                detBean.setUmsDetId(Integer.parseInt(formBean.getUmsDetId()[i]));
                detBean.setCurrentQuantity(curquantity);
                detBean.setReturnedQuantity(quantity);
                
                detBean.setNote(formBean.getNote()[i]);
                detBean.setStatus(formBean.getStatus()[i]);
                storeDAO.insertReturnedMaterialStoreDetail(detBean);
              }
              catch (Exception localException2) {}
            }
          }
        }
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Rms:add-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  private ReturnedMaterialDiaryDetailBean detExisted(ArrayList arrDet, int detId)
  {
    ReturnedMaterialDiaryDetailBean detail = null;
    for (int i = 0; i < arrDet.size(); i++)
    {
      detail = (ReturnedMaterialDiaryDetailBean)arrDet.get(i);
      if (detail.getDetId() == detId)
      {
        arrDet.remove(detail);
        ReturnedMaterialDiaryDetailBean bean = detail;
        return bean;
      }
    }
    return null;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_EDIT + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_STOCK_STORE2;
  }
}
