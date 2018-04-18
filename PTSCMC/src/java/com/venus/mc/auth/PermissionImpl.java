/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.auth;

import com.venus.core.auth.Permission;

/**
 *
 * @author phuongtu
 */
public class PermissionImpl implements Permission{

    public int hasPermission(int empId, String func) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int hasPermission(int empId, String func, String type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static final String FUNC_MATERIAL = "101";

}
