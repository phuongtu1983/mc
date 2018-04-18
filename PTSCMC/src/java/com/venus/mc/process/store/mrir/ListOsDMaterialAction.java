package com.venus.mc.process.store.mrir;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MrirBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DeliveryNoticeDAO;
import com.venus.mc.dao.MrirDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListOsDMaterialAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int osdId = NumberUtil.parseInt(request.getParameter("osdId"), 0);
    int mrirId = NumberUtil.parseInt(request.getParameter("mrirId"), 0);
    try
    {
      ArrayList arrOsD = null;
      MrirDAO mrirDAO = new MrirDAO();
      MrirBean mrirBean = mrirDAO.getMrir(mrirId);
      if (osdId > 0) {
        arrOsD = mrirDAO.getOsDDetailsByOsD(osdId);
      } else if (mrirBean.getDnId() > 0) {
        DeliveryNoticeDAO localDeliveryNoticeDAO = new DeliveryNoticeDAO();
      }
      if (arrOsD == null) {
        arrOsD = new ArrayList();
      }
      request.setAttribute("osDMaterialList", arrOsD);
    }
    catch (Exception localException) {}
    return true;
  }
}
