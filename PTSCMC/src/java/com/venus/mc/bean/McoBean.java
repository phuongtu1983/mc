/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class McoBean {

    private int mcoId; // primary key
    private int createdEmp; // foreign key : reference to employee(emp_id)
    private String mcoNumber;
    private int orgId;
    private String orgName;
    private int kind;
    private String kindString;
    private String carryOnDatePlan;
    private String explanation;
    private String descCarryOut;
    private String descNotCarryOut;
    private int status;
    private String carryOutDate;
    private String carryOutHour;
    private String carryOutMinute;
    private int result;

    //constructure region
    public McoBean() {
    }

    public McoBean(int mcoId) {
        this.mcoId = mcoId;
    }

    public McoBean(int mcoId, int orgId, String mcoNumber, int kind, String carryOnDatePlan, String explanation, String descCarryOut, String descNotCarryOut, int status, String carryOutDate, String carryOutHour, String carryOutMinute, int result) {
        this.mcoId = mcoId;
        this.orgId = orgId;
        this.mcoNumber = mcoNumber;
        this.kind = kind;
        this.carryOnDatePlan = carryOnDatePlan;
        this.explanation = explanation;
        this.descCarryOut = descCarryOut;
        this.descNotCarryOut = descNotCarryOut;
        this.status = status;
        this.carryOutDate = carryOutDate;
        this.carryOutHour = carryOutHour;
        this.carryOutMinute = carryOutMinute;
        this.result = result;
    }

    public int getMcoId() {
        return this.mcoId;
    }

    public void setMcoId(int mcoId) {
        this.mcoId = mcoId;
    }

    public int getCreatedEmp() {
        return this.createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public int getOrgId() {
        return this.orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getMcoNumber() {
        return this.mcoNumber;
    }

    public void setMcoNumber(String mcoNumber) {
        this.mcoNumber = mcoNumber;
    }

    public int getKind() {
        return this.kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getKindString() {
        return this.kindString;
    }

    public void setKindString(String kindString) {
        this.kindString = kindString;
    }

    public String getCarryOnDatePlan() {
        return this.carryOnDatePlan;
    }

    public void setCarryOnDatePlan(String carryOnDatePlan) {
        this.carryOnDatePlan = carryOnDatePlan;
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

    public String getCarryOutDate() {
        return this.carryOutDate;
    }

    public void setCarryOutDate(String carryOutDate) {
        this.carryOutDate = carryOutDate;
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
