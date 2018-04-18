package com.venus.mc.process.store.mrir;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MrirDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListOsDDetailAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String osdId = request.getParameter("osdId");
    ArrayList osDDetailList = null;
    if (!GenericValidator.isBlankOrNull(osdId))
    {
      MrirDAO mrirDAO = new MrirDAO();
      try
      {
        osDDetailList = mrirDAO.getOsDDetailsByOsD(NumberUtil.parseInt(osdId, 0));
      }
      catch (Exception ex)
      {
        LogUtil.error(getClass(), ex.getMessage());
      }
    }
    if (osDDetailList == null) {
      osDDetailList = new ArrayList();
    }
    request.setAttribute("materialList", osDDetailList);
    return true;
  }
}
