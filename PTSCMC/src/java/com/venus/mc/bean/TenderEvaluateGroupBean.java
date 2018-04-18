/// <summary>
/// Author : phuongtu
/// Created Date : 13/08/2009
/// </summary>
package com.venus.mc.bean;

public class TenderEvaluateGroupBean {

    //fields region
    private int tegId; // primary key
    private int tenId; // foreign key : reference to tender_plan(ten_id)
    private String employee;
    private String position;
    private String orgName;
    private String note;
    private int stt;

    //constructure region
    public TenderEvaluateGroupBean() {
    }

    public TenderEvaluateGroupBean(int tegId) {
        this.tegId = tegId;
    }

    public TenderEvaluateGroupBean(int tegId, String employee, String position, String note) {
        this.tegId = tegId;
        this.employee = employee;
        this.position = position;
        this.note = note;
    }

    public TenderEvaluateGroupBean(int tegId, int tenId, String employee, String position, String orgName, String note, int stt) {
        this.tegId = tegId;
        this.tenId = tenId;
        this.employee = employee;
        this.position = position;
        this.orgName = orgName;
        this.note = note;
        this.stt = stt;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    //properties region
    public int getTegId() {
        return this.tegId;
    }

    public void setTegId(int tegId) {
        this.tegId = tegId;
    }

    public int getTenId() {
        return this.tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
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
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
