/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.FileAttachmentBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class RequestFileAttachmentDAO extends BasicDAO {

    public RequestFileAttachmentDAO() {
    }

    public ArrayList getRequestFileAttachments()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select * From request_file_attachment Order by att_id ASC";

        ArrayList request_file_attachmentList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            FileAttachmentBean request_file_attachment = null;
            while (rs.next()) {
                request_file_attachment = new FileAttachmentBean();
                request_file_attachment.setAttId(rs.getInt("att_id"));
                request_file_attachment.setPid(rs.getInt("p_id"));
                request_file_attachment.setCreatedEmp(rs.getInt("created_emp"));
                request_file_attachment.setAttName(rs.getString("att_name"));
                request_file_attachment.setSource(rs.getString("source"));
                request_file_attachment.setHref(rs.getString("href"));
                request_file_attachment.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                request_file_attachment.setFileSize(rs.getInt("file_size"));
                request_file_attachment.setComments(StringUtil.getString(rs.getString("comments")));
                request_file_attachmentList.add(request_file_attachment);
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
        return request_file_attachmentList;
    }

    public FileAttachmentBean getRequestFileAttachment(int attId) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From request_file_attachment Where att_id=" + attId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                FileAttachmentBean request_file_attachment = new FileAttachmentBean();
                request_file_attachment.setAttId(rs.getInt("att_id"));
                request_file_attachment.setPid(rs.getInt("req_id"));
                request_file_attachment.setCreatedEmp(rs.getInt("created_emp"));
                request_file_attachment.setAttName(rs.getString("att_name"));
                request_file_attachment.setSource(rs.getString("source"));
                request_file_attachment.setHref(rs.getString("href"));
                request_file_attachment.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                request_file_attachment.setFileSize(rs.getInt("file_size"));
                request_file_attachment.setComments(StringUtil.getString(rs.getString("comments")));

                return request_file_attachment;
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

    public void insertRequestFileAttachment(FileAttachmentBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {

            String sql = "";



            sql = "Insert Into request_file_attachment(req_id, created_emp, att_name, source, href, created_date, file_size, comments)"
                    + " Values (" + bean.getCreatedEmp() + ",'" + bean.getAttName()
                    + "','" + bean.getSource() + "','" + bean.getHref() + "','" + bean.getCreatedDate() + "'," + bean.getFileSize()
                    + ",'" + bean.getComments() + "')";

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

    public void updateRequestFileAttachment(FileAttachmentBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {



            String sql = "Update request_file_attachment Set "
                    + " p_id=" + bean.getPid()
                    + ", created_emp=" + bean.getCreatedEmp()
                    + ", att_name='" + bean.getAttName() + "'"
                    + ", source='" + bean.getSource() + "'"
                    + ", href='" + bean.getHref() + "'"
                    + ", created_date='" + bean.getCreatedDate() + "'"
                    + ", file_size=" + bean.getFileSize()
                    + ", comments='" + bean.getComments() + "'"
                    + " Where att_id=" + bean.getAttId();

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

    public int deleteRequestFileAttachment(int attId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From request_file_attachment " + " Where att_id=" + attId;
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

    public ArrayList searchSimpleRequestFileAttachment(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "att_name";
                break;
        }
        ResultSet rs = null;

        String sql = "Select * From request_file_attachment Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by att_id DESC";

        ArrayList request_file_attachmentList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            FileAttachmentBean request_file_attachment = null;
            while (rs.next()) {
                request_file_attachment = new FileAttachmentBean();
                request_file_attachment.setAttId(rs.getInt("att_id"));
                request_file_attachment.setPid(rs.getInt("req_id"));
                request_file_attachment.setPid(rs.getInt("created_emp"));
                request_file_attachment.setAttName(rs.getString("att_name"));
                request_file_attachment.setSource(rs.getString("source"));
                request_file_attachment.setHref(rs.getString("href"));
                request_file_attachment.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                request_file_attachment.setFileSize(rs.getInt("file_size"));
                request_file_attachment.setComments(StringUtil.getString(rs.getString("comments")));
                request_file_attachmentList.add(request_file_attachment);
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
        return request_file_attachmentList;
    }

    public ArrayList searchAdvRequestFileAttachment(FileAttachmentBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From request_file_attachment Where 1 ";

        if (bean.getAttId() != 0) {
            sql = sql + " AND att_id = " + bean.getAttId();
        }

        if (bean.getCreatedEmp() != 0) {
            sql = sql + " AND created_emp = " + bean.getCreatedEmp();
        }

        if (bean.getPid() != 0) {
            sql = sql + " AND req_id =" + bean.getPid();
        }

        if (!StringUtil.isBlankOrNull(bean.getAttName())) {
            sql = sql + " AND att_name LIKE '% " + bean.getAttName() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getSource())) {
            sql = sql + " AND source LIKE '%" + bean.getSource() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getHref())) {
            sql = sql + " AND href LIKE '%" + bean.getHref() + "%'";
        }

        if (bean.getCreatedDate() != null) {
            sql = sql + " AND created_date = '" + bean.getCreatedDate() + "'";
        }

        if (bean.getFileSize() != 0) {
            sql = sql + " AND file_size = " + bean.getFileSize();
        }

        if (!StringUtil.isBlankOrNull(bean.getComments())) {
            sql = sql + " AND comments LIKE '%" + bean.getComments() + "%'";
        }
        sql = sql + " Order by att_id DESC";

        ArrayList request_file_attachmentList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            FileAttachmentBean request_file_attachment = null;

            while (rs.next()) {
                request_file_attachment = new FileAttachmentBean();
                request_file_attachment.setAttId(rs.getInt("att_id"));
                request_file_attachment.setPid(rs.getInt("req_id"));
                request_file_attachment.setPid(rs.getInt("created_emp"));
                request_file_attachment.setAttName(rs.getString("att_name"));
                request_file_attachment.setSource(rs.getString("source"));
                request_file_attachment.setHref(rs.getString("href"));
                request_file_attachment.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                request_file_attachment.setFileSize(rs.getInt("file_size"));
                request_file_attachment.setComments(StringUtil.getString(rs.getString("comments")));
                request_file_attachmentList.add(request_file_attachment);
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
        return request_file_attachmentList;
    }
}
