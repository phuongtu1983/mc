/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.PositionBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class PositionDAO extends BasicDAO {

    public PositionDAO() {
    }

    public ArrayList getPositions()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select * From position Order by pos_id ASC";

        ArrayList positionList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            PositionBean position = null;
            while (rs.next()) {
                position = new PositionBean();
                position.setPosId(rs.getInt("pos_id"));
                position.setName(rs.getString("name"));
                positionList.add(position);
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
        return positionList;
    }

    public PositionBean getPosition(int proId) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From position Where pos_id=" + proId;


        try {


            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                PositionBean position = new PositionBean();
                position.setPosId(rs.getInt("pos_id"));
                position.setName(rs.getString("name"));

                return position;
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

    public PositionBean getPositionByName(String name) throws Exception {
        ResultSet rs = null;

        String sql = "select pos_id from position where name='" + name + "'";


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                PositionBean position = new PositionBean();
                position.setPosId(rs.getInt("pos_id"));

                return position;
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

    public void insertPosition(PositionBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {
            String sql = "";



            sql = "Insert Into position (name)"
                    + " Values ('" + bean.getName() + "')";

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

    public void updatePosition(PositionBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {



            String sql = "Update position Set "
                    + " name='" + bean.getName() + "'"
                    + " Where pos_id=" + bean.getPosId();

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

    public int deletePosition(int proId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From position where pos_id=" + proId;
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

    public ArrayList searchSimplePosition(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "name";
                break;
        }
        ResultSet rs = null;


        String sql = "Select * From position Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by pos_id DESC";

        ArrayList positionList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            PositionBean position = null;
            while (rs.next()) {
                position = new PositionBean();
                position.setPosId(rs.getInt("pos_id"));
                position.setName(rs.getString("name"));
                positionList.add(position);
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
        return positionList;

    }

    public ArrayList searchAdvPosition(PositionBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From position Where 1";

        if (bean.getPosId() != 0) {
            sql = sql + " AND pos_id =" + bean.getPosId();
        }
        if (!StringUtil.isBlankOrNull(bean.getName())) {
            sql = sql + " AND name LIKE '%" + bean.getName() + "%'";
        }
        sql = sql + " Order by pos_id DESC";

        ArrayList positionList = new ArrayList();
        try {


            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            PositionBean position = null;

            while (rs.next()) {
                position = new PositionBean();
                position.setPosId(rs.getInt("pos_id"));
                position.setName(rs.getString("name"));
                positionList.add(position);
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
        return positionList;
    }
}
