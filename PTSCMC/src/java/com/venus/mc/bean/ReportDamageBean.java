package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */
public class ReportDamageBean {

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
    private String equName;
    private String usedOrg;
    private String managerEmpName;
    private String managerEquipmentEmpName;
    private String usedEmpName;
    private String usedCode;
    private String manageCode;

    public ReportDamageBean(int rdId, int equId, int createdEmp, int managerEmp, int usedEmp, int managerEquipmentEmp, String rdNumber, String createdDate, String createdTime, String createdLocation, int status, String comment, String equName, String usedOrg, String managerEmpName, String managerEquipmentEmpName, String usedEmpName, String usedCode, String manageCode) {
        this.rdId = rdId;
        this.equId = equId;
        this.createdEmp = createdEmp;
        this.managerEmp = managerEmp;
        this.usedEmp = usedEmp;
        this.managerEquipmentEmp = managerEquipmentEmp;
        this.rdNumber = rdNumber;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdLocation = createdLocation;
        this.status = status;
        this.comment = comment;
        this.equName = equName;
        this.usedOrg = usedOrg;
        this.managerEmpName = managerEmpName;
        this.managerEquipmentEmpName = managerEquipmentEmpName;
        this.usedEmpName = usedEmpName;
        this.usedCode = usedCode;
        this.manageCode = manageCode;
    }

    public String getManageCode() {
        return manageCode;
    }

    public String getManagerEmpName() {
        return managerEmpName;
    }

    public String getManagerEquipmentEmpName() {
        return managerEquipmentEmpName;
    }

    public String getUsedCode() {
        return usedCode;
    }

    public String getUsedEmpName() {
        return usedEmpName;
    }

    public void setManageCode(String manageCode) {
        this.manageCode = manageCode;
    }

    public void setManagerEmpName(String managerEmpName) {
        this.managerEmpName = managerEmpName;
    }

    public void setManagerEquipmentEmpName(String managerEquipmentEmpName) {
        this.managerEquipmentEmpName = managerEquipmentEmpName;
    }

    public void setUsedCode(String usedCode) {
        this.usedCode = usedCode;
    }

    public void setUsedEmpName(String usedEmpName) {
        this.usedEmpName = usedEmpName;
    }

    public String getEquName() {
        return equName;
    }

    public String getUsedOrg() {
        return usedOrg;
    }

    public void setEquName(String equName) {
        this.equName = equName;
    }

    public void setUsedOrg(String usedOrg) {
        this.usedOrg = usedOrg;
    }

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

    public ReportDamageBean() {
    }
    static public int STATUS1 = 1;//Mất
    static public int STATUS2 = 2;//Hỏng

    static public int COMPLETED0 = 0;//Chua sua
    static public int COMPLETED1 = 1;//Sua chua roi
}
