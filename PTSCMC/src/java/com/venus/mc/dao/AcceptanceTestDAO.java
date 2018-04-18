package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.AcceptanceTestBean;
import com.venus.mc.bean.AcceptanceTestDetailBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;

/**
 * @author Mai Vinh Loc
 */
public class AcceptanceTestDAO extends BasicDAO {

    public AcceptanceTestDAO() {
    }

    public ArrayList getAcceptanceTests() throws Exception {
        ResultSet rs = null;
        String sql = "SELECT a.*,o.name FROM acceptance_test AS a LEFT JOIN employee AS e ON e.emp_id = a.created_emp LEFT JOIN organization AS o ON o.org_id=e.org_id";
        ArrayList acceptanceTestList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            AcceptanceTestBean acceptanceTest = null;
            while (rs.next()) {
                acceptanceTest = new AcceptanceTestBean();
                acceptanceTest.setAtId(rs.getInt("at_id"));
                acceptanceTest.setSrId(rs.getInt("sr_id"));
                acceptanceTest.setTestDate(DateUtil.formatDate(rs.getDate("test_date"), "dd/MM/yyyy"));
                acceptanceTest.setAtNumber(rs.getString("at_number"));
                acceptanceTest.setUsedOrg(rs.getString("name"));
                acceptanceTestList.add(acceptanceTest);
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
        return acceptanceTestList;
    }

    public AcceptanceTestBean getAcceptanceTest(int acceptanceTestid) throws Exception {
        ResultSet rs = null;
        String sql = "select * from acceptance_test where at_id=" + acceptanceTestid;
        AcceptanceTestBean acceptanceTest = null;
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                acceptanceTest = new AcceptanceTestBean();
                acceptanceTest.setAtId(rs.getInt("at_id"));
                acceptanceTest.setSrId(rs.getInt("sr_id"));
                acceptanceTest.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                acceptanceTest.setTestDate(DateUtil.formatDate(rs.getDate("test_date"), "dd/MM/yyyy"));
                acceptanceTest.setCreatedEmp(rs.getInt("created_emp"));
                acceptanceTest.setAtNumber(rs.getString("at_number"));
                acceptanceTest.setCreatedTime(rs.getString("created_time"));
                acceptanceTest.setCreatedLocation(rs.getString("created_location"));
                acceptanceTest.setManagerEmp(rs.getInt("manager_emp"));
                acceptanceTest.setManagerEquipmentEmp(rs.getInt("manager_equipment_emp"));
                acceptanceTest.setManagerQAEmp(rs.getInt("manager_QA_emp"));
                acceptanceTest.setResultAfterRepair(rs.getString("result_after_repair"));
                acceptanceTest.setResult(rs.getInt("result"));
                acceptanceTest.setComment(rs.getString("result_comment"));
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
        return acceptanceTest;
    }

    public ArrayList getAcceptanceTestDetails(int atId) throws Exception {
        ResultSet rs = null;
        String sql = "SELECT d.*, m.name_vn AS MaterialName, e.used_code, u.unit_vn FROM acceptance_test_detail AS d LEFT JOIN equipment AS e ON e.equ_id = d.equ_id LEFT JOIN material AS m ON m.mat_id=e.mat_id LEFT JOIN unit AS u ON u.uni_id = m.uni_id WHERE at_id=" + atId;

        ArrayList request_detailList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            AcceptanceTestDetailBean request_detail = null;
            while (rs.next()) {
                request_detail = new AcceptanceTestDetailBean();
                request_detail.setDetId(rs.getInt("det_id"));
                request_detail.setAtId(rs.getInt("at_id"));
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

    public ArrayList getSrListOfOrg(int orgId) throws Exception {
        ResultSet rs = null;
        String sql = "select sr_id,sr_number from survey_report as r"
                + " left join employee as e on e.emp_id = r.created_emp"
                + " where e.org_id=" + orgId;

        ArrayList srList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            LabelValueBean sr = null;
            while (rs.next()) {
                sr = new LabelValueBean();
                sr.setValue(rs.getString("sr_id"));
                sr.setLabel(rs.getString("sr_number"));
                srList.add(sr);
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
        return srList;
    }

    public int insertAcceptanceTest(AcceptanceTestBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "";
            sql = "insert into acceptance_test(created_date,test_date,created_emp,at_number,created_time, created_location,manager_emp, manager_equipment_emp, manager_QA_emp,result_after_repair, result, result_comment, sr_id)"
                    + " values (sysdate(),STR_TO_DATE('" + bean.getTestDate() + "','%d/%m/%Y')," + bean.getCreatedEmp() + ",'" + bean.getAtNumber() + "','" + bean.getCreatedTime() + "','" + bean.getCreatedLocation() + "'," + bean.getManagerEmp() + "," + bean.getManagerEquipmentEmp() + "," + bean.getManagerQAEmp() + ",'" + bean.getResultAfterRepair() + "'," + bean.getResult() + ",'" + bean.getComment() + "'," + bean.getSrId() + ")";

            ////System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int insertAcceptanceTestDetail(AcceptanceTestDetailBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }

        String sql = "";
        try {
            sql = "insert into acceptance_test_detail(at_id,equ_id,quantity)"
                    + " values (" + bean.getAtId() + ","
                    + bean.getEquId() + ","
                    + bean.getQuantity() + ")";

            ////System.out.println("sql ====" + sql);
            return DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public void updateAcceptanceTest(AcceptanceTestBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "update acceptance_test set "
                    + " test_date=STR_TO_DATE('" + bean.getTestDate() + "','%d/%m/%Y')"
                    + ", created_emp=" + bean.getCreatedEmp() + ""
                    + ", at_number=" + bean.getAtNumber() + ""
                    + ", created_time='" + bean.getCreatedTime() + "'"
                    + ", created_location='" + bean.getCreatedLocation() + "'"
                    + ", manager_emp=" + bean.getManagerEmp() + ""
                    + ", manager_equipment_emp=" + bean.getManagerEquipmentEmp() + ""
                    + ", manager_QA_emp=" + bean.getManagerQAEmp() + ""
                    + ", result_after_repair='" + bean.getResultAfterRepair() + "'"
                    + ", result=" + bean.getResult() + ""
                    + ", result_comment='" + bean.getComment() + "'"
                    + ", sr_id=" + bean.getSrId() + ""
                    + " where at_id=" + bean.getAtId();

            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateAcceptanceTestDetail(AcceptanceTestDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "update acceptance_test_detail set "
                    + " at_id=" + bean.getAtId() + ""
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

    public int deleteAcceptanceTest(String acceptanceTestid) throws Exception {
        int result = 0;
        try {
            String sql = "delete from acceptance_test " + " where at_id=" + acceptanceTestid;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int deleteAcceptanceTestDetail(String detId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from acceptance_test_detail " + " where det_id=" + detId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int deleteAcceptanceTestDetails(String atId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from acceptance_test_detail " + " where at_id=" + atId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleAcceptanceTest(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "at_number";
                break;
            case 2:
                strFieldname = "test_date";
                break;
            case 3:
                strFieldname = "name";
                break;
        }
        ResultSet rs = null;
        String sql = "SELECT a.*,o.name FROM acceptance_test AS a LEFT JOIN employee AS e ON e.emp_id = a.created_emp LEFT JOIN organization AS o ON o.org_id=e.org_id WHERE 1 ";
        if ((fieldid > 0) && (fieldid != 2) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        if ((fieldid == 2) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND STR_TO_DATE('" + strFieldvalue + "','%d/%m/%Y') ";
        }
        sql = sql + " order by at_id desc";

        ArrayList acceptanceTestList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            AcceptanceTestBean acceptanceTest = null;
            while (rs.next()) {
                acceptanceTest = new AcceptanceTestBean();
                acceptanceTest.setAtId(rs.getInt("at_id"));
                acceptanceTest.setSrId(rs.getInt("sr_id"));
                acceptanceTest.setTestDate(DateUtil.formatDate(rs.getDate("test_date"), "dd/MM/yyyy"));
                acceptanceTest.setAtNumber(rs.getString("at_number"));
                acceptanceTest.setUsedOrg(rs.getString("name"));
                acceptanceTestList.add(acceptanceTest);
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
        return acceptanceTestList;

    }

    public boolean checkAtNumber(int id, String at_number) throws SQLException {
        boolean result = false;
        ResultSet rs = null;
        try {
            rs = DBUtil.executeQuery("SELECT * FROM acceptance_test WHERE (at_id <> " + id + " AND at_number = '" + at_number + "')");
            if (rs.next()) {
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return result;
    }

    public boolean checkDeleted(String id) throws SQLException {
        boolean result = false;
        ResultSet rs = null;
        try {

            String sql = "SELECT * FROM acceptance_test WHERE at_id =";
            rs = DBUtil.executeQuery(sql + id);
            if (rs.next()) {
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return result;
    }
}
