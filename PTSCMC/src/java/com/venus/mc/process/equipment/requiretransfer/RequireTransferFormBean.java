/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.requiretransfer;

import com.venus.mc.bean.RequireTransferBean;

/**
 *
 * @author mai vinh loc
 */
public class RequireTransferFormBean extends org.apache.struts.action.ActionForm {
    //fields region
    private int rtId;
    private int createdEmpId;
    private String createdDate;
    private String rtNumber;
    private String reason;
    private String orgName;
    private int orgId;
    private String[] detId;
    private int[] equId;
    private String[] useCode;
    private String[] equipmentName;
    private String[] timeEstimate;
    private int[] status;
    private String[] quantity;

    //constructure region
    public RequireTransferFormBean() {
        super();
    }

    public RequireTransferFormBean(RequireTransferBean bean) {
        super();
        this.rtId = bean.getRtId();
        this.createdEmpId = bean.getCreatedEmpId();
        this.createdDate = bean.getCreatedDate();
        this.rtNumber = bean.getRtNumber();
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

    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public void setStatus(int[] status) {
        this.status = status;
    }

    public int[] getStatus() {
        return status;
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

    public void setRtId(int rtId) {
        this.rtId = rtId;
    }

    public int getRtId() {
        return rtId;
    }

    public void setRtNumber(String rtNumber) {
        this.rtNumber = rtNumber;
    }

    public String getRtNumber() {
        return rtNumber;
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
