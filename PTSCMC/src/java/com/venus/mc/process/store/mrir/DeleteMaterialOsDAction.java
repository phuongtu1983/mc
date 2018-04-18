package com.venus.mc.process.store.mrir;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.OsDDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteMaterialOsDAction
  extends SpineAction
{
  private boolean isStream = false;
  
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    OsDFormBean formBean = (OsDFormBean)form;
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
    OsDDetailBean detail = null;
    if (detId != null) {
      for (int i = 0; i < detId.length; i++) {
        if (MCUtil.isInSet(detId[i], chkDetId))
        {
          int dndId = NumberUtil.parseInt(detId[i], 0);
          boolean bContinue = true;
          if (dndId > 0) {
            try
            {
              if (mrirDAO.deleteOsDDetail(dndId) == 0) {
                bContinue = false;
              }
            }
            catch (Exception ex)
            {
              LogUtil.error("DeleteMaterialOsDAction: " + ex.getMessage());
            }
          }
          if (bContinue) {}
        }
        else
        {
          if (detId[i].indexOf("dn_") == 0) {
            try
            {
              detail = dnDAO.getDnDetailForOsD(NumberUtil.parseInt(detId[i].substring(3, detId[i].length()), 0));
            }
            catch (Exception ex)
            {
              LogUtil.error("DeleteMaterialOsDAction: " + ex.getMessage());
            }
          } else {
            try
            {
              detail = mrirDAO.getOsDMaterial(NumberUtil.parseInt(detId[i], 0));
            }
            catch (Exception ex)
            {
              LogUtil.error("DeleteMaterialOsDAction: " + ex.getMessage());
            }
          }
          if (detail != null)
          {
            detail.setQuantity(NumberUtil.parseInt(formBean.getQuantity()[i].replaceAll(",", ""), 0));
            arrMat.add(detail);
          }
        }
      }
    }
    request.setAttribute("materialList", arrMat);
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return this.isStream;
  }
}
