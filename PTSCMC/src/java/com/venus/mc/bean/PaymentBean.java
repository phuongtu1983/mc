/// <summary>
/// Author : phuongtu
/// Created Date : 29/09/2009
/// </summary>
package com.venus.mc.bean;

public class PaymentBean {

    //fields region
    private int payId; // primary key
    private String payNumber;
    private String createdDate;
    private String payDate;
    private double total;
    private String document;
    private String note;
    private String moveAccountingDate;
    private int status;
    private int createdEmp;
    private String location;
    private int handleEmp;
    private String volume;
    private double totalPay;
    private double punish;
    private double rates;

    //constructure region
    public PaymentBean() {
    }

    public PaymentBean(int payId) {
        this.payId = payId;
    }

    public PaymentBean(int payId, String payNumber, String createdDate, String payDate, double total, String document, String note, String moveAccountingDate, String volume) {
        this.payId = payId;
        this.payNumber = payNumber;
        this.createdDate = createdDate;
        this.payDate = payDate;
        this.total = total;
        this.document = document;
        this.note = note;
        this.moveAccountingDate = moveAccountingDate;
        this.volume = volume;

    }

    //properties region
    public int getPayId() {
        return this.payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    public String getPayNumber() {
        return this.payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPayDate() {
        return this.payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDocument() {
        return this.document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMoveAccountingDate() {
        return this.moveAccountingDate;
    }

    public void setMoveAccountingDate(String moveAccountingDate) {
        this.moveAccountingDate = moveAccountingDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public int getHandleEmp() {
        return handleEmp;
    }

    public void setHandleEmp(int handleEmp) {
        this.handleEmp = handleEmp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }

    public double getPunish() {
        return punish;
    }

    public void setPunish(double punish) {
        this.punish = punish;
    }

    public double getRates() {
        return rates;
    }

    public void setRates(double rates) {
        this.rates = rates;
    }
    public static int STATUS_PAID = 1;
    public static int STATUS_REQUESTED = 2;
}
