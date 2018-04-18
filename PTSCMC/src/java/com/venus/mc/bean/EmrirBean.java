/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class EmrirBean {

    private int emrirId; // primary key
    private String createdDate;
    private String searchStartDate;
    private String searchEndDate;
    private String emrirNumber;
    private int ednId;
    private String ednNumber;
    private String note;
    private String packingListNo;
    private String invoiceNo;
    private int status;
    private String conNumber;
    private int proId;
    private String proName;
    private int orgId;
    private String orgName;

    //constructure region
    public EmrirBean() {
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

    public String getSearchStartDate() {
        return this.searchStartDate;
    }

    public void setSearchStartDate(String searchStartDate) {
        this.searchStartDate = searchStartDate;
    }

    public String getSearchEndDate() {
        return this.searchEndDate;
    }

    public void setSearchEndDate(String searchEndDate) {
        this.searchEndDate = searchEndDate;
    }

    public String getEmrirNumber() {
        return this.emrirNumber;
    }

    public void setEmrirNumber(String emrirNumber) {
        this.emrirNumber = emrirNumber;
    }

    public int getEdnId() {
        return this.ednId;
    }

    public void setEdnId(int ednId) {
        this.ednId = ednId;
    }

    public String getEdnNumber() {
        return this.ednNumber;
    }

    public void setEdnNumber(String ednNumber) {
        this.ednNumber = ednNumber;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPackingListNo(String packingListNo) {
        this.packingListNo = packingListNo;
    }

    public String getPackingListNo() {
        return packingListNo;
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
}
