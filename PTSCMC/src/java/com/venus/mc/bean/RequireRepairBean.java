package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */
public class RequireRepairBean {

    private int rrId;
    private int orgId;
    private int createdEmp;
    private String createdDate;
    private String requireDate;
    private String rrNumber;
    private String comment;
    private String requireOrg;

    public RequireRepairBean(int rrId, int createdEmp, String createdDate, String requireDate, String rrNumber, String comment, String requireOrg) {
        this.rrId = rrId;
        this.createdEmp = createdEmp;
        this.createdDate = createdDate;
        this.requireDate = requireDate;
        this.rrNumber = rrNumber;
        this.comment = comment;
        this.requireOrg = requireOrg;

    }

    public int getRrId() {
        return rrId;
    }

    public void setRrId(int rrId) {
        this.rrId = rrId;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getRrNumber() {
        return rrNumber;
    }

    public void setRrNumber(String rrNumber) {
        this.rrNumber = rrNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRequireDate(String requireDate) {
        this.requireDate = requireDate;
    }

    public String getRequireDate() {
        return requireDate;
    }

    public String getRequireOrg() {
        return requireOrg;
    }

    public void setRequireOrg(String requireOrg) {
        this.requireOrg = requireOrg;
    }

    public RequireRepairBean() {
    }
}
