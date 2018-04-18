/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.web;

/**
 *
 * @author kngo
 */
public class WebFormBean extends org.apache.struts.action.ActionForm {

    private int webId; // primary key
    private String address;
    private String content;
    private String comment;
    private String evaluation;

    public WebFormBean() {
    }

    public int getWebId() {
        return this.webId;
    }

    public void setWebId(int webId) {
        this.webId = webId;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getEvaluation() {
        return this.evaluation;
    }
}
