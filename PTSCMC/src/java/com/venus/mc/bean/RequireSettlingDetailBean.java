package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */
public class RequireSettlingDetailBean {

    private int detId;
    private int rsId;
    private String workPlan;
    private String contentWork;
    private String location;
    private double quantity;
    private String startTimePlan;
    private String endTimePlan;
    private String startTimeReality;
    private String endTimeReality;
    private String explanation;
    private String comment;

    public RequireSettlingDetailBean(int detId, int rsId, String workPlan, String contentWork,String location, double quantity,String startTimePlan, String endTimePlan,  String startTimeReality, String endTimeReality, String explanation,  String comment) {
        this.detId = detId;
        this.rsId = rsId;
        this.workPlan = workPlan;
        this.contentWork = contentWork;
        this.location = location;
        this.quantity = quantity;
        this.startTimePlan = startTimePlan;
        this.endTimePlan = endTimePlan;
        this.startTimeReality = startTimeReality;
        this.endTimeReality = endTimeReality;
        this.explanation = explanation;
        this.comment = comment;

    }

    public RequireSettlingDetailBean(int detId) {
        this.detId = detId;
    }

    public int getDetId() {
        return detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

     public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getContentWork() {
        return contentWork;
    }

    public void setContentWork(String contentWork) {
        this.contentWork = contentWork;
    }

    public String getEndTimePlan() {
        return endTimePlan;
    }

    public void setEndTimePlan(String endTimePlan) {
        this.endTimePlan = endTimePlan;
    }

    public String getEndTimeReality() {
        return endTimeReality;
    }

    public void setEndTimeReality(String endTimeReality) {
        this.endTimeReality = endTimeReality;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRsId() {
        return rsId;
    }

    public void setRsId(int rsId) {
        this.rsId = rsId;
    }

    public String getStartTimePlan() {
        return startTimePlan;
    }

    public void setStartTimePlan(String startTimePlan) {
        this.startTimePlan = startTimePlan;
    }

    public String getStartTimeReality() {
        return startTimeReality;
    }

    public void setStartTimeReality(String startTimeReality) {
        this.startTimeReality = startTimeReality;
    }

    public String getWorkPlan() {
        return workPlan;
    }

    public void setWorkPlan(String workPlan) {
        this.workPlan = workPlan;
    }


    public RequireSettlingDetailBean() {
    }
}
