package com.venus.mc.process.store.miv;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MivBean;
import com.venus.mc.bean.RfmBean;
import com.venus.mc.bean.RfmDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RfmDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListMaterialInRfmAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    ArrayList arrMaterial = null;
    int rfmId = 0;
    int kind = 0;
    String materialIds = null;
    RfmDAO rfmDAO = new RfmDAO();
    if (!GenericValidator.isBlankOrNull(request.getParameter("rfmId"))) {
      rfmId = NumberUtil.parseInt(request.getParameter("rfmId"), 0);
    }
    if (!GenericValidator.isBlankOrNull(request.getParameter("materialIds"))) {
      materialIds = request.getParameter("materialIds");
    }
    kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    if (materialIds == null)
    {
      try
      {
        if (kind == MivBean.KIND_COMPANY) {
          arrMaterial = rfmDAO.getRfmDetails(rfmId, RfmBean.RFM_KIND);
        } else {
          arrMaterial = rfmDAO.getRfmDetails(rfmId, RfmBean.ERFM_KIND);
        }
      }
      catch (Exception localException) {}
      if (arrMaterial == null) {
        arrMaterial = new ArrayList();
      }
      if (GenericValidator.isBlankOrNull(request.getParameter("loadMaterials")))
      {
        this.actionForwardResult = "listmaterial";
      }
      else
      {
        try
        {
          String matIds = "0";
          RfmDetailBean detail = null;
          for (int i = 0; i < arrMaterial.size(); i++)
          {
            detail = (RfmDetailBean)arrMaterial.get(i);
            matIds = matIds + "," + detail.getMatId();
          }
          if (kind == MivBean.KIND_COMPANY) {
            arrMaterial = rfmDAO.getRfmMaterial(rfmId, matIds);
          } else {
            arrMaterial = rfmDAO.getERfmMaterial(rfmId, matIds);
          }
        }
        catch (Exception localException1) {}
        if (arrMaterial == null) {
          arrMaterial = new ArrayList();
        }
        this.actionForwardResult = "materialdetail";
      }
    }
    else
    {
      try
      {
        if (kind == MivBean.KIND_COMPANY) {
          arrMaterial = rfmDAO.getRfmMaterial(rfmId, materialIds);
        } else {
          arrMaterial = rfmDAO.getERfmMaterial(rfmId, materialIds);
        }
      }
      catch (Exception localException2) {}
      if (arrMaterial == null) {
        arrMaterial = new ArrayList();
      }
      this.actionForwardResult = "materialdetail";
    }
    request.setAttribute("materialList", arrMaterial);
    return true;
  }
}
