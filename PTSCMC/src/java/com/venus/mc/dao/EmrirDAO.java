/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.EmrirBean;
import com.venus.mc.bean.EmrirDetailBean;
import com.venus.mc.bean.EmrirEosDBean;
import com.venus.mc.bean.EosDBean;
import com.venus.mc.bean.EosDDetailBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author kngo
 */
public class EmrirDAO extends BasicDAO {

    public String getUnit(int ematId) throws Exception {
        String sUnit = "";
        ResultSet rs = null;

        String sql = "Select unit_vn From ematerial inner join unit on ematerial.uni_id = unit.uni_id Where emat_id = " + ematId;

        try {



            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                sUnit = rs.getString("unit_vn");


                return sUnit;
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
        return sUnit;
    }

    public ArrayList getMaterials() throws Exception {
        ResultSet rs = null;

        String sql = "Select * From ematerial Order by emat_id desc";

        ArrayList materialList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EmrirDetailBean material = null;
            while (rs.next()) {
                material = new EmrirDetailBean();
                material.setEmatId(rs.getInt("emat_id"));
                material.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
                materialList.add(material);
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
        return materialList;
    }

    public ArrayList getMaterials(String ematIds) throws Exception {
        ResultSet rs = null;

        String sql = "Select ematerial.*, unit_vn From ematerial left join unit on ematerial.uni_id = unit.uni_id Where emat_id In(" + ematIds + ")Order by emat_id desc";

        ArrayList materialList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EmrirDetailBean material = null;
            while (rs.next()) {
                material = new EmrirDetailBean();
                material.setEmatId(rs.getInt("emat_id"));
                material.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
                material.setUnit(rs.getString("unit_vn"));
                materialList.add(material);
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
        return materialList;
    }

    public ArrayList searchMaterials(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "name_vn";
                break;
        }
        String sql = "";
        sql = "Select * From ematerial Where 1 ";

        ResultSet rs = null;

        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by emat_id DESC";

        ArrayList materialList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EmrirDetailBean material = null;
            while (rs.next()) {
                material = new EmrirDetailBean();
                material.setEmatId(rs.getInt("emat_id"));
                material.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
                materialList.add(material);
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
        return materialList;
    }

    public ArrayList getEmrirs()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select emrir.*, edn_number"
                + " From emrir"
                + " left join edelivery_notice on emrir.edn_id = edelivery_notice.edn_id"
                + " Order by emrir_id ASC";

        ArrayList emrirList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EmrirBean emrir = null;
            while (rs.next()) {
                emrir = new EmrirBean();
                emrir.setEmrirId(rs.getInt("emrir_id"));
                emrir.setEdnId(rs.getInt("edn_id"));
                emrir.setEdnNumber(rs.getString("edn_number"));
                emrir.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                emrir.setEmrirNumber(rs.getString("emrir_number"));
                emrir.setNote(StringUtil.getString(rs.getString("note")));
                emrir.setPackingListNo(rs.getString("packing_list_no"));
                emrirList.add(emrir);
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
        return emrirList;
    }

    public EmrirBean getEmrir(int emrirId) throws Exception {
        ResultSet rs = null;

        String sql = "Select m.*,edn.edn_number,edn.econ_number,edn.created_org,o.name,edn.pro_id,p.name"
                + " From emrir as m"
                + " left join edelivery_notice as edn on edn.edn_id=m.edn_id"
                + " left join organization as o on o.org_id=edn.created_org"
                + " left join organization as p on p.org_id=edn.pro_id"
                + " Where emrir_id = " + emrirId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EmrirBean emrir = new EmrirBean();
                emrir.setEmrirId(rs.getInt("emrir_id"));
                emrir.setEdnId(rs.getInt("edn_id"));
                emrir.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                emrir.setEmrirNumber(rs.getString("emrir_number"));
                emrir.setEdnNumber(rs.getString("edn.edn_number"));
                emrir.setConNumber(rs.getString("edn.econ_number"));
                emrir.setPackingListNo(rs.getString("m.packing_list_no"));
                emrir.setProId(rs.getInt("edn.pro_id"));
                emrir.setProName(rs.getString("p.name"));
                emrir.setOrgId(rs.getInt("edn.created_org"));
                emrir.setOrgName(rs.getString("o.name"));
                emrir.setNote(StringUtil.getString(rs.getString("note")));

                return emrir;
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

    public EmrirBean getEmrirByNumber(String emrirNumber) throws Exception {
        ResultSet rs = null;

        String sql = "Select emrir_id From emrir Where emrir_number = '" + emrirNumber + "'";


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EmrirBean emrir = new EmrirBean();
                emrir.setEmrirId(rs.getInt("emrir_id"));

                return emrir;
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

    public int insertEmrir(EmrirBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }

        if (GenericValidator.isBlankOrNull(bean.getNote())) {
            bean.setNote("");
        }
        if (GenericValidator.isBlankOrNull(bean.getPackingListNo())) {
            bean.setPackingListNo("");
        }
        String sql = "insert into emrir("
                + "created_date, "
                + "edn_id, ";
        if (bean.getProId() > 0) {
            sql += "pro_id,";
        }
        sql += "emrir_number, "
                + "note, "
                + "packing_list_no)"
                + " Values ("
                + "sysdate(),"
                + bean.getEdnId() + ",";
        if (bean.getProId() > 0) {
            sql += bean.getProId() + ",";
        }
        sql += "'" + bean.getEmrirNumber() + "',"
                + "'" + bean.getNote() + "',"
                + "'" + bean.getPackingListNo() + "'"
                + ")";
        try {
            return DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }

    }

    public void updateEmrir(EmrirBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        String sql = "Update emrir Set "
                + " edn_id=" + bean.getEdnId()
                + ", emrir_number='" + bean.getEmrirNumber() + "'"
                + ", note='" + bean.getNote() + "'"
                + ", packing_list_no='" + bean.getPackingListNo() + "'"
                + " Where emrir_id=" + bean.getEmrirId();
        try {
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public int deleteEmrir(int emrirId) throws Exception {
        String sql = "Delete From emrir Where emrir_id = " + emrirId;
        try {
            return DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public void updateEdeliveryNotice(int ednId, int status) throws Exception {



        try {


            String sql = "";

            sql = "Update edelivery_notice Set status = " + status
                    + " Where edn_id = " + ednId;

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

    public ArrayList searchSimpleEmrir(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "emrir_number";
                break;
            case 2:
                strFieldname = "edn_number";
                break;
        }
        ResultSet rs = null;

        String sql = "Select emrir.*, edn_number From emrir left join edelivery_notice on emrir.edn_id = edelivery_notice.edn_id Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by emrir_id DESC";

        ArrayList emrirList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EmrirBean emrir = null;
            while (rs.next()) {
                emrir = new EmrirBean();
                emrir.setEmrirId(rs.getInt("emrir_id"));
                emrir.setEdnId(rs.getInt("edn_id"));
                emrir.setEdnNumber(rs.getString("edn_number"));
                emrir.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                emrir.setEmrirNumber(rs.getString("emrir_number"));
                emrir.setNote(StringUtil.getString(rs.getString("note")));
                emrirList.add(emrir);
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
        return emrirList;
    }

    public ArrayList searchAdvEmrir(EmrirBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select emrir.*, edn_number From emrir left join edelivery_notice on emrir.edn_id = edelivery_notice.edn_id Where 1";

        if (!StringUtil.isBlankOrNull(bean.getEmrirNumber())) {
            sql = sql + " AND emrir_number LIKE '%" + bean.getEmrirNumber() + "%'";
        }

        if (bean.getEdnId() != 0) {
            sql = sql + " AND emrir.edn_id = " + bean.getEdnId();
        }

        if (!StringUtil.isBlankOrNull(bean.getSearchStartDate()) && !StringUtil.isBlankOrNull(bean.getSearchEndDate())) {
            sql = sql + " AND created_date >= STR_TO_DATE('" + bean.getSearchStartDate() + "','%d/%m/%Y') AND created_date <= STR_TO_DATE('" + bean.getSearchEndDate() + "','%d/%m/%Y')";
        }


        sql = sql + " Order by emrir_id DESC";

        ArrayList emrirList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EmrirBean emrir = null;

            while (rs.next()) {
                emrir = new EmrirBean();
                emrir.setEmrirId(rs.getInt("emrir_id"));
                emrir.setEdnId(rs.getInt("edn_id"));
                emrir.setEdnNumber(rs.getString("edn_number"));
                emrir.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                emrir.setEmrirNumber(rs.getString("emrir_number"));
                emrir.setNote(StringUtil.getString(rs.getString("note")));
                emrirList.add(emrir);
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
        return emrirList;
    }

    public ArrayList getEmrirDetails()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select md.*, m.name_vn"
                + " From emrir_detail as md"
                + " left join ematerial as m on md.emat_id = m.emat_id"
                + " Order by det_id ASC";

        ArrayList arrMrir = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EmrirDetailBean mrirBean = null;
            while (rs.next()) {
                mrirBean = new EmrirDetailBean();
                mrirBean.setDetId(rs.getString("md.det_id"));
                mrirBean.setEmrirId(rs.getInt("md.emrir_id"));
                mrirBean.setEmatId(rs.getInt("md.emat_id"));
                mrirBean.setMatName(StringUtil.decodeString(rs.getString("m.name_vn")));
                mrirBean.setUnit(rs.getString("md.unit"));
                mrirBean.setQuantity(rs.getDouble("md.quantity"));
                mrirBean.setPrice(rs.getDouble("md.price"));
                mrirBean.setStatus(rs.getInt("md.status"));
                mrirBean.setHeatSerial(StringUtil.getString(rs.getString("md.heat_serial")));
                mrirBean.setMaterialGrade(StringUtil.getString(rs.getString("md.material_grade")));
                mrirBean.setMaterialType(StringUtil.getString(rs.getString("md.material_type")));
                mrirBean.setRating(StringUtil.getString(rs.getString("md.rating")));
                mrirBean.setSize1(StringUtil.getString(rs.getString("md.size1")));
                mrirBean.setSize2(StringUtil.getString(rs.getString("md.size2")));
                mrirBean.setSize3(StringUtil.getString(rs.getString("md.size3")));
                mrirBean.setTraceNo(StringUtil.getString(rs.getString("md.trace_no")));
                mrirBean.setCertNo(StringUtil.getString(rs.getString("md.cert_no")));
                mrirBean.setColourCode(StringUtil.getString(rs.getString("md.colour_code")));

                arrMrir.add(mrirBean);
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
        return arrMrir;
    }

    public ArrayList getEmrirDetailsByEmrir(int emrirId) throws Exception {
        ResultSet rs = null;

        String sql = "Select md.*, m.name_vn"
                + " From emrir_detail as md"
                + " left join ematerial as m on md.emat_id = m.emat_id"
                + " Where md.emrir_id = " + emrirId;

        ArrayList arrEmrir = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EmrirDetailBean emrirBean = null;
            while (rs.next()) {
                emrirBean = new EmrirDetailBean();
                emrirBean.setDetId(rs.getString("md.det_id"));
                emrirBean.setEmrirId(rs.getInt("md.emrir_id"));
                emrirBean.setEmatId(rs.getInt("md.emat_id"));
                emrirBean.setMatName(StringUtil.decodeString(rs.getString("m.name_vn")));
                emrirBean.setUnit(rs.getString("md.unit"));
                emrirBean.setQuantity(rs.getDouble("md.quantity"));
                emrirBean.setPrice(rs.getDouble("md.price"));
                emrirBean.setStatus(rs.getInt("md.status"));
                emrirBean.setHeatSerial(StringUtil.getString(rs.getString("md.heat_serial")));
                emrirBean.setMaterialGrade(StringUtil.getString(rs.getString("md.material_grade")));
                emrirBean.setMaterialType(StringUtil.getString(rs.getString("md.material_type")));
                emrirBean.setRating(StringUtil.getString(rs.getString("md.rating")));
                emrirBean.setSize1(StringUtil.getString(rs.getString("md.size1")));
                emrirBean.setSize2(StringUtil.getString(rs.getString("md.size2")));
                emrirBean.setSize3(StringUtil.getString(rs.getString("md.size3")));
                emrirBean.setTraceNo(StringUtil.getString(rs.getString("md.trace_no")));
                emrirBean.setCertNo(StringUtil.getString(rs.getString("md.cert_no")));
                emrirBean.setColourCode(StringUtil.getString(rs.getString("md.colour_code")));
                emrirBean.setSystemNo(StringUtil.getString(rs.getString("md.system_no")));
                emrirBean.setItemNo(StringUtil.getString(rs.getString("md.item_no")));
                emrirBean.setLocation(StringUtil.getString(rs.getString("md.location")));
                emrirBean.setComment(StringUtil.getString(rs.getString("md.comment")));
                emrirBean.setMatKind(rs.getInt("md.mk_id"));
                arrEmrir.add(emrirBean);
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
        return arrEmrir;
    }

    public EmrirDetailBean getEmrirDetail(int emrirId) throws Exception {
        ResultSet rs = null;

        String sql = "Select md.*"
                + " From emrir_detail as md"
                + " Where emrir_id=" + emrirId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EmrirDetailBean emrirBean = new EmrirDetailBean();
                emrirBean.setDetId(rs.getString("md.det_id"));
                emrirBean.setEmrirId(rs.getInt("md.emrir_id"));
                emrirBean.setEmatId(rs.getInt("md.emat_id"));
                emrirBean.setUnit(rs.getString("md.unit"));
                emrirBean.setQuantity(rs.getDouble("md.quantity"));
                emrirBean.setPrice(rs.getDouble("md.price"));
                emrirBean.setStatus(rs.getInt("md.status"));
                emrirBean.setHeatSerial(StringUtil.getString(rs.getString("md.heat_serial")));
                emrirBean.setMaterialGrade(StringUtil.getString(rs.getString("md.material_grade")));
                emrirBean.setMaterialType(StringUtil.getString(rs.getString("md.material_type")));
                emrirBean.setRating(StringUtil.getString(rs.getString("md.rating")));
                emrirBean.setSize1(StringUtil.getString(rs.getString("md.size1")));
                emrirBean.setSize2(StringUtil.getString(rs.getString("md.size2")));
                emrirBean.setSize3(StringUtil.getString(rs.getString("md.size3")));
                emrirBean.setTraceNo(StringUtil.getString(rs.getString("md.trace_no")));
                emrirBean.setCertNo(StringUtil.getString(rs.getString("md.cert_no")));
                emrirBean.setColourCode(StringUtil.getString(rs.getString("md.colour_code")));
                emrirBean.setSystemNo(StringUtil.getString(rs.getString("md.system_no")));
                emrirBean.setItemNo(StringUtil.getString(rs.getString("md.item_no")));
                emrirBean.setLocation(StringUtil.getString(rs.getString("md.location")));
                emrirBean.setComment(StringUtil.getString(rs.getString("md.comment")));
                emrirBean.setMatKind(rs.getInt("md.mk_id"));

                return emrirBean;
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

    public int insertEmrirDetail(EmrirDetailBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        String sql = "";

        if (GenericValidator.isBlankOrNull(bean.getMaterialGrade())) {
            bean.setMaterialGrade("");
        }
        if (GenericValidator.isBlankOrNull(bean.getHeatSerial())) {
            bean.setHeatSerial("");
        }
        if (GenericValidator.isBlankOrNull(bean.getMaterialType())) {
            bean.setMaterialType("");
        }
        if (GenericValidator.isBlankOrNull(bean.getSize1())) {
            bean.setSize1("");
        }
        if (GenericValidator.isBlankOrNull(bean.getSize2())) {
            bean.setSize2("");
        }
        if (GenericValidator.isBlankOrNull(bean.getSize3())) {
            bean.setSize3("");
        }
        if (GenericValidator.isBlankOrNull(bean.getTraceNo())) {
            bean.setTraceNo("");
        }
        if (GenericValidator.isBlankOrNull(bean.getCertNo())) {
            bean.setCertNo("");
        }
        if (GenericValidator.isBlankOrNull(bean.getColourCode())) {
            bean.setColourCode("");
        }
        if (GenericValidator.isBlankOrNull(bean.getLocation())) {
            bean.setLocation("");
        }
        if (GenericValidator.isBlankOrNull(bean.getSystemNo())) {
            bean.setSystemNo("");
        }
        if (GenericValidator.isBlankOrNull(bean.getItemNo())) {
            bean.setItemNo("");
        }
        if (GenericValidator.isBlankOrNull(bean.getComment())) {
            bean.setComment("");
        }
        String mkId = "null";
        if (bean.getMatKind() > 0) {
            mkId = "" + bean.getMatKind();
        }

        sql = "Insert Into emrir_detail("
                + "emrir_id, "
                + "emat_id, "
                + "unit, "
                + "quantity, "
                + "price, "
                + "heat_serial, "
                + "material_grade,"
                + "material_type,"
                + "rating,"
                + "size1,"
                + "size2,"
                + "size3,"
                + "trace_no,"
                + "cert_no,"
                + "system_no,"
                + "item_no,"
                + "comment,"
                + "location,"
                + "mk_id,"
                + "colour_code)"
                + " Values (" + bean.getEmrirId()
                + "," + bean.getEmatId()
                + ",'" + bean.getUnit() + "'"
                + "," + bean.getQuantity()
                + "," + bean.getPrice()
                + ",'" + bean.getHeatSerial() + "'"
                + ",'" + bean.getMaterialGrade() + "'"
                + ",'" + bean.getMaterialType() + "'"
                + ",'" + bean.getRating() + "'"
                + ",'" + bean.getSize1() + "'"
                + ",'" + bean.getSize2() + "'"
                + ",'" + bean.getSize3() + "'"
                + ",'" + bean.getTraceNo() + "'"
                + ",'" + bean.getCertNo() + "'"
                + ",'" + bean.getSystemNo() + "'"
                + ",'" + bean.getItemNo() + "'"
                + ",'" + bean.getComment() + "'"
                + ",'" + bean.getLocation() + "'"
                + "," + mkId
                + ",'" + bean.getColourCode() + "'" + ")";
        try {
            //System.out.println("sql ====" + sql);
            return DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public void updateEmrirDetail(EmrirDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        String sql = "Update emrir_detail Set "
                + "quantity=" + bean.getQuantity()
                + ", location='" + bean.getLocation() + "'"
                + ", heat_serial='" + bean.getHeatSerial() + "'"
                + ", material_grade='" + bean.getMaterialGrade() + "'"
                + ", material_type='" + bean.getMaterialType() + "'"
                + ", rating='" + bean.getRating() + "'"
                + ", size1='" + bean.getSize1() + "'"
                + ", size2='" + bean.getSize2() + "'"
                + ", size3='" + bean.getSize3() + "'"
                + ", trace_no='" + bean.getTraceNo() + "'"
                + ", cert_no='" + bean.getCertNo() + "'"
                + ", system_no='" + bean.getSystemNo() + "'"
                + ", item_no='" + bean.getItemNo() + "'"
                + ", comment='" + bean.getComment() + "'"
                + ", colour_code='" + bean.getColourCode() + "'";
        if (bean.getMatKind() > 0) {
            sql += ", mk_id=" + bean.getMatKind();
        }
        sql += " where det_id=" + bean.getDetId();
        try {
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public int deleteEmrirDetail(int detId) throws Exception {
        String sql = "Delete From emrir_detail Where det_id = " + detId;
        try {
            return DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public ArrayList getEosDs()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select eos_d.* From eos_d Order by eosd_id ASC";

        ArrayList eos_dList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EosDBean eos_d = null;
            while (rs.next()) {
                eos_d = new EosDBean();
                eos_d.setEosdId(rs.getInt("eosd_id"));
                eos_d.setEmrirId(rs.getInt("emrir_id"));
                eos_d.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                eos_d.setCreatedEmpId(rs.getInt("created_emp"));
                eos_d.setEosdNumber(rs.getString("eosd_number"));
                eos_d.setNonConform(rs.getString("non_conform"));
                eos_d.setDescription(rs.getString("description"));
                eos_d.setCorrectAct(rs.getString("correct_act"));
                eos_d.setActionBy(rs.getInt("action_by"));
                try {
                    eos_d.setDueDate(DateUtil.formatDate(rs.getDate("due_date"), "dd/MM/yyyy"));
                } catch (Exception ex) {
                    eos_d.setDueDate("");
                }
                eos_d.setClosed(rs.getInt("closed"));
                try {
                    eos_d.setClosedDate(DateUtil.formatDate(rs.getDate("closed_date"), "dd/MM/yyyy"));
                } catch (Exception ex) {
                    eos_d.setClosedDate("");
                }
                eos_d.setNote(StringUtil.getString(rs.getString("note")));
                eos_dList.add(eos_d);
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
        return eos_dList;
    }

    public EosDBean getEosD(int eosdId) throws Exception {
        ResultSet rs = null;

        String sql = "select osd.*,e.fullname,e1.fullname"
                + " from eos_d as osd"
                + " left join employee as e on e.emp_id=osd.created_emp"
                + " left join employee as e1 on e1.emp_id=osd.action_by"
                + " where osd.eosd_id = " + eosdId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EosDBean eosdBean = new EosDBean();
                eosdBean.setEosdId(rs.getInt("eosd_id"));
                eosdBean.setEmrirId(rs.getInt("emrir_id"));
                eosdBean.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                eosdBean.setCreatedEmpId(rs.getInt("created_emp"));
                eosdBean.setCreatedEmpName(rs.getString("e.fullname"));
                eosdBean.setEosdNumber(rs.getString("eosd_number"));
                eosdBean.setNonConform(rs.getString("non_conform"));
                eosdBean.setDescription(rs.getString("description"));
                eosdBean.setCorrectAct(rs.getString("correct_act"));
                eosdBean.setActionBy(rs.getInt("action_by"));
                eosdBean.setActionByName(rs.getString("e1.fullname"));
                eosdBean.setDueDate(DateUtil.formatDate(rs.getDate("due_date"), "dd/MM/yyyy"));
                eosdBean.setClosed(rs.getInt("closed"));
                eosdBean.setClosedDate(DateUtil.formatDate(rs.getDate("closed_date"), "dd/MM/yyyy"));
                eosdBean.setNote(StringUtil.getString(rs.getString("note")));

                return eosdBean;
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

    public EosDBean getEosDByNumber(String eosdNumber) throws Exception {
        ResultSet rs = null;

        String sql = "Select eosd_id From eos_d Where eosd_number = '" + eosdNumber + "'";


        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EosDBean eosd = new EosDBean();
                eosd.setEosdId(rs.getInt("eosd_id"));

                return eosd;
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

    public EosDBean getEosDByEmrir(int emrirId) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From eos_d Where emrir_id=" + emrirId;

        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EosDBean eos_d = new EosDBean();
                eos_d.setEosdId(rs.getInt("eosd_id"));
                eos_d.setEmrirId(rs.getInt("emrir_id"));
                eos_d.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                eos_d.setCreatedEmpId(rs.getInt("created_emp"));
                eos_d.setEosdNumber(rs.getString("eosd_number"));
                eos_d.setNonConform(rs.getString("non_conform"));
                eos_d.setDescription(rs.getString("description"));
                eos_d.setCorrectAct(rs.getString("correct_act"));
                eos_d.setActionBy(rs.getInt("action_by"));
                eos_d.setDueDate(DateUtil.formatDate(rs.getDate("due_date"), "dd/MM/yyyy"));
                eos_d.setClosed(rs.getInt("closed"));
                eos_d.setClosedDate(DateUtil.formatDate(rs.getDate("closed_date"), "dd/MM/yyyy"));
                eos_d.setNote(StringUtil.getString(rs.getString("note")));

                return eos_d;
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

    public int insertEosD(EosDBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        String sql = "";
        String closed_date = "null";
        if (!GenericValidator.isBlankOrNull(bean.getClosedDate())) {
            closed_date = "STR_TO_DATE('" + bean.getClosedDate() + "','%d/%m/%Y')";
        }

        String due_date = "null";
        if (!GenericValidator.isBlankOrNull(bean.getDueDate())) {
            due_date = "STR_TO_DATE('" + bean.getDueDate() + "','%d/%m/%Y')";
        }

        sql = "Insert Into eos_d("
                + "created_date, "
                + "eosd_number, "
                + "emrir_id, "
                + "created_emp, "
                + "non_conform, "
                + "description, "
                + "correct_act, "
                + "action_by, "
                + "due_date, "
                + "closed, "
                + "closed_date, "
                + "note)"
                + " Values ("
                + "sysdate(),"
                + "'" + bean.getEosdNumber() + "',"
                + bean.getEmrirId() + ","
                + bean.getCreatedEmpId() + ",'"
                + bean.getNonConform()
                + "','" + bean.getDescription() + "','"
                + bean.getCorrectAct() + "',"
                + bean.getActionBy() + ","
                + due_date + ","
                + bean.getClosed() + ","
                + closed_date + ","
                + "'" + bean.getNote() + "'"
                + ")";
        try {
            return DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateEosD(EosDBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        String sql = "Update eos_d Set "
                + " eosd_number='" + bean.getEosdNumber() + "'"
                + ", non_conform='" + bean.getNonConform() + "'"
                + ", description='" + bean.getDescription() + "'"
                + ", correct_act='" + bean.getCorrectAct() + "'";
        if (bean.getActionBy() > 0) {
            sql += ", action_by=" + bean.getActionBy();
        }
        if (!GenericValidator.isBlankOrNull(bean.getClosedDate())) {
            sql += ", closed_date=STR_TO_DATE('" + bean.getClosedDate() + "','%d/%m/%Y')";
        }
        if (!GenericValidator.isBlankOrNull(bean.getDueDate())) {
            sql += ", due_date=STR_TO_DATE('" + bean.getDueDate() + "','%d/%m/%Y')";
        }
        sql += ", closed=" + bean.getClosed()
                + ", note='" + bean.getNote() + "'"
                + " Where eosd_id=" + bean.getEosdId();
        try {
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public int deleteEosD(int eosdId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From eos_d Where eosd_id=" + eosdId;
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

    public ArrayList getEosDDetails()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select eos_d_detail.* From eos_d_detail Order by det_id ASC";

        ArrayList eos_d_detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EosDDetailBean eos_d_detail = null;
            while (rs.next()) {
                eos_d_detail = new EosDDetailBean();
                eos_d_detail.setDetId(rs.getString("det_id"));
                eos_d_detail.setEosdId(rs.getInt("eosd_id"));
                eos_d_detail.setEmatId(rs.getInt("emat_id"));
                eos_d_detail.setUnit(rs.getString("unit"));
                eos_d_detail.setQuantity(rs.getDouble("quantity"));
                eos_d_detailList.add(eos_d_detail);
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
        return eos_d_detailList;
    }

    public ArrayList getEosDDetailsByEosD(int eosdId)
            throws Exception {
        ResultSet rs = null;

        String sql = "Select eos_d_detail.*, name_vn"
                + " from eos_d_detail"
                + " inner join ematerial on eos_d_detail.emat_id = ematerial.emat_id"
                + " where eosd_id=" + eosdId;

        ArrayList arrRes = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EosDDetailBean eosDBean = null;
            while (rs.next()) {
                eosDBean = new EosDDetailBean();
                eosDBean.setDetId(rs.getString("det_id"));
                eosDBean.setEosdId(rs.getInt("eosd_id"));
                eosDBean.setEmatId(rs.getInt("emat_id"));
                eosDBean.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
                eosDBean.setUnit(rs.getString("unit"));
                eosDBean.setQuantity(rs.getDouble("quantity"));
                arrRes.add(eosDBean);
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
        return arrRes;
    }

    public EosDDetailBean getEosDDetail(int eosdId) throws Exception {
        ResultSet rs = null;

        String sql = "Select eos_d_detail.* From eos_d_detail Where eosd_id = " + eosdId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EosDDetailBean bean = new EosDDetailBean();
                bean.setDetId(rs.getString("det_id"));
                bean.setEosdId(rs.getInt("eosd_id"));
                bean.setEmatId(rs.getInt("emat_id"));
                bean.setUnit(rs.getString("unit"));
                bean.setQuantity(rs.getDouble("quantity"));

                return bean;
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

    public int insertEosDDetail(EosDDetailBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        String sql = "Insert Into eos_d_detail(eosd_id, emat_id, unit, quantity)"
                + " Values (" + bean.getEosdId() + "," + bean.getEmatId() + ",'" + bean.getUnit() + "'," + bean.getQuantity() + ")";
        try {
            return DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public void updateEosDDetail(EosDDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        String sql = "Update eos_d_detail Set "
                + " unit='" + bean.getUnit() + "'"
                + ", quantity=" + bean.getQuantity()
                + " Where det_id = " + bean.getDetId();
        try {
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public int deleteEosDDetail(int detId) throws Exception {
        String sql = "Delete From eos_d_detail Where det_id = " + detId;
        try {
            return DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public ArrayList getEmrir4Msv() throws Exception {
        ArrayList arrEmrir = new ArrayList();
        ResultSet rs = null;
        try {
            String sql = "select emrir_id,emrir_number from emrir"
                    + " where status=0 and edn_id>0 and edn_id is not null";

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                LabelValueBean bean = new LabelValueBean();
                bean.setLabel(rs.getString("emrir_number"));
                bean.setValue(rs.getString("emrir_id"));
                arrEmrir.add(bean);
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return arrEmrir;
    }

    public ArrayList getEmrir4Mrv() throws Exception {
        ArrayList arrEmrir = new ArrayList();
        ResultSet rs = null;
        try {
            String sql = "select emrir_id,emrir_number from emrir"
                    + " where status=0 and (edn_id=0 or edn_id is null)";

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                LabelValueBean bean = new LabelValueBean();
                bean.setLabel(rs.getString("emrir_number"));
                bean.setValue(rs.getString("emrir_id"));
                arrEmrir.add(bean);
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return arrEmrir;

    }

    public void updateEmrirStatus(int emrirId, int status) throws Exception {
        if (emrirId == 0) {
            return;
        }



        try {


            String sql = "";
            sql = "Update emrir Set "
                    + " status=" + status
                    + " Where emrir_id=" + emrirId;

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

    public EmrirDetailBean getEmrirMaterial(int detId) throws Exception {
        if (detId == 0) {
            return null;
        }

        ResultSet rs = null;

        String sql = "select md.*, m.name_vn,m.code"
                + " from emrir_detail as md"
                + " left join ematerial as m on md.emat_id = m.emat_id"
                + " where md.det_id=" + detId;



        try {


            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EmrirDetailBean mrirDetail = new EmrirDetailBean();
                mrirDetail.setDetId(rs.getString("md.det_id"));
                mrirDetail.setEmrirId(rs.getInt("md.emrir_id"));
                mrirDetail.setEmatId(rs.getInt("md.emat_id"));
                mrirDetail.setMatName(StringUtil.decodeString(rs.getString("m.name_vn")));
                mrirDetail.setMatCode(rs.getString("m.code"));
                mrirDetail.setUnit(rs.getString("md.unit"));
                mrirDetail.setQuantity(rs.getDouble("md.quantity"));
                mrirDetail.setPrice(rs.getDouble("md.price"));
                mrirDetail.setHeatSerial(rs.getString("md.heat_serial"));
                mrirDetail.setStatus(rs.getInt("md.status"));
                mrirDetail.setMaterialGrade(StringUtil.getString(rs.getString("md.material_grade")));
                mrirDetail.setMaterialType(StringUtil.getString(rs.getString("md.material_type")));
                mrirDetail.setRating(StringUtil.getString(rs.getString("md.rating")));
                mrirDetail.setSize1(StringUtil.getString(rs.getString("md.size1")));
                mrirDetail.setSize2(StringUtil.getString(rs.getString("md.size2")));
                mrirDetail.setSize3(StringUtil.getString(rs.getString("md.size3")));
                mrirDetail.setTraceNo(StringUtil.getString(rs.getString("md.trace_no")));
                mrirDetail.setCertNo(StringUtil.getString(rs.getString("md.cert_no")));
                mrirDetail.setColourCode(StringUtil.getString(rs.getString("md.colour_code")));
                mrirDetail.setSystemNo(StringUtil.getString(rs.getString("md.system_no")));
                mrirDetail.setItemNo(StringUtil.getString(rs.getString("md.item_no")));
                mrirDetail.setLocation(StringUtil.getString(rs.getString("md.location")));
                mrirDetail.setComment(StringUtil.getString(rs.getString("md.comment")));
                mrirDetail.setMatKind(rs.getInt("md.mk_id"));

                return mrirDetail;
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

    public EosDDetailBean getEosDMaterial(int detId) throws Exception {
        if (detId == 0) {
            return null;
        }
        ResultSet rs = null;

        String sql = "select md.*, m.name_vn,m.code"
                + " from emrir_detail as md"
                + " left join ematerial as m on md.mat_id = m.mat_id"
                + " where md.det_id=" + detId;



        try {


            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EosDDetailBean bean = new EosDDetailBean();
                bean.setDetId(rs.getString("md.det_id"));
                bean.setEmatId(rs.getInt("md.emat_id"));
                bean.setMatName(StringUtil.decodeString(rs.getString("m.name_vn")));
                bean.setMatCode(rs.getString("m.code"));
                bean.setUnit(rs.getString("md.unit"));
                bean.setQuantity(rs.getDouble("md.quantity"));

                return bean;
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

    public ArrayList getEmrirEosDs(int emrirId)
            throws Exception {
        ResultSet rs = null;

        String sql = "Select md.*, m.name_vn"
                + " From emrir_detail as md"
                + " left join ematerial as m on md.emat_id = m.emat_id"
                + " Where md.emrir_id = " + emrirId;

//        String sql = "Select mrir.mrir_id, det_id, mrir_detail.mat_id, name_vn ,unit, quantity, manufacture, heat_serial," +
//                " inspection, original, quality, warranty, insurance, approval_type, compl_cert, os_d.osd_id, osd_number," +
//                " closed_date, closed, material_type,material_grade,rating,size1,size2,size3,trace_no,cert_no,colour_code " +
//                "From ((mrir left join mrir_detail on mrir.mrir_id = mrir_detail.mrir_id)" +
//                " left join os_d on mrir.mrir_id = os_d.mrir_id)" +
//                " left join material on mrir_detail.mat_id = material.mat_id " +
//                "Where mrir.mrir_id = " + emrirId;

        ArrayList mrirList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EmrirEosDBean emrir = null;
            while (rs.next()) {
                emrir = new EmrirEosDBean();
                emrir.setDetId(rs.getInt("det_id"));
                emrir.setEmrirId(rs.getInt("emrir_id"));
                emrir.setEmatId(rs.getInt("emat_id"));
                emrir.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
                emrir.setUnit(rs.getString("unit"));
                emrir.setQuantity(rs.getInt("quantity"));
                emrir.setHeatSerial(rs.getString("heat_serial"));
                emrir.setMaterialGrade(StringUtil.getString(rs.getString("material_grade")));
                emrir.setMaterialType(StringUtil.getString(rs.getString("material_type")));
                emrir.setRating(StringUtil.getString(rs.getString("rating")));
                emrir.setSize1(StringUtil.getString(rs.getString("size1")));
                emrir.setSize2(StringUtil.getString(rs.getString("size2")));
                emrir.setSize3(StringUtil.getString(rs.getString("size3")));
                emrir.setTraceNo(StringUtil.getString(rs.getString("trace_no")));
                emrir.setCertNo(StringUtil.getString(rs.getString("cert_no")));
                emrir.setColourCode(StringUtil.getString(rs.getString("colour_code")));
                emrir.setSystemNo(StringUtil.getString(rs.getString("md.system_no")));
                emrir.setItemNo(StringUtil.getString(rs.getString("md.item_no")));
                emrir.setLocation(StringUtil.getString(rs.getString("md.location")));
                emrir.setComment(StringUtil.getString(rs.getString("md.comment")));
                emrir.setMatKind(rs.getInt("md.mk_id"));
//                emrir.setEosdId(rs.getInt("eos_d.osd_id"));
//                if (emrir.getEosdId() > 0) {
//                    emrir.setEosdNumber(rs.getString("osd_number"));
//                    emrir.setClosed(rs.getInt("closed"));
//                    emrir.setClosedDate(DateUtil.formatDate(rs.getDate("closed_date"), "dd/MM/yyyy"));
//                    if (rs.getInt("closed") == 0) {
//                        emrir.setConclude(MCUtil.getBundleString("message.osd.closed1"));
//                    } else if (rs.getInt("closed") == 1) {
//                        emrir.setConclude(MCUtil.getBundleString("message.osd.closed2"));
//                    }
//                }
                mrirList.add(emrir);
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
        return mrirList;
    }
}
