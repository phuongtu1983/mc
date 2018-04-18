package com.venus.mc.process.store.level2;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.UsedMaterialDiaryBean;
import com.venus.mc.bean.UsedMaterialDiaryDetailBean;
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

public class AddUmsAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    UsedMaterialStore2FormBean formBean = (UsedMaterialStore2FormBean)form;
    HttpSession session = request.getSession();
    UsedMaterialDiaryBean bean = null;
    boolean bNew = false;
    
    int umsId = formBean.getUmsId();
    if (umsId == 0) {
      bNew = true;
    } else {
      bNew = false;
    }
    bean = new UsedMaterialDiaryBean();
    bean.setUmsId(umsId);
    bean.setSto2Id(formBean.getSto2Id());
    bean.setUpdateDate(formBean.getUpdateDate());
    bean.setOrgId(formBean.getOrgId());
    bean.setProId(formBean.getProId());
    bean.setCreatedEmp(formBean.getCreatedEmp());
    bean.setUsmNumber(formBean.getUsmNumber());
    bean.setLocation(formBean.getLocation());
    try
    {
      StoreDAO storeDAO = new StoreDAO();
      if (bNew)
      {
        umsId = storeDAO.insertUsedMaterialStore(bean);
      }
      else
      {
        OnlineUser user = MCUtil.getOnlineUser(session);
        LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
        storeDAO.updateUsedMaterialStore(bean);
      }
      session.setAttribute("id", Integer.valueOf(umsId));
      if (umsId != 0)
      {
        ArrayList arrDet = null;
        try
        {
          arrDet = storeDAO.getUsedMaterialStoreDetails(umsId);
        }
        catch (Exception ex)
        {
          arrDet = new ArrayList();
        }
        if (formBean.getUmsDetId() != null)
        {
          String[] detIds = formBean.getUmsDetId();
          String[] matIds = formBean.getMatId();
          UsedMaterialDiaryDetailBean detBean = null;
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
                  quantity = Double.parseDouble(formBean.getUsedQuantity()[i]);
                  if (quantity != detBean.getUsedQuantity())
                  {
                    detBean.setUsedQuantity(quantity);
                    canUpdate = true;
                  }
                  if (!detBean.getNote().equals(formBean.getNote()[i]))
                  {
                    detBean.setNote(formBean.getNote()[i]);
                    canUpdate = true;
                  }
                  if (canUpdate)
                  {
                    detBean.setStoId(formBean.getSto2Id());
                    storeDAO.updateUsedMaterialStoreDetail(detBean);
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
                if (GenericValidator.isBlankOrNull(formBean.getUsedQuantity()[i])) {
                  quantity = 0.0D;
                } else {
                  quantity = Double.parseDouble(formBean.getUsedQuantity()[i]);
                }
                detBean = new UsedMaterialDiaryDetailBean(0, quantity);
                detBean.setMatId(Integer.parseInt(matIds[i]));
                detBean.setReqDetailId(Integer.parseInt(formBean.getReqDetailId()[i]));
                detBean.setUmsId(umsId);
                detBean.setStoId(formBean.getSto2Id());
                detBean.setCurrentQuantity(curquantity);
                
                detBean.setMivId(NumberUtil.parseInt(formBean.getMivId()[i], 0));
                detBean.setNote(formBean.getNote()[i]);
                storeDAO.insertUsedMaterialStoreDetail(detBean);
              }
              catch (Exception localException2) {}
            }
          }
        }
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Ums:add-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  private UsedMaterialDiaryDetailBean detExisted(ArrayList arrDet, int detId)
  {
    UsedMaterialDiaryDetailBean detail = null;
    for (int i = 0; i < arrDet.size(); i++)
    {
      detail = (UsedMaterialDiaryDetailBean)arrDet.get(i);
      if (detail.getDetId() == detId)
      {
        arrDet.remove(detail);
        UsedMaterialDiaryDetailBean bean = detail;
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
