package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.TenderLetterBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TenderLetterDAO
  extends BasicDAO
{
  public ArrayList getTenderLetters()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From tender_letter Order by let_id ASC";
    
    ArrayList tender_letterList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      TenderLetterBean tender_letter = null;
      while (rs.next())
      {
        tender_letter = new TenderLetterBean();
        tender_letter.setLetId(rs.getInt("let_id"));
        tender_letter.setTenId(rs.getInt("ten_id"));
        tender_letter.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        tender_letter.setRecievedEmp1(rs.getInt("recieved_emp1"));
        tender_letter.setRecievedEmp2(rs.getInt("recieved_emp2"));
        tender_letterList.add(tender_letter);
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
    return tender_letterList;
  }
  
  public TenderLetterBean getTenderLetter(int tenId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select l.*,p.tender_number From tender_plan as p left join tender_letter as l on l.ten_id=p.ten_id Where p.ten_id=" + tenId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        TenderLetterBean tender_letter = new TenderLetterBean();
        tender_letter.setLetId(rs.getInt("let_id"));
        tender_letter.setTenId(tenId);
        tender_letter.setTenNumber(rs.getString("tender_number"));
        tender_letter.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        tender_letter.setRecievedEmp1(rs.getInt("recieved_emp1"));
        tender_letter.setRecievedEmp2(rs.getInt("recieved_emp2"));
        
        return tender_letter;
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
  
  public int insertTenderLetter(TenderLetterBean bean)
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
      
      sql1 = "SELECT * FROM tender_letter Where ten_id=" + bean.getTenId();
      
      rs = DBUtil.executeQuery(sql1);
      if (!rs.next())
      {
        sql = "Insert Into tender_letter(ten_id, created_date, recieved_emp1, recieved_emp2) Values (" + bean.getTenId() + ",now()" + "," + bean.getRecievedEmp1() + "," + bean.getRecievedEmp2() + ")";
        
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
  
  public void updateTenderLetter(TenderLetterBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "Update tender_letter Set  ten_id=" + bean.getTenId() + ", created_date=STR_TO_DATE('" + bean.getCreatedDate() + "','%d/%m/%Y')" + ", recieved_emp1='" + bean.getRecievedEmp1() + "'" + ", recieved_emp2='" + bean.getRecievedEmp2() + "'" + " Where let_id=" + bean.getLetId();
      
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
  
  public int deleteTenderLetter(int letId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "Delete From tender_letter  Where let_id=" + letId;
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
  
  public ArrayList searchSimpleTenderLetter(int fieldid, String strFieldvalue)
    throws Exception
  {
    String strFieldname = "";
    switch (fieldid)
    {
    case 1: 
      strFieldname = "ten_id";
    }
    ResultSet rs = null;
    
    String sql = "Select * From tender_letter Where 1 ";
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      sql = sql + "AND " + strFieldname + " = " + strFieldvalue;
    }
    sql = sql + " Order by let_id DESC";
    
    ArrayList tender_letterList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      TenderLetterBean tender_letter = null;
      while (rs.next())
      {
        tender_letter = new TenderLetterBean();
        tender_letter.setLetId(rs.getInt("let_id"));
        tender_letter.setTenId(rs.getInt("ten_id"));
        tender_letter.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        tender_letter.setRecievedEmp1(rs.getInt("recieved_emp1"));
        tender_letter.setRecievedEmp2(rs.getInt("recieved_emp2"));
        tender_letterList.add(tender_letter);
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
    return tender_letterList;
  }
  
  public ArrayList searchAdvTenderLetter(TenderLetterBean bean)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From tender_letter Where 1 ";
    if (bean.getLetId() != 0) {
      sql = sql + " AND let_id =" + bean.getLetId();
    }
    if (bean.getTenId() != 0) {
      sql = sql + " AND ten_id =" + bean.getTenId();
    }
    if (bean.getCreatedDate() != null) {
      sql = sql + " AND created_date = '" + bean.getCreatedDate() + "'";
    }
    if (bean.getRecievedEmp1() != 0) {
      sql = sql + " AND recieved_emp1 =" + bean.getRecievedEmp1();
    }
    if (bean.getRecievedEmp2() != 0) {
      sql = sql + " AND recieved_emp2 =" + bean.getRecievedEmp2();
    }
    sql = sql + " Order by let_id DESC";
    
    ArrayList tender_letterList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      TenderLetterBean tender_letter = null;
      while (rs.next())
      {
        tender_letter = new TenderLetterBean();
        tender_letter.setLetId(rs.getInt("let_id"));
        tender_letter.setTenId(rs.getInt("ten_id"));
        tender_letter.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        tender_letter.setRecievedEmp1(rs.getInt("recieved_emp1"));
        tender_letter.setRecievedEmp2(rs.getInt("recieved_emp2"));
        tender_letterList.add(tender_letter);
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
    return tender_letterList;
  }
}
