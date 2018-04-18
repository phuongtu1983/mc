/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.upload.decorator;

import com.venus.core.util.GenericValidator;
import com.venus.mc.bean.FileAttachmentBean;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.displaytag.decorator.TableDecorator;

/**
 *
 * @author thcao
 */
public class FileDecorator extends TableDecorator {

    /** Creates a new instance of FileDecorator */
    public FileDecorator() {
        super();
    }

    public String getLink() {
        FileAttachmentBean fileBean = (FileAttachmentBean) getCurrentRowObject();
        String href = fileBean.getHref();
        String attName = fileBean.getAttName();
        int fid = fileBean.getAttId();
        int ftype = fileBean.getFtype();
        int pid = fileBean.getPid();
        
        HttpServletRequest request = (HttpServletRequest) getPageContext().getRequest();
        HttpSession session  = request.getSession();
        String sessionId = (String) session.getAttribute(Constants.SESSION_ID);
        String fdiv = MCUtil.getParameter("fdiv", request);
        if (GenericValidator.isBlankOrNull(fdiv)) {
            fdiv = "divAttachFileSystem";
        }
//        String str = "<input style='cursor:hand;' title='" + MCUtil.getBundleString("message.file.delete") +
//                "' type='image' src='img/attach_remove.png' onClick=\"if (confirm('" + MCUtil.getBundleString("message.file.confirm") + "')) { return removeFileAttachment(" + fid + "," + ftype + "," + pid + ");} return false;" + "\">" +
//                "<a class='my3' style='cursor:hand;' title='" + MCUtil.getBundleString("message.file.read") + "' href='" + href + "'>" +
//                "<input type='image' style='cursor:hand;' src='img/zip.gif' title='" + MCUtil.getBundleString("message.file.read") + "'>" +
//                attName + "</a>";
        String str = "<input style='cursor:hand;' title='" + MCUtil.getBundleString("message.file.delete") +
                "' type='image' src='img/attach_remove.png' onClick=\"if (confirm('" + MCUtil.getBundleString("message.file.confirm") + "')) { return removeAttachmentFile(" + fid + "," + ftype + "," + pid + ",'" + fdiv +"');} return false;" + "\">" +
                "<a class='my3' target='_blank' title='" + MCUtil.getBundleString("message.file.read") + "' href='getFile.do?fid=" + fid + "&ftype=" + ftype + "&pid=" + pid + "&session="+ sessionId + "'>" +
                "<input type='image' style='cursor:hand;' src='img/zip.gif' title='" + MCUtil.getBundleString("message.file.read") + "'>" +
                attName + "</a>";
        return str;
    }
    /*
    public String getEditLink() {
    FileAttachmentBean fileBean = (FileAttachmentBean)getCurrentRowObject();
    int fileID = fileBean.getFileID();
    int taskID = fileBean.getParentID();
    return "<a href=\"uploadFileForm.do?fileID=" + fileID
    + "&amp;taskID=" + taskID + "\">Edit</a>";
    }
    public String getSizeKB() {
    FileAttachmentBean fileBean = (FileAttachmentBean)getCurrentRowObject();
    int size = fileBean.getFileSize();
    return "" + size + "KB";
    }
    public String addRowClass() {
    return "taskinprogress";
    }
     */
}
