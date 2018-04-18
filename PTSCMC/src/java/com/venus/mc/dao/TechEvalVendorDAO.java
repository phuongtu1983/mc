/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ComEvalMaterialDetailBean;
import com.venus.mc.bean.TechEvalDetailBean;
import com.venus.mc.bean.TechEvalVendorBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.tenderplan.TenderPlanFormBean;
import com.venus.mc.util.MCUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class TechEvalVendorDAO extends BasicDAO {

    public TechEvalVendorDAO() {
    }

    public ArrayList getTechEvalVendors()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tech_eval_vendor Order by tev_id ASC";

        ArrayList tech_eval_vendorList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            TechEvalVendorBean tech_eval_vendor = null;
            while (rs.next()) {
                tech_eval_vendor = new TechEvalVendorBean();
                tech_eval_vendor.setTevId(rs.getInt("tev_id"));
                tech_eval_vendor.setTeId(rs.getInt("te_id"));
                tech_eval_vendor.setVenId(rs.getInt("ven_id"));
                tech_eval_vendorList.add(tech_eval_vendor);
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
        return tech_eval_vendorList;
    }

    public TechEvalVendorBean getTechEvalVendor(int tevId) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tech_eval_vendor Where tev_id=" + tevId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                TechEvalVendorBean tech_eval_vendor = new TechEvalVendorBean();
                tech_eval_vendor.setTevId(rs.getInt("tev_id"));
                tech_eval_vendor.setTeId(rs.getInt("te_id"));
                tech_eval_vendor.setVenId(rs.getInt("ven_id"));

                return tech_eval_vendor;
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

    public String getDeliveryTime(int tenId) throws Exception {
        ResultSet rs = null;

        String sql = "Select delivery_time From tender_plan Where ten_id=" + tenId;

        String result = "";
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                result = rs.getString("delivery_time");
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
        return result;
    }

    public void insertTechEvalVendor(TechEvalVendorBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {

            String sql = "";



            sql = "Insert Into tech_eval_vendor(te_id, ven_id)" + " Values (" + bean.getTeId() + "," + bean.getVenId() + ")";

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

    public void insertTechEvalVendorForTechEval(int teId, int tenId, int form) throws Exception {
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs5 = null;
        try {

            String sql = "";
            String sql1 = "";
            String sql2 = "";
            String sql3 = "";
            String sql4 = "";
            String certificate_attach = "";
            int terId = 0;

            sql = "SELECT c.cer_name FROM tender_certificate AS tc LEFT JOIN certificate AS c ON c.cer_id = tc.cer_id WHERE ten_id = " + tenId;


            rs5 = DBUtil.executeQuery(sql);
            while (rs5.next()) {
                if (certificate_attach.length() > 0) {
                    certificate_attach = certificate_attach + "\r\n" + rs5.getString("cer_name");
                } else {
                    certificate_attach = rs5.getString("cer_name");
                }
            }
            DBUtil.closeConnection(rs5);
            String bid = " 1 ";
            if (form == 1) {
                bid = " bidded = 1";
            }
            sql = "SELECT v.tev_id FROM tender_evaluate_vendor AS v LEFT JOIN tech_eval_vendor AS r ON v.tev_id=r.tev_id WHERE " + bid + " AND r.tev_id IS NULL AND ten_id = " + tenId;

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {

                sql1 = "Insert Into tech_eval_vendor(te_id, tev_id, certificate_attach)" + " Values (" + teId + "," + rs.getInt("tev_id") + ",'" + certificate_attach + "')";
//                stmt1.executeUpdate(sql1);
//                ResultSet rs1 = stmt1.getGeneratedKeys();
//                while (rs1.next()) {
//                    terId = rs1.getInt(stmt1.RETURN_GENERATED_KEYS);
//                }
                terId = DBUtil.executeInsert(sql1);

                sql2 = "SELECT det_id,quantity,unit, m.name_vn,reqDetail_id,t.mat_id FROM tender_plan_detail AS t LEFT JOIN material AS m ON m.mat_id = t.mat_id  WHERE ten_id = " + tenId;

                rs2 = DBUtil.executeQuery(sql2);
                while (rs2.next()) {
                    sql3 = "Insert Into tech_eval_detail(ter_id, det_id_tp,spec,mat_id,qty,unit,eval_tbe,delivery_time)" + " Values (" + terId + "," + rs2.getInt("det_id") + ",'" + rs2.getString("name_vn") + "'," + rs2.getInt("mat_id") + "," + rs2.getString("quantity") + ",'" + rs2.getString("unit") + "'," + 0 + ",'" + getDeliveryTime(tenId) + "')";
//                    stmt3.executeUpdate(sql3);
                    DBUtil.executeInsert(sql3);

                    sql4 = "UPDATE request_detail SET mat_id = " + rs2.getInt("mat_id") + ", original_mat_id = " + rs2.getInt("mat_id") + " WHERE request_detail.det_id = " + rs2.getInt("reqDetail_id");
//                    stmt4.executeUpdate(sql4);
                    DBUtil.executeInsert(sql4);
                }
                DBUtil.closeConnection(rs2);
            }
            DBUtil.closeConnection(rs);
            System.out.println("sql ====" + sql);

        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if (rs2 != null) {
                DBUtil.closeConnection(rs2);
            }
            if (rs != null) {
                DBUtil.closeConnection(rs);

            }
            if (rs5 != null) {
                DBUtil.closeConnection(rs5);
            }
        }
    }

    public void updateTechEvalVendorForTechEval(String result, String certificateAttach, int terId, int tenId) throws Exception {

        ResultSet rs1 = null;
        ResultSet rs = null;
        try {
            boolean kq = false;
            int evalKind = 2;

            String sql = "Update tech_eval_vendor Set " + " result=" + result + ", certificate_attach='" + certificateAttach + "'" + " Where ter_id=" + terId;

            //System.out.println("sql=" + sql);
            DBUtil.executeUpdate(sql);

            //tron goi
            sql = "SELECT d.* FROM tech_eval AS t LEFT JOIN tech_eval_vendor AS d ON d.te_id = t.te_id LEFT JOIN tender_plan AS t2 ON t2.ten_id = t.ten_id WHERE t2.eval_kind = 0 AND t.ten_id = " + tenId;


            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                evalKind = 0;
                if (rs.getInt("result") < 2) {
                    kq = true;
                    break;
                }
            }
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            if (evalKind == 0) {
                if (kq) {
                    sql = "Update request_detail Set " + " step = '" + MCUtil.getBundleString("message.tenderplan") + "'" + ", step_id = '" + 2 + "'" + " Where ten_id = " + tenId;
                    //System.out.println("sql=" + sql);
                    DBUtil.executeUpdate(sql);
                } else {
                    sql = "Update request_detail Set " + " step = '" + MCUtil.getBundleString("message.request") + "'" + ", step_id = '" + 1 + "'" + " Where ten_id = " + tenId;
                    //System.out.println("sql=" + sql);
                    DBUtil.executeUpdate(sql);
                }
            }

            //tung phan
            kq = false;
            evalKind = 2;
            int reqDetailId = 0;
            sql = "SELECT DISTINCT d.det_id_tp FROM tech_eval AS t LEFT JOIN tech_eval_vendor AS v ON v.te_id = t.te_id LEFT JOIN tech_eval_detail AS d ON d.ter_id = v.ter_id LEFT JOIN tender_plan AS t2 ON t2.ten_id = t.ten_id WHERE t2.eval_kind = 1 AND t.ten_id = " + tenId;
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                kq = false;
                sql = "SELECT d.*, td.reqDetail_id FROM tech_eval AS t LEFT JOIN tech_eval_vendor AS v ON v.te_id = t.te_id LEFT JOIN tech_eval_detail AS d ON d.ter_id = v.ter_id LEFT JOIN tender_plan AS t2 ON t2.ten_id = t.ten_id LEFT JOIN tender_plan_detail AS td ON td.det_id = d.det_id_tp WHERE t2.eval_kind = 1 AND t.ten_id = " + tenId + " AND d.det_id_tp = " + rs.getInt("det_id_tp");

                rs1 = DBUtil.executeQuery(sql);
                while (rs1.next()) {
                    evalKind = 1;
                    reqDetailId = rs1.getInt("reqDetail_id");
                    if (rs1.getInt("eval_tbe") < 2) {
                        kq = true;

                        break;
                    }
                }
                if (rs1 != null) {
                    DBUtil.closeConnection(rs1);
                }
                if (evalKind == 1) {
                    if (kq) {
                        sql = "Update request_detail Set " + " step = '" + MCUtil.getBundleString("message.tenderplan") + "'" + ", step_id = '" + 2 + "'" + " Where det_id = " + reqDetailId;
                        //System.out.println("sql=" + sql);
                        DBUtil.executeUpdate(sql);
                    } else {
                        sql = "Update request_detail Set " + " step = '" + MCUtil.getBundleString("message.request") + "'" + ", step_id = '" + 1 + "'" + " Where det_id = " + reqDetailId;
                        //System.out.println("sql=" + sql);
                        DBUtil.executeUpdate(sql);
                    }
                }
            }
            DBUtil.closeConnection(rs);

        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            if (rs1 != null) {
                DBUtil.closeConnection(rs1);
            }
        }
    }

    public void updateTechEvalVendor(TechEvalVendorBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {



            String sql = "Update tech_eval_vendor Set " + " te_id=" + bean.getTeId() + ", ven_id=" + bean.getVenId() + " Where tev_id=" + bean.getTevId();

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

    public int deleteTechEvalVendor(int tevId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From tech_eval_vendor " + " Where tev_id=" + tevId;
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

    public ArrayList getTechEvalVendor(int teId, int eval_kind) throws Exception { //danh sach NCC cua Danh gia ky thuat
        ResultSet rs = null;

        String sql = " select t.ter_id, t1.ten_id, v.ven_id, v.name, t.certificate_attach, t.result " 
                + " from  tech_eval_vendor AS t" + " left join tech_eval AS t1 ON t1.te_id = t.te_id" 
                + " left join tender_evaluate_vendor AS t2 ON t2.tev_id = t.tev_id" 
                + " left join vendor AS v ON v.ven_id = t2.ven_id"
                + " WHERE t2.bidded = 1 and t.te_id =" + teId
                + " order by t2.tev_id";


        ArrayList arrBean = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                TechEvalDetailBean vendor = new TechEvalDetailBean();
                vendor.setTerId(rs.getInt("ter_id"));
                vendor.setVenId(rs.getInt("ven_id"));
                vendor.setTenId(rs.getInt("ten_id"));
                vendor.setVendorName(StringUtil.decodeString(rs.getString("name")));
                vendor.setCertificateAttach(rs.getString("certificate_attach"));
                if (eval_kind == TenderPlanFormBean.EVAL_KIND_ALL) { //tron goi
                    if (rs.getInt("result") == TechEvalDetailBean.RESULT_NOTEVAL) {
                        vendor.setResult(MCUtil.getBundleString("message.techeval.result.noteval"));
                    } else if (rs.getInt("result") == TechEvalDetailBean.RESULT_REACH) {
                        vendor.setResult(MCUtil.getBundleString("message.vendor.evaluate.reach"));
                    } else if (rs.getInt("result") == TechEvalDetailBean.RESULT_NOTREACH) {
                        vendor.setResult(MCUtil.getBundleString("message.vendor.evaluate.notreach"));
                    }
                }
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

    public ArrayList getTechEvalMaterial(int teId, int terId, int eval_kind) throws Exception { //Danh sach VTTB cua Danh gia ky thuat 
        ResultSet rs = null;

//        String sql = "select rdet.det_id, rdet.req_id, m.mat_id, m.name_vn, t.unit, sum(t.qty) as qty, t3.provide_date,t.spec"
//                + ", t4.delivery_time as tplan, t.delivery_time as tdetail"
//                + ", t.other_condition, t.eval_tbe, t1.result, o.name as project, t.det_id_tp"
//                + " from  tech_eval_detail AS t "
//                + " left join tech_eval_vendor AS t1 ON t1.ter_id = t.ter_id"
//                + " left join tech_eval AS t2 ON t2.te_id = t1.te_id "
//                + " left join tender_plan_detail AS t3 ON t3.det_id = t.det_id_tp  "
//                + " left join tender_plan AS t4 ON t4.ten_id = t3.ten_id  "
//                + " left join material AS m ON t3.mat_id = m.mat_id"
//                + //phuongtu them
//                " left join request_detail as rdet on rdet.det_id=t3.reqDetail_id"
//                + " left join request as r on r.req_id=rdet.req_id"
//                + " left join organization as o on r.org_id=o.org_id "
//                + " WHERE t1.te_id =" + teId + " AND t1.ter_id= " + terId
//                + " GROUP BY m.name_vn order by rdet.req_id DESC, rdet.det_id ASC";

        String sql = " SELECT mat_id,name_vn, unit, SUM(qty) AS qty, provide_date,spec,tplan, tdetail, "
                + " other_condition, eval_tbe, result, project,det_id_tp "
                + " FROM (SELECT t3.det_id AS tenDetId,m.mat_id, m.name_vn, t.unit, t.qty AS qty, t3.provide_date,t.spec, t4.delivery_time AS tplan, t.delivery_time AS tdetail,  "
                + " t.other_condition, t.eval_tbe, t1.result, o.NAME AS project, t.det_id_tp "
                + " FROM  tech_eval_detail AS t  "
                + " LEFT JOIN tech_eval_vendor AS t1 ON t1.ter_id = t.ter_id "
                + " LEFT JOIN tech_eval AS t2 ON t2.te_id = t1.te_id   "
                + " LEFT JOIN tender_plan_detail AS t3 ON t3.det_id = t.det_id_tp    "
                + " LEFT JOIN tender_plan AS t4 ON t4.ten_id = t3.ten_id    "
                + " LEFT JOIN material AS m ON t3.mat_id = m.mat_id  "
                + " LEFT JOIN request_detail AS rdet ON rdet.det_id=t3.reqDetail_id  "
                + " LEFT JOIN request AS r ON r.req_id=rdet.req_id  "
                + " LEFT JOIN organization AS o ON r.org_id=o.org_id  "
                + " WHERE t1.te_id =" + teId + " AND t1.ter_id= " + terId
           //     + " ORDER BY rdet.req_id DESC, rdet.det_id ASC) AS tbl GROUP BY name_vn  ";
//                 + " ORDER BY t3.det_id ASC) AS tbl GROUP BY name_vn ORDER BY tbl.tenDetId ";
                 + " ORDER BY t3.det_id ASC) AS tbl GROUP BY name_vn ORDER BY tbl.tenDetId asc";

        ArrayList arrBean = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            int i = 1;
            while (rs.next()) {
                TechEvalDetailBean material = new TechEvalDetailBean();
                material.setMatId(i++);
                material.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                material.setQty(rs.getString("qty"));
                material.setUnit(rs.getString("unit"));
                material.setProvideDate(DateUtil.formatDate(rs.getDate("provide_date"), "dd/MM/yyyy"));
                material.setSpec(StringUtil.decodeString(rs.getString("spec")));
                material.setDeliveryTime(rs.getString("tplan"));
                material.setDeliveryTimeVendor(rs.getString("tdetail"));
                material.setDetIdTp(rs.getInt("det_id_tp"));
                material.setKind(eval_kind);
                //if (eval_kind == TenderPlanFormBean.EVAL_KIND_PART) {//tung phan
                material.setOtherCondition(rs.getString("other_condition"));
                material.setEvalTbe(rs.getInt("eval_tbe"));
                if (material.getEvalTbe() == ComEvalMaterialDetailBean.RESULT1) {
                    material.setEvalTbeString(MCUtil.getBundleString("message.result1"));
                } else if (material.getEvalTbe() == ComEvalMaterialDetailBean.RESULT2) {
                    material.setEvalTbeString(MCUtil.getBundleString("message.result2"));
                } else if (material.getEvalTbe() == ComEvalMaterialDetailBean.RESULT3) {
                    material.setEvalTbeString(MCUtil.getBundleString("message.result3"));
                }
                /*    } else if (eval_kind == TenderPlanFormBean.EVAL_KIND_ALL) {//trong goi
                int result = rs.getInt("result");
                if (result == ComEvalMaterialDetailBean.RESULT3) {
                material.setEvalTbeString(MCUtil.getBundleString("message.techeval.result.noteval"));
                } else if (result == ComEvalMaterialDetailBean.RESULT1) {
                material.setEvalTbeString(MCUtil.getBundleString("message.vendor.evaluate.reach"));
                } else if (result == ComEvalMaterialDetailBean.RESULT2) {
                material.setEvalTbeString(MCUtil.getBundleString("message.vendor.evaluate.notreach"));
                }
                }
                 * 
                 */
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

    public int isMaterialReached(int detIdTp, int eval_kind) throws Exception {
        ResultSet rs = null;

        String sql = "";
        if (eval_kind == TenderPlanFormBean.EVAL_KIND_PART) {//tung phan
            sql = "select t.det_id"
                    + " from tech_eval_detail AS t"
                    + " WHERE t.det_id_tp =" + detIdTp + " and t.eval_tbe=" + ComEvalMaterialDetailBean.RESULT1;
        } else if (eval_kind == TenderPlanFormBean.EVAL_KIND_ALL) {//trong goi
            sql = "select t.det_id"
                    + " from tech_eval_detail AS t left join tech_eval_vendor as tev on t.ter_id=tev.ter_id"
                    + " WHERE t.det_id_tp =" + detIdTp + " and tev.result=" + ComEvalMaterialDetailBean.RESULT1;
        }

        int result = 0;
        try {


            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                result = 1;
                break;
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
        return result;
    }
}
