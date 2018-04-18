package com.venus.mc.process.store.miv;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MivDAO;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteMivDetailAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    String[] arrDetId = request.getParameterValues("detId");
    try
    {
      OnlineUser user = MCUtil.getOnlineUser(session);
      LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
      int length = 0;
      MivDAO mivDAO = new MivDAO();
      if (arrDetId != null) {
        length = arrDetId.length;
      }
      String messages = "";
      String tempStr = "";
      if (messages.equals(""))
      {
        for (int i = 0; i < length; i++) {
          mivDAO.deleteMivDetail(NumberUtil.parseInt(arrDetId[i], 0));
        }
        OutputUtil.sendStringToOutput(response, "deleted");
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:MivDetail:delete-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
