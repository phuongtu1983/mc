/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class TechEvalVendorBean {

    //fields region
    private int tevId; // primary key
    private int teId; // foreign key : reference to tech_eval(te_id)
    private int venId; // foreign key : reference to vendor(ven_id)

    //constructure region
    public TechEvalVendorBean() {
    }

    public TechEvalVendorBean(int tevId) {
        this.tevId = tevId;
    }

    //properties region
    public int getTevId() {
        return this.tevId;
    }

    public void setTevId(int tevId) {
        this.tevId = tevId;
    }

    public int getTeId() {
        return this.teId;
    }

    public void setTeId(int teId) {
        this.teId = teId;
    }

    public int getVenId() {
        return this.venId;
    }

    public void setVenId(int venId) {
        this.venId = venId;
    }
}
