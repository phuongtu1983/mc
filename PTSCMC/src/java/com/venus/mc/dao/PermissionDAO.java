package com.venus.mc.dao;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.bean.PermissionBean;
import com.venus.mc.bean.PermissionDetailBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.permission.ApplicationPermissionBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;

public class PermissionDAO
  extends BasicDAO
{
  public ArrayList getPermissions()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select * from permission ";
    
    ArrayList permissionList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      PermissionBean permission = null;
      while (rs.next())
      {
        permission = new PermissionBean();
        permission.setPerId(rs.getInt("per_id"));
        permission.setName(rs.getString("name"));
        permissionList.add(permission);
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
    return permissionList;
  }
  
  public PermissionBean getPermission(int perId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * from permission Where per_id=" + perId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        PermissionBean permission = new PermissionBean();
        permission.setPerId(rs.getInt("per_id"));
        permission.setName(rs.getString("name"));
        permission.setEmployees(rs.getString("employees"));
        permission.setOrganizations(rs.getString("organizations"));
        
        return permission;
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
  
  public ArrayList getPermissionOrg(int perId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select organizations from permission where per_id=" + perId;
    
    ArrayList orgList = new ArrayList();
    try
    {
      String organizations = "";
      
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        organizations = rs.getString("organizations");
      }
      DBUtil.closeConnection(rs);
      if (!GenericValidator.isBlankOrNull(organizations))
      {
        sql = "select org_id, name from organization where org_id in (" + organizations + ")";
        rs = DBUtil.executeQuery(sql);
        OrganizationBean org = null;
        while (rs.next())
        {
          org = new OrganizationBean();
          org.setOrgId(rs.getInt("org_id"));
          org.setName(rs.getString("name"));
          orgList.add(org);
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
    return orgList;
  }
  
  public ArrayList getPermissionEmp(int perId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select employees from permission where per_id=" + perId;
    
    ArrayList empList = new ArrayList();
    try
    {
      String employees = "";
      
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        employees = rs.getString("employees");
      }
      DBUtil.closeConnection(rs);
      if (!GenericValidator.isBlankOrNull(employees))
      {
        sql = "select emp_id, fullname, email, o.name FROM employee AS e LEFT JOIN organization AS o ON o.org_id = e.org_id where emp_id in (" + employees + ")";
        rs = DBUtil.executeQuery(sql);
        EmployeeBean emp = null;
        while (rs.next())
        {
          emp = new EmployeeBean();
          emp.setEmpId(rs.getInt("emp_id"));
          emp.setFullname(rs.getString("fullname"));
          emp.setEmail(rs.getString("email"));
          emp.setOrgName(rs.getString("o.name"));
          empList.add(emp);
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
    return empList;
  }
  
  public PermissionBean getPermissionByNumber(String name)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select per_id From permission Where name='" + name + "'";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        PermissionBean permission = new PermissionBean();
        permission.setPerId(rs.getInt("per_id"));
        
        return permission;
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
  
  public int insertPermission(PermissionBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      sql = "Insert Into permission (name, employees, organizations) Values ('" + bean.getName() + "','" + bean.getEmployees() + "','" + bean.getOrganizations() + "')";
      
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
  
  public void updatePermission(PermissionBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "Update permission Set  name='" + bean.getName() + "'" + ", employees='" + bean.getEmployees() + "'" + ", organizations='" + bean.getOrganizations() + "'" + " Where per_id=" + bean.getPerId();
      
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
  
  public ArrayList getPermissionDetails(int perId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "Select * From permission_detail Where per_id=" + perId;
    
    ArrayList detailList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      PermissionDetailBean detail = null;
      while (rs.next())
      {
        detail = new PermissionDetailBean();
        detail.setDetId(rs.getInt("det_id"));
        detail.setPerId(rs.getInt("per_id"));
        detail.setFunction(rs.getString("function"));
        detail.setPermit(rs.getString("permit"));
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
  
  public int insertPermissionDetail(PermissionDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return 0;
    }
    int result = 0;
    try
    {
      String sql = "";
      sql = "Insert Into permission_detail (per_id, function, permit) Values (" + bean.getPerId() + "," + bean.getFunction() + ",'" + bean.getPermit() + "')";
      
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
  
  public void updatePermissionDetail(PermissionDetailBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "Update permission_detail Set permit='" + bean.getPermit() + "'" + " Where per_id=" + bean.getPerId() + " and function=" + bean.getFunction();
      
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
  
  public int deletePermission(String perId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "Delete From permission_detail Where per_id=" + perId;
      DBUtil.executeUpdate(sql);
      sql = "Delete From permission Where per_id=" + perId;
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
  
  public ArrayList<ApplicationPermissionBean> getPermissionsOfEmployee(int empId, int orgId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select p.*, det.function, det.permit from permission_detail as det, permission as p where det.per_id=p.per_id and (0";
    if (empId > 0) {
      sql = sql + " or employees like '" + empId + "' or employees like '" + empId + ",%' or employees like '%," + empId + ",%' or employees like '%," + empId + "'";
    }
    if (orgId > 0) {
      sql = sql + " or organizations like '" + orgId + "' or organizations like '" + orgId + ",%' or organizations like '%," + orgId + ",%' or organizations like '%," + orgId + "'";
    }
    sql = sql + ")";
    
    ArrayList<ApplicationPermissionBean> permissionList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      ApplicationPermissionBean bean = null;
      while (rs.next())
      {
        bean = new ApplicationPermissionBean();
        bean.setPerId(rs.getInt("per_id"));
        bean.setName(rs.getString("name"));
        bean.setEmployees(rs.getString("employees"));
        bean.setOrganizations(rs.getString("organizations"));
        bean.setFunction(rs.getString("function"));
        bean.setPermit(rs.getString("permit"));
        permissionList.add(bean);
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
    return permissionList;
  }
  
  public String getEmployeesHasPermission(String per, String func)
    throws Exception
  {
    ResultSet rs = null;
    
    String result = "0";
    String sql = "SELECT p.employees FROM permission_detail AS det, permission AS p WHERE p.per_id=det.per_id AND det.FUNCTION IN (" + func + ")";
    String[] p = per.split(",");
    for (int i = 0; i < p.length; i++) {
      sql = sql + "AND CONCAT(',',det.permit,',') LIKE '%," + p[i] + ",%'";
    }
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next()) {
        if (!GenericValidator.isBlankOrNull(rs.getString("employees"))) {
          result = result + "," + rs.getString("employees");
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
  
  public String getEmployeesHasPermissionForStore(String per, String func)
    throws Exception
  {
    ResultSet rs = null;
    
    String result = "0";
    String sql = "SELECT p.employees FROM permission_detail AS det, permission AS p WHERE p.per_id=det.per_id AND det.FUNCTION IN (" + func + ")";
    
    String[] p = per.split(",");
    for (int i = 0; i < p.length; i++) {
      sql = sql + "AND CONCAT(',',det.permit,',') LIKE '%," + p[i] + ",%'";
    }
    String[] employeeIds = null;
    if (!getRequestOrg().equals(""))
    {
      int orgId = NumberUtil.parseInt(getRequestOrg(), 0);
      EmployeeDAO empDAO = new EmployeeDAO();
      try
      {
        ArrayList empList = empDAO.getEmployeeListByOrg(orgId);
        LabelValueBean employee = null;
        String empIds = "";
        for (int i = 0; i < empList.size(); i++)
        {
          employee = (LabelValueBean)empList.get(i);
          empIds = empIds + "," + employee.getValue();
        }
        if (!GenericValidator.isBlankOrNull(empIds))
        {
          empIds = empIds.substring(1);
          employeeIds = empIds.split(",");
        }
      }
      catch (Exception localException1) {}
    }
    try
    {
      rs = DBUtil.executeQuery(sql);
      String employees = "";
      while (rs.next()) {
        if (employeeIds != null)
        {
          employees = "," + rs.getString("employees") + ",";
          for (int i = 0; i < employeeIds.length; i++) {
            if (employees.indexOf("," + employeeIds[i] + ",") > -1) {
              result = result + "," + employeeIds[i];
            }
          }
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
}
