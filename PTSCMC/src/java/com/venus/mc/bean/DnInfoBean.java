package com.venus.mc.bean;

//mai vinh loc
public class DnInfoBean {

    //fields region
    private int dniId; // primary key
    private int dnId; // foreign key : reference to delivery_notice(dn_id)
    private String moveCreateMrir;
    private String receiveMrir;
    private String moveCreateMsv;
    private String receiveMsv;

    //constructure region
    public DnInfoBean() {
    }

    public DnInfoBean(int dniId, int dnId, String moveCreateMrir, String receiveMrir, String moveCreateMsv, String receiveMsv) {
        this.dniId = dniId;
        this.dnId = dnId;
        this.moveCreateMrir = moveCreateMrir;
        this.receiveMrir = receiveMrir;
        this.moveCreateMsv = moveCreateMsv;
        this.receiveMsv = receiveMsv;
    }

    public int getDniId() {
        return dniId;
    }

    public int getDnId() {
        return dnId;
    }

    public String getMoveCreateMrir() {
        return moveCreateMrir;
    }

    public String getMoveCreateMsv() {
        return moveCreateMsv;
    }

    public String getReceiveMrir() {
        return receiveMrir;
    }

    public String getReceiveMsv() {
        return receiveMsv;
    }

    public void setDniId(int dniId) {
        this.dniId = dniId;
    }

    public void setDnId(int dnId) {
        this.dnId = dnId;
    }

    public void setMoveCreateMrir(String moveCreateMrir) {
        this.moveCreateMrir = moveCreateMrir;
    }

    public void setMoveCreateMsv(String moveCreateMsv) {
        this.moveCreateMsv = moveCreateMsv;
    }

    public void setReceiveMrir(String receiveMrir) {
        this.receiveMrir = receiveMrir;
    }

    public void setReceiveMsv(String receiveMsv) {
        this.receiveMsv = receiveMsv;
    }
}
