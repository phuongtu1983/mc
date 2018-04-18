/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.DmvBean;
import com.venus.mc.bean.DmvMaterialBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.util.MCUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thcao
 */
public class DmvDAO extends BasicDAO {

    public ArrayList getDmvList() throws Exception {
        ArrayList arrRes = new ArrayList();
        ResultSet rs = null;

        try {
            String sql = "select dmv.*,e.fullname,s.name,o.name"
                    + " from dmv as dmv"
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
                DmvBean bean = new DmvBean();
                bean.setDmvId(rs.getInt("dmv.dmv_id"));
                bean.setCreatedDate(DateUtil.formatDate(rs.getDate("dmv.created_date"), "dd/MM/yyyy"));
                bean.setDmvNumber(rs.getString("dmv.dmv_number"));
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

    public DmvBean getNewDmvFromDn(int dnId) throws Exception {
        if (dnId == 0) {
            return null;
        }
        ResultSet rs = null;

        try {
            String sql = "select s.sto_id,s.name,r.org_id from delivery_notice as m"
                    + //" left join request_detail as d on m.req_id=d.det_id" +
                    " left join request as r on r.req_id=m.req_id"
                    + " left join organization as o on o.org_id=r.org_id"
                    + //" left join project as p on p.pro_id=r.pro_id" +
                    " left join store as s on s.pro_id=r.pro_id"
                    + " where m.dn_id=" + dnId;

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                DmvBean bean = new DmvBean();
                String storeName = rs.getString("s.name");
                int orgId = rs.getInt("r.org_id");
                if (GenericValidator.isBlankOrNull(storeName)) {
                    if (orgId > 0) {
                        bean.setStoId(1);
                        bean.setStoName(MCUtil.getBundleString("message.store.comp"));
                    }
                } else {
                    bean.setStoId(rs.getInt("s.sto_id"));
                    bean.setStoName(rs.getString("s.name"));
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

    public DmvBean getDmv(int dmvId) throws Exception {
        if (dmvId == 0) {
            return null;
        }
        ResultSet rs = null;

        try {
            String sql = "select dmv.*,ce.fullname,re.fullname,s.name,o.name,v.name,dn.con_id,c.contract_number,p.name,"
                    + "r.mrir_number,msv.msv_number,miv.miv_number,rfm.rfm_number"
                    + " from dmv as dmv"
                    + " left join employee as ce on dmv.created_emp=ce.emp_id"
                    + " left join employee as re on dmv.receive_emp=re.emp_id"
                    + " left join organization as o on o.org_id=dmv.org_id"
                    + " left join organization as p on p.org_id=dmv.pro_id"
                    + " left join delivery_notice as dn on dn.dn_id=dmv.dn_id"
                    + " left join contract as c on dn.con_id=c.con_id"
                    + " left join vendor as v on v.ven_id=c.ven_id"
                    + " left join store as  s on dmv.sto_id=s.sto_id"
                    + " left join mrir as r on r.mrir_id=dmv.mrir_id"
                    + " left join msv as msv on dmv.msv_id=msv.msv_id"
                    + " left join rfm as rfm on dmv.rfm_id=rfm.rfm_id"
                    + " left join miv as miv on dmv.miv_id=miv.miv_id"
                    + " where dmv_id=" + dmvId;


            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                DmvBean bean = new DmvBean();
                bean.setDmvId(rs.getInt("dmv.dmv_id"));
                bean.setCreatedDate(DateUtil.formatDate(rs.getDate("dmv.created_date"), "dd/MM/yyyy"));
                bean.setCreatedEmpId(rs.getInt("dmv.created_emp"));
                bean.setCreatedEmpName(rs.getString("ce.fullname"));
                bean.setDmvNumber(rs.getString("dmv.dmv_number"));
                bean.setDmvOrder(rs.getString("dmv.dmv_order"));
                bean.setConId(rs.getInt("dn.con_id"));
                bean.setConNumber(rs.getString("c.contract_number"));
                bean.setVendorName(StringUtil.decodeString(rs.getString("v.name")));
                //bean.setMrirId(rs.getInt("m.mrir_id"));
                //bean.setMrirNumber(rs.getString("r.mrir_number"));                
                bean.setReceiveEmpId(rs.getInt("dmv.receive_emp"));
                bean.setReceiveEmpName(rs.getString("re.fullname"));
                bean.setStoId(rs.getInt("dmv.sto_id"));
                bean.setStoName(rs.getString("s.name"));
                bean.setProId(rs.getInt("dmv.pro_id"));
                bean.setProName(rs.getString("p.name"));
                bean.setOrgId(rs.getInt("dmv.org_id"));
                bean.setOrgName(rs.getString("o.name"));
                bean.setDescription(rs.getString("dmv.description"));
                bean.setKind(rs.getInt("dmv.kind"));
                bean.setDnId(rs.getInt("dmv.dn_id"));
                bean.setDeliverer(rs.getString("dmv.deliverer"));
                bean.setMrirId(rs.getInt("dmv.mrir_id"));
                bean.setMrirNumber(rs.getString("r.mrir_number"));
                bean.setMivId(rs.getInt("dmv.miv_id"));
                bean.setMivNumber(rs.getString("miv.miv_number"));
                bean.setMsvId(rs.getInt("dmv.msv_id"));
                bean.setMsvNumber(rs.getString("msv.msv_number"));
                bean.setRfmId(rs.getInt("dmv.rfm_id"));
                bean.setRfmNumber(rs.getString("rfm.rfm_number"));
                bean.setReqId(rs.getInt("dmv.req_id"));
                bean.setTotal(rs.getDouble("dmv.total"));
                //bean.setConId(rs.getInt("r.con_id"));
                //bean.setConNumber(rs.getString("c.contract_number"));
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

    public int insertDmv(DmvBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        if (GenericValidator.isBlankOrNull(bean.getDmvNumber())
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
            sql = "insert into dmv("
                    + "created_date,"
                    + "dmv_number, "
                    + "created_emp, "
                    + "deliverer, "
                    + "dn_id, "
                    + "org_id, ";
            if (bean.getProId() > 0) {
                sql += "pro_id, ";
            }
            sql += "sto_id, "
                    + "description, "
                    + "total, "
                    + "dmv_order,"
                    + "receive_emp,"
                    + "req_id,"
                    + "kind)"
                    + " Values ("
                    + "sysdate(),"
                    + "'" + bean.getDmvNumber() + "',"
                    + bean.getCreatedEmpId() + ","
                    + "'" + bean.getDeliverer() + "',"
                    + bean.getDnId() + ","
                    + bean.getOrgId() + ",";
            if (bean.getProId() > 0) {
                sql += bean.getProId() + ",";
            }
            sql += bean.getStoId() + ","
                    + "'" + bean.getDescription() + "',"
                    + bean.getTotal() + ","
                    + "'" + bean.getDmvOrder() + "',"
                    + +bean.getReceiveEmpId() + ","
                    + +bean.getReqId() + ","
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

    public int updateDmv(DmvBean bean) throws Exception {
        if (bean == null || bean.getDmvId() == 0) {
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
            String sql = "update dmv set ";
            sql += " dmv_number='" + bean.getDmvNumber() + "'";
            sql += ",deliverer='" + bean.getDeliverer() + "'";
            sql += ",description='" + bean.getDeliverer() + "'";
            sql += ",dmv_order='" + bean.getDmvOrder() + "'";
            sql += ",kind=" + bean.getKind();
            sql += " where dmv_id=" + bean.getDmvId();

            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int insertDmvMaterial(DmvMaterialBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        if (bean.getDmvId() == 0
                || bean.getMatId() == 0
                || bean.getQuantity() == 0) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "";
            sql = "insert into dmv_detail("
                    + "dmv_id, "
                    + "mat_id, "
                    + "unit, "
                    + "quantity, "
                    + "price, "
                    + "total,"
                    + "req_detail_id)"
                    + " Values ("
                    + bean.getDmvId() + ","
                    + bean.getMatId() + ","
                    + "'" + bean.getUnit() + "',"
                    + bean.getQuantity() + ","
                    + bean.getPrice() + ","
                    + bean.getTotal() + ","
                    + bean.getReqDetailId()
                    + ")";
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList getMaterialsFromDmv(int dmvId) throws Exception {
        ArrayList arrRes = new ArrayList();
        if (dmvId == 0) {
            return arrRes;
        }
        ResultSet rs = null;

        try {
            String sql = "select d.det_id,d.mat_id,d.quantity,d.price,d.total,d.unit,d.req_detail_id,m.name_vn,m.code"
                    + " from dmv_detail as d"
                    + " left join material as m on m.mat_id=d.mat_id"
                    + " where dmv_id=" + dmvId;

            rs = DBUtil.executeQuery(sql);
            int id = 1;
            while (rs.next()) {
                DmvMaterialBean bean = new DmvMaterialBean();
                bean.setDetId(rs.getInt("det_id"));
                bean.setMatId(rs.getInt("mat_id"));
                bean.setMatName(StringUtil.decodeString(rs.getString("m.name_vn")));
                bean.setMatCode(rs.getString("m.code"));
                bean.setQuantity(rs.getDouble("d.quantity"));
                bean.setPrice(rs.getDouble("d.price"));
                bean.setReqDetailId(rs.getInt("d.req_detail_id"));
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

    public int updateDmv(int dmvId, int mrirId, int msvId, int rfmId, int mivId) {
        if (dmvId == 0) {
            return 0;
        }
        if (mrirId == 0 && msvId == 0 && rfmId == 0 && mivId == 0) {
            return 0;
        }
        try {
            String sql = "update dmv set";
            if (mrirId > 0) {
                sql += " mrir_id=" + mrirId;
            }
            if (msvId > 0) {
                sql += " msv_id=" + msvId;
            }
            if (rfmId > 0) {
                sql += " rfm_id=" + rfmId;
            }
            if (mivId > 0) {
                sql += " miv_id=" + mivId;
            }
            sql += " where dmv_id=" + dmvId;
            return DBUtil.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MsvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList searchSimple(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "dmv.dmv_number";
                break;
            case 2:
                strFieldname = "dmv.dmv_order";
                break;
            case 3:
                strFieldname = "s.name";
                break;
        }

        ArrayList arrRes = new ArrayList();
        ResultSet rs = null;

        try {
            String sql = "select dmv.*,e.fullname,s.name,o.name"
                    + " from dmv as dmv"
                    + " left join employee as e on dmv.receive_emp=e.emp_id"
                    + " left join organization as o on o.org_id=dmv.org_id"
                    + //                    " left join mrir as r on r.mrir_id=m.mrir_id" +
                    //                    " left join contract as c on r.con_id=c.con_id" +
                    //" left join vendor as v on c.ven_id=v.ven_id" +
                    " left join store as  s on dmv.sto_id=s.sto_id";
            //" where kind=2 " + //1: cong cu dung cu - 2: khac
            sql += " where 1";
            if ((fieldid > 0) && (!GenericValidator.isBlankOrNull(strFieldvalue))) {
                sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
            }
            sql += " order by created_date desc";

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                DmvBean bean = new DmvBean();
                bean.setDmvId(rs.getInt("dmv.dmv_id"));
                bean.setCreatedDate(DateUtil.formatDate(rs.getDate("dmv.created_date"), "dd/MM/yyyy"));
                bean.setDmvNumber(rs.getString("dmv.dmv_number"));
                bean.setDmvOrder(rs.getString("dmv.dmv_order"));
                bean.setReceiveEmpId(rs.getInt("dmv.receive_emp"));
                bean.setReceiveEmpName(rs.getString("e.fullname"));
                bean.setStoName(rs.getString("dmv.sto_id"));
                bean.setStoName(rs.getString("s.name"));
                bean.setOrgId(rs.getInt("dmv.org_id"));
                bean.setOrgName(rs.getString("o.name"));
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
}
