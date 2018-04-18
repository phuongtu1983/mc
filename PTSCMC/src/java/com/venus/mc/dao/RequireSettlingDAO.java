package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.RequireSettlingBean;
import com.venus.mc.bean.RequireSettlingDetailBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mai Vinh Loc
 */
public class RequireSettlingDAO extends BasicDAO {

    public RequireSettlingDAO() {
    }

    public ArrayList getRequireSettlings() throws Exception {

        ResultSet rs = null;

        String sql = "SELECT r.*,o.name FROM require_settling AS r LEFT JOIN employee AS e ON e.emp_id = r.created_emp LEFT JOIN organization AS o ON o.org_id=e.org_id";

        ArrayList requireSettlingList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            RequireSettlingBean requireSettling = null;
            while (rs.next()) {
                requireSettling = new RequireSettlingBean();
                requireSettling.setRsId(rs.getInt("rs_id"));
                requireSettling.setRsNumber(rs.getString("rs_number"));
                requireSettling.setRequireDate(DateUtil.formatDate(rs.getDate("require_date"), "dd/MM/yyyy"));
                requireSettling.setUsedOrg(rs.getString("name"));
                requireSettlingList.add(requireSettling);
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
        return requireSettlingList;
    }

    public RequireSettlingBean getRequireSettling(int rsId) throws Exception {

        ResultSet rs = null;

        String sql = "select * from require_settling where rs_id=" + rsId;


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                RequireSettlingBean requireSettling = new RequireSettlingBean();
                requireSettling.setRsId(rs.getInt("rs_id"));
                requireSettling.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                requireSettling.setRequireDate(DateUtil.formatDate(rs.getDate("require_date"), "dd/MM/yyyy"));
                requireSettling.setCreatedEmp(rs.getInt("created_emp"));
                requireSettling.setRsNumber(rs.getString("rs_number"));
                requireSettling.setSrId(rs.getInt("sr_id"));
                requireSettling.setProId(rs.getInt("pro_id"));
                requireSettling.setRequireOrg(rs.getInt("require_org"));
                requireSettling.setPerformOrg(rs.getInt("perform_org"));
                requireSettling.setStatus(rs.getInt("status"));

                return requireSettling;
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

    public ArrayList getRequireSettlingDetails(int rsId) throws Exception {
        ResultSet rs = null;

        String sql = "SELECT * FROM require_settling_detail WHERE rs_id=" + rsId;

        ArrayList detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            RequireSettlingDetailBean detail = null;
            while (rs.next()) {
                detail = new RequireSettlingDetailBean();
                detail.setDetId(rs.getInt("det_id"));
                detail.setRsId(rs.getInt("rs_id"));
                detail.setWorkPlan(rs.getString("work_plan"));
                detail.setContentWork(rs.getString("content_work"));
                detail.setLocation(rs.getString("location"));
                detail.setQuantity(rs.getDouble("quantity"));
                detail.setStartTimePlan(DateUtil.formatDate(rs.getDate("start_time_plan"), "dd/MM/yyyy"));
                detail.setEndTimePlan(DateUtil.formatDate(rs.getDate("end_time_plan"), "dd/MM/yyyy"));
                detail.setStartTimeReality(DateUtil.formatDate(rs.getDate("start_time_reality"), "dd/MM/yyyy"));
                detail.setEndTimeReality(DateUtil.formatDate(rs.getDate("end_time_reality"), "dd/MM/yyyy"));
                detail.setExplanation(rs.getString("explanation"));
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

    public int insertRequireSettling(RequireSettlingBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "";
            sql = "insert into require_settling(created_date,require_date,created_emp,rs_number,sr_id, pro_id,require_org, perform_org,status)"
                    + " values (sysdate(),STR_TO_DATE('" + bean.getRequireDate() + "','%d/%m/%Y')," + bean.getCreatedEmp() + ",'" + bean.getRsNumber() + "'," + bean.getSrId() + "," + bean.getProId() + "," + bean.getRequireOrg() + "," + bean.getPerformOrg() + "," + bean.getStatus() + ")";

            ////System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int insertRequireSettlingDetail(RequireSettlingDetailBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }

        String sql = "";
        try {
            sql = "insert into require_settling_detail(rs_id,work_plan,content_work, location, quantity, start_time_plan, end_time_plan, start_time_reality, end_time_reality, explanation, comment)"
                    + " values (" + bean.getRsId() + ",'"
                    + bean.getWorkPlan() + "','"
                    + bean.getContentWork() + "','"
                    + bean.getLocation() + "',"
                    + bean.getQuantity() + ","
                    + "STR_TO_DATE('" + bean.getStartTimePlan() + "','%d/%m/%Y'),"
                    + "STR_TO_DATE('" + bean.getEndTimePlan() + "','%d/%m/%Y'),"
                    + "STR_TO_DATE('" + bean.getStartTimeReality() + "','%d/%m/%Y'),"
                    + "STR_TO_DATE('" + bean.getEndTimeReality() + "','%d/%m/%Y'),'"
                    + bean.getExplanation() + "','"
                    + bean.getComment() + "')";

            ////System.out.println("sql ====" + sql);
            return DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public void updateRequireSettling(RequireSettlingBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "update require_settling set "
                    + " require_date=STR_TO_DATE('" + bean.getRequireDate() + "','%d/%m/%Y')"
                    + ", created_emp=" + bean.getCreatedEmp() + ""
                    + ", rs_number='" + bean.getRsNumber() + "'"
                    + ", sr_id=" + bean.getSrId() + ""
                    + ", pro_id=" + bean.getProId() + ""
                    + ", require_org=" + bean.getRequireOrg() + ""
                    + ", perform_org=" + bean.getPerformOrg() + ""
                    + ", status='" + bean.getStatus() + "'"
                    + " where rs_id=" + bean.getRsId();

            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateRequireSettlingDetail(RequireSettlingDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "update require_settling_detail set "
                    + " rs_id=" + bean.getRsId() + ""
                    + ", work_plan=" + bean.getWorkPlan() + ""
                    + ", content_work=" + bean.getContentWork() + ""
                    + ", location=" + bean.getLocation() + ""
                    + ", quantity=" + bean.getQuantity() + ""
                    + ", start_time_plan=STR_TO_DATE('" + bean.getStartTimePlan() + "','%d/%m/%Y')"
                    + ", end_time_plan=STR_TO_DATE('" + bean.getEndTimePlan() + "','%d/%m/%Y')"
                    + ", start_time_reality=STR_TO_DATE('" + bean.getStartTimeReality() + "','%d/%m/%Y')"
                    + ", end_time_reality=STR_TO_DATE('" + bean.getEndTimeReality() + "','%d/%m/%Y')"
                    + ", explanation='" + bean.getExplanation() + "'"
                    + ", comment=" + bean.getComment() + ""
                    + " where det_id=" + bean.getDetId();

            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteRequireSettling(String requireSettlingid) throws Exception {
        int result = 0;
        try {
            String sql = "delete from require_settling " + " where rs_id=" + requireSettlingid;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int deleteRequireSettlingDetail(String detId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from require_settling_detail " + " where det_id=" + detId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int deleteRequireSettlingDetails(String rsId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from require_settling_detail " + " where rs_id=" + rsId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleRequireSettling(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "rs_number";
                break;

            case 2:
                strFieldname = "require_date";

            case 3:
                strFieldname = "name";

        }
        ResultSet rs = null;

        String sql = "SELECT r.*,o.name FROM require_settling AS r LEFT JOIN employee AS e ON e.emp_id = r.created_emp LEFT JOIN organization AS o ON o.org_id=e.org_id WHERE 1 ";
        if ((fieldid > 0) && (fieldid != 2) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        if ((fieldid == 2) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND STR_TO_DATE('" + strFieldvalue + "','%d/%m/%Y') ";
        }
        sql = sql + " order by rs_id desc";

        ArrayList requireSettlingList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            RequireSettlingBean requireSettling = null;
            while (rs.next()) {
                requireSettling = new RequireSettlingBean();
                requireSettling.setRsId(rs.getInt("rs_id"));
                requireSettling.setRsNumber(rs.getString("rs_number"));
                requireSettling.setRequireDate(DateUtil.formatDate(rs.getDate("require_date"), "dd/MM/yyyy"));
                requireSettling.setUsedOrg(rs.getString("name"));
                requireSettlingList.add(requireSettling);
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
        return requireSettlingList;

    }

    public boolean checkRsNumber(int id, String rs_number) throws SQLException {
        ResultSet rs = null;

        try {


            //     System.out.println("executeQuery: " + sql);

            rs = DBUtil.executeQuery("SELECT * FROM require_settling WHERE (rs_id <> " + id + " AND rs_number = '" + rs_number + "')");
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

            sql = "SELECT * FROM require_settling WHERE rs_id =";


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
