package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.RepairNotPlanBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mai Vinh Loc
 */
public class RepairNotPlanDAO extends BasicDAO {

    public RepairNotPlanDAO() {
    }

    public ArrayList getRepairNotPlans(int equId) throws Exception {

        ResultSet rs = null;

        String sql = "";
        sql = "SELECT r.*, rd.rd_id, rd.rd_number,rr.rr_number, sr.sr_id, sr.sr_number, at1.at_id, at1.at_number FROM repair_not_plan AS r LEFT JOIN report_damage AS rd ON rd.equ_id = r.equ_id LEFT JOIN require_repair_detail AS rrd ON rrd.equ_id = r.equ_id LEFT JOIN require_repair AS rr ON rr.rr_id = rrd.rr_id LEFT JOIN survey_report_detail AS srd ON srd.equ_id = r.equ_id LEFT JOIN survey_report AS sr ON sr.sr_id = srd.sr_id LEFT JOIN acceptance_test_detail AS atd ON atd.equ_id = r.equ_id LEFT JOIN acceptance_test AS at1 ON at1.at_id = atd.at_id WHERE r.equ_id = " + equId + " order by rnp_id desc";


        ArrayList repairnotplanList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            RepairNotPlanBean repairnotplan = null;
            while (rs.next()) {
                repairnotplan = new RepairNotPlanBean();
                repairnotplan.setRnpId(rs.getInt("rnp_id"));
                repairnotplan.setEquId(rs.getInt("equ_id"));
                repairnotplan.setOrgUsed(rs.getInt("org_used"));
                repairnotplan.setCost(rs.getDouble("cost"));
                repairnotplan.setRdId(rs.getInt("rd_id"));
                repairnotplan.setRdNumber(String.valueOf(StringUtil.decodeString(rs.getString("rd_number"))));
                repairnotplan.setSrId(rs.getInt("sr_id"));
                repairnotplan.setSrNumber(String.valueOf(StringUtil.decodeString(rs.getString("sr_number"))));
                repairnotplan.setRrId(rs.getInt("rr_id"));
                repairnotplan.setRrNumber(String.valueOf(StringUtil.decodeString(rs.getString("rr_number"))));
                repairnotplan.setAtId(rs.getInt("at_id"));
                repairnotplan.setAtNumber(String.valueOf(StringUtil.decodeString(rs.getString("at_number"))));
                repairnotplanList.add(repairnotplan);
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
        return repairnotplanList;
    }

    public RepairNotPlanBean getRepairNotPlan(int rnpId, int equId) throws Exception {

        ResultSet rs = null;

        String sql = "";

        sql = "SELECT r.*, rd.rd_id, rd.rd_number,rr.rr_number, sr.sr_id, sr.sr_number, at1.at_id, at1.at_number FROM repair_not_plan AS r LEFT JOIN report_damage AS rd ON rd.equ_id = r.equ_id LEFT JOIN require_repair_detail AS rrd ON rrd.equ_id = r.equ_id LEFT JOIN require_repair AS rr ON rr.rr_id = rrd.rr_id LEFT JOIN survey_report_detail AS srd ON srd.equ_id = r.equ_id LEFT JOIN survey_report AS sr ON sr.sr_id = srd.sr_id LEFT JOIN acceptance_test_detail AS atd ON atd.equ_id = r.equ_id LEFT JOIN acceptance_test AS at1 ON at1.at_id = atd.at_id WHERE r.equ_id = " + equId + " AND r.rnp_id=" + rnpId;



        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                RepairNotPlanBean repairnotplan = new RepairNotPlanBean();
                repairnotplan = new RepairNotPlanBean();
                repairnotplan.setRnpId(rs.getInt("rnp_id"));
                repairnotplan.setEquId(rs.getInt("equ_id"));
                repairnotplan.setOrgUsed(rs.getInt("org_used"));
                repairnotplan.setCost(rs.getDouble("cost"));
                repairnotplan.setRdId(rs.getInt("rd_id"));
                repairnotplan.setRdNumber(String.valueOf(StringUtil.decodeString(rs.getString("rd_number"))));
                repairnotplan.setSrId(rs.getInt("sr_id"));
                repairnotplan.setSrNumber(String.valueOf(StringUtil.decodeString(rs.getString("sr_number"))));
                repairnotplan.setRrId(rs.getInt("rr_id"));
                repairnotplan.setRrNumber(String.valueOf(StringUtil.decodeString(rs.getString("rr_number"))));
                repairnotplan.setAtId(rs.getInt("at_id"));
                repairnotplan.setAtNumber(String.valueOf(StringUtil.decodeString(rs.getString("at_number"))));

                return repairnotplan;
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

    public void insertRepairNotPlan(RepairNotPlanBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;

        try {
            String sql = "";
            sql = "insert into repair_not_plan(equ_id,rr_id,cost,org_used)"
                    + " values (" + bean.getEquId() + "," + bean.getRrId() + "," + bean.getCost() + "," + bean.getOrgUsed() + ")";

            ////System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateRepairNotPlan(RepairNotPlanBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;

        try {
            String sql = "update repair_not_plan set "
                    + " equ_id=" + bean.getEquId() + ""
                    + ", rr_id=" + bean.getRrId() + ""
                    + ", cost=" + bean.getCost() + ""
                    + ", org_used=" + bean.getOrgUsed() + ""
                    + " where rnp_id=" + bean.getRnpId();

            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteRepairNotPlan(String rnpId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from repair_not_plan where rnp_id=" + rnpId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleRepairNotPlan(int fieldid, String strFieldvalue, int equId) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "org_used";
                break;
            case 2:
                strFieldname = "cost";
                break;
        }
        ResultSet rs = null;

        String sql = "";

        sql = "SELECT * FROM repair_not_plan WHERE equ_id = " + equId + " ";

        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by rnp_id desc";

        ArrayList repairnotplanList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            RepairNotPlanBean repairnotplan = null;
            while (rs.next()) {
                repairnotplan = new RepairNotPlanBean();
                repairnotplan.setRnpId(rs.getInt("rnp_id"));
                repairnotplan.setEquId(rs.getInt("equ_id"));
                repairnotplan.setRrId(rs.getInt("rr_id"));
                repairnotplan.setCost(rs.getDouble("cost"));
                repairnotplan.setOrgUsed(rs.getInt("org_used"));
                repairnotplan.setEquName(rs.getString("equNameVn"));
                repairnotplan.setOrgName(rs.getString("orgName"));
                repairnotplanList.add(repairnotplan);
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
        return repairnotplanList;

    }

    public ArrayList searchAdvRepairNotPlan(RepairNotPlanBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "";
        sql = "SELECT * FROM repair_not_plan WHERE equ_id = " + bean.getEquId() + " ";

        if (bean.getCost() > 0) {
            sql = sql + " AND cost = " + bean.getCost() + "";
        }

        sql = sql + " order by rnp_id desc";

        ArrayList repairnotplanList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            RepairNotPlanBean repairnotplan = null;

            while (rs.next()) {
                repairnotplan = new RepairNotPlanBean();
                repairnotplan.setRnpId(rs.getInt("rnp_id"));
                repairnotplan.setEquId(rs.getInt("equ_id"));
                repairnotplan.setRrId(rs.getInt("rr_id"));
                repairnotplan.setCost(rs.getDouble("cost"));
                repairnotplan.setOrgUsed(rs.getInt("org_used"));
                repairnotplan.setEquName(rs.getString("equNameVn"));
                repairnotplan.setOrgName(rs.getString("orgName"));
                repairnotplanList.add(repairnotplan);
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
        return repairnotplanList;
    }
}
