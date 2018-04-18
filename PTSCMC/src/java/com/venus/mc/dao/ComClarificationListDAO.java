/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ComClarificationListBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class ComClarificationListDAO extends BasicDAO {

    public ComClarificationListDAO() {
    }

    public ArrayList getComClarificationLists()
            throws Exception {
        ResultSet rs = null;
        String sql = "Select * From com_clarification_list Order by ccl_id ASC";

        ArrayList com_clarification_listList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComClarificationListBean com_clarification_list = null;
            while (rs.next()) {
                com_clarification_list = new ComClarificationListBean();
                com_clarification_list.setCclId(rs.getInt("ccl_id"));
                com_clarification_list.setCcId(rs.getInt("cc_id"));
                com_clarification_list.setReference(rs.getString("reference"));
                com_clarification_list.setClarification(rs.getString("clarification"));
                com_clarification_list.setSupplierReply(rs.getString("supplier_reply"));
                com_clarification_list.setStatus(rs.getInt("status"));
                com_clarification_listList.add(com_clarification_list);
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
        return com_clarification_listList;
    }

    public ComClarificationListBean getComClarificationList(int cclId) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From com_clarification_list Where ccl_id=" + cclId;
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ComClarificationListBean com_clarification_list = new ComClarificationListBean();
                com_clarification_list.setCclId(rs.getInt("ccl_id"));
                com_clarification_list.setCcId(rs.getInt("cc_id"));
                com_clarification_list.setReference(rs.getString("reference"));
                com_clarification_list.setClarification(rs.getString("clarification"));
                com_clarification_list.setSupplierReply(rs.getString("supplier_reply"));
                com_clarification_list.setStatus(rs.getInt("status"));

                return com_clarification_list;
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

    public ComClarificationListBean getComClarificationListByName(String name) throws Exception {
        ResultSet rs = null;

        String sql = "select ccl_id from com_clarification_list where name='" + name + "'";
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ComClarificationListBean com_clarification_list = new ComClarificationListBean();
                com_clarification_list.setCclId(rs.getInt("ccl_id"));

                return com_clarification_list;
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

    public void insertComClarificationList(ComClarificationListBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Insert Into com_clarification_list(cc_id, reference, clarification, supplier_reply, status)"
                    + " Values (" + bean.getCcId() + ",'" + bean.getReference()
                    + "','" + bean.getClarification() + "','" + bean.getSupplierReply()
                    + "'," + bean.getStatus() + ")";
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateComClarificationList(ComClarificationListBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Update com_clarification_list Set "
                    + " cc_id=" + bean.getCcId()
                    + ", reference='" + bean.getReference() + "'"
                    + ", clarifiction='" + bean.getClarification() + "'"
                    + ", supplier_reply='" + bean.getSupplierReply() + "'"
                    + ", status=" + bean.getStatus()
                    + " Where ccl_id=" + bean.getCclId();
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteComClarificationList(int cclId) throws Exception {
        int result = 0;
        try {
            String sql = "Delete From com_clarification_list " + " Where ccl_id=" + cclId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleComClarificationList(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "reference";
                break;
        }
        ResultSet rs = null;

        String sql = "Select * From com_clarification_list Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by ccl_id DESC";

        ArrayList com_clarification_listList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComClarificationListBean com_clarification_list = null;
            while (rs.next()) {
                com_clarification_list = new ComClarificationListBean();
                com_clarification_list.setCclId(rs.getInt("ccl_id"));
                com_clarification_list.setCcId(rs.getInt("cc_id"));
                com_clarification_list.setReference(rs.getString("reference"));
                com_clarification_list.setClarification(rs.getString("clarification"));
                com_clarification_list.setSupplierReply(rs.getString("supplier_reply"));
                com_clarification_list.setStatus(rs.getInt("status"));
                com_clarification_listList.add(com_clarification_list);
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
        return com_clarification_listList;

    }

    public ArrayList searchAdvComClarificationList(ComClarificationListBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From com_clarification_list Where 1 ";

        if (bean.getCclId() != 0) {
            sql = sql + " AND ccl_id = " + bean.getCclId();
        }
        if (bean.getCcId() != 0) {
            sql = sql + " AND cc_id = " + bean.getCcId();
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

        sql = sql + " Order by ccl_id DESC";

        ArrayList com_clarification_listList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComClarificationListBean com_clarification_list = null;
            while (rs.next()) {
                com_clarification_list = new ComClarificationListBean();
                com_clarification_list.setCclId(rs.getInt("ccl_id"));
                com_clarification_list.setCcId(rs.getInt("cc_id"));
                com_clarification_list.setReference(rs.getString("reference"));
                com_clarification_list.setClarification(rs.getString("clarification"));
                com_clarification_list.setSupplierReply(rs.getString("supplier_reply"));
                com_clarification_list.setStatus(rs.getInt("status"));
                com_clarification_listList.add(com_clarification_list);
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
        return com_clarification_listList;
    }
}
