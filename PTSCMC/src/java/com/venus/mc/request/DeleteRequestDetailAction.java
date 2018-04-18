package com.venus.mc.request;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.bean.RequestDetailBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteRequestDetailAction
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
      RequestDAO requestDAO = new RequestDAO();
      if (arrDetId != null) {
        length = arrDetId.length;
      }
      String messages = "";
      String tempStr = "";
      for (int i = 0; i < length; i++)
      {
        if (!arrDetId[i].equals("0")) {
          tempStr = requestDAO.getOrderRelationRequestDetail(arrDetId[i]);
        }
        if (!tempStr.equals("")) {
          messages = messages + ", " + tempStr;
        }
      }
      if (!messages.equals(""))
      {
        OutputUtil.sendStringToOutput(response, MCUtil.getBundleString("message.order") + " : " + messages.substring(2));
      }
      else
      {
        for (int i = 0; i < length; i++) {
          if (!arrDetId[i].equals("0"))
          {
            RequestDetailBean reqDetBean = requestDAO.getRequestDetail(NumberUtil.parseInt(arrDetId[i], 0));
            if (reqDetBean != null)
            {
              int matId = reqDetBean.getMatId();
              try
              {
                MaterialDAO matDAO = new MaterialDAO();
                MaterialBean matBean = matDAO.getMaterial(matId + "");
                if ((matBean != null) && 
                  (GenericValidator.isBlankOrNull(matBean.getCode()))) {
                  matDAO.deleteMaterial(matBean.getMatId() + "");
                }
              }
              catch (Exception localException1) {}
            }
            requestDAO.deleteRequestDetail(arrDetId[i]);
          }
        }
        OutputUtil.sendStringToOutput(response, "deleted");
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:RequestDetail:delete-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
