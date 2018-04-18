/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class TechClarificationListDetailBean {

    //fields region
    private int detId; // primary key
    private int tclId; // foreign key : reference to tech_clarification_list(tcl_id)
    private String subcategory;
    private String reference;
    private String clarification;
    private String supplierReply;
    private int status;

    //constructure region
    public TechClarificationListDetailBean() {
    }

    public TechClarificationListDetailBean(int detId) {
        this.detId = detId;
    }

    public TechClarificationListDetailBean(int detId, String subcategory, String reference, String clarification, String supplierReply, int status) {
        this.detId = detId;
        this.subcategory = subcategory;
        this.reference = reference;
        this.clarification = clarification;
        this.supplierReply = supplierReply;
        this.status = status;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getTclId() {
        return this.tclId;
    }

    public void setTclId(int tclId) {
        this.tclId = tclId;
    }

    public String getSubcategory() {
        return this.subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
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
