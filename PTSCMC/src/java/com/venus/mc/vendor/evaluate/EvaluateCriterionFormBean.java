
/// <summary>
/// Author : phuongtu
/// Created Date : 06/07/2009
/// </summary>
package com.venus.mc.vendor.evaluate;

import com.venus.mc.bean.EvaluateCriterionBean;

public class EvaluateCriterionFormBean extends org.apache.struts.action.ActionForm {
    //fields region
    private int criId;
    private int parentId;
    private String name;
    private String reach;
    private String notReach;
    private String comments;

    //constructure region
    public EvaluateCriterionFormBean() {
    }

    public EvaluateCriterionFormBean(EvaluateCriterionBean bean) {
        this.criId = bean.getCriId();
        this.parentId = bean.getParentId();
        this.name = bean.getName();
        this.reach = bean.getReach();
        this.notReach = bean.getNotReach();
        this.comments = bean.getComments();
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

