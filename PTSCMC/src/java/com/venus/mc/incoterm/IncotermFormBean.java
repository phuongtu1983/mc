/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.incoterm;


/**
 *
 * @author Mai Vinh Loc
 */
public class IncotermFormBean extends org.apache.struts.action.ActionForm {

   private String incName;
    private int incId;
    private String comment;

    public IncotermFormBean() {
    }

    public int getIncId() {
        return incId;
    }

    public void setIncId(int incId) {
        this.incId = incId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public String getIncName() {
        return incName;
    }

    public void setIncName(String incName) {
        this.incName = incName;
    }
}
