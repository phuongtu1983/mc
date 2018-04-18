package com.venus.mc.request;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.RequestDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequestDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ChangeMaterialFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String reqDetId = request.getParameter("reqDetId");
    if (!GenericValidator.isBlankOrNull(reqDetId)) {
      try
      {
        RequestDAO reqDAO = new RequestDAO();
        RequestDetailBean detBean = reqDAO.getRequestDetail(NumberUtil.parseInt(reqDetId, 0));
        if (detBean == null)
        {
          detBean = new RequestDetailBean();
          detBean.setDetId(NumberUtil.parseInt(reqDetId, 0));
        }
        request.setAttribute("requestDetail", detBean);
      }
      catch (Exception localException) {}
    }
    return true;
  }
}
