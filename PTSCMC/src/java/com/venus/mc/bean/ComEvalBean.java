/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class ComEvalBean {

    //fields region
    private int ceId; // primary key
    private int tenId; // foreign key : reference to tender_plan(ten_id)
    private int ccId; // foreign key : reference to com_clarification(cc_id)
    private String createdDate;
    private String[] tegId;
    private String[] regTegId;
    private String[] employee;
    private String[] position;
    private String tenNumber;
    private String borNumber;
    private String kind;
    private int cevId;
    private int cemId;
    private int createdEmp;
    private int form;

    //constructure region
    public ComEvalBean() {
    }

    public ComEvalBean(int ceId, int tenId, int ccId, String createdDate, String[] tegId, String[] regTegId, String[] employee, String[] position, String tenNumber, String borNumber, String kind, int cevId, int cemId, int createdEmp, int form) {
        this.ceId = ceId;
        this.tenId = tenId;
        this.ccId = ccId;
        this.createdDate = createdDate;
        this.tegId = tegId;
        this.regTegId = regTegId;
        this.employee = employee;
        this.position = position;
        this.tenNumber = tenNumber;
        this.borNumber = borNumber;
        this.kind = kind;
        this.cevId = cevId;
        this.cemId = cemId;
        this.createdEmp = createdEmp;
        this.form = form;
    }

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    //properties region
    public int getCeId() {
        return this.ceId;
    }

    public void setCeId(int ceId) {
        this.ceId = ceId;
    }

    public int getTenId() {
        return this.tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public int getCcId() {
        return this.ccId;
    }

    public void setCcId(int ccId) {
        this.ccId = ccId;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String[] getPosition() {
        return position;
    }

    public String[] getRegTegId() {
        return regTegId;
    }

    public String[] getTegId() {
        return tegId;
    }

    public String[] getEmployee() {
        return employee;
    }

    public void setEmployee(String[] employee) {
        this.employee = employee;
    }

    public void setPosition(String[] position) {
        this.position = position;
    }

    public void setRegTegId(String[] regTegId) {
        this.regTegId = regTegId;
    }

    public void setTegId(String[] tegId) {
        this.tegId = tegId;
    }

    public String getBorNumber() {
        return borNumber;
    }

    public void setTenNumber(String tenNumber) {
        this.tenNumber = tenNumber;
    }

    public void setBorNumber(String borNumber) {
        this.borNumber = borNumber;
    }

    public String getTenNumber() {
        return tenNumber;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public int getCemId() {
        return cemId;
    }

    public void setCemId(int cemId) {
        this.cemId = cemId;
    }

    public int getCevId() {
        return cevId;
    }

    public void setCevId(int cevId) {
        this.cevId = cevId;
    }
}
