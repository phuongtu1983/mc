package com.venus.mc.dao;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.auth.OnlineUserImpl;
import com.venus.mc.bean.EmployeeBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.util.MCUtil;
import com.venus.mc.util.SQLSearchExpressionUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;

public class EmployeeDAO
  extends BasicDAO
{
  public static final int CB1 = 1;
  public static final int CB2 = 2;
  public static final int CB3 = 3;
  public static final int CB4 = 4;
  public static final int NV = 0;
  
  public EmployeeBean getMemberObjDefault()
  {
    EmployeeBean employee = new EmployeeBean();
    employee.setFullname("");
    employee.setNickname("");
    employee.setPassword("");
    employee.setEmail("");
    employee.setOrgId(0);
    employee.setPosId(0);
    
    employee.setStatus(1);
    return employee;
  }
  
  public OnlineUser login(String username, String password)
    throws Exception
  {
    OnlineUserImpl user = null;
    ResultSet rs = null;
    
    String sql = "select m.emp_id,m.fullname,m.nickname,m.password,m.email,m.org_id,o.name as org_name,m.pos_id,m.status,m.last_ip,m.last_logon_date  from employee m left join organization o on o.org_id=m.org_id  where nickname='" + username + "'" + " and password='" + password + "'" + " and m.status>0";
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next())
      {
        user = new OnlineUserImpl();
        user.setID(rs.getInt("emp_id"));
        user.setFullName(rs.getString("fullname"));
        user.setName(rs.getString("nickname"));
        user.setOrganizationID(rs.getInt("org_id"));
        user.setOrganizationName(rs.getString("org_name"));
        user.setEmail(rs.getString("email"));
        user.setLastLogonIP(rs.getString("last_ip"));
        user.setLastLogonTimestamp(rs.getTimestamp("last_logon_date"));
      }
    }
    catch (SQLException sqle)
    {
      user = null;
      throw new Exception(sqle.getMessage());
    }
    catch (Exception ex)
    {
      user = null;
      throw new Exception(ex.getMessage());
    }
    finally
    {
      if (rs != null) {
        DBUtil.closeConnection(rs);
      }
    }
    return user;
  }
  
  public void updateLastLogon(int mem_id, String mem_last_ip)
    throws Exception
  {
    try
    {
      String sql = "update employee set last_logon_date=sysdate()";
      if (!StringUtil.isBlankOrNull(mem_last_ip)) {
        sql = sql + ",last_ip='" + mem_last_ip + "'";
      }
      sql = sql + " where emp_id='" + mem_id + "'";
      
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
  
  public ArrayList getEmployees()
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select e.*, p.name as pos, o.name as org from employee as e, organization as o, position as p where e.emp_id > 1 and e.org_id = o.org_id and e.pos_id = p.pos_id and ( ";
    if ((getRequestOrg().equals("")) && (getRequestEmp() == 0))
    {
      sql = sql + " 1 ";
    }
    else
    {
      sql = sql + " 0 ";
      if (!getRequestOrg().equals("")) {
        sql = sql + " or e.org_id in(" + getRequestOrg() + ")";
      } else {
        sql = sql + " or 0 ";
      }
      if (getRequestEmp() > 0) {
        sql = sql + " or e.emp_id=" + getRequestEmp();
      } else {
        sql = sql + " or 0 ";
      }
    }
    sql = sql + ") order by e.fullname";
    ArrayList empList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      EmployeeBean employee = null;
      String org = "";String email = "";
      while (rs.next())
      {
        employee = new EmployeeBean();
        org = "";
        email = "";
        employee.setEmpId(rs.getInt("emp_id"));
        employee.setOrgId(rs.getInt("org_id"));
        employee.setPosId(rs.getInt("pos_id"));
        
        employee.setFullname(rs.getString("fullname"));
        employee.setNickname(rs.getString("nickname"));
        employee.setPassword(rs.getString("password"));
        employee.setEmail(rs.getString("email"));
        employee.setOfficePhone(rs.getString("office_phone"));
        employee.setHandPhone(rs.getString("hand_phone"));
        employee.setHomePhone(rs.getString("home_phone"));
        employee.setStatus(rs.getInt("status"));
        employee.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        employee.setModifiedDate(DateUtil.formatDate(rs.getDate("modified_date"), "dd/MM/yyyy"));
        employee.setLastLogonDate(DateUtil.formatDate(rs.getDate("last_logon_date"), "dd/MM/yyyy"));
        employee.setFirstIp(rs.getString("first_ip"));
        employee.setLastIp(rs.getString("last_ip"));
        employee.setOrgName(rs.getString("org"));
        employee.setPosName(rs.getString("pos"));
        if (rs.getInt("status") == 1) {
          employee.setStatusDetail(MCUtil.getBundleString("message.employee.status1"));
        } else if (rs.getInt("status") == 2) {
          employee.setStatusDetail(MCUtil.getBundleString("message.employee.status2"));
        } else if (rs.getInt("status") == 3) {
          employee.setStatusDetail(MCUtil.getBundleString("message.employee.status3"));
        }
        empList.add(employee);
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
  
  public EmployeeBean getEmployee(int empId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select employee.*, position.name, organization.name from (employee inner join organization on employee.org_id = organization.org_id) inner join position on employee.pos_id = position.pos_id Where emp_id=" + empId;
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        EmployeeBean employee = new EmployeeBean();
        employee.setEmpId(rs.getInt("emp_id"));
        employee.setOrgId(rs.getInt("org_id"));
        employee.setPosId(rs.getInt("pos_id"));
        employee.setCode(rs.getString("code"));
        employee.setFullname(rs.getString("fullname"));
        employee.setNickname(rs.getString("nickname"));
        employee.setPassword(rs.getString("password"));
        employee.setEmail(rs.getString("email"));
        employee.setOfficePhone(rs.getString("office_phone"));
        employee.setHandPhone(rs.getString("hand_phone"));
        employee.setHomePhone(rs.getString("home_phone"));
        employee.setStatus(rs.getInt("status"));
        employee.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        employee.setModifiedDate(DateUtil.formatDate(rs.getDate("modified_date"), "dd/MM/yyyy"));
        employee.setLastLogonDate(DateUtil.formatDate(rs.getDate("last_logon_date"), "dd/MM/yyyy"));
        employee.setFirstIp(rs.getString("first_ip"));
        employee.setLastIp(rs.getString("last_ip"));
        employee.setOrgName(rs.getString("organization.name"));
        employee.setPosName(rs.getString("position.name"));
        
        return employee;
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
  
  public EmployeeBean getEmployeeByName(String fullname)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select emp_id from employee where fullname='" + fullname + "'";
    try
    {
      rs = DBUtil.executeQuery(sql);
      if (rs.next())
      {
        EmployeeBean employee = new EmployeeBean();
        employee.setEmpId(rs.getInt("emp_id"));
        
        return employee;
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
  
  public void insertEmployee(EmployeeBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "";
      
      sql = "Insert Into employee(org_id, pos_id, code, fullname, nickname, password, email, office_phone, hand_phone, home_phone, status, created_date, modified_date, last_logon_date, first_ip, last_ip) Values (" + bean.getOrgId() + "," + bean.getPosId() + ",'" + bean.getCode() + "','" + bean.getFullname() + "','" + bean.getNickname() + "','" + bean.getPassword() + "','" + bean.getEmail() + "','" + bean.getOfficePhone() + "','" + bean.getHandPhone() + "','" + bean.getHomePhone() + "'," + bean.getStatus() + ",sysdate(),sysdate(),sysdate(),'" + bean.getFirstIp() + "','" + bean.getLastIp() + "')";
      
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
  
  public void updateEmployee(EmployeeBean bean)
    throws Exception
  {
    if (bean == null) {
      return;
    }
    try
    {
      String sql = "Update employee Set  org_id=" + bean.getOrgId() + ", pos_id=" + bean.getPosId() + ", code='" + bean.getCode() + "'" + ", fullname='" + bean.getFullname() + "'" + ", nickname='" + bean.getNickname() + "'" + ", email='" + bean.getEmail() + "'" + ", office_phone='" + bean.getOfficePhone() + "'" + ", hand_phone='" + bean.getHandPhone() + "'" + ", home_phone='" + bean.getHomePhone() + "'" + ", status=" + bean.getStatus() + ", modified_date=sysdate()" + " Where emp_id=" + bean.getEmpId();
      
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
  
  public int deleteEmployee(int empId)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "Delete From employee where emp_id=" + empId;
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
  
  public ArrayList searchSimpleEmployee(int fieldid, String strFieldvalue)
    throws Exception
  {
    String strFieldname = "";
    switch (fieldid)
    {
    case 1: 
      strFieldname = "fullname";
      break;
    case 2: 
      strFieldname = "organization.name";
    }
    ResultSet rs = null;
    
    String sql = "select employee.*, position.name, organization.name from employee left join organization on employee.org_id = organization.org_id left join position on employee.pos_id = position.pos_id Where 1 ";
    if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
      sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
    }
    sql = sql + " Order by emp_id DESC";
    
    ArrayList employeeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      EmployeeBean employee = null;
      while (rs.next())
      {
        employee = new EmployeeBean();
        employee.setEmpId(rs.getInt("emp_id"));
        employee.setOrgId(rs.getInt("org_id"));
        employee.setPosId(rs.getInt("pos_id"));
        employee.setFullname(rs.getString("fullname"));
        employee.setNickname(rs.getString("nickname"));
        employee.setPassword(rs.getString("password"));
        employee.setEmail(rs.getString("email"));
        employee.setOfficePhone(rs.getString("office_phone"));
        employee.setHandPhone(rs.getString("hand_phone"));
        employee.setHomePhone(rs.getString("home_phone"));
        employee.setStatus(rs.getInt("status"));
        employee.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        employee.setModifiedDate(DateUtil.formatDate(rs.getDate("modified_date"), "dd/MM/yyyy"));
        employee.setLastLogonDate(DateUtil.formatDate(rs.getDate("last_logon_date"), "dd/MM/yyyy"));
        employee.setFirstIp(rs.getString("first_ip"));
        employee.setLastIp(rs.getString("last_ip"));
        employee.setOrgName(rs.getString("organization.name"));
        employee.setPosName(rs.getString("position.name"));
        if (rs.getInt("status") == 1) {
          employee.setStatusDetail(MCUtil.getBundleString("message.employee.status1"));
        } else if (rs.getInt("status") == 2) {
          employee.setStatusDetail(MCUtil.getBundleString("message.employee.status2"));
        } else if (rs.getInt("status") == 3) {
          employee.setStatusDetail(MCUtil.getBundleString("message.employee.status3"));
        }
        employeeList.add(employee);
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
    return employeeList;
  }
  
  public ArrayList searchAdvEmployee(EmployeeBean bean)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select employee.*, position.name, organization.name from (employee inner join organization on employee.org_id = organization.org_id) inner join position on employee.pos_id = position.pos_id Where 1";
    if (bean.getEmpId() != 0) {
      sql = sql + " AND emp_id =" + bean.getEmpId();
    }
    if (bean.getOrgId() != 0) {
      sql = sql + " AND org_id =" + bean.getOrgId();
    }
    if (bean.getPosId() != 0) {
      sql = sql + " AND pos_id =" + bean.getPosId();
    }
    if (!StringUtil.isBlankOrNull(bean.getFullname())) {
      sql = sql + " AND fullname LIKE '%" + bean.getFullname() + "%'";
    }
    if (!StringUtil.isBlankOrNull(bean.getNickname())) {
      sql = sql + " AND nickname = '" + bean.getNickname() + "'";
    }
    if (!StringUtil.isBlankOrNull(bean.getPassword())) {
      sql = sql + " AND password = '" + bean.getPassword() + "'";
    }
    if (!StringUtil.isBlankOrNull(bean.getEmail())) {
      sql = sql + " AND email = '" + bean.getEmail() + "'";
    }
    if (!StringUtil.isBlankOrNull(bean.getOfficePhone())) {
      sql = sql + " AND office_phone = '" + bean.getOfficePhone() + "'";
    }
    if (!StringUtil.isBlankOrNull(bean.getHandPhone())) {
      sql = sql + " AND hand_phone = '" + bean.getHandPhone() + "'";
    }
    if (!StringUtil.isBlankOrNull(bean.getHomePhone())) {
      sql = sql + " AND home_phone = '" + bean.getHomePhone() + "'";
    }
    if (bean.getStatus() != 0) {
      sql = sql + " AND status =" + bean.getStatus();
    }
    if (bean.getCreatedDate() != null) {
      sql = sql + " AND created_date =" + bean.getCreatedDate();
    }
    if (bean.getModifiedDate() != null) {
      sql = sql + " AND modified_date =" + bean.getModifiedDate();
    }
    if (bean.getLastLogonDate() != null) {
      sql = sql + " AND last_logon_date =" + bean.getLastLogonDate();
    }
    if (!StringUtil.isBlankOrNull(bean.getFirstIp())) {
      sql = sql + " AND first_ip LIKE '%" + bean.getFirstIp() + "%'";
    }
    if (!StringUtil.isBlankOrNull(bean.getLastIp())) {
      sql = sql + " AND last_ip LIKE '%" + bean.getLastIp() + "%'";
    }
    sql = sql + " Order by emp_id DESC";
    
    ArrayList employeeList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      EmployeeBean employee = null;
      while (rs.next())
      {
        employee = new EmployeeBean();
        employee.setEmpId(rs.getInt("emp_id"));
        employee.setOrgId(rs.getInt("org_id"));
        employee.setPosId(rs.getInt("pos_id"));
        employee.setFullname(rs.getString("fullname"));
        employee.setNickname(rs.getString("nickname"));
        employee.setPassword(rs.getString("password"));
        employee.setEmail(rs.getString("email"));
        employee.setOfficePhone(rs.getString("office_phone"));
        employee.setHandPhone(rs.getString("hand_phone"));
        employee.setHomePhone(rs.getString("home_phone"));
        employee.setStatus(rs.getInt("status"));
        employee.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        employee.setModifiedDate(DateUtil.formatDate(rs.getDate("modified_date"), "dd/MM/yyyy"));
        employee.setLastLogonDate(DateUtil.formatDate(rs.getDate("last_logon_date"), "dd/MM/yyyy"));
        employee.setFirstIp(rs.getString("first_ip"));
        employee.setLastIp(rs.getString("last_ip"));
        employee.setOrgName(rs.getString("organization.name"));
        employee.setPosName(rs.getString("position.name"));
        if (rs.getInt("status") == 1) {
          employee.setStatusDetail(MCUtil.getBundleString("message.employee.status1"));
        } else if (rs.getInt("status") == 2) {
          employee.setStatusDetail(MCUtil.getBundleString("message.employee.status2"));
        } else if (rs.getInt("status") == 3) {
          employee.setStatusDetail(MCUtil.getBundleString("message.employee.status3"));
        }
        employeeList.add(employee);
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
    return employeeList;
  }
  
  public ArrayList getEmployeeListByOrg(int orgId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select e.*,o.name from employee as e left join organization o on o.org_id=e.org_id where e.org_id = " + orgId + " or o.parent_id=" + orgId;
    
    ArrayList empList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      LabelValueBean employee = null;
      while (rs.next())
      {
        employee = new LabelValueBean();
        employee.setLabel(rs.getString("e.fullname"));
        employee.setValue(rs.getString("e.emp_id"));
        empList.add(employee);
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
  
  public ArrayList getEmployeeByOrgIds(String orgIds)
    throws Exception
  {
    ResultSet rs = null;
    if (GenericValidator.isBlankOrNull(orgIds)) {
      orgIds = "0";
    }
    String sql = "select employee.*, position.name, organization.name from (employee inner join organization on employee.org_id = organization.org_id) inner join position on employee.pos_id = position.pos_id  where employee.org_id in (" + orgIds + ")";
    
    ArrayList empList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      EmployeeBean employee = null;
      while (rs.next())
      {
        employee = new EmployeeBean();
        employee.setEmpId(rs.getInt("emp_id"));
        employee.setOrgId(rs.getInt("org_id"));
        employee.setPosId(rs.getInt("pos_id"));
        employee.setFullname(rs.getString("fullname"));
        employee.setNickname(rs.getString("nickname"));
        employee.setPassword(rs.getString("password"));
        employee.setEmail(rs.getString("email"));
        employee.setOfficePhone(rs.getString("office_phone"));
        employee.setHandPhone(rs.getString("hand_phone"));
        employee.setHomePhone(rs.getString("home_phone"));
        employee.setStatus(rs.getInt("status"));
        employee.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        employee.setModifiedDate(DateUtil.formatDate(rs.getDate("modified_date"), "dd/MM/yyyy"));
        employee.setLastLogonDate(DateUtil.formatDate(rs.getDate("last_logon_date"), "dd/MM/yyyy"));
        employee.setFirstIp(rs.getString("first_ip"));
        employee.setLastIp(rs.getString("last_ip"));
        employee.setOrgName(rs.getString("organization.name"));
        employee.setPosName(rs.getString("position.name"));
        if (rs.getInt("status") == 1) {
          employee.setStatusDetail(MCUtil.getBundleString("message.employee.status1"));
        } else if (rs.getInt("status") == 2) {
          employee.setStatusDetail(MCUtil.getBundleString("message.employee.status2"));
        } else if (rs.getInt("status") == 3) {
          employee.setStatusDetail(MCUtil.getBundleString("message.employee.status3"));
        }
        empList.add(employee);
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
  
  public String getEmailOfEmployees(String empIds)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select email from employee where emp_id in (" + empIds + ")";
    
    String emails = "";
    try
    {
      rs = DBUtil.executeQuery(sql);
      while (rs.next())
      {
        emails = emails + rs.getString("email");
        if (!rs.isLast()) {
          emails = emails + ",";
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
    return emails;
  }
  
  public ArrayList getEmployeeOfOrg(int orgId)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select e.*,o.name from employee as e left join organization o on o.org_id=e.org_id where e.org_id = " + orgId + " order by e.fullname";
    
    ArrayList empList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      EmployeeBean employee = null;
      while (rs.next())
      {
        employee = new EmployeeBean();
        employee.setFullname(rs.getString("e.fullname"));
        employee.setEmpId(rs.getInt("e.emp_id"));
        employee.setEmail(rs.getString("email"));
        empList.add(employee);
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
  
  public Object isExistMember(String userName, String email)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select m.emp_id, m.fullname, m.password, o.name  from employee m left join organization o on o.org_id = m.org_id where m.nickname='" + userName + "' and m.email='" + email + "'";
    try
    {
      rs = DBUtil.executeQuery(sql);
      EmployeeBean employee = null;
      if (rs.next())
      {
        employee = new EmployeeBean();
        employee.setEmpId(rs.getInt("emp_id"));
        employee.setFullname(rs.getString("fullname"));
        employee.setPassword(rs.getString("password"));
        employee.setOrgName(rs.getString("name"));
        
        return employee;
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
  
  public int changePassword(String username, String password, String newPassword)
    throws Exception
  {
    int result = 0;
    try
    {
      String sql = "update employee set password='" + newPassword + "'" + " where nickname='" + username + "'" + " and password='" + password + "'";
      
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
  
  public ArrayList getEmployeesByNames(String names)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select e.*, p.name as pos, o.name as org from employee as e, organization as o, position as p where e.emp_id > 1 and e.org_id = o.org_id and e.pos_id = p.pos_id and ( ";
    
    sql = sql + " 1 ";
    sql = sql + ")";
    if (!GenericValidator.isBlankOrNull(names)) {
      sql = sql + " and ((" + SQLSearchExpressionUtil.excuteExpression(names, "e.fullname") + ") or (" + SQLSearchExpressionUtil.excuteExpression(names, "o.name") + ") or (" + SQLSearchExpressionUtil.excuteExpression(names, "e.email") + "))";
    }
    sql = sql + " order by e.fullname";
    
    ArrayList empList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      EmployeeBean employee = null;
      String org = "";String email = "";
      while (rs.next())
      {
        employee = new EmployeeBean();
        org = "";
        email = "";
        employee.setEmpId(rs.getInt("emp_id"));
        employee.setOrgId(rs.getInt("org_id"));
        employee.setPosId(rs.getInt("pos_id"));
        
        employee.setFullname(rs.getString("fullname"));
        employee.setNickname(rs.getString("nickname"));
        employee.setPassword(rs.getString("password"));
        employee.setEmail(rs.getString("email"));
        employee.setOfficePhone(rs.getString("office_phone"));
        employee.setHandPhone(rs.getString("hand_phone"));
        employee.setHomePhone(rs.getString("home_phone"));
        employee.setStatus(rs.getInt("status"));
        employee.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        employee.setModifiedDate(DateUtil.formatDate(rs.getDate("modified_date"), "dd/MM/yyyy"));
        employee.setLastLogonDate(DateUtil.formatDate(rs.getDate("last_logon_date"), "dd/MM/yyyy"));
        employee.setFirstIp(rs.getString("first_ip"));
        employee.setLastIp(rs.getString("last_ip"));
        employee.setOrgName(rs.getString("org"));
        employee.setPosName(rs.getString("pos"));
        if (rs.getInt("status") == 1) {
          employee.setStatusDetail(MCUtil.getBundleString("message.employee.status1"));
        } else if (rs.getInt("status") == 2) {
          employee.setStatusDetail(MCUtil.getBundleString("message.employee.status2"));
        } else if (rs.getInt("status") == 3) {
          employee.setStatusDetail(MCUtil.getBundleString("message.employee.status3"));
        }
        empList.add(employee);
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
  
  public ArrayList getEmployeesHasPermission(String permissions)
    throws Exception
  {
    ResultSet rs = null;
    
    String sql = "select e.*, p.name as pos, o.name as org from employee as e, organization as o, position as p where e.emp_id > 1 and e.org_id = o.org_id and e.pos_id = p.pos_id and ( ";
    if ((getRequestOrg().equals("")) && (getRequestEmp() == 0))
    {
      sql = sql + " 1 ";
    }
    else
    {
      sql = sql + " 0 ";
      if (!getRequestOrg().equals("")) {
        sql = sql + " or e.org_id in(" + getRequestOrg() + ")";
      } else {
        sql = sql + " or 0 ";
      }
      if (getRequestEmp() > 0) {
        sql = sql + " or e.emp_id=" + getRequestEmp();
      } else {
        sql = sql + " or 0 ";
      }
    }
    sql = sql + ") AND EXISTS (SELECT p.per_id FROM permission AS p, permission_detail AS pdet WHERE p.per_id=pdet.per_id AND pdet.FUNCTION=1 AND ( 0";
    String[] ps = permissions.split(",");
    for (int i = 0; i < ps.length; i++) {
      sql = sql + " or CONCAT(',',pdet.permit,',') LIKE '%," + ps[i] + ",%'";
    }
    sql = sql + ") AND CONCAT(',',p.employees,',') LIKE CONCAT('%,', e.emp_id,',%')";
    sql = sql + ") order by e.fullname";
    ArrayList empList = new ArrayList();
    try
    {
      rs = DBUtil.executeQuery(sql);
      EmployeeBean employee = null;
      String org = "";String email = "";
      while (rs.next())
      {
        employee = new EmployeeBean();
        org = "";
        email = "";
        employee.setEmpId(rs.getInt("emp_id"));
        employee.setOrgId(rs.getInt("org_id"));
        employee.setPosId(rs.getInt("pos_id"));
        employee.setFullname(rs.getString("fullname"));
        employee.setNickname(rs.getString("nickname"));
        employee.setPassword(rs.getString("password"));
        employee.setEmail(rs.getString("email"));
        employee.setOfficePhone(rs.getString("office_phone"));
        employee.setHandPhone(rs.getString("hand_phone"));
        employee.setHomePhone(rs.getString("home_phone"));
        employee.setStatus(rs.getInt("status"));
        employee.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        employee.setModifiedDate(DateUtil.formatDate(rs.getDate("modified_date"), "dd/MM/yyyy"));
        employee.setLastLogonDate(DateUtil.formatDate(rs.getDate("last_logon_date"), "dd/MM/yyyy"));
        employee.setFirstIp(rs.getString("first_ip"));
        employee.setLastIp(rs.getString("last_ip"));
        employee.setOrgName(rs.getString("org"));
        employee.setPosName(rs.getString("pos"));
        if (rs.getInt("status") == 1) {
          employee.setStatusDetail(MCUtil.getBundleString("message.employee.status1"));
        } else if (rs.getInt("status") == 2) {
          employee.setStatusDetail(MCUtil.getBundleString("message.employee.status2"));
        } else if (rs.getInt("status") == 3) {
          employee.setStatusDetail(MCUtil.getBundleString("message.employee.status3"));
        }
        empList.add(employee);
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
}
