package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.VerifiedPlanBean;
import com.venus.mc.bean.VerifiedPlanWarningBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.util.MCUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mai Vinh Loc
 */
public class VerifiedPlanDAO extends BasicDAO {

    public VerifiedPlanDAO() {
    }

    public ArrayList getVerifiedPlans(int equId) throws Exception {

        ResultSet rs = null;

        String sql = "";
        sql = "SELECT * FROM verified_plan WHERE equ_id = " + equId + " order by vp_id desc";


        ArrayList verifiedplanList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            VerifiedPlanBean verifiedplan = null;
            while (rs.next()) {
                verifiedplan = new VerifiedPlanBean();
                verifiedplan.setVpId(rs.getInt("vp_id"));
                verifiedplan.setEquId(rs.getInt("equ_id"));
                verifiedplan.setOrgId(rs.getInt("org_id"));
                verifiedplan.setContent(rs.getString("content"));
                verifiedplan.setCost(rs.getDouble("cost"));
                verifiedplan.setStatus(rs.getInt("status"));
                verifiedplan.setComment(rs.getString("comment"));
                verifiedplan.setPerformKind(rs.getInt("perform_kind"));
                verifiedplan.setPerformPart(rs.getString("perform_part"));
                verifiedplan.setTimePlan(DateUtil.formatDate(rs.getDate("time_plan"), "dd/MM/yyyy"));
                verifiedplan.setTimeEffect(rs.getString("time_effect"));
                verifiedplan.setTimeNext(DateUtil.formatDate(rs.getDate("time_next"), "dd/MM/yyyy"));
                if (rs.getInt("status") == VerifiedPlanBean.S1) {
                    verifiedplan.setStatusName(MCUtil.getBundleString("message.verifiedplan.status1"));
                }
                if (rs.getInt("status") == VerifiedPlanBean.S2) {
                    verifiedplan.setStatusName(MCUtil.getBundleString("message.verifiedplan.status2"));
                }
                if (rs.getInt("status") == VerifiedPlanBean.S3) {
                    verifiedplan.setStatusName(MCUtil.getBundleString("message.verifiedplan.status3"));
                }

                verifiedplanList.add(verifiedplan);
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
        return verifiedplanList;
    }

    public VerifiedPlanBean getVerifiedPlan(int vpId, int equId) throws Exception {

        ResultSet rs = null;

        String sql = "";

        sql = "SELECT * FROM verified_plan WHERE equ_id = " + equId + " AND vp_id=" + vpId;



        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                VerifiedPlanBean verifiedplan = new VerifiedPlanBean();
                verifiedplan = new VerifiedPlanBean();
                verifiedplan.setVpId(rs.getInt("vp_id"));
                verifiedplan.setEquId(rs.getInt("equ_id"));
                verifiedplan.setOrgId(rs.getInt("org_id"));
                verifiedplan.setContent(rs.getString("content"));
                verifiedplan.setCost(rs.getDouble("cost"));
                verifiedplan.setStatus(rs.getInt("status"));
                verifiedplan.setComment(rs.getString("comment"));
                verifiedplan.setPerformKind(rs.getInt("perform_kind"));
                verifiedplan.setPerformPart(rs.getString("perform_part"));
                verifiedplan.setTimePlan(DateUtil.formatDate(rs.getDate("time_plan"), "dd/MM/yyyy"));
                verifiedplan.setTimeEffect(rs.getString("time_effect"));
                verifiedplan.setTimeNext(DateUtil.formatDate(rs.getDate("time_next"), "dd/MM/yyyy"));

                return verifiedplan;
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

    public void insertVerifiedPlan(VerifiedPlanBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        String equId = null;
        String orgId = null;
        String performPart = "";

        if (bean.getEquId() > 0) {
            equId = bean.getEquId() + "";
        }
        if (bean.getPerformKind() == 1) {
            orgId = bean.getOrgId() + "";
        } else {
            performPart = bean.getPerformPart();
        }
        try {
            String sql = "";
            sql = "insert into verified_plan(equ_id,time_plan,time_effect,time_next,content,cost,status,comment,perform_kind,perform_part,org_id)"
                    + " values (" + equId + ",STR_TO_DATE('" + bean.getTimePlan() + "','%d/%m/%Y'),'" + bean.getTimeEffect() + "',STR_TO_DATE('" + bean.getTimeNext() + "','%d/%m/%Y'),'" + bean.getContent() + "'," + bean.getCost() + "," + bean.getStatus() + ",'" + bean.getComment() + "'," + bean.getPerformKind() + ",'" + performPart + "'," + orgId + ")";

            ////System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateVerifiedPlan(VerifiedPlanBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        String equId = null;
        String orgId = null;
        String performPart = "";

        if (bean.getEquId() > 0) {
            equId = bean.getEquId() + "";
        }
        if (bean.getPerformKind() == 1) {
            orgId = bean.getOrgId() + "";
        } else {
            performPart = bean.getPerformPart();
        }
        try {
            String sql = "update verified_plan set "
                    + " equ_id=" + equId + ""
                    + ", org_id=" + orgId + ""
                    + ", content='" + bean.getContent() + "'"
                    + ", cost=" + bean.getCost() + ""
                    + ", status=" + bean.getStatus() + ""
                    + ", comment='" + bean.getComment() + "'"
                    + ", perform_kind=" + bean.getPerformKind() + ""
                    + ", perform_part='" + performPart + "'"
                    + ", time_plan=STR_TO_DATE('" + bean.getTimePlan() + "','%d/%m/%Y')"
                    + ", time_effect='" + bean.getTimeEffect() + "'"
                    + ", time_next=STR_TO_DATE('" + bean.getTimeNext() + "','%d/%m/%Y')"
                    + " where vp_id=" + bean.getVpId();

            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteVerifiedPlan(String vpId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from verified_plan where vp_id=" + vpId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleVerifiedPlan(int fieldid, String strFieldvalue, int equId) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "content";
                break;
            case 2:
                strFieldname = "cost";
                break;
        }
        ResultSet rs = null;

        String sql = "";

        sql = "SELECT * FROM verified_plan WHERE equ_id = " + equId + " ";

        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by vp_id desc";

        ArrayList verifiedplanList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            VerifiedPlanBean verifiedplan = null;
            while (rs.next()) {
                verifiedplan = new VerifiedPlanBean();
                verifiedplan = new VerifiedPlanBean();
                verifiedplan.setVpId(rs.getInt("vp_id"));
                verifiedplan.setEquId(rs.getInt("equ_id"));
                verifiedplan.setOrgId(rs.getInt("org_id"));
                verifiedplan.setContent(rs.getString("content"));
                verifiedplan.setCost(rs.getDouble("cost"));
                verifiedplan.setStatus(rs.getInt("status"));
                verifiedplan.setComment(rs.getString("comment"));
                verifiedplan.setPerformKind(rs.getInt("perform_kind"));
                verifiedplan.setPerformPart(rs.getString("perform_part"));
                verifiedplan.setTimePlan(DateUtil.formatDate(rs.getDate("time_plan"), "dd/MM/yyyy"));
                verifiedplan.setTimeEffect(rs.getString("time_effect"));
                verifiedplan.setTimeNext(DateUtil.formatDate(rs.getDate("time_next"), "dd/MM/yyyy"));
                if (rs.getInt("status") == VerifiedPlanBean.S1) {
                    verifiedplan.setStatusName(MCUtil.getBundleString("message.verifiedplan.status1"));
                }
                if (rs.getInt("status") == VerifiedPlanBean.S2) {
                    verifiedplan.setStatusName(MCUtil.getBundleString("message.verifiedplan.status2"));
                }
                if (rs.getInt("status") == VerifiedPlanBean.S3) {
                    verifiedplan.setStatusName(MCUtil.getBundleString("message.verifiedplan.status3"));
                }
                verifiedplanList.add(verifiedplan);
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
        return verifiedplanList;

    }

    public ArrayList searchAdvVerifiedPlan(VerifiedPlanBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "";
        sql = "SELECT * FROM verified_plan WHERE equ_id = " + bean.getEquId() + " ";

        if (bean.getCost() > 0) {
            sql = sql + " AND cost = " + bean.getCost() + "";
        }

        if (bean.getStatus() > 0) {
            sql = sql + " AND status = " + bean.getStatus() + "";
        }

        if (bean.getOrgId() > 0) {
            sql = sql + " AND org_id = " + bean.getOrgId() + "";
        }

        if (bean.getPerformKind() > 0) {
            sql = sql + " AND perform_kind = " + bean.getPerformKind() + "";
        }

        if (!StringUtil.isBlankOrNull(bean.getContent())) {
            sql = sql + " AND content LIKE '%" + bean.getContent() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getComment())) {
            sql = sql + " AND comment LIKE '%" + bean.getComment() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getPerformPart())) {
            sql = sql + " AND perform_part LIKE '%" + bean.getPerformPart() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getTimePlan())) {
            sql = sql + " AND time_plan LIKE STR_TO_DATE('" + bean.getTimePlan() + "','%d/%m/%Y %H')";
        }

        if (!StringUtil.isBlankOrNull(bean.getTimeEffect())) {
            sql = sql + " AND time_effect LIKE '%" + bean.getTimeEffect() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getTimeNext())) {
            sql = sql + " AND time_next LIKE STR_TO_DATE('" + bean.getTimeNext() + "','%d/%m/%Y %H')";
        }
        sql = sql + " order by vp_id desc";

        ArrayList verifiedplanList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            VerifiedPlanBean verifiedplan = null;

            while (rs.next()) {
                verifiedplan = new VerifiedPlanBean();
                verifiedplan = new VerifiedPlanBean();
                verifiedplan.setVpId(rs.getInt("vp_id"));
                verifiedplan.setEquId(rs.getInt("equ_id"));
                verifiedplan.setOrgId(rs.getInt("org_id"));
                verifiedplan.setContent(rs.getString("content"));
                verifiedplan.setCost(rs.getDouble("cost"));
                verifiedplan.setStatus(rs.getInt("status"));
                verifiedplan.setComment(rs.getString("comment"));
                verifiedplan.setPerformKind(rs.getInt("perform_kind"));
                verifiedplan.setPerformPart(rs.getString("perform_part"));
                verifiedplan.setTimePlan(DateUtil.formatDate(rs.getDate("time_plan"), "dd/MM/yyyy"));
                verifiedplan.setTimeEffect(rs.getString("time_effect"));
                verifiedplan.setTimeNext(DateUtil.formatDate(rs.getDate("time_next"), "dd/MM/yyyy"));
                if (rs.getInt("status") == VerifiedPlanBean.S1) {
                    verifiedplan.setStatusName(MCUtil.getBundleString("message.verifiedplan.status1"));
                }
                if (rs.getInt("status") == VerifiedPlanBean.S2) {
                    verifiedplan.setStatusName(MCUtil.getBundleString("message.verifiedplan.status2"));
                }
                if (rs.getInt("status") == VerifiedPlanBean.S3) {
                    verifiedplan.setStatusName(MCUtil.getBundleString("message.verifiedplan.status3"));
                }
                verifiedplanList.add(verifiedplan);
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
        return verifiedplanList;
    }
    /*
    public boolean checkDecisionNumber(int id, String value) throws SQLException {
    
    try {
    ResultSet rs = null;
    
    
    //     System.out.println("executeQuery: " + sql);
    rs = DBUtil.executeQuery("SELECT * FROM verified_plan WHERE vp_id <> " + id + " AND decision_number = '" + value + "'");
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
    
    public boolean checkUsedCode(int id, String value) throws SQLException {
    
    try {
    ResultSet rs = null;
    
    
    //     System.out.println("executeQuery: " + sql);
    rs = DBUtil.executeQuery("SELECT * FROM verified_plan WHERE vp_id <> " + id + " AND used_code = '" + value + "'");
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

    public ArrayList getVerifiedPlanForWarning(int dateAmount) throws Exception {
        ResultSet rs = null;

        String sql = "select e.manage_code, m.name_vn, vp.time_true"
                + " from verified_plan as vp "
                + " left join equipment as e on e.equ_id=vp.equ_id"
                + " left join material as m on m.mat_id=e.mat_id"
                + " where vp.warned=0 and datediff(sysdate(),time_next)>=" + dateAmount;

        ArrayList list = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            VerifiedPlanWarningBean detail = null;
            while (rs.next()) {
                detail = new VerifiedPlanWarningBean();
                detail.setVpId(rs.getInt("vp_id"));
                detail.setManageCode(rs.getString("manage_code"));
                detail.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
                detail.setTimeTrue(DateUtil.formatDate(rs.getDate("time_next"), "dd/MM/yyyy"));
                list.add(detail);
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
        return list;
    }

    public void updateVerifiedPlanWarnedStatus(String ids) throws Exception {



        try {


            String sql = "update verified_plan set warned=1 where vp_id in (" + ids + ")";
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            try {
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
    }
}
