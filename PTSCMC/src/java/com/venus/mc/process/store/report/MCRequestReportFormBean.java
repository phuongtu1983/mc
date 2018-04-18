/// <summary>
/// Author : phuongtu
/// Created Date : 21/09/2009
/// </summary>
package com.venus.mc.process.store.report;

public class MCRequestReportFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int id;
    private String requestNumber;
    private String projectName;
    private String organization;
    private String matCode;
    private String matName;
    private String requestDate;
    private String requestDeliveryDate;
    private String matUnit;
    private double quantity;
    private String tenderplanNumber;
    private String contractNumber;
    private String contractDate;
    private String vendor;
    private String mrirDate;
    private String mrirNumber;
    private String msvNumber;
    private String mivNumber;
    private double currentQuantity;
    private String itemNo;
    private String matGrade;
    private String matType;
    private String matSize1;
    private String matSize2;
    private String matSize3;
    private String manufacturer;
    private String heatNo;
    private String millNo;
    private String traceNo;
    private String colorCode;
    private String msvDate;
    private String rfmNumber;
    private double rfmQuantity;
    private String requester;
    private double mivQuantity;
    private String mivDate;
    private double balance;
    private double returnWorkShop;
    private String matNote;
    private String location;
    private double conQuantity;
    private String unitPrice;
    private String no;
    private String msvmiv;
    private String msvmivDate;
    private String procurement;
    private String totalPrice;
    private String timeUse;

    //constructure region
    public MCRequestReportFormBean() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestDeliveryDate() {
        return requestDeliveryDate;
    }

    public void setRequestDeliveryDate(String requestDeliveryDate) {
        this.requestDeliveryDate = requestDeliveryDate;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getTenderplanNumber() {
        return tenderplanNumber;
    }

    public void setTenderplanNumber(String tenderplanNumber) {
        this.tenderplanNumber = tenderplanNumber;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(double currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
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

    public String getMatUnit() {
        return matUnit;
    }

    public void setMatUnit(String matUnit) {
        this.matUnit = matUnit;
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

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getMivNumber() {
        return mivNumber;
    }

    public void setMivNumber(String mivNumber) {
        this.mivNumber = mivNumber;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getHeatNo() {
        return heatNo;
    }

    public void setHeatNo(String heatNo) {
        this.heatNo = heatNo;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMatGrade() {
        return matGrade;
    }

    public void setMatGrade(String matGrade) {
        this.matGrade = matGrade;
    }

    public String getMatSize1() {
        return matSize1;
    }

    public void setMatSize1(String matSize1) {
        this.matSize1 = matSize1;
    }

    public String getMatSize2() {
        return matSize2;
    }

    public void setMatSize2(String matSize2) {
        this.matSize2 = matSize2;
    }

    public String getMatSize3() {
        return matSize3;
    }

    public void setMatSize3(String matSize3) {
        this.matSize3 = matSize3;
    }

    public String getMatType() {
        return matType;
    }

    public void setMatType(String matType) {
        this.matType = matType;
    }

    public String getMillNo() {
        return millNo;
    }

    public void setMillNo(String millNo) {
        this.millNo = millNo;
    }

    public String getMivDate() {
        return mivDate;
    }

    public void setMivDate(String mivDate) {
        this.mivDate = mivDate;
    }

    public double getMivQuantity() {
        return mivQuantity;
    }

    public void setMivQuantity(double mivQuantity) {
        this.mivQuantity = mivQuantity;
    }

    public String getMsvDate() {
        return msvDate;
    }

    public void setMsvDate(String msvDate) {
        this.msvDate = msvDate;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getRfmNumber() {
        return rfmNumber;
    }

    public void setRfmNumber(String rfmNumber) {
        this.rfmNumber = rfmNumber;
    }

    public double getRfmQuantity() {
        return rfmQuantity;
    }

    public void setRfmQuantity(double rfmQuantity) {
        this.rfmQuantity = rfmQuantity;
    }

    public String getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    public double getReturnWorkShop() {
        return returnWorkShop;
    }

    public void setReturnWorkShop(double returnWorkShop) {
        this.returnWorkShop = returnWorkShop;
    }

    public String getMatNote() {
        return matNote;
    }

    public void setMatNote(String matNote) {
        this.matNote = matNote;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getConQuantity() {
        return conQuantity;
    }

    public void setConQuantity(double conQuantity) {
        this.conQuantity = conQuantity;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getMsvmiv() {
        return msvmiv;
    }

    public void setMsvmiv(String msvmiv) {
        this.msvmiv = msvmiv;
    }

    public String getMsvmivDate() {
        return msvmivDate;
    }

    public void setMsvmivDate(String msvmivDate) {
        this.msvmivDate = msvmivDate;
    }

    public String getProcurement() {
        return procurement;
    }

    public void setProcurement(String procurement) {
        this.procurement = procurement;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTimeUse() {
        return timeUse;
    }

    public void setTimeUse(String timeUse) {
        this.timeUse = timeUse;
    }
}
