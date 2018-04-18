/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ComEvalVendorBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mai vinh loc
 */
public class ComEvalVendorDAO extends BasicDAO {

    public ComEvalVendorDAO() {
    }

    public ArrayList getComEvalVendors()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select * From com_eval_vendor Order by cev_id ASC";
        ArrayList com_eval_vendorList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalVendorBean com_eval_vendor = null;
            while (rs.next()) {
                com_eval_vendor = new ComEvalVendorBean();
                com_eval_vendor.setCevId(rs.getInt("cev_id"));
                com_eval_vendor.setCeId(rs.getInt("ce_id"));
                com_eval_vendor.setTerId(rs.getInt("ter_id"));
                com_eval_vendor.setCurrency(rs.getString("currency"));
                com_eval_vendor.setCostTransport(rs.getDouble("cost_transport"));
                com_eval_vendor.setOtherTax(rs.getDouble("other_tax"));
                com_eval_vendor.setOtherCost(rs.getDouble("other_cost"));
                com_eval_vendor.setSum(NumberUtil.formatMoneyDefault(rs.getDouble("sum")));
                com_eval_vendor.setRand(rs.getInt("rand"));
                com_eval_vendorList.add(com_eval_vendor);
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
        return com_eval_vendorList;
    }

    public ComEvalVendorBean getComEvalVendor(int cevId, int venId) throws Exception {
        ResultSet rs = null;
        String sql = "Select v.*,r.incoterm,r.ten_id,r.ven_id From com_eval_vendor as v  left join tech_eval_vendor as t on v.ter_id=t.ter_id left join tender_evaluate_vendor as r on t.tev_id=r.tev_id Where cev_id=" + cevId;
        try {
            String sql1 = "SELECT v.name FROM vendor AS v WHERE ven_id=" + venId;
            String vendorName = "";

            rs = DBUtil.executeQuery(sql1);
            while (rs.next()) {
                vendorName = rs.getString("v.name");
            }
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ComEvalVendorBean com_eval_vendor = new ComEvalVendorBean();
                com_eval_vendor.setCevId(rs.getInt("cev_id"));
                com_eval_vendor.setCeId(rs.getInt("ce_id"));
                com_eval_vendor.setTerId(rs.getInt("ter_id"));
                com_eval_vendor.setTenId(rs.getInt("ten_id"));
                com_eval_vendor.setCurrency(rs.getString("currency"));
                com_eval_vendor.setCustomTax(rs.getDouble("custom_tax"));
                com_eval_vendor.setCostTransport(rs.getDouble("cost_transport"));
                com_eval_vendor.setOtherTax(rs.getDouble("other_tax"));
                com_eval_vendor.setOtherCost(rs.getDouble("other_cost"));
                com_eval_vendor.setPriceCertificate(rs.getDouble("price_certificate"));
                com_eval_vendor.setPriceToPort(rs.getDouble("price_to_port"));
                com_eval_vendor.setSum(NumberUtil.formatMoneyDefault(rs.getDouble("sum")));
                if (rs.getInt("rates") == 0) {
                    com_eval_vendor.setRates(1.0);
                } else {
                    com_eval_vendor.setRates(rs.getDouble("rates"));
                }
                com_eval_vendor.setRatesAfter(rs.getDouble("rates_after"));
                com_eval_vendor.setRand(rs.getInt("rand"));
                com_eval_vendor.setIncoterm(rs.getInt("incoterm"));
                com_eval_vendor.setVenId(rs.getInt("r.ven_id"));
                com_eval_vendor.setVendorName(StringUtil.decodeString(vendorName));
                com_eval_vendor.setPaymentMethod(rs.getString("payment_method"));

                return com_eval_vendor;
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

    public ComEvalVendorBean getComEvalVendorByName(String name) throws Exception {
        ResultSet rs = null;

        String sql = "select cev_id from com_eval_vendor where name='" + name + "'";
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ComEvalVendorBean com_eval_vendor = new ComEvalVendorBean();
                com_eval_vendor.setCevId(rs.getInt("cev_id"));

                return com_eval_vendor;
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

    public void insertComEvalVendor(ComEvalVendorBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Insert Into com_eval_vendor(ce_id, ter_id, currency, cost_transport, other_tax, other_cost, sum, rand)"
                    + " Values (" + bean.getCeId() + "," + bean.getTerId() + "," + bean.getCurrency() + "," + bean.getCostTransport()
                    + "," + bean.getOtherTax() + "," + bean.getOtherCost() + "," + bean.getSum() + "," + bean.getRand() + ")";
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void insertComEvalVendorForComEval(int ceId, int tenId) throws Exception {
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {
            String sql = "";
            String sql1 = "";
            String sql2 = "";
            String sql3 = "";
            int cevId = 0;

            sql = "SELECT v.ter_id,c.cev_id FROM tech_eval_vendor AS v LEFT JOIN tender_evaluate_vendor AS r ON v.tev_id=r.tev_id LEFT JOIN com_eval_vendor AS c ON c.ter_id=v.ter_id WHERE c.cev_id IS NULL AND result = 1 AND r.ten_id = " + tenId;

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                sql1 = "Insert Into com_eval_vendor(ce_id, ter_id)"
                        + " Values (" + ceId + "," + rs.getInt("ter_id") + ")";
                cevId = DBUtil.executeInsert(sql1);

                sql2 = "SELECT t.*,t.mat_id FROM tech_eval_detail AS t left join tech_eval_vendor as v on v.ter_id = t.ter_id Where v.result = 1 AND t.ter_id = " + rs.getInt("ter_id");

                rs2 = DBUtil.executeQuery(sql2);
                while (rs2.next()) {
                    sql3 = "Insert Into com_eval_detail(cev_id, det_id_tp,quantity,mat_id,unit)"
                            + " Values (" + cevId + "," + rs2.getInt("det_id_tp") + "," + rs2.getInt("qty") + "," + rs2.getInt("mat_id") + ",'" + rs2.getString("unit") + "')";
//                    stmt3.executeUpdate(sql3);
                    DBUtil.executeInsert(sql3);
                }
                if (rs2 != null) {
                    DBUtil.closeConnection(rs2);
                }
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

    public void updateComEvalVendor(ComEvalVendorBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Update com_eval_vendor Set "
                    + " ce_id=" + bean.getCeId()
                    + ", ter_id=" + bean.getTerId()
                    + ", currency=" + bean.getCurrency()
                    + ", cost_transport=" + bean.getCostTransport()
                    + ", other_tax=" + bean.getOtherTax()
                    + ", other_cost=" + bean.getOtherCost()
                    + ", sum=" + bean.getSum()
                    + ", rand=" + bean.getRand()
                    + " Where cev_id=" + bean.getCevId();
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateComEvalVendorForComEval(ComEvalVendorBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        ResultSet rs = null;
        try {
            String sql1 = "";
            int i = 1;
            String sql = "Update com_eval_vendor Set "
                    + "  currency='" + bean.getCurrency() + "'"
                    + ", custom_tax=" + bean.getCustomTax()
                    + ", cost_transport=" + bean.getCostTransport()
                    + ", other_tax=" + bean.getOtherTax()
                    + ", other_cost=" + bean.getOtherCost()
                    + ", price_certificate=" + bean.getPriceCertificate()
                    + ", price_to_port=" + bean.getPriceToPort()
                    + ", sum=" + bean.getSum()
                    + ", rand=" + bean.getRand()
                    + ", rates=" + bean.getRates()
                    + ", rates_after=" + bean.getRatesAfter()
                    + ", payment_method='" + bean.getPaymentMethod() + "'"
                    + " Where cev_id=" + bean.getCevId();

            //System.out.println("sql=" + sql);
            DBUtil.executeUpdate(sql);
            sql1 = "SELECT sum,cev_id FROM com_eval_vendor WHERE ce_id = '" + bean.getCeId() + "' ORDER BY sum ASC ";

            rs = DBUtil.executeQuery(sql1);
            while (rs.next()) {
                sql = "Update com_eval_vendor Set "
                        + " rand = " + i
                        + " Where cev_id=" + rs.getInt("cev_id");
                DBUtil.executeUpdate(sql);
                i++;
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

    public int deleteComEvalVendor(int cevId) throws Exception {
        int result = 0;
        try {
            String sql = "Delete From com_eval_vendor " + " Where cev_id=" + cevId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleComEvalVendor(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "ven_id";
                break;
        }
        ResultSet rs = null;

        String sql = "Select * From com_eval_vendor Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " = " + strFieldvalue;
        }
        sql = sql + " Order by cev_id DESC";

        ArrayList com_eval_vendorList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalVendorBean com_eval_vendor = null;
            while (rs.next()) {
                com_eval_vendor = new ComEvalVendorBean();
                com_eval_vendor.setCevId(rs.getInt("cev_id"));
                com_eval_vendor.setCeId(rs.getInt("ce_id"));
                com_eval_vendor.setTerId(rs.getInt("ter_id"));
                com_eval_vendor.setCurrency(rs.getString("currency"));
                com_eval_vendor.setCostTransport(rs.getDouble("cost_transport"));
                com_eval_vendor.setOtherTax(rs.getDouble("other_tax"));
                com_eval_vendor.setOtherCost(rs.getDouble("other_cost"));
                com_eval_vendor.setSum(NumberUtil.formatMoneyDefault(rs.getDouble("sum")));
                com_eval_vendor.setRand(rs.getInt("rand"));
                com_eval_vendorList.add(com_eval_vendor);
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
        return com_eval_vendorList;

    }

    public ArrayList searchAdvComEvalVendor(ComEvalVendorBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From com_eval_vendor Where 1 ";

        if (bean.getCevId() != 0) {
            sql = sql + " AND cev_id = " + bean.getCevId();
        }
        if (bean.getCeId() != 0) {
            sql = sql + " AND ce_id = " + bean.getCeId();
        }
        if (bean.getTerId() != 0) {
            sql = sql + " AND ven_id = " + bean.getTerId();
        }

        sql = sql + " Order by cev_id DESC";

        ArrayList com_eval_vendorList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalVendorBean com_eval_vendor = null;
            while (rs.next()) {
                com_eval_vendor.setCevId(rs.getInt("cev_id"));
                com_eval_vendor.setCeId(rs.getInt("ce_id"));
                com_eval_vendor.setTerId(rs.getInt("ter_id"));
                com_eval_vendor.setCurrency(rs.getString("currency"));
                com_eval_vendor.setCostTransport(rs.getDouble("cost_transport"));
                com_eval_vendor.setOtherTax(rs.getDouble("other_tax"));
                com_eval_vendor.setOtherCost(rs.getDouble("other_cost"));
                com_eval_vendor.setSum(NumberUtil.formatMoneyDefault(rs.getDouble("sum")));
                com_eval_vendor.setRand(rs.getInt("rand"));
                com_eval_vendorList.add(com_eval_vendor);
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
        return com_eval_vendorList;
    }
}
