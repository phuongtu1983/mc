/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.mc.bean.EmaterialStoreBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thcao
 */
public class EmaterialStoreDAO extends BasicDAO {

    public EmaterialStoreBean getEmaterialStore(int ematId, int stoId) throws Exception {
        if (ematId == 0 || stoId == 0) {
            return null;
        }

        ResultSet rs = null;
        try {
            String sql = "select * from ematerial_store"
                    + " where emat_id=" + ematId
                    + " and sto_id=" + stoId;

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EmaterialStoreBean bean = new EmaterialStoreBean();
                bean.setEmsId(rs.getInt("ems_id"));
                bean.setStoId(rs.getInt("sto_id"));
                bean.setEmatId(rs.getInt("emat_id"));
                bean.setPrice(rs.getDouble("price"));
                bean.setReserveQuantity(rs.getDouble("reserve_quantity"));
                bean.setAvailableQuantity(rs.getDouble("available_quantity"));
                bean.setActualQuantity(rs.getDouble("actual_quantity"));

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

    public int insertEmaterialStore(int ematId, int stoId, double price, double quantity) throws Exception {
        if (stoId == 0
                || ematId == 0
                || quantity == 0) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "";
            sql = "insert into ematerial_store(sto_id, emat_id, price, available_quantity, actual_quantity)"
                    + " Values ("
                    + stoId + ","
                    + ematId + ","
                    + price + ","
                    + quantity + ","
                    + quantity
                    + ")";
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public void updateEmaterialStore(EmaterialStoreBean bean) throws Exception {
        if (bean == null || bean.getEmsId() == 0) {
            return;
        }
        String sql = "update ematerial_store set "
                + " available_quantity=" + bean.getAvailableQuantity()
                + ",actual_quantity=" + bean.getActualQuantity()
                + " where ems_id=" + bean.getEmsId();
        try {
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
