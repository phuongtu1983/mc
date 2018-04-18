/// <summary>
/// Author : phuongtu
/// Created Date : 19/08/2009
/// </summary>
package com.venus.mc.bean;

public class BidOpeningGroupBean {

    //fields region
    private int bogId; // primary key
    private int borId; // foreign key : reference to bid_opening_report(bor_id)
    private String employee;
    private String position;
    private String note;

    //constructure region
    public BidOpeningGroupBean() {
    }

    public BidOpeningGroupBean(int bogId) {
        this.bogId = bogId;
    }

    public BidOpeningGroupBean(int bogId, String employee, String position, String orgName, String note) {
        this.bogId = bogId;
        this.employee = employee;
        this.position = position;
        this.note = note;
    }

    //properties region
    public int getBogId() {
        return this.bogId;
    }

    public void setBogId(int bogId) {
        this.bogId = bogId;
    }

    public int getBorId() {
        return this.borId;
    }

    public void setBorId(int borId) {
        this.borId = borId;
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
