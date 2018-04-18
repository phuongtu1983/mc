
/// <summary>
/// Author : phuongtu
/// Created Date : 28/10/2009
/// </summary>
package com.venus.mc.bean;

public class PermissionBean {

    //fields region
    private int perId; // primary key
    private String name;
    private String employees;
    private String organizations;

    //constructure region
    public PermissionBean() {
    }

    public PermissionBean(int perId) {
        this.perId = perId;
    }

    public PermissionBean(int perId, String name) {
        this.perId = perId;
        this.name = name;
    }

    //properties region
    public int getPerId() {
        return this.perId;
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

    public String getOrganizations() {
        return organizations;
    }

    public void setOrganizations(String organizations) {
        this.organizations = organizations;
    }
}
