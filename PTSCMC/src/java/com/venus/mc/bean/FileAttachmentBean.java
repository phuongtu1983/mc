/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.bean;

/**
 *
 * @author kngo
 */
public class FileAttachmentBean {
    //fields region
    private int attId; // primary key
    private int createdEmp; // foreign key : reference to employee(emp_id)
    private int pid; // foreign key : reference to request(req_id)
    private String attName;
    private String source;
    private String href;
    private String createdDate;
    private int fileSize;
    private String comments;
    private int ftype;

    //constructure region
    public FileAttachmentBean() {
    }

    public FileAttachmentBean(int attId) {
        this.attId = attId;
    }

    public FileAttachmentBean(int attId, String attName, String source, String href, String createdDate, int fileSize, String comments) {
        this.attId = attId;
        this.attName = attName;
        this.source = source;
        this.href = href;
        this.createdDate = createdDate;
        this.fileSize = fileSize;
        this.comments = comments;
    }

    //properties region
    public int getAttId() {
        return this.attId;
    }

    public void setAttId(int attId) {
        this.attId = attId;
    }

    public int getCreatedEmp() {
        return this.createdEmp;
    }

    public void setCreatedEmp(int createdEmp) {
        this.createdEmp = createdEmp;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getAttName() {
        return this.attName;
    }

    public void setAttName(String attName) {
        this.attName = attName;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source.replace("\\", "/");
    }

    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {        
        this.href = href.replace("\\", "/");
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getFtype() {
        return ftype;
    }

    public void setFtype(int ftype) {
        this.ftype = ftype;
    }
    
}
