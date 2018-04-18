package com.venus.mc.util;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.auth.OnlineUserPermission;
import com.venus.mc.dao.PermissionDAO;
import com.venus.mc.permission.ApplicationPermissionBean;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PermissionUtil
{
  public static boolean hasPermission(HttpServletRequest request, String function, int permit)
  {
    HttpSession session = request.getSession();
    int userId = MCUtil.getMemberID(session);
    if ((session != null) && (session.getAttribute("permissionUserList") != null))
    {
      ArrayList<ApplicationPermissionBean> arrPer = (ArrayList)session.getAttribute("permissionUserList");
      ApplicationPermissionBean permission = null;
      for (int i = 0; i < arrPer.size(); i++)
      {
        permission = (ApplicationPermissionBean)arrPer.get(i);
        if (permission.getPermit().indexOf("," + permit + ",") > -1) {
          if (permission.getEmployees().indexOf("," + userId + ",") > -1)
          {
            String[] p = function.split(";");
            for (int j = 0; j < p.length; j++) {
              if (permission.getFunction().equals(p[j])) {
                return true;
              }
            }
          }
          else if (permission.getOrgEmployees().indexOf("," + userId + ",") > -1)
          {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public static boolean hasOneOfPermission(HttpServletRequest request, String function, String permit)
  {
    boolean result = false;
    String[] pers = permit.split(",");
    for (int i = 0; i < pers.length; i++)
    {
      int per = Integer.parseInt(pers[i]);
      result |= hasPermission(request, function, per);
    }
    return result;
  }
  
  public static boolean hasAllOfPermission(HttpServletRequest request, String function, String permit)
  {
    boolean result = true;
    String[] pers = permit.split(",");
    for (int i = 0; i < pers.length; i++)
    {
      int per = Integer.parseInt(pers[i]);
      result &= hasPermission(request, function, per);
    }
    return result;
  }
  
  public static String getEmployeesHasPermission(HttpServletRequest request)
  {
    HttpSession session = request.getSession();
    String employees = "0";
    int userId = MCUtil.getMemberID(session);
    if ((session != null) && (session.getAttribute("permissionUserList") != null) && (session.getAttribute("permissionUser") != null))
    {
      OnlineUserPermission up = (OnlineUserPermission)session.getAttribute("permissionUser");
      ArrayList<ApplicationPermissionBean> arrPer = (ArrayList)session.getAttribute("permissionUserList");
      ApplicationPermissionBean permission = null;
      for (int i = 0; i < arrPer.size(); i++)
      {
        permission = (ApplicationPermissionBean)arrPer.get(i);
        if (permission.getPermit().indexOf("," + up.getPermission() + ",") > -1)
        {
          String function = up.getFunction();
          String[] p = function.split(";");
          boolean hasPermission = false;
          for (int j = 0; j < p.length; j++) {
            if (permission.getFunction().equals(p[j]))
            {
              hasPermission = true;
              break;
            }
          }
          if (hasPermission) {
            if (permission.getEmployees().indexOf("," + userId + ",") > -1) {
              employees = employees + "," + permission.getOrgEmployees() + "," + userId;
            } else if (permission.getOrgEmployees().indexOf("," + userId + ",") > -1) {
              employees = employees + "," + permission.getOrgEmployees();
            }
          }
        }
      }
    }
    if (employees.equals("0")) {
      employees = userId + "";
    }
    return employees;
  }
  
  public static boolean getEmployeesHasPermission(HttpServletRequest request, int permissionId)
  {
    HttpSession session = request.getSession();
    boolean hasPermission = false;
    try
    {
      PermissionDAO permissionDAO = new PermissionDAO();
      ArrayList<ApplicationPermissionBean> arrPer = permissionDAO.getPermissionsOfEmployee(MCUtil.getMemberID(session), MCUtil.getOrganizationID(session));
      ApplicationPermissionBean bean = null;
      for (int i = 0; i < arrPer.size(); i++)
      {
        bean = (ApplicationPermissionBean)arrPer.get(i);
        if (!GenericValidator.isBlankOrNull(bean.getPermit())) {
          for (int j = 0; j < bean.getPermit().split(",").length; j++) {
            if (NumberUtil.parseInt(bean.getPermit().split(",")[j], 0) == permissionId)
            {
              hasPermission = true;
              break;
            }
          }
        }
      }
    }
    catch (Exception localException) {}
    return hasPermission;
  }
  
  public static boolean hasPermission(HttpServletRequest request, String function, int permit, int ownerId)
  {
    HttpSession session = request.getSession();
    int userId = MCUtil.getMemberID(session);
    if (ownerId == 0) {
      return hasPermission(request, function, permit);
    }
    if (userId == ownerId) {
      return true;
    }
    return false;
  }
  
  public static String FUNC_LIST = "1";
  public static String FUNC_ADD = "2";
  public static String FUNC_DELETE = "3";
  public static String FUNC_EDIT = "4";
  public static String FUNC_VIEW = "5";
  public static int PER_SYSTEM = 1;
  public static int PER_LIBRARY_VENDOR = 2;
  public static int PER_LIBRARY_VENDOR_EVAL = 3;
  public static int PER_LIBRARY_MATERIAL_COMPANY = 4;
  public static int PER_LIBRARY_MATERIAL_OUT = 5;
  public static int PER_LIBRARY_MATERIAL_CATALOG = 6;
  public static int PER_LIBRARY_MATERIAL_UNIT = 7;
  public static int PER_LIBRARY_MATERIAL_ORIGIN = 8;
  public static int PER_LIBRARY_MATERIAL_PRICE = 9;
  public static int PER_LIBRARY_STORE = 10;
  public static int PER_LIBRARY_PROJECT = 11;
  public static int PER_PURCHASING_REQUEST = 12;
  public static int PER_PURCHASING_REQUEST_MATERIAL = 13;
  public static int PER_PURCHASING_REQUEST_MATERIALNOTCODE = 71;
  public static int PER_PURCHASING_REQUEST_TRACING = 14;
  public static int PER_PURCHASING_REQUEST_GATHER = 15;
  public static int PER_PURCHASING_REQUEST_VIEW = 70;
  public static int PER_PURCHASING_INTERMEMO = 16;
  public static int PER_PURCHASING_TENDERPLAN = 17;
  public static int PER_PURCHASING_CONTRACT = 18;
  public static int PER_PURCHASING_PRINCIPLE = 19;
  public static int PER_PURCHASING_CONTRACT_FOLLOW = 20;
  public static int PER_PURCHASING_CONTRACT_EXECUTE = 21;
  public static int PER_PURCHASING_ORDER = 22;
  public static int PER_PURCHASING_DELIVERYREQUEST = 23;
  public static int PER_PURCHASING_DELIVERYNOTICE = 24;
  public static int PER_PURCHASING_DELIVERYNOTICE_PROJECT = 25;
  public static int PER_PURCHASING_DELIVERYNOTICE_ORG = 79;
  public static int PER_PURCHASING_INVOICE = 26;
  public static int PER_PURCHASING_PAYMENT = 27;
  public static int PER_STOCK_MRIR = 28;
  public static int PER_STOCK_MRIR_PROJECT = 29;
  public static int PER_STOCK_MRIR_STORE = 30;
  public static int PER_STOCK_MSV = 31;
  public static int PER_STOCK_RFM = 32;
  public static int PER_STOCK_ORG = 80;
  public static int PER_STOCK_RFM_MATERIAL = 33;
  public static int PER_STOCK_MIV = 34;
  public static int PER_STOCK_REPORT_REQUEST = 35;
  public static int PER_STOCK_REPORT_STORE = 36;
  public static int PER_STOCK_REPORT_STORE_PROJECT = 37;
  public static int PER_STOCK_REPORT_BALANCE = 38;
  public static int PER_STOCK_INVENTORY = 39;
  public static int PER_STOCK_PROJECT = 40;
  public static int PER_STOCK_PROJECT_MRIR = 41;
  public static int PER_STOCK_PROJECT_MSV = 42;
  public static int PER_STOCK_PROJECT_STATISTICS = 43;
  public static int PER_STOCK_PROJECT_RFM = 44;
  public static int PER_STOCK_PROJECT_MIV = 45;
  public static int PER_STOCK_STORE2 = 46;
  public static int PER_STOCK_YCKT_STORE2 = 47;
  public static int PER_STOCK_YCKT_REDUNDANT = 61;
  public static int PER_STOCK_YCKT_PROJECT = 62;
  public static int PER_EQUIPMENT_COLOR = 48;
  public static int PER_EQUIPMENT = 49;
  public static int PER_EQUIPMENT_ASSET = 50;
  public static int PER_EQUIPMENT_MC = 51;
  public static int PER_EQUIPMENT_MCO = 52;
  public static int PER_EQUIPMENT_EMC = 53;
  public static int PER_EQUIPMENT_EMCO = 54;
  public static int PER_EQUIPMENT_FACTREPORT = 63;
  public static int PER_EQUIPMENT_REPAIRREQUEST = 64;
  public static int PER_EQUIPMENT_SURVEYREPORT = 65;
  public static int PER_LIBRARY = 55;
  public static int PER_MATERIAL = 56;
  public static int PER_PURCHASING = 57;
  public static int PER_CONTRACT = 58;
  public static int PER_STORE = 59;
  public static int PER_STORE_LEVEL2 = 60;
  public static int PER_PURCHASING_REQUEST_CHECK = 72;
  public static int PER_PURCHASING_REQUEST_STORE = 73;
  public static int PER_PURCHASING_REQUEST_PLANDEP = 74;
  public static int PER_PURCHASING_REQUEST_MANAGER = 75;
  public static int PER_LIBRARY_CERTIFICATE = 76;
  public static int PER_LIBRARY_INCOTERM = 77;
  public static int PER_STOCK_REPORT_STORE_LEVEL2 = 78;
  public static int PER_PURCHASING_REQUEST_SHORTCUT = 79;
  public static int PER_PURCHASING_CONTRACT_UPDATE_STATUS = 80;
}
