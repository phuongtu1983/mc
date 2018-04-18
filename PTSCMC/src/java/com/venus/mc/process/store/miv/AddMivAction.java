package com.venus.mc.process.store.miv;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.EquipmentBean;
import com.venus.mc.bean.MivBean;
import com.venus.mc.bean.MivDetailBean;
import com.venus.mc.bean.RfmBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ColorCodeDAO;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.dao.EquipmentDAO;
import com.venus.mc.dao.MaterialStoreRequestDAO;
import com.venus.mc.dao.MivDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.dao.RfmDAO;
import com.venus.mc.dao.StoreDAO;
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

public class AddMivAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    MivFormBean formBean = (MivFormBean)form;
    
    MivBean bean = null;
    boolean bNew = false;
    boolean isExist = false;
    MivDAO mivDAO = new MivDAO();
    int sto2Id = 0;
    try
    {
      if (formBean.getMivKind() == MivBean.KIND_COMPANY) {
        bean = mivDAO.getMivByNumber(formBean.getMivNumber());
      } else {
        bean = mivDAO.getEMivByNumber(formBean.getMivNumber());
      }
    }
    catch (Exception localException1) {}
    int mivId = formBean.getMivId();
    if (mivId == 0)
    {
      bNew = true;
      if (bean != null) {
        isExist = true;
      }
    }
    else
    {
      bNew = false;
      if ((bean != null) && (bean.getMivId() != mivId)) {
        isExist = true;
      }
    }
    if (isExist)
    {
      ActionMessages errors = new ActionMessages();
      errors.add("mivExisted", new ActionMessage("errors.miv.existed"));
      saveErrors(request, errors);
      return false;
    }
    bean = new MivBean();
    bean.setMivId(mivId);
    bean.setMivNumber(formBean.getMivNumber());
    bean.setCreator(formBean.getCreator());
    bean.setCreatedDate(formBean.getCreatedDate());
    bean.setReceiver(formBean.getReceiver());
    bean.setRfmId(formBean.getRfmId());
    bean.setDescription(formBean.getDescription());
    bean.setTotal(formBean.getTotal());
    bean.setType(formBean.getType());
    bean.setMivKind(formBean.getMivKind());
    try
    {
      StoreDAO storeDAO = new StoreDAO();
      RfmDAO rfmDAO = new RfmDAO();
      
      RfmBean rfmBean = null;
      if (bean.getMivKind() == MivBean.KIND_COMPANY) {
        rfmBean = rfmDAO.getRfm(bean.getRfmId(), RfmBean.RFM_KIND, MCUtil.getMemberID(request.getSession()));
      } else {
        rfmBean = rfmDAO.getRfm(bean.getRfmId(), RfmBean.ERFM_KIND, MCUtil.getMemberID(request.getSession()));
      }
      if (rfmBean != null)
      {
        bean.setStoId(rfmBean.getStoId());
        bean.setProId(rfmBean.getProId());
        bean.setOrgId(rfmBean.getOrgId());
        bean.setRequestOrg(rfmBean.getRequestOrg());
      }
      if (bNew)
      {
        if (bean.getMivKind() == MivBean.KIND_COMPANY)
        {
          mivId = mivDAO.insertMiv(bean);
          sto2Id = storeDAO.insertStoreLevel2(formBean.getWhichUseName(), bean.getProId(), bean.getOrgId());
        }
        else
        {
          mivId = mivDAO.insertEMiv(bean);
        }
      }
      else
      {
        OnlineUser user = MCUtil.getOnlineUser(session);
        LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
        if (bean.getMivKind() == MivBean.KIND_COMPANY)
        {
          mivDAO.updateMiv(bean);
          sto2Id = storeDAO.insertStoreLevel2(formBean.getWhichUseName(), bean.getProId(), bean.getOrgId());
        }
        else
        {
          mivDAO.updateEMiv(bean);
        }
      }
      session.setAttribute("id", Integer.valueOf(mivId));
      session.setAttribute("kind", Integer.valueOf(bean.getMivKind()));
      if (mivId != 0)
      {
        if ((formBean.getType() == MivBean.TYPE_CCDC) || (formBean.getType() == MivBean.TYPE_TSCDHH))
        {
          bean.setMivId(mivId);
          addEquipment(bean, formBean);
        }
        addMivDetail(bean, mivId, formBean, sto2Id);
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Miv:add-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  private MivDetailBean detExisted(ArrayList arrDet, int detId)
  {
    MivDetailBean detail = null;
    for (int i = 0; i < arrDet.size(); i++)
    {
      detail = (MivDetailBean)arrDet.get(i);
      if (detail.getDetId() == detId)
      {
        arrDet.remove(detail);
        MivDetailBean bean = new MivDetailBean(detId);
        bean.setQuantity(detail.getQuantity());
        bean.setTotal(detail.getTotal());
        return bean;
      }
    }
    return null;
  }
  
  private void addMivDetail(MivBean bean, int mivId, MivFormBean formBean, int sto2Id)
  {
    ArrayList arrDet = null;
    MivDAO mivDAO = new MivDAO();
    StoreDAO storeDAO = new StoreDAO();
    MaterialStoreRequestDAO msrDAO = new MaterialStoreRequestDAO();
    try
    {
      if (bean.getMivKind() == MivBean.KIND_COMPANY) {
        arrDet = mivDAO.getMivDetails(mivId);
      } else {
        arrDet = mivDAO.getEMivDetails(mivId);
      }
    }
    catch (Exception ex)
    {
      arrDet = new ArrayList();
    }
    if (formBean.getMivDetId() != null)
    {
      String[] detIds = formBean.getMivDetId();
      String[] matIds = formBean.getMatId();
      MivDetailBean detBean = null;
      double quantity = 0.0D;
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
              quantity = Double.parseDouble(formBean.getQuantity()[i]);
              if (quantity != detBean.getQuantity())
              {
                detBean.setQuantity(quantity);
                detBean.setTotal(Double.parseDouble(formBean.getDetTotal()[i]));
                canUpdate = true;
              }
              if (canUpdate)
              {
                detBean.setStoId(sto2Id);
                if (bean.getMivKind() == MivBean.KIND_COMPANY)
                {
                  MivDetailBean detail = mivDAO.getMivDetail(detBean.getDetId());
                  mivDAO.updateMivDetail(detBean);
                  msrDAO.updateMaterialStoreRequest(detBean, detail);
                }
                else
                {
                  mivDAO.updateEMivDetail(detBean);
                }
              }
            }
          }
          catch (Exception localException1) {}
        } else {
          try
          {
            double price = 0.0D;
            double total = 0.0D;
            if (GenericValidator.isBlankOrNull(formBean.getPrice()[i])) {
              price = 0.0D;
            } else {
              price = Double.parseDouble(formBean.getPrice()[i]);
            }
            if (GenericValidator.isBlankOrNull(formBean.getQuantity()[i]))
            {
              quantity = 0.0D;
              total = 0.0D;
            }
            else
            {
              quantity = Double.parseDouble(formBean.getQuantity()[i]);
              total = Double.parseDouble(formBean.getDetTotal()[i]);
            }
            detBean = new MivDetailBean(0, formBean.getUnit()[i], quantity, price, total, 0);
            detBean.setMivId(mivId);
            detBean.setMatId(Integer.parseInt(matIds[i]));
            detBean.setStoId(sto2Id);
            detBean.setRfmDetId(Integer.parseInt(formBean.getRfmDetId()[i]));
            if (formBean.getReqDetId() != null) {
              detBean.setReqDetailId(Integer.parseInt(formBean.getReqDetId()[i]));
            }
            if (bean.getMivKind() == MivBean.KIND_COMPANY)
            {
              try
              {
                storeDAO.insertStoreLevel2Detail(mivId, sto2Id, detBean.getMatId(), quantity, detBean.getReqDetailId(), formBean.getStoId());
              }
              catch (Exception localException2) {}
              if (mivDAO.insertMivDetail(detBean) > 0)
              {
                detBean.setStoId(formBean.getStoId());
                msrDAO.updateMaterialStoreRequest(detBean);
              }
            }
            else
            {
              mivDAO.insertEMivDetail(detBean);
            }
          }
          catch (Exception localException3) {}
        }
      }
    }
  }
  
  private void addEquipment(MivBean bean, MivFormBean formBean)
  {
    EquipmentDAO equipmentDAO = new EquipmentDAO();
    ContractDAO contractDAO = new ContractDAO();
    MrirDAO mrirDAO = new MrirDAO();
    ColorCodeDAO ccDAO = new ColorCodeDAO();
    if (formBean.getMivDetId() != null)
    {
      String[] detIds = formBean.getMivDetId();
      double quantity = 0.0D;
      EquipmentBean equipBean = null;
      int conId = 0;
      int mrirId = 0;
      int ccId = 0;
      for (int i = 0; i < detIds.length; i++) {
        if (detIds[i].equals("0"))
        {
          if (GenericValidator.isBlankOrNull(formBean.getQuantity()[i])) {
            quantity = 0.0D;
          } else {
            quantity = Double.parseDouble(formBean.getQuantity()[i]);
          }
          try
          {
            for (int j = 0; j < quantity; j++)
            {
              equipBean = new EquipmentBean();
              equipBean.setMivId(bean.getMivId());
              equipBean.setMatId(Integer.parseInt(formBean.getMatId()[i]));
              equipBean.setReqDetailId(Integer.parseInt(formBean.getReqDetId()[i]));
              conId = contractDAO.getContractIdFromReqDetailId(equipBean.getReqDetailId());
              mrirId = mrirDAO.getMrirIdFromReqDetailId(equipBean.getReqDetailId());
              ccId = ccDAO.getColorCodeId();
              equipBean.setConId(conId);
              equipBean.setMrirId(mrirId);
              equipBean.setCcId(ccId);
              equipBean.setKind(EquipmentBean.K1);
              equipmentDAO.insertEquipment(equipBean);
            }
          }
          catch (Exception localException) {}
        }
      }
    }
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_EDIT + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_STOCK_MIV;
  }
}
