/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.core.auth;

/**
 *
 * @author phuongtu
 */
public interface Permission {

    public abstract int hasPermission(int empId, String func);

    public abstract int hasPermission(int empId, String func, String type);
    public static final String TYPE_INSERT = "101";
    public static final String TYPE_UPDATE = "102";
    public static final String TYPE_DELETE = "103";
    public static final String TYPE_LIST = "104";
    public static final String TYPE_VIEW = "105";
    public static final String TYPE_MENU = "106";
}
