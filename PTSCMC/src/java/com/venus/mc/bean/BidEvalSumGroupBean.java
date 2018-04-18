/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class BidEvalSumGroupBean {

    //fields region
    private int besgId; // primary key
    private int besId; // foreign key : reference to bid_eval_sum(bes_id)
    private String employee;
    private String position;
    private String note;

    //constructure region
    public BidEvalSumGroupBean() {
    }

    public BidEvalSumGroupBean(int besgId) {
        this.besgId = besgId;
    }

    public BidEvalSumGroupBean(int besgId, String employee, String position, String note) {
        this.besgId = besgId;
        this.employee = employee;
        this.position = position;
        this.note = note;
    }

    //properties region
    public int getBesgId() {
        return this.besgId;
    }

    public void setBesgId(int besgId) {
        this.besgId = besgId;
    }

    public int getBesId() {
        return this.besId;
    }

    public void setBesId(int besId) {
        this.besId = besId;
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

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }
}
