/*
 * PositionBean.java
 *
 * Created on March 7, 2007, 9:43 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.venus.mc.vendor.material;

import com.venus.mc.bean.VendorMaterialBean;

/**
 *
 * @author Administrator
 */
public class VendorMaterialFormBean extends org.apache.struts.action.ActionForm {

    private int vmId;
    private int venId;
    private int oriId;
    private int groId;
    private String nameVn;
    private String nameEn;
    private String manufacturer;
    private String note;
    private String vendorName;
    private String oriName;
    private String groName;

    public VendorMaterialFormBean() {
        super();
    }

    public VendorMaterialFormBean(VendorMaterialBean bean) {
        this.vmId = bean.getVmId();
        this.venId = bean.getVenId();
        this.oriId = bean.getOriId();
        this.groId = bean.getGroId();
        this.nameVn = bean.getNameVn();
        this.nameEn = bean.getNameEn();
        this.manufacturer = bean.getManufacturer();
        this.note = bean.getNote();
    }

    public int getVmId() {
        return vmId;
    }

    public void setVmId(int vmId) {
        this.vmId = vmId;
    }

    public int getVenId() {
        return venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public String getNameVn() {
        return nameVn;
    }

    public void setNameVn(String nameVn) {
        this.nameVn = nameVn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public int getOriId() {
        return oriId;
    }

    public void setOriId(int oriId) {
        this.oriId = oriId;
    }

    public int getGroId() {
        return groId;
    }

    public void setGroId(int groId) {
        this.groId = groId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getOriName() {
        return oriName;
    }

    public void setOriName(String oriName) {
        this.oriName = oriName;
    }

    public String getGroName() {
        return groName;
    }

    public void setGroName(String groName) {
        this.groName = groName;
    }
}
