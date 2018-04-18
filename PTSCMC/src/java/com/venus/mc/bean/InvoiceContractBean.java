/// <summary>
/// Author : phuongtu
/// Created Date : 25/09/2009
/// </summary>
package com.venus.mc.bean;

public class InvoiceContractBean {

    //fields region
    private int icId; // primary key
    private int conId; // foreign key : reference to contract(con_id)
    private int payId; // foreign key : reference to payment(pay_id)
    private String invoice;
    private String paymentDate;
    private String invoiceDate;
    private double amount;
    private String note;
    private int kind;
    private int createdEmp;
    private String contractNumber;
    private String parentNumber;
    private double sumVAT;
    private String contractPaymentDate;
    private int status;
    private String receiveDate;
    private double otherAmount;
    private double transportAmount;
    private int contractKind;
    private String currency;
    private double rates;
    private int detId;
    private String contractDate;

    //constructure region
    public InvoiceContractBean() {
    }

    public InvoiceContractBean(int icId) {
        this.icId = icId;
    }

    public InvoiceContractBean(int icId, String invoice, String paymentDate, String invoiceDate, String note, int kind) {
        this.icId = icId;
        this.invoice = invoice;
        this.paymentDate = paymentDate;
        this.invoiceDate = invoiceDate;
        this.note = note;
        this.kind = kind;

    }

    //properties region
    public int getIcId() {
        return this.icId;
    }

    public void setIcId(int icId) {
        this.icId = icId;
    }

    public int getConId() {
        return this.conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public String getInvoice() {
        return this.invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getParentNumber() {
        return parentNumber;
    }

    public void setParentNumber(String parentNumber) {
        this.parentNumber = parentNumber;
    }

    public String getContractPaymentDate() {
        return contractPaymentDate;
    }

    public void setContractPaymentDate(String contractPaymentDate) {
        this.contractPaymentDate = contractPaymentDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getSumVAT() {
        return sumVAT;
    }

    public void setSumVAT(double sumVAT) {
        this.sumVAT = sumVAT;
    }

    public double getOtherAmount() {
        return otherAmount;
    }

    public void setOtherAmount(double otherAmount) {
        this.otherAmount = otherAmount;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public int getContractKind() {
        return contractKind;
    }

    public void setContractKind(int contractKind) {
        this.contractKind = contractKind;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getRates() {
        return rates;
    }

    public void setRates(double rates) {
        this.rates = rates;
    }

    public int getDetId() {
        return detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public double getTransportAmount() {
        return transportAmount;
    }

    public void setTransportAmount(double transportAmount) {
        this.transportAmount = transportAmount;
    }
}
