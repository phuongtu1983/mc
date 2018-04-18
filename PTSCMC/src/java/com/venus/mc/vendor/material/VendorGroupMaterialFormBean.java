/*
 * PositionBean.java
 *
 * Created on March 7, 2007, 9:43 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.venus.mc.vendor.material;

import com.venus.mc.bean.VendorGroupMaterialBean;

/**
 *
 * @author Administrator
 */
public class VendorGroupMaterialFormBean extends org.apache.struts.action.ActionForm {

    private int vgmId;
    private int venId;
    private int groId;
    private String groupName;
    private String groupNote;

    //constructure region
    public VendorGroupMaterialFormBean() {
    }

    public VendorGroupMaterialFormBean(VendorGroupMaterialBean bean) {
        this.vgmId = bean.getVgmId();
        this.venId = bean.getVenId();
        this.groId = bean.getGroId();
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupNote() {
        return groupNote;
    }

    public void setGroupNote(String groupNote) {
        this.groupNote = groupNote;
    }
}
