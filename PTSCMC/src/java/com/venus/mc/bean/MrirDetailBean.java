/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 * @author kngo
 *  Author : kngo
 *  Created Date : 22/09/2009
 */
public class MrirDetailBean {

    private String detId; // primary key
    private int mrirId; // foreign key : reference to mrir(mrir_id)
    private int matId; // foreign key : reference to material(mat_id)
    private int reqId;
    private int dndId;
    private int reqDetailId;
    private String reqNumber;
    private String matName;
    private String matCode;
    private String proName;
    private String unit;
    private double quantity;
    private double price;
    private String manufacture;
    private String heatSerial;
    private String inspection;
    private String original;
    private String quality;
    private String warranty;
    private String insurance;
    private String approvalType;
    private String complCert;
    private String materialGrade;
    private String materialType;
    private String rating;
    private String size1;
    private String size2;
    private String size3;
    private String traceNo;
    private String certNo;
    private String colourCode;
    private String systemNo;
    private String itemNo;
    private String timeUse;
    private String location;
    private int matKind;
    private int materialKind;
    private String comment;
    private int status; //0: chua xu ly 1: da xu ly
    private String note;
    private double remainQuantity;

    //constructure region
    public MrirDetailBean() {
        super();
        manufacture = "";
        heatSerial = "";
        inspection = "";
        original = "";
        quality = "";
        warranty = "";
        insurance = "";
        approvalType = "";
        complCert = "";
        reqNumber = "";
        status = 0;
        materialGrade = "";
        materialType = "";
        rating = "";
        size1 = "";
        size2 = "";
        size3 = "";
        traceNo = "";
        certNo = "";
        colourCode = "";
        proName = "";
        systemNo = "";
        itemNo = "";
        location = "";
        comment = "";
        matKind = 0;
        materialKind = 0;
    }

    public void setDetId(String detId) {
        this.detId = detId;
    }

    public String getDetId() {
        return detId;
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

    public String getMatName() {
        return this.matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
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

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public int getReqDetailId() {
        return this.reqDetailId;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setReqNumber(String reqNumber) {
        this.reqNumber = reqNumber;
    }

    public String getReqNumber() {
        return reqNumber;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public int getReqId() {
        return reqId;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setColourCode(String colourCode) {
        this.colourCode = colourCode;
    }

    public String getColourCode() {
        return colourCode;
    }

    public void setMaterialGrade(String materialGrade) {
        this.materialGrade = materialGrade;
    }

    public String getMaterialGrade() {
        return materialGrade;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public void setSize1(String size1) {
        this.size1 = size1;
    }

    public String getSize1() {
        return size1;
    }

    public void setSize2(String size2) {
        this.size2 = size2;
    }

    public String getSize2() {
        return size2;
    }

    public void setSize3(String size3) {
        this.size3 = size3;
    }

    public String getSize3() {
        return size3;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    public String getTraceNo() {
        return traceNo;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo;
    }

    public String getSystemNo() {
        return systemNo;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setMatKind(int matKind) {
        this.matKind = matKind;
    }

    public int getMatKind() {
        return matKind;
    }

    public void setTimeUse(String timeUse) {
        this.timeUse = timeUse;
    }

    public String getTimeUse() {
        return timeUse;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setMaterialKind(int materialKind) {
        this.materialKind = materialKind;
    }

    public int getMaterialKind() {
        return materialKind;
    }

    public void setDndId(int dndId) {
        this.dndId = dndId;
    }

    public int getDndId() {
        return dndId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getRemainQuantity() {
        return remainQuantity;
    }

    public void setRemainQuantity(double remainQuantity) {
        this.remainQuantity = remainQuantity;
    }
}
