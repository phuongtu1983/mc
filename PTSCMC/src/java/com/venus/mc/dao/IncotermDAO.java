package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.IncotermBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mai Vinh Loc
 */
public class IncotermDAO extends BasicDAO {

    public IncotermDAO() {
    }

    public ArrayList getIncoterms() throws Exception {

        ResultSet rs = null;

        String sql = "SELECT * FROM incoterm order by inc_id DESC";

        ArrayList cerList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            IncotermBean incoterm = null;
            while (rs.next()) {
                incoterm = new IncotermBean();
                incoterm.setIncId(rs.getInt("inc_id"));
                incoterm.setIncName(rs.getString("inc_name"));
                incoterm.setComment(rs.getString("comment"));
                cerList.add(incoterm);
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
        return cerList;
    }

    public IncotermBean getIncoterm(int incId) throws Exception {
        ResultSet rs = null;

        String sql = "SELECT incoterm.* From incoterm WHERE inc_id=" + incId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                IncotermBean incoterm = new IncotermBean();
                incoterm.setIncId(rs.getInt("inc_id"));
                incoterm.setIncName(StringUtil.decodeString(rs.getString("inc_name")));
                incoterm.setComment(rs.getString("comment"));

                return incoterm;
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

    public IncotermBean getIncotermByName(String inc_name) throws Exception {
        ResultSet rs = null;

        String sql = "Select inc_id From incoterm Where inc_name = '" + inc_name + "'";


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                IncotermBean incoterm = new IncotermBean();
                incoterm.setIncId(rs.getInt("inc_id"));

                return incoterm;
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

    public void insertIncoterm(IncotermBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {

            String sql = "";



            sql = "Insert Into incoterm(inc_name, comment)"
                    + " Values ('" + bean.getIncName() + "','" + bean.getComment() + "')";

            //System.out.println("sql ====" + sql);
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

    public void updateIncoterm(IncotermBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {



            String sql = "Update incoterm Set "
                    + " inc_name='" + bean.getIncName() + "'"
                    + ", comment='" + bean.getComment() + "'"
                    + " Where inc_id=" + bean.getIncId();

            //System.out.println("sql=" + sql);
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

    public int deleteIncoterm(int incId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From incoterm Where inc_id = " + incId;
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

    public ArrayList searchSimpleIncoterm(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "inc_name";
            case 2:
                strFieldname = "comment";
                break;
        }
        ResultSet rs = null;

        String sql = "Select incoterm.* From incoterm Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by inc_id DESC";

        ArrayList incotermList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            IncotermBean incoterm = null;
            while (rs.next()) {
                incoterm = new IncotermBean();
                incoterm.setIncId(rs.getInt("inc_id"));
                incoterm.setIncName(rs.getString("inc_name"));
                incoterm.setComment(rs.getString("comment"));
                incotermList.add(incoterm);
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
        return incotermList;
    }
}
