package com.venus.mc.dao;

import com.venus.mc.bean.TenderPlanRequestBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TenderPlanRequestDAO
  extends BasicDAO
{
  public ArrayList getTenderPlanRequests()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From tender_plan_request Order by tpr_id ASC";
    
    ArrayList tender_plan_requestList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      TenderPlanRequestBean tender_plan_request = null;
      while (rs.next())
      {
        tender_plan_request = new TenderPlanRequestBean();
        tender_plan_request.setTprId(rs.getInt("tpr_id"));
        tender_plan_request.setTenId(rs.getInt("ten_id"));
        tender_plan_request.setReqId(rs.getInt("req_id"));
        tender_plan_requestList.add(tender_plan_request);
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
    return tender_plan_requestList;
  }
  
  public TenderPlanRequestBean getTenderPlanRequest(int tprId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From tender_plan_request Where tpr_id=" + tprId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        TenderPlanRequestBean tender_plan_request = new TenderPlanRequestBean();
        tender_plan_request.setTprId(rs.getInt("tpr_id"));
        tender_plan_request.setTenId(rs.getInt("ten_id"));
        tender_plan_request.setReqId(rs.getInt("req_id"));
        
        return tender_plan_request;
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
  
  public void insertTenderPlanRequest(TenderPlanRequestBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "";
      sql = "Insert Into tender_plan_request(ten_id, req_it) Values (" + bean.getTenId() + "," + bean.getReqId() + ")";
      
      DBUtil.executeInsert(sql);
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
  
  public void updateTenderPlanRequest(TenderPlanRequestBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "Update tender_plan_request Set  ten_id=" + bean.getTenId() + ", req_id=" + bean.getReqId() + " Where tpr_id=" + bean.getTprId();
      
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
  
  public int deleteTenderPlanRequest(int tprId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "Delete From tender_plan_request  Where tpr_id=" + tprId;
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
    return result;
  }
}
