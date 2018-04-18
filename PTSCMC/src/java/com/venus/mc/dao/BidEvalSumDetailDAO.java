/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.BidEvalSumDetailBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class BidEvalSumDetailDAO extends BasicDAO {

    public BidEvalSumDetailDAO() {
    }

    public ArrayList getBidEvalSumDetails()
            throws Exception {
        ResultSet rs = null;
        String sql = "Select * From bid_eval_sum_detail Order by det_id ASC";

        ArrayList bid_eval_sum_detailList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            BidEvalSumDetailBean bid_eval_sum_detail = null;
            while (rs.next()) {
                bid_eval_sum_detail = new BidEvalSumDetailBean();
                bid_eval_sum_detail.setDetId(rs.getInt("det_id"));
                bid_eval_sum_detail.setBesvId(rs.getInt("besv_id"));
                bid_eval_sum_detail.setMatId(rs.getInt("mat_id"));
                bid_eval_sum_detail.setQuantity(rs.getDouble("quantity"));
                bid_eval_sum_detail.setPrice(NumberUtil.formatMoneyDefault(rs.getDouble("price")));
                bid_eval_sum_detail.setTotal(NumberUtil.formatMoneyDefault(rs.getDouble("total")));
                bid_eval_sum_detailList.add(bid_eval_sum_detail);
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
        return bid_eval_sum_detailList;
    }

    public BidEvalSumDetailBean getBidEvalSumDetail(int detId) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From bid_eval_sum_detail Where det_id=" + detId;
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                BidEvalSumDetailBean bid_eval_sum_detail = new BidEvalSumDetailBean();
                bid_eval_sum_detail.setDetId(rs.getInt("det_id"));
                bid_eval_sum_detail.setBesvId(rs.getInt("besv_id"));
                bid_eval_sum_detail.setMatId(rs.getInt("mat_id"));
                bid_eval_sum_detail.setQuantity(rs.getDouble("quantity"));
                bid_eval_sum_detail.setPrice(NumberUtil.formatMoneyDefault(rs.getDouble("price")));
                bid_eval_sum_detail.setTotal(NumberUtil.formatMoneyDefault(rs.getDouble("total")));
                return bid_eval_sum_detail;
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

    public void insertBidEvalSumDetail(BidEvalSumDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {

            String sql = "";



            sql = "Insert Into bid_eval_sum_detail(besv_id, mat_id, quantity, price, total)"
                    + " Values (" + bean.getBesvId() + "," + bean.getMatId()
                    + "," + bean.getQuantity() + "," + bean.getPrice()
                    + "," + bean.getTotal() + ")";

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

    public void updateBidEvalSumDetail(BidEvalSumDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {



            String sql = "Update bid_eval_sum_detail Set "
                    + " besv_id=" + bean.getBesvId()
                    + ", mat_id=" + bean.getMatId()
                    + ", quantity=" + bean.getQuantity()
                    + ", price=" + bean.getPrice()
                    + ", total=" + bean.getTotal()
                    + " Where det_id=" + bean.getDetId();
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

    public int deleteBidEvalSumDetail(int detId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From bid_eval_sum_detail " + " Where det_id=" + detId;
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

    public ArrayList searchSimpleBidEvalSumDetail(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "besv_id";
                break;
        }
        ResultSet rs = null;
        String sql = "Select * From bid_eval_sum_detail Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " = " + strFieldvalue;
        }
        sql = sql + " Order by det_id DESC";

        ArrayList bid_eval_sum_detailList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            BidEvalSumDetailBean bid_eval_sum_detail = null;
            while (rs.next()) {
                bid_eval_sum_detail = new BidEvalSumDetailBean();
                bid_eval_sum_detail.setDetId(rs.getInt("det_id"));
                bid_eval_sum_detail.setBesvId(rs.getInt("besv_id"));
                bid_eval_sum_detail.setMatId(rs.getInt("mat_id"));
                bid_eval_sum_detail.setQuantity(rs.getDouble("quantity"));
                bid_eval_sum_detail.setPrice(NumberUtil.formatMoneyDefault(rs.getDouble("price")));
                bid_eval_sum_detail.setTotal(NumberUtil.formatMoneyDefault(rs.getDouble("total")));
                bid_eval_sum_detailList.add(bid_eval_sum_detail);
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
        return bid_eval_sum_detailList;

    }

    public ArrayList searchAdvBidEvalSumDetail(BidEvalSumDetailBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From bid_eval_sum_detail Where 1 ";

        if (bean.getDetId() != 0) {
            sql = sql + " AND det_id =" + bean.getDetId();
        }

        if (bean.getBesvId() != 0) {
            sql = sql + " AND besv_id =" + bean.getBesvId();
        }

        if (bean.getMatId() != 0) {
            sql = sql + " AND mat_id =" + bean.getMatId();
        }

        if (bean.getQuantity() != 0) {
            sql = sql + " AND quantity =" + bean.getQuantity();
        }

        if (NumberUtil.parseDouble(bean.getPrice(), 0) != 0) {
            sql = sql + " AND price =" + bean.getPrice();
        }

        if (NumberUtil.parseDouble(bean.getTotal(), 0) != 0) {
            sql = sql + " AND total =" + bean.getTotal();
        }

        sql = sql + " Order by det_id DESC";

        ArrayList bid_eval_sum_detailList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            BidEvalSumDetailBean bid_eval_sum_detail = null;
            while (rs.next()) {
                bid_eval_sum_detail = new BidEvalSumDetailBean();
                bid_eval_sum_detail.setDetId(rs.getInt("det_id"));
                bid_eval_sum_detail.setBesvId(rs.getInt("besv_id"));
                bid_eval_sum_detail.setMatId(rs.getInt("mat_id"));
                bid_eval_sum_detail.setQuantity(rs.getDouble("quantity"));
                bid_eval_sum_detail.setPrice(NumberUtil.formatMoneyDefault(rs.getDouble("price")));
                bid_eval_sum_detail.setTotal(NumberUtil.formatMoneyDefault(rs.getDouble("total")));
                bid_eval_sum_detailList.add(bid_eval_sum_detail);
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
        return bid_eval_sum_detailList;
    }
}
