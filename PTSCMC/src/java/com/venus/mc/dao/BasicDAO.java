package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class BasicDAO
{
  private String involvedEmps = "";
  private String involvedOrgs = "";
  private int requestEmp = 0;
  private String requestOrg = "";
  
  public String getInvolvedEmps()
  {
    return this.involvedEmps;
  }
  
  public void setInvolvedEmps(String employees)
  {
    this.involvedEmps = employees;
  }
  
  public int getRequestEmp()
  {
    return this.requestEmp;
  }
  
  public void setRequestEmp(int requestEmp)
  {
    this.requestEmp = requestEmp;
  }
  
  public String getRequestOrg()
  {
    return this.requestOrg;
  }
  
  public void setRequestOrg(String requestOrg)
  {
    this.requestOrg = requestOrg;
  }
  
  public String getInvolvedOrgs()
  {
    return this.involvedOrgs;
  }
  
  public void setInvolvedOrgs(String involvedOrgs)
  {
    this.involvedOrgs = involvedOrgs;
  }
  
  public String formatArrayDate(String dates, String format)
  {
    String result = "";
    if (GenericValidator.isBlankOrNull(dates)) {
      return result;
    }
    String[] temp = dates.split(",");
    String date = "";
    try
    {
      Date d = null;
      for (int i = 0; i < temp.length; i++)
      {
        date = temp[i];
        if ((!GenericValidator.isBlankOrNull(date)) && (date.indexOf("0000") == -1)) {
          try
          {
            d = DateUtil.convertStringToDate(date, "yyyy-MM-dd");
            result = result + "," + DateUtil.formatDate(d, format);
          }
          catch (Exception localException) {}
        }
      }
    }
    catch (Exception localException1) {}
    if (!GenericValidator.isBlankOrNull(result)) {
      result = result.substring(1);
    }
    return result;
  }
  
  public String getNextNumber(String prefix, int length, String colName, String tableName)
    throws Exception
  {
    String result = "";
    ResultSet rs = null;
    try
    {
      String t = "";
      String y = "-";
      for (int i = 0; i < length; i++) {
        t = t + "_";
      }
      for (int i = 1; i < length; i++) {
        y = y + "_";
      }
      String sql = "select max(" + colName + ") as number from " + tableName + " where " + colName + " like '" + prefix + t + "'" + " and " + colName + " not like '" + prefix + y + "'";
      
      rs = DBUtil.executeQuery(sql);
      if (rs.next()) {
        result = rs.getString("number");
      }
      if ((result == null) || (result.equals("")))
      {
        result = "";
        for (int i = 0; i < length - 1; i++) {
          result = result + "0";
        }
        result = result + "1";
      }
      else
      {
        int num = 0;
        try
        {
          num = Integer.parseInt(result.substring(prefix.length())) + 1;
        }
        catch (Exception ex)
        {
          num = 0;
        }
        result = num + "";
        while (result.length() < length) {
          result = "0" + result;
        }
      }
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return result;
  }
}
