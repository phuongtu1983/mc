package com.venus.mc.contract;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TenderPlanDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class TenderPlanDetailContractAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String tenId = request.getParameter("tenId");
    String venId = request.getParameter("venId");
    if ((!GenericValidator.isBlankOrNull(tenId)) && (!GenericValidator.isBlankOrNull(venId))) {
      try
      {
        TenderPlanBean bean = null;
        TenderPlanDAO tenderPlanDAO = new TenderPlanDAO();
        bean = tenderPlanDAO.getTenderPlanDetailForContract(tenId, venId);
        String json = "";
        if (bean == null)
        {
          json = "{\"currency\":\"\"";
          json = json + ",\"delivery\":\"\"";
          json = json + ",\"certificate\":\"\"";
          json = json + ",\"note\":\"\"";
          json = json + "}";
        }
        else
        {
          json = "{\"currency\":\"" + bean.getCurrency() + "\"";
          json = json + ",\"delivery\":\"" + bean.getDeliveryTime() + "\"";
          json = json + ",\"certificate\":\"" + bean.getCerText() + "\"";
          json = json + ",\"note\":\"" + bean.getPackName() + "\"";
          json = json + "}";
        }
        OutputUtil.sendStringToOutput(response, json);
      }
      catch (Exception localException) {}
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
