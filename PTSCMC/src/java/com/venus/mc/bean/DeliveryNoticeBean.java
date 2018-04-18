
/// <summary>
/// Author : kngo
/// Created Date : 05/10/2009
/// </summary>

package com.venus.mc.bean;

public class DeliveryNoticeBean {

    //fields region
    private int dnId; // primary key
    private int conId; // foreign key : reference to contract(con_id)
    private int drId; // foreign key : reference to delivery_request(dr_id)
    private String dnNumber;
    private String createdDate;
    private String expectedDate;
    private String deliveryPlace;
    private String deliveryPresenter;

    //constructure region
    public DeliveryNoticeBean() {
    }

    public DeliveryNoticeBean(int dnId) {
        this.dnId = dnId;
    }

    public DeliveryNoticeBean(int dnId, String dnNumber, String createdDate, String expectedDate, String deliveryPlace, String deliveryPresenter) {
        this.dnId = dnId;
        this.dnNumber = dnNumber;
        this.createdDate = createdDate;
        this.expectedDate = expectedDate;
        this.deliveryPlace = deliveryPlace;
        this.deliveryPresenter = deliveryPresenter;
    }

    //properties region
    public int getDnId() {
        return this.dnId;
    }

    public void setDnId(int dnId) {
        this.dnId = dnId;
    }

    public int getConId() {
        return this.conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public int getDrId() {
        return this.drId;
    }

    public void setDrId(int drId) {
        this.drId = drId;
    }

    public String getDnNumber() {
        return this.dnNumber;
    }

    public void setDnNumber(String dnNumber) {
        this.dnNumber = dnNumber;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getExpectedDate() {
        return this.expectedDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }

    public String getDeliveryPlace() {
        return this.deliveryPlace;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    public String getDeliveryPresenter() {
        return this.deliveryPresenter;
    }

    public void setDeliveryPresenter(String deliveryPresenter) {
        this.deliveryPresenter = deliveryPresenter;
    }
}
