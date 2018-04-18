/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.BidClosingReportBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
/**
 *
 * @author kngo
 */
public class BidClosingReportDAO extends BasicDAO {

    public BidClosingReportDAO() {
    }

    public ArrayList getBidClosingReports() throws Exception {
        ResultSet rs = null;

        String sql = "Select * From bid_closing_report Order by bcr_id ASC";

        ArrayList bid_closing_reportList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            BidClosingReportBean bid_closing_report = null;
            while (rs.next()) {
                bid_closing_report = new BidClosingReportBean();
                bid_closing_report.setBcrId(rs.getInt("bcr_id"));
                bid_closing_report.setTenId(rs.getInt("ten_id"));
                bid_closing_report.setComments(StringUtil.getString(rs.getString("comments")));
                bid_closing_reportList.add(bid_closing_report);
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
        return bid_closing_reportList;
    }

    public void insertBidClosingReport(BidClosingReportBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "";
//            sql = "Insert Into bid_closing_report(ten_id, subfix, created_date, comments)" +
//                    " Values (" + bean.getTenId() + ",'" + bean.getSubfix() +
//                    "','" + bean.getCreatedDate() + "','" + bean.getComments() + "')";
            //System.out.println("sql ====" + sql);
            DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateBidClosingReport(BidClosingReportBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "";
//            String sql = "Update bid_closing_report Set " +
//                    " ten_id=" + bean.getTenId() +
//                    ", subfix='" + bean.getSubfix() + "'" +
//                    ", created_date='" + bean.getCreatedDate() + "'" +
//                    ", comments='" + bean.getComments() + "'" +
//                    " Where bcr_id=" + bean.getBcrId();
//            //System.out.println("sql=" + sql);
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteBidClosingReport(int bcrId) throws Exception {
        int result = 0;
        try {
            String sql = "Delete From bid_closing_report " + " Where bcr_id=" + bcrId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleBidClosingReport(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "ten_id";
                break;
        }
        ResultSet rs = null;

        String sql = "Select bcr_id, pro_id, name, physical_add, kind, comments From bid_closing_report Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " = " + strFieldvalue;
        }
        sql = sql + " Order by bcr_id DESC";

        ArrayList bid_closing_reportList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            BidClosingReportBean bid_closing_report = null;
            while (rs.next()) {
                bid_closing_report = new BidClosingReportBean();
                bid_closing_report.setBcrId(rs.getInt("bcr_id"));
                bid_closing_report.setTenId(rs.getInt("ten_id"));
                bid_closing_report.setComments(StringUtil.getString(rs.getString("comments")));
                bid_closing_reportList.add(bid_closing_report);
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
        return bid_closing_reportList;
    }

    public ArrayList searchAdvBidClosingReport(BidClosingReportBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From bid_closing_report Where 1 ";

        if (bean.getBcrId() != 0) {
            sql = sql + " AND bcr_id =" + bean.getBcrId();
        }

        if (bean.getTenId() != 0) {
            sql = sql + " AND ten_id =" + bean.getTenId();
        }

//        if (!StringUtil.isBlankOrNull(bean.getSubfix())) {
//            sql = sql + " AND subfix LIKE '%" + bean.getSubfix() + "%'";
//        }

        if (bean.getCreatedDate() != null) {
            sql = sql + " AND created_date = '" + bean.getCreatedDate() + "'";
        }

        if (!StringUtil.isBlankOrNull(bean.getComments())) {
            sql = sql + " AND comments LIKE '%" + bean.getComments() + "%'";
        }
        sql = sql + " Order by bcr_id DESC";

        ArrayList bid_closing_reportList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            BidClosingReportBean bid_closing_report = null;
            while (rs.next()) {
                bid_closing_report = new BidClosingReportBean();
                bid_closing_report.setBcrId(rs.getInt("bcr_id"));
                bid_closing_report.setTenId(rs.getInt("ten_id"));
                bid_closing_report.setComments(StringUtil.getString(rs.getString("comments")));
                bid_closing_reportList.add(bid_closing_report);
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
        return bid_closing_reportList;
    }
}
