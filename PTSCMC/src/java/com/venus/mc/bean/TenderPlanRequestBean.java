/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class TenderPlanRequestBean {

    //fields region
    private int tprId; // primary key
    private int tenId; // foreign key : reference to tender_plan(ten_id)
    private int reqId; // foreign key : reference to request(req_id)

    //constructure region
    public TenderPlanRequestBean() {
    }

    public TenderPlanRequestBean(int tprId) {
        this.tprId = tprId;
    }

    //properties region
    public int getTprId() {
        return this.tprId;
    }

    public void setTprId(int tprId) {
        this.tprId = tprId;
    }

    public int getTenId() {
        return this.tenId;
    }

    public void setTenId(int tenId) {
        this.tenId = tenId;
    }

    public int getReqId() {
        return this.reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }
}
