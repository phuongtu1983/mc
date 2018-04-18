package com.venus.mc.process.store.rfm;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RfmDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListRfmAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    RfmDAO rfmDAO = new RfmDAO();
    ArrayList rfmList = null;
    try
    {
      rfmList = rfmDAO.getRfms(kind);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Rfm:list-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("rfmList", rfmList);
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_LIST + "";
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_STOCK_RFM;
  }
}
