package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.MaterialStoreRequestBean;
import com.venus.mc.bean.MivDetailBean;
import com.venus.mc.bean.RfmDetailBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MaterialStoreRequestDAO
  extends BasicDAO
{
  public MaterialStoreRequestBean getMaterialStoreRequest(int matId, int stoId, int reqDetailId, int msvId)
    throws Exception
  {
    if ((matId == 0) || (stoId == 0)) {
      return null;
    }
    ResultSet rs = null;
    try
    {
      String sql = "select * from material_store_request where mat_id=" + matId + " and sto_id=" + stoId;
      if (reqDetailId > 0) {
        sql = sql + " and req_detail_id=" + reqDetailId;
      }
      if (msvId > 0) {
        sql = sql + " and msv_id=" + msvId;
      }
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        MaterialStoreRequestBean bean = new MaterialStoreRequestBean();
        bean.setMsrId(rs.getInt("msr_id"));
        bean.setStoId(rs.getInt("sto_id"));
        bean.setMatId(rs.getInt("mat_id"));
        bean.setMsvId(rs.getInt("msv_id"));
        bean.setPrice(rs.getDouble("price"));
        bean.setReserveQuantity(rs.getDouble("reserve_quantity"));
        bean.setAvailableQuantity(rs.getDouble("available_quantity"));
        bean.setActualQuantity(rs.getDouble("actual_quantity"));
        bean.setReqDetailId(rs.getInt("req_detail_id"));
        
        return bean;
      }
    }
    catch (SQLException ex)
    {
      Logger.getLogger(MsvDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return null;
  }
  
  public int insertMaterialStoreRequest(int matId, int stoId, double price, double quantity, int reqDetailId, int msvId)
    throws Exception
  {
    if ((stoId == 0) || (matId == 0) || (quantity == 0.0D)) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      sql = "insert into material_store_request(sto_id, mat_id, price, available_quantity, actual_quantity, msv_id, req_detail_id) Values (" + stoId + "," + matId + "," + price + "," + quantity + "," + quantity + "," + msvId + "," + reqDetailId + ")";
      
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
  
  public void updateMaterialStoreRequest(MaterialStoreRequestBean bean)
    throws Exception
  {
    if ((bean == null) || (bean.getMsrId() == 0)) {
      return;
    }
    String sql = "update material_store_request set  available_quantity=" + bean.getAvailableQuantity() + ",actual_quantity=" + bean.getActualQuantity() + " where msr_id=" + bean.getMsrId();
    try
    {
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
  
  public void updateMaterialStoreRequest(MivDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    String sql = "update material_store_request set  reserve_quantity=reserve_quantity-" + bean.getQuantity() + ", actual_quantity=actual_quantity-" + bean.getQuantity() + " where req_detail_id=" + bean.getReqDetailId() + " and sto_id=" + bean.getStoId() + " and msv_id = (SELECT msv_id FROM rfm_detail WHERE det_id = " + bean.getRfmDetId() + ")";
    try
    {
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
  
  public void updateMaterialStoreRequest(MivDetailBean bean, MivDetailBean oldBean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    String sql = "update material_store_request set  reserve_quantity=reserve_quantity+" + oldBean.getQuantity() + "-" + bean.getQuantity() + ", actual_quantity=actual_quantity+" + oldBean.getQuantity() + "-" + bean.getQuantity() + " where req_detail_id=" + bean.getReqDetailId() + " and sto_id=" + bean.getStoId();
    try
    {
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
  
  public int deleteMaterialStoreRequest(int msrId)
    throws Exception
  {
    if (msrId == 0) {
      return 0;
    }
    try
    {
      String sql = "delete from material_store_request where msr_id=" + msrId;
      return DBUtil.executeUpdate(sql);
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
  }
  
  public MaterialStoreRequestBean getMaterialStoreRequest(int reqDetId, int stoId)
    throws Exception
  {
    MaterialStoreRequestBean bean = null;
    String sql = "select * from material_store_request where req_detail_id=" + reqDetId + " and sto_id=" + stoId;
    ResultSet rs = null;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        bean = new MaterialStoreRequestBean();
        bean.setMsrId(rs.getInt("msr_id"));
        bean.setStoId(rs.getInt("sto_id"));
        bean.setMatId(rs.getInt("mat_id"));
        bean.setMsvId(rs.getInt("msv_id"));
        bean.setPrice(rs.getDouble("price"));
        bean.setReserveQuantity(rs.getDouble("reserve_quantity"));
        bean.setAvailableQuantity(rs.getDouble("available_quantity"));
        bean.setActualQuantity(rs.getDouble("actual_quantity"));
        bean.setReqDetailId(rs.getInt("req_detail_id"));
        
        return bean;
      }
    }
    catch (Exception localException1) {}finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return null;
  }
  
  public ArrayList checkMaterialInStore(int reqId, int stoId, int matId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "";
    sql = "SELECT SUM(m.reserve_quantity) AS s1,SUM(m.actual_quantity) AS s2,SUM(m.available_quantity) AS s3, m2.name_vn,m2.code, u.unit_vn, m.mat_id, r.request_quantity,s.name FROM material_store_request AS m LEFT JOIN request_detail AS r ON m.mat_id=r.original_mat_id  LEFT JOIN request AS t  ON t.req_id=r.req_id  LEFT JOIN material AS m2 ON m2.mat_id = r.original_mat_id  LEFT JOIN unit AS u ON u.uni_id=m2.uni_id  LEFT JOIN store AS s ON s.sto_id=m.sto_id  where m.actual_quantity>0 ";
    if (reqId > 0) {
      sql = sql + " and t.req_id=" + reqId;
    }
    if (stoId > 0) {
      sql = sql + " and m.sto_id=" + stoId;
    }
    if (matId > 0) {
      sql = sql + " and r.original_mat_id=" + matId;
    }
    sql = sql + " GROUP BY r.det_id order by r.det_id";
    
    ArrayList detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RfmDetailBean detail = null;
      while (rs.next())
      {
        detail = new RfmDetailBean();
        detail.setMatCode(rs.getString("code"));
        detail.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
        detail.setActualQuantity(rs.getDouble("s2"));
        detail.setAvailableQuantity(rs.getDouble("s3"));
        detail.setReserveQuantity(rs.getDouble("s1"));
        detail.setQuantity(rs.getDouble("request_quantity"));
        detail.setUnit(rs.getString("unit_vn"));
        detail.setMatId(rs.getInt("mat_id"));
        detail.setStoreName(rs.getString("name"));
        detailList.add(detail);
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
    return detailList;
  }
}
