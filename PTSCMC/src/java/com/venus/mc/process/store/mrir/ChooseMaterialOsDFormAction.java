package com.venus.mc.process.store.mrir;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ChooseMaterialOsDFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int detId = NumberUtil.parseInt(request.getParameter("detId"), 0);
    int osdId = NumberUtil.parseInt(request.getParameter("osdId"), 0);
    int matId = NumberUtil.parseInt(request.getParameter("matId"), 0);
    String matName = "";
    MaterialDAO matDAO = new MaterialDAO();
    try
    {
      matName = matDAO.getMatNameVnByMatId(matId);
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:RequestMaterial:add-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("detId", Integer.valueOf(detId));
    request.setAttribute("osdId", Integer.valueOf(osdId));
    request.setAttribute("matName", matName);
    return true;
  }
}
