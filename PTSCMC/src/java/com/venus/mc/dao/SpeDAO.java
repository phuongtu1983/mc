package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.SpeBean;
import com.venus.mc.database.DBUtil;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SpeDAO
  extends BasicDAO
{
  public ArrayList getSpes()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from specification order by sign asc";
    
    ArrayList speList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      SpeBean spe = null;
      while (rs.next())
      {
        spe = new SpeBean();
        spe.setSpe1Id(rs.getInt("spe_id"));
        spe.setSign(rs.getString("sign"));
        spe.setNote(StringUtil.decodeString(rs.getString("note")));
        
        speList.add(spe);
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
    return speList;
  }
  
  public ArrayList getSpe1s()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from specification1 order by sign DESC";
    
    ArrayList spe1List = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      SpeBean spe1 = null;
      while (rs.next())
      {
        spe1 = new SpeBean();
        spe1.setSpe1Id(rs.getInt("spe1_id"));
        spe1.setSign(rs.getString("sign"));
        spe1.setNote(StringUtil.decodeString(rs.getString("note")));
        
        spe1List.add(spe1);
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
    return spe1List;
  }
  
  public ArrayList getSpe2s(String spe1Id)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from specification2 where spe1_id =  '" + spe1Id + "'  order by sign DESC";
    
    ArrayList spe2List = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      SpeBean spe2 = null;
      while (rs.next())
      {
        spe2 = new SpeBean();
        spe2.setSpe2Id(rs.getInt("spe2_id"));
        spe2.setSign(rs.getString("sign"));
        spe2.setNote(StringUtil.decodeString(rs.getString("note")));
        
        spe2List.add(spe2);
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
    return spe2List;
  }
  
  public ArrayList getSpe3s(String spe2Id)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from specification3 where spe2_id =  '" + spe2Id + "'  order by sign DESC";
    
    ArrayList spe3List = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      SpeBean spe3 = null;
      while (rs.next())
      {
        spe3 = new SpeBean();
        spe3.setSpe3Id(rs.getInt("spe3_id"));
        spe3.setSign(rs.getString("sign"));
        spe3.setNote(StringUtil.decodeString(rs.getString("note")));
        
        spe3List.add(spe3);
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
    return spe3List;
  }
  
  public ArrayList getSpe4s(String spe3Id)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from specification4 where spe3_id =  '" + spe3Id + "'  order by sign DESC";
    
    ArrayList spe4List = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      SpeBean spe4 = null;
      while (rs.next())
      {
        spe4 = new SpeBean();
        spe4.setSpe4Id(rs.getInt("spe4_id"));
        spe4.setSign(rs.getString("sign"));
        spe4.setNote(StringUtil.decodeString(rs.getString("note")));
        
        spe4List.add(spe4);
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
    return spe4List;
  }
  
  public ArrayList getSpe5s(String spe4Id)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from specification5 where spe4_id =  '" + spe4Id + "'  order by sign DESC";
    
    ArrayList spe5List = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      SpeBean spe5 = null;
      while (rs.next())
      {
        spe5 = new SpeBean();
        spe5.setSpe5Id(rs.getInt("spe5_id"));
        spe5.setSign(rs.getString("sign"));
        spe5.setNote(StringUtil.decodeString(rs.getString("note")));
        
        spe5List.add(spe5);
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
    return spe5List;
  }
  
  public ArrayList getSpe6s(String spe5Id)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from specification6 where spe5_id =  '" + spe5Id + "'  order by sign DESC";
    
    ArrayList spe6List = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      SpeBean spe6 = null;
      while (rs.next())
      {
        spe6 = new SpeBean();
        spe6.setSpe6Id(rs.getInt("spe6_id"));
        spe6.setSign(rs.getString("sign"));
        spe6.setNote(StringUtil.decodeString(rs.getString("note")));
        
        spe6List.add(spe6);
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
    return spe6List;
  }
  
  public String getSpeId1()
    throws Exception
  {
    String sign = "";
    ResultSet rs = null;
    
    String sql = "SELECT MAX(CAST(SIGN AS SIGNED INTEGER))+1 AS SIGN FROM specification1";
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        sign = rs.getString("sign");
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
    return sign;
  }
  
  public String getSpeId2(int speId)
    throws Exception
  {
    String sign = "";
    ResultSet rs = null;
    
    String sql = "SELECT MAX(CAST(SIGN AS SIGNED INTEGER))+1 AS SIGN FROM specification2 WHERE spe1_id = " + speId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        sign = rs.getString("sign");
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
    return sign;
  }
  
  public String getSpeId3(int speId)
    throws Exception
  {
    String sign = "";
    ResultSet rs = null;
    
    String sql = "SELECT MAX(CAST(SIGN AS SIGNED INTEGER))+1 AS SIGN FROM specification3 WHERE spe2_id = " + speId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        sign = rs.getString("sign");
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
    return sign;
  }
  
  public String getSpeId4(int speId)
    throws Exception
  {
    String sign = "";
    ResultSet rs = null;
    
    String sql = "SELECT MAX(CAST(SIGN AS SIGNED INTEGER))+1 AS SIGN FROM specification4 WHERE spe3_id = " + speId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        sign = rs.getString("sign");
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
    return sign;
  }
  
  public String getSpeId5(int speId)
    throws Exception
  {
    String sign = "";
    ResultSet rs = null;
    
    String sql = "SELECT MAX(CAST(SIGN AS SIGNED INTEGER))+1 AS SIGN FROM specification5 WHERE spe4_id = " + speId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        sign = rs.getString("sign");
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
    return sign;
  }
  
  public String getSpeId6(int speId)
    throws Exception
  {
    String sign = "";
    ResultSet rs = null;
    
    String sql = "SELECT MAX(CAST(SIGN AS SIGNED INTEGER))+1 AS SIGN FROM specification6 WHERE spe5_id = " + speId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        sign = rs.getString("sign");
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
    return sign;
  }
  
  public SpeBean getSpe(String speid)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from specification where spe_id=" + speid + " order by sign DESC";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        SpeBean spe = new SpeBean();
        spe.setSpe1Id(rs.getInt("spe_id"));
        spe.setSign(rs.getString("sign"));
        spe.setNote(StringUtil.decodeString(rs.getString("note")));
        
        return spe;
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
  
  public ArrayList getSpe2as(String speId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT s2.* FROM specification2 as s2 LEFT JOIN specification1 as s1 ON s1.spe1_id = s2.spe1_id WHERE s1.spe1_id = '" + speId + "'  order by s2.spe2_id desc";
    
    ArrayList spe2List = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      SpeBean spe2 = null;
      while (rs.next())
      {
        spe2 = new SpeBean();
        spe2.setSpe2Id(rs.getInt("spe2_id"));
        spe2.setSign(rs.getString("sign"));
        spe2.setNote(StringUtil.decodeString(rs.getString("note")));
        
        spe2List.add(spe2);
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
    return spe2List;
  }
  
  public ArrayList getSpe3as(String speId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT s3.* FROM specification3 as s3 LEFT JOIN specification2 as s2 ON s2.spe2_id = s3.spe2_id WHERE s2.spe2_id = '" + speId + "'  order by s3.spe3_id desc";
    
    ArrayList spe3List = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      SpeBean spe3 = null;
      while (rs.next())
      {
        spe3 = new SpeBean();
        spe3.setSpe3Id(rs.getInt("spe3_id"));
        spe3.setSign(rs.getString("sign"));
        spe3.setNote(StringUtil.decodeString(rs.getString("note")));
        
        spe3List.add(spe3);
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
    return spe3List;
  }
  
  public ArrayList getSpe4as(String speId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT s4.* FROM specification4 as s4 LEFT JOIN specification3 as s3 ON s3.spe3_id = s4.spe3_id WHERE s3.spe3_id = '" + speId + "'  order by s4.spe4_id desc";
    
    ArrayList spe4List = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      SpeBean spe4 = null;
      while (rs.next())
      {
        spe4 = new SpeBean();
        spe4.setSpe4Id(rs.getInt("spe4_id"));
        spe4.setSign(rs.getString("sign"));
        spe4.setNote(StringUtil.decodeString(rs.getString("note")));
        
        spe4List.add(spe4);
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
    return spe4List;
  }
  
  public ArrayList getSpe5as(String speId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT s5.* FROM specification5 as s5 LEFT JOIN specification4 as s4 ON s4.spe4_id = s5.spe4_id WHERE s4.spe4_id = '" + speId + "'  order by s5.spe5_id desc";
    
    ArrayList spe5List = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      SpeBean spe5 = null;
      while (rs.next())
      {
        spe5 = new SpeBean();
        spe5.setSpe5Id(rs.getInt("spe5_id"));
        spe5.setSign(rs.getString("sign"));
        spe5.setNote(StringUtil.decodeString(rs.getString("note")));
        
        spe5List.add(spe5);
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
    return spe5List;
  }
  
  public ArrayList getSpe6as(String speId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT s6.* FROM specification6 as s6 LEFT JOIN specification5 as s5 ON s5.spe5_id = s6.spe5_id WHERE s5.spe5_id = '" + speId + "'  order by s6.spe6_id desc";
    
    ArrayList spe6List = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      SpeBean spe6 = null;
      while (rs.next())
      {
        spe6 = new SpeBean();
        spe6.setSpe6Id(rs.getInt("spe6_id"));
        spe6.setSign(rs.getString("sign"));
        spe6.setNote(StringUtil.decodeString(rs.getString("note")));
        
        spe6List.add(spe6);
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
    return spe6List;
  }
  
  public int insertSpe(SpeBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      if (bean.getSpe().equals("1")) {
        sql = "insert into specification1(sign,note) values ('" + bean.getSign() + "','" + StringUtil.encodeHTML(bean.getNote()) + "')";
      }
      if (bean.getSpe().equals("2")) {
        sql = "insert into specification2(sign,note,spe1_id) values ('" + bean.getSign() + "','" + StringUtil.encodeHTML(bean.getNote()) + "','" + bean.getSpe1Id() + "')";
      }
      if (bean.getSpe().equals("3")) {
        sql = "insert into specification3(sign,note,spe2_id) values ('" + bean.getSign() + "','" + StringUtil.encodeHTML(bean.getNote()) + "','" + bean.getSpe2Id() + "')";
      }
      if (bean.getSpe().equals("4")) {
        sql = "insert into specification4(sign,note,spe3_id) values ('" + bean.getSign() + "','" + StringUtil.encodeHTML(bean.getNote()) + "','" + bean.getSpe3Id() + "')";
      }
      if (bean.getSpe().equals("5")) {
        sql = "insert into specification5(sign,note,spe4_id) values ('" + bean.getSign() + "','" + StringUtil.encodeHTML(bean.getNote()) + "','" + bean.getSpe4Id() + "')";
      }
      if (bean.getSpe().equals("6")) {
        sql = "insert into specification6(sign,note,spe5_id) values ('" + bean.getSign() + "','" + StringUtil.encodeHTML(bean.getNote()) + "','" + bean.getSpe5Id() + "')";
      }
      System.out.println("sql ====" + sql);
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
  
  public void updateSpe(String spe, SpeBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "update specification" + spe + " set " + " sign='" + bean.getSign() + "'" + ", note='" + StringUtil.encodeHTML(bean.getNote()) + "'" + " where spe" + spe + "_id=" + bean.getSpeId();
      
      System.out.println("sql=" + sql);
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
  
  public boolean checkDeletedSpe(int id, int speId)
    throws SQLException
  {
    ResultSet rs = null;
    try
    {
      String sql = "";
      sql = "SELECT * FROM specification" + (id + 1) + " WHERE spe" + id + "_id =" + speId;
      
      rs = DBUtil.executeQuery(sql);
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
  
  public int deleteSpe(String spe, String spes)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "";
      if (spe.equals("1")) {
        sql = "delete from specification1  where spe1_id=" + spes;
      }
      if (spe.equals("2")) {
        sql = "delete from specification2  where spe2_id=" + spes;
      }
      if (spe.equals("3")) {
        sql = "delete from specification3  where spe3_id=" + spes;
      }
      if (spe.equals("4")) {
        sql = "delete from specification4  where spe4_id=" + spes;
      }
      if (spe.equals("5")) {
        sql = "delete from specification5  where spe5_id=" + spes;
      }
      if (spe.equals("6")) {
        sql = "delete from specification6  where spe6_id=" + spes;
      }
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
  
  public ArrayList searchSimpleSpe(int fieldid, String strFieldvalue)
    throws Exception
  {
    String strFieldname = "";
    switch (fieldid)
    {
    case 1: 
      strFieldname = "sign";
      break;
    case 2: 
      strFieldname = "note";
    }
    ResultSet rs = null;
    
    String sql = "select spe_id, sign, note from specification where 1 ";
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(StringUtil.encodeHTML(strFieldvalue)))) {
      sql = sql + "AND " + strFieldname + " LIKE '%" + StringUtil.encodeHTML(strFieldvalue) + "%'";
    }
    sql = sql + " order by sign desc";
    
    ArrayList speList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      SpeBean spe = null;
      while (rs.next())
      {
        spe = new SpeBean();
        spe.setSpe1Id(rs.getInt("spe_id"));
        spe.setSign(rs.getString("sign"));
        spe.setNote(StringUtil.decodeString(rs.getString("note")));
        speList.add(spe);
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
    return speList;
  }
  
  public ArrayList searchAdvSpe(SpeBean bean)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select spe_id, sign, note from specification where 1 ";
    if (!StringUtil.isBlankOrNull(bean.getSign())) {
      sql = sql + " AND sign LIKE '%" + bean.getSign() + "%'";
    }
    if (!StringUtil.isBlankOrNull(bean.getNote())) {
      sql = sql + " AND note LIKE '%" + StringUtil.encodeHTML(bean.getNote()) + "%'";
    }
    sql = sql + " order by sign desc";
    
    ArrayList speList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      SpeBean spe = null;
      while (rs.next())
      {
        spe = new SpeBean();
        spe.setSpe1Id(rs.getInt("spe_id"));
        spe.setSign(rs.getString("sign"));
        spe.setNote(StringUtil.decodeString(rs.getString("note")));
        speList.add(spe);
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
    return speList;
  }
  
  public SpeBean getSpe1BySign(String sign)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from specification1 where sign='" + sign + "'";
    
    SpeBean spe1 = null;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        spe1 = new SpeBean();
        spe1.setSpe1Id(rs.getInt("spe1_id"));
        spe1.setSign(rs.getString("sign"));
        spe1.setNote(StringUtil.decodeString(rs.getString("note")));
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
    return spe1;
  }
  
  public SpeBean getSpe2BySign(String sign, int spe1)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from specification2 where sign =  '" + sign + "' and spe1_id=" + spe1;
    
    SpeBean spe2 = null;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        spe2 = new SpeBean();
        spe2.setSpe2Id(rs.getInt("spe2_id"));
        spe2.setSign(rs.getString("sign"));
        spe2.setSpe1Id(rs.getInt("spe1_id"));
        spe2.setNote(StringUtil.decodeString(rs.getString("note")));
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
    return spe2;
  }
  
  public SpeBean getSpe3BySign(String sign, int spe2)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from specification3 where sign =  '" + sign + "' and spe2_id=" + spe2;
    
    SpeBean spe3 = null;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        spe3 = new SpeBean();
        spe3.setSpe3Id(rs.getInt("spe3_id"));
        spe3.setSign(rs.getString("sign"));
        spe3.setSpe2Id(rs.getInt("spe2_id"));
        spe3.setNote(StringUtil.decodeString(rs.getString("note")));
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
    return spe3;
  }
  
  public SpeBean getSpe4BySign(String sign, int spe3)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from specification4 where sign =  '" + sign + "' and spe3_id=" + spe3;
    
    SpeBean spe4 = null;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        spe4 = new SpeBean();
        spe4.setSpe4Id(rs.getInt("spe4_id"));
        spe4.setSign(rs.getString("sign"));
        spe4.setSpe3Id(rs.getInt("spe3_id"));
        spe4.setNote(StringUtil.decodeString(rs.getString("note")));
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
    return spe4;
  }
  
  public SpeBean getSpe5BySign(String sign, int spe4)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from specification5 where sign =  '" + sign + "' and spe4_id=" + spe4;
    
    SpeBean spe5 = null;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        spe5 = new SpeBean();
        spe5.setSpe5Id(rs.getInt("spe5_id"));
        spe5.setSign(rs.getString("sign"));
        spe5.setSpe4Id(rs.getInt("spe4_id"));
        spe5.setNote(StringUtil.decodeString(rs.getString("note")));
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
    return spe5;
  }
}
