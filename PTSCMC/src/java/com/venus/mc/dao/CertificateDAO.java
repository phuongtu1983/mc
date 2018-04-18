package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.CertificateBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mai Vinh Loc
 */
public class CertificateDAO extends BasicDAO {

    public CertificateDAO() {
    }

    public ArrayList getCertificates() throws Exception {
        ResultSet rs = null;
        String sql = "SELECT * FROM certificate  order by cer_id DESC";

        ArrayList cerList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            CertificateBean certificate = null;
            while (rs.next()) {
                certificate = new CertificateBean();
                certificate.setCerId(rs.getInt("cer_id"));
                certificate.setCerName(rs.getString("cer_name"));
                cerList.add(certificate);
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
        return cerList;
    }

    public CertificateBean getCertificate(int cerId) throws Exception {
        ResultSet rs = null;

        String sql = "SELECT certificate.* From certificate WHERE cer_id=" + cerId;
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                CertificateBean certificate = new CertificateBean();
                certificate.setCerId(rs.getInt("cer_id"));
                certificate.setCerName(StringUtil.decodeString(rs.getString("cer_name")));
                return certificate;
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

    public CertificateBean getCertificateByName(String cer_name) throws Exception {
        ResultSet rs = null;

        String sql = "Select cer_id From certificate Where cer_name = '" + cer_name + "'";
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                CertificateBean certificate = new CertificateBean();
                certificate.setCerId(rs.getInt("cer_id"));
                return certificate;
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

    public void insertCertificate(CertificateBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Insert Into certificate(cer_name)"
                    + " Values ('" + bean.getCerName() + "')";
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateCertificate(CertificateBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Update certificate Set "
                    + " cer_name='" + bean.getCerName() + "'"
                    + " Where cer_id=" + bean.getCerId();
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteCertificate(int cerId) throws Exception {
        int result = 0;
        try {
            String sql = "Delete From certificate Where cer_id = " + cerId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleCertificate(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "cer_name";
                break;
        }
        ResultSet rs = null;

        String sql = "Select certificate.* From certificate Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by cer_id DESC";

        ArrayList certificateList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            CertificateBean certificate = null;
            while (rs.next()) {
                certificate = new CertificateBean();
                certificate.setCerId(rs.getInt("cer_id"));
                certificate.setCerName(rs.getString("cer_name"));
                certificateList.add(certificate);
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
        return certificateList;
    }
}
