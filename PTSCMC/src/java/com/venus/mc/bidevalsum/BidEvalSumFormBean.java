/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bidevalsum;

/**
 *
 * @author mai vinh loc
 */
public class BidEvalSumFormBean extends org.apache.struts.action.ActionForm {
    //fields region

    private int besId; // primary key
    private int empId; // foreign key : reference to com_clarification(cc_id)
    private int tenId; // foreign key : reference to tender_plan(ten_id)
    private int[] besvId;
    private String createdDate;
    private String[] besgId;
    private String[] regBesgId;
    private String[] employee;
    private String[] position;
    private String[] evalId;
    private String[] evalEmployee;
    private String[] evalPosition;
    private String[] evalNote;
    private String[] confirm;
    private String[] detId;
    private String[] detIdTp;
    private String tenNumber;
    private String besNumber;

    //constructure region
    public BidEvalSumFormBean() {
        super();

    }

    public void setDetId(String[] detId) {
        this.detId = detId;
    }

    public String[] getDetId() {
        return detId;
    }

    public String[] getConfirm() {
        return confirm;
    }

    public void setConfirm(String[] confirm) {
        this.confirm = confirm;
    }

    public int[] getBesvId() {
        return besvId;
    }

    public void setBesvId(int[] besvId) {
        this.besvId = besvId;
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

    public String[] getPosition() {
        return position;
    }

    public String[] getEmployee() {
        return employee;
    }

    public void setEmployee(String[] employee) {
        this.employee = employee;
    }

    public void setPosition(String[] position) {
        this.position = position;
    }

    public void setTenNumber(String tenNumber) {
        this.tenNumber = tenNumber;
    }

    public String getTenNumber() {
        return tenNumber;
    }

    public void setEvalPosition(String[] evalPosition) {
        this.evalPosition = evalPosition;
    }

    public void setEvalNote(String[] evalNote) {
        this.evalNote = evalNote;
    }

    public void setEvalId(String[] evalId) {
        this.evalId = evalId;
    }

    public void setEvalEmployee(String[] evalEmployee) {
        this.evalEmployee = evalEmployee;
    }

    public String[] getEvalPosition() {
        return evalPosition;
    }

    public String[] getEvalNote() {
        return evalNote;
    }

    public String[] getEvalId() {
        return evalId;
    }

    public String[] getEvalEmployee() {
        return evalEmployee;
    }

    public void setRegBesgId(String[] regBesgId) {
        this.regBesgId = regBesgId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setBesgId(String[] besgId) {
        this.besgId = besgId;
    }

    public void setBesNumber(String besNumber) {
        this.besNumber = besNumber;
    }

    public void setBesId(int besId) {
        this.besId = besId;
    }

    public String[] getRegBesgId() {
        return regBesgId;
    }

    public int getEmpId() {
        return empId;
    }

    public String[] getBesgId() {
        return besgId;
    }

    public String getBesNumber() {
        return besNumber;
    }

    public int getBesId() {
        return besId;
    }

    public String[] getDetIdTp() {
        return detIdTp;
    }

    public void setDetIdTp(String[] detIdTp) {
        this.detIdTp = detIdTp;
    }
}
