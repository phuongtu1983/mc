/// <summary>
/// Author : phuongtu
/// Created Date : 04/11/2009
/// </summary>
package com.venus.mc.invoice;

import com.venus.mc.bean.InvoiceContractBean;

public class InvoiceFormBean extends org.apache.struts.action.ActionForm {

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
    private String[] conDetId;
    private String[] reqDetId;
    private String[] matId;
    private String[] unit;
    private String[] quantity;
    private String[] vat;
    private String[] price;
    private String[] detTotal;
    private double sumVAT;
    private String contractPaymentDate;
    private int status;
    private String receiveDate;
    private double otherAmount;
    private double transportAmount;
    private double rates;

    //constructure region
    public InvoiceFormBean() {
    }

    public InvoiceFormBean(InvoiceContractBean bean) {
        this.icId = bean.getIcId();
        this.conId = bean.getConId();
        this.payId = bean.getPayId();
        this.invoice = bean.getInvoice();
        this.invoiceDate = bean.getInvoiceDate();
        this.paymentDate = bean.getPaymentDate();
        this.kind = bean.getKind();
        this.note = bean.getNote();
        this.transportAmount = bean.getTransportAmount();
        this.otherAmount = bean.getOtherAmount();

    }

    //properties region
    public int getIcId() {
        return icId;
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

    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getKind() {
        return this.kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String[] getConDetId() {
        return conDetId;
    }

    public void setConDetId(String[] conDetId) {
        this.conDetId = conDetId;
    }

    public String[] getMatId() {
        return matId;
    }

    public void setMatId(String[] matId) {
        this.matId = matId;
    }

    public String[] getPrice() {
        return price;
    }

    public void setPrice(String[] price) {
        this.price = price;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    public String[] getUnit() {
        return unit;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    public String[] getVat() {
        return vat;
    }

    public void setVat(String[] vat) {
        this.vat = vat;
    }

    public String[] getDetTotal() {
        return detTotal;
    }

    public void setDetTotal(String[] detTotal) {
        this.detTotal = detTotal;
    }

    public String[] getReqDetId() {
        return reqDetId;
    }

    public void setReqDetId(String[] reqDetId) {
        this.reqDetId = reqDetId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public double getOtherAmount() {
        return otherAmount;
    }

    public void setOtherAmount(double otherAmount) {
        this.otherAmount = otherAmount;
    }

    public double getRates() {
        return rates;
    }

    public void setRates(double rates) {
        this.rates = rates;
    }

    public double getTransportAmount() {
        return transportAmount;
    }

    public void setTransportAmount(double transportAmount) {
        this.transportAmount = transportAmount;
    }
}
