package com.venus.mc.process.store.miv;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MivBean;
import com.venus.mc.bean.RfmBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RfmDAO;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RfmInfoAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int rfmId = 0;
    int kind = 0;
    if (!GenericValidator.isBlankOrNull(request.getParameter("rfmId"))) {
      rfmId = NumberUtil.parseInt(request.getParameter("rfmId"), 0);
    }
    kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    RfmBean bean = null;
    try
    {
      RfmDAO rfmDAO = new RfmDAO();
      if (kind == MivBean.KIND_COMPANY) {
        bean = rfmDAO.getRfm(rfmId, RfmBean.RFM_KIND, MCUtil.getMemberID(request.getSession()));
      } else {
        bean = rfmDAO.getRfm(rfmId, RfmBean.ERFM_KIND, MCUtil.getMemberID(request.getSession()));
      }
    }
    catch (Exception localException) {}
    if (bean == null) {
      bean = new RfmBean();
    }
    request.setAttribute("rfm", bean);
    return true;
  }
}
