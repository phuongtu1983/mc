package com.venus.mc.process.store.mrir;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.MrirDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteMaterialMrirAction
  extends SpineAction
{
  private boolean isStream = false;
  
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    MrirFormBean formBean = (MrirFormBean)form;
    String[] chkDetId = formBean.getChkDetId();
    if ((chkDetId == null) || (chkDetId.length == 0))
    {
      this.isStream = true;
      OutputUtil.sendStringToOutput(response, "noselect");
      return true;
    }
    this.isStream = false;
    int[] reqDetailId = formBean.getReqDetailId();
    String[] detId = formBean.getDetId();
    ArrayList arrMat = new ArrayList();
    DnDAO dnDAO = new DnDAO();
    MrirDAO mrirDAO = new MrirDAO();
    MrirDetailBean detail = null;
    if (detId != null) {
      for (int i = 0; i < detId.length; i++) {
        if (MCUtil.isInSet(detId[i], chkDetId))
        {
          int dndId = NumberUtil.parseInt(detId[i], 0);
          boolean bContinue = true;
          if (dndId > 0) {
            try
            {
              if (mrirDAO.deleteMrirDetail(dndId) == 0) {
                bContinue = false;
              } else {
                try
                {
                  dnDAO.updateDnDetailStatus(formBean.getDnId(), reqDetailId[i], 0);
                }
                catch (Exception ex)
                {
                  LogUtil.error("AddMrirAction: " + ex.getMessage());
                }
              }
            }
            catch (Exception ex)
            {
              LogUtil.error("DeleteMaterialMrirAction: " + ex.getMessage());
            }
          }
          if (bContinue) {}
        }
        else
        {
          if (detId[i].indexOf("dn_") == 0) {
            try
            {
              detail = dnDAO.getDnDetailForMrir(NumberUtil.parseInt(detId[i].substring(3, detId[i].length()), 0));
            }
            catch (Exception ex)
            {
              LogUtil.error("DeleteMaterialMrirAction: " + ex.getMessage());
            }
          } else {
            try
            {
              detail = mrirDAO.getMrirMaterial(NumberUtil.parseInt(detId[i], 0));
            }
            catch (Exception ex)
            {
              LogUtil.error("DeleteMaterialMrirAction: " + ex.getMessage());
            }
          }
          if (detail != null)
          {
            detail.setQuantity(NumberUtil.parseDouble(formBean.getQuantity()[i].replaceAll(",", ""), 0.0D));
            detail.setHeatSerial(formBean.getHeatSerial()[i]);
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
            }
            arrMat.add(detail);
          }
        }
      }
    }
    request.setAttribute("materialList", arrMat);
    if (!this.isStream) {
      if (formBean.getKind() == 1) {
        this.actionForwardResult = "successpro";
      } else {
        this.actionForwardResult = "success";
      }
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return this.isStream;
  }
}
