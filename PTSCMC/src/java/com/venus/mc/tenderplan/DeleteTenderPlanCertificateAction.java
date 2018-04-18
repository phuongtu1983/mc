package com.venus.mc.tenderplan;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteTenderPlanCertificateAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    String[] arrCertificateId = request.getParameterValues("tenderPlanCertificateChk");
    try
    {
      OnlineUser user = MCUtil.getOnlineUser(session);
      LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
      int length = 0;
      TenderPlanDAO tenderDAO = new TenderPlanDAO();
      if (arrCertificateId != null) {
        length = arrCertificateId.length;
      }
      for (int i = 0; i < length; i++) {
        tenderDAO.deleteTenderPlanCertificate(arrCertificateId[i]);
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:TenderPlanCertificate:delete-" + ex.getMessage());
      ex.printStackTrace();
    }
    return true;
  }
}
