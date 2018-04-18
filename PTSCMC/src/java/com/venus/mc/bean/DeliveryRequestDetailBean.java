
/// <summary>
/// Author : phuongtu
/// Created Date : 24/08/2009
/// </summary>
package com.venus.mc.bean;

public class DeliveryRequestDetailBean {

    //fields region
    private int detId; // primary key
    private int derId; // foreign key : reference to delivery_request(dr_id)
    private int matId; // foreign key : reference to material(mat_id)
    private double quantity;
    private String unit;
    private double price;
    private double vat;
    private String currency;
    private double total;
//    private String deliveryDate;
    private int conDetailId;
    private int reqDetailId;
//    private String moveCreateMrir;
//    private String receiveMrir;
//    private String moveCreateMsv;
//    private String receiveMsv;
//    private String note;
    //constructure region
    public DeliveryRequestDetailBean() {
    }

    public DeliveryRequestDetailBean(int detId) {
        this.detId = detId;
    }

    public DeliveryRequestDetailBean(int detId, double quantity, String unit, double price, double vat, String currency, double total/*, String deliveryDate, String moveCreateMrir, String receiveMrir, String moveCreateMsv, String receiveMsv, String note*/) {
        this.detId = detId;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.vat = vat;
        this.currency = currency;
        this.total = total;
//        this.deliveryDate = deliveryDate;
//        this.moveCreateMrir = moveCreateMrir;
//        this.moveCreateMsv = moveCreateMsv;
//        this.receiveMrir = receiveMrir;
//        this.receiveMsv = receiveMsv;
//        this.note = note;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getDerId() {
        return derId;
    }

    public void setDerId(int derId) {
        this.derId = derId;
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

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVat() {
        return this.vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

//    public String getDeliveryDate() {
//        return this.deliveryDate;
//    }
//
//    public void setDeliveryDate(String deliveryDate) {
//        this.deliveryDate = deliveryDate;
//    }
    public int getConDetailId() {
        return conDetailId;
    }

    public void setConDetailId(int conDetailId) {
        this.conDetailId = conDetailId;
    }

    public int getReqDetailId() {
        return reqDetailId;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }
//    public String getMoveCreateMrir() {
//        return this.moveCreateMrir;
//    }
//
//    public void setMoveCreateMrir(String moveCreateMrir) {
//        this.moveCreateMrir = moveCreateMrir;
//    }
//
//    public String getReceiveMrir() {
//        return this.receiveMrir;
//    }
//
//    public void setReceiveMrir(String receiveMrir) {
//        this.receiveMrir = receiveMrir;
//    }
//
//    public String getMoveCreateMsv() {
//        return this.moveCreateMsv;
//    }
//
//    public void setMoveCreateMsv(String moveCreateMsv) {
//        this.moveCreateMsv = moveCreateMsv;
//    }
//
//    public String getReceiveMsv() {
//        return this.receiveMsv;
//    }
//
//    public void setReceiveMsv(String receiveMsv) {
//        this.receiveMsv = receiveMsv;
//    }
//
//    public String getNote() {
//        return this.note;
//    }
//
//    public void setNote(String note) {
//        this.note = note;
//    }
}
