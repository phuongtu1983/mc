/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.MrvBean;
import com.venus.mc.bean.MrvMaterialBean;
import com.venus.mc.bean.MsvBean;
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
public class MrvDAO extends BasicDAO {

    public ArrayList getMrvList() throws Exception {
        ArrayList arrRes = new ArrayList();
        ResultSet rs = null;
        try {
            String sql = "select mrv.*,e.fullname,s.name,o.name"
                    + " from mrv as mrv"
                    + " left join employee as e on mrv.delivery_emp=e.emp_id"
                    + " left join organization as o on o.org_id=mrv.org_id"
                    + //" left join miv as v on v.miv_id=mrv.miv_id" +
                    //                    " left join contract as c on r.con_id=c.con_id" +
                    //" left join vendor as v on c.ven_id=v.ven_id" +
                    " left join store as  s on mrv.sto_id=s.sto_id"
                    + //" where kind=2 " + //1: cong cu dung cu - 2: khac
                    " order by created_date desc";

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                MrvBean bean = new MrvBean();
                bean.setMrvId(rs.getInt("mrv.mrv_id"));
                bean.setCreatedDate(DateUtil.formatDate(rs.getDate("mrv.created_date"), "dd/MM/yyyy"));
                bean.setMrvNumber(rs.getString("mrv.mrv_number"));
                //bean.setMrirId(rs.getInt("m.mrir_id"));
                //bean.setMrirNumber(rs.getString("r.mrir_number"));
                //bean.setCreator(rs.getInt("mrv.creator"));
                //bean.setCreatorName(rs.getString("e.fullname"));
                //bean.setStoId(rs.getInt("m.sto_id"));                
                bean.setDeliveryEmpId(rs.getInt("mrv.delivery_emp"));
                bean.setDeliveryEmpName(rs.getString("e.fullname"));
                bean.setStoName(rs.getString("mrv.sto_id"));
                bean.setStoName(rs.getString("s.name"));
                bean.setOrgId(rs.getInt("mrv.org_id"));
                bean.setOrgName(rs.getString("o.name"));
                bean.setMivNumbers(rs.getString("mrv.miv_numbers"));
                //bean.setDescription(rs.getString("mrv.description"));
                //bean.setType(rs.getInt("mrv.type"));
                //bean.setTotal(rs.getDouble("mrv.total"));
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

    public MsvBean getMrv(int mrvId) throws Exception {
        if (mrvId == 0) {
            return null;
        }
        ResultSet rs = null;
        try {
            String sql = "Select m.*,s.name,r.mrir_number"
                    + " from msv as m"
                    + " left join mrir as r on m.mrir_id=r.mrir_id"
                    + //" left join employee as e on m.created_emp=e.emp_id" +
                    //" left join employee as e1 on m.delivery_emp=e1.emp_id" +
                    " left join store as s on m.sto_id=s.sto_id"
                    + " Where mrv_id=" + mrvId;

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                MsvBean bean = new MsvBean();
                bean.setMsvId(rs.getInt("m.msv_id"));
                bean.setCreatedDate(DateUtil.formatDate(rs.getDate("m.created_date"), "dd/MM/yyyy"));
                bean.setMsvNumber(rs.getString("m.msv_number"));
                bean.setMrirId(rs.getInt("m.mrir_id"));
                bean.setMrirNumber(rs.getString("r.mrir_number"));
                bean.setCreatedEmpId(rs.getInt("m.created_emp"));
                bean.setStoId(rs.getInt("m.sto_id"));
                bean.setStoName(rs.getString("s.name"));
                bean.setDescription(rs.getString("m.description"));
                bean.setOrgId(rs.getInt("m.org_id"));
                bean.setType(rs.getInt("m.type"));
                bean.setTotal(rs.getDouble("m.total"));
                bean.setDeliveryEmpId(rs.getInt("m.delivery_emp"));

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

    public ArrayList getMaterialsFromMrv(int mrvId) throws Exception {
        ArrayList arrRes = new ArrayList();
        if (mrvId == 0) {
            return arrRes;
        }
        ResultSet rs = null;
        try {
            String sql = "select d.det_id,d.mat_id,d.quantity,d.price,d.total,d.unit,m.name_vn,m.code"
                    + " from mrv_detail as d"
                    + " left join material as m on m.mat_id=d.mat_id"
                    + " where mrv_id=" + mrvId;

            rs = DBUtil.executeQuery(sql);
            int id = 1;
            while (rs.next()) {
                MrvMaterialBean bean = new MrvMaterialBean();
                bean.setDetId(rs.getInt("det_id"));
                bean.setMatId(rs.getInt("mat_id"));
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

    public MrvBean getNewMrvFromMrir(int mrirId) throws Exception {
        if (mrirId == 0) {
            return null;
        }
        ResultSet rs = null;
        try {
            String sql = "select o.kind, r.org_id from mrir as m"
                    + //" left join request_detail as d on m.req_id=d.det_id" +
                    //" left join request as r on r.req_id=m.req_id" +
                    //" left join employee as e on e.emp_id=m.req_id" +
                    " left join organization as o on o.org_id=m.org_id"
                    + //" left join project as p on p.pro_id=r.pro_id" +
                    " left join store as s on s.pro_id=r.org_id"
                    + " where m.mrir_id=" + mrirId;

            rs = DBUtil.executeQuery("select * from mrir where mrir_id=" + mrirId);
            while (rs.next()) {
                MrvBean bean = new MrvBean();
                bean.setStoId(1);
                bean.setStoName(MCUtil.getBundleString("message.store.comp"));
                bean.setMrirId(mrirId);

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

    public ArrayList getMaterialsFromMrir(int mrirId) throws Exception {
        ArrayList arrRes = new ArrayList();
        if (mrirId == 0) {
            return arrRes;
        }
        ResultSet rs = null;
        try {
            String sql = "select d.mat_id,d.quantity,d.price,d.unit,d.req_detail_id,m.name_vn,m.code"
                    + " from mrir_detail as d"
                    + " left join material as m on m.mat_id=d.mat_id"
                    + " where mrir_id=" + mrirId;

            rs = DBUtil.executeQuery(sql);
            int id = 1;
            while (rs.next()) {
                MrvMaterialBean bean = new MrvMaterialBean();
                bean.setDetId(id++);
                bean.setMatId(rs.getInt("mat_id"));
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

    public int insertMrv(MrvBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        if (GenericValidator.isBlankOrNull(bean.getMrvNumber())
                || bean.getMrirId() == 0
                || bean.getCreatedEmpId() == 0
                || bean.getStoId() == 0) {
            return 0;
        }
        if (GenericValidator.isBlankOrNull(bean.getDescription())) {
            bean.setDescription("");
        }
        if (GenericValidator.isBlankOrNull(bean.getMivNumbers())) {
            bean.setMivNumbers("");
        }

        int result = 0;
        try {
            String sql = "";
            sql = "insert into mrv("
                    + " created_date,"
                    + " mrv_number,"
                    + " mrir_id,"
                    + " created_emp,"
                    + " org_id,"
                    + " pro_id,"
                    + " sto_id,"
                    + " description,"
                    + " total,"
                    + " miv_numbers,"
                    + " delivery_emp"
                    + " ) Values ("
                    + "sysdate(),"
                    + "'" + bean.getMrvNumber() + "',"
                    + bean.getMrirId() + ","
                    + bean.getCreatedEmpId() + ","
                    + bean.getOrgId() + ","
                    + bean.getProId() + ","
                    + bean.getStoId() + ","
                    + "'" + bean.getDescription() + "',"
                    + bean.getTotal() + ","
                    + "'" + bean.getMivNumbers() + "',"
                    + +bean.getDeliveryEmpId() + ""
                    + ")";
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public int insertMrvMaterial(MrvMaterialBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        if (bean.getMrvId() == 0
                || bean.getMatId() == 0
                || bean.getQuantity() == 0) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "";
            sql = "insert into mrv_detail(mrv_id, mat_id, unit, quantity, price, total)"
                    + " Values ("
                    + bean.getMrvId() + ","
                    + bean.getMatId() + ","
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

    public ArrayList searchSimple(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "mrv.mrv_number";
                break;
            case 2:
                strFieldname = "mrv.miv_numbers";
                break;
            case 3:
                strFieldname = "s.name";
                break;
        }

        ArrayList arrRes = new ArrayList();
        ResultSet rs = null;
        try {
            String sql = "select mrv.*,e.fullname,s.name,o.name"
                    + " from mrv as mrv"
                    + " left join employee as e on mrv.delivery_emp=e.emp_id"
                    + " left join organization as o on o.org_id=mrv.org_id"
                    + //" left join miv as v on v.miv_id=mrv.miv_id" +
                    //                    " left join contract as c on r.con_id=c.con_id" +
                    //" left join vendor as v on c.ven_id=v.ven_id" +
                    " left join store as  s on mrv.sto_id=s.sto_id";
            sql += " where 1";
            if ((fieldid > 0) && (!GenericValidator.isBlankOrNull(strFieldvalue))) {
                sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
            }
            sql += " order by created_date desc";

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                MrvBean bean = new MrvBean();
                bean.setMrvId(rs.getInt("mrv.mrv_id"));
                bean.setCreatedDate(DateUtil.formatDate(rs.getDate("mrv.created_date"), "dd/MM/yyyy"));
                bean.setMrvNumber(rs.getString("mrv.mrv_number"));
                //bean.setMrirId(rs.getInt("m.mrir_id"));
                //bean.setMrirNumber(rs.getString("r.mrir_number"));
                //bean.setCreator(rs.getInt("mrv.creator"));
                //bean.setCreatorName(rs.getString("e.fullname"));
                //bean.setStoId(rs.getInt("m.sto_id"));                
                bean.setDeliveryEmpId(rs.getInt("mrv.delivery_emp"));
                bean.setDeliveryEmpName(rs.getString("e.fullname"));
                bean.setStoName(rs.getString("mrv.sto_id"));
                bean.setStoName(rs.getString("s.name"));
                bean.setOrgId(rs.getInt("mrv.org_id"));
                bean.setOrgName(rs.getString("o.name"));
                bean.setMivNumbers(rs.getString("mrv.miv_numbers"));
                //bean.setDescription(rs.getString("mrv.description"));
                //bean.setType(rs.getInt("mrv.type"));
                //bean.setTotal(rs.getDouble("mrv.total"));
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

    public int updateMrv(MrvBean bean) throws Exception {
        if (bean == null
                || bean.getMrvId() == 0) {
            return 0;
        }

        if (GenericValidator.isBlankOrNull(bean.getDescription())) {
            bean.setDescription("");
        }
        if (GenericValidator.isBlankOrNull(bean.getMivNumbers())) {
            bean.setMivNumbers("");
        }

        int result = 0;
        try {
            String sql = "update mrv set ";
            sql += "mrv_number='" + bean.getMrvNumber() + "'";
            sql += ",description='" + bean.getDescription() + "'";
            sql += ",miv_numbers='" + bean.getMivNumbers() + "'";
            sql += " where mrv_id=" + bean.getMrvId();

            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }
}
