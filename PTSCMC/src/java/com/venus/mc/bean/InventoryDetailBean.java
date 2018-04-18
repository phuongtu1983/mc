
/// <summary>
/// Author : kngo
/// Created Date : 05/10/2009
/// </summary>
package com.venus.mc.bean;

public class InventoryDetailBean {

    //fields region
    private int detId; // primary key
    private int invId; // foreign key : reference to inventory(inv_id)
    private int matId; // foreign key : reference to material(mat_id)
    private String matName;
    private String matCode;
    private String unit;
    private double quantityActual;
    private double quantityDocument;
    private double quantityVariance;
    private int isChanged;
    private String commentDetail;

    //constructure region
    public InventoryDetailBean() {
    }

    public InventoryDetailBean(int detId) {
        this.detId = detId;
    }

    public InventoryDetailBean(int detId, double quantityActual, double quantityDocument, int isChanged, String commentDetail) {
        this.detId = detId;
        this.quantityActual = quantityActual;
        this.quantityDocument = quantityDocument;
        this.isChanged = isChanged;
        this.commentDetail = commentDetail;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getInvId() {
        return this.invId;
    }

    public void setInvId(int invId) {
        this.invId = invId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public String getMatName() {
        return this.matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatCode() {
        return this.matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getQuantityActual() {
        return this.quantityActual;
    }

    public void setQuantityActual(double quantittyActual) {
        this.quantityActual = quantittyActual;
    }

    public double getQuantityDocument() {
        return this.quantityDocument;
    }

    public void setQuantityDocument(double quantityDocument) {
        this.quantityDocument = quantityDocument;
    }

    public double getQuantityVariance() {
        return this.quantityVariance;
    }

    public void setQuantityVariance(double quantityVariance) {
        this.quantityVariance = quantityVariance;
    }

    public int getIsChanged() {
        return this.isChanged;
    }

    public void setIsChanged(int isChanged) {
        this.isChanged = isChanged;
    }

    public String getCommentDetail() {
        return this.commentDetail;
    }

    public void setCommentDetail(String commentDetail) {
        this.commentDetail = commentDetail;
    }
}
