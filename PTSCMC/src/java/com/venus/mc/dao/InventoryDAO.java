/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.InventoryBean;
import com.venus.mc.bean.InventoryDetailBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class InventoryDAO extends BasicDAO {

    public void updateMaterialStoreRequest(int stoId, int matId, double quantityActual) throws Exception {



        try {



            String sql = "Update material_store_request Set "
                    + " actual_quantity = " + quantityActual
                    + " Where sto_id = " + stoId + " And mat_id = " + matId;
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

    public ArrayList getInventorys()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select inventory.*, name, fullname From (inventory left join store on inventory.sto_id = store.sto_id) left join employee on inventory.created_emp = employee.emp_id";

        ArrayList inventoryList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            InventoryBean inventory = null;
            while (rs.next()) {
                inventory = new InventoryBean();
                inventory.setInvId(rs.getInt("inv_id"));
                inventory.setStoId(rs.getInt("sto_id"));
                inventory.setStoName(rs.getString("name"));
                inventory.setInvDate(DateUtil.formatDate(rs.getDate("inv_date"), "dd/MM/yyyy"));
                inventory.setCreatedEmp(rs.getInt("created_emp"));
                inventory.setEmpName(rs.getString("fullname"));
                inventory.setComment(rs.getString("comment"));
                inventoryList.add(inventory);
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
        return inventoryList;
    }

    public InventoryBean getInventory(int invId) throws Exception {
        ResultSet rs = null;

        String sql = "Select inventory.* From inventory Where inv_id=" + invId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                InventoryBean inventory = new InventoryBean();
                inventory.setInvId(rs.getInt("inv_id"));
                inventory.setStoId(rs.getInt("sto_id"));
                inventory.setInvDate(DateUtil.formatDate(rs.getDate("inv_date"), "dd/MM/yyyy"));
                inventory.setCreatedEmp(rs.getInt("created_emp"));
                inventory.setComment(rs.getString("comment"));

                return inventory;
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

    public InventoryBean getInventoryByStoId(int stoId) throws Exception {
        ResultSet rs = null;

        String sql = "Select inventory.* From inventory Where sto_id=" + stoId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                InventoryBean inventory = new InventoryBean();
                inventory.setInvId(rs.getInt("inv_id"));

                return inventory;
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

    public int insertInventory(InventoryBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }



        try {
            String sql = "";



            sql = "Insert Into inventory(sto_id, inv_date, created_emp, comment)"
                    + " Values (" + bean.getStoId() + ",STR_TO_DATE('" + bean.getInvDate() + "','%d/%m/%Y')," + bean.getCreatedEmp()
                    + ",'" + bean.getComment() + "')";

            //System.out.println("sql ====" + sql);
//            DBUtil.executeUpdate(sql);
            return DBUtil.executeInsert(sql);
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

    public void updateInventory(InventoryBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {


            String sql = "";

            sql = "Update inventory Set "
                    + " sto_id=" + bean.getStoId()
                    + ", created_emp=" + bean.getCreatedEmp()
                    + ", comment='" + bean.getComment() + "'"
                    + " Where inv_id=" + bean.getInvId();

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

    public int deleteInventory(int invId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From inventory Where inv_id = " + invId;
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

    public ArrayList searchSimpleInventory(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "name";
                break;
        }
        ResultSet rs = null;

        String sql = "Select inventory.*, name, fullname From (inventory left join store on inventory.sto_id = store.sto_id) left join employee on inventory.created_emp = employee.emp_id Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by inv_id DESC";

        ArrayList inventoryList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            InventoryBean inventory = null;
            while (rs.next()) {
                inventory = new InventoryBean();
                inventory.setInvId(rs.getInt("inv_id"));
                inventory.setStoId(rs.getInt("sto_id"));
                inventory.setStoName(rs.getString("name"));
                inventory.setInvDate(DateUtil.formatDate(rs.getDate("inv_date"), "dd/MM/yyyy"));
                inventory.setCreatedEmp(rs.getInt("created_emp"));
                inventory.setEmpName(rs.getString("fullname"));
                inventory.setComment(rs.getString("comment"));
                inventoryList.add(inventory);
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
        return inventoryList;

    }

    public ArrayList getMaterialsByStoId(int stoId)
            throws Exception {
        ResultSet rs = null;

        String sql = "Select material_store_request.*, name_vn, code, unit_vn From (material_store_request left join material on material_store_request.mat_id = material.mat_id) left join unit on material.uni_id = unit.uni_id Where sto_id = " + stoId;

        ArrayList inventory_detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            InventoryDetailBean inventory_detail = null;
            while (rs.next()) {
                inventory_detail = new InventoryDetailBean();
                inventory_detail.setMatId(rs.getInt("mat_id"));
                inventory_detail.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
                inventory_detail.setMatCode(rs.getString("code"));
                inventory_detail.setUnit(rs.getString("unit_vn"));
                inventory_detail.setQuantityActual(rs.getDouble("actual_quantity"));
                inventory_detail.setQuantityDocument(rs.getDouble("actual_quantity"));
                inventory_detailList.add(inventory_detail);
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
        return inventory_detailList;
    }

    public InventoryDetailBean getMaterialsByStoId(int stoId, int matId)
            throws Exception {
        InventoryDetailBean inventory_detail = null;
        ResultSet rs = null;

        String sql = "Select material_store_request.*, name_vn, code, unit_vn From (material_store_request left join material on material_store_request.mat_id = material.mat_id) left join unit on material.uni_id = unit.uni_id "
                + "Where sto_id = " + stoId + " And material_store_request.mat_id = " + matId;

        try {



            rs = DBUtil.executeQuery(sql);

            while (rs.next()) {
                inventory_detail = new InventoryDetailBean();
                inventory_detail.setMatId(rs.getInt("mat_id"));
                inventory_detail.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
                inventory_detail.setMatCode(rs.getString("code"));
                inventory_detail.setUnit(rs.getString("unit_vn"));
                inventory_detail.setQuantityActual(rs.getDouble("actual_quantity"));
                inventory_detail.setQuantityDocument(rs.getDouble("actual_quantity"));

                return inventory_detail;
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
        return inventory_detail;
    }

    public ArrayList getInventoryDetails()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select inventory_detail.* From inventory_detail";

        ArrayList inventory_detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            InventoryDetailBean inventory_detail = null;
            while (rs.next()) {
                inventory_detail = new InventoryDetailBean();
                inventory_detail.setDetId(rs.getInt("det_id"));
                inventory_detail.setInvId(rs.getInt("inv_id"));
                inventory_detail.setMatId(rs.getInt("mat_id"));
                inventory_detail.setQuantityActual(rs.getDouble("quantity_actual"));
                inventory_detail.setQuantityDocument(rs.getDouble("quantity_document"));
                inventory_detail.setQuantityVariance(rs.getDouble("quantity_document") - rs.getDouble("quantity_actual"));
                inventory_detail.setIsChanged(rs.getInt("is_changed"));
                inventory_detail.setCommentDetail(rs.getString("comment"));
                inventory_detailList.add(inventory_detail);
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
        return inventory_detailList;
    }

    public ArrayList getInventoryDetailsByInvId(int invId)
            throws Exception {
        ResultSet rs = null;

        String sql = "Select inventory_detail.*, name_vn, code, unit_vn From (inventory_detail left join material on inventory_detail.mat_id = material.mat_id) left join unit on material.uni_id = unit.uni_id Where inv_id = " + invId;

        ArrayList inventory_detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            InventoryDetailBean inventory_detail = null;
            while (rs.next()) {
                inventory_detail = new InventoryDetailBean();
                inventory_detail.setDetId(rs.getInt("det_id"));
                inventory_detail.setInvId(rs.getInt("inv_id"));
                inventory_detail.setMatId(rs.getInt("mat_id"));
                inventory_detail.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
                inventory_detail.setMatCode(rs.getString("code"));
                inventory_detail.setUnit(rs.getString("unit_vn"));
                inventory_detail.setQuantityActual(rs.getDouble("quantity_actual"));
                inventory_detail.setQuantityDocument(rs.getDouble("quantity_document"));
                inventory_detail.setQuantityVariance(rs.getDouble("quantity_document") - rs.getDouble("quantity_actual"));
                inventory_detail.setIsChanged(rs.getInt("is_changed"));
                inventory_detail.setCommentDetail(rs.getString("comment"));
                inventory_detailList.add(inventory_detail);
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
        return inventory_detailList;
    }

    public InventoryDetailBean getInventoryDetail(int detId) throws Exception {
        ResultSet rs = null;

        String sql = "Select inventory_detail.*, From inventory_detail Where det_id=" + detId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                InventoryDetailBean inventory_detail = new InventoryDetailBean();
                inventory_detail.setDetId(rs.getInt("det_id"));
                inventory_detail.setInvId(rs.getInt("inv_id"));
                inventory_detail.setMatId(rs.getInt("mat_id"));
                inventory_detail.setQuantityActual(rs.getDouble("quantity_actual"));
                inventory_detail.setQuantityDocument(rs.getDouble("quantity_document"));
                inventory_detail.setQuantityVariance(rs.getDouble("quantity_document") - rs.getDouble("quantity_actual"));
                inventory_detail.setIsChanged(rs.getInt("is_changed"));
                inventory_detail.setCommentDetail(rs.getString("comment"));

                return inventory_detail;
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

    public void insertInventoryDetail(InventoryDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {

            String sql = "";



            sql = "Insert Into inventory_detail(inv_id, mat_id, quantity_actual, quantity_document, is_changed, comment)"
                    + " Values (" + bean.getInvId() + "," + bean.getMatId()
                    + "," + bean.getQuantityActual() + "," + bean.getQuantityDocument()
                    + ",0,'" + bean.getCommentDetail() + "')";

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

    public void updateInventoryDetail(InventoryDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {



            String sql = "Update inventory_detail Set "
                    + " inv_id=" + bean.getInvId()
                    + ", mat_id=" + bean.getMatId()
                    + ", quantity_actual=" + bean.getQuantityActual()
                    + //                    ", quantity_document=" + bean.getQuantityDocument() +
                    ", is_changed=" + bean.getIsChanged()
                    + ", comment='" + bean.getCommentDetail() + "'"
                    + " Where det_id=" + bean.getDetId();

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

    public int deleteInventoryDetail(int detId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From inventory_detail Where det_id=" + detId;
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
}
