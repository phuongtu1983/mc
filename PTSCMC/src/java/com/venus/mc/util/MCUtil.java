package com.venus.mc.util;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts.util.LabelValueBean;

public class MCUtil
{
  public static final String storageRoot = "storage";
  
  public static String getBundleString(String key)
  {
    return ResourceBundle.getBundle("resources").getString(key);
  }
  
  public static String displayMemberInfo(OnlineUser member, boolean isShowLastLogon)
  {
    if (member == null) {
      return getBundleString("message.welcomedefault");
    }
    String str = getBundleString("message.welcome");
    if (StringUtil.isBlankOrNull(member.getFullName())) {
      str = str + " <span class=\"userstyle\">" + member.getName() + "</span>";
    } else {
      str = str + " <span class=\"userstyle\">" + member.getFullName() + "</span>";
    }
    if (!StringUtil.isBlankOrNull(member.getOrganizationName())) {
      str = str + " - : <strong>" + member.getOrganizationName() + "</strong>";
    }
    if (isShowLastLogon)
    {
      if (member.getLastLogonTimestamp() != null) {
        str = str + " (Last Logon: " + DateUtil.formatDate(member.getLastLogonTimestamp(), "hh:mm dd-MM-yy");
      }
      if (!StringUtil.isBlankOrNull(member.getLastLogonIP())) {
        str = str + " - IP: " + member.getLastLogonIP() + ")";
      }
    }
    return str;
  }
  
  public static int getMemberID(HttpSession session)
  {
    int memberid = -1;
    if ((session != null) && (session.getAttribute("employeeObj") != null))
    {
      OnlineUser user = (OnlineUser)session.getAttribute("employeeObj");
      memberid = user.getID();
    }
    return memberid;
  }
  
  public static int getOrganizationID(HttpSession session)
  {
    int orgid = -1;
    if ((session != null) && (session.getAttribute("employeeObj") != null))
    {
      OnlineUser user = (OnlineUser)session.getAttribute("employeeObj");
      orgid = user.getOrganizationID();
    }
    return orgid;
  }
  
  public static String getOrganizationName(HttpSession session)
  {
    String orgName = "";
    if ((session != null) && (session.getAttribute("employeeObj") != null))
    {
      OnlineUser user = (OnlineUser)session.getAttribute("employeeObj");
      orgName = user.getOrganizationName();
    }
    return orgName;
  }
  
  public static String getMemberFullName(HttpSession session)
  {
    String fullName = "";
    if ((session != null) && (session.getAttribute("employeeObj") != null))
    {
      OnlineUser user = (OnlineUser)session.getAttribute("employeeObj");
      fullName = user.getFullName();
    }
    return fullName;
  }
  
  public static String getMemberName(HttpSession session)
  {
    String memberName = "";
    if ((session != null) && (session.getAttribute("employeeObj") != null))
    {
      OnlineUser user = (OnlineUser)session.getAttribute("employeeObj");
      memberName = user.getName();
    }
    return memberName;
  }
  
  public static OnlineUser getOnlineUser(HttpSession session)
  {
    OnlineUser user = null;
    if ((session != null) && (session.getAttribute("employeeObj") != null)) {
      user = (OnlineUser)session.getAttribute("employeeObj");
    }
    return user;
  }
  
  public static String getParameter(String parameter, HttpServletRequest request)
  {
    String retStr = "";
    if (!StringUtil.isBlankOrNull(parameter))
    {
      retStr = request.getParameter(parameter);
      if (StringUtil.isBlankOrNull(retStr))
      {
        retStr = (String)request.getAttribute(parameter);
        if (StringUtil.isBlankOrNull(retStr))
        {
          retStr = (String)request.getSession().getAttribute(parameter);
          if (StringUtil.isBlankOrNull(retStr)) {
            retStr = "";
          }
        }
      }
    }
    return retStr;
  }
  
