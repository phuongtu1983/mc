/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ComEvalMaterialBean;
import com.venus.mc.bean.ComEvalMaterialDetailBean;
import com.venus.mc.bean.TenderEvaluateVendorBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.util.MCUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mai vinh loc
 */
public class ComEvalMaterialDAO extends BasicDAO {

    public ComEvalMaterialDAO() {
    }

    public ArrayList getComEvalMaterials()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select * From com_eval_material Order by cem_id ASC";
        ArrayList com_eval_materialList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalMaterialBean com_eval_material = null;
            while (rs.next()) {
                com_eval_material = new ComEvalMaterialBean();
                com_eval_material.setCemId(rs.getInt("cem_id"));
                com_eval_material.setCeId(rs.getInt("ce_id"));
                com_eval_material.setVenId(rs.getInt("ven_id"));
                com_eval_material.setMatId(rs.getInt("mat_id"));
                com_eval_materialList.add(com_eval_material);
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
        return com_eval_materialList;
    }

    public ComEvalMaterialBean getComEvalMaterialVendor(int cemId, int venId) throws Exception {
        ResultSet rs = null;

        //String sql = "SELECT m.*,c.ten_id FROM com_eval_material_vendor AS m  LEFT JOIN com_eval AS c ON m.ce_id=c.ce_id WHERE cem_id=" + cemId;
        String sql = "Select v.*,r.incoterm,r.ten_id,r.ven_id From com_eval_material_vendor as v  left join tech_eval_vendor as t on v.ter_id=t.ter_id left join tender_evaluate_vendor as r on t.tev_id=r.tev_id Where cem_id=" + cemId;
        try {
            String sql1 = "SELECT v.name FROM vendor AS v WHERE ven_id=" + venId;
            String vendorName = "";
            rs = DBUtil.executeQuery(sql1);
            while (rs.next()) {
                vendorName = rs.getString("name");
            }
            DBUtil.closeConnection(rs);
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ComEvalMaterialBean com_eval_material = new ComEvalMaterialBean();
                com_eval_material.setCemId(rs.getInt("cem_id"));
                com_eval_material.setCeId(rs.getInt("ce_id"));
                com_eval_material.setTenId(rs.getInt("ten_id"));
                com_eval_material.setTerId(rs.getInt("ter_id"));
                com_eval_material.setVenId(rs.getInt("ven_id"));
                com_eval_material.setVendorName(StringUtil.decodeString(vendorName));
                com_eval_material.setCurrency(rs.getString("currency"));
                if (rs.getInt("rates") == 0) {
                    com_eval_material.setRates(1.0);
                } else {
                    com_eval_material.setRates(rs.getDouble("rates"));
                }
                com_eval_material.setRatesAfter(NumberUtil.formatMoneyDefault(rs.getDouble("rates_after")));
                com_eval_material.setPaymentMethod(rs.getString("payment_method"));
                com_eval_material.setGuaranteeContract(rs.getString("guarantee_contract"));
                com_eval_material.setIncoterm(rs.getInt("incoterm"));

                return com_eval_material;
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

    public ComEvalMaterialBean getComEvalMaterialByName(String name) throws Exception {
        ResultSet rs = null;

        String sql = "select cem_id from com_eval_material where name='" + name + "'";
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ComEvalMaterialBean com_eval_material = new ComEvalMaterialBean();
                com_eval_material.setCemId(rs.getInt("cem_id"));

                return com_eval_material;
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

    public void insertComEvalMaterial(ComEvalMaterialBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Insert Into com_eval_material(ce_id, ven_id, mat_id, unit, quantity)"
                    + " Values (" + bean.getCeId() + "," + bean.getVenId() + "," + bean.getMatId() + ")";
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void insertComEvalMaterialForComEvalMaterial(int ceId, int tenId) throws Exception {
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {
            String sql = "";
            String sql1 = "";
            String sql2 = "";
            String sql3 = "";
            int cemId = 0;
            sql = "SELECT v.ter_id,c.cem_id FROM tech_eval_vendor AS v LEFT JOIN tender_evaluate_vendor AS r ON v.tev_id=r.tev_id LEFT JOIN com_eval_material_vendor AS c ON c.ter_id=v.ter_id WHERE c.cem_id IS NULL AND r.ten_id = " + tenId;

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                sql1 = "Insert Into com_eval_material_vendor(ce_id, ter_id)"
                        + " Values (" + ceId + "," + rs.getInt("ter_id") + ")";
                cemId = DBUtil.executeInsert(sql1);
                sql2 = "Select det_id_tp,qty,unit From tech_eval_detail Where ter_id = " + rs.getInt("ter_id");

                rs2 = DBUtil.executeQuery(sql2);
                while (rs2.next()) {
                    sql3 = "Insert Into com_eval_material_detail(cem_id, det_id_tp,quantity,unit)"
                            + " Values (" + cemId + "," + rs2.getInt("det_id_tp") + "," + rs2.getInt("qty") + ",'" + rs2.getString("unit") + "')";
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

    public void updateComEvalMaterial(ComEvalMaterialBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Update com_eval_material Set "
                    + " ce_id=" + bean.getCeId()
                    + ", ven_id=" + bean.getVenId()
                    + ", mat_id=" + bean.getMatId()
                    + " Where cem_id=" + bean.getCemId();
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateComEvalMaterialVendorForComEval(ComEvalMaterialBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Update com_eval_material_vendor Set "
                    + "  payment_method='" + bean.getPaymentMethod() + "'"
                    + ", guarantee_contract='" + bean.getGuaranteeContract() + "'"
                    + ", currency='" + bean.getCurrency() + "'"
                    + ", rates='" + bean.getRates() + "'"
                    + ", rates_after=" + bean.getRatesAfter()
                    + " Where cem_id=" + bean.getCemId();
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateTenderEvaluateVendorIncoterm(int tenId, int venId, int incoterm) throws Exception {
        try {
            String sql = "Update tender_evaluate_vendor Set "
                    + "  incoterm=" + incoterm + ""
                    + " Where ten_id=" + tenId + " AND ven_id = " + venId;
            //System.out.println("sql=" + sql);
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteComEvalMaterial(int cemId) throws Exception {
        int result = 0;
        try {
            String sql = "Delete From com_eval_material " + " Where cem_id=" + cemId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleComEvalMaterial(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "ven_id";
                break;
        }
        ResultSet rs = null;

        String sql = "Select * From com_eval_material Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " = " + strFieldvalue;
        }
        sql = sql + " Order by cem_id DESC";

        ArrayList com_eval_materialList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalMaterialBean com_eval_material = null;
            while (rs.next()) {
                com_eval_material = new ComEvalMaterialBean();
                com_eval_material.setCemId(rs.getInt("cem_id"));
                com_eval_material.setCeId(rs.getInt("ce_id"));
                com_eval_material.setVenId(rs.getInt("ven_id"));
                com_eval_material.setMatId(rs.getInt("mat_id"));

                com_eval_materialList.add(com_eval_material);
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
        return com_eval_materialList;

    }

    public ArrayList searchAdvComEvalMaterial(ComEvalMaterialBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From com_eval_material Where 1 ";

        if (bean.getCemId() != 0) {
            sql = sql + " AND cem_id = " + bean.getCemId();
        }
        if (bean.getCeId() != 0) {
            sql = sql + " AND ce_id = " + bean.getCeId();
        }
        if (bean.getVenId() != 0) {
            sql = sql + " AND ven_id = " + bean.getVenId();
        }

        sql = sql + " Order by cem_id DESC";

        ArrayList com_eval_materialList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalMaterialBean com_eval_material = null;
            while (rs.next()) {
                com_eval_material.setCemId(rs.getInt("cem_id"));
                com_eval_material.setCeId(rs.getInt("ce_id"));
                com_eval_material.setVenId(rs.getInt("ven_id"));
                com_eval_material.setMatId(rs.getInt("mat_id"));

                com_eval_materialList.add(com_eval_material);
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
        return com_eval_materialList;
    }

    public ArrayList getComEvalVendorMaterial(int ceId) throws Exception { //danh sach NCC cua Danh gia thuong mai tung phan
        ResultSet rs = null;

//        String sql = "select r.ter_id, v.ven_id, v.name, c.guarantee_contract, c.payment_method, v.kind, c.currency, c.ce_id"
//                + " from  com_eval_material_vendor AS c left join tech_eval_vendor AS r ON r.ter_id = c.ter_id"
//                + " left join tender_evaluate_vendor AS t ON t.tev_id = r.tev_id"
//                + " left join vendor AS v ON v.ven_id = t.ven_id WHERE r.result<>0 and c.ce_id=" + ceId;
        String sql = "select r.ter_id, v.ven_id, v.name, c.guarantee_contract, c.payment_method, v.kind, c.currency, c.ce_id, i.inc_name"
                + ", i.inc_id, c.rates "
                + " from com_eval_material_vendor AS c "
                + " left join tech_eval_vendor AS r ON r.ter_id = c.ter_id "
                + " left join tender_evaluate_vendor AS t ON t.tev_id = r.tev_id "
                + " left join vendor AS v ON v.ven_id = t.ven_id "
                + " left join incoterm as i on i.inc_id = t.incoterm"
                + " WHERE r.result<>0 and c.ce_id= " + ceId
                + " and v.ven_id in "
                + "(select distinct te2.ven_id"
                + " from com_eval_material_vendor AS c2,tech_eval_vendor AS r2,tech_eval_detail as t2, tender_evaluate_vendor as te2 "
                + " where r2.ter_id = c2.ter_id and t2.ter_id=r2.ter_id and r2.tev_id=te2.tev_id and c2.ce_id=" + ceId
                + " and t2.eval_tbe=1)"// 1 = dat
                + " order by t.tev_id";

        ArrayList arrBean = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            TenderEvaluateVendorBean vendor = null;
            while (rs.next()) {
                vendor = new TenderEvaluateVendorBean();
                vendor.setTerId(rs.getInt("ter_id"));
                vendor.setVendorName(StringUtil.decodeString(rs.getString("name")));
                vendor.setVenId(rs.getInt("ven_id"));
                vendor.setPaymentMethod(rs.getString("payment_method"));
                vendor.setGuaranteeContract(rs.getString("guarantee_contract"));
                vendor.setVenKind(rs.getInt("kind"));
                vendor.setCurrency(rs.getString("currency"));
                vendor.setCeId(rs.getInt("ce_id"));
                vendor.setIncoterm(rs.getInt("inc_id"));
                vendor.setIncortemText(rs.getString("inc_name"));
                vendor.setRates(rs.getDouble("rates"));
                arrBean.add(vendor);
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
        return arrBean;
    }

    public ArrayList getComEvalMaterial(int ceId, int terId) throws Exception { //Danh sach VTTB cua Danh gia thuong mai tung phan
        ResultSet rs = null;

//        String sql = "select rdet.det_id,rdet.req_id, e.eval_tbe,d.det_id_tp,v.name,m.mat_id, m.name_vn,SUM(d.quantity) AS qt, d.unit, d.price, d.price_custom, d.price_transport, d.price_tax"
//                + ", d.price_other_cost,d.total, d.result, r.request_number, price_after, o.name as project, d.price_ptscmc"
//                + " from com_eval_material_detail AS d "
//                + " left join com_eval_material_vendor AS c ON c.cem_id = d.cem_id"
//                + " left join tender_plan_detail AS t ON t.det_id = d.det_id_tp "
//                + " left join material AS m ON t.mat_id = m.mat_id  "
//                + " left join tech_eval_vendor AS t1 ON t1.ter_id = c.ter_id "
//                + " left join tender_evaluate_vendor AS t2 ON t2.tev_id = t1.tev_id "
//                + " LEFT JOIN tech_eval_detail AS e ON e.ter_id = c.ter_id AND e.det_id_tp = d.det_id_tp "
//                + " left join vendor AS v ON v.ven_id = t2.ven_id"
//                + //phuongtu them
//                " left join request_detail as rdet on rdet.det_id=t.reqDetail_id"
//                + " left join request as r on r.req_id=rdet.req_id"
//                + " left join organization as o on r.org_id=o.org_id "
//                + " WHERE c.ce_id =" + ceId + " and c.ter_id= " + terId
//                + " GROUP BY m.name_vn order by rdet.req_id desc, rdet.det_id asc";


        String sql = " SELECT eval_tbe,det_id_tp,NAME,mat_id, name_vn,SUM(qt) AS qt, unit, price, price_custom, price_transport, price_tax, price_other_cost, "
                + " total, result, request_number, price_after, project, price_ptscmc, price_certificate, price_to_port"
                + " FROM (SELECT  t.det_id AS tenDetId, e.eval_tbe,d.det_id_tp,v.NAME,m.mat_id, m.name_vn,d.quantity AS qt, d.unit, d.price, d.price_custom, d.price_transport, d.price_tax, d.price_other_cost, "
                + " d.total, d.result, r.request_number, price_after, o.NAME AS project, d.price_ptscmc, d.price_certificate, d.price_to_port "
                + " FROM com_eval_material_detail AS d "
                + " LEFT JOIN com_eval_material_vendor AS c ON c.cem_id = d.cem_id  "
                + " LEFT JOIN tender_plan_detail AS t ON t.det_id = d.det_id_tp   "
                + " LEFT JOIN material AS m ON t.mat_id = m.mat_id    "
                + " LEFT JOIN tech_eval_vendor AS t1 ON t1.ter_id = c.ter_id  "
                + " LEFT JOIN tender_evaluate_vendor AS t2 ON t2.tev_id = t1.tev_id  "
                + " LEFT JOIN tech_eval_detail AS e ON e.ter_id = c.ter_id AND e.det_id_tp = d.det_id_tp  "
                + " LEFT JOIN vendor AS v ON v.ven_id = t2.ven_id  "
                + " LEFT JOIN request_detail AS rdet ON rdet.det_id=t.reqDetail_id  "
                + " LEFT JOIN request AS r ON r.req_id=rdet.req_id  "
                + " LEFT JOIN organization AS o ON r.org_id=o.org_id   "
                + " WHERE c.ce_id =" + ceId + " AND c.ter_id= " + terId
                //   + " ORDER BY rdet.req_id DESC, rdet.det_id ASC) AS tbl GROUP BY  name_vn ";
//                + " ORDER BY t.det_id ASC) AS tbl GROUP BY  name_vn  ORDER BY tbl.tenDetId";
                + " ORDER BY t.det_id ASC) AS tbl GROUP BY  name_vn  ORDER BY tbl.tenDetId asc";
        ArrayList arrBean = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            int i = 1;
            while (rs.next()) {
                ComEvalMaterialDetailBean material = new ComEvalMaterialDetailBean();
                material.setCemId(i++);
                material.setDetIdTp(rs.getInt("det_id_tp"));
                material.setMatId(rs.getInt("mat_id"));
                material.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                material.setQuantityText(NumberUtil.formatMoneyDefault(rs.getDouble("qt")));
                material.setUnit(rs.getString("unit"));
                material.setPrice(rs.getDouble("price"));

                material.setPricePtscmc(rs.getDouble("price_ptscmc"));
                material.setPriceAfter(rs.getDouble("price_after"));
                material.setPriceCustom(rs.getDouble("price_custom"));
                material.setPriceTransport(rs.getDouble("price_transport"));
                material.setPriceOtherCost(rs.getDouble("price_other_cost"));
                material.setPriceCertificate(rs.getDouble("price_certificate"));
                material.setPriceToPort(rs.getDouble("price_to_port"));

                material.setPricePtscmcText(NumberUtil.formatMoneyDefault(material.getPricePtscmc()));
                material.setPriceAfterText(NumberUtil.formatMoneyDefault(material.getPriceAfter()));
                material.setPriceCustomText(NumberUtil.formatMoneyDefault(material.getPriceCustom()));
                material.setPriceTransportText(NumberUtil.formatMoneyDefault(material.getPriceTransport()));
                material.setPriceTax(rs.getDouble("price_tax"));
                material.setPriceTaxText(NumberUtil.formatMoneyDefault(material.getPriceTax()));
                material.setPriceOtherCostText(NumberUtil.formatMoneyDefault(material.getPriceOtherCost()));
                material.setPriceCertificateText(NumberUtil.formatMoneyDefault(material.getPriceCertificate()));
                material.setPriceToPortText(NumberUtil.formatMoneyDefault(material.getPriceToPort()));
                material.setTotal(NumberUtil.formatMoneyDefault(rs.getDouble("total")));
                material.setResult(rs.getInt("eval_tbe"));
                if (material.getResult() == ComEvalMaterialDetailBean.RESULT1) {
                    material.setVendorName(StringUtil.decodeString(rs.getString("name")));
                    material.setPriceText(NumberUtil.formatMoneyDefault(material.getPriceAfter()));
                    material.setTotalText(material.getTotal() + "");
                    material.setPriceCurText(NumberUtil.formatMoneyDefault(material.getPrice()));
                } else if (material.getResult() == ComEvalMaterialDetailBean.RESULT2) {
                    material.setVendorName(StringUtil.decodeString(rs.getString("name")));
                    material.setPriceText("");
                    material.setTotalText(MCUtil.getBundleString("message.result2"));
                    material.setPriceCurText("");
                } else if (material.getResult() == ComEvalMaterialDetailBean.RESULT3) {
                    material.setVendorName(StringUtil.decodeString(rs.getString("name")));
                    material.setPriceText("");
                    material.setTotalText(MCUtil.getBundleString("message.result3"));
                    material.setPriceCurText("");
                }
                material.setRequestNumber(rs.getString("request_number"));
                material.setProjectName(StringUtil.decodeString(rs.getString("project")));
                arrBean.add(material);
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
        return arrBean;
    }

    public ArrayList getPriceOfComEvalMaterial(int ceId, int detIdTp) throws Exception { //Danh sach gia cua 1 VTTB cua cac NCC
        ResultSet rs = null;

        String sql = " SELECT v.NAME, c2.price"
                + "  com_eval_material_vendor AS c1 LEFT JOIN com_eval_material_detail AS c2 ON c1.cem_id = c2.cem_id"
                + " LEFT JOIN tech_eval_vendor AS t1 ON t1.ter_id = c1.ter_id"
                + " LEFT JOIN tender_evaluate_vendor AS t2 ON t2.tev_id = t1.tev_id  "
                + " LEFT JOIN vendor AS v ON v.ven_id = t2.ven_id "
                + " WHERE c1.ce_id =" + ceId + " AND c2.det_id_tp = " + detIdTp;

        ArrayList arrBean = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            int i = 1;
            while (rs.next()) {
                ComEvalMaterialDetailBean material = new ComEvalMaterialDetailBean();
                material.setVenId(i++);
                material.setVendorName(StringUtil.decodeString(rs.getString("name")));
                material.setPrice(rs.getDouble("price"));
                arrBean.add(material);
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
        return arrBean;
    }

    public ArrayList getComEvalMaterialDetailOk(int detIdTp, int cemId) throws Exception {
        ResultSet rs = null;

        String sql = "SELECT * FROM com_eval_material_detail WHERE det_id_tp IN ("
                + " SELECT t2.det_id FROM tender_plan_detail AS t1, tender_plan_detail AS t2 "
                + " WHERE t1.det_id=" + detIdTp + " AND t1.ten_id=t2.ten_id AND t1.mat_id=t2.mat_id)"
                + " AND cem_id=" + cemId;

        ArrayList com_eval_materialList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalMaterialDetailBean material = null;
            while (rs.next()) {
                material = new ComEvalMaterialDetailBean();
                material.setDetId(rs.getInt("det_id"));
                material.setCemId(rs.getInt("cem_id"));
                material.setDetIdTp(rs.getInt("det_id_tp"));
                material.setQuantity(rs.getInt("quantity"));
                material.setUnit(rs.getString("unit"));
                material.setPrice(rs.getDouble("price"));
                material.setPriceAfter(rs.getDouble("price_after"));
                material.setPriceCustom(rs.getDouble("price_custom"));
                material.setPriceTransport(rs.getDouble("price_transport"));
                material.setPriceTax(rs.getDouble("price_tax"));
                material.setPriceOtherCost(rs.getDouble("price_other_cost"));
                material.setPricePtscmc(rs.getDouble("price_ptscmc"));
                material.setTotal(rs.getDouble("total") + "");
                material.setResult(rs.getInt("result"));
                com_eval_materialList.add(material);
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
        return com_eval_materialList;

    }
}
