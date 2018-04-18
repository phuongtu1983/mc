/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.workReport;

import com.venus.core.sax.RowSAXHandler;
import com.venus.core.util.DateUtil;
import com.venus.mc.bean.FileAttachmentBean;
import com.venus.mc.bean.RequestBean;
import com.venus.mc.core.SpineReportParser;
import com.venus.mc.dao.FileAttachmentDAO;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.upload.UploadFileUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author phuongtu
 */
public class IntermemoReport extends SpineReportParser {

    private String attFileRow = "attFileRow";
    private ArrayList arrAttachmentFile;

    public IntermemoReport(String xmlTemplate, String wordTemplate, String resultFileName) {
        super(xmlTemplate, wordTemplate, resultFileName);
    }

    @Override
    protected void parse(Object object) throws Exception {
        Integer reqIdObject = (Integer) object;
        int reqId = reqIdObject.intValue();
        RequestBean bean = null;
        RequestDAO requestDAO = new RequestDAO();
        try {
            bean = requestDAO.getRequest(reqId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (bean == null) {
            return;
        }

        Date date = DateUtil.transformerStringEnDate(bean.getCreatedDate(), "/");
        SimpleDateFormat sdf = null;
        setText("mcrp_storeName", bean.getProjectNameEn());
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        setText("mcrp_createdDate", sdf.format(date));
        setText("mcrp_assignedEmp", bean.getAssignedEmpName());
        setText("mcrp_intermemoSubject", bean.getIntermemoSubject());

        Hashtable map = new Hashtable();
        RowSAXHandler row = null;
        row = new RowSAXHandler(attFileRow, this);
        arrAttachmentFile = new ArrayList();
        FileAttachmentDAO faDAO = new FileAttachmentDAO();
        String attFileName = "";
        try {
            ArrayList faList = null;
            faList = faDAO.getAttachmentFiles(UploadFileUtil.ATTACH_FILE_REQUEST, reqId);
            FileAttachmentBean faBean = null;
            for (int i = 0; i < faList.size(); i++) {
                attFileName = "";
                faBean = (FileAttachmentBean) faList.get(i);
                attFileName += (i + 1) + "/  " + faBean.getAttName();
                arrAttachmentFile.add(attFileName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        row.setRowCount(arrAttachmentFile.size());
        map.put(attFileRow, row);

        this.setArrTable(map);
    }

    private String getAttachmentFileText(int i, String tab) {
        String text = "";
        if (i < arrAttachmentFile.size()) {
            if (tab.equals("mcrp_attachmentFile")) {
                text = (String) arrAttachmentFile.get(i);
            }
        }
        return text;
    }

    @Override
    public String getTabText(String rowId, int i, String tab) {
        if (rowId.equals(attFileRow)) {
            return getAttachmentFileText(i, tab);
        }
        return "";
    }
}
