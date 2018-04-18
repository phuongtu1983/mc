//mai vinh loc
package com.venus.mc.bean;

public class DnBean {

    //fields region
    private int dnId; // primary key
    private String createdDate;
    private int createdEmp;
    private String dnNumber;
    private int conId;
    private int drId;
    private String expectedDate;
    private String extensionDate;
    private String deliveryPresenter;
    private String deliveryPlace;
    private String contractNumber;
    private int whichUse;
    private int createdOrg;
    private String orgName;
    private String proName;
    private int proId;
    private int kind;
    private int status;
    private int warned;
    private String actualDate;//phuongtu
    private int highlight;
    private int orgHandle;
    String abbreviate;
    String day;
    String month;
    String year;
    private int canSave;

    //constructure region
    public DnBean() {
    }

    public DnBean(int dnId, String createdDate, int createdEmp, String dnNumber, int conId, int drId, String expectedDate, String extensionDate, String deliveryPresenter, String deliveryPlace, String contractNumber, int whichUse, int createdOrg, String orgName, String proName, int proId, int kind, int status, int warned, String actualDate, int highlight) {
        this.dnId = dnId;
        this.createdDate = createdDate;
        this.createdEmp = createdEmp;
        this.dnNumber = dnNumber;
        this.conId = conId;
        this.drId = drId;
        this.expectedDate = expectedDate;
        this.extensionDate = extensionDate;
        this.deliveryPresenter = deliveryPresenter;
        this.deliveryPlace = deliveryPlace;
        this.contractNumber = contractNumber;
        this.whichUse = whichUse;
        this.createdOrg = createdOrg;
        this.orgName = orgName;
        this.proName = proName;
        this.proId = proId;
        this.kind = kind;
        this.status = status;
        this.warned = warned;
        this.actualDate = actualDate;
        this.highlight = highlight;
    }

    public DnBean(int dnId, String createdDate, int createdEmp, String dnNumber, int conId, int drId, String expectedDate, String extensionDate, String deliveryPresenter, String deliveryPlace, String contractNumber, int whichUse, int createdOrg, String orgName, String proName, int proId, int kind, int status, int warned, String actualDate, int highlight, int orgHandle, String abbreviate, String day, String month, String year) {
        this.dnId = dnId;
        this.createdDate = createdDate;
        this.createdEmp = createdEmp;
        this.dnNumber = dnNumber;
        this.conId = conId;
        this.drId = drId;
        this.expectedDate = expectedDate;
        this.extensionDate = extensionDate;
        this.deliveryPresenter = deliveryPresenter;
        this.deliveryPlace = deliveryPlace;
        this.contractNumber = contractNumber;
        this.whichUse = whichUse;
        this.createdOrg = createdOrg;
        this.orgName = orgName;
        this.proName = proName;
        this.proId = proId;
        this.kind = kind;
        this.status = status;
        this.warned = warned;
        this.actualDate = actualDate;
        this.highlight = highlight;
        this.orgHandle = orgHandle;
        this.abbreviate = abbreviate;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getCanSave() {
        return canSave;
    }

    public void setCanSave(int canSave) {
        this.canSave = canSave;
    }

   

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public void setAbbreviate(String abbreviate) {
        this.abbreviate = abbreviate;
    }

    public String getAbbreviate() {
        return abbreviate;
    }

    public void setOrgHandle(int orgHandle) {
        this.orgHandle = orgHandle;
    }

    public int getOrgHandle() {
        return orgHandle;
    }

    public int getHighlight() {
        return highlight;
    }

    public void setHighlight(int highlight) {
        this.highlight = highlight;
    }

    public String getExtensionDate() {
        return extensionDate;
    }

    public void setExtensionDate(String extensionDate) {
        this.extensionDate = extensionDate;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public DnBean(int dnId) {
        this.dnId = dnId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getProId() {
        return proId;
    }

    public int getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public int getConId() {
        return conId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public String getDeliveryPresenter() {
        return deliveryPresenter;
    }

    public int getDnId() {
        return dnId;
    }

    public String getDnNumber() {
        return dnNumber;
    }

    public int getDrId() {
        return drId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }

    public void setDeliveryPresenter(String deliveryPresenter) {
        this.deliveryPresenter = deliveryPresenter;
    }

    public void setDnId(int dnId) {
        this.dnId = dnId;
    }

    public void setDnNumber(String dnNumber) {
        this.dnNumber = dnNumber;
    }

    public void setDrId(int drId) {
        this.drId = drId;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    public int getWhichUse() {
        return whichUse;
    }

    public void setWhichUse(int whichUse) {
        this.whichUse = whichUse;
    }

    public int getCreatedOrg() {
        return createdOrg;
    }

    public void setCreatedOrg(int createdOrg) {
        this.createdOrg = createdOrg;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getWarned() {
        return warned;
    }

    public void setWarned(int warned) {
        this.warned = warned;
    }

    public String getActualDate() {
        return actualDate;
    }

    public void setActualDate(String actualDate) {
        this.actualDate = actualDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    //1: TBGH; 2: YCKTVT kho cap 2 Du an; 3: YCKTVT kho cap 2 cong ty; 4: YCKTVT kho cap 1 Du an; 5:Kiem tra TSCDHH
    public static int KIND1 = 1;
    public static int KIND2 = 2;
    public static int KIND3 = 3;
    public static int KIND4 = 4;
    public static int KIND5 = 5;
}
