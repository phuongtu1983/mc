/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.EmcoBean;
import com.venus.mc.bean.EmcoDetailBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class EmcoDAO extends BasicDAO {

    public ArrayList getEmcos()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select ematerial_carry_out.* From ematerial_carry_out";

        ArrayList emcoList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EmcoBean emco = null;
            while (rs.next()) {
                emco = new EmcoBean();
                emco.setEmcoId(rs.getInt("emco_id"));
                emco.setDepartment(rs.getString("department"));
                emco.setEmcoNumber(rs.getString("emco_number"));
                emco.setStatus(rs.getInt("status"));
                emco.setCarryOutDate(DateUtil.formatDate(rs.getDate("carry_out_date"), "dd/MM/yyyy"));
                emco.setCarryOutHour(rs.getString("carry_out_hour"));
                emco.setCarryOutMinute(rs.getString("carry_out_minute"));
                emco.setResult(rs.getInt("result"));
                emco.setDescCarryOut(rs.getString("desc_carry_out"));
                emco.setDescNotCarryOut(rs.getString("desc_not_carry_out"));
                emco.setExplanation(rs.getString("explanation"));
                emcoList.add(emco);
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
        return emcoList;
    }

    public EmcoBean getEmco(int emcoId) throws Exception {
        ResultSet rs = null;

        String sql = "Select ematerial_carry_out.* From ematerial_carry_out Where emco_id = " + emcoId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EmcoBean emco = new EmcoBean();
                emco.setEmcoId(rs.getInt("emco_id"));
                emco.setEmcoNumber(rs.getString("emco_number"));
                emco.setDepartment(rs.getString("department"));
                emco.setStatus(rs.getInt("status"));
                emco.setCarryOutDate(DateUtil.formatDate(rs.getDate("carry_out_date"), "dd/MM/yyyy"));
                emco.setCarryOutHour(rs.getString("carry_out_hour"));
                emco.setCarryOutMinute(rs.getString("carry_out_minute"));
                emco.setResult(rs.getInt("result"));
                emco.setDescCarryOut(rs.getString("desc_carry_out"));
                emco.setDescNotCarryOut(rs.getString("desc_not_carry_out"));
                emco.setExplanation(rs.getString("explanation"));

                return emco;
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

    public EmcoBean getEmcoByNumber(String emcoNumber) throws Exception {
        ResultSet rs = null;

        String sql = "Select ematerial_carry_out.* From ematerial_carry_out Where emco_number = '" + emcoNumber + "'";


        try {




            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EmcoBean emco = new EmcoBean();
                emco.setEmcoId(rs.getInt("emco_id"));

                return emco;
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

    public int insertEmco(EmcoBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }



        try {
            String sql = "";



            String carry_out_date = "null";
            if (!GenericValidator.isBlankOrNull(bean.getCarryOutDate())) {
                carry_out_date = "STR_TO_DATE('" + bean.getCarryOutDate() + "','%d/%m/%Y')";
            }

            sql = "Insert Into ematerial_carry_out(created_emp, department, emco_number, status, carry_out_date, carry_out_hour, carry_out_minute, result, desc_carry_out, desc_not_carry_out, explanation)"
                    + " Values (" + bean.getCreatedEmp() + ",'" + bean.getDepartment() + "','" + bean.getEmcoNumber() + "'," + bean.getStatus()
                    + "," + carry_out_date + ",'" + bean.getCarryOutHour() + "','" + bean.getCarryOutMinute() + "'," + bean.getResult()
                    + ",'" + bean.getDescCarryOut() + "','" + bean.getDescNotCarryOut() + "','" + bean.getExplanation() + "')";

            //System.out.println("sql ====" + sql);
            DBUtil.executeUpdate(sql);
//            return DBUtil.executeInsert(sql);
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
        return 0;
    }

    public void updateEmco(EmcoBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {


            String sql = "";

            String carry_out_date = "null";
            if (!GenericValidator.isBlankOrNull(bean.getCarryOutDate())) {
                carry_out_date = "STR_TO_DATE('" + bean.getCarryOutDate() + "','%d/%m/%Y')";
            }

            sql = "Update ematerial_carry_out Set "
                    + " emco_number = '" + bean.getEmcoNumber() + "'"
                    + ", status = " + bean.getStatus()
                    + ", carry_out_date = " + carry_out_date
                    + ", carry_out_hour = '" + bean.getCarryOutHour() + "'"
                    + ", carry_out_minute = '" + bean.getCarryOutMinute() + "'"
                    + ", result = " + bean.getResult()
                    + ", desc_carry_out = '" + bean.getDescCarryOut() + "'"
                    + ", desc_not_carry_out = '" + bean.getDescNotCarryOut() + "'"
                    + ", explanation = '" + bean.getExplanation() + "'"
                    + " Where emco_id = " + bean.getEmcoId();

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

    public int deleteEmco(int emcoId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From ematerial_carry_out Where emco_id = " + emcoId;
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

    public ArrayList searchSimpleEmco(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "emco_number";
                break;

        }
        ResultSet rs = null;

        String sql = "Select ematerial_carry_out.* From ematerial_carry_out Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by emco_id DESC";

        ArrayList emcoList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EmcoBean emco = null;
            while (rs.next()) {
                emco = new EmcoBean();
                emco.setEmcoId(rs.getInt("emco_id"));
                emco.setEmcoNumber(rs.getString("emco_number"));
                emco.setDepartment(rs.getString("department"));
                emco.setStatus(rs.getInt("status"));
                emco.setCarryOutDate(DateUtil.formatDate(rs.getDate("carry_out_date"), "dd/MM/yyyy"));
                emco.setCarryOutHour(rs.getString("carry_out_hour"));
                emco.setCarryOutMinute(rs.getString("carry_out_minute"));
                emco.setResult(rs.getInt("result"));
                emco.setDescCarryOut(rs.getString("desc_carry_out"));
                emco.setDescNotCarryOut(rs.getString("desc_not_carry_out"));
                emco.setExplanation(rs.getString("explanation"));
                emcoList.add(emco);
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
        return emcoList;
    }

    public ArrayList searchAdvEmco(EmcoBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select ematerial_carry_out.* From ematerial_carry_out Where 1";

        if (!StringUtil.isBlankOrNull(bean.getDepartment())) {
            sql = sql + " AND department LIKE '%" + bean.getDepartment() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getEmcoNumber())) {
            sql = sql + " AND emco_number LIKE '%" + bean.getEmcoNumber() + "%'";
        }

        if (bean.getStatus() != 0) {
            sql = sql + " AND status = " + bean.getStatus();
        }

        if (!StringUtil.isBlankOrNull(bean.getCarryOutDate())) {
            sql = sql + " AND carry_out_date = STR_TO_DATE('" + bean.getCarryOutDate() + "','%d/%m/%Y')";
        }

        if (!StringUtil.isBlankOrNull(bean.getCarryOutHour())) {
            sql = sql + " AND carry_out_hour = '" + bean.getCarryOutHour();
        }

        if (!StringUtil.isBlankOrNull(bean.getCarryOutMinute())) {
            sql = sql + " AND carry_out_minute = '" + bean.getCarryOutMinute();
        }

        if (bean.getResult() != 0) {
            sql = sql + " AND result = " + bean.getResult();
        }

        if (!StringUtil.isBlankOrNull(bean.getDescCarryOut())) {
            sql = sql + " AND desc_carry_out LIKE '%" + bean.getDescCarryOut() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getDescNotCarryOut())) {
            sql = sql + " AND desc_not_carry_out LIKE '%" + bean.getDescNotCarryOut() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getExplanation())) {
            sql = sql + " AND explanation LIKE '%" + bean.getExplanation() + "%'";
        }

        sql = sql + " Order by emco_id DESC";

        ArrayList emcoList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EmcoBean emco = null;

            while (rs.next()) {
                emco = new EmcoBean();
                emco.setEmcoId(rs.getInt("emco_id"));
                emco.setEmcoNumber(rs.getString("emco_number"));
                emco.setDepartment(rs.getString("department"));
                emco.setStatus(rs.getInt("status"));
                emco.setCarryOutDate(DateUtil.formatDate(rs.getDate("carry_out_date"), "dd/MM/yyyy"));
                emco.setCarryOutHour(rs.getString("carry_out_hour"));
                emco.setCarryOutMinute(rs.getString("carry_out_minute"));
                emco.setResult(rs.getInt("result"));
                emco.setDescCarryOut(rs.getString("desc_carry_out"));
                emco.setDescNotCarryOut(rs.getString("desc_not_carry_out"));
                emco.setExplanation(rs.getString("explanation"));
                emcoList.add(emco);
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
        return emcoList;
    }

    public ArrayList getEmcoDetails()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select ematerial_carry_out_detail.* From ematerial_carry_out_detail Order by det_id ASC";

        ArrayList ematerial_carry_out_detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EmcoDetailBean emco_detail = null;
            while (rs.next()) {
                emco_detail = new EmcoDetailBean();
                emco_detail.setDetId(rs.getInt("det_id"));
                emco_detail.setEmcoId(rs.getInt("emco_id"));
                emco_detail.setEquipment(rs.getString("equipment"));
                emco_detail.setUnit(rs.getString("unit"));
                emco_detail.setQuantity(rs.getInt("quantity"));
                emco_detail.setSpec(rs.getString("spec"));
                emco_detail.setEmcDetailId(rs.getInt("emc_detail_id"));
                ematerial_carry_out_detailList.add(emco_detail);
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
        return ematerial_carry_out_detailList;
    }

    public ArrayList getEmcoDetailsByEmco(int emcoId) throws Exception {
        ResultSet rs = null;

        String sql = "Select ematerial_carry_out_detail.*, emc_id From ematerial_carry_out_detail left join ematerial_carry_on_detail on ematerial_carry_out_detail.emc_detail_id = ematerial_carry_on_detail.det_id Where emco_id  = " + emcoId;

        ArrayList ematerial_carry_out_detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EmcoDetailBean emco_detail = null;
            while (rs.next()) {
                emco_detail = new EmcoDetailBean();
                emco_detail.setDetId(rs.getInt("det_id"));
                emco_detail.setEmcoId(rs.getInt("emco_id"));
                emco_detail.setEquipment(rs.getString("equipment"));
                emco_detail.setUnit(rs.getString("unit"));
                emco_detail.setQuantity(rs.getInt("quantity"));
                emco_detail.setSpec(rs.getString("spec"));
                emco_detail.setEmcDetailId(rs.getInt("emc_detail_id"));
                emco_detail.setEmcId(rs.getInt("emc_id"));
                ematerial_carry_out_detailList.add(emco_detail);
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
        return ematerial_carry_out_detailList;
    }

    public int getEmcDetailId(int emcId, String equipment) throws Exception {
        ResultSet rs = null;

        String sql = "Select det_id From ematerial_carry_on_detail Where emc_id = " + emcId + " And equipment = '" + equipment + "'";

        int result = 0;
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                result = rs.getInt("det_id");
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
        return result;
    }

    public EmcoDetailBean getEmcoDetail(int emcoId) throws Exception {
        ResultSet rs = null;

        String sql = "Select ematerial_carry_out_detail.* From ematerial_carry_out_detail Where emco_id = " + emcoId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EmcoDetailBean emco_detail = new EmcoDetailBean();
                emco_detail.setDetId(rs.getInt("det_id"));
                emco_detail.setEmcoId(rs.getInt("emco_id"));
                emco_detail.setEquipment(rs.getString("equipment"));
                emco_detail.setUnit(rs.getString("unit"));
                emco_detail.setQuantity(rs.getInt("quantity"));
                emco_detail.setSpec(rs.getString("spec"));
                emco_detail.setEmcDetailId(rs.getInt("emc_detail_id"));

                return emco_detail;
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

    public void insertEmcoDetail(EmcoDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }




        try {
            String sql = "";



            if (GenericValidator.isBlankOrNull(bean.getUnit())) {
                bean.setUnit("");
            }

            if (GenericValidator.isBlankOrNull(bean.getSpec())) {
                bean.setSpec("");
            }

            sql = "Insert Into ematerial_carry_out_detail(emco_id, equipment, unit, quantity, spec, emc_detail_id)"
                    + " Values (" + bean.getEmcoId() + ",'" + bean.getEquipment() + "','" + bean.getUnit()
                    + "'," + bean.getQuantity() + ",'" + bean.getSpec() + "'," + bean.getEmcDetailId() + ")";

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

    public void updateEmcoDetail(EmcoDetailBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {



            if (GenericValidator.isBlankOrNull(bean.getUnit())) {
                bean.setUnit("");
            }

            if (GenericValidator.isBlankOrNull(bean.getSpec())) {
                bean.setSpec("");
            }

            String sql = "Update ematerial_carry_out_detail Set "
                    + " emco_id = " + bean.getEmcoId()
                    + ", equipment = '" + bean.getEquipment() + "'"
                    + ", unit = '" + bean.getUnit() + "'"
                    + ", quantity = " + bean.getQuantity()
                    + ", spec = '" + bean.getSpec() + "'"
                    + ", emc_detail_id = " + bean.getEmcDetailId()
                    + " Where det_id = " + bean.getDetId();

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

    public int deleteEmcoDetail(int detId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From ematerial_carry_out_detail Where det_id = " + detId;
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
