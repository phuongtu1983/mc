package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.ProjectBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.util.MCUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectDAO
  extends BasicDAO
{
  public ArrayList getProjects()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From organization Where kind = " + OrganizationBean.KIND_PROJECT + " AND DATE(end_date) >= DATE(sysdate()) " + " Order by org_id ASC";
    
    ArrayList projectList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      ProjectBean project = null;
      while (rs.next())
      {
        project = new ProjectBean();
        project.setProId(rs.getInt("org_id"));
        project.setName(rs.getString("name"));
        project.setStartDate(DateUtil.formatDate(rs.getDate("start_date"), "dd/MM/yyyy"));
        project.setEndDate(DateUtil.formatDate(rs.getDate("end_date"), "dd/MM/yyyy"));
        project.setComments(StringUtil.getString(rs.getString("comments")));
        project.setInvestorsName(rs.getString("investors_name"));
        project.setStatus(rs.getInt("status"));
        if (rs.getInt("status") == 0) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status1"));
        } else if (rs.getInt("status") == 1) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status2"));
        } else if (rs.getInt("status") == 2) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status3"));
        }
        projectList.add(project);
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
    return projectList;
  }
  
  public ArrayList getProjectsForRFM()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT * FROM organization WHERE kind=" + OrganizationBean.KIND_PROJECT + " AND end_date >= NOW() ORDER BY org_id ASC";
    
    ArrayList projectList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      ProjectBean project = null;
      while (rs.next())
      {
        project = new ProjectBean();
        project.setProId(rs.getInt("org_id"));
        project.setName(rs.getString("name"));
        project.setStartDate(DateUtil.formatDate(rs.getDate("start_date"), "dd/MM/yyyy"));
        project.setEndDate(DateUtil.formatDate(rs.getDate("end_date"), "dd/MM/yyyy"));
        project.setComments(StringUtil.getString(rs.getString("comments")));
        project.setStatus(rs.getInt("status"));
        if (rs.getInt("status") == 0) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status1"));
        } else if (rs.getInt("status") == 1) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status2"));
        } else if (rs.getInt("status") == 2) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status3"));
        }
        projectList.add(project);
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
    return projectList;
  }
  
  public ArrayList getProjectsForDn2(int orgId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT DISTINCT o.* FROM used_material_import AS u LEFT JOIN store AS s ON s.sto_id = u.sto_id LEFT JOIN organization AS o ON o.org_id = s.org_id WHERE  s.used_org=" + orgId + "  AND o.name IS NOT NULL AND u.current_quantity > 0 ORDER BY o.org_id ASC";
    
    ArrayList projectList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      ProjectBean project = null;
      while (rs.next())
      {
        project = new ProjectBean();
        project.setProId(rs.getInt("org_id"));
        project.setName(rs.getString("name"));
        project.setStartDate(DateUtil.formatDate(rs.getDate("start_date"), "dd/MM/yyyy"));
        project.setEndDate(DateUtil.formatDate(rs.getDate("end_date"), "dd/MM/yyyy"));
        project.setComments(StringUtil.getString(rs.getString("comments")));
        project.setStatus(rs.getInt("status"));
        if (rs.getInt("status") == 0) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status1"));
        } else if (rs.getInt("status") == 1) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status2"));
        } else if (rs.getInt("status") == 2) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status3"));
        }
        projectList.add(project);
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
    return projectList;
  }
  
  public ArrayList getProjectsForDn4()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "SELECT DISTINCT o.org_id, o.name FROM material_store_request AS u LEFT JOIN store AS s ON u.sto_id = s.sto_id LEFT JOIN organization AS o ON o.org_id = s.org_id WHERE o.name IS NOT NULL AND u.actual_quantity>0 ORDER BY o.org_id ASC";
    
    ArrayList projectList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      ProjectBean project = null;
      while (rs.next())
      {
        project = new ProjectBean();
        project.setProId(rs.getInt("org_id"));
        project.setName(rs.getString("name"));
        projectList.add(project);
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
    return projectList;
  }
  
  public ArrayList getProjectsForStore()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From organization Where kind = " + OrganizationBean.KIND_PROJECT + " Order by org_id ASC";
    
    ArrayList projectList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      ProjectBean project = null;
      while (rs.next())
      {
        project = new ProjectBean();
        project.setProId(rs.getInt("org_id"));
        project.setName(rs.getString("name"));
        project.setStartDate(DateUtil.formatDate(rs.getDate("start_date"), "dd/MM/yyyy"));
        project.setEndDate(DateUtil.formatDate(rs.getDate("end_date"), "dd/MM/yyyy"));
        project.setComments(StringUtil.getString(rs.getString("comments")));
        project.setStatus(rs.getInt("status"));
        if (rs.getInt("status") == 0) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status1"));
        } else if (rs.getInt("status") == 1) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status2"));
        } else if (rs.getInt("status") == 2) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status3"));
        }
        projectList.add(project);
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
    return projectList;
  }
  
  public ProjectBean getProject(int proId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From organization Where org_id = " + proId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        ProjectBean project = new ProjectBean();
        project.setProId(rs.getInt("org_id"));
        project.setName(rs.getString("name"));
        project.setName_en(rs.getString("name_en"));
        project.setQc_name(rs.getString("qc_name"));
        project.setInvestorsName(rs.getString("investors_name"));
        project.setStartDate(DateUtil.formatDate(rs.getDate("start_date"), "dd/MM/yyyy"));
        project.setEndDate(DateUtil.formatDate(rs.getDate("end_date"), "dd/MM/yyyy"));
        project.setComments(StringUtil.getString(rs.getString("comments")));
        project.setStatus(rs.getInt("status"));
        if (rs.getInt("status") == 0) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status1"));
        } else if (rs.getInt("status") == 1) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status2"));
        } else if (rs.getInt("status") == 2) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status3"));
        }
        project.setAbbreviate(rs.getString("abbreviate"));
        
        return project;
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
  
  public ProjectBean getProjectByName(String name)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select org_id From organization Where name = '" + name + "'";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        ProjectBean project = new ProjectBean();
        project.setProId(rs.getInt("org_id"));
        
        return project;
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
  
  public int insertProject(ProjectBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    label260:
    try
    {
      String sql = "";
      
      String start_date = "null";
      if (!GenericValidator.isBlankOrNull(bean.getStartDate())) {
        start_date = "STR_TO_DATE('" + bean.getStartDate() + "','%d/%m/%Y')";
      }
      String end_date = "null";
      if (!GenericValidator.isBlankOrNull(bean.getEndDate())) {
        end_date = "STR_TO_DATE('" + bean.getEndDate() + "','%d/%m/%Y')";
      }
      sql = "Insert Into organization(name,name_en, parent_id, kind, start_date, end_date,qc_name,investors_name,comments,status,abbreviate) Values ('" + bean.getName() + "','" + bean.getName_en() + "',0,100," + start_date + "," + end_date + ",'" + bean.getQc_name() + "','" + bean.getInvestorsName() + "','" + bean.getComments() + "'" + "," + bean.getStatus() + ",'" + bean.getAbbreviate() + "')";
      
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
    finally
    {
      break label260;
    }
  }
  
  public void updateProject(ProjectBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "Update organization Set  name='" + bean.getName() + "'" + ", name_en='" + bean.getName_en() + "'" + ", start_date=STR_TO_DATE('" + bean.getStartDate() + "','%d/%m/%Y')" + ", end_date=STR_TO_DATE('" + bean.getEndDate() + "','%d/%m/%Y')" + ", qc_name='" + bean.getQc_name() + "'" + ", investors_name='" + bean.getInvestorsName() + "'" + ", comments='" + bean.getComments() + "'" + ", status=" + bean.getStatus() + ", abbreviate='" + bean.getAbbreviate() + "'" + " Where org_id=" + bean.getProId();
      
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
  
  public int deleteProject(int proId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "Delete From organization Where org_id = " + proId;
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
  
  public ArrayList searchSimpleProject(int fieldid, String strFieldvalue)
    throws Exception
  {
    String strFieldname = "";
    switch (fieldid)
    {
    case 1: 
      strFieldname = "name";
    }
    ResultSet rs = null;
    
    String sql = "Select * From organization Where kind = 100 ";
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
    }
    sql = sql + " Order by org_id DESC";
    
    ArrayList projectList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      ProjectBean project = null;
      while (rs.next())
      {
        project = new ProjectBean();
        project.setProId(rs.getInt("org_id"));
        project.setName(rs.getString("name"));
        project.setStartDate(DateUtil.formatDate(rs.getDate("start_date"), "dd/MM/yyyy"));
        project.setEndDate(DateUtil.formatDate(rs.getDate("end_date"), "dd/MM/yyyy"));
        project.setComments(StringUtil.getString(rs.getString("comments")));
        project.setInvestorsName(rs.getString("investors_name"));
        project.setStatus(rs.getInt("status"));
        if (rs.getInt("status") == 0) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status1"));
        } else if (rs.getInt("status") == 1) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status2"));
        } else if (rs.getInt("status") == 2) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status3"));
        }
        projectList.add(project);
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
    return projectList;
  }
  
  public ArrayList searchAdvProject(ProjectBean bean)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From organization Where kind = 100 ";
    if (!StringUtil.isBlankOrNull(bean.getName())) {
      sql = sql + " AND name LIKE '%" + bean.getName() + "%'";
    }
    if (!StringUtil.isBlankOrNull(bean.getStartDate())) {
      sql = sql + " AND start_date >= STR_TO_DATE('" + bean.getStartDate() + "','%d/%m/%Y')";
    }
    if (!StringUtil.isBlankOrNull(bean.getEndDate())) {
      sql = sql + " AND end_date <= STR_TO_DATE('" + bean.getEndDate() + "','%d/%m/%Y')";
    }
    if (bean.getStatus() != 0) {
      sql = sql + " AND status = " + bean.getStatus();
    }
    sql = sql + " Order by org_id DESC";
    
    ArrayList projectList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      ProjectBean project = null;
      while (rs.next())
      {
        project = new ProjectBean();
        project.setProId(rs.getInt("org_id"));
        project.setName(rs.getString("name"));
        project.setStartDate(DateUtil.formatDate(rs.getDate("start_date"), "dd/MM/yyyy"));
        project.setEndDate(DateUtil.formatDate(rs.getDate("end_date"), "dd/MM/yyyy"));
        project.setComments(StringUtil.getString(rs.getString("comments")));
        project.setInvestorsName(rs.getString("investors_name"));
        project.setStatus(rs.getInt("status"));
        if (rs.getInt("status") == 0) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status1"));
        } else if (rs.getInt("status") == 1) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status2"));
        } else if (rs.getInt("status") == 2) {
          project.setStatusDetail(MCUtil.getBundleString("message.project.status3"));
        }
        projectList.add(project);
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
    return projectList;
  }
}