  public static void createFullFolder(String pathName)
  {
    String str = pathName.replace('\\', '/');
    StringTokenizer token = new StringTokenizer(str, "/");
    String dir = "";
    while (token.hasMoreTokens())
    {
      str = token.nextToken();
      if (str.indexOf(':') == 1)
      {
        dir = str;
      }
      else
      {
        dir = dir + "/" + str;
        try
        {
          new File(dir).mkdir();
        }
        catch (Exception localException) {}
      }
    }
  }
  
  public static String getFileName(String pathName)
  {
    String pthName = pathName.replace('\\', '/');
    return pthName.substring(pthName.lastIndexOf('/') + 1);
  }
  
  @Deprecated
  public static String getUploadFolder(int ftype)
  {
    String strFolder = "private";
    switch (ftype)
    {
    case 1: 
      strFolder = "request";
      break;
    case 2: 
      strFolder = "tenderplan";
    }
    return strFolder;
  }
  
  public static String getSpaces(int length)
  {
    String str = "";
    for (int i = 0; i < length; i++) {
      str = str + "&nbsp;";
    }
    return str;
  }
  
  public static String getMenuSpaces(int length, HttpServletRequest request)
  {
    String uagent = request.getHeader("user-agent");
    if (uagent.indexOf("Firefox") == -1) {
      return "";
    }
    String str = "";
    for (int i = 0; i < length; i++) {
      str = str + "&nbsp;";
    }
    return str;
  }
  
  public static boolean isInSet(int value, int[] arrValues)
  {
    for (int i = 0; i < arrValues.length; i++) {
      if (value == arrValues[i]) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isInSet(String value, String[] arrValues)
  {
    for (int i = 0; i < arrValues.length; i++) {
      if (value.equals(arrValues[i])) {
        return true;
      }
    }
    return false;
  }
  
  public static ArrayList getArrayCurrency()
  {
    ArrayList currency = new ArrayList();
    
    LabelValueBean value = new LabelValueBean();
    value.setLabel("VN?");
    value.setValue("VN?");
    currency.add(value);
    value = new LabelValueBean();
    value.setLabel("USD");
    value.setValue("USD");
    currency.add(value);
    value = new LabelValueBean();
    value.setLabel("AUD");
    value.setValue("AUD");
    currency.add(value);
    value = new LabelValueBean();
    value.setLabel("CAD");
    value.setValue("CAD");
    currency.add(value);
    value = new LabelValueBean();
    value.setLabel("CHF");
    value.setValue("CHF");
    currency.add(value);
    value = new LabelValueBean();
    value.setLabel("DKK");
    value.setValue("DKK");
    currency.add(value);
    value = new LabelValueBean();
    value.setLabel("EUR");
    value.setValue("EUR");
    currency.add(value);
    value = new LabelValueBean();
    value.setLabel("GBP");
    value.setValue("GBP");
    currency.add(value);
    value = new LabelValueBean();
    value.setLabel("HKD");
    value.setValue("HKD");
    currency.add(value);
    value = new LabelValueBean();
    value.setLabel("INR");
    value.setValue("INR");
    currency.add(value);
    value = new LabelValueBean();
    value.setLabel("JPY");
    value.setValue("JPY");
    currency.add(value);
    value = new LabelValueBean();
    value.setLabel("KRW");
    value.setValue("KRW");
    currency.add(value);
    value = new LabelValueBean();
    value.setLabel("KWD");
    value.setValue("KWD");
    currency.add(value);
    value = new LabelValueBean();
    value.setLabel("MYR");
    value.setValue("MYR");
    currency.add(value);
    value = new LabelValueBean();
    value.setLabel("NOK");
    value.setValue("NOK");
    currency.add(value);
    value = new LabelValueBean();
    value.setLabel("RUB");
    value.setValue("RUB");
    currency.add(value);
    value = new LabelValueBean();
    value.setLabel("SEK");
    value.setValue("SEK");
    currency.add(value);
    value = new LabelValueBean();
    value.setLabel("SGD");
    value.setValue("SGD");
    currency.add(value);
    value = new LabelValueBean();
    value.setLabel("THB");
    value.setValue("THB");
    currency.add(value);
    return currency;
  }
}
