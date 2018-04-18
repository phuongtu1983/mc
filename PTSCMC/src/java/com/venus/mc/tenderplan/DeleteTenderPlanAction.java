package com.venus.mc.tenderplan;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeleteTenderPlanAction
  extends SpineAction
{
  private String result = "";
  
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    String[] arrTenId = request.getParameterValues("tenId");
    try
    {
      OnlineUser user = MCUtil.getOnlineUser(session);
      LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - DELETE");
      int length = 0;
      TenderPlanDAO tenderDAO = new TenderPlanDAO();
      if (arrTenId != null) {
        length = arrTenId.length;
      }
      this.result = "";
      String messages = "";
      String tempStr = "";
      String tenderNumber = "";
      for (int i = 0; i < length; i++)
      {
        tempStr = tenderDAO.getContractRelationTenderPlan(arrTenId[i]);
        tenderNumber = tenderDAO.getTenderNumber(arrTenId[i]);
        if (!tempStr.equals(""))
        {
          messages = messages + MCUtil.getBundleString("message.tenderplan.number") + " : " + tenderNumber + " --> " + MCUtil.getBundleString("message.contract") + " : " + tempStr + " <br> ";
        }
        else
        {
          tenderDAO.deleteBidEvalSum(arrTenId[i]);
          tenderDAO.deletePriceComparision(arrTenId[i]);
          tenderDAO.deleteComEval(arrTenId[i]);
          tenderDAO.deleteTechEval(arrTenId[i]);
          tenderDAO.deleteBidOpening(arrTenId[i]);
          tenderDAO.deleteBidClosing(arrTenId[i]);
          tenderDAO.deleteTenderLetter(arrTenId[i]);
          tenderDAO.updateRequestDetailStep(Integer.parseInt(arrTenId[i]), RequestBean.REQUEST, MCUtil.getBundleString("message.request"));
          tenderDAO.deleteTenderPlans(arrTenId[i]);
        }
      }
      if (!messages.equals(""))
      {
        this.result = messages;
        return false;
      }
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:TenderPlan:delete-" + ex.getMessage());
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
    return PermissionUtil.PER_PURCHASING_TENDERPLAN;
  }
}
