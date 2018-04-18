/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.comclarification;

/**
 *
 * @author mai vinh loc
 */
public class ComClarificationFormBean extends org.apache.struts.action.ActionForm {
 
    //fields region
    private int ccId; // primary key
    private int tenId; // foreign key : reference to tender_plan(ten_id)
    private int venId;
    private String ccNumber;
    private String subfix;
    private String createdDate;
    private String reference;
    private String clarification;
    private String supplierReply;
    private int status;

    //constructure region
    public ComClarificationFormBean() {
        super();
    }

    //properties region
    public int getCcId() {
        return this.ccId;
    }

    public void setCcId(int ccId) {
        this.ccId = ccId;
    }

    public int getTenId() {
        return this.tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public int getVenId() {
        return venId;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getSubfix() {
        return this.subfix;
    }

    public void setSubfix(String subfix) {
        this.subfix = subfix;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getClarification() {
        return clarification;
    }

    public String getReference() {
        return reference;
    }

    public int getStatus() {
        return status;
    }

    public String getSupplierReply() {
        return supplierReply;
    }

    public void setClarification(String clarification) {
        this.clarification = clarification;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setSupplierReply(String supplierReply) {
        this.supplierReply = supplierReply;
    }
    
}
