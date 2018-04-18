/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author mai vinh loc
 */
public class TechEvalGroupBean {

    //fields region
    private int tegId; // primary key
    private int teId; // foreign key : reference to tender_plan(ten_id)
    private String employee;
    private String position;
    private String fullname;
    private String posName;
    private String note;

    //constructure region
    public TechEvalGroupBean() {
    }

    public TechEvalGroupBean(int tegId) {
        this.tegId = tegId;
    }

    public TechEvalGroupBean(int tegId, String employee, String position, String note) {
        this.tegId = tegId;
        this.employee = employee;
        this.position = position;
        this.note = note;
    }

    //properties region
    public int getTegId() {
        return this.tegId;
    }

    public void setTegId(int tegId) {
        this.tegId = tegId;
    }

    public int getTeId() {
        return this.teId;
    }

    public void setTeId(int teId) {
        this.teId = teId;
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

    public String getFullname() {
        return fullname;
    }

    public String getPosName() {
        return posName;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }
    
}
