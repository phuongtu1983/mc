//mai vinh loc
package com.venus.mc.bean;

public class EdnBean {

    //fields region
    private int dnId; // primary key
    private String createdDate;
    private int createdEmp;
    private String dnNumber;
    private String expectedDate;
    private String deliveryPresenter;
    private String deliveryPlace;
    private String contractNumber;
    private int createdOrg;
    private int proId;
    private String proName;
    private String orgName;

    //constructure region
    public EdnBean() {
    }

    public EdnBean(int dnId) {
        this.dnId = dnId;
    }

    public EdnBean(int dnId, String createdDate, int createdEmp, String dnNumber, String expectedDate, String deliveryPresenter, String deliveryPlace, String contractNumber, int createdOrg, int proId, String proName, String orgName, int highlight) {
        this.dnId = dnId;
        this.createdDate = createdDate;
        this.createdEmp = createdEmp;
        this.dnNumber = dnNumber;
        this.expectedDate = expectedDate;
        this.deliveryPresenter = deliveryPresenter;
        this.deliveryPlace = deliveryPlace;
        this.contractNumber = contractNumber;
        this.createdOrg = createdOrg;
        this.proId = proId;
        this.proName = proName;
        this.orgName = orgName;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public String getDeliveryPresenter() {
        return deliveryPresenter;
    }

    public int getDnId() {
        return dnId;
    }

    public String getDnNumber() {
        return dnNumber;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }

    public void setDeliveryPresenter(String deliveryPresenter) {
        this.deliveryPresenter = deliveryPresenter;
    }

    public void setDnId(int dnId) {
        this.dnId = dnId;
    }

    public void setDnNumber(String dnNumber) {
        this.dnNumber = dnNumber;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    public int getCreatedOrg() {
        return createdOrg;
    }

    public void setCreatedOrg(int createdOrg) {
        this.createdOrg = createdOrg;
    }

    public int getProId() {
        return proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
