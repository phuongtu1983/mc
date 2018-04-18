/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class BidEvalSumDetailBean {

    //fields region
    private int detId; // primary key
    private int besvId; // foreign key : reference to bid_eval_sum_vendor(besv_id)
    private int matId; // foreign key : reference to material(mat_id)
    private double quantity;
    private String price;
    private String priceAfter;
    private String total;
    private String matName;
    private String unit;
    private String nameVn;
    private String stt;
    private String request;
    private String deliveryTime;

    //constructure region
    public BidEvalSumDetailBean() {
    }

    public BidEvalSumDetailBean(int detId) {
        this.detId = detId;
    }

    public BidEvalSumDetailBean(int detId, double quantity, String price, String total, String deliveryTime) {
        this.detId = detId;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.deliveryTime = deliveryTime;
    }

    public void setPriceAfter(String priceAfter) {
        this.priceAfter = priceAfter;
    }

    public String getPriceAfter() {
        return priceAfter;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getBesvId() {
        return this.besvId;
    }

    public void setBesvId(int besvId) {
        this.besvId = besvId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal() {
        return this.total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public String getNameVn() {
        return nameVn;
    }

    public void setNameVn(String nameVn) {
        this.nameVn = nameVn;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
