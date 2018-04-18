/// <summary>
/// Author : phuongtu
/// Created Date : 05/08/2009
/// </summary>
package com.venus.mc.contract;

import com.venus.mc.bean.ContractBean;

public class ContractFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int conId;
    private int tenId;
    private int venId;
    private int responseEmp;
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
    private String orderMaterialSource;
    private String employeeName;
    private String tenderNumber;
    private String vendorName;
    private String parentNumber;
    private double totalNotVAT;
    private double sumVAT;
    private int isClosed;
    private String signDate;
    private String volume;
    private int createdEmp;
    private String[] conDetId;
    private String[] reqDetId;
    private String[] matId;
    private String[] unit;
    private String[] quantity;
    private String[] vat;
    private String[] price;
    private String[] detTotal;
    private String[] conProId;
    private String[] proId;
    private String[] cost;
    private String[] conInvId;
    private String[] invoice;
    private String[] paymentDate;
    private String[] invoiceDate;
    private String[] invoiceNote;
    private String[] amount;
    private String[] expire;
    private String[] matStatus;
    private int followEmp;
    private String followEmpName;
    private int processStatus;
    private String processStatusText;
    private int isNeedHighLight;
    private String processStatusString;
    private String delivery;
    private String appendixContractNumber;
    private String certificate;
    private String[] matIdTemp;
    private String[] materialName;
    private String[] confirm;
    private String deliveryNumber;
    private String payStatus;
    private int isPermissionPrice;
    private int followOrg;
    private int isNotResell;
    private String softDocument;
    private String totalText;
    private String materialGroup;
    private int canSave;
    private int ownerId;
    private int stt;
    private String content;
    private String color;
