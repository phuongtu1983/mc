/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class StoreBean {

    private int stoId; // primary key
    private int proId; // foreign key : reference to project(pro_id)
    private String proName;
    private String name;
    private String physicalAdd;
    private int kind;
    private String comments;
    private String kindDetail;
    private int usedOrg;
    private int empId;
    private String empTel;

    //constructure region
    public StoreBean() {
    }

    public StoreBean(int stoId) {
        this.stoId = stoId;
    }

    public StoreBean(int stoId, String name, String physicalAdd, int kind, String comments) {
        this.stoId = stoId;
        this.name = name;
        this.physicalAdd = physicalAdd;
        this.kind = kind;
        this.comments = comments;
    }

    public int getStoId() {
        return this.stoId;
    }

    public void setStoId(int stoId) {
        this.stoId = stoId;
    }

    public int getProId() {
        return this.proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhysicalAdd() {
        return this.physicalAdd;
    }

    public void setPhysicalAdd(String physicalAdd) {
        this.physicalAdd = physicalAdd;
    }

    public int getKind() {
        return this.kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getProName() {
        return this.proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getKindDetail() {
        return this.kindDetail;
    }

    public void setKindDetail(String kindDetail) {
        this.kindDetail = kindDetail;
    }

    public int getUsedOrg() {
        return usedOrg;
    }

    public void setUsedOrg(int usedOrg) {
        this.usedOrg = usedOrg;
    }
    
    public int getEmpId() {
        return this.empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpTel() {
        return this.empTel;
    }

    public void setEmpTel(String empTel) {
        this.empTel = empTel;
    }
    
    public static int LEVEL1 = 1;
    public static int LEVEL2 = 2;
}
