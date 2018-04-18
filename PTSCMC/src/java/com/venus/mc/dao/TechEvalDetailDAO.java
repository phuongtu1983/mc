/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.TechEvalDetailBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class TechEvalDetailDAO extends BasicDAO {

    public TechEvalDetailDAO() {
    }

    public ArrayList getTechEvalDetails()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tech_eval_detail Order by det_id ASC";

        ArrayList tech_eval_detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            TechEvalDetailBean tech_eval_detail = null;
            while (rs.next()) {
                tech_eval_detail = new TechEvalDetailBean();
                tech_eval_detail.setDetId(rs.getInt("det_id"));
                tech_eval_detail.setTerId(rs.getInt("tev_id"));
                tech_eval_detail.setMatId(rs.getInt("mat_id"));
                tech_eval_detail.setPropose(rs.getString("propose"));
                tech_eval_detail.setDeliveryTime(rs.getString("delivery_time"));
                tech_eval_detail.setOtherCondition(rs.getString("other_condition"));
                tech_eval_detail.setOffer(rs.getString("offer"));
                tech_eval_detail.setSpec(rs.getString("spec"));
                tech_eval_detail.setQty(rs.getString("qty"));
                tech_eval_detail.setUnit(rs.getString("unit"));
                tech_eval_detail.setEvalTbe(rs.getInt("eval_tbe"));
                tech_eval_detailList.add(tech_eval_detail);
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
        return tech_eval_detailList;
    }

    public TechEvalDetailBean getTechEvalDetail(int terId, int venId) throws Exception {
        ResultSet rs = null;
        String sql = "SELECT DISTINCT e.ten_id,r.ter_id,r.certificate_attach,r.result,P.EVAL_KIND FROM tech_eval_vendor AS r LEFT JOIN tech_eval_detail AS d ON r.ter_id=d.ter_id LEFT JOIN TECH_EVAL AS E ON E.`TE_ID` = R.TE_ID LEFT JOIN `TENDER_PLAN` AS P ON P.`TEN_ID` = E.`TEN_ID` WHERE r.ter_id=" + terId;

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

            //System.out.println("sql=" + sql);
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                TechEvalDetailBean tech_eval_detail = new TechEvalDetailBean();
                tech_eval_detail.setTenId(rs.getInt("e.ten_id"));
                tech_eval_detail.setTerId(rs.getInt("r.ter_id"));
                tech_eval_detail.setVendorName(StringUtil.decodeString(vendorName));
                tech_eval_detail.setCertificateAttach(rs.getString("r.certificate_attach"));
                tech_eval_detail.setResult(rs.getString("r.result"));
                tech_eval_detail.setVenId(venId);
                tech_eval_detail.setKind(rs.getInt("eval_kind"));
                return tech_eval_detail;
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

    public void insertTechEvalDetail(TechEvalDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {

            String sql = "";



            sql = "Insert Into tech_eval_detail(tev_id, mat_id, propose, delivery_time, other_condition, offer, spec, qty, eval_tbe)"
                    + " Values (" + bean.getTerId() + "," + bean.getMatId() + ",'" + bean.getPropose() + "','" + bean.getDeliveryTime()
                    + "','" + bean.getOtherCondition() + "','" + bean.getOffer() + "','" + bean.getSpec() + "','" + bean.getQty()
                    + "," + bean.getEvalTbe() + ")";

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

    public void updateTechEvalDetail(TechEvalDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        ResultSet rs = null;
        try {
            String sql = "select distinct ter_id, mat_id from tech_eval_detail as ted where ted.det_id=" + bean.getDetId();
            rs = DBUtil.executeQuery(sql);
            int matId = 0;
            int terId = 0;
            while (rs.next()) {
                matId = rs.getInt("mat_id");
                terId = rs.getInt("ter_id");
                if (rs != null) {
                    DBUtil.closeConnection(rs);
                }

                break;
            }

            sql = "Update tech_eval_detail Set "
                    + //          " ter_id=" + bean.getTerId() +
                    //          ", mat_id=" + bean.getMatId() +
                    //           ", propose='" + bean.getPropose() + "'" +
                    " delivery_time='" + bean.getDeliveryTime() + "'"
                    + ", other_condition='" + bean.getOtherCondition() + "'"
                    + //             ", offer='" + bean.getOffer() + "'" +
                    ", spec='" + bean.getSpec() + "'"
                    + //              ", qty='" + bean.getQty() + "'" +
                    ", eval_tbe=" + bean.getEvalTbe()
                    + " Where mat_id = " + matId + " and ter_id = " + terId;
            //System.out.println("sql=" + sql);
            DBUtil.executeUpdate(sql);
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

    public void updateTechEvalDetailMatId(int matId, int detId) throws Exception {




        try {



            String sql = "Update tech_eval_detail AS t Set "
                    + " spec = (SELECT name_vn FROM material AS m WHERE m.mat_id = " + matId + " ) "
                    + ", mat_id = " + matId + ""
                    + " Where det_id=" + detId;
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

    public void updateTenderPlanDetailMatId(int matId, int tenId, int detId) throws Exception {
        ResultSet rs = null;
        try {
//            String sql1 = "select mat_id from tech_eval_detail as ted where ted.det_id=" + detId;
//
//            rs = DBUtil.executeQuery(sql1);
//
//            int matIdTemp = 0;
//            while (rs.next()) {
//                matIdTemp = rs.getInt("mat_id");
//            }
//
//            String sql = "Update tender_plan_detail AS t Set "
//                    + " mat_id_temp = " + matId + ""
//                    + " Where ten_id=" + tenId + " AND mat_id = " + matIdTemp;

            String sql1 = "select det_id_tp from tech_eval_detail as ted where ted.det_id=" + detId;

            rs = DBUtil.executeQuery(sql1);

            int detIdTp = 0;
            while (rs.next()) {
                detIdTp = rs.getInt("det_id_tp");
            }
            
            // mat_id
            
            sql1 = "select mat_id from tender_plan_detail as ted where ted.det_id=" + detIdTp;

            rs = DBUtil.executeQuery(sql1);

            int mat_id = 0;
            while (rs.next()) {
                mat_id = rs.getInt("mat_id");
            }
            //

            String sql = "Update tender_plan_detail AS t Set "
                    + " mat_id_temp = " + matId + ""
                    + " Where ten_id=" + tenId + " and mat_id = " + mat_id;
            //System.out.println("sql=" + sql);
            DBUtil.executeUpdate(sql);
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
    
    public void updateTenderPlanDetailMatIdNon(int matIdTemp, int tenId, int detId) throws Exception {
        ResultSet rs = null;
        try {
            
            String sql = "Update tender_plan_detail AS t Set "
                    + " mat_id_temp = " + matIdTemp + ""
                    + ", confirm = 1"
                    + " Where ten_id=" + tenId + " and mat_id = " + getMatIdByDetId(detId);
            //System.out.println("sql=" + sql);
            DBUtil.executeUpdate(sql);
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
    
    public int getMatIdByDetId(int detId) throws Exception {
        ResultSet rs = null;
        int mat_id = 0;
        try {

            String sql1 = "";
            // mat_id
            
            sql1 = "SELECT mat_id FROM bid_eval_sum_detail AS b WHERE b.det_id = " + detId;

            rs = DBUtil.executeQuery(sql1);

            
            while (rs.next()) {
                mat_id = rs.getInt("mat_id");
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
        return mat_id;
    }
    
    public int getMatIdTempByDetId(int detId) throws Exception {
        ResultSet rs = null;
        int mat_id = 0;
        try {

            String sql1 = "";
            // mat_id
            
            sql1 = "SELECT mat_id_temp FROM bid_eval_sum_detail AS b WHERE b.det_id = " + detId;

            rs = DBUtil.executeQuery(sql1);

            
            while (rs.next()) {
                mat_id = rs.getInt("mat_id_temp");
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
        return mat_id;
    }
    
    public void updateRequestDetail(int tenId) throws Exception {
        ResultSet rs = null;

        try {
            String sql = "SELECT mat_id, mat_id_temp,reqDetail_id FROM tender_plan_detail WHERE ten_id = " + tenId + " AND confirm = 1 AND mat_id_temp>0";
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                sql = "UPDATE request_detail SET mat_id = " + rs.getString("mat_id_temp") + " WHERE ten_id = " + tenId + " AND original_mat_id = " + rs.getString("mat_id");
                DBUtil.executeUpdate(sql);
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

    public void updateTechEvalDetailSpec(int matId, int detId) throws Exception {
        ResultSet rs = null;
        try {
            String sql1 = "select ter_id, mat_id from tech_eval_detail as ted where ted.det_id=" + detId;


            rs = DBUtil.executeQuery(sql1);
            int terId = 0;
            int matIdTemp = 0;
            while (rs.next()) {
                terId = rs.getInt("ter_id");
                matIdTemp = rs.getInt("mat_id");
            }

            String sql = "Update tech_eval_detail AS t Set "
                    + " spec = (SELECT name_vn FROM material AS m WHERE m.mat_id = " + matId + " ) "
                    + ", mat_id = " + matId + ""
                    + " Where mat_id=" + matIdTemp + " AND ter_id = " + terId;
            //System.out.println("sql=" + sql);
            DBUtil.executeUpdate(sql);
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
    
    public void updateBidEvalSumDetailMatIdTemp(int matIdTemp,int tenId, int detId) throws Exception {
        ResultSet rs = null;
        try {
            
            String sql = "Update bid_eval_sum_detail AS t Set "
                    + " mat_id_temp = " + matIdTemp + ""
                    + " WHERE mat_id =  " + getMatIdByDetId(detId) + " AND t.bes_id = (SELECT b.bes_id FROM bid_eval_sum AS b WHERE b.ten_id = " + tenId + ")" ;
            //System.out.println("sql=" + sql);
            DBUtil.executeUpdate(sql);
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

    public int deleteTechEvalDetail(int detId) throws Exception {
        int result = 0;
        try {
            String sql = "Delete From tech_eval_detail " + " Where det_id=" + detId;
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

    public ArrayList searchSimpleTechEvalDetail(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "tev_id";
                break;
        }
        ResultSet rs = null;

        String sql = "Select * From tech_eval_detail Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " = " + strFieldvalue;
        }
        sql = sql + " Order by det_id DESC";

        ArrayList tech_eval_detailList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            TechEvalDetailBean tech_eval_detail = null;
            while (rs.next()) {
                tech_eval_detail = new TechEvalDetailBean();
                tech_eval_detail.setDetId(rs.getInt("det_id"));
                tech_eval_detail.setTerId(rs.getInt("tev_id"));
                tech_eval_detail.setMatId(rs.getInt("mat_id"));
                tech_eval_detail.setPropose(rs.getString("propose"));
                tech_eval_detail.setDeliveryTime(rs.getString("delivery_time"));
                tech_eval_detail.setOtherCondition(rs.getString("other_condition"));
                tech_eval_detail.setOffer(rs.getString("offer"));
                tech_eval_detail.setSpec(rs.getString("spec"));
                tech_eval_detail.setQty(rs.getString("qty"));
                tech_eval_detail.setUnit(rs.getString("unit"));
                tech_eval_detail.setEvalTbe(rs.getInt("eval_tbe"));
                tech_eval_detailList.add(tech_eval_detail);
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
        return tech_eval_detailList;

    }

    public ArrayList searchAdvTechEvalDetail(TechEvalDetailBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tech_eval_detail Where 1 ";

        if (bean.getTerId() != 0) {
            sql = sql + " AND tev_id =" + bean.getTerId();
        }
        if (bean.getMatId() != 0) {
            sql = sql + " AND mat_id =" + bean.getMatId();
        }
        if (!StringUtil.isBlankOrNull(bean.getPropose())) {
            sql = sql + " AND propose LIKE '%" + bean.getPropose() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getDeliveryTime())) {
            sql = sql + " AND delivery_time LIKE '%" + bean.getDeliveryTime() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getOtherCondition())) {
            sql = sql + " AND other_condition LIKE '%" + bean.getOtherCondition() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getOffer())) {
            sql = sql + " AND offer LIKE '%" + bean.getOffer() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getSpec())) {
            sql = sql + " AND spec LIKE '%" + bean.getSpec() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getQty())) {
            sql = sql + " AND qty LIKE '%" + bean.getQty() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getUnit())) {
            sql = sql + " AND unit LIKE '%" + bean.getUnit() + "%'";
        }
        if (bean.getEvalTbe() != 0) {
            sql = sql + " AND eval_tbe =" + bean.getEvalTbe();
        }

        sql = sql + " Order by det_id DESC";

        ArrayList tech_eval_detailList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            TechEvalDetailBean tech_eval_detail = null;

            while (rs.next()) {
                tech_eval_detail = new TechEvalDetailBean();
                tech_eval_detail.setDetId(rs.getInt("det_id"));
                tech_eval_detail.setTerId(rs.getInt("tev_id"));
                tech_eval_detail.setMatId(rs.getInt("mat_id"));
                tech_eval_detail.setPropose(rs.getString("propose"));
                tech_eval_detail.setDeliveryTime(rs.getString("delivery_time"));
                tech_eval_detail.setOtherCondition(rs.getString("other_condition"));
                tech_eval_detail.setOffer(rs.getString("offer"));
                tech_eval_detail.setSpec(rs.getString("spec"));
                tech_eval_detail.setQty(rs.getString("qty"));
                tech_eval_detail.setUnit(rs.getString("unit"));
                tech_eval_detail.setEvalTbe(rs.getInt("eval_tbe"));
                tech_eval_detailList.add(tech_eval_detail);
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
        return tech_eval_detailList;
    }
}
