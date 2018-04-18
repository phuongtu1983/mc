package com.venus.mc.request;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteRequestAction
  extends SpineAction
{
  private String result = "";
  
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    String[] arrReqId = request.getParameterValues("reqId");
    try
    {
      OnlineUser user = MCUtil.getOnlineUser(session);
      LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
      int length = 0;
      RequestDAO requestDAO = new RequestDAO();
      if (arrReqId != null) {
        length = arrReqId.length;
      }
      this.result = "";
      String messages = "";
      String tempStr = "";
      for (int i = 0; i < length; i++)
      {
        ArrayList arrDet = requestDAO.getRequestDetails(Integer.parseInt(arrReqId[i]));
        for (int j = 0; j < arrDet.size(); j++)
        {
          RequestDetailFormBean detail = (RequestDetailFormBean)arrDet.get(j);
          tempStr = requestDAO.getOrderRelationRequestDetail(detail.getDetId() + "");
          if (!tempStr.equals("")) {
            messages = messages + ", " + tempStr;
          }
        }
      }
      if (!messages.equals("")) {
        this.result = (this.result + "\n " + MCUtil.getBundleString("message.order") + " : " + messages.substring(2));
      }
      if (this.result.equals("")) {
        for (int i = 0; i < length; i++) {
          requestDAO.deleteRequest(arrReqId[i]);
        }
      } else {
        return false;
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Request:delete-" + ex.getMessage());
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
    return PermissionUtil.PER_PURCHASING_REQUEST;
  }
}
