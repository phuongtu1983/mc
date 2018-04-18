/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class EmcBean {

    //fields region
    private int emcId; // primary key
    private int createdEmp; // foreign key : reference to employee(emp_id)
    private String emcNumber;
    private String department;
    private String explanation;
    private String descCarryOn;
    private int status;
    private String descNotCarryOn;
    private String carryOnHour;
    private String carryOnMinute;
    private String carryOnDate;
    private int result;
    private String carryOutDatePlan;

    //constructure region
    public EmcBean() {
    }

    public EmcBean(int emcId) {
        this.emcId = emcId;
    }

    public EmcBean(int emcId, String emcNumber, String department, String explanation, String descCarryOn, int status, String descNotCarryOn, String carryOnHour, String carryOnMinute, String carryOnDate, int result, String carryOutDatePlan) {
        this.emcId = emcId;
        this.emcNumber = emcNumber;
        this.department = department;
        this.explanation = explanation;
        this.descCarryOn = descCarryOn;
        this.status = status;
        this.descNotCarryOn = descNotCarryOn;
        this.carryOnHour = carryOnHour;
        this.carryOnMinute = carryOnMinute;
        this.carryOnDate = carryOnDate;
        this.result = result;
        this.carryOutDatePlan = carryOutDatePlan;
    }

    public int getEmcId() {
        return this.emcId;
    }

    public void setEmcId(int emcId) {
        this.emcId = emcId;
    }

    public int getCreatedEmp() {
        return this.createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public String getEmcNumber() {
        return this.emcNumber;
    }

    public void setEmcNumber(String emcNumber) {
        this.emcNumber = emcNumber;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getExplanation() {
        return this.explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getDescCarryOn() {
        return this.descCarryOn;
    }

    public void setDescCarryOn(String descCarryOn) {
        this.descCarryOn = descCarryOn;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescNotCarryOn() {
        return this.descNotCarryOn;
    }

    public void setDescNotCarryOn(String descNotCarryOn) {
        this.descNotCarryOn = descNotCarryOn;
    }

    public String getCarryOnHour() {
        return this.carryOnHour;
    }

    public void setCarryOnHour(String carryOnHour) {
        this.carryOnHour = carryOnHour;
    }

    public String getCarryOnMinute() {
        return this.carryOnMinute;
    }

    public void setCarryOnMinute(String carryOnMinute) {
        this.carryOnMinute = carryOnMinute;
    }

    public String getCarryOnDate() {
        return this.carryOnDate;
    }

    public void setCarryOnDate(String carryOnDate) {
        this.carryOnDate = carryOnDate;
    }

    public int getResult() {
        return this.result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getCarryOutDatePlan() {
        return this.carryOutDatePlan;
    }

    public void setCarryOutDatePlan(String carryOutDatePlan) {
        this.carryOutDatePlan = carryOutDatePlan;
    }
}
