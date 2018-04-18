/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.techclarification;

/**
 *
 * @author mai vinh loc
 */
public class TechClarificationDetailFormBean extends org.apache.struts.action.ActionForm {
 
    private int tclId; // primary key
    private int tcId; // foreign key : reference to tech_clarification(tc_id)
    private String discipline;
    private String category;
    private String note;
    private int detId; // primary key
    private String subcategory;
    private String reference;
    private String clarification;
    private String supplierReply;
    private int status;
    private int venId;
    private int createdEmp;

    //constructure region
    public TechClarificationDetailFormBean() {
        super();
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public int getCreatedEmp() {
        return createdEmp;
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

    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
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

    public int getVenId() {
        return venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }
    
}
