
/// <summary>
/// Author : phuongtu
/// Created Date : 09/07/2009
/// </summary>
package com.venus.mc.bean;

public class VendorMaterialBean {

    //fields region
    private int vmId; // primary key
    private int venId; // foreign key : reference to vendor(ven_id)
    private String nameVn;
    private String nameEn;
    private int groId; // foreign key : reference to group_material(gro_id)
    private int oriId; // foreign key : reference to origin(ori_id)
    private String manufacturer;
    private String note;

    //constructure region
    public VendorMaterialBean() {
    }

    public VendorMaterialBean(int vmId) {
        this.vmId = vmId;
    }

    public VendorMaterialBean(int vmId, String nameVn, String nameEn, String manufacturer, String note) {
        this.vmId = vmId;
        this.nameVn = nameVn;
        this.nameEn = nameEn;
        this.manufacturer = manufacturer;
        this.note = note;
    }

    //properties region
    public int getVmId() {
        return this.vmId;
    }

    public void setVmId(int vmId) {
        this.vmId = vmId;
    }

    public int getVenId() {
        return this.venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public String getNameVn() {
        return this.nameVn;
    }

    public void setNameVn(String nameVn) {
        this.nameVn = nameVn;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public int getOriId() {
        return this.oriId;
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
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
