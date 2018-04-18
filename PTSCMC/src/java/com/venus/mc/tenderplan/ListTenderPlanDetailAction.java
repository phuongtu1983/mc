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

public class ListTenderPlanDetailAction
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
    ArrayList arrDetail = null;
    if (!GenericValidator.isBlankOrNull(tenId)) {
      try
      {
        TenderPlanDAO tenderDAO = new TenderPlanDAO();
        arrDetail = tenderDAO.getTenderPlanDetail(Integer.parseInt(tenId));
      }
      catch (Exception localException) {}
    }
    if (arrDetail == null) {
      arrDetail = new ArrayList();
    }
    TenderPlanDetailFormBean bean = null;
    for (int i = 0; i < arrDetail.size(); i++)
    {
      bean = (TenderPlanDetailFormBean)arrDetail.get(i);
      bean.setRemainQuantity(bean.getRemainQuantity() + bean.getQuantity());
    }
    request.setAttribute("tenderPlanDetailList", arrDetail);
    return true;
  }
}
