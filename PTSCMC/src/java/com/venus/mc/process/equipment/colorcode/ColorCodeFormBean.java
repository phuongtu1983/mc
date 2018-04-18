package com.venus.mc.process.equipment.colorcode;

/**
 * @author Mai Vinh Loc
 */
public class ColorCodeFormBean extends org.apache.struts.action.ActionForm {

    private int ccId;
    private String colorCode;
    private String timeApplication;
    private String startDate;
    private String endDate;

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

    public ColorCodeFormBean() {
        super();

    }
}
