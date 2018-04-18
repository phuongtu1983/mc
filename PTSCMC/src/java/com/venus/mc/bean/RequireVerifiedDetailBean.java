package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */
public class RequireVerifiedDetailBean {

    private String detId;
    private int rvId;
    private int equId;
    private String equipmentName;
    private String usedCode;
    private String timeEstimate;
    private String comment;

    public RequireVerifiedDetailBean() {
    }

    public String getUsedCode() {
        return usedCode;
    }

    public void setUsedCode(String usedCode) {
        this.usedCode = usedCode;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getEquId() {
        return equId;
    }

    public void setEquId(int equId) {
        this.equId = equId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setDetId(String detId) {
        this.detId = detId;
    }

    public String getDetId() {
        return detId;
    }

    public void setRvId(int rvId) {
        this.rvId = rvId;
    }

    public int getRvId() {
        return rvId;
    }

    public void setTimeEstimate(String timeEstimate) {
        this.timeEstimate = timeEstimate;
    }

    public String getTimeEstimate() {
        return timeEstimate;
    }
}
