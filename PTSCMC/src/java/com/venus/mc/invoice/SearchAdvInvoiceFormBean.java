
/// <summary>
/// Author : phuongtu
/// Created Date : 05/08/2009
/// </summary>
package com.venus.mc.invoice;

public class SearchAdvInvoiceFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int icId;
    private String invoice;
    private String contractNumber;
    private String invoiceFromDate;
    private String invoiceToDate;
    private String paymentFromDate;
    private String paymentToDate;
    private double totalFrom;
    private double totalTo;

    //constructure region
    public SearchAdvInvoiceFormBean() {
    }

    //properties region
    public int getIcId() {
        return icId;
    }

    public void setIcId(int icId) {
        this.icId = icId;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getContractNumber() {
        return this.contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getInvoiceFromDate() {
        return invoiceFromDate;
    }

    public void setInvoiceFromDate(String invoiceFromDate) {
        this.invoiceFromDate = invoiceFromDate;
    }

    public String getInvoiceToDate() {
        return invoiceToDate;
    }

    public void setInvoiceToDate(String invoiceToDate) {
        this.invoiceToDate = invoiceToDate;
    }

    public String getPaymentFromDate() {
        return paymentFromDate;
    }

    public void setPaymentFromDate(String paymentFromDate) {
        this.paymentFromDate = paymentFromDate;
    }

    public String getPaymentToDate() {
        return paymentToDate;
    }

    public void setPaymentToDate(String paymentToDate) {
        this.paymentToDate = paymentToDate;
    }

    public double getTotalFrom() {
        return totalFrom;
    }

    public void setTotalFrom(double totalFrom) {
        this.totalFrom = totalFrom;
    }

    public double getTotalTo() {
        return totalTo;
    }

    public void setTotalTo(double totalTo) {
        this.totalTo = totalTo;
    }
}
