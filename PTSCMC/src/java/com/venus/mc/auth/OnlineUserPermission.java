
/// <summary>
/// Author : phuongtu
/// Created Date : 28/10/2009
/// </summary>
package com.venus.mc.auth;

public class OnlineUserPermission {

    //fields region
    private int userId;
    private String function;
    private int permission;


    //constructure region
    public OnlineUserPermission() {
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
