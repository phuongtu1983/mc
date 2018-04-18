//mai vinh loc
package com.venus.mc.bean;

public class McWarningBean {

    //fields region
    private int mcId; // primary key
    private String matName;
    private String manageCode;
    private double quantity;
    private String mcNumber;
    private String outDatePlan;

    //constructure region
    public McWarningBean() {
    }

    public int getMcId() {
        return mcId;
    }

    public void setMcId(int mcId) {
        this.mcId = mcId;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public void setManageCode(String manageCode) {
        this.manageCode = manageCode;
    }

    public String getManageCode() {
        return manageCode;
    }

    public String getMcNumber() {
        return mcNumber;
    }

    public void setMcNumber(String mcNumber) {
        this.mcNumber = mcNumber;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getOutDatePlan() {
        return outDatePlan;
    }

    public void setOutDatePlan(String outDatePlan) {
        this.outDatePlan = outDatePlan;
    }
}
