/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class BidEvalSumBean {

    //fields region
    private int besId; // primary key
    private int tenId; // foreign key : reference to tender_plan(ten_id)
    private int empId; // foreign key : reference to employee(emp_id)
    private String besNumber;
    private String subject;
    private String createdDate;
    private String tenNumber;
    private String borNumber;
    private int createdEmp;
    private String tenderPlanDate;
    private String tenderLetterDate;
    //constructure region

    public BidEvalSumBean() {
    }

    public BidEvalSumBean(int besId) {
        this.besId = besId;
    }

    public BidEvalSumBean(int besId, int tenId, int empId, String besNumber, String subject, String createdDate, String tenNumber, String borNumber, int createdEmp) {
        this.besId = besId;
        this.tenId = tenId;
        this.empId = empId;
        this.besNumber = besNumber;
        this.subject = subject;
        this.createdDate = createdDate;
        this.tenNumber = tenNumber;
        this.borNumber = borNumber;
        this.createdEmp = createdEmp;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    //properties region
    public int getBesId() {
        return this.besId;
    }

    public void setBesId(int besId) {
        this.besId = besId;
    }

    public int getTenId() {
        return this.tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public int getEmpId() {
        return this.empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getBesNumber() {
        return this.besNumber;
    }

    public void setBesNumber(String besNumber) {
        this.besNumber = besNumber;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setBorNumber(String borNumber) {
        this.borNumber = borNumber;
    }

    public String getBorNumber() {
        return borNumber;
    }

    public void setTenNumber(String tenNumber) {
        this.tenNumber = tenNumber;
    }

    public String getTenNumber() {
        return tenNumber;
    }

    public String getTenderLetterDate() {
        return tenderLetterDate;
    }

    public String getTenderPlanDate() {
        return tenderPlanDate;
    }

    public void setTenderLetterDate(String tenderLetterDate) {
        this.tenderLetterDate = tenderLetterDate;
    }

    public void setTenderPlanDate(String tenderPlanDate) {
        this.tenderPlanDate = tenderPlanDate;
    }
}
