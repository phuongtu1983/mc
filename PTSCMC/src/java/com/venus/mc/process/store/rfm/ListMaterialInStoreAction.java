package com.venus.mc.process.store.rfm;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RfmDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListMaterialInStoreAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    ArrayList materialList = null;
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    int stoId = NumberUtil.parseInt(request.getParameter("stoId"), 0);
    if (GenericValidator.isBlankOrNull(request.getParameter("reload"))) {
      try
      {
        RfmDAO materialDAO = new RfmDAO();
        materialList = materialDAO.searchRfmMaterial(0, null, kind, stoId);
      }
      catch (Exception ex)
      {
        LogUtil.error("FAILED:InStore:listRfm-" + ex.getMessage());
        ex.printStackTrace();
      }
    }
    if (materialList == null) {
      materialList = new ArrayList();
    }
    request.setAttribute("materialList", materialList);
    return true;
  }
}
