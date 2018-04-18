/// <summary>
/// Author : phuongtu
/// Created Date : 24/08/2009
/// </summary>
package com.venus.mc.deliveryrequest;

import com.venus.mc.bean.DeliveryRequestBean;

public class DeliveryRequestFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int drId;
    private int venId;
    private String deliveryNumber;
    private String requestNumber;
    private String createdDate;
    private String abbreviate;
    private String orgName;
    private double total;
    private String currency;
    private String deliveryDate;
    private String effectedDate;
    private String vendorName;
    private String note;
    private String[] delDetId;
    private String[] conDetId;
    private String[] matId;
    private String[] unit;
    private String[] quantity;
    private String[] vat;
    private String[] price;
    private String[] detTotal;
    private double totalNotVAT;
    private double sumVAT;
    private double transport;
    private double other_fee;
    private String conNumber;
    private String address;
    private String phone;
    private String fax;
    private String volume;
    private String[] conProId;
    private String[] proId;
    private String[] cost;
    private String[] conInvId;
    private String[] invoice;
    private String[] paymentDate;
    private String[] invoiceDate;
    private String[] invoiceNote;
    private String[] reqDetId;
    private int createdEmp;
    private String signDate;
    private String materialGroup;
    private String employeeName;
    private String followEmpName;
    private int responseEmp;
    private int followEmp;
    private int processStatus;
    private String processStatusText;
    private String processStatusString;
    private String delivery;
    private String certificate;
    private String day;
    private String month;
    private String year;
    private String textTotal;
    private String conDate;
