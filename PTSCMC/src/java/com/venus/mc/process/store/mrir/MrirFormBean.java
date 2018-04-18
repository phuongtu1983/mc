/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.mrir;

import com.venus.mc.bean.MrirBean;

/**
 * @author kngo
 *  Author : kngo
 *  Created Date : 22/09/2009
 */
public class MrirFormBean extends org.apache.struts.action.ActionForm {

    public static final int KIND_MSV_COMPANY = 0;
    public static final int KIND_MSV_PROJECT = 1;
    public static final int KIND_MRV = 2;
    //public static final int KIND_MRV_PROJECT = 3;
    private int mrirId;
    private String createdDate;
    private int conId; // foreign key : reference to contract(con_id)    
    private int dnId;
    private String dnNumber;
    private int venId;
    private String venName;
    private String mrirNumber;
    private String conNumber;
    private int proId;
    private int materialKind;
    private String proName;
    private String note;
    private String blNo;
    private String invoiceNo;
    private String plNo;
    private int cbxMaterialOfReq;
    private int reqId;
    private String[] chkDetId;
    private String[] detId;
    private int[] matId;
    private String[] unit;
    private String[] price;
    private String[] quantity;
    private String[] manufacture;
    private String[] heatSerial;
    private String[] inspection;
    private String[] original;
    private String[] quality;
    private String[] warranty;
    private String[] insurance;
    private String[] approvalType;
    private String[] complCert;
    private int[] reqDetailId;
    private String[] materialGrade;
    private String[] materialType;
    private String[] rating;
    private String[] size1;
    private String[] size2;
    private String[] size3;
    private String[] traceNo;
    private String[] certNo;
    private String[] colourCode;
    private String[] comment;
    private String[] itemNo;
    private String[] systemNo;
    private String[] timeUse;
    private String[] location;
    private int[] matKind;
    private int[] dndId;
    private int kind;

    public MrirFormBean() {
        super();
    }

