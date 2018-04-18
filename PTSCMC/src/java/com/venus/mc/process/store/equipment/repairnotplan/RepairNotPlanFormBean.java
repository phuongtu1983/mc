package com.venus.mc.process.store.equipment.repairnotplan;

/**
 * @author Mai Vinh Loc
 */
public class RepairNotPlanFormBean extends org.apache.struts.action.ActionForm {

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

    public RepairNotPlanFormBean() {
        super();

    }
}
