/// <summary>
/// Author : phuongtu
/// Created Date : 21/09/2009
/// </summary>
package com.venus.mc.request;

public class RequestReportBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int id;
    private String requestNumber;
    private String requestDeliveryDate;
    private String provideDate;
    private String projectName;
    private String organization;
    private String description;
    private String matCode;
    private String matName;
    private String matUnit;
    private double quantity;
    private double price;
    private double total;
    private double vat;
    private double sum;
    private String invoice;
    private String invoiceDate;
    private String status;
    private String contractNumber;
    private String contractDate;
    private String vendor;
    private String deliveryDate;
    private String moveCreateMrir;
    private String mrirNumber;
    private String mrirDate;
    private String receiveMrir;
    private String moveCreateMsv;
    private String msvNumber;
    private String msvDate;
    private String receiveMsv;
    private String moveAccountingDate;
    private String paymentDate;
    private String assetVendor;
    private String volume;
    private String responseEmp;
    private String matGroup;
    private String requester;
    private String followEmp;
    private String no;
    private String poMatName;
    private String currency;
    private String contractDeliveryDate;
    private String finishOrderDate;
    private String lastProcessDate;
//    private String mrirQuantityText;
    private double mrirQuantity;
//    private String quantityText;
//    private String priceText;
//    private String totalText;
//    private String vatText;
//    private String sumText;
    //constructure region

    public RequestReportBean() {
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

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
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

    public String getMsvDate() {
        return msvDate;
    }

    public void setMsvDate(String msvDate) {
        this.msvDate = msvDate;
    }

    public String getAssetVendor() {
        return assetVendor;
    }

    public void setAssetVendor(String assetVendor) {
        this.assetVendor = assetVendor;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getMoveAccountingDate() {
        return moveAccountingDate;
    }

    public void setMoveAccountingDate(String moveAccountingDate) {
        this.moveAccountingDate = moveAccountingDate;
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

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
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

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProvideDate() {
        return provideDate;
    }

    public void setProvideDate(String provideDate) {
        this.provideDate = provideDate;
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

    public String getResponseEmp() {
        return responseEmp;
    }

    public void setResponseEmp(String responseEmp) {
        this.responseEmp = responseEmp;
    }

    public String getMatGroup() {
        return matGroup;
    }

    public void setMatGroup(String matGroup) {
        this.matGroup = matGroup;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getFollowEmp() {
        return followEmp;
    }

    public void setFollowEmp(String followEmp) {
        this.followEmp = followEmp;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPoMatName() {
        return poMatName;
    }

    public void setPoMatName(String poMatName) {
        this.poMatName = poMatName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getContractDeliveryDate() {
        return contractDeliveryDate;
    }

    public void setContractDeliveryDate(String contractDeliveryDate) {
        this.contractDeliveryDate = contractDeliveryDate;
    }

//    public String getMrirQuantityText() {
//        return mrirQuantityText;
//    }
//
//    public void setMrirQuantityText(String mrirQuantityText) {
//        this.mrirQuantityText = mrirQuantityText;
//    }
//    public String getPriceText() {
//        return priceText;
//    }
//
//    public void setPriceText(String priceText) {
//        this.priceText = priceText;
//    }
//
//    public String getQuantityText() {
//        return quantityText;
//    }
//
//    public void setQuantityText(String quantityText) {
//        this.quantityText = quantityText;
//    }
//
//    public String getSumText() {
//        return sumText;
//    }
//
//    public void setSumText(String sumText) {
//        this.sumText = sumText;
//    }
//
//    public String getTotalText() {
//        return totalText;
//    }
//
//    public void setTotalText(String totalText) {
//        this.totalText = totalText;
//    }
//
//    public String getVatText() {
//        return vatText;
//    }
//
//    public void setVatText(String vatText) {
//        this.vatText = vatText;
//    }
    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public double getMrirQuantity() {
        return mrirQuantity;
    }

    public void setMrirQuantity(double mrirQuantity) {
        this.mrirQuantity = mrirQuantity;
    }

    public String getFinishOrderDate() {
        return finishOrderDate;
    }

    public void setFinishOrderDate(String finishOrderDate) {
        this.finishOrderDate = finishOrderDate;
    }

    public String getLastProcessDate() {
        return lastProcessDate;
    }

    public void setLastProcessDate(String lastProcessDate) {
        this.lastProcessDate = lastProcessDate;
    }

}
