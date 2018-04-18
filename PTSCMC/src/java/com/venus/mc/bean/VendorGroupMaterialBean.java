
/// <summary>
/// Author : phuongtu
/// Created Date : 14/07/2009
/// </summary>
package com.venus.mc.bean;

public class VendorGroupMaterialBean {

    //fields region
    private int vgmId; // primary key
    private int venId; // foreign key : reference to vendor(ven_id)
    private int groId; // foreign key : reference to group_material(gro_id)

    //constructure region
    public VendorGroupMaterialBean() {
    }

    public VendorGroupMaterialBean(int vgmId) {
        this.vgmId = vgmId;
    }

    //properties region
    public int getVgmId() {
        return this.vgmId;
    }

    public void setVgmId(int vgmId) {
        this.vgmId = vgmId;
    }

    public int getVenId() {
        return this.venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }

    public int getGroId() {
        return this.groId;
    }

    public void setGroId(int groId) {
        this.groId = groId;
    }
}
