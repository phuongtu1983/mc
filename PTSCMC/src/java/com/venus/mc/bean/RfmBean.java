/// <summary>
/// Author : kngo
/// Created Date : 22/09/2009
/// </summary>
package com.venus.mc.bean;

public class RfmBean {

    //fields region
    private int rfmId; // primary key
    private String createdDate;
    private String rfmNumber;
    private int creator; // foreign key : reference to employee(emp_id)
    private int proId; // foreign key : reference to project(pro_id)
    private int orgId; // foreign key : reference to organization(org_id)
    private int requestOrg;
    private String deliveryDate;
    private String deliveryPlace;
    private int stoId; // foreign key : reference to store(sto_id)
    private int reqType;
    private String reqName;
    private int kind;
    private String forName;
    private String empName;
    private String orgName;
    private int receiveId;
    private int reqId;
    private String storeName;
    private String whichUseName;
    private String reqOrgName;
    private int createdEmp;
    private int storeApprove;
    private String storeComment;
    private int accountingApprove;
    private String accountingComment;
    private String purpose;
    private int statusReserveQuantity;
    private int Stt;
    private int orgHandle;
    private int canSave;

    //constructure region
    public RfmBean() {
    }

    public RfmBean(int rfmId) {
        this.rfmId = rfmId;
    }

    public RfmBean(int rfmId, String createdDate, String rfmNumber, int creator, int proId, int orgId, int requestOrg, String deliveryDate, String deliveryPlace, int stoId, int reqType, String reqName, int kind, String forName, String empName, String orgName, int receiveId, int reqId, String storeName, String whichUseName, String reqOrgName, int createdEmp, int storeApprove, String storeComment, int accountingApprove, String accountingComment, int statusReserveQuantity, String purpose) {
        this.rfmId = rfmId;
        this.createdDate = createdDate;
        this.rfmNumber = rfmNumber;
        this.creator = creator;
        this.proId = proId;
        this.orgId = orgId;
        this.requestOrg = requestOrg;
        this.deliveryDate = deliveryDate;
        this.deliveryPlace = deliveryPlace;
        this.stoId = stoId;
        this.reqType = reqType;
        this.reqName = reqName;
        this.kind = kind;
        this.forName = forName;
        this.empName = empName;
        this.orgName = orgName;
        this.receiveId = receiveId;
        this.reqId = reqId;
        this.storeName = storeName;
        this.whichUseName = whichUseName;
        this.reqOrgName = reqOrgName;
        this.createdEmp = createdEmp;
        this.storeApprove = storeApprove;
        this.storeComment = storeComment;
        this.accountingApprove = accountingApprove;
        this.accountingComment = accountingComment;
        this.statusReserveQuantity = statusReserveQuantity;
        this.purpose = purpose;
    }

    public void setCanSave(int canSave) {
        this.canSave = canSave;
    }

    public int getCanSave() {
        return canSave;
    }

    public int getOrgHandle() {
        return orgHandle;
    }

    public void setOrgHandle(int orgHandle) {
        this.orgHandle = orgHandle;
    }

    public void setStt(int Stt) {
        this.Stt = Stt;
    }

    public int getStt() {
        return Stt;
    }

    public int getStatusReserveQuantity() {
        return statusReserveQuantity;
    }

    public void setStatusReserveQuantity(int statusReserveQuantity) {
        this.statusReserveQuantity = statusReserveQuantity;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    //properties region
    public int getRfmId() {
        return this.rfmId;
    }

    public void setRfmId(int rfmId) {
        this.rfmId = rfmId;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getRfmNumber() {
        return this.rfmNumber;
    }

    public void setRfmNumber(String rfmNumber) {
        this.rfmNumber = rfmNumber;
    }

    public int getCreator() {
        return this.creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public int getProId() {
        return this.proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getOrgId() {
        return this.orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
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

    public int getStoId() {
        return this.stoId;
    }

    public void setStoId(int stoId) {
        this.stoId = stoId;
    }

    public int getReqType() {
        return this.reqType;
    }

    public void setReqType(int reqType) {
        this.reqType = reqType;
    }

    public int getKind() {
        return this.kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getReqName() {
        return reqName;
    }

    public void setReqName(String reqName) {
        this.reqName = reqName;
    }

    public String getForName() {
        return forName;
    }

    public void setForName(String forName) {
        this.forName = forName;
    }

    public String getEmpName() {
        return empName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(int receiveId) {
        this.receiveId = receiveId;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getWhichUseName() {
        return whichUseName;
    }

    public void setWhichUseName(String whichUseName) {
        this.whichUseName = whichUseName;
    }

    public int getRequestOrg() {
        return requestOrg;
    }

    public void setRequestOrg(int requestOrg) {
        this.requestOrg = requestOrg;
    }

    public String getReqOrgName() {
        return reqOrgName;
    }

    public void setReqOrgName(String reqOrgName) {
        this.reqOrgName = reqOrgName;
    }

    public int getAccountingApprove() {
        return accountingApprove;
    }

    public String getAccountingComment() {
        return accountingComment;
    }

    public int getStoreApprove() {
        return storeApprove;
    }

    public String getStoreComment() {
        return storeComment;
    }

    public void setAccountingApprove(int accountingApprove) {
        this.accountingApprove = accountingApprove;
    }

    public void setAccountingComment(String accountingComment) {
        this.accountingComment = accountingComment;
    }

    public void setStoreApprove(int storeApprove) {
        this.storeApprove = storeApprove;
    }

    public void setStoreComment(String storeComment) {
        this.storeComment = storeComment;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    public static int NOT_MIV_STATUS = 1;
    public static int MIV_STATUS = 2;
    public static int RFM_KIND = 0;
    public static int ERFM_KIND = 1;
}
