/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.EmsvBean;
import com.venus.mc.bean.EmsvMaterialBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.util.MCUtil;
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
public class EmsvDAO extends BasicDAO {

    public ArrayList getEmsvList() throws Exception {
        ArrayList arrRes = new ArrayList();
        ResultSet rs = null;
        try {
            String sql = "select emsv.*,s.name,dn.econ_number"
                    + " from emsv as emsv"
                    + " left join emrir as m on m.emrir_id=emsv.emrir_id"
                    + " left join edelivery_notice as dn on dn.edn_id=m.edn_id"
                    + " left join store as  s on emsv.sto_id=s.sto_id"
                    + //" where kind=2 " + //1: cong cu dung cu - 2: khac
                    " order by created_date desc";

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EmsvBean bean = new EmsvBean();
                bean.setEmsvId(rs.getInt("emsv.emsv_id"));
                bean.setCreatedDate(DateUtil.formatDate(rs.getDate("emsv.created_date"), "dd/MM/yyyy"));
                bean.setEmsvNumber(rs.getString("emsv.emsv_number"));
                //bean.setMrirId(rs.getInt("m.mrir_id"));
                //bean.setMrirNumber(rs.getString("r.mrir_number"));
                //bean.setCreator(rs.getInt("emsv.creator"));
                //bean.setCreatorName(rs.getString("e.fullname"));
                //bean.setStoId(rs.getInt("m.sto_id"));              
                bean.setContract(rs.getString("dn.econ_number"));
                bean.setDeliverer(rs.getString("emsv.deliverer"));
                bean.setVendor(StringUtil.decodeString(rs.getString("emsv.vendor")));
                bean.setStoName(rs.getString("emsv.sto_id"));
                bean.setStoName(rs.getString("s.name"));

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

    public EmsvBean getEmsv(int emsvId) throws Exception {
        if (emsvId == 0) {
            return null;
        }
        ResultSet rs = null;
        try {
            String sql = "Select m.*,e.fullname,s.name,r.emrir_number From emsv as m"
                    + " left join employee as e on m.created_emp=e.emp_id"
                    + " left join emrir as r on m.emrir_id=r.emrir_id"
                    + " left join store as s on m.sto_id=s.sto_id"
                    + " Where emsv_id=" + emsvId;

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EmsvBean bean = new EmsvBean();
                bean.setEmsvId(rs.getInt("m.emsv_id"));
                bean.setCreatedDate(DateUtil.formatDate(rs.getDate("m.created_date"), "dd/MM/yyyy"));
                bean.setEmsvNumber(rs.getString("m.emsv_number"));
                bean.setEmrirId(rs.getInt("m.emrir_id"));
                bean.setEmrirNumber(rs.getString("r.emrir_number"));
                bean.setCreatedEmpId(rs.getInt("m.created_emp"));
                bean.setCreatedEmpName(rs.getString("e.fullname"));
                bean.setStoId(rs.getInt("m.sto_id"));
                bean.setStoName(rs.getString("s.name"));

                bean.setTotal(rs.getDouble("m.total"));
                bean.setDeliverer(rs.getString("m.deliverer"));

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

    public ArrayList getMaterialsFromEmsv(int emsvId) throws Exception {
        ArrayList arrRes = new ArrayList();
        if (emsvId == 0) {
            return arrRes;
        }
        ResultSet rs = null;
        try {
            String sql = "select d.emat_id,d.quantity,d.price,d.total,d.unit,m.name_vn,m.code"
                    + " from emsv_detail as d"
                    + " left join ematerial as m on m.emat_id=d.emat_id"
                    + " where emsv_id=" + emsvId;

            rs = DBUtil.executeQuery(sql);
            int id = 1;
            while (rs.next()) {
                EmsvMaterialBean bean = new EmsvMaterialBean();
                bean.setDetId(id++);
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

    public EmsvBean getNewEmsvFromEmrir(int emrirId) throws Exception {
        if (emrirId == 0) {
            return null;
        }
        ResultSet rs = null;
        try {
            String sql = "select s.name, s.sto_id, o.kind, r.org_id from emrir as m"
                    + //" left join request_detail as d on m.req_id=d.det_id" +
                    " left join request as r on r.req_id=m.req_id"
                    + " left join organization as o on o.org_id=r.org_id"
                    + //" left join project as p on p.pro_id=r.pro_id" +
                    " left join store as s on s.org_id=r.org_id"
                    + " where m.emrir_id=" + emrirId;

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EmsvBean bean = new EmsvBean();
                int kind = rs.getInt("o.kind");
                if (kind == 100) { //kho du an    
                    bean.setStoId(rs.getInt("s.sto_id"));
                    bean.setStoName(rs.getString("s.name"));
                } else {
                    bean.setStoId(1);
                    bean.setStoName(MCUtil.getBundleString("message.store.comp"));
                }

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

    public ArrayList getMaterialsFromEmrir(int emrirId) throws Exception {
        ArrayList arrRes = new ArrayList();
        if (emrirId == 0) {
            return arrRes;
        }
        ResultSet rs = null;
        try {
            String sql = "select d.emat_id,d.quantity,d.price,d.unit,m.name_vn,m.code"
                    + " from emrir_detail as d"
                    + " left join ematerial as m on m.emat_id=d.emat_id"
                    + " where emrir_id=" + emrirId;

            rs = DBUtil.executeQuery(sql);
            int id = 1;
            while (rs.next()) {
                EmsvMaterialBean bean = new EmsvMaterialBean();
                bean.setDetId(id++);
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

    public ArrayList getEmrir4Emsv() throws Exception {
        ArrayList arrMrir = new ArrayList();
        ResultSet rs = null;
        try {
            String sql = "select emrir_id,emrir_number from emrir"
                    + " where status=0 and edn_id>0 and edn_id is not null";

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                LabelValueBean bean = new LabelValueBean();
                bean.setLabel(rs.getString("emrir_number"));
                bean.setValue(rs.getString("emrir_id"));
                arrMrir.add(bean);
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
        return arrMrir;
    }

    public int insertEmsv(EmsvBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        if (GenericValidator.isBlankOrNull(bean.getEmsvNumber())
                || bean.getEmrirId() == 0
                || bean.getCreatedEmpId() == 0
                || bean.getStoId() == 0) {
            return 0;
        }

        if (GenericValidator.isBlankOrNull(bean.getDeliverer())) {
            bean.setDeliverer("");
        }

        int result = 0;
        try {
            String sql = "";
            sql = "insert into emsv(created_date,emsv_number, emrir_id, created_emp, sto_id, total, deliverer)"
                    + " Values ("
                    + "sysdate(),"
                    + "'" + bean.getEmsvNumber() + "',"
                    + bean.getEmrirId() + ","
                    + bean.getCreatedEmpId() + ","
                    + bean.getStoId() + ","
                    + bean.getTotal() + ","
                    + "'" + bean.getDeliverer() + "'"
                    + ")";
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int insertEmsvMaterial(EmsvMaterialBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        if (bean.getEmsvId() == 0
                || bean.getEmatId() == 0
                || bean.getQuantity() == 0) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "";
            sql = "insert into emsv_detail(emsv_id, emat_id, unit, quantity, price, total)"
                    + " Values ("
                    + bean.getEmsvId() + ","
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

    public int updateEmrirStatus(int emrirId, int status) throws Exception {
        if (emrirId == 0) {
            return 0;
        }
        try {
            String sql = "update emrir Set "
                    + " status=" + status
                    + " where emrir_id=" + emrirId;

            return DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public ArrayList searchSimple(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "emsv.emsv_number";
                break;
            case 2:
                strFieldname = "dn.econ_number";
                break;
            case 3:
                strFieldname = "s.name";
                break;
        }
        ArrayList arrRes = new ArrayList();
        ResultSet rs = null;
        try {
            String sql = "select emsv.*,s.name,dn.econ_number"
                    + " from emsv as emsv"
                    + " left join emrir as m on m.emrir_id=emsv.emrir_id"
                    + " left join edelivery_notice as dn on dn.edn_id=m.edn_id"
                    + " left join store as  s on emsv.sto_id=s.sto_id";
            sql += " where 1";
            if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
                sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
            }
            sql += " order by created_date desc";

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EmsvBean bean = new EmsvBean();
                bean.setEmsvId(rs.getInt("emsv.emsv_id"));
                bean.setCreatedDate(DateUtil.formatDate(rs.getDate("emsv.created_date"), "dd/MM/yyyy"));
                bean.setEmsvNumber(rs.getString("emsv.emsv_number"));
                //bean.setMrirId(rs.getInt("m.mrir_id"));
                //bean.setMrirNumber(rs.getString("r.mrir_number"));
                //bean.setCreator(rs.getInt("emsv.creator"));
                //bean.setCreatorName(rs.getString("e.fullname"));
                //bean.setStoId(rs.getInt("m.sto_id"));           
                bean.setContract(rs.getString("dn.econ_number"));
                bean.setDeliverer(rs.getString("emsv.deliverer"));
                bean.setVendor(StringUtil.decodeString(rs.getString("emsv.vendor")));
                bean.setStoId(rs.getInt("emsv.sto_id"));
                bean.setStoName(rs.getString("s.name"));

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

    public int updateEmsv(EmsvBean bean) throws Exception {
        if (bean == null
                || bean.getEmsvId() == 0) {
            return 0;
        }
        if (GenericValidator.isBlankOrNull(bean.getDeliverer())) {
            bean.setDeliverer("");
        }

        int result = 0;
        try {
            String sql = "update emsv set ";
            sql += "emsv_number = '" + bean.getEmsvNumber() + "'";
            sql += ",deliverer = '" + bean.getDeliverer() + "'";
            sql += " where emsv_id=" + bean.getEmsvId();
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }
}
