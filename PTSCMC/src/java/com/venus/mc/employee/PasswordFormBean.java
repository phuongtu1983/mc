/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.employee;

/**
 *
 * @author phuongtu
 */
public class PasswordFormBean extends org.apache.struts.action.ActionForm {

    private String password;
    private String newpassword;
    private String retypepassword;

    public PasswordFormBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getNewpassword() {
        return newpassword;
    }

    public String getPassword() {
        return password;
    }

    public String getRetypepassword() {
        return retypepassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRetypepassword(String retypepassword) {
        this.retypepassword = retypepassword;
    }
}
