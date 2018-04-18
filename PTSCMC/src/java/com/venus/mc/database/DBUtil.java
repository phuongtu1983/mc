package com.venus.mc.database;

import com.venus.core.database.DBInit;
import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

public class DBUtil
{
  public static int executeUpdate(String sql)
    throws SQLException
  {
    Connection myConnection = null;
    Statement stmt = null;
    result = 0;
    try
    {
      myConnection = DBInit.dataSource.getConnection();
      stmt = myConnection.createStatement();
      String logStr = "";
      
      System.out.println(myConnection.hashCode() + " : " + DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : executeUpdate: " + sql);
      LogUtil.info(sql);
      long milis = System.currentTimeMillis();
      result = stmt.executeUpdate(sql);
      milis = System.currentTimeMillis() - milis;
      System.out.println(myConnection.hashCode() + " : " + "time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
      
      return result;
    }
    catch (SQLException ex)
    {
      throw new SQLException(ex.getMessage());
    }
    finally
    {
      try
      {
        if (stmt != null)
        {
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : stament closed - before close: " + stmt.isClosed());
          stmt.close();
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : stament closed - after close: " + stmt.isClosed());
          stmt = null;
        }
        if (myConnection != null)
        {
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : connection closed - before close: " + myConnection.isClosed());
          myConnection.close();
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : connection closed - after close: " + myConnection.isClosed());
          myConnection = null;
        }
      }
      catch (SQLException e)
      {
        throw new SQLException(e.getMessage());
      }
    }
  }
  
  public static int executeInsert(String sql)
    throws SQLException
  {
    Connection myConnection = null;
    Statement stmt = null;
    result = 0;
    try
    {
      myConnection = DBInit.dataSource.getConnection();
      stmt = myConnection.createStatement();
      System.out.println(myConnection.hashCode() + " : " + DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : executeInsert: " + sql);
      LogUtil.info(sql);
      long milis = System.currentTimeMillis();
      stmt.executeUpdate(sql, 1);
      milis = System.currentTimeMillis() - milis;
      System.out.println(myConnection.hashCode() + " : " + "time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next())
      {
        result = rs.getInt(1);
        System.out.println("RETURN_GENERATED_KEYS : " + result);
      }
      return result;
    }
    catch (SQLException ex)
    {
      throw new SQLException(ex.getMessage());
    }
    finally
    {
      try
      {
        if (stmt != null)
        {
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : stament closed - before close: " + stmt.isClosed());
          stmt.close();
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : stament closed - after close: " + stmt.isClosed());
          stmt = null;
        }
        if (myConnection != null)
        {
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : connection closed - before close: " + myConnection.isClosed());
          myConnection.close();
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : connection closed - after close: " + myConnection.isClosed());
          myConnection = null;
        }
      }
      catch (SQLException e)
      {
        throw new SQLException(e.getMessage());
      }
    }
  }
  
  public static int executeInsertNoGenKeys(String sql)
    throws SQLException
  {
    Connection myConnection = null;
    Statement stmt = null;
    result = 0;
    try
    {
      myConnection = DBInit.dataSource.getConnection();
      stmt = myConnection.createStatement();
      System.out.println(myConnection.hashCode() + " : " + DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : executeInsert: " + sql);
      LogUtil.info(sql);
      long milis = System.currentTimeMillis();
      result = stmt.executeUpdate(sql, 2);
      milis = System.currentTimeMillis() - milis;
      System.out.println(myConnection.hashCode() + " : " + "time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
      
      return result;
    }
    catch (SQLException ex)
    {
      throw new SQLException(ex.getMessage());
    }
    finally
    {
      try
      {
        if (stmt != null)
        {
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : stament closed - before close: " + stmt.isClosed());
          stmt.close();
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : stament closed - after close: " + stmt.isClosed());
          stmt = null;
        }
        if (myConnection != null)
        {
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : connection closed - before close: " + myConnection.isClosed());
          myConnection.close();
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : connection closed - after close: " + myConnection.isClosed());
          myConnection = null;
        }
      }
      catch (SQLException e)
      {
        throw new SQLException(e.getMessage());
      }
    }
  }
  
  public static int executeInsert(String sql, int autoGeneratedKeys)
    throws SQLException
  {
    Connection myConnection = null;
    Statement stmt = null;
    result = 0;
    try
    {
      myConnection = DBInit.dataSource.getConnection();
      stmt = myConnection.createStatement();
      System.out.println(myConnection.hashCode() + " : " + DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : executeInsert: " + sql);
      LogUtil.info(sql);
      long milis = System.currentTimeMillis();
      int ret = stmt.executeUpdate(sql, autoGeneratedKeys);
      milis = System.currentTimeMillis() - milis;
      System.out.println(myConnection.hashCode() + " : " + "time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
      ResultSet rs;
      if (autoGeneratedKeys == 1)
      {
        rs = stmt.getGeneratedKeys();
        if (!rs.next()) {}
      }
      return rs.getInt(1);
    }
    catch (SQLException ex)
    {
      throw new SQLException(ex.getMessage());
    }
    finally
    {
      try
      {
        if (stmt != null)
        {
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : stament closed - before close: " + stmt.isClosed());
          stmt.close();
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : stament closed - after close: " + stmt.isClosed());
          stmt = null;
        }
        if (myConnection != null)
        {
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : connection closed - before close: " + myConnection.isClosed());
          myConnection.close();
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : connection closed - after close: " + myConnection.isClosed());
          myConnection = null;
        }
      }
      catch (SQLException e)
      {
        throw new SQLException(e.getMessage());
      }
    }
  }
  
  public static ResultSet executeQuery(String sql)
    throws SQLException
  {
    Connection myConnection = null;
    Statement stmt = null;
    ResultSet rs = null;
    try
    {
      myConnection = DBInit.dataSource.getConnection();
      stmt = myConnection.createStatement();
      System.out.println(myConnection.hashCode() + " : " + DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : executeQuery: " + sql);
      long milis = System.currentTimeMillis();
      
      rs = stmt.executeQuery(sql);
      
      milis = System.currentTimeMillis() - milis;
      
      System.out.println(myConnection.hashCode() + " : " + "time : " + (int)(milis / 1000L % 60L) + " phut " + (int)(milis / 1000L / 60L) + " mili giay");
    }
    catch (SQLException ex)
    {
      if (stmt != null)
      {
        System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + "error : " + myConnection.hashCode() + " : stament closed - before close: " + stmt.isClosed());
        stmt.close();
        System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + "error : " + myConnection.hashCode() + " : stament closed - after close: " + stmt.isClosed());
        stmt = null;
      }
      if (myConnection != null)
      {
        System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + "error : " + myConnection.hashCode() + " : connection closed - before close: " + myConnection.isClosed());
        myConnection.close();
        System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + "error : " + myConnection.hashCode() + " : connection closed - after close: " + myConnection.isClosed());
        myConnection = null;
      }
      throw new SQLException(ex.getMessage());
    }
    return rs;
  }
  
  @Deprecated
  public static boolean checkExisted(String sql)
    throws SQLException
  {
    boolean result = false;
    ResultSet rs = null;
    try
    {
      rs = executeQuery(sql);
      if (rs.next()) {
        result = true;
      } else {
        result = false;
      }
    }
    catch (SQLException ex)
    {
      throw new SQLException(ex.getMessage());
    }
    finally
    {
      closeConnection(rs);
    }
    return result;
  }
  
  public static boolean checkFieldExisted(String table, String fieldName, String fieldValue)
  {
    if ((GenericValidator.isBlankOrNull(table)) || (GenericValidator.isBlankOrNull(fieldName)) || (GenericValidator.isBlankOrNull(fieldValue))) {
      return false;
    }
    String sql = "select " + fieldName + " from " + table + " where " + fieldName + "='" + fieldValue + "'";
    try
    {
      return checkExisted(sql);
    }
    catch (Exception ex) {}
    return false;
  }
  
  public static boolean checkFieldExisted(String table, String fieldName, int fieldValue)
  {
    if ((GenericValidator.isBlankOrNull(table)) || (GenericValidator.isBlankOrNull(fieldName))) {
      return false;
    }
    String sql = "select " + fieldName + " from " + table + " where " + fieldName + "=" + fieldValue;
    try
    {
      return checkExisted(sql);
    }
    catch (Exception ex) {}
    return false;
  }
  
  private static String outputToLog(String sql)
  {
    String result = "";
    String lowercase = sql.toLowerCase();
    if (lowercase.indexOf("update ") == 0)
    {
      int setInd = lowercase.indexOf("set");
      int whereInd = lowercase.indexOf("where");
      String fromStr = "";
      String setStr = "";
      String whereStr = "";
      if (setInd > -1)
      {
        String[] cols = null;
        String[] types = null;
        fromStr = lowercase.substring(6, setInd).trim();
        if (whereInd > -1)
        {
          setStr = sql.substring(setInd + 3, whereInd);
          whereStr = lowercase.substring(whereInd + 5);
        }
        else
        {
          setStr = lowercase.substring(setInd + 3);
        }
        if (!setStr.equals(""))
        {
          String[] pairs = setStr.split(",");
          cols = new String[pairs.length];
          types = new String[pairs.length];
          String str = "";
          int index = 0;
          for (int i = 0; i < pairs.length; i++)
          {
            str = pairs[i];
            index = str.indexOf("=");
            if (index > -1)
            {
              cols[i] = str.substring(0, index).trim();
              if (str.substring(index + 1, index + 2).equals("'")) {
                types[i] = "'";
              } else {
                types[i] = "";
              }
            }
          }
        }
        if (cols.length > 0)
        {
          result = "select ";
          for (int i = 0; i < cols.length; i++)
          {
            result = result + cols[i];
            if (i < cols.length - 1) {
              result = result + ",";
            }
          }
          result = result + " from " + fromStr + " where " + whereStr;
          try
          {
            result = "Before update : " + executeSelect(result, cols, types);
          }
          catch (Exception localException) {}
        }
      }
    }
    return result;
  }
  
  private static String executeSelect(String sql, String[] cols, String[] types)
    throws SQLException
  {
    ResultSet rs = null;
    String result = "";
    try
    {
      rs = executeQuery(sql);
      String value = "";
      while (rs.next()) {
        for (int i = 0; i < cols.length; i++)
        {
          value = rs.getString(cols[i]);
          result = result + ", " + cols[i] + " = " + types[i] + value + types[i];
        }
      }
    }
    catch (SQLException ex)
    {
      throw new SQLException(ex.getMessage());
    }
    finally
    {
      closeConnection(rs);
    }
    if (!result.equals("")) {
      result = result.substring(2);
    }
    return result;
  }
  
  public static void closeConnection(ResultSet rs)
    throws SQLException
  {
    try
    {
      if (rs != null)
      {
        Connection myConnection = null;
        if (rs.getStatement() != null)
        {
          myConnection = rs.getStatement().getConnection();
          Statement stmt = rs.getStatement();
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : stament closed - before close: " + stmt.isClosed());
          stmt.close();
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : stament closed - after close: " + stmt.isClosed());
          stmt = null;
        }
        if (myConnection != null)
        {
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : connection closed - before close: " + myConnection.isClosed());
          myConnection.close();
          System.out.println(DateUtil.today("dd/MMM/yyyy hh:mm:ss") + " - " + myConnection.hashCode() + " : connection closed - after close: " + myConnection.isClosed());
          myConnection = null;
        }
        rs.close();
        rs = null;
      }
    }
    catch (Exception localException) {}
  }
}
