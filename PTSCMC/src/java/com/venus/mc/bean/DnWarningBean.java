//mai vinh loc
package com.venus.mc.bean;

public class DnWarningBean {

    //fields region
    private int dnwId; // primary key
    private String matName;
    private String dnNumber;
    private String matUnit;
    private double quantity;
    private String reqNumber;
    private String expectedDate;

    //constructure region
    public DnWarningBean() {
    }

    public DnWarningBean(int dnwId, String matName, String dnNumber) {
        this.dnwId = dnwId;
        this.dnNumber = dnNumber;
        this.matName = matName;
    }

    public int getDnwId() {
        return dnwId;
    }

    public void setDnwId(int dnwId) {
        this.dnwId = dnwId;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getDnNumber() {
        return dnNumber;
    }

    public void setDnNumber(String dnNumber) {
        this.dnNumber = dnNumber;
    }

    public String getMatUnit() {
        return matUnit;
    }

    public void setMatUnit(String matUnit) {
        this.matUnit = matUnit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getReqNumber() {
        return reqNumber;
    }

    public void setReqNumber(String reqNumber) {
        this.reqNumber = reqNumber;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }
}
