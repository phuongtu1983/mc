package com.venus.mc.material;

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

public class ListSpe1Action
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    SpeDAO speDAO = new SpeDAO();
    ArrayList spe1List = null;
    
    ArrayList arrSpe1 = new ArrayList();
    
    LabelValueBean value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.spe.select"));
    value.setValue("???");
    arrSpe1.add(value);
    try
    {
      spe1List = speDAO.getSpe1s();
    }
    catch (Exception localException) {}
    for (int i = 0; i < spe1List.size(); i++)
    {
      SpeBean spe = (SpeBean)spe1List.get(i);
      value = new LabelValueBean();
      value.setLabel(String.valueOf(spe.getSign()) + " - " + String.valueOf(spe.getNote()).replaceAll("null", ""));
      value.setValue(String.valueOf(spe.getSpe1Id()));
      arrSpe1.add(value);
    }
    request.setAttribute("spe1List", arrSpe1);
    this.actionForwardResult = "spe1a";
    
    SpeBean spebean = new SpeBean();
    request.setAttribute("speForm", spebean);
    
    return true;
  }
}
