/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.deliveryrequest;

import com.venus.mc.bean.DeliveryRequestDetailBean;

/**
 *
 * @author phuongtu
 */
public class DeliveryRequestDetailFormBean extends org.apache.struts.action.ActionForm {

    private int detId;
    private int derId;
    private int matId;
    private double quantity;
    private String unit;
    private String origin;
    private double price;
    private double vat;
    private double total;
//    private String deliveryDate;
    private String matName;
    private int conDetailId;
    private String contractNumber;
    private int reqDetId;
    private String requestNumber;
    private String requestNumbers;
    private String conNumber;
    private String currency;
    private int stt;
    private String created_date;
//    private String moveCreateMrir;
//    private String receiveMrir;
//    private String moveCreateMsv;
//    private String receiveMsv;
//    private String note;
    //constructure region

    public DeliveryRequestDetailFormBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public DeliveryRequestDetailFormBean(int stt, String matName, String unit, double quantity, double price, double total, String requestNumbers) {
        this.stt = stt;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.total = total;
        this.matName = matName;
        this.requestNumbers = requestNumbers;
        
    }

    public DeliveryRequestDetailFormBean(DeliveryRequestDetailBean bean) {
        this.detId = bean.getDetId();
        this.derId = bean.getDerId();
        this.matId = bean.getMatId();
        this.quantity = bean.getQuantity();
        this.unit = bean.getUnit();
        this.price = bean.getPrice();
        this.vat = bean.getVat();
        this.total = bean.getTotal();
        this.currency = bean.getCurrency();
//        this.deliveryDate = bean.getDeliveryDate();
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    //properties region
    
    public int getDetId() {
        return this.detId;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public int getStt() {
        return stt;
    }

    public void setRequestNumbers(String requestNumbers) {
        this.requestNumbers = requestNumbers;
    }

    public String getRequestNumbers() {
        return requestNumbers;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public String getConNumber() {
        return conNumber;
    }

    public void setConNumber(String conNumber) {
        this.conNumber = conNumber;
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
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

//    public String getDeliveryDate() {
//        return deliveryDate;
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

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getReqDetId() {
        return reqDetId;
    }

    public void setReqDetId(int reqDetId) {
        this.reqDetId = reqDetId;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
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
