/*
 * OnlineUserImpl.java
 *
 * Created on March 21, 2007, 4:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.venus.mc.auth;

import com.venus.core.auth.OnlineUser;
import java.sql.Timestamp;

/**
 *
 * @author Administrator
 */
public class OnlineUserImpl implements OnlineUser {

    private int memberID;
    private String memberName;
    private String fullName;
    private int organizationID;
    private String organizationName;
    private String email;
    private String lastLogonIP;
    private Timestamp lastLogonTimestamp;

    /** Creates a new instance of OnlineUserImpl */
    public OnlineUserImpl() {
    }

    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
    }

    public int getOrganizationID() {
        return organizationID;
    }

    public void setID(int memberID) {
        this.memberID = memberID;
    }

    public int getID() {
        return memberID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setName(String memberName) {
        this.memberName = memberName;
    }

    public String getName() {
        return memberName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getLastLogonIP() {
        return lastLogonIP;
    }

    public Timestamp getLastLogonTimestamp() {
        return lastLogonTimestamp;
    }

    public void setLastLogonIP(String lastLogonIP) {
        this.lastLogonIP = lastLogonIP;
    }

    public void setLastLogonTimestamp(Timestamp lastLogonTimestamp) {
        this.lastLogonTimestamp = lastLogonTimestamp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean hasPermission(String func, String type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
