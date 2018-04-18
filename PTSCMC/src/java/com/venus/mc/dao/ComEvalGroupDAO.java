/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.mc.bean.ComEvalGroupBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mai vinh loc
 */
public class ComEvalGroupDAO extends BasicDAO {

    public ComEvalGroupDAO() {
    }

    public ArrayList getComEvalGroups()
            throws Exception {
        ResultSet rs = null;
        String sql = "Select * From com_eval_group Order by ceg_id ASC";

        ArrayList com_eval_groupList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalGroupBean com_eval_group = null;
            while (rs.next()) {
                com_eval_group = new ComEvalGroupBean();
                com_eval_group.setCegId(rs.getInt("ceg_id"));
                com_eval_group.setCeId(rs.getInt("ce_id"));
                com_eval_group.setEmployee(rs.getString("employee"));
                com_eval_group.setPosition(rs.getString("position"));
                com_eval_groupList.add(com_eval_group);
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
        return com_eval_groupList;
    }

    public ArrayList getEmployeeForComEval(String ceId)
            throws Exception {
        ResultSet rs = null;
        String sql = "select p.*,e.*,g.* from com_eval_group as g left join employee as e on g.employee = e.emp_id left join organization as o on e.org_id=o.org_id left join position as p on p.pos_id=e.pos_id where ce_id = '" + ceId + "' order by e.fullname desc";

        ArrayList empList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalGroupBean bean = null;
            while (rs.next()) {
                bean = new ComEvalGroupBean();
                bean.setEmployee(rs.getString("g.employee"));
                bean.setPosition(rs.getString("p.pos_id"));
                bean.setFullname(rs.getString("e.fullname"));
                bean.setPosName(rs.getString("p.name"));

                empList.add(bean);
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
        return empList;
    }

    public ComEvalGroupBean getComEvalGroup(int tegId) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From com_eval_group Where ceg_id=" + tegId;
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ComEvalGroupBean com_eval_group = new ComEvalGroupBean();
                com_eval_group.setCegId(rs.getInt("ceg_id"));
                com_eval_group.setCeId(rs.getInt("ce_id"));
                com_eval_group.setEmployee(rs.getString("employee"));
                com_eval_group.setPosition(rs.getString("position"));
                return com_eval_group;
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

    public void insertComEvalGroup(ComEvalGroupBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Insert Into com_eval_group(ce_id, employee, position)"
                    + " Values (" + bean.getCeId() + ",'" + bean.getEmployee()
                    + "','" + bean.getPosition() + "')";
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateComEvalGroup(ComEvalGroupBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Update com_eval_group Set "
                    + " ce_id=" + bean.getCeId()
                    + ", employee='" + bean.getEmployee() + "'"
                    + ", postion='" + bean.getPosition() + "'"
                    + " Where ceg_id=" + bean.getCegId();
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteComEvalGroup(int tegId) throws Exception {
        int result = 0;
        try {
            String sql = "Delete From com_eval_group " + " Where ceg_id=" + tegId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }
}
