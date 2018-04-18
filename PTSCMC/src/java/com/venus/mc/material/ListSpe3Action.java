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

public class ListSpe3Action
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String spe2 = request.getParameter("spe2");
    if (GenericValidator.isBlankOrNull(spe2)) {
      return true;
    }
    String a = request.getParameter("a");
    if (GenericValidator.isBlankOrNull(a)) {
      a = "0";
    }
    SpeDAO spe3DAO = new SpeDAO();
    ArrayList spe3List = null;
    
    ArrayList arrSpe3 = new ArrayList();
    
    LabelValueBean value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.spe.select"));
    value.setValue("???");
    arrSpe3.add(value);
    if (a.equals("0"))
    {
      try
      {
        spe3List = spe3DAO.getSpe3s(spe2);
      }
      catch (Exception localException) {}
      for (int i = 0; i < spe3List.size(); i++)
      {
        SpeBean spe = (SpeBean)spe3List.get(i);
        value = new LabelValueBean();
        value.setLabel(String.valueOf(spe.getSign()) + " - " + String.valueOf(spe.getNote()).replaceAll("null", ""));
        value.setValue(String.valueOf(spe.getSpe3Id()));
        arrSpe3.add(value);
      }
      request.setAttribute("spe3List", arrSpe3);
      this.actionForwardResult = "spe3";
    }
    else
    {
      try
      {
        spe3List = spe3DAO.getSpe3as(spe2);
      }
      catch (Exception localException1) {}
      for (int i = 0; i < spe3List.size(); i++)
      {
        SpeBean spe = (SpeBean)spe3List.get(i);
        value = new LabelValueBean();
        value.setLabel(String.valueOf(spe.getSign()) + " - " + String.valueOf(spe.getNote()).replaceAll("null", ""));
        value.setValue(String.valueOf(spe.getSpe3Id()));
        arrSpe3.add(value);
      }
      request.setAttribute("spe3List", arrSpe3);
      this.actionForwardResult = "spe3a";
    }
    SpeBean spebean = new SpeBean();
    request.setAttribute("speForm", spebean);
    
    return true;
  }
}
