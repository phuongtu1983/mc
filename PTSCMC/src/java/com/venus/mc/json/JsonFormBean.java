/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.json;

/**
 *
 * @author phuongtu
 */
public class JsonFormBean extends org.apache.struts.action.ActionForm {

    private String message;

    public JsonFormBean() {
        super();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
