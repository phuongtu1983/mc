package com.venus.mc.process.store.rfm;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RfmDAO;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteRfmDetailAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    String[] arrDetId = request.getParameterValues("detId");
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    try
    {
      OnlineUser user = MCUtil.getOnlineUser(session);
      LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
      int length = 0;
      RfmDAO rfmDAO = new RfmDAO();
      if (arrDetId != null) {
        length = arrDetId.length;
      }
      for (int i = 0; i < length; i++) {
        rfmDAO.deleteRfmDetail(arrDetId[i], kind);
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:RfmDetail:delete-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
}
