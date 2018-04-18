/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author mai vinh loc
 */
public class TechEvalBean {

    private int teId; // primary key   
    private int tenId; // foreign key : reference to tender_plan(ten_id)
    private int borId;
    private String createdDate;
    private String[] tegId;
    private String[] regTegId;
    private String[] employee;
    private String[] position;
    private String tenNumber;
    private String borNumber;
    private int createdEmp;
    private int form;

    //constructure region
    public TechEvalBean() {
    }

    public TechEvalBean(int teId, int tenId, int borId, String createdDate, String[] tegId, String[] regTegId, String[] employee, String[] position, String tenNumber, String borNumber, int createdEmp, int form) {
        this.teId = teId;
        this.tenId = tenId;
        this.borId = borId;
        this.createdDate = createdDate;
        this.tegId = tegId;
        this.regTegId = regTegId;
        this.employee = employee;
        this.position = position;
        this.tenNumber = tenNumber;
        this.borNumber = borNumber;
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

    public int getBorId() {
        return borId;
    }

    public void setBorId(int borId) {
        this.borId = borId;
    }

    //properties region
    public int getTeId() {
        return this.teId;
    }

    public void setTeId(int teId) {
        this.teId = teId;
    }

    public int getTenId() {
        return this.tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
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
}