//    private String[] moveCreateMrir;
//    private String[] receiveMrir;
//    private String[] moveCreateMsv;
//    private String[] receiveMsv;
//    private String[] detailNote;
//    private String[] detailDeliveryDate;

    //constructure region
    public DeliveryRequestFormBean() {
    }

    public DeliveryRequestFormBean(int drId) {
        this.drId = drId;
    }

    public DeliveryRequestFormBean(DeliveryRequestBean bean) {
        this.drId = bean.getDrId();
        this.deliveryNumber = bean.getDeliveryNumber();
        this.total = bean.getTotal();
        this.currency = bean.getCurrency();
        this.deliveryDate = bean.getDeliveryDate();
        this.createdDate = bean.getCreatedDate();
        this.note = bean.getNote();
        this.totalNotVAT = bean.getTotalNotVAT();
        this.sumVAT = bean.getSumVAT();
        this.volume = bean.getVolume();
        this.effectedDate = bean.getEffectedDate();
    }

    public DeliveryRequestFormBean(String phone, String address,String followEmpName,String day, String month, String year, String effectedDate, String abbreviate, String note, String orgName, String requestNumber, String conNumber, String vendorName, String currency, double totalNotVAT, double sumVAT, double total, String delivery, String certificate, String textTotal, String conDate, String deliveryNumber) {
         this.phone= phone; 
        this.address= address;
        this.followEmpName= followEmpName;
        this.day = day;
        this.month = month;
        this.year = year;
        this.abbreviate = abbreviate;
        this.effectedDate = effectedDate;
        this.note = note;
        this.orgName = orgName;
        this.requestNumber = requestNumber;
        this.conNumber = conNumber;
        this.vendorName = vendorName;
        this.currency = currency;
        this.totalNotVAT = totalNotVAT;
        this.sumVAT= sumVAT;
        this.total= total;
        this.delivery = delivery;
        this.certificate=certificate;
        this.textTotal = textTotal;
        this.conDate = conDate;
        this.deliveryNumber = deliveryNumber;
    }

    public void setConDate(String conDate) {
        this.conDate = conDate;
    }

    public String getConDate() {
        return conDate;
    }

    public void setTextTotal(String textTotal) {
        this.textTotal = textTotal;
    }

    public String getTextTotal() {
        return textTotal;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setAbbreviate(String abbreviate) {
        this.abbreviate = abbreviate;
    }

    public String getAbbreviate() {
        return abbreviate;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    //properties region
    public int getDrId() {
        return this.drId;
    }

    public void setDrId(int drId) {
        this.drId = drId;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getCertificate() {
        return certificate;
    }

    public int getVenId() {
        return this.venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public String getDeliveryNumber() {
        return this.deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getCreatedDate() {
        return createdDate;
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

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String[] getConDetId() {
        return conDetId;
    }

    public void setConDetId(String[] conDetId) {
        this.conDetId = conDetId;
    }

    public String[] getDelDetId() {
        return delDetId;
    }

    public void setDelDetId(String[] delDetId) {
        this.delDetId = delDetId;
    }

    public String[] getDetTotal() {
        return detTotal;
    }

    public void setDetTotal(String[] detTotal) {
        this.detTotal = detTotal;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getSumVAT() {
        return sumVAT;
    }

    public void setSumVAT(double sumVAT) {
        this.sumVAT = sumVAT;
    }

    public double getTotalNotVAT() {
        return totalNotVAT;
    }

    public void setTotalNotVAT(double totalNotVAT) {
        this.totalNotVAT = totalNotVAT;
    }

    public String getAddress() {
        return address;
    }

    public String getConNumber() {
        return conNumber;
    }

    public String getFax() {
        return fax;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setConNumber(String conNumber) {
        this.conNumber = conNumber;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String[] getConInvId() {
        return conInvId;
    }

    public void setConInvId(String[] conInvId) {
        this.conInvId = conInvId;
    }

    public String[] getConProId() {
        return conProId;
    }

    public void setConProId(String[] conProId) {
        this.conProId = conProId;
    }

    public String[] getCost() {
        return cost;
    }

    public void setCost(String[] cost) {
        this.cost = cost;
    }

//    public String[] getDetailDeliveryDate() {
//        return detailDeliveryDate;
//    }
//
//    public void setDetailDeliveryDate(String[] detailDeliveryDate) {
//        this.detailDeliveryDate = detailDeliveryDate;
//    }
//
//    public String[] getDetailNote() {
//        return detailNote;
//    }
//
//    public void setDetailNote(String[] detailNote) {
//        this.detailNote = detailNote;
//    }
    public String[] getInvoice() {
        return invoice;
    }

    public String[] getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoice(String[] invoice) {
        this.invoice = invoice;
    }

    public String[] getInvoiceNote() {
        return invoiceNote;
    }

    public void setInvoiceDate(String[] invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

//    public String[] getMoveCreateMrir() {
//        return moveCreateMrir;
//    }
    public void setInvoiceNote(String[] invoiceNote) {
        this.invoiceNote = invoiceNote;
    }

//    public String[] getMoveCreateMsv() {
//        return moveCreateMsv;
//    }
//    public void setMoveCreateMrir(String[] moveCreateMrir) {
//        this.moveCreateMrir = moveCreateMrir;
//    }
    public String[] getPaymentDate() {
        return paymentDate;
    }

//    public void setMoveCreateMsv(String[] moveCreateMsv) {
//        this.moveCreateMsv = moveCreateMsv;
//    }
    public String[] getProId() {
        return proId;
    }

    public void setPaymentDate(String[] paymentDate) {
        this.paymentDate = paymentDate;
    }

//    public String[] getReceiveMrir() {
//        return receiveMrir;
//    }
    public void setProId(String[] proId) {
        this.proId = proId;
    }

//    public String[] getReceiveMsv() {
//        return receiveMsv;
//    }
//    public void setReceiveMrir(String[] receiveMrir) {
//        this.receiveMrir = receiveMrir;
//    }
    public String getVolume() {
        return volume;
    }

//    public void setReceiveMsv(String[] receiveMsv) {
//        this.receiveMsv = receiveMsv;
//    }
    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String[] getReqDetId() {
        return reqDetId;
    }

    public void setReqDetId(String[] reqDetId) {
        this.reqDetId = reqDetId;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getMaterialGroup() {
        return materialGroup;
    }

    public void setMaterialGroup(String materialGroup) {
        this.materialGroup = materialGroup;
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

    public int getResponseEmp() {
        return responseEmp;
    }

    public void setResponseEmp(int responseEmp) {
        this.responseEmp = responseEmp;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getProcessStatusString() {
        return processStatusString;
    }

    public void setProcessStatusString(String processStatusString) {
        this.processStatusString = processStatusString;
    }

    public String getFollowEmpName() {
        return followEmpName;
    }

    public void setFollowEmpName(String followEmpName) {
        this.followEmpName = followEmpName;
    }

    public String getEffectedDate() {
        return effectedDate;
    }

    public void setEffectedDate(String effectedDate) {
        this.effectedDate = effectedDate;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public double getOther_fee() {
        return other_fee;
    }

    public void setOther_fee(double other_fee) {
        this.other_fee = other_fee;
    }

    public double getTransport() {
        return transport;
    }

    public void setTransport(double transport) {
        this.transport = transport;
    }
}
