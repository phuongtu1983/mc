
/// <summary>
/// Author : kngo
/// Created Date : 05/10/2009
/// </summary>
package com.venus.mc.bean;

public class InventoryBean {

    private int invId; // primary key
    private int stoId; // foreign key : reference to store(sto_id)
    private String stoName;
    private String invDate;
    private int createdEmp; // foreign key : reference to employee(emp_id)
    private String empName;
    private String comment;

    //constructure region
    public InventoryBean() {
    }

    public InventoryBean(int invId) {
        this.invId = invId;
    }

    public InventoryBean(int invId, String invDate, String comment) {
        this.invId = invId;
        this.invDate = invDate;
        this.comment = comment;
    }

    //properties region
    public int getInvId() {
        return this.invId;
    }

    public void setInvId(int invId) {
        this.invId = invId;
    }

    public int getStoId() {
        return this.stoId;
    }

    public void setStoId(int stoId) {
        this.stoId = stoId;
    }

    public String getStoName() {
        return this.stoName;
    }

    public void setStoName(String stoName) {
        this.stoName = stoName;
    }

    public String getInvDate() {
        return this.invDate;
    }

    public void setInvDate(String invDate) {
        this.invDate = invDate;
    }

    public int getCreatedEmp() {
        return this.createdEmp;
    }

    public void setCreatedEmp(int creator) {
        this.createdEmp = creator;
    }

    public String getComment() {
        return this.comment;
    }

    public String getEmpName() {
        return this.empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
