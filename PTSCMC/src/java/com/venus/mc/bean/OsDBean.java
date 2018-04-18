/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 * @author kngo
 *  Author : kngo
 *  Created Date : 22/09/2009
 */
public class OsDBean {

    private int osdId; // primary key
    private String createdDate;
    private String osdNumber;
    private int mrirId; // foreign key : reference to mrir(mrir_id)
    private int createdEmpId;
    private String createdEmpName;
    private int dnId;
    private String dnNumber;
    private String nonConform;
    private String description;
    private String correctAct;
    private int actionBy;
    private String dueDate;
    private int closed;
    private String stringClosed;
    private String closedDate;
    private String note;
    private int stt;
    private String contractNumber;
    private String requestNumber;
    private String mrirNumber;

    //constructure region
    public OsDBean() {
    }

    public OsDBean(int osdId) {
        this.osdId = osdId;
    }

    public OsDBean(int osdId, String createdDate, String osdNumber, int mrirId, int createdEmp, String nonConform, String description, String correctAct, int actionBy, String dueDate, int closed, String closedDate, String note, int reqDetailId) {
        this.osdId = osdId;
        this.createdDate = createdDate;
        this.osdNumber = osdNumber;
        this.mrirId = mrirId;
        this.createdEmpId = createdEmp;
        this.nonConform = nonConform;
        this.description = description;
        this.correctAct = correctAct;
        this.actionBy = actionBy;
        this.dueDate = dueDate;
        this.closed = closed;
        this.closedDate = closedDate;
        this.note = note;
    }

    //properties region
    public int getOsdId() {
        return this.osdId;
    }

    public void setOsdId(int osdId) {
        this.osdId = osdId;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getOsdNumber() {
        return this.osdNumber;
    }

    public void setOsdNumber(String osdNumber) {
        this.osdNumber = osdNumber;
    }

    public int getMrirId() {
        return this.mrirId;
    }

    public void setMrirId(int mrirId) {
        this.mrirId = mrirId;
    }

    public int getCreatedEmpId() {
        return this.createdEmpId;
    }

    public void setCreatedEmpId(int createdEmpId) {
        this.createdEmpId = createdEmpId;
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

    public void setCreatedEmpName(String createdEmpName) {
        this.createdEmpName = createdEmpName;
    }

    public String getCreatedEmpName() {
        return createdEmpName;
    }

    public void setDnId(int dnId) {
        this.dnId = dnId;
    }

    public int getDnId() {
        return dnId;
    }

    public void setDnNumber(String dnNumber) {
        this.dnNumber = dnNumber;
    }

    public String getDnNumber() {
        return dnNumber;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public void setMrirNumber(String mrirNumber) {
        this.mrirNumber = mrirNumber;
    }

    public String getMrirNumber() {
        return mrirNumber;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getStringClosed() {
        return stringClosed;
    }

    public void setStringClosed(String stringClosed) {
        this.stringClosed = stringClosed;
    }

    
}
