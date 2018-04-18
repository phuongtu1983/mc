package com.venus.mc.bean;

/**
 * @author thucao
 */
public class HandedReportDetailBean {

    private String detId;
    private int hrId;
    private int equId;
    private String equipmentName;
    private String usedCode;
    private double quantity;
    private String unitName;
    private String comment;
    private String specifications;

    public HandedReportDetailBean() {
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

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setHrId(int hrId) {
        this.hrId = hrId;
    }

    public int getHrId() {
        return hrId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getSpecifications() {
        return specifications;
    }
}
