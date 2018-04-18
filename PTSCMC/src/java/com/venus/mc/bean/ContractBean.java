/// <summary>
/// Author : phuongtu
/// Created Date : 05/08/2009
/// </summary>
package com.venus.mc.bean;

public class ContractBean {

    //fields region
    private int conId; // primary key
    private int tenId; // foreign key : reference to tender_plan(ten_id)
    private int venId; // foreign key : reference to vendor(ven_id)
    private int responseEmp; // foreign key : reference to employee(emp_id)
    private String status;
    private String contractNumber;
    private String createdDate;
    private String effectedDate;
    private String expireDate;
    private double subTotal;
    private double transport;
    private double otherFee;
    private double total;
    private String currency;
    private String warranty;
    private String deliveryDate;
    private String deliveryPlace;
    private String payment;
    private int paymentMode;
    private int paymentStatus;
    private int kind;
    private int parentId;
    private String note;
    private String employeeName;
    private String tenderNumber;
    private String vendorName;
    private String address;
    private String phone;
    private String fax;
    private String presenter;
    private String posPresenter;
    private String field;
    private String license;
    private double totalNotVAT;
    private double sumVAT;
    private double totalVAT1;
    private double totalVAT;
    private double sumVAT1;
    private double sumVAT0;
    private double money;
    private String moneyText;
    private String appendixNumber;
     private String appendixNo;
    private int isClosed;
    private String signDate;
    private String volume;
    private int createdEmp;
    private int followEmp;
    private int processStatus;
    private String processStatusText;
    private String packName;
    private String delivery;
    private String appendixContractNumber;
    private String certificate;
    private String deliveryNumber;
    private String appendixDate;
    private String softDocument;
    private String vendorName2;
    private String field2;
    private String license2;
    private String contractNumber1;
    private String vendorName1;
    private String field1;
    private String license1;
    private String contractNumber2;
    private String delivery1;
    private String delivery2;
    private String parentNumber;
    private int ownerId;
    private String orgName;
    private String abbreviate;
    //constructure region

    public ContractBean() {
    }

    public ContractBean(int conId) {
        this.conId = conId;
    }

