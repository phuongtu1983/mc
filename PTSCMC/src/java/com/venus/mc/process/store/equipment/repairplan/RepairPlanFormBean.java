package com.venus.mc.process.store.equipment.repairplan;

/**
 * @author Mai Vinh Loc
 */
public class RepairPlanFormBean extends org.apache.struts.action.ActionForm {

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
    private String[] reqDetId;
    private String[] matId;
    private String[] unit;
    private String[] quantity;

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    public void setReqDetId(String[] reqDetId) {
        this.reqDetId = reqDetId;
    }

    public void setMatId(String[] matId) {
        this.matId = matId;
    }

    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    public String[] getUnit() {
        return unit;
    }

    public String[] getReqDetId() {
        return reqDetId;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public String[] getMatId() {
        return matId;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeRepair(int timeRepair) {
        this.timeRepair = timeRepair;
    }

    public int getTimeRepair() {
        return timeRepair;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public int getCreatedEmp() {
        return createdEmp;
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

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getOrgId() {
        return orgId;
    }

    public RepairPlanFormBean() {
        super();

    }
}
