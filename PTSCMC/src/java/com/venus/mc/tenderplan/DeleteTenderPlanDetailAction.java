package com.venus.mc.tenderplan;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteTenderPlanDetailAction
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
      TenderPlanDAO tenderDAO = new TenderPlanDAO();
      if (arrDetId != null) {
        length = arrDetId.length;
      }
      for (int i = 0; i < length; i++) {
        if (!arrDetId[i].equals("0"))
        {
          tenderDAO.updateBidEvalSumDetailQuantity(arrDetId[i]);
          tenderDAO.deleteBidEvalSumDetailQuantity(arrDetId[i]);
          
          tenderDAO.deleteTenderPlanDetail(arrDetId[i]);
        }
      }
      OutputUtil.sendStringToOutput(response, "deleted");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:TenderPlanDetail:delete-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
  
  protected boolean isReturnStream()
  {
    return true;
  }
}
