/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.mc.bean.TenderEvaluateGroupBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class TenderEvaluateGroupDAO extends BasicDAO {

    public TenderEvaluateGroupDAO() {
    }

    public ArrayList getTenderEvaluateGroups()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tender_evaluate_group Order by teg_id ASC";

        ArrayList tender_evaluate_groupList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            TenderEvaluateGroupBean tender_evaluate_group = null;
            while (rs.next()) {
                tender_evaluate_group = new TenderEvaluateGroupBean();
                tender_evaluate_group.setTegId(rs.getInt("teg_id"));
                tender_evaluate_group.setTenId(rs.getInt("ten_id"));
                tender_evaluate_group.setEmployee(rs.getString("employee"));
                tender_evaluate_group.setPosition(rs.getString("position"));
                tender_evaluate_groupList.add(tender_evaluate_group);
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
        return tender_evaluate_groupList;
    }

    public TenderEvaluateGroupBean getTenderEvaluateGroup(int tegId) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tender_evaluate_group Where teg_id=" + tegId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                TenderEvaluateGroupBean tender_evaluate_group = new TenderEvaluateGroupBean();
                tender_evaluate_group.setTegId(rs.getInt("teg_id"));
                tender_evaluate_group.setTenId(rs.getInt("ten_id"));
                tender_evaluate_group.setEmployee(rs.getString("employee"));
                tender_evaluate_group.setPosition(rs.getString("position"));

                return tender_evaluate_group;
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

    public void insertTenderEvaluateGroup(TenderEvaluateGroupBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {

            String sql = "";



            sql = "Insert Into tender_evaluate_group(ten_id, employee, position)"
                    + " Values (" + bean.getTenId() + ",'" + bean.getEmployee()
                    + "','" + bean.getPosition() + "')";

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

    public void updateTenderEvaluateGroup(TenderEvaluateGroupBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {



            String sql = "Update tender_evaluate_group Set "
                    + " ten_id=" + bean.getTenId()
                    + ", employee='" + bean.getEmployee() + "'"
                    + ", postion='" + bean.getPosition() + "'"
                    + " Where teg_id=" + bean.getTegId();

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

    public int deleteTenderEvaluateGroup(int tegId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From tender_evaluate_group " + " Where teg_id=" + tegId;
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
}
