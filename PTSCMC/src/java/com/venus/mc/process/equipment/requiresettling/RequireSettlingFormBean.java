package com.venus.mc.process.equipment.requiresettling;

import com.venus.mc.bean.RequireSettlingBean;

/**
 * @author Mai Vinh Loc
 */
public class RequireSettlingFormBean extends org.apache.struts.action.ActionForm {

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
    private String[] reqDetId;
    private String[] workPlan;
    private String[] contentWork;
    private String[] location;
    private double[] quantity;
    private String[] startTimePlan;
    private String[] endTimePlan;
    private String[] startTimeReality;
    private String[] endTimeReality;
    private String[] explanation;
    private String[] comment;

    public RequireSettlingFormBean(RequireSettlingBean bean) {
        this.rsId = bean.getRsId();
        this.createdEmp = bean.getCreatedEmp();
        this.createdDate = bean.getCreatedDate();
        this.requireDate = bean.getRequireDate();
        this.rsNumber = bean.getRsNumber();
        this.srId = bean.getSrId();
        this.proId = bean.getProId();
        this.performOrg = bean.getPerformOrg();
        this.requireOrg = bean.getRequireOrg();
        this.status = bean.getStatus();
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

    public String[] getComment() {
        return comment;
    }

    public String[] getContentWork() {
        return contentWork;
    }

    public String[] getEndTimePlan() {
        return endTimePlan;
    }

    public String[] getEndTimeReality() {
        return endTimeReality;
    }

    public String[] getExplanation() {
        return explanation;
    }

    public String[] getLocation() {
        return location;
    }

    public double[] getQuantity() {
        return quantity;
    }

    public String[] getReqDetId() {
        return reqDetId;
    }

    public String[] getStartTimePlan() {
        return startTimePlan;
    }

    public String[] getStartTimeReality() {
        return startTimeReality;
    }

    public String[] getWorkPlan() {
        return workPlan;
    }

    public void setComment(String[] comment) {
        this.comment = comment;
    }

    public void setContentWork(String[] contentWork) {
        this.contentWork = contentWork;
    }

    public void setEndTimePlan(String[] endTimePlan) {
        this.endTimePlan = endTimePlan;
    }

    public void setEndTimeReality(String[] endTimeReality) {
        this.endTimeReality = endTimeReality;
    }

    public void setExplanation(String[] explanation) {
        this.explanation = explanation;
    }

    public void setLocation(String[] location) {
        this.location = location;
    }

    public void setQuantity(double[] quantity) {
        this.quantity = quantity;
    }

    public void setReqDetId(String[] reqDetId) {
        this.reqDetId = reqDetId;
    }

    public void setStartTimePlan(String[] startTimePlan) {
        this.startTimePlan = startTimePlan;
    }

    public void setStartTimeReality(String[] startTimeReality) {
        this.startTimeReality = startTimeReality;
    }

    public void setWorkPlan(String[] workPlan) {
        this.workPlan = workPlan;
    }

    public RequireSettlingFormBean() {
        super();

    }
}
