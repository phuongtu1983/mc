/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.FileAttachmentBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.upload.UploadFileUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author thcao
 */
public class FileAttachmentDAO extends BasicDAO {

    @Deprecated
    public static String getTableName(int ftype) {
        String tableName = "";
        if (ftype == UploadFileUtil.ATTACH_FILE_REQUEST) {
            tableName = "request_file_attachment";
        } else if (ftype == UploadFileUtil.ATTACH_FILE_TENDERPLAN) {
            tableName = "tender_plan_file_attachment";
        } else if (ftype == UploadFileUtil.ATTACH_FILE_TENDERLETTER) {
            tableName = "tender_letter_file_attachment";
        } else if (ftype == UploadFileUtil.ATTACH_FILE_CONTRACT) {
            tableName = "contract_file_attachment";
        } else if (ftype == UploadFileUtil.ATTACH_FILE_BIDCLOSE) {
            tableName = "bid_closing_file_attachment";
        } else if (ftype == UploadFileUtil.ATTACH_FILE_BIDOPEN) {
            tableName = "bid_opening_file_attachment";
        } else if (ftype == UploadFileUtil.ATTACH_FILE_TECHEVAL) {
            tableName = "tech_eval_file_attachment";
        } else if (ftype == UploadFileUtil.ATTACH_FILE_COMEVAL) {
            tableName = "com_eval_file_attachment";
        } else if (ftype == UploadFileUtil.ATTACH_FILE_BIDSUM) {
            tableName = "bid_eval_sum_file_attachment";
        } else if (ftype == UploadFileUtil.ATTACH_FILE_REQUIREVERIFIED) {
            tableName = "require_verified_file_attachment";
        } else if (ftype == UploadFileUtil.ATTACH_FILE_EQUIPMENT) {
            tableName = "equ_file_attachment";
        }
        return tableName;
    }

    public int insertAttachmentFile(FileAttachmentBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }

        try {
            String sql = "";
            if (GenericValidator.isBlankOrNull(bean.getAttName())) {
                bean.setAttName("");
            }
            if (GenericValidator.isBlankOrNull(bean.getComments())) {
                bean.setComments("");
            }
            if (GenericValidator.isBlankOrNull(bean.getHref())) {
                bean.setHref("");
            }
            if (GenericValidator.isBlankOrNull(bean.getSource())) {
                bean.setSource("");
            }
            String tableName = UploadFileUtil.getTableName(bean.getFtype());

            sql = "insert into "
                    + tableName
                    + " (created_emp,p_id,att_name,source,href,created_date,file_size,comments)"
                    + " values ("
                    + bean.getCreatedEmp() + ","
                    + bean.getPid() + ","
                    + "'" + bean.getAttName() + "',"
                    + "'" + bean.getSource() + "',"
                    + "'" + bean.getHref() + "',"
                    + "sysdate(),"
                    + bean.getFileSize() + ","
                    + "'" + bean.getComments() + "')";

            return DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public ArrayList getAttachmentFiles(int ftype, int pid) throws Exception {
        ArrayList fileList = new ArrayList();
        if (pid == 0) {
            return fileList;
        }
        ResultSet rs = null;
        try {


            String tableName = UploadFileUtil.getTableName(ftype);
            String sql = "select att_id,created_emp,p_id,att_name,source,href,created_date,file_size,comments"
                    + " from " + tableName
                    + " where p_id=" + pid;


            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                FileAttachmentBean fileBean = new FileAttachmentBean();
                fileBean.setFtype(ftype);
                fileBean.setAttId(rs.getInt("att_id"));
                fileBean.setPid(rs.getInt("p_id"));
                fileBean.setCreatedEmp(rs.getInt("created_emp"));
                fileBean.setAttName(rs.getString("att_name"));
                fileBean.setSource(rs.getString("source"));
                fileBean.setHref(rs.getString("href"));
                fileBean.setFileSize(rs.getInt("file_size"));
                fileBean.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                fileBean.setComments(StringUtil.getString(rs.getString("comments")));
                fileList.add(fileBean);
            }

            return fileList;
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }

        }
    }

    public FileAttachmentBean getAttachmentFile(int ftype, int fid) throws Exception {
        if (fid == 0) {
            return null;
        }
        ResultSet rs = null;
        try {


            String tableName = UploadFileUtil.getTableName(ftype);
            String sql = "select att_id,created_emp,p_id,att_name,source,href,created_date,file_size,comments"
                    + " from " + tableName
                    + " where att_id=" + fid;


            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                FileAttachmentBean fileBean = new FileAttachmentBean();
                fileBean.setFtype(ftype);
                fileBean.setAttId(rs.getInt("att_id"));
                fileBean.setPid(rs.getInt("p_id"));
                fileBean.setCreatedEmp(rs.getInt("created_emp"));
                fileBean.setAttName(rs.getString("att_name"));
                fileBean.setSource(rs.getString("source"));
                fileBean.setHref(rs.getString("href"));
                fileBean.setFileSize(rs.getInt("file_size"));
                fileBean.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                fileBean.setComments(StringUtil.getString(rs.getString("comments")));

                return fileBean;
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

    //Loc
    public FileAttachmentBean getAttachmentAFile(int ftype, int pid, String comments) throws Exception {

        ResultSet rs = null;
        try {


            String tableName = UploadFileUtil.getTableName(ftype);
            String sql = "select att_id,created_emp,p_id,att_name,source,href,created_date,file_size,comments"
                    + " from " + tableName
                    + " where p_id=" + pid + " and comments like '" + comments + "'";


            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                FileAttachmentBean fileBean = new FileAttachmentBean();
                fileBean.setFtype(ftype);
                fileBean.setAttId(rs.getInt("att_id"));
                fileBean.setPid(rs.getInt("p_id"));
                fileBean.setCreatedEmp(rs.getInt("created_emp"));
                fileBean.setAttName(rs.getString("att_name"));
                fileBean.setSource(rs.getString("source"));
                fileBean.setHref(rs.getString("href"));
                fileBean.setFileSize(rs.getInt("file_size"));
                fileBean.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                fileBean.setComments(StringUtil.getString(rs.getString("comments")));

                return fileBean;
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

    public int deleteAttachmentFile(int ftype, int fid) throws Exception {
        if (fid == 0) {
            return 0;
        }
        try {
            String tableName = UploadFileUtil.getTableName(ftype);
            String sql = "delete from " + tableName
                    + " where att_id=" + fid;
            return DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    //Loc
    public String deleteAttachmentAFile(int ftype, int pid, String comments) throws Exception {
        if (pid == 0) {
            return "";
        }
        ResultSet rs = null;
        try {
            String tableName = UploadFileUtil.getTableName(ftype);
            int a;

            String source = "";
            String sql = "select source "
                    + " from " + tableName
                    + " where p_id = " + pid + " and comments like '" + comments + "'";





            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                source = rs.getString("source");
            }

            sql = "delete from " + tableName
                    + " where p_id=" + pid + " and comments like '" + comments + "'";
            a = DBUtil.executeUpdate(sql);

            return source;
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }

        }
    }
}
