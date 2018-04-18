package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.mc.bean.TenderLetterDetailBean;
import com.venus.mc.database.DBUtil;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TenderLetterDetailDAO
  extends BasicDAO
{
  public ArrayList getTenderLetterDetails()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From tender_letter_detail Order by det_id ASC";
    
    ArrayList tender_letter_detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      TenderLetterDetailBean tender_letter_detail = null;
      while (rs.next())
      {
        tender_letter_detail = new TenderLetterDetailBean();
        tender_letter_detail.setDetId(rs.getInt("det_id"));
        tender_letter_detail.setTenId(rs.getInt("ten_id"));
        tender_letter_detail.setTevId(rs.getInt("tev_id"));
        tender_letter_detail.setSubfix(rs.getString("subfix"));
        tender_letter_detailList.add(tender_letter_detail);
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
    return tender_letter_detailList;
  }
  
  public TenderLetterDetailBean getTenderLetterDetail(int detId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT t.*, t1.*, t2.*, v.NAME AS vendorName, v.ven_id FROM tender_letter_detail t LEFT JOIN tender_letter AS t1 ON t.let_id = t1.let_id LEFT JOIN tender_plan AS t2 ON t1.ten_id = t2.ten_id LEFT JOIN tender_evaluate_vendor AS tv ON tv.tev_id = t.tev_id LEFT JOIN vendor AS v ON v.ven_id = tv.ven_id Where det_id=" + detId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        TenderLetterDetailBean tender_letter_detail = new TenderLetterDetailBean();
        tender_letter_detail.setDetId(rs.getInt("det_id"));
        tender_letter_detail.setLetId(rs.getInt("let_id"));
        tender_letter_detail.setTevId(rs.getInt("tev_id"));
        tender_letter_detail.setDate(DateUtil.formatDate(rs.getDate("t1.created_date"), "dd/MM/yyyy"));
        
        tender_letter_detail.setSubfix(rs.getString("subfix"));
        tender_letter_detail.setTenId(rs.getInt("ten_id"));
        tender_letter_detail.setForm(rs.getInt("form"));
        tender_letter_detail.setVendorName(rs.getString("vendorName"));
        tender_letter_detail.setVenId(rs.getInt("ven_id"));
        
        return tender_letter_detail;
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
  
  public int getKindOfVendor(int detId)
    throws Exception
  {
    ResultSet rs = null;
    int result = 0;
    try
    {
      String sql = "SELECT v.kind FROM tender_letter_detail AS t  LEFT JOIN tender_evaluate_vendor AS t1 ON t.tev_id= t1.tev_id LEFT JOIN vendor AS  v ON v.ven_id = t1.ven_id WHERE t.det_id = " + detId;
      
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        result = rs.getInt("kind");
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
  
  public void insertTenderLetterDetail(TenderLetterDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "";
      sql = "Insert Into tender_letter_detail(let_id, tev_id, subfix) Values (" + bean.getLetId() + "," + bean.getTevId() + ",'" + bean.getSubfix() + "')";
      
      System.out.println("sql ====" + sql);
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
  }
  
  public void updateTenderLetterDetail(TenderLetterDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "Update tender_letter_detail Set  let_id=" + bean.getLetId() + ", tev_id=" + bean.getTevId() + ", subfix='" + bean.getSubfix() + "'" + " Where det_id=" + bean.getDetId();
      
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
  
  public int deleteTenderLetterDetail(int detId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "Delete From tender_letter_detail  Where det_id=" + detId;
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
  
  public ArrayList getTenderLetterDetailsByTenderPlan(int tenId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select ld.* From tender_letter as l left join tender_letter_detail as ld on l.let_id=ld.let_id where l.ten_id=" + tenId;
    
    ArrayList tender_letter_detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      TenderLetterDetailBean tender_letter_detail = null;
      while (rs.next())
      {
        tender_letter_detail = new TenderLetterDetailBean();
        tender_letter_detail.setDetId(rs.getInt("det_id"));
        tender_letter_detail.setLetId(rs.getInt("let_id"));
        tender_letter_detail.setTevId(rs.getInt("tev_id"));
        tender_letter_detail.setSubfix(rs.getString("subfix"));
        tender_letter_detailList.add(tender_letter_detail);
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
    return tender_letter_detailList;
  }
  
  public ArrayList getDetIdByLetId(int letId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select det_id from tender_letter_detail where let_id=" + letId;
    
    ArrayList detId = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        detId.add(Integer.valueOf(rs.getInt("det_id")));
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
    return detId;
  }
}
