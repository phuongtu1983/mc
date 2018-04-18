package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.OriginBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mai Vinh Loc
 */
public class OriginDAO extends BasicDAO {

    public OriginDAO() {
    }

    public ArrayList getOrigins()
            throws Exception {

        ResultSet rs = null;

        String sql = "select * from origin order by ori_id desc";

        ArrayList originList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            OriginBean origin = null;
            while (rs.next()) {
                origin = new OriginBean();
                origin.setOriId(rs.getInt("ori_id"));
                origin.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                origin.setNameEn(StringUtil.decodeString(rs.getString("name_en")));
                origin.setNote(StringUtil.getString(rs.getString("note")));

                originList.add(origin);
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
        return originList;
    }

    public OriginBean getOrigin(String originid) throws Exception {

        ResultSet rs = null;

        String sql = "select * from origin where ori_id=" + originid;


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                OriginBean origin = new OriginBean();
                origin.setOriId(rs.getInt("ori_id"));
                origin.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                origin.setNameEn(StringUtil.decodeString(rs.getString("name_en")));
                origin.setNote(StringUtil.getString(rs.getString("note")));

                return origin;
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

    public void insertOrigin(OriginBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {

            String sql = "";



            sql = "insert into origin(name_en,name_vn,note)"
                    + " values ('" + bean.getNameEn() + "','" + bean.getNameVn() + "','" + bean.getNote() + "')";

            ////System.out.println("sql ====" + sql);
            int a = DBUtil.executeUpdate(sql);
            a += 1;
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

    public void updateOrigin(OriginBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {



            String sql = "update origin set "
                    + " name_en='" + bean.getNameEn() + "'"
                    + ", name_vn='" + bean.getNameVn() + "'"
                    + ", note='" + bean.getNote() + "'"
                    + " where ori_id=" + bean.getOriId();
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

    public int deleteOrigin(String originid) throws Exception {


        int result = 0;
        try {


            String sql = "delete from origin " + " where ori_id=" + originid;
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

    public ArrayList searchSimpleOrigin(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "name_en";
                break;
            case 2:
                strFieldname = "name_vn";
                break;
        }
        ResultSet rs = null;

        String sql = "select * from origin where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by ori_id desc";

        ArrayList originList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            OriginBean origin = null;
            while (rs.next()) {
                origin = new OriginBean();
                origin.setOriId(rs.getInt("ori_id"));
                origin.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                origin.setNameEn(StringUtil.decodeString(rs.getString("name_en")));
                origin.setNote(StringUtil.getString(rs.getString("note")));
                originList.add(origin);
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
        return originList;

    }

    public ArrayList searchAdvOrigin(OriginBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "select ori_id, name_en, name_vn from origin where 1 ";

        if (!StringUtil.isBlankOrNull(bean.getNameEn())) {
            sql = sql + " AND name_en LIKE '%" + bean.getNameEn() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getNameVn())) {
            sql = sql + " AND name_vn LIKE '%" + bean.getNameVn() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getNote())) {
            sql = sql + " AND note LIKE '%" + bean.getNote() + "%'";
        }

        sql = sql + " order by ori_id desc";

        ArrayList originList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            OriginBean origin = null;

            while (rs.next()) {
                origin = new OriginBean();
                origin.setOriId(rs.getInt("ori_id"));
                origin.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                origin.setNameEn(StringUtil.decodeString(rs.getString("name_en")));
                origin.setNote(StringUtil.getString(rs.getString("note")));
                originList.add(origin);
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
        return originList;
    }

    public boolean checkNameVn(int id, String nameVn) throws SQLException {
        ResultSet rs = null;
        try {


            //     System.out.println("executeQuery: " + sql);

            rs = DBUtil.executeQuery("SELECT * FROM origin WHERE ori_id <> " + id + " AND name_vn = '" + nameVn + "'");
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

    public boolean checkNameEn(int id, String nameEn) throws SQLException {
        ResultSet rs = null;
        try {


            //     System.out.println("executeQuery: " + sql);

            rs = DBUtil.executeQuery("SELECT * FROM origin WHERE ori_id <> " + id + " AND name_en = '" + nameEn + "'");
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



            //     System.out.println("executeQuery: " + sql);

            rs = DBUtil.executeQuery("SELECT * FROM material WHERE ori_id = " + id);
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
