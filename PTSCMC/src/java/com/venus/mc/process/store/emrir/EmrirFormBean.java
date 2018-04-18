/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.emrir;

import com.venus.mc.bean.EmrirBean;

/**
 *
 * @author kngo
 */
public class EmrirFormBean extends org.apache.struts.action.ActionForm {

    private int emrirId;
    private String createdDate;
    private int createdEmpId;
    private int createdEmpName;
    private int ednId;
    private String ednNumber;
    private String emrirNumber;
    private String note;
    private String conNumber;
    private int proId;
    private String proName;
    private int orgId;
    private String orgName;
    private int cbxMaterialOfDn;
    private String packingListNo;
    private String invoiceNo;
    private String[] chkDetId;
    private String[] detId;
    private String[] quantity;
    private int[] ematId;
    private String[] unit;
    private String[] price;
    private String[] heatSerial;
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
    private String[] location;
    private int[] matKind;
    private int status;

    public EmrirFormBean() {
        super();
    }

    //constructure region
    public EmrirFormBean(EmrirBean bean) {
        this.emrirId = bean.getEmrirId();
        this.createdDate = bean.getCreatedDate();
        this.ednId = bean.getEdnId();
        this.emrirNumber = bean.getEmrirNumber();
        this.note = bean.getNote();
        this.ednNumber = bean.getEdnNumber();
        this.packingListNo = bean.getPackingListNo();
        this.status = bean.getStatus();
        this.conNumber = bean.getConNumber();
        this.proId = bean.getProId();
        this.proName = bean.getProName();
        this.orgId = bean.getOrgId();
        this.orgName = bean.getOrgName();
    }

    //properties region
    public int getEmrirId() {
        return this.emrirId;
    }

    public void setEmrirId(int emrirId) {
        this.emrirId = emrirId;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getEdnId() {
        return this.ednId;
    }

    public void setEdnId(int ednId) {
        this.ednId = ednId;
    }

    public String getEmrirNumber() {
        return this.emrirNumber;
    }

    public void setEmrirNumber(String emrirNumber) {
        this.emrirNumber = emrirNumber;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String[] getHeatSerial() {
        return heatSerial;
    }

    public void setHeatSerial(String[] heatSerial) {
        this.heatSerial = heatSerial;
    }

    public void setEdnNumber(String ednNumber) {
        this.ednNumber = ednNumber;
    }

    public String getEdnNumber() {
        return ednNumber;
    }

    public void setCreatedEmpId(int createdEmpId) {
        this.createdEmpId = createdEmpId;
    }

    public int getCreatedEmpId() {
        return createdEmpId;
    }

    public void setCreatedEmpName(int createdEmpName) {
        this.createdEmpName = createdEmpName;
    }

    public int getCreatedEmpName() {
        return createdEmpName;
    }

    public void setCbxMaterialOfDn(int cbxMaterialOfDn) {
        this.cbxMaterialOfDn = cbxMaterialOfDn;
    }

    public int getCbxMaterialOfDn() {
        return cbxMaterialOfDn;
    }

    public void setCertNo(String[] certNo) {
        this.certNo = certNo;
    }

    public String[] getCertNo() {
        return certNo;
    }

    public void setChkDetId(String[] chkDetId) {
        this.chkDetId = chkDetId;
    }

    public String[] getChkDetId() {
        return chkDetId;
    }

    public void setColourCode(String[] colourCode) {
        this.colourCode = colourCode;
    }

    public String[] getColourCode() {
        return colourCode;
    }

    public void setDetId(String[] detId) {
        this.detId = detId;
    }

    public String[] getDetId() {
        return detId;
    }

    public void setEmatId(int[] ematId) {
        this.ematId = ematId;
    }

    public int[] getEmatId() {
        return ematId;
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

    public void setPackingListNo(String packingListNo) {
        this.packingListNo = packingListNo;
    }

    public String getPackingListNo() {
        return packingListNo;
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

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public void setConNumber(String conNumber) {
        this.conNumber = conNumber;
    }

    public String getConNumber() {
        return conNumber;
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

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getProId() {
        return proId;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProName() {
        return proName;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
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
}
