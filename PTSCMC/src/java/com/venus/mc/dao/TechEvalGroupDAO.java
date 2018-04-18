/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.mc.bean.TechEvalGroupBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mai vinh loc
 */
public class TechEvalGroupDAO extends BasicDAO {

    public TechEvalGroupDAO() {
    }

    public ArrayList getTechEvalGroups()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tech_eval_group Order by teg_id ASC";

        ArrayList tech_eval_groupList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            TechEvalGroupBean tech_eval_group = null;
            while (rs.next()) {
                tech_eval_group = new TechEvalGroupBean();
                tech_eval_group.setTegId(rs.getInt("teg_id"));
                tech_eval_group.setTeId(rs.getInt("te_id"));
                tech_eval_group.setEmployee(rs.getString("employee"));
                tech_eval_group.setPosition(rs.getString("position"));
                tech_eval_groupList.add(tech_eval_group);
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
        return tech_eval_groupList;
    }

    public ArrayList getEmployeeForTechEval(String teId)
            throws Exception {

        ResultSet rs = null;

        String sql = "select p.*,e.*,g.* from tech_eval_group as g left join employee as e on g.employee = e.emp_id left join organization as o on e.org_id=o.org_id left join position as p on p.pos_id=e.pos_id where te_id = '" + teId + "' order by e.fullname desc";

        ArrayList empList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            TechEvalGroupBean bean = null;
            while (rs.next()) {
                bean = new TechEvalGroupBean();
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

    public TechEvalGroupBean getTechEvalGroup(int tegId) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tech_eval_group Where teg_id=" + tegId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                TechEvalGroupBean tech_eval_group = new TechEvalGroupBean();
                tech_eval_group.setTegId(rs.getInt("teg_id"));
                tech_eval_group.setTeId(rs.getInt("te_id"));
                tech_eval_group.setEmployee(rs.getString("employee"));
                tech_eval_group.setPosition(rs.getString("position"));

                return tech_eval_group;
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

    public void insertTechEvalGroup(TechEvalGroupBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {

            String sql = "";



            sql = "Insert Into tech_eval_group(te_id, employee, position)"
                    + " Values (" + bean.getTeId() + ",'" + bean.getEmployee()
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

    public void updateTechEvalGroup(TechEvalGroupBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {



            String sql = "Update tech_eval_group Set "
                    + " te_id=" + bean.getTeId()
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

    public int deleteTechEvalGroup(int tegId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From tech_eval_group " + " Where teg_id=" + tegId;
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
