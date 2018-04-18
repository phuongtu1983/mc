package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */
public class RepairNotPlanBean {

    private int rnpId;
    private int equId;
    private int rrId;
    private double cost;
    private int orgUsed;
    private String equName;
    private String orgName;
    private int rdId;
    private String rdNumber;
    private String rrNumber;
    private int srId;
    private String srNumber;
    private int atId;
    private String atNumber;
    private int createdEmp;

    public RepairNotPlanBean(int rnpId, int equId, int rrId, double cost, int orgUsed, String equName, String orgName, int rdId, String rdNumber, String rrNumber, int srId, String srNumber, int atId, String atNumber, int createdEmp) {
        this.rnpId = rnpId;
        this.equId = equId;
        this.rrId = rrId;
        this.cost = cost;
        this.orgUsed = orgUsed;
        this.equName = equName;
        this.orgName = orgName;
        this.rdId = rdId;
        this.rdNumber = rdNumber;
        this.rrNumber = rrNumber;
        this.srId = srId;
        this.srNumber = srNumber;
        this.atId = atId;
        this.atNumber = atNumber;
        this.createdEmp = createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public int getAtId() {
        return atId;
    }

    public String getAtNumber() {
        return atNumber;
    }

    public int getRdId() {
        return rdId;
    }

    public String getRdNumber() {
        return rdNumber;
    }

    public String getRrNumber() {
        return rrNumber;
    }

    public int getSrId() {
        return srId;
    }

    public String getSrNumber() {
        return srNumber;
    }

    public void setAtId(int atId) {
        this.atId = atId;
    }

    public void setAtNumber(String atNumber) {
        this.atNumber = atNumber;
    }

    public void setRdId(int rdId) {
        this.rdId = rdId;
    }

    public void setRdNumber(String rdNumber) {
        this.rdNumber = rdNumber;
    }

    public void setRrNumber(String rrNumber) {
        this.rrNumber = rrNumber;
    }

    public void setSrId(int srId) {
        this.srId = srId;
    }

    public void setSrNumber(String srNumber) {
        this.srNumber = srNumber;
    }

    public String getEquName() {
        return equName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setEquName(String equName) {
        this.equName = equName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getEquId() {
        return equId;
    }

    public double getCost() {
        return cost;
    }

    public int getOrgUsed() {
        return orgUsed;
    }

    public int getRnpId() {
        return rnpId;
    }

    public int getRrId() {
        return rrId;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setEquId(int equId) {
        this.equId = equId;
    }

    public void setOrgUsed(int orgUsed) {
        this.orgUsed = orgUsed;
    }

    public void setRnpId(int rnpId) {
        this.rnpId = rnpId;
    }

    public void setRrId(int rrId) {
        this.rrId = rrId;
    }

    public RepairNotPlanBean() {
    }
}
