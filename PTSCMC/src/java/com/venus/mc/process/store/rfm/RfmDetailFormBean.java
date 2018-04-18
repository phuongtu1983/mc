/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.rfm;

/**
 *
 * @author mai vinh loc
 */
public class RfmDetailFormBean extends org.apache.struts.action.ActionForm {

    private int detId; // primary key
    private int rfmId; // foreign key : reference to rfm(rfm_id)
    private int matId; // foreign key : reference to material(mat_id)
    private String msvNumber;
    private double quantity;
    private String unit;
    private String comment;
    private int reqDetailId;
    private String matName;
    private double price;

    //constructure region
    public RfmDetailFormBean() {
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

    public int getRfmId() {
        return this.rfmId;
    }

    public void setRfmId(int rfmId) {
        this.rfmId = rfmId;
    }

    public int getMatId() {
        return this.matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public String getMsvNumber() {
        return this.msvNumber;
    }

    public void setMsvNumber(String msvNumber) {
        this.msvNumber = msvNumber;
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

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getReqDetailId() {
        return this.reqDetailId;
    }

    public void setReqDetailId(int reqDetailId) {
        this.reqDetailId = reqDetailId;
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
    
}
