/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.McoBean;
import com.venus.mc.bean.McoDetailBean;
import com.venus.mc.bean.McoWarningBean;
import com.venus.mc.bean.OrganizationBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.util.MCUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class McoDAO extends BasicDAO {

    public ArrayList getOrgs()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select distinct material_carry_out.org_id, name From material_carry_out left join organization on material_carry_out.org_id = organization.org_id Where material_carry_out.status = 2";

        ArrayList mcList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            OrganizationBean orgBean = null;
            while (rs.next()) {
                orgBean = new OrganizationBean();
                orgBean.setOrgId(rs.getInt("org_id"));
                orgBean.setName(rs.getString("name"));
                mcList.add(orgBean);
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

    public ArrayList getMcosByOrgId(int orgId)
            throws Exception {
        ResultSet rs = null;

        String sql = "Select material_carry_out.*, name From material_carry_out left join organization on material_carry_out.org_id = organization.org_id Where material_carry_out.org_id = " + orgId + " And material_carry_out.status = 2";

        ArrayList mcList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            McoBean mco = null;
            while (rs.next()) {
                mco = new McoBean();
                mco.setMcoId(rs.getInt("mco_id"));
                mco.setOrgId(rs.getInt("org_id"));
                mco.setOrgName(rs.getString("name"));
                mco.setMcoNumber(rs.getString("mco_number"));
                mco.setKind(rs.getInt("kind"));
                if (rs.getInt("kind") == 1) {
                    mco.setKindString(MCUtil.getBundleString("message.mc.kind1"));
                } else if (rs.getInt("kind") == 2) {
                    mco.setKindString(MCUtil.getBundleString("message.mc.kind2"));
                }
                mco.setStatus(rs.getInt("status"));
                mco.setCarryOnDatePlan(DateUtil.formatDate(rs.getDate("carry_on_date_plan"), "dd/MM/yyyy"));
                mco.setCarryOutDate(DateUtil.formatDate(rs.getDate("carry_out_date"), "dd/MM/yyyy"));
                mco.setCarryOutHour(rs.getString("carry_out_hour"));
                mco.setCarryOutMinute(rs.getString("carry_out_minute"));
                mco.setResult(rs.getInt("result"));
                mco.setDescCarryOut(rs.getString("desc_carry_out"));
                mco.setDescNotCarryOut(rs.getString("desc_not_carry_out"));
                mco.setExplanation(rs.getString("explanation"));
                mcList.add(mco);
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

    public ArrayList getMcos()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select material_carry_out.*, name From material_carry_out left join organization on material_carry_out.org_id = organization.org_id";

        ArrayList mcList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            McoBean mco = null;
            while (rs.next()) {
                mco = new McoBean();
                mco.setMcoId(rs.getInt("mco_id"));
                mco.setOrgId(rs.getInt("org_id"));
                mco.setOrgName(rs.getString("name"));
                mco.setMcoNumber(rs.getString("mco_number"));
                mco.setKind(rs.getInt("kind"));
                if (rs.getInt("kind") == 1) {
                    mco.setKindString(MCUtil.getBundleString("message.mc.kind1"));
                } else if (rs.getInt("kind") == 2) {
                    mco.setKindString(MCUtil.getBundleString("message.mc.kind2"));
                }
                mco.setStatus(rs.getInt("status"));
                mco.setCarryOnDatePlan(DateUtil.formatDate(rs.getDate("carry_on_date_plan"), "dd/MM/yyyy"));
                mco.setCarryOutDate(DateUtil.formatDate(rs.getDate("carry_out_date"), "dd/MM/yyyy"));
                mco.setCarryOutHour(rs.getString("carry_out_hour"));
                mco.setCarryOutMinute(rs.getString("carry_out_minute"));
                mco.setResult(rs.getInt("result"));
                mco.setDescCarryOut(rs.getString("desc_carry_out"));
                mco.setDescNotCarryOut(rs.getString("desc_not_carry_out"));
                mco.setExplanation(rs.getString("explanation"));
                mcList.add(mco);
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

    public McoBean getMco(int mcoId) throws Exception {
        ResultSet rs = null;

        String sql = "Select material_carry_out.*, name From material_carry_out left join organization on material_carry_out.org_id = organization.org_id Where mco_id = " + mcoId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                McoBean mco = new McoBean();
                mco.setMcoId(rs.getInt("mco_id"));
                mco.setOrgId(rs.getInt("org_id"));
                mco.setOrgName(rs.getString("name"));
                mco.setMcoNumber(rs.getString("mco_number"));
                mco.setKind(rs.getInt("kind"));
                if (rs.getInt("kind") == 1) {
                    mco.setKindString(MCUtil.getBundleString("message.mc.kind1"));
                } else if (rs.getInt("kind") == 2) {
                    mco.setKindString(MCUtil.getBundleString("message.mc.kind2"));
                }
                mco.setStatus(rs.getInt("status"));
                mco.setCarryOnDatePlan(DateUtil.formatDate(rs.getDate("carry_on_date_plan"), "dd/MM/yyyy"));
                mco.setCarryOutDate(DateUtil.formatDate(rs.getDate("carry_out_date"), "dd/MM/yyyy"));
                mco.setCarryOutHour(rs.getString("carry_out_hour"));
                mco.setCarryOutMinute(rs.getString("carry_out_minute"));
                mco.setResult(rs.getInt("result"));
                mco.setDescCarryOut(rs.getString("desc_carry_out"));
                mco.setDescNotCarryOut(rs.getString("desc_not_carry_out"));
                mco.setExplanation(rs.getString("explanation"));

                return mco;
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

    public McoBean getMcoByNumber(String mcoNumber) throws Exception {
        ResultSet rs = null;

        String sql = "Select material_carry_out.* From material_carry_out Where mco_number = '" + mcoNumber + "'";


        try {




            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                McoBean mco = new McoBean();
                mco.setMcoId(rs.getInt("mco_id"));

                return mco;
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

    public int insertMco(McoBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }



        try {
            String sql = "";

//            

            String carry_out_date = "null";
            if (!GenericValidator.isBlankOrNull(bean.getCarryOutDate())) {
                carry_out_date = "STR_TO_DATE('" + bean.getCarryOutDate() + "','%d/%m/%Y')";
            }

            String carry_on_date_plan = "null";
            if (!GenericValidator.isBlankOrNull(bean.getCarryOnDatePlan())) {
                carry_on_date_plan = "STR_TO_DATE('" + bean.getCarryOnDatePlan() + "','%d/%m/%Y')";
            }

            sql = "Insert Into material_carry_out(created_emp, org_id, mco_number, kind, status, carry_on_date_plan, carry_out_date, carry_out_hour, carry_out_minute, result, desc_carry_out, desc_not_carry_out, explanation)"
                    + " Values (" + bean.getCreatedEmp() + "," + bean.getOrgId() + ",'" + bean.getMcoNumber() + "'," + bean.getKind()
                    + "," + bean.getStatus() + "," + carry_on_date_plan + "," + carry_out_date
                    + ",'" + bean.getCarryOutHour() + "','" + bean.getCarryOutMinute() + "'," + bean.getResult() + ",'" + bean.getDescCarryOut()
                    + "','" + bean.getDescNotCarryOut() + "','" + bean.getExplanation() + "')";

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
//        return 0;
    }

    public void updateMco(McoBean bean) throws Exception {
        if (bean == null) {
            return;
        }



        try {


            String sql = "";

            String carry_out_date = "null";
            if (!GenericValidator.isBlankOrNull(bean.getCarryOutDate())) {
                carry_out_date = "STR_TO_DATE('" + bean.getCarryOutDate() + "','%d/%m/%Y')";
            }

            String carry_on_date_plan = "null";
            if (!GenericValidator.isBlankOrNull(bean.getCarryOnDatePlan())) {
                carry_on_date_plan = "STR_TO_DATE('" + bean.getCarryOnDatePlan() + "','%d/%m/%Y')";
            }

            sql = "Update material_carry_out Set "
                    + " org_id = " + bean.getOrgId()
                    + ", mco_number = '" + bean.getMcoNumber() + "'"
                    + ", kind = " + bean.getKind()
                    + ", status = " + bean.getStatus()
                    + ", carry_on_date_plan = " + carry_on_date_plan
                    + ", carry_out_date = " + carry_out_date
                    + ", carry_out_hour = '" + bean.getCarryOutHour() + "'"
                    + ", carry_out_minute = '" + bean.getCarryOutMinute() + "'"
                    + ", result = " + bean.getResult()
                    + ", desc_carry_out = '" + bean.getDescCarryOut() + "'"
                    + ", desc_not_carry_out = '" + bean.getDescNotCarryOut() + "'"
                    + ", explanation = '" + bean.getExplanation() + "'"
                    + " Where mco_id = " + bean.getMcoId();

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

    public int deleteMco(int mcoId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From material_carry_out Where mco_id = " + mcoId;
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

    public ArrayList searchSimpleMco(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "mco_number";
                break;
        }
        ResultSet rs = null;

        String sql = "Select material_carry_out.*, name From material_carry_out left join organization on material_carry_out.org_id = organization.org_id Where 1 ";
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + "AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " Order by mco_id DESC";

        ArrayList mcList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            McoBean mco = null;
            while (rs.next()) {
                mco = new McoBean();
                mco.setMcoId(rs.getInt("mco_id"));
                mco.setOrgId(rs.getInt("org_id"));
                mco.setOrgName(rs.getString("name"));
                mco.setMcoNumber(rs.getString("mco_number"));
                mco.setKind(rs.getInt("kind"));
                if (rs.getInt("kind") == 1) {
                    mco.setKindString(MCUtil.getBundleString("message.mc.kind1"));
                } else if (rs.getInt("kind") == 2) {
                    mco.setKindString(MCUtil.getBundleString("message.mc.kind2"));
                }
                mco.setStatus(rs.getInt("status"));
                mco.setCarryOnDatePlan(DateUtil.formatDate(rs.getDate("carry_on_date_plan"), "dd/MM/yyyy"));
                mco.setCarryOutDate(DateUtil.formatDate(rs.getDate("carry_out_date"), "dd/MM/yyyy"));
                mco.setCarryOutHour(rs.getString("carry_out_hour"));
                mco.setCarryOutMinute(rs.getString("carry_out_minute"));
                mco.setResult(rs.getInt("result"));
                mco.setDescCarryOut(rs.getString("desc_carry_out"));
                mco.setDescNotCarryOut(rs.getString("desc_not_carry_out"));
                mco.setExplanation(rs.getString("explanation"));
                mcList.add(mco);
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

    public ArrayList searchAdvMco(McoBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "Select material_carry_out.*, name From material_carry_out left join organization on material_carry_out.org_id = organization.org_id Where 1";

        if (bean.getOrgId() != 0) {
            sql = sql + " AND material_carry_out.org_id = " + bean.getOrgId();
        }

        if (!StringUtil.isBlankOrNull(bean.getMcoNumber())) {
            sql = sql + " AND mco_number LIKE '%" + bean.getMcoNumber() + "%'";
        }

        if (bean.getKind() != 0) {
            sql = sql + " AND material_carry_out.kind = " + bean.getKind();
        }

        if (bean.getStatus() != 0) {
            sql = sql + " AND status = " + bean.getStatus();
        }

        if (!StringUtil.isBlankOrNull(bean.getCarryOnDatePlan())) {
            sql = sql + " AND carry_on_date_plan = STR_TO_DATE('" + bean.getCarryOnDatePlan() + "','%d/%m/%Y')";
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

        sql = sql + " Order by mco_id DESC";

        ArrayList mcList = new ArrayList();
        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            McoBean mco = null;

            while (rs.next()) {
                mco = new McoBean();
                mco.setMcoId(rs.getInt("mco_id"));
                mco.setOrgId(rs.getInt("org_id"));
                mco.setOrgName(rs.getString("name"));
                mco.setMcoNumber(rs.getString("mco_number"));
                mco.setKind(rs.getInt("kind"));
                if (rs.getInt("kind") == 1) {
                    mco.setKindString(MCUtil.getBundleString("message.mc.kind1"));
                } else if (rs.getInt("kind") == 2) {
                    mco.setKindString(MCUtil.getBundleString("message.mc.kind2"));
                }
                mco.setStatus(rs.getInt("status"));
                mco.setCarryOnDatePlan(DateUtil.formatDate(rs.getDate("carry_on_date_plan"), "dd/MM/yyyy"));
                mco.setCarryOutDate(DateUtil.formatDate(rs.getDate("carry_out_date"), "dd/MM/yyyy"));
                mco.setCarryOutHour(rs.getString("carry_out_hour"));
                mco.setCarryOutMinute(rs.getString("carry_out_minute"));
                mco.setResult(rs.getInt("result"));
                mco.setDescCarryOut(rs.getString("desc_carry_out"));
                mco.setDescNotCarryOut(rs.getString("desc_not_carry_out"));
                mco.setExplanation(rs.getString("explanation"));
                mcList.add(mco);
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

    public ArrayList getMcoDetails()
            throws Exception {
        ResultSet rs = null;

        String sql = "Select material_carry_out_detail.*, name_vn, unit_vn, used_code From (material_carry_out_detail left join equipment on material_carry_out_detail.equ_id = equipment.equ_id) left join material on equipment.mat_id = material.mat_id) left join unit on material.uni_id = unit.uni_id Order by det_id ASC";

        ArrayList material_carry_out_detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            McoDetailBean material_carry_out_detail = null;
            while (rs.next()) {
                material_carry_out_detail = new McoDetailBean();
                material_carry_out_detail.setDetId(rs.getInt("det_id"));
                material_carry_out_detail.setMcoId(rs.getInt("mco_id"));
                material_carry_out_detail.setEquId(rs.getInt("equ_id"));
                material_carry_out_detail.setUsedCode(rs.getString("used_code"));
                material_carry_out_detail.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                material_carry_out_detail.setUnit(rs.getString("unit_vn"));
                material_carry_out_detail.setQuantity(rs.getDouble("quantity"));
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

    public ArrayList getMcoDetailsByMco(int mcoId) throws Exception {
        ResultSet rs = null;

        String sql = "Select material_carry_out_detail.*, name_vn, unit_vn, used_code From ((material_carry_out_detail left join equipment on material_carry_out_detail.equ_id = equipment.equ_id) left join material on equipment.mat_id = material.mat_id) left join unit on material.uni_id = unit.uni_id Where mco_id  = " + mcoId;

        ArrayList material_carry_out_detailList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            McoDetailBean material_carry_out_detail = null;
            while (rs.next()) {
                material_carry_out_detail = new McoDetailBean();
                material_carry_out_detail.setDetId(rs.getInt("det_id"));
                material_carry_out_detail.setMcoId(rs.getInt("mco_id"));
                material_carry_out_detail.setEquId(rs.getInt("equ_id"));
                material_carry_out_detail.setEquName("(" + rs.getString("used_code") + ") " + StringUtil.decodeString(rs.getString("name_vn")));
                material_carry_out_detail.setUsedCode(rs.getString("used_code"));
                material_carry_out_detail.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                material_carry_out_detail.setUnit(rs.getString("unit_vn"));
                material_carry_out_detail.setQuantity(rs.getDouble("quantity"));
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

    public McoDetailBean getMcoDetail(int mcoId) throws Exception {
        ResultSet rs = null;

        String sql = "Select material_carry_out_detail.*, name_vn, unit_vn, used_code From (material_carry_out_detail left join equipment on material_carry_out_detail.equ_id = equipment.equ_id) left join material on equipment.mat_id = material.mat_id) left join unit on material.uni_id = unit.uni_id Where mco_id = " + mcoId;


        try {



            //System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                McoDetailBean material_carry_out_detail = new McoDetailBean();
                material_carry_out_detail.setDetId(rs.getInt("det_id"));
                material_carry_out_detail.setMcoId(rs.getInt("mco_id"));
                material_carry_out_detail.setEquId(rs.getInt("equ_id"));
                material_carry_out_detail.setUsedCode(rs.getString("used_code"));
                material_carry_out_detail.setNameVn(StringUtil.decodeString(rs.getString("name_vn")));
                material_carry_out_detail.setUnit(rs.getString("unit_vn"));
                material_carry_out_detail.setQuantity(rs.getDouble("quantity"));
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

    public void insertMcoDetail(McoDetailBean bean) throws Exception {
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

            sql = "Insert Into material_carry_out_detail(mco_id, equ_id, unit, quantity, spec)"
                    + " Values (" + bean.getMcoId() + "," + bean.getEquId() + ",'" + bean.getUnit()
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

    public void updateMcoDetail(McoDetailBean bean) throws Exception {
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

            String sql = "Update material_carry_out_detail Set "
                    + " mco_id = " + bean.getMcoId()
                    + ", equ_id = " + bean.getEquId()
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

    public int deleteMcoDetail(int detId) throws Exception {


        int result = 0;
        try {


            String sql = "Delete From material_carry_out_detail Where det_id = " + detId;
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
            McoDetailBean equipment = null;
            while (rs.next()) {
                equipment = new McoDetailBean();
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
            McoDetailBean equipment = null;
            while (rs.next()) {
                equipment = new McoDetailBean();
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
            McoDetailBean equipment = null;
            while (rs.next()) {
                equipment = new McoDetailBean();
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

    public ArrayList getMaterialCarryOutForWarning(int dateAmount) throws Exception {
        ResultSet rs = null;

        String sql = "select e.manage_code, m.name_vn, mcodet.quantity, mco.mco_number, mco.carry_on_date_plan, mco.mco_id"
                + " from material_carry_out as mco "
                + " left join material_carry_out_detail as mcodet on mcodet.mco_id=mco.mco_id"
                + " left join equipment as e on e.equ_id=mcodet.equ_id"
                + " left join material as m on m.mat_id=e.mat_id"
                + " where mco.warned=0 and datediff(sysdate(),carry_on_date_plan)>=" + dateAmount;

        ArrayList list = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            McoWarningBean detail = null;
            while (rs.next()) {
                detail = new McoWarningBean();
                detail.setMcoId(rs.getInt("mco_id"));
                detail.setManageCode(rs.getString("manage_code"));
                detail.setMatName(StringUtil.decodeString(rs.getString("name_vn")));
                detail.setOnDatePlan(DateUtil.formatDate(rs.getDate("carry_on_date_plan"), "dd/MM/yyyy"));
                detail.setQuantity(rs.getDouble("quantity"));
                detail.setMcoNumber(rs.getString("mco_number"));
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

    public void updateMcoWarnedStatus(String ids) throws Exception {
        try {
            String sql = "update material_carry_out set warned=1 where mco_id in (" + ids + ")";
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
