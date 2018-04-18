package com.venus.mc.contract;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteContractDetailAction
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
      ContractDAO contractDAO = new ContractDAO();
      if (arrDetId != null) {
        length = arrDetId.length;
      }
      String messages = "";
      String tempStr = "";
      for (int i = 0; i < length; i++)
      {
        tempStr = contractDAO.getDeliveryRequestRelationContractDetail(arrDetId[i]);
        if (!tempStr.equals("")) {
          messages = messages + ", " + tempStr;
        }
      }
      if (!messages.equals(""))
      {
        OutputUtil.sendStringToOutput(response, MCUtil.getBundleString("message.deliveryrequest") + " : " + messages.substring(2));
      }
      else
      {
        for (int i = 0; i < length; i++) {
          if (contractDAO.getKind(arrDetId[i]) == ContractBean.KIND_ADJUSTMENT) {
            contractDAO.updateContractDetailNote(arrDetId[i], "0");
          } else {
            contractDAO.deleteContractDetail(NumberUtil.parseInt(arrDetId[i], 0));
          }
        }
        OutputUtil.sendStringToOutput(response, "deleted");
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:ContractDetail:delete-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
