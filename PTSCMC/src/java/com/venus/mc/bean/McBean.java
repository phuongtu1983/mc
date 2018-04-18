/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class McBean {
    private int mcId; // primary key
    private int createdEmp; // foreign key : reference to employee(emp_id)
    private int orgId;
    private String orgName;
    private String mcNumber;
    private int kind;
    private String kindString;
    private String carryOnDate;
    private String explanation;
    private String descCarryOn;
    private String descNotCarryOn;
    private int status;
    private String carryOnHour;
    private String carryOnMinute;
    private int result;

    //constructure region
    public McBean() {
    }

    public McBean(int mcId) {
        this.mcId = mcId;
    }

    public McBean(int mcId, int orgId, String mcNumber, int kind, String carryOnDate, String explanation, String descCarryOn, String descNotCarryOn, int status, String carryOnHour, String carryOnMinute, int result) {
        this.mcId = mcId;
        this.orgId = orgId;
        this.mcNumber = mcNumber;
        this.kind = kind;
        this.carryOnDate = carryOnDate;
        this.explanation = explanation;
        this.descCarryOn = descCarryOn;
        this.descNotCarryOn = descNotCarryOn;
        this.status = status;
        this.carryOnHour = carryOnHour;
        this.carryOnMinute = carryOnMinute;
        this.result = result;
    }

    public int getMcId() {
        return this.mcId;
    }

    public void setMcId(int mcId) {
        this.mcId = mcId;
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

    public String getMcNumber() {
        return this.mcNumber;
    }

    public void setMcNumber(String mcNumber) {
        this.mcNumber = mcNumber;
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

    public String getCarryOnDate() {
        return this.carryOnDate;
    }

    public void setCarryOnDate(String carryOnDate) {
        this.carryOnDate = carryOnDate;
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
