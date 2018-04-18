package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ReturnedMaterialDiaryBean;
import com.venus.mc.bean.ReturnedMaterialDiaryDetailBean;
import com.venus.mc.bean.StoreBean;
import com.venus.mc.bean.StoreLevel2Bean;
import com.venus.mc.bean.UsedMaterialDiaryBean;
import com.venus.mc.bean.UsedMaterialDiaryDetailBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.process.store.level2.ReturnedMaterialSearchedFormBean;
import com.venus.mc.process.store.level2.ReturnedMaterialStore2FormBean;
import com.venus.mc.process.store.level2.UsedMaterialSearchedFormBean;
import com.venus.mc.process.store.level2.UsedMaterialStore2FormBean;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.SQLSearchExpressionUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;

public class StoreDAO
  extends BasicDAO
{
  public ArrayList getStores()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT store.*, o.name AS proName FROM store LEFT JOIN organization AS o ON store.org_id = o.org_id WHERE o.kind = 100 Order by sto_id ASC";
    
    ArrayList storeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      StoreBean store = null;
      while (rs.next())
      {
        store = new StoreBean();
        store.setStoId(rs.getInt("sto_id"));
        store.setProId(rs.getInt("org_id"));
        store.setName(rs.getString("name"));
        store.setProName(rs.getString("proName"));
        store.setPhysicalAdd(rs.getString("physical_add"));
        store.setKind(rs.getInt("kind"));
        if (rs.getInt("kind") == 1) {
          store.setKindDetail(MCUtil.getBundleString("message.store.kind1"));
        } else if (rs.getInt("kind") == 2) {
          store.setKindDetail(MCUtil.getBundleString("message.store.kind2"));
        }
        store.setComments(StringUtil.getString(rs.getString("comments")));
        store.setEmpId(rs.getInt("emp_id"));
        store.setEmpTel(rs.getString("emp_tel"));
        storeList.add(store);
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
    return storeList;
  }
  
  public ArrayList getStores(int kind)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT store.*, o.name AS proName FROM store LEFT JOIN organization AS o ON store.org_id = o.org_id WHERE store.kind = " + kind + " Order by sto_id ASC";
    
    ArrayList storeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      StoreBean store = null;
      while (rs.next())
      {
        store = new StoreBean();
        store.setStoId(rs.getInt("sto_id"));
        store.setName(rs.getString("name"));
        store.setProId(rs.getInt("org_id"));
        store.setProName(rs.getString("proName"));
        store.setPhysicalAdd(rs.getString("physical_add"));
        store.setKind(rs.getInt("kind"));
        if (rs.getInt("kind") == 1) {
          store.setKindDetail(MCUtil.getBundleString("message.store.kind1"));
        } else if (rs.getInt("kind") == 2) {
          store.setKindDetail(MCUtil.getBundleString("message.store.kind2"));
        }
        store.setComments(StringUtil.getString(rs.getString("comments")));
        store.setEmpId(rs.getInt("emp_id"));
        store.setEmpTel(rs.getString("emp_tel"));
        storeList.add(store);
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
    return storeList;
  }
  
  public StoreBean getStore(int stoId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "SELECT store.*, o.name AS proName FROM store LEFT JOIN organization AS o ON store.org_id = o.org_id WHERE o.kind = 100 AND sto_id=" + stoId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        StoreBean store = new StoreBean();
        store.setStoId(rs.getInt("sto_id"));
        store.setName(rs.getString("name"));
        store.setProId(rs.getInt("org_id"));
        store.setProName(rs.getString("proName"));
        store.setPhysicalAdd(rs.getString("physical_add"));
        store.setKind(rs.getInt("kind"));
        store.setComments(StringUtil.getString(rs.getString("comments")));
        store.setUsedOrg(rs.getInt("used_org"));
        store.setEmpId(rs.getInt("emp_id"));
        store.setEmpTel(rs.getString("emp_tel"));
        
        return store;
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
  
  public ArrayList getProjectStores(int proId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT store.*, o.name AS proName FROM store LEFT JOIN organization AS o ON store.org_id = o.org_id WHERE o.kind = 100 AND store.org_id = " + proId + " Order by sto_id ASC";
    
    ArrayList storeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      StoreBean store = null;
      while (rs.next())
      {
        store = new StoreBean();
        store.setStoId(rs.getInt("sto_id"));
        store.setName(rs.getString("name"));
        store.setProId(rs.getInt("org_id"));
        store.setProName(rs.getString("proName"));
        store.setPhysicalAdd(rs.getString("physical_add"));
        store.setKind(rs.getInt("kind"));
        if (rs.getInt("kind") == 1) {
          store.setKindDetail(MCUtil.getBundleString("message.store.kind1"));
        } else if (rs.getInt("kind") == 2) {
          store.setKindDetail(MCUtil.getBundleString("message.store.kind2"));
        }
        store.setComments(StringUtil.getString(rs.getString("comments")));
        store.setEmpId(rs.getInt("emp_id"));
        store.setEmpTel(rs.getString("emp_tel"));
        storeList.add(store);
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
    return storeList;
  }
  
  public StoreBean getProjectStore(int proId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "SELECT store.*, o.name AS proName FROM store LEFT JOIN organization AS o ON store.org_id = o.org_id WHERE o.kind = 100 AND o.org_id=" + proId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        StoreBean store = new StoreBean();
        store.setStoId(rs.getInt("sto_id"));
        store.setName(rs.getString("name"));
        store.setProId(rs.getInt("org_id"));
        store.setProName(rs.getString("proName"));
        store.setPhysicalAdd(rs.getString("physical_add"));
        store.setKind(rs.getInt("kind"));
        if (rs.getInt("kind") == 1) {
          store.setKindDetail(MCUtil.getBundleString("message.store.kind1"));
        } else if (rs.getInt("kind") == 2) {
          store.setKindDetail(MCUtil.getBundleString("message.store.kind2"));
        }
        store.setComments(StringUtil.getString(rs.getString("comments")));
        store.setEmpId(rs.getInt("emp_id"));
        store.setEmpTel(rs.getString("emp_tel"));
        
        return store;
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
  
  public StoreBean getStoreByName(String name)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select sto_id From store Where name = '" + name + "'";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        StoreBean store = new StoreBean();
        store.setStoId(rs.getInt("sto_id"));
        
        return store;
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
  
  public int insertStore(StoreBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      sql = "Insert Into store(org_id, name, physical_add, kind, comments, emp_id, emp_tel) Values (" + bean.getProId() + ",'" + bean.getName() + "','" + bean.getPhysicalAdd() + "'," + bean.getKind() + ",'" + bean.getComments() + "'," + bean.getEmpId() + ",'" + bean.getEmpTel() + "')";
      
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
  
  public void updateStore(StoreBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "Update store Set  org_id=" + bean.getProId() + ", name='" + bean.getName() + "'" + ", physical_add='" + bean.getPhysicalAdd() + "'" + ", kind=" + bean.getKind() + ", comments='" + bean.getComments() + "'" + ", emp_id=" + bean.getEmpId() + ", emp_tel='" + bean.getEmpTel() + "'" + " Where sto_id=" + bean.getStoId();
      
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
  
  public void updateNameStore(StoreBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "Update store Set  name='" + bean.getName() + "'" + " Where org_id=" + bean.getProId();
      
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
  
  public int deleteStore(int stoId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "Delete From store Where sto_id = " + stoId;
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
  
  public int deleteStoreByProId(int proId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "Delete From store Where org_id = " + proId;
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
  
  public ArrayList searchSimpleStore(int fieldid, String strFieldvalue)
    throws Exception
  {
    String strFieldname = "";
    switch (fieldid)
    {
    case 1: 
      strFieldname = "store.name";
    }
    ResultSet rs = null;
    
    String sql = "Select store.*, organization.name From store left join organization on store.org_id = organization.org_id Where organization.kind = 100 ";
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
    }
    sql = sql + " GROUP BY store.NAME Order by sto_id DESC";
    
    ArrayList storeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      StoreBean store = null;
      while (rs.next())
      {
        store = new StoreBean();
        store.setStoId(rs.getInt("sto_id"));
        store.setName(rs.getString("name"));
        store.setProId(rs.getInt("org_id"));
        store.setProName(rs.getString("organization.name"));
        store.setPhysicalAdd(rs.getString("physical_add"));
        store.setKind(rs.getInt("kind"));
        if (rs.getInt("kind") == 1) {
          store.setKindDetail(MCUtil.getBundleString("message.store.kind1"));
        } else if (rs.getInt("kind") == 2) {
          store.setKindDetail(MCUtil.getBundleString("message.store.kind2"));
        }
        store.setComments(StringUtil.getString(rs.getString("comments")));
        storeList.add(store);
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
    return storeList;
  }
  
  public ArrayList searchAdvStore(StoreBean bean)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select store.*, organization.name From store left join organization on store.org_id = organization.org_id Where organization.kind = 100";
    if (bean.getProId() > 0) {
      sql = sql + " AND store.org_id = " + bean.getProId();
    }
    if (!StringUtil.isBlankOrNull(bean.getName())) {
      sql = sql + " AND store.name LIKE '%" + bean.getName() + "%'";
    }
    if (!StringUtil.isBlankOrNull(bean.getPhysicalAdd())) {
      sql = sql + " AND physical_add LIKE '%" + bean.getPhysicalAdd() + "%'";
    }
    if (bean.getKind() > 0) {
      sql = sql + " AND store.kind = " + bean.getKind();
    }
    sql = sql + " Order by sto_id DESC";
    
    ArrayList storeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      StoreBean store = null;
      while (rs.next())
      {
        store = new StoreBean();
        store.setStoId(rs.getInt("sto_id"));
        store.setName(rs.getString("name"));
        store.setProId(rs.getInt("org_id"));
        store.setProName(rs.getString("organization.name"));
        store.setPhysicalAdd(rs.getString("physical_add"));
        store.setKind(rs.getInt("kind"));
        if (rs.getInt("kind") == 1) {
          store.setKindDetail(MCUtil.getBundleString("message.store.kind1"));
        } else if (rs.getInt("kind") == 2) {
          store.setKindDetail(MCUtil.getBundleString("message.store.kind2"));
        }
        store.setComments(StringUtil.getString(rs.getString("comments")));
        storeList.add(store);
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
    return storeList;
  }
  
  public ArrayList getStoreLevel2s(int orgId, String permissionOrgs)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select DISTINCT s.*, (SELECT COUNT(umd.ums_id) FROM used_material_diary AS umd WHERE s.sto_id=umd.sto_id) AS umsQuantity, (SELECT COUNT(rmd.rms_id) FROM returned_material_diary AS rmd WHERE s.sto_id=rmd.sto_id) as rmsQuantity FROM store s, used_material_import AS u where u.sto_id=s.sto_id AND s.kind=" + StoreBean.LEVEL2;
    if (orgId > 0) {
      sql = sql + " and s.used_org=" + orgId;
    }
    if (!GenericValidator.isBlankOrNull(permissionOrgs)) {
      sql = sql + " and s.used_org in (" + permissionOrgs + ")";
    }
    ArrayList storeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      StoreLevel2Bean store = null;
      while (rs.next())
      {
        store = new StoreLevel2Bean();
        store.setSto2Id(rs.getInt("sto_id"));
        store.setName(rs.getString("name"));
        store.setOrgId(rs.getInt("org_id"));
        store.setUmsQuantity(rs.getInt("umsQuantity"));
        store.setRmsQuantity(rs.getInt("rmsQuantity"));
        if (store.getUmsQuantity() > 0) {
          store.setUmsQuantityText(store.getUmsQuantity() + " " + MCUtil.getBundleString("message.record"));
        } else {
          store.setUmsQuantityText("0 " + MCUtil.getBundleString("message.record"));
        }
        if (store.getRmsQuantity() > 0) {
          store.setRmsQuantityText(store.getRmsQuantity() + " " + MCUtil.getBundleString("message.record"));
        } else {
          store.setRmsQuantityText("0 " + MCUtil.getBundleString("message.record"));
        }
        storeList.add(store);
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
    return storeList;
  }
  
  public ArrayList getUsedMaterialStores(int stoId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select umd.*, po.name as projectName, o.name as orgName, e.fullname, COUNT(rdet.det_id) AS returnedDetail From used_material_diary as umd, organization as po, organization as o, employee as e , used_material_diary_detail AS udet LEFT JOIN returned_material_diary_detail AS rdet ON rdet.ums_det_id=udet.det_id where umd.pro_id=po.org_id and umd.org_id=o.org_id and umd.created_emp=e.emp_id AND udet.ums_id=umd.ums_id";
    if (stoId != 0) {
      sql = sql + " and umd.sto_id=" + stoId;
    }
    sql = sql + " GROUP BY umd.ums_id ORDER BY umd.ums_id DESC";
    
    ArrayList storeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      UsedMaterialDiaryBean store = null;
      while (rs.next())
      {
        store = new UsedMaterialDiaryBean();
        store.setUmsId(rs.getInt("ums_id"));
        store.setSto2Id(rs.getInt("sto_id"));
        store.setUpdateDate(DateUtil.formatDate(rs.getDate("update_date"), "dd/MM/yyyy"));
        store.setUsmNumber(rs.getString("usm_number"));
        store.setEmployeeName(rs.getString("fullname"));
        store.setProjectName(rs.getString("projectName"));
        store.setOrgName(rs.getString("orgName"));
        if (rs.getInt("returnedDetail") > 0) {
          store.setCanNotDelete(1);
        }
        storeList.add(store);
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
    return storeList;
  }
  
  public ArrayList searchUsedMaterialStores(int fieldid, String strFieldvalue, int stoId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select umd.*, po.name as projectName, o.name as orgName, e.fullname, COUNT(rdet.det_id) AS returnedDetail From used_material_diary as umd, organization as po, organization as o, employee as e , used_material_diary_detail AS udet LEFT JOIN returned_material_diary_detail AS rdet ON rdet.ums_det_id=udet.det_id where umd.pro_id=po.org_id and umd.org_id=o.org_id and umd.created_emp=e.emp_id AND udet.ums_id=umd.ums_id";
    if (stoId != 0) {
      sql = sql + " and umd.sto_id=" + stoId;
    }
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      if (fieldid == 1) {
        sql = sql + " and (" + SQLSearchExpressionUtil.excuteExpression(strFieldvalue, "umd.usm_number") + ")";
      } else if (fieldid == 2) {
        sql = sql + " and (" + SQLSearchExpressionUtil.excuteExpression(strFieldvalue, "o.name") + ")";
      } else if (fieldid == 3) {
        sql = sql + " and (" + SQLSearchExpressionUtil.excuteExpression(strFieldvalue, "po.name") + ")";
      }
    }
    sql = sql + " GROUP BY umd.ums_id ORDER BY umd.ums_id DESC";
    
    ArrayList storeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      UsedMaterialDiaryBean store = null;
      while (rs.next())
      {
        store = new UsedMaterialDiaryBean();
        store.setUmsId(rs.getInt("ums_id"));
        store.setSto2Id(rs.getInt("sto_id"));
        store.setUpdateDate(DateUtil.formatDate(rs.getDate("update_date"), "dd/MM/yyyy"));
        store.setUsmNumber(rs.getString("usm_number"));
        store.setEmployeeName(rs.getString("fullname"));
        store.setProjectName(rs.getString("projectName"));
        store.setOrgName(rs.getString("orgName"));
        if (rs.getInt("returnedDetail") > 0) {
          store.setCanNotDelete(1);
        }
        storeList.add(store);
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
    return storeList;
  }
  
  public ArrayList getReturnedMaterialStores(int stoId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select umd.*, po.name as projectName, o.name as orgName, e.fullname From returned_material_diary as umd, organization as po, organization as o, employee as e where umd.pro_id=po.org_id and umd.org_id=o.org_id and umd.created_emp=e.emp_id ";
    if (stoId != 0) {
      sql = sql + " and umd.sto_id=" + stoId;
    }
    sql = sql + " ORDER BY umd.rms_id DESC";
    
    ArrayList storeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      ReturnedMaterialDiaryBean store = null;
      while (rs.next())
      {
        store = new ReturnedMaterialDiaryBean();
        store.setRmsId(rs.getInt("rms_id"));
        store.setSto2Id(rs.getInt("sto_id"));
        store.setUpdateDate(DateUtil.formatDate(rs.getDate("update_date"), "dd/MM/yyyy"));
        store.setRsmNumber(rs.getString("rsm_number"));
        store.setEmployeeName(rs.getString("fullname"));
        store.setProjectName(rs.getString("projectName"));
        store.setOrgName(rs.getString("orgName"));
        storeList.add(store);
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
    return storeList;
  }
  
  public ArrayList searchReturnedMaterialStores(int fieldid, String strFieldvalue, int stoId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select umd.*, po.name as projectName, o.name as orgName, e.fullname From returned_material_diary as umd, organization as po, organization as o, employee as e where umd.pro_id=po.org_id and umd.org_id=o.org_id and umd.created_emp=e.emp_id ";
    if (stoId != 0) {
      sql = sql + " and umd.sto_id=" + stoId;
    }
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      if (fieldid == 1) {
        sql = sql + " and (" + SQLSearchExpressionUtil.excuteExpression(strFieldvalue, "umd.rsm_number") + ")";
      } else if (fieldid == 2) {
        sql = sql + " and (" + SQLSearchExpressionUtil.excuteExpression(strFieldvalue, "o.name") + ")";
      } else if (fieldid == 3) {
        sql = sql + " and (" + SQLSearchExpressionUtil.excuteExpression(strFieldvalue, "po.name") + ")";
      }
    }
    sql = sql + " ORDER BY umd.rms_id DESC";
    
    ArrayList storeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      ReturnedMaterialDiaryBean store = null;
      while (rs.next())
      {
        store = new ReturnedMaterialDiaryBean();
        store.setRmsId(rs.getInt("rms_id"));
        store.setSto2Id(rs.getInt("sto_id"));
        store.setUpdateDate(DateUtil.formatDate(rs.getDate("update_date"), "dd/MM/yyyy"));
        store.setRsmNumber(rs.getString("rsm_number"));
        store.setEmployeeName(rs.getString("fullname"));
        store.setProjectName(rs.getString("projectName"));
        store.setOrgName(rs.getString("orgName"));
        storeList.add(store);
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
    return storeList;
  }
  
  public UsedMaterialStore2FormBean getUsedMaterialStore(int umsId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select u.*, s.name as orgName From used_material_diary as u, store as s, organization as o  Where u.sto_id=s.sto_id and s.org_id=o.org_id and u.ums_id=" + umsId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        UsedMaterialStore2FormBean store = new UsedMaterialStore2FormBean();
        store.setUmsId(rs.getInt("ums_id"));
        store.setSto2Id(rs.getInt("sto_id"));
        store.setOrganizationName(rs.getString("orgName"));
        store.setUpdateDate(DateUtil.formatDate(rs.getDate("update_date"), "dd/MM/yyyy"));
        store.setCreatedEmp(rs.getInt("created_emp"));
        store.setProId(rs.getInt("pro_id"));
        store.setOrgId(rs.getInt("org_id"));
        store.setUsmNumber(rs.getString("usm_number"));
        store.setLocation(rs.getString("location"));
        return store;
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
  
  public ReturnedMaterialStore2FormBean getReturnedMaterialStore(int rmsId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select u.*, s.name as orgName From returned_material_diary as u, store as s, organization as o  Where u.sto_id=s.sto_id and s.org_id=o.org_id and u.rms_id=" + rmsId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        ReturnedMaterialStore2FormBean store = new ReturnedMaterialStore2FormBean();
        store.setRmsId(rs.getInt("rms_id"));
        store.setSto2Id(rs.getInt("sto_id"));
        store.setOrganizationName(rs.getString("orgName"));
        store.setUpdateDate(DateUtil.formatDate(rs.getDate("update_date"), "dd/MM/yyyy"));
        store.setCreatedEmp(rs.getInt("created_emp"));
        store.setProId(rs.getInt("pro_id"));
        store.setOrgId(rs.getInt("org_id"));
        store.setRsmNumber(rs.getString("rsm_number"));
        
        return store;
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
  
  public StoreLevel2Bean getStoreLevel2(int stoId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "select s.* from store as s where s.kind = " + StoreBean.LEVEL2 + " and s.sto_id=" + stoId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        StoreLevel2Bean store = new StoreLevel2Bean();
        store.setSto2Id(rs.getInt("sto_id"));
        store.setName(rs.getString("name"));
        store.setOrgId(rs.getInt("org_id"));
        store.setUsedOrg(rs.getInt("used_org"));
        
        return store;
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
  
  public int insertUsedMaterialStore(UsedMaterialDiaryBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    try
    {
      String sql = "";
      String updateDate = "null";
      if (!GenericValidator.isBlankOrNull(bean.getUpdateDate())) {
        updateDate = "STR_TO_DATE('" + bean.getUpdateDate() + "','%d/%m/%Y')";
      }
      sql = "Insert Into used_material_diary(sto_id, update_date,created_emp,usm_number,pro_id,org_id,location) Values (" + bean.getSto2Id() + "," + updateDate + "," + bean.getCreatedEmp() + ",'" + bean.getUsmNumber() + "'," + bean.getProId() + "," + bean.getOrgId() + ",'" + bean.getLocation() + "')";
      
      return DBUtil.executeInsert(sql);
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
  
  public int insertReturnedMaterialStore(ReturnedMaterialDiaryBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    try
    {
      String sql = "";
      String updateDate = "null";
      if (!GenericValidator.isBlankOrNull(bean.getUpdateDate())) {
        updateDate = "STR_TO_DATE('" + bean.getUpdateDate() + "','%d/%m/%Y')";
      }
      sql = "Insert Into returned_material_diary(sto_id, update_date,created_emp,rsm_number,pro_id,org_id) Values (" + bean.getSto2Id() + "," + updateDate + "," + bean.getCreatedEmp() + ",'" + bean.getRsmNumber() + "'," + bean.getProId() + "," + bean.getOrgId() + ")";
      
      return DBUtil.executeInsert(sql);
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
  
  public void updateUsedMaterialStore(UsedMaterialDiaryBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String updateDate = "null";
      if (!GenericValidator.isBlankOrNull(bean.getUpdateDate())) {
        updateDate = "STR_TO_DATE('" + bean.getUpdateDate() + "','%d/%m/%Y')";
      }
      String sql = "Update used_material_diary Set  sto_id=" + bean.getSto2Id() + ", update_date=" + updateDate + ", usm_number='" + bean.getUsmNumber() + "'" + ", location='" + bean.getLocation() + "'" + " Where ums_id=" + bean.getUmsId();
      
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
  
  public void updateReturnedMaterialStore(ReturnedMaterialDiaryBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String updateDate = "null";
      if (!GenericValidator.isBlankOrNull(bean.getUpdateDate())) {
        updateDate = "STR_TO_DATE('" + bean.getUpdateDate() + "','%d/%m/%Y')";
      }
      String sql = "Update returned_material_diary Set  sto_id=" + bean.getSto2Id() + ", update_date=" + updateDate + ", usm_number='" + bean.getRsmNumber() + "'" + " Where ums_id=" + bean.getRmsId();
      
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
  
  public ArrayList getUsedMaterialStoreDetails(int umsId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From used_material_diary_detail where ums_id=" + umsId;
    
    ArrayList storeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      UsedMaterialDiaryDetailBean store = null;
      while (rs.next())
      {
        store = new UsedMaterialDiaryDetailBean();
        store.setDetId(rs.getInt("det_id"));
        store.setUmsId(rs.getInt("ums_id"));
        store.setMatId(rs.getInt("mat_id"));
        store.setUsedQuantity(rs.getDouble("used_quantity"));
        store.setCurrentQuantity(rs.getDouble("current_quantity"));
        store.setReqDetailId(rs.getInt("req_detail_id"));
        store.setMivId(rs.getInt("miv_id"));
        store.setNote(rs.getString("note"));
        storeList.add(store);
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
    return storeList;
  }
  
  public ArrayList getReturnedMaterialStoreDetails(int rmsId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From returned_material_diary_detail where rms_id=" + rmsId;
    
    ArrayList storeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      ReturnedMaterialDiaryDetailBean store = null;
      while (rs.next())
      {
        store = new ReturnedMaterialDiaryDetailBean();
        store.setDetId(rs.getInt("det_id"));
        store.setRmsId(rs.getInt("rms_id"));
        store.setMatId(rs.getInt("mat_id"));
        store.setReturnedQuantity(rs.getDouble("returned_quantity"));
        store.setCurrentQuantity(rs.getDouble("current_quantity"));
        store.setReqDetailId(rs.getInt("req_detail_id"));
        store.setUmsDetId(rs.getInt("ums_det_id"));
        store.setStatus(rs.getString("status"));
        store.setNote(rs.getString("note"));
        storeList.add(store);
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
    return storeList;
  }
  
  public int insertUsedMaterialStoreDetail(UsedMaterialDiaryDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int detId = 0;
    try
    {
      String sql = "";
      sql = "Insert Into used_material_diary_detail(ums_id, mat_id, current_quantity, used_quantity, req_detail_id, miv_id, note, org_id, emp_id) Values (" + bean.getUmsId() + "," + bean.getMatId() + "," + bean.getCurrentQuantity() + "," + bean.getUsedQuantity() + "," + bean.getReqDetailId() + "," + bean.getMivId() + ",'" + bean.getNote() + "'";
      if (bean.getOrgId() != 0) {
        sql = sql + "," + bean.getOrgId();
      } else {
        sql = sql + ",null";
      }
      if (bean.getEmpId() != 0) {
        sql = sql + "," + bean.getEmpId();
      } else {
        sql = sql + ",null";
      }
      sql = sql + ")";
      
      detId = DBUtil.executeInsert(sql);
      
      sql = "update used_material_import set  current_quantity=current_quantity-" + bean.getUsedQuantity() + " where req_detail_id=" + bean.getReqDetailId() + " and sto_id=" + bean.getStoId() + " and miv_id=" + bean.getMivId();
      
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
    return detId;
  }
  
  public int insertReturnedMaterialStoreDetail(ReturnedMaterialDiaryDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int detId = 0;
    try
    {
      String sql = "";
      sql = "Insert Into returned_material_diary_detail(rms_id, mat_id, current_quantity, returned_quantity, req_detail_id, ums_det_id, status, note) Values (" + bean.getRmsId() + "," + bean.getMatId() + "," + bean.getCurrentQuantity() + "," + bean.getReturnedQuantity() + "," + bean.getReqDetailId() + "," + bean.getUmsDetId() + ",'" + bean.getStatus() + "','" + bean.getNote() + "')";
      
      detId = DBUtil.executeInsert(sql);
      
      UsedMaterialDiaryDetailBean umsDetBean = getUsedMaterialDiaryDetail(bean.getUmsDetId());
      if (umsDetBean != null)
      {
        sql = "update used_material_import set  current_quantity=current_quantity+" + bean.getReturnedQuantity() + " where req_detail_id=" + umsDetBean.getReqDetailId() + " and sto_id=" + umsDetBean.getStoId() + " and miv_id=" + umsDetBean.getMivId();
        
        DBUtil.executeUpdate(sql);
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
    return detId;
  }
  
  public void updateUsedMaterialStoreDetail(UsedMaterialDiaryDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      UsedMaterialDiaryDetailBean detail = getUsedMaterialDiaryDetail(bean.getDetId());
      String sql = "Update used_material_diary_detail Set  used_quantity=" + bean.getUsedQuantity() + ", note='" + bean.getNote() + "'" + " Where det_id=" + bean.getDetId();
      
      DBUtil.executeUpdate(sql);
      
      sql = "update used_material_import set  current_quantity=current_quantity+" + detail.getUsedQuantity() + "-" + bean.getUsedQuantity() + " where req_detail_id=" + bean.getReqDetailId() + " and sto_id=" + bean.getStoId() + " and miv_id=" + bean.getMivId();
      
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
  
  public void updateReturnedMaterialStoreDetail(ReturnedMaterialDiaryDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "Update returned_material_diary_detail Set  returned_quantity=" + bean.getReturnedQuantity() + ", note='" + bean.getNote() + "'" + ", status='" + bean.getStatus() + "'" + " Where det_id=" + bean.getDetId();
      
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
  
  public int insertStoreLevel2(String name, int storeOrg, int requestOrg)
    throws Exception
  {
    ResultSet rs = null;
    try
    {
      String sql = "";
      sql = "select sto_id from store where used_org=" + requestOrg;
      if (storeOrg > 0) {
        sql = sql + " and org_id=" + storeOrg;
      } else {
        sql = sql + " and org_id is null";
      }
      rs = DBUtil.executeQuery(sql);
      int stoId = 0;
      if (rs.next()) {
        stoId = rs.getInt("sto_id");
      }
      DBUtil.closeConnection(rs);
      if (stoId == 0)
      {
        sql = "Insert Into store(name, used_org, kind, org_id) Values ('" + name + "'," + requestOrg + "," + StoreBean.LEVEL2;
        if (storeOrg > 0) {
          sql = sql + "," + storeOrg;
        } else {
          sql = sql + ",null";
        }
        sql = sql + ")";
        return DBUtil.executeInsert(sql);
      }
      return stoId;
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
  
  public void insertStoreLevel2Detail(int mivId, int stoId, int matId, double quantity, int reqDetailId, int rfmSto)
    throws Exception
  {
    ResultSet rs = null;
    try
    {
      String sql = "";
      
      sql = "select det_id from used_material_import where miv_id=" + mivId + " and sto_id=" + stoId + " and mat_id=" + matId + " and req_detail_id=" + reqDetailId;
      
      rs = DBUtil.executeQuery(sql);
      int detId = 0;
      if (rs.next()) {
        detId = rs.getInt("det_id");
      }
      DBUtil.closeConnection(rs);
      if (detId == 0)
      {
        sql = "Insert Into used_material_import(mat_id, miv_id, sto_id, import_quantity, current_quantity, req_detail_id) Values (" + matId + "," + mivId + "," + stoId + "," + quantity + "," + quantity + "," + reqDetailId + ")";
        
        DBUtil.executeInsert(sql);
      }
      else
      {
        sql = "update used_material_import set current_quantity=current_quantity+" + quantity + " where det_id=" + detId;
        DBUtil.executeInsert(sql);
      }
      sql = "select msr_id from material_store_request where sto_id=" + rfmSto + " and req_detail_id=" + reqDetailId;
      rs = DBUtil.executeQuery(sql);
      detId = 0;
      if (rs.next()) {
        detId = rs.getInt("msr_id");
      }
      DBUtil.closeConnection(rs);
      if (detId == 0)
      {
        sql = "Insert Into material_store_request(mat_id, sto_id, actual_quantity, req_detail_id) Values (" + matId + "," + stoId + "," + quantity + "," + reqDetailId + ")";
        
        DBUtil.executeInsert(sql);
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
    finally {}
  }
  
  public ArrayList searchStoreLevel2Material(int stoId, int fieldid, String strFieldvalue, int extraId, String extraValue)
    throws Exception
  {
    String strFieldname = "";
    String strFieldname2 = "";
    switch (fieldid)
    {
    case 1: 
      strFieldname = "code";
      break;
    case 2: 
      strFieldname = "name_vn";
    }
    switch (extraId)
    {
    case 3: 
      strFieldname2 = "r.request_number";
    }
    ResultSet rs = null;
    
    String sql = "select s.*, r.request_number, m.code, m.name_vn, miv.miv_number from used_material_import as s  left join request_detail as rdet on s.req_detail_id=rdet.det_id left join request as r on r.req_id=rdet.req_id left join material as m on rdet.mat_id=m.mat_id left join miv as miv on s.miv_id=miv.miv_id where s.current_quantity>0 and s.sto_id=" + stoId;
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      sql = sql + " and (" + SQLSearchExpressionUtil.excuteExpression(strFieldvalue, strFieldname) + ")";
    }
    if (!StringUtil.isBlankOrNull(extraValue)) {
      sql = sql + " and (" + SQLSearchExpressionUtil.excuteExpression(extraValue, strFieldname2) + ")";
    }
    ArrayList materialList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      UsedMaterialSearchedFormBean material = null;
      while (rs.next())
      {
        material = new UsedMaterialSearchedFormBean();
        
        material.setMs2rId(rs.getInt("det_id"));
        material.setSto2Id(rs.getInt("sto_id"));
        material.setMatId(rs.getInt("mat_id"));
        material.setCurrentQuantity(rs.getDouble("current_quantity"));
        material.setReqDetailId(rs.getInt("req_detail_id"));
        material.setRequestNumber(rs.getString("request_number"));
        material.setMatCode(rs.getString("code"));
        material.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
        material.setMivNumber(rs.getString("miv_number"));
        materialList.add(material);
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
    return materialList;
  }
  
  public ArrayList searchStoreLevel2MaterialForReturned(int stoId, int empId, String date, int fieldid, String strFieldvalue, int extraId, String extraValue)
    throws Exception
  {
    String strFieldname = "";
    String strFieldname2 = "";
    switch (fieldid)
    {
    case 1: 
      strFieldname = "code";
      break;
    case 2: 
      strFieldname = "name_vn";
    }
    switch (extraId)
    {
    case 3: 
      strFieldname2 = "s.usm_number";
    }
    ResultSet rs = null;
    
    String sql = "SELECT DISTINCT sdet.det_id, sdet.mat_id, sdet.req_detail_id, s.sto_id, s.usm_number, r.request_number, m.CODE, m.name_vn, sdet.used_quantity  AS currentQuantity  FROM used_material_diary_detail AS sdet  LEFT JOIN used_material_diary AS s ON sdet.ums_id=s.ums_id LEFT JOIN request_detail AS rdet ON sdet.req_detail_id=rdet.det_id LEFT JOIN request AS r ON r.req_id=rdet.req_id LEFT JOIN material AS m ON rdet.mat_id=m.mat_id WHERE sdet.current_quantity> sdet.used_quantity  and s.sto_id=" + stoId;
    if (empId != 0) {
      sql = sql + " and s.created_emp=" + empId;
    }
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      sql = sql + " and (" + SQLSearchExpressionUtil.excuteExpression(strFieldvalue, strFieldname) + ")";
    }
    if (!StringUtil.isBlankOrNull(extraValue)) {
      sql = sql + " and (" + SQLSearchExpressionUtil.excuteExpression(extraValue, strFieldname2) + ")";
    }
    if (!GenericValidator.isBlankOrNull(date)) {
      sql = sql + " AND (DATE_FORMAT(s.update_date,'%d/%m/%Y') LIKE '%" + date + "%')";
    }
    ArrayList materialList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      ReturnedMaterialSearchedFormBean material = null;
      while (rs.next())
      {
        material = new ReturnedMaterialSearchedFormBean();
        material.setMs2rId(rs.getInt("det_id"));
        material.setSto2Id(rs.getInt("sto_id"));
        material.setMatId(rs.getInt("mat_id"));
        material.setCurrentQuantity(rs.getDouble("currentQuantity"));
        material.setReqDetailId(rs.getInt("req_detail_id"));
        material.setRequestNumber(rs.getString("request_number"));
        material.setMatCode(rs.getString("code"));
        material.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
        material.setUmsNumber(rs.getString("usm_number"));
        materialList.add(material);
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
    return materialList;
  }
  
  public ArrayList getUsedMaterialsByMsrId(String ids)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select s.mat_id, s.current_quantity, s.req_detail_id, s.miv_id, m.name_vn as matName, un.unit_vn as unitName, miv.miv_number, '' as note From used_material_import as s  left join material as m on s.mat_id=m.mat_id   left join unit as un on un.uni_id=m.uni_id   left join miv as miv on miv.miv_id=s.miv_id   where s.det_id in (" + ids + ")";
    
    ArrayList storeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      UsedMaterialDiaryDetailBean store = null;
      while (rs.next())
      {
        store = new UsedMaterialDiaryDetailBean();
        store.setMatId(rs.getInt("mat_id"));
        store.setCurrentQuantity(rs.getDouble("current_quantity"));
        
        store.setReqDetailId(rs.getInt("req_detail_id"));
        store.setMatName(StringUtil.decodeString(rs.getString("matName")));
        store.setUnit(rs.getString("unitName"));
        store.setMivNumber(rs.getString("miv_number"));
        store.setNote(rs.getString("note"));
        store.setMivId(rs.getInt("miv_id"));
        storeList.add(store);
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
    return storeList;
  }
  
  public ArrayList getReturnedMaterialsByMsrId(String ids)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select sdet.mat_id, sdet.used_quantity, sdet.req_detail_id, sdet.det_id, s.usm_number, m.name_vn as matName, un.unit_vn as unitName, '' as note, '' as status From used_material_diary_detail as sdet  left join used_material_diary as s on s.ums_id=sdet.ums_id left join material as m on sdet.mat_id=m.mat_id   left join unit as un on un.uni_id=m.uni_id   where sdet.det_id in (" + ids + ")";
    
    ArrayList storeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      ReturnedMaterialDiaryDetailBean store = null;
      while (rs.next())
      {
        store = new ReturnedMaterialDiaryDetailBean();
        store.setMatId(rs.getInt("mat_id"));
        store.setCurrentQuantity(rs.getDouble("used_quantity"));
        store.setReturnedQuantity(store.getCurrentQuantity());
        store.setReqDetailId(rs.getInt("req_detail_id"));
        store.setUmsDetId(rs.getInt("det_id"));
        store.setMatName(StringUtil.decodeString(rs.getString("matName")));
        store.setUnit(rs.getString("unitName"));
        store.setUmsNumber(rs.getString("usm_number"));
        store.setNote(rs.getString("note"));
        store.setStatus(rs.getString("status"));
        storeList.add(store);
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
    return storeList;
  }
  
  public ArrayList getUsedMaterialDetails(int umsId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select udet.*, m.name_vn as matName, un.unit_vn as unitName, udet.miv_id, miv.miv_number, rdet.det_id AS returnedDetail From used_material_diary_detail as udet  left join material as m on udet.mat_id=m.mat_id  left join unit as un on un.uni_id=m.uni_id  left join miv as miv on miv.miv_id=udet.miv_id  LEFT JOIN returned_material_diary_detail AS rdet ON rdet.ums_det_id=udet.det_id where udet.ums_id=" + umsId + " order by udet.det_id";
    
    ArrayList storeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      UsedMaterialDiaryDetailBean store = null;
      while (rs.next())
      {
        store = new UsedMaterialDiaryDetailBean();
        store.setDetId(rs.getInt("det_id"));
        store.setUmsId(rs.getInt("ums_id"));
        store.setMatId(rs.getInt("mat_id"));
        store.setCurrentQuantity(rs.getDouble("current_quantity"));
        store.setUsedQuantity(rs.getDouble("used_quantity"));
        store.setReqDetailId(rs.getInt("req_detail_id"));
        store.setMatName(StringUtil.decodeString(rs.getString("matName")));
        store.setUnit(rs.getString("unitName"));
        store.setOrgId(rs.getInt("org_id"));
        store.setEmpId(rs.getInt("emp_id"));
        store.setMivId(rs.getInt("miv_id"));
        store.setMivNumber(rs.getString("miv_number"));
        store.setNote(rs.getString("note"));
        if (rs.getInt("returnedDetail") > 0) {
          store.setCanNotDelete(1);
        }
        storeList.add(store);
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
    return storeList;
  }
  
  public ArrayList getReturnedMaterialDetails(int rmsId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select udet.*, m.name_vn as matName, un.unit_vn as unitName From returned_material_diary_detail as udet  left join material as m on udet.mat_id=m.mat_id  left join unit as un on un.uni_id=m.uni_id  where udet.rms_id=" + rmsId;
    
    ArrayList storeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      ReturnedMaterialDiaryDetailBean store = null;
      while (rs.next())
      {
        store = new ReturnedMaterialDiaryDetailBean();
        store.setDetId(rs.getInt("det_id"));
        store.setRmsId(rs.getInt("rms_id"));
        store.setMatId(rs.getInt("mat_id"));
        store.setCurrentQuantity(rs.getDouble("current_quantity"));
        store.setReturnedQuantity(rs.getDouble("returned_quantity"));
        store.setReqDetailId(rs.getInt("req_detail_id"));
        store.setUmsDetId(rs.getInt("ums_det_id"));
        store.setMatName(StringUtil.decodeString(rs.getString("matName")));
        store.setUnit(rs.getString("unitName"));
        
        store.setNote(rs.getString("note"));
        store.setStatus(rs.getString("status"));
        storeList.add(store);
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
    return storeList;
  }
  
  public UsedMaterialDiaryDetailBean getUsedMaterialDiaryDetail(int detId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select det.*, u.sto_id from used_material_diary_detail as det, used_material_diary as u  where det.ums_id=u.ums_id and det.det_id=" + detId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        UsedMaterialDiaryDetailBean store = new UsedMaterialDiaryDetailBean();
        store.setDetId(rs.getInt("det_id"));
        store.setUmsId(rs.getInt("ums_id"));
        store.setMatId(rs.getInt("mat_id"));
        store.setStoId(rs.getInt("sto_id"));
        store.setCurrentQuantity(rs.getDouble("current_quantity"));
        store.setUsedQuantity(rs.getDouble("used_quantity"));
        store.setReqDetailId(rs.getInt("req_detail_id"));
        store.setMivId(rs.getInt("miv_id"));
        return store;
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
  
  public ReturnedMaterialDiaryDetailBean getReturnedMaterialDiaryDetail(int detId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select det.*, u.sto_id from returned_material_diary_detail as det, returned_material_diary as u  where det.rms_id=u.rms_id and det.det_id=" + detId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        ReturnedMaterialDiaryDetailBean store = new ReturnedMaterialDiaryDetailBean();
        store.setDetId(rs.getInt("det_id"));
        store.setRmsId(rs.getInt("rms_id"));
        store.setMatId(rs.getInt("mat_id"));
        store.setCurrentQuantity(rs.getDouble("current_quantity"));
        store.setReturnedQuantity(rs.getDouble("returned_quantity"));
        store.setReqDetailId(rs.getInt("req_detail_id"));
        store.setUmsDetId(rs.getInt("ums_det_id"));
        store.setNote(rs.getString("note"));
        store.setStatus(rs.getString("status"));
        return store;
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
  
  public int deleteUmsDetail(int detId)
    throws Exception
  {
    int result = 0;
    try
    {
      UsedMaterialDiaryDetailBean detail = getUsedMaterialDiaryDetail(detId);
      String sql = "Delete From used_material_diary_detail Where det_id=" + detId;
      result = DBUtil.executeUpdate(sql);
      if (detail != null)
      {
        sql = "update material_store_request set actual_quantity=actual_quantity+" + detail.getUsedQuantity() + " Where sto_id=" + detail.getStoId() + " and req_detail_id=" + detail.getReqDetailId();
        
        result = DBUtil.executeUpdate(sql);
        
        sql = "update used_material_import set current_quantity=current_quantity+" + detail.getUsedQuantity() + " Where sto_id=" + detail.getStoId() + " and req_detail_id=" + detail.getReqDetailId() + " and miv_id=" + detail.getMivId();
        
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
  
  public int deleteRmsDetail(int detId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "Delete From returned_material_diary_detail Where det_id=" + detId;
      result = DBUtil.executeUpdate(sql);
      UsedMaterialDiaryDetailBean detail = getUsedMaterialDiaryDetail(detId);
      if (detail != null)
      {
        sql = "update used_material_import set current_quantity=current_quantity-" + detail.getUsedQuantity() + " Where sto_id=" + detail.getStoId() + " and req_detail_id=" + detail.getReqDetailId() + " and miv_id=" + detail.getMivId();
        
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
  
  public int deleteUms(int umsId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "Delete From used_material_diary Where ums_id=" + umsId;
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
  
  public int deleteRms(int rmsId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "Delete From returned_material_diary Where rms_id=" + rmsId;
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
  
  public UsedMaterialStore2FormBean getUsedMaterialStoreByDate(int stoId, String createdDate)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select u.*, s.name as orgName From used_material_diary as u, store as s, organization as o  Where u.sto_id=s.sto_id and s.org_id=o.org_id and u.sto_id=" + stoId + " AND STR_TO_DATE('" + createdDate + "','%d/%m/%Y')=u.update_date";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        UsedMaterialStore2FormBean store = new UsedMaterialStore2FormBean();
        store.setUmsId(rs.getInt("ums_id"));
        store.setSto2Id(rs.getInt("sto_id"));
        store.setOrganizationName(rs.getString("orgName"));
        store.setUpdateDate(DateUtil.formatDate(rs.getDate("update_date"), "dd/MM/yyyy"));
        
        return store;
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
  
  public ArrayList getStoresLevel1()
    throws Exception
  {
    ArrayList storeList = new ArrayList();
    ResultSet rs = null;
    
    String sql = "select * from store where kind=1";
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next())
      {
        LabelValueBean store = new LabelValueBean();
        store.setValue(rs.getString("sto_id"));
        store.setLabel(rs.getString("name"));
        storeList.add(store);
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
    return storeList;
  }
  
  public ArrayList getProject()
    throws Exception
  {
    ArrayList storeList = new ArrayList();
    ResultSet rs = null;
    
    String sql = "select * from organization where kind=100";
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next())
      {
        LabelValueBean store = new LabelValueBean();
        store.setValue(rs.getString("org_id"));
        store.setLabel(rs.getString("name"));
        storeList.add(store);
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
    return storeList;
  }
  
  public int getStore1IdByPro(int proId)
    throws Exception
  {
    String sql = "select sto_id from store where kind=1 and org_id=" + proId;
    ResultSet rs = null;
    int result = 0;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next()) {
        result = rs.getInt("sto_id");
      }
    }
    catch (SQLException sqle)
    {
      throw new Exception(sqle.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return result;
  }
  
  public ArrayList getStoresOfProject(int proId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "";
    if (proId != 0) {
      sql = "select s.* from store as s  where s.org_id=" + proId + " and (s.kind=1" + " or (s.kind=2 and s.name like '%VSP'))";
    } else {
      sql = "select s.* from store as s  where s.sto_id=1";
    }
    ArrayList storeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      StoreBean store = null;
      while (rs.next())
      {
        store = new StoreBean();
        store.setStoId(rs.getInt("sto_id"));
        store.setName(rs.getString("name"));
        store.setProId(rs.getInt("org_id"));
        store.setPhysicalAdd(rs.getString("physical_add"));
        store.setKind(rs.getInt("kind"));
        if (rs.getInt("kind") == 1) {
          store.setKindDetail(MCUtil.getBundleString("message.store.kind1"));
        } else if (rs.getInt("kind") == 2) {
          store.setKindDetail(MCUtil.getBundleString("message.store.kind2"));
        }
        store.setComments(StringUtil.getString(rs.getString("comments")));
        storeList.add(store);
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
    return storeList;
  }
  
  public StoreBean getStore(int orgId, String name)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "SELECT * FROM store WHERE org_id=" + orgId + " and name='" + name + "'";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        StoreBean store = new StoreBean();
        store.setStoId(rs.getInt("sto_id"));
        store.setName(rs.getString("name"));
        store.setProId(rs.getInt("org_id"));
        store.setPhysicalAdd(rs.getString("physical_add"));
        store.setKind(rs.getInt("kind"));
        store.setComments(StringUtil.getString(rs.getString("comments")));
        store.setUsedOrg(rs.getInt("used_org"));
        store.setEmpId(rs.getInt("emp_id"));
        store.setEmpTel(rs.getString("emp_tel"));
        
        return store;
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
  
  public StoreBean getStore(String name)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "SELECT * FROM store WHERE name='" + name + "'";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        StoreBean store = new StoreBean();
        store.setStoId(rs.getInt("sto_id"));
        store.setName(rs.getString("name"));
        store.setProId(rs.getInt("org_id"));
        store.setPhysicalAdd(rs.getString("physical_add"));
        store.setKind(rs.getInt("kind"));
        store.setComments(StringUtil.getString(rs.getString("comments")));
        store.setUsedOrg(rs.getInt("used_org"));
        store.setEmpId(rs.getInt("emp_id"));
        store.setEmpTel(rs.getString("emp_tel"));
        
        return store;
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
  
  public ArrayList getStoreLevel2s(int orgId, String permissionOrgs, String proIds)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT s.sto_id, CONCAT(s.NAME,' - ',o.NAME) AS NAME, s.org_id FROM store AS s, organization AS o WHERE s.used_org=o.org_id and s.kind=" + StoreBean.LEVEL2;
    if (orgId > 0) {
      sql = sql + " and s.used_org=" + orgId;
    }
    if (!GenericValidator.isBlankOrNull(permissionOrgs)) {
      sql = sql + " and s.used_org in (" + permissionOrgs + ")";
    }
    if (!GenericValidator.isBlankOrNull(proIds)) {
      sql = sql + " and s.org_id in (" + proIds + ")";
    }
    ArrayList storeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      StoreLevel2Bean store = null;
      while (rs.next())
      {
        store = new StoreLevel2Bean();
        store.setSto2Id(rs.getInt("sto_id"));
        store.setName(rs.getString("name"));
        store.setOrgId(rs.getInt("org_id"));
        storeList.add(store);
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
    return storeList;
  }
  
  public String getNextUSMNumber(String prefix, int length)
    throws Exception
  {
    String result = "";
    try
    {
      result = getNextNumber(prefix, length, "usm_number", "used_material_diary");
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    return result;
  }
  
  public String getNextRMSNumber(String prefix, int length)
    throws Exception
  {
    String result = "";
    try
    {
      result = getNextNumber(prefix, length, "rsm_number", "returned_material_diary");
    }
    catch (Exception ex)
    {
      throw new Exception(ex.getMessage());
    }
    return result;
  }
  
  public void updateMaterialStore2(double quantity, int reqDetailId, int storeId)
    throws Exception
  {
    try
    {
      String sql = "update used_material_import set  current_quantity=current_quantity+" + quantity + " where req_detail_id=" + reqDetailId + " and sto_id=" + storeId;
      
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
}
