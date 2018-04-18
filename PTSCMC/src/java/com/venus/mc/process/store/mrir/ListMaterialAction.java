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

public class ListMaterialAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String mrirId = request.getParameter("mrirId");
    ArrayList arrMaterial = null;
    if (!GenericValidator.isBlankOrNull(mrirId)) {
      try
      {
        MrirDAO mrirDAO = new MrirDAO();
        arrMaterial = mrirDAO.getMrirDetailsByMrir(NumberUtil.parseInt(mrirId, 0));
      }
      catch (Exception localException) {}
    }
    if (arrMaterial == null) {
      arrMaterial = new ArrayList();
    }
    request.setAttribute("mrirMaterialList", arrMaterial);
    return true;
  }
}
