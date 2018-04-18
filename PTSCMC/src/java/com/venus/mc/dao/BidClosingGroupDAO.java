/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.BidClosingGroupBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class BidClosingGroupDAO extends BasicDAO {

    public BidClosingGroupDAO() {
    }

    public ArrayList getBidClosingGroups() throws Exception {
        ResultSet rs = null;
        String sql = "Select * From bid_closing_group Order by bcg_id ASC";

        ArrayList bid_closing_groupList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            BidClosingGroupBean bid_closing_group = null;
            while (rs.next()) {
                bid_closing_group = new BidClosingGroupBean();
                bid_closing_group.setBcgId(rs.getInt("bcg_id"));
                bid_closing_group.setBcrId(rs.getInt("bcr_id"));
                bid_closing_group.setEmployee(rs.getString("employee"));
                bid_closing_group.setPosition(rs.getString("position"));
                bid_closing_groupList.add(bid_closing_group);
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
        return bid_closing_groupList;
    }

    public void insertBidClosingGroup(BidClosingGroupBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "";
            sql = "Insert Into bid_closing_group(bcr_id, employee, position)"
                    + " Values (" + bean.getBcrId() + ",'" + bean.getEmployee()
                    + "','" + bean.getPosition() + "')";
            DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateBidClosingGroup(BidClosingGroupBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Update bid_closing_group Set "
                    + " bcr_id=" + bean.getBcrId()
                    + ", employee='" + bean.getEmployee() + "'"
                    + ", position='" + bean.getPosition() + "'"
                    + " Where bcg_id=" + bean.getBcgId();
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteBidClosingGroup(int bcgId) throws Exception {
        int result = 0;
        try {
            String sql = "Delete From bid_closing_group " + " Where bcg_id=" + bcgId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleBidClosingGroup(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "employee";
                break;
        }
        ResultSet rs = null;

        String sql = "Select * From bid_closing_group Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by bcg_id DESC";

        ArrayList bid_closing_groupList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            BidClosingGroupBean bid_closing_group = null;
            while (rs.next()) {
                bid_closing_group = new BidClosingGroupBean();
                bid_closing_group.setBcgId(rs.getInt("bcg_id"));
                bid_closing_group.setBcrId(rs.getInt("bcr_id"));
                bid_closing_group.setEmployee(rs.getString("employee"));
                bid_closing_group.setPosition(rs.getString("position"));
                bid_closing_groupList.add(bid_closing_group);
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
        return bid_closing_groupList;

    }

    public ArrayList searchAdvBidClosingGroup(BidClosingGroupBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From bid_closing_group Where 1 ";

        if (bean.getBcgId() != 0) {
            sql = sql + " AND bcg_id =" + bean.getBcgId();
        }

        if (bean.getBcrId() != 0) {
            sql = sql + " AND bcr_id =" + bean.getBcrId();
        }


        if (!StringUtil.isBlankOrNull(bean.getEmployee())) {
            sql = sql + " AND employee LIKE '%" + bean.getEmployee() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getPosition())) {
            sql = sql + " AND position LIKE '%" + bean.getPosition() + "%'";
        }

        sql = sql + " Order by bcg_id DESC";

        ArrayList bid_closing_groupList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            BidClosingGroupBean bid_closing_group = null;

            while (rs.next()) {
                bid_closing_group = new BidClosingGroupBean();
                bid_closing_group.setBcgId(rs.getInt("bcg_id"));
                bid_closing_group.setBcrId(rs.getInt("bcr_id"));
                bid_closing_group.setEmployee(rs.getString("employee"));
                bid_closing_group.setPosition(rs.getString("position"));
                bid_closing_groupList.add(bid_closing_group);
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
        return bid_closing_groupList;
    }
}
