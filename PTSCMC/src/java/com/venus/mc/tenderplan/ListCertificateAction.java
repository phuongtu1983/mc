package com.venus.mc.tenderplan;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.TenderPlanDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ListCertificateAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    String tenId = request.getParameter("tenId");
    Integer id = (Integer)session.getAttribute("id");
    session.removeAttribute("id");
    if (id != null) {
      tenId = id + "";
    }
    ArrayList arrCertificate = null;
    if (!GenericValidator.isBlankOrNull(tenId)) {
      try
      {
        TenderPlanDAO tenderDAO = new TenderPlanDAO();
        arrCertificate = tenderDAO.getTenderPlanCertificate(Integer.parseInt(tenId));
      }
      catch (Exception localException) {}
    }
    if (arrCertificate == null) {
      arrCertificate = new ArrayList();
    }
    request.setAttribute("materialCertificateList", arrCertificate);
    return true;
  }
}
