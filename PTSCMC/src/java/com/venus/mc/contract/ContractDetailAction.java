package com.venus.mc.contract;

import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ContractDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ContractDetailAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String conId = request.getParameter("conId");
    try
    {
      ContractDAO contractDAO = new ContractDAO();
      ContractBean bean = contractDAO.getContract(NumberUtil.parseInt(conId, 0));
      String json = "{\"deliveryDate\":\"" + bean.getDeliveryDate() + "\"";
      json = json + ",\"certificate\":\"" + bean.getCertificate().replace("\n", ";") + "\"";
      json = json + ",\"delivery\":\"" + bean.getDelivery() + "\"";
      json = json + "}";
      OutputUtil.sendStringToOutput(response, json);
    }
    catch (Exception localException) {}
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
