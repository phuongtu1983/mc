/*
 * LoginForm.java
 *
 * Created on December 8, 2006, 11:40 AM
 */

package com.venus.mc.auth;

/**
 *
 * @author Administrator
 * @version
 */

public class LoginForm extends org.apache.struts.action.ActionForm {
    
    /**
     *
     */
    public LoginForm() {
        super();
    }
    
    /**
     * Holds value of property nickname.
     */
    private String nickname;
    
    /**
     * Get for property nickname.
     * @return Value of property nickname.
     */
    public String getNickname() {
        return this.nickname;
    }
    
    /**
     * Set for property nickname.
     * @param nickname New value of property nickname.
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    /**
     * Holds value of property password.
     */
    private String password;
    
    /**
     * Get for property password.
     * @return Value of property password.
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
     * Set for property password.
     * @param password New value of property password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    private String md5pw;
    
    public String getMd5pw() {
        return md5pw;
    }
    
    public void setMd5pw(String md5pw) {
        this.md5pw = md5pw;
    }
    
    private String remember;
    
    public String getRemember() {
        return remember;
    }
    
    public void setRemember(String remember) {
        this.remember = remember;
    }
}
