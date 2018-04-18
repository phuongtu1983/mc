package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */
public class RequireSettlingBean {

    private int rsId;
    private String createdDate;
    private String requireDate;
    private int createdEmp;
    private String rsNumber;
    private int srId;
    private int proId;
    private int requireOrg;
    private int performOrg;
    private int status;
    private String usedOrg;

    public RequireSettlingBean(int rsId, String createdDate, String requireDate, int createdEmp, String rsNumber, int srId, int proId, int requireOrg, int performOrg, int status, String usedOrg) {
        this.rsId = rsId;
        this.createdDate = createdDate;
        this.requireDate = requireDate;
        this.createdEmp = createdEmp;
        this.rsNumber = rsNumber;
        this.srId = srId;
        this.proId = proId;
        this.requireOrg = requireOrg;
        this.performOrg = performOrg;
        this.status = status;
        this.usedOrg = usedOrg;
    }

    public String getUsedOrg() {
        return usedOrg;
    }

    public void setUsedOrg(String usedOrg) {
        this.usedOrg = usedOrg;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public int getPerformOrg() {
        return performOrg;
    }

    public int getRequireOrg() {
        return requireOrg;
    }

    public void setPerformOrg(int performOrg) {
        this.performOrg = performOrg;
    }

    public void setRequireOrg(int requireOrg) {
        this.requireOrg = requireOrg;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getRequireDate() {
        return requireDate;
    }

    public void setRequireDate(String requireDate) {
        this.requireDate = requireDate;
    }

    public int getSrId() {
        return srId;
    }

    public void setSrId(int srId) {
        this.srId = srId;
    }

    public int getRsId() {
        return rsId;
    }

    public void setRsId(int rsId) {
        this.rsId = rsId;
    }

    public String getRsNumber() {
        return rsNumber;
    }

    public void setRsNumber(String rsNumber) {
        this.rsNumber = rsNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public RequireSettlingBean() {
    }

    static public int STATUS1 = 1;//Binh thuong
    static public int STATUS2 = 2;//Khan
    static public int STATUS3 = 3;//Thuong khan
}
