/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class WebBean {
    private int webId; // primary key
    private String address;
    private String content;
    private String comment;
    private String evaluation;

    public WebBean() {
    }

    public WebBean(int webId) {
        this.webId = webId;
    }

    public WebBean(int webId, String address, String content, String comment, String evaluation) {
        this.webId = webId;
        this.address = address;
        this.content = content;
        this.comment = comment;
        this.evaluation = evaluation;
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
