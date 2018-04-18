package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.MaterialBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.bean.RfmBean;
import com.venus.mc.bean.RfmDetailBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.process.store.rfm.RfmFormBean;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.SQLSearchExpressionUtil;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RfmDAO
  extends BasicDAO
{
  public ArrayList getRfms(int kind)
    throws Exception
  {
    ResultSet rs = null;
    
    String table = "";
    if (kind == 0) {
      table = "";
    } else {
      table = "e";
    }
    String sql = "SELECT r.*,s.name AS stoName, o.kind as kindOrg FROM " + table + "rfm AS r LEFT JOIN store AS s ON s.sto_id = r.sto_id left join organization as o on o.org_id=r.org_id order by " + table + "rfm_id desc";
    
    ArrayList rfmList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RfmFormBean rfm = null;
      while (rs.next())
      {
        rfm = new RfmFormBean();
        rfm.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        rfm.setCreator(rs.getInt("created_emp"));
        rfm.setDeliveryDate(DateUtil.formatDate(rs.getDate("delivery_date"), "dd/MM/yyyy"));
        rfm.setDeliveryPlace(rs.getString("delivery_place"));
        rfm.setKind(kind);
        rfm.setOrgId(rs.getInt("org_id"));
        if (rs.getInt("kindOrg") == OrganizationBean.KIND_PROJECT) {
          rfm.setForName(MCUtil.getBundleString("message.store.proId"));
        } else {
          rfm.setForName(MCUtil.getBundleString("message.rfm.orgId"));
        }
        rfm.setReqType(rs.getInt("req_type"));
        if (rs.getInt("req_type") == 1) {
          rfm.setReqName(MCUtil.getBundleString("message.rfm.store.project"));
        }
        if (rs.getInt("req_type") == 2) {
          rfm.setReqName(MCUtil.getBundleString("message.rfm.store.cty"));
        }
        if (rs.getInt("req_type") == 3) {
          rfm.setReqName(MCUtil.getBundleString("message.miv.type.other"));
        }
        rfm.setRfmId(rs.getInt(table + "rfm_id"));
        rfm.setRfmNumber(rs.getString(table + "rfm_number"));
        rfm.setStoId(rs.getInt("sto_id"));
        rfm.setStoName(rs.getString("stoName"));
        rfm.setRequestOrg(rs.getInt("request_org"));
        
        rfmList.add(rfm);
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
    return rfmList;
  }
  
  public ArrayList getRequestMsv(int stoId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT r1.request_number,r1.req_id FROM msv_detail AS m LEFT JOIN msv AS m1 ON m.msv_id = m1.msv_id LEFT JOIN request_detail AS r ON m.req_detail_id = r.det_id LEFT JOIN request AS r1 ON r.req_id = r1.req_id WHERE m1.sto_id = " + stoId;
    
    ArrayList rfmList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RequestBean rfm = null;
      while (rs.next())
      {
        rfm = new RequestBean();
        rfm.setReqId(rs.getInt("req_id"));
        rfm.setRequestNumber(rs.getString("request_number"));
        
        rfmList.add(rfm);
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
    return rfmList;
  }
  
  public ArrayList getMaterialMsv(int ids)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT m.mat_id, m2.name_vn, u.*, m.price,m.req_detail_id  FROM material_store_request AS m LEFT JOIN material AS m2 ON m2.mat_id = m.mat_id LEFT JOIN unit AS u ON u.uni_id=m2.uni_id LEFT JOIN store AS s ON s.sto_id=m.sto_id WHERE s.kind=1 AND m.available_quantity>0 AND m.mat_id in(" + ids + ")";
    
    ArrayList rfmList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      MaterialBean rfm = null;
      while (rs.next())
      {
        rfm = new MaterialBean();
        rfm.setMatId(rs.getInt("mat_id"));
        rfm.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
        
        rfmList.add(rfm);
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
    return rfmList;
  }
  
  public ArrayList getMaterialsOfMsv(String ids, int kind, int stoId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "";
    String store = "1";
    if (stoId > 0) {
      store = " m.sto_id = " + stoId;
    }
    if (kind == 0) {
      sql = "SELECT m.*, m2.name_vn, m2.code, u.*,v.msv_id, v.msv_number, t.request_number,s.name  FROM material_store_request AS m LEFT JOIN material AS m2 ON m2.mat_id = m.mat_id LEFT JOIN unit AS u ON u.uni_id=m2.uni_id LEFT JOIN request_detail AS r ON r.det_id=m.req_detail_id LEFT JOIN request AS t ON t.req_id=r.req_id LEFT JOIN store AS s ON s.sto_id=m.sto_id LEFT JOIN msv AS v ON  m.msv_id = v.msv_id where s.kind=1 AND m.actual_quantity>0 AND " + store + " AND m.req_detail_id IN (SELECT req_detail_id FROM material_store_request AS m WHERE m.msr_id in(" + ids + "))";
    } else {
      sql = "SELECT DISTINCT m.*, m2.name_vn, m2.code, u.*, v.emsv_id,v.emsv_number, s.name, s.sto_id FROM ematerial_store AS m LEFT JOIN ematerial AS m2 ON m2.emat_id = m.emat_id LEFT JOIN unit AS u ON u.uni_id = m2.uni_id LEFT JOIN emsv AS v ON v.sto_id = m.sto_id LEFT JOIN store AS s  ON s.sto_id = m.sto_id where v.emsv_id>0 AND s.kind=1 AND m.actual_quantity>0 AND " + store + " AND m.emat_id in(" + ids + ")";
    }
    System.out.println("sql ====" + sql);
    
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
        detail.setQuantity(rs.getDouble("available_quantity"));
        detail.setAvailableQuantity(rs.getDouble("available_quantity"));
        detail.setUnit(rs.getString("unit_vn"));
        if (kind == 0)
        {
          detail.setMatId(rs.getInt("mat_id"));
          detail.setReqDetailId(rs.getInt("req_detail_id"));
          detail.setRequestNumber(rs.getString("request_number"));
          detail.setMsvNumber(rs.getString("msv_number"));
          detail.setMsvId(rs.getInt("msv_id"));
        }
        else
        {
          detail.setMatId(rs.getInt("emat_id"));
          detail.setMsvNumber(rs.getString("emsv_number"));
          detail.setMsvId(rs.getInt("emsv_id"));
        }
        detail.setStoreName(StringUtil.decodeString(rs.getString("name")));
        detail.setStoId(rs.getInt("sto_id"));
        detail.setPrice(rs.getDouble("price"));
        detail.setKind(kind);
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
  
  public ArrayList searchRfmMaterial(int fieldid, String strFieldvalue, int kind, int stoId)
    throws Exception
  {
    String strFieldname = "";
    switch (fieldid)
    {
    case 1: 
      strFieldname = "m2.name_vn";
      break;
    case 2: 
      strFieldname = "t.request_number";
    }
    String store = "1";
    if (stoId > 0) {
      store = " m.sto_id = " + stoId;
    }
    String sql = "";
    if (kind == 0) {
      sql = "SELECT m.*, m2.name_vn, m2.code, u.*,v.msv_number, t.request_number,s.name  FROM material_store_request AS m LEFT JOIN material AS m2 ON m2.mat_id = m.mat_id LEFT JOIN unit AS u ON u.uni_id=m2.uni_id LEFT JOIN request_detail AS r ON r.det_id=m.req_detail_id LEFT JOIN request AS t ON t.req_id=r.req_id LEFT JOIN store AS s ON s.sto_id=m.sto_id LEFT JOIN msv AS v ON  v.msv_id = m.msv_id where  s.kind=1 AND m.actual_quantity>0 AND " + store;
    } else {
      sql = "SELECT DISTINCT m.*, m2.name_vn,m2.code, u.*,v.emsv_id,v.emsv_number, s.name,s.sto_id  FROM ematerial_store AS m LEFT JOIN ematerial AS m2 ON m2.emat_id = m.emat_id LEFT JOIN unit AS u ON u.uni_id=m2.uni_id LEFT JOIN emsv AS v ON v.sto_id=m.sto_id LEFT JOIN store AS s ON s.sto_id=m.sto_id where  s.kind=1 AND m.actual_quantity>0 AND v.emsv_id>0 AND " + store;
    }
    ResultSet rs = null;
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
    }
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
        detail.setActualQuantity(rs.getDouble("actual_quantity"));
        detail.setAvailableQuantity(rs.getDouble("available_quantity"));
        detail.setReserveQuantity(rs.getDouble("reserve_quantity"));
        detail.setUnit(rs.getString("unit_vn"));
        if (kind == 0)
        {
          detail.setMsrId(rs.getInt("msr_id"));
          detail.setMatId(rs.getInt("mat_id"));
          detail.setReqDetailId(rs.getInt("req_detail_id"));
          detail.setRequestNumber(rs.getString("request_number"));
        }
        else
        {
          detail.setMsrId(rs.getInt("ems_id"));
          detail.setMatId(rs.getInt("emat_id"));
        }
        detail.setStoreName(rs.getString("name"));
        detail.setStoId(rs.getInt("sto_id"));
        detail.setPrice(rs.getDouble("price"));
        
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
  
  public ArrayList searchMaterialInStore(int fieldid, String strFieldvalue, String strFieldvalues, int extraId, String extraValue, int kind)
    throws Exception
  {
    String strFieldname = "";
    String strFieldname2 = "";
    switch (fieldid)
    {
    case 1: 
      strFieldname = "m2.code";
      break;
    case 2: 
      strFieldname = "m2.name_vn";
      break;
    case 3: 
      strFieldname = "t.request_number";
      break;
    case 4: 
      strFieldname = "s.name";
    }
    switch (fieldid)
    {
    case 3: 
      strFieldname2 = "t.request_number";
    }
    ResultSet rs = null;
    
    String sql = "";
    if (kind == 0) {
      sql = "SELECT SUM(m.reserve_quantity) AS s1,SUM(m.actual_quantity) AS s2,SUM(m.available_quantity) AS s3,m.msr_id, m.sto_id, m2.name_vn,m2.code, u.unit_vn, m.mat_id,v.msv_id, v.msv_number, m.req_detail_id, m.price, t.request_number,s.name, md.location  FROM material_store_request AS m LEFT JOIN material AS m2 ON m2.mat_id = m.mat_id LEFT JOIN unit AS u ON u.uni_id=m2.uni_id LEFT JOIN msv AS v ON v.msv_id = m.msv_id LEFT JOIN request_detail AS r ON r.det_id=m.req_detail_id LEFT JOIN request AS t ON t.req_id=r.req_id LEFT JOIN store AS s ON s.sto_id=m.sto_id LEFT JOIN mrir_detail AS md ON md.mrir_id =v.mrir_id AND md.req_detail_id=m.req_detail_id where s.kind=1 AND m.actual_quantity>0 AND 1 ";
    } else {
      sql = "SELECT SUM(m.reserve_quantity) AS s1,SUM(m.actual_quantity) AS s2,SUM(m.available_quantity) AS s3,m.*, m2.name_vn,m2.code, u.*,v.emsv_id,v.emsv_number, s.name,s.sto_id, md.location  FROM ematerial_store AS m LEFT JOIN ematerial AS m2 ON m2.emat_id = m.emat_id LEFT JOIN unit AS u ON u.uni_id=m2.uni_id LEFT JOIN emsv AS v ON v.sto_id=m.sto_id LEFT JOIN store AS s ON s.sto_id=m.sto_id LEFT JOIN emrir_detail AS md ON md.emrir_id =v.emrir_id where  s.kind=1 AND m.available_quantity>0 AND 1 ";
    }
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      sql = sql + " and (" + SQLSearchExpressionUtil.excuteExpression(strFieldvalue, strFieldname) + ")";
    }
    if (!StringUtil.isBlankOrNull(extraValue)) {
      sql = sql + " and t.request_number " + SQLSearchExpressionUtil.excuteExpression(extraValue, strFieldname2);
    }
    if ((!StringUtil.isBlankOrNull(strFieldvalues)) && 
      (!strFieldvalues.equals("all"))) {
      sql = sql + " and m.sto_id=" + strFieldvalues;
    }
    if (kind == 0) {
      sql = sql + " GROUP BY m.req_detail_id, m.mat_id order by m2.mat_id desc";
    } else {
      sql = sql + " GROUP BY m.sto_id order by m2.emat_id desc";
    }
    ArrayList detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RfmDetailBean detail = null;
      while (rs.next())
      {
        detail = new RfmDetailBean();
        detail.setMsrId(rs.getInt("msr_id"));
        detail.setStoId(rs.getInt("sto_id"));
        detail.setMatCode(rs.getString("code"));
        detail.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
        detail.setActualQuantity(rs.getDouble("s2"));
        detail.setAvailableQuantity(rs.getDouble("s3"));
        detail.setReserveQuantity(rs.getDouble("s1"));
        detail.setUnit(rs.getString("unit_vn"));
        if (kind == 0)
        {
          detail.setMatId(rs.getInt("mat_id"));
          detail.setMsvId(rs.getInt("msv_id"));
          detail.setMsvNumber(rs.getString("msv_number"));
          detail.setReqDetailId(rs.getInt("req_detail_id"));
          detail.setRequestNumber(rs.getString("request_number"));
        }
        else
        {
          detail.setMatId(rs.getInt("emat_id"));
          detail.setMsvId(rs.getInt("emsv_id"));
          detail.setMsvNumber(rs.getString("emsv_number"));
        }
        detail.setRequestNumber(rs.getString("request_number"));
        detail.setStoreName(rs.getString("name"));
        detail.setPrice(rs.getDouble("price"));
        detail.setLocation(rs.getString("location"));
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
  
  public RfmBean getRfmByNumber(int kind, String rfmNumber)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "";
    String table = "";
    if (kind == 0) {
      table = "";
    } else {
      table = "e";
    }
    sql = "Select " + table + "rfm_id, status_reserve_quantity From " + table + "rfm Where " + table + "rfm_number='" + rfmNumber + "'";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        RfmBean bean = new RfmBean();
        bean.setRfmId(rs.getInt(table + "rfm_id"));
        bean.setStatusReserveQuantity(rs.getInt("status_reserve_quantity"));
        
        return bean;
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
  
  public RfmBean getRfm(int rfmId, int kind, int memId)
    throws Exception
  {
    ResultSet rs = null;
    
    String table = "";
    if (kind == 0) {
      table = "";
    } else {
      table = "e";
    }
    String sql = "SELECT m.miv_id, r.*,e.fullname AS empName,o.name AS orgName, ro.name as requestOrg, s.name as stoName, o.kind as kindOrg FROM " + table + "rfm AS r LEFT JOIN employee AS e ON r.created_emp=e.emp_id " + " LEFT JOIN organization AS o ON o.org_id=r.org_id " + " LEFT JOIN organization AS ro ON ro.org_id=r.request_org " + " left join store as s on s.sto_id=r.sto_id LEFT JOIN miv AS m ON m.rfm_id = r.rfm_id " + " where " + table + "r.rfm_id=" + rfmId;
    try
    {
      System.out.println("sql=" + sql);
      
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        RfmBean rfm = new RfmBean();
        rfm.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        rfm.setCreator(rs.getInt("created_emp"));
        rfm.setEmpName(rs.getString("empName"));
        rfm.setOrgName(rs.getString("orgName"));
        rfm.setDeliveryDate(DateUtil.formatDate(rs.getDate("delivery_date"), "dd/MM/yyyy"));
        rfm.setDeliveryPlace(StringUtil.decodeString(rs.getString("delivery_place")));
        rfm.setProId(rs.getInt("org_id"));
        rfm.setOrgId(rs.getInt("request_org"));
        if (rs.getInt("kindOrg") == OrganizationBean.KIND_PROJECT)
        {
          rfm.setReceiveId(2);
          rfm.setWhichUseName(rs.getString("orgName") + "-" + rs.getString("requestOrg"));
        }
        else
        {
          rfm.setReceiveId(1);
          rfm.setWhichUseName(rs.getString("requestOrg"));
        }
        rfm.setReqType(rs.getInt("req_type"));
        rfm.setRfmId(rs.getInt(table + "rfm_id"));
        rfm.setRfmNumber(rs.getString(table + "rfm_number"));
        rfm.setStoId(rs.getInt("sto_id"));
        if (kind == 0) {
          rfm.setReqId(rs.getInt("req_id"));
        }
        rfm.setStoreName(rs.getString("stoName"));
        rfm.setRequestOrg(rs.getInt("request_org"));
        rfm.setReqOrgName(rs.getString("requestOrg"));
        rfm.setOrgHandle(rs.getInt("org_handle"));
        rfm.setKind(kind);
        rfm.setStoreApprove(rs.getInt("store_approve"));
        rfm.setStoreComment(StringUtil.decodeString(rs.getString("store_comment")));
        rfm.setAccountingApprove(rs.getInt("accounting_approve"));
        rfm.setAccountingComment(StringUtil.decodeString(rs.getString("accounting_comment")));
        rfm.setPurpose(rs.getString("purpose"));
        
        rfm.setCreatedEmp(rs.getInt("created_emp"));
        if ((rfm.getCreatedEmp() == memId) && (StringUtil.isBlankOrNull(rs.getString("miv_id")))) {
          rfm.setCanSave(1);
        } else {
          rfm.setCanSave(0);
        }
        return rfm;
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
  
  public ArrayList getRfmDetails(int rfmId, int kind)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "";
    if (kind == 0) {
      sql = "Select DISTINCT d.*, m.name_vn as materialName, m.code, t.request_number, ms.available_quantity,m1.msv_number From rfm_detail as d left join material as m on d.mat_id=m.mat_id LEFT JOIN request_detail AS r ON r.det_id=d.req_detail_id LEFT JOIN request AS t ON t.req_id=r.req_id LEFT JOIN rfm AS r1 ON r1.rfm_id = d.rfm_id LEFT JOIN material_store_request AS ms ON (ms.req_detail_id = d.req_detail_id AND ms.mat_id = d.mat_id AND r1.sto_id = ms.sto_id AND ms.msv_id = d.msv_id) LEFT JOIN msv AS m1 ON m1.msv_id=d.msv_id  Where d.rfm_id=" + rfmId;
    } else {
      sql = "SELECT DISTINCT d.*, m.name_vn AS materialName, m.code, ms.available_quantity,m1.emsv_number FROM erfm_detail AS d LEFT JOIN ematerial AS m ON d.emat_id=m.emat_id LEFT JOIN erfm AS r1 ON r1.erfm_id = d.erfm_id LEFT JOIN ematerial_store AS ms ON (ms.emat_id = d.emat_id AND r1.sto_id = ms.sto_id) LEFT JOIN emsv AS m1 ON m1.emsv_id=d.emsv_id  Where d.erfm_id=" + rfmId;
    }
    ArrayList detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RfmDetailBean detail = null;
      while (rs.next())
      {
        detail = new RfmDetailBean();
        detail.setDetId(rs.getInt("det_id"));
        if (rs.getString("materialName") != null) {
          detail.setMatName(StringUtil.decodeString(rs.getString("materialName")));
        }
        detail.setMatCode(rs.getString("code"));
        detail.setQuantity(rs.getDouble("quantity"));
        detail.setQtTemp(rs.getDouble("quantity"));
        detail.setAvailableQuantity(rs.getDouble("available_quantity"));
        detail.setUnit(rs.getString("unit"));
        detail.setComment(StringUtil.decodeString(rs.getString("comment")));
        detail.setPrice(rs.getDouble("price"));
        detail.setKind(kind);
        detail.setMsvId(rs.getInt("msv_id"));
        if (kind == 0)
        {
          detail.setRfmId(rs.getInt("rfm_id"));
          detail.setMatId(rs.getInt("mat_id"));
          detail.setReqDetailId(rs.getInt("req_detail_id"));
          detail.setRequestNumber(rs.getString("request_number"));
          detail.setMsvNumber(rs.getString("msv_number"));
          detail.setMsvId(rs.getInt("msv_id"));
        }
        else
        {
          detail.setRfmId(rs.getInt("erfm_id"));
          detail.setMatId(rs.getInt("emat_id"));
          detail.setMsvNumber(rs.getString("emsv_number"));
          detail.setMsvId(rs.getInt("emsv_id"));
        }
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
  
  public int insertRfm(RfmBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      String date1 = "null";
      String org_handle = null;
      if (bean.getDeliveryDate() != null) {
        date1 = "STR_TO_DATE('" + bean.getDeliveryDate() + "','%d/%m/%Y')";
      }
      String orgId = null;
      String proId = null;
      if (bean.getOrgId() > 0) {
        orgId = bean.getOrgId() + "";
      }
      if (bean.getOrgHandle() > 0) {
        org_handle = bean.getOrgHandle() + "";
      }
      if (bean.getProId() > 0) {
        proId = bean.getProId() + "";
      }
      if (bean.getKind() == 0) {
        sql = "insert into rfm(created_date,created_emp,org_handle,delivery_date,delivery_place,org_id,req_type,rfm_number,sto_id,req_id,request_org,store_approve,store_comment,accounting_approve,accounting_comment,status_reserve_quantity,purpose) values (SYSDATE(),'" + bean.getCreator() + "'," + org_handle + "," + date1 + ",'" + bean.getDeliveryPlace() + "'," + proId + "," + bean.getReqType() + ",'" + bean.getRfmNumber() + "'," + bean.getStoId() + "," + bean.getReqId() + "," + orgId + "," + bean.getStoreApprove() + ",'" + bean.getStoreComment() + "'," + bean.getAccountingApprove() + ",'" + bean.getAccountingComment() + "'," + bean.getStatusReserveQuantity() + ",'" + bean.getPurpose() + "')";
      } else {
        sql = "insert into erfm(created_date,created_emp,org_handle,delivery_date,delivery_place,org_id,req_type,erfm_number,sto_id,request_org,store_approve,store_comment,accounting_approve,accounting_comment,status_reserve_quantity) values (SYSDATE(),'" + bean.getCreator() + "'," + org_handle + "," + date1 + ",'" + bean.getDeliveryPlace() + "'," + proId + "," + bean.getReqType() + ",'" + bean.getRfmNumber() + "'," + bean.getStoId() + "," + orgId + "," + bean.getStoreApprove() + ",'" + bean.getStoreComment() + "'," + bean.getAccountingApprove() + ",'" + bean.getAccountingComment() + "'," + bean.getStatusReserveQuantity() + ")";
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
  
  public int insertRfmDetail(RfmDetailBean bean, int kind)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int result = 0;
    
    String msvId = null;
    if (bean.getMsvId() > 0) {
      msvId = bean.getMsvId() + "";
    }
    try
    {
      String sql = "";
      if (kind == 0) {
        sql = "insert into rfm_detail(rfm_id,mat_id,msv_id,quantity,unit,comment,req_detail_id,price,note) values (" + bean.getRfmId() + "," + bean.getMatId() + "," + msvId + "," + bean.getQuantity() + ",'" + bean.getUnit() + "','" + bean.getComment() + "'," + bean.getReqDetailId() + "," + bean.getPrice() + ",'" + bean.getNote() + "')";
      } else {
        sql = "insert into erfm_detail(erfm_id,emat_id,emsv_id,quantity,unit,comment,price) values (" + bean.getRfmId() + "," + bean.getMatId() + "," + msvId + "," + bean.getQuantity() + ",'" + bean.getUnit() + "','" + bean.getComment() + "'," + bean.getPrice() + ")";
      }
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
  
  public void insertStore(RfmDetailBean bean, int kind)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql1 = "";
      String sql2 = "";
      if (kind == 0) {
        sql1 = "UPDATE material_store_request AS m SET m.reserve_quantity = m.reserve_quantity + " + bean.getQuantity() + " , m.available_quantity = m.available_quantity - " + bean.getQuantity() + " WHERE m.sto_id = " + bean.getStoId() + " AND m.mat_id = " + bean.getMatId() + " AND m.req_detail_id = " + bean.getReqDetailId() + " AND m.msv_id = " + bean.getMsvId();
      } else {
        sql1 = "UPDATE ematerial_store AS m SET m.reserve_quantity = m.reserve_quantity + " + bean.getQuantity() + " , m.available_quantity = m.available_quantity - " + bean.getQuantity() + " WHERE m.sto_id = " + bean.getStoId() + " AND m.emat_id = " + bean.getMatId();
      }
      if (kind == 0) {
        sql2 = "UPDATE material_store_request SET available_quantity = 0 WHERE available_quantity < 0 ";
      } else {
        sql2 = "UPDATE ematerial_store_request SET available_quantity = 0 WHERE available_quantity < 0 ";
      }
      DBUtil.executeUpdate(sql1);
      DBUtil.executeUpdate(sql2);
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
  
  public void updateRfm(RfmBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "";
      String orgId = null;
      String proId = null;
      String org_handle = null;
      if (bean.getOrgHandle() > 0) {
        org_handle = bean.getOrgHandle() + "";
      }
      if (bean.getOrgId() > 0) {
        orgId = bean.getOrgId() + "";
      }
      if (bean.getProId() > 0) {
        proId = bean.getProId() + "";
      }
      if (bean.getKind() == 0) {
        sql = "update rfm set  created_emp='" + bean.getCreator() + "'" + ", delivery_date=STR_TO_DATE('" + bean.getDeliveryDate() + "','%d/%m/%Y')" + ", delivery_place='" + bean.getDeliveryPlace() + "'" + ", org_handle=" + org_handle + ", org_id=" + proId + "" + ", req_type=" + bean.getReqType() + "" + ", rfm_number='" + bean.getRfmNumber() + "'" + ", sto_id=" + bean.getStoId() + "" + ", req_id=" + bean.getReqId() + "" + ", request_org=" + orgId + "" + ", store_approve=" + bean.getStoreApprove() + "" + ", store_comment='" + bean.getStoreComment() + "'" + ", accounting_approve=" + bean.getAccountingApprove() + "" + ", accounting_comment='" + bean.getAccountingComment() + "'" + ", status_reserve_quantity=" + bean.getStatusReserveQuantity() + ",purpose='" + bean.getPurpose() + "' where rfm_id=" + bean.getRfmId();
      } else {
        sql = "update erfm set  created_emp='" + bean.getCreator() + "'" + ", delivery_date=STR_TO_DATE('" + bean.getDeliveryDate() + "','%d/%m/%Y')" + ", delivery_place='" + bean.getDeliveryPlace() + "'" + ", org_handle=" + org_handle + ", org_id=" + proId + "" + ", req_type=" + bean.getReqType() + "" + ", erfm_number='" + bean.getRfmNumber() + "'" + ", sto_id=" + bean.getStoId() + "" + ", request_org=" + orgId + "" + ", store_approve=" + bean.getStoreApprove() + "" + ", store_comment='" + bean.getStoreComment() + "'" + ", accounting_approve=" + bean.getAccountingApprove() + "" + ", accounting_comment='" + bean.getAccountingComment() + "'" + ", status_reserve_quantity=" + bean.getStatusReserveQuantity() + "" + " where erfm_id=" + bean.getRfmId();
      }
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
  
  public void updateRfmDetail(RfmDetailBean bean, int kind)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    String msvId = null;
    if (bean.getMsvId() > 0) {
      msvId = bean.getMsvId() + "";
    }
    try
    {
      String sql = "";
      if (kind == 0) {
        sql = "update rfm_detail set  rfm_id=" + bean.getRfmId() + "" + ", mat_id=" + bean.getMatId() + "" + ", msv_id=" + msvId + "" + ", quantity=" + bean.getQuantity() + "" + ", unit='" + bean.getUnit() + "'" + ", comment='" + bean.getComment() + "'" + ", req_detail_id=" + bean.getReqDetailId() + "" + ", price=" + bean.getPrice() + "" + " where det_id=" + bean.getDetId();
      } else {
        sql = "update erfm_detail set  erfm_id=" + bean.getRfmId() + "" + ", emat_id=" + bean.getMatId() + "" + ", emsv_id=" + msvId + "" + ", quantity=" + bean.getQuantity() + "" + ", unit='" + bean.getUnit() + "" + ", comment='" + bean.getComment() + "'" + ", price=" + bean.getPrice() + "" + " where det_id=" + bean.getDetId();
      }
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
  
  public void updateStore(RfmDetailBean bean, int kind)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "";
      
      int result = 0;
      if (kind == 0)
      {
        double temp = bean.getQtTemp();
        sql = "UPDATE material_store_request AS m SET m.reserve_quantity = m.reserve_quantity + " + temp + " , m.available_quantity = m.available_quantity - " + temp + " WHERE m.sto_id = " + bean.getStoId() + " AND m.mat_id = " + bean.getMatId() + " AND m.req_detail_id = " + bean.getReqDetailId() + " AND m.msv_id = " + bean.getMsvId();
      }
      else
      {
        double temp = bean.getQtTemp();
        sql = "UPDATE ematerial_store AS m SET m.reserve_quantity = m.reserve_quantity + " + temp + " , m.available_quantity = m.available_quantity - " + temp + " WHERE m.sto_id = " + bean.getStoId() + " AND m.emat_id = " + bean.getMatId();
      }
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
  
  public int deleteRfm(String rfmId, int kind)
    throws Exception
  {
    int result = 0;
    try
    {
      deleteRfmDetails(rfmId, kind);
      String sql = "";
      if (kind == 0) {
        sql = "delete from rfm  where rfm_id=" + rfmId;
      } else {
        sql = "delete from erfm  where erfm_id=" + rfmId;
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
    return result;
  }
  
  public int deleteRfmDetail(String detId, int kind)
    throws Exception
  {
    int result = 0;
    try
    {
      ResultSet rs = null;
      String sql = "";
      if (kind == 0)
      {
        if (Integer.parseInt(detId) > 0)
        {
          sql = "SELECT d.*,r.sto_id FROM rfm_detail AS d LEFT JOIN rfm AS r ON r.rfm_id = d.rfm_id WHERE d.det_id =" + detId;
          
          rs = DBUtil.executeQuery(sql);
          while (rs.next()) {
            sql = "UPDATE material_store_request AS m SET m.reserve_quantity = m.reserve_quantity - " + rs.getInt("quantity") + " , m.available_quantity = m.available_quantity + " + rs.getInt("quantity") + " WHERE m.sto_id = " + rs.getInt("sto_id") + " AND m.mat_id = " + rs.getInt("mat_id") + " AND m.req_detail_id = " + rs.getInt("req_detail_id") + " AND m.msv_id = " + rs.getInt("msv_id");
          }
          DBUtil.closeConnection(rs);
          result = DBUtil.executeUpdate(sql);
        }
        sql = "Delete From rfm_detail Where det_id=" + detId;
        result = DBUtil.executeUpdate(sql);
      }
      else
      {
        if (Integer.parseInt(detId) > 0)
        {
          sql = "SELECT d.*,r.sto_id FROM erfm_detail AS d LEFT JOIN erfm AS r ON r.erfm_id = d.erfm_id WHERE d.det_id =" + detId;
          
          rs = DBUtil.executeQuery(sql);
          while (rs.next()) {
            sql = "UPDATE ematerial_store AS m SET m.reserve_quantity = m.reserve_quantity - " + rs.getInt("quantity") + " , m.available_quantity = m.available_quantity + " + rs.getInt("quantity") + " WHERE m.sto_id = " + rs.getInt("sto_id") + " AND m.emat_id = " + rs.getInt("emat_id");
          }
          DBUtil.closeConnection(rs);
          result = DBUtil.executeUpdate(sql);
        }
        sql = "Delete From erfm_detail Where det_id=" + detId;
        result = DBUtil.executeUpdate(sql);
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
    return result;
  }
  
  public int deleteRfmDetails(String rfmId, int kind)
    throws Exception
  {
    int result = 0;
    try
    {
      ResultSet rs = null;
      String sql = "";
      if (kind == 0)
      {
        sql = "SELECT d.*,r.sto_id FROM rfm_detail AS d LEFT JOIN rfm AS r ON r.rfm_id = d.rfm_id WHERE d.rfm_id =" + rfmId;
        
        rs = DBUtil.executeQuery(sql);
        while (rs.next())
        {
          sql = "UPDATE material_store_request AS m SET m.reserve_quantity = m.reserve_quantity - " + rs.getInt("quantity") + " , m.available_quantity = m.available_quantity + " + rs.getInt("quantity") + " WHERE m.sto_id = " + rs.getInt("sto_id") + " AND m.mat_id = " + rs.getInt("mat_id") + " AND m.req_detail_id = " + rs.getInt("req_detail_id") + " AND m.msv_id = " + rs.getInt("msv_id");
          result = DBUtil.executeUpdate(sql);
        }
        DBUtil.closeConnection(rs);
        sql = "Delete From rfm_detail Where rfm_id = " + rfmId;
        result = DBUtil.executeUpdate(sql);
      }
      else
      {
        sql = "SELECT d.*,r.sto_id FROM erfm_detail AS d LEFT JOIN erfm AS r ON r.erfm_id = d.erfm_id WHERE d.erfm_id =" + rfmId;
        
        rs = DBUtil.executeQuery(sql);
        while (rs.next())
        {
          sql = "UPDATE ematerial_store AS m SET m.reserve_quantity = m.reserve_quantity - " + rs.getInt("quantity") + " , m.available_quantity = m.available_quantity + " + rs.getInt("quantity") + " WHERE m.sto_id = " + rs.getInt("sto_id") + " AND m.emat_id = " + rs.getInt("emat_id");
          result = DBUtil.executeUpdate(sql);
        }
        DBUtil.closeConnection(rs);
        sql = "Delete From erfm_detail Where erfm_id = " + rfmId;
        result = DBUtil.executeUpdate(sql);
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
    return result;
  }
  
  public ArrayList searchSimpleRfm(int fieldid, String strFieldvalue, String strFieldvalues, int kind, boolean ok)
    throws Exception
  {
    String strFieldname = "";
    String table = "";
    if (kind == 0) {
      table = "";
    } else {
      table = "e";
    }
    switch (fieldid)
    {
    case 1: 
      strFieldname = table + "rfm_number";
      break;
    case 2: 
      strFieldname = "delivery_place";
    }
    ResultSet rs = null;
    
    String sql = "";
    sql = "SELECT r.*,s.name AS stoName,o.kind AS kindOrg,o1.name as orgName, e.fullname FROM " + table + "rfm AS r LEFT JOIN store AS s ON s.sto_id = r.sto_id" + " left join organization as o on o.org_id=r.org_id" + " LEFT JOIN employee AS e ON e.emp_id = r.created_emp" + " LEFT JOIN organization AS o1 ON o1.org_id=e.org_id WHERE r.created_emp>0 and ( ";
    if (ok)
    {
      sql = sql + " r.org_handle = 4 ";
    }
    else
    {
      if (!getRequestOrg().equals("")) {
        sql = sql + " r.request_org in (" + getRequestOrg() + ")";
      } else {
        sql = sql + " 1 ";
      }
      if (getRequestEmp() > 0) {
        sql = sql + " or r.created_emp=" + getRequestEmp();
      }
    }
    sql = sql + " )";
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      sql = sql + " and (" + SQLSearchExpressionUtil.excuteExpression(strFieldvalue, strFieldname) + ")";
    }
    if ((!StringUtil.isBlankOrNull(strFieldvalues)) && 
      (!strFieldvalues.equals("all"))) {
      sql = sql + " and o.org_id=" + strFieldvalues;
    }
    sql = sql + " order by r." + table + "rfm_id DESC";
    
    ArrayList rfmList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RfmFormBean rfm = null;
      while (rs.next())
      {
        rfm = new RfmFormBean();
        rfm.setCreatedEmp(rs.getInt("created_emp"));
        if (rfm.getCreatedEmp() == getRequestEmp()) {
          rfm.setCanDelete(true);
        } else {
          rfm.setCanDelete(false);
        }
        rfm.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        rfm.setCreator(rs.getInt("created_emp"));
        rfm.setOrgName(rs.getString("orgName"));
        rfm.setEmpName(rs.getString("fullname"));
        rfm.setDeliveryDate(DateUtil.formatDate(rs.getDate("delivery_date"), "dd/MM/yyyy"));
        rfm.setDeliveryPlace(rs.getString("delivery_place"));
        rfm.setKind(kind);
        if (rs.getInt("kindOrg") == OrganizationBean.KIND_PROJECT) {
          rfm.setForName(MCUtil.getBundleString("message.store.proId"));
        } else {
          rfm.setForName(MCUtil.getBundleString("message.rfm.orgId"));
        }
        rfm.setReqType(rs.getInt("req_type"));
        if (rs.getInt("req_type") == 1) {
          rfm.setReqName(MCUtil.getBundleString("message.rfm.store.project"));
        }
        if (rs.getInt("req_type") == 2) {
          rfm.setReqName(MCUtil.getBundleString("message.rfm.store.cty"));
        }
        if (rs.getInt("req_type") == 3) {
          rfm.setReqName(MCUtil.getBundleString("message.miv.type.other"));
        }
        rfm.setRfmId(rs.getInt(table + "rfm_id"));
        rfm.setRfmNumber(rs.getString(table + "rfm_number"));
        rfm.setStoId(rs.getInt("sto_id"));
        rfm.setStoName(rs.getString("stoName"));
        rfm.setRequestOrg(rs.getInt("request_org"));
        rfmList.add(rfm);
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
    return rfmList;
  }
  
  public ArrayList searchAdvRfm(RfmBean bean)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "";
    String table = "";
    if (bean.getKind() == 0) {
      table = "";
    } else {
      table = "e";
    }
    sql = "select * from " + table + "rfm where 1 ";
    
    int orgId = 0;
    int proId = 0;
    if (bean.getOrgId() > 0) {
      orgId = bean.getOrgId();
    }
    if (bean.getProId() > 0) {
      orgId = bean.getProId();
    }
    if (!StringUtil.isBlankOrNull(bean.getRfmNumber())) {
      sql = sql + " AND " + table + "rfm_number LIKE '%" + bean.getRfmNumber() + "%'";
    }
    if (!StringUtil.isBlankOrNull(bean.getCreatedDate())) {
      sql = sql + " AND 0 = DATEDIFF(created_date, STR_TO_DATE('" + bean.getCreatedDate() + "','%d/%m/%Y %H'))";
    }
    if (bean.getCreator() > 0) {
      sql = sql + " AND created_emp = " + bean.getCreator() + "";
    }
    if (orgId > 0) {
      sql = sql + " AND org_id = " + orgId + "";
    }
    if (bean.getReqType() > 0) {
      sql = sql + " AND req_type = " + bean.getReqType() + "";
    }
    if (bean.getStoId() > 0) {
      sql = sql + " AND sto_id = " + bean.getStoId() + "";
    }
    if (bean.getReqId() > 0) {
      sql = sql + " AND req_id = " + bean.getReqId() + "";
    }
    if (!StringUtil.isBlankOrNull(bean.getDeliveryDate())) {
      sql = sql + " AND 0 = DATEDIFF(delivery_date, STR_TO_DATE('" + bean.getDeliveryDate() + "','%d/%m/%Y %H'))";
    }
    if (!StringUtil.isBlankOrNull(bean.getDeliveryPlace())) {
      sql = sql + " AND delivery_place LIKE '%" + bean.getDeliveryPlace() + "%'";
    }
    sql = sql + " order by " + table + "rfm_id desc";
    
    ArrayList rfmList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RfmFormBean rfm = null;
      while (rs.next())
      {
        rfm = new RfmFormBean();
        rfm.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        rfm.setCreator(rs.getInt("created_emp"));
        rfm.setDeliveryDate(DateUtil.formatDate(rs.getDate("delivery_date"), "dd/MM/yyyy"));
        rfm.setDeliveryPlace(rs.getString("delivery_place"));
        rfm.setKind(bean.getKind());
        rfm.setOrgId(rs.getInt("org_id"));
        rfm.setReqType(rs.getInt("req_type"));
        rfm.setRfmId(rs.getInt(table + "rfm_id"));
        rfm.setRfmNumber(rs.getString(table + "rfm_number"));
        rfm.setStoId(rs.getInt("sto_id"));
        rfm.setRequestOrg(rs.getInt("request_org"));
        rfmList.add(rfm);
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
    return rfmList;
  }
  
  public ArrayList getRfmMaterial(int rfmId, String matIds)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select d.*, m.name_vn as materialName, m.code From rfm_detail as d left join material as m on d.mat_id=m.mat_id Where rfm_id=" + rfmId + " and d.mat_id in(" + matIds + ")";
    
    ArrayList detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RfmDetailBean detail = null;
      while (rs.next())
      {
        detail = new RfmDetailBean();
        detail.setDetId(rs.getInt("det_id"));
        detail.setRfmId(rs.getInt("rfm_id"));
        detail.setMatId(rs.getInt("mat_id"));
        if (rs.getString("materialName") != null) {
          detail.setMatName(StringUtil.decodeString(rs.getString("materialName")));
        }
        detail.setMatCode(rs.getString("code"));
        detail.setQuantity(rs.getDouble("quantity"));
        detail.setUnit(rs.getString("unit"));
        detail.setMsvId(rs.getInt("msv_id"));
        detail.setComment(rs.getString("comment"));
        detail.setPrice(rs.getDouble("price"));
        detail.setReqDetailId(rs.getInt("req_detail_id"));
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
  
  public ArrayList getERfmMaterial(int rfmId, String matIds)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select d.*, m.name_vn as materialName, m.code From erfm_detail as d left join ematerial as m on d.emat_id=m.emat_id Where erfm_id=" + rfmId + " and d.emat_id in(" + matIds + ")";
    
    ArrayList detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RfmDetailBean detail = null;
      while (rs.next())
      {
        detail = new RfmDetailBean();
        detail.setDetId(rs.getInt("det_id"));
        detail.setRfmId(rs.getInt("erfm_id"));
        detail.setMatId(rs.getInt("emat_id"));
        if (rs.getString("materialName") != null) {
          detail.setMatName(StringUtil.decodeString(rs.getString("materialName")));
        }
        detail.setMatCode(rs.getString("code"));
        detail.setQuantity(rs.getDouble("quantity"));
        detail.setUnit(rs.getString("unit"));
        detail.setMsvId(rs.getInt("emsv_id"));
        detail.setComment(rs.getString("comment"));
        detail.setPrice(rs.getDouble("price"));
        
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
  
  public ArrayList getRfmsAvailable(int proId, int orgId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT r.*,s.name AS stoName, o.kind as kindOrg  FROM rfm AS r LEFT JOIN store AS s ON s.sto_id = r.sto_id  left join organization as o on o.org_id=r.org_id  where r.org_id = " + proId + " AND r.request_org =  " + orgId + " AND r.status=" + RfmBean.NOT_MIV_STATUS + " order by rfm_number asc";
    
    ArrayList rfmList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RfmFormBean rfm = null;
      while (rs.next())
      {
        rfm = new RfmFormBean();
        rfm.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        rfm.setCreator(rs.getInt("created_emp"));
        rfm.setDeliveryDate(DateUtil.formatDate(rs.getDate("delivery_date"), "dd/MM/yyyy"));
        rfm.setDeliveryPlace(rs.getString("delivery_place"));
        rfm.setOrgId(rs.getInt("org_id"));
        if (rs.getInt("kindOrg") == OrganizationBean.KIND_PROJECT) {
          rfm.setForName(MCUtil.getBundleString("message.store.proId"));
        } else {
          rfm.setForName(MCUtil.getBundleString("message.rfm.orgId"));
        }
        rfm.setReqType(rs.getInt("req_type"));
        if (rs.getInt("req_type") == 1) {
          rfm.setReqName(MCUtil.getBundleString("message.rfm.store.project"));
        }
        if (rs.getInt("req_type") == 2) {
          rfm.setReqName(MCUtil.getBundleString("message.rfm.store.cty"));
        }
        if (rs.getInt("req_type") == 3) {
          rfm.setReqName(MCUtil.getBundleString("message.miv.type.other"));
        }
        rfm.setRfmId(rs.getInt("rfm_id"));
        rfm.setRfmNumber(rs.getString("rfm_number"));
        rfm.setStoId(rs.getInt("sto_id"));
        rfm.setStoName(rs.getString("stoName"));
        rfm.setRequestOrg(rs.getInt("request_org"));
        
        rfmList.add(rfm);
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
    return rfmList;
  }
  
  public ArrayList getERfmsAvailable()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT r.*,s.name AS stoName, o.kind as kindOrg  FROM erfm AS r LEFT JOIN store AS s ON s.sto_id = r.sto_id  left join organization as o on o.org_id=r.org_id  where r.status=" + RfmBean.NOT_MIV_STATUS + " order by erfm_number asc";
    
    ArrayList rfmList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      RfmFormBean rfm = null;
      while (rs.next())
      {
        rfm = new RfmFormBean();
        rfm.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        rfm.setCreator(rs.getInt("created_emp"));
        rfm.setDeliveryDate(DateUtil.formatDate(rs.getDate("delivery_date"), "dd/MM/yyyy"));
        rfm.setDeliveryPlace(rs.getString("delivery_place"));
        rfm.setOrgId(rs.getInt("org_id"));
        if (rs.getInt("kindOrg") == OrganizationBean.KIND_PROJECT) {
          rfm.setForName(MCUtil.getBundleString("message.store.proId"));
        } else {
          rfm.setForName(MCUtil.getBundleString("message.rfm.orgId"));
        }
        rfm.setReqType(rs.getInt("req_type"));
        if (rs.getInt("req_type") == 1) {
          rfm.setReqName(MCUtil.getBundleString("message.rfm.store.project"));
        }
        if (rs.getInt("req_type") == 2) {
          rfm.setReqName(MCUtil.getBundleString("message.rfm.store.cty"));
        }
        if (rs.getInt("req_type") == 3) {
          rfm.setReqName(MCUtil.getBundleString("message.miv.type.other"));
        }
        rfm.setRfmId(rs.getInt("erfm_id"));
        rfm.setRfmNumber(rs.getString("erfm_number"));
        rfm.setStoId(rs.getInt("sto_id"));
        rfm.setStoName(rs.getString("stoName"));
        rfm.setRequestOrg(rs.getInt("request_org"));
        
        rfmList.add(rfm);
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
    return rfmList;
  }
  
  public boolean checkExisted(String id)
    throws SQLException
  {
    ResultSet rs = null;
    try
    {
      rs = DBUtil.executeQuery("SELECT * FROM rfm WHERE rfm_id =" + id);
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
  
  public String checkDeleted(String id, int kind)
    throws SQLException
  {
    ResultSet rs = null;
    try
    {
      String sql = "";
      if (kind == 0) {
        sql = "SELECT * FROM miv WHERE rfm_id =";
      } else {
        sql = "SELECT * FROM emiv WHERE erfm_id =";
      }
      rs = DBUtil.executeQuery(sql + id);
      String str1;
      if (rs.next()) {
        return rs.getString("miv_number");
      }
      return "";
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
  
  public int getRfmDetail(int rfmId, int reqDetId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT det_id from rfm_detail where rfm_id=" + rfmId + " and req_detail_id=" + reqDetId;
    int result = 0;
    try
    {
      System.out.println("sql=" + sql);
      
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        result = rs.getInt("det_id");
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
  
  public String getNextRfmNumber(String prefix, int length)
    throws Exception
  {
    String result = "";
    try
    {
      result = getNextNumber(prefix, length, "rfm_number", "rfm");
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    return result;
  }
}
