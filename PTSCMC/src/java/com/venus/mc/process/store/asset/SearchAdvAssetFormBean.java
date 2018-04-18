package com.venus.mc.process.store.asset;

/**
 * @author Mai Vinh Loc
 */
public class SearchAdvAssetFormBean extends org.apache.struts.action.ActionForm {

    private int assId;
    private int mivId;
    private String mivNumber;
    private String decisionNumber;
    private String manageCode;
    private String assetName;
    private String unit;
    private String requestNumber;
    private String contractNumber;
    private String testNumber;
    private String usedCode;
    private String colorCode;
    private String specCerts;
    private String fuelLevel;
    private int status;
    private String appearedDate;
    private String usedDate;
    private String comment;
    private int kind;
    private String statusName;

    public void setDecisionNumber(String decisionNumber) {
        this.decisionNumber = decisionNumber;
    }

    public void setUsedDate(String usedDate) {
        this.usedDate = usedDate;
    }

    public void setUsedCode(String usedCode) {
        this.usedCode = usedCode;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setTestNumber(String testNumber) {
        this.testNumber = testNumber;
    }

    public void setSpecCerts(String specCerts) {
        this.specCerts = specCerts;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public void setMivNumber(String mivNumber) {
        this.mivNumber = mivNumber;
    }

    public void setMivId(int mivId) {
        this.mivId = mivId;
    }

    public void setManageCode(String manageCode) {
        this.manageCode = manageCode;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public void setFuelLevel(String fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public void setAssId(int assId) {
        this.assId = assId;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public void setAppearedDate(String appearedDate) {
        this.appearedDate = appearedDate;
    }

    public String getUsedDate() {
        return usedDate;
    }

    public String getUsedCode() {
        return usedCode;
    }

    public String getUnit() {
        return unit;
    }

    public String getTestNumber() {
        return testNumber;
    }

    public String getSpecCerts() {
        return specCerts;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public String getMivNumber() {
        return mivNumber;
    }

    public int getMivId() {
        return mivId;
    }

    public String getManageCode() {
        return manageCode;
    }

    public int getKind() {
        return kind;
    }

    public String getFuelLevel() {
        return fuelLevel;
    }

    public int getAssId() {
        return assId;
    }

    public String getDecisionNumber() {
        return decisionNumber;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public String getAppearedDate() {
        return appearedDate;
    }

    public String getColorCode() {
        return colorCode;
    }

    public String getComment() {
        return comment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public SearchAdvAssetFormBean() {
    }
}
