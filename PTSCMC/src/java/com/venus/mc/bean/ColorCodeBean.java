package com.venus.mc.bean;

/**
 * @author Mai Vinh Loc
 */
public class ColorCodeBean {

    private int ccId;
    private String colorCode;
    private String timeApplication;
    private String startDate;
    private String endDate;

    public ColorCodeBean() {
    }

    public ColorCodeBean(int ccId, String colorCode, String timeApplication, String startDate, String endDate) {
        this.ccId = ccId;
        this.colorCode = colorCode;
        this.timeApplication = timeApplication;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getCcId() {
        return ccId;
    }

    public String getColorCode() {
        return colorCode;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setCcId(int ccId) {
        this.ccId = ccId;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getTimeApplication() {
        return timeApplication;
    }

    public void setTimeApplication(String timeApplication) {
        this.timeApplication = timeApplication;
    }
}
