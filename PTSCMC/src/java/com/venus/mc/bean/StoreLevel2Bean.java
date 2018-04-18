/// <summary>
/// Author : phuongtu
/// Created Date : 06/10/2009
/// </summary>
package com.venus.mc.bean;

public class StoreLevel2Bean {

    //fields region
    private int sto2Id; // primary key
    private String name;
    private int orgId;
    private int usedOrg;
    private String createdDate;
    private String organizationName;
    private int umsQuantity;
    private int rmsQuantity;
    private String umsQuantityText;
    private String rmsQuantityText;

    //constructure region
    public StoreLevel2Bean() {
    }

    public StoreLevel2Bean(int sto2Id) {
        this.sto2Id = sto2Id;
    }

    public StoreLevel2Bean(int sto2Id, String name, int orgId, String createdDate) {
        this.sto2Id = sto2Id;
        this.name = name;
        this.orgId = orgId;
        this.createdDate = createdDate;
    }

    //properties region
    public int getSto2Id() {
        return this.sto2Id;
    }

    public void setSto2Id(int sto2Id) {
        this.sto2Id = sto2Id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrgId() {
        return this.orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public int getUsedOrg() {
        return usedOrg;
    }

    public void setUsedOrg(int usedOrg) {
        this.usedOrg = usedOrg;
    }

    public int getRmsQuantity() {
        return rmsQuantity;
    }

    public void setRmsQuantity(int rmsQuantity) {
        this.rmsQuantity = rmsQuantity;
    }

    public int getUmsQuantity() {
        return umsQuantity;
    }

    public void setUmsQuantity(int umsQuantity) {
        this.umsQuantity = umsQuantity;
    }

    public String getRmsQuantityText() {
        return rmsQuantityText;
    }

    public void setRmsQuantityText(String rmsQuantityText) {
        this.rmsQuantityText = rmsQuantityText;
    }

    public String getUmsQuantityText() {
        return umsQuantityText;
    }

    public void setUmsQuantityText(String umsQuantityText) {
        this.umsQuantityText = umsQuantityText;
    }
}
