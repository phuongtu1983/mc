
/// <summary>
/// Author : phuongtu
/// Created Date : 24/08/2009
/// </summary>
package com.venus.mc.bean;

public class DeliveryRequestContractBean {

    //fields region
    private int drcId; // primary key
    private int drId; // foreign key : reference to delivery_request(dr_id)
    private int conId; // foreign key : reference to contract(con_id)

    //constructure region
    public DeliveryRequestContractBean() {
    }

    public DeliveryRequestContractBean(int drcId) {
        this.drcId = drcId;
    }

    //properties region
    public int getDrcId() {
        return this.drcId;
    }

    public void setDrcId(int drcId) {
        this.drcId = drcId;
    }

    public int getDrId() {
        return this.drId;
    }

    public void setDrId(int drId) {
        this.drId = drId;
    }

    public int getConId() {
        return this.conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }
}
