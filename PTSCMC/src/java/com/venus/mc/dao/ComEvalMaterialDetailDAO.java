/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ComEvalMaterialDetailBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.util.MCUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mai vinh loc
 */
public class ComEvalMaterialDetailDAO extends BasicDAO {

    public ComEvalMaterialDetailDAO() {
    }

    public ArrayList getComEvalMaterialDetails()
            throws Exception {
        ResultSet rs = null;

        //String sql = "Select * From com_eval_material_detail Order by cem_id ASC";
        String sql = "SELECT c.*, c1.currency FROM com_eval_material_detail AS c"
                + " LEFT JOIN com_eval_material_vendor AS c1 ON c.cem_id = c1.cem_id"
                + " ORDER BY c.cem_id ASC ";

        ArrayList com_eval_material_detailList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalMaterialDetailBean com_eval_material_detail = null;
            while (rs.next()) {
                com_eval_material_detail = new ComEvalMaterialDetailBean();
                com_eval_material_detail.setCurrency(rs.getString("currency"));
                com_eval_material_detail.setCemId(rs.getInt("cem_id"));
                com_eval_material_detail.setDetId(rs.getInt("det_id"));
                com_eval_material_detail.setTerId(rs.getInt("ter_id"));
                com_eval_material_detail.setPrice(rs.getDouble("price"));
                com_eval_material_detail.setPriceCustom(rs.getDouble("price_custom"));
                com_eval_material_detail.setPriceTransport(rs.getDouble("price_transport"));
                com_eval_material_detail.setPriceTax(rs.getDouble("price_tax"));
                com_eval_material_detail.setPriceOtherCost(rs.getDouble("price_other_cost"));
                com_eval_material_detail.setPricePtscmc(rs.getDouble("price_ptscmc"));
                com_eval_material_detail.setTotal(NumberUtil.formatMoneyDefault(rs.getDouble("total"), com_eval_material_detail.getCurrency()));

                com_eval_material_detailList.add(com_eval_material_detail);
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
        return com_eval_material_detailList;
    }

    public ArrayList getComEvalMaterialDetails(int cemId, int terId) throws Exception {
        ResultSet rs = null;
        int stt = 1;
//        //String sql = "SELECT v.*,v1.name,v1.ven_id FROM com_eval_material_detail AS v LEFT JOIN tech_eval_vendor AS t ON v.ter_id=t.ter_id LEFT JOIN tender_evaluate_vendor AS t1 ON t1.tev_id = t.tev_id LEFT JOIN vendor AS v1 ON v1.ven_id = t1.ven_id Where cem_id=" + cemId;
//        String sql = "SELECT c.currency, e.eval_tbe, SUM(d.quantity) AS qt,m.name_vn,m.mat_id "
//                + ", d.det_id, d.cem_id, d.det_id_tp, d.unit, d.quantity, d.price, d.price_custom, d.price_transport"
//                + ", d.price_tax, d.price_other_cost, d.price_ptscmc, d.price_after, SUM(d.total) AS total, d.result"
//                + " FROM com_eval_material_detail AS d "
//                + " LEFT JOIN com_eval_material_vendor AS c ON c.cem_id = d.cem_id " // thêm lay currency
//                + " LEFT JOIN tender_plan_detail AS t ON t.det_id = d.det_id_tp LEFT JOIN tech_eval_detail AS e ON e.det_id_tp = d.det_id_tp LEFT JOIN request_detail AS rdet ON t.reqDetail_id = rdet.det_id LEFT JOIN material AS m ON e.mat_id = m.mat_id WHERE d.cem_id = " + cemId + " AND e.ter_id= " + terId + " GROUP BY m.name_vn ORDER BY rdet.req_id DESC, rdet.det_id ASC ";

        String sql = "SELECT currency, eval_tbe, SUM(qt) AS qt,name_vn,mat_id ,det_id, cem_id, det_id_tp, unit,quantity, price, price_custom, "
                + " price_transport, price_tax, price_other_cost, price_certificate, price_to_port, price_ptscmc,price_after, SUM(total) AS total, result "
                + " FROM (SELECT t.det_id AS tenDetId,c.currency, e.eval_tbe, d.quantity AS qt,m.name_vn,m.mat_id , d.det_id, d.cem_id, d.det_id_tp, d.unit, d.quantity, d.price, d.price_custom,  "
                + " d.price_transport, d.price_tax, d.price_other_cost, d.price_ptscmc, d.price_after, d.total AS total, d.result, d.price_certificate, d.price_to_port  "
                + " FROM com_eval_material_detail AS d   "
                + " LEFT JOIN com_eval_material_vendor AS c ON c.cem_id = d.cem_id   "
                + " LEFT JOIN tender_plan_detail AS t ON t.det_id = d.det_id_tp  "
                + " LEFT JOIN tech_eval_detail AS e ON e.det_id_tp = d.det_id_tp  "
                + " LEFT JOIN request_detail AS rdet ON t.reqDetail_id = rdet.det_id  "
            //    + " LEFT JOIN material AS m ON e.mat_id = m.mat_id  "
                + " LEFT JOIN material AS m ON t.mat_id = m.mat_id  "
                + " WHERE d.cem_id = " + cemId + " AND e.ter_id= " + terId
         //       + "ORDER BY rdet.req_id DESC, rdet.det_id ASC )AS tbl GROUP BY name_vn ";
//               + " ORDER BY t.det_id ASC )AS tbl GROUP BY name_vn ORDER BY tbl.tenDetId ";
               + " ORDER BY t.det_id ASC)AS tbl GROUP BY name_vn ORDER BY tbl.tenDetId asc";
                
        ArrayList list = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalMaterialDetailBean com_eval_material_detail = null;
            while (rs.next()) {
                com_eval_material_detail = new ComEvalMaterialDetailBean();
                com_eval_material_detail.setDetId(rs.getInt("det_id"));
                com_eval_material_detail.setDetIdTp(rs.getInt("det_id_tp"));
                com_eval_material_detail.setCurrency(rs.getString("currency"));
                com_eval_material_detail.setCemId(rs.getInt("cem_id"));
                com_eval_material_detail.setUnit(rs.getString("unit"));
                com_eval_material_detail.setQuantity(rs.getDouble("qt"));
                com_eval_material_detail.setPrice(rs.getDouble("price"));
                com_eval_material_detail.setPriceCustom(rs.getDouble("price_custom"));
                com_eval_material_detail.setPriceTransport(rs.getDouble("price_transport"));
                com_eval_material_detail.setPriceTax(rs.getDouble("price_tax"));
                com_eval_material_detail.setPriceOtherCost(rs.getDouble("price_other_cost"));
                com_eval_material_detail.setPriceCertificate(rs.getDouble("price_certificate"));
                com_eval_material_detail.setPriceToPort(rs.getDouble("price_to_port"));
                com_eval_material_detail.setPricePtscmc(rs.getDouble("price_ptscmc"));
                com_eval_material_detail.setPriceAfter(rs.getDouble("price_after"));
                com_eval_material_detail.setTotal(NumberUtil.formatMoneyDefault(rs.getDouble("total"), com_eval_material_detail.getCurrency()));
                com_eval_material_detail.setMatId(rs.getInt("mat_id"));
                com_eval_material_detail.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                com_eval_material_detail.setResult(rs.getInt("result"));
                com_eval_material_detail.setEvalTbe(rs.getInt("eval_tbe"));
                com_eval_material_detail.setStt(stt + "");
                if (rs.getInt("eval_tbe") == ComEvalMaterialDetailBean.RESULT1) {
                    com_eval_material_detail.setStatus(MCUtil.getBundleString("message.result1"));
                }
                if (rs.getInt("eval_tbe") == ComEvalMaterialDetailBean.RESULT2) {
                    com_eval_material_detail.setStatus(MCUtil.getBundleString("message.result2"));
                }
                if (rs.getInt("eval_tbe") == ComEvalMaterialDetailBean.RESULT3) {
                    com_eval_material_detail.setStatus(MCUtil.getBundleString("message.result3"));
                }
                list.add(com_eval_material_detail);
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
        return list;
    }

    public ComEvalMaterialDetailBean getComEvalMaterialDetailByName(String name) throws Exception {
        ResultSet rs = null;

        String sql = "select cem_id from com_eval_material_detail where name='" + name + "'";
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ComEvalMaterialDetailBean com_eval_material_detail = new ComEvalMaterialDetailBean();
                com_eval_material_detail.setCemId(rs.getInt("cem_id"));

                return com_eval_material_detail;
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

    public void insertComEvalMaterialDetail(ComEvalMaterialDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "";
            sql = "Insert Into com_eval_material_detail(cem_id, ter_id)"
                    + " Values (" + bean.getCemId() + "," + bean.getTerId() + ")";
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void insertComEvalMaterialDetailForComEvalMaterialDetail(int ceId, int tenId) throws Exception {
        ResultSet rs = null;
        try {
            String sql = "";
            String sql1 = "";
            String sql2 = "";
            String sql3 = "";
            int cemId = 0;

            sql = "Select t.ter_id,t.mat_id,t.unit,t.qty From tech_eval_detail as t left join tech_eval_vendor as r on t.ter_id=r.ter_id left join tech_eval as e on r.te_id=e.te_id  Where result = 1 and e.ten_id = " + tenId;

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                sql1 = "Insert Into com_eval_material_detail(ce_id, ter_id,mat_id,unit,quantity)"
                        + " Values (" + ceId + "," + rs.getInt("ter_id") + "," + rs.getInt("mat_id") + ",'" + rs.getString("unit") + "'," + rs.getInt("qty") + ")";
                cemId = DBUtil.executeInsert(sql1);
                sql3 = "Insert Into com_eval_material_detail_detail(cem_id, ter_id)"
                        + " Values (" + cemId + "," + rs.getInt("ter_id") + ")";
                DBUtil.executeInsert(sql3);
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

    public void updateComEvalMaterialDetail(ComEvalMaterialDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        ResultSet rs = null;
        try {
            String sql = "";
            int detIdTp = 0;
            sql = "Update com_eval_material_detail Set "
                    + "  price=" + bean.getPrice()
                    + ", price_custom=" + bean.getPriceCustom()
                    + ", price_transport=" + bean.getPriceTransport()
                    + ", price_tax=" + bean.getPriceTax()
                    + ", price_other_cost=" + bean.getPriceOtherCost()
                    + ", price_certificate=" + bean.getPriceCertificate()
                    + ", price_to_port=" + bean.getPriceToPort()
                    + ", price_ptscmc=" + bean.getPricePtscmc()
                    + ", price_after=" + bean.getPriceAfter()
                    + ", total=" + bean.getTotal()
                    + " Where cem_id=" + bean.getCemId() + " AND det_id_tp=" + bean.getDetIdTp() + "";
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateComEvalMaterialDetailResult(int tenId) throws Exception {

        ResultSet rs = null;
        try {
            String sql = "";
            int detIdTp = 0;
            sql = "SELECT * FROM tender_plan_detail AS d WHERE d.ten_id=" + tenId;

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                detIdTp = rs.getInt("det_id");
                updateComEvalMaterialDetailResultTemp(selectComEvalMaterialDetailResult(detIdTp), detIdTp);
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

    public int selectComEvalMaterialDetailResult(int detIdTp) throws Exception {
        ResultSet rs = null;

        try {


            String sql = "";
            int detId = 0;
//            sql = "SELECT * FROM com_eval_material_detail AS d WHERE d.total=(SELECT min(total) FROM com_eval_material_detail AS d WHERE d.det_id_tp="+ detIdTp+" and total>0)" ;
            sql = "SELECT * FROM com_eval_material_detail AS d WHERE d.total=(SELECT min(total) FROM com_eval_material_detail AS d WHERE d.det_id_tp=" + detIdTp + " and total>0) AND d.det_id_tp=" + detIdTp + " AND total>0 GROUP BY det_id_tp";

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                detId = rs.getInt("det_id");
            }

            return detId;
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

    public void updateComEvalMaterialDetailResultTemp(int detId, int detIdTp) throws Exception {
        ResultSet rs = null;

        try {
            String sql = "";
            int result[];
            int j = 0;
            int detId1[];
            detId1 = new int[99];
            result = new int[99];
            sql = "SELECT * FROM com_eval_material_detail AS d WHERE d.det_id_tp=" + detIdTp;

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                if (detId == rs.getInt("det_id")) {
                    result[j] = 1;
                } else {
                    result[j] = 2;
                }
                detId1[j] = rs.getInt("det_id");
                j++;
            }
            for (int i = 0; i < j; i++) {

                sql = "Update com_eval_material_detail Set "
                        + " result=" + result[i]
                        + " Where det_id=" + detId1[i] + "";

                System.out.println("sql=" + sql);
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

    public void updateComEvalMaterialDetailForComEval(ComEvalMaterialDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Update com_eval_material_detail Set "
                    + " ce_id=" + bean.getCemId()
                    + ", ter_id=" + bean.getTerId()
                    + " Where cem_id=" + bean.getCemId();
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteComEvalMaterialDetail(int cemId) throws Exception {
        int result = 0;
        try {
            String sql = "Delete From com_eval_material_detail " + " Where cem_id=" + cemId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleComEvalMaterialDetail(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "ven_id";
                break;
        }
        ResultSet rs = null;

        //String sql = "Select * From com_eval_material_detail left join com Where 1 ";
        String sql = "SELECT c.*, c1.currency FROM com_eval_material_detail AS c LEFT JOIN com_eval_material_vendor AS c1 ON c.cem_id = c1.cem_id WHERE 1";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " = " + strFieldvalue;
        }
        sql = sql + " Order by cem_id DESC";

        ArrayList com_eval_material_detailList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalMaterialDetailBean com_eval_material_detail = null;
            while (rs.next()) {
                com_eval_material_detail = new ComEvalMaterialDetailBean();
                com_eval_material_detail.setCemId(rs.getInt("cem_id"));
                com_eval_material_detail.setCurrency(rs.getString("currency"));
                com_eval_material_detail.setDetId(rs.getInt("det_id"));
                com_eval_material_detail.setTerId(rs.getInt("ter_id"));
                com_eval_material_detail.setPrice(rs.getDouble("price"));
                com_eval_material_detail.setPriceCustom(rs.getDouble("price_custom"));
                com_eval_material_detail.setPriceTransport(rs.getDouble("price_transport"));
                com_eval_material_detail.setPriceTax(rs.getDouble("price_tax"));
                com_eval_material_detail.setPriceOtherCost(rs.getDouble("price_other_cost"));
                com_eval_material_detail.setPricePtscmc(rs.getDouble("price_ptscmc"));
                com_eval_material_detail.setTotal(NumberUtil.formatMoneyDefault(rs.getDouble("total"), com_eval_material_detail.getCurrency()));
                com_eval_material_detailList.add(com_eval_material_detail);
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
        return com_eval_material_detailList;

    }

    public ArrayList searchAdvComEvalMaterialDetail(ComEvalMaterialDetailBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "SELECT c.*, c1.currency FROM com_eval_material_detail AS c LEFT JOIN com_eval_material_vendor AS c1 ON c.cem_id = c1.cem_id WHERE 1 ";

        if (bean.getCemId() != 0) {
            sql = sql + " AND cem_id = " + bean.getCemId();
        }

        if (bean.getTerId() != 0) {
            sql = sql + " AND ven_id = " + bean.getTerId();
        }

        sql = sql + " Order by cem_id DESC";

        ArrayList com_eval_material_detailList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalMaterialDetailBean com_eval_material_detail = null;
            while (rs.next()) {
                com_eval_material_detail.setCemId(rs.getInt("cem_id"));
                com_eval_material_detail.setCurrency(rs.getString("currency"));
                com_eval_material_detail.setCemId(rs.getInt("cem_id"));
                com_eval_material_detail.setDetId(rs.getInt("det_id"));
                com_eval_material_detail.setTerId(rs.getInt("ter_id"));
                com_eval_material_detail.setPrice(rs.getDouble("price"));
                com_eval_material_detail.setPriceCustom(rs.getDouble("price_custom"));
                com_eval_material_detail.setPriceTransport(rs.getDouble("price_transport"));
                com_eval_material_detail.setPriceTax(rs.getDouble("price_tax"));
                com_eval_material_detail.setPriceOtherCost(rs.getDouble("price_other_cost"));
                com_eval_material_detail.setPricePtscmc(rs.getDouble("price_ptscmc"));
                com_eval_material_detail.setTotal(NumberUtil.formatMoneyDefault(rs.getDouble("total"), com_eval_material_detail.getCurrency()));
                com_eval_material_detail.setResult(rs.getInt("result"));
                com_eval_material_detailList.add(com_eval_material_detail);
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
        return com_eval_material_detailList;
    }
}
