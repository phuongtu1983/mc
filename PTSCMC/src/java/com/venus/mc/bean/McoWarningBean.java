//mai vinh loc
package com.venus.mc.bean;

public class McoWarningBean {

    //fields region
    private int mcoId; // primary key
    private String matName;
    private String manageCode;
    private String onDatePlan;
    private double quantity;
    private String mcoNumber;

    //constructure region
    public McoWarningBean() {
    }

    public int getMcoId() {
        return mcoId;
    }

    public void setMcoId(int mcoId) {
        this.mcoId = mcoId;
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

    public String getMcoNumber() {
        return mcoNumber;
    }

    public void setMcoNumber(String mcoNumber) {
        this.mcoNumber = mcoNumber;
    }

    public String getOnDatePlan() {
        return onDatePlan;
    }

    public void setOnDatePlan(String onDatePlan) {
        this.onDatePlan = onDatePlan;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
