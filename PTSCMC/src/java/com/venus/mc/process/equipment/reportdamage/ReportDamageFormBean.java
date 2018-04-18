package com.venus.mc.process.equipment.reportdamage;

/**
 * @author Mai Vinh Loc
 */
public class ReportDamageFormBean extends org.apache.struts.action.ActionForm {

    private int rdId;
    private int equId;
    private int createdEmp;
    private int managerEmp;
    private int usedEmp;
    private int managerEquipmentEmp;
    private String rdNumber;
    private String createdDate;
    private String createdTime;
    private String createdLocation;
    private int status;
    private String comment;

    public int getRdId() {
        return rdId;
    }

    public String getRdNumber() {
        return rdNumber;
    }

    public void setRdId(int rdId) {
        this.rdId = rdId;
    }

    public void setRdNumber(String rdNumber) {
        this.rdNumber = rdNumber;
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

    public int getEquId() {
        return equId;
    }

    public int getManagerEmp() {
        return managerEmp;
    }

    public int getManagerEquipmentEmp() {
        return managerEquipmentEmp;
    }

    public int getStatus() {
        return status;
    }

    public int getUsedEmp() {
        return usedEmp;
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

    public void setEquId(int equId) {
        this.equId = equId;
    }

    public void setManagerEmp(int managerEmp) {
        this.managerEmp = managerEmp;
    }

    public void setManagerEquipmentEmp(int managerEquipmentEmp) {
        this.managerEquipmentEmp = managerEquipmentEmp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setUsedEmp(int usedEmp) {
        this.usedEmp = usedEmp;
    }

    public ReportDamageFormBean() {
        super();

    }
}
