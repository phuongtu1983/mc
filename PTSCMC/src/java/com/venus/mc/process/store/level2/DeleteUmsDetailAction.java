package com.venus.mc.process.store.level2;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteUmsDetailAction
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
      StoreDAO storeDAO = new StoreDAO();
      if (arrDetId != null) {
        length = arrDetId.length;
      }
      for (int i = 0; i < length; i++) {
        if (!arrDetId[i].equals("0")) {
          storeDAO.deleteUmsDetail(NumberUtil.parseInt(arrDetId[i], 0));
        }
      }
      OutputUtil.sendStringToOutput(response, "deleted");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:UmsDetail:delete-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
