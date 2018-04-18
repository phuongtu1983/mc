/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.BidEvalSumVendorBean;
import com.venus.mc.bean.TenderEvaluateVendorBean;
import com.venus.mc.bean.TenderPlanDetailBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class BidEvalSumVendorDAO extends BasicDAO {

    public BidEvalSumVendorDAO() {
    }

    public ArrayList getBidEvalSumVendors()
            throws Exception {
        ResultSet rs = null;
        String sql = "Select * From bid_eval_sum_vendor Order by besv_id ASC";

        ArrayList bid_eval_sum_vendorList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            BidEvalSumVendorBean bid_eval_sum_vendor = null;
            while (rs.next()) {
                bid_eval_sum_vendor = new BidEvalSumVendorBean();
                bid_eval_sum_vendor.setBesvId(rs.getInt("besv_id"));
                bid_eval_sum_vendor.setBesId(rs.getInt("bes_id"));
                bid_eval_sum_vendor.setBesId(rs.getInt("ven_id"));
                bid_eval_sum_vendor.setDeliveryTime(rs.getString("delivery_time"));
                bid_eval_sum_vendor.setPaymentMode(rs.getString("payment_mode"));
                bid_eval_sum_vendorList.add(bid_eval_sum_vendor);
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
        return bid_eval_sum_vendorList;
    }

    public BidEvalSumVendorBean getBidEvalSumVendor(int besvId, int tenId) throws Exception {
        ResultSet rs = null;
        String sql2 = "SELECT eval_kind FROM tender_plan WHERE ten_id = " + tenId;
        int evalKind = 0;
        ResultSet rs1 = null;
        rs = DBUtil.executeQuery(sql2);
        while (rs.next()) {
            evalKind = rs.getInt("eval_kind");
        }
        if (rs != null) {
            DBUtil.closeConnection(rs);
        }
        String sql = "";
        if (evalKind == 0) { //0: tron goi; 1:tung phan
            sql = "SELECT v.*,d.name,d.ven_id,b.delivery_time,b.besv_id,b.bes_id,b.payment_mode FROM com_eval_vendor AS v LEFT JOIN com_eval AS e ON e.ce_id=v.ce_id LEFT JOIN tech_eval_vendor AS t ON t.ter_id=v.ter_id LEFT JOIN tender_evaluate_vendor AS r ON r.tev_id=t.tev_id LEFT JOIN vendor AS d ON d.ven_id=r.ven_id LEFT JOIN bid_eval_sum AS s ON s.ten_id=e.ten_id LEFT JOIN bid_eval_sum_vendor AS b ON b.bes_id=s.bes_id WHERE b.besv_id=" + besvId + " AND v.rand=1 AND e.ten_id = " + tenId + " Order by d.name ASC";
        } else {
            sql = "SELECT d.name,d.ven_id,b.delivery_time,b.besv_id,s.bes_id,b.payment_mode FROM com_eval_material_vendor AS v LEFT JOIN tech_eval_vendor AS te ON te.ter_id=v.ter_id LEFT JOIN tender_evaluate_vendor AS t ON t.tev_id=te.tev_id LEFT JOIN com_eval AS e ON e.ce_id=v.ce_id LEFT JOIN vendor AS d ON d.ven_id=t.ven_id LEFT JOIN bid_eval_sum AS s ON s.ten_id=e.ten_id LEFT JOIN bid_eval_sum_vendor AS b ON b.bes_id=s.bes_id WHERE b.ven_id=d.ven_id AND b.besv_id=" + besvId + " AND e.ten_id = " + tenId + " GROUP BY d.name Order by d.name ASC";
        }
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                BidEvalSumVendorBean bid_eval_sum_vendor = new BidEvalSumVendorBean();
                bid_eval_sum_vendor.setBesvId(rs.getInt("besv_id"));
                bid_eval_sum_vendor.setBesId(rs.getInt("bes_id"));
                bid_eval_sum_vendor.setVenId(rs.getInt("ven_id"));
                bid_eval_sum_vendor.setTenId(tenId);
                bid_eval_sum_vendor.setDeliveryTime(rs.getString("delivery_time"));
                bid_eval_sum_vendor.setPaymentMode(rs.getString("payment_mode"));
                if (evalKind == 0) { //0: tron goi; 1:tung phan
                    bid_eval_sum_vendor.setCostTransport(NumberUtil.formatMoneyDefault(rs.getDouble("cost_transport")));
                    bid_eval_sum_vendor.setOtherCost(NumberUtil.formatMoneyDefault(rs.getDouble("other_cost")));
                    bid_eval_sum_vendor.setOtherTax(NumberUtil.formatMoneyDefault(rs.getDouble("other_tax")));
                    bid_eval_sum_vendor.setSum(NumberUtil.formatMoneyDefault(rs.getDouble("sum")));
                } else {
                    bid_eval_sum_vendor.setCostTransport("0");
                    bid_eval_sum_vendor.setOtherCost("0");
                    bid_eval_sum_vendor.setOtherTax("0");
                    bid_eval_sum_vendor.setSum("0");
                }
                String sql1 = "SELECT v.name FROM vendor AS v WHERE ven_id=" + rs.getInt("ven_id");
                String vendorName = "";

                rs1 = DBUtil.executeQuery(sql1);
                while (rs1.next()) {
                    vendorName = rs1.getString("v.name");
                }
                if (rs1 != null) {
                    DBUtil.closeConnection(rs1);
                }
                bid_eval_sum_vendor.setVendorName(StringUtil.decodeString(vendorName));
                return bid_eval_sum_vendor;
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

    public void insertBidEvalSumVendor(BidEvalSumVendorBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Insert Into bid_eval_sum_vendor(bes_id, ven_id, delivery_time, payment_mode)"
                    + " Values (" + bean.getBesId() + "," + bean.getVenId()
                    + ",'" + bean.getDeliveryTime() + ",'" + bean.getPaymentMode() + "')";
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void insertBidEvalSumMaterialForBidEvalSum(int besId, int tenId) throws Exception {
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {
            String sql = "";
            String sql1 = "";
            String sql2 = "";
            String sql3 = "";
            int besvId = 0;

            String sql4 = "SELECT eval_kind FROM tender_plan WHERE ten_id = " + tenId;
            int evalKind = 0;
            rs = DBUtil.executeQuery(sql4);
            while (rs.next()) {
                evalKind = rs.getInt("eval_kind");
            }
            DBUtil.closeConnection(rs);
            if (evalKind == 0) { //0: tron goi; 1:tung phan
                sql = "SELECT d.name,d.ven_id,ted.delivery_time,b.besv_id,v.ter_id,v.cev_id FROM com_eval_vendor AS v LEFT JOIN com_eval AS e ON e.ce_id=v.ce_id LEFT JOIN tech_eval_vendor AS t ON t.ter_id=v.ter_id LEFT JOIN tender_evaluate_vendor AS r ON r.tev_id=t.tev_id LEFT JOIN vendor AS d ON d.ven_id=r.ven_id LEFT JOIN bid_eval_sum AS s ON s.ten_id=e.ten_id LEFT JOIN bid_eval_sum_vendor AS b ON b.bes_id=s.bes_id left join tech_eval_detail as ted on ted.ter_id = v.ter_id WHERE b.besv_id IS NULL AND v.rand=1 AND e.ten_id = " + tenId + " GROUP BY v.ter_id ";
            } else {
                sql = "SELECT d.name,d.ven_id,v.ter_id,ted.delivery_time,b.besv_id FROM com_eval_material_vendor AS v LEFT JOIN com_eval_material_detail AS ce ON ce.cem_id=v.cem_id LEFT JOIN com_eval AS e ON e.ce_id=v.ce_id LEFT JOIN tech_eval_vendor AS te ON te.ter_id=v.ter_id LEFT JOIN tender_evaluate_vendor AS ve ON ve.tev_id=te.tev_id LEFT JOIN vendor AS d ON d.ven_id=ve.ven_id LEFT JOIN bid_eval_sum AS s ON s.ten_id=e.ten_id LEFT JOIN bid_eval_sum_vendor AS b ON b.bes_id=s.bes_id left join tech_eval_detail as ted on ted.ter_id = v.ter_id WHERE b.besv_id IS NULL AND ce.result=1 AND e.ten_id = " + tenId + " GROUP BY d.name Order by d.name ASC";
            }
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                sql1 = "Insert Into bid_eval_sum_vendor(bes_id,ven_id,delivery_time)"
                        + " Values (" + besId + "," + rs.getInt("ven_id") + ",'" + rs.getString("delivery_time") + "')";
                besvId = DBUtil.executeInsert(sql1);
                if (evalKind == 0) { //0: tron goi; 1:tung phan
                    sql2 = "SELECT td.reqDetail_id, t1.qty AS qt, t1.*, t2.price AS prices,t2.total AS totals,t2.unit FROM tech_eval_detail AS t1 LEFT JOIN com_eval_detail AS t2 ON t2.det_id_tp=t1.det_id_tp LEFT JOIN tender_plan_detail AS td ON td.det_id = t1.det_id_tp WHERE t2.cev_id=" + rs.getInt("cev_id") + " AND t1.ter_id=" + rs.getInt("ter_id") + " GROUP BY td.det_id ORDER BY t1.det_id_tp ";
                } else {
                    sql2 = "SELECT td.reqDetail_id, t1.qty AS qt, t1.*, t2.price_ptscmc AS prices,t2.total AS totals,t2.unit FROM tech_eval_detail AS t1 LEFT JOIN com_eval_material_detail AS t2 ON t2.det_id_tp=t1.det_id_tp LEFT JOIN com_eval_material_vendor AS v ON v.cem_id = t2.cem_id LEFT JOIN tender_plan_detail AS td ON td.det_id = t1.det_id_tp WHERE t1.ter_id=" + rs.getInt("ter_id") + " AND t2.result = 1 AND v.ter_id = t1.ter_id GROUP BY td.reqDetail_id ORDER BY t1.det_id_tp ";
                }

                rs2 = DBUtil.executeQuery(sql2);
                while (rs2.next()) {
                    sql3 = "Insert Into bid_eval_sum_detail(besv_id, mat_id,quantity,price,total,unit,req_detail_id)"
                            + " Values (" + besvId + "," + rs2.getInt("mat_id") + "," + rs2.getInt("qt") + "," + rs2.getDouble("prices") + "," + rs2.getDouble("totals") + ",'" + rs2.getString("unit") + "'," + rs2.getInt("reqDetail_id") + ")";
                    DBUtil.executeInsert(sql3);
                }
                DBUtil.closeConnection(rs2);
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
    }

    public void insertBidEvalForBidEvalSum(int besId, int venId) throws Exception {
        try {
            String sql = "";
            sql = "Insert Into bid_eval_sum_vendor(bes_id,ven_id,delivery_time)"
                    + " Values (" + besId + "," + venId + ",'')";
            DBUtil.executeInsert(sql);

        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public void insertBidEvalForBidEvalSumDetail(int besId, int matId, double qt, double price, double total, String unit, int reqDetailId) throws Exception {
        try {
            String sql = "";
            sql = "Insert Into bid_eval_sum_detail(besv_id, bes_id, mat_id,mat_id_temp,quantity,price,total,unit,req_detail_id)"
                            + " Values (0," + besId + "," + matId + "," + matId + "," + qt + "," + price + "," + total + ",'" +unit + "'," + reqDetailId + ")";
            DBUtil.executeInsert(sql);

        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public int getBidEvalSumDetId(int matId, int besId)
            throws Exception {
        ResultSet rs = null;
        String sql = "SELECT det_id FROM bid_eval_sum_detail WHERE mat_id = "+ matId +" AND bes_id = "+ besId;

        int detId = 0;
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                detId = rs.getInt("det_id");
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
        return detId;
    }
    
    public void updateBidEvalSumDetail(int detId, double qt) throws Exception {        
        try {
            String sql = "Update bid_eval_sum_detail Set "
                    + " quantity=quantity+" + qt
                    + " Where det_id=" + detId;
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateBidEvalSumVendor(BidEvalSumVendorBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Update bid_eval_sum_vendor Set "
                    + //              " bes_id=" + bean.getBesId() +
                    //               ", ven_id=" + bean.getVenId() +
                    " delivery_time='" + bean.getDeliveryTime()
                    + "', payment_mode='" + bean.getPaymentMode()
                    + "' Where besv_id=" + bean.getBesvId();
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteBidEvalSumVendor(int besvId) throws Exception {
        int result = 0;
        try {
            String sql = "Delete From bid_eval_sum_vendor " + " Where besv_id=" + besvId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleBidEvalSumVendor(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "bes_id";
                break;
        }
        ResultSet rs = null;

        String sql = "Select * From bid_eval_sum_vendor Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " = " + strFieldvalue;
        }
        sql = sql + " Order by besv_id DESC";

        ArrayList bid_eval_sum_vendorList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            BidEvalSumVendorBean bid_eval_sum_vendor = null;
            while (rs.next()) {
                bid_eval_sum_vendor = new BidEvalSumVendorBean();
                bid_eval_sum_vendor.setBesvId(rs.getInt("besv_id"));
                bid_eval_sum_vendor.setBesId(rs.getInt("bes_id"));
                bid_eval_sum_vendor.setBesId(rs.getInt("ven_id"));
                bid_eval_sum_vendor.setDeliveryTime(rs.getString("delivery_time"));
                bid_eval_sum_vendor.setPaymentMode(rs.getString("payment_mode"));
                bid_eval_sum_vendorList.add(bid_eval_sum_vendor);
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
        return bid_eval_sum_vendorList;
    }

    public ArrayList searchAdvBidEvalSumVendor(BidEvalSumVendorBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select besv_id, pro_id, name, physical_add, kind, comments From bid_eval_sum_vendor Where 1 ";

        if (bean.getBesvId() != 0) {
            sql = sql + " AND besv_id =" + bean.getBesvId();
        }

        if (bean.getBesId() != 0) {
            sql = sql + " AND bes_id =" + bean.getBesId();
        }

        if (bean.getVenId() != 0) {
            sql = sql + " AND ven_id =" + bean.getVenId();
        }

        if (!StringUtil.isBlankOrNull(bean.getDeliveryTime())) {
            sql = sql + " AND delivery_time LIKE '%" + bean.getDeliveryTime() + "'%";
        }

        if (!StringUtil.isBlankOrNull(bean.getPaymentMode())) {
            sql = sql + " AND payment_mode LIKE '%" + bean.getPaymentMode() + "%'";
        }
        sql = sql + " Order by besv_id DESC";

        ArrayList bid_eval_sum_vendorList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            BidEvalSumVendorBean bid_eval_sum_vendor = null;
            while (rs.next()) {
                bid_eval_sum_vendor = new BidEvalSumVendorBean();
                bid_eval_sum_vendor.setBesvId(rs.getInt("besv_id"));
                bid_eval_sum_vendor.setBesId(rs.getInt("bes_id"));
                bid_eval_sum_vendor.setBesId(rs.getInt("ven_id"));
                bid_eval_sum_vendor.setDeliveryTime(rs.getString("delivery_time"));
                bid_eval_sum_vendor.setPaymentMode(rs.getString("payment_mode"));
                bid_eval_sum_vendorList.add(bid_eval_sum_vendor);
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
        return bid_eval_sum_vendorList;
    }
    
    public ArrayList getTenderPlanDetailsforDiffBid(int tenId) throws Exception {
        ResultSet rs = null;

        String sql = "SELECT t.* FROM tender_plan_detail AS t WHERE t.ten_id = "+ tenId +" AND CONCAT(t.mat_id_temp ,t.reqDetail_id) NOT IN (SELECT CONCAT(mat_id , req_detail_id) FROM bid_eval_sum_detail)";

        ArrayList tender_plan_detailList = new ArrayList();
        try {

            rs = DBUtil.executeQuery(sql);
            TenderPlanDetailBean detail = null;
            while (rs.next()) {
                detail = new TenderPlanDetailBean();
                detail.setDetId(rs.getInt("det_id"));
                detail.setTenId(rs.getInt("ten_id"));
                detail.setMatId(rs.getInt("mat_id"));
                detail.setUnit(rs.getString("unit"));
                detail.setQuantity(rs.getDouble("quantity"));
                //detail.setProvideDate(rs.getString("provide_date"));
                //detail.setProvideDate(DateUtil.formatDate(rs.getDate("provide_date"), "dd/MM/yyyy"));
                detail.setReqDetailId(rs.getInt("reqDetail_id"));
                //detail.setMatName(StringUtil.decodeString(rs.getString("matName")));
                //detail.setMatOrigin(rs.getString("matOrigin"));
                detail.setPrice(rs.getDouble("price"));
                detail.setTotal(rs.getDouble("total"));
                tender_plan_detailList.add(detail);
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
        return tender_plan_detailList;
    }
    
    public ArrayList getTenderPlanVendorDiffBid(int tenId) throws Exception {
        ResultSet rs = null;

            String sql = "SELECT * FROM tender_evaluate_vendor AS t WHERE t.ten_id="+ tenId +" AND t.ven_id NOT IN (SELECT ven_id FROM bid_eval_sum AS b LEFT JOIN bid_eval_sum_vendor AS v ON v.bes_id = b.bes_id WHERE b.ten_id = t.ten_id)";

        ArrayList list = new ArrayList();
        try {

            rs = DBUtil.executeQuery(sql);
            TenderEvaluateVendorBean bean = null;
            while (rs.next()) {
                bean = new TenderEvaluateVendorBean();
                bean.setTevId(rs.getInt("tev_id"));
                bean.setTenId(rs.getInt("ten_id"));
                bean.setVenId(rs.getInt("ven_id"));
                bean.setSent(rs.getInt("sent"));
                bean.setBidded(rs.getInt("bidded"));
                bean.setBiddedPage(rs.getString("bidded_page"));
                bean.setBiddedFoundation(rs.getString("bidded_foundation"));
                bean.setBiddedStatus(rs.getInt("bidded_status"));
                bean.setQuoNo(rs.getString("quo_no"));
                bean.setQuoDate(DateUtil.formatDate(rs.getDate("quo_date"), "dd/MM/yyyy"));
                bean.setBidValidity(rs.getString("bid_validity"));
                bean.setIncoterm(rs.getInt("incoterm"));
                list.add(bean);
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
        return list;
    }
}
