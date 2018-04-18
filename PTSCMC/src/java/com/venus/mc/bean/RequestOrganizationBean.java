
/// <summary>
/// Author : phuongtu
/// Created Date : 14/09/2009
/// </summary>
package com.venus.mc.bean;

public class RequestOrganizationBean {

    //fields region
    private int roId; // primary key
    private int orgId;
    private int number;
    private String mapChar;
    private String kind;
    //constructure region
    public RequestOrganizationBean() {
    }

    public RequestOrganizationBean(int roId) {
        this.roId = roId;
    }

    public RequestOrganizationBean(int roId, int orgId, int number, String mapChar, String kind) {
        this.roId = roId;
        this.orgId = orgId;
        this.number = number;
        this.mapChar = mapChar;
        this.kind = kind;
    }

    //properties region
    public int getRoId() {
        return this.roId;
    }

    public void setRoId(int roId) {
        this.roId = roId;
    }

    public int getOrgId() {
        return this.orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMapChar() {
        return this.mapChar;
    }

    public void setMapChar(String mapChar) {
        this.mapChar = mapChar;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
