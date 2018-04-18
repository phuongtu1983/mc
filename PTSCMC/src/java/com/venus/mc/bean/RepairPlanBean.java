package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */
public class RepairPlanBean {

    private int rpId;
    private int equId;
    private int assId;
    private String timeTrue;
    private String repairPart;
    private double cost;
    private String repairKind;
    private int status;
    private String comment;
    private int performKind;
    private String performPart;
    private int orgId;
    private int createdEmp;
    private int timeRepair;
    private String timeUnit;

    public RepairPlanBean(int rpId, int equId, int assId, String timeTrue, String repairPart, double cost, String repairKind, int status, String comment, int performKind, String performPart, int orgId, int createdEmp, int timeRepair, String timeUnit) {
        this.rpId = rpId;
        this.equId = equId;
        this.assId = assId;
        this.timeTrue = timeTrue;
        this.repairPart = repairPart;
        this.cost = cost;
        this.repairKind = repairKind;
        this.status = status;
        this.comment = comment;
        this.performKind = performKind;
        this.performPart = performPart;
        this.orgId = orgId;
        this.createdEmp = createdEmp;
        this.timeRepair = timeRepair;
        this.timeUnit = timeUnit;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public int getTimeRepair() {
        return timeRepair;
    }

    public void setTimeRepair(int timeRepair) {
        this.timeRepair = timeRepair;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public int getAssId() {
        return assId;
    }

    public String getComment() {
        return comment;
    }

    public double getCost() {
        return cost;
    }

    public int getEquId() {
        return equId;
    }

    public int getPerformKind() {
        return performKind;
    }

    public String getPerformPart() {
        return performPart;
    }

    public String getRepairKind() {
        return repairKind;
    }

    public String getRepairPart() {
        return repairPart;
    }

    public int getRpId() {
        return rpId;
    }

    public int getStatus() {
        return status;
    }

    public String getTimeTrue() {
        return timeTrue;
    }

    public void setAssId(int assId) {
        this.assId = assId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setEquId(int equId) {
        this.equId = equId;
    }

    public void setPerformKind(int performKind) {
        this.performKind = performKind;
    }

    public void setPerformPart(String performPart) {
        this.performPart = performPart;
    }

    public void setRepairKind(String repairKind) {
        this.repairKind = repairKind;
    }

    public void setRepairPart(String repairPart) {
        this.repairPart = repairPart;
    }

    public void setRpId(int rpId) {
        this.rpId = rpId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTimeTrue(String timeTrue) {
        this.timeTrue = timeTrue;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public RepairPlanBean() {
    }
    //1: Thuong xuyen; 2: Dinh ky; 3: Dot xuat
    public static int RK1 = 1;
    public static int RK2 = 2;
    public static int RK3 = 3;
    //1: Chưa BDSC; 2: Dang BDSC; 3: Đã BDSC
    public static int S1 = 1;
    public static int S2 = 2;
    public static int S3 = 3;
    //1:Tu sua chua; 2: Thue ngoai
    public static int PK1 = 1;
    public static int PK2 = 2;
}
