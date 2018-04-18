/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class EmcoBean {

    private int emcoId; // primary key
    private int createdEmp; // foreign key : reference to employee(emp_id)
    private String department;
    private String emcoNumber;
    private String carryOutDate;
    private String explanation;
    private String descCarryOut;
    private String descNotCarryOut;
    private int status;
    private String carryOutHour;
    private String carryOutMinute;
    private int result;

    //constructure region
    public EmcoBean() {
    }

    public EmcoBean(int emcoId) {
        this.emcoId = emcoId;
    }

    public EmcoBean(int emcoId, String emcoNumber, String carryOutDate, String explanation, String descCarryOut, String descNotCarryOut, int status, String carryOutHour, String carryOutMinute, int result) {
        this.emcoId = emcoId;
        this.emcoNumber = emcoNumber;
        this.carryOutDate = carryOutDate;
        this.explanation = explanation;
        this.descCarryOut = descCarryOut;
        this.descNotCarryOut = descNotCarryOut;
        this.status = status;
        this.carryOutHour = carryOutHour;
        this.carryOutMinute = carryOutMinute;
        this.result = result;
    }

    public int getEmcoId() {
        return this.emcoId;
    }

    public void setEmcoId(int emcoId) {
        this.emcoId = emcoId;
    }

    public int getCreatedEmp() {
        return this.createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmcoNumber() {
        return this.emcoNumber;
    }

    public void setEmcoNumber(String emcoNumber) {
        this.emcoNumber = emcoNumber;
    }

    public String getCarryOutDate() {
        return this.carryOutDate;
    }

    public void setCarryOutDate(String carryOutDate) {
        this.carryOutDate = carryOutDate;
    }

    public String getExplanation() {
        return this.explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getDescCarryOut() {
        return this.descCarryOut;
    }

    public void setDescCarryOut(String descCarryOut) {
        this.descCarryOut = descCarryOut;
    }

    public String getDescNotCarryOut() {
        return this.descNotCarryOut;
    }

    public void setDescNotCarryOut(String descNotCarryOut) {
        this.descNotCarryOut = descNotCarryOut;
    }

    public String getCarryOutHour() {
        return this.carryOutHour;
    }

    public void setCarryOutHour(String carryOutHour) {
        this.carryOutHour = carryOutHour;
    }

    public String getCarryOutMinute() {
        return this.carryOutMinute;
    }

    public void setCarryOutMinute(String carryOutMinute) {
        this.carryOutMinute = carryOutMinute;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getResult() {
        return this.result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
