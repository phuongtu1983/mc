/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author phuongtu
 */
public class VendorCriterionBean {

    private int criid;
    private int parentid;
    private String name;
    private String reach;
    private String notReach;
    private String comment;

    public VendorCriterionBean() {
    }

    public String getComment() {
        return comment;
    }

    public int getCriid() {
        return criid;
    }

    public String getName() {
        return name;
    }

    public String getNotReach() {
        return notReach;
    }

    public int getParentid() {
        return parentid;
    }

    public String getReach() {
        return reach;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCriid(int criid) {
        this.criid = criid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNotReach(String notReach) {
        this.notReach = notReach;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public void setReach(String reach) {
        this.reach = reach;
    }
}
