package com.venus.mc.process.store.rfm;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RfmDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteRfmAction
  extends SpineAction
{
  private String result = "";
  
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    String[] arrRfmId = request.getParameterValues("rfmId");
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    String mivNumber = "";
    try
    {
      OnlineUser user = MCUtil.getOnlineUser(session);
      LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
      int length = 0;
      RfmDAO rfmDAO = new RfmDAO();
      if (arrRfmId != null) {
        length = arrRfmId.length;
      }
      for (int i = 0; i < length; i++)
      {
        mivNumber = rfmDAO.checkDeleted(arrRfmId[i], kind);
        if (!StringUtil.isBlankOrNull(mivNumber))
        {
          this.result = (this.result + "<br/>" + MCUtil.getBundleString("errors.delete"));
          this.result = (this.result + "<br/>" + MCUtil.getBundleString("message.please") + " " + MCUtil.getBundleString("message.del") + " " + MCUtil.getBundleString("message.l_miv") + " : " + mivNumber);
          this.result = this.result.substring(5);
          return false;
        }
        rfmDAO.deleteRfm(arrRfmId[i], kind);
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:DeliveryRequest:delete-" + ex.getMessage());
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
    return PermissionUtil.PER_STOCK_RFM;
  }
}
