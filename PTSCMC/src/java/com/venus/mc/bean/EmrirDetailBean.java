/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class EmrirDetailBean {

    private String detId; // primary key
    private int emrirId; // foreign key : reference to mrir(mrir_id)
    private int ematId; // foreign key : reference to material(mat_id)
    private String matName;
    private String matCode;
    private String unit;
    private double quantity;
    private double price;
    private String heatSerial;
    private String materialGrade;
    private String materialType;
    private String rating;
    private String size1;
    private String size2;
    private String size3;
    private String traceNo;
    private String certNo;
    private String colourCode;
    private int status;
    private String systemNo;
    private String itemNo;
    private String location;
    private int matKind;
    private String comment;

    //constructure region
    public EmrirDetailBean() {
        super();
    }

    //properties region
    public String getDetId() {
        return this.detId;
    }

    public void setDetId(String detId) {
        this.detId = detId;
    }

    public int getEmrirId() {
        return this.emrirId;
    }

    public void setEmrirId(int emrirId) {
        this.emrirId = emrirId;
    }

    public int getEmatId() {
        return this.ematId;
    }

    public void setEmatId(int ematId) {
        this.ematId = ematId;
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

    public String getHeatSerial() {
        return this.heatSerial;
    }

    public void setHeatSerial(String heatSerial) {
        this.heatSerial = heatSerial;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
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

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemNo() {
        return itemNo;
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

    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo;
    }

    public String getSystemNo() {
        return systemNo;
    }
}
