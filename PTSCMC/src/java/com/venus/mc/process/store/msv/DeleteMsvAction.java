package com.venus.mc.process.store.msv;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.MaterialStoreRequestBean;
import com.venus.mc.bean.MsvBean;
import com.venus.mc.bean.MsvMaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialStoreRequestDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.dao.MsvDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteMsvAction
  extends SpineAction
{
  private String result = "";
  
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    String[] arrMsvId = request.getParameterValues("msvId");
    OnlineUser user = MCUtil.getOnlineUser(session);
    LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
    int length = 0;
    String rfmNumber = "";
    MsvDAO msvDAO = new MsvDAO();
    MsvBean msvBean = null;
    int msvId = 0;int dnId = 0;
    if (arrMsvId != null) {
      length = arrMsvId.length;
    }
    for (int i = 0; i < length; i++)
    {
      msvId = NumberUtil.parseInt(arrMsvId[i], 0);
      if (msvId > 0)
      {
        try
        {
          msvBean = msvDAO.getMsv(msvId);
        }
        catch (Exception ex)
        {
          LogUtil.error(DeleteMsvAction.class.getName() + ": " + ex.getMessage());
        }
        rfmNumber = deleteMsv(msvDAO, msvBean);
        if (!StringUtil.isBlankOrNull(rfmNumber))
        {
          this.result = (this.result + "<br/>" + MCUtil.getBundleString("errors.delete"));
          this.result = (this.result + "<br/>" + MCUtil.getBundleString("message.please") + " " + MCUtil.getBundleString("message.del") + " " + MCUtil.getBundleString("message.rfm.rfmNumber") + " : " + rfmNumber);
          this.result = this.result.substring(5);
          return false;
        }
      }
    }
    return true;
  }
  
  private String deleteMsv(MsvDAO msvDAO, MsvBean msvBean)
  {
    if (msvBean == null) {
      return "";
    }
    ArrayList arrDetail = null;
    String rfmNumber = "";
    try
    {
      arrDetail = msvDAO.getMaterialsFromMsv(msvBean.getMsvId());
    }
    catch (Exception localException1) {}
    if (arrDetail != null)
    {
      for (int i = 0; i < arrDetail.size(); i++)
      {
        MsvMaterialBean matBean = (MsvMaterialBean)arrDetail.get(i);
        try
        {
          rfmNumber = msvDAO.checkMsvDetailStatus(matBean.getMatId(), matBean.getMsvId(), matBean.getReqDetailId());
          if (!StringUtil.isBlankOrNull(rfmNumber)) {
            return rfmNumber;
          }
        }
        catch (Exception localException2) {}
      }
      for (int i = 0; i < arrDetail.size(); i++)
      {
        MsvMaterialBean beanMaterial = (MsvMaterialBean)arrDetail.get(i);
        double temp = Double.parseDouble(beanMaterial.getQuantity() + "");
        if (msvBean.getType() == 1) {
          updateMSR(beanMaterial.getMatId(), beanMaterial.getStoId(), temp, beanMaterial.getReqDetailId());
        } else {
          updateMSR(beanMaterial.getMatId(), beanMaterial.getStoId(), beanMaterial.getPrice(), temp, beanMaterial.getReqDetailId(), beanMaterial.getMsvId());
        }
      }
    }
    try
    {
      msvDAO.deleteAllMsvDetail(msvBean.getMsvId());
    }
    catch (Exception localException3) {}
    try
    {
      msvDAO.deleteMsv(msvBean.getMsvId());
    }
    catch (Exception localException4) {}
    if (msvBean.getMrirId() > 0)
    {
      MrirDAO mrirDAO = new MrirDAO();
      try
      {
        mrirDAO.updateMrirStatus(msvBean.getMrirId(), 0);
      }
      catch (Exception ex)
      {
        LogUtil.error(AddMsvAction.class + ": " + ex.getMessage());
      }
    }
    return "";
  }
  
  private void updateMSR(int matId, int stoId, double price, double quantity, int reqDetailId, int msvId)
  {
    MaterialStoreRequestDAO msrDAO = new MaterialStoreRequestDAO();
    MaterialStoreRequestBean msrBean = null;
    try
    {
      msrBean = msrDAO.getMaterialStoreRequest(matId, stoId, reqDetailId, msvId);
    }
    catch (Exception ex)
    {
      LogUtil.error(AddMsvAction.class + ": " + ex.getMessage());
    }
    if (msrBean != null) {
      if ((msrBean.getActualQuantity() == quantity) && (msrBean.getAvailableQuantity() == quantity))
      {
        try
        {
          msrDAO.deleteMaterialStoreRequest(msrBean.getMsrId());
        }
        catch (Exception ex)
        {
          LogUtil.error(AddMsvAction.class + ": " + ex.getMessage());
        }
      }
      else
      {
        msrBean.setActualQuantity(msrBean.getActualQuantity() - quantity);
        msrBean.setAvailableQuantity(msrBean.getAvailableQuantity() - quantity);
        try
        {
          msrDAO.updateMaterialStoreRequest(msrBean);
        }
        catch (Exception ex)
        {
          LogUtil.error(AddMsvAction.class + ": " + ex.getMessage());
        }
      }
    }
  }
  
  private void updateMSR(int matId, int stoId, double quantity, int reqDetailId)
  {
    MaterialStoreRequestDAO msrDAO = new MaterialStoreRequestDAO();
    MaterialStoreRequestBean msrBean = null;
    try
    {
      msrBean = msrDAO.getMaterialStoreRequest(matId, stoId, reqDetailId, 0);
    }
    catch (Exception ex)
    {
      LogUtil.error(AddMsvAction.class + ": " + ex.getMessage());
    }
    if (msrBean != null) {
      if ((msrBean.getActualQuantity() == quantity) && (msrBean.getAvailableQuantity() == quantity))
      {
        try
        {
          msrDAO.deleteMaterialStoreRequest(msrBean.getMsrId());
        }
        catch (Exception ex)
        {
          LogUtil.error(AddMsvAction.class + ": " + ex.getMessage());
        }
      }
      else
      {
        msrBean.setActualQuantity(msrBean.getActualQuantity() - quantity);
        msrBean.setAvailableQuantity(msrBean.getAvailableQuantity() - quantity);
        try
        {
          msrDAO.updateMaterialStoreRequest(msrBean);
        }
        catch (Exception ex)
        {
          LogUtil.error(AddMsvAction.class + ": " + ex.getMessage());
        }
      }
    }
  }
  
  protected String getErrorsString(HttpServletRequest request)
  {
    return this.result;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_DELETE + "";
  }
}
