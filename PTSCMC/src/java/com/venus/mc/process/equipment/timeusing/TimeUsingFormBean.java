/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.timeusing;

import com.venus.mc.bean.TimeUsingBean;

/**
 *
 * @author mai vinh loc
 */
public class TimeUsingFormBean extends org.apache.struts.action.ActionForm {
    //fields region    
    private int[] tuId;
    private int createdEmpId;
    private String createdEmpName;
    private String updateDate;
    private long[] timeUsed;
    private int[] equId;

    //constructure region
    public TimeUsingFormBean() {
        super();
    }

    public TimeUsingFormBean(TimeUsingBean bean) {
        super();
    }

    public int getCreatedEmpId() {
        return createdEmpId;
    }

    public void setCreatedEmpId(int createdEmpId) {
        this.createdEmpId = createdEmpId;
    }

    public void setCreatedEmpName(String createdEmpName) {
        this.createdEmpName = createdEmpName;
    }

    public String getCreatedEmpName() {
        return createdEmpName;
    }

    public void setTimeUsed(long[] timeUsed) {
        this.timeUsed = timeUsed;
    }

    public long[] getTimeUsed() {
        return timeUsed;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setTuId(int[] tuId) {
        this.tuId = tuId;
    }

    public int[] getTuId() {
        return tuId;
    }

    public void setEquId(int[] equId) {
        this.equId = equId;
    }

    public int[] getEquId() {
        return equId;
    }
}
