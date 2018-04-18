/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class BidEvalSumVendorBean {

    //fields region
    private int besvId; // primary key
    private int besId; // foreign key : reference to bid_eval_sum(bes_id)
    private int venId; // foreign key : reference to vendor(ven_id)
    private int tenId;
    private String deliveryTime;
    private String paymentMode;
    private String vendorName;
    private String costTransport;
    private String otherTax;
    private String otherCost;
    private String sum;

    //constructure region
    public BidEvalSumVendorBean() {
    }

    public BidEvalSumVendorBean(int besvId) {
        this.besvId = besvId;
    }

    public BidEvalSumVendorBean(int besvId, String deliveryTime, String paymentMode) {
        this.besvId = besvId;
        this.deliveryTime = deliveryTime;
        this.paymentMode = paymentMode;
    }

    //properties region
    public int getBesvId() {
        return this.besvId;
    }

    public void setBesvId(int besvId) {
        this.besvId = besvId;
    }

    public int getBesId() {
        return this.besId;
    }

    public void setBesId(int besId) {
        this.besId = besId;
    }

    public int getVenId() {
        return this.venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public String getDeliveryTime() {
        return this.deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getPaymentMode() {
        return this.paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getCostTransport() {
        return costTransport;
    }

    public String getOtherCost() {
        return otherCost;
    }

    public String getOtherTax() {
        return otherTax;
    }

    public String getSum() {
        return sum;
    }

    public void setCostTransport(String costTransport) {
        this.costTransport = costTransport;
    }

    public void setOtherCost(String otherCost) {
        this.otherCost = otherCost;
    }

    public void setOtherTax(String otherTax) {
        this.otherTax = otherTax;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public int getTenId() {
        return tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    
}
