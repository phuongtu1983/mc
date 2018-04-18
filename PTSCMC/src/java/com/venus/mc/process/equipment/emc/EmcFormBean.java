/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.emc;

import com.venus.mc.bean.EmcBean;

/**
 *
 * @author kngo
 */
public class EmcFormBean extends org.apache.struts.action.ActionForm {

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
    private String equipName;
    private String[] equipment;
    private String[] unit;
    private String[] quantity;
    private String[] spec;


    //constructure region
    public EmcFormBean() {
        super();
    }

    public EmcFormBean(EmcBean bean) {
        this.emcId = bean.getEmcId();
        this.department = bean.getDepartment();
        this.emcNumber = bean.getEmcNumber();
        this.result = bean.getResult();
        this.status = bean.getStatus();
        this.carryOnDate = bean.getCarryOnDate();
        this.carryOnHour = bean.getCarryOnHour();
        this.carryOnMinute = bean.getCarryOnMinute();
        this.descCarryOn = bean.getDescCarryOn();
        this.descNotCarryOn = bean.getDescNotCarryOn();
        this.carryOutDatePlan = bean.getCarryOutDatePlan();
        this.explanation = bean.getExplanation();
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

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
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
