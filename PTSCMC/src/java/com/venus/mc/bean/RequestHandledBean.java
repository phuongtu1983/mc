/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author phuongtu
 */
public class RequestHandledBean {

    private int id;
    private int reqId;
    private int orgId;
    private int assignedEmp;

    public int getAssignedEmp() {
        return assignedEmp;
    }

    public void setAssignedEmp(int assignedEmp) {
        this.assignedEmp = assignedEmp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }
}
