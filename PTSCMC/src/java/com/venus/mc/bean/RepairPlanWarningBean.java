//mai vinh loc
package com.venus.mc.bean;

public class RepairPlanWarningBean {

    //fields region
    private int rpId; // primary key
    private String matName;
    private String manageCode;
    private String timeTrue;

    //constructure region
    public RepairPlanWarningBean() {
    }

    public int getRpId() {
        return rpId;
    }

    public void setRpId(int rpId) {
        this.rpId = rpId;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public void setTimeTrue(String timeTrue) {
        this.timeTrue = timeTrue;
    }

    public String getTimeTrue() {
        return timeTrue;
    }

    public void setManageCode(String manageCode) {
        this.manageCode = manageCode;
    }

    public String getManageCode() {
        return manageCode;
    }
}
