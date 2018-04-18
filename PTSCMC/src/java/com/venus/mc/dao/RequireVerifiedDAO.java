/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.RequireVerifiedBean;
import com.venus.mc.bean.RequireVerifiedDetailBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author thcao
 */
public class RequireVerifiedDAO extends BasicDAO {

    public RequireVerifiedDAO() {
    }

    public ArrayList getRequireVerifiedList() throws Exception {

        ResultSet rs = null;

        String sql = "select r.*,o.org_id,o.name from require_verified as r"
                + " left join employee as e on e.emp_id = r.created_emp"
                + " left join organization as o on o.org_id=e.org_id ";

        ArrayList requireVerifiedList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            RequireVerifiedBean requireVerified = null;
            while (rs.next()) {
                requireVerified = new RequireVerifiedBean();
                requireVerified.setRvId(rs.getInt("rv_id"));
                requireVerified.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                requireVerified.setCreatedEmpId(rs.getInt("created_emp"));
                requireVerified.setRvNumber(rs.getString("rv_number"));
                requireVerified.setReason(rs.getString("reason"));
                requireVerified.setOrgName(rs.getString("o.name"));
                requireVerified.setOrgId(rs.getInt("o.org_id"));
                requireVerifiedList.add(requireVerified);
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
        return requireVerifiedList;
    }

    public RequireVerifiedBean getRequireVerified(int rvId) throws Exception {

        ResultSet rs = null;

        String sql = "select r.*,o.org_id,o.name from require_verified as r"
                + " left join employee as e on e.emp_id=r.created_emp"
                + " left join organization as o on e.org_id=o.org_id"
                + " where rv_id=" + rvId;


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                RequireVerifiedBean requireVerified = new RequireVerifiedBean();
                requireVerified.setRvId(rs.getInt("rv_id"));
                requireVerified.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                requireVerified.setCreatedEmpId(rs.getInt("created_emp"));
                requireVerified.setOrgId(rs.getInt("o.org_id"));
                requireVerified.setOrgName(rs.getString("o.name"));
                requireVerified.setRvNumber(rs.getString("rv_number"));
                requireVerified.setReason(rs.getString("reason"));

                return requireVerified;
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

    public boolean checkRvNumber(int rvId, String rvNumber) throws Exception {
        ResultSet rs = null;

        try {


            String sql = "select * from require_verified where (rv_id <> " + rvId + " and rv_number = '" + rvNumber + "')";

            rs = DBUtil.executeQuery(sql);
            if (rs.next()) {

                return true;
            } else {

                return false;
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
    }

    public int insertRequireVerified(RequireVerifiedBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "";
            sql = "insert into require_verified(created_date,created_emp,rv_number ,reason)" + " values (sysdate()," + bean.getCreatedEmpId() + ",'" + bean.getRvNumber() + "','" + bean.getReason() + "')";
            ////System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public void updateRequireVerified(RequireVerifiedBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "update require_verified set rv_number='" + bean.getRvNumber() + "', reason='" + bean.getReason() + "'" + " where rv_id=" + bean.getRvId();
            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int insertRequireVerifiedDetail(RequireVerifiedDetailBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        String sql;
        String timeEstimate = "null";
        if (!GenericValidator.isBlankOrNull(bean.getTimeEstimate())) {
            timeEstimate = "STR_TO_DATE('" + bean.getTimeEstimate() + "','%d/%m/%Y')";
        }
        try {
            sql = "insert into require_verified_detail("
                    + "rv_id,"
                    + "equ_id,"
                    + "time_estimate,"
                    + "comment)"
                    + " values ("
                    + bean.getRvId() + ","
                    + bean.getEquId() + ","
                    + timeEstimate + ","
                    + "'" + bean.getComment() + "'"
                    + ")";

            ////System.out.println("sql ====" + sql);
            return DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public void updateRequireVerifiedDetail(RequireVerifiedDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "update require_verified_detail set "
                    + "equ_id=" + bean.getEquId();
            if (!GenericValidator.isBlankOrNull(bean.getTimeEstimate())) {
                sql += ",time_estimate='" + bean.getTimeEstimate() + "'";
            }
            sql += ",comment='" + bean.getComment() + "'"
                    + " where det_id=" + bean.getDetId();
            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public int deleteRequireVerified(int rvId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from require_verified " + " where rv_id=" + rvId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
        return result;
    }

    public int deleteRequireVerifiedDetail(int detId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from require_verified_detail " + " where det_id=" + detId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
        return result;
    }

    public int deleteRequireVerifiedDetails(int rvId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from require_verified_detail " + " where rv_id=" + rvId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleRequireVerified(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "rv_number";
                break;

        }
        ResultSet rs = null;

        String sql = "select r.*,o.name,o.org_id"
                + " from require_verified as r"
                + " left join employee as e on e.emp_id = r.created_emp"
                + " left join organization as o on o.org_id=e.org_id where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "and " + strFieldname + " like '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by rv_id desc";

        ArrayList requireVerifiedList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            RequireVerifiedBean requireVerified = null;
            while (rs.next()) {
                requireVerified = new RequireVerifiedBean();
                requireVerified.setRvId(rs.getInt("rv_id"));
                requireVerified.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                requireVerified.setCreatedEmpId(rs.getInt("created_emp"));
                requireVerified.setRvNumber(rs.getString("rv_number"));
                requireVerified.setReason(rs.getString("reason"));
                requireVerified.setOrgName(rs.getString("o.name"));
                requireVerified.setOrgId(rs.getInt("o.org_id"));
                requireVerifiedList.add(requireVerified);
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
        return requireVerifiedList;

    }

    public ArrayList getRequireVerifiedDetails(int rvId) throws Exception {
        ResultSet rs = null;

        String sql = "select d.*, e.used_code,m.name_vn"
                + " from require_verified_detail as d"
                + " left join equipment as e on e.equ_id = d.equ_id"
                + " left join material as m on e.mat_id = m.mat_id"
                + " where rv_id=" + rvId;

        ArrayList detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            RequireVerifiedDetailBean detail = null;
            while (rs.next()) {
                detail = new RequireVerifiedDetailBean();
                detail.setDetId(rs.getString("det_id"));
                detail.setRvId(rs.getInt("rv_id"));
                detail.setEquId(rs.getInt("equ_id"));
                detail.setEquipmentName(StringUtil.decodeString(rs.getString("m.name_vn")));
                detail.setUsedCode(rs.getString("used_code"));
                detail.setTimeEstimate(DateUtil.formatDate(rs.getDate("time_estimate"), "dd/MM/yyyy"));
                detail.setComment(rs.getString("comment"));
                detailList.add(detail);
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
        return detailList;
    }

    public int deleteRequireVerifiedDetail(String detId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from require_verified_detail " + " where det_id=" + detId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
        return result;
    }
}
