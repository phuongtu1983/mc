/// <summary>
/// Author : phuongtu
/// Created Date : 25/09/2009
/// </summary>
package com.venus.mc.contract;

public class InvoiceContractFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int icId;
    private int conId;
    private String invoice;
    private String paymentDate;
    private String invoiceDate;
    private double amount;
    private String note;
    private String contractNumber;
    private int createdEmp;
    private int payId;
    private double sumVAT;
    private String contractPaymentDate;
    private int status;
    private String statusName;
    private String restDay;
    private String receiveDate;
    private double otherAmount;
    private double transportAmount;
    private String responseEmp;
    private String amountString;
    private String vendor;
    private String currency;
    private double rates;
    private String contractKind;
    private int highlight;
    private int detId;
    //constructure region

    public InvoiceContractFormBean() {
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getRestDay() {
        return restDay;
    }

    public void setRestDay(String restDay) {
        this.restDay = restDay;
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

    public String getResponseEmp() {
        return responseEmp;
    }

    public void setResponseEmp(String responseEmp) {
        this.responseEmp = responseEmp;
    }

    public String getAmountString() {
        return amountString;
    }

    public void setAmountString(String amountString) {
        this.amountString = amountString;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
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

    public String getContractKind() {
        return contractKind;
    }

    public void setContractKind(String contractKind) {
        this.contractKind = contractKind;
    }

    public int getHighlight() {
        return highlight;
    }

    public void setHighlight(int highlight) {
        this.highlight = highlight;
    }

    public int getDetId() {
        return detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public double getTransportAmount() {
        return transportAmount;
    }

    public void setTransportAmount(double transportAmount) {
        this.transportAmount = transportAmount;
    }
    public static int STATUS_NOTPAYMENT = 1;
    public static int STATUS_PAYMENTED = 2;
    public static int STATUS_ACCOUNT_TRANSFERED = 3;
}
