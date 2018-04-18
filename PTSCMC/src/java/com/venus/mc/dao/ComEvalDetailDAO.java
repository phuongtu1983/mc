/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ComEvalDetailBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class ComEvalDetailDAO extends BasicDAO {

    public ComEvalDetailDAO() {
    }

    public ArrayList getComEvalDetails()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select * From com_eval_detail Order by det_id ASC";
        ArrayList com_eval_detailList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalDetailBean com_eval_detail = null;
            while (rs.next()) {
                com_eval_detail = new ComEvalDetailBean();
                com_eval_detail.setDetId(rs.getInt("det_id"));
                com_eval_detail.setCevId(rs.getInt("cev_id"));
                com_eval_detail.setMatId(rs.getInt("mat_id"));
                com_eval_detail.setUnit(rs.getString("unit"));
                com_eval_detail.setQuantity(rs.getDouble("quantity"));
                com_eval_detail.setSuggestedSupplier(rs.getString("suggested_supplier"));
                com_eval_detailList.add(com_eval_detail);
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
        return com_eval_detailList;
    }

    public ComEvalDetailBean getComEvalDetail(int detId) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From com_eval_detail Where det_id=" + detId;
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ComEvalDetailBean com_eval_detail = new ComEvalDetailBean();
                com_eval_detail.setDetId(rs.getInt("det_id"));
                com_eval_detail.setCevId(rs.getInt("cev_id"));
                com_eval_detail.setMatId(rs.getInt("mat_id"));
                com_eval_detail.setUnit(rs.getString("unit"));
                com_eval_detail.setQuantity(rs.getDouble("quantity"));
                com_eval_detail.setSuggestedSupplier(rs.getString("suggested_supplier"));

                return com_eval_detail;
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

    public ComEvalDetailBean getComEvalDetailByName(String name) throws Exception {
        ResultSet rs = null;

        String sql = "select det_id from com_eval_detail where name='" + name + "'";
        try {
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                ComEvalDetailBean com_eval_detail = new ComEvalDetailBean();
                com_eval_detail.setDetId(rs.getInt("det_id"));

                return com_eval_detail;
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

    public void insertComEvalDetail(ComEvalDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Insert Into com_eval_detail(cev_id, mat_id, unit, quantity, suggested_supplier)"
                    + " Values (" + bean.getCevId() + "," + bean.getMatId() + ",'" + bean.getUnit()
                    + "'," + bean.getQuantity() + ",'" + bean.getSuggestedSupplier() + "')";
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateComEvalDetail(ComEvalDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        try {
            String sql = "Update com_eval_detail Set "
                    + " cev_id=" + bean.getCevId()
                    + //                   ", det_id_tp=" + bean.getDetIdTp() +
                    ", unit='" + bean.getUnit() + "'"
                    + //            ", quantity=" + bean.getQuantity() +
                    ", price='" + bean.getPrice() + "'"
                    + ", price_after='" + bean.getPriceAfter() + "'"
                    + ", total='" + bean.getTotal() + "'"
                    //                    + " Where cev_id=" + bean.getCevId() + " and mat_id = " + bean.getMatId();
                    + " Where det_id=" + bean.getDetId();
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public int deleteComEvalDetail(int detId) throws Exception {
        int result = 0;
        try {
            String sql = "Delete From com_eval_detail " + " Where det_id=" + detId;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleComEvalDetail(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "suggested_supplier";
                break;
        }
        ResultSet rs = null;

        String sql = "Select * From com_eval_detail Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by det_id DESC";

        ArrayList com_eval_detailList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalDetailBean com_eval_detail = null;
            while (rs.next()) {
                com_eval_detail = new ComEvalDetailBean();
                com_eval_detail.setDetId(rs.getInt("det_id"));
                com_eval_detail.setCevId(rs.getInt("cev_id"));
                com_eval_detail.setMatId(rs.getInt("mat_id"));
                com_eval_detail.setUnit(rs.getString("unit"));
                com_eval_detail.setQuantity(rs.getDouble("quantity"));
                com_eval_detail.setSuggestedSupplier(rs.getString("suggested_supplier"));
                com_eval_detailList.add(com_eval_detail);
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
        return com_eval_detailList;

    }

    public ArrayList searchAdvComEvalDetail(ComEvalDetailBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select * From com_eval_detail Where 1 ";

        if (bean.getDetId() != 0) {
            sql = sql + " AND det_id = " + bean.getDetId();
        }
        if (bean.getCevId() != 0) {
            sql = sql + " AND cev_id = " + bean.getCevId();
        }
        if (bean.getMatId() != 0) {
            sql = sql + " AND mat_id = " + bean.getMatId();
        }
        if (!StringUtil.isBlankOrNull(bean.getUnit())) {
            sql = sql + " AND unit LIKE '%" + bean.getUnit() + "%'";
        }
        if (bean.getQuantity() != 0) {
            sql = sql + " AND quantity =" + bean.getQuantity();
        }
        if (!StringUtil.isBlankOrNull(bean.getSuggestedSupplier())) {
            sql = sql + " AND suggested_supplier LIKE '%" + bean.getSuggestedSupplier() + "%'";
        }
        sql = sql + " Order by det_id DESC";

        ArrayList com_eval_detailList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalDetailBean com_eval_detail = null;
            while (rs.next()) {
                com_eval_detail = new ComEvalDetailBean();
                com_eval_detail.setDetId(rs.getInt("det_id"));
                com_eval_detail.setCevId(rs.getInt("cev_id"));
                com_eval_detail.setMatId(rs.getInt("mat_id"));
                com_eval_detail.setUnit(rs.getString("unit"));
                com_eval_detail.setQuantity(rs.getDouble("quantity"));
                com_eval_detail.setSuggestedSupplier(rs.getString("suggested_supplier"));
                com_eval_detailList.add(com_eval_detail);
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
        return com_eval_detailList;
    }

    public ArrayList getComEvalDetailOk(int detId) throws Exception {
        ResultSet rs = null;
        String sql = "SELECT c2.* FROM com_eval_detail AS c1, com_eval_detail AS c2 WHERE c1.det_id=" + detId
                + " AND c1.cev_id=c2.cev_id AND c1.mat_id=c2.mat_id";

        ArrayList com_eval_materialList = new ArrayList();
        try {
            rs = DBUtil.executeQuery(sql);
            ComEvalDetailBean material = null;
            while (rs.next()) {
                material = new ComEvalDetailBean();
                material.setDetId(rs.getInt("det_id"));
                material.setCevId(rs.getInt("cev_id"));
                material.setMatId(rs.getInt("mat_id"));
                material.setQuantity(rs.getDouble("quantity"));
                material.setUnit(rs.getString("unit"));
                material.setPrice(rs.getDouble("price") + "");
                material.setPriceAfter(rs.getDouble("price_after") + "");
                material.setTotal(rs.getDouble("total") + "");
                com_eval_materialList.add(material);
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
        return com_eval_materialList;

    }
}
