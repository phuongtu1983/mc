package com.venus.mc.process.store.miv;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MivDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteMivAction
  extends SpineAction
{
  private String result = "";
  
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    String[] arrMivId = request.getParameterValues("mivId");
    try
    {
      OnlineUser user = MCUtil.getOnlineUser(session);
      LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
      int length = 0;
      MivDAO mivDAO = new MivDAO();
      if (arrMivId != null) {
        length = arrMivId.length;
      }
      this.result = "";
      String messages = "";
      String tempStr = "";
      if (this.result.equals("")) {
        for (int i = 0; i < length; i++)
        {
          mivDAO.updateRfm(mivDAO.getRfmId(NumberUtil.parseInt(arrMivId[i], 0)));
          mivDAO.deleteUsedMaterialMiv(NumberUtil.parseInt(arrMivId[i], 0));
          mivDAO.deleteMiv(NumberUtil.parseInt(arrMivId[i], 0));
        }
      } else {
        return false;
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Miv:delete-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected String getErrorsString(HttpServletRequest request)
  {
    return this.result;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_DELETE + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_STOCK_MIV;
  }
}
