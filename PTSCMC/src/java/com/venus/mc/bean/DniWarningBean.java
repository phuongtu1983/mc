//mai vinh loc
package com.venus.mc.bean;

public class DniWarningBean {

    //fields region
    private int dniId; // primary key
    private String dnNumber;
    private String moveCreateMrir;
    private String receiveMrir;
    private String receiveMsv;

    //constructure region
    public DniWarningBean() {
    }

    public int getDniId() {
        return dniId;
    }

    public void setDniId(int dniId) {
        this.dniId = dniId;
    }

    public String getDnNumber() {
        return dnNumber;
    }

    public void setDnNumber(String dnNumber) {
        this.dnNumber = dnNumber;
    }

    public String getMoveCreateMrir() {
        return moveCreateMrir;
    }

    public void setMoveCreateMrir(String moveCreateMrir) {
        this.moveCreateMrir = moveCreateMrir;
    }

    public String getReceiveMrir() {
        return receiveMrir;
    }

    public void setReceiveMrir(String receiveMrir) {
        this.receiveMrir = receiveMrir;
    }

    public String getReceiveMsv() {
        return receiveMsv;
    }

    public void setReceiveMsv(String receiveMsv) {
        this.receiveMsv = receiveMsv;
    }
}
