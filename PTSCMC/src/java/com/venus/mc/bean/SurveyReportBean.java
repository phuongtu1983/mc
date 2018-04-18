package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */
public class SurveyReportBean {

    private int srId;
     private int orgId;
    private String createdDate;
    private String surveyDate;
    private int createdEmp;
    private String srNumber;
    private String createdTime;
    private String createdLocation;
    private int managerEmp;
    private int managerEquipmentEmp;
    private String reasonToRepair;
    private int request;
    private String comment;
    private String usedOrg;

    public SurveyReportBean(int srId, String createdDate, String surveyDate, int createdEmp, String srNumber, String createdTime, String createdLocation, int managerEmp, int managerEquipmentEmp, String reasonToRepair, int request, String comment, String usedOrg) {
        this.srId = srId;
        this.createdDate = createdDate;
        this.surveyDate = surveyDate;
        this.createdEmp = createdEmp;
        this.srNumber = srNumber;
        this.createdTime = createdTime;
        this.createdLocation = createdLocation;
        this.managerEmp = managerEmp;
        this.managerEquipmentEmp = managerEquipmentEmp;
        this.reasonToRepair = reasonToRepair;
        this.request = request;
        this.comment = comment;
        this.usedOrg = usedOrg;
        this.orgId = orgId;
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

    public String getReasonToRepair() {
        return reasonToRepair;
    }

    public void setReasonToRepair(String reasonToRepair) {
        this.reasonToRepair = reasonToRepair;
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    public int getSrId() {
        return srId;
    }

    public void setSrId(int srId) {
        this.srId = srId;
    }

    public String getSrNumber() {
        return srNumber;
    }

    public void setSrNumber(String srNumber) {
        this.srNumber = srNumber;
    }

    public String getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(String surveyDate) {
        this.surveyDate = surveyDate;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getOrgId() {
        return orgId;
    }

    public SurveyReportBean() {
    }
    static public int REQUEST1 = 1;//Tự BDSC
    static public int REQUEST2 = 2;//Thuê thuầu phụ BDSC
}
