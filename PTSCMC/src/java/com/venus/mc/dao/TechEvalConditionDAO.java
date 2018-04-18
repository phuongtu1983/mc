/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.TechEvalConditionBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class TechEvalConditionDAO extends BasicDAO {

    public TechEvalConditionDAO() {
    }

    public ArrayList getTechEvalConditions()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tech_eval_condition Order by tec_id ASC";

        ArrayList tech_eval_conditionList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            TechEvalConditionBean tech_eval_condition = null;
            while (rs.next()) {
                tech_eval_condition = new TechEvalConditionBean();
                tech_eval_condition.setTecId(rs.getInt("tec_id"));
                tech_eval_condition.setDetId(rs.getInt("det_id"));
                tech_eval_condition.setCertificateAttach(rs.getString("certificate_attach"));
                tech_eval_condition.setDeliveryTime(rs.getDate("delivery_time"));
                tech_eval_condition.setTestCertificate(rs.getString("test_certificate"));
                tech_eval_condition.setOrigin(rs.getString("origin"));
                tech_eval_condition.setMoreRequire(rs.getString("more_require"));
                tech_eval_condition.setWarranty(rs.getString("warranty"));
                tech_eval_condition.setConlusion(rs.getInt("conlustion"));
                tech_eval_conditionList.add(tech_eval_condition);
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
        return tech_eval_conditionList;
    }

    public TechEvalConditionBean getTechEvalCondition(int tecId) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tech_eval_condition Where tec_id=" + tecId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                TechEvalConditionBean tech_eval_condition = new TechEvalConditionBean();
                tech_eval_condition.setTecId(rs.getInt("tec_id"));
                tech_eval_condition.setDetId(rs.getInt("det_id"));
                tech_eval_condition.setCertificateAttach(rs.getString("certificate_attach"));
                tech_eval_condition.setDeliveryTime(rs.getDate("delivery_time"));
                tech_eval_condition.setTestCertificate(rs.getString("test_certificate"));
                tech_eval_condition.setOrigin(rs.getString("origin"));
                tech_eval_condition.setMoreRequire(rs.getString("more_require"));
                tech_eval_condition.setWarranty(rs.getString("warranty"));
                tech_eval_condition.setConlusion(rs.getInt("conlustion"));

                return tech_eval_condition;
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

    public void insertTechEvalCondition(TechEvalConditionBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {

            String sql = "";



            sql = "Insert Into tech_eval_condition(det_id, certificate_attach, delivery_time, test_certificate, origin, more_require, warranty, conlustion)"
                    + " Values (" + bean.getDetId() + ",'" + bean.getCertificateAttach() + "','" + bean.getDeliveryTime()
                    + "','" + bean.getTestCertificate() + "','" + bean.getOrigin() + "','" + bean.getMoreRequire()
                    + "','" + bean.getWarranty() + "'," + bean.getConlusion() + ")";

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

    public void updateTechEvalCondition(TechEvalConditionBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {



            String sql = "Update tech_eval_condition Set "
                    + " det_id=" + bean.getDetId()
                    + ", certificate_attach='" + bean.getCertificateAttach() + "'"
                    + ", delivery_time='" + bean.getDeliveryTime() + "'"
                    + ", test_certificate='" + bean.getTestCertificate() + "'"
                    + ", origin='" + bean.getOrigin() + "'"
                    + ", more_require='" + bean.getMoreRequire() + "'"
                    + ", warranty='" + bean.getWarranty() + "'"
                    + ", conlustion=" + bean.getConlusion()
                    + " Where tec_id=" + bean.getTecId();
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

    public int deleteTechEvalCondition(int tecId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From tech_eval_condition " + " Where tec_id=" + tecId;
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

    public ArrayList searchSimpleTechEvalCondition(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "det_id";
                break;
        }
        ResultSet rs = null;

        String sql = "Select * From tech_eval_condition Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " = " + strFieldvalue;
        }
        sql = sql + " Order by tec_id DESC";

        ArrayList tech_eval_conditionList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            TechEvalConditionBean tech_eval_condition = null;
            while (rs.next()) {
                tech_eval_condition = new TechEvalConditionBean();
                tech_eval_condition.setTecId(rs.getInt("tec_id"));
                tech_eval_condition.setDetId(rs.getInt("det_id"));
                tech_eval_condition.setCertificateAttach(rs.getString("certificate_attach"));
                tech_eval_condition.setDeliveryTime(rs.getDate("delivery_time"));
                tech_eval_condition.setTestCertificate(rs.getString("test_certificate"));
                tech_eval_condition.setOrigin(rs.getString("origin"));
                tech_eval_condition.setMoreRequire(rs.getString("more_require"));
                tech_eval_condition.setWarranty(rs.getString("warranty"));
                tech_eval_condition.setConlusion(rs.getInt("conlustion"));
                tech_eval_conditionList.add(tech_eval_condition);
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
        return tech_eval_conditionList;

    }

    public ArrayList searchAdvTechEvalCondition(TechEvalConditionBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tech_eval_condition Where 1 ";


        if (bean.getTecId() != 0) {
            sql = sql + " AND tec_id =" + bean.getTecId();
        }
        if (bean.getDetId() != 0) {
            sql = sql + " AND det_id =" + bean.getDetId();
        }
        if (!StringUtil.isBlankOrNull(bean.getCertificateAttach())) {
            sql = sql + " AND certificate_attach LIKE '%" + bean.getDetId() + "%'";
        }
        if (bean.getDeliveryTime() != null) {
            sql = sql + " AND delivery_time = '" + bean.getDeliveryTime() + "'";
        }
        if (!StringUtil.isBlankOrNull(bean.getTestCertificate())) {
            sql = sql + " AND test_certificate LIKE '%" + bean.getTestCertificate() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getOrigin())) {
            sql = sql + " AND origin LIKE '%" + bean.getOrigin() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getMoreRequire())) {
            sql = sql + " AND more_require LIKE '%" + bean.getMoreRequire() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getWarranty())) {
            sql = sql + " AND warranty LIKE '%" + bean.getWarranty() + "%'";
        }
        if (bean.getConlusion() != 0) {
            sql = sql + " AND conlustion =" + bean.getConlusion();
        }

        sql = sql + " Order by tec_id DESC";

        ArrayList tech_eval_conditionList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            TechEvalConditionBean tech_eval_condition = null;

            while (rs.next()) {
                tech_eval_condition = new TechEvalConditionBean();
                tech_eval_condition.setTecId(rs.getInt("tec_id"));
                tech_eval_condition.setDetId(rs.getInt("det_id"));
                tech_eval_condition.setCertificateAttach(rs.getString("certificate_attach"));
                tech_eval_condition.setDeliveryTime(rs.getDate("delivery_time"));
                tech_eval_condition.setTestCertificate(rs.getString("test_certificate"));
                tech_eval_condition.setOrigin(rs.getString("origin"));
                tech_eval_condition.setMoreRequire(rs.getString("more_require"));
                tech_eval_condition.setWarranty(rs.getString("warranty"));
                tech_eval_condition.setConlusion(rs.getInt("conlustion"));
                tech_eval_conditionList.add(tech_eval_condition);
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
        return tech_eval_conditionList;
    }
}
