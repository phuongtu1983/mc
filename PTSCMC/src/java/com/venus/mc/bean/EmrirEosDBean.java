/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class EmrirEosDBean {

    private int emrirId; // primary key
    private String createdDate;
    private String econNumber;
    private String proName;
    private String venName;
    private int ednId;
    private String emrirNumber;
    private int detId; // primary key
    private int ematId; // foreign key : reference to material(mat_id)
    private String matName;
    private String unit;
    private int quantity;
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
    private String systemNo;
    private String itemNo;
    private String location;
    private int matKind;
    private String comment;
    private int eosdId; // primary key
    private String eosdNumber;
    private int closed;
    private String closedDate;
    private String conclude;

    //constructure region
    public EmrirEosDBean() {
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getProName() {
        return this.proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getVenName() {
        return this.venName;
    }

    public void setVenName(String venName) {
        this.venName = venName;
    }

    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public void setEmatId(int ematId) {
        this.ematId = ematId;
    }

    public int getEmatId() {
        return ematId;
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

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getHeatSerial() {
        return this.heatSerial;
    }

    public void setHeatSerial(String heatSerial) {
        this.heatSerial = heatSerial;
    }

    public void setEosdId(int eosdId) {
        this.eosdId = eosdId;
    }

    public int getEosdId() {
        return eosdId;
    }

    public void setEosdNumber(String eosdNumber) {
        this.eosdNumber = eosdNumber;
    }

    public String getEosdNumber() {
        return eosdNumber;
    }

    public int getClosed() {
        return this.closed;
    }

    public void setClosed(int closed) {
        this.closed = closed;
    }

    public String getClosedDate() {
        return this.closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public String getConclude() {
        return this.conclude;
    }

    public void setConclude(String conclude) {
        this.conclude = conclude;
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

    public void setEconNumber(String econNumber) {
        this.econNumber = econNumber;
    }

    public String getEconNumber() {
        return econNumber;
    }

    public void setEdnId(int ednId) {
        this.ednId = ednId;
    }

    public int getEdnId() {
        return ednId;
    }

    public void setEmrirId(int emrirId) {
        this.emrirId = emrirId;
    }

    public void setEmrirNumber(String emrirNumber) {
        this.emrirNumber = emrirNumber;
    }

    public int getEmrirId() {
        return emrirId;
    }

    public String getEmrirNumber() {
        return emrirNumber;
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

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}
