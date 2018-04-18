package com.venus.mc.auth;

/**
 * @author phuongtu
 */
public class ResetPasswordForm extends org.apache.struts.action.ActionForm {

    private String username;
    private String email;

    /**
     *
     */
    public ResetPasswordForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
