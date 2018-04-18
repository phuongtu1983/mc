/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.TenderEvaluateVendorBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.util.MCUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class TenderEvaluateVendorDAO extends BasicDAO {

    public TenderEvaluateVendorDAO() {
    }

    public ArrayList getTenderEvaluateVendors()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tender_evaluate_vendor Order by tev_id ASC";

        ArrayList tender_evaluate_vendorList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            TenderEvaluateVendorBean tender_evaluate_vendor = null;
            while (rs.next()) {
                tender_evaluate_vendor = new TenderEvaluateVendorBean();
                tender_evaluate_vendor.setTevId(rs.getInt("tev_id"));
                tender_evaluate_vendor.setTenId(rs.getInt("ten_id"));
                tender_evaluate_vendor.setVenId(rs.getInt("ven_id"));
                tender_evaluate_vendor.setSent(rs.getInt("sent"));
                tender_evaluate_vendor.setBidded(rs.getInt("bidded"));
                tender_evaluate_vendor.setBiddedPage(rs.getString("bidded_page"));
                tender_evaluate_vendor.setBiddedFoundation(rs.getString("bidded_foundation"));
                tender_evaluate_vendor.setBiddedStatus(rs.getInt("bidded_status"));
                tender_evaluate_vendor.setQuoNo(rs.getString("quo_no"));
//                tender_evaluate_vendor.setQuoDate(rs.getDate("quo_date"));
                tender_evaluate_vendor.setBidValidity(rs.getString("bid_validity"));
                tender_evaluate_vendor.setIncoterm(rs.getInt("incoterm"));
                tender_evaluate_vendorList.add(tender_evaluate_vendor);
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
        return tender_evaluate_vendorList;
    }

    public ArrayList getTenderEvaluateVendorsForTechEval(String tenId)
            throws Exception {
        ResultSet rs = null;

        int stt = 1;
        String sql = "Select t.tev_id,v.name,v.ven_id,r.result,r.ter_id From tender_evaluate_vendor as t Left Join vendor as v On v.ven_id=t.ven_id Left Join tech_eval_vendor as r On r.tev_id=t.tev_id Where t.bidded=1 and t.ten_id=" + tenId + " ORDER BY t.tev_id ";

        ArrayList tender_evaluate_vendorList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            TenderEvaluateVendorBean tender_evaluate_vendor = null;
            while (rs.next()) {
                tender_evaluate_vendor = new TenderEvaluateVendorBean();
                tender_evaluate_vendor.setVendorName(StringUtil.decodeString(rs.getString("v.name")));
                tender_evaluate_vendor.setVenId(rs.getInt("v.ven_id"));
                tender_evaluate_vendor.setResult(rs.getString("r.result"));
                tender_evaluate_vendor.setTerId(rs.getInt("r.ter_id"));
                tender_evaluate_vendor.setTevId(rs.getInt("t.tev_id"));
                tender_evaluate_vendor.setStt(stt);
                //        tender_evaluate_vendor.setBidded(rs.getInt("r.result"));
                if (tender_evaluate_vendor.getResult() == null) {
                    tender_evaluate_vendor.setResult("");
                }
                if (tender_evaluate_vendor.getResult().startsWith("0")) {
                    tender_evaluate_vendor.setResult("");
                }
                if (tender_evaluate_vendor.getResult().startsWith("1")) {
                    tender_evaluate_vendor.setResult(MCUtil.getBundleString("message.result5"));
                }
                if (tender_evaluate_vendor.getResult().startsWith("2")) {
                    tender_evaluate_vendor.setResult(MCUtil.getBundleString("message.result5"));
                }
                if (tender_evaluate_vendor.getResult().startsWith("3")) {
                    tender_evaluate_vendor.setResult(MCUtil.getBundleString("message.result5"));
                }
                if (tender_evaluate_vendor.getResult().startsWith("5")) {
                    tender_evaluate_vendor.setResult(MCUtil.getBundleString("message.result5"));
                }
                tender_evaluate_vendorList.add(tender_evaluate_vendor);
                stt++;
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
        return tender_evaluate_vendorList;
    }

    public ArrayList getTenderEvaluateVendorsForTechEval2(String tenId)
            throws Exception {
        ResultSet rs = null;

        int stt = 1;
        String sql = "Select t.tev_id,v.name,v.ven_id,r.result,r.ter_id From tender_evaluate_vendor as t Left Join vendor as v On v.ven_id=t.ven_id Left Join tech_eval_vendor as r On r.tev_id=t.tev_id Where t.bidded = 1  AND r.te_id > 0 AND t.ten_id=" + tenId + " ORDER BY t.tev_id ";

        ArrayList tender_evaluate_vendorList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            TenderEvaluateVendorBean tender_evaluate_vendor = null;
            while (rs.next()) {
                tender_evaluate_vendor = new TenderEvaluateVendorBean();
                tender_evaluate_vendor.setVendorName(StringUtil.decodeString(rs.getString("v.name")));
                tender_evaluate_vendor.setVenId(rs.getInt("v.ven_id"));
                tender_evaluate_vendor.setResult(rs.getString("r.result"));
                tender_evaluate_vendor.setTerId(rs.getInt("r.ter_id"));
                tender_evaluate_vendor.setTevId(rs.getInt("t.tev_id"));
                tender_evaluate_vendor.setStt(stt);
                tender_evaluate_vendor.setBidded(rs.getInt("r.result"));
                if (tender_evaluate_vendor.getResult() == null) {
                    tender_evaluate_vendor.setResult("");
                }
                if (tender_evaluate_vendor.getResult().startsWith("0")) {
                    tender_evaluate_vendor.setResult("");
                }
                if (tender_evaluate_vendor.getResult().startsWith("1")) {
                    tender_evaluate_vendor.setResult(MCUtil.getBundleString("message.result5"));
                }
                if (tender_evaluate_vendor.getResult().startsWith("2")) {
                    tender_evaluate_vendor.setResult(MCUtil.getBundleString("message.result5"));
                }
                if (tender_evaluate_vendor.getResult().startsWith("3")) {
                    tender_evaluate_vendor.setResult(MCUtil.getBundleString("message.result5"));
                }
                if (tender_evaluate_vendor.getResult().startsWith("5")) {
                    tender_evaluate_vendor.setResult(MCUtil.getBundleString("message.result5"));
                }
                tender_evaluate_vendorList.add(tender_evaluate_vendor);
                stt++;
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
        return tender_evaluate_vendorList;
    }

    public ArrayList getTenderEvaluateVendorsForComEvalMaterial(String tenId)
            throws Exception {
        ResultSet rs = null;

        int stt = 1;
        String sql = "SELECT v.name, v.ven_id, c.*, r.ter_id AS ter, c.rates"
                + " FROM tender_evaluate_vendor AS t LEFT JOIN vendor AS v ON v.ven_id = t.ven_id"
                + " LEFT JOIN tech_eval_vendor AS r ON r.tev_id = t.tev_id LEFT JOIN tech_eval_detail AS ted ON ted.ter_id = r.ter_id"
                + " LEFT JOIN com_eval_material_vendor AS c ON r.ter_id = c.ter_id"
                + " WHERE t.bidded = 1 AND ted.eval_tbe = 1 AND t.ten_id = " + tenId;
        sql += " GROUP BY v.ven_id ORDER BY t.tev_id ";

        ArrayList tender_evaluate_vendorList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            TenderEvaluateVendorBean tender_evaluate_vendor = null;
            while (rs.next()) {
                tender_evaluate_vendor = new TenderEvaluateVendorBean();
                tender_evaluate_vendor.setCurrency(rs.getString("currency"));
                if (GenericValidator.isBlankOrNull(tender_evaluate_vendor.getCurrency())) {
                    tender_evaluate_vendor.setCurrency("");
                }
                tender_evaluate_vendor.setVendorName(StringUtil.decodeString(rs.getString("v.name")));
                tender_evaluate_vendor.setVenId(rs.getInt("ven_id"));
                tender_evaluate_vendor.setTerId(rs.getInt("ter_id"));
                //tender_evaluate_vendor.setRatesAfter(NumberUtil.formatMoneyDefault(rs.getDouble("rates_after"), rs.getString("currency")));
                if (!tender_evaluate_vendor.getCurrency().equals("VN") && rs.getDouble("rates") == 1) {
                    tender_evaluate_vendor.setRatesAfter(NumberUtil.formatMoneyDefault(rs.getDouble("rates_after"), tender_evaluate_vendor.getCurrency()));
                } else {
                    tender_evaluate_vendor.setRatesAfter(NumberUtil.formatMoneyDefault(rs.getDouble("rates_after"), "VND"));
                }
                tender_evaluate_vendor.setCemId(rs.getInt("cem_id"));
                tender_evaluate_vendor.setStt(stt);
                tender_evaluate_vendorList.add(tender_evaluate_vendor);
                stt++;
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
        return tender_evaluate_vendorList;
    }

    public ArrayList getTenderEvaluateVendorsForComEval(String tenId)
            throws Exception {
        ResultSet rs = null;

        int stt = 1;
        String sql = "Select c.cev_id, c.currency, v.name,v.ven_id,c.sum,c.rand,r.ter_id, c.rates"
                + " From tender_evaluate_vendor as t"
                + " Left Join vendor as v On v.ven_id=t.ven_id"
                + " Left Join tech_eval_vendor as r On r.tev_id=t.tev_id"
                + " Left Join com_eval_vendor as c On r.ter_id=c.ter_id"
                + " Where r.result=1 and t.ten_id=" + tenId + " ORDER BY t.tev_id ";

        ArrayList tender_evaluate_vendorList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            TenderEvaluateVendorBean tender_evaluate_vendor = null;
            while (rs.next()) {
                tender_evaluate_vendor = new TenderEvaluateVendorBean();
                tender_evaluate_vendor.setVendorName(StringUtil.decodeString(rs.getString("v.name")));
                tender_evaluate_vendor.setCurrency(rs.getString("currency"));
                if (GenericValidator.isBlankOrNull(tender_evaluate_vendor.getCurrency())) {
                    tender_evaluate_vendor.setCurrency("");
                }
                tender_evaluate_vendor.setVenId(rs.getInt("ven_id"));
                if (!tender_evaluate_vendor.getCurrency().equals("VN") && rs.getDouble("rates") == 1) {
                    tender_evaluate_vendor.setSum(NumberUtil.formatMoneyDefault(rs.getDouble("sum"), tender_evaluate_vendor.getCurrency()));
                } else {
                    tender_evaluate_vendor.setSum(NumberUtil.formatMoneyDefault(rs.getDouble("sum"), "VND"));
                }

                tender_evaluate_vendor.setRand(rs.getInt("rand"));
                tender_evaluate_vendor.setCevId(rs.getInt("cev_id"));
                tender_evaluate_vendor.setStt(stt);
                tender_evaluate_vendorList.add(tender_evaluate_vendor);
                stt++;
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
        return tender_evaluate_vendorList;
    }

    public ArrayList getTenderEvaluateVendorsForBidEvalSum(String tenId)
            throws Exception {
        ResultSet rs = null;
        int stt = 1;
        String sql1 = "SELECT eval_kind FROM tender_plan WHERE ten_id = " + tenId;
        int evalKind = 0;

        rs = DBUtil.executeQuery(sql1);
        while (rs.next()) {
            evalKind = rs.getInt("eval_kind");
        }
        if (rs != null) {
            DBUtil.closeConnection(rs);
        }
        String sql = "";

        if (evalKind == 0) { //0: tron goi; 1:tung phan
            sql = "SELECT c.con_id as cb, v.sum, v.currency ,d.name,d.ven_id,b.delivery_time,b.besv_id, e.ten_id FROM com_eval_vendor AS v LEFT JOIN com_eval AS e ON e.ce_id=v.ce_id LEFT JOIN tech_eval_vendor AS t ON t.ter_id=v.ter_id LEFT JOIN tender_evaluate_vendor AS r ON r.tev_id=t.tev_id LEFT JOIN vendor AS d ON d.ven_id=r.ven_id LEFT JOIN bid_eval_sum AS s ON s.ten_id=e.ten_id LEFT JOIN bid_eval_sum_vendor AS b ON b.bes_id=s.bes_id LEFT JOIN contract AS c ON c.ten_id = s.ten_id AND c.ven_id = b.ven_id WHERE v.rand=1 AND e.ten_id = " + tenId + " ORDER BY r.tev_id ";
        } else {
            sql = "SELECT c.con_id AS cb, v.currency,d.NAME,d.ven_id,b.delivery_time,b.besv_id, e.ten_id, SUM(ce.total) as sum FROM com_eval_material_vendor AS v LEFT JOIN com_eval AS e ON e.ce_id=v.ce_id LEFT JOIN tech_eval_vendor AS r ON r.ter_id=v.ter_id LEFT JOIN tender_evaluate_vendor AS te ON te.tev_id=r.tev_id LEFT JOIN com_eval_material_detail AS ce ON ce.cem_id=v.cem_id LEFT JOIN vendor AS d ON d.ven_id=te.ven_id LEFT JOIN bid_eval_sum AS s ON s.ten_id=e.ten_id LEFT JOIN bid_eval_sum_vendor AS b ON b.bes_id=s.bes_id AND b.ven_id = d.ven_id LEFT JOIN contract AS c ON c.ten_id = s.ten_id AND c.ven_id = b.ven_id WHERE ce.result=1 AND e.ten_id = " + tenId + " GROUP BY d.NAME ORDER BY r.tev_id ";
        }
        ArrayList tender_evaluate_vendorList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            TenderEvaluateVendorBean tender_evaluate_vendor = null;
            while (rs.next()) {
                tender_evaluate_vendor = new TenderEvaluateVendorBean();
                tender_evaluate_vendor.setVendorName(StringUtil.decodeString(rs.getString("name")));
                tender_evaluate_vendor.setTenId(NumberUtil.parseInt(tenId, 0));
                tender_evaluate_vendor.setCurrency(rs.getString("currency"));
                tender_evaluate_vendor.setBesvId(rs.getInt("besv_id"));
                if (rs.getString("delivery_time") == null) {
                    tender_evaluate_vendor.setDeliveryTime("");
                } else {
                    tender_evaluate_vendor.setDeliveryTime(rs.getString("delivery_time"));
                }

                if (rs.getString("cb") == null) {
                    tender_evaluate_vendor.setCb(0);
                } else {
                    tender_evaluate_vendor.setCb(rs.getInt("cb"));
                }

//                if (evalKind == 0) { //0: tron goi; 1:tung phan
//                    tender_evaluate_vendor.setSum(NumberUtil.formatMoneyDefault(rs.getDouble("sum")*(1+10/100.0)));
//                } else {
//                    tender_evaluate_vendor.setSum(NumberUtil.formatMoneyDefault(rs.getDouble("sum")*(1+10/100.0)));
//                }
                if (evalKind == 0) { //0: tron goi; 1:tung phan
                    tender_evaluate_vendor.setSum(NumberUtil.formatMoneyDefault((rs.getDouble("sum") * 1.1), tender_evaluate_vendor.getCurrency()));
                } else {
                    tender_evaluate_vendor.setSum(NumberUtil.formatMoneyDefault((rs.getDouble("sum") * 1.1), tender_evaluate_vendor.getCurrency()));
                }

                tender_evaluate_vendor.setResult(MCUtil.getBundleString("message.vendor.evaluate.reach"));
                tender_evaluate_vendor.setVenId(rs.getInt("ven_id"));
                tender_evaluate_vendor.setTenId(rs.getInt("ten_id"));
                tender_evaluate_vendor.setStt(stt);
                tender_evaluate_vendorList.add(tender_evaluate_vendor);
                stt++;
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
        return tender_evaluate_vendorList;
    }
    
    public ArrayList getTenderEvaluateVendorsForBidEvalSumNon(String tenId)
            throws Exception {
        ResultSet rs = null;
        int stt = 1;
        
        String sql = "";        
            sql = "SELECT c.con_id AS cb, d.NAME,d.ven_id,b.delivery_time,b.besv_id, s.ten_id FROM bid_eval_sum AS s LEFT JOIN bid_eval_sum_vendor AS b ON s.bes_id=b.bes_id LEFT JOIN vendor AS d ON d.ven_id=b.ven_id LEFT JOIN tender_evaluate_vendor AS r ON s.ten_id=r.ten_id AND r.ven_id = b.ven_id LEFT JOIN contract AS c ON c.ten_id = s.ten_id AND c.ven_id = b.ven_id WHERE s.ten_id = " + tenId + " ORDER BY r.tev_id ";
        
        ArrayList tender_evaluate_vendorList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            TenderEvaluateVendorBean tender_evaluate_vendor = null;
            while (rs.next()) {
                tender_evaluate_vendor = new TenderEvaluateVendorBean();
                tender_evaluate_vendor.setVendorName(StringUtil.decodeString(rs.getString("name")));
                //tender_evaluate_vendor.setTenId(NumberUtil.parseInt(tenId, 0));
                tender_evaluate_vendor.setCurrency("");
                tender_evaluate_vendor.setBesvId(rs.getInt("besv_id"));
                tender_evaluate_vendor.setSum("");
                if (rs.getString("delivery_time") == null) {
                    tender_evaluate_vendor.setDeliveryTime("");
                } else {
                    tender_evaluate_vendor.setDeliveryTime(rs.getString("delivery_time"));
                }

                if (rs.getString("cb") == null) {
                    tender_evaluate_vendor.setCb(0);
                } else {
                    tender_evaluate_vendor.setCb(rs.getInt("cb"));
                }
                
                tender_evaluate_vendor.setResult(MCUtil.getBundleString("message.vendor.evaluate.reach"));
                tender_evaluate_vendor.setVenId(rs.getInt("ven_id"));
                tender_evaluate_vendor.setTenId(rs.getInt("ten_id"));
                tender_evaluate_vendor.setStt(stt);
                tender_evaluate_vendorList.add(tender_evaluate_vendor);
                stt++;
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
        return tender_evaluate_vendorList;
    }

    public TenderEvaluateVendorBean getTenderEvaluateVendor(int tevId) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tender_evaluate_vendor Where tev_id=" + tevId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                TenderEvaluateVendorBean tender_evaluate_vendor = new TenderEvaluateVendorBean();
                tender_evaluate_vendor.setTevId(rs.getInt("tev_id"));
                tender_evaluate_vendor.setTenId(rs.getInt("ten_id"));
                tender_evaluate_vendor.setVenId(rs.getInt("ven_id"));
                tender_evaluate_vendor.setSent(rs.getInt("sent"));
                tender_evaluate_vendor.setBidded(rs.getInt("bidded"));
                tender_evaluate_vendor.setBiddedPage(rs.getString("bidded_page"));
                tender_evaluate_vendor.setBiddedFoundation(rs.getString("bidded_foundation"));
                tender_evaluate_vendor.setBiddedStatus(rs.getInt("bidded_status"));
                tender_evaluate_vendor.setQuoNo(rs.getString("quo_no"));
//                tender_evaluate_vendor.setQuoDate(rs.getDate("quo_date"));
                tender_evaluate_vendor.setBidValidity(rs.getString("bid_validity"));
                tender_evaluate_vendor.setIncoterm(rs.getInt("incoterm"));

                return tender_evaluate_vendor;
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

    public void insertTenderEvaluateVendor(TenderEvaluateVendorBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {

            String sql = "";



            sql = "Insert Into tender_evaluate_vendor(ten_id, ven_id, sent, bidded, bidded_page, bidded_foundation, bidded_status, quo_no, quo_date, bid_validity, incoterm)"
                    + " Values (" + bean.getTenId() + "," + bean.getVenId() + "," + bean.getSent() + "," + bean.getBidded()
                    + ",'" + bean.getBiddedPage() + "','" + bean.getBiddedFoundation() + "'," + bean.getBiddedStatus()
                    + ",'" + bean.getQuoNo() + "','" + bean.getQuoDate() + "," + bean.getIncoterm() + ")";

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

    public void updateTenderEvaluateVendor(TenderEvaluateVendorBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {



            String sql = "Update tender_evaluate_vendor Set "
                    + " ten_id=" + bean.getTenId()
                    + ", ven_id=" + bean.getVenId()
                    + ", sent=" + bean.getSent()
                    + ", bidded=" + bean.getBidded()
                    + ", bidded_page='" + bean.getBiddedPage() + "'"
                    + ", bidded_foundation='" + bean.getBiddedFoundation() + "'"
                    + ", bidded_status=" + bean.getBiddedStatus()
                    + ", quo_no='" + bean.getQuoNo() + "'"
                    + ", quo_date='" + bean.getQuoDate() + "'"
                    + ", bid_validity='" + bean.getBidValidity() + "'"
                    + ", incoterm='" + bean.getIncoterm() + "'"
                    + " Where tev_id=" + bean.getTevId();
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

    public int deleteTenderEvaluateVendor(int tevId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From tender_evaluate_vendor " + " Where tev_id=" + tevId;
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

    public ArrayList searchSimpleTenderEvaluateVendor(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "ven_id";
                break;
        }
        ResultSet rs = null;

        String sql = "Select * From tender_evaluate_vendor Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " = " + strFieldvalue;
        }
        sql = sql + " Order by tev_id DESC";

        ArrayList tender_evaluate_vendorList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            TenderEvaluateVendorBean tender_evaluate_vendor = null;
            while (rs.next()) {
                tender_evaluate_vendor = new TenderEvaluateVendorBean();
                tender_evaluate_vendor.setTevId(rs.getInt("tev_id"));
                tender_evaluate_vendor.setTenId(rs.getInt("ten_id"));
                tender_evaluate_vendor.setVenId(rs.getInt("ven_id"));
                tender_evaluate_vendor.setSent(rs.getInt("sent"));
                tender_evaluate_vendor.setBidded(rs.getInt("bidded"));
                tender_evaluate_vendor.setBiddedPage(rs.getString("bidded_page"));
                tender_evaluate_vendor.setBiddedFoundation(rs.getString("bidded_foundation"));
                tender_evaluate_vendor.setBiddedStatus(rs.getInt("bidded_status"));
                tender_evaluate_vendor.setQuoNo(rs.getString("quo_no"));
//                tender_evaluate_vendor.setQuoDate(rs.getDate("quo_date"));
                tender_evaluate_vendor.setBidValidity(rs.getString("bid_validity"));
                tender_evaluate_vendor.setIncoterm(rs.getInt("incoterm"));
                tender_evaluate_vendorList.add(tender_evaluate_vendor);
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
        return tender_evaluate_vendorList;

    }

    public ArrayList searchAdvTenderEvaluateVendor(TenderEvaluateVendorBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tender_evaluate_vendor Where 1 ";

        if (bean.getTevId() != 0) {
            sql = sql + " AND tev_id =" + bean.getTevId();
        }
        if (bean.getTenId() != 0) {
            sql = sql + " AND ten_id =" + bean.getTenId();
        }
        if (bean.getVenId() != 0) {
            sql = sql + " AND ven_id =" + bean.getVenId();
        }
        if (bean.getSent() != 0) {
            sql = sql + " AND sent =" + bean.getSent();
        }
        if (bean.getBidded() != 0) {
            sql = sql + " AND bidded =" + bean.getBidded();
        }
        if (!StringUtil.isBlankOrNull(bean.getBiddedPage())) {
            sql = sql + " AND bidded_page LIKE '%" + bean.getBiddedPage() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getBiddedFoundation())) {
            sql = sql + " AND bidded_foundation LIKE '%" + bean.getBiddedFoundation() + "%'";
        }
        if (bean.getBiddedStatus() != 0) {
            sql = sql + " AND bidded_status =" + bean.getBiddedStatus();
        }
        if (!StringUtil.isBlankOrNull(bean.getQuoNo())) {
            sql = sql + " AND quo_no LIKE '%" + bean.getQuoNo() + "%'";
        }
        if (bean.getQuoDate() != null) {
            sql = sql + " AND quo_date ='" + bean.getQuoDate() + "'";
        }
        if (!StringUtil.isBlankOrNull(bean.getBidValidity())) {
            sql = sql + " AND bid_validity LIKE '%" + bean.getBidValidity() + "%'";
        }
        if (bean.getIncoterm() != 0) {
            sql = sql + " AND incoterm = " + bean.getIncoterm();
        }

        sql = sql + " Order by tev_id DESC";

        ArrayList tender_evaluate_vendorList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            TenderEvaluateVendorBean tender_evaluate_vendor = null;

            while (rs.next()) {
                tender_evaluate_vendor = new TenderEvaluateVendorBean();
                tender_evaluate_vendor.setTevId(rs.getInt("tev_id"));
                tender_evaluate_vendor.setTenId(rs.getInt("ten_id"));
                tender_evaluate_vendor.setVenId(rs.getInt("ven_id"));
                tender_evaluate_vendor.setSent(rs.getInt("sent"));
                tender_evaluate_vendor.setBidded(rs.getInt("bidded"));
                tender_evaluate_vendor.setBiddedPage(rs.getString("bidded_page"));
                tender_evaluate_vendor.setBiddedFoundation(rs.getString("bidded_foundation"));
                tender_evaluate_vendor.setBiddedStatus(rs.getInt("bidded_status"));
                tender_evaluate_vendor.setQuoNo(rs.getString("quo_no"));
//                tender_evaluate_vendor.setQuoDate(rs.getDate("quo_date"));
                tender_evaluate_vendor.setBidValidity(rs.getString("bid_validity"));
                tender_evaluate_vendor.setIncoterm(rs.getInt("incoterm"));
                tender_evaluate_vendorList.add(tender_evaluate_vendor);
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
        return tender_evaluate_vendorList;
    }
}
