package com.venus.mc.material;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.SpeBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.SpeDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class ListSpe5Action
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String spe4 = request.getParameter("spe4");
    if (GenericValidator.isBlankOrNull(spe4)) {
      return true;
    }
    String a = request.getParameter("a");
    if (GenericValidator.isBlankOrNull(a)) {
      a = "0";
    }
    SpeDAO spe5DAO = new SpeDAO();
    ArrayList spe5List = null;
    
    ArrayList arrSpe5 = new ArrayList();
    
    LabelValueBean value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.spe.select"));
    value.setValue("???");
    arrSpe5.add(value);
    if (a.equals("0"))
    {
      try
      {
        spe5List = spe5DAO.getSpe5s(spe4);
      }
      catch (Exception localException) {}
      for (int i = 0; i < spe5List.size(); i++)
      {
        SpeBean spe = (SpeBean)spe5List.get(i);
        value = new LabelValueBean();
        value.setLabel(String.valueOf(spe.getSign()) + " - " + String.valueOf(spe.getNote()).replaceAll("null", ""));
        value.setValue(String.valueOf(spe.getSpe5Id()));
        arrSpe5.add(value);
      }
      request.setAttribute("spe5List", arrSpe5);
      this.actionForwardResult = "spe5";
    }
    else
    {
      try
      {
        spe5List = spe5DAO.getSpe5as(spe4);
      }
      catch (Exception localException1) {}
      for (int i = 0; i < spe5List.size(); i++)
      {
        SpeBean spe = (SpeBean)spe5List.get(i);
        value = new LabelValueBean();
        value.setLabel(String.valueOf(spe.getSign()) + " - " + String.valueOf(spe.getNote()).replaceAll("null", ""));
        value.setValue(String.valueOf(spe.getSpe5Id()));
        arrSpe5.add(value);
      }
      request.setAttribute("spe5List", arrSpe5);
      this.actionForwardResult = "spe5a";
    }
    SpeBean spebean = new SpeBean();
    request.setAttribute("speForm", spebean);
    return true;
  }
}
