
/// <summary>
/// Author : phuongtu
/// Created Date : 21/09/2009
/// </summary>
package com.venus.mc.process.store.report;

public class MCReportFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int order;
    private String matCode;
    private String matName;
    private String matUnit;
    private String contractNumber;
    private String requestNumber;
    private double matPrice;
    private String mrirNumber;
    private String heatNo;
    private String mrirDate;
    private String msvNumber;
    private double msvQuantity;
    private String store;
    private String rfmNumber;
    private String mivNumber;
    private String usedOrg;
    private double usedQuantity;
    private String mivDate;
    private double balance;
    private double totalReceived;
    private double totalIssued;
    private double contractQuantity;
    private String certificate;
    private String matNote;
    private String location;

    //constructure region
    public MCReportFormBean() {
        super();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getHeatNo() {
        return heatNo;
    }

    public void setHeatNo(String heatNo) {
        this.heatNo = heatNo;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public double getMatPrice() {
        return matPrice;
    }

    public void setMatPrice(double matPrice) {
        this.matPrice = matPrice;
    }

    public String getMatUnit() {
        return matUnit;
    }

    public void setMatUnit(String matUnit) {
        this.matUnit = matUnit;
    }

    public String getMivDate() {
        return mivDate;
    }

    public void setMivDate(String mivDate) {
        this.mivDate = mivDate;
    }

    public String getMivNumber() {
        return mivNumber;
    }

    public void setMivNumber(String mivNumber) {
        this.mivNumber = mivNumber;
    }

    public String getMrirDate() {
        return mrirDate;
    }

    public void setMrirDate(String mrirDate) {
        this.mrirDate = mrirDate;
    }

    public String getMrirNumber() {
        return mrirNumber;
    }

    public void setMrirNumber(String mrirNumber) {
        this.mrirNumber = mrirNumber;
    }

    public String getMsvNumber() {
        return msvNumber;
    }

    public void setMsvNumber(String msvNumber) {
        this.msvNumber = msvNumber;
    }

    public double getMsvQuantity() {
        return msvQuantity;
    }

    public void setMsvQuantity(double msvQuantity) {
        this.msvQuantity = msvQuantity;
    }

    public double getTotalReceived() {
        return totalReceived;
    }

    public void setTotalReceived(double totalReceived) {
        this.totalReceived = totalReceived;
    }

    public double getTotalIssued() {
        return totalIssued;
    }

    public void setTotalIssued(double totalIssued) {
        this.totalIssued = totalIssued;
    }

    public String getStore() {
        return store;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getRfmNumber() {
        return rfmNumber;
    }

    public void setRfmNumber(String rfmNumber) {
        this.rfmNumber = rfmNumber;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getUsedOrg() {
        return usedOrg;
    }

    public void setUsedOrg(String usedOrg) {
        this.usedOrg = usedOrg;
    }

    public double getUsedQuantity() {
        return usedQuantity;
    }

    public void setUsedQuantity(double usedQuantity) {
        this.usedQuantity = usedQuantity;
    }

    public double getContractQuantity() {
        return contractQuantity;
    }

    public void setContractQuantity(double contractQuantity) {
        this.contractQuantity = contractQuantity;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getMatNote() {
        return matNote;
    }

    public void setMatNote(String matNote) {
        this.matNote = matNote;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
