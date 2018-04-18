/// <summary>
/// Author : phuongtu
/// Created Date : 27/08/2009
/// </summary>
package com.venus.mc.payment;

import com.venus.mc.bean.PaymentBean;

public class PaymentFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int payId;
    private String payNumber;
    private String createdDate;
    private String payDate;
    private double total;
    private String document;
    private String note;
    private String moveAccountingDate;
    private String contractNumber;
    private String contractDate;
    private String vendorName;
    private int status;
    private int createdEmp;
    private String[] icId;
    private String[] payBillId;
    private String location;
    private int handleEmp;
    private String handleEmpName;
    private String statusName;
    private String volume;
    private double totalPay;
    private double punish;
    private int venId;
    private int conId;
    private int conKind;
    private String currency;
    private String totalPayText;
    private String[] invId;
    private String[] amount;
    private String[] payConId;
    private double rates;

    //constructure region
    public PaymentFormBean() {
    }

    public PaymentFormBean(int payId) {
        this.payId = payId;
    }

    public PaymentFormBean(PaymentBean bean) {
        this.payId = bean.getPayId();
        this.payNumber = bean.getPayNumber();
        this.createdDate = bean.getCreatedDate();
        this.payDate = bean.getPayDate();
        this.total = bean.getTotal();
        this.document = bean.getDocument();
        this.note = bean.getNote();
        this.moveAccountingDate = bean.getMoveAccountingDate();
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
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
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMoveAccountingDate() {
        return moveAccountingDate;
    }

    public void setMoveAccountingDate(String moveAccountingDate) {
        this.moveAccountingDate = moveAccountingDate;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String[] getIcId() {
        return icId;
    }

    public void setIcId(String[] icId) {
        this.icId = icId;
    }

    public String[] getPayBillId() {
        return payBillId;
    }

    public void setPayBillId(String[] payBillId) {
        this.payBillId = payBillId;
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

    public String getHandleEmpName() {
        return handleEmpName;
    }

    public void setHandleEmpName(String handleEmpName) {
        this.handleEmpName = handleEmpName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public int getVenId() {
        return venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public int getConKind() {
        return conKind;
    }

    public void setConKind(int conKind) {
        this.conKind = conKind;
    }

    public String getTotalPayText() {
        return totalPayText;
    }

    public void setTotalPayText(String totalPayText) {
        this.totalPayText = totalPayText;
    }

    public String[] getAmount() {
        return amount;
    }

    public void setAmount(String[] amount) {
        this.amount = amount;
    }

    public String[] getInvId() {
        return invId;
    }

    public void setInvId(String[] invId) {
        this.invId = invId;
    }

    public String[] getPayConId() {
        return payConId;
    }

    public void setPayConId(String[] payConId) {
        this.payConId = payConId;
    }

    public int getConId() {
        return conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public double getRates() {
        return rates;
    }

    public void setRates(double rates) {
        this.rates = rates;
    }
}
