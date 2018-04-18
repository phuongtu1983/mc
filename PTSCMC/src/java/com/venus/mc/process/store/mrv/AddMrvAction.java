package com.venus.mc.process.store.mrv;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MaterialStoreRequestBean;
import com.venus.mc.bean.MsvBean;
import com.venus.mc.bean.MsvMaterialBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialStoreRequestDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.dao.MsvDAO;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.process.store.msv.MsvFormBean;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class AddMrvAction
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
    int dnId = 0;
    try
    {
      bean = msvDAO.getMsvByNumber(formBean.getMsvNumber());
      dnId = msvDAO.getDnId(formBean.getMrirId());
    }
    catch (Exception ex)
    {
      LogUtil.error(AddMrvAction.class + ": " + ex.getMessage());
    }
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
    bean = new MsvBean();
    bean.setMsvId(msvId);
    bean.setMsvNumber(formBean.getMsvNumber());
    bean.setMrirId(formBean.getMrirId());
    bean.setCreatedEmpId(MCUtil.getMemberID(session));
    bean.setStoId(formBean.getStoId());
    bean.setDescription(formBean.getDescription());
    bean.setOrgId(formBean.getOrgId());
    bean.setProId(formBean.getProId());
    bean.setDeliveryEmpId(formBean.getDeliveryEmpId());
    bean.setMivNumbers(formBean.getMivNumbers());
    bean.setType(1);
    bean.setDnId(dnId);
    if (bNew)
    {
      int[] matId = formBean.getMatId();
      
      String[] unit = formBean.getUnit();
      String[] quantity = formBean.getQuantity();
      String[] price = formBean.getPrice();
      if (matId == null)
      {
        this.strErrors = "Errors";
        return false;
      }
      int len = matId.length;
      double sumAll = 0.0D;
      ArrayList arrMaterial = new ArrayList();
      
      MrirDAO mrirDAO = new MrirDAO();
      int mrirDetId = 0;
      for (int i = 0; i < len; i++)
      {
        MsvMaterialBean matBean = new MsvMaterialBean();
        matBean.setMatId(matId[i]);
        matBean.setStoId(formBean.getStoId());
        matBean.setReqDetailId(formBean.getReqDetailId()[i]);
        matBean.setUnit(unit[i]);
        try
        {
          mrirDetId = mrirDAO.getMrirDetail(formBean.getMrirId(), matBean.getReqDetailId());
        }
        catch (Exception localException1) {}
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
        LogUtil.error(AddMrvAction.class + ": " + ex.getMessage());
      }
      session.setAttribute("id", Integer.valueOf(msvId));
      if (msvId > 0)
      {
        int detId = 0;
        for (int i = 0; i < arrMaterial.size(); i++)
        {
          MsvMaterialBean beanMaterial = (MsvMaterialBean)arrMaterial.get(i);
          beanMaterial.setMsvId(msvId);
          try
          {
            detId = msvDAO.insertMsvMaterial(beanMaterial);
          }
          catch (Exception ex)
          {
            LogUtil.error(AddMrvAction.class + ": " + ex.getMessage());
          }
          MaterialStoreRequestBean msrBean = null;
          MaterialStoreRequestDAO msrDAO = new MaterialStoreRequestDAO();
          try
          {
            msrBean = msrDAO.getMaterialStoreRequest(beanMaterial.getMatId(), beanMaterial.getStoId(), beanMaterial.getReqDetailId(), beanMaterial.getMsvId());
          }
          catch (Exception ex)
          {
            LogUtil.error(AddMrvAction.class + ": " + ex.getMessage());
          }
          if (msrBean == null) {
            try
            {
              double temp = Double.parseDouble(beanMaterial.getQuantity() + "");
              msrDAO.insertMaterialStoreRequest(beanMaterial.getMatId(), beanMaterial.getStoId(), beanMaterial.getPrice(), temp, beanMaterial.getReqDetailId(), beanMaterial.getMsvId());
            }
            catch (Exception ex)
            {
              LogUtil.error(AddMrvAction.class + ": " + ex.getMessage());
            }
          } else {
            try
            {
              double temp = Double.parseDouble(beanMaterial.getQuantity() + "");
              msrBean.setActualQuantity(msrBean.getActualQuantity() + temp);
              msrBean.setAvailableQuantity(msrBean.getAvailableQuantity() + temp);
              msrDAO.updateMaterialStoreRequest(msrBean);
              
              StoreDAO storeDAO = new StoreDAO();
              storeDAO.updateMaterialStore2(-temp, beanMaterial.getReqDetailId(), beanMaterial.getStoId());
            }
            catch (Exception ex)
            {
              LogUtil.error(AddMrvAction.class + ": " + ex.getMessage());
            }
          }
        }
        if (formBean.getMrirId() > 0) {
          try
          {
            mrirDAO.updateMrirStatus(formBean.getMrirId(), 1);
          }
          catch (Exception ex)
          {
            LogUtil.error(AddMrvAction.class + ": " + ex.getMessage());
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
        LogUtil.error(AddMrvAction.class + ": " + ex.getMessage());
      }
    }
    return true;
  }
}
