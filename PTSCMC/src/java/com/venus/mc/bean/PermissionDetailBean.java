
/// <summary>
/// Author : phuongtu
/// Created Date : 28/10/2009
/// </summary>
package com.venus.mc.bean;

public class PermissionDetailBean {

    //fields region
    private int detId; // primary key
    private int perId; // foreign key : reference to permission(per_id)
    private String function;
    private String permit;

    //constructure region
    public PermissionDetailBean() {
    }

    public PermissionDetailBean(int detId) {
        this.detId = detId;
    }

    public PermissionDetailBean(int detId, String function, String permit) {
        this.detId = detId;
        this.function = function;
        this.permit = permit;
    }

    public PermissionDetailBean(String function, String permit) {
        this.function = function;
        this.permit = permit;
    }

    //properties region
    public int getDetId() {
        return this.detId;
    }

    public void setDetId(int detId) {
        this.detId = detId;
    }

    public int getPerId() {
        return this.perId;
    }

    public void setPerId(int perId) {
        this.perId = perId;
    }

    public String getFunction() {
        return this.function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getPermit() {
        return this.permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }
}
