package com.venus.mc.request;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.bean.RequestDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.RequestDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SaveRequestMaterialChangeAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String detId = request.getParameter("detId");
    String matId = request.getParameter("matId");
    if ((!GenericValidator.isBlankOrNull(detId)) && (!GenericValidator.isBlankOrNull(matId))) {
      try
      {
        RequestDAO requestDAO = new RequestDAO();
        RequestDetailBean oldBean = requestDAO.getRequestDetail(NumberUtil.parseInt(detId, 0));
        if (oldBean != null)
        {
          MaterialDAO matDAO = new MaterialDAO();
          MaterialBean matBean = matDAO.getMaterial(matId);
          if (matBean != null)
          {
            oldBean.setMatId(matBean.getMatId());
            oldBean.setUnit(matBean.getUnitName());
            requestDAO.updateRequestDetailForChangeMaterial(oldBean);
          }
        }
      }
      catch (Exception ex)
      {
        LogUtil.error("FAILED:RequestDetail:change material-" + ex.getMessage());
      }
    }
    return true;
  }
}
