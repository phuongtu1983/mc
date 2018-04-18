package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ColorCodeBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mai Vinh Loc
 */
public class ColorCodeDAO extends BasicDAO {

    public ColorCodeDAO() {
    }

    public ArrayList getColorCodes()
            throws Exception {
        ResultSet rs = null;
        String sql = "select * from color_code order by cc_id desc";

        ArrayList colorCodeList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ColorCodeBean colorCode = null;
            while (rs.next()) {
                colorCode = new ColorCodeBean();
                colorCode.setCcId(rs.getInt("cc_id"));
                colorCode.setColorCode(rs.getString("color_code"));
                colorCode.setTimeApplication(rs.getString("time_application"));
                colorCode.setStartDate(DateUtil.formatDate(rs.getDate("start_date"), "dd/MM/yyyy"));
                colorCode.setEndDate(DateUtil.formatDate(rs.getDate("end_date"), "dd/MM/yyyy"));
                colorCodeList.add(colorCode);
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
        return colorCodeList;
    }

    public ColorCodeBean getColorCode(String colorCodeId) throws Exception {
        ResultSet rs = null;

        String sql = "select * from color_code where cc_id=" + colorCodeId;
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ColorCodeBean colorCode = new ColorCodeBean();
                colorCode.setCcId(rs.getInt("cc_id"));
                colorCode.setColorCode(rs.getString("color_code"));
                colorCode.setTimeApplication(rs.getString("time_application"));
                colorCode.setStartDate(DateUtil.formatDate(rs.getDate("start_date"), "dd/MM/yyyy"));
                colorCode.setEndDate(DateUtil.formatDate(rs.getDate("end_date"), "dd/MM/yyyy"));

                return colorCode;
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

    public int insertColorCode(ColorCodeBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "";
            sql = "insert into color_code(color_code,time_application,start_date,end_date)"
                    + " values ('" + bean.getColorCode() + "','" + bean.getTimeApplication() + "',STR_TO_DATE('" + bean.getStartDate() + "','%d/%m/%Y'),STR_TO_DATE('" + bean.getEndDate() + "','%d/%m/%Y'))";

            ////System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public void updateColorCode(ColorCodeBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {



            String sql = "update color_code set "
                    + " color_code='" + bean.getColorCode() + "'"
                    + ", time_application='" + bean.getTimeApplication() + "'"
                    + ", start_date=STR_TO_DATE('" + bean.getStartDate() + "','%d/%m/%Y')"
                    + ", end_date=STR_TO_DATE('" + bean.getEndDate() + "','%d/%m/%Y')"
                    + " where cc_id=" + bean.getCcId();
            ////System.out.println("sql=" + sql);
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

    public int deleteColorCode(String colorCodeId) throws Exception {


        int result = 0;
        try {


            String sql = "delete from color_code " + " where cc_id=" + colorCodeId;
            result = DBUtil.executeUpdate(sql);
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
        return result;
    }

    public ArrayList searchSimpleColorCode(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "color_code";
                break;
            case 2:
                strFieldname = "time_application";
                break;
        }
        ResultSet rs = null;

        String sql = "select * from color_code where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by cc_id desc";

        ArrayList colorCodeList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ColorCodeBean colorCode = null;
            while (rs.next()) {
                colorCode = new ColorCodeBean();
                colorCode.setCcId(rs.getInt("cc_id"));
                colorCode.setColorCode(rs.getString("color_code"));
                colorCode.setTimeApplication(rs.getString("time_application"));
                colorCode.setStartDate(DateUtil.formatDate(rs.getDate("start_date"), "dd/MM/yyyy"));
                colorCode.setEndDate(DateUtil.formatDate(rs.getDate("end_date"), "dd/MM/yyyy"));
                colorCodeList.add(colorCode);
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
        return colorCodeList;
    }

    public boolean checkColorCode(int id, String colorCode) throws SQLException {
        ResultSet rs = null;
        try {
            rs = DBUtil.executeQuery("SELECT * FROM color_code WHERE cc_id <> " + id + " AND color_code = '" + colorCode + "'");
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
            rs = DBUtil.executeQuery("SELECT * FROM equipment WHERE cc_id = " + id);
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

    public int getColorCodeId() throws Exception {
        ResultSet rs = null;

        String sql = "Select cc_id From color_code Where start_date < SYSDATE() and SYSDATE()< end_date";
        int result = 0;
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                result = rs.getInt("cc_id");
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
}
