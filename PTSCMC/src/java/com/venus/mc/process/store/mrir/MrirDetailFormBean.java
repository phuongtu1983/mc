/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.mrir;

/**
 * @author kngo
 *  Author : kngo
 *  Created Date : 22/09/2009
 */
public class MrirDetailFormBean extends org.apache.struts.action.ActionForm {
    private int detId;
    private int mrirId;
    private int matId;
    private String unit;
    private double quantity;
    private String manufacture;
    private String heatSerial;
    private String inspection;
    private String original;
    private String quality;
    private String warranty;
    private String insurance;
    private String approvalType;
    private String complCert;

    //constructure region
    public MrirDetailFormBean() {
        super();
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getMrirId() {
        return this.mrirId;
    }

    public void setMrirId(int mrirId) {
        this.mrirId = mrirId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getManufacture() {
        return this.manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getHeatSerial() {
        return this.heatSerial;
    }

    public void setHeatSerial(String heatSerial) {
        this.heatSerial = heatSerial;
    }

    public String getInspection() {
        return this.inspection;
    }

    public void setInspection(String inspection) {
        this.inspection = inspection;
    }

    public String getOriginal() {
        return this.original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getQuality() {
        return this.quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getWarranty() {
        return this.warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getInsurance() {
        return this.insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getApprovalType() {
        return this.approvalType;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    public String getComplCert() {
        return this.complCert;
    }

    public void setComplCert(String complCert) {
        this.complCert = complCert;
    }
}

