/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.process.equipment.mco;

/**
 *
 * @author kngo
 */
public class SearchAdvMcoFormBean extends org.apache.struts.action.ActionForm {

    private int mcoId; // primary key
    private String mcoNumber;    
    private int orgId;
    private int kind;
    private String carryOutDate;
    private String explanation;
    private String descCarryOut;
    private String descNotCarryOut;
    private int status;
    private String carryOutHour;
    private String carryOutMinute;
    private int result;

    //constructure region
    public SearchAdvMcoFormBean() {
        super();
    }

    //properties region
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