    public ContractBean(int conId, int tenId, int venId, int responseEmp, String status, String contractNumber, String createdDate, String effectedDate, String expireDate, double subTotal, double transport, double otherFee, double total, String currency, String warranty, String deliveryDate, String deliveryPlace, String payment, int paymentMode, int paymentStatus, int kind, int parentId, String note, String employeeName, String tenderNumber, String vendorName, String address, String phone, String fax, String presenter, String posPresenter, String field, String license, double totalNotVAT, double sumVAT, double totalVAT1, double totalVAT, double sumVAT1, double sumVAT0, double money, String moneyText, String appendixNumber, String appendixNo, int isClosed, String signDate, String volume, int createdEmp, int followEmp, int processStatus, String processStatusText, String packName, String delivery, String appendixContractNumber, String certificate, String deliveryNumber, String appendixDate, String softDocument, String vendorName2, String field2, String license2, String contractNumber1, String vendorName1, String field1, String license1, String contractNumber2, String delivery1, String delivery2, String parentNumber, int ownerId, String orgName, String abbreviate) {
        this.conId = conId;
        this.tenId = tenId;
        this.venId = venId;
        this.responseEmp = responseEmp;
        this.status = status;
        this.contractNumber = contractNumber;
        this.createdDate = createdDate;
        this.effectedDate = effectedDate;
        this.expireDate = expireDate;
        this.subTotal = subTotal;
        this.transport = transport;
        this.otherFee = otherFee;
        this.total = total;
        this.currency = currency;
        this.warranty = warranty;
        this.deliveryDate = deliveryDate;
        this.deliveryPlace = deliveryPlace;
        this.payment = payment;
        this.paymentMode = paymentMode;
        this.paymentStatus = paymentStatus;
        this.kind = kind;
        this.parentId = parentId;
        this.note = note;
        this.employeeName = employeeName;
        this.tenderNumber = tenderNumber;
        this.vendorName = vendorName;
        this.address = address;
        this.phone = phone;
        this.fax = fax;
        this.presenter = presenter;
        this.posPresenter = posPresenter;
        this.field = field;
        this.license = license;
        this.totalNotVAT = totalNotVAT;
        this.sumVAT = sumVAT;
        this.totalVAT1 = totalVAT1;
        this.totalVAT = totalVAT;
        this.sumVAT1 = sumVAT1;
        this.sumVAT0 = sumVAT0;
        this.money = money;
        this.moneyText = moneyText;
        this.appendixNumber = appendixNumber;
        this.appendixNo = appendixNo;
        this.isClosed = isClosed;
        this.signDate = signDate;
        this.volume = volume;
        this.createdEmp = createdEmp;
        this.followEmp = followEmp;
        this.processStatus = processStatus;
        this.processStatusText = processStatusText;
        this.packName = packName;
        this.delivery = delivery;
        this.appendixContractNumber = appendixContractNumber;
        this.certificate = certificate;
        this.deliveryNumber = deliveryNumber;
        this.appendixDate = appendixDate;
        this.softDocument = softDocument;
        this.vendorName2 = vendorName2;
        this.field2 = field2;
        this.license2 = license2;
        this.contractNumber1 = contractNumber1;
        this.vendorName1 = vendorName1;
        this.field1 = field1;
        this.license1 = license1;
        this.contractNumber2 = contractNumber2;
        this.delivery1 = delivery1;
        this.delivery2 = delivery2;
        this.parentNumber = parentNumber;
        this.ownerId = ownerId;
        this.orgName = orgName;
        this.abbreviate = abbreviate;
    }

    

