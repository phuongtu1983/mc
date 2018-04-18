/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class EmcReportBean {

    private int no;
    private int emcId; // primary key
    private int emcoId; // primary key
    private String department;
    private String emcNumber;
    private String emcoNumber;
    private String carryOutDatePlan;
    private String carryOutDate;
    private String descCarryOut;
    private String descNotCarryOut;
    private String carryOnDate;
    private String descCarryOn;
    private String descNotCarryOn;

    public int getNo() {
        return this.no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getEmcId() {
        return this.emcId;
    }

    public void setEmcId(int emcId) {
        this.emcId = emcId;
    }

    public int getEmcoId() {
        return this.emcoId;
    }

    public void setEmcoId(int emcoId) {
        this.emcoId = emcoId;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmcNumber() {
        return this.emcNumber;
    }

    public void setEmcNumber(String emcNumber) {
        this.emcNumber = emcNumber;
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

    public String getCarryOutDatePlan() {
        return this.carryOutDatePlan;
    }

    public void setCarryOutDatePlan(String carryOutDatePlan) {
        this.carryOutDatePlan = carryOutDatePlan;
    }

    public String getDescCarryOn() {
        return this.descCarryOn;
    }

    public void setDescCarryOn(String descCarryOn) {
        this.descCarryOn = descCarryOn;
    }

    public String getDescNotCarryOn() {
        return this.descNotCarryOn;
    }

    public void setDescNotCarryOn(String descNotCarryOn) {
        this.descNotCarryOn = descNotCarryOn;
    }

    public String getCarryOnDate() {
        return this.carryOnDate;
    }

    public void setCarryOnDate(String carryOnDate) {
        this.carryOnDate = carryOnDate;
    }
}
