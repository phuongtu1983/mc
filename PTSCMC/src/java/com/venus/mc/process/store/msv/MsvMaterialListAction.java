package com.venus.mc.process.store.msv;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MsvDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MsvMaterialListAction
  extends SpineAction
{
  protected boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    int mrirId = NumberUtil.parseInt(MCUtil.getParameter("mrirId", request), 0);
    int stoId = NumberUtil.parseInt(MCUtil.getParameter("stoId", request), 0);
    MsvDAO msvDAO = new MsvDAO();
    ArrayList arrMaterial = null;
    try
    {
      arrMaterial = msvDAO.getMaterialsFromMrir(mrirId, stoId);
    }
    catch (Exception ex)
    {
      LogUtil.error(MsvMaterialListAction.class.getName() + ": " + ex.getMessage());
    }
    request.setAttribute("materialList", arrMaterial);
    return true;
  }
}
