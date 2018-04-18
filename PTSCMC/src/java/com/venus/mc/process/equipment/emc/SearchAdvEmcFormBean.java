/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.emc;

/**
 *
 * @author kngo
 */
public class SearchAdvEmcFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int emcId; // primary key
    private String emcNumber;
    private String department;
    private String carryOnDate;
    private String explanation;
    private String descCarryOn;
    private String descNotCarryOn;
    private int status;
    private String carryOnHour;
    private String carryOnMinute;
    private int result;

    //constructure region
    public SearchAdvEmcFormBean() {
        super();
    }

    //properties region
    public int getEmcId() {
        return this.emcId;
    }

    public void setEmcId(int emcId) {
        this.emcId = emcId;
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
