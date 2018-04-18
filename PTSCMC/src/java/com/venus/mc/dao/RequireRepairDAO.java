package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.RepairNotPlanBean;
import com.venus.mc.bean.RequireRepairBean;
import com.venus.mc.bean.RequireRepairDetailBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mai Vinh Loc
 */
public class RequireRepairDAO extends BasicDAO {

    public RequireRepairDAO() {
    }

    public ArrayList getRequireRepairs() throws Exception {

        ResultSet rs = null;

        String sql = "SELECT r.*,o.name FROM require_repair AS r LEFT JOIN employee AS e ON e.emp_id = r.created_emp LEFT JOIN organization AS o ON o.org_id=e.org_id ";

        ArrayList requireRepairList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            RequireRepairBean requireRepair = null;
            while (rs.next()) {
                requireRepair = new RequireRepairBean();
                requireRepair.setRrId(rs.getInt("rr_id"));
                requireRepair.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                requireRepair.setRequireDate(DateUtil.formatDate(rs.getDate("require_date"), "dd/MM/yyyy"));
                requireRepair.setCreatedEmp(rs.getInt("created_emp"));
                requireRepair.setRrNumber(rs.getString("rr_number"));
                requireRepair.setComment(rs.getString("comment"));
                requireRepair.setRequireOrg(rs.getString("name"));

                requireRepairList.add(requireRepair);
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
        return requireRepairList;
    }

    public RequireRepairBean getRequireRepair(int requireRepairid) throws Exception {

        ResultSet rs = null;

        String sql = "SELECT r.*,o.name FROM require_repair AS r LEFT JOIN employee AS e ON e.emp_id = r.created_emp LEFT JOIN organization AS o ON o.org_id=e.org_id where rr_id=" + requireRepairid;


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                RequireRepairBean requireRepair = new RequireRepairBean();
                requireRepair.setRrId(rs.getInt("rr_id"));
                requireRepair.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                requireRepair.setRequireDate(DateUtil.formatDate(rs.getDate("require_date"), "dd/MM/yyyy"));
                requireRepair.setCreatedEmp(rs.getInt("created_emp"));
                requireRepair.setRrNumber(rs.getString("rr_number"));
                requireRepair.setComment(rs.getString("comment"));
                requireRepair.setRequireOrg(rs.getString("name"));

                return requireRepair;
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

    public RequireRepairDetailBean getRequireRepairDetail(int detId) throws Exception {

        ResultSet rs = null;

        String sql = "select * from require_repair_detail where det_id=" + detId;


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                RequireRepairDetailBean requireRepair = new RequireRepairDetailBean();
                requireRepair.setRrId(rs.getInt("rr_id"));
                requireRepair.setEquId(rs.getInt("equ_id"));

                return requireRepair;
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

    public ArrayList getRequireRepairDetails(int rrId) throws Exception {
        ResultSet rs = null;

        String sql = "SELECT d.*, m.name_vn AS MaterialName, e.used_code, u.unit_vn FROM require_repair_detail AS d LEFT JOIN equipment AS e ON e.equ_id = d.equ_id LEFT JOIN material AS m ON m.mat_id=e.mat_id LEFT JOIN unit AS u ON u.uni_id = m.uni_id WHERE rr_id=" + rrId;

        ArrayList request_detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            RequireRepairDetailBean request_detail = null;
            while (rs.next()) {
                request_detail = new RequireRepairDetailBean();
                request_detail.setDetId(rs.getInt("det_id"));
                request_detail.setRrId(rs.getInt("rr_id"));
                request_detail.setEquId(rs.getInt("equ_id"));
                request_detail.setMatName(StringUtil.decodeString(rs.getString("MaterialName")));
                request_detail.setUnitName(rs.getString("unit_vn"));
                request_detail.setUsedCode(rs.getString("used_code"));
                request_detail.setQuantity(rs.getDouble("quantity"));
                request_detailList.add(request_detail);
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
        return request_detailList;
    }

    public int insertRequireRepair(RequireRepairBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "";
            sql = "insert into require_repair(created_date,require_date,created_emp,rr_number ,comment)"
                    + " values (SYSDATE(),STR_TO_DATE('" + bean.getRequireDate() + "','%d/%m/%Y')," + bean.getCreatedEmp() + ",'" + bean.getRrNumber() + "','" + bean.getComment() + "')";

            ////System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int insertRequireRepairDetail(RequireRepairDetailBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }

        String sql = "";
        try {
            sql = "insert into require_repair_detail(rr_id,equ_id,quantity)"
                    + " values (" + bean.getRrId() + ","
                    + bean.getEquId() + ","
                    + bean.getQuantity() + ")";

            ////System.out.println("sql ====" + sql);
            return DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public int insertRepairNotPlan(RepairNotPlanBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }

        String sql = "";
        try {
            sql = "insert into repair_not_plan(equ_id,rr_id,org_used)"
                    + " values (" + bean.getEquId() + ","
                    + bean.getRrId() + ","
                    + bean.getOrgUsed() + ")";

            ////System.out.println("sql ====" + sql);
            return DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public void updateRequireRepair(RequireRepairBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "update require_repair set "
                    + " require_date=STR_TO_DATE('" + bean.getRequireDate() + "','%d/%m/%Y')"
                    //                  + ", created_emp=" + bean.getCreatedEmp() + ""
                    + ", rr_number='" + bean.getRrNumber() + "'"
                    + ", comment='" + bean.getComment() + "'"
                    + " where rr_id=" + bean.getRrId();

            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateRequireRepairDetail(RequireRepairDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "update require_repair_detail set "
                    + " rr_id=" + bean.getRrId() + ""
                    + ", equ_id=" + bean.getEquId() + ""
                    + ", quantity=" + bean.getQuantity() + ""
                    + " where det_id=" + bean.getDetId();

            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
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
                    + " rr_id=" + bean.getRrId() + ""
                    + ", equ_id=" + bean.getEquId() + ""
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

    public int deleteRequireRepair(String requireRepairid) throws Exception {
        int result = 0;
        try {
            String sql = "delete from require_repair " + " where rr_id=" + requireRepairid;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int deleteRequireRepairDetail(String detId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from require_repair_detail " + " where det_id=" + detId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int deleteRequireRepairDetails(String rrId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from require_repair_detail " + " where rr_id=" + rrId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int deleteRepairNotPlan(String rrId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from repair_not_plan " + " where rr_id=" + rrId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int deleteRepairNotPlan(int equId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from repair_not_plan " + " where equ_id=" + equId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleRequireRepair(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "rr_number";
                break;
            case 2:
                strFieldname = "require_date";
            case 3:
                strFieldname = "name";

        }
        ResultSet rs = null;

        String sql = "SELECT r.*,o.name FROM require_repair AS r LEFT JOIN employee AS e ON e.emp_id = r.created_emp LEFT JOIN organization AS o ON o.org_id=e.org_id WHERE 1 ";
        if ((fieldid > 0) && (fieldid != 2) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        if ((fieldid == 2) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND STR_TO_DATE('" + strFieldvalue + "','%d/%m/%Y') ";
        }
        sql = sql + " order by rr_id desc";

        ArrayList requireRepairList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            RequireRepairBean requireRepair = null;
            while (rs.next()) {
                requireRepair = new RequireRepairBean();
                requireRepair.setRrId(rs.getInt("rr_id"));
                requireRepair.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                requireRepair.setRequireDate(DateUtil.formatDate(rs.getDate("require_date"), "dd/MM/yyyy"));
                requireRepair.setCreatedEmp(rs.getInt("created_emp"));
                requireRepair.setRrNumber(rs.getString("rr_number"));
                requireRepair.setComment(rs.getString("comment"));
                requireRepair.setRequireOrg(rs.getString("name"));
                requireRepairList.add(requireRepair);
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
        return requireRepairList;

    }

    public boolean checkRdNumber(int id, String rr_number) throws SQLException {
        ResultSet rs = null;

        try {


            //     System.out.println("executeQuery: " + sql);

            rs = DBUtil.executeQuery("SELECT * FROM require_repair WHERE (rr_id <> " + id + " AND rr_number = '" + rr_number + "')");
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

    public boolean checkDeleted(String id) throws SQLException {
        ResultSet rs = null;

        try {


            String sql = "";
            //     System.out.println("executeQuery: " + sql);

            sql = "SELECT * FROM require_repair WHERE rr_id =";


            rs = DBUtil.executeQuery(sql + id);
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
}
