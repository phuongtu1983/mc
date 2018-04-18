
/// <summary>
/// Author : phuongtu
/// Created Date : 08/04/2010
/// </summary>
package com.venus.mc.permission;

public class PermissionViewBean extends org.apache.struts.action.ActionForm {

    //fields region
    private int value;
    private String name;
    private String counter;
    private int level;
    private int sharedId;

    //constructure region
    public PermissionViewBean() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSharedId() {
        return sharedId;
    }

    public void setSharedId(int sharedId) {
        this.sharedId = sharedId;
    }
}
