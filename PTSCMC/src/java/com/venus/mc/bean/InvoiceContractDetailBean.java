
/// <summary>
/// Author : phuongtu
/// Created Date : 04/11/2009
/// </summary>
package com.venus.mc.bean;

public class InvoiceContractDetailBean {

    //fields region
    private int detId; // primary key
    private int icId;
    private int matId;
    private String unit;
    private double quantity;
    private double price;
    private double total;
    private String currency;
    private double vat;
    private String note;
    private int reqDetailId;
    private String matName;
    private String matOrigin;
    private String requestNumber;

    //constructure region
    public InvoiceContractDetailBean() {
    }

    public InvoiceContractDetailBean(int detId) {
        this.detId = detId;
    }

    public InvoiceContractDetailBean(int detId, int icId, int matId, String unit, double quantity, double price, double total, String currency, double vat, String note, int reqDetailId) {
        this.detId = detId;
        this.icId = icId;
        this.matId = matId;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.currency = currency;
        this.vat = vat;
        this.note = note;
        this.reqDetailId = reqDetailId;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getIcId() {
        return this.icId;
    }

    public void setIcId(int icId) {
        this.icId = icId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getVat() {
        return this.vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getReqDetailId() {
        return this.reqDetailId;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatOrigin() {
        return matOrigin;
    }

    public void setMatOrigin(String matOrigin) {
        this.matOrigin = matOrigin;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }
}
