//mai vinh loc
package com.venus.mc.bean;

public class VerifiedPlanWarningBean {

    //fields region
    private int vpId; // primary key
    private String matName;
    private String manageCode;
    private String timeTrue;

    //constructure region
    public VerifiedPlanWarningBean() {
    }

    public int getVpId() {
        return vpId;
    }

    public void setVpId(int vpId) {
        this.vpId = vpId;
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
