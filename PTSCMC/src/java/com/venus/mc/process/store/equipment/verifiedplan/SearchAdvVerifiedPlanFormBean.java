package com.venus.mc.process.store.equipment.verifiedplan;

/**
 * @author Mai Vinh Loc
 */
public class SearchAdvVerifiedPlanFormBean extends org.apache.struts.action.ActionForm {

    private int vpId;
    private int equId;
    private int assId;
    private String timePlan;
    private String timeEffect;
    private String timeNext;
    private String content;
    private double cost;
    private int status;
    private String comment;
    private int performKind;
    private String performPart;
    private int orgId;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVpId() {
        return vpId;
    }

    public int getStatus() {
        return status;
    }

    public String getTimePlan() {
        return timePlan;
    }

    public String getTimeEffect() {
        return timeEffect;
    }

    public void setTimeNext(String timeNext) {
        this.timeNext = timeNext;
    }

    public String getTimeNext() {
        return timeNext;
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

    public void setVpId(int vpId) {
        this.vpId = vpId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTimeEffect(String timeEffect) {
        this.timeEffect = timeEffect;
    }

    public void setTimePlan(String timePlan) {
        this.timePlan = timePlan;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getOrgId() {
        return orgId;
    }

    public SearchAdvVerifiedPlanFormBean() {
    }
}
