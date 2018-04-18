package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.UnitBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UnitDAO
  extends BasicDAO
{
  public ArrayList getUnits()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from unit order by unit_en asc";
    
    ArrayList unitList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      UnitBean unit = null;
      while (rs.next())
      {
        unit = new UnitBean();
        unit.setUniId(rs.getInt("uni_id"));
        if (rs.getString("unit_vn") != null) {
          unit.setUnitVn(StringUtil.decodeString(rs.getString("unit_vn")));
        }
        if (rs.getString("unit_en") != null) {
          unit.setUnitEn(StringUtil.decodeString(rs.getString("unit_en")));
        }
        unitList.add(unit);
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
    return unitList;
  }
  
  public UnitBean getUnit(String unitid)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from unit where uni_id=" + unitid;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        UnitBean unit = new UnitBean();
        unit.setUniId(rs.getInt("uni_id"));
        if (rs.getString("unit_vn") != null) {
          unit.setUnitVn(StringUtil.decodeString(rs.getString("unit_vn")));
        }
        if (rs.getString("unit_en") != null) {
          unit.setUnitEn(StringUtil.decodeString(rs.getString("unit_en")));
        }
        return unit;
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
    return null;
  }
  
  public int insertUnit(UnitBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      sql = "insert into unit(unit_en,unit_vn) values ('" + bean.getUnitEn() + "','" + bean.getUnitVn() + "')";
      
      result = DBUtil.executeInsert(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    return result;
  }
  
  public void updateUnit(UnitBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "update unit set  unit_en='" + bean.getUnitEn() + "'" + ", unit_vn='" + bean.getUnitVn() + "'" + " where uni_id=" + bean.getUniId();
      
      DBUtil.executeUpdate(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally {}
  }
  
  public int deleteUnit(String unitid)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "delete from unit  where uni_id=" + unitid;
      result = DBUtil.executeUpdate(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    finally {}
    return result;
  }
  
  public ArrayList searchSimpleUnit(int fieldid, String strFieldvalue)
    throws Exception
  {
    String strFieldname = "";
    switch (fieldid)
    {
    case 1: 
      strFieldname = "unit_en";
      break;
    case 2: 
      strFieldname = "unit_vn";
    }
    ResultSet rs = null;
    
    String sql = "select uni_id, unit_en, unit_vn from unit where 1 ";
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
    }
    sql = sql + " order by uni_id desc";
    
    ArrayList unitList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      UnitBean unit = null;
      while (rs.next())
      {
        unit = new UnitBean();
        unit.setUniId(rs.getInt("uni_id"));
        if (rs.getString("unit_vn") != null) {
          unit.setUnitVn(StringUtil.decodeString(rs.getString("unit_vn")));
        }
        if (rs.getString("unit_en") != null) {
          unit.setUnitEn(StringUtil.decodeString(rs.getString("unit_en")));
        }
        unitList.add(unit);
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
    return unitList;
  }
  
  public ArrayList searchAdvUnit(UnitBean bean)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select uni_id, unit_en, unit_vn from unit where 1 ";
    if (!StringUtil.isBlankOrNull(bean.getUnitEn())) {
      sql = sql + " AND unit_en LIKE '%" + bean.getUnitEn() + "%'";
    }
    if (!StringUtil.isBlankOrNull(bean.getUnitVn())) {
      sql = sql + " AND unit_vn LIKE '%" + bean.getUnitVn() + "%'";
    }
    sql = sql + " order by uni_id desc";
    
    ArrayList unitList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      UnitBean unit = null;
      while (rs.next())
      {
        unit = new UnitBean();
        unit.setUniId(rs.getInt("uni_id"));
        if (rs.getString("unit_vn") != null) {
          unit.setUnitVn(StringUtil.decodeString(rs.getString("unit_vn")));
        }
        if (rs.getString("unit_en") != null) {
          unit.setUnitEn(StringUtil.decodeString(rs.getString("unit_en")));
        }
        unitList.add(unit);
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
    return unitList;
  }
  
  public boolean checkNameVn(int id, String nameVn)
    throws SQLException
  {
    ResultSet rs = null;
    try
    {
      rs = DBUtil.executeQuery("SELECT * FROM unit WHERE  uni_id <> " + id + " AND  unit_vn = '" + nameVn + "'");
      boolean bool;
      if (rs.next()) {
        return true;
      }
      return false;
    }
    catch (SQLException ex)
    {
      throw new SQLException(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
  }
  
  public boolean checkNameEn(int id, String nameEn)
    throws SQLException
  {
    ResultSet rs = null;
    try
    {
      rs = DBUtil.executeQuery("SELECT * FROM unit WHERE  uni_id <> " + id + " AND unit_en = '" + nameEn + "'");
      boolean bool;
      if (rs.next()) {
        return true;
      }
      return false;
    }
    catch (SQLException ex)
    {
      throw new SQLException(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
  }
  
  public boolean checkDeleted(String id)
    throws SQLException
  {
    ResultSet rs = null;
    try
    {
      rs = DBUtil.executeQuery("SELECT * FROM material WHERE uni_id = " + id);
      boolean bool;
      if (rs.next()) {
        return true;
      }
      return false;
    }
    catch (SQLException ex)
    {
      throw new SQLException(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
  }
  
  public UnitBean getUnitByName(String name)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from unit where unit_vn='" + name + "'";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        UnitBean unit = new UnitBean();
        unit.setUniId(rs.getInt("uni_id"));
        if (rs.getString("unit_vn") != null) {
          unit.setUnitVn(StringUtil.decodeString(rs.getString("unit_vn")));
        }
        if (rs.getString("unit_en") != null) {
          unit.setUnitEn(StringUtil.decodeString(rs.getString("unit_en")));
        }
        return unit;
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
    return null;
  }
}
