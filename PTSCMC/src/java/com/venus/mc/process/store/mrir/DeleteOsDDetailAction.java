package com.venus.mc.process.store.mrir;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MrirDAO;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteOsDDetailAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    String[] arrDetId = request.getParameterValues("osDMaterialChk");
    try
    {
      OnlineUser user = MCUtil.getOnlineUser(session);
      LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
      int length = 0;
      MrirDAO mrirDAO = new MrirDAO();
      if (arrDetId != null) {
        length = arrDetId.length;
      }
      for (int i = 0; i < length; i++) {
        if (!arrDetId[i].equals("0")) {
          mrirDAO.deleteOsDDetail(NumberUtil.parseInt(arrDetId[i], 0));
        }
      }
      OutputUtil.sendStringToOutput(response, "deleted");
    }
    catch (Exception localException) {}
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
