/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ComEvalMaterialDetailBean;
import com.venus.mc.bean.TechEvalBean;
import com.venus.mc.bean.TechEvalGroupBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class TechEvalDAO extends BasicDAO {

    public TechEvalDAO() {
    }

    public ArrayList getTechEvals()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tech_eval Order by te_id ASC";

        ArrayList tech_evalList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            TechEvalBean tech_eval = null;
            while (rs.next()) {
                tech_eval = new TechEvalBean();
                tech_eval.setTeId(rs.getInt("te_id"));
                tech_eval.setTenId(rs.getInt("ten_id"));
                tech_eval.setCreatedDate(rs.getString("created_date"));
                tech_evalList.add(tech_eval);
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
        return tech_evalList;
    }

    public TechEvalBean getTechEval(int tenId) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tech_eval Where ten_id=" + tenId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                TechEvalBean tech_eval = new TechEvalBean();
                tech_eval.setTeId(rs.getInt("te_id"));
                tech_eval.setTenId(rs.getInt("ten_id"));
                tech_eval.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));

                return tech_eval;
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

    public int getForm(int tenId) throws Exception {
        ResultSet rs = null;

        String sql = "Select form From tender_plan Where ten_id=" + tenId;

        int result = 0;
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                result = rs.getInt("form");
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

    public ArrayList getTechEvalGroup(int tenId) throws Exception {
        ResultSet rs = null;

        String sql = "Select g.* From tech_eval_group as g left join tech_eval as v on g.te_id=v.te_id where v.ten_id =" + tenId;

        ArrayList list = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            TechEvalGroupBean bean = null;
            while (rs.next()) {
                bean = new TechEvalGroupBean();
                bean.setTegId(rs.getInt("teg_id"));
                bean.setTeId(rs.getInt("te_id"));
                bean.setEmployee(rs.getString("employee"));
                bean.setPosition(rs.getString("position"));
                bean.setNote(StringUtil.getString(rs.getString("note")));
                list.add(bean);
            }
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            if (bean == null) {
                String sql1 = "SELECT * FROM tender_evaluate_group WHERE ten_id = " + tenId;
                rs = DBUtil.executeQuery(sql1);
                while (rs.next()) {
                    bean = new TechEvalGroupBean();
                    //       bean.setTegId(rs.getInt("teg_id"));
                    //       bean.setTeId(rs.getInt("te_id"));
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

    public int deleteTechEvalGroup(String tegId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From tech_eval_group Where teg_id=" + tegId;
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

    public int insertTechEval(TechEvalBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
//        
//        
        int result = 0;
        try {

            String sql = "";
//            
//            

            sql = "Insert Into tech_eval(ten_id, created_date)"
                    + " Values (" + bean.getTenId()
                    + ",now())";

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

    public void insertTechEvalGroup(TechEvalGroupBean bean) throws Exception {
        if (bean == null) {
            return;
        }


        try {
            String sql = "";


            sql = "Insert Into tech_eval_group(te_id, employee, position, note)"
                    + " Values (" + bean.getTeId() + ",'" + bean.getEmployee() + "','" + bean.getPosition() + "','" + bean.getNote() + "')";
            System.out.println("sql=" + sql);
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

    public void updateTechEvalGroup(TechEvalGroupBean bean) throws Exception {
        if (bean == null) {
            return;
        }


        try {
            String sql = "";


            sql = "Update tech_eval_group Set "
                    + " te_id='" + bean.getTeId() + "'"
                    + ", employee='" + bean.getEmployee() + "'"
                    + ", position='" + bean.getPosition() + "'"
                    + ", note='" + bean.getNote() + "'"
                    + " where teg_id=" + bean.getTegId();
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

    public void updateTechEval(TechEvalBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {


            String sql = "Update tech_eval Set "
                    + " ten_id=" + bean.getTenId()
                    + ", created_date=STR_TO_DATE('" + bean.getCreatedDate() + "','%d/%m/%Y')"
                    + " Where te_id=" + bean.getTeId();

            System.out.println("sql=" + sql);
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

    public int deleteTechEval(int teId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From tech_eval " + " Where te_id=" + teId;
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

    public TechEvalBean getTechEvalById(int teId) throws Exception {
        ResultSet rs = null;
        String sql = "Select * From tech_eval Where te_id=" + teId;
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                TechEvalBean tech_eval = new TechEvalBean();
                tech_eval.setTeId(rs.getInt("te_id"));
                tech_eval.setTenId(rs.getInt("ten_id"));
                tech_eval.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));

                return tech_eval;
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

    public String getTechMaterialNotReach(int tenId) throws Exception {
        ResultSet rs = null;

//        String sql = "SELECT tbl.det_id_tp FROM (SELECT DISTINCT tdet.det_id_tp, SUM(tdet.eval_tbe) eval"
//                + " FROM tender_evaluate_vendor AS tev, tech_eval_vendor AS ter, tech_eval_detail AS tdet"
//                + " WHERE tev.tev_id=ter.tev_id AND ter.ter_id=tdet.ter_id"
//                + " AND tev.ten_id=" + tenId + " GROUP BY tdet.det_id_tp) AS tbl"
//                + " WHERE tbl.eval=0";

        String sql = "SELECT tdet.det_id FROM tender_plan_detail AS tdet WHERE tdet.ten_id=" + tenId
                + " AND tdet.is_roll_back=0 and tdet.det_id NOT IN ("
                + " SELECT ted.det_id_tp FROM tech_eval_detail AS ted"
                + " WHERE ted.det_id_tp=tdet.det_id AND ted.eval_tbe=" + ComEvalMaterialDetailBean.RESULT1 + ")";

        String result = "0";
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                result += "," + rs.getString("det_id");
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

    public TechEvalBean getTechEvalByTenId(int tenId) throws Exception {
        ResultSet rs = null;
        String sql = "Select * From tech_eval Where ten_id=" + tenId;
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                TechEvalBean tech_eval = new TechEvalBean();
                tech_eval.setTeId(rs.getInt("te_id"));
                tech_eval.setTenId(rs.getInt("ten_id"));
                tech_eval.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));

                return tech_eval;
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
}
