/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.BidEvalSumGroupBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class BidEvalSumGroupDAO extends BasicDAO {

    public BidEvalSumGroupDAO() {
    }

    public ArrayList getBidEvalSumGroups()
            throws Exception {
        ResultSet rs = null;
        String sql = "Select * From bid_eval_sum_group Order by besg_id ASC";

        ArrayList bid_eval_sum_groupList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            BidEvalSumGroupBean bid_eval_sum_group = null;
            while (rs.next()) {
                bid_eval_sum_group = new BidEvalSumGroupBean();
                bid_eval_sum_group.setBesgId(rs.getInt("besg_id"));
                bid_eval_sum_group.setBesId(rs.getInt("bes_id"));
                bid_eval_sum_group.setEmployee(rs.getString("employee"));
                bid_eval_sum_group.setPosition(rs.getString("position"));
                bid_eval_sum_groupList.add(bid_eval_sum_group);
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
        return bid_eval_sum_groupList;
    }

    public BidEvalSumGroupBean getBidEvalSumGroup(int besgId) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From bid_eval_sum_group Where besg_id=" + besgId;
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                BidEvalSumGroupBean bid_eval_sum_group = new BidEvalSumGroupBean();
                bid_eval_sum_group.setBesgId(rs.getInt("besg_id"));
                bid_eval_sum_group.setBesId(rs.getInt("bes_id"));
                bid_eval_sum_group.setEmployee(rs.getString("employee"));
                bid_eval_sum_group.setPosition(rs.getString("position"));
                return bid_eval_sum_group;
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

    public void insertBidEvalSumGroup(BidEvalSumGroupBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Insert Into bid_eval_sum_group(bes_id, employee, position)"
                    + " Values (" + bean.getBesId() + ",'" + bean.getEmployee()
                    + "','" + bean.getPosition() + "')";
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateBidEvalSumGroup(BidEvalSumGroupBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Update bid_eval_sum_group Set "
                    + " bes_id=" + bean.getBesId()
                    + ", employee='" + bean.getEmployee()
                    + "', position='" + bean.getPosition()
                    + "' Where besg_id=" + bean.getBesgId();
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteBidEvalSumGroup(int besgId) throws Exception {
        int result = 0;
        try {
            String sql = "Delete From bid_eval_sum_group " + " Where besg_id=" + besgId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleBidEvalSumGroup(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "bes_id";
                break;
        }
        ResultSet rs = null;
        String sql = "Select * From bid_eval_sum_group Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " = " + strFieldvalue;
        }
        sql = sql + " Order by besg_id DESC";

        ArrayList bid_eval_sum_groupList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            BidEvalSumGroupBean bid_eval_sum_group = null;
            while (rs.next()) {
                bid_eval_sum_group = new BidEvalSumGroupBean();
                bid_eval_sum_group.setBesgId(rs.getInt("besg_id"));
                bid_eval_sum_group.setBesId(rs.getInt("bes_id"));
                bid_eval_sum_group.setEmployee(rs.getString("employee"));
                bid_eval_sum_group.setPosition(rs.getString("position"));
                bid_eval_sum_groupList.add(bid_eval_sum_group);
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
        return bid_eval_sum_groupList;

    }

    public ArrayList searchAdvBidEvalSumGroup(BidEvalSumGroupBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select besg_id, pro_id, name, physical_add, kind, comments From bid_eval_sum_group Where 1 ";

        if (bean.getBesgId() != 0) {
            sql = sql + " AND besg_id = " + bean.getBesgId();
        }

        if (bean.getBesId() != 0) {
            sql = sql + " AND bes_id = " + bean.getBesId();
        }

        if (!StringUtil.isBlankOrNull(bean.getEmployee())) {
            sql = sql + " AND employee LIKE '" + bean.getEmployee() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getPosition())) {
            sql = sql + " AND position LIKE '%" + bean.getPosition() + "%'";
        }

        sql = sql + " Order by besg_id DESC";

        ArrayList bid_eval_sum_groupList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            BidEvalSumGroupBean bid_eval_sum_group = null;
            while (rs.next()) {
                bid_eval_sum_group = new BidEvalSumGroupBean();
                bid_eval_sum_group.setBesgId(rs.getInt("besg_id"));
                bid_eval_sum_group.setBesId(rs.getInt("bes_id"));
                bid_eval_sum_group.setEmployee(rs.getString("employee"));
                bid_eval_sum_group.setPosition(rs.getString("position"));
                bid_eval_sum_groupList.add(bid_eval_sum_group);
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
        return bid_eval_sum_groupList;
    }
}
