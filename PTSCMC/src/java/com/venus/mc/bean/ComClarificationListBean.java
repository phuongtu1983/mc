/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class ComClarificationListBean {

    //fields region
    private int cclId; // primary key
    private int ccId; // foreign key : reference to com_clarification(cc_id)
    private String reference;
    private String clarification;
    private String supplierReply;
    private int status;

    //constructure region
    public ComClarificationListBean() {
    }

    public ComClarificationListBean(int cclId) {
        this.cclId = cclId;
    }

    public ComClarificationListBean(int cclId, String reference, String clarification, String supplierReply, int status) {
        this.cclId = cclId;
        this.reference = reference;
        this.clarification = clarification;
        this.supplierReply = supplierReply;
        this.status = status;
    }

    //properties region
    public int getCclId() {
        return this.cclId;
    }

    public void setCclId(int cclId) {
        this.cclId = cclId;
    }

    public int getCcId() {
        return this.ccId;
    }

    public void setCcId(int ccId) {
        this.ccId = ccId;
    }

    public String getReference() {
        return this.reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getClarification() {
        return this.clarification;
    }

    public void setClarification(String clarification) {
        this.clarification = clarification;
    }

    public String getSupplierReply() {
        return this.supplierReply;
    }

    public void setSupplierReply(String supplierReply) {
        this.supplierReply = supplierReply;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
