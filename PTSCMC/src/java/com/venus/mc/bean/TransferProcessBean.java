package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */
public class TransferProcessBean {

    private int tpId;
    private int equId;
    private int assId;
    private int receiveOrg;
    private int receiveEmp;
    private int project;
    private String receiveDate;
    private String returnDate;
    private String comment;
    private String orgName;
    private int createdEmp;

    public TransferProcessBean(int tpId, int equId, int assId, int receiveOrg, int receiveEmp, int project, String receiveDate, String returnDate, String comment, String orgName, int createdEmp) {
        this.tpId = tpId;
        this.equId = equId;
        this.assId = assId;
        this.receiveOrg = receiveOrg;
        this.receiveEmp = receiveEmp;
        this.project = project;
        this.receiveDate = receiveDate;
        this.returnDate = returnDate;
        this.comment = comment;
        this.orgName = orgName;
        this.createdEmp = createdEmp;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgName() {
        return orgName;
    }

    public int getAssId() {
        return assId;
    }

    public String getComment() {
        return comment;
    }

    public int getEquId() {
        return equId;
    }

    public int getProject() {
        return project;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public int getReceiveEmp() {
        return receiveEmp;
    }

    public int getReceiveOrg() {
        return receiveOrg;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public int getTpId() {
        return tpId;
    }

    public void setAssId(int assId) {
        this.assId = assId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setEquId(int equId) {
        this.equId = equId;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public void setReceiveEmp(int receiveEmp) {
        this.receiveEmp = receiveEmp;
    }

    public void setReceiveOrg(int receiveOrg) {
        this.receiveOrg = receiveOrg;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setTpId(int tpId) {
        this.tpId = tpId;
    }

    public TransferProcessBean() {
    }
}
