package com.venus.mc.deliveryrequest;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteDeliveryRequestAction
  extends SpineAction
{
  private String result = "";
  
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    String[] arrConId = request.getParameterValues("conId");
    try
    {
      OnlineUser user = MCUtil.getOnlineUser(session);
      LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
      int length = 0;
      ContractDAO contractDAO = new ContractDAO();
      if (arrConId != null) {
        length = arrConId.length;
      }
      this.result = "";
      String messages = "";
      String tempStr = "";
      for (int i = 0; i < length; i++)
      {
        tempStr = contractDAO.getContractFollowRelationContract(arrConId[i]);
        if (!tempStr.equals("")) {
          messages = messages + ", " + tempStr;
        }
      }
      if (!messages.equals("")) {
        this.result = (this.result + "<br/>" + MCUtil.getBundleString("message.contractFollow.folId") + " : " + messages.substring(2));
      }
      messages = "";
      for (int i = 0; i < length; i++)
      {
        tempStr = contractDAO.getDeliveryRequestRelationContract(arrConId[i]);
        if (!tempStr.equals("")) {
          messages = messages + ", " + tempStr;
        }
      }
      if (!messages.equals("")) {
        this.result = (this.result + "<br/>" + MCUtil.getBundleString("message.deliveryrequest") + " : " + messages.substring(2));
      }
      messages = "";
      for (int i = 0; i < length; i++)
      {
        tempStr = contractDAO.getAppendixRelationContract(arrConId[i]);
        if (!tempStr.equals("")) {
          messages = messages + ", " + tempStr;
        }
      }
      if (!messages.equals("")) {
        this.result = (this.result + "<br/>" + MCUtil.getBundleString("message.appendixdetail.title") + " : " + messages.substring(2));
      }
      messages = "";
      for (int i = 0; i < length; i++)
      {
        tempStr = contractDAO.getInvoiceRelationContract(arrConId[i]);
        if (!tempStr.equals("")) {
          messages = messages + ", " + tempStr;
        }
      }
      if (!messages.equals("")) {
        this.result = (this.result + "<br/>" + MCUtil.getBundleString("message.invoice") + " : " + messages.substring(2));
      }
      messages = "";
      for (int i = 0; i < length; i++)
      {
        tempStr = contractDAO.getDeliveryNoticeRelationContract(arrConId[i]);
        if (!tempStr.equals("")) {
          messages = messages + ", " + tempStr;
        }
      }
      if (!messages.equals("")) {
        this.result = (this.result + "<br/>" + MCUtil.getBundleString("message.deliverynotice") + " : " + messages.substring(2));
      }
      messages = "";
      for (int i = 0; i < length; i++)
      {
        tempStr = contractDAO.getMrirRelationContract(arrConId[i]);
        if (!tempStr.equals("")) {
          messages = messages + ", " + tempStr;
        }
      }
      if (!messages.equals("")) {
        this.result = (this.result + "<br/>" + MCUtil.getBundleString("message.mrir.title") + " : " + messages.substring(2));
      }
      if (this.result.equals(""))
      {
        for (int i = 0; i < length; i++)
        {
          contractDAO.updateDelRequestDetail(arrConId[i]);
          contractDAO.deleteDeliveryRequest(arrConId[i]);
        }
      }
      else
      {
        this.result = this.result.substring(5);
        return false;
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
    return PermissionUtil.PER_PURCHASING_DELIVERYREQUEST;
  }
}
