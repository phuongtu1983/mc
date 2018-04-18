package com.venus.mc.process.store.rfm;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RfmDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListRfmMaterialAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String matIds = request.getParameter("matIds");
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    int stoId = NumberUtil.parseInt(request.getParameter("stoId"), 0);
    ArrayList materialList = null;
    if (!GenericValidator.isBlankOrNull(matIds))
    {
      RfmDAO rfmDAO = new RfmDAO();
      try
      {
        materialList = rfmDAO.getMaterialsOfMsv(matIds, kind, stoId);
      }
      catch (Exception localException) {}
    }
    if (materialList == null) {
      materialList = new ArrayList();
    }
    request.setAttribute("materialList", materialList);
    return true;
  }
}
