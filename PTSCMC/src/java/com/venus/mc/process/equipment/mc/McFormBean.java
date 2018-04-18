/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.mc;

import com.venus.mc.bean.McBean;

/**
 *
 * @author kngo
 */
public class McFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int mcId;
    private int orgId;
    private String mcNumber;
    private int kind;
    private String carryOnDate;
    private String explanation;
    private String descCarryOn;
    private String descNotCarryOn;
    private int status;
    private String carryOnHour;
    private String carryOnMinute;
    private int result;
    private int mcoId;
    private String[] equipment;
    private String[] unit;
    private String[] quantity;
    private String[] spec;
    private String[] mcoDetailId;

    //constructure region
    public McFormBean() {
        super();
    }

    public McFormBean(McBean bean) {
        this.mcId = bean.getMcId();
        this.orgId = bean.getOrgId();
        this.mcNumber = bean.getMcNumber();
        this.kind = bean.getKind();
        this.carryOnDate = bean.getCarryOnDate();
        this.explanation = bean.getExplanation();
        this.descCarryOn = bean.getDescCarryOn();
        this.descNotCarryOn = bean.getDescNotCarryOn();
        this.status = bean.getStatus();
        this.carryOnHour = bean.getCarryOnHour();
        this.carryOnMinute = bean.getCarryOnMinute();
        this.result = bean.getResult();
    }

    //properties region
    public int getMcId() {
        return this.mcId;
    }

    public void setMcId(int mcId) {
        this.mcId = mcId;
    }

    public int getOrgId() {
        return this.orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
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

    public int getMcoId() {
        return this.mcoId;
    }

    public void setMcoId(int mcoId) {
        this.mcoId = mcoId;
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

    public String[] getMcoDetailId() {
        return mcoDetailId;
    }

    public void setMcoDetailId(String[] mcoDetailId) {
        this.mcoDetailId = mcoDetailId;
    }
}
