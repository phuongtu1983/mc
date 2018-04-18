package com.venus.mc.process.store.miv;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MivDAO;
import com.venus.mc.dao.OrganizationDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class GetMivNumberAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    MivFormBean formBean = new MivFormBean();
    String prefix = "CKHH-MIV-XXX-YYYY";
    if (!GenericValidator.isBlankOrNull(request.getParameter("stoId")))
    {
      int stoId = NumberUtil.parseInt(request.getParameter("stoId"), 0);
      try
      {
        prefix = "CKHH-MIV-";
        OrganizationDAO orgDAO = new OrganizationDAO();
        OrganizationBean ob = orgDAO.getOrganization(stoId);
        if (ob != null) {
          prefix = prefix + ob.getAbbreviate() + "-";
        } else {
          prefix = prefix + "XXX-";
        }
        MivDAO mivDAO = new MivDAO();
        String number = mivDAO.getNextMivNumber(prefix, 4);
        prefix = prefix + number;
      }
      catch (Exception localException) {}
    }
    formBean.setMivNumber(prefix);
    request.setAttribute("miv", formBean);
    return true;
  }
}
