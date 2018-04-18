package com.venus.mc.contract;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteContractAction
  extends SpineAction
{
  private String result = "";
  
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    int conId = 0;
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
          conId = contractDAO.getParentId(arrConId[0]);
          contractDAO.updateDelRequestDetail(arrConId[i]);
          contractDAO.deleteContract(arrConId[i]);
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
      LogUtil.error("FAILED:Contract:delete-" + ex.getMessage());
      ex.printStackTrace();
    }
    int kind = 0;
    if (!GenericValidator.isBlankOrNull(request.getParameter("kind"))) {
      kind = Integer.parseInt(request.getParameter("kind"));
    }
    if (kind == ContractBean.KIND_CONTRACT)
    {
      this.actionForwardResult = "contract";
    }
    else if (kind == ContractBean.KIND_PRINCIPLE)
    {
      this.actionForwardResult = "contractPrinciple";
    }
    else if (kind == ContractBean.KIND_ORDER)
    {
      this.actionForwardResult = "order";
    }
    else if (kind == ContractBean.KIND_APPENDIX)
    {
      this.actionForwardResult = "appendix";
    }
    else if (kind == ContractBean.KIND_ADJUSTMENT)
    {
      session.setAttribute("parent_id", Integer.valueOf(conId));
      this.actionForwardResult = "adjustment";
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
    return PermissionUtil.PER_PURCHASING_CONTRACT;
  }
}
