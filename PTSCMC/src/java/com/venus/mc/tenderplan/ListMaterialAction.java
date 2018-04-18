package com.venus.mc.tenderplan;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MaterialDAO;
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
    String matIds = request.getParameter("matIds");
    String reqIds = request.getParameter("reqIds");
    String conDetIds = request.getParameter("conDetIds");
    ArrayList arrMaterial = null;
    if ((!GenericValidator.isBlankOrNull(matIds)) && (!GenericValidator.isBlankOrNull(reqIds))) {
      try
      {
        MaterialDAO materialDAO = new MaterialDAO();
        arrMaterial = materialDAO.getMaterialsForTenderPlan(matIds, reqIds, conDetIds);
      }
      catch (Exception localException) {}
    }
    if (arrMaterial == null) {
      arrMaterial = new ArrayList();
    }
    request.setAttribute("materialList", arrMaterial);
    return true;
  }
}
