package com.venus.mc.deliveryrequest;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.DeliveryRequestBean;
import com.venus.mc.bean.DeliveryRequestDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class AddDeliveryRequestAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    DeliveryRequestFormBean formBean = (DeliveryRequestFormBean)form;
    ContractDAO contractDAO = new ContractDAO();
    DeliveryRequestBean bean = null;
    boolean bNew = false;
    boolean isExist = false;
    try
    {
      bean = contractDAO.getDeliveryRequestByNumber(formBean.getDeliveryNumber());
    }
    catch (Exception localException1) {}
    int drId = formBean.getDrId();
    if (drId == 0)
    {
      bNew = true;
      if (bean != null) {
        isExist = true;
      }
    }
    else
    {
      bNew = false;
      if ((bean != null) && (bean.getDrId() != drId)) {
        isExist = true;
      }
    }
    if (isExist)
    {
      ActionMessages errors = new ActionMessages();
      errors.add("deliveryRequestExisted", new ActionMessage("errors.deliveryrequest.existed"));
      saveErrors(request, errors);
      return false;
    }
    bean = new DeliveryRequestBean();
    bean.setDrId(formBean.getDrId());
    bean.setVenId(formBean.getVenId());
    bean.setDeliveryNumber(formBean.getDeliveryNumber());
    bean.setDeliveryDate(formBean.getDeliveryDate());
    bean.setNote(formBean.getNote());
    bean.setSumVAT(formBean.getSumVAT());
    bean.setTotalNotVAT(formBean.getTotalNotVAT());
    bean.setVolume(formBean.getVolume());
    double temp = formBean.getTotal();
    bean.setTotal(temp);
    bean.setSignDate(formBean.getSignDate());
    bean.setFollowEmp(formBean.getFollowEmp());
    bean.setProcessStatus(formBean.getProcessStatus());
    bean.setProcessStatusText(formBean.getProcessStatusText());
    bean.setDelivery(formBean.getDelivery());
    try
    {
      if (bNew)
      {
        bean.setResponseEmp(MCUtil.getMemberID(request.getSession()));
        bean.setCreatedEmp(MCUtil.getMemberID(request.getSession()));
        drId = contractDAO.insertDeliveryRequest(bean);
      }
      else
      {
        OnlineUser user = MCUtil.getOnlineUser(session);
        LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
        contractDAO.updateDeliveryRequest(bean);
      }
      session.setAttribute("id", Integer.valueOf(drId));
      if (drId != 0)
      {
        ArrayList arrDet = null;
        try
        {
          arrDet = contractDAO.getDeliveryRequestDetails(drId);
        }
        catch (Exception ex)
        {
          arrDet = new ArrayList();
        }
        if (formBean.getDelDetId() != null)
        {
          String[] detIds = formBean.getDelDetId();
          String[] matIds = formBean.getMatId();
          DeliveryRequestDetailBean detBean = null;
          boolean canUpdate = false;
          double quantity = 0.0D;
          double price = 0.0D;
          double total = 0.0D;
          double vat = 0.0D;
          for (int i = 0; i < detIds.length; i++)
          {
            canUpdate = false;
            if (!detIds[i].equals("0")) {
              try
              {
                detBean = detExisted(arrDet, Integer.parseInt(detIds[i]));
                if (detBean != null)
                {
                  quantity = NumberUtil.parseDouble(formBean.getQuantity()[i], 0.0D);
                  if (detBean.getQuantity() != quantity)
                  {
                    detBean.setQuantity(quantity);
                    canUpdate = true;
                  }
                  vat = NumberUtil.parseDouble(formBean.getVat()[i], 0.0D);
                  if (detBean.getVat() != vat)
                  {
                    detBean.setVat(vat);
                    canUpdate = true;
                  }
                  price = NumberUtil.parseDouble(formBean.getPrice()[i], 0.0D);
                  if (detBean.getPrice() != price)
                  {
                    detBean.setPrice(price);
                    canUpdate = true;
                  }
                  total = NumberUtil.parseDouble(formBean.getDetTotal()[i], 0.0D);
                  if (detBean.getTotal() != total)
                  {
                    detBean.setTotal(total);
                    canUpdate = true;
                  }
                  if (canUpdate) {
                    contractDAO.updateDeliveryRequestDetail(detBean);
                  }
                }
              }
              catch (Exception localException2) {}
            } else {
              try
              {
                if (GenericValidator.isBlankOrNull(formBean.getQuantity()[i])) {
                  quantity = 0.0D;
                } else {
                  quantity = Double.parseDouble(formBean.getQuantity()[i]);
                }
                if (GenericValidator.isBlankOrNull(formBean.getPrice()[i])) {
                  price = 0.0D;
                } else {
                  price = Double.parseDouble(formBean.getPrice()[i]);
                }
                if (GenericValidator.isBlankOrNull(formBean.getVat()[i])) {
                  vat = 0.0D;
                } else {
                  vat = Double.parseDouble(formBean.getVat()[i]);
                }
                if (GenericValidator.isBlankOrNull(formBean.getDetTotal()[i])) {
                  total = 0.0D;
                } else {
                  total = Double.parseDouble(formBean.getDetTotal()[i]);
                }
                detBean = new DeliveryRequestDetailBean(0, quantity, formBean.getUnit()[i], price, vat, "", total);
                detBean.setDerId(drId);
                detBean.setMatId(Integer.parseInt(matIds[i]));
                detBean.setConDetailId(Integer.parseInt(formBean.getConDetId()[i]));
                if (formBean.getReqDetId() != null) {
                  detBean.setReqDetailId(Integer.parseInt(formBean.getReqDetId()[i]));
                }
                contractDAO.insertDeliveryRequestDetail(detBean);
              }
              catch (Exception localException3) {}
            }
          }
        }
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:DeliveryRequest:add-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  private DeliveryRequestDetailBean detExisted(ArrayList arrDet, int detId)
  {
    DeliveryRequestDetailFormBean formBean = null;
    for (int i = 0; i < arrDet.size(); i++)
    {
      formBean = (DeliveryRequestDetailFormBean)arrDet.get(i);
      if (formBean.getDetId() == detId)
      {
        arrDet.remove(formBean);
        DeliveryRequestDetailBean bean = new DeliveryRequestDetailBean(formBean.getDetId());
        
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
    return PermissionUtil.PER_PURCHASING_DELIVERYREQUEST;
  }
}
