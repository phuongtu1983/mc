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

public class ListRequestAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    ArrayList list = null;
    RfmDAO rfmDAO = new RfmDAO();
    if (!GenericValidator.isBlankOrNull(request.getParameter("stoId"))) {
      try
      {
        list = rfmDAO.getRequestMsv(NumberUtil.parseInt(request.getParameter("stoId"), 0));
      }
      catch (Exception localException) {}
    }
    if (list == null) {
      list = new ArrayList();
    }
    request.setAttribute("requestList", list);
    return true;
  }
}
