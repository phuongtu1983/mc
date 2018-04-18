package com.venus.mc.process.store.msv;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MaterialStoreRequestBean;
import com.venus.mc.bean.MsvBean;
import com.venus.mc.bean.MsvMaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialStoreRequestDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.dao.MsvDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class AddMsvAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    MsvFormBean formBean = (MsvFormBean)form;
    MsvDAO msvDAO = new MsvDAO();
    MsvBean bean = null;
    boolean bNew = false;
    boolean isExist = false;
    try
    {
      bean = msvDAO.getMsvByNumber(formBean.getMsvNumber());
    }
    catch (Exception localException1) {}
    int msvId = formBean.getMsvId();
    if (msvId == 0)
    {
      bNew = true;
      if (bean != null) {
        isExist = true;
      }
    }
    else
    {
      bNew = false;
      if ((bean != null) && (bean.getMsvId() != msvId)) {
        isExist = true;
      }
    }
    if (isExist)
    {
      ActionMessages errors = new ActionMessages();
      errors.add("numberExisted", new ActionMessage("errors.duplicate.number"));
      saveErrors(request, errors);
      return false;
    }
    if (bNew) {
      bean = new MsvBean();
    } else {
      try
      {
        bean = msvDAO.getMsv(msvId);
      }
      catch (Exception localException2) {}
    }
    bean.setMsvNumber(formBean.getMsvNumber());
    bean.setMrirId(formBean.getMrirId());
    bean.setCreatedEmpId(MCUtil.getMemberID(session));
    bean.setStoId(formBean.getStoId());
    bean.setDescription(formBean.getDescription());
    bean.setKind(formBean.getKind());
    bean.setDeliverer(formBean.getDeliverer());
    bean.setType(0);
    if (bNew)
    {
      int[] matId = formBean.getMatId();
      int[] reqDetailId = formBean.getReqDetailId();
      String[] unit = formBean.getUnit();
      String[] quantity = formBean.getQuantity();
      String[] price = formBean.getPrice();
      int len = matId.length;
      double sumAll = 0.0D;
      ArrayList arrMaterial = new ArrayList();
      
      MrirDAO mrirDAO = new MrirDAO();
      int mrirDetId = 0;
      for (int i = 0; i < len; i++)
      {
        MsvMaterialBean matBean = new MsvMaterialBean();
        matBean.setMatId(matId[i]);
        matBean.setReqDetailId(reqDetailId[i]);
        matBean.setStoId(formBean.getStoId());
        matBean.setUnit(unit[i]);
        try
        {
          mrirDetId = mrirDAO.getMrirDetail(formBean.getMrirId(), matBean.getReqDetailId());
        }
        catch (Exception localException3) {}
        matBean.setMrirDetId(mrirDetId);
        
        double qty = NumberUtil.parseDouble(quantity[i].replaceAll(",", ""), 0.0D);
        matBean.setQuantity(qty);
        double pri = NumberUtil.parseDouble(price[i].replaceAll(",", ""), 0.0D);
        matBean.setPrice(pri);
        double tot = pri * qty;
        matBean.setTotal(tot);
        sumAll += tot;
        arrMaterial.add(matBean);
      }
      bean.setTotal(sumAll);
      try
      {
        msvId = msvDAO.insertMsv(bean);
      }
      catch (Exception ex)
      {
        LogUtil.error(AddMsvAction.class + ": " + ex.getMessage());
      }
      session.setAttribute("id", Integer.valueOf(msvId));
      if (msvId > 0)
      {
        int detId = 0;
        
        MsvMaterialBean beanMaterial = null;
        for (int i = 0; i < arrMaterial.size(); i++)
        {
          beanMaterial = (MsvMaterialBean)arrMaterial.get(i);
          beanMaterial.setMsvId(msvId);
          try
          {
            detId = msvDAO.insertMsvMaterial(beanMaterial);
          }
          catch (Exception ex)
          {
            LogUtil.error(AddMsvAction.class + ": " + ex.getMessage());
          }
          double temp = Double.parseDouble(beanMaterial.getQuantity() + "");
          updateMSR(beanMaterial.getMatId(), beanMaterial.getStoId(), beanMaterial.getPrice(), temp, beanMaterial.getReqDetailId(), msvId);
        }
        if (formBean.getMrirId() > 0) {
          try
          {
            mrirDAO.updateMrirStatus(formBean.getMrirId(), 1);
          }
          catch (Exception ex)
          {
            LogUtil.error(AddMsvAction.class + ": " + ex.getMessage());
          }
        }
      }
    }
    else
    {
      try
      {
        msvDAO.updateMsv(bean);
      }
      catch (Exception ex)
      {
        LogUtil.error(AddMsvAction.class + ": " + ex.getMessage());
      }
    }
    return true;
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
    if (msrBean == null)
    {
      try
      {
        msrDAO.insertMaterialStoreRequest(matId, stoId, price, quantity, reqDetailId, msvId);
      }
      catch (Exception ex)
      {
        LogUtil.error(AddMsvAction.class + ": " + ex.getMessage());
      }
    }
    else
    {
      msrBean.setActualQuantity(msrBean.getActualQuantity() + quantity);
      msrBean.setAvailableQuantity(msrBean.getAvailableQuantity() + quantity);
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
