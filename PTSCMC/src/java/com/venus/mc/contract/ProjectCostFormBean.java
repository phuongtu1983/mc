
/// <summary>
/// Author : phuongtu
/// Created Date : 24/09/2009
/// </summary>
package com.venus.mc.contract;

public class ProjectCostFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int pcId;
    private int proId;
    private int conId;
    private double cost;
    private String projectName;

    //constructure region
    public ProjectCostFormBean() {
    }

    //properties region
    public int getPcId() {
        return this.pcId;
    }

    public void setPcId(int pcId) {
        this.pcId = pcId;
    }

    public int getProId() {
        return this.proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getConId() {
        return this.conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
