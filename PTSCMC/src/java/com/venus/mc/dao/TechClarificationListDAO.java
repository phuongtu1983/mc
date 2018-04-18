/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.TechClarificationListBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class TechClarificationListDAO extends BasicDAO {

    public TechClarificationListDAO() {
    }

    public ArrayList getTechClarificationLists()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tech_clarification_list Order by tcl_id ASC";

        ArrayList tech_clarification_listList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            TechClarificationListBean tech_clarification_list = null;
            while (rs.next()) {
                tech_clarification_list = new TechClarificationListBean();
                tech_clarification_list.setTclId(rs.getInt("tcl_id"));
                tech_clarification_list.setTcId(rs.getInt("tc_id"));
                tech_clarification_list.setDiscipline(rs.getString("discipline"));
                tech_clarification_list.setCategory(rs.getString("category"));
                tech_clarification_list.setNote(StringUtil.getString(rs.getString("note")));
                tech_clarification_listList.add(tech_clarification_list);
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
        return tech_clarification_listList;
    }

    public TechClarificationListBean getTechClarificationList(int tclId) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tech_clarification_list Where tcl_id=" + tclId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                TechClarificationListBean tech_clarification_list = new TechClarificationListBean();
                tech_clarification_list.setTclId(rs.getInt("tcl_id"));
                tech_clarification_list.setTcId(rs.getInt("tc_id"));
                tech_clarification_list.setDiscipline(rs.getString("discipline"));
                tech_clarification_list.setCategory(rs.getString("category"));
                tech_clarification_list.setNote(StringUtil.getString(rs.getString("note")));

                return tech_clarification_list;
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

    public ArrayList getTechClarificationListsTcId(int tcId)
            throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tech_clarification_list where tc_id=" + tcId + " Order by tcl_id ASC";

        ArrayList tech_clarification_listList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            TechClarificationListBean tech_clarification_list = null;
            while (rs.next()) {
                tech_clarification_list = new TechClarificationListBean();
                tech_clarification_list.setTclId(rs.getInt("tcl_id"));
                tech_clarification_list.setTcId(rs.getInt("tc_id"));
                tech_clarification_list.setDiscipline(rs.getString("discipline"));
                tech_clarification_list.setCategory(rs.getString("category"));
                tech_clarification_list.setNote(StringUtil.getString(rs.getString("note")));
                tech_clarification_listList.add(tech_clarification_list);
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
        return tech_clarification_listList;
    }

    public void insertTechClarificationList(TechClarificationListBean bean, int tcId) throws Exception {
        if (bean == null) {
            return;
        }



        try {

            String sql = "";



            sql = "Insert Into tech_clarification_list(tc_id, discipline, category, note)"
                    + " Values (" + tcId + ",'" + bean.getDiscipline()
                    + "','" + bean.getCategory() + "','" + bean.getNote() + "')";

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

    public void updateTechClarificationListForDetail(TechClarificationListBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {



            String sql = "Update tech_clarification_list Set "
                    + //    " tc_id=" + bean.getTcId() +
                    "  discipline='" + bean.getDiscipline() + "'"
                    + ", category='" + bean.getCategory() + "'"
                    + ", note='" + bean.getNote() + "'"
                    + " Where tcl_id=" + bean.getTclId();

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

    public void updateTechClarificationList(TechClarificationListBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {



            String sql = "Update tech_clarification_list Set "
                    + " tc_id=" + bean.getTcId()
                    + ", discipline='" + bean.getDiscipline() + "'"
                    + ", category='" + bean.getCategory() + "'"
                    + ", note=" + bean.getNote() + "'"
                    + " Where tcl_id=" + bean.getTclId();

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

    public int deleteTechClarificationList(int tclId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From tech_clarification_list " + " Where tcl_id=" + tclId;
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

    public ArrayList searchSimpleTechClarificationList(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "tc_id";
                break;
        }
        ResultSet rs = null;

        String sql = "Select * From tech_clarification_list Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " = " + strFieldvalue;
        }
        sql = sql + " Order by tcl_id DESC";

        ArrayList tech_clarification_listList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            TechClarificationListBean tech_clarification_list = null;
            while (rs.next()) {
                tech_clarification_list = new TechClarificationListBean();
                tech_clarification_list.setTclId(rs.getInt("tcl_id"));
                tech_clarification_list.setTcId(rs.getInt("tc_id"));
                tech_clarification_list.setDiscipline(rs.getString("discipline"));
                tech_clarification_list.setCategory(rs.getString("category"));
                tech_clarification_list.setNote(StringUtil.getString(rs.getString("note")));
                tech_clarification_listList.add(tech_clarification_list);
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
        return tech_clarification_listList;

    }

    public ArrayList searchAdvTechClarificationList(TechClarificationListBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From tech_clarification_list Where 1 ";

        if (bean.getTclId() != 0) {
            sql = sql + " AND tcl_id =" + bean.getTclId();
        }
        if (bean.getTcId() != 0) {
            sql = sql + " AND tc_id =" + bean.getTcId();
        }
        if (!StringUtil.isBlankOrNull(bean.getDiscipline())) {
            sql = sql + " AND discipline LIKE '%" + bean.getDiscipline() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getCategory())) {
            sql = sql + " AND category LIKE '%" + bean.getCategory() + "%'";
        }
        if (!StringUtil.isBlankOrNull(bean.getNote())) {
            sql = sql + " AND note LIKE '%" + bean.getNote() + "%'";
        }
        sql = sql + " Order by tcl_id DESC";

        ArrayList tech_clarification_listList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            TechClarificationListBean tech_clarification_list = null;

            while (rs.next()) {
                tech_clarification_list = new TechClarificationListBean();
                tech_clarification_list.setTclId(rs.getInt("tcl_id"));
                tech_clarification_list.setTcId(rs.getInt("tc_id"));
                tech_clarification_list.setDiscipline(rs.getString("discipline"));
                tech_clarification_list.setCategory(rs.getString("category"));
                tech_clarification_list.setNote(StringUtil.getString(rs.getString("note")));
                tech_clarification_listList.add(tech_clarification_list);
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
        return tech_clarification_listList;
    }
}
