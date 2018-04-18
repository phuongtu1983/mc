package com.venus.mc.process.purchasing.deliverynotice;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DnDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListDnDetailAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    String dnId = request.getParameter("dnId");
    Integer id = (Integer)session.getAttribute("id");
    if (id != null) {
      dnId = id + "";
    }
    session.removeAttribute("id");
    ArrayList arrRequestDetail = null;
    if (!GenericValidator.isBlankOrNull(dnId)) {
      try
      {
        DnDAO dnDAO = new DnDAO();
        arrRequestDetail = dnDAO.getDnDetails(Integer.parseInt(dnId));
      }
      catch (Exception localException) {}
    }
    if (arrRequestDetail == null) {
      arrRequestDetail = new ArrayList();
    }
    request.setAttribute("dnDetailList", arrRequestDetail);
    return true;
  }
}
