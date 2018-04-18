/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.WebBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class WebDAO extends BasicDAO {

    public WebDAO() {
    }

    public ArrayList getWebs()
            throws Exception {
        ResultSet rs = null;

        String sql = "SELECT web.* From web Order by web_id ASC";

        ArrayList webList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            WebBean web = null;
            while (rs.next()) {
                web = new WebBean();
                web.setWebId(rs.getInt("web_id"));
                web.setAddress(StringUtil.decodeString(rs.getString("address")));
                web.setContent(rs.getString("content"));
                web.setComment(rs.getString("comment"));
                web.setEvaluation(rs.getString("evaluation"));
                webList.add(web);
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
        return webList;
    }

    public WebBean getWeb(int webId) throws Exception {
        ResultSet rs = null;

        String sql = "SELECT web.* From web WHERE web_id=" + webId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                WebBean web = new WebBean();
                web.setWebId(rs.getInt("web_id"));
                web.setAddress(StringUtil.decodeString(rs.getString("address")));
                web.setContent(rs.getString("content"));
                web.setComment(rs.getString("comment"));
                web.setEvaluation(rs.getString("evaluation"));

                return web;
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

    public WebBean getWebByAddress(String address) throws Exception {
        ResultSet rs = null;

        String sql = "Select web_id From web Where address = '" + address + "'";


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                WebBean web = new WebBean();
                web.setWebId(rs.getInt("web_id"));

                return web;
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

    public void insertWeb(WebBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {

            String sql = "";



            sql = "Insert Into web(address, content, comment, evaluation)"
                    + " Values ('" + bean.getAddress() + "','" + bean.getContent()
                    + "','" + bean.getComment() + "','" + bean.getEvaluation() + "')";

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

    public void updateWeb(WebBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {



            String sql = "Update web Set "
                    + " address='" + bean.getAddress() + "'"
                    + ", content='" + bean.getContent() + "'"
                    + ", comment='" + bean.getComment() + "'"
                    + ", evaluation='" + bean.getEvaluation() + "'"
                    + " Where web_id=" + bean.getWebId();

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

    public int deleteWeb(int webId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From web Where web_id = " + webId;
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

    public ArrayList searchSimpleWeb(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "address";
                break;
            case 2:
                strFieldname = "content";
                break;
            case 3:
                strFieldname = "comment";
                break;
        }
        ResultSet rs = null;

        String sql = "Select web.* From web Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by web_id DESC";

        ArrayList webList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            WebBean web = null;
            while (rs.next()) {
                web = new WebBean();
                web.setWebId(rs.getInt("web_id"));
                web.setAddress(rs.getString("address"));
                web.setContent(rs.getString("content"));
                web.setComment(rs.getString("comment"));
                web.setEvaluation(rs.getString("evaluation"));
                webList.add(web);
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
        return webList;
    }
}
