/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.EdmvBean;
import com.venus.mc.bean.EdmvMaterialBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.process.store.edmv.EdmvFormBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author thcao
 */
public class EdmvDAO extends BasicDAO {

    public ArrayList getEdmvList() throws Exception {
        ArrayList arrRes = new ArrayList();
        ResultSet rs = null;
        try {
            String sql = "select dmv.*,e.fullname,s.name,o.name"
                    + " from edmv as dmv"
                    + " left join employee as e on dmv.receive_emp=e.emp_id"
                    + " left join organization as o on o.org_id=dmv.org_id"
                    + //                    " left join mrir as r on r.mrir_id=m.mrir_id" +
                    //                    " left join contract as c on r.con_id=c.con_id" +
                    //" left join vendor as v on c.ven_id=v.ven_id" +
                    " left join store as  s on dmv.sto_id=s.sto_id"
                    + //" where kind=2 " + //1: cong cu dung cu - 2: khac
                    " order by created_date desc";
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EdmvBean bean = new EdmvBean();
                bean.setEdmvId(rs.getInt("dmv.edmv_id"));
                bean.setCreatedDate(DateUtil.formatDate(rs.getDate("dmv.created_date"), "dd/MM/yyyy"));
                bean.setEdmvNumber(rs.getString("dmv.edmv_number"));
                bean.setDmvOrder(rs.getString("dmv.dmv_order"));
                //bean.setMrirId(rs.getInt("m.mrir_id"));
                //bean.setMrirNumber(rs.getString("r.mrir_number"));
                //bean.setCreator(rs.getInt("dmv.creator"));
                //bean.setCreatorName(rs.getString("e.fullname"));
                //bean.setStoId(rs.getInt("m.sto_id"));                
                bean.setReceiveEmpId(rs.getInt("dmv.receive_emp"));
                bean.setReceiveEmpName(rs.getString("e.fullname"));
                bean.setStoName(rs.getString("dmv.sto_id"));
                bean.setStoName(rs.getString("s.name"));
                bean.setOrgId(rs.getInt("dmv.org_id"));
                bean.setOrgName(rs.getString("o.name"));
                //bean.setDescription(rs.getString("dmv.description"));
                //bean.setType(rs.getInt("dmv.type"));
                //bean.setTotal(rs.getDouble("dmv.total"));
                //bean.setConId(rs.getInt("r.con_id"));
                //bean.setConNumber(rs.getString("c.contract_number"));
                //bean.setDeliverer(rs.getString("m.deliverer"));
                arrRes.add(bean);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MsvDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return arrRes;
    }

    public EdmvBean getEdmv(int edmvId) throws Exception {
        if (edmvId == 0) {
            return null;
        }
        ResultSet rs = null;
        try {
            String sql = "select dmv.*,ce.fullname,re.fullname,s.name,o.name,dn.pro_id,dn.econ_number,p.name,"
                    + " emrir.emrir_number,emsv.emsv_number,erfm.erfm_number,emiv.emiv_number"
                    + " from edmv as dmv"
                    + " left join employee as ce on dmv.created_emp=ce.emp_id"
                    + " left join employee as re on dmv.receive_emp=re.emp_id"
                    + " left join organization as o on o.org_id=dmv.org_id"
                    + " left join edelivery_notice as dn on dn.edn_id=dmv.edn_id"
                    + " left join organization as p on p.org_id=dn.pro_id"
                    + " left join store as  s on dmv.sto_id=s.sto_id"
                    + " left join emrir as emrir on emrir.emrir_id=dmv.emrir_id"
                    + " left join emsv as emsv on emsv.emsv_id=dmv.emsv_id"
                    + " left join erfm as erfm on erfm.erfm_id=dmv.erfm_id"
                    + " left join emiv as emiv on emiv.emiv_id=dmv.emiv_id"
                    + //" where kind=2 " + //1: cong cu dung cu - 2: khac
                    " Where edmv_id=" + edmvId;

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EdmvBean bean = new EdmvBean();
                bean.setEdmvId(rs.getInt("dmv.edmv_id"));
                bean.setCreatedDate(DateUtil.formatDate(rs.getDate("dmv.created_date"), "dd/MM/yyyy"));
                bean.setCreatedEmpId(rs.getInt("dmv.created_emp"));
                bean.setCreatedEmpName(rs.getString("ce.fullname"));
                bean.setEdmvNumber(rs.getString("dmv.edmv_number"));
                bean.setDmvOrder(rs.getString("dmv.dmv_order"));
                bean.setContract(rs.getString("dn.econ_number"));
                bean.setVendor(StringUtil.decodeString(rs.getString("dmv.vendor")));
                //bean.setMrirId(rs.getInt("m.mrir_id"));
                //bean.setMrirNumber(rs.getString("r.mrir_number"));                
                bean.setReceiveEmpId(rs.getInt("dmv.receive_emp"));
                bean.setReceiveEmpName(rs.getString("re.fullname"));
                bean.setStoId(rs.getInt("dmv.sto_id"));
                bean.setStoName(rs.getString("s.name"));
                bean.setProId(rs.getInt("dn.pro_id"));
                bean.setProName(rs.getString("p.name"));
                bean.setOrgId(rs.getInt("dmv.org_id"));
                bean.setOrgName(rs.getString("o.name"));
                bean.setDescription(rs.getString("dmv.description"));
                bean.setKind(rs.getInt("dmv.kind"));
                bean.setEdnId(rs.getInt("dmv.edn_id"));
                bean.setDeliverer(rs.getString("dmv.deliverer"));
                bean.setTotal(rs.getDouble("dmv.total"));
                bean.setEmrirId(rs.getInt("dmv.emrir_id"));
                bean.setEmrirNumber(rs.getString("emrir.emrir_number"));
                bean.setEmsvId(rs.getInt("dmv.emsv_id"));
                bean.setEmsvNumber(rs.getString("emsv.emsv_number"));
                bean.setErfmId(rs.getInt("dmv.erfm_id"));
                bean.setErfmNumber(rs.getString("erfm.erfm_number"));
                bean.setEmivId(rs.getInt("dmv.emiv_id"));
                bean.setEmivNumber(rs.getString("emiv.emiv_number"));
                bean.setDeliverer(rs.getString("dmv.deliverer"));

                return bean;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MsvDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return null;
    }

    public int insertEdmv(EdmvBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        if (GenericValidator.isBlankOrNull(bean.getEdmvNumber())
                || bean.getCreatedEmpId() == 0) {
            return 0;
        }
        if (GenericValidator.isBlankOrNull(bean.getDescription())) {
            bean.setDescription("");
        }
        if (GenericValidator.isBlankOrNull(bean.getDmvOrder())) {
            bean.setDmvOrder("");
        }
        if (GenericValidator.isBlankOrNull(bean.getDeliverer())) {
            bean.setDeliverer("");
        }

        int result = 0;
        try {
            String sql = "";
            sql = "insert into edmv("
                    + "created_date,"
                    + "edmv_number, "
                    + "created_emp, "
                    + "deliverer, "
                    + "edn_id, "
                    + "org_id, "
                    + "sto_id, "
                    + "description, "
                    + "total, "
                    + "dmv_order,"
                    + "receive_emp,"
                    + "kind)"
                    + " Values ("
                    + "sysdate(),"
                    + "'" + bean.getEdmvNumber() + "',"
                    + bean.getCreatedEmpId() + ","
                    + "'" + bean.getDeliverer() + "',"
                    + bean.getEdnId() + ","
                    + bean.getOrgId() + ","
                    + bean.getStoId() + ","
                    + "'" + bean.getDescription() + "',"
                    + bean.getTotal() + ","
                    + "'" + bean.getDmvOrder() + "',"
                    + +bean.getReceiveEmpId() + ","
                    + +bean.getKind()
                    + ")";
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int insertEdmvMaterial(EdmvMaterialBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        if (bean.getEdmvId() == 0
                || bean.getEmatId() == 0
                || bean.getQuantity() == 0) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "";
            sql = "insert into edmv_detail("
                    + "edmv_id, "
                    + "emat_id, "
                    + "unit, "
                    + "quantity, "
                    + "price, "
                    + "total)"
                    + " Values ("
                    + bean.getEdmvId() + ","
                    + bean.getEmatId() + ","
                    + "'" + bean.getUnit() + "',"
                    + bean.getQuantity() + ","
                    + bean.getPrice() + ","
                    + bean.getTotal()
                    + ")";
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList getEdnList()
            throws Exception {
        ResultSet rs = null;
        ArrayList dnList = new ArrayList();
        String sql = "Select edn_id,edn_number From edelivery_notice where status=0";
        try {
            rs = DBUtil.executeQuery(sql);
            LabelValueBean dn = null;
            while (rs.next()) {
                dn = new LabelValueBean();
                dn.setValue(rs.getString("edn_id"));
                dn.setLabel(rs.getString("edn_number"));
                dnList.add(dn);
            }


        } catch (SQLException ex) {
            Logger.getLogger(MsvDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return dnList;
    }

    public EdmvFormBean createNewEdmvFromEdn(int edn_id) throws Exception {
        ResultSet rs = null;
        String sql = "select d.*,p.name,s.name,s.sto_id"
                + " from edelivery_notice as d"
                + " left join organization p on d.pro_id=p.org_id"
                + " left join store s on s.org_id=d.pro_id"
                + " where d.edn_id=" + edn_id;

        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EdmvFormBean dn = new EdmvFormBean();
                //dn.setCreatedDate(DateUtil.formatDate(rs.getDate("created_date"), "dd/MM/yyyy"));
                dn.setEdnId(rs.getInt("d.edn_id"));
                //dn.setExpectedDate(DateUtil.formatDate(rs.getDate("expected_date"), "dd/MM/yyyy"));
                //dn.setDnNumber(rs.getString("dn_number"));
                dn.setContract(rs.getString("d.econ_number"));
                dn.setProId(rs.getInt("d.pro_id"));
                dn.setProName(rs.getString("p.name"));
                dn.setStoId(rs.getInt("s.sto_id"));
                dn.setStoName(rs.getString("s.name"));

                return dn;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MsvDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return null;
    }

    public ArrayList getEdnDetailForEdmv(int dn_id) throws Exception {
        ResultSet rs = null;
        String sql = "select d.*, m.name_vn, m.code,u.unit_vn,u.unit_en"
                + " from edelivery_notice_detail as d"
                + " left join ematerial as m on d.emat_id=m.emat_id"
                + //" left join delivery_notice as n on n.dn_id=d.dn_id" +
                //" left join contract AS c ON c.con_id=n.con_id" +
                //" left join delivery_notice AS r1 ON r1.dn_id = d.dn_id" +
                " left join unit as u on u.uni_id = m.uni_id"
                + " Where d.edn_id=" + dn_id;

        ArrayList detailList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            EdmvMaterialBean detail = null;
            while (rs.next()) {
                detail = new EdmvMaterialBean();
                detail.setDetId(rs.getInt("det_id"));
                detail.setEmatId(rs.getInt("emat_id"));
                detail.setMatCode(rs.getString("code"));
                detail.setMatName(StringUtil.decodeString(rs.getString("m.name_vn")));
                detail.setQuantity(rs.getDouble("quantity"));
                detail.setUnit(rs.getString("u.unit_vn"));
                detail.setPrice(rs.getDouble("price"));
                detailList.add(detail);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MsvDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return detailList;
    }

    public ArrayList searchSimple(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "m.edmv_number";
                break;
            case 2:
                strFieldname = "m.dmv_order";
                break;
            case 3:
                strFieldname = "s.name";
                break;
        }
        ArrayList arrRes = new ArrayList();
        ResultSet rs = null;
        try {
            String sql = "select m.*,"
                    //    + "r.mrir_number,"
                    + "d.econ_number,s.name"
                    + " from edmv as m"
                    + //" left join mrir as r on r.mrir_id=m.mrir_id" +
                    " left join edelivery_notice as d on m.edn_id=d.edn_id"
                    + //" left join vendor as v on c.ven_id=v.ven_id" +
                    " left join store as  s on m.sto_id=s.sto_id" //" where kind=2 " + //1: cong cu dung cu - 2: khac
                    ;
            sql += " where 1";
            if ((fieldid > 0) && (!GenericValidator.isBlankOrNull(strFieldvalue))) {
                sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
            }
            sql += " order by created_date desc";
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EdmvBean bean = new EdmvBean();
                bean.setEdmvId(rs.getInt("m.edmv_id"));
                bean.setCreatedDate(DateUtil.formatDate(rs.getDate("m.created_date"), "dd/MM/yyyy"));
                bean.setEdmvNumber(rs.getString("m.edmv_number"));
                bean.setCreatedEmpId(rs.getInt("m.created_emp"));
                bean.setStoId(rs.getInt("m.sto_id"));
                bean.setStoName(rs.getString("s.name"));
                bean.setContract(rs.getString("d.econ_number"));
                bean.setKind(rs.getInt("m.kind"));
                bean.setTotal(rs.getDouble("m.total"));
                bean.setDeliverer(rs.getString("m.deliverer"));
                arrRes.add(bean);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MsvDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return arrRes;
    }

    public ArrayList getMaterialsFromEdmv(int edmvId) throws Exception {
        ArrayList arrRes = new ArrayList();
        if (edmvId == 0) {
            return arrRes;
        }
        ResultSet rs = null;
        try {
            String sql = "select d.det_id,d.emat_id,d.quantity,d.price,d.total,d.unit,m.name_vn,m.code"
                    + " from edmv_detail as d"
                    + " left join ematerial as m on m.emat_id=d.emat_id"
                    + " where edmv_id=" + edmvId;
            rs = DBUtil.executeQuery(sql);
            int id = 1;
            while (rs.next()) {
                EdmvMaterialBean bean = new EdmvMaterialBean();
                bean.setDetId(rs.getInt("det_id"));
                bean.setEmatId(rs.getInt("emat_id"));
                bean.setMatName(StringUtil.decodeString(rs.getString("m.name_vn")));
                bean.setMatCode(rs.getString("m.code"));
                bean.setQuantity(rs.getDouble("d.quantity"));
                bean.setPrice(rs.getDouble("d.price"));
                bean.setTotal(bean.getQuantity() * bean.getPrice());
                bean.setUnit(rs.getString("d.unit"));
                arrRes.add(bean);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MsvDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return arrRes;
    }

    public int updateEdmv(EdmvBean bean) throws Exception {
        if (bean == null
                || bean.getEdmvId() == 0) {
            return 0;
        }

        if (GenericValidator.isBlankOrNull(bean.getDescription())) {
            bean.setDescription("");
        }
        if (GenericValidator.isBlankOrNull(bean.getDmvOrder())) {
            bean.setDmvOrder("");
        }
        if (GenericValidator.isBlankOrNull(bean.getDeliverer())) {
            bean.setDeliverer("");
        }

        int result = 0;
        try {
            String sql = "update edmv set ";
            sql += " edmv_number = '" + bean.getEdmvNumber() + "'";
            sql += ",deliverer = '" + bean.getDeliverer() + "'";
            sql += ",description = '" + bean.getDescription() + "'";
            sql += ",dmv_order = '" + bean.getDmvOrder() + "'";
            sql += ",kind = " + bean.getKind();
            sql += " where edmv_id = " + bean.getEdmvId();

            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int updateEdmv(int edmvId, int emrirId, int emsvId, int erfmId, int emivId) {
        if (edmvId == 0) {
            return 0;
        }
        if (emrirId == 0 && emsvId == 0 && erfmId == 0 && emivId == 0) {
            return 0;
        }
        try {
            String sql = "update edmv set";
            if (emrirId > 0) {
                sql += " emrir_id=" + emrirId;
            }
            if (emsvId > 0) {
                sql += " emsv_id=" + emsvId;
            }
            if (erfmId > 0) {
                sql += " erfm_id=" + erfmId;
            }
            if (emivId > 0) {
                sql += " emiv_id=" + emivId;
            }
            sql += " where edmv_id=" + edmvId;
            return DBUtil.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MsvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
