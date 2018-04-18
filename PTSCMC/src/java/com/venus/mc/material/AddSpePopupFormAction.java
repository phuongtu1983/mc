package com.venus.mc.material;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.SpeBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.SpeDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

public class AddSpePopupFormAction
  extends SpineAction
{
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    SpeFormBean formBean = (SpeFormBean)form;
    SpeBean bean = new SpeBean();
    
    session.setAttribute("speForm", bean);
    
    String spe0 = MCUtil.getParameter("spe", request);
    session.setAttribute("spe", spe0);
    
    String[] kind = request.getParameterValues("kind");
    String[] speId = request.getParameterValues("speId");
    String[] save = request.getParameterValues("save");
    String s1 = "0";
    String s2 = "0";
    String s3 = "0";
    String s4 = "0";
    String s5 = "0";
    String s6 = "0";
    String[] del = request.getParameterValues("del");
    String[] add = request.getParameterValues("add");
    String[] edit = request.getParameterValues("edit");
    String spes = "";
    String spe = "";
    
    SpeBean bean1 = new SpeBean();
    if ((save != null) && 
      (save[0].equals("1")))
    {
      SpeDAO speDAO = new SpeDAO();
      try
      {
        bean1.setSign(formBean.getSign());
        bean1.setNote(formBean.getNote());
        bean1.setSpeId(NumberUtil.parseInt(speId[0], 0));
        bean1.setSpe1Id(NumberUtil.parseInt(speId[0], 0));
        bean1.setSpe2Id(NumberUtil.parseInt(speId[0], 0));
        bean1.setSpe3Id(NumberUtil.parseInt(speId[0], 0));
        bean1.setSpe4Id(NumberUtil.parseInt(speId[0], 0));
        bean1.setSpe5Id(NumberUtil.parseInt(speId[0], 0));
        bean1.setSpe(kind[0]);
        OnlineUser user = MCUtil.getOnlineUser(session);
        LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
        
        speDAO.insertSpe(bean1);
      }
      catch (Exception ex)
      {
        LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
        ex.printStackTrace();
      }
    }
    if ((del != null) && 
      (del[0].equals("1")))
    {
      SpeDAO speDAO = new SpeDAO();
      if (session.getAttribute("spe") != null)
      {
        spe = session.getAttribute("spe").toString();
        session.removeAttribute("spe");
      }
      s1 = MCUtil.getParameter("spe1", request);
      s2 = MCUtil.getParameter("spe2", request);
      s3 = MCUtil.getParameter("spe3", request);
      s4 = MCUtil.getParameter("spe4", request);
      s5 = MCUtil.getParameter("spe5", request);
      s6 = MCUtil.getParameter("spe6", request);
      try
      {
        if (del != null)
        {
          if (spe.equals("1")) {
            spes = s1;
          }
          if (spe.equals("2")) {
            spes = s2;
          }
          if (spe.equals("3")) {
            spes = s3;
          }
          if (spe.equals("4")) {
            spes = s4;
          }
          if (spe.equals("5")) {
            spes = s5;
          }
          if (spe.equals("6")) {
            spes = s6;
          }
          bean1.setSpe(spe);
          speDAO.deleteSpe(spe, spes);
        }
        else
        {
          OnlineUser user = MCUtil.getOnlineUser(session);
          LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
        }
      }
      catch (Exception ex)
      {
        LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
        ex.printStackTrace();
      }
    }
    SpeDAO spe1DAO = new SpeDAO();
    ArrayList spe1List = null;
    try
    {
      spe1List = spe1DAO.getSpe1s();
    }
    catch (Exception localException1) {}
    ArrayList arrSpe1 = new ArrayList();
    
    LabelValueBean value = new LabelValueBean();
    value.setLabel(MCUtil.getBundleString("message.spe.select"));
    value.setValue("0");
    arrSpe1.add(value);
    for (int i = 0; i < spe1List.size(); i++)
    {
      SpeBean spe1 = (SpeBean)spe1List.get(i);
      value = new LabelValueBean();
      value.setLabel(String.valueOf(spe1.getSign()) + " - " + String.valueOf(StringUtil.decodeString(spe1.getNote())));
      value.setValue(String.valueOf(spe1.getSpe1Id()));
      arrSpe1.add(value);
    }
    request.setAttribute("spe1List", arrSpe1);
    
    return true;
  }
  
  protected String getFunction()
  {
    return PermissionUtil.FUNC_VIEW + ";" + PermissionUtil.FUNC_EDIT;
  }
  
  protected int getPermit()
  {
    return PermissionUtil.PER_LIBRARY_MATERIAL_CATALOG;
  }
}
