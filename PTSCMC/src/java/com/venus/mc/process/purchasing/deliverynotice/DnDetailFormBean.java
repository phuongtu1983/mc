/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.purchasing.deliverynotice;

/**
 *
 * @author mai vinh loc
 */
public class DnDetailFormBean extends org.apache.struts.action.ActionForm {

    private int detId; // primary key
    private int dnId;
    private int matId; // foreign key : reference to material(mat_id)
    private double quantity;
    private String unit;
    private String matName;
    private String matCode;
    private double price;
    private String note;

    //constructure region
    public DnDetailFormBean() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public int getDnId() {
        return dnId;
    }

    public String getNote() {
        return note;
    }

    public void setDnId(int dnId) {
        this.dnId = dnId;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
