/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class EosDBean {

    private int eosdId; // primary key
    private String createdDate;
    private String eosdNumber;
    private int emrirId; // foreign key : reference to mrir(mrir_id)
    private int createdEmpId;
    private String createdEmpName;
    private String nonConform;
    private String description;
    private String correctAct;
    private int actionBy;
    private String actionByName;
    private String dueDate;
    private int closed;
    private String closedDate;
    private String note;

    //constructure region
    public EosDBean() {
    }

    //properties region
    public int getEosdId() {
        return this.eosdId;
    }

    public void setEosdId(int eosdId) {
        this.eosdId = eosdId;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getEosdNumber() {
        return this.eosdNumber;
    }

    public void setEosdNumber(String eosdNumber) {
        this.eosdNumber = eosdNumber;
    }

    public int getEmrirId() {
        return this.emrirId;
    }

    public void setEmrirId(int emrirId) {
        this.emrirId = emrirId;
    }

    public void setCreatedEmpId(int createdEmpId) {
        this.createdEmpId = createdEmpId;
    }

    public int getCreatedEmpId() {
        return createdEmpId;
    }

    public void setCreatedEmpName(String createdEmpName) {
        this.createdEmpName = createdEmpName;
    }

    public String getCreatedEmpName() {
        return createdEmpName;
    }

    public String getNonConform() {
        return this.nonConform;
    }

    public void setNonConform(String nonConform) {
        this.nonConform = nonConform;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCorrectAct() {
        return this.correctAct;
    }

    public void setCorrectAct(String correctAct) {
        this.correctAct = correctAct;
    }

    public int getActionBy() {
        return this.actionBy;
    }

    public void setActionBy(int actionBy) {
        this.actionBy = actionBy;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getClosed() {
        return this.closed;
    }

    public void setClosed(int closed) {
        this.closed = closed;
    }

    public String getClosedDate() {
        return this.closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setActionByName(String actionByName) {
        this.actionByName = actionByName;
    }

    public String getActionByName() {
        return actionByName;
    }
}
