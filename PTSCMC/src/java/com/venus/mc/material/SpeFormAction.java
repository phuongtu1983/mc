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

public class SpeFormAction
  extends SpineAction
{
  private String result = "";
  
  public boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    HttpSession session = request.getSession();
    SpeFormBean formBean = (SpeFormBean)form;
    SpeBean bean = new SpeBean();
    boolean isExist = false;
    this.result = "";
    if (NumberUtil.parseInt(MCUtil.getParameter("spe", request), 0) == 0)
    {
      if (session.getAttribute("speForm") != null) {
        session.removeAttribute("speForm");
      }
      if (session.getAttribute("spe") != null) {
        session.removeAttribute("spe");
      }
      if (session.getAttribute("spe1") != null) {
        session.removeAttribute("spe1");
      }
      if (session.getAttribute("spe2") != null) {
        session.removeAttribute("spe2");
      }
      if (session.getAttribute("spe3") != null) {
        session.removeAttribute("spe3");
      }
      if (session.getAttribute("spe4") != null) {
        session.removeAttribute("spe4");
      }
      if (session.getAttribute("spe5") != null) {
        session.removeAttribute("spe5");
      }
      if (session.getAttribute("spe6") != null) {
        session.removeAttribute("spe6");
      }
    }
    session.setAttribute("speForm", bean);
    
    String spe0 = MCUtil.getParameter("spe", request);
    session.setAttribute("spe", spe0);
    
    String[] spe00 = request.getParameterValues("spe");
    String[] speId = request.getParameterValues("speId");
    String[] save = request.getParameterValues("save");
    String[] sign = request.getParameterValues("sign");
    String[] note = request.getParameterValues("note");
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
    if (MCUtil.getParameter("spe1", request).length() > 0)
    {
      s1 = MCUtil.getParameter("spe1", request);
      session.setAttribute("spe1", s1);
    }
    if (MCUtil.getParameter("spe2", request).length() > 0)
    {
      s2 = MCUtil.getParameter("spe2", request);
      session.setAttribute("spe2", s2);
    }
    if (MCUtil.getParameter("spe3", request).length() > 0)
    {
      s3 = MCUtil.getParameter("spe3", request);
      session.setAttribute("spe3", s3);
    }
    if (MCUtil.getParameter("spe4", request).length() > 0)
    {
      s4 = MCUtil.getParameter("spe4", request);
      session.setAttribute("spe4", s4);
    }
    if (MCUtil.getParameter("spe5", request).length() > 0)
    {
      s5 = MCUtil.getParameter("spe5", request);
      session.setAttribute("spe5", s5);
    }
    if (MCUtil.getParameter("spe6", request).length() > 0)
    {
      s6 = MCUtil.getParameter("spe6", request);
      session.setAttribute("spe6", s6);
    }
    SpeBean bean1 = new SpeBean();
    if ((save != null) && 
      (save[0].equals("1")))
    {
      SpeDAO speDAO = new SpeDAO();
      if (session.getAttribute("spe") != null)
      {
        spe = session.getAttribute("spe").toString();
        session.removeAttribute("spe");
      }
      if (session.getAttribute("spe1") != null)
      {
        s1 = session.getAttribute("spe1").toString();
        session.removeAttribute("spe1");
      }
      if (session.getAttribute("spe2") != null)
      {
        s2 = session.getAttribute("spe2").toString();
        session.removeAttribute("spe2");
      }
      if (session.getAttribute("spe3") != null)
      {
        s3 = session.getAttribute("spe3").toString();
        session.removeAttribute("spe3");
      }
      if (session.getAttribute("spe4") != null)
      {
        s4 = session.getAttribute("spe4").toString();
        session.removeAttribute("spe4");
      }
      if (session.getAttribute("spe5") != null)
      {
        s5 = session.getAttribute("spe5").toString();
        session.removeAttribute("spe5");
      }
      if (session.getAttribute("spe6") != null)
      {
        s6 = session.getAttribute("spe6").toString();
        session.removeAttribute("spe6");
      }
      try
      {
        bean1.setSpe1Id(Integer.parseInt(s1));
        bean1.setSpe2Id(Integer.parseInt(s2));
        bean1.setSpe3Id(Integer.parseInt(s3));
        bean1.setSpe4Id(Integer.parseInt(s4));
        bean1.setSpe5Id(Integer.parseInt(s5));
        bean1.setSpe6Id(Integer.parseInt(s6));
        bean1.setSign(formBean.getSign());
        bean1.setNote(formBean.getNote());
        bean1.setSpeId(NumberUtil.parseInt(speId[0], 0));
        bean1.setSpe(spe);
        OnlineUser user = MCUtil.getOnlineUser(session);
        LogUtil.info("Username : " + user.getName() + " - Form : " + mapping.getPath() + " - EDIT");
        if (NumberUtil.parseInt(speId[0], 0) == 0) {
          speDAO.insertSpe(bean1);
        } else {
          speDAO.updateSpe(spe00[0], bean1);
        }
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
          isExist = speDAO.checkDeletedSpe(NumberUtil.parseInt(spe, 0), NumberUtil.parseInt(spes, 0));
          if (isExist)
          {
            this.result = MCUtil.getBundleString("errors.material.deleteSpe");
            return false;
          }
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
    
    SpeDAO speDAO = new SpeDAO();
    String sign1 = "";
    int kind = NumberUtil.parseInt(spe00[0], 0);
    try
    {
      if (kind == 1) {
        sign1 = speDAO.getSpeId1();
      } else if (kind == 2) {
        sign1 = speDAO.getSpeId2(NumberUtil.parseInt(speId[0], 0));
      } else if (kind == 3) {
        sign1 = speDAO.getSpeId3(NumberUtil.parseInt(speId[0], 0));
      } else if (kind == 4) {
        sign1 = speDAO.getSpeId4(NumberUtil.parseInt(speId[0], 0));
      } else if (kind == 5) {
        sign1 = speDAO.getSpeId5(NumberUtil.parseInt(speId[0], 0));
      } else if (kind == 6) {
        sign1 = speDAO.getSpeId6(NumberUtil.parseInt(speId[0], 0));
      }
      bean.setSign(NumberUtil.formatNumber(NumberUtil.parseDouble(sign1, 0.0D), "000"));
      bean.setNote("");
    }
    catch (Exception ex)
    {
      LogUtil.error("FAILED:Vender:add-" + ex.getMessage());
      ex.printStackTrace();
    }
    request.setAttribute("speForm", bean);
    
    return true;
  }
  
  protected String getErrorsString(HttpServletRequest request)
  {
    return this.result;
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
