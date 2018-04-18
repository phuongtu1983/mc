package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */
public class AcceptanceTestBean {

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

    public AcceptanceTestBean(int atId, int srId, String createdDate, String testDate, int createdEmp, String atNumber, String createdTime, String createdLocation, int managerEmp, int managerEquipmentEmp, int managerQAEmp, String resultAfterRepair, int result, String comment, String usedOrg) {
        this.atId = atId;
        this.srId = srId;
        this.createdDate = createdDate;
        this.testDate = testDate;
        this.createdEmp = createdEmp;
        this.atNumber = atNumber;
        this.createdTime = createdTime;
        this.createdLocation = createdLocation;
        this.managerEmp = managerEmp;
        this.managerEquipmentEmp = managerEquipmentEmp;
        this.managerQAEmp = managerQAEmp;
        this.resultAfterRepair = resultAfterRepair;
        this.result = result;
        this.comment = comment;
        this.usedOrg = usedOrg;
    }

    public void setSrId(int srId) {
        this.srId = srId;
    }

    public int getSrId() {
        return srId;
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

    public AcceptanceTestBean() {
    }

    static public int RESULT1 = 1;//Đạt yêu cầu
    static public int RESULT2 = 2;//Không đạt yêu cầu
}
