/// <summary>
/// Author : phuongtu
/// Created Date : 21/09/2009
/// </summary>
package com.venus.mc.process.store.report;

public class Store2Level2FormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int id;
    private String matCode;
    private String matName;
    private String organization;
    private String projectName;
    private String requestNumber;
    private String rfmNumber;
    private String mivNumber;
    private double mivQuantity;
    private String matUnit;
    private String mivDate;
    private String workshop;
    private String usmNumber;
    private String usmDate;
    private double usmQuantity;
    private double balance;
    private String matNote;
    private String usmMatUnit;

    //constructure region
    public Store2Level2FormBean() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatUnit() {
        return matUnit;
    }

    public void setMatUnit(String matUnit) {
        this.matUnit = matUnit;
    }

    public String getRfmNumber() {
        return rfmNumber;
    }

    public void setRfmNumber(String rfmNumber) {
        this.rfmNumber = rfmNumber;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getMivNumber() {
        return mivNumber;
    }

    public void setMivNumber(String mivNumber) {
        this.mivNumber = mivNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMivQuantity() {
        return mivQuantity;
    }

    public void setMivQuantity(double mivQuantity) {
        this.mivQuantity = mivQuantity;
    }

    public String getMivDate() {
        return mivDate;
    }

    public void setMivDate(String mivDate) {
        this.mivDate = mivDate;
    }

    public String getMatNote() {
        return matNote;
    }

    public void setMatNote(String matNote) {
        this.matNote = matNote;
    }

    public String getUsmDate() {
        return usmDate;
    }

    public void setUsmDate(String usmDate) {
        this.usmDate = usmDate;
    }

    public String getUsmNumber() {
        return usmNumber;
    }

    public void setUsmNumber(String usmNumber) {
        this.usmNumber = usmNumber;
    }

    public double getUsmQuantity() {
        return usmQuantity;
    }

    public void setUsmQuantity(double usmQuantity) {
        this.usmQuantity = usmQuantity;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    public String getUsmMatUnit() {
        return usmMatUnit;
    }

    public void setUsmMatUnit(String usmMatUnit) {
        this.usmMatUnit = usmMatUnit;
    }
}