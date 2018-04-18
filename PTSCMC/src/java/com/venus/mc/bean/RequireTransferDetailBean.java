package com.venus.mc.bean;

/**
 * @author thucao
 */
public class RequireTransferDetailBean {

    private String detId;
    private int rtId;
    private int equId;
     private int orgId;
    private String equipmentName;
    private String usedCode;
    private String timeEstimate;
    private double quantity;
    private int status;
    private String unitName;

    public RequireTransferDetailBean() {
    }

    public String getUsedCode() {
        return usedCode;
    }

    public void setUsedCode(String usedCode) {
        this.usedCode = usedCode;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getEquId() {
        return equId;
    }

    public void setEquId(int equId) {
        this.equId = equId;
    }

    public void setDetId(String detId) {
        this.detId = detId;
    }

    public String getDetId() {
        return detId;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setRtId(int rtId) {
        this.rtId = rtId;
    }

    public int getRtId() {
        return rtId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setTimeEstimate(String timeEstimate) {
        this.timeEstimate = timeEstimate;
    }

    public String getTimeEstimate() {
        return timeEstimate;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getOrgId() {
        return orgId;
    }
    
}
