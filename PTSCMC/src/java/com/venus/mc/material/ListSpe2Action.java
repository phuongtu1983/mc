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

public class ListSpe2Action
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String spe1 = request.getParameter("spe1");
    if (GenericValidator.isBlankOrNull(spe1)) {
      spe1 = "0";
    }
    String a = request.getParameter("a");
    if (GenericValidator.isBlankOrNull(a)) {
      a = "0";
    }
    SpeDAO spe2DAO = new SpeDAO();
    ArrayList spe2List = null;
    
    ArrayList arrSpe2 = new ArrayList();
    
    LabelValueBean value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.spe.select"));
    value.setValue("???");
    arrSpe2.add(value);
    if (a.equals("0"))
    {
      try
      {
        spe2List = spe2DAO.getSpe2s(spe1);
      }
      catch (Exception localException) {}
      for (int i = 0; i < spe2List.size(); i++)
      {
        SpeBean spe = (SpeBean)spe2List.get(i);
        value = new LabelValueBean();
        value.setLabel(String.valueOf(spe.getSign()) + " - " + String.valueOf(spe.getNote()).replaceAll("null", ""));
        value.setValue(String.valueOf(spe.getSpe2Id()));
        arrSpe2.add(value);
      }
      request.setAttribute("spe2List", arrSpe2);
      this.actionForwardResult = "spe2";
    }
    else
    {
      try
      {
        spe2List = spe2DAO.getSpe2as(spe1);
      }
      catch (Exception localException1) {}
      for (int i = 0; i < spe2List.size(); i++)
      {
        SpeBean spe = (SpeBean)spe2List.get(i);
        value = new LabelValueBean();
        value.setLabel(String.valueOf(spe.getSign()) + " - " + String.valueOf(spe.getNote()).replaceAll("null", ""));
        value.setValue(String.valueOf(spe.getSpe2Id()));
        arrSpe2.add(value);
      }
      request.setAttribute("spe2List", arrSpe2);
      this.actionForwardResult = "spe2a";
    }
    SpeBean spebean = new SpeBean();
    request.setAttribute("speForm", spebean);
    
    return true;
  }
}
