
/// <summary>
/// Author : phuongtu
/// Created Date : 06/07/2009
/// </summary>
package com.venus.mc.bean;

public class EvaluateCriterionBean {
    //fields region
    private int criId; // primary key
    private int parentId;
    private String name;
    private String reach;
    private String notReach;
    private String comments;

    //constructure region
    public EvaluateCriterionBean() {
    }

    public EvaluateCriterionBean(int criId) {
        this.criId = criId;
    }

    public EvaluateCriterionBean(int criId, int parentId, String name, String reach, String notReach, String comments) {
        this.criId = criId;
        this.parentId = parentId;
        this.name = name;
        this.reach = reach;
        this.notReach = notReach;
        this.comments = comments;
    }

    //properties region
    public int getCriId() {
        return this.criId;
    }

    public void setCriId(int criId) {
        this.criId = criId;
    }

    public int getParentId() {
        return this.parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReach() {
        return this.reach;
    }

    public void setReach(String reach) {
        this.reach = reach;
    }

    public String getNotReach() {
        return this.notReach;
    }

    public void setNotReach(String notReach) {
        this.notReach = notReach;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

