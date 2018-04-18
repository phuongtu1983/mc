/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.tenderletter;

/**
 *
 * @author mai vinh loc
 */
public class TenderLetterFormBean extends org.apache.struts.action.ActionForm {

    private int letId; // primary key
    private int tenId; // foreign key : reference to tender_plan(ten_id)
    private String createdDate;
    private int createdEmp;
    private int recievedEmp1;
    private int recievedEmp2;
    private String[] regDetId;
    private String[] subfix;
    private String[] venId;
    private String[] tevId;
    private String[] vendorBidded;
    private int detId;

    //constructure region
    public TenderLetterFormBean() {
        super();
    }

    public String[] getVendorBidded() {
        return vendorBidded;
    }

    public void setVendorBidded(String[] vendorBidded) {
        this.vendorBidded = vendorBidded;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
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

    public void setTevId(String[] tevId) {
        this.tevId = tevId;
    }

    public String[] getTevId() {
        return tevId;
    }
}
