package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.DeliveryNoticeBean;
import com.venus.mc.bean.DeliveryNoticeDetailBean;
import com.venus.mc.bean.EdeliveryNoticeBean;
import com.venus.mc.bean.EdeliveryNoticeDetailBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;

public class DeliveryNoticeDAO
  extends BasicDAO
{
  public ArrayList getDeliveryNoticesForMrir()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select delivery_notice.* From delivery_notice";
    ArrayList dnList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      DeliveryNoticeBean dn = null;
      while (rs.next())
      {
        dn = new DeliveryNoticeBean();
        dn.setDnId(rs.getInt("dn_id"));
        dn.setConId(rs.getInt("con_id"));
        dn.setDnNumber(rs.getString("dn_number"));
        dnList.add(dn);
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
    return dnList;
  }
  
  public ArrayList getDeliveryNoticesForMrir(int status)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select delivery_notice.* From delivery_notice Where status = " + status;
    
    ArrayList dnList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      DeliveryNoticeBean dn = null;
      while (rs.next())
      {
        dn = new DeliveryNoticeBean();
        dn.setDnId(rs.getInt("dn_id"));
        dn.setConId(rs.getInt("con_id"));
        dn.setDnNumber(rs.getString("dn_number"));
        dnList.add(dn);
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
    return dnList;
  }
  
  public ArrayList getDeliveryNoticeDetails(int dnId, int reqId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select delivery_notice_detail.*, name_vn, unit_vn From (((request left join request_detail on request.req_id = request_detail.req_id) left join delivery_notice_detail on request_detail.det_id = delivery_notice_detail.req_detail_id) inner join material on delivery_notice_detail.mat_id = material.mat_id) inner join unit on material.uni_id = unit.uni_id Where request.req_id = " + reqId + " And delivery_notice_detail.dn_id =" + dnId;
    
    ArrayList dnList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      DeliveryNoticeDetailBean dn = null;
      while (rs.next())
      {
        dn = new DeliveryNoticeDetailBean();
        dn.setDetId(rs.getInt("det_id"));
        dn.setDnId(rs.getInt("dn_id"));
        dn.setMatId(rs.getInt("mat_id"));
        dn.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
        dn.setUnit(rs.getString("unit_vn"));
        dn.setPrice(rs.getDouble("price"));
        dn.setQuantity(rs.getDouble("quantity"));
        dn.setReqDetailId(rs.getInt("req_detail_id"));
        dnList.add(dn);
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
    return dnList;
  }
  
  public DeliveryNoticeDetailBean getDeliveryNoticeDetail(int reqId, int matId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select delivery_notice_detail.*, name_vn, unit_vn From (((request left join request_detail on request.req_id = request_detail.req_id) left join delivery_notice_detail on request_detail.det_id = delivery_notice_detail.req_detail_id) inner join material on delivery_notice_detail.mat_id = material.mat_id) inner join unit on material.uni_id = unit.uni_id Where request.req_id = " + reqId + " and delivery_notice_detail.mat_id = " + matId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      DeliveryNoticeDetailBean dn = null;
      if (rs.next())
      {
        dn = new DeliveryNoticeDetailBean();
        dn.setDetId(rs.getInt("det_id"));
        dn.setDnId(rs.getInt("dn_id"));
        dn.setMatId(rs.getInt("mat_id"));
        dn.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
        dn.setUnit(rs.getString("unit_vn"));
        dn.setPrice(rs.getDouble("price"));
        dn.setQuantity(rs.getDouble("quantity"));
        dn.setReqDetailId(rs.getInt("req_detail_id"));
        
        return dn;
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
  
  public DeliveryNoticeBean getDeliveryNotice(int dnId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select delivery_notice.* From delivery_notice Where dn_id=" + dnId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        DeliveryNoticeBean dn = new DeliveryNoticeBean();
        dn = new DeliveryNoticeBean();
        dn.setDnId(rs.getInt("dn_id"));
        dn.setConId(rs.getInt("con_id"));
        dn.setDnNumber(rs.getString("dn_number"));
        
        return dn;
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
  
  public ArrayList getDeliveryNoticeList()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select dn_id,dn_number From delivery_notice";
    ArrayList dnList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      LabelValueBean dn = null;
      while (rs.next())
      {
        dn = new LabelValueBean();
        dn.setValue(rs.getString("dn_id"));
        dn.setLabel(rs.getString("dn_number"));
        dnList.add(dn);
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
    return dnList;
  }
  
  public ArrayList getEdeliveryNoticesForEmrir()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select edelivery_notice.* From edelivery_notice";
    
    ArrayList dnList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      EdeliveryNoticeBean dn = null;
      while (rs.next())
      {
        dn = new EdeliveryNoticeBean();
        dn.setEdnId(rs.getInt("edn_id"));
        dn.setEdnNumber(rs.getString("edn_number"));
        dnList.add(dn);
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
    return dnList;
  }
  
  public ArrayList getEdeliveryNoticesForEmrir(int status)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select edelivery_notice.* From edelivery_notice Where status = " + status;
    
    ArrayList dnList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      EdeliveryNoticeBean dn = null;
      while (rs.next())
      {
        dn = new EdeliveryNoticeBean();
        dn.setEdnId(rs.getInt("edn_id"));
        dn.setEdnNumber(rs.getString("edn_number"));
        dnList.add(dn);
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
    return dnList;
  }
  
  public ArrayList getEdeliveryNoticeDetails(int ednId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select edelivery_notice_detail.*, name_vn, unit_vn From (edelivery_notice_detail inner join ematerial on edelivery_notice_detail.emat_id = ematerial.emat_id) left join unit on ematerial.uni_id = unit.uni_id Where edelivery_notice_detail.edn_id = " + ednId;
    
    ArrayList dnList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      EdeliveryNoticeDetailBean dn = null;
      while (rs.next())
      {
        dn = new EdeliveryNoticeDetailBean();
        dn.setDetId(rs.getInt("det_id"));
        dn.setEdnId(rs.getInt("edn_id"));
        dn.setEmatId(rs.getInt("emat_id"));
        dn.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
        dn.setUnit(rs.getString("unit_vn"));
        dn.setPrice(rs.getDouble("price"));
        dn.setQuantity(rs.getDouble("quantity"));
        dnList.add(dn);
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
    return dnList;
  }
  
  public EdeliveryNoticeDetailBean getEdeliveryNoticeDetail(int ematId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select edelivery_notice_detail.*, name_vn, unit_vn From (edelivery_notice_detail inner join ematerial on edelivery_notice_detail.emat_id = ematerial.emat_id) left join unit on ematerial.uni_id = unit.uni_id Where edelivery_notice_detail.emat_id = " + ematId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      EdeliveryNoticeDetailBean dn = null;
      if (rs.next())
      {
        dn = new EdeliveryNoticeDetailBean();
        dn.setDetId(rs.getInt("det_id"));
        dn.setEdnId(rs.getInt("edn_id"));
        dn.setEmatId(rs.getInt("emat_id"));
        dn.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
        dn.setUnit(rs.getString("unit_vn"));
        dn.setPrice(rs.getDouble("price"));
        dn.setQuantity(rs.getDouble("quantity"));
        
        return dn;
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
  
  public EdeliveryNoticeBean getEdeliveryNotice(int ednId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select edelivery_notice.* From edelivery_notice Where edn_id=" + ednId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        EdeliveryNoticeBean dn = new EdeliveryNoticeBean();
        dn.setEdnId(rs.getInt("edn_id"));
        dn.setEdnNumber(rs.getString("edn_number"));
        dn.setEconNumber(rs.getString("econ_number"));
        dn.setProId(rs.getInt("pro_id"));
        return dn;
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
  
  public ArrayList getEdeliveryNoticeList()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select edn_id, edn_number From edelivery_notice";
    
    ArrayList dnList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      LabelValueBean dn = null;
      while (rs.next())
      {
        dn = new LabelValueBean();
        dn.setValue(rs.getString("edn_id"));
        dn.setLabel(rs.getString("edn_number"));
        dnList.add(dn);
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
    return dnList;
  }
}
