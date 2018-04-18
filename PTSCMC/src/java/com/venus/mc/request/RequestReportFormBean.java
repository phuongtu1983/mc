/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.request;

/**
 *
 * @author phuongtu
 */
public class RequestReportFormBean extends org.apache.struts.action.ActionForm {

    private int orgId;

    //constructure region
    public RequestReportFormBean() {
        super();
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getOrgId() {
        return orgId;
    }
}
