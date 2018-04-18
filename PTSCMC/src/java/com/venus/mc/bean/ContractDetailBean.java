/// <summary>
/// Author : phuongtu
/// Created Date : 25/09/2009
/// </summary>
package com.venus.mc.bean;

public class ContractDetailBean {

    //fields region
    private int detId; // primary key
    private int conId; // foreign key : reference to contract(con_id)
    private int matId; // foreign key : reference to material(mat_id)
    private String unit;
    private double quantity;
    private double price;
    private double total;
    private String currency;
    private double vat;
    private int expired;
    private int status;
//    private String deliveryDate;
//    private String moveCreateMrir;
//    private String receiveMrir;
//    private String moveCreateMsv;
//    private String receiveMsv;
//    private String note;
    private int reqDetailId;
    private String note;
    private int matIdTemp;
    private String materialName;
    private int confirm;

    //constructure region
    public ContractDetailBean() {
    }

    public ContractDetailBean(int detId) {
        this.detId = detId;
    }

    public ContractDetailBean(int detId, String unit, double quantity, double price, double total, String currency, double vat/*, String deliveryDate, String moveCreateMrir, String receiveMrir, String moveCreateMsv, String receiveMsv, String note*/, int reqDetailId, int matIdTemp, String materialName, int confirm) {
        this.detId = detId;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.currency = currency;
        this.vat = vat;
//        this.deliveryDate = deliveryDate;
//        this.moveCreateMrir = moveCreateMrir;
//        this.receiveMrir = receiveMrir;
//        this.moveCreateMsv = moveCreateMsv;
//        this.receiveMsv = receiveMsv;
//        this.note = note;
        this.reqDetailId = reqDetailId;
        this.matIdTemp = matIdTemp;
        this.materialName = materialName;
        this.confirm = confirm;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getConfirm() {
        return confirm;
    }

    public int getMatIdTemp() {
        return matIdTemp;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }

    public void setMatIdTemp(int matIdTemp) {
        this.matIdTemp = matIdTemp;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public int getConId() {
        return this.conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
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

//    public String getDeliveryDate() {
//        return this.deliveryDate;
//    }
//
//    public void setDeliveryDate(String deliveryDate) {
//        this.deliveryDate = deliveryDate;
//    }
//
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
    public int getReqDetailId() {
        return this.reqDetailId;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
