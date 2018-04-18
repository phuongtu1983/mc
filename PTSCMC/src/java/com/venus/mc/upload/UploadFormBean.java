/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.upload;

import org.apache.struts.upload.FormFile;

/**
 *
 * @author thcao
 */
public class UploadFormBean extends org.apache.struts.action.ActionForm {

    public FormFile theFile;
    public FormFile theFile0;
    public FormFile theFile1;
    public FormFile theFile2;
    public FormFile theFile3;
    public FormFile theFile4;
    public int ftype;
    public int pid;
    public int fid;
    public String comments;

    public UploadFormBean() {
        super();
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public int getFtype() {
        return ftype;
    }

    public void setFtype(int ftype) {
        this.ftype = ftype;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setTheFile(FormFile theFile) {
        this.theFile = theFile;
    }

    public FormFile getTheFile() {
        return theFile;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public void setTheFile0(FormFile theFile0) {
        this.theFile0 = theFile0;
    }

    public FormFile getTheFile0() {
        return theFile0;
    }

    public void setTheFile1(FormFile theFile1) {
        this.theFile1 = theFile1;
    }

    public FormFile getTheFile1() {
        return theFile1;
    }

    public void setTheFile2(FormFile theFile2) {
        this.theFile2 = theFile2;
    }

    public FormFile getTheFile2() {
        return theFile2;
    }

    public void setTheFile3(FormFile theFile3) {
        this.theFile3 = theFile3;
    }

    public FormFile getTheFile3() {
        return theFile3;
    }

    public void setTheFile4(FormFile theFile4) {
        this.theFile4 = theFile4;
    }

    public FormFile getTheFile4() {
        return theFile4;
    }
}
