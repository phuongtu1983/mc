/// <summary>
/// Author : phuongtu
/// Created Date : 21/09/2009
/// </summary>
package com.venus.mc.process.store.report;

public class Store2LevelFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int id;
    private String matCode;
    private String matName;
    private String matUnit;
    private double quantity;
    private String requestNumber;
    private String rfmmiv;
    private String umsNumnber;
    private String umsEmployee;
    private String projectName;
    private String rmsNumber;
    private String rmsEmployee;
    private String workshop;
    private String umsrmsDate;
    private double balance;
    private String matNote;
    private String location;

    //constructure region
    public Store2LevelFormBean() {
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

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getMatNote() {
        return matNote;
    }

    public void setMatNote(String matNote) {
        this.matNote = matNote;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getRfmmiv() {
        return rfmmiv;
    }

    public void setRfmmiv(String rfmmiv) {
        this.rfmmiv = rfmmiv;
    }

    public String getRmsEmployee() {
        return rmsEmployee;
    }

    public void setRmsEmployee(String rmsEmployee) {
        this.rmsEmployee = rmsEmployee;
    }

    public String getRmsNumber() {
        return rmsNumber;
    }

    public void setRmsNumber(String rmsNumber) {
        this.rmsNumber = rmsNumber;
    }

    public String getUmsEmployee() {
        return umsEmployee;
    }

    public void setUmsEmployee(String umsEmployee) {
        this.umsEmployee = umsEmployee;
    }

    public String getUmsNumnber() {
        return umsNumnber;
    }

    public void setUmsNumnber(String umsNumnber) {
        this.umsNumnber = umsNumnber;
    }

    public String getUmsrmsDate() {
        return umsrmsDate;
    }

    public void setUmsrmsDate(String umsrmsDate) {
        this.umsrmsDate = umsrmsDate;
    }
}