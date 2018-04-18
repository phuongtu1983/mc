/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.mco;

import com.venus.mc.bean.McoBean;

/**
 *
 * @author kngo
 */
public class McoFormBean extends org.apache.struts.action.ActionForm {

    private int mcoId; // primary key
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
    private String[] equipment;
    private String[] unit;
    private String[] quantity;
    private String[] spec;

    public McoFormBean() {
        super();
    }

    public McoFormBean(McoBean bean) {
        this.mcoId = bean.getMcoId();
        this.orgId = bean.getOrgId();
        this.mcoNumber = bean.getMcoNumber();
        this.kind = bean.getKind();
        this.carryOnDatePlan = bean.getCarryOnDatePlan();
        this.explanation = bean.getExplanation();
        this.descCarryOut = bean.getDescCarryOut();
        this.descNotCarryOut = bean.getDescNotCarryOut();
        this.status = bean.getStatus();
        this.carryOutDate = bean.getCarryOutDate();
        this.carryOutHour = bean.getCarryOutHour();
        this.carryOutMinute = bean.getCarryOutMinute();
        this.result = bean.getResult();
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

    public String[] getEquipment() {
        return equipment;
    }

    public void setEquipment(String[] equipment) {
        this.equipment = equipment;
    }

    public String[] getUnit() {
        return unit;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    public String[] getSpec() {
        return spec;
    }

    public void setSpec(String[] spec) {
        this.spec = spec;
    }
}
