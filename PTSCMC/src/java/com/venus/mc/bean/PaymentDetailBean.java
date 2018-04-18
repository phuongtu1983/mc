/// <summary>
/// Author : phuongtu
/// Created Date : 29/09/2009
/// </summary>
package com.venus.mc.bean;

public class PaymentDetailBean {

    //fields region
    private int detId; // primary key
    private int payId;
    private int invId;
    private int conId;
    private double amount;
    private String note;

    //constructure region
    public PaymentDetailBean() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getConId() {
        return conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public int getDetId() {
        return detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getInvId() {
        return invId;
    }

    public void setInvId(int invId) {
        this.invId = invId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }
}
