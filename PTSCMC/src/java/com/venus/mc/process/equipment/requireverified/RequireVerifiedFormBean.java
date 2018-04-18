/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.requireverified;

import com.venus.mc.bean.RequireVerifiedBean;

/**
 *
 * @author mai vinh loc
 */
public class RequireVerifiedFormBean extends org.apache.struts.action.ActionForm {
    //fields region
    private int rvId;
    private int createdEmpId;
    private String createdDate;
    private String rvNumber;
    private String reason;
    private String orgName;
    private int orgId;
    private String[] detId;
    private int[] equId;
    private String[] useCode;
    private String[] equipmentName;
    private String[] timeEstimate;
    private String[] comment;

    //constructure region
    public RequireVerifiedFormBean() {
        super();
    }

    public RequireVerifiedFormBean(RequireVerifiedBean bean) {
        super();
        this.rvId = bean.getRvId();
        this.createdEmpId = bean.getCreatedEmpId();
        this.createdDate = bean.getCreatedDate();
        this.rvNumber = bean.getRvNumber();
        this.reason = bean.getReason();
        this.orgId = bean.getOrgId();
        this.orgName = bean.getOrgName();
    }

    public void setEquId(int[] equId) {
        this.equId = equId;
    }

    public int[] getEquId() {
        return equId;
    }

    public int getCreatedEmpId() {
        return createdEmpId;
    }

    public void setCreatedEmpId(int createdEmpId) {
        this.createdEmpId = createdEmpId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setComment(String[] comment) {
        this.comment = comment;
    }

    public String[] getComment() {
        return comment;
    }

    public void setDetId(String[] detId) {
        this.detId = detId;
    }

    public String[] getDetId() {
        return detId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setRvId(int rvId) {
        this.rvId = rvId;
    }

    public int getRvId() {
        return rvId;
    }

    public void setRvNumber(String rvNumber) {
        this.rvNumber = rvNumber;
    }

    public String getRvNumber() {
        return rvNumber;
    }

    public void setTimeEstimate(String[] timeEstimate) {
        this.timeEstimate = timeEstimate;
    }

    public String[] getTimeEstimate() {
        return timeEstimate;
    }

    public void setEquipmentName(String[] equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String[] getEquipmentName() {
        return equipmentName;
    }

    public String[] getUseCode() {
        return useCode;
    }

    public void setUseCode(String[] useCode) {
        this.useCode = useCode;
    }
}
