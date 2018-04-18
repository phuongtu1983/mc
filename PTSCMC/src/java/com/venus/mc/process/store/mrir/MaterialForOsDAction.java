package com.venus.mc.process.store.mrir;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MrirBean;
import com.venus.mc.bean.OsDBean;
import com.venus.mc.bean.OsDDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DeliveryNoticeDAO;
import com.venus.mc.dao.MrirDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MaterialForOsDAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int matId = NumberUtil.parseInt(request.getParameter("matId"), 0);
    int mrirId = NumberUtil.parseInt(request.getParameter("mrirId"), 0);
    try
    {
      MrirDAO mrirDAO = new MrirDAO();
      DeliveryNoticeDAO dnDAO = new DeliveryNoticeDAO();
      OsDBean osDBean = null;
      osDBean = mrirDAO.getOsDByMrir(mrirId);
      
      OsDDetailBean osDDetailBean = new OsDDetailBean();
      if (osDBean != null) {
        osDDetailBean.setOsdId(osDBean.getOsdId());
      }
      osDDetailBean.setMatId(matId);
      if (mrirId > 0)
      {
        MrirBean mrirBean = null;
        mrirBean = mrirDAO.getMrir(mrirId);
        if (mrirBean == null) {}
      }
      request.setAttribute("osDMaterial", osDDetailBean);
    }
    catch (Exception localException) {}
    return true;
  }
}
