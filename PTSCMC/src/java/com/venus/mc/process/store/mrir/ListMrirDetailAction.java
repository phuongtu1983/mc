package com.venus.mc.process.store.mrir;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MrirDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListMrirDetailAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String mrirId = request.getParameter("mrirId");
    ArrayList mrirDetailList = null;
    if (!GenericValidator.isBlankOrNull(mrirId)) {
      try
      {
        MrirDAO mrirDAO = new MrirDAO();
        mrirDetailList = mrirDAO.getMrirDetailsByMrir(NumberUtil.parseInt(mrirId, 0));
      }
      catch (Exception localException) {}
    }
    if (mrirDetailList == null) {
      mrirDetailList = new ArrayList();
    }
    request.setAttribute("mrirDetailList", mrirDetailList);
    return true;
  }
}
