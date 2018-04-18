/// <summary>
/// Author : phuongtu
/// Created Date : 06/10/2009
/// </summary>
package com.venus.mc.bean;

public class UsedMaterialDiaryBean {

    //fields region
    private int umsId; // primary key
    private int sto2Id; // foreign key : reference to store_level2(sto2_id)
    private String updateDate;
    private int createdEmp;
    private String usmNumber;
    private int proId;
    private int orgId;
    private String projectName;
    private String orgName;
    private String employeeName;
    private String location;
    private int canNotDelete;

    //constructure region
    public UsedMaterialDiaryBean() {
    }

    public UsedMaterialDiaryBean(int umsId) {
        this.umsId = umsId;
    }

    public UsedMaterialDiaryBean(int umsId, String updateDate) {
        this.umsId = umsId;
        this.updateDate = updateDate;
    }

    //properties region
    public int getUmsId() {
        return this.umsId;
    }

    public void setUmsId(int umsId) {
        this.umsId = umsId;
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

    public String getUsmNumber() {
        return usmNumber;
    }

    public void setUsmNumber(String usmNumber) {
        this.usmNumber = usmNumber;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCanNotDelete() {
        return canNotDelete;
    }

    public void setCanNotDelete(int canNotDelete) {
        this.canNotDelete = canNotDelete;
    }
}
