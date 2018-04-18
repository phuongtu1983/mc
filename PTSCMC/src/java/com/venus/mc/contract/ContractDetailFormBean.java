/// <summary>
/// Author : phuongtu
/// Created Date : 05/08/2009
/// </summary>
package com.venus.mc.contract;

public class ContractDetailFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int detId;
    private int conId;
    private int matId;
    private String unit;
    private double quantity;
    private double price;
    private double total;
    private double totalNotVat;
    private String currency;
    private double vat;
    private String deliveryDate;
    private String matName;
    private String matOrigin;
    private int reqDetailId;
    private String projectName;
    private String moveCreateMrir;
    private String receiveMrir;
    private String moveCreateMsv;
    private String receiveMsv;
    private String note;
    private String requestNumber;
    private String requestDate;
    private int matStatus;
    private double remainQuantity;
    private int cb;
    private String materialName;
    private int confirm;
    private int matIdTemp;
    private String contractNumber;
    private String nameTemp;
    private String orgName;
    private double quantity1;
    private double price1;
    private String unit1;
    private double vat1;
    private double total1;
    private double totalNotVat1;
    private double changeMoney;
    private int stt;
    private String name1;
    private String no;
    private String emptyTitle;
    private double totals;

    //constructure region
    public ContractDetailFormBean() {
    }

    public ContractDetailFormBean(int detId, int conId, int matId, String unit, double quantity, double price, double total, double totalNotVat, String currency, double vat, String deliveryDate, String matName, String matOrigin, int reqDetailId, String projectName, String moveCreateMrir, String receiveMrir, String moveCreateMsv, String receiveMsv, String note, String requestNumber, String requestDate, int matStatus, double remainQuantity, int cb, String materialName, int confirm, int matIdTemp, String contractNumber, String nameTemp, String orgName, double quantity1, double price1, String unit1, double vat1, double total1, double totalNotVat1, double changeMoney, int stt, String name1, String no, double totals) {
        this.detId = detId;
        this.conId = conId;
        this.matId = matId;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.totalNotVat = totalNotVat;
        this.currency = currency;
        this.vat = vat;
        this.deliveryDate = deliveryDate;
        this.matName = matName;
        this.matOrigin = matOrigin;
        this.reqDetailId = reqDetailId;
        this.projectName = projectName;
        this.moveCreateMrir = moveCreateMrir;
        this.receiveMrir = receiveMrir;
        this.moveCreateMsv = moveCreateMsv;
        this.receiveMsv = receiveMsv;
        this.note = note;
        this.requestNumber = requestNumber;
        this.requestDate = requestDate;
        this.matStatus = matStatus;
        this.remainQuantity = remainQuantity;
        this.cb = cb;
        this.materialName = materialName;
        this.confirm = confirm;
        this.matIdTemp = matIdTemp;
        this.contractNumber = contractNumber;
        this.nameTemp = nameTemp;
        this.orgName = orgName;
        this.quantity1 = quantity1;
        this.price1 = price1;
        this.unit1 = unit1;
        this.vat1 = vat1;
        this.total1 = total1;
        this.totalNotVat1 = totalNotVat1;
        this.changeMoney = changeMoney;
        this.stt = stt;
        this.name1 = name1;
        this.no = no;
        this.totals = totals;
    }

    public double getTotals() {
        return totals;
    }

    public void setTotals(double totals) {
        this.totals = totals;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public int getStt() {
        return stt;
    }

    public void setChangeMoney(double changeMoney) {
        this.changeMoney = changeMoney;
    }

    public double getChangeMoney() {
        return changeMoney;
    }

    public void setVat1(double vat1) {
        this.vat1 = vat1;
    }

    public void setUnit1(String unit1) {
        this.unit1 = unit1;
    }

    public void setTotal1(double total1) {
        this.total1 = total1;
    }

    public double getVat1() {
        return vat1;
    }

    public String getUnit1() {
        return unit1;
    }

    public double getTotalNotVat() {
        return totalNotVat;
    }

    public double getTotalNotVat1() {
        return totalNotVat1;
    }

    public void setTotalNotVat(double totalNotVat) {
        this.totalNotVat = totalNotVat;
    }

    public void setTotalNotVat1(double totalNotVat1) {
        this.totalNotVat1 = totalNotVat1;
    }

    public double getTotal1() {
        return total1;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setMatIdTemp(int matIdTemp) {
        this.matIdTemp = matIdTemp;
    }

    public int getMatIdTemp() {
        return matIdTemp;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }

    public int getConfirm() {
        return confirm;
    }

    public void setCb(int cb) {
        this.cb = cb;
    }

    public int getCb() {
        return cb;
    }

    public int getConId() {
        return this.conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public double getVat() {
        return this.vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public String getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatOrigin() {
        return matOrigin;
    }

    public void setMatOrigin(String matOrigin) {
        this.matOrigin = matOrigin;
    }

    public int getReqDetailId() {
        return reqDetailId;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getMoveCreateMrir() {
        return moveCreateMrir;
    }

    public void setMoveCreateMrir(String moveCreateMrir) {
        this.moveCreateMrir = moveCreateMrir;
    }

    public String getMoveCreateMsv() {
        return moveCreateMsv;
    }

    public void setMoveCreateMsv(String moveCreateMsv) {
        this.moveCreateMsv = moveCreateMsv;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReceiveMrir() {
        return receiveMrir;
    }

    public void setReceiveMrir(String receiveMrir) {
        this.receiveMrir = receiveMrir;
    }

    public String getReceiveMsv() {
        return receiveMsv;
    }

    public void setReceiveMsv(String receiveMsv) {
        this.receiveMsv = receiveMsv;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public int getMatStatus() {
        return matStatus;
    }

    public void setMatStatus(int matStatus) {
        this.matStatus = matStatus;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public double getRemainQuantity() {
        return remainQuantity;
    }

    public void setRemainQuantity(double remainQuantity) {
        this.remainQuantity = remainQuantity;
    }

    public String getNameTemp() {
        return nameTemp;
    }

    public void setNameTemp(String nameTemp) {
        this.nameTemp = nameTemp;
    }

    public double getPrice1() {
        return price1;
    }

    public void setPrice1(double price1) {
        this.price1 = price1;
    }

    public double getQuantity1() {
        return quantity1;
    }

    public void setQuantity1(double quantity1) {
        this.quantity1 = quantity1;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getEmptyTitle() {
        return emptyTitle;
    }

    public void setEmptyTitle(String emptyTitle) {
        this.emptyTitle = emptyTitle;
    }

}
