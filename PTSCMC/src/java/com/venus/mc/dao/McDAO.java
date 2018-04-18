/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.McBean;
import com.venus.mc.bean.McDetailBean;
import com.venus.mc.bean.McReportBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.util.MCUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class McDAO extends BasicDAO {

    public ArrayList getMcReports()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select material_carry_out.mco_id, material_carry_out.org_id, name, used_code, name_vn, material_carry_out_detail.equ_id, mco_number, carry_out_date, carry_on_date_plan, desc_carry_out, desc_not_carry_out, material_carry_on.mc_id, mc_number, carry_on_date, desc_carry_on, desc_not_carry_on "
                + "From (((((material_carry_out left join organization on material_carry_out.org_id = organization.org_id) "
                + "left join material_carry_out_detail on material_carry_out.mco_id = material_carry_out_detail.mco_id) "
                + "left join material_carry_on_detail on material_carry_out_detail.det_id = material_carry_on_detail.mco_detail_id) "
                + "left join material_carry_on on material_carry_on_detail.mc_id = material_carry_on.mc_id) "
                + "left join equipment on material_carry_out_detail.equ_id = equipment.equ_id) "
                + "left join material on equipment.mat_id = material.mat_id";

        ArrayList mcList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            McReportBean mc = null;
            int no = 1;
            while (rs.next()) {
                mc = new McReportBean();
                mc.setNo(no);
                mc.setMcId(rs.getInt("mc_id"));
                mc.setOrgId(rs.getInt("org_id"));
                mc.setOrgName(rs.getString("name"));
                mc.setMcNumber(rs.getString("mc_number"));
                mc.setCarryOnDate(DateUtil.formatDate(rs.getDate("carry_on_date"), "dd/MM/yyyy"));
                mc.setDescCarryOn(rs.getString("desc_carry_on"));
                mc.setDescNotCarryOn(rs.getString("desc_not_carry_on"));

                mc.setMcoId(rs.getInt("mco_id"));
                mc.setMcoNumber(rs.getString("mco_number"));
                mc.setCarryOnDatePlan(DateUtil.formatDate(rs.getDate("carry_on_date_plan"), "dd/MM/yyyy"));
                mc.setCarryOutDate(DateUtil.formatDate(rs.getDate("carry_out_date"), "dd/MM/yyyy"));
                mc.setDescCarryOut(rs.getString("desc_carry_out"));
                mc.setDescNotCarryOut(rs.getString("desc_not_carry_out"));
                no++;
                mcList.add(mc);
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

    public ArrayList getMcs()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select material_carry_on.*, name From material_carry_on left join organization on material_carry_on.org_id = organization.org_id";

        ArrayList mcList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            McBean mc = null;
            while (rs.next()) {
                mc = new McBean();
                mc.setMcId(rs.getInt("mc_id"));
                mc.setOrgId(rs.getInt("org_id"));
                mc.setOrgName(rs.getString("name"));
                mc.setMcNumber(rs.getString("mc_number"));
                mc.setKind(rs.getInt("kind"));
                if (rs.getInt("kind") == 1) {
                    mc.setKindString(MCUtil.getBundleString("message.mc.kind1"));
                } else if (rs.getInt("kind") == 2) {
                    mc.setKindString(MCUtil.getBundleString("message.mc.kind2"));
                }
                mc.setStatus(rs.getInt("status"));
                mc.setCarryOnDate(DateUtil.formatDate(rs.getDate("carry_on_date"), "dd/MM/yyyy"));
                mc.setCarryOnHour(rs.getString("carry_on_hour"));
                mc.setCarryOnMinute(rs.getString("carry_on_minute"));
                mc.setResult(rs.getInt("result"));
                mc.setDescCarryOn(rs.getString("desc_carry_on"));
                mc.setDescNotCarryOn(rs.getString("desc_not_carry_on"));
                mc.setExplanation(rs.getString("explanation"));
                mcList.add(mc);
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

    public McBean getMc(int mcId) throws Exception {
        ResultSet rs = null;

        String sql = "Select material_carry_on.*, name From material_carry_on left join organization on material_carry_on.org_id = organization.org_id Where mc_id = " + mcId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                McBean mc = new McBean();
                mc.setMcId(rs.getInt("mc_id"));
                mc.setOrgId(rs.getInt("org_id"));
                mc.setOrgName(rs.getString("name"));
                mc.setMcNumber(rs.getString("mc_number"));
                mc.setKind(rs.getInt("kind"));
                if (rs.getInt("kind") == 1) {
                    mc.setKindString(MCUtil.getBundleString("message.mc.kind1"));
                } else if (rs.getInt("kind") == 2) {
                    mc.setKindString(MCUtil.getBundleString("message.mc.kind2"));
                }
                mc.setStatus(rs.getInt("status"));
                mc.setCarryOnDate(DateUtil.formatDate(rs.getDate("carry_on_date"), "dd/MM/yyyy"));
                mc.setCarryOnHour(rs.getString("carry_on_hour"));
                mc.setCarryOnMinute(rs.getString("carry_on_minute"));
                mc.setResult(rs.getInt("result"));
                mc.setDescCarryOn(rs.getString("desc_carry_on"));
                mc.setDescNotCarryOn(rs.getString("desc_not_carry_on"));
                mc.setExplanation(rs.getString("explanation"));

                return mc;
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

    public McBean getMcByNumber(String mcNumber) throws Exception {
        ResultSet rs = null;

        String sql = "Select material_carry_on.* From material_carry_on Where mc_number = '" + mcNumber + "'";


        try {




            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                McBean mc = new McBean();
                mc.setMcId(rs.getInt("mc_id"));

                return mc;
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

    public int insertMc(McBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }



        try {
            String sql = "";



            String carry_on_date = "null";
            if (!GenericValidator.isBlankOrNull(bean.getCarryOnDate())) {
                carry_on_date = "STR_TO_DATE('" + bean.getCarryOnDate() + "','%d/%m/%Y')";
            }

            sql = "Insert Into material_carry_on(created_emp, org_id, mc_number, kind, status, carry_on_date, carry_on_hour, carry_on_minute, result, desc_carry_on, desc_not_carry_on, explanation)"
                    + " Values (" + bean.getCreatedEmp() + "," + bean.getOrgId()
                    + ",'" + bean.getMcNumber() + "'," + bean.getKind() + "," + bean.getStatus() + "," + carry_on_date
                    + ",'" + bean.getCarryOnHour() + "','" + bean.getCarryOnMinute() + "'," + bean.getResult() + ",'" + bean.getDescCarryOn()
                    + "','" + bean.getDescNotCarryOn() + "','" + bean.getExplanation() + "')";

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

    public void updateMc(McBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {


            String sql = "";

            String carry_on_date = "null";
            if (!GenericValidator.isBlankOrNull(bean.getCarryOnDate())) {
                carry_on_date = "STR_TO_DATE('" + bean.getCarryOnDate() + "','%d/%m/%Y')";
            }

            sql = "Update material_carry_on Set "
                    + " mc_number = '" + bean.getMcNumber() + "'"
                    + ", kind = " + bean.getKind()
                    + ", status = " + bean.getStatus()
                    + ", carry_on_date = " + carry_on_date
                    + ", carry_on_hour = '" + bean.getCarryOnHour() + "'"
                    + ", carry_on_minute = '" + bean.getCarryOnMinute() + "'"
                    + ", result = " + bean.getResult()
                    + ", desc_carry_on = '" + bean.getDescCarryOn() + "'"
                    + ", desc_not_carry_on = '" + bean.getDescNotCarryOn() + "'"
                    + ", explanation = '" + bean.getExplanation() + "'"
                    + " Where mc_id = " + bean.getMcId();

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

    public int deleteMc(int mcId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From material_carry_on Where mc_id = " + mcId;
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

    public ArrayList searchSimpleMc(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "mc_number";
                break;

        }
        ResultSet rs = null;

        String sql = "Select material_carry_on.*, name From material_carry_on left join organization on material_carry_on.org_id = organization.org_id Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by mc_id DESC";

        ArrayList mcList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            McBean mc = null;
            while (rs.next()) {
                mc = new McBean();
                mc.setMcId(rs.getInt("mc_id"));
                mc.setOrgId(rs.getInt("org_id"));
                mc.setOrgName(rs.getString("name"));
                mc.setMcNumber(rs.getString("mc_number"));
                mc.setKind(rs.getInt("kind"));
                if (rs.getInt("kind") == 1) {
                    mc.setKindString(MCUtil.getBundleString("message.mc.kind1"));
                } else if (rs.getInt("kind") == 2) {
                    mc.setKindString(MCUtil.getBundleString("message.mc.kind2"));
                }
                mc.setStatus(rs.getInt("status"));
                mc.setCarryOnDate(DateUtil.formatDate(rs.getDate("carry_on_date"), "dd/MM/yyyy"));
                mc.setCarryOnHour(rs.getString("carry_on_hour"));
                mc.setCarryOnMinute(rs.getString("carry_on_minute"));
                mc.setResult(rs.getInt("result"));
                mc.setDescCarryOn(rs.getString("desc_carry_on"));
                mc.setDescNotCarryOn(rs.getString("desc_not_carry_on"));
                mc.setExplanation(rs.getString("explanation"));
                mcList.add(mc);
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

    public ArrayList searchAdvMc(McBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select material_carry_on.*, name From material_carry_on left join organization on material_carry_on.org_id = organization.org_id Where 1";

        if (bean.getOrgId() != 0) {
            sql = sql + " AND material_carry_on.org_id = " + bean.getOrgId();
        }

        if (!StringUtil.isBlankOrNull(bean.getMcNumber())) {
            sql = sql + " AND mc_number LIKE '%" + bean.getMcNumber() + "%'";
        }

        if (bean.getKind() != 0) {
            sql = sql + " AND material_carry_on.kind = " + bean.getKind();
        }

        if (bean.getStatus() != 0) {
            sql = sql + " AND status = " + bean.getStatus();
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

        sql = sql + " Order by mc_id DESC";

        ArrayList mcList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            McBean mc = null;

            while (rs.next()) {
                mc = new McBean();
                mc.setMcId(rs.getInt("mc_id"));
                mc.setOrgId(rs.getInt("org_id"));
                mc.setOrgName(rs.getString("name"));
                mc.setMcNumber(rs.getString("mc_number"));
                mc.setKind(rs.getInt("kind"));
                if (rs.getInt("kind") == 1) {
                    mc.setKindString(MCUtil.getBundleString("message.mc.kind1"));
                } else if (rs.getInt("kind") == 2) {
                    mc.setKindString(MCUtil.getBundleString("message.mc.kind2"));
                }
                mc.setStatus(rs.getInt("status"));
                mc.setCarryOnDate(DateUtil.formatDate(rs.getDate("carry_on_date"), "dd/MM/yyyy"));
                mc.setCarryOnHour(rs.getString("carry_on_hour"));
                mc.setCarryOnMinute(rs.getString("carry_on_minute"));
                mc.setResult(rs.getInt("result"));
                mc.setDescCarryOn(rs.getString("desc_carry_on"));
                mc.setDescNotCarryOn(rs.getString("desc_not_carry_on"));
                mc.setExplanation(rs.getString("explanation"));
                mcList.add(mc);
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

    public ArrayList getMcDetails()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select material_carry_on_detail.*, name_vn, unit_vn From (material_carry_on_detail left join equipment on material_carry_on_detail.equ_id = equipment.equ_id) left join material on equipment.mat_id = material.mat_id) left join unit on material.uni_id = unit.uni_id Order by det_id ASC";

        ArrayList material_carry_on_detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            McDetailBean mc_detail = null;
            while (rs.next()) {
                mc_detail = new McDetailBean();
                mc_detail.setDetId(rs.getInt("det_id"));
                mc_detail.setMcId(rs.getInt("mc_id"));
                mc_detail.setEquId(rs.getInt("equ_id"));
                mc_detail.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                mc_detail.setUnit(rs.getString("unit_vn"));
                mc_detail.setQuantity(rs.getDouble("quantity"));
                mc_detail.setSpec(rs.getString("spec"));
                mc_detail.setMcoDetailId(rs.getInt("mco_detail_id"));
                material_carry_on_detailList.add(mc_detail);
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
        return material_carry_on_detailList;
    }

    public ArrayList getMcDetailsByMc(int mcId) throws Exception {
        ResultSet rs = null;

        String sql = "Select material_carry_on_detail.*, name_vn, unit_vn, used_code, mco_id From (((material_carry_on_detail left join material_carry_out_detail on material_carry_on_detail.mco_detail_id = material_carry_out_detail.det_id )left join equipment on material_carry_on_detail.equ_id = equipment.equ_id) left join material on equipment.mat_id = material.mat_id) left join unit on material.uni_id = unit.uni_id Where mc_id  = " + mcId;

        ArrayList material_carry_on_detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            McDetailBean mc_detail = null;
            while (rs.next()) {
                mc_detail = new McDetailBean();
                mc_detail.setDetId(rs.getInt("det_id"));
                mc_detail.setMcId(rs.getInt("mc_id"));
                mc_detail.setEquId(rs.getInt("equ_id"));
                mc_detail.setUsedCode(rs.getString("used_code"));
                mc_detail.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                mc_detail.setEquName("(" + rs.getString("used_code") + ") " + mc_detail.getNameVn());
                mc_detail.setUnit(rs.getString("unit_vn"));
                mc_detail.setQuantity(rs.getDouble("quantity"));
                mc_detail.setSpec(rs.getString("spec"));
                mc_detail.setMcoDetailId(rs.getInt("mco_detail_id"));
                mc_detail.setMcoId(rs.getInt("mco_id"));
                material_carry_on_detailList.add(mc_detail);
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
        return material_carry_on_detailList;
    }

    public int getMcoDetailId(int mcoId, int equId) throws Exception {
        ResultSet rs = null;

        String sql = "Select det_id From material_carry_out_detail Where mco_id = " + mcoId + " And equ_id = " + equId;
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

    public McDetailBean getMcDetail(int mcId) throws Exception {
        ResultSet rs = null;

        String sql = "Select material_carry_on_detail.*, name_vn, unit_vn From (material_carry_on_detail left join equipment on material_carry_on_detail.equ_id = equipment.equ_id) left join material on equipment.mat_id = material.mat_id) left join unit on material.uni_id = unit.uni_id Where mc_id = " + mcId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                McDetailBean mc_detail = new McDetailBean();
                mc_detail.setDetId(rs.getInt("det_id"));
                mc_detail.setMcId(rs.getInt("mc_id"));
                mc_detail.setEquId(rs.getInt("equ_id"));
                mc_detail.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                mc_detail.setUnit(rs.getString("unit_vn"));
                mc_detail.setQuantity(rs.getDouble("quantity"));
                mc_detail.setSpec(rs.getString("spec"));
                mc_detail.setMcoDetailId(rs.getInt("mco_detail_id"));

                return mc_detail;
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

    public void insertMcDetail(McDetailBean bean) throws Exception {
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

            sql = "Insert Into material_carry_on_detail(mc_id, equ_id, unit, quantity, spec, mco_detail_id)"
                    + " Values (" + bean.getMcId() + "," + bean.getEquId() + ",'" + bean.getUnit()
                    + "'," + bean.getQuantity() + ",'" + bean.getSpec() + "'," + bean.getMcoDetailId() + ")";

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

    public void updateMcDetail(McDetailBean bean) throws Exception {
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

            String sql = "Update material_carry_on_detail Set "
                    + " mc_id = " + bean.getMcId()
                    + ", equ_id = " + bean.getEquId()
                    + ", unit = '" + bean.getUnit() + "'"
                    + ", quantity = " + bean.getQuantity()
                    + ", spec = '" + bean.getSpec() + "'"
                    + ", mco_detail_id = " + bean.getMcoDetailId()
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

    public int deleteMcDetail(int detId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From material_carry_on_detail Where det_id = " + detId;
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

    public ArrayList getEquipments() throws Exception {
        ResultSet rs = null;

        String sql = "Select equipment.*, name_vn, unit_vn From (equipment left join material on equipment.mat_id = material.mat_id) left join unit on material.uni_id = unit.uni_id Order by equ_id DESC";

        ArrayList equipmentList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            McDetailBean equipment = null;
            while (rs.next()) {
                equipment = new McDetailBean();
                equipment.setEquId(rs.getInt("equ_id"));
                equipment.setUsedCode(rs.getString("used_code"));
                equipment.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                equipment.setUnit(rs.getString("unit_vn"));
                equipmentList.add(equipment);
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
        return equipmentList;
    }

    public ArrayList getEquipments(String equIds) throws Exception {
        ResultSet rs = null;

        String sql = "Select equipment.*, name_vn, unit_vn From (equipment left join material on equipment.mat_id = material.mat_id) left join unit on material.uni_id = unit.uni_id Where equ_id In(" + equIds + ")Order by equ_id DESC";

        ArrayList equipmentList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            McDetailBean equipment = null;
            while (rs.next()) {
                equipment = new McDetailBean();
                equipment.setEquId(rs.getInt("equ_id"));
                equipment.setUsedCode(rs.getString("used_code"));
                equipment.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                equipment.setUnit(rs.getString("unit_vn"));
                equipment.setQuantity(1);
                equipmentList.add(equipment);
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
        return equipmentList;
    }

    public ArrayList searchEquipments(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "name_vn";
                break;
        }
        String sql = "";
        sql = "Select equipment.*, name_vn, unit_vn From (equipment left join material on equipment.mat_id = material.mat_id) left join unit on material.uni_id = unit.uni_id Where 1 ";

        ResultSet rs = null;

        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by equ_id DESC";

        ArrayList equipmentList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            McDetailBean equipment = null;
            while (rs.next()) {
                equipment = new McDetailBean();
                equipment.setEquId(rs.getInt("equ_id"));
                equipment.setUsedCode(rs.getString("used_code"));
                equipment.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                equipment.setUnit(rs.getString("unit_vn"));
                equipmentList.add(equipment);
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
        return equipmentList;
    }

    public String getUnit(int equId) throws Exception {
        String sUnit = "";
        ResultSet rs = null;

        String sql = "Select unit_vn From (equipment left join material on equipment.mat_id = material.mat_id) left join unit on material.uni_id = unit.uni_id Where equ_id = " + equId;

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
}
