package com.venus.mc.request;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialStoreRequestDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CheckMaterialInStoreAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int reqId = NumberUtil.parseInt(request.getParameter("reqId"), 0);
    int matId = NumberUtil.parseInt(request.getParameter("matId"), 0);
    int stoId = NumberUtil.parseInt(request.getParameter("stoId"), 0);
    ArrayList materialList = null;
    try
    {
      MaterialStoreRequestDAO materialDAO = new MaterialStoreRequestDAO();
      materialList = materialDAO.checkMaterialInStore(reqId, stoId, matId);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Material:check-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("materialList", materialList);
    return true;
  }
}
