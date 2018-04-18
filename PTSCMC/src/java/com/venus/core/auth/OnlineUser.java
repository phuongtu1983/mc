/*
 * OnlineUser.java
 *
 * Created on March 21, 2007, 10:30 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.venus.core.auth;

import java.sql.Timestamp;

/**
 *
 * @author Administrator
 */
public interface OnlineUser {
    
    public abstract int getID();

    public abstract String getName();
    
    public abstract String getFullName();    
    
    public abstract int getOrganizationID();
    
    public abstract String getOrganizationName();
    
    public abstract String getEmail(); 

    public abstract Timestamp getLastLogonTimestamp();

    public abstract String getLastLogonIP();
    
    public abstract boolean hasPermission(String func, String type);
}
