/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.TechClarificationListDetailBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class TechClarificationListDetailDAO extends BasicDAO {

    public TechClarificationListDetailDAO() {
    }

    public ArrayList getTechClarificationListDetails()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tech_clarification_list_detail Order by det_id ASC";

        ArrayList tech_clarification_list_detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            TechClarificationListDetailBean tech_clarification_list_detail = null;
            while (rs.next()) {
                tech_clarification_list_detail = new TechClarificationListDetailBean();
                tech_clarification_list_detail.setDetId(rs.getInt("det_id"));
                tech_clarification_list_detail.setTclId(rs.getInt("tcl_id"));
                tech_clarification_list_detail.setSubcategory(rs.getString("subcategory"));
                tech_clarification_list_detail.setReference(rs.getString("reference"));
                tech_clarification_list_detail.setClarification(rs.getString("clarification"));
                tech_clarification_list_detail.setSupplierReply(rs.getString("supplier_reply"));
                tech_clarification_list_detail.setStatus(rs.getInt("status"));
                tech_clarification_list_detailList.add(tech_clarification_list_detail);
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
        return tech_clarification_list_detailList;
    }

    public ArrayList getTechClarificationListDetail(String tclId)
            throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tech_clarification_list_detail Where tcl_id='" + tclId + "' Order by det_id ASC";

        ArrayList tech_clarification_list_detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            TechClarificationListDetailBean tech_clarification_list_detail = null;
            while (rs.next()) {
                tech_clarification_list_detail = new TechClarificationListDetailBean();
                tech_clarification_list_detail.setDetId(rs.getInt("det_id"));
                tech_clarification_list_detail.setTclId(rs.getInt("tcl_id"));
                tech_clarification_list_detail.setSubcategory(rs.getString("subcategory"));
                tech_clarification_list_detail.setReference(rs.getString("reference"));
                tech_clarification_list_detail.setClarification(rs.getString("clarification"));
                tech_clarification_list_detail.setSupplierReply(rs.getString("supplier_reply"));
                tech_clarification_list_detail.setStatus(rs.getInt("status"));
                tech_clarification_list_detailList.add(tech_clarification_list_detail);
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
        return tech_clarification_list_detailList;
    }

    public TechClarificationListDetailBean getTechClarificationListDetail(int detId) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tech_clarification_list_detail Where det_id=" + detId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                TechClarificationListDetailBean tech_clarification_list_detail = new TechClarificationListDetailBean();
                tech_clarification_list_detail.setDetId(rs.getInt("det_id"));
                tech_clarification_list_detail.setTclId(rs.getInt("tcl_id"));
                tech_clarification_list_detail.setSubcategory(rs.getString("subcategory"));
                tech_clarification_list_detail.setReference(rs.getString("reference"));
                tech_clarification_list_detail.setClarification(rs.getString("clarification"));
                tech_clarification_list_detail.setSupplierReply(rs.getString("supplier_reply"));
                tech_clarification_list_detail.setStatus(rs.getInt("status"));

                return tech_clarification_list_detail;
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

    public void insertTechClarificationListDetail(TechClarificationListDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {

            String sql = "";



            sql = "Insert Into tech_clarification_list_detail(tcl_id, subcategory, reference, clarification, supplier_reply, status)"
                    + " Values (" + bean.getTclId() + ",'" + bean.getSubcategory()
                    + "','" + bean.getReference() + "','" + bean.getClarification()
                    + "','" + bean.getSupplierReply() + "'," + bean.getStatus() + ")";

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

    public void updateTechClarificationListDetail(TechClarificationListDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {



            String sql = "Update tech_clarification_list_detail Set "
                    + " tcl_id=" + bean.getTclId()
                    + ", subcategory='" + bean.getSubcategory() + "'"
                    + ", reference='" + bean.getReference() + "'"
                    + ", clarification='" + bean.getClarification() + "'"
                    + ", supplier_reply='" + bean.getSupplierReply() + "'"
                    + ", status=" + bean.getStatus()
                    + " Where det_id=" + bean.getDetId();

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

    public int deleteTechClarificationListDetail(int detId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From tech_clarification_list_detail " + " Where det_id=" + detId;
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

    public ArrayList searchSimpleTechClarificationListDetail(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "tcl_id";
                break;
        }
        ResultSet rs = null;

        String sql = "Select * From tech_clarification_list_detail Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " = " + strFieldvalue;
        }
        sql = sql + " Order by det_id DESC";

        ArrayList tech_clarification_list_detailList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            TechClarificationListDetailBean tech_clarification_list_detail = null;
            while (rs.next()) {
                tech_clarification_list_detail = new TechClarificationListDetailBean();
                tech_clarification_list_detail.setDetId(rs.getInt("det_id"));
                tech_clarification_list_detail.setTclId(rs.getInt("tcl_id"));
                tech_clarification_list_detail.setSubcategory(rs.getString("subcategory"));
                tech_clarification_list_detail.setReference(rs.getString("reference"));
                tech_clarification_list_detail.setClarification(rs.getString("clarification"));
                tech_clarification_list_detail.setSupplierReply(rs.getString("supplier_reply"));
                tech_clarification_list_detail.setStatus(rs.getInt("status"));
                tech_clarification_list_detailList.add(tech_clarification_list_detail);
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
        return tech_clarification_list_detailList;

    }

    public ArrayList searchAdvTechClarificationListDetail(TechClarificationListDetailBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tech_clarification_list_detail Where 1 ";

        if (bean.getDetId() != 0) {
            sql = sql + " AND det_id =" + bean.getDetId();
        }

        if (bean.getTclId() != 0) {
            sql = sql + " AND tcl_id =" + bean.getTclId();
        }

        if (!StringUtil.isBlankOrNull(bean.getSubcategory())) {
            sql = sql + " AND subcategory = " + bean.getSubcategory();
        }

        if (!StringUtil.isBlankOrNull(bean.getReference())) {
            sql = sql + " AND reference LIKE '%" + bean.getReference() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getClarification())) {
            sql = sql + " AND clarification LIKE '%" + bean.getClarification() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getSupplierReply())) {
            sql = sql + " AND supplier_reply LIKE '%" + bean.getSupplierReply() + "%'";
        }
        if (bean.getStatus() != 0) {
            sql = sql + " AND status =" + bean.getStatus();
        }

        sql = sql + " Order by det_id DESC";

        ArrayList tech_clarification_list_detailList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            TechClarificationListDetailBean tech_clarification_list_detail = null;

            while (rs.next()) {
                tech_clarification_list_detail = new TechClarificationListDetailBean();
                tech_clarification_list_detail.setDetId(rs.getInt("det_id"));
                tech_clarification_list_detail.setTclId(rs.getInt("tcl_id"));
                tech_clarification_list_detail.setSubcategory(rs.getString("subcategory"));
                tech_clarification_list_detail.setReference(rs.getString("reference"));
                tech_clarification_list_detail.setClarification(rs.getString("clarification"));
                tech_clarification_list_detail.setSupplierReply(rs.getString("supplier_reply"));
                tech_clarification_list_detail.setStatus(rs.getInt("status"));
                tech_clarification_list_detailList.add(tech_clarification_list_detail);
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
        return tech_clarification_list_detailList;
    }
}