    //constructure region
    public MrirFormBean(MrirBean bean) {
        this.mrirId = bean.getMrirId();
        this.mrirNumber = bean.getMrirNumber();
        this.createdDate = bean.getCreatedDate();
        this.conId = bean.getConId();
        this.conNumber = bean.getConNumber();
        this.dnId = bean.getDnId();
        this.dnNumber = bean.getDnNumber();

        this.note = bean.getNote();
        this.blNo = bean.getBlNo();
        this.invoiceNo = bean.getInvoiceNo();
        this.plNo = bean.getPlNo();

        this.venName = bean.getVenName();

        this.kind = bean.getKind();
        this.proId = bean.getProId();
        this.proName = bean.getProName();
        this.materialKind = bean.getMaterialKind();
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

    public int getDnId() {
        return this.dnId;
    }

    public void setDnId(int dnId) {
        this.dnId = dnId;
    }

    public String getVenName() {
        return this.venName;
    }

    public void setVenName(String venName) {
        this.venName = venName;
    }

    public String getMrirNumber() {
        return this.mrirNumber;
    }

    public void setMrirNumber(String mrirNumber) {
        this.mrirNumber = mrirNumber;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public void setMatId(int[] matId) {
        this.matId = matId;
    }

    public int[] getMatId() {
        return matId;
    }

    public String[] getUnit() {
        return unit;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    public String[] getPrice() {
        return price;
    }

    public void setPrice(String[] price) {
        this.price = price;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    public String[] getManufacture() {
        return manufacture;
    }

    public void setManufacture(String[] manufacture) {
        this.manufacture = manufacture;
    }

    public String[] getHeatSerial() {
        return heatSerial;
    }

    public void setHeatSerial(String[] heatSerial) {
        this.heatSerial = heatSerial;
    }

    public String[] getInspection() {
        return inspection;
    }

    public void setInspection(String[] inspection) {
        this.inspection = inspection;
    }

    public String[] getOriginal() {
        return original;
    }

    public void setOriginal(String[] original) {
        this.original = original;
    }

    public String[] getQuality() {
        return quality;
    }

    public void setQuality(String[] quality) {
        this.quality = quality;
    }

    public String[] getWarranty() {
        return warranty;
    }

    public void setWarranty(String[] warranty) {
        this.warranty = warranty;
    }

    public String[] getInsurance() {
        return insurance;
    }

    public void setInsurance(String[] insurance) {
        this.insurance = insurance;
    }

    public String[] getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(String[] approvalType) {
        this.approvalType = approvalType;
    }

    public String[] getComplCert() {
        return complCert;
    }

    public void setComplCert(String[] complCert) {
        this.complCert = complCert;
    }

    public String getDnNumber() {
        return dnNumber;
    }

    public void setConNumber(String conNumber) {
        this.conNumber = conNumber;
    }

    public String getConNumber() {
        return conNumber;
    }

    public void setDnNumber(String dnNumber) {
        this.dnNumber = dnNumber;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public int getVenId() {
        return venId;
    }

    public void setReqDetailId(int[] reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public int[] getReqDetailId() {
        return reqDetailId;
    }

    public int getCbxMaterialOfReq() {
        return cbxMaterialOfReq;
    }

    public void setCbxMaterialOfReq(int cbxMaterialOfReq) {
        this.cbxMaterialOfReq = cbxMaterialOfReq;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public int getReqId() {
        return reqId;
    }

    public void setChkDetId(String[] chkDetId) {
        this.chkDetId = chkDetId;
    }

    public String[] getChkDetId() {
        return chkDetId;
    }

    public void setDetId(String[] detId) {
        this.detId = detId;
    }

    public String[] getDetId() {
        return detId;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getKind() {
        return kind;
    }

    public void setCertNo(String[] certNo) {
        this.certNo = certNo;
    }

    public String[] getCertNo() {
        return certNo;
    }

    public void setColourCode(String[] colourCode) {
        this.colourCode = colourCode;
    }

    public String[] getColourCode() {
        return colourCode;
    }

    public void setMaterialGrade(String[] materialGrade) {
        this.materialGrade = materialGrade;
    }

    public String[] getMaterialGrade() {
        return materialGrade;
    }

    public void setMaterialType(String[] materialType) {
        this.materialType = materialType;
    }

    public String[] getMaterialType() {
        return materialType;
    }

    public void setRating(String[] rating) {
        this.rating = rating;
    }

    public String[] getRating() {
        return rating;
    }

    public void setSize1(String[] size1) {
        this.size1 = size1;
    }

    public String[] getSize1() {
        return size1;
    }

    public void setSize2(String[] size2) {
        this.size2 = size2;
    }

    public String[] getSize2() {
        return size2;
    }

    public void setSize3(String[] size3) {
        this.size3 = size3;
    }

    public String[] getSize3() {
        return size3;
    }

    public void setTraceNo(String[] traceNo) {
        this.traceNo = traceNo;
    }

    public String[] getTraceNo() {
        return traceNo;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProName() {
        return proName;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getProId() {
        return proId;
    }

    public void setComment(String[] comment) {
        this.comment = comment;
    }

    public String[] getComment() {
        return comment;
    }

    public void setItemNo(String[] itemNo) {
        this.itemNo = itemNo;
    }

    public String[] getItemNo() {
        return itemNo;
    }

    public void setLocation(String[] location) {
        this.location = location;
    }

    public String[] getLocation() {
        return location;
    }

    public void setMatKind(int[] matKind) {
        this.matKind = matKind;
    }

    public int[] getMatKind() {
        return matKind;
    }

    public void setSystemNo(String[] systemNo) {
        this.systemNo = systemNo;
    }

    public String[] getSystemNo() {
        return systemNo;
    }

    public void setTimeUse(String[] timeUse) {
        this.timeUse = timeUse;
    }

    public String[] getTimeUse() {
        return timeUse;
    }

    public void setMaterialKind(int materialKind) {
        this.materialKind = materialKind;
    }

    public int getMaterialKind() {
        return materialKind;
    }

    public void setDndId(int[] dndId) {
        this.dndId = dndId;
    }

    public int[] getDndId() {
        return dndId;
    }
}
