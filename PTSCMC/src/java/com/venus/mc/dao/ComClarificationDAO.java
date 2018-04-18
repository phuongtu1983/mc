/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ComClarificationBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class ComClarificationDAO extends BasicDAO {

    public ComClarificationDAO() {
    }

    public ArrayList getComClarifications()
            throws Exception {
        ResultSet rs = null;
        String sql = "Select * From com_clarification Order by cc_id ASC";

        ArrayList com_clarificationList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComClarificationBean com_clarification = null;
            while (rs.next()) {
                com_clarification = new ComClarificationBean();
                com_clarification.setCcId(rs.getInt("cc_id"));
                com_clarification.setTenId(rs.getInt("ten_id"));
                com_clarification.setCcNumber(rs.getString("cc_number"));
                com_clarification.setSubfix(rs.getString("subfix"));
                com_clarification.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                com_clarificationList.add(com_clarification);
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
        return com_clarificationList;
    }

    public ComClarificationBean getComClarification(int ccId) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From com_clarification Where cc_id=" + ccId;
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ComClarificationBean com_clarification = new ComClarificationBean();
                com_clarification.setCcId(rs.getInt("cc_id"));
                com_clarification.setTenId(rs.getInt("ten_id"));
                com_clarification.setCcNumber(rs.getString("cc_number"));
                com_clarification.setSubfix(rs.getString("subfix"));
                com_clarification.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));

                return com_clarification;
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

    public ComClarificationBean getComClarificationByName(String name) throws Exception {
        ResultSet rs = null;

        String sql = "select cc_id from com_clarification where name='" + name + "'";
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ComClarificationBean com_clarification = new ComClarificationBean();
                com_clarification.setCcId(rs.getInt("cc_id"));

                return com_clarification;
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

    public void insertComClarification(ComClarificationBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Insert Into com_clarification(ten_id, cc_number, subfix, created_date)"
                    + " Values (" + bean.getTenId() + ",'" + bean.getCcNumber()
                    + "','" + bean.getSubfix() + "','" + bean.getCreatedDate() + "')";
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int insertComClarificationId(ComClarificationBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "Insert Into com_clarification(ten_id, cc_number, subfix, created_date)"
                    + " Values (" + bean.getTenId() + ",'" + bean.getCcNumber()
                    + "','" + bean.getSubfix() + "',now())";
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public void updateComClarification(ComClarificationBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Update com_clarification Set "
                    + " ten_id=" + bean.getTenId()
                    + ", cc_number='" + bean.getCcNumber() + "'"
                    + ", subfix='" + bean.getSubfix() + "'"
                    + ", created_date=STR_TO_DATE('" + bean.getCreatedDate() + "','%d/%m/%Y')"
                    + " Where cc_id=" + bean.getCcId();
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteComClarification(int ccId) throws Exception {
        int result = 0;
        try {
            String sql = "Delete From com_clarification " + " Where cc_id=" + ccId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleComClarification(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "cc_number";
                break;
        }
        ResultSet rs = null;

        String sql = "Select * From com_clarification Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by cc_id DESC";

        ArrayList com_clarificationList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComClarificationBean com_clarification = null;
            while (rs.next()) {
                com_clarification = new ComClarificationBean();
                com_clarification.setCcId(rs.getInt("cc_id"));
                com_clarification.setTenId(rs.getInt("ten_id"));
                com_clarification.setCcNumber(rs.getString("cc_number"));
                com_clarification.setSubfix(rs.getString("subfix"));
                com_clarification.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                com_clarificationList.add(com_clarification);
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
        return com_clarificationList;

    }

    public ArrayList searchAdvComClarification(ComClarificationBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From com_clarification Where 1 ";

        if (bean.getCcId() != 0) {
            sql = sql + " AND cc_id = " + bean.getCcId();
        }
        if (bean.getTenId() != 0) {
            sql = sql + " AND ten_id = " + bean.getTenId();
        }
        if (!StringUtil.isBlankOrNull(bean.getCcNumber())) {
            sql = sql + " AND cc_number LIKE '%" + bean.getCcNumber() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getSubfix())) {
            sql = sql + " AND subfix LIKE '%" + bean.getSubfix() + "%'";
        }
        if (bean.getCreatedDate() != null) {
            sql = sql + " AND created_date =" + (DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
        }
        sql = sql + " Order by cc_id DESC";

        ArrayList com_clarificationList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComClarificationBean com_clarification = null;
            while (rs.next()) {
                com_clarification = new ComClarificationBean();
                com_clarification.setCcId(rs.getInt("cc_id"));
                com_clarification.setTenId(rs.getInt("ten_id"));
                com_clarification.setCcNumber(rs.getString("cc_number"));
                com_clarification.setSubfix(rs.getString("subfix"));
                com_clarification.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                com_clarificationList.add(com_clarification);
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
        return com_clarificationList;
    }
}