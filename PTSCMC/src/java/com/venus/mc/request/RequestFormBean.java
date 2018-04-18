/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.request;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.RequestBean;

/**
 *
 * @author phuongtu
 */
public class RequestFormBean extends org.apache.struts.action.ActionForm {
    //fields region

    private int reqId;
    private int proId;
    private int orgId;
    private int createdEmp;
    private int createdOrg;
    private String requestNumber;
    private String createdDate;
    private int statusSuggest;
    private String[] approveSuggest;
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
    private String[] orgHandle;
    private String[] orgRefer;
    private String[] orgPaid;
    private String employeeName;
    private String organizationName;
    private String[] reqDetId;
    private String[] matId;
    private String[] unit;
    private String[] presentQuantity;
    private String[] additionalQuantity;
    private String[] requestQuantity;
    private String[] checkAppro;
    private String[] stockAppro;
    private String[] plandepAppro;
    private String[] bomAppro;
    private String[] deliverDate;
    private String[] materialKind;
    private String[] empId;
    private int assignedEmp;
    private String bomAgreeDate;
    private int highlight;
    private int status;
    private String statusText;
    private int permission;
    private String purchaseOrg;
    private String purchaseOrgName;
    private int isAssignEmp;
    private int isReceiveRequest;
    private int isCreatedEmp;
    private int isMaterialNotCode;
    private int storeOrg;
    private int isShortCut;

    //constructure region
    public RequestFormBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public RequestFormBean(RequestBean bean) {
        this.reqId = bean.getReqId();
        this.proId = bean.getProId();
        this.orgId = bean.getOrgId();
        this.createdEmp = bean.getCreatedEmp();
        this.createdOrg = bean.getCreatedOrg();
        this.requestNumber = bean.getRequestNumber();
        this.createdDate = bean.getCreatedDate();
        this.statusSuggest = bean.getStatusSuggest();
        this.certificateRequire = bean.getCertificateRequire();
        this.whichUse = bean.getWhichUse();
        this.description = bean.getDescription();
        this.checkComment = bean.getCheckComment();
        this.storeComment = bean.getStoreComment();
        this.bomComment = bean.getBomComment();
        this.plandepComment = bean.getPlandepComment();
        this.employeeName = bean.getEmployeeName();
        this.organizationName = bean.getOrganizationName();
        this.checkApprove = bean.getCheckApprove();
        this.storeApprove = bean.getStoreApprove();
        this.bomApprove = bean.getBomApprove();
        this.bomAgreeDate = bean.getBomAgreeDate();
        this.plandepApprove = bean.getPlandepApprove();
        this.assignedEmp = bean.getAssignedEmp();
        this.storeOrg = bean.getStoreOrg();
        if (!GenericValidator.isBlankOrNull(bean.getApproveSuggest())) {
            this.approveSuggest = bean.getApproveSuggest().split(",");
        }
        if (!GenericValidator.isBlankOrNull(bean.getOrgHandle())) {
            this.orgHandle = bean.getOrgHandle().split(",");
        }
        if (!GenericValidator.isBlankOrNull(bean.getOrgRefer())) {
            this.orgRefer = bean.getOrgRefer().split(",");
        }
        if (!GenericValidator.isBlankOrNull(bean.getOrgPaid())) {
            this.orgPaid = bean.getOrgPaid().split(",");
        }
        this.status = bean.getStatus();
        this.statusText = bean.getStatusText();
//        this.purchaseOrg = bean.getPurchaseOrg();
        this.highlight = bean.getHighlight();
    }

