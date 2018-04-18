package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */
public class EquipmentBean {

    private int equId;
    private int mivId;
    private String mivNumber;
    private String decisionNumber;
    private String test;
    private String manageCode;
    private String equipmentName;
    private String requestNumber;
    private String contractNumber;
    private String testNumber;
    private String unit;
    private String usedCode;
    private String colorCode;
    private String specCerts;
    private String fuelLevel;
    private int status;
    private String appearedDate;
    private String usedDate;
    private String comment;
    private int kind;
    private int matId;
    private int reqDetailId;
    private int conId;
    private int mrirId;
    private int ccId;
    private String statusName;
    private int createdEmp;
    private double quantity;

    public EquipmentBean(int equId, int mivId, String mivNumber, String decisionNumber, String test, String manageCode, String equipmentName, String requestNumber, String contractNumber, String testNumber, String unit, String usedCode, String colorCode, String specCerts, String fuelLevel, int status, String appearedDate, String usedDate, String comment, int kind, int matId, int reqDetailId, int conId, int mrirId, int ccId, String statusName, int createdEmp, double quantity) {
        this.equId = equId;
        this.mivId = mivId;
        this.mivNumber = mivNumber;
        this.decisionNumber = decisionNumber;
        this.test = test;
        this.manageCode = manageCode;
        this.equipmentName = equipmentName;
        this.requestNumber = requestNumber;
        this.contractNumber = contractNumber;
        this.testNumber = testNumber;
        this.unit = unit;
        this.usedCode = usedCode;
        this.colorCode = colorCode;
        this.specCerts = specCerts;
        this.fuelLevel = fuelLevel;
        this.status = status;
        this.appearedDate = appearedDate;
        this.usedDate = usedDate;
        this.comment = comment;
        this.kind = kind;
        this.matId = matId;
        this.reqDetailId = reqDetailId;
        this.conId = conId;
        this.mrirId = mrirId;
        this.ccId = ccId;
        this.statusName = statusName;
        this.createdEmp = createdEmp;
        this.quantity = quantity;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

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

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public void setEquId(int equId) {
        this.equId = equId;
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

    public String getEquipmentName() {
        return equipmentName;
    }

    public int getEquId() {
        return equId;
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

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public int getReqDetailId() {
        return reqDetailId;
    }

    public int getMatId() {
        return matId;
    }

    public int getConId() {
        return conId;
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

    public int getMrirId() {
        return mrirId;
    }

    public void setMrirId(int mrirId) {
        this.mrirId = mrirId;
    }

    public void setCcId(int ccId) {
        this.ccId = ccId;
    }

    public int getCcId() {
        return ccId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public EquipmentBean() {
    }
    //1:�?ang sử dụng; 2: �?ang sửa sửa, 3: Ch�? thanh lý; 4: �?ã thanh lý; 5: Chưa sử dụng
    public static int S1 = 1;
    public static int S2 = 2;
    public static int S3 = 3;
    public static int S4 = 4;
    public static int S5 = 5;
    //1: CCDC; 2: TSCDHH
    public static int K1 = 1;
    public static int K2 = 2;
}
