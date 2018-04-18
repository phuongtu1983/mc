package com.venus.mc.process.store.mrir;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.DeliveryNoticeDetailBean;
import com.venus.mc.bean.MrirDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DeliveryNoticeDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MaterialForMrirAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int mrirId = NumberUtil.parseInt(request.getParameter("mrirId"), 0);
    int dnId = NumberUtil.parseInt(request.getParameter("dnId"), 0);
    int reqId = NumberUtil.parseInt(request.getParameter("reqId"), 0);
    int matId = NumberUtil.parseInt(request.getParameter("matId"), 0);
    try
    {
      MrirDetailBean mrirDetailBean = new MrirDetailBean();
      DeliveryNoticeDAO dnDAO = new DeliveryNoticeDAO();
      if (mrirId > 0)
      {
        mrirDetailBean.setMrirId(mrirId);
        mrirDetailBean.setMatId(matId);
        
        DeliveryNoticeDetailBean dnDetailBean = dnDAO.getDeliveryNoticeDetail(reqId, matId);
        if (dnDetailBean != null)
        {
          mrirDetailBean.setMatName(dnDetailBean.getMatName());
          mrirDetailBean.setUnit(dnDetailBean.getUnit());
          mrirDetailBean.setQuantity(dnDetailBean.getQuantity());
          mrirDetailBean.setPrice(dnDetailBean.getPrice());
        }
      }
      else if (dnId > 0)
      {
        mrirDetailBean.setMatId(matId);
        
        DeliveryNoticeDetailBean dnDetailBean = dnDAO.getDeliveryNoticeDetail(reqId, matId);
        if (dnDetailBean != null)
        {
          mrirDetailBean.setMatName(dnDetailBean.getMatName());
          mrirDetailBean.setUnit(dnDetailBean.getUnit());
          mrirDetailBean.setQuantity(dnDetailBean.getQuantity());
          mrirDetailBean.setPrice(dnDetailBean.getPrice());
        }
      }
      mrirDetailBean.setManufacture("");
      mrirDetailBean.setHeatSerial("");
      mrirDetailBean.setInspection("");
      mrirDetailBean.setOriginal("");
      mrirDetailBean.setQuality("");
      mrirDetailBean.setWarranty("");
      mrirDetailBean.setInsurance("");
      mrirDetailBean.setApprovalType("");
      mrirDetailBean.setComplCert("");
      request.setAttribute("mrirMaterial", mrirDetailBean);
    }
    catch (Exception localException) {}
    return true;
  }
}