    //properties region
    public int getReqId() {
        return this.reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public int getProId() {
        return this.proId;
    }

    public void setHighlight(int highlight) {
        this.highlight = highlight;
    }

    public int getHighlight() {
        return highlight;
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

    public String[] getApproveSuggest() {
        return approveSuggest;
    }

    public void setApproveSuggest(String[] approveSuggest) {
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

    public String[] getOrgHandle() {
        return this.orgHandle;
    }

    public void setOrgHandle(String[] orgHandle) {
        this.orgHandle = orgHandle;
    }

    public String[] getOrgRefer() {
        return this.orgRefer;
    }

    public void setOrgRefer(String[] orgRefer) {
        this.orgRefer = orgRefer;
    }

    public String[] getOrgPaid() {
        return this.orgPaid;
    }

    public void setOrgPaid(String[] orgPaid) {
        this.orgPaid = orgPaid;
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

    public String[] getCheckAppro() {
        return checkAppro;
    }

    public void setCheckAppro(String[] checkAppro) {
        this.checkAppro = checkAppro;
    }

    public String[] getMatId() {
        return matId;
    }

    public void setMatId(String[] matId) {
        this.matId = matId;
    }

    public String[] getReqDetId() {
        return reqDetId;
    }

    public void setReqDetId(String[] reqDetId) {
        this.reqDetId = reqDetId;
    }

    public String[] getUnit() {
        return unit;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
    }

    public String[] getPresentQuantity() {
        return presentQuantity;
    }

    public void setPresentQuantity(String[] presentQuantity) {
        this.presentQuantity = presentQuantity;
    }

    public String[] getAdditionalQuantity() {
        return additionalQuantity;
    }

    public void setAdditionalQuantity(String[] additionalQuantity) {
        this.additionalQuantity = additionalQuantity;
    }

    public String[] getRequestQuantity() {
        return requestQuantity;
    }

    public void setRequestQuantity(String[] requestQuantity) {
        this.requestQuantity = requestQuantity;
    }

    public String[] getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(String[] deliverDate) {
        this.deliverDate = deliverDate;
    }

    public String[] getStockAppro() {
        return stockAppro;
    }

    public void setStockAppro(String[] stockAppro) {
        this.stockAppro = stockAppro;
    }

    public String[] getPlandepAppro() {
        return plandepAppro;
    }

    public void setPlandepAppro(String[] plandepAppro) {
        this.plandepAppro = plandepAppro;
    }

    public String[] getBomAppro() {
        return bomAppro;
    }

    public void setBomAppro(String[] bomAppro) {
        this.bomAppro = bomAppro;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
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

    public void setBomAgreeDate(String bomAgreeDate) {
        this.bomAgreeDate = bomAgreeDate;
    }

    public String getBomAgreeDate() {
        return bomAgreeDate;
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

    public String[] getMaterialKind() {
        return materialKind;
    }

    public void setMaterialKind(String[] materialKind) {
        this.materialKind = materialKind;
    }

    public int getAssignedEmp() {
        return assignedEmp;
    }

    public void setAssignedEmp(int assignedEmp) {
        this.assignedEmp = assignedEmp;
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

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public String getPurchaseOrg() {
        return purchaseOrg;
    }

    public void setPurchaseOrg(String purchaseOrg) {
        this.purchaseOrg = purchaseOrg;
    }

    public String getPurchaseOrgName() {
        return purchaseOrgName;
    }

    public void setPurchaseOrgName(String purchaseOrgName) {
        this.purchaseOrgName = purchaseOrgName;
    }

    public int getIsAssignEmp() {
        return isAssignEmp;
    }

    public void setIsAssignEmp(int isAssignEmp) {
        this.isAssignEmp = isAssignEmp;
    }

    public String[] getEmpId() {
        return empId;
    }

    public void setEmpId(String[] empId) {
        this.empId = empId;
    }

    public int getIsReceiveRequest() {
        return isReceiveRequest;
    }

    public void setIsReceiveRequest(int isReceiveRequest) {
        this.isReceiveRequest = isReceiveRequest;
    }

    public int getIsCreatedEmp() {
        return isCreatedEmp;
    }

    public void setIsCreatedEmp(int isCreatedEmp) {
        this.isCreatedEmp = isCreatedEmp;
    }

    public int getIsMaterialNotCode() {
        return isMaterialNotCode;
    }

    public void setIsMaterialNotCode(int isMaterialNotCode) {
        this.isMaterialNotCode = isMaterialNotCode;
    }

    public int getStoreOrg() {
        return storeOrg;
    }

    public void setStoreOrg(int storeOrg) {
        this.storeOrg = storeOrg;
    }

    public int getIsShortCut() {
        return isShortCut;
    }

    public void setIsShortCut(int isShortCut) {
        this.isShortCut = isShortCut;
    }
    static public int WHICHUSE_PROJECT = 1;
    static public int WHICHUSE_ORGANIZATION = 2;
    static public int WHICHUSE_WORKSHOP = 3;
    static public int WHICHUSE_TEAM = 4;
    static public String APPROVE_1 = "1";
    static public String APPROVE_2 = "2";
    static public String APPROVE_3 = "3";
    static public int NOTTREATYET = 0;
    static public int APPROVE = 1;
    static public int NOTAPPROVE = 2;
    static public int KIND_BOTH = 0;
    static public int KIND_MAJOR = 1;
    static public int KIND_SECONDARY = 2;
}
