/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class TenderLetterBean {
    //fields region

    private int letId; // primary key
    private int tenId; // foreign key : reference to tender_plan(ten_id)
    private String createdDate;
    private int recievedEmp1;
    private int recievedEmp2;
    private String[] regDetId;
    private String[] subfix;
    private String[] venId;
    private String[] vendorBidded;
    private int detId;
    private String tenNumber;
    private int createdEmp;

    //constructure region
    public TenderLetterBean() {
    }

    public TenderLetterBean(int letId, int tenId, String createdDate, int recievedEmp1, int recievedEmp2, String[] regDetId, String[] subfix, String[] venId, String[] vendorBidded, int detId, String tenNumber, int createdEmp) {
        this.letId = letId;
        this.tenId = tenId;
        this.createdDate = createdDate;
        this.recievedEmp1 = recievedEmp1;
        this.recievedEmp2 = recievedEmp2;
        this.regDetId = regDetId;
        this.subfix = subfix;
        this.venId = venId;
        this.vendorBidded = vendorBidded;
        this.detId = detId;
        this.tenNumber = tenNumber;
        this.createdEmp = createdEmp;
    }

    public String[] getVendorBidded() {
        return vendorBidded;
    }

    public void setVendorBidded(String[] vendorBidded) {
        this.vendorBidded = vendorBidded;
    }

    public TenderLetterBean(int letId) {
        this.letId = letId;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    //properties region
    public int getLetId() {
        return this.letId;
    }

    public void setLetId(int letId) {
        this.letId = letId;
    }

    public int getTenId() {
        return this.tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getRecievedEmp1() {
        return this.recievedEmp1;
    }

    public void setRecievedEmp1(int recievedEmp1) {
        this.recievedEmp1 = recievedEmp1;
    }

    public int getRecievedEmp2() {
        return this.recievedEmp2;
    }

    public void setRecievedEmp2(int recievedEmp2) {
        this.recievedEmp2 = recievedEmp2;
    }

    public String[] getRegDetId() {
        return regDetId;
    }

    public void setRegDetId(String[] venId) {
        this.regDetId = venId;
    }

    public void setSubfix(String[] subfix) {
        this.subfix = subfix;
    }

    public String[] getSubfix() {
        return subfix;
    }

    public void setVenId(String[] venId) {
        this.venId = venId;
    }

    public String[] getVenId() {
        return venId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getDetId() {
        return detId;
    }

    public void setTenNumber(String tenNumber) {
        this.tenNumber = tenNumber;
    }

    public String getTenNumber() {
        return tenNumber;
    }
}
