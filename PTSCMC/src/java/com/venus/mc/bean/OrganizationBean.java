package com.venus.mc.bean;

public class OrganizationBean {

    //fields region
    private int orgId; // primary key
    private String name;
    private String abbreviate;
    private int parentId;
    private int status;
    private int kind;
    private String statusDetail;
    private String kindDetail;

    //constructure region
    public OrganizationBean() {
    }

    public OrganizationBean(int orgId) {
        this.orgId = orgId;
    }

    public OrganizationBean(int orgId, String name, String abbreviate, int parentId, int status) {
        this.orgId = orgId;
        this.name = name;
        this.abbreviate = abbreviate;
        this.parentId = parentId;
        this.status = status;
    }

    //properties region
    public int getOrgId() {
        return this.orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviate() {
        return this.abbreviate;
    }

    public void setAbbreviate(String abbreviate) {
        this.abbreviate = abbreviate;
    }

    public int getParentId() {
        return this.parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getKind() {
        return this.kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getStatusDetail() {
        return this.statusDetail;
    }

    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

    public String getKindDetail() {
        return this.kindDetail;
    }

    public void setKindDetail(String kindDetail) {
        this.kindDetail = kindDetail;
    }
    public static int KIND_PROJECT = 100;
    public static int KIND_PHONG = 1;
    public static int KIND_XUONG = 2;
    public static int KIND_DOI = 3;
    public static int KIND_TO = 4;
}
