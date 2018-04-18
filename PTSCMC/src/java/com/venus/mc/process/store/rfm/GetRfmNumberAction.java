package com.venus.mc.process.store.rfm;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.RfmBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.dao.RfmDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class GetRfmNumberAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    RfmBean bean = new RfmBean();
    String prefix = "CKHH-YCXK-XXX-YYYY";
    if (!GenericValidator.isBlankOrNull(request.getParameter("stoId")))
    {
      int stoId = NumberUtil.parseInt(request.getParameter("stoId"), 0);
      try
      {
        prefix = "CKHH-YCXK-";
        OrganizationDAO orgDAO = new OrganizationDAO();
        OrganizationBean ob = orgDAO.getOrganization(stoId);
        if (ob != null) {
          prefix = prefix + ob.getAbbreviate() + "-";
        } else {
          prefix = prefix + "XXX-";
        }
        RfmDAO rfmDAO = new RfmDAO();
        String number = rfmDAO.getNextRfmNumber(prefix, 4);
        prefix = prefix + number;
      }
      catch (Exception localException) {}
    }
    bean.setRfmNumber(prefix);
    request.setAttribute("rfm", bean);
    return true;
  }
}
