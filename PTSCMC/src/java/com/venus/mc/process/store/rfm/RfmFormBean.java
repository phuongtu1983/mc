package com.venus.mc.process.store.rfm;

public class RfmFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int rfmId; // primary key
    private String createdDate;
    private int createdEmp;
    private String rfmNumber;
    private int creator; // foreign key : reference to employee(emp_id)
    private int proId; // foreign key : reference to project(pro_id)
    private int orgId; // foreign key : reference to organization(org_id)
    private String deliveryDate;
    private String deliveryPlace;
    private int stoId; // foreign key : reference to store(sto_id)
    private int reqType;
    private int kind;
    private int reqId;
    private int[] delDetId; // primary key
    private int[] detId; // primary key
    private int[] matId; // foreign key : reference to material(mat_id)
    private int[] msvId;
    private String[] msvNumber;
    private double[] quantity;
    private double[] qtTemp;
    private String[] unit;
    private String[] comment;
    private int[] reqDetailId;
    private String reqName;
    private String forName;
    private String stoName;
    private int receiveId;
    private Double[] price;
    private int requestOrg;
    private int storeApprove;
    private String storeComment;
    private int accountingApprove;
    private String accountingComment;
    private String orgName;
    private String empName;
    private String purpose;
    private boolean canDelete;
    private int orgHandle;

    //constructure region
    public RfmFormBean() {
    }

    public void setOrgHandle(int orgHandle) {
        this.orgHandle = orgHandle;
    }

    public int getOrgHandle() {
        return orgHandle;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public int[] getDelDetId() {
        return delDetId;
    }

    public void setDelDetId(int[] delDetId) {
        this.delDetId = delDetId;
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

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public int getReqId() {
        return reqId;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    public void setReqDetailId(int[] reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public void setQuantity(double[] quantity) {
        this.quantity = quantity;
    }

    public void setComment(String[] comment) {
        this.comment = comment;
    }

    public void setMsvNumber(String[] msvNumber) {
        this.msvNumber = msvNumber;
    }

    public void setMatId(int[] matId) {
        this.matId = matId;
    }

    public void setDetId(int[] detId) {
        this.detId = detId;
    }

    public String[] getUnit() {
        return unit;
    }

    public int[] getReqDetailId() {
        return reqDetailId;
    }

    public double[] getQuantity() {
        return quantity;
    }

    public String[] getMsvNumber() {
        return msvNumber;
    }

    public int[] getMatId() {
        return matId;
    }

    public int[] getDetId() {
        return detId;
    }

    public String[] getComment() {
        return comment;
    }

    public void setReqName(String reqName) {
        this.reqName = reqName;
    }

    public void setForName(String forName) {
        this.forName = forName;
    }

    public String getForName() {
        return forName;
    }

    public String getReqName() {
        return reqName;
    }

    public void setReceiveId(int receiveId) {
        this.receiveId = receiveId;
    }

    public int getReceiveId() {
        return receiveId;
    }

    public void setStoName(String stoName) {
        this.stoName = stoName;
    }

    public String getStoName() {
        return stoName;
    }

    public void setPrice(Double[] price) {
        this.price = price;
    }

    public Double[] getPrice() {
        return price;
    }

    public int getRequestOrg() {
        return requestOrg;
    }

    public void setRequestOrg(int requestOrg) {
        this.requestOrg = requestOrg;
    }

    public void setQtTemp(double[] qtTemp) {
        this.qtTemp = qtTemp;
    }

    public double[] getQtTemp() {
        return qtTemp;
    }

    public void setMsvId(int[] msvId) {
        this.msvId = msvId;
    }

    public int[] getMsvId() {
        return msvId;
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

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    public boolean getCanDelete() {
        return this.canDelete;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}