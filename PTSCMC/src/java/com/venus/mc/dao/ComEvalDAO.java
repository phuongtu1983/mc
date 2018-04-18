/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ComEvalBean;
import com.venus.mc.bean.ComEvalDetailBean;
import com.venus.mc.bean.ComEvalGroupBean;
import com.venus.mc.bean.ComEvalVendorBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class ComEvalDAO extends BasicDAO {

    public ComEvalDAO() {
    }

    public ArrayList getComEvals()
            throws Exception {
        ResultSet rs = null;
        String sql = "Select * From com_eval Order by ce_id ASC";

        ArrayList com_evalList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalBean com_eval = null;
            while (rs.next()) {
                com_eval = new ComEvalBean();
                com_eval.setCeId(rs.getInt("ce_id"));
                com_eval.setTenId(rs.getInt("ten_id"));
                com_eval.setCcId(rs.getInt("cc_id"));
                com_eval.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                com_evalList.add(com_eval);
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
        return com_evalList;
    }

    public ComEvalBean getComEval(int tenId) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From com_eval Where ten_id=" + tenId;
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ComEvalBean com_eval = new ComEvalBean();
                com_eval.setCeId(rs.getInt("ce_id"));
                com_eval.setTenId(rs.getInt("ten_id"));
                com_eval.setCcId(rs.getInt("cc_id"));
                com_eval.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));

                return com_eval;
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

    public ComEvalBean getComEvalForVendor(int tenId) throws Exception {
        ResultSet rs = null;

        String sql = "Select e.*,v.cev_id From com_eval as e left join com_eval_vendor as v on e.ce_id=v.ce_id Where ten_id=" + tenId;
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ComEvalBean com_eval = new ComEvalBean();
                com_eval.setCeId(rs.getInt("ce_id"));
                com_eval.setTenId(rs.getInt("ten_id"));
                com_eval.setCcId(rs.getInt("cc_id"));
                com_eval.setCevId(rs.getInt("cev_id"));
                com_eval.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));

                return com_eval;
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

    public ComEvalBean getComEvalForMaterial(int tenId) throws Exception {
        ResultSet rs = null;

        String sql = "Select e.*,m.cem_id From com_eval as e left join com_eval_material_vendor as m on e.ce_id=m.ce_id Where ten_id=" + tenId;
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ComEvalBean com_eval = new ComEvalBean();
                com_eval.setCeId(rs.getInt("ce_id"));
                com_eval.setTenId(rs.getInt("ten_id"));
                com_eval.setCcId(rs.getInt("cc_id"));
                com_eval.setCemId(rs.getInt("cem_id"));
                com_eval.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));

                return com_eval;
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

    public ComEvalBean getComEvalByName(String name) throws Exception {
        ResultSet rs = null;

        String sql = "select ce_id from com_eval where name='" + name + "'";
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ComEvalBean com_eval = new ComEvalBean();
                com_eval.setCeId(rs.getInt("ce_id"));

                return com_eval;
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

    public ArrayList getComEvalGroup(int tenId) throws Exception {
        ResultSet rs = null;

        String sql = "Select g.* From com_eval_group as g left join com_eval as v on g.ce_id=v.ce_id where v.ten_id =" + tenId;
        ArrayList list = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalGroupBean bean = null;
            while (rs.next()) {
                bean = new ComEvalGroupBean();
                bean.setCegId(rs.getInt("ceg_id"));
                bean.setCeId(rs.getInt("ce_id"));
                bean.setEmployee(rs.getString("employee"));
                bean.setPosition(rs.getString("position"));
                bean.setNote(StringUtil.getString(rs.getString("note")));
                list.add(bean);
            }
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            if (bean == null) {
                String sql1 = "Select g.* From tech_eval_group as g left join tech_eval as v on g.te_id=v.te_id where v.ten_id =" + tenId;
                rs = DBUtil.executeQuery(sql1);
                while (rs.next()) {
                    bean = new ComEvalGroupBean();
                    bean.setEmployee(rs.getString("employee"));
                    bean.setPosition(rs.getString("position"));
                    bean.setNote(rs.getString("note"));
                    list.add(bean);
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
        return list;
    }

    public int deleteComEvalGroup(String cegId) throws Exception {
        int result = 0;
        try {
            String sql = "Delete From com_eval_group Where ceg_id=" + cegId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public void updateComEvalGroup(ComEvalGroupBean bean) throws Exception {
        if (bean == null) {
            return;
        }


        try {
            String sql = "";


            sql = "Update com_eval_group Set "
                    + " ce_id='" + bean.getCeId() + "'"
                    + ", employee='" + bean.getEmployee() + "'"
                    + ", position='" + bean.getPosition() + "'"
                    + ", note='" + bean.getNote() + "'"
                    + " where ceg_id=" + bean.getCegId();
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

    public void insertComEvalGroup(ComEvalGroupBean bean) throws Exception {
        if (bean == null) {
            return;
        }


        try {
            String sql = "";


            sql = "Insert Into com_eval_group(ce_id, employee, position, note)"
                    + " Values (" + bean.getCeId() + ",'" + bean.getEmployee() + "','" + bean.getPosition() + "','" + bean.getNote() + "')";
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

    public int insertComEval(ComEvalBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
//        
//        
        int result = 0;
        try {

            String sql = "";
//            
            String CcId = bean.getCcId() + "";
//            
            if (Integer.parseInt(CcId) == 0) {
                CcId = null;
            }

            sql = "Insert Into com_eval(ten_id, cc_id,created_date,kind)"
                    + " Values (" + bean.getTenId() + "," + CcId + ",now()," + bean.getKind() + ")";

            //System.out.println("sql ====" + sql);
//            DBUtil.executeUpdate(sql);
//            ResultSet rs = stmt.getGeneratedKeys();
//            while (rs.next()) {
//                return rs.getInt(stmt.RETURN_GENERATED_KEYS);
//            }
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            try {
//                
//                
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
        return result;
    }

    public void updateComEval(ComEvalBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {


            String ccId = bean.getCcId() + "";
            if (Integer.parseInt(ccId) == 0) {
                ccId = null;
            }

            String sql = "Update com_eval Set "
                    + " ten_id=" + bean.getTenId()
                    + ", cc_id=" + ccId
                    + ", created_date=STR_TO_DATE('" + bean.getCreatedDate() + "','%d/%m/%Y')"
                    + " Where ce_id=" + bean.getCeId();

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

    public int deleteComEval(int ceId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From com_eval " + " Where ce_id=" + ceId;
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

    public ArrayList searchSimpleComEval(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "create_date";
                break;
        }
        ResultSet rs = null;

        String sql = "Select * From com_eval Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by ce_id DESC";

        ArrayList com_evalList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalBean com_eval = null;
            while (rs.next()) {
                com_eval = new ComEvalBean();
                com_eval.setCeId(rs.getInt("ce_id"));
                com_eval.setTenId(rs.getInt("ten_id"));
                com_eval.setCcId(rs.getInt("cc_id"));
                com_eval.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                com_evalList.add(com_eval);
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
        return com_evalList;
    }

    public ArrayList getComEvalVendor(int ceId) throws Exception {
        ResultSet rs = null;

        String sql = "SELECT t.ter_id, v.ven_id, v.name, c.custom_tax, c.cost_transport, c.other_tax, c.other_cost, c.sum, c.rand, v.kind "
                + ", c.currency, c.payment_method, v.ven_id, c.rates, i.inc_name, i.inc_id, c.price_certificate, c.price_to_port"
                + " from com_eval_vendor as c"
                + " left join tech_eval_vendor as t on t.ter_id = c.ter_id"
                + " left join tender_evaluate_vendor as t1 on t1.tev_id = t.tev_id"
                + " left join vendor as v on v.ven_id = t1.ven_id"
                + " left join incoterm as i on i.inc_id = t1.incoterm"
                + " Where t.result=1 and c.ce_id =" + ceId
                + " order by t1.tev_id";

        ArrayList beanList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ComEvalVendorBean bean = new ComEvalVendorBean();
                bean.setTerId(rs.getInt("ter_id"));
                bean.setCeId(ceId);
                bean.setVendorName(StringUtil.decodeString(rs.getString("name")));
                bean.setCustomTax(rs.getDouble("custom_tax"));
                bean.setCostTransport(rs.getDouble("cost_transport"));
                bean.setOtherTax(rs.getDouble("other_tax"));
                bean.setOtherCost(rs.getDouble("other_cost"));
				bean.setPriceCertificate(rs.getDouble("price_certificate"));
                bean.setPriceToPort(rs.getDouble("price_to_port"));
                bean.setSum(NumberUtil.formatMoneyDefault(rs.getDouble("sum")));
                bean.setRand(rs.getInt("rand"));
                bean.setVenKind(rs.getInt("kind"));
                bean.setCurrency(rs.getString("currency"));
                if (GenericValidator.isBlankOrNull(bean.getCurrency())) {
                    bean.setCurrency("");
                }
                bean.setPaymentMethod(rs.getString("payment_method"));
                bean.setVenId(rs.getInt("ven_id"));
                bean.setRates(rs.getDouble("rates"));
                bean.setIncoterm(rs.getInt("inc_id"));
                bean.setIncortemText(rs.getString("inc_name"));
                beanList.add(bean);
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
        return beanList;
    }

    public ArrayList getComEvalMaterial(int ceId, int terId, String currency) throws Exception {
        ResultSet rs = null;

        String sql = "SELECT SUM(d.quantity) AS qt,d.unit, d.price_after, d.total,m.name_vn,m.mat_id"
                + ", r.request_number, o.name as project, d.price"
                + " FROM com_eval_detail AS d LEFT JOIN tender_plan_detail AS t ON t.det_id = d.det_id_tp"
                + " LEFT JOIN com_eval_vendor c on c.cev_id = d.cev_id"
                + " LEFT JOIN material AS m ON t.mat_id = m.mat_id"
                + //phuongtu them
                " left join tender_plan_detail as tdet on tdet.det_id=d.det_id_tp"
                + " left join request_detail as rdet on rdet.det_id=tdet.reqDetail_id"
                + " left join request as r on r.req_id=rdet.req_id"
                + " left join organization as o on o.org_id=r.org_id"
                + " WHERE c.ce_id = " + ceId + " and c.ter_id=" + terId
                + " GROUP BY m.name_vn order by tdet.det_id";

        ArrayList beanList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            int i = 1;
            while (rs.next()) {
                ComEvalDetailBean bean = new ComEvalDetailBean();
                bean.setDetId(i++);
                bean.setMatId(rs.getInt("mat_id"));
                bean.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
                bean.setUnit(rs.getString("unit"));
                bean.setQuantity(rs.getDouble("qt"));
                bean.setQuantityText(NumberUtil.formatMoneyDefault(bean.getQuantity(), currency));
                bean.setPrice(NumberUtil.formatMoneyDefault(rs.getDouble("price_after"), currency));
                bean.setTotal(NumberUtil.formatMoneyDefault(rs.getDouble("total"), currency));
                bean.setRequestNumber(rs.getString("request_number"));
                bean.setProjectName(StringUtil.decodeString(rs.getString("project")));
                bean.setPriceCur(NumberUtil.formatMoneyDefault(rs.getDouble("price"), currency));
                beanList.add(bean);
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
        return beanList;
    }
}
