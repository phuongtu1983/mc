package com.venus.mc.dao;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.util.MCUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;

public class OrganizationDAO
  extends BasicDAO
{
  public ArrayList getOrganizations()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From organization Order by name";
    
    ArrayList organizationList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      OrganizationBean organization = null;
      while (rs.next())
      {
        organization = new OrganizationBean();
        organization.setOrgId(rs.getInt("org_id"));
        organization.setName(rs.getString("name"));
        organization.setAbbreviate(rs.getString("abbreviate"));
        organization.setParentId(rs.getInt("parent_id"));
        organization.setStatus(rs.getInt("status"));
        organization.setKind(rs.getInt("kind"));
        if (rs.getInt("status") == 1) {
          organization.setStatusDetail(MCUtil.getBundleString("message.organization.status1"));
        } else if (rs.getInt("status") == 2) {
          organization.setStatusDetail(MCUtil.getBundleString("message.organization.status2"));
        }
        if (rs.getInt("kind") == 1) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind1"));
        } else if (rs.getInt("kind") == 2) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind2"));
        } else if (rs.getInt("kind") == 3) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind3"));
        } else if (rs.getInt("kind") == 4) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind4"));
        }
        organizationList.add(organization);
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
    return organizationList;
  }
  
  public ArrayList getOrgByKind(int kind)
    throws Exception
  {
    ResultSet rs = null;
    
    String kinds = "";
    if (kind < OrganizationBean.KIND_PROJECT) {
      kinds = "kind < " + OrganizationBean.KIND_PROJECT;
    } else {
      kinds = "end_date >= NOW() AND kind = " + OrganizationBean.KIND_PROJECT;
    }
    String sql = "Select * From organization Where " + kinds + " Order by name";
    
    ArrayList organizationList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      OrganizationBean organization = null;
      while (rs.next())
      {
        organization = new OrganizationBean();
        organization.setOrgId(rs.getInt("org_id"));
        organization.setName(rs.getString("name"));
        organization.setAbbreviate(rs.getString("abbreviate"));
        organization.setParentId(rs.getInt("parent_id"));
        organization.setStatus(rs.getInt("status"));
        organization.setKind(rs.getInt("kind"));
        if (rs.getInt("status") == 1) {
          organization.setStatusDetail(MCUtil.getBundleString("message.organization.status1"));
        } else if (rs.getInt("status") == 2) {
          organization.setStatusDetail(MCUtil.getBundleString("message.organization.status2"));
        }
        if (rs.getInt("kind") == 1) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind1"));
        } else if (rs.getInt("kind") == 2) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind2"));
        } else if (rs.getInt("kind") == 3) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind3"));
        } else if (rs.getInt("kind") == 4) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind4"));
        }
        organizationList.add(organization);
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
    return organizationList;
  }
  
  public ArrayList getOrgByDN()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From organization Where kind < 4 Order by name";
    
    ArrayList organizationList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      OrganizationBean organization = null;
      while (rs.next())
      {
        organization = new OrganizationBean();
        organization.setOrgId(rs.getInt("org_id"));
        organization.setName(rs.getString("name"));
        organization.setAbbreviate(rs.getString("abbreviate"));
        organization.setParentId(rs.getInt("parent_id"));
        organization.setStatus(rs.getInt("status"));
        organization.setKind(rs.getInt("kind"));
        if (rs.getInt("status") == 1) {
          organization.setStatusDetail(MCUtil.getBundleString("message.organization.status1"));
        } else if (rs.getInt("status") == 2) {
          organization.setStatusDetail(MCUtil.getBundleString("message.organization.status2"));
        }
        if (rs.getInt("kind") == 1) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind1"));
        } else if (rs.getInt("kind") == 2) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind2"));
        } else if (rs.getInt("kind") == 3) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind3"));
        } else if (rs.getInt("kind") == 4) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind4"));
        }
        organizationList.add(organization);
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
    return organizationList;
  }
  
  public ArrayList getOrganizations(int kind)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From organization Where kind=" + kind + " Order by name";
    
    ArrayList organizationList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      OrganizationBean organization = null;
      while (rs.next())
      {
        organization = new OrganizationBean();
        organization.setOrgId(rs.getInt("org_id"));
        organization.setName(rs.getString("name"));
        organization.setAbbreviate(rs.getString("abbreviate"));
        organization.setParentId(rs.getInt("parent_id"));
        organization.setStatus(rs.getInt("status"));
        organization.setKind(rs.getInt("kind"));
        organizationList.add(organization);
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
    return organizationList;
  }
  
  public OrganizationBean getOrganization(int orgId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select * From organization Where org_id=" + orgId + " order by name";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        OrganizationBean organization = new OrganizationBean();
        organization = new OrganizationBean();
        organization.setOrgId(rs.getInt("org_id"));
        organization.setName(rs.getString("name"));
        organization.setAbbreviate(rs.getString("abbreviate"));
        organization.setParentId(rs.getInt("parent_id"));
        organization.setStatus(rs.getInt("status"));
        organization.setKind(rs.getInt("kind"));
        
        return organization;
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
  
  public OrganizationBean getOrganizations(String orgIds)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select * From organization Where org_id in (" + orgIds + ") order by name";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        OrganizationBean organization = new OrganizationBean();
        organization = new OrganizationBean();
        organization.setOrgId(rs.getInt("org_id"));
        organization.setName(rs.getString("name"));
        organization.setAbbreviate(rs.getString("abbreviate"));
        organization.setParentId(rs.getInt("parent_id"));
        organization.setStatus(rs.getInt("status"));
        organization.setKind(rs.getInt("kind"));
        return organization;
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
  
  public OrganizationBean getOrganizationByName(String name)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "select org_id from organization where name='" + name + "'";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        OrganizationBean organization = new OrganizationBean();
        organization.setOrgId(rs.getInt("org_id"));
        
        return organization;
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
  
  public int insertOrganization(OrganizationBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      
      sql = "Insert Into organization(name, abbreviate, parent_id, status, kind) Values ('" + bean.getName() + "','" + bean.getAbbreviate() + "'," + bean.getParentId() + "," + bean.getStatus() + "," + bean.getKind() + ")";
      
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
  
  public void updateOrganization(OrganizationBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "Update organization Set  name='" + bean.getName() + "'" + ", abbreviate='" + bean.getAbbreviate() + "'" + ", parent_id=" + bean.getParentId() + ", status=" + bean.getStatus() + ", kind=" + bean.getKind() + " Where org_id=" + bean.getOrgId();
      
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
  
  public int deleteOrganization(int orgId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "Delete From organization Where org_id=" + orgId;
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
  
  public ArrayList searchSimpleOrganization(int fieldid, String strFieldvalue)
    throws Exception
  {
    String strFieldname = "";
    switch (fieldid)
    {
    case 1: 
      strFieldname = "name";
    }
    ResultSet rs = null;
    
    String sql = "Select * From organization Where kind < " + OrganizationBean.KIND_PROJECT;
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
    }
    sql = sql + " order by org_id DESC";
    
    ArrayList organizationList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      OrganizationBean organization = null;
      while (rs.next())
      {
        organization = new OrganizationBean();
        organization = new OrganizationBean();
        organization.setOrgId(rs.getInt("org_id"));
        organization.setName(rs.getString("name"));
        organization.setAbbreviate(rs.getString("abbreviate"));
        organization.setParentId(rs.getInt("parent_id"));
        organization.setStatus(rs.getInt("status"));
        organization.setKind(rs.getInt("kind"));
        organizationList.add(organization);
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
    return organizationList;
  }
  
  public ArrayList searchAdvOrganization(OrganizationBean bean)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From organization Where kind < " + OrganizationBean.KIND_PROJECT;
    if (!StringUtil.isBlankOrNull(bean.getName())) {
      sql = sql + " AND name LIKE '%" + bean.getName() + "%'";
    }
    if (!StringUtil.isBlankOrNull(bean.getAbbreviate())) {
      sql = sql + " AND abbreviate LIKE '%" + bean.getAbbreviate() + "%'";
    }
    if (bean.getParentId() != 0) {
      sql = sql + " AND parent_id = " + bean.getParentId();
    }
    if (bean.getStatus() != 0) {
      sql = sql + " AND status = " + bean.getStatus();
    }
    if (bean.getKind() != 0) {
      sql = sql + " AND kind =" + bean.getKind();
    }
    sql = sql + " Order by name";
    
    ArrayList organizationList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      OrganizationBean organization = null;
      while (rs.next())
      {
        organization = new OrganizationBean();
        organization.setOrgId(rs.getInt("org_id"));
        organization.setName(rs.getString("name"));
        organization.setAbbreviate(rs.getString("abbreviate"));
        organization.setParentId(rs.getInt("parent_id"));
        organization.setStatus(rs.getInt("status"));
        organization.setKind(rs.getInt("kind"));
        if (rs.getInt("status") == 1) {
          organization.setStatusDetail(MCUtil.getBundleString("message.organization.status1"));
        } else if (rs.getInt("status") == 2) {
          organization.setStatusDetail(MCUtil.getBundleString("message.organization.status2"));
        }
        if (rs.getInt("kind") == 1) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind1"));
        } else if (rs.getInt("kind") == 2) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind2"));
        } else if (rs.getInt("kind") == 3) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind3"));
        } else if (rs.getInt("kind") == 4) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind4"));
        }
        organizationList.add(organization);
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
    return organizationList;
  }
  
  public ArrayList getOrganizationList()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from organization where kind!=100 and kind!=4 order by name";
    
    ArrayList organizationList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      LabelValueBean organization = null;
      while (rs.next())
      {
        organization = new LabelValueBean();
        organization.setLabel(rs.getString("name"));
        organization.setValue(rs.getString("org_id"));
        organizationList.add(organization);
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
    return organizationList;
  }
  
  public ArrayList getProjectList()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from organization Where kind = " + OrganizationBean.KIND_PROJECT + " order by name";
    
    ArrayList organizationList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      LabelValueBean organization = null;
      while (rs.next())
      {
        organization = new LabelValueBean();
        organization.setLabel(rs.getString("name"));
        organization.setValue(rs.getString("org_id"));
        organizationList.add(organization);
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
    return organizationList;
  }
  
  public ArrayList getProjectListInProgress()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from organization where kind = " + OrganizationBean.KIND_PROJECT + " and start_date <= sysdate() and sysdate()<= end_date" + " order by name";
    
    ArrayList orgList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      LabelValueBean org = null;
      while (rs.next())
      {
        org = new LabelValueBean();
        org.setLabel(rs.getString("name"));
        org.setValue(rs.getString("org_id"));
        orgList.add(org);
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
    return orgList;
  }
  
  public OrganizationBean getOrganizationOfEmployee(int empId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select o.* From organization as o, employee as e Where o.org_id=e.org_id and e.emp_id=" + empId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        OrganizationBean organization = new OrganizationBean();
        organization = new OrganizationBean();
        organization.setOrgId(rs.getInt("org_id"));
        organization.setName(rs.getString("name"));
        organization.setAbbreviate(rs.getString("abbreviate"));
        organization.setParentId(rs.getInt("parent_id"));
        organization.setStatus(rs.getInt("status"));
        organization.setKind(rs.getInt("kind"));
        
        return organization;
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
  
  public ArrayList getOrganizationChild(int parentId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From organization where parent_id=" + parentId + " Order by name";
    
    ArrayList organizationList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      OrganizationBean organization = null;
      while (rs.next())
      {
        organization = new OrganizationBean();
        organization.setOrgId(rs.getInt("org_id"));
        organization.setName(rs.getString("name"));
        organization.setAbbreviate(rs.getString("abbreviate"));
        organization.setParentId(rs.getInt("parent_id"));
        organization.setStatus(rs.getInt("status"));
        organization.setKind(rs.getInt("kind"));
        if (rs.getInt("status") == 1) {
          organization.setStatusDetail(MCUtil.getBundleString("message.organization.status1"));
        } else if (rs.getInt("status") == 2) {
          organization.setStatusDetail(MCUtil.getBundleString("message.organization.status2"));
        }
        if (rs.getInt("kind") == 1) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind1"));
        } else if (rs.getInt("kind") == 2) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind2"));
        } else if (rs.getInt("kind") == 3) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind3"));
        } else if (rs.getInt("kind") == 4) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind4"));
        }
        organizationList.add(organization);
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
    return organizationList;
  }
  
  public OrganizationBean getOrganizationByAbbreviate(String name)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "select org_id from organization where abbreviate='" + name + "'";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        OrganizationBean organization = new OrganizationBean();
        organization.setOrgId(rs.getInt("org_id"));
        
        return organization;
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
  
  public ArrayList getOrgExceptKind(String kinds)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From organization Where kind not in (" + kinds + ") Order by name";
    
    ArrayList organizationList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      OrganizationBean organization = null;
      while (rs.next())
      {
        organization = new OrganizationBean();
        organization.setOrgId(rs.getInt("org_id"));
        organization.setName(rs.getString("name"));
        organization.setAbbreviate(rs.getString("abbreviate"));
        organization.setParentId(rs.getInt("parent_id"));
        organization.setStatus(rs.getInt("status"));
        organization.setKind(rs.getInt("kind"));
        if (rs.getInt("status") == 1) {
          organization.setStatusDetail(MCUtil.getBundleString("message.organization.status1"));
        } else if (rs.getInt("status") == 2) {
          organization.setStatusDetail(MCUtil.getBundleString("message.organization.status2"));
        }
        if (rs.getInt("kind") == 1) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind1"));
        } else if (rs.getInt("kind") == 2) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind2"));
        } else if (rs.getInt("kind") == 3) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind3"));
        } else if (rs.getInt("kind") == 4) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind4"));
        }
        organizationList.add(organization);
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
    return organizationList;
  }
  
  public ArrayList getOrgByKinds(String kinds)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From organization Where kind in (" + kinds + ") Order by name";
    
    ArrayList organizationList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      OrganizationBean organization = null;
      while (rs.next())
      {
        organization = new OrganizationBean();
        organization.setOrgId(rs.getInt("org_id"));
        organization.setName(rs.getString("name"));
        organization.setAbbreviate(rs.getString("abbreviate"));
        organization.setParentId(rs.getInt("parent_id"));
        organization.setStatus(rs.getInt("status"));
        organization.setKind(rs.getInt("kind"));
        if (rs.getInt("status") == 1) {
          organization.setStatusDetail(MCUtil.getBundleString("message.organization.status1"));
        } else if (rs.getInt("status") == 2) {
          organization.setStatusDetail(MCUtil.getBundleString("message.organization.status2"));
        }
        if (rs.getInt("kind") == 1) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind1"));
        } else if (rs.getInt("kind") == 2) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind2"));
        } else if (rs.getInt("kind") == 3) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind3"));
        } else if (rs.getInt("kind") == 4) {
          organization.setKindDetail(MCUtil.getBundleString("message.organization.kind4"));
        }
        organizationList.add(organization);
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
    return organizationList;
  }
  
  public String getnestedChildOfOrg(String orgId)
    throws Exception
  {
    ResultSet rs = null;
    String result = "0";
    try
    {
      if (!GenericValidator.isBlankOrNull(orgId))
      {
        String sql = "select org_id from organization where parent_id in (" + orgId + ")";
        rs = DBUtil.executeQuery(sql);
        while (rs.next()) {
          result = result + "," + rs.getString("org_id");
        }
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
  
  public String getnestedParentOfOrg(String orgId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select p.org_id from organization as c, organization as p where c.parent_id=p.org_id and c.org_id in (" + orgId + ")";
    
    String result = "0";
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        result = result + "," + rs.getString("org_id");
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
    if (!result.equals("0")) {
      result = result.substring(2);
    }
    return result;
  }
  
  public OrganizationBean getParentOrganization(int orgId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "Select p.* From organization as c, organization as p Where c.parent_id=p.org_id and c.org_id=" + orgId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        OrganizationBean organization = new OrganizationBean();
        organization = new OrganizationBean();
        organization.setOrgId(rs.getInt("org_id"));
        organization.setName(rs.getString("name"));
        organization.setAbbreviate(rs.getString("abbreviate"));
        organization.setParentId(rs.getInt("parent_id"));
        organization.setStatus(rs.getInt("status"));
        organization.setKind(rs.getInt("kind"));
        
        return organization;
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
  
  public String getSiblingOrganization(String orgId)
    throws Exception
  {
    ResultSet rs = null;
    String sql = "SELECT GROUP_CONCAT(s.org_id) AS org_id FROM organization AS s, organization AS o WHERE s.parent_id=o.parent_id AND o.org_id<>s.org_id AND o.org_id in (" + orgId + ") GROUP BY s.org_id";
    String result = "0";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next()) {
        return rs.getString("org_id");
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
  
  public ArrayList getProjectListInProcessing()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from organization Where kind = " + OrganizationBean.KIND_PROJECT + " and status=1 order by name";
    
    ArrayList organizationList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      LabelValueBean organization = null;
      while (rs.next())
      {
        organization = new LabelValueBean();
        organization.setLabel(rs.getString("name"));
        organization.setValue(rs.getString("org_id"));
        organizationList.add(organization);
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
    return organizationList;
  }
}
