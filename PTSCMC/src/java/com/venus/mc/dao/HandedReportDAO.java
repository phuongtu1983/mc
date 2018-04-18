/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.HandedReportBean;
import com.venus.mc.bean.HandedReportDetailBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author thcao
 */
public class HandedReportDAO extends BasicDAO {

    public HandedReportDAO() {
    }

    public ArrayList getHandedReportList() throws Exception {

        ResultSet rs = null;

        String sql = "select r.*,o1.name,o2.name,o2.org_id from handed_report as r"
                + " left join organization as o1 on o1.org_id=r.org_receive"
                + " left join employee as e on e.emp_id=r.created_emp"
                + " left join organization as o2 on o2.org_id=e.org_id";
        ArrayList hrList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            HandedReportBean hrBean = null;
            while (rs.next()) {
                hrBean = new HandedReportBean();
                hrBean.setHrId(rs.getInt("hr_id"));
                hrBean.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                hrBean.setCreatedEmpId(rs.getInt("created_emp"));
                hrBean.setHrNumber(rs.getString("hr_number"));
                hrBean.setOrgId(rs.getInt("o2.org_id"));
                hrBean.setOrgName(rs.getString("o2.name"));
                hrBean.setOrgReceiveId(rs.getInt("org_receive"));
                hrBean.setOrgReceiveName(rs.getString("o1.name"));
                hrBean.setCreatedLocation(rs.getString("created_location"));
                hrBean.setCreatedTime(rs.getString("created_time"));
                hrBean.setRtId(rs.getInt("rt_id"));
                hrList.add(hrBean);
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
        return hrList;
    }

    public HandedReportBean getHandedReport(int hrId) throws Exception {

        ResultSet rs = null;

        String sql = "select r.*,o1.name,o2.name,o2.org_id,e.fullname,e1.fullname from handed_report as r"
                + " left join organization as o1 on r.org_receive=o1.org_id"
                + " left join employee as e on e.emp_id=r.created_emp"
                + " left join employee as e1 on e1.emp_id=r.emp_receive"
                + " left join organization as o2 on e.org_id=o2.org_id"
                + " where hr_id=" + hrId;
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                HandedReportBean bean = new HandedReportBean();
                bean.setHrId(rs.getInt("hr_id"));
                bean.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                bean.setCreatedEmpId(rs.getInt("created_emp"));
                bean.setCreatedEmpName(rs.getString("e.fullname"));
                bean.setOrgId(rs.getInt("o2.org_id"));
                bean.setOrgName(rs.getString("o2.name"));
                bean.setOrgReceiveId(rs.getInt("org_receive"));
                bean.setOrgReceiveName(rs.getString("o1.name"));
                bean.setHrNumber(rs.getString("hr_number"));
                bean.setCreatedTime(rs.getString("created_time"));
                bean.setCreatedLocation(rs.getString("created_location"));
                bean.setEmpReceiveId(rs.getInt("r.emp_receive"));
                bean.setEmpReceiveName(rs.getString("e1.fullname"));
                bean.setRtId(rs.getInt("rt_id"));

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

    public boolean checkHrNumber(int hrId, String hrNumber) throws Exception {
        ResultSet rs = null;
        try {
            String sql = "select * from handed_report where (hr_id <> " + hrId + " and hr_number = '" + hrNumber + "')";
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

    public int insertHandedReport(HandedReportBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        int result = 0;
        String orgReceive = "null";
        if (bean.getOrgReceiveId() > 0) {
            orgReceive = "" + bean.getOrgReceiveId();
        }
        String empId = "null";
        if (bean.getEmpReceiveId() > 0) {
            empId = "" + bean.getEmpReceiveId();
        }
        String rtId = "null";
        if (bean.getRtId() > 0) {
            rtId = "" + bean.getRtId();
        }
        try {
            String sql = "";
            sql = "insert into handed_report("
                    + "created_date,"
                    + "created_emp,"
                    + "hr_number,"
                    + "created_time,"
                    + "created_location,"
                    + "rt_id,"
                    + "emp_receive,"
                    + "org_receive)"
                    + " values ("
                    + "sysdate(),"
                    + bean.getCreatedEmpId() + ","
                    + "'" + bean.getHrNumber() + "',"
                    + "'" + bean.getCreatedTime() + "',"
                    + "'" + bean.getCreatedLocation() + "',"
                    + rtId + ","
                    + empId + ","
                    + orgReceive + ")";
            ////System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
        return result;
    }

    public void updateHandedReport(HandedReportBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        String sql = "update handed_report set"
                + " hr_number='" + bean.getHrNumber() + "'"
                + " ,created_time='" + bean.getCreatedTime() + "'"
                + " ,created_location='" + bean.getCreatedLocation() + "'";
        if (bean.getRtId() > 0) {
            sql += " ,rt_id='" + bean.getRtId() + "'";
        }
        if (bean.getOrgReceiveId() > 0) {
            sql += " ,org_receive=" + bean.getOrgReceiveId();
        }
        if (bean.getRtId() > 0) {
            sql += " ,rt_id=" + bean.getRtId();
        }
        if (bean.getEmpReceiveId() > 0) {
            sql += " ,emp_receive=" + bean.getEmpReceiveId();
        }
        if (bean.getOrgReceiveId() > 0) {
            sql += " ,org_receive=" + bean.getOrgReceiveId();
        }
        sql += " where hr_id=" + bean.getHrId();
        try {
            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public int insertHandedReportDetail(HandedReportDetailBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        if (GenericValidator.isBlankOrNull(bean.getSpecifications())) {
            bean.setSpecifications("");
        }
        if (GenericValidator.isBlankOrNull(bean.getComment())) {
            bean.setComment("");
        }
        String sql = "insert into handed_report_detail("
                + "hr_id,"
                + "equ_id,"
                + "specifications,"
                + "comment,"
                + "quantity)"
                + " values ("
                + bean.getHrId() + ","
                + bean.getEquId() + ","
                + "'" + bean.getSpecifications() + "',"
                + "'" + bean.getComment() + "',"
                + bean.getQuantity()
                + ")";

        try {


            ////System.out.println("sql ====" + sql);
            return DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public void updateHandedReportDetail(HandedReportDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        if (GenericValidator.isBlankOrNull(bean.getSpecifications())) {
            bean.setSpecifications("");
        }
        if (GenericValidator.isBlankOrNull(bean.getComment())) {
            bean.setComment("");
        }
        int result = 0;
        String sql = "update handed_report_detail set "
                + " equ_id=" + bean.getEquId()
                + " ,specifications='" + bean.getSpecifications() + "'"
                + " ,comment='" + bean.getComment() + "'"
                + " ,quantity=" + bean.getQuantity()
                + " where det_id=" + bean.getDetId();

        try {
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public int deleteHandedReport(int hrId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from handed_report " + " where hr_id=" + hrId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
        return result;
    }

    public int deleteHandedReportDetail(int detId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from handed_report_detail " + " where det_id=" + detId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
        return result;
    }

    public int deleteHandedReportDetails(int hrId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from handed_report_detail " + " where hr_id=" + hrId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleHandedReport(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "hr_number";
                break;

        }
        ResultSet rs = null;

        String sql = "select r.*,o.name,o.org_id,o1.name"
                + " from handed_report as r"
                + " left join employee as e on e.emp_id = r.created_emp"
                + " left join organization as o on o.org_id=e.org_id"
                + " left join organization as o1 on o1.org_id=r.org_receive where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "and " + strFieldname + " like '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by created_date DESC";
        //sql = sql + " order by rr_id desc";
        ArrayList requireList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            HandedReportBean hrBean = null;
            while (rs.next()) {
                hrBean = new HandedReportBean();
                hrBean.setHrId(rs.getInt("hr_id"));
                hrBean.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                hrBean.setCreatedEmpId(rs.getInt("created_emp"));
                hrBean.setHrNumber(rs.getString("hr_number"));
                hrBean.setOrgName(rs.getString("o.name"));
                hrBean.setOrgId(rs.getInt("o.org_id"));
                hrBean.setOrgReceiveId(rs.getInt("org_receive"));
                hrBean.setOrgReceiveName(rs.getString("o1.name"));
                hrBean.setRtId(rs.getInt("rt_id"));
                requireList.add(hrBean);
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

    public ArrayList getHandedReportDetails(int hrId) throws Exception {
        ResultSet rs = null;

        String sql = "select d.*, e.used_code,m.name_vn,u.unit_vn"
                + " from handed_report_detail as d"
                + " left join equipment as e on e.equ_id = d.equ_id"
                + " left join material as m on e.mat_id = m.mat_id"
                + " left join unit as u on u.uni_id = m.uni_id"
                + " where hr_id=" + hrId;
        ArrayList detailList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            HandedReportDetailBean detail = null;
            while (rs.next()) {
                detail = new HandedReportDetailBean();
                detail.setDetId(rs.getString("det_id"));
                detail.setHrId(rs.getInt("hr_id"));
                detail.setEquId(rs.getInt("equ_id"));
                detail.setEquipmentName(StringUtil.decodeString(rs.getString("m.name_vn")));
                detail.setUsedCode(rs.getString("used_code"));
                detail.setQuantity(rs.getDouble("quantity"));
                detail.setUnitName(rs.getString("u.unit_vn"));
                detail.setSpecifications(rs.getString("specifications"));
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

    public int deleteHandedReportDetail(String detId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from handed_report_detail " + " where det_id=" + detId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
        return result;
    }
}
