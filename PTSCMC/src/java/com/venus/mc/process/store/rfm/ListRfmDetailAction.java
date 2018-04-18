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

public class ListRfmDetailAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String rfmId = request.getParameter("rfmId");
    int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
    ArrayList arrRequestDetail = null;
    if (!GenericValidator.isBlankOrNull(rfmId)) {
      try
      {
        RfmDAO rfmDAO = new RfmDAO();
        arrRequestDetail = rfmDAO.getRfmDetails(Integer.parseInt(rfmId), kind);
      }
      catch (Exception localException) {}
    }
    if (arrRequestDetail == null) {
      arrRequestDetail = new ArrayList();
    }
    request.setAttribute("rfmDetailList", arrRequestDetail);
    return true;
  }
}
