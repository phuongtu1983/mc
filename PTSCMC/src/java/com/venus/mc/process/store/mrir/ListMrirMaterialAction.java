package com.venus.mc.process.store.mrir;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MrirDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListMrirMaterialAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String matIds = request.getParameter("matIds");
    ArrayList materialList = null;
    if (!GenericValidator.isBlankOrNull(matIds))
    {
      MrirDAO mrirDAO = new MrirDAO();
      try
      {
        materialList = mrirDAO.getMaterials(matIds);
      }
      catch (Exception localException) {}
    }
    if (materialList == null) {
      materialList = new ArrayList();
    }
    request.setAttribute("mrirMaterialList", materialList);
    return true;
  }
}
