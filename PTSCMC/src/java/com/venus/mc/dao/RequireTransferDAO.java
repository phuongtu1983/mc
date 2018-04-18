/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.RequireTransferBean;
import com.venus.mc.bean.RequireTransferDetailBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author thcao
 */
public class RequireTransferDAO extends BasicDAO {

    public RequireTransferDAO() {
    }

    public ArrayList getRequireTransferList() throws Exception {

        ResultSet rs = null;

        String sql = "select r.*,o.org_id,o.name from require_transfer as r"
                + " left join employee as e on e.emp_id = r.created_emp"
                + " left join organization as o on o.org_id=e.org_id ";

        ArrayList requireTransferList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            RequireTransferBean requireTransfer = null;
            while (rs.next()) {
                requireTransfer = new RequireTransferBean();
                requireTransfer.setRtId(rs.getInt("rt_id"));
                requireTransfer.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                requireTransfer.setCreatedEmpId(rs.getInt("created_emp"));
                requireTransfer.setRtNumber(rs.getString("rt_number"));
                requireTransfer.setReason(rs.getString("reason"));
                requireTransfer.setOrgName(rs.getString("o.name"));
                requireTransfer.setOrgId(rs.getInt("o.org_id"));
                requireTransferList.add(requireTransfer);
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
        return requireTransferList;
    }

    public RequireTransferBean getRequireTransfer(int rtId) throws Exception {

        ResultSet rs = null;

        String sql = "select r.*,o.org_id,o.name from require_transfer as r"
                + " left join employee as e on e.emp_id=r.created_emp"
                + " left join organization as o on e.org_id=o.org_id"
                + " where rt_id=" + rtId;


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                RequireTransferBean bean = new RequireTransferBean();
                bean.setRtId(rs.getInt("rt_id"));
                bean.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                bean.setCreatedEmpId(rs.getInt("created_emp"));
                bean.setOrgId(rs.getInt("o.org_id"));
                bean.setOrgName(rs.getString("o.name"));
                bean.setRtNumber(rs.getString("rt_number"));
                bean.setReason(rs.getString("reason"));

                return bean;
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

    public boolean checkRtNumber(int rtId, String rtNumber) throws Exception {
        ResultSet rs = null;

        try {


            String sql = "select * from require_transfer where (rt_id <> " + rtId + " and rt_number = '" + rtNumber + "')";

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

    public int insertRequireTransfer(RequireTransferBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "";
            sql = "insert into require_transfer("
                    + "created_date,"
                    + "created_emp,"
                    + "rt_number ,"
                    + "reason)" + " values ("
                    + "sysdate(),"
                    + bean.getCreatedEmpId() + ","
                    + "'" + bean.getRtNumber() + "',"
                    + "'" + bean.getReason() + "')";
            ////System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
        return result;
    }

    public void updateRequireTransfer(RequireTransferBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "update require_transfer set rt_number='" + bean.getRtNumber() + "', reason='" + bean.getReason() + "'" + " where rt_id=" + bean.getRtId();
            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public int insertRequireTransferDetail(RequireTransferDetailBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        String sql;
        String timeEstimate = "null";
        if (!GenericValidator.isBlankOrNull(bean.getTimeEstimate())) {
            timeEstimate = "STR_TO_DATE('" + bean.getTimeEstimate() + "','%d/%m/%Y')";
        }
        try {
            sql = "insert into require_transfer_detail("
                    + "rt_id,"
                    + "equ_id,"
                    + "time_estimate,"
                    + "quantity,"
                    + "status_comment)"
                    + " values ("
                    + bean.getRtId() + ","
                    + bean.getEquId() + ","
                    + timeEstimate + ","
                    + bean.getQuantity() + ","
                    + bean.getStatus()
                    + ")";

            ////System.out.println("sql ====" + sql);
            return DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public void updateRequireTransferDetail(RequireTransferDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "update require_transfer_detail set "
                    + "equ_id=" + bean.getEquId();
            if (!GenericValidator.isBlankOrNull(bean.getTimeEstimate())) {
                sql += ",time_estimate=STR_TO_DATE('" + bean.getTimeEstimate() + "','%d/%m/%Y')";
            }
            sql += ",status_comment=" + bean.getStatus()
                    + ",quantity=" + bean.getQuantity()
                    + " where det_id=" + bean.getDetId();
            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public int deleteRequireTransfer(int rtId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from require_transfer " + " where rt_id=" + rtId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
        return result;
    }

    public int deleteRequireTransferDetail(int detId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from require_transfer_detail " + " where det_id=" + detId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
        return result;
    }

    public int deleteRequireTransferDetails(int rtId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from require_transfer_detail " + " where rt_id=" + rtId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleRequireTransfer(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "rt_number";
                break;

        }
        ResultSet rs = null;

        String sql = "select r.*,o.name,o.org_id"
                + " from require_transfer as r"
                + " left join employee as e on e.emp_id = r.created_emp"
                + " left join organization as o on o.org_id=e.org_id where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "and " + strFieldname + " like '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by rt_id desc";

        ArrayList requireList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            RequireTransferBean requireTransfer = null;
            while (rs.next()) {
                requireTransfer = new RequireTransferBean();
                requireTransfer.setRtId(rs.getInt("rt_id"));
                requireTransfer.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                requireTransfer.setCreatedEmpId(rs.getInt("created_emp"));
                requireTransfer.setRtNumber(rs.getString("rt_number"));
                requireTransfer.setReason(rs.getString("reason"));
                requireTransfer.setOrgName(rs.getString("o.name"));
                requireTransfer.setOrgId(rs.getInt("o.org_id"));
                requireList.add(requireTransfer);
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
        return requireList;

    }

    public ArrayList getRequireTransferDetails(int rtId) throws Exception {
        ResultSet rs = null;

        String sql = "select d.*, e.used_code,m.name_vn,u.unit_vn"
                + " from require_transfer_detail as d"
                + " left join equipment as e on e.equ_id = d.equ_id"
                + " left join material as m on e.mat_id = m.mat_id"
                + " left join unit as u on u.uni_id = m.uni_id"
                + " where rt_id=" + rtId;

        ArrayList detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            RequireTransferDetailBean detail = null;
            while (rs.next()) {
                detail = new RequireTransferDetailBean();
                detail.setDetId(rs.getString("det_id"));
                detail.setRtId(rs.getInt("rt_id"));
                detail.setEquId(rs.getInt("equ_id"));
                detail.setEquipmentName(StringUtil.decodeString(rs.getString("m.name_vn")));
                detail.setUsedCode(rs.getString("used_code"));
                detail.setQuantity(rs.getDouble("quantity"));
                detail.setUnitName(rs.getString("u.unit_vn"));
                detail.setTimeEstimate(DateUtil.formatDate(rs.getDate("time_estimate"), "dd/MM/yyyy"));
                detail.setStatus(rs.getInt("status_comment"));
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

    public int deleteRequireTransferDetail(String detId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from require_transfer_detail " + " where det_id=" + detId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
        return result;
    }

    public ArrayList getRequireTransferListOfOrg(int orgId) throws Exception {

        ResultSet rs = null;

        String sql = "";
        if (orgId == 19) {
            sql = "select rt_id,rt_number from require_transfer as r"
                    + " left join employee as e on e.emp_id = r.created_emp"
                    + " where 1 ";
        } else {
            sql = "select rt_id,rt_number from require_transfer as r"
                    + " left join employee as e on e.emp_id = r.created_emp"
                    + " where e.org_id=" + orgId;
        }

        ArrayList requireTransferList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            LabelValueBean requireTransfer = null;
            while (rs.next()) {
                requireTransfer = new LabelValueBean();
                requireTransfer.setValue(rs.getString("rt_id"));
                requireTransfer.setLabel(rs.getString("rt_number"));
                requireTransferList.add(requireTransfer);
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
        return requireTransferList;
    }
}
