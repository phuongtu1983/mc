package com.venus.mc.core;

import com.venus.core.util.CheckLogonTag;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.OutputUtil;
import com.venus.mc.auth.OnlineUserPermission;
import com.venus.mc.bean.ParameterBean;
import com.venus.mc.dao.ParameterDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.PermissionUtil;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class SpineAction
  extends Action
{
  protected String actionForwardResult;
  protected String strErrors;
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    this.actionForwardResult = "success";
    HttpSession session = request.getSession();
    if (checkLogin(mapping, form, request))
    {
      if (isValidUrl(request))
      {
        OnlineUserPermission up = new OnlineUserPermission();
        up.setUserId(MCUtil.getMemberID(session));
        up.setFunction(getFunction());
        up.setPermission(getPermit());
        if ((up.getPermission() != 0) && (GenericValidator.isBlankOrNull(up.getFunction()))) {
          session.setAttribute("permissionUser", up);
        }
        if (checkPermission(mapping, form, request))
        {
          if (isPreventAccess()) {
            return mapping.findForward("pauseSystem");
          }
          boolean result = doAction(mapping, form, request, response);
          session.removeAttribute("permissionUser");
          return doActionResult(result, mapping, request, response);
        }
        ActionMessages errors = new ActionMessages();
        errors.add("permissioninvalid", new ActionMessage("errors.permissionInvalid"));
        saveErrors(request, errors);
        this.actionForwardResult = "errorForward";
      }
      else
      {
        String message = getXmlMessage();
        if (!GenericValidator.isBlankOrNull(message))
        {
          ActionForward af = mapping.findForward("jsonForward");
          String path = af.getPath();
          String params = getXmlParameters(mapping, form, request, response);
          path = path + "?mapMessage=" + message + "&params=" + params;
          return new ActionForward(path);
        }
        ActionMessages errors = new ActionMessages();
        errors.add("urlnotvalid", new ActionMessage("errors.urlNotValid"));
        saveErrors(request, errors);
        return mapping.findForward("errorForward");
      }
    }
    else
    {
      String message = getXmlMessage();
      if (!GenericValidator.isBlankOrNull(message))
      {
        ActionForward af = mapping.findForward("jsonForward");
        String path = af.getPath();
        String params = getXmlParameters(mapping, form, request, response);
        path = path + "?mapMessage=" + message + "&params=" + params;
        request.getSession().setAttribute("preLoginPage", path);
      }
      OutputUtil.sendStringToOutput(response, "login");
      return null;
    }
    session.removeAttribute("permissionUser");
    return mapping.findForward(this.actionForwardResult);
  }
  
  protected boolean checkLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request)
  {
    HttpSession session = request.getSession();
    ActionMessages errors = new ActionMessages();
    if (!CheckLogonTag.isValidSession(session, "employeeObj"))
    {
      errors.add("userLogin", new ActionMessage("errors.user.userInvalid"));
      saveErrors(request, errors);
      String query = "/" + request.getRequestURI();
      session.setAttribute("preLoginPage", query);
      this.actionForwardResult = "globalLogin";
      return false;
    }
    return true;
  }
  
  protected boolean checkPermission(ActionMapping mapping, ActionForm form, HttpServletRequest request)
  {
    HttpSession session = request.getSession();
    OnlineUserPermission up = null;
    if ((session != null) && (session.getAttribute("permissionUser") != null))
    {
      up = (OnlineUserPermission)session.getAttribute("permissionUser");
      return PermissionUtil.hasPermission(request, up.getFunction(), up.getPermission());
    }
    return true;
  }
  
  protected boolean doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    return true;
  }
  
  protected boolean isAjax()
  {
    return false;
  }
  
  protected boolean isReturnStream()
  {
    return false;
  }
  
  protected boolean isValidUrl(HttpServletRequest request)
  {
    if ((!GenericValidator.isBlankOrNull(request.getParameter("session"))) && 
      (request.getSession().getId().equals(request.getParameter("session")))) {
      return true;
    }
    return false;
  }
  
  protected String getXmlMessage()
  {
    return "";
  }
  
  protected String getXmlParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    return "";
  }
  
  protected String getErrorsString(HttpServletRequest request)
  {
    String errorsString = "";
    ActionMessages messages = getErrors(request);
    Iterator<ActionMessage> lstMessage = messages.get();
    ActionMessage message = null;
    while (lstMessage.hasNext())
    {
      message = (ActionMessage)lstMessage.next();
      errorsString = errorsString + MCUtil.getBundleString(message.getKey());
    }
    return errorsString + " " + this.strErrors;
  }
  
  protected ActionForward doActionResult(boolean result, ActionMapping mapping, HttpServletRequest request, HttpServletResponse response)
  {
    try
    {
      if (!result)
      {
        ActionForward input = mapping.getInputForward();
        if ((input != null) && (!GenericValidator.isBlankOrNull(input.getName()))) {
          return input;
        }
        String error = "error:" + getErrorsString(request);
        OutputUtil.sendStringToOutput(response, error);
      }
      else if (!isReturnStream())
      {
        ActionForward af = null;
        String forwardAction = "";
        if (!this.actionForwardResult.equals("success")) {
          forwardAction = this.actionForwardResult;
        } else {
          forwardAction = "success";
        }
        af = mapping.findForward(forwardAction);
        if (af != null) {
          return af;
        }
        OutputUtil.sendStringToOutput(response, forwardAction);
      }
      else
      {
        customizedReturnAction(request, response);
      }
    }
    catch (Exception localException) {}
    return null;
  }
  
  protected String getFunction()
  {
    return "";
  }
  
  protected int getPermit()
  {
    return 0;
  }
  
  protected void customizedReturnAction(HttpServletRequest request, HttpServletResponse response) {}
  
  protected boolean isPreventAccess()
  {
    boolean result = false;
    try
    {
      ParameterDAO paramDAO = new ParameterDAO();
      String name = "prevent_access";
      ParameterBean bean = paramDAO.getParameter(name);
      if ((bean != null) && 
        (bean.getValue().equals("on"))) {
        result = true;
      }
    }
    catch (Exception localException) {}
    return result;
  }
}
