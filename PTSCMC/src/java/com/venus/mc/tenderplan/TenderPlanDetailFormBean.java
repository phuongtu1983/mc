/// <summary>
/// Author : phuongtu
/// Created Date : 13/08/2009
/// </summary>
package com.venus.mc.tenderplan;

import com.venus.mc.bean.TenderPlanDetailBean;

public class TenderPlanDetailFormBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int detId;
    private int tenId;
    private int matId;
    private String unit;
    private double quantity;
    private int qt;
    private String provideDate;
    private int reqDetailId;
    private String matName;
    private String matCode;
    private String requestNumber;
    private String matOrigin;
    private double price;
    private long vat;
    private double total;
    private String projectName;
    private double remainQuantity;
    private double requestQuantity;
    private int matStatus;
    private String materialName;
    private int confirm;
    private int matIdTemp;
    private String nameTemp;
    private int reqId;
    private String note;

    //constructure region
    public TenderPlanDetailFormBean() {
        super();
    }

    public TenderPlanDetailFormBean(TenderPlanDetailBean bean) {
        this.detId = bean.getDetId();
        this.unit = bean.getUnit();
        this.quantity = bean.getQuantity();
        this.provideDate = bean.getProvideDate();
        this.reqDetailId = bean.getReqDetailId();
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public void setNameTemp(String nameTemp) {
        this.nameTemp = nameTemp;
    }

    public String getNameTemp() {
        return nameTemp;
    }

    public void setMatIdTemp(int matIdTemp) {
        this.matIdTemp = matIdTemp;
    }

    public int getMatIdTemp() {
        return matIdTemp;
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

    public String getMaterialName() {
        return materialName;
    }

    public int getTenId() {
        return this.tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
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

    public String getProvideDate() {
        return this.provideDate;
    }

    public void setProvideDate(String provideDate) {
        this.provideDate = provideDate;
    }

    public int getReqDetailId() {
        return this.reqDetailId;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getMatOrigin() {
        return matOrigin;
    }

    public void setMatOrigin(String matOrigin) {
        this.matOrigin = matOrigin;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getVat() {
        return vat;
    }

    public void setVat(long vat) {
        this.vat = vat;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setQt(int qt) {
        this.qt = qt;
    }

    public int getQt() {
        return qt;
    }

    public double getRemainQuantity() {
        return remainQuantity;
    }

    public void setRemainQuantity(double remainQuantity) {
        this.remainQuantity = remainQuantity;
    }

    public double getRequestQuantity() {
        return requestQuantity;
    }

    public void setRequestQuantity(double requestQuantity) {
        this.requestQuantity = requestQuantity;
    }

    public int getMatStatus() {
        return matStatus;
    }

    public void setMatStatus(int matStatus) {
        this.matStatus = matStatus;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