    public void setAbbreviate(String abbreviate) {
        this.abbreviate = abbreviate;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getAbbreviate() {
        return abbreviate;
    }

    

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    //properties region
    public int getConId() {
        return this.conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public void setParentNumber(String parentNumber) {
        this.parentNumber = parentNumber;
    }

    public String getParentNumber() {
        return parentNumber;
    }

    public void setMoneyText(String moneyText) {
        this.moneyText = moneyText;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getMoneyText() {
        return moneyText;
    }

    public double getMoney() {
        return money;
    }

    public void setSumVAT0(double sumVAT0) {
        this.sumVAT0 = sumVAT0;
    }

    public double getSumVAT0() {
        return sumVAT0;
    }

    public void setTotalVAT(double totalVAT) {
        this.totalVAT = totalVAT;
    }

    public void setTotalVAT1(double totalVAT1) {
        this.totalVAT1 = totalVAT1;
    }

    public void setSumVAT1(double sumVAT1) {
        this.sumVAT1 = sumVAT1;
    }

    public double getTotalVAT1() {
        return totalVAT1;
    }

    public double getTotalVAT() {
        return totalVAT;
    }

    public double getSumVAT1() {
        return sumVAT1;
    }

    public void setAppendixDate(String appendixDate) {
        this.appendixDate = appendixDate;
    }

    public String getAppendixDate() {
        return appendixDate;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public int getTenId() {
        return this.tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public int getVenId() {
        return this.venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public int getResponseEmp() {
        return this.responseEmp;
    }

    public void setResponseEmp(int responseEmp) {
        this.responseEmp = responseEmp;
    }

    public String getContractNumber() {
        return this.contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getEffectedDate() {
        return this.effectedDate;
    }

    public void setEffectedDate(String effectedDate) {
        this.effectedDate = effectedDate;
    }

    public String getExpireDate() {
        return this.expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public double getSubTotal() {
        return this.subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTransport() {
        return this.transport;
    }

    public void setTransport(double transport) {
        this.transport = transport;
    }

    public double getOtherFee() {
        return this.otherFee;
    }

    public void setOtherFee(double otherFee) {
        this.otherFee = otherFee;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getWarranty() {
        return this.warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryPlace() {
        return this.deliveryPlace;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    public String getPayment() {
        return this.payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public int getPaymentMode() {
        return this.paymentMode;
    }

    public void setPaymentMode(int paymentMode) {
        this.paymentMode = paymentMode;
    }

    public int getPaymentStatus() {
        return this.paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getKind() {
        return this.kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getTenderNumber() {
        return tenderNumber;
    }

    public void setTenderNumber(String tenderNumber) {
        this.tenderNumber = tenderNumber;
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

    public String getAddress() {
        return address;
    }

    public String getFax() {
        return fax;
    }

    public String getPhone() {
        return phone;
    }

    public String getPosPresenter() {
        return posPresenter;
    }

    public String getPresenter() {
        return presenter;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPosPresenter(String posPresenter) {
        this.posPresenter = posPresenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public String getField() {
        return field;
    }

    public String getLicense() {
        return license;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setLicense(String license) {
        this.license = license;
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

    public String getAppendixNumber() {
        return appendixNumber;
    }

    public void setAppendixNumber(String appendixNumber) {
        this.appendixNumber = appendixNumber;
    }

    public int getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(int isClosed) {
        this.isClosed = isClosed;
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

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFollowEmp() {
        return followEmp;
    }

    public void setFollowEmp(int followEmp) {
        this.followEmp = followEmp;
    }

    public int getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(int processStatus) {
        this.processStatus = processStatus;
    }

    public String getProcessStatusText() {
        return processStatusText;
    }

    public void setProcessStatusText(String processStatusText) {
        this.processStatusText = processStatusText;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getAppendixContractNumber() {
        return appendixContractNumber;
    }

    public void setAppendixContractNumber(String appendixContractNumber) {
        this.appendixContractNumber = appendixContractNumber;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getSoftDocument() {
        return softDocument;
    }

    public void setSoftDocument(String softDocument) {
        this.softDocument = softDocument;
    }

    public String getVendorName2() {
        return vendorName2;
    }

    public String getField2() {
        return field2;
    }

    public String getLicense2() {
        return license2;
    }

    public void setVendorName2(String vendorName2) {
        this.vendorName2 = vendorName2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public void setLicense2(String license2) {
        this.license2 = license2;
    }

    public String getContractNumber1() {
        return contractNumber1;
    }

    public void setContractNumber1(String contractNumber1) {
        this.contractNumber1 = contractNumber1;
    }

    public String getContractNumber2() {
        return contractNumber2;
    }

    public void setContractNumber2(String contractNumber2) {
        this.contractNumber2 = contractNumber2;
    }

    public String getField1() {
        return field1;
    }

    public String getLicense1() {
        return license1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public void setLicense1(String license1) {
        this.license1 = license1;
    }

    public void setVendorName1(String vendorName1) {
        this.vendorName1 = vendorName1;
    }

    public String getVendorName1() {
        return vendorName1;
    }

    public String getDelivery1() {
        return delivery1;
    }

    public String getDelivery2() {
        return delivery2;
    }

    public void setDelivery1(String delivery1) {
        this.delivery1 = delivery1;
    }

    public void setDelivery2(String delivery2) {
        this.delivery2 = delivery2;
    }

    public String getAppendixNo() {
        return appendixNo;
    }

    public void setAppendixNo(String appendixNo) {
        this.appendixNo = appendixNo;
    }
    
    public static int KIND_CONTRACT = 1;
    public static int KIND_PRINCIPLE = 2;
    public static int KIND_ORDER = 3;
    public static int KIND_APPENDIX = 4;
    public static int KIND_DELIVERY_REQUEST = 5;
    public static int KIND_ADJUSTMENT = 8;
    public static int STATUS_OPENED = 0;
    public static int STATUS_CLOSED = 1;
}
