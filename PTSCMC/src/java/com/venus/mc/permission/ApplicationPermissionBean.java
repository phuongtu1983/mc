
/// <summary>
/// Author : phuongtu
/// Created Date : 28/10/2009
/// </summary>
package com.venus.mc.permission;

import java.io.Serializable;

public class ApplicationPermissionBean implements Serializable {

    //fields region
    private int perId;
    private String name;
    private String employees;
    private String organizations;
    private String orgEmployees;
    private String function;
    private String permit;

    //constructure region
    public ApplicationPermissionBean() {
    }

    //properties region
    public int getPerId() {
        return perId;
    }

    public void setPerId(int perId) {
        this.perId = perId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployees() {
        return employees;
    }

    public void setEmployees(String employees) {
        this.employees = employees;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getOrganizations() {
        return organizations;
    }

    public void setOrganizations(String organizations) {
        this.organizations = organizations;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public String getOrgEmployees() {
        return orgEmployees;
    }

    public void setOrgEmployees(String orgEmployees) {
        this.orgEmployees = orgEmployees;
    }
}
