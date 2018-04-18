/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.store.mrir;

/**
 *
 * @author kngo
 *  Author : kngo
 *  Created Date : 22/09/2009
 */
public class OsDDetailFormBean extends org.apache.struts.action.ActionForm {

    private int detId;
    private int osdId;
    private int matId;
    private double quantity;
    private String unit;
    private String manufacture;

    //constructure region
    public OsDDetailFormBean() {
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getOsdId() {
        return this.osdId;
    }

    public void setOsdId(int osdId) {
        this.osdId = osdId;
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
    
    public String getManufacture() {
        return this.manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }
}


