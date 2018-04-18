package com.venus.mc.process.purchasing.deliverynotice;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.DnBean;
import com.venus.mc.bean.DnDetailBean;
import com.venus.mc.bean.EquipmentBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.dao.EquipmentDAO;
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

public class AddDnAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    
    EquipmentDAO equipmentDAO = new EquipmentDAO();
    EquipmentBean equipBean = null;
    DnFormBean formBean = (DnFormBean)form;
    DnDAO dnDAO = new DnDAO();
    DnBean bean = null;
    int kind = 0;
    boolean bNew = false;
    boolean isExist = false;
    try
    {
      bean = dnDAO.getDnByNumber(formBean.getDnNumber());
      kind = dnDAO.getKindOfMaterial(formBean.getMatId()[0]);
    }
    catch (Exception localException1) {}
    int dnId = formBean.getDnId();
    if (dnId == 0)
    {
      bNew = true;
      if (bean != null) {
        isExist = true;
      }
    }
    else
    {
      bNew = false;
      if ((bean != null) && (bean.getDnId() != dnId)) {
        isExist = true;
      }
    }
    if (isExist)
    {
      ActionMessages errors = new ActionMessages();
      errors.add("dnExisted", new ActionMessage("errors.dn.existed"));
      saveErrors(request, errors);
      return false;
    }
    bean = new DnBean();
    bean.setDnId(formBean.getDnId());
    bean.setConId(formBean.getConId());
    bean.setDrId(formBean.getDrId());
    bean.setDnNumber(formBean.getDnNumber());
    bean.setCreatedDate(formBean.getCreatedDate());
    bean.setExpectedDate(formBean.getExpectedDate());
    bean.setActualDate(formBean.getActualDate());
    bean.setExtensionDate(formBean.getExtensionDate());
    bean.setOrgHandle(formBean.getOrgHandle());
    if (bean.getOrgHandle() == 0) {
      bean.setOrgHandle(4);
    }
    if (formBean.getDeliveryPlace() != null) {
      bean.setDeliveryPlace(formBean.getDeliveryPlace());
    } else {
      bean.setDeliveryPlace("");
    }
    if (formBean.getDeliveryPresenter() != null) {
      bean.setDeliveryPresenter(formBean.getDeliveryPresenter());
    } else {
      bean.setDeliveryPresenter("");
    }
    bean.setWhichUse(formBean.getWhichUse());
    bean.setCreatedOrg(formBean.getCreatedOrg());
    bean.setProId(formBean.getProId());
    if (kind == 3) {
      bean.setKind(formBean.getKind());
    } else {
      bean.setKind(formBean.getKind());
    }
    bean.setCreatedEmp(formBean.getCreatedEmp());
    bean.setStatus(formBean.getStatus());
    try
    {
      if (bNew)
      {
        dnId = dnDAO.insertDn(bean);
      }
      else
      {
        OnlineUser user = MCUtil.getOnlineUser(session);
        LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
        dnDAO.updateDn(bean);
      }
      session.setAttribute("id", Integer.valueOf(dnId));
      if (dnId != 0)
      {
        ArrayList arrDet = null;
        try
        {
          arrDet = dnDAO.getDnDetails(dnId);
        }
        catch (Exception ex)
        {
          arrDet = new ArrayList();
        }
        if (formBean.getDelDetId() != null)
        {
          int[] detIds = formBean.getDelDetId();
          int[] matIds = formBean.getMatId();
          DnDetailBean detBean = null;
          if (formBean.getStatus() == 1)
          {
            for (int i = 0; i < detIds.length; i++)
            {
              equipBean = new EquipmentBean();
              equipBean.setMatId(matIds[i]);
              equipBean.setReqDetailId(formBean.getReqDetailId()[i]);
              equipBean.setConId(formBean.getConId());
              equipBean.setKind(EquipmentBean.K2);
              equipmentDAO.insertEquipment(equipBean);
            }
          }
          else
          {
            for (int j = 0; j < detIds.length; j++) {
              if (formBean.getKind() != 2) {
                if ((formBean.getMoveCreateMrir()[j].length() == 10) && (formBean.getMoveCreateMsv()[j].length() == 10) && (formBean.getReceiveMrir()[j].length() == 10) && (formBean.getReceiveMsv()[j].length() == 10))
                {
                  bean.setHighlight(0);
                }
                else
                {
                  bean.setHighlight(1);
                  break;
                }
              }
            }
            dnDAO.updateDnHighlight(bean);
            for (int i = 0; i < detIds.length; i++)
            {
              detBean = new DnDetailBean();
              if (formBean.getKind() != 2)
              {
                detBean.setDnId(dnId);
                detBean.setDetId(detIds[i]);
                detBean.setConDetId(NumberUtil.parseInt(formBean.getConDetId()[i], 0));
                detBean.setMatId(matIds[i]);
                detBean.setUnit(formBean.getUnit()[i]);
                detBean.setPrice(formBean.getPrice()[i]);
                detBean.setQuantity(Double.valueOf(formBean.getQuantity()[(i * 2 + 1)]).doubleValue());
                detBean.setQtDn(Double.valueOf(formBean.getQtDn()[i]).doubleValue());
                detBean.setQtTemp(Double.valueOf(formBean.getQtTemp()[i]).doubleValue());
                detBean.setNote(formBean.getNote()[i]);
                detBean.setReqDetailId(formBean.getReqDetailId()[i]);
                if ((formBean.getKind() > 1) && (formBean.getKind() < DnBean.KIND5))
                {
                  detBean.setMaterialGrade(formBean.getMaterialGrade()[i]);
                  detBean.setMaterialType(formBean.getMaterialType()[i]);
                  detBean.setSize1(formBean.getSize1()[i]);
                  detBean.setSize2(formBean.getSize2()[i]);
                  detBean.setSize3(formBean.getSize3()[i]);
                  detBean.setPoNo(formBean.getPoNo()[i]);
                  detBean.setMrirNo(formBean.getMrirNo()[i]);
                  detBean.setHeatNo(formBean.getHeatNo()[i]);
                  detBean.setTraceNo(formBean.getTraceNo()[i]);
                  detBean.setArea(formBean.getArea()[i]);
                  detBean.setWeight(formBean.getWeight()[i]);
                  detBean.setLocation(formBean.getLocation()[i]);
                  detBean.setRemark(formBean.getRemark()[i]);
                }
                else
                {
                  detBean.setMaterialGrade("");
                  detBean.setMaterialType("");
                  detBean.setSize1("");
                  detBean.setSize2("");
                  detBean.setSize3("");
                  detBean.setPoNo("");
                  detBean.setMrirNo("");
                  detBean.setHeatNo("");
                  detBean.setTraceNo("");
                  detBean.setArea("");
                  detBean.setWeight("");
                  detBean.setLocation("");
                  detBean.setRemark("");
                  detBean.setMoveCreateMrir(formBean.getMoveCreateMrir()[i]);
                  detBean.setMoveCreateMsv(formBean.getMoveCreateMsv()[i]);
                  detBean.setReceiveMrir(formBean.getReceiveMrir()[i]);
                  detBean.setReceiveMsv(formBean.getReceiveMsv()[i]);
                }
              }
              else
              {
                detBean.setDnId(dnId);
                detBean.setDetId(detIds[i]);
                detBean.setConDetId(NumberUtil.parseInt(formBean.getConDetId()[i], 0));
                detBean.setMatId(matIds[i]);
                detBean.setUnit(formBean.getUnit()[i]);
                detBean.setPrice(formBean.getPrice()[i]);
                detBean.setQuantity(Double.valueOf(formBean.getQuantity()[i]).doubleValue());
                detBean.setReqDetailId(formBean.getReqDetailId()[i]);
                try
                {
                  dnDAO.updateQtDn(Double.valueOf(detBean.getQuantity()), detBean.getConDetId());
                }
                catch (Exception ex)
                {
                  LogUtil.error(AddDnAction.class.getName() + ": " + ex.getMessage());
                }
              }
              if (detIds[i] > 0)
              {
                if (detExisted(arrDet, detIds[i])) {
                  try
                  {
                    dnDAO.updateDnDetail(detBean);
                    dnDAO.updateContractDetailQtDn(-detBean.getQtTemp() + detBean.getQuantity(), detBean.getReqDetailId());
                  }
                  catch (Exception ex)
                  {
                    LogUtil.error(AddDnAction.class.getName() + ": " + ex.getMessage());
                  }
                }
              }
              else {
                try
                {
                  dnDAO.insertDnDetail(detBean);
                  dnDAO.updateContractDetailQtDn(detBean.getQuantity(), detBean.getReqDetailId());
                }
                catch (Exception ex)
                {
                  LogUtil.error(AddDnAction.class.getName() + ": " + ex.getMessage());
                }
              }
              try
              {
                dnDAO.updateUsedMaterialImport(formBean.getProId(), MCUtil.getOrganizationID(session));
              }
              catch (Exception ex)
              {
                LogUtil.error(AddDnAction.class.getName() + ": " + ex.getMessage());
              }
            }
          }
        }
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Dn:add-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  private boolean detExisted(ArrayList arrDet, int detId)
  {
    DnDetailBean formBean = null;
    for (int i = 0; i < arrDet.size(); i++)
    {
      formBean = (DnDetailBean)arrDet.get(i);
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
}
