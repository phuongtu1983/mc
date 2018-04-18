package com.venus.mc.dao;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.EquipmentBean;
import com.venus.mc.bean.RepairMaterialBean;
import com.venus.mc.database.DBUtil;
import com.venus.mc.util.MCUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Mai Vinh Loc
 */
public class EquipmentDAO extends BasicDAO {

    public EquipmentDAO() {
    }

    public ArrayList getEquipments() throws Exception {

        ResultSet rs = null;

        String sql = "SELECT e.*, m1.miv_number, m2.name_vn,m2.name_en, c.contract_number,c.kind,c.appendix_contract_number,r.request_number,u.unit_vn,u.unit_en FROM equipment AS e LEFT JOIN miv AS m1 ON m1.miv_id=e.miv_id LEFT JOIN contract AS c ON c.con_id=e.con_id LEFT JOIN material AS m2 ON m2.mat_id=e.mat_id LEFT JOIN request_detail AS rd ON rd.det_id=e.req_detail_id LEFT JOIN request AS r ON r.req_id=rd.req_id LEFT JOIN unit AS u ON u.uni_id = m2.uni_id order by e.equ_id desc";

        ArrayList equipmentList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EquipmentBean equipment = null;
            while (rs.next()) {
                equipment = new EquipmentBean();
                equipment.setEquId(rs.getInt("equ_id"));
                equipment.setConId(rs.getInt("con_id"));
                equipment.setMatId(rs.getInt("mat_id"));
                equipment.setMivId(rs.getInt("miv_id"));
                equipment.setReqDetailId(rs.getInt("req_detail_id"));
                equipment.setMivNumber(rs.getString("miv_number"));
                equipment.setDecisionNumber(rs.getString("decision_number"));
                equipment.setTest(rs.getString("test"));
                equipment.setManageCode(rs.getString("manage_code"));
                equipment.setEquipmentName(StringUtil.decodeString(rs.getString("name_vn")));
                equipment.setRequestNumber(rs.getString("request_number"));
                if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
//                    equipment.setContractNumber(rs.getString("appendix_contract_number") + "(BS Số " + rs.getString("contract_number") + ")");
                    equipment.setContractNumber(rs.getString("contract_number"));
                } else {
                    equipment.setContractNumber(rs.getString("contract_number"));
                }
                equipment.setTestNumber(rs.getString("mrir_id"));
                equipment.setUnit(rs.getString("unit_vn"));
                equipment.setUsedCode(rs.getString("used_code"));
                equipment.setColorCode(rs.getString("cc_id"));
                equipment.setStatus(rs.getInt("status"));
                equipment.setAppearedDate(DateUtil.formatDate(rs.getDate("appeared_date"), "dd/MM/yyyy"));
                equipment.setUsedDate(DateUtil.formatDate(rs.getDate("used_date"), "dd/MM/yyyy"));
                if (GenericValidator.isBlankOrNull(rs.getString("spec_certs"))) {
                    equipment.setSpecCerts("");
                } else {
                    equipment.setSpecCerts(StringUtil.decodeString(rs.getString("spec_certs")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("fuel_level"))) {
                    equipment.setFuelLevel("");
                } else {
                    equipment.setFuelLevel(StringUtil.decodeString(rs.getString("fuel_level")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("comment"))) {
                    equipment.setComment("");
                } else {
                    equipment.setComment(StringUtil.decodeString(rs.getString("comment")));
                }
                if (rs.getInt("status") == EquipmentBean.S1) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status1"));
                }
                if (rs.getInt("status") == EquipmentBean.S2) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status2"));
                }
                if (rs.getInt("status") == EquipmentBean.S3) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status3"));
                }
                if (rs.getInt("status") == EquipmentBean.S4) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status4"));
                }

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

    public ArrayList getEquipments(int kind) throws Exception {

        ResultSet rs = null;

        String sql = "SELECT e.*, m1.miv_number, m2.name_vn,m2.name_en, c.contract_number,c.kind,c.appendix_contract_number,r.request_number,u.unit_vn,u.unit_en FROM equipment AS e LEFT JOIN miv AS m1 ON m1.miv_id=e.miv_id LEFT JOIN contract AS c ON c.con_id=e.con_id LEFT JOIN material AS m2 ON m2.mat_id=e.mat_id LEFT JOIN request_detail AS rd ON rd.det_id=e.req_detail_id LEFT JOIN request AS r ON r.req_id=rd.req_id LEFT JOIN unit AS u ON u.uni_id = m2.uni_id WHERE e.kind = " + kind + " order by e.equ_id desc";

        ArrayList equipmentList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EquipmentBean equipment = null;
            while (rs.next()) {
                equipment = new EquipmentBean();
                equipment.setEquId(rs.getInt("equ_id"));
                equipment.setConId(rs.getInt("con_id"));
                equipment.setMatId(rs.getInt("mat_id"));
                equipment.setMivId(rs.getInt("miv_id"));
                equipment.setReqDetailId(rs.getInt("req_detail_id"));
                if (rs.getString("miv_number") != null) {
                    equipment.setMivNumber(rs.getString("miv_number"));
                } else {
                    equipment.setMivNumber("");
                }
                equipment.setDecisionNumber(rs.getString("decision_number"));
                equipment.setTest(rs.getString("test"));
                equipment.setManageCode(rs.getString("manage_code"));
                equipment.setEquipmentName(StringUtil.decodeString(rs.getString("name_vn")));
                equipment.setRequestNumber(rs.getString("request_number"));
                if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
//                    equipment.setContractNumber(rs.getString("appendix_contract_number") + "(BS Số " + rs.getString("contract_number") + ")");
                    equipment.setContractNumber(rs.getString("contract_number"));
                } else {
                    equipment.setContractNumber(rs.getString("contract_number"));
                }
                equipment.setTestNumber(rs.getString("mrir_id"));
                equipment.setUnit(rs.getString("unit_vn"));
                equipment.setUsedCode(rs.getString("used_code"));
                equipment.setColorCode(rs.getString("cc_id"));
                equipment.setStatus(rs.getInt("status"));
                equipment.setAppearedDate(DateUtil.formatDate(rs.getDate("appeared_date"), "dd/MM/yyyy"));
                equipment.setUsedDate(DateUtil.formatDate(rs.getDate("used_date"), "dd/MM/yyyy"));
                if (GenericValidator.isBlankOrNull(rs.getString("spec_certs"))) {
                    equipment.setSpecCerts("");
                } else {
                    equipment.setSpecCerts(StringUtil.decodeString(rs.getString("spec_certs")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("fuel_level"))) {
                    equipment.setFuelLevel("");
                } else {
                    equipment.setFuelLevel(StringUtil.decodeString(rs.getString("fuel_level")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("comment"))) {
                    equipment.setComment("");
                } else {
                    equipment.setComment(StringUtil.decodeString(rs.getString("comment")));
                }
                if (rs.getInt("status") == EquipmentBean.S1) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status1"));
                }
                if (rs.getInt("status") == EquipmentBean.S2) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status2"));
                }
                if (rs.getInt("status") == EquipmentBean.S3) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status3"));
                }
                if (rs.getInt("status") == EquipmentBean.S4) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status4"));
                }
                if (rs.getInt("status") == EquipmentBean.S5) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status5"));
                }
                equipment.setKind(rs.getInt("kind"));
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

    public ArrayList getEquipmentsOrg(int kind, int manageOrg) throws Exception {

        ResultSet rs = null;

        String sql = "SELECT e.*, m1.miv_number, m2.name_vn,m2.name_en, c.contract_number,c.kind,c.appendix_contract_number,r.request_number,u.unit_vn,u.unit_en FROM equipment AS e LEFT JOIN miv AS m1 ON m1.miv_id=e.miv_id LEFT JOIN contract AS c ON c.con_id=e.con_id LEFT JOIN material AS m2 ON m2.mat_id=e.mat_id LEFT JOIN request_detail AS rd ON rd.det_id=e.req_detail_id LEFT JOIN request AS r ON r.req_id=rd.req_id LEFT JOIN unit AS u ON u.uni_id = m2.uni_id WHERE e.kind = " + kind + " and e.manage_org = " + manageOrg + " order by e.equ_id desc";

        ArrayList equipmentList = new ArrayList();
        try {



            rs = DBUtil.executeQuery(sql);
            EquipmentBean equipment = null;
            while (rs.next()) {
                equipment = new EquipmentBean();
                equipment.setEquId(rs.getInt("equ_id"));
                equipment.setConId(rs.getInt("con_id"));
                equipment.setMatId(rs.getInt("mat_id"));
                equipment.setMivId(rs.getInt("miv_id"));
                equipment.setReqDetailId(rs.getInt("req_detail_id"));
                if (rs.getString("miv_number") != null) {
                    equipment.setMivNumber(rs.getString("miv_number"));
                } else {
                    equipment.setMivNumber("");
                }
                equipment.setDecisionNumber(rs.getString("decision_number"));
                equipment.setTest(rs.getString("test"));
                equipment.setManageCode(rs.getString("manage_code"));
                equipment.setEquipmentName(StringUtil.decodeString(rs.getString("name_vn")));
                equipment.setRequestNumber(rs.getString("request_number"));
                if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
//                    equipment.setContractNumber(rs.getString("appendix_contract_number") + "(BS Số " + rs.getString("contract_number") + ")");
                    equipment.setContractNumber(rs.getString("contract_number"));
                } else {
                    equipment.setContractNumber(rs.getString("contract_number"));
                }
                equipment.setTestNumber(rs.getString("mrir_id"));
                equipment.setUnit(rs.getString("unit_vn"));
                equipment.setUsedCode(rs.getString("used_code"));
                equipment.setColorCode(rs.getString("cc_id"));
                equipment.setStatus(rs.getInt("status"));
                equipment.setAppearedDate(DateUtil.formatDate(rs.getDate("appeared_date"), "dd/MM/yyyy"));
                equipment.setUsedDate(DateUtil.formatDate(rs.getDate("used_date"), "dd/MM/yyyy"));
                if (GenericValidator.isBlankOrNull(rs.getString("spec_certs"))) {
                    equipment.setSpecCerts("");
                } else {
                    equipment.setSpecCerts(StringUtil.decodeString(rs.getString("spec_certs")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("fuel_level"))) {
                    equipment.setFuelLevel("");
                } else {
                    equipment.setFuelLevel(StringUtil.decodeString(rs.getString("fuel_level")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("comment"))) {
                    equipment.setComment("");
                } else {
                    equipment.setComment(StringUtil.decodeString(rs.getString("comment")));
                }
                if (rs.getInt("status") == EquipmentBean.S1) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status1"));
                }
                if (rs.getInt("status") == EquipmentBean.S2) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status2"));
                }
                if (rs.getInt("status") == EquipmentBean.S3) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status3"));
                }
                if (rs.getInt("status") == EquipmentBean.S4) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status4"));
                }
                if (rs.getInt("status") == EquipmentBean.S5) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status5"));
                }
                equipment.setKind(rs.getInt("kind"));
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

    public ArrayList getRepairMaterial(String rpId)
            throws Exception {
        ResultSet rs = null;

        int i = 1;
        String sql = "";

        sql = "SELECT d.*, m.name_vn AS materialName, m.code,u.unit_vn,u.unit_en FROM repair_material AS d LEFT JOIN material AS m ON d.mat_id=m.mat_id LEFT JOIN unit AS u ON u.uni_id = m.uni_id WHERE d.rp_id IN (" + rpId + ")";


        ArrayList repair_material_detailList = new ArrayList();
        try {


            System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            RepairMaterialBean detail = null;
            while (rs.next()) {
                detail = new RepairMaterialBean();
                detail.setDetId(i); //STT
                detail.setRmId(rs.getInt("rm_id"));
                detail.setRpId(rs.getInt("rp_id"));
                detail.setMatId(rs.getInt("mat_id"));
                detail.setMatCode(rs.getString("code"));
                detail.setMatName(StringUtil.decodeString(rs.getString("materialName")));
                detail.setQuantity(rs.getDouble("quantity"));
                detail.setUnit(rs.getString("u.unit_vn"));
                repair_material_detailList.add(detail);
                i++;
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
        return repair_material_detailList;
    }

    public ArrayList getEquipments(String ids) throws Exception {

        ResultSet rs = null;

        String sql = "SELECT e.*, m1.miv_number, m2.name_vn,m2.name_en, c.contract_number,c.kind,c.appendix_contract_number,r.request_number,u.unit_vn,u.unit_en FROM equipment AS e LEFT JOIN miv AS m1 ON m1.miv_id=e.miv_id LEFT JOIN contract AS c ON c.con_id=e.con_id LEFT JOIN material AS m2 ON m2.mat_id=e.mat_id LEFT JOIN request_detail AS rd ON rd.det_id=e.req_detail_id LEFT JOIN request AS r ON r.req_id=rd.req_id LEFT JOIN unit AS u ON u.uni_id = m2.uni_id "
                + " where equ_id in(" + ids + ")";


        ArrayList equipmentList = new ArrayList();
        try {


            rs = DBUtil.executeQuery(sql);
            EquipmentBean equipment = null;
            while (rs.next()) {
                equipment = new EquipmentBean();
                equipment.setEquId(rs.getInt("equ_id"));
                equipment.setConId(rs.getInt("con_id"));
                equipment.setMatId(rs.getInt("mat_id"));
                equipment.setMivId(rs.getInt("miv_id"));
                equipment.setReqDetailId(rs.getInt("req_detail_id"));
                equipment.setMivNumber(rs.getString("miv_number"));
                equipment.setDecisionNumber(rs.getString("decision_number"));
                equipment.setTest(rs.getString("test"));
                equipment.setManageCode(rs.getString("manage_code"));
                equipment.setEquipmentName(StringUtil.decodeString(rs.getString("name_vn")));
                equipment.setRequestNumber(rs.getString("request_number"));
                if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
//                    equipment.setContractNumber(rs.getString("appendix_contract_number") + "(BS Số " + rs.getString("contract_number") + ")");
                    equipment.setContractNumber(rs.getString("contract_number"));
                } else {
                    equipment.setContractNumber(rs.getString("contract_number"));
                }
                equipment.setTestNumber(rs.getString("mrir_id"));
                equipment.setUnit(rs.getString("unit_vn"));
                equipment.setUsedCode(rs.getString("used_code"));
                equipment.setColorCode(rs.getString("cc_id"));
                equipment.setStatus(rs.getInt("status"));
                equipment.setAppearedDate(DateUtil.formatDate(rs.getDate("appeared_date"), "dd/MM/yyyy"));
                equipment.setUsedDate(DateUtil.formatDate(rs.getDate("used_date"), "dd/MM/yyyy"));
                if (GenericValidator.isBlankOrNull(rs.getString("spec_certs"))) {
                    equipment.setSpecCerts("");
                } else {
                    equipment.setSpecCerts(StringUtil.decodeString(rs.getString("spec_certs")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("fuel_level"))) {
                    equipment.setFuelLevel("");
                } else {
                    equipment.setFuelLevel(StringUtil.decodeString(rs.getString("fuel_level")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("comment"))) {
                    equipment.setComment("");
                } else {
                    equipment.setComment(StringUtil.decodeString(rs.getString("comment")));
                }
                equipment.setQuantity(1);
                if (rs.getInt("status") == EquipmentBean.S1) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status1"));
                }
                if (rs.getInt("status") == EquipmentBean.S2) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status2"));
                }
                if (rs.getInt("status") == EquipmentBean.S3) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status3"));
                }
                if (rs.getInt("status") == EquipmentBean.S4) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status4"));
                }
                equipment.setKind(rs.getInt("kind"));
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

    public EquipmentBean getEquipment(String equipmentid) throws Exception {

        ResultSet rs = null;

        String sql = "SELECT e.*, m1.miv_number, m2.name_vn,m2.name_en, c.contract_number,m.mrir_number, m3.code, c1.color_code, "
                + "r.request_number,u.unit_vn,u.unit_en FROM equipment AS e, c.kind, c.appendix_contract_number"
                + "LEFT JOIN miv AS m1 ON m1.miv_id=e.miv_id LEFT JOIN contract AS c ON c.con_id=e.con_id "
                + "LEFT JOIN material AS m2 ON m2.mat_id=e.mat_id "
                + "LEFT JOIN request_detail AS rd ON rd.det_id=e.req_detail_id "
                + "LEFT JOIN request AS r ON r.req_id=rd.req_id "
                + "LEFT JOIN mrir AS m ON m.mrir_id=e.mrir_id "
                + "LEFT JOIN color_code AS c1 ON c1.cc_id=e.cc_id "
                + "LEFT JOIN material AS m3 ON m3.mat_id=e.mat_id "
                + "LEFT JOIN unit AS u ON u.uni_id = m2.uni_id where e.equ_id=" + equipmentid;



        try {


            ////System.out.println("sql=" + sql);
            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EquipmentBean equipment = new EquipmentBean();
                equipment.setEquId(rs.getInt("equ_id"));
                equipment.setConId(rs.getInt("con_id"));
                equipment.setMatId(rs.getInt("mat_id"));
                equipment.setMivId(rs.getInt("miv_id"));
                equipment.setReqDetailId(rs.getInt("req_detail_id"));
                equipment.setMivNumber(rs.getString("miv_number"));
                equipment.setDecisionNumber(rs.getString("decision_number"));
                equipment.setTest(rs.getString("test"));
                equipment.setManageCode(rs.getString("m3.code"));
                equipment.setEquipmentName(StringUtil.decodeString(rs.getString("name_vn")));
                equipment.setRequestNumber(rs.getString("request_number"));
                if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
//                    equipment.setContractNumber(rs.getString("appendix_contract_number") + "(BS Số " + rs.getString("contract_number") + ")");
                    equipment.setContractNumber(rs.getString("contract_number"));
                } else {
                    equipment.setContractNumber(rs.getString("contract_number"));
                }
                equipment.setTestNumber(rs.getString("m.mrir_number"));
                equipment.setUnit(rs.getString("unit_vn"));
                equipment.setUsedCode(rs.getString("used_code"));
                equipment.setColorCode(rs.getString("c1.color_code"));
                equipment.setStatus(rs.getInt("status"));
                equipment.setAppearedDate(DateUtil.formatDate(rs.getDate("appeared_date"), "dd/MM/yyyy"));
                equipment.setUsedDate(DateUtil.formatDate(rs.getDate("used_date"), "dd/MM/yyyy"));
                if (GenericValidator.isBlankOrNull(rs.getString("spec_certs"))) {
                    equipment.setSpecCerts("");
                } else {
                    equipment.setSpecCerts(StringUtil.decodeString(rs.getString("spec_certs")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("fuel_level"))) {
                    equipment.setFuelLevel("");
                } else {
                    equipment.setFuelLevel(StringUtil.decodeString(rs.getString("fuel_level")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("comment"))) {
                    equipment.setComment("");
                } else {
                    equipment.setComment(StringUtil.decodeString(rs.getString("comment")));
                }
                if (rs.getInt("status") == EquipmentBean.S1) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status1"));
                }
                if (rs.getInt("status") == EquipmentBean.S2) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status2"));
                }
                if (rs.getInt("status") == EquipmentBean.S3) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status3"));
                }
                if (rs.getInt("status") == EquipmentBean.S4) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status4"));
                }
                equipment.setKind(rs.getInt("kind"));

                return equipment;
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

    public EquipmentBean getEquipment(String equipmentid, int kind) throws Exception {

        ResultSet rs = null;

        String sql = "SELECT e.*, m1.miv_number, m2.name_vn,m2.name_en, c.contract_number,m.mrir_number, m3.code, c1.color_code, "
                + "r.request_number,u.unit_vn,u.unit_en FROM equipment AS e, c.kind, c.appendix_contract_number"
                + "LEFT JOIN miv AS m1 ON m1.miv_id=e.miv_id LEFT JOIN contract AS c ON c.con_id=e.con_id "
                + "LEFT JOIN material AS m2 ON m2.mat_id=e.mat_id "
                + "LEFT JOIN request_detail AS rd ON rd.det_id=e.req_detail_id "
                + "LEFT JOIN request AS r ON r.req_id=rd.req_id "
                + "LEFT JOIN mrir AS m ON m.mrir_id=e.mrir_id "
                + "LEFT JOIN color_code AS c1 ON c1.cc_id=e.cc_id "
                + "LEFT JOIN material AS m3 ON m3.mat_id=e.mat_id "
                + "LEFT JOIN unit AS u ON u.uni_id = m2.uni_id where e.equ_id=" + equipmentid + " and e.kind = " + kind;


        try {


            System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            while (rs.next()) {
                EquipmentBean equipment = new EquipmentBean();
                equipment.setEquId(rs.getInt("equ_id"));
                equipment.setConId(rs.getInt("con_id"));
                equipment.setMatId(rs.getInt("mat_id"));
                equipment.setMivId(rs.getInt("miv_id"));
                equipment.setReqDetailId(rs.getInt("req_detail_id"));
                equipment.setMivNumber(rs.getString("miv_number"));
                equipment.setDecisionNumber(rs.getString("decision_number"));
                equipment.setTest(rs.getString("test"));
                equipment.setManageCode(rs.getString("m3.code"));
                equipment.setEquipmentName(StringUtil.decodeString(rs.getString("name_vn")));
                equipment.setRequestNumber(rs.getString("request_number"));
                if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
//                    equipment.setContractNumber(rs.getString("appendix_contract_number") + "(BS Số " + rs.getString("contract_number") + ")");
                    equipment.setContractNumber(rs.getString("contract_number"));
                } else {
                    equipment.setContractNumber(rs.getString("contract_number"));
                }
                equipment.setTestNumber(rs.getString("m.mrir_number"));
                equipment.setUnit(rs.getString("unit_vn"));
                equipment.setUsedCode(rs.getString("used_code"));
                equipment.setColorCode(rs.getString("c1.color_code"));
                if (GenericValidator.isBlankOrNull(rs.getString("spec_certs"))) {
                    equipment.setSpecCerts("");
                } else {
                    equipment.setSpecCerts(StringUtil.decodeString(rs.getString("spec_certs")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("fuel_level"))) {
                    equipment.setFuelLevel("");
                } else {
                    equipment.setFuelLevel(StringUtil.decodeString(rs.getString("fuel_level")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("comment"))) {
                    equipment.setComment("");
                } else {
                    equipment.setComment(StringUtil.decodeString(rs.getString("comment")));
                }
                equipment.setStatus(rs.getInt("status"));
                equipment.setAppearedDate(DateUtil.formatDate(rs.getDate("appeared_date"), "dd/MM/yyyy"));
                equipment.setUsedDate(DateUtil.formatDate(rs.getDate("used_date"), "dd/MM/yyyy"));

                if (rs.getInt("status") == EquipmentBean.S1) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status1"));
                }
                if (rs.getInt("status") == EquipmentBean.S2) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status2"));
                }
                if (rs.getInt("status") == EquipmentBean.S3) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status3"));
                }
                if (rs.getInt("status") == EquipmentBean.S4) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status4"));
                }
                equipment.setKind(rs.getInt("kind"));

                return equipment;
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

    public int insertEquipment(EquipmentBean bean) throws Exception {
        if (bean == null) {
            return 0;
        }
        int result = 0;
        try {
            String sql = "";
            String appearedDate = "null";
            String usedDate = "null";
            String ccId = "null";
            String mivId = "null";
            String mrirId = "null";
            String reqDetailId = "null";
            String decisionNumber = "";
            String test = "";
            if (!GenericValidator.isBlankOrNull(bean.getAppearedDate())) {
                appearedDate = "STR_TO_DATE('" + bean.getAppearedDate() + "','%d/%m/%Y')";
            }

            if (!GenericValidator.isBlankOrNull(bean.getUsedDate())) {
                usedDate = "STR_TO_DATE('" + bean.getUsedDate() + "','%d/%m/%Y')";
            }
            if (GenericValidator.isBlankOrNull(bean.getManageCode())) {
                bean.setManageCode("");
            }
            if (GenericValidator.isBlankOrNull(bean.getTestNumber())) {
                bean.setTestNumber("");
            }
            if (GenericValidator.isBlankOrNull(bean.getUsedCode())) {
                bean.setUsedCode("");
            }
            if (GenericValidator.isBlankOrNull(bean.getColorCode())) {
                bean.setColorCode("");
            }
            if (bean.getCcId() > 0) {
                ccId = "" + bean.getCcId();
            }
            if (bean.getMivId() > 0) {
                mivId = "" + bean.getMivId();
            }
            if (bean.getMrirId() > 0) {
                mrirId = "" + bean.getMrirId();
            }

            if (bean.getReqDetailId() > 0) {
                reqDetailId = "" + bean.getReqDetailId();
            }

            if (GenericValidator.isBlankOrNull(bean.getDecisionNumber())) {
                bean.setDecisionNumber("");
            }
            if (GenericValidator.isBlankOrNull(bean.getTest())) {
                bean.setTest("");
            }
            if (bean.getStatus() == 0) {
                bean.setStatus(EquipmentBean.S5);
            }
            sql = "insert into equipment(miv_id,kind,decision_number,test,mat_id,req_detail_id,con_id,mrir_id,used_code,cc_id,spec_certs"
                    + ",fuel_level,status,appeared_date,used_date,comment)"
                    + " values (" + mivId + "," + bean.getKind() + ",'" + bean.getDecisionNumber() + "','" + bean.getTest() + "'," + bean.getMatId() + "," + reqDetailId + ","
                    + bean.getConId() + "," + mrirId + ",'" + bean.getUsedCode() + "'," + ccId + ",'"
                    + bean.getSpecCerts() + "','" + bean.getFuelLevel() + "'," + bean.getStatus() + "," + appearedDate + "," + usedDate + ",'" + bean.getComment() + "')";

            System.out.println("sql ====" + sql);
            result = DBUtil.executeInsert(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public void updateEquipment(EquipmentBean bean) throws Exception {
        if (bean == null) {
            return;
        }
        int result = 0;
        try {
            String sql = "update equipment set "
                    + // " miv_id=" + bean.getMivId() + "" +
                    //", mat_id=" + bean.getMatId() + "" +
                    // ", req_detail_id=" + bean.getReqDetailId() + "" +
                    // ", con_id=" + bean.getConId() + "" +
                    //                   ", decision_number='" + bean.getDecisionNumber() + "'" +
                    // ", manage_code='" + bean.getManageCode() + "'" +
                    //             ", equipment_name='" + bean.getEquipmentName() + "'" +
                    //             ", request_number='" + bean.getRequestNumber() + "'" +
                    //             ", contract_number='" + bean.getContractNumber() + "'" +
                    //", mrir_id='" + bean.getTestNumber() + "'" +
                    //                   ", unit='" + bean.getUnit() + "'" +
                    " used_code='" + bean.getUsedCode() + "'"
                    //", cc_id='" + bean.getColorCode() + "'"
                    + ", decision_number='" + bean.getDecisionNumber() + "'"
                    + ", test='" + bean.getTest() + "'"
                    + ", spec_certs='" + bean.getSpecCerts() + "'"
                    + ", fuel_level='" + bean.getFuelLevel() + "'"
                    + ", status=" + bean.getStatus() + ""
                    + ", appeared_date=STR_TO_DATE('" + bean.getAppearedDate() + "','%d/%m/%Y')"
                    + ", used_date=STR_TO_DATE('" + bean.getUsedDate() + "','%d/%m/%Y')"
                    + ", comment='" + bean.getComment() + "'"
                    + " where equ_id=" + bean.getEquId();

            ////System.out.println("sql=" + sql);
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateOrgManager(int equId, int orgId) throws Exception {
        try {
            String sql = "Update equipment Set manage_org = " + orgId
                    + " Where equ_id=" + equId;
            //System.out.println("sql=" + sql);
            DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public int deleteEquipment(String equipmentid) throws Exception {
        int result = 0;
        try {
            String sql = "delete from equipment " + " where equ_id=" + equipmentid;
            result = DBUtil.executeUpdate(sql);
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    public ArrayList searchSimpleEquipment(int fieldid, String strFieldvalue, int kind) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "miv_number";
                break;
            case 2:
                strFieldname = "name_vn";
                break;
            case 3:
                strFieldname = "request_number";
                break;
            case 4:
                strFieldname = "contract_number";
                break;
        }
        ResultSet rs = null;

        String sql = "SELECT e.*, m1.miv_number, m2.name_vn,m2.name_en, c.contract_number,c.kind,c.appendix_contract_number,r.request_number,u.unit_vn,u.unit_en FROM equipment AS e LEFT JOIN miv AS m1 ON m1.miv_id=e.miv_id LEFT JOIN contract AS c ON c.con_id=e.con_id LEFT JOIN material AS m2 ON m2.mat_id=e.mat_id LEFT JOIN request_detail AS rd ON rd.det_id=e.req_detail_id LEFT JOIN request AS r ON r.req_id=rd.req_id LEFT JOIN unit AS u ON u.uni_id = m2.uni_id where e.kind = " + kind;
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by equ_id desc";
        System.out.println("sql=" + sql);

        ArrayList equipmentList = new ArrayList();
        try {


            System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EquipmentBean equipment = null;
            while (rs.next()) {
                equipment = new EquipmentBean();
                equipment.setEquId(rs.getInt("equ_id"));
                equipment.setConId(rs.getInt("con_id"));
                equipment.setMatId(rs.getInt("mat_id"));
                equipment.setMivId(rs.getInt("miv_id"));
                equipment.setReqDetailId(rs.getInt("req_detail_id"));
                equipment.setMivNumber(rs.getString("miv_number"));
                equipment.setDecisionNumber(rs.getString("decision_number"));
                equipment.setTest(rs.getString("test"));
                equipment.setManageCode(rs.getString("manage_code"));
                equipment.setEquipmentName(StringUtil.decodeString(rs.getString("name_vn")));
                equipment.setRequestNumber(rs.getString("request_number"));
                if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
//                    equipment.setContractNumber(rs.getString("appendix_contract_number") + "(BS Số " + rs.getString("contract_number") + ")");
                    equipment.setContractNumber(rs.getString("contract_number"));
                } else {
                    equipment.setContractNumber(rs.getString("contract_number"));
                }
                equipment.setTestNumber(rs.getString("mrir_id"));
                equipment.setUnit(rs.getString("unit_vn"));
                equipment.setUsedCode(rs.getString("used_code"));
                equipment.setColorCode(rs.getString("cc_id"));
                equipment.setStatus(rs.getInt("status"));
                equipment.setAppearedDate(DateUtil.formatDate(rs.getDate("appeared_date"), "dd/MM/yyyy"));
                equipment.setUsedDate(DateUtil.formatDate(rs.getDate("used_date"), "dd/MM/yyyy"));
                if (GenericValidator.isBlankOrNull(rs.getString("spec_certs"))) {
                    equipment.setSpecCerts("");
                } else {
                    equipment.setSpecCerts(StringUtil.decodeString(rs.getString("spec_certs")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("fuel_level"))) {
                    equipment.setFuelLevel("");
                } else {
                    equipment.setFuelLevel(StringUtil.decodeString(rs.getString("fuel_level")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("comment"))) {
                    equipment.setComment("");
                } else {
                    equipment.setComment(StringUtil.decodeString(rs.getString("comment")));
                }
                if (rs.getInt("status") == EquipmentBean.S1) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status1"));
                }
                if (rs.getInt("status") == EquipmentBean.S2) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status2"));
                }
                if (rs.getInt("status") == EquipmentBean.S3) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status3"));
                }
                if (rs.getInt("status") == EquipmentBean.S4) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status4"));
                }
                equipment.setKind(rs.getInt("kind"));
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

    public ArrayList searchSimpleEquipmentofRequireRepair(int fieldid, String strFieldvalue, int completed, int orgId) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "used_code";
                break;
            case 2:
                strFieldname = "equ_id";
                break;
        }
        ResultSet rs = null;

        String sql = "SELECT r.*,o.name,m.name_vn,m.name_en,t.used_code FROM report_damage AS r LEFT JOIN employee AS e ON e.emp_id = r.used_emp LEFT JOIN organization AS o ON o.org_id=e.org_id LEFT JOIN equipment AS t ON t.equ_id=r.equ_id LEFT JOIN material AS m ON m.mat_id=t.mat_id WHERE r.completed = " + completed + "  AND t.manage_org = " + orgId;
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by equ_id desc";

        ArrayList equipmentList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EquipmentBean equipment = null;
            while (rs.next()) {
                equipment = new EquipmentBean();
                equipment.setEquId(rs.getInt("equ_id"));
                equipment.setEquipmentName(StringUtil.decodeString(rs.getString("name_vn")));
                equipment.setStatus(rs.getInt("status"));
                equipment.setUsedCode(rs.getString("used_code"));
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

    public ArrayList searchSimpleEquipmentofRequireTransfer(int fieldid, String strFieldvalue) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "used_code";
                break;
            case 2:
                strFieldname = "equ_id";
                break;
        }
        ResultSet rs = null;

        String sql = "SELECT e.*, m1.miv_number, m2.name_vn,m2.name_en, c.contract_number,c.kind,c.appendix_contract_number,r.request_number,u.unit_vn,u.unit_en FROM equipment AS e LEFT JOIN miv AS m1 ON m1.miv_id=e.miv_id LEFT JOIN contract AS c ON c.con_id=e.con_id LEFT JOIN material AS m2 ON m2.mat_id=e.mat_id LEFT JOIN request_detail AS rd ON rd.det_id=e.req_detail_id LEFT JOIN request AS r ON r.req_id=rd.req_id LEFT JOIN unit AS u ON u.uni_id = m2.uni_id where status = " + EquipmentBean.S5;
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by equ_id desc";

        ArrayList equipmentList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EquipmentBean equipment = null;
            while (rs.next()) {
                equipment = new EquipmentBean();
                equipment.setEquId(rs.getInt("equ_id"));
                equipment.setConId(rs.getInt("con_id"));
                equipment.setMatId(rs.getInt("mat_id"));
                equipment.setMivId(rs.getInt("miv_id"));
                equipment.setReqDetailId(rs.getInt("req_detail_id"));
                equipment.setMivNumber(rs.getString("miv_number"));
                equipment.setDecisionNumber(rs.getString("decision_number"));
                equipment.setTest(rs.getString("test"));
                equipment.setManageCode(rs.getString("manage_code"));
                equipment.setEquipmentName(StringUtil.decodeString(rs.getString("name_vn")));
                equipment.setRequestNumber(rs.getString("request_number"));
                if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
//                    equipment.setContractNumber(rs.getString("appendix_contract_number") + "(BS Số " + rs.getString("contract_number") + ")");
                    equipment.setContractNumber(rs.getString("contract_number"));
                } else {
                    equipment.setContractNumber(rs.getString("contract_number"));
                }
                equipment.setTestNumber(rs.getString("mrir_id"));
                equipment.setUnit(rs.getString("unit_vn"));
                equipment.setUsedCode(rs.getString("used_code"));
                equipment.setColorCode(rs.getString("cc_id"));
                equipment.setStatus(rs.getInt("status"));
                equipment.setAppearedDate(DateUtil.formatDate(rs.getDate("appeared_date"), "dd/MM/yyyy"));
                equipment.setUsedDate(DateUtil.formatDate(rs.getDate("used_date"), "dd/MM/yyyy"));
                if (GenericValidator.isBlankOrNull(rs.getString("spec_certs"))) {
                    equipment.setSpecCerts("");
                } else {
                    equipment.setSpecCerts(StringUtil.decodeString(rs.getString("spec_certs")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("fuel_level"))) {
                    equipment.setFuelLevel("");
                } else {
                    equipment.setFuelLevel(StringUtil.decodeString(rs.getString("fuel_level")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("comment"))) {
                    equipment.setComment("");
                } else {
                    equipment.setComment(StringUtil.decodeString(rs.getString("comment")));
                }
                if (rs.getInt("status") == EquipmentBean.S1) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status1"));
                }
                if (rs.getInt("status") == EquipmentBean.S2) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status2"));
                }
                if (rs.getInt("status") == EquipmentBean.S3) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status3"));
                }
                if (rs.getInt("status") == EquipmentBean.S4) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status4"));
                }
                equipment.setKind(rs.getInt("kind"));
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

    public ArrayList searchSimpleEquipmentofSr(int fieldid, String strFieldvalue, int srId) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "used_code";
                break;
            case 2:
                strFieldname = "equ_id";
                break;
        }
        ResultSet rs = null;

        String sql = "SELECT e.used_code,sr.*, m2.mat_id,m2.name_vn,m2.name_en, u.unit_vn,u.unit_en FROM survey_report_detail AS sr LEFT JOIN equipment AS e ON sr.equ_id = e.equ_id LEFT JOIN material AS m2 ON m2.mat_id=e.mat_id LEFT JOIN request_detail AS rd ON rd.det_id=e.req_detail_id LEFT JOIN unit AS u ON u.uni_id = m2.uni_id  where sr_id=" + srId;
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by equ_id desc";

        ArrayList equipmentList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EquipmentBean equipment = null;
            while (rs.next()) {
                equipment = new EquipmentBean();
                equipment.setEquId(rs.getInt("equ_id"));
                equipment.setMatId(rs.getInt("mat_id"));
                equipment.setUnit(rs.getString("unit_vn"));
                equipment.setEquipmentName(StringUtil.decodeString(rs.getString("name_vn")));
                equipment.setQuantity(rs.getDouble("quantity"));
                equipment.setUsedCode(rs.getString("used_code"));
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

    public ArrayList searchSimpleEquipmentofHandedReport(int fieldid, String strFieldvalue, int rtId) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "used_code";
                break;
            case 2:
                strFieldname = "equ_id";
                break;
        }
        ResultSet rs = null;

        String sql = "select DISTINCT e.*, m2.name_vn,m2.name_en,u.unit_vn,u.unit_en"
                + //" from equipment as e" +
                " from require_transfer_detail as v"
                + " left join equipment AS e on v.equ_id = e.equ_id"
                + " left join material as m2 on m2.mat_id=e.mat_id"
                + " left join unit as u on u.uni_id = m2.uni_id"
                + " where v.rt_id=" + rtId;
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by equ_id desc";

        ArrayList equipmentList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EquipmentBean equipment = null;
            while (rs.next()) {
                equipment = new EquipmentBean();
                equipment.setEquId(rs.getInt("e.equ_id"));
                equipment.setMatId(rs.getInt("e.mat_id"));
                equipment.setManageCode(rs.getString("e.manage_code"));
                equipment.setEquipmentName(StringUtil.decodeString(rs.getString("m2.name_vn")));
                equipment.setUnit(rs.getString("u.unit_vn"));
                equipment.setUsedCode(rs.getString("e.used_code"));
                equipment.setColorCode(rs.getString("e.cc_id"));
                equipment.setStatus(rs.getInt("e.status"));
                equipment.setAppearedDate(DateUtil.formatDate(rs.getDate("appeared_date"), "dd/MM/yyyy"));
                equipment.setUsedDate(DateUtil.formatDate(rs.getDate("used_date"), "dd/MM/yyyy"));
                if (GenericValidator.isBlankOrNull(rs.getString("e.spec_certs"))) {
                    equipment.setSpecCerts("");
                } else {
                    equipment.setSpecCerts(StringUtil.decodeString(rs.getString("e.spec_certs")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("e.fuel_level"))) {
                    equipment.setFuelLevel("");
                } else {
                    equipment.setFuelLevel(StringUtil.decodeString(rs.getString("e.fuel_level")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("e.comment"))) {
                    equipment.setComment("");
                } else {
                    equipment.setComment(StringUtil.decodeString(rs.getString("e.comment")));
                }
                int status = rs.getInt("e.status");
                if (status > 0) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status" + status));
                }
                equipment.setKind(rs.getInt("kind"));
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

    public ArrayList searchAdvEquipment(EquipmentBean bean) throws Exception {
        ResultSet rs = null;

        String sql = "SELECT e.*, m1.miv_number, m2.name_vn,m2.name_en, c.contract_number,c.kind,c.appendix_contract_number,r.request_number,u.unit_vn,u.unit_en"
                + " FROM equipment AS e LEFT JOIN miv AS m1 ON m1.miv_id=e.miv_id LEFT JOIN contract AS c ON c.con_id=e.con_id LEFT JOIN material AS m2 ON m2.mat_id=e.mat_id LEFT JOIN request_detail AS rd ON rd.det_id=e.req_detail_id LEFT JOIN request AS r ON r.req_id=rd.req_id LEFT JOIN unit AS u ON u.uni_id = m2.uni_id where 1 ";

        if (bean.getMivId() > 0) {
            sql = sql + " AND miv_id = " + bean.getMivId() + "";
        }

        if (bean.getKind() > 0) {
            sql = sql + " AND e.kind = " + bean.getKind() + "";
        }

        if (bean.getMatId() > 0) {
            sql = sql + " AND mat_id = " + bean.getMatId() + "";
        }

        if (bean.getReqDetailId() > 0) {
            sql = sql + " AND req_detail_id = " + bean.getReqDetailId() + "";
        }

        if (bean.getConId() > 0) {
            sql = sql + " AND con_id = " + bean.getConId() + "";
        }

        if (bean.getStatus() > 0) {
            sql = sql + " AND status = " + bean.getStatus() + "";
        }

        if (!StringUtil.isBlankOrNull(bean.getMivNumber())) {
            sql = sql + " AND miv_number LIKE '%" + bean.getMivNumber() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getDecisionNumber())) {
            sql = sql + " AND decision_number LIKE '%" + bean.getDecisionNumber() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getManageCode())) {
            sql = sql + " AND manage_code LIKE '%" + bean.getManageCode() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getEquipmentName())) {
            sql = sql + " AND equipment_name LIKE '%" + bean.getEquipmentName() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getRequestNumber())) {
            sql = sql + " AND request_number LIKE '%" + bean.getRequestNumber() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getContractNumber())) {
            sql = sql + " AND contract_number LIKE '%" + bean.getContractNumber() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getTestNumber())) {
            sql = sql + " AND mrir_id LIKE '%" + bean.getTestNumber() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getUnit())) {
            sql = sql + " AND unit LIKE '%" + bean.getUnit() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getUsedCode())) {
            sql = sql + " AND used_code LIKE '%" + bean.getUsedCode() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getColorCode())) {
            sql = sql + " AND cc_id LIKE '%" + bean.getColorCode() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getSpecCerts())) {
            sql = sql + " AND spec_certs LIKE '%" + bean.getSpecCerts() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getFuelLevel())) {
            sql = sql + " AND fuel_level LIKE '%" + bean.getFuelLevel() + "%'";
        }

        if (!StringUtil.isBlankOrNull(bean.getAppearedDate())) {
            sql = sql + " AND appeared_date LIKE STR_TO_DATE('" + bean.getAppearedDate() + "','%d/%m/%Y %H')";
        }

        if (!StringUtil.isBlankOrNull(bean.getUsedDate())) {
            sql = sql + " AND used_date LIKE STR_TO_DATE('" + bean.getUsedDate() + "','%d/%m/%Y %H')";
        }

        if (!StringUtil.isBlankOrNull(bean.getComment())) {
            sql = sql + " AND comment LIKE '%" + bean.getComment() + "%'";
        }

        sql = sql + " order by equ_id desc";

        ArrayList equipmentList = new ArrayList();
        try {


            System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EquipmentBean equipment = null;

            while (rs.next()) {
                equipment = new EquipmentBean();
                equipment.setEquId(rs.getInt("equ_id"));
                equipment.setConId(rs.getInt("con_id"));
                equipment.setMatId(rs.getInt("mat_id"));
                equipment.setMivId(rs.getInt("miv_id"));
                equipment.setReqDetailId(rs.getInt("req_detail_id"));
                equipment.setMivNumber(rs.getString("miv_number"));
                equipment.setDecisionNumber(rs.getString("decision_number"));
                equipment.setTest(rs.getString("test"));
                equipment.setManageCode(rs.getString("manage_code"));
                equipment.setEquipmentName(StringUtil.decodeString(rs.getString("name_vn")));
                equipment.setRequestNumber(rs.getString("request_number"));
                if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
//                    equipment.setContractNumber(rs.getString("appendix_contract_number") + "(BS Số " + rs.getString("contract_number") + ")");
                    equipment.setContractNumber(rs.getString("contract_number"));
                } else {
                    equipment.setContractNumber(rs.getString("contract_number"));
                }
                equipment.setTestNumber(rs.getString("mrir_id"));
                equipment.setUnit(rs.getString("unit_vn"));
                equipment.setUsedCode(rs.getString("used_code"));
                equipment.setColorCode(rs.getString("cc_id"));
                equipment.setStatus(rs.getInt("status"));
                equipment.setAppearedDate(DateUtil.formatDate(rs.getDate("appeared_date"), "dd/MM/yyyy"));
                equipment.setUsedDate(DateUtil.formatDate(rs.getDate("used_date"), "dd/MM/yyyy"));
                if (GenericValidator.isBlankOrNull(rs.getString("spec_certs"))) {
                    equipment.setSpecCerts("");
                } else {
                    equipment.setSpecCerts(StringUtil.decodeString(rs.getString("spec_certs")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("fuel_level"))) {
                    equipment.setFuelLevel("");
                } else {
                    equipment.setFuelLevel(StringUtil.decodeString(rs.getString("fuel_level")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("comment"))) {
                    equipment.setComment("");
                } else {
                    equipment.setComment(StringUtil.decodeString(rs.getString("comment")));
                }
                if (rs.getInt("status") == EquipmentBean.S1) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status1"));
                }
                if (rs.getInt("status") == EquipmentBean.S2) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status2"));
                }
                if (rs.getInt("status") == EquipmentBean.S3) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status3"));
                }
                if (rs.getInt("status") == EquipmentBean.S4) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status4"));
                }
                equipment.setKind(rs.getInt("kind"));
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

    public boolean checkDecisionNumber(int id, String value) throws SQLException {
        ResultSet rs = null;
        try {
            //     System.out.println("executeQuery: " + sql);

            rs = DBUtil.executeQuery("SELECT * FROM equipment WHERE equ_id <> " + id + " AND decision_number = '" + value + "'");
            if (rs.next()) {

                return true;
            } else {

                return false;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
    }

    public boolean checkUsedCode(int id, String value) throws SQLException {
        ResultSet rs = null;
        try {
            
            //     System.out.println("executeQuery: " + sql);

            rs = DBUtil.executeQuery("SELECT * FROM equipment WHERE equ_id <> " + id + " AND used_code = '" + value + "'");
            if (rs.next()) {

                return true;
            } else {

                return false;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }finally {
            if (rs != null) {
                DBUtil.closeConnection(rs);
            }
            
        }
    }

    public ArrayList searchSimpleEquipmentOfOrg(int fieldid, String strFieldvalue, int orgId) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "used_code";
                break;
            case 2:
                strFieldname = "equ_id";
                break;
        }
        ResultSet rs = null;

        String sql = "SELECT e.*, m1.miv_number, m2.name_vn,m2.name_en, c.contract_number,c.kind,c.appendix_contract_number,r.request_number,u.unit_vn,u.unit_en FROM equipment AS e LEFT JOIN miv AS m1 ON m1.miv_id=e.miv_id LEFT JOIN contract AS c ON c.con_id=e.con_id LEFT JOIN material AS m2 ON m2.mat_id=e.mat_id LEFT JOIN request_detail AS rd ON rd.det_id=e.req_detail_id LEFT JOIN request AS r ON r.req_id=rd.req_id LEFT JOIN unit AS u ON u.uni_id = m2.uni_id"
                + " where e.manage_org=" + orgId;
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by equ_id desc";

        ArrayList equipmentList = new ArrayList();
        try {


            ////System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EquipmentBean equipment = null;
            while (rs.next()) {
                equipment = new EquipmentBean();
                equipment.setEquId(rs.getInt("equ_id"));
                equipment.setConId(rs.getInt("con_id"));
                equipment.setMatId(rs.getInt("mat_id"));
                equipment.setMivId(rs.getInt("miv_id"));
                equipment.setReqDetailId(rs.getInt("req_detail_id"));
                equipment.setMivNumber(rs.getString("miv_number"));
                equipment.setDecisionNumber(rs.getString("decision_number"));
                equipment.setTest(rs.getString("test"));
                equipment.setManageCode(rs.getString("manage_code"));
                equipment.setEquipmentName(StringUtil.decodeString(rs.getString("name_vn")));
                equipment.setRequestNumber(rs.getString("request_number"));
                if (rs.getInt("kind") == ContractBean.KIND_APPENDIX) {
//                    equipment.setContractNumber(rs.getString("appendix_contract_number") + "(BS Số " + rs.getString("contract_number") + ")");
                    equipment.setContractNumber(rs.getString("contract_number"));
                } else {
                    equipment.setContractNumber(rs.getString("contract_number"));
                }
                equipment.setTestNumber(rs.getString("mrir_id"));
                equipment.setUnit(rs.getString("unit_vn"));
                equipment.setUsedCode(rs.getString("used_code"));
                equipment.setColorCode(rs.getString("cc_id"));
                equipment.setStatus(rs.getInt("status"));
                equipment.setAppearedDate(DateUtil.formatDate(rs.getDate("appeared_date"), "dd/MM/yyyy"));
                equipment.setUsedDate(DateUtil.formatDate(rs.getDate("used_date"), "dd/MM/yyyy"));
                if (GenericValidator.isBlankOrNull(rs.getString("spec_certs"))) {
                    equipment.setSpecCerts("");
                } else {
                    equipment.setSpecCerts(StringUtil.decodeString(rs.getString("spec_certs")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("fuel_level"))) {
                    equipment.setFuelLevel("");
                } else {
                    equipment.setFuelLevel(StringUtil.decodeString(rs.getString("fuel_level")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("comment"))) {
                    equipment.setComment("");
                } else {
                    equipment.setComment(StringUtil.decodeString(rs.getString("comment")));
                }
                if (rs.getInt("status") == EquipmentBean.S1) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status1"));
                }
                if (rs.getInt("status") == EquipmentBean.S2) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status2"));
                }
                if (rs.getInt("status") == EquipmentBean.S3) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status3"));
                }
                if (rs.getInt("status") == EquipmentBean.S4) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status4"));
                }
                equipment.setKind(rs.getInt("kind"));
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

    public ArrayList searchSimpleEquipmentOfOrg1(int fieldid, String strFieldvalue, int orgId) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "used_code";
                break;
            case 2:
                strFieldname = "equ_id";
                break;
        }
        ResultSet rs = null;

        String sql = "select DISTINCT e.*, m2.name_vn,m2.name_en,u.unit_vn,u.unit_en"
                + //" from equipment as e" +
                " from verified_plan as v"
                + " left join equipment AS e on v.equ_id = e.equ_id"
                + " left join material as m2 on m2.mat_id=e.mat_id"
                + " left join unit as u on u.uni_id = m2.uni_id"
                + " where e.manage_org=" + orgId;
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by equ_id desc";

        ArrayList equipmentList = new ArrayList();
        try {


            System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EquipmentBean equipment = null;
            while (rs.next()) {
                equipment = new EquipmentBean();
                equipment.setEquId(rs.getInt("e.equ_id"));
                equipment.setMatId(rs.getInt("e.mat_id"));
                equipment.setManageCode(rs.getString("e.manage_code"));
                equipment.setEquipmentName(StringUtil.decodeString(rs.getString("m2.name_vn")));
                equipment.setUnit(rs.getString("u.unit_vn"));
                equipment.setUsedCode(rs.getString("e.used_code"));
                equipment.setColorCode(rs.getString("e.cc_id"));
                equipment.setStatus(rs.getInt("e.status"));
                equipment.setAppearedDate(DateUtil.formatDate(rs.getDate("appeared_date"), "dd/MM/yyyy"));
                equipment.setUsedDate(DateUtil.formatDate(rs.getDate("used_date"), "dd/MM/yyyy"));
                if (GenericValidator.isBlankOrNull(rs.getString("e.spec_certs"))) {
                    equipment.setSpecCerts("");
                } else {
                    equipment.setSpecCerts(StringUtil.decodeString(rs.getString("e.spec_certs")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("e.fuel_level"))) {
                    equipment.setFuelLevel("");
                } else {
                    equipment.setFuelLevel(StringUtil.decodeString(rs.getString("e.fuel_level")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("e.comment"))) {
                    equipment.setComment("");
                } else {
                    equipment.setComment(StringUtil.decodeString(rs.getString("e.comment")));
                }
                int status = rs.getInt("e.status");
                if (status > 0) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status" + status));
                }
                equipment.setKind(rs.getInt("kind"));
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

    public ArrayList searchSimpleEquipmentOfRtId(int fieldid, String strFieldvalue, int rtId) throws Exception {
        String strFieldname = "";
        switch (fieldid) {
            case 1:
                strFieldname = "used_code";
                break;
            case 2:
                strFieldname = "equ_id";
                break;
        }
        ResultSet rs = null;

        String sql = "select DISTINCT e.*, m2.name_vn,m2.name_en,u.unit_vn,u.unit_en"
                + //" from equipment as e" +
                " from require_transfer_detail as v"
                + " left join equipment AS e on v.equ_id = e.equ_id"
                + " left join material as m2 on m2.mat_id=e.mat_id"
                + " left join unit as u on u.uni_id = m2.uni_id"
                + " where v.rt_id=" + rtId;
        if ((fieldid > 0) && (!StringUtil.isBlankOrNull(strFieldvalue))) {
            sql = sql + " AND " + strFieldname + " LIKE '%" + strFieldvalue + "%'";
        }
        sql = sql + " order by equ_id desc";

        ArrayList equipmentList = new ArrayList();
        try {


            System.out.println("sql=" + sql);

            rs = DBUtil.executeQuery(sql);
            EquipmentBean equipment = null;
            while (rs.next()) {
                equipment = new EquipmentBean();
                equipment.setEquId(rs.getInt("e.equ_id"));
                equipment.setMatId(rs.getInt("e.mat_id"));
                equipment.setManageCode(rs.getString("e.manage_code"));
                equipment.setEquipmentName(StringUtil.decodeString(rs.getString("m2.name_vn")));
                equipment.setUnit(rs.getString("u.unit_vn"));
                equipment.setUsedCode(rs.getString("e.used_code"));
                equipment.setColorCode(rs.getString("e.cc_id"));
                equipment.setStatus(rs.getInt("e.status"));
                equipment.setAppearedDate(DateUtil.formatDate(rs.getDate("appeared_date"), "dd/MM/yyyy"));
                equipment.setUsedDate(DateUtil.formatDate(rs.getDate("used_date"), "dd/MM/yyyy"));
                if (GenericValidator.isBlankOrNull(rs.getString("e.spec_certs"))) {
                    equipment.setSpecCerts("");
                } else {
                    equipment.setSpecCerts(StringUtil.decodeString(rs.getString("e.spec_certs")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("e.fuel_level"))) {
                    equipment.setFuelLevel("");
                } else {
                    equipment.setFuelLevel(StringUtil.decodeString(rs.getString("e.fuel_level")));
                }
                if (GenericValidator.isBlankOrNull(rs.getString("e.comment"))) {
                    equipment.setComment("");
                } else {
                    equipment.setComment(StringUtil.decodeString(rs.getString("e.comment")));
                }
                int status = rs.getInt("e.status");
                if (status > 0) {
                    equipment.setStatusName(MCUtil.getBundleString("message.equipment.status" + status));
                }
                equipment.setKind(rs.getInt("kind"));
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
}
