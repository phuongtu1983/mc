/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 * @author kngo
 *  Author : kngo
 * Created Date : 22/09/2009
 */
public class MrirBean {

    private int mrirId; // primary key
    private String createdDate;
    private String searchStartDate;
    private String searchEndDate;
    private String mrirNumber;
    private int conId; // foreign key : reference to contract(con_id)
    private String conNumber;
    private String venName;
    private int dnId;
    private String dnNumber;
    private int proId;
    private String proName;
    //private int reqId;
    private String reqNumber;
    private String note;
    private String blNo;
    private String invoiceNo;
    private String plNo;
    private int osdId;
    private int status;
    private int kind;
    private int materialKind;

    //constructure region
    public MrirBean() {
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

    public String getMrirNumber() {
        return this.mrirNumber;
    }

    public void setMrirNumber(String mrirNumber) {
        this.mrirNumber = mrirNumber;
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

    public String getDnNumber() {
        return this.dnNumber;
    }

    public void setDnNumber(String dnNumber) {
        this.dnNumber = dnNumber;
    }

//    public String getReqNumber() {
//        return this.reqNumber;
//    }
//
//    public void setReqNumber(String reqNumber) {
//        this.reqNumber = reqNumber;
//    }
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

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setConNumber(String conNumber) {
        this.conNumber = conNumber;
    }

    public String getConNumber() {
        return conNumber;
    }

    public void setVenName(String venName) {
        this.venName = venName;
    }

    public String getVenName() {
        return venName;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getKind() {
        return kind;
    }

    public void setOsdId(int osdId) {
        this.osdId = osdId;
    }

    public int getOsdId() {
        return osdId;
    }

    public void setReqNumber(String reqNumber) {
        this.reqNumber = reqNumber;
    }

    public String getReqNumber() {
        return reqNumber;
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

    public void setMaterialKind(int materialKind) {
        this.materialKind = materialKind;
    }

    public int getMaterialKind() {
        return materialKind;
    }
}  

