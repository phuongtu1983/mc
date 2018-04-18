/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.techeval;

/**
 *
 * @author mai vinh loc
 */
public class TechEvalFormBean extends org.apache.struts.action.ActionForm {
    //fields region
    
    private int teId; // primary key
    private int tcId; // foreign key : reference to tech_clarification(tc_id)
    private int tenId; // foreign key : reference to tender_plan(ten_id)
    private int borId;
    private String createdDate;
    private String[] tegId;
    private String[] regTegId;
    private String[] employee;
    private String[] position;
    private String[] evalId;
    private String[] evalEmployee;
    private String[] evalPosition;
    private String[] evalNote;
    private String tenNumber;
    private String borNumber;

    //constructure region
    public TechEvalFormBean() {
        super();
   
    }

    public void setBorId(int borId) {
        this.borId = borId;
    }

    public int getBorId() {
        return borId;
    }

    //properties region
    public int getTeId() {
        return this.teId;
    }

    public void setTeId(int teId) {
        this.teId = teId;
    }

    public int getTcId() {
        return this.tcId;
    }

    public void setTcId(int tcId) {
        this.tcId = tcId;
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

    public void setEvalPosition(String[] evalPosition) {
        this.evalPosition = evalPosition;
    }

    public void setEvalNote(String[] evalNote) {
        this.evalNote = evalNote;
    }

    public void setEvalId(String[] evalId) {
        this.evalId = evalId;
    }

    public void setEvalEmployee(String[] evalEmployee) {
        this.evalEmployee = evalEmployee;
    }

    public String[] getEvalPosition() {
        return evalPosition;
    }

    public String[] getEvalNote() {
        return evalNote;
    }

    public String[] getEvalId() {
        return evalId;
    }

    public String[] getEvalEmployee() {
        return evalEmployee;
    }

}
