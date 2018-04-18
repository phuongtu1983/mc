/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class BidClosingGroupBean {
    //fields region

    private int bcgId; // primary key
    private int bcrId; // foreign key : reference to bid_closing_report(bcr_id)
    private String employee;
    private String position;
    private String note;

    //constructure region
    public BidClosingGroupBean() {
    }

    public BidClosingGroupBean(int bcgId) {
        this.bcgId = bcgId;
    }

    public BidClosingGroupBean(int bcgId, String employee, String position, String note) {
        this.bcgId = bcgId;
        this.employee = employee;
        this.position = position;
        this.note = note;
    }

    //properties region
    public int getBcgId() {
        return this.bcgId;
    }

    public void setBcgId(int bcgId) {
        this.bcgId = bcgId;
    }

    public int getBcrId() {
        return this.bcrId;
    }

    public void setBcrId(int bcrId) {
        this.bcrId = bcrId;
    }

    public String getEmployee() {
        return this.employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
