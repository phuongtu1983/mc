/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class McReportBean {

    private int no;
    private int mcId; // primary key
    private int mcoId; // primary key
    private int orgId;
    private String orgName;
    private String mcNumber;
    private String mcoNumber;
    private String carryOnDatePlan;
    private String carryOnDate;
    private String descCarryOn;
    private String descNotCarryOn;
    private String carryOutDate;
    private String descCarryOut;
    private String descNotCarryOut;

    public int getNo() {
        return this.no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getMcId() {
        return this.mcId;
    }

    public void setMcId(int mcId) {
        this.mcId = mcId;
    }

    public int getMcoId() {
        return this.mcoId;
    }

    public void setMcoId(int mcoId) {
        this.mcoId = mcoId;
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

    public String getMcNumber() {
        return this.mcNumber;
    }

    public void setMcNumber(String mcNumber) {
        this.mcNumber = mcNumber;
    }

    public String getMcoNumber() {
        return this.mcoNumber;
    }

    public void setMcoNumber(String mcoNumber) {
        this.mcoNumber = mcoNumber;
    }

    public String getCarryOnDate() {
        return this.carryOnDate;
    }

    public void setCarryOnDate(String carryOnDate) {
        this.carryOnDate = carryOnDate;
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

    public String getCarryOnDatePlan() {
        return this.carryOnDatePlan;
    }

    public void setCarryOnDatePlan(String carryOnDatePlan) {
        this.carryOnDatePlan = carryOnDatePlan;
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
}
