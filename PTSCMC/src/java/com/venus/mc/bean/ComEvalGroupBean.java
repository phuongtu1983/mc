/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author mai vinh loc
 */
public class ComEvalGroupBean {

    //fields region
    private int cegId; // primary key
    private int ceId; // foreign key : reference to tender_plan(ten_id)
    private String employee;
    private String position;
    private String fullname;
    private String posName;
    private String note;

    //constructure region
    public ComEvalGroupBean() {
    }

    public ComEvalGroupBean(int cegId) {
        this.cegId = cegId;
    }

    public ComEvalGroupBean(int cegId, String employee, String position, String note) {
        this.cegId = cegId;
        this.employee = employee;
        this.position = position;
        this.note = note;
    }

    //properties region
    public int getCegId() {
        return this.cegId;
    }

    public void setCegId(int cegId) {
        this.cegId = cegId;
    }

    public int getCeId() {
        return this.ceId;
    }

    public void setCeId(int ceId) {
        this.ceId = ceId;
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
