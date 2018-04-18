/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class RequestBean {
    //fields region

    private int reqId; // primary key
    private int proId; // foreign key : reference to project(pro_id)
    private int orgId; // foreign key : reference to organization(org_id)
    private int createdEmp; // foreign key : reference to employee(emp_id)
    private int createdOrg; // foreign key : reference to organization(org_id)
    private int kind;
    private String requestNumber;
    private String createdDate;
    private String approveSuggest;
    private int statusSuggest;
    private String certificateRequire;
    private int whichUse;
    private String description;
    private String checkComment;
    private String storeComment;
    private String bomComment;
    private String plandepComment;
    private int checkApprove;
    private int storeApprove;
    private int bomApprove;
    private int plandepApprove;
    private String orgHandle;
    private String orgRefer;
    private String orgPaid;
    private String intermemoSubject;
    private String employeeName;
    private String organizationName;
    private String statusName;
    private String orgName;
    private String whichUseName;
    private String bomAgreeDate;
    private int assignedEmp;
    private String assignedEmpName;
    private String createdOrganizationName;
    private String materialKind;
    private String ros;
    private int approveEmp;
    private String projectNameEn;
    private int highlight;
    private int status;
    private String statusText;
    private boolean canDelete;
    private String purchaseOrg;
    private int storeOrg;

    //constructure region
    public RequestBean() {
    }

    public RequestBean(int reqId) {
        this.reqId = reqId;
    }

    public RequestBean(int reqId, int kind, String requestNumber, String createdDate, String approveSuggest, int statusSuggest, String certificateRequire, int whichUse, String description, String checkComment, String storeComment, String bomComment, String plandepComment, String orgHandle, String orgRefer, String orgPaid, String intermemoSubject, int checkApprove, int storeApprove, int bomApprove, int plandepApprove) {
        this.reqId = reqId;
        this.kind = kind;
        this.requestNumber = requestNumber;
        this.createdDate = createdDate;
        this.approveSuggest = approveSuggest;
        this.statusSuggest = statusSuggest;
        this.certificateRequire = certificateRequire;
        this.whichUse = whichUse;
        this.description = description;
        this.checkComment = checkComment;
        this.storeComment = storeComment;
        this.bomComment = bomComment;
        this.plandepComment = plandepComment;
        this.orgHandle = orgHandle;
        this.orgRefer = orgRefer;
        this.orgPaid = orgPaid;
        this.intermemoSubject = intermemoSubject;
        this.checkApprove = checkApprove;
        this.storeApprove = storeApprove;
        this.bomApprove = bomApprove;
        this.plandepApprove = plandepApprove;
    }

    //properties region
    public int getReqId() {
        return this.reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public void setHighlight(int highlight) {
        this.highlight = highlight;
    }

    public int getHighlight() {
        return highlight;
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

    public int getKind() {
        return this.kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getRequestNumber() {
        return this.requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getApproveSuggest() {
        return this.approveSuggest;
    }

    public void setApproveSuggest(String approveSuggest) {
        this.approveSuggest = approveSuggest;
    }

    public int getStatusSuggest() {
        return this.statusSuggest;
    }

    public void setStatusSuggest(int statusSuggest) {
        this.statusSuggest = statusSuggest;
    }

    public String getCertificateRequire() {
        return this.certificateRequire;
    }

    public void setCertificateRequire(String certificateRequire) {
        this.certificateRequire = certificateRequire;
    }

    public int getWhichUse() {
        return this.whichUse;
    }

    public void setWhichUse(int whichUse) {
        this.whichUse = whichUse;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCheckComment() {
        return this.checkComment;
    }

    public void setCheckComment(String checkComment) {
        this.checkComment = checkComment;
    }

    public String getStoreComment() {
        return this.storeComment;
    }

    public void setStoreComment(String storeComment) {
        this.storeComment = storeComment;
    }

    public String getBomComment() {
        return this.bomComment;
    }

    public void setBomComment(String bomComment) {
        this.bomComment = bomComment;
    }

    public String getPlandepComment() {
        return this.plandepComment;
    }

    public void setPlandepComment(String plandepComment) {
        this.plandepComment = plandepComment;
    }

    public String getOrgHandle() {
        return this.orgHandle;
    }

    public void setOrgHandle(String orgHandle) {
        this.orgHandle = orgHandle;
    }

    public String getOrgRefer() {
        return this.orgRefer;
    }

    public void setOrgRefer(String orgRefer) {
        this.orgRefer = orgRefer;
    }

    public String getOrgPaid() {
        return this.orgPaid;
    }

    public void setOrgPaid(String orgPaid) {
        this.orgPaid = orgPaid;
    }

    public String getIntermemoSubject() {
        return this.intermemoSubject;
    }

    public void setIntermemoSubject(String intermemoSubject) {
        this.intermemoSubject = intermemoSubject;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getWhichUseName() {
        return whichUseName;
    }

    public void setWhichUseName(String whichUseName) {
        this.whichUseName = whichUseName;
    }

    public int getCheckApprove() {
        return checkApprove;
    }

    public void setCheckApprove(int checkApprove) {
        this.checkApprove = checkApprove;
    }

    public int getBomApprove() {
        return bomApprove;
    }

    public void setBomApprove(int bomApprove) {
        this.bomApprove = bomApprove;
    }

    public int getPlandepApprove() {
        return plandepApprove;
    }

    public void setPlandepApprove(int plandepApprove) {
        this.plandepApprove = plandepApprove;
    }

    public int getStoreApprove() {
        return storeApprove;
    }

    public void setStoreApprove(int storeApprove) {
        this.storeApprove = storeApprove;
    }

    public int getCreatedOrg() {
        return createdOrg;
    }

    public void setCreatedOrg(int createdOrg) {
        this.createdOrg = createdOrg;
    }

    public String getBomAgreeDate() {
        return bomAgreeDate;
    }

    public void setBomAgreeDate(String bomAgreeDate) {
        this.bomAgreeDate = bomAgreeDate;
    }

    public int getAssignedEmp() {
        return assignedEmp;
    }

    public void setAssignedEmp(int assignedEmp) {
        this.assignedEmp = assignedEmp;
    }

    public String getCreatedOrganizationName() {
        return createdOrganizationName;
    }

    public void setCreatedOrganizationName(String createdOrganizationName) {
        this.createdOrganizationName = createdOrganizationName;
    }

    public String getMaterialKind() {
        return materialKind;
    }

    public void setMaterialKind(String materialKind) {
        this.materialKind = materialKind;
    }

    public String getRos() {
        return ros;
    }

    public void setRos(String ros) {
        this.ros = ros;
    }

    public int getApproveEmp() {
        return approveEmp;
    }

    public void setApproveEmp(int approveEmp) {
        this.approveEmp = approveEmp;
    }

    public String getAssignedEmpName() {
        return assignedEmpName;
    }

    public void setAssignedEmpName(String assignedEmpName) {
        this.assignedEmpName = assignedEmpName;
    }

    public String getProjectNameEn() {
        return projectNameEn;
    }

    public void setProjectNameEn(String projectNameEn) {
        this.projectNameEn = projectNameEn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public boolean getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    public String getPurchaseOrg() {
        return purchaseOrg;
    }

    public void setPurchaseOrg(String purchaseOrg) {
        this.purchaseOrg = purchaseOrg;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getStoreOrg() {
        return storeOrg;
    }

    public void setStoreOrg(int storeOrg) {
        this.storeOrg = storeOrg;
    }
    public static int REQUEST = 1;
    public static int INTERMEMO = 2;
    public static int STEP_REQ = 1;
    public static int STEP_TENDER = 2;
    public static int STEP_CONTRACT = 3;
    public static int STEP_ORDER = 4;
    public static int STEP_DELIVERYREQUEST = 5;
    public static int STEP_PRINCIPLE = 6;
    public static String RQ_XL = "xl";
    public static String RQ_TK = "tk";
    public static String RQ_TT = "tt";
    public static int STATUS_CREATE = 10;//moi tao
    public static int STATUS_STORE = 20;//da chuyen toi kho
    public static int STATUS_CHECK = 30;//da chuyen toi kiem tra
    public static int STATUS_PLANDEP = 40;//da chuyen toi phong ke hoach
    public static int STATUS_MANAGER = 50;//da chuyen toi giam doc
    public static int STATUS_HANDLE = 60;//da chuyen cho bo phan xu ly
    public static int STATUS_STORE_ACCEPT = 25;//kho da duyet
}
