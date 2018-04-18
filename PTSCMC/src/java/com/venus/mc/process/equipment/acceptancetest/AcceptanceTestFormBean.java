/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.acceptancetest;

import com.venus.mc.bean.AcceptanceTestBean;

/**
 *
 * @author mai vinh loc
 */
public class AcceptanceTestFormBean extends org.apache.struts.action.ActionForm {
    //fields region

    private int atId;
    private int srId;
    private String createdDate;
    private String testDate;
    private int createdEmp;
    private String atNumber;
    private String createdTime;
    private String createdLocation;
    private int managerEmp;
    private int managerEquipmentEmp;
    private int managerQAEmp;
    private String resultAfterRepair;
    private int result;
    private String comment;
    private String usedOrg;
    private String[] reqDetId;
    private String[] matId;
    private String[] equId;
    private String[] unit;
    private String[] usedCode;
    private String[] quantity;

    //constructure region
    public AcceptanceTestFormBean() {
        super();

    }

    public AcceptanceTestFormBean(AcceptanceTestBean bean) {
        this.atId = bean.getAtId();
        this.srId = bean.getSrId();
        this.createdEmp = bean.getCreatedEmp();
        this.createdDate = bean.getCreatedDate();
        this.testDate = bean.getTestDate();
        this.atNumber = bean.getAtNumber();
        this.comment = bean.getComment();
        this.createdLocation = bean.getCreatedLocation();
        this.createdTime = bean.getCreatedTime();
        this.managerEmp = bean.getManagerEmp();
        this.managerEquipmentEmp = bean.getManagerEquipmentEmp();
        this.managerQAEmp = bean.getManagerQAEmp();
        this.resultAfterRepair = bean.getResultAfterRepair();
        this.result = bean.getResult();
        this.usedOrg = bean.getUsedOrg();
    }

    public void setSrId(int srId) {
        this.srId = srId;
    }

    public int getSrId() {
        return srId;
    }

    public void setEquId(String[] equId) {
        this.equId = equId;
    }

    public String[] getEquId() {
        return equId;
    }

    public String getUsedOrg() {
        return usedOrg;
    }

    public void setUsedOrg(String usedOrg) {
        this.usedOrg = usedOrg;
    }

    public String getComment() {
        return comment;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public String getCreatedLocation() {
        return createdLocation;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public int getManagerEmp() {
        return managerEmp;
    }

    public int getManagerEquipmentEmp() {
        return managerEquipmentEmp;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public void setCreatedLocation(String createdLocation) {
        this.createdLocation = createdLocation;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public void setManagerEmp(int managerEmp) {
        this.managerEmp = managerEmp;
    }

    public void setManagerEquipmentEmp(int managerEquipmentEmp) {
        this.managerEquipmentEmp = managerEquipmentEmp;
    }

    public int getAtId() {
        return atId;
    }

    public void setAtId(int atId) {
        this.atId = atId;
    }

    public String getAtNumber() {
        return atNumber;
    }

    public void setAtNumber(String atNumber) {
        this.atNumber = atNumber;
    }

    public int getManagerQAEmp() {
        return managerQAEmp;
    }

    public void setManagerQAEmp(int managerQAEmp) {
        this.managerQAEmp = managerQAEmp;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getResultAfterRepair() {
        return resultAfterRepair;
    }

    public void setResultAfterRepair(String resultAfterRepair) {
        this.resultAfterRepair = resultAfterRepair;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public String[] getMatId() {
        return matId;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public String[] getReqDetId() {
        return reqDetId;
    }

    public String[] getUnit() {
        return unit;
    }

    public String[] getUsedCode() {
        return usedCode;
    }

    public void setMatId(String[] matId) {
        this.matId = matId;
    }

    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    public void setReqDetId(String[] reqDetId) {
        this.reqDetId = reqDetId;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    public void setUsedCode(String[] usedCode) {
        this.usedCode = usedCode;
    }
}
