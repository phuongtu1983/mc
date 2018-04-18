/// <summary>
/// Author : phuongtu
/// Created Date : 05/08/2009
/// </summary>
package com.venus.mc.contract;

public class ContractProcessFollowFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int conId;
    private String contractNumber;
    private String signDate;
    private String note;
    private double totalNotVAT;
    private double sumVAT;
    private double total;
    private String vendorName;
    private String deliveryDate;
    private String actualDeliveryDate;
    private String payment;
    private String invoice;
    private String invoiceDate;
    private String invoiceNote;
    private String moveCreateMrir;
    private String receiveMrir;
    private String moveCreateMsv;
    private String receiveMsv;
    private String paymentNumber;
    private String moveAccountingDate;
    private String volume;
    private String paymentStatus;
    private String contractPayment;
    private String assignEmp;
    private String requestNumber;
    private String project;
    private String softDocument;
    private String status;
    private String remainPayment;
    private String followEmp;
    private String paymentDate;
    private String currency;
    private String paymentTotal;
    private String paymentNote;

    //constructure region
    public ContractProcessFollowFormBean() {
    }

    //properties region
    public int getConId() {
        return this.conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public String getContractNumber() {
        return this.contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getPayment() {
        return this.payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getTotalNotVAT() {
        return totalNotVAT;
    }

    public void setTotalNotVAT(double totalNotVAT) {
        this.totalNotVAT = totalNotVAT;
    }

    public double getSumVAT() {
        return sumVAT;
    }

    public void setSumVAT(double sumVAT) {
        this.sumVAT = sumVAT;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getMoveCreateMrir() {
        return moveCreateMrir;
    }

    public void setMoveCreateMrir(String moveCreateMrir) {
        this.moveCreateMrir = moveCreateMrir;
    }

    public String getMoveCreateMsv() {
        return moveCreateMsv;
    }

    public void setMoveCreateMsv(String moveCreateMsv) {
        this.moveCreateMsv = moveCreateMsv;
    }

    public String getReceiveMrir() {
        return receiveMrir;
    }

    public void setReceiveMrir(String receiveMrir) {
        this.receiveMrir = receiveMrir;
    }

    public String getReceiveMsv() {
        return receiveMsv;
    }

    public void setReceiveMsv(String receiveMsv) {
        this.receiveMsv = receiveMsv;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNote() {
        return invoiceNote;
    }

    public void setInvoiceNote(String invoiceNote) {
        this.invoiceNote = invoiceNote;
    }

    public String getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(String actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getMoveAccountingDate() {
        return moveAccountingDate;
    }

    public void setMoveAccountingDate(String moveAccountingDate) {
        this.moveAccountingDate = moveAccountingDate;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getContractPayment() {
        return contractPayment;
    }

    public void setContractPayment(String contractPayment) {
        this.contractPayment = contractPayment;
    }

    public String getAssignEmp() {
        return assignEmp;
    }

    public void setAssignEmp(String assignEmp) {
        this.assignEmp = assignEmp;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getSoftDocument() {
        return softDocument;
    }

    public void setSoftDocument(String softDocument) {
        this.softDocument = softDocument;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemainPayment() {
        return remainPayment;
    }

    public void setRemainPayment(String remainPayment) {
        this.remainPayment = remainPayment;
    }

    public String getFollowEmp() {
        return followEmp;
    }

    public void setFollowEmp(String followEmp) {
        this.followEmp = followEmp;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentNote() {
        return paymentNote;
    }

    public void setPaymentNote(String paymentNote) {
        this.paymentNote = paymentNote;
    }

    public String getPaymentTotal() {
        return paymentTotal;
    }

    public void setPaymentTotal(String paymentTotal) {
        this.paymentTotal = paymentTotal;
    }
}
