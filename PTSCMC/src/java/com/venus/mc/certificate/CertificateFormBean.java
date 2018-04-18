/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.certificate;

/**
 *
 * @author Mai Vinh Loc
 */
public class CertificateFormBean extends org.apache.struts.action.ActionForm {

    private int cerId; // primary key
    private String cerName;

    public CertificateFormBean() {
    }

    public int getCerId() {
        return this.cerId;
    }

    public void setCerId(int cerId) {
        this.cerId = cerId;
    }

    public String getCerName() {
        return this.cerName;
    }

    public void setCerName(String cerName) {
        this.cerName = cerName;
    }
}
