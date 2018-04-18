/// <summary>
/// Author : phuongtu
/// Created Date : 06/10/2009
/// </summary>
package com.venus.mc.bean;

public class ReturnedMaterialDiaryBean {

    //fields region
    private int rmsId; // primary key
    private int sto2Id; // foreign key : reference to store_level2(sto2_id)
    private String updateDate;
    private int createdEmp;
    private String rsmNumber;
    private int proId;
    private int orgId;
    private String projectName;
    private String orgName;
    private String employeeName;

    //constructure region
    public ReturnedMaterialDiaryBean() {
    }

    public ReturnedMaterialDiaryBean(int rmsId) {
        this.rmsId = rmsId;
    }

    public ReturnedMaterialDiaryBean(int rmsId, String updateDate) {
        this.rmsId = rmsId;
        this.updateDate = updateDate;
    }

    //properties region
    public int getRmsId() {
        return this.rmsId;
    }

    public void setRmsId(int rmsId) {
        this.rmsId = rmsId;
    }

    public int getSto2Id() {
        return this.sto2Id;
    }

    public void setSto2Id(int sto2Id) {
        this.sto2Id = sto2Id;
    }

    public String getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getRsmNumber() {
        return rsmNumber;
    }

    public void setRsmNumber(String rsmNumber) {
        this.rsmNumber = rsmNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
