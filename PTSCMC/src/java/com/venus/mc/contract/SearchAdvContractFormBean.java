/// <summary>
/// Author : phuongtu
/// Created Date : 05/08/2009
/// </summary>
package com.venus.mc.contract;

public class SearchAdvContractFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int venId;
    private String contractNumber;
    private String effectedFromDate;
    private String effectedToDate;
    private String expireFromDate;
    private String expireToDate;
    private double totalFrom;
    private double totalTo;
    private int kind;
    private int paymentStatus;
    private int isPrint;
    private int proId;
    private int handleEmp;
    private int followEmp;
    private String vendorName;
    private String handleEmpName;
    private String followEmpName;
    private String moveAccountingFromDate;
    private String moveAccountingToDate;

    //constructure region
    public SearchAdvContractFormBean() {
    }

    public String getMoveAccountingFromDate() {
        return moveAccountingFromDate;
    }

    public String getMoveAccountingToDate() {
        return moveAccountingToDate;
    }

    public void setMoveAccountingToDate(String moveAccountingToDate) {
        this.moveAccountingToDate = moveAccountingToDate;
    }

    public void setMoveAccountingFromDate(String moveAccountingFromDate) {
        this.moveAccountingFromDate = moveAccountingFromDate;
    }

    //properties region
    public int getVenId() {
        return this.venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public String getContractNumber() {
        return this.contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public int getKind() {
        return this.kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getEffectedFromDate() {
        return effectedFromDate;
    }

    public void setEffectedFromDate(String effectedFromDate) {
        this.effectedFromDate = effectedFromDate;
    }

    public String getEffectedToDate() {
        return effectedToDate;
    }

    public void setEffectedToDate(String effectedToDate) {
        this.effectedToDate = effectedToDate;
    }

    public String getExpireFromDate() {
        return expireFromDate;
    }

    public void setExpireFromDate(String expireFromDate) {
        this.expireFromDate = expireFromDate;
    }

    public String getExpireToDate() {
        return expireToDate;
    }

    public void setExpireToDate(String expireToDate) {
        this.expireToDate = expireToDate;
    }

    public double getTotalFrom() {
        return totalFrom;
    }

    public void setTotalFrom(double totalFrom) {
        this.totalFrom = totalFrom;
    }

    public double getTotalTo() {
        return totalTo;
    }

    public void setTotalTo(double totalTo) {
        this.totalTo = totalTo;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(int isPrint) {
        this.isPrint = isPrint;
    }

    public int getFollowEmp() {
        return followEmp;
    }

    public void setFollowEmp(int followEmp) {
        this.followEmp = followEmp;
    }

    public int getHandleEmp() {
        return handleEmp;
    }

    public void setHandleEmp(int handleEmp) {
        this.handleEmp = handleEmp;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getFollowEmpName() {
        return followEmpName;
    }

    public void setFollowEmpName(String followEmpName) {
        this.followEmpName = followEmpName;
    }

    public String getHandleEmpName() {
        return handleEmpName;
    }

    public void setHandleEmpName(String handleEmpName) {
        this.handleEmpName = handleEmpName;
    }
}
