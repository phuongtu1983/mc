/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.employee;

/**
 *
 * @author kngo
 */
public class EmployeeFormBean extends org.apache.struts.action.ActionForm {

    private int empId; // primary key
    private int orgId; // foreign key : reference to organization(org_id)
    private int posId; // foreign key : reference to position(pos_id)
    private String code;
    private String fullname;
    private String nickname;
    private String password;
    private String email;
    private String officePhone;
    private String handPhone;
    private String homePhone;
    private int status;
    private String createdDate;
    private String modifiedDate;
    private String lastLogonDate;
    private String firstIp;
    private String lastIp;
    private String orgName;
    private String posName;

    //constructure region
    public EmployeeFormBean() {
    }

    public int getEmpId() {
        return this.empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getOrgId() {
        return this.orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getPosId() {
        return this.posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficePhone() {
        return this.officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getHandPhone() {
        return this.handPhone;
    }

    public void setHandPhone(String handPhone) {
        this.handPhone = handPhone;
    }

    public String getHomePhone() {
        return this.homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getLastLogonDate() {
        return this.lastLogonDate;
    }

    public void setLastLogonDate(String lastLogonDate) {
        this.lastLogonDate = lastLogonDate;
    }

    public String getFirstIp() {
        return this.firstIp;
    }

    public void setFirstIp(String firstIp) {
        this.firstIp = firstIp;
    }

    public String getLastIp() {
        return this.lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
