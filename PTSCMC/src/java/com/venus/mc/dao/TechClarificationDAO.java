/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.TechClarificationBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class TechClarificationDAO extends BasicDAO {
    
    public TechClarificationDAO() {
    }
    
    public ArrayList getTechClarifications()
            throws Exception {
        ResultSet rs = null;
        
        String sql = "Select * From tech_clarification Order by tc_id ASC";
        
        ArrayList tech_clarificationList = new ArrayList();
        try {
            
            
            
            rs = DBUtil.executeQuery(sql);
            TechClarificationBean tech_clarification = null;
            while (rs.next()) {
                tech_clarification = new TechClarificationBean();
                tech_clarification.setTcId(rs.getInt("tc_id"));
                tech_clarification.setTerId(rs.getInt("ter_id"));
                tech_clarification.setTcNumber(rs.getString("tc_number"));
                tech_clarification.setSubfix(rs.getString("subfix"));
                tech_clarification.setCreatedDate(rs.getString("created_date"));
                tech_clarificationList.add(tech_clarification);
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
        return tech_clarificationList;
    }
    
    public TechClarificationBean getTechClarification(int terId, int venId) throws Exception {
        ResultSet rs = null;
        
        String sql = "SELECT t.*,v.name FROM tech_clarification AS t LEFT JOIN vendor AS v ON v.ven_id=" + venId + " WHERE ter_id=" + terId;
        
        
        try {
            String sql1 = "SELECT v.name FROM vendor AS v WHERE ven_id=" + venId;
            String vendorName = "";
            
            
            rs = DBUtil.executeQuery(sql1);
            while (rs.next()) {
                vendorName = StringUtil.decodeString(rs.getString("v.name"));
            }
            DBUtil.closeConnection(rs);
            //System.out.println("sql=" + sql);
            rs = DBUtil.executeQuery(sql);
            TechClarificationBean tech_clarification = new TechClarificationBean();
            while (rs.next()) {
                tech_clarification.setTcId(rs.getInt("tc_id"));
                tech_clarification.setTerId(terId);
                tech_clarification.setTcNumber(rs.getString("tc_number"));
                tech_clarification.setSubfix(rs.getString("subfix"));
                tech_clarification.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                tech_clarification.setVendorName(vendorName);
                tech_clarification.setVenId(venId);
                
                return tech_clarification;
            }
            if (tech_clarification.getTcId() == 0) {
                tech_clarification.setVendorName(vendorName);
                tech_clarification.setVenId(venId);
                tech_clarification.setTerId(terId);
                tech_clarification.setVenId(venId);
                tech_clarification.setCreatedDate(DateUtil.today("dd/MM/yyyy"));
                
                return tech_clarification;
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
    
    public void insertTechClarification(TechClarificationBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        
        
        
        try {
            
            String sql = "";
            
            
            
            sql = "Insert Into tech_clarification(ter_id, tc_number, subfix, created_date)"
                    + " Values (" + bean.getTerId() + ",'" + bean.getTcNumber()
                    + "','" + bean.getSubfix() + "'," + bean.getCreatedDate() + "')";

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
    
    public int insertTechClarificationId(TechClarificationBean bean) throws Exception {
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

            sql = "Insert Into tech_clarification(ter_id, tc_number, subfix, created_date)"
                    + " Values (" + bean.getTerId() + ",'" + bean.getTcNumber()
                    + "','" + bean.getSubfix() + "',now())";
            
            System.out.println("sql ====" + sql);
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
    
    public void updateTechClarification(TechClarificationBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        
        
        
        try {
            
            
            
            String sql = "Update tech_clarification Set "
                    + " ter_id=" + bean.getTerId()
                    + ", tc_number='" + bean.getTcNumber() + "'"
                    + ", subfix='" + bean.getSubfix() + "'"
                    + ", created_date=STR_TO_DATE('" + bean.getCreatedDate() + "','%d/%m/%Y')"
                    + " Where tc_id=" + bean.getTcId();

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
    
    public int deleteTechClarification(int tcId) throws Exception {
        
        
        int result = 0;
        try {
            
            
            String sql = "Delete From tech_clarification " + " Where tc_id=" + tcId;
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
    
    public ArrayList searchSimpleTechClarification(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "tc_number";
                break;
        }
        ResultSet rs = null;
        
        String sql = "Select * From tech_clarification Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by tc_id DESC";
        
        ArrayList tech_clarificationList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            TechClarificationBean tech_clarification = null;
            while (rs.next()) {
                tech_clarification = new TechClarificationBean();
                tech_clarification.setTcId(rs.getInt("tc_id"));
                tech_clarification.setTerId(rs.getInt("ter_id"));
                tech_clarification.setTcNumber(rs.getString("tc_number"));
                tech_clarification.setSubfix(rs.getString("subfix"));
                tech_clarification.setCreatedDate(rs.getString("created_date"));
                tech_clarificationList.add(tech_clarification);
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
        return tech_clarificationList;
        
    }
    
    public ArrayList searchAdvTechClarification(TechClarificationBean bean) throws Exception {
        ResultSet rs = null;
        
        String sql = "Select * From tech_clarification Where 1 ";
        
        if (bean.getTcId() != 0) {
            sql = sql + " AND tc_id =" + bean.getTcId();
        }
        if (bean.getTenId() != 0) {
            sql = sql + " AND ter_id =" + bean.getTerId();
        }
        
        if (!StringUtil.isBlankOrNull(bean.getTcNumber())) {
            sql = sql + " AND tc_number LIKE '%" + bean.getTcNumber() + "%'";
        }
        
        if (!StringUtil.isBlankOrNull(bean.getSubfix())) {
            sql = sql + " AND subfix LIKE '%" + bean.getSubfix() + "%'";
        }
        
        if (bean.getCreatedDate() != null) {
            sql = sql + " AND created_date = '" + bean.getCreatedDate() + "'";
        }
        
        sql = sql + " Order by tc_id DESC";
        
        ArrayList tech_clarificationList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            TechClarificationBean tech_clarification = null;
            
            while (rs.next()) {
                tech_clarification = new TechClarificationBean();
                tech_clarification.setTcId(rs.getInt("tc_id"));
                tech_clarification.setTerId(rs.getInt("ter_id"));
                tech_clarification.setTcNumber(rs.getString("tc_number"));
                tech_clarification.setSubfix(rs.getString("subfix"));
                tech_clarification.setCreatedDate(rs.getString("created_date"));
                tech_clarificationList.add(tech_clarification);
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
        return tech_clarificationList;
    }
}
