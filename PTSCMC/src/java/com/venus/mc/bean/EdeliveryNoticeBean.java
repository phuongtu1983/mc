/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class EdeliveryNoticeBean {

    //fields region
    private int ednId; // primary key
    private String ednNumber;
    private int proId;
    private String econNumber;
    private String createdDate;
    private String expectedDate;
    private String deliveryPlace;
    private String deliveryPresenter;

    //constructure region
    public EdeliveryNoticeBean() {
        super();
    }

    public EdeliveryNoticeBean(int ednId) {
        this.ednId = ednId;
    }

    public EdeliveryNoticeBean(int ednId, String ednNumber, String createdDate, String expectedDate, String deliveryPlace, String deliveryPresenter) {
        this.ednId = ednId;
        this.ednNumber = ednNumber;
        this.createdDate = createdDate;
        this.expectedDate = expectedDate;
        this.deliveryPlace = deliveryPlace;
        this.deliveryPresenter = deliveryPresenter;
    }

    //properties region
    public int getEdnId() {
        return this.ednId;
    }

    public void setEdnId(int ednId) {
        this.ednId = ednId;
    }

    public String getEdnNumber() {
        return this.ednNumber;
    }

    public void setEdnNumber(String ednNumber) {
        this.ednNumber = ednNumber;
    }

    public int getProId() {
        return this.proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getEconNumber() {
        return this.econNumber;
    }

    public void setEconNumber(String econNumber) {
        this.econNumber = econNumber;
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
