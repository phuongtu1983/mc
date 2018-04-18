package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.EmaterialBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mai Vinh Loc
 */
public class EmaterialDAO extends BasicDAO {

    public EmaterialDAO() {
    }

    public ArrayList getEmaterials() throws Exception {

        ResultSet rs = null;

        String sql = "select * from ematerial order by emat_id desc";

        ArrayList ematerialList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EmaterialBean ematerial = null;
            while (rs.next()) {
                ematerial = new EmaterialBean();
                ematerial.setEmatId(rs.getInt("emat_id"));
                ematerial.setOriId(rs.getInt("ori_id"));
                ematerial.setUniId(rs.getInt("uni_id"));
                ematerial.setCode(rs.getString("code"));
                ematerial.setNameEn(StringUtil.decodeString(rs.getString("name_en")));
                ematerial.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                ematerial.setCode(rs.getString("code"));
                ematerial.setQc(rs.getString("qc"));
                if (rs.getString("kind").equals("0")) {
                    ematerial.setKindName("");
                }
                if (rs.getString("kind").equals("1")) {
                    ematerial.setKindName("TTBDC");
                }
                if (rs.getString("kind").equals("2")) {
                    ematerial.setKindName("VTTB");
                }
                ematerial.setNote(StringUtil.getString(rs.getString("note")));

                ematerialList.add(ematerial);
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
        return ematerialList;
    }

    public EmaterialBean getEmaterial(String ematerialid) throws Exception {

        ResultSet rs = null;

        String sql = "select * from ematerial where emat_id=" + ematerialid;


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EmaterialBean ematerial = new EmaterialBean();
                ematerial.setEmatId(rs.getInt("emat_id"));
                ematerial.setOriId(rs.getInt("ori_id"));
                ematerial.setOriIdEn(rs.getInt("ori_id"));
                ematerial.setUniId(rs.getInt("uni_id"));
                ematerial.setUniIdEn(rs.getInt("uni_id"));
                ematerial.setCode(rs.getString("code"));
                ematerial.setNameEn(StringUtil.decodeString(rs.getString("name_en")));
                ematerial.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                ematerial.setCode(rs.getString("code"));
                ematerial.setKind(rs.getInt("kind"));
                ematerial.setNote(StringUtil.getString(rs.getString("note")));
                ematerial.setQc(rs.getString("qc"));

                return ematerial;
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

    public EmaterialBean getEmaterialMrir(int ematId) throws Exception {
        ResultSet rs = null;

        String sql = "select ematerial.* From ematerial Where emat_id=" + ematId;


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EmaterialBean ematerial = new EmaterialBean();
                ematerial.setEmatId(rs.getInt("emat_id"));
                ematerial.setOriId(rs.getInt("ori_id"));
                ematerial.setUniId(rs.getInt("uni_id"));
                ematerial.setCode(rs.getString("code"));
                ematerial.setNameEn(StringUtil.decodeString(rs.getString("name_en")));
                ematerial.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                ematerial.setCode(rs.getString("code"));
                ematerial.setKind(rs.getInt("kind"));
                ematerial.setNote(StringUtil.getString(rs.getString("note")));
                ematerial.setQc(rs.getString("qc"));

                return ematerial;
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

    public void insertEmaterial(EmaterialBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "";
            sql = "insert into ematerial(ori_id,uni_id,code,name_vn,name_en,kind,note,qc)"
                    + " values ('" + bean.getOriId() + "','" + bean.getUniId() + "','" + bean.getCode() + "','" + bean.getNameVn() + "','" + bean.getNameEn() + "','" + bean.getKind() + "','" + bean.getNote() + "','" + bean.getQc() + "')";

            ////System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateEmaterial(EmaterialBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "update ematerial set "
                    + " ori_id='" + bean.getOriId() + "'"
                    + ", uni_id='" + bean.getUniId() + "'"
                    + ", code='" + bean.getCode() + "'"
                    + ", name_en='" + bean.getNameEn() + "'"
                    + ", name_vn='" + bean.getNameVn() + "'"
                    + ", kind='" + bean.getKind() + "'"
                    + ", note='" + bean.getNote() + "'"
                    + ", qc='" + bean.getQc() + "'"
                    + " where emat_id=" + bean.getEmatId();
            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteEmaterial(String ematerialid) throws Exception {
        int result = 0;
        try {
            String sql = "delete from ematerial " + " where emat_id=" + ematerialid;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleEmaterial(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "code";
                break;
            case 2:
                strFieldname = "name_vn";
                break;
            case 3:
                strFieldname = "name_en";
                break;
            case 4:
                strFieldname = "note";
                break;
        }
        ResultSet rs = null;

        String sql = "select * from ematerial where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by emat_id desc";

        ArrayList ematerialList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EmaterialBean ematerial = null;
            while (rs.next()) {
                ematerial = new EmaterialBean();
                ematerial.setEmatId(rs.getInt("emat_id"));
                ematerial.setOriId(rs.getInt("ori_id"));
                ematerial.setUniId(rs.getInt("uni_id"));
                ematerial.setCode(rs.getString("code"));
                ematerial.setNameEn(StringUtil.decodeString(rs.getString("name_en")));
                ematerial.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                ematerial.setCode(rs.getString("code"));
                ematerial.setKind(rs.getInt("kind"));
                ematerial.setQc(rs.getString("qc"));
                if (rs.getString("kind").equals("0")) {
                    ematerial.setKindName("");
                }
                if (rs.getString("kind").equals("1")) {
                    ematerial.setKindName("TTBDC");
                }
                if (rs.getString("kind").equals("2")) {
                    ematerial.setKindName("VTTB");
                }
                ematerial.setNote(StringUtil.getString(rs.getString("note")));
                ematerialList.add(ematerial);
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
        return ematerialList;

    }

    public ArrayList searchAdvEmaterial(EmaterialBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "select * from ematerial where 1 ";

        if (bean.getOriId() > 0) {
            sql = sql + " AND ori_id = '" + bean.getOriId() + "'";
        }

        if (bean.getUniId() > 0) {
            sql = sql + " AND uni_id = '" + bean.getUniId() + "'";
        }

        if (!StringUtil.isBlankOrNull(bean.getCode())) {
            sql = sql + " AND code LIKE '%" + bean.getCode() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getNameEn())) {
            sql = sql + " AND name_en LIKE '%" + bean.getNameEn() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getNameVn())) {
            sql = sql + " AND name_vn LIKE '%" + bean.getNameVn() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getNote())) {
            sql = sql + " AND note LIKE '%" + bean.getNote() + "%'";
        }

        if (bean.getKind() > 0) {
            sql = sql + " AND kind = '" + bean.getKind() + "'";
        }

        sql = sql + " order by emat_id desc";

        ArrayList ematerialList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EmaterialBean ematerial = null;

            while (rs.next()) {
                ematerial = new EmaterialBean();
                ematerial.setEmatId(rs.getInt("emat_id"));
                ematerial.setOriId(rs.getInt("ori_id"));
                ematerial.setUniId(rs.getInt("uni_id"));
                ematerial.setCode(rs.getString("code"));
                ematerial.setNameEn(StringUtil.decodeString(rs.getString("name_en")));
                ematerial.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                ematerial.setCode(rs.getString("code"));
                ematerial.setKind(rs.getInt("kind"));
                ematerial.setQc(rs.getString("qc"));
                if (rs.getString("kind").equals("0")) {
                    ematerial.setKindName("");
                }
                if (rs.getString("kind").equals("1")) {
                    ematerial.setKindName("TTBDC");
                }
                if (rs.getString("kind").equals("2")) {
                    ematerial.setKindName("VTTB");
                }
                ematerial.setNote(StringUtil.getString(rs.getString("note")));
                ematerialList.add(ematerial);
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
        return ematerialList;
    }

    public ArrayList searchEmaterial(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "code";
                break;
            case 2:
                strFieldname = "name_vn";
                break;
        }
        ResultSet rs = null;

        String sql = "select emat_id, code, name_vn, name_en from ematerial where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by emat_id desc";

        ArrayList ematerialList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EmaterialBean ematerial = null;
            while (rs.next()) {
                ematerial = new EmaterialBean();
                ematerial.setEmatId(rs.getInt("emat_id"));
                ematerial.setCode(rs.getString("code"));
                ematerial.setNameEn(StringUtil.decodeString(rs.getString("name_en")));
                ematerial.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                ematerialList.add(ematerial);
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
        return ematerialList;

    }

    public boolean checkCode(int id, String value) throws SQLException {
        ResultSet rs = null;
        try {
            rs = DBUtil.executeQuery("SELECT * FROM ematerial WHERE emat_id <> " + id + " AND code = '" + value + "'");
            if (rs.next()) {

                return true;
            } else {

                return false;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
    }

    public boolean checkName(int id, String nameVn, String nameEn) throws SQLException {
        ResultSet rs = null;
        try {



            //     System.out.println("executeQuery: " + sql);

            rs = DBUtil.executeQuery("SELECT * FROM ematerial WHERE (emat_id <> " + id + " AND name_vn = '" + nameVn + "') OR (mat_id <> " + id + " AND name_en = '" + nameEn + "')");
            if (rs.next()) {

                return true;
            } else {

                return false;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
    }

    public boolean checkDeleted(String id) throws SQLException {
        ResultSet rs = null;
        try {



            String sql = "";
            //     System.out.println("executeQuery: " + sql);

            sql = "SELECT * FROM ematerial_store WHERE emat_id =";


            rs = DBUtil.executeQuery(sql + id);
            if (rs.next()) {

                return true;
            } else {

                return false;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
    }
}
