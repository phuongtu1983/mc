package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ReportDamageBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mai Vinh Loc
 */
public class ReportDamageDAO extends BasicDAO {

    public ReportDamageDAO() {
    }

    public ArrayList getReportDamages() throws Exception {

        ResultSet rs = null;

        String sql = "SELECT r.*,o.name,m.name_vn,m.name_en,t.used_code FROM report_damage AS r LEFT JOIN employee AS e ON e.emp_id = r.used_emp LEFT JOIN organization AS o ON o.org_id=e.org_id LEFT JOIN equipment AS t ON t.equ_id=r.equ_id LEFT JOIN material AS m ON m.mat_id=t.mat_id ORDER BY rd_id desc";

        ArrayList reportDamageList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            ReportDamageBean reportDamage = null;
            while (rs.next()) {
                reportDamage = new ReportDamageBean();
                reportDamage.setRdId(rs.getInt("rd_id"));
                reportDamage.setEquId(rs.getInt("equ_id"));
                reportDamage.setCreatedEmp(rs.getInt("created_emp"));
                reportDamage.setManagerEmp(rs.getInt("manager_emp"));
                reportDamage.setUsedEmp(rs.getInt("used_emp"));
                reportDamage.setManagerEquipmentEmp(rs.getInt("manager_equipment_emp"));
                reportDamage.setStatus(rs.getInt("status"));
                reportDamage.setRdNumber(rs.getString("rd_number"));
                reportDamage.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                reportDamage.setCreatedTime(rs.getString("created_time"));
                reportDamage.setCreatedLocation(rs.getString("created_location"));
                reportDamage.setComment(rs.getString("comment"));

                reportDamage.setEquName((StringUtil.decodeString(rs.getString("name_vn")) + " - " + StringUtil.decodeString(rs.getString("used_code"))));
                if (rs.getString("name") != null) {
                    reportDamage.setUsedOrg(rs.getString("name"));
                } else {
                    reportDamage.setUsedOrg("");
                }

                reportDamageList.add(reportDamage);
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return reportDamageList;
    }

    public ReportDamageBean getReportDamage(String reportDamageid) throws Exception {

        ResultSet rs = null;

        String sql = "select * from report_damage where rd_id=" + reportDamageid;


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ReportDamageBean reportDamage = new ReportDamageBean();
                reportDamage.setRdId(rs.getInt("rd_id"));
                reportDamage.setEquId(rs.getInt("equ_id"));
                reportDamage.setCreatedEmp(rs.getInt("created_emp"));
                reportDamage.setManagerEmp(rs.getInt("manager_emp"));
                reportDamage.setUsedEmp(rs.getInt("used_emp"));
                reportDamage.setManagerEquipmentEmp(rs.getInt("manager_equipment_emp"));
                reportDamage.setStatus(rs.getInt("status"));
                reportDamage.setRdNumber(rs.getString("rd_number"));
                reportDamage.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                reportDamage.setCreatedTime(rs.getString("created_time"));
                reportDamage.setCreatedLocation(rs.getString("created_location"));
                reportDamage.setComment(rs.getString("comment"));

                return reportDamage;
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return null;
    }

    public ReportDamageBean getReport(int reportDamageid) throws Exception {

        ResultSet rs = null;

        String sql = "SELECT rd.*, ee.fullname AS managerEmpName, e2.fullname AS managerEquipmentEmpName, e3.fullname AS usedEmpName, m.name_vn AS equName, e.used_code, e.manage_code FROM report_damage AS rd LEFT JOIN equipment AS e ON e.equ_id = rd.equ_id LEFT JOIN material AS m ON m.mat_id = e.mat_id LEFT JOIN employee AS ee ON ee.emp_id = rd.manager_emp LEFT JOIN employee AS e2 ON e2.emp_id = rd.manager_equipment_emp LEFT JOIN employee AS e3 ON e3.emp_id = rd.used_emp  WHERE rd_id=" + reportDamageid;


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ReportDamageBean reportDamage = new ReportDamageBean();
                reportDamage.setRdId(rs.getInt("rd_id"));
                reportDamage.setEquId(rs.getInt("equ_id"));
                reportDamage.setCreatedEmp(rs.getInt("created_emp"));
                reportDamage.setManagerEmp(rs.getInt("manager_emp"));
                reportDamage.setUsedEmp(rs.getInt("used_emp"));
                reportDamage.setManagerEquipmentEmp(rs.getInt("manager_equipment_emp"));
                reportDamage.setStatus(rs.getInt("status"));
                reportDamage.setRdNumber(rs.getString("rd_number"));
                reportDamage.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                reportDamage.setCreatedTime(rs.getString("created_time"));
                reportDamage.setCreatedLocation(rs.getString("created_location"));
                reportDamage.setManagerEmpName(rs.getString("managerEmpName"));
                reportDamage.setManagerEquipmentEmpName(rs.getString("managerEquipmentEmpName"));
                reportDamage.setUsedEmpName(rs.getString("usedEmpName"));
                reportDamage.setEquName(StringUtil.decodeString(rs.getString("equName")));
                reportDamage.setUsedCode(rs.getString("used_code"));
                reportDamage.setManageCode(rs.getString("manage_code"));
                reportDamage.setComment(rs.getString("comment"));

                return reportDamage;
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return null;
    }

    public ReportDamageBean getReportDamageMrir(int ematId) throws Exception {
        ResultSet rs = null;

        String sql = "select * From report_damage Where rd_id=" + ematId;


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ReportDamageBean reportDamage = new ReportDamageBean();
                reportDamage.setRdId(rs.getInt("rd_id"));
                reportDamage.setEquId(rs.getInt("equ_id"));
                reportDamage.setCreatedEmp(rs.getInt("created_emp"));
                reportDamage.setManagerEmp(rs.getInt("manager_emp"));
                reportDamage.setUsedEmp(rs.getInt("used_emp"));
                reportDamage.setManagerEquipmentEmp(rs.getInt("manager_equipment_emp"));
                reportDamage.setStatus(rs.getInt("status"));
                reportDamage.setRdNumber(rs.getString("rd_number"));
                reportDamage.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                reportDamage.setCreatedTime(rs.getString("created_time"));
                reportDamage.setCreatedLocation(rs.getString("created_location"));
                reportDamage.setComment(rs.getString("comment"));

                return reportDamage;
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return null;
    }

    public int insertReportDamage(ReportDamageBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "";
            sql = "insert into report_damage(equ_id,created_emp,manager_emp,used_emp,manager_equipment_emp,status,rd_number,created_date,created_time,created_location,comment)"
                    + " values (" + bean.getEquId() + "," + bean.getCreatedEmp() + "," + bean.getManagerEmp() + "," + bean.getUsedEmp() + "," + bean.getManagerEquipmentEmp() + "," + bean.getStatus() + ",'" + bean.getRdNumber() + "',SYSDATE(),'" + bean.getCreatedTime() + "','" + bean.getCreatedLocation() + "','" + bean.getComment() + "')";

            ////System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public void updateReportDamage(ReportDamageBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "update report_damage set "
                    + " equ_id=" + bean.getEquId() + ""
                    + ", created_emp=" + bean.getCreatedEmp() + ""
                    + ", manager_emp=" + bean.getManagerEmp() + ""
                    + ", used_emp=" + bean.getUsedEmp() + ""
                    + ", manager_equipment_emp=" + bean.getManagerEquipmentEmp() + ""
                    + ", status=" + bean.getStatus() + ""
                    + ", rd_number='" + bean.getRdNumber() + "'"
                    + ", created_time='" + bean.getCreatedTime() + "'"
                    + ", created_location='" + bean.getCreatedLocation() + "'"
                    + ", comment='" + bean.getComment() + "'"
                    + " where rd_id=" + bean.getRdId();

            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteReportDamage(String reportDamageid) throws Exception {
        int result = 0;
        try {
            String sql = "delete from report_damage " + " where rd_id=" + reportDamageid;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleReportDamage(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "rd_number";
                break;
            case 2:
                strFieldname = "name_vn";
                break;
            case 3:
                strFieldname = "name";
        }
        ResultSet rs = null;

        String sql = "SELECT r.*,o.name,m.name_vn,m.name_en,t.used_code FROM report_damage AS r LEFT JOIN employee AS e ON e.emp_id = r.used_emp LEFT JOIN organization AS o ON o.org_id=e.org_id LEFT JOIN equipment AS t ON t.equ_id=r.equ_id LEFT JOIN material AS m ON m.mat_id=t.mat_id WHERE 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by rd_id desc";

        ArrayList reportDamageList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            ReportDamageBean reportDamage = null;
            while (rs.next()) {
                reportDamage = new ReportDamageBean();
                reportDamage.setRdId(rs.getInt("rd_id"));
                reportDamage.setEquId(rs.getInt("equ_id"));
                reportDamage.setCreatedEmp(rs.getInt("created_emp"));
                reportDamage.setManagerEmp(rs.getInt("manager_emp"));
                reportDamage.setUsedEmp(rs.getInt("used_emp"));
                reportDamage.setManagerEquipmentEmp(rs.getInt("manager_equipment_emp"));
                reportDamage.setStatus(rs.getInt("status"));
                reportDamage.setRdNumber(rs.getString("rd_number"));
                reportDamage.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                reportDamage.setCreatedTime(rs.getString("created_time"));
                reportDamage.setCreatedLocation(rs.getString("created_location"));
                reportDamage.setComment(rs.getString("comment"));

                reportDamage.setEquName((StringUtil.decodeString(rs.getString("name_vn")) + " - " + StringUtil.decodeString(rs.getString("used_code"))));
                if (rs.getString("name") != null) {
                    reportDamage.setUsedOrg(rs.getString("name"));
                } else {
                    reportDamage.setUsedOrg("");
                }

                reportDamageList.add(reportDamage);
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return reportDamageList;

    }

    public boolean checkRdNumber(int id, String rd_number) throws SQLException {
        ResultSet rs = null;
        try {
            rs = DBUtil.executeQuery("SELECT * FROM report_damage WHERE (rd_id <> " + id + " AND rd_number = '" + rd_number + "')");
            if (rs.next()) {

                return true;
            } else {

                return false;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
    }
    /*
    public boolean checkDeleted(String id) throws SQLException {
    
    try {
    ResultSet rs = null;
    
    
    String sql = "";
    //     System.out.println("executeQuery: " + sql);
    
    sql = "SELECT * FROM report_damage WHERE rd_id =";
    
    rs = DBUtil.executeQuery(sql + id);
    if (rs.next()) {
    return true;
    } else {
    return false;
    }
    } catch (SQLException ex) {
    throw new SQLException(ex.getMessage());
    } finally {
    try {
    
    
    } catch (SQLException e) {
    throw new SQLException(e.getMessage());
    }
    }
    }
    
     */
}
