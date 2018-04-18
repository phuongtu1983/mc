package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.PriceComparisonBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PriceComparisonDAO
  extends BasicDAO
{
  public ArrayList getPriceComparisons()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From price_comparison Order by pc_id ASC";
    
    ArrayList price_comparisonList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      PriceComparisonBean price_comparison = null;
      while (rs.next())
      {
        price_comparison = new PriceComparisonBean();
        price_comparison.setPcId(rs.getInt("pc_id"));
        price_comparison.setTenId(rs.getInt("ten_id"));
        price_comparison.setSubject(rs.getString("subject"));
        price_comparison.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        price_comparison.setCreatedEmp(rs.getInt("created_emp"));
        price_comparisonList.add(price_comparison);
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
    return price_comparisonList;
  }
  
  public PriceComparisonBean getPriceComparison(int tenId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT * FROM price_comparison WHERE ten_id=" + tenId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        PriceComparisonBean price_comparison = new PriceComparisonBean();
        price_comparison.setPcId(rs.getInt("pc_id"));
        price_comparison.setTenId(tenId);
        price_comparison.setSubject(rs.getString("subject"));
        price_comparison.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        price_comparison.setCreatedEmp(rs.getInt("created_emp"));
        
        return price_comparison;
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
  
  public int insertPriceComparison(PriceComparisonBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    ResultSet rs = null;
    try
    {
      String sql = "";
      String sql1 = "";
      
      sql1 = "SELECT * FROM price_comparison Where ten_id=" + bean.getTenId();
      
      rs = DBUtil.executeQuery(sql1);
      if (!rs.next())
      {
        sql = "Insert Into price_comparison(ten_id, created_date, created_emp, subject) Values (" + bean.getTenId() + ",now()" + "," + bean.getCreatedEmp() + ",'" + bean.getSubject() + "')";
        
        return DBUtil.executeInsert(sql);
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
    return 0;
  }
  
  public void updatePriceComparison(PriceComparisonBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "Update price_comparison Set  ten_id=" + bean.getTenId() + ", created_date=STR_TO_DATE('" + bean.getCreatedDate() + "','%d/%m/%Y')" + ", created_emp=" + bean.getCreatedEmp() + "" + ", subject='" + bean.getSubject() + "'" + " Where pc_id=" + bean.getPcId();
      
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
  
  public int deletePriceComparison(int pcId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "Delete From price_comparison  Where pc_id=" + pcId;
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
  
  public ArrayList searchSimplePriceComparison(int fieldid, String strFieldvalue)
    throws Exception
  {
    String strFieldname = "";
    switch (fieldid)
    {
    case 1: 
      strFieldname = "ten_id";
    }
    ResultSet rs = null;
    
    String sql = "Select * From price_comparison Where 1 ";
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      sql = sql + "AND " + strFieldname + " = " + strFieldvalue;
    }
    sql = sql + " Order by pc_id DESC";
    
    ArrayList price_comparisonList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      PriceComparisonBean price_comparison = null;
      while (rs.next())
      {
        price_comparison = new PriceComparisonBean();
        price_comparison.setPcId(rs.getInt("pc_id"));
        price_comparison.setTenId(rs.getInt("ten_id"));
        price_comparison.setSubject(rs.getString("subject"));
        price_comparison.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        price_comparison.setCreatedEmp(rs.getInt("created_emp"));
        price_comparisonList.add(price_comparison);
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
    return price_comparisonList;
  }
}
