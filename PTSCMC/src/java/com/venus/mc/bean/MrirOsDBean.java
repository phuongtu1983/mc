/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class MrirOsDBean {

    private int mrirId; // primary key
    private String createdDate;
    private int conId; // foreign key : reference to contract(con_id)
    private int drId; // foreign key : reference to delivery_request(dr_id)
    private String conNumber;
    private String proName;
    private String venName;
    private int dnId;
    private int reqId;
    private String reqNumber;
    private String mrirNumber;
    private String blNo;
    private String invoiceNo;
    private String plNo;
    private int detId; // primary key
    private int matId; // foreign key : reference to material(mat_id)
    private String matName;
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
    private String materialGrade;
    private String materialType;
    private String rating;
    private String size1;
    private String size2;
    private String size3;
    private String traceNo;
    private String certNo;
    private String colourCode;
    private int osdId; // primary key
    private String osdNumber;
    private int closed;
    private String closedDate;
    private String conclude;
    private int reqDetailId;
    private String itemNo;
    private String systemNo;
    private String comment;

    //constructure region
    public MrirOsDBean() {
    }

    //properties region
    public int getMrirId() {
        return this.mrirId;
    }

    public void setMrirId(int mrirId) {
        this.mrirId = mrirId;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getConId() {
        return this.conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public String getConNumber() {
        return this.conNumber;
    }

    public void setConNumber(String conNumber) {
        this.conNumber = conNumber;
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

    public int getDrId() {
        return this.drId;
    }

    public void setDrId(int drId) {
        this.drId = drId;
    }

    public int getDnId() {
        return this.dnId;
    }

    public void setDnId(int dnId) {
        this.dnId = dnId;
    }

    public int getReqId() {
        return this.reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public String getReqNumber() {
        return this.reqNumber;
    }

    public void setReqNumber(String reqNumber) {
        this.reqNumber = reqNumber;
    }

    public String getMrirNumber() {
        return this.mrirNumber;
    }

    public void setMrirNumber(String mrirNumber) {
        this.mrirNumber = mrirNumber;
    }

    public String getBlNo() {
        return this.blNo;
    }

    public void setBlNo(String blNo) {
        this.blNo = blNo;
    }

    public String getInvoiceNo() {
        return this.invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getPlNo() {
        return this.plNo;
    }

    public void setPlNo(String plNo) {
        this.plNo = plNo;
    }

    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
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

    public int getOsdId() {
        return this.osdId;
    }

    public void setOsdId(int osdId) {
        this.osdId = osdId;
    }

    public String getOsdNumber() {
        return this.osdNumber;
    }

    public void setOsdNumber(String osdNumber) {
        this.osdNumber = osdNumber;
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

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public int getReqDetailId() {
        return reqDetailId;
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

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}
