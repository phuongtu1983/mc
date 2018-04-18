package com.venus.mc.process.store.mrir;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MrirDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.dao.MrirDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class AddMaterialMrirAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    MrirFormBean formBean = (MrirFormBean)form;
    String[] detId = formBean.getDetId();
    ArrayList arrMat = new ArrayList();
    DnDAO dnDAO = new DnDAO();
    MrirDAO mrirDAO = new MrirDAO();
    MrirDetailBean detail = null;
    int dnDetId = formBean.getCbxMaterialOfReq();
    int materialKind = formBean.getMaterialKind();
    try
    {
      if (detId != null) {
        for (int i = 0; i < detId.length; i++)
        {
          if (detId[i].equals("dn_" + dnDetId))
          {
            ActionMessages errors = new ActionMessages();
            errors.add("mrirMaterialExisted", new ActionMessage("errors.mrir.materialexisted"));
            saveErrors(request, errors);
            return false;
          }
          if (detId[i].indexOf("dn_") == 0) {
            try
            {
              detail = dnDAO.getDnDetailForMrir(NumberUtil.parseInt(detId[i].substring(3, detId[i].length()), 0));
            }
            catch (Exception ex)
            {
              LogUtil.error(AddMaterialMrirAction.class.getName() + ": " + ex.getMessage());
            }
          } else {
            try
            {
              detail = mrirDAO.getMrirMaterial(NumberUtil.parseInt(detId[i], 0));
            }
            catch (Exception ex)
            {
              LogUtil.error(AddMaterialMrirAction.class.getName() + ": " + ex.getMessage());
            }
          }
          if (detail != null)
          {
            detail.setQuantity(NumberUtil.parseDouble(formBean.getQuantity()[i].replaceAll(",", ""), 0.0D));
            detail.setHeatSerial(formBean.getHeatSerial()[i]);
            detail.setLocation(formBean.getLocation()[i]);
            detail.setMaterialKind(materialKind);
            if (formBean.getKind() == 1)
            {
              detail.setMaterialGrade(formBean.getMaterialGrade()[i]);
              detail.setMaterialType(formBean.getMaterialType()[i]);
              detail.setRating(formBean.getRating()[i]);
              detail.setSize1(formBean.getSize1()[i]);
              detail.setSize2(formBean.getSize2()[i]);
              detail.setSize3(formBean.getSize3()[i]);
              detail.setTraceNo(formBean.getTraceNo()[i]);
              detail.setCertNo(formBean.getCertNo()[i]);
              detail.setColourCode(formBean.getColourCode()[i]);
              detail.setComment(formBean.getComment()[i]);
              detail.setSystemNo(formBean.getSystemNo()[i]);
              detail.setItemNo(formBean.getItemNo()[i]);
              detail.setMatKind(formBean.getMatKind()[i]);
              detail.setQuantity(NumberUtil.parseDouble(formBean.getQuantity()[i], 0.0D));
            }
            else
            {
              detail.setQuality(formBean.getQuality()[i]);
              detail.setApprovalType(formBean.getApprovalType()[i]);
              detail.setComplCert(formBean.getComplCert()[i]);
              detail.setInspection(formBean.getInspection()[i]);
              detail.setInsurance(formBean.getInsurance()[i]);
              detail.setManufacture(formBean.getManufacture()[i]);
              detail.setOriginal(formBean.getOriginal()[i]);
              detail.setWarranty(formBean.getWarranty()[i]);
              detail.setTimeUse(formBean.getTimeUse()[i]);
            }
            arrMat.add(detail);
          }
        }
      }
    }
    catch (Exception ex)
    {
      LogUtil.error(AddMaterialMrirAction.class.getName() + ": " + ex.getMessage());
    }
    try
    {
      detail = dnDAO.getDnDetailForMrir(dnDetId);
      if (detail != null)
      {
        detail.setMaterialKind(materialKind);
        detail.setQuantity(detail.getRemainQuantity());
        arrMat.add(detail);
      }
    }
    catch (Exception ex)
    {
      LogUtil.error(AddMaterialMrirAction.class.getName() + ": " + ex.getMessage());
    }
    request.setAttribute("materialList", arrMat);
    if (formBean.getKind() == 1) {
      this.actionForwardResult = "successpro";
    } else {
      this.actionForwardResult = "success";
    }
    return true;
  }
}
