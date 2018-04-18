package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.SurveyReportBean;
import com.venus.mc.bean.SurveyReportDetailBean;
import com.venus.mc.bean.SurveyReportMaterialBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mai Vinh Loc
 */
public class SurveyReportDAO extends BasicDAO {

    public SurveyReportDAO() {
    }

    public ArrayList getSurveyReports() throws Exception {

        ResultSet rs = null;

        String sql = "SELECT s.*,o.name FROM survey_report AS s LEFT JOIN employee AS e ON e.emp_id = s.created_emp LEFT JOIN organization AS o ON o.org_id=e.org_id";

        ArrayList surveyReportList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            SurveyReportBean surveyReport = null;
            while (rs.next()) {
                surveyReport = new SurveyReportBean();
                surveyReport.setSrId(rs.getInt("sr_id"));
                surveyReport.setSurveyDate(DateUtil.formatDate(rs.getDate("survey_date"), "dd/MM/yyyy"));
                surveyReport.setSrNumber(rs.getString("sr_number"));
                surveyReport.setUsedOrg(rs.getString("name"));
                surveyReportList.add(surveyReport);
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
        return surveyReportList;
    }

    public SurveyReportBean getSurveyReport(int surveyReportid) throws Exception {

        ResultSet rs = null;

        String sql = "select * from survey_report where sr_id=" + surveyReportid;


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                SurveyReportBean surveyReport = new SurveyReportBean();
                surveyReport.setSrId(rs.getInt("sr_id"));
                surveyReport.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                surveyReport.setSurveyDate(DateUtil.formatDate(rs.getDate("survey_date"), "dd/MM/yyyy"));
                surveyReport.setCreatedEmp(rs.getInt("created_emp"));
                surveyReport.setSrNumber(rs.getString("sr_number"));
                surveyReport.setCreatedTime(rs.getString("created_time"));
                surveyReport.setCreatedLocation(rs.getString("created_location"));
                surveyReport.setManagerEmp(rs.getInt("manager_emp"));
                surveyReport.setManagerEquipmentEmp(rs.getInt("manager_equipment_emp"));
                surveyReport.setReasonToRepair(rs.getString("reason_to_repair"));
                surveyReport.setRequest(rs.getInt("request"));
                surveyReport.setComment(rs.getString("comment"));

                return surveyReport;
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

    public ArrayList getSurveyReportDetails(int srId) throws Exception {
        ResultSet rs = null;

        String sql = "SELECT d.*, m.name_vn AS MaterialName, e.used_code, u.unit_vn FROM survey_report_detail AS d LEFT JOIN equipment AS e ON e.equ_id = d.equ_id LEFT JOIN material AS m ON m.mat_id=e.mat_id LEFT JOIN unit AS u ON u.uni_id = m.uni_id WHERE sr_id=" + srId;

        ArrayList detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            SurveyReportDetailBean detail = null;
            while (rs.next()) {
                detail = new SurveyReportDetailBean();
                detail.setDetId(rs.getInt("det_id"));
                detail.setSrId(rs.getInt("sr_id"));
                detail.setEquId(rs.getInt("equ_id"));
                detail.setMatName(StringUtil.decodeString(rs.getString("MaterialName")));
                detail.setUnitName(rs.getString("unit_vn"));
                detail.setUsedCode(rs.getString("used_code"));
                detail.setQuantity(rs.getDouble("quantity"));
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

    public ArrayList getSurveyReportMaterials(int srId) throws Exception {
        ResultSet rs = null;

        String sql = "SELECT d.*, m.name_vn AS MaterialName, m.code, u.unit_vn FROM survey_report_material AS d LEFT JOIN material AS m ON m.mat_id=d.mat_id LEFT JOIN unit AS u ON u.uni_id = m.uni_id WHERE sr_id=" + srId;

        ArrayList detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            SurveyReportMaterialBean detail = null;
            while (rs.next()) {
                detail = new SurveyReportMaterialBean();
                detail.setDetId(rs.getInt("det_id"));
                detail.setSrId(rs.getInt("sr_id"));
                detail.setMatId(rs.getInt("mat_id"));
                detail.setMatName(StringUtil.decodeString(rs.getString("MaterialName")));
                detail.setCode(rs.getString("code"));
                detail.setQt(rs.getDouble("quantity"));
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

    public int insertSurveyReport(SurveyReportBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "";
            sql = "insert into survey_report(created_date,survey_date,created_emp,sr_number,created_time, created_location,manager_emp, manager_equipment_emp, reason_to_repair, request, comment)"
                    + " values (SYSDATE(),STR_TO_DATE('" + bean.getSurveyDate() + "','%d/%m/%Y')," + bean.getCreatedEmp() + ",'" + bean.getSrNumber() + "','" + bean.getCreatedTime() + "','" + bean.getCreatedLocation() + "'," + bean.getManagerEmp() + "," + bean.getManagerEquipmentEmp() + ",'" + bean.getReasonToRepair() + "'," + bean.getRequest() + ",'" + bean.getComment() + "')";

            ////System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int insertSurveyReportDetail(SurveyReportDetailBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }

        String sql = "";
        try {
            sql = "insert into survey_report_detail(sr_id,equ_id,quantity)"
                    + " values ('" + bean.getSrId() + "','"
                    + bean.getEquId() + "','"
                    + bean.getQuantity() + "')";

            ////System.out.println("sql ====" + sql);
            return DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public int insertSurveyReportMaterial(SurveyReportMaterialBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }

        String sql = "";
        try {
            sql = "insert into survey_report_material(sr_id,mat_id,quantity)"
                    + " values ('" + bean.getSrId() + "','"
                    + bean.getMatId() + "','"
                    + bean.getQt() + "')";

            ////System.out.println("sql ====" + sql);
            return DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public void updateSurveyReport(SurveyReportBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "update survey_report set "
                    + " survey_date=STR_TO_DATE('" + bean.getSurveyDate() + "','%d/%m/%Y')"
                    + ", sr_number='" + bean.getSrNumber() + "'"
                    + ", created_time='" + bean.getCreatedTime() + "'"
                    + ", created_location='" + bean.getCreatedLocation() + "'"
                    + ", manager_emp=" + bean.getManagerEmp() + ""
                    + ", manager_equipment_emp=" + bean.getManagerEquipmentEmp() + ""
                    + ", reason_to_repair='" + bean.getReasonToRepair() + "'"
                    + ", request=" + bean.getRequest() + ""
                    + ", comment='" + bean.getComment() + "'"
                    + " where sr_id=" + bean.getSrId();

            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateSurveyReportDetail(SurveyReportDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "update survey_report_detail set "
                    + " sr_id=" + bean.getSrId() + ""
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

    public void updateSurveyReportMaterial(SurveyReportMaterialBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "update survey_report_material set "
                    + " sr_id=" + bean.getSrId() + ""
                    + ", mat_id=" + bean.getMatId() + ""
                    + ", quantity=" + bean.getQt() + ""
                    + " where det_id=" + bean.getDetId();

            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteSurveyReport(String surveyReportid) throws Exception {
        int result = 0;
        try {
            String sql = "delete from survey_report " + " where sr_id=" + surveyReportid;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int deleteSurveyReportDetail(String detId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from survey_report_detail " + " where det_id=" + detId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int deleteSurveyReportDetails(String srId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from survey_report_detail " + " where sr_id=" + srId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int deleteSurveyReportMaterial(String detId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from survey_report_material " + " where det_id=" + detId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int deleteSurveyReportMaterials(String srId) throws Exception {
        int result = 0;
        try {
            String sql = "delete from survey_report_material " + " where sr_id=" + srId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleSurveyReport(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "sr_number";
                break;
            case 2:
                strFieldname = "survey_date";
                break;
            case 3:
                strFieldname = "name";
                break;
        }
        ResultSet rs = null;

        String sql = "SELECT s.*,o.name FROM survey_report AS s LEFT JOIN employee AS e ON e.emp_id = s.created_emp LEFT JOIN organization AS o ON o.org_id=e.org_id WHERE 1 ";
        if ((fieldid > 0) && (fieldid != 2) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        if ((fieldid == 2) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND STR_TO_DATE('" + strFieldvalue + "','%d/%m/%Y') ";
        }
        sql = sql + " order by sr_id desc";

        ArrayList surveyReportList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            SurveyReportBean surveyReport = null;
            while (rs.next()) {
                surveyReport = new SurveyReportBean();
                surveyReport.setSrId(rs.getInt("sr_id"));
                surveyReport.setSurveyDate(DateUtil.formatDate(rs.getDate("survey_date"), "dd/MM/yyyy"));
                surveyReport.setSrNumber(rs.getString("sr_number"));
                surveyReport.setUsedOrg(rs.getString("name"));
                surveyReportList.add(surveyReport);
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
        return surveyReportList;

    }

    public boolean checkSrNumber(int id, String sr_number) throws SQLException {
        ResultSet rs = null;

        try {


            //     System.out.println("executeQuery: " + sql);

            rs = DBUtil.executeQuery("SELECT * FROM survey_report WHERE (sr_id <> " + id + " AND sr_number = '" + sr_number + "')");
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

            sql = "SELECT * FROM survey_report WHERE sr_id =";


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
