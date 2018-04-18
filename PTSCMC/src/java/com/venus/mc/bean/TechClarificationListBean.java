/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class TechClarificationListBean {

    //fields region
    private int tclId; // primary key
    private int tcId; // foreign key : reference to tech_clarification(tc_id)
    private String discipline;
    private String category;
    private String note;
    private String clarification;
    private String supplierReply;
    private String subcategory;
    private String reference;
    private int status;
    private int detId; // primary key

    //constructure region
    public TechClarificationListBean() {
    }

    public TechClarificationListBean(int tclId) {
        this.tclId = tclId;
    }

    public TechClarificationListBean(int tclId, String discipline, String category, String note) {
        this.tclId = tclId;
        this.discipline = discipline;
        this.category = category;
        this.note = note;
    }

    //properties region
    public int getTclId() {
        return this.tclId;
    }

    public void setTclId(int tclId) {
        this.tclId = tclId;
    }

    public int getTcId() {
        return this.tcId;
    }

    public void setTcId(int tcId) {
        this.tcId = tcId;
    }

    public String getDiscipline() {
        return this.discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getClarification() {
        return clarification;
    }

    public void setClarification(String clarification) {
        this.clarification = clarification;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public String getSupplierReply() {
        return supplierReply;
    }

    public void setSupplierReply(String supplierReply) {
        this.supplierReply = supplierReply;
    }

    public int getDetId() {
        return detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }
    
}

