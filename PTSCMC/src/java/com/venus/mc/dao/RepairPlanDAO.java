package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.RepairMaterialBean;
import com.venus.mc.bean.RepairPlanBean;
import com.venus.mc.bean.RepairPlanWarningBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.util.MCUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mai Vinh Loc
 */
public class RepairPlanDAO extends BasicDAO {

    public RepairPlanDAO() {
    }

    public ArrayList getRepairPlans(int equId, int assId) throws Exception {

        ResultSet rs = null;

        String sql = "";
        if (assId == 0) {
            sql = "SELECT * FROM repair_plan WHERE equ_id = " + equId + " order by rp_id desc";
        } else {
            sql = "SELECT * FROM repair_plan WHERE ass_id = " + assId + " order by rp_id desc";
        }


        ArrayList repairplanList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            RepairPlanBean repairplan = null;
            while (rs.next()) {
                repairplan = new RepairPlanBean();
                repairplan.setRpId(rs.getInt("rp_id"));
                repairplan.setEquId(rs.getInt("equ_id"));
                // repairplan.setAssId(rs.getInt("ass_id"));
                repairplan.setOrgId(rs.getInt("org_id"));
                repairplan.setRepairPart(rs.getString("repair_part"));
                repairplan.setCost(rs.getDouble("cost"));
                repairplan.setRepairKind(rs.getString("repair_kind"));
                repairplan.setStatus(rs.getInt("status"));
                repairplan.setComment(rs.getString("comment"));
                repairplan.setPerformKind(rs.getInt("perform_kind"));
                repairplan.setPerformPart(rs.getString("perform_part"));
                repairplan.setTimeRepair(rs.getInt("time_repair"));
                repairplan.setTimeUnit(rs.getString("time_unit"));
                repairplan.setTimeTrue(DateUtil.formatDate(rs.getDate("time_true"), "dd/MM/yyyy"));
                if (rs.getInt("repair_kind") == RepairPlanBean.RK1) {
                    repairplan.setRepairKind(MCUtil.getBundleString("message.repairplan.rk1"));
                }
                if (rs.getInt("repair_kind") == RepairPlanBean.RK2) {
                    repairplan.setRepairKind(MCUtil.getBundleString("message.repairplan.rk2"));
                }
                if (rs.getInt("repair_kind") == RepairPlanBean.RK3) {
                    repairplan.setRepairKind(MCUtil.getBundleString("message.repairplan.rk3"));
                }

                repairplanList.add(repairplan);
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
        return repairplanList;
    }

    public ArrayList getMaterials(String ids) throws Exception {
        ResultSet rs = null;

        String sql = "SELECT m.*, u.* FROM material AS m LEFT JOIN unit AS u ON u.uni_id=m.uni_id WHERE m.mat_id in(" + ids + ")";


        ArrayList detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            RepairMaterialBean detail = null;
            while (rs.next()) {
                detail = new RepairMaterialBean();
                // detail.setRmId(rs.getInt("rm_id"));
                // detail.setRpId(rs.getInt("rp_id"));
                detail.setMatId(rs.getInt("mat_id"));
                //       detail.setContractNumber(rs.getString("econ_number"));
                detail.setMatCode(rs.getString("code"));
                detail.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
                //             detail.setQuantity(rs.getInt("quantity"));
                detail.setUnit(rs.getString("unit_vn"));
                //              detail.setPrice(rs.getDouble("price"));
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

    public ArrayList getRepairMaterials(int rpId) throws Exception {
        ResultSet rs = null;

        String sql = "SELECT d.*, m.name_vn AS materialName, m.code,u.unit_vn,u.unit_en FROM repair_material AS d LEFT JOIN material AS m ON d.mat_id=m.mat_id LEFT JOIN unit AS u ON u.uni_id = m.uni_id "
                + " Where d.rp_id=" + rpId;

        ArrayList detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            RepairMaterialBean detail = null;
            while (rs.next()) {
                detail = new RepairMaterialBean();
                detail.setDetId(rs.getInt("rm_id"));
                detail.setRmId(rs.getInt("rm_id"));
                detail.setRpId(rs.getInt("rp_id"));
                detail.setMatId(rs.getInt("mat_id"));
                detail.setMatCode(rs.getString("code"));
                detail.setMatName(StringUtil.decodeString(rs.getString("materialName")));
                detail.setQuantity(rs.getDouble("quantity"));
                detail.setUnit(rs.getString("u.unit_vn"));
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

    public RepairPlanBean getRepairPlan(int rpId, int equId, int assId) throws Exception {

        ResultSet rs = null;

        String sql = "";
        if (assId == 0) {
            sql = "SELECT * FROM repair_plan WHERE equ_id = " + equId + " AND rp_id=" + rpId;
        } else {
            sql = "SELECT * FROM repair_plan WHERE ass_id = " + assId + " AND rp_id=" + rpId;
        }


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                RepairPlanBean repairplan = new RepairPlanBean();
                repairplan.setRpId(rs.getInt("rp_id"));
                repairplan.setEquId(rs.getInt("equ_id"));
                // repairplan.setAssId(rs.getInt("ass_id"));
                repairplan.setOrgId(rs.getInt("org_id"));
                repairplan.setRepairPart(rs.getString("repair_part"));
                repairplan.setCost(rs.getDouble("cost"));
                repairplan.setRepairKind(rs.getString("repair_kind"));
                repairplan.setStatus(rs.getInt("status"));
                repairplan.setComment(rs.getString("comment"));
                repairplan.setPerformKind(rs.getInt("perform_kind"));
                repairplan.setPerformPart(rs.getString("perform_part"));
                repairplan.setTimeTrue(DateUtil.formatDate(rs.getDate("time_true"), "dd/MM/yyyy"));
                repairplan.setTimeRepair(rs.getInt("time_repair"));
                repairplan.setTimeUnit(rs.getString("time_unit"));

                return repairplan;
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

    public int insertRepairPlan(RepairPlanBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        int result = 0;
        String equId = null;
        String assId = null;
        String orgId = null;
        String performPart = "";
        if (bean.getAssId() > 0) {
            assId = bean.getAssId() + "";
        }
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
            sql = "insert into repair_plan(equ_id,time_true,repair_part,cost,repair_kind,status,comment,perform_kind,perform_part,org_id,time_repair,time_unit)"
                    + " values (" + equId + ",STR_TO_DATE('" + bean.getTimeTrue() + "','%d/%m/%Y'),'" + bean.getRepairPart() + "'," + bean.getCost() + "," + bean.getRepairKind() + "," + bean.getStatus() + ",'" + bean.getComment() + "'," + bean.getPerformKind() + ",'" + performPart + "'," + orgId + "," + bean.getTimeRepair() + ",'" + bean.getTimeUnit() + "')";

            ////System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int insertRepairMaterial(RepairMaterialBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }

        String sql = "";
        try {
            sql = "insert into repair_material(rp_id,mat_id,unit,quantity)" + " values (" + bean.getRpId() + "," + bean.getMatId() + ",'" + bean.getUnit() + "'," + bean.getQuantity() + ")";

            System.out.println("sql ====" + sql);
            return DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public void updateRepairMaterial(RepairMaterialBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "update repair_material set " + " rp_id=" + bean.getRpId() + "" + ", mat_id=" + bean.getMatId() + "" + ", quantity=" + bean.getQuantity() + "" + " where rm_id=" + bean.getDetId();

            System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateRepairPlan(RepairPlanBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        String equId = null;
        String assId = null;
        String orgId = null;
        String performPart = "";
        if (bean.getAssId() > 0) {
            assId = bean.getAssId() + "";
        }
        if (bean.getEquId() > 0) {
            equId = bean.getEquId() + "";
        }
        if (bean.getPerformKind() == 1) {
            orgId = bean.getOrgId() + "";
        } else {
            performPart = bean.getPerformPart();
        }
        try {
            String sql = "update repair_plan set "
                    + " equ_id=" + equId + ""
                    + // ", ass_id=" + assId + "" +
                    ", org_id=" + orgId + ""
                    + ", repair_part='" + bean.getRepairPart() + "'"
                    + ", cost=" + bean.getCost() + ""
                    + ", repair_kind=" + bean.getRepairKind() + ""
                    + ", status=" + bean.getStatus() + ""
                    + ", comment='" + bean.getComment() + "'"
                    + ", perform_kind=" + bean.getPerformKind() + ""
                    + ", perform_part='" + performPart + "'"
                    + ", time_true=STR_TO_DATE('" + bean.getTimeTrue() + "','%d/%m/%Y')"
                    + ", time_repair=" + bean.getTimeRepair() + ""
                    + ", time_unit='" + bean.getTimeUnit() + "'"
                    + " where rp_id=" + bean.getRpId();

            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteRepairPlan(String rpId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from repair_plan where rp_id=" + rpId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int copyRepairPlan(String rpId, int copyNumber) throws Exception {
        int result = 0;
        ResultSet rs = null;

        String sql = "";

        sql = "SELECT * FROM repair_plan WHERE rp_id = " + rpId;



        try {



            rs = DBUtil.executeQuery(sql);
            RepairPlanBean repairplan = null;
            while (rs.next()) {
                repairplan = new RepairPlanBean();
                repairplan.setRpId(rs.getInt("rp_id"));
                repairplan.setEquId(rs.getInt("equ_id"));
                // repairplan.setAssId(rs.getInt("ass_id"));
                repairplan.setOrgId(rs.getInt("org_id"));
                repairplan.setRepairPart(rs.getString("repair_part"));
                repairplan.setCost(rs.getDouble("cost"));
                repairplan.setRepairKind(rs.getString("repair_kind"));
                repairplan.setStatus(rs.getInt("status"));
                repairplan.setComment(rs.getString("comment"));
                repairplan.setPerformKind(rs.getInt("perform_kind"));
                repairplan.setPerformPart(rs.getString("perform_part"));
                repairplan.setTimeRepair(rs.getInt("time_repair"));
                repairplan.setTimeUnit(rs.getString("time_unit"));
                repairplan.setTimeTrue(DateUtil.formatDate(rs.getDate("time_true"), "dd/MM/yyyy"));
                repairplan.setRepairKind(rs.getString("repair_kind"));
                for (int i = 1; i <= copyNumber; i++) {
                    sql = "insert into repair_plan(equ_id,time_true,repair_part,cost,repair_kind,status,comment,perform_kind,perform_part,org_id,time_repair,time_unit)"
                            + " values (" + repairplan.getEquId() + ",STR_TO_DATE('" + repairplan.getTimeTrue() + "','%d/%m/%Y'),'" + repairplan.getRepairPart() + "'," + repairplan.getCost() + "," + repairplan.getRepairKind() + "," + repairplan.getStatus() + ",'" + repairplan.getComment() + "'," + repairplan.getPerformKind() + ",'" + repairplan.getPerformPart() + "'," + repairplan.getOrgId() + "," + repairplan.getTimeRepair() + ",'" + repairplan.getTimeUnit() + "')";
                    System.out.println("sql ====" + sql);
                    result = DBUtil.executeInsert(sql);
                }
                break;
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
        return result;
    }

    public int deleteRepairMaterial(String detId) throws Exception {
        int result = 0;
        try {
            String sql = "Delete From repair_material Where rm_id=" + detId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchMaterial(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "m.name_vn";
                break;
            case 2:
                strFieldname = "m.code";
                break;
        }
        String sql = "";

        sql = "SELECT m.code, m.mat_id, m.name_vn AS materialName, u.* FROM material AS m LEFT JOIN unit AS u ON u.uni_id=m.uni_id WHERE kind = 1 AND 1 ";

        ResultSet rs = null;

        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by m.mat_id desc";

        ArrayList detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            RepairMaterialBean detail = null;
            while (rs.next()) {
                detail = new RepairMaterialBean();
                detail.setMatId(rs.getInt("mat_id"));
                //       detail.setContractNumber(rs.getString("econ_number"));
                detail.setMatCode(rs.getString("code"));
                detail.setMatName(StringUtil.decodeString(rs.getString("materialName")));
                //       detail.setQuantity(rs.getInt("quantity"));
                detail.setUnit(rs.getString("unit_vn"));
                //       detail.setPrice(rs.getDouble("price"));

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

    public ArrayList searchSimpleRepairPlan(int fieldid, String strFieldvalue, int equId, int assId) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "repair_part";
                break;
            case 2:
                strFieldname = "cost";
                break;
        }
        ResultSet rs = null;

        String sql = "";
        if (assId == 0) {
            sql = "SELECT * FROM repair_plan WHERE equ_id = " + equId;
        } else {
            sql = "SELECT * FROM repair_plan WHERE ass_id = " + assId;
        }

        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by rp_id desc";

        ArrayList repairplanList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            RepairPlanBean repairplan = null;
            while (rs.next()) {
                repairplan = new RepairPlanBean();
                repairplan.setRpId(rs.getInt("rp_id"));
                repairplan.setEquId(rs.getInt("equ_id"));
                //repairplan.setAssId(rs.getInt("ass_id"));
                repairplan.setOrgId(rs.getInt("org_id"));
                repairplan.setRepairPart(rs.getString("repair_part"));
                repairplan.setCost(rs.getDouble("cost"));
                repairplan.setRepairKind(rs.getString("repair_kind"));
                repairplan.setStatus(rs.getInt("status"));
                repairplan.setComment(rs.getString("comment"));
                repairplan.setPerformKind(rs.getInt("perform_kind"));
                repairplan.setPerformPart(rs.getString("perform_part"));
                repairplan.setTimeTrue(DateUtil.formatDate(rs.getDate("time_true"), "dd/MM/yyyy"));
                repairplan.setTimeRepair(rs.getInt("time_repair"));
                repairplan.setTimeUnit(rs.getString("time_unit"));
                if (rs.getInt("repair_kind") == RepairPlanBean.RK1) {
                    repairplan.setRepairKind(MCUtil.getBundleString("message.repairplan.rk1"));
                }
                if (rs.getInt("repair_kind") == RepairPlanBean.RK2) {
                    repairplan.setRepairKind(MCUtil.getBundleString("message.repairplan.rk2"));
                }
                if (rs.getInt("repair_kind") == RepairPlanBean.RK3) {
                    repairplan.setRepairKind(MCUtil.getBundleString("message.repairplan.rk3"));
                }
                repairplanList.add(repairplan);
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
        return repairplanList;

    }

    public ArrayList searchAdvRepairPlan(RepairPlanBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "";
        if (bean.getAssId() == 0) {
            sql = "SELECT * FROM repair_plan WHERE equ_id = " + bean.getEquId() + " ";
        } else {
            sql = "SELECT * FROM repair_plan WHERE ass_id = " + bean.getAssId() + " ";
        }

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

        if (!StringUtil.isBlankOrNull(bean.getRepairPart())) {
            sql = sql + " AND repair_part LIKE '%" + bean.getRepairPart() + "%'";
        }

        if (Integer.parseInt(bean.getRepairKind()) > 0) {
            sql = sql + " AND repair_kind = " + bean.getRepairKind() + "";
        }

        if (!StringUtil.isBlankOrNull(bean.getComment())) {
            sql = sql + " AND comment LIKE '%" + bean.getComment() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getPerformPart())) {
            sql = sql + " AND perform_part LIKE '%" + bean.getPerformPart() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getTimeTrue())) {
            sql = sql + " AND time_true LIKE STR_TO_DATE('" + bean.getTimeTrue() + "','%d/%m/%Y %H')";
        }

        if (bean.getTimeRepair() > 0) {
            sql = sql + " AND time_repair = " + bean.getTimeRepair() + "";
        }

        if (!StringUtil.isBlankOrNull(bean.getTimeUnit())) {
            sql = sql + " AND time_unit LIKE '%" + bean.getTimeUnit() + "%'";
        }

        sql = sql + " order by rp_id desc";

        ArrayList repairplanList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            RepairPlanBean repairplan = null;

            while (rs.next()) {
                repairplan = new RepairPlanBean();
                repairplan.setRpId(rs.getInt("rp_id"));
                repairplan.setEquId(rs.getInt("equ_id"));
                //    repairplan.setAssId(rs.getInt("ass_id"));
                repairplan.setOrgId(rs.getInt("org_id"));
                repairplan.setRepairPart(rs.getString("repair_part"));
                repairplan.setCost(rs.getDouble("cost"));
                repairplan.setRepairKind(rs.getString("repair_kind"));
                repairplan.setStatus(rs.getInt("status"));
                repairplan.setComment(rs.getString("comment"));
                repairplan.setPerformKind(rs.getInt("perform_kind"));
                repairplan.setPerformPart(rs.getString("perform_part"));
                repairplan.setTimeTrue(DateUtil.formatDate(rs.getDate("time_true"), "dd/MM/yyyy"));
                repairplan.setTimeRepair(rs.getInt("time_repair"));
                repairplan.setTimeUnit(rs.getString("time_unit"));
                if (rs.getInt("repair_kind") == RepairPlanBean.RK1) {
                    repairplan.setRepairKind(MCUtil.getBundleString("message.repairplan.rk1"));
                }
                if (rs.getInt("repair_kind") == RepairPlanBean.RK2) {
                    repairplan.setRepairKind(MCUtil.getBundleString("message.repairplan.rk2"));
                }
                if (rs.getInt("repair_kind") == RepairPlanBean.RK3) {
                    repairplan.setRepairKind(MCUtil.getBundleString("message.repairplan.rk3"));
                }
                repairplanList.add(repairplan);
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
        return repairplanList;
    }
    //Tong thoi gian BDSC tinh đen thoi diem hien tai

    public long getTotalTimeRepairPlan(int equId, String strDate) throws Exception {
        ResultSet rs = null;

        String sql = "select sum(r.time_repair) total from repair_plan as r "
                + "where r.equ_id=" + equId + " and r.time_true <= (select min(r1.time_true) as time_next "
                + "from repair_plan as r1 where r1.equ_id= " + equId + " and r1.time_true> STR_TO_DATE('" + strDate + "','%d/%m/%Y' ))";

        long result = 0;
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                result = rs.getLong("total");
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
        return result;
    }

//Ngay Bao Duong sua chua ke tiep
    public String getDateOfRepairNext(int equId, String strDate) throws Exception {
        ResultSet rs = null;

        String result = "";
        String sql = "select min(r1.time_true) as time_next from repair_plan as r1 where r1.equ_id= " + equId + " and r1.time_true> STR_TO_DATE('" + strDate + "','%d/%m/%Y' )";


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                result = DateUtil.formatDate(rs.getDate("time_next"), "dd/MM/yyyy");
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
        return result;
    }
    /*
    public boolean checkDecisionNumber(int id, String value) throws SQLException {
    
    try {
    ResultSet rs = null;
    myConnection = DBInit.dataSource.getConnection();
    
    //     System.out.println("executeQuery: " + sql);
    rs = DBUtil.executeQuery("SELECT * FROM repair_plan WHERE rp_id <> " + id + " AND decision_number = '" + value + "'");
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
    myConnection = DBInit.dataSource.getConnection();
    
    //     System.out.println("executeQuery: " + sql);
    rs = DBUtil.executeQuery("SELECT * FROM repair_plan WHERE rp_id <> " + id + " AND used_code = '" + value + "'");
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

    public ArrayList getRepairPlanForWarning(int dateAmount) throws Exception {
        ResultSet rs = null;

        String sql = "select e.manage_code, m.name_vn, rp.time_true"
                + " from repair_plan as rp "
                + " left join equipment as e on e.equ_id=rp.equ_id"
                + " left join material as m on m.mat_id=e.mat_id"
                + " where rp.warned=0 and datediff(sysdate(),time_true)>=" + dateAmount;

        ArrayList list = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            RepairPlanWarningBean detail = null;
            while (rs.next()) {
                detail = new RepairPlanWarningBean();
                detail.setRpId(rs.getInt("rp_id"));
                detail.setManageCode(rs.getString("manage_code"));
                detail.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
                detail.setTimeTrue(DateUtil.formatDate(rs.getDate("time_true"), "dd/MM/yyyy"));
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

    public void updateRepairPlanWarnedStatus(String ids) throws Exception {



        try {


            String sql = "update repair_plan set warned=1 where rp_id in (" + ids + ")";
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
