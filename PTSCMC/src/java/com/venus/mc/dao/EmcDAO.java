/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.EmcBean;
import com.venus.mc.bean.EmcDetailBean;
import com.venus.mc.bean.EmcReportBean;
import com.venus.mc.bean.McWarningBean;
import com.venus.mc.database.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author kngo
 */
public class EmcDAO extends BasicDAO {

    public ArrayList getEmcsByDept(String department)
            throws Exception {
        ResultSet rs = null;

        String sql = "Select ematerial_carry_on.* From ematerial_carry_on Where department = '" + department + "'";

        ArrayList emcList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EmcBean emc = null;
            while (rs.next()) {
                emc = new EmcBean();
                emc.setEmcId(rs.getInt("emc_id"));
                emc.setEmcNumber(rs.getString("emc_number"));
                emc.setDepartment(rs.getString("department"));
                emc.setStatus(rs.getInt("status"));
                emc.setCarryOutDatePlan(DateUtil.formatDate(rs.getDate("carry_out_date_plan"), "dd/MM/yyyy"));
                emc.setCarryOnDate(DateUtil.formatDate(rs.getDate("carry_on_date"), "dd/MM/yyyy"));
                emc.setCarryOnHour(rs.getString("carry_on_hour"));
                emc.setCarryOnMinute(rs.getString("carry_on_minute"));
                emc.setResult(rs.getInt("result"));
                emc.setDescCarryOn(rs.getString("desc_carry_on"));
                emc.setDescNotCarryOn(rs.getString("desc_not_carry_on"));
                emc.setExplanation(rs.getString("explanation"));
                emcList.add(emc);
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
        return emcList;
    }

    public ArrayList getDepartments()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select distinct department From ematerial_carry_on Where status = 2";

        ArrayList deptList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            LabelValueBean value;
            value = new LabelValueBean();
            while (rs.next()) {
                value = new LabelValueBean();
                value.setValue(rs.getString("department"));
                value.setLabel(rs.getString("department"));
                deptList.add(value);
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
        return deptList;
    }

    public ArrayList getDepartmentsNoStatus()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select distinct department From ematerial_carry_on";

        ArrayList deptList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            LabelValueBean value;
            value = new LabelValueBean();
            while (rs.next()) {
                value = new LabelValueBean();
                value.setValue(rs.getString("department"));
                value.setLabel(rs.getString("department"));
                deptList.add(value);
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
        return deptList;
    }

    public ArrayList getEmcReports()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select ematerial_carry_on.emc_id, ematerial_carry_on.department, ematerial_carry_on_detail.equipment, emc_number, carry_on_date, carry_out_date_plan, desc_carry_on, desc_not_carry_on, ematerial_carry_out.emco_id, emco_number, carry_out_date, desc_carry_out, desc_not_carry_out "
                + "From ((ematerial_carry_on left join ematerial_carry_on_detail on ematerial_carry_on.emc_id = ematerial_carry_on_detail.emc_id) "
                + "left join ematerial_carry_out_detail on ematerial_carry_on_detail.det_id = ematerial_carry_out_detail.emc_detail_id) "
                + "left join ematerial_carry_out on ematerial_carry_out_detail.emco_id = ematerial_carry_out.emco_id";

        ArrayList mcList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EmcReportBean emc = null;
            int no = 1;
            while (rs.next()) {
                emc = new EmcReportBean();
                emc.setNo(no);
                emc.setEmcId(rs.getInt("emc_id"));
                emc.setDepartment(rs.getString("department"));
                emc.setEmcNumber(rs.getString("emc_number"));
                emc.setCarryOutDatePlan(DateUtil.formatDate(rs.getDate("carry_out_date_plan"), "dd/MM/yyyy"));
                emc.setCarryOutDate(DateUtil.formatDate(rs.getDate("carry_out_date"), "dd/MM/yyyy"));
                emc.setDescCarryOut(rs.getString("desc_carry_out"));
                emc.setDescNotCarryOut(rs.getString("desc_not_carry_out"));

                emc.setEmcoId(rs.getInt("emco_id"));
                emc.setEmcoNumber(rs.getString("emco_number"));
                emc.setCarryOnDate(DateUtil.formatDate(rs.getDate("carry_on_date"), "dd/MM/yyyy"));
                emc.setDescCarryOn(rs.getString("desc_carry_on"));
                emc.setDescNotCarryOn(rs.getString("desc_not_carry_on"));

                no++;
                mcList.add(emc);
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
        return mcList;
    }

    public ArrayList getEmcs()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select ematerial_carry_on.* From ematerial_carry_on";

        ArrayList emcList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EmcBean emc = null;
            while (rs.next()) {
                emc = new EmcBean();
                emc.setEmcId(rs.getInt("emc_id"));
                emc.setEmcNumber(rs.getString("emc_number"));
                emc.setDepartment(rs.getString("department"));
                emc.setStatus(rs.getInt("status"));
                emc.setCarryOutDatePlan(DateUtil.formatDate(rs.getDate("carry_out_date_plan"), "dd/MM/yyyy"));
                emc.setCarryOnDate(DateUtil.formatDate(rs.getDate("carry_on_date"), "dd/MM/yyyy"));
                emc.setCarryOnHour(rs.getString("carry_on_hour"));
                emc.setCarryOnMinute(rs.getString("carry_on_minute"));
                emc.setResult(rs.getInt("result"));
                emc.setDescCarryOn(rs.getString("desc_carry_on"));
                emc.setDescNotCarryOn(rs.getString("desc_not_carry_on"));
                emc.setExplanation(rs.getString("explanation"));
                emcList.add(emc);
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
        return emcList;
    }

    public EmcBean getEmc(int emcId) throws Exception {
        ResultSet rs = null;

        String sql = "Select ematerial_carry_on.* From ematerial_carry_on Where emc_id = " + emcId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EmcBean emc = new EmcBean();
                emc.setEmcId(rs.getInt("emc_id"));
                emc.setEmcNumber(rs.getString("emc_number"));
                emc.setDepartment(rs.getString("department"));
                emc.setStatus(rs.getInt("status"));
                emc.setCarryOutDatePlan(DateUtil.formatDate(rs.getDate("carry_out_date_plan"), "dd/MM/yyyy"));
                emc.setCarryOnDate(DateUtil.formatDate(rs.getDate("carry_on_date"), "dd/MM/yyyy"));
                emc.setCarryOnHour(rs.getString("carry_on_hour"));
                emc.setCarryOnMinute(rs.getString("carry_on_minute"));
                emc.setResult(rs.getInt("result"));
                emc.setDescCarryOn(rs.getString("desc_carry_on"));
                emc.setDescNotCarryOn(rs.getString("desc_not_carry_on"));
                emc.setExplanation(rs.getString("explanation"));

                return emc;
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

    public EmcBean getEmcByNumber(String emcNumber) throws Exception {
        ResultSet rs = null;

        String sql = "Select ematerial_carry_on.* From ematerial_carry_on Where emc_number = '" + emcNumber + "'";


        try {




            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EmcBean emc = new EmcBean();
                emc.setEmcId(rs.getInt("emc_id"));

                return emc;
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

    public int insertEmc(EmcBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }



        try {
            String sql = "";

//            

            String carry_on_date = "null";
            if (!GenericValidator.isBlankOrNull(bean.getCarryOnDate())) {
                carry_on_date = "STR_TO_DATE('" + bean.getCarryOnDate() + "','%d/%m/%Y')";
            }

            String carry_out_date_plan = "null";
            if (!GenericValidator.isBlankOrNull(bean.getCarryOutDatePlan())) {
                carry_out_date_plan = "STR_TO_DATE('" + bean.getCarryOutDatePlan() + "','%d/%m/%Y')";
            }

            sql = "Insert Into ematerial_carry_on(created_emp, department, emc_number, status, carry_out_date_plan, carry_on_date, carry_on_hour, carry_on_minute, result, desc_carry_on, desc_not_carry_on, explanation)"
                    + " Values (" + bean.getCreatedEmp() + ",'" + bean.getDepartment() + "','" + bean.getEmcNumber()
                    + "'," + bean.getStatus() + "," + carry_out_date_plan + "," + carry_on_date
                    + ",'" + bean.getCarryOnHour() + "','" + bean.getCarryOnMinute() + "'," + bean.getResult() + ",'" + bean.getDescCarryOn()
                    + "','" + bean.getDescNotCarryOn() + "','" + bean.getExplanation() + "')";

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

    public void updateEmc(EmcBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {


            String sql = "";

            String carry_on_date = "null";
            if (!GenericValidator.isBlankOrNull(bean.getCarryOnDate())) {
                carry_on_date = "STR_TO_DATE('" + bean.getCarryOnDate() + "','%d/%m/%Y')";
            }

            String carry_out_date_plan = "null";
            if (!GenericValidator.isBlankOrNull(bean.getCarryOutDatePlan())) {
                carry_out_date_plan = "STR_TO_DATE('" + bean.getCarryOutDatePlan() + "','%d/%m/%Y')";
            }

            sql = "Update ematerial_carry_on Set "
                    + " department = '" + bean.getDepartment() + "'"
                    + ", emc_number = '" + bean.getEmcNumber() + "'"
                    + ", status = " + bean.getStatus()
                    + ", carry_out_date_plan = " + carry_out_date_plan
                    + ", carry_on_date = " + carry_on_date
                    + ", carry_on_hour = '" + bean.getCarryOnHour() + "'"
                    + ", carry_on_minute = '" + bean.getCarryOnMinute() + "'"
                    + ", result = " + bean.getResult()
                    + ", desc_carry_on = '" + bean.getDescCarryOn() + "'"
                    + ", desc_not_carry_on = '" + bean.getDescNotCarryOn() + "'"
                    + ", explanation = '" + bean.getExplanation() + "'"
                    + " Where emc_id = " + bean.getEmcId();

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

    public int deleteEmc(int emcId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From ematerial_carry_on Where emc_id = " + emcId;
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

    public ArrayList searchSimpleEmc(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "emc_number";
                break;
        }
        ResultSet rs = null;

        String sql = "Select ematerial_carry_on.* From ematerial_carry_on Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by emc_id DESC";

        ArrayList emcList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EmcBean emc = null;
            while (rs.next()) {
                emc = new EmcBean();
                emc.setEmcId(rs.getInt("emc_id"));
                emc.setEmcNumber(rs.getString("emc_number"));
                emc.setDepartment(rs.getString("department"));
                emc.setStatus(rs.getInt("status"));
                emc.setCarryOutDatePlan(DateUtil.formatDate(rs.getDate("carry_out_date_plan"), "dd/MM/yyyy"));
                emc.setCarryOnDate(DateUtil.formatDate(rs.getDate("carry_on_date"), "dd/MM/yyyy"));
                emc.setCarryOnHour(rs.getString("carry_on_hour"));
                emc.setCarryOnMinute(rs.getString("carry_on_minute"));
                emc.setResult(rs.getInt("result"));
                emc.setDescCarryOn(rs.getString("desc_carry_on"));
                emc.setDescNotCarryOn(rs.getString("desc_not_carry_on"));
                emc.setExplanation(rs.getString("explanation"));
                emcList.add(emc);
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
        return emcList;
    }

    public ArrayList searchAdvEmc(EmcBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select ematerial_carry_on.* From ematerial_carry_on Where 1";

        if (!StringUtil.isBlankOrNull(bean.getDepartment())) {
            sql = sql + " AND department LIKE '%" + bean.getDepartment() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getEmcNumber())) {
            sql = sql + " AND emc_number LIKE '%" + bean.getEmcNumber() + "%'";
        }

        if (bean.getStatus() != 0) {
            sql = sql + " AND status = " + bean.getStatus();
        }

        if (!StringUtil.isBlankOrNull(bean.getCarryOutDatePlan())) {
            sql = sql + " AND carry_out_date_plan = STR_TO_DATE('" + bean.getCarryOutDatePlan() + "','%d/%m/%Y')";
        }

        if (!StringUtil.isBlankOrNull(bean.getCarryOnDate())) {
            sql = sql + " AND carry_on_date = STR_TO_DATE('" + bean.getCarryOnDate() + "','%d/%m/%Y')";
        }

        if (!StringUtil.isBlankOrNull(bean.getCarryOnHour())) {
            sql = sql + " AND carry_on_hour = '" + bean.getCarryOnHour();
        }

        if (!StringUtil.isBlankOrNull(bean.getCarryOnMinute())) {
            sql = sql + " AND carry_on_minute = '" + bean.getCarryOnMinute();
        }

        if (bean.getResult() != 0) {
            sql = sql + " AND result = " + bean.getResult();
        }

        if (!StringUtil.isBlankOrNull(bean.getDescCarryOn())) {
            sql = sql + " AND desc_carry_on LIKE '%" + bean.getDescCarryOn() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getDescNotCarryOn())) {
            sql = sql + " AND desc_not_carry_on LIKE '%" + bean.getDescNotCarryOn() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getExplanation())) {
            sql = sql + " AND explanation LIKE '%" + bean.getExplanation() + "%'";
        }

        sql = sql + " Order by emc_id DESC";

        ArrayList emcList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EmcBean emc = null;

            while (rs.next()) {
                emc = new EmcBean();
                emc.setEmcId(rs.getInt("emc_id"));
                emc.setEmcNumber(rs.getString("emc_number"));
                emc.setDepartment(rs.getString("department"));
                emc.setStatus(rs.getInt("status"));
                emc.setCarryOutDatePlan(DateUtil.formatDate(rs.getDate("carry_out_date_plan"), "dd/MM/yyyy"));
                emc.setCarryOnDate(DateUtil.formatDate(rs.getDate("carry_on_date"), "dd/MM/yyyy"));
                emc.setCarryOnHour(rs.getString("carry_on_hour"));
                emc.setCarryOnMinute(rs.getString("carry_on_minute"));
                emc.setResult(rs.getInt("result"));
                emc.setDescCarryOn(rs.getString("desc_carry_on"));
                emc.setDescNotCarryOn(rs.getString("desc_not_carry_on"));
                emc.setExplanation(rs.getString("explanation"));
                emcList.add(emc);
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
        return emcList;
    }

    public ArrayList getEmcDetails()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select ematerial_carry_on_detail.* From ematerial_carry_out_detail Order by det_id ASC";

        ArrayList material_carry_out_detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EmcDetailBean material_carry_out_detail = null;
            while (rs.next()) {
                material_carry_out_detail = new EmcDetailBean();
                material_carry_out_detail.setDetId(rs.getInt("det_id"));
                material_carry_out_detail.setEmcId(rs.getInt("emc_id"));
                material_carry_out_detail.setEquipment(rs.getString("equipment"));
                material_carry_out_detail.setUnit(rs.getString("unit"));
                material_carry_out_detail.setQuantity(rs.getInt("quantity"));
                material_carry_out_detail.setSpec(rs.getString("spec"));
                material_carry_out_detailList.add(material_carry_out_detail);
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
        return material_carry_out_detailList;
    }

    public ArrayList getEmcDetailsByEmc(int emcId) throws Exception {
        ResultSet rs = null;

        String sql = "Select ematerial_carry_on_detail.* From ematerial_carry_on_detail Where emc_id  = " + emcId;

        ArrayList material_carry_out_detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EmcDetailBean material_carry_out_detail = null;
            while (rs.next()) {
                material_carry_out_detail = new EmcDetailBean();
                material_carry_out_detail.setDetId(rs.getInt("det_id"));
                material_carry_out_detail.setEmcId(rs.getInt("emc_id"));
                material_carry_out_detail.setEquipment(rs.getString("equipment"));
                material_carry_out_detail.setUnit(rs.getString("unit"));
                material_carry_out_detail.setQuantity(rs.getInt("quantity"));
                material_carry_out_detail.setSpec(rs.getString("spec"));
                material_carry_out_detailList.add(material_carry_out_detail);
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
        return material_carry_out_detailList;
    }

    public EmcDetailBean getEmcDetail(int emcId) throws Exception {
        ResultSet rs = null;

        String sql = "Select ematerial_carry_on_detail.* From ematerial_carry_on_detail Where emc_id = " + emcId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EmcDetailBean material_carry_out_detail = new EmcDetailBean();
                material_carry_out_detail.setDetId(rs.getInt("det_id"));
                material_carry_out_detail.setEmcId(rs.getInt("emc_id"));
                material_carry_out_detail.setEquipment(rs.getString("equipment"));
                material_carry_out_detail.setUnit(rs.getString("unit"));
                material_carry_out_detail.setQuantity(rs.getInt("quantity"));
                material_carry_out_detail.setSpec(rs.getString("spec"));

                return material_carry_out_detail;
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

    public void insertEmcDetail(EmcDetailBean bean) throws Exception {
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

            sql = "Insert Into ematerial_carry_on_detail(emc_id, equipment, unit, quantity, spec)"
                    + " Values (" + bean.getEmcId() + ",'" + bean.getEquipment() + "','" + bean.getUnit()
                    + "'," + bean.getQuantity() + ",'" + bean.getSpec() + "')";

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

    public void updateEmcDetail(EmcDetailBean bean) throws Exception {
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

            String sql = "Update ematerial_carry_on_detail Set "
                    + " emc_id = " + bean.getEmcId()
                    + ", equipment = '" + bean.getEquipment() + "'"
                    + ", unit = '" + bean.getUnit() + "'"
                    + ", quantity = " + bean.getQuantity()
                    + ", spec = '" + bean.getSpec() + "'"
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

    public int deleteEmcDetail(int detId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From ematerial_carry_on_detail Where det_id = " + detId;
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

    public ArrayList getMaterialCarryOnForWarning(int dateAmount) throws Exception {
        ResultSet rs = null;

        String sql = "select e.manage_code, m.name_vn, mcdet.quantity, mc.mc_number, mc.emc_id, mc.carry_out_date_plan"
                + " from ematerial_carry_on as mc "
                + " left join ematerial_carry_on_detail as mcdet on mcdet.emc_id=mc.emc_id"
                + " left join equipment as e on e.equ_id=mcodet.equ_id"
                + " left join material as m on m.mat_id=e.mat_id"
                + " where mc.warned=0 and datediff(sysdate(),carry_out_date_plan)>=" + dateAmount;

        ArrayList list = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            McWarningBean detail = null;
            while (rs.next()) {
                detail = new McWarningBean();
                detail.setMcId(rs.getInt("emc_id"));
                detail.setManageCode(rs.getString("manage_code"));
                detail.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
                detail.setQuantity(rs.getDouble("quantity"));
                detail.setMcNumber(rs.getString("mc_number"));
                detail.setOutDatePlan(DateUtil.formatDate(rs.getDate("carry_out_date_plan"), "dd/MM/yyyy"));
                list.add(detail);
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
        return list;
    }

    public void updateMcWarnedStatus(String ids) throws Exception {



        try {


            String sql = "update ematerial_carry_on set warned=1 where emc_id in (" + ids + ")";
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
}