//    private String[] moveCreateMrir;
//    private String[] receiveMrir;
//    private String[] moveCreateMsv;
//    private String[] receiveMsv;
//    private String[] detailNote;
//    private String[] detailDeliveryDate;
    //constructure region

    public ContractFormBean() {
    }

    public ContractFormBean(ContractBean bean) {
        this.conId = bean.getConId();
        this.tenId = bean.getTenId();
        this.venId = bean.getVenId();
        this.status = bean.getStatus();
        this.responseEmp = bean.getResponseEmp();
        this.contractNumber = bean.getContractNumber();
        this.createdDate = bean.getCreatedDate();
        this.effectedDate = bean.getEffectedDate();
        this.signDate = bean.getSignDate();
        this.expireDate = bean.getExpireDate();
        this.subTotal = bean.getSubTotal();
        this.transport = bean.getTransport();
        this.otherFee = bean.getOtherFee();
        this.total = bean.getTotal();
        this.currency = bean.getCurrency();
        this.warranty = bean.getWarranty();
        this.deliveryDate = bean.getDeliveryDate();
        this.deliveryPlace = bean.getDeliveryPlace();
        this.payment = bean.getPayment();
        this.paymentMode = bean.getPaymentMode();
        this.paymentStatus = bean.getPaymentStatus();
        this.kind = bean.getKind();
        this.employeeName = bean.getEmployeeName();
        this.tenderNumber = bean.getTenderNumber();
        this.vendorName = bean.getVendorName();
        this.parentId = bean.getParentId();
        this.note = bean.getNote();
        this.totalNotVAT = bean.getTotalNotVAT();
        this.sumVAT = bean.getSumVAT();
        this.isClosed = bean.getIsClosed();
        this.volume = bean.getVolume();
        this.createdEmp = bean.getCreatedEmp();
        this.followEmp = bean.getFollowEmp();
        this.ownerId = bean.getOwnerId();
        this.processStatus = bean.getProcessStatus();
        this.processStatusText = bean.getProcessStatusText();
        this.delivery = bean.getDelivery();
        this.appendixContractNumber = bean.getAppendixContractNumber();
        this.certificate = bean.getCertificate();
        this.deliveryNumber = bean.getDeliveryNumber();
        this.parentNumber = bean.getParentNumber();
        this.softDocument = bean.getSoftDocument();
    }

    public ContractFormBean(int stt, String content, double total) {
        this.stt = stt;
        this.content = content;
        this.total = total;
    }

    //properties region
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public int getStt() {
        return stt;
    }

    public int getConId() {
        return this.conId;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setConfirm(String[] confirm) {
        this.confirm = confirm;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setMatIdTemp(String[] matIdTemp) {
        this.matIdTemp = matIdTemp;
    }

    public String[] getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String[] materialName) {
        this.materialName = materialName;
    }

    public String[] getMatIdTemp() {
        return matIdTemp;
    }

    public String[] getConfirm() {
        return confirm;
    }

    public void setConId(int conId) {
        this.conId = conId;
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

    public String getOrderMaterialSource() {
        return orderMaterialSource;
    }

    public void setOrderMaterialSource(String orderMaterialSource) {
        this.orderMaterialSource = orderMaterialSource;
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

    public String getParentNumber() {
        return parentNumber;
    }

    public void setParentNumber(String parentNumber) {
        this.parentNumber = parentNumber;
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

    public String[] getCost() {
        return cost;
    }

    public void setCost(String[] cost) {
        this.cost = cost;
    }

    public String[] getInvoice() {
        return invoice;
    }

    public void setInvoice(String[] invoice) {
        this.invoice = invoice;
    }

    public String[] getProId() {
        return proId;
    }

    public void setProId(String[] proId) {
        this.proId = proId;
    }

    public String[] getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String[] paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String[] getConProId() {
        return conProId;
    }

    public void setConProId(String[] conProId) {
        this.conProId = conProId;
    }

    public String[] getConInvId() {
        return conInvId;
    }

    public void setConInvId(String[] conInvId) {
        this.conInvId = conInvId;
    }

//    public String[] getDetailNote() {
//        return detailNote;
//    }
//
//    public void setDetailNote(String[] detailNote) {
//        this.detailNote = detailNote;
//    }
//
//    public String[] getMoveCreateMrir() {
//        return moveCreateMrir;
//    }
//
//    public void setMoveCreateMrir(String[] moveCreateMrir) {
//        this.moveCreateMrir = moveCreateMrir;
//    }
//
//    public String[] getMoveCreateMsv() {
//        return moveCreateMsv;
//    }
//
//    public void setMoveCreateMsv(String[] moveCreateMsv) {
//        this.moveCreateMsv = moveCreateMsv;
//    }
//
//    public String[] getReceiveMrir() {
//        return receiveMrir;
//    }
//
//    public void setReceiveMrir(String[] receiveMrir) {
//        this.receiveMrir = receiveMrir;
//    }
//
//    public String[] getReceiveMsv() {
//        return receiveMsv;
//    }
//
//    public void setReceiveMsv(String[] receiveMsv) {
//        this.receiveMsv = receiveMsv;
//    }
//
//    public String[] getDetailDeliveryDate() {
//        return detailDeliveryDate;
//    }
//
//    public void setDetailDeliveryDate(String[] detailDeliveryDate) {
//        this.detailDeliveryDate = detailDeliveryDate;
//    }
    public String[] getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String[] invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String[] getInvoiceNote() {
        return invoiceNote;
    }

    public void setInvoiceNote(String[] invoiceNote) {
        this.invoiceNote = invoiceNote;
    }

    public String[] getAmount() {
        return amount;
    }

    public void setAmount(String[] amount) {
        this.amount = amount;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public String[] getExpire() {
        return expire;
    }

    public void setExpire(String[] expire) {
        this.expire = expire;
    }

    public int getFollowEmp() {
        return followEmp;
    }

    public void setFollowEmp(int followEmp) {
        this.followEmp = followEmp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFollowEmpName() {
        return followEmpName;
    }

    public void setFollowEmpName(String followEmpName) {
        this.followEmpName = followEmpName;
    }

    public String[] getMatStatus() {
        return matStatus;
    }

    public void setMatStatus(String[] matStatus) {
        this.matStatus = matStatus;
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

    public int getIsNeedHighLight() {
        return isNeedHighLight;
    }

    public void setIsNeedHighLight(int isNeedHighLight) {
        this.isNeedHighLight = isNeedHighLight;
    }

    public String getProcessStatusString() {
        return processStatusString;
    }

    public void setProcessStatusString(String processStatusString) {
        this.processStatusString = processStatusString;
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

    public int getIsPermissionPrice() {
        return isPermissionPrice;
    }

    public void setIsPermissionPrice(int isPermissionPrice) {
        this.isPermissionPrice = isPermissionPrice;
    }

    public int getFollowOrg() {
        return followOrg;
    }

    public void setFollowOrg(int followOrg) {
        this.followOrg = followOrg;
    }

    public int getIsNotResell() {
        return isNotResell;
    }

    public void setIsNotResell(int isNotResell) {
        this.isNotResell = isNotResell;
    }

    public String getSoftDocument() {
        return softDocument;
    }

    public void setSoftDocument(String softDocument) {
        this.softDocument = softDocument;
    }

    public String getTotalText() {
        return totalText;
    }

    public void setTotalText(String totalText) {
        this.totalText = totalText;
    }

    public String getMaterialGroup() {
        return materialGroup;
    }

    public void setMaterialGroup(String materialGroup) {
        this.materialGroup = materialGroup;
    }

    public int getCanSave() {
        return canSave;
    }

    public void setCanSave(int canSave) {
        this.canSave = canSave;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public static int STATUS_APPROVED = 1;
    public static int STATUS_APPROVING = 2;
    public static int STATUS_PROCESSING = 3;
    public static int PAYMENT_CASH = 1;
    public static int PAYMENT_TRANSFER = 2;
    public static int MATERIAL_STATUS_NORMAL = 1;
    public static int MATERIAL_STATUS_CANCEL = 2;
    public static int PROCESS_STATUS_NOTDELIVER = 1;
    public static int PROCESS_STATUS_NOTENOUGH = 2;
    public static int PROCESS_STATUS_DELIVERIED = 3;
}
