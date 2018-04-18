package com.venus.mc.process.store.mrir;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.DeliveryNoticeDetailBean;
import com.venus.mc.bean.MrirDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.dao.MrirDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AddAllMaterialMrirAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    MrirFormBean formBean = (MrirFormBean)form;
    
    int dnId = formBean.getDnId();
    int reqId = formBean.getReqId();
    int materialKind = formBean.getMaterialKind();
    
    String[] detId = formBean.getDetId();
    ArrayList arrMat = new ArrayList();
    DnDAO dnDAO = new DnDAO();
    MrirDAO mrirDAO = new MrirDAO();
    MrirDetailBean detail = null;
    
    ArrayList arrMatDn = null;
    try
    {
      arrMatDn = dnDAO.getDeliveryNoticeDetails(dnId, reqId, materialKind);
    }
    catch (Exception ex)
    {
      LogUtil.error(AddAllMaterialMrirAction.class.getName() + ": " + ex.getMessage());
      arrMatDn = new ArrayList();
    }
    DeliveryNoticeDetailBean dnBean = null;
    if (detId == null)
    {
      try
      {
        String detIds = "0";
        for (int j = 0; j < arrMatDn.size(); j++)
        {
          dnBean = (DeliveryNoticeDetailBean)arrMatDn.get(j);
          int dnDetId = dnBean.getDetId();
          if ((dnDetId != 0) && (dnBean.getStatus() != 1)) {
            detIds = detIds + "," + dnDetId;
          }
        }
        arrMat = dnDAO.getDnDetailsForMrir(detIds);
      }
      catch (Exception ex)
      {
        LogUtil.error(AddAllMaterialMrirAction.class.getName() + ": " + ex.getMessage());
      }
    }
    else
    {
      String detIds = "0";
      for (int j = 0; j < arrMatDn.size(); j++)
      {
        dnBean = (DeliveryNoticeDetailBean)arrMatDn.get(j);
        int dnDetId = dnBean.getDetId();
        if ((dnDetId != 0) && (dnBean.getStatus() != 1)) {
          detIds = detIds + "," + dnDetId;
        }
      }
      String det0Ids = "0";
      for (int i = 0; i < detId.length; i++) {
        if (detId[i].indexOf("dn_") == -1) {
          det0Ids = det0Ids + "," + detId[i];
        }
      }
      try
      {
        ArrayList arrDet = dnDAO.getDnDetailsForMrir(detIds);
        for (int i = 0; i < arrDet.size(); i++)
        {
          detail = (MrirDetailBean)arrDet.get(i);
          setDnDetail(detail, formBean, detId, materialKind);
          arrMat.add(detail);
        }
        arrDet = mrirDAO.getMrirMaterials(det0Ids);
        for (int i = 0; i < arrDet.size(); i++)
        {
          detail = (MrirDetailBean)arrDet.get(i);
          
          arrMat.add(detail);
        }
      }
      catch (Exception localException1) {}
    }
    request.setAttribute("materialList", arrMat);
    if (formBean.getKind() == 1) {
      this.actionForwardResult = "successpro";
    } else {
      this.actionForwardResult = "success";
    }
    return true;
  }
  
  private void setDnDetail(MrirDetailBean detail, MrirFormBean formBean, String[] detId, int materialKind)
  {
    int j = -1;
    for (int i = 0; i < detId.length; i++) {
      if (detId[i].equals("dn_" + detail.getDetId()))
      {
        j = i;
        break;
      }
    }
    if (j == -1) {
      return;
    }
    detail.setQuantity(NumberUtil.parseDouble(formBean.getQuantity()[j].replaceAll(",", ""), 0.0D));
    detail.setHeatSerial(formBean.getHeatSerial()[j]);
    detail.setLocation(formBean.getLocation()[j]);
    detail.setMaterialKind(materialKind);
    if (formBean.getKind() == 1)
    {
      detail.setMaterialGrade(formBean.getMaterialGrade()[j]);
      detail.setMaterialType(formBean.getMaterialType()[j]);
      detail.setRating(formBean.getRating()[j]);
      detail.setSize1(formBean.getSize1()[j]);
      detail.setSize2(formBean.getSize2()[j]);
      detail.setSize3(formBean.getSize3()[j]);
      detail.setTraceNo(formBean.getTraceNo()[j]);
      detail.setCertNo(formBean.getCertNo()[j]);
      detail.setColourCode(formBean.getColourCode()[j]);
      detail.setComment(formBean.getComment()[j]);
      detail.setSystemNo(formBean.getSystemNo()[j]);
      detail.setItemNo(formBean.getItemNo()[j]);
      detail.setMatKind(formBean.getMatKind()[j]);
    }
    else
    {
      detail.setQuality(formBean.getQuality()[j]);
      detail.setApprovalType(formBean.getApprovalType()[j]);
      detail.setComplCert(formBean.getComplCert()[j]);
      detail.setInspection(formBean.getInspection()[j]);
      detail.setInsurance(formBean.getInsurance()[j]);
      detail.setManufacture(formBean.getManufacture()[j]);
      detail.setOriginal(formBean.getOriginal()[j]);
      detail.setWarranty(formBean.getWarranty()[j]);
      detail.setTimeUse(formBean.getTimeUse()[j]);
    }
  }
}
