package com.venus.mc.process.store.rfm;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.RfmBean;
import com.venus.mc.bean.RfmDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RfmDAO;
import com.venus.mc.request.RequestFormBean;
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

public class AddRfmAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    RfmFormBean formBean = (RfmFormBean)form;
    RfmDAO rfmDAO = new RfmDAO();
    RfmBean bean = null;
    boolean bNew = false;
    boolean isExist = false;
    int status = 0;
    try
    {
      bean = rfmDAO.getRfm(formBean.getRfmId(), kind, MCUtil.getMemberID(request.getSession()));
    }
    catch (Exception localException1) {}
    int rfmId = formBean.getRfmId();
    if (rfmId == 0)
    {
      bNew = true;
      if (bean != null) {
        isExist = true;
      }
    }
    else
    {
      bNew = false;
      status = bean.getStatusReserveQuantity();
      if ((bean != null) && (bean.getRfmId() != rfmId)) {
        isExist = true;
      }
    }
    if (isExist)
    {
      ActionMessages errors = new ActionMessages();
      errors.add("rfmExisted", new ActionMessage("errors.rfm.existed"));
      saveErrors(request, errors);
      return false;
    }
    bean = new RfmBean();
    bean.setRfmId(formBean.getRfmId());
    bean.setCreator(formBean.getCreator());
    bean.setRfmNumber(formBean.getRfmNumber());
    bean.setCreatedDate(formBean.getCreatedDate());
    bean.setDeliveryDate(formBean.getDeliveryDate());
    bean.setOrgId(formBean.getOrgId());
    bean.setProId(formBean.getProId());
    bean.setDeliveryPlace(formBean.getDeliveryPlace());
    bean.setStoId(formBean.getStoId());
    bean.setReqType(formBean.getReqType());
    bean.setKind(kind);
    bean.setReqId(formBean.getReqId());
    bean.setRequestOrg(formBean.getRequestOrg());
    bean.setOrgHandle(formBean.getOrgHandle());
    bean.setStoreApprove(formBean.getStoreApprove());
    bean.setStoreComment(formBean.getStoreComment());
    bean.setAccountingApprove(formBean.getAccountingApprove());
    bean.setAccountingComment(formBean.getAccountingComment());
    bean.setPurpose(formBean.getPurpose());
    if ((status == 0) && (formBean.getStoreApprove() == RequestFormBean.APPROVE) && (formBean.getAccountingApprove() == RequestFormBean.APPROVE)) {
      bean.setStatusReserveQuantity(1);
    }
    try
    {
      if (bNew)
      {
        rfmId = rfmDAO.insertRfm(bean);
      }
      else
      {
        OnlineUser user = MCUtil.getOnlineUser(session);
        LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
        rfmDAO.updateRfm(bean);
      }
      session.setAttribute("id", Integer.valueOf(rfmId));
      if (rfmId != 0)
      {
        ArrayList arrDet = null;
        try
        {
          arrDet = rfmDAO.getRfmDetails(rfmId, kind);
        }
        catch (Exception ex)
        {
          arrDet = new ArrayList();
        }
        if (formBean.getDelDetId() != null)
        {
          int[] detIds = formBean.getDelDetId();
          
          RfmDetailBean detBean = new RfmDetailBean();
          for (int i = 0; i < detIds.length; i++)
          {
            detBean.setRfmId(rfmId);
            detBean.setDetId(formBean.getDelDetId()[i]);
            detBean.setMatId(formBean.getMatId()[i]);
            detBean.setMsvId(formBean.getMsvId()[i]);
            detBean.setMsvNumber(formBean.getMsvNumber()[i]);
            double temp = formBean.getQuantity()[i];
            double qtTemp = formBean.getQuantity()[i] - formBean.getQtTemp()[i];
            detBean.setQuantity(temp);
            detBean.setQtTemp(qtTemp);
            detBean.setUnit(formBean.getUnit()[i]);
            detBean.setComment(formBean.getComment()[i]);
            detBean.setReqDetailId(formBean.getReqDetailId()[i]);
            detBean.setStoId(formBean.getStoId());
            detBean.setPrice(formBean.getPrice()[i].doubleValue());
            if (detIds[i] > 0) {
              try
              {
                if (detExisted(arrDet, detIds[i]))
                {
                  rfmDAO.updateRfmDetail(detBean, kind);
                  
                  rfmDAO.updateStore(detBean, kind);
                }
              }
              catch (Exception localException2) {}
            } else {
              try
              {
                rfmDAO.insertRfmDetail(detBean, kind);
                
                rfmDAO.insertStore(detBean, kind);
              }
              catch (Exception localException3) {}
            }
          }
        }
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Rfm:add-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  private boolean detExisted(ArrayList arrDet, int detId)
  {
    RfmDetailBean formBean = null;
    for (int i = 0; i < arrDet.size(); i++)
    {
      formBean = (RfmDetailBean)arrDet.get(i);
      if (formBean.getDetId() == detId)
      {
        arrDet.remove(formBean);
        
        return true;
      }
    }
    return false;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_EDIT + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_STOCK_RFM;
  }
}
