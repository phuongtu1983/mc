/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.emco;

import com.venus.mc.bean.EmcoBean;

/**
 *
 * @author kngo
 */
public class EmcoFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int emcoId;
    private String department;
    private String emcoNumber;
    private int kind;
    private String carryOutDate;
    private String explanation;
    private String descCarryOut;
    private String descNotCarryOut;
    private int status;
    private String carryOutHour;
    private String carryOutMinute;
    private int result;
    private int emcId;
    private String[] equipment;
    private String[] unit;
    private String[] quantity;
    private String[] spec;
    private String[] emcDetailId;

    //constructure region
    public EmcoFormBean() {
        super();
    }

    public EmcoFormBean(EmcoBean bean) {
        this.emcoId = bean.getEmcoId();
        this.department = bean.getDepartment();
        this.emcoNumber = bean.getEmcoNumber();
        this.carryOutDate = bean.getCarryOutDate();
        this.explanation = bean.getExplanation();
        this.descCarryOut = bean.getDescCarryOut();
        this.descNotCarryOut = bean.getDescNotCarryOut();
        this.status = bean.getStatus();
        this.carryOutHour = bean.getCarryOutHour();
        this.carryOutMinute = bean.getCarryOutMinute();
        this.result = bean.getResult();
    }

    //properties region
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

    public String getEmcoNumber() {
        return this.emcoNumber;
    }

    public void setEmcoNumber(String emcoNumber) {
        this.emcoNumber = emcoNumber;
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

    public int getEmcId() {
        return this.emcId;
    }

    public void setEmcId(int emcId) {
        this.emcId = emcId;
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

    public String[] getEmcDetailId() {
        return emcDetailId;
    }

    public void setEmcDetailId(String[] emcDetailId) {
        this.emcDetailId = emcDetailId;
    }
}
