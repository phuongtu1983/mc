/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author thcao
 */
public class TimeUsingBean {

    private int tuId;
    private String updateDate;
    private int createdEmpId;
    private String createdEmpName;
    private int orgId;
    private String orgName;
    private int no;
    private String usedCode;
    private String unit;
    private int equId;
    private String equipmentName;
    private String appearedDate;
    private int manageOrgId;
    private String manageOrgName;
    private String nextSchedule;
    private long totalTimeUsed;
    private long timeUsed;
    private long timeRemain;
    private long totalTimeRepair;

    public TimeUsingBean() {
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setCreatedEmpId(int createdEmpId) {
        this.createdEmpId = createdEmpId;
    }

    public int getCreatedEmpId() {
        return createdEmpId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setAppearedDate(String appearedDate) {
        this.appearedDate = appearedDate;
    }

    public String getAppearedDate() {
        return appearedDate;
    }

    public void setCreatedEmpName(String createdEmpName) {
        this.createdEmpName = createdEmpName;
    }

    public String getCreatedEmpName() {
        return createdEmpName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setManageOrgId(int manageOrgId) {
        this.manageOrgId = manageOrgId;
    }

    public int getManageOrgId() {
        return manageOrgId;
    }

    public void setNextSchedule(String nextSchedule) {
        this.nextSchedule = nextSchedule;
    }

    public String getNextSchedule() {
        return nextSchedule;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setTimeRemain(long timeRemain) {
        this.timeRemain = timeRemain;
    }

    public long getTimeRemain() {
        return timeRemain;
    }

    public void setTimeUsed(long timeUsed) {
        this.timeUsed = timeUsed;
    }

    public long getTimeUsed() {
        return timeUsed;
    }

    public void setTotalTimeUsed(long totalTimeUsed) {
        this.totalTimeUsed = totalTimeUsed;
    }

    public long getTotalTimeUsed() {
        return totalTimeUsed;
    }

    public void setTuId(int tuId) {
        this.tuId = tuId;
    }

    public int getTuId() {
        return tuId;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUsedCode(String usedCode) {
        this.usedCode = usedCode;
    }

    public String getUsedCode() {
        return usedCode;
    }

    public void setManageOrgName(String manageOrgName) {
        this.manageOrgName = manageOrgName;
    }

    public String getManageOrgName() {
        return manageOrgName;
    }

    public void setEquId(int equId) {
        this.equId = equId;
    }

    public int getEquId() {
        return equId;
    }

    public long getTotalTimeRepair() {
        return totalTimeRepair;
    }

    public void setTotalTimeRepair(long totalTimeRepair) {
        this.totalTimeRepair = totalTimeRepair;
    }

}
