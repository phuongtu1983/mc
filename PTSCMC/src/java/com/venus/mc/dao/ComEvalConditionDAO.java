/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ComEvalConditionBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class ComEvalConditionDAO extends BasicDAO {

    public ComEvalConditionDAO() {
    }

    public ArrayList getComEvalConditions()
            throws Exception {
        ResultSet rs = null;
        String sql = "Select * From com_eval_condition Order by com_id ASC";

        ArrayList com_eval_conditionList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalConditionBean com_eval_condition = null;
            while (rs.next()) {
                com_eval_condition = new ComEvalConditionBean();
                com_eval_condition.setComId(rs.getInt("com_id"));
                com_eval_condition.setCevId(rs.getInt("cev_id"));
                com_eval_condition.setPaymentMethod(rs.getString("payment_method"));
                com_eval_condition.setGuaranteeContract(rs.getString("guarantee_contract"));
                com_eval_condition.setCustomsCost(rs.getString("customs_cost"));
                com_eval_condition.setTransportCost(rs.getString("transport_cost"));
                com_eval_condition.setOtherTax(rs.getString("other_tax"));
                com_eval_condition.setOtherCost(rs.getString("other_cost"));
                com_eval_condition.setTotalEval(rs.getString("total_eval"));
                com_eval_condition.setSubTotal(rs.getString("sub_total"));
                com_eval_condition.setFreightCharge(rs.getString("freight_charge"));
                com_eval_condition.setTestCertification(rs.getString("test_certification"));
                com_eval_condition.setSpareParts(rs.getString("spare_parts"));
                com_eval_condition.setGrossPrice(rs.getString("gross_price"));
                com_eval_condition.setDiscount(rs.getString("discount"));
                com_eval_condition.setNetPrice(rs.getString("net_price"));
                com_eval_condition.setShippingLocation(rs.getString("shipping_location"));
                com_eval_condition.setDeliverySchedule(rs.getString("delivery_schedule"));
                com_eval_condition.setPaymentTerms(rs.getString("payment_terms"));
                com_eval_condition.setWarranty(rs.getString("warranty"));
                com_eval_condition.setConclusion(rs.getInt("conclusion"));
                com_eval_conditionList.add(com_eval_condition);
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
        return com_eval_conditionList;
    }

    public ComEvalConditionBean getComEvalCondition(int comId) throws Exception {
        ResultSet rs = null;
        String sql = "Select * From com_eval_condition Where com_id=" + comId;

        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ComEvalConditionBean com_eval_condition = new ComEvalConditionBean();
                com_eval_condition.setComId(rs.getInt("com_id"));
                com_eval_condition.setCevId(rs.getInt("cev_id"));
                com_eval_condition.setPaymentMethod(rs.getString("payment_method"));
                com_eval_condition.setGuaranteeContract(rs.getString("guarantee_contract"));
                com_eval_condition.setCustomsCost(rs.getString("customs_cost"));
                com_eval_condition.setTransportCost(rs.getString("transport_cost"));
                com_eval_condition.setOtherTax(rs.getString("other_tax"));
                com_eval_condition.setOtherCost(rs.getString("other_cost"));
                com_eval_condition.setTotalEval(rs.getString("total_eval"));
                com_eval_condition.setSubTotal(rs.getString("sub_total"));
                com_eval_condition.setFreightCharge(rs.getString("freight_charge"));
                com_eval_condition.setTestCertification(rs.getString("test_certification"));
                com_eval_condition.setSpareParts(rs.getString("spare_parts"));
                com_eval_condition.setGrossPrice(rs.getString("gross_price"));
                com_eval_condition.setDiscount(rs.getString("discount"));
                com_eval_condition.setNetPrice(rs.getString("net_price"));
                com_eval_condition.setShippingLocation(rs.getString("shipping_location"));
                com_eval_condition.setDeliverySchedule(rs.getString("delivery_schedule"));
                com_eval_condition.setPaymentTerms(rs.getString("payment_terms"));
                com_eval_condition.setWarranty(rs.getString("warranty"));
                com_eval_condition.setConclusion(rs.getInt("conclusion"));

                return com_eval_condition;
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

    public ComEvalConditionBean getComEvalConditionByName(String name) throws Exception {
        ResultSet rs = null;

        String sql = "select com_id from com_eval_condition where name='" + name + "'";
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ComEvalConditionBean com_eval_condition = new ComEvalConditionBean();
                com_eval_condition.setComId(rs.getInt("com_id"));

                return com_eval_condition;
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

    public void insertComEvalCondition(ComEvalConditionBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Insert Into com_eval_condition(cev_id, payment_method, guarantee_contract, customs_cost, tranpost_cost, other_tax, other_cost, "
                    + "total_eval, sub_total, freight_charge, test_certification, spare_parts, gross_price, discount, net_price, shipping_location, "
                    + "delivery_schedulem payment_terms, warrantym potc_deviation, conclusion)"
                    + " Values (" + bean.getCevId() + ",'" + bean.getPaymentMethod() + "','" + bean.getGuaranteeContract() + "','" + bean.getCustomsCost()
                    + "','" + bean.getTransportCost() + "','" + bean.getOtherTax() + "','" + bean.getOtherCost() + "','" + bean.getTotalEval()
                    + "','" + bean.getSubTotal() + "','" + bean.getFreightCharge() + "','" + bean.getTestCertification() + "','" + bean.getSpareParts()
                    + "','" + bean.getGrossPrice() + "','" + bean.getDiscount() + "','" + bean.getNetPrice() + "','" + bean.getShippingLocation()
                    + "','" + bean.getDeliverySchedule() + "','" + bean.getPaymentTerms() + "','" + bean.getWarranty() + "','" + bean.getPotcDeviation()
                    + ",'" + bean.getConclusion() + "')";
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateComEvalCondition(ComEvalConditionBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Update com_eval_condition Set "
                    + " cev_id=" + bean.getCevId()
                    + ", payment_method='" + bean.getPaymentMethod() + "'"
                    + ", guarantee_contract=" + bean.getGuaranteeContract()
                    + ", customs_cost='" + bean.getCustomsCost() + "'"
                    + ", transport_cost='" + bean.getTransportCost() + "'"
                    + ", other_tax=" + bean.getOtherTax()
                    + ", other_cost='" + bean.getOtherCost() + "'"
                    + ", total_eval='" + bean.getTotalEval() + "'"
                    + ", sub_total=" + bean.getSubTotal()
                    + ", freight_charge='" + bean.getFreightCharge() + "'"
                    + ", test_certification='" + bean.getTestCertification() + "'"
                    + ", spare_parts=" + bean.getSpareParts()
                    + ", gross_price='" + bean.getGrossPrice() + "'"
                    + ", discount='" + bean.getDiscount() + "'"
                    + ", net_price=" + bean.getNetPrice()
                    + ", shipping_location='" + bean.getShippingLocation() + "'"
                    + ", delivery_schedule='" + bean.getDeliverySchedule() + "'"
                    + ", payment_terms=" + bean.getPaymentTerms()
                    + ", warranty='" + bean.getWarranty() + "'"
                    + ", conclusion=" + bean.getConclusion()
                    + " Where com_id=" + bean.getComId();
            //System.out.println("sql=" + sql);
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteComEvalCondition(int comId) throws Exception {
        int result = 0;
        try {
            String sql = "Delete From com_eval_condition " + " Where com_id=" + comId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleComEvalCondition(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "name";
                break;
        }
        ResultSet rs = null;

        String sql = "Select * From com_eval_condition Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by com_id DESC";

        ArrayList com_eval_conditionList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalConditionBean com_eval_condition = null;
            while (rs.next()) {
                com_eval_condition = new ComEvalConditionBean();
                com_eval_condition.setComId(rs.getInt("com_id"));
                com_eval_condition.setCevId(rs.getInt("cev_id"));
                com_eval_condition.setPaymentMethod(rs.getString("payment_method"));
                com_eval_condition.setGuaranteeContract(rs.getString("guarantee_contract"));
                com_eval_condition.setCustomsCost(rs.getString("customs_cost"));
                com_eval_condition.setTransportCost(rs.getString("transport_cost"));
                com_eval_condition.setOtherTax(rs.getString("other_tax"));
                com_eval_condition.setOtherCost(rs.getString("other_cost"));
                com_eval_condition.setTotalEval(rs.getString("total_eval"));
                com_eval_condition.setSubTotal(rs.getString("sub_total"));
                com_eval_condition.setFreightCharge(rs.getString("freight_charge"));
                com_eval_condition.setTestCertification(rs.getString("test_certification"));
                com_eval_condition.setSpareParts(rs.getString("spare_parts"));
                com_eval_condition.setGrossPrice(rs.getString("gross_price"));
                com_eval_condition.setDiscount(rs.getString("discount"));
                com_eval_condition.setNetPrice(rs.getString("net_price"));
                com_eval_condition.setShippingLocation(rs.getString("shipping_location"));
                com_eval_condition.setDeliverySchedule(rs.getString("delivery_schedule"));
                com_eval_condition.setPaymentTerms(rs.getString("payment_terms"));
                com_eval_condition.setWarranty(rs.getString("warranty"));
                com_eval_condition.setConclusion(rs.getInt("conclusion"));
                com_eval_conditionList.add(com_eval_condition);
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
        return com_eval_conditionList;

    }

    public ArrayList searchAdvComEvalCondition(ComEvalConditionBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From com_eval_condition Where 1 ";

        if (bean.getComId() != 0) {
            sql = sql + " AND com_id = " + bean.getComId();
        }
        if (bean.getCevId() != 0) {
            sql = sql + " AND cev_id = " + bean.getCevId();
        }
        if (!StringUtil.isBlankOrNull(bean.getPaymentMethod())) {
            sql = sql + " AND payment_method LIKE '%" + bean.getPaymentMethod() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getGuaranteeContract())) {
            sql = sql + " AND guarantee_contract LIKE '%" + bean.getGuaranteeContract() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getCustomsCost())) {
            sql = sql + " AND customs_cost LIKE '%" + bean.getCustomsCost() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getTransportCost())) {
            sql = sql + " AND transport_cost LIKE '%" + bean.getTransportCost() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getOtherTax())) {
            sql = sql + " AND other_tax LIKE '%" + bean.getOtherTax() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getOtherCost())) {
            sql = sql + " AND other_cost LIKE '%" + bean.getOtherCost() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getTotalEval())) {
            sql = sql + " AND total_eval LIKE '%" + bean.getTotalEval() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getSubTotal())) {
            sql = sql + " AND sub_total LIKE '%" + bean.getSubTotal() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getFreightCharge())) {
            sql = sql + " AND freight_charge LIKE '%" + bean.getFreightCharge() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getTestCertification())) {
            sql = sql + " AND test_certification LIKE '%" + bean.getTestCertification() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getSpareParts())) {
            sql = sql + " AND spare_parts LIKE '%" + bean.getSpareParts() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getGrossPrice())) {
            sql = sql + " AND gross_price LIKE '%" + bean.getGrossPrice() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getDiscount())) {
            sql = sql + " AND discount LIKE '%" + bean.getDiscount() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getNetPrice())) {
            sql = sql + " AND net_price LIKE '%" + bean.getNetPrice() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getShippingLocation())) {
            sql = sql + " AND shipping_location LIKE '%" + bean.getShippingLocation() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getDeliverySchedule())) {
            sql = sql + " AND delivery_schedule LIKE '%" + bean.getDeliverySchedule() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getPaymentTerms())) {
            sql = sql + " AND payment_terms LIKE '%" + bean.getPaymentTerms() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getWarranty())) {
            sql = sql + " AND warranty LIKE '%" + bean.getWarranty() + "%'";
        }
        if (bean.getConclusion() != 0) {
            sql = sql + " AND conclusion =" + bean.getConclusion();
        }

        sql = sql + " Order by com_id DESC";

        ArrayList com_eval_conditionList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalConditionBean com_eval_condition = null;
            while (rs.next()) {
                com_eval_condition = new ComEvalConditionBean();
                com_eval_condition.setComId(rs.getInt("com_id"));
                com_eval_condition.setCevId(rs.getInt("cev_id"));
                com_eval_condition.setPaymentMethod(rs.getString("payment_method"));
                com_eval_condition.setGuaranteeContract(rs.getString("guarantee_contract"));
                com_eval_condition.setCustomsCost(rs.getString("customs_cost"));
                com_eval_condition.setTransportCost(rs.getString("transport_cost"));
                com_eval_condition.setOtherTax(rs.getString("other_tax"));
                com_eval_condition.setOtherCost(rs.getString("other_cost"));
                com_eval_condition.setTotalEval(rs.getString("total_eval"));
                com_eval_condition.setSubTotal(rs.getString("sub_total"));
                com_eval_condition.setFreightCharge(rs.getString("freight_charge"));
                com_eval_condition.setTestCertification(rs.getString("test_certification"));
                com_eval_condition.setSpareParts(rs.getString("spare_parts"));
                com_eval_condition.setGrossPrice(rs.getString("gross_price"));
                com_eval_condition.setDiscount(rs.getString("discount"));
                com_eval_condition.setNetPrice(rs.getString("net_price"));
                com_eval_condition.setShippingLocation(rs.getString("shipping_location"));
                com_eval_condition.setDeliverySchedule(rs.getString("delivery_schedule"));
                com_eval_condition.setPaymentTerms(rs.getString("payment_terms"));
                com_eval_condition.setWarranty(rs.getString("warranty"));
                com_eval_condition.setConclusion(rs.getInt("conclusion"));
                com_eval_conditionList.add(com_eval_condition);
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
        return com_eval_conditionList;
    }
}
