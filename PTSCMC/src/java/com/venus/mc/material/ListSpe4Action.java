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

public class ListSpe4Action
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String spe3 = request.getParameter("spe3");
    if (GenericValidator.isBlankOrNull(spe3)) {
      return true;
    }
    String a = request.getParameter("a");
    if (GenericValidator.isBlankOrNull(a)) {
      a = "0";
    }
    SpeDAO spe4DAO = new SpeDAO();
    ArrayList spe4List = null;
    
    ArrayList arrSpe4 = new ArrayList();
    
    LabelValueBean value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.spe.select"));
    value.setValue("???");
    arrSpe4.add(value);
    if (a.equals("0"))
    {
      try
      {
        spe4List = spe4DAO.getSpe4s(spe3);
      }
      catch (Exception localException) {}
      for (int i = 0; i < spe4List.size(); i++)
      {
        SpeBean spe = (SpeBean)spe4List.get(i);
        value = new LabelValueBean();
        value.setLabel(String.valueOf(spe.getSign()) + " - " + String.valueOf(spe.getNote()).replaceAll("null", ""));
        value.setValue(String.valueOf(spe.getSpe4Id()));
        arrSpe4.add(value);
      }
      request.setAttribute("spe4List", arrSpe4);
      this.actionForwardResult = "spe4";
    }
    else
    {
      try
      {
        spe4List = spe4DAO.getSpe4as(spe3);
      }
      catch (Exception localException1) {}
      for (int i = 0; i < spe4List.size(); i++)
      {
        SpeBean spe = (SpeBean)spe4List.get(i);
        value = new LabelValueBean();
        value.setLabel(String.valueOf(spe.getSign()) + " - " + String.valueOf(spe.getNote()).replaceAll("null", ""));
        value.setValue(String.valueOf(spe.getSpe4Id()));
        arrSpe4.add(value);
      }
      request.setAttribute("spe4List", arrSpe4);
      this.actionForwardResult = "spe4a";
    }
    SpeBean spebean = new SpeBean();
    request.setAttribute("speForm", spebean);
    return true;
  }
}
