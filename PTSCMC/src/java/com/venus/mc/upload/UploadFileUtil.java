/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.upload;

import com.venus.core.util.FileUtil;
import com.venus.mc.bean.FileAttachmentBean;
import com.venus.mc.dao.FileAttachmentDAO;
import java.util.ArrayList;

/**
 *
 * @author thcao
 */
public class UploadFileUtil {

    public static final int ATTACH_FILE_START = 0;
    public static final int ATTACH_FILE_REQUEST = 1;
    public static final int ATTACH_FILE_TENDERPLAN = 2;
    public static final int ATTACH_FILE_TENDERLETTER = 3;
    public static final int ATTACH_FILE_CONTRACT = 4;
    public static final int ATTACH_FILE_BIDCLOSE = 5;
    public static final int ATTACH_FILE_BIDOPEN = 6;
    public static final int ATTACH_FILE_TECHEVAL = 7;
    public static final int ATTACH_FILE_COMEVAL = 8;
    public static final int ATTACH_FILE_BIDSUM = 9;
    //public static final int ATTACH_FILE_REQUIREREPAIR = 10;
    public static final int ATTACH_FILE_REQUIREVERIFIED = 11;
    public static final int ATTACH_FILE_REQUIRETRANSFER = 12;
    public static final int ATTACH_FILE_END = 30;
    //Loc
    public static final int ATTACH_FILE_EQUIPMENT = 20;
    public static final int ATTACH_FILE_HANDED_REPORT = 13;
    public static final int ATTACH_FILE_REPORT_DAMAGE = 22;
    public static final int ATTACH_FILE_REQUIRE_REPAIR = 23;
    public static final int ATTACH_FILE_SURVEY_REPORT = 24;
    public static final int ATTACH_FILE_REQUIRE_SETTLING = 25;
    public static final int ATTACH_FILE_ACCEPTANCE_TEST = 26;

    public static String getTableName(int ftype) {
        String tableName = "";
        if (ftype == ATTACH_FILE_REQUEST) {
            tableName = "request_file_attachment";
        } else if (ftype == ATTACH_FILE_TENDERPLAN) {
            tableName = "tender_plan_file_attachment";
        } else if (ftype == ATTACH_FILE_TENDERLETTER) {
            tableName = "tender_letter_file_attachment";
        } else if (ftype == ATTACH_FILE_CONTRACT) {
            tableName = "contract_file_attachment";
        } else if (ftype == ATTACH_FILE_BIDCLOSE) {
            tableName = "bid_closing_file_attachment";
        } else if (ftype == ATTACH_FILE_BIDOPEN) {
            tableName = "bid_opening_file_attachment";
        } else if (ftype == ATTACH_FILE_TECHEVAL) {
            tableName = "tech_eval_file_attachment";
        } else if (ftype == ATTACH_FILE_COMEVAL) {
            tableName = "com_eval_file_attachment";
        } else if (ftype == ATTACH_FILE_BIDSUM) {
            tableName = "bid_eval_sum_file_attachment";
        } else if (ftype == ATTACH_FILE_REQUIREVERIFIED) {
            tableName = "require_verified_file_attachment";
        } else if (ftype == ATTACH_FILE_REQUIRETRANSFER) {
            tableName = "require_transfer_file_attachment";
        } else if (ftype == ATTACH_FILE_EQUIPMENT) {
            tableName = "equ_file_attachment";
        } else if (ftype == ATTACH_FILE_HANDED_REPORT) {
            tableName = "handed_report_file_attachment";
        } else if (ftype == ATTACH_FILE_REPORT_DAMAGE) {
            tableName = "report_damage_file_attachment";
        } else if (ftype == ATTACH_FILE_REQUIRE_REPAIR) {
            tableName = "require_repair_file_attachment";
        } else if (ftype == ATTACH_FILE_SURVEY_REPORT) {
            tableName = "survey_report_file_attachment";
        } else if (ftype == ATTACH_FILE_REQUIRE_SETTLING) {
            tableName = "require_settling_file_attachment";
        } else if (ftype == ATTACH_FILE_ACCEPTANCE_TEST) {
            tableName = "acceptance_test_file_attachment";
        }

        return tableName;
    }

    public static boolean isValidFType(int ftype) {
        return (ftype > ATTACH_FILE_START
                && ftype < ATTACH_FILE_END);
    }

    public static String getUploadFolder(int ftype) {
        String strFolder = "private";
        switch (ftype) {
            case ATTACH_FILE_REQUEST:
                strFolder = "request";
                break;
            case ATTACH_FILE_TENDERPLAN:
                strFolder = "tenderplan";
                break;
            case ATTACH_FILE_TENDERLETTER:
                strFolder = "tenderletter";
                break;
            case ATTACH_FILE_CONTRACT:
                strFolder = "contract";
                break;
            case ATTACH_FILE_BIDOPEN:
                strFolder = "bidopen";
                break;
            case ATTACH_FILE_BIDCLOSE:
                strFolder = "bidclose";
                break;
            case ATTACH_FILE_TECHEVAL:
                strFolder = "techeval";
                break;
            case ATTACH_FILE_COMEVAL:
                strFolder = "comeval";
                break;
            case ATTACH_FILE_BIDSUM:
                strFolder = "bidsum";
                break;
            case ATTACH_FILE_REQUIREVERIFIED:
                strFolder = "requireverified";
                break;
            case ATTACH_FILE_REQUIRETRANSFER:
                strFolder = "requiretransfer";
                break;
            case ATTACH_FILE_EQUIPMENT:
                strFolder = "equipment";
                break;
            case ATTACH_FILE_HANDED_REPORT:
                strFolder = "handed_report";
                break;
            case ATTACH_FILE_REPORT_DAMAGE:
                strFolder = "report_damage";
                break;
            case ATTACH_FILE_REQUIRE_REPAIR:
                strFolder = "require_repair";
                break;
            case ATTACH_FILE_SURVEY_REPORT:
                strFolder = "survey_report";
                break;
            case ATTACH_FILE_REQUIRE_SETTLING:
                strFolder = "require_settling";
                break;
            case ATTACH_FILE_ACCEPTANCE_TEST:
                strFolder = "acceptance_test";
                break;
        }
        return strFolder;
    }

    public static int deleteFile(int ftype, int pid, int fid) throws Exception {
        int res = 0;
        if (isValidFType(ftype)) {
            FileAttachmentDAO fileAttachmentDAO = new FileAttachmentDAO();
            FileAttachmentBean fileBean = fileAttachmentDAO.getAttachmentFile(ftype, fid);
            FileUtil.deleteFile(fileBean.getSource());
            res = fileAttachmentDAO.deleteAttachmentFile(ftype, fid);
        }
        return res;
    }

    public static int deleteFiles(int ftype, int pid) throws Exception {
        int res = 0;
        if (isValidFType(ftype)) {
            FileAttachmentDAO fileAttachmentDAO = new FileAttachmentDAO();
            ArrayList arrFiles = fileAttachmentDAO.getAttachmentFiles(ftype, pid);
            res = arrFiles.size();
            for (int i = 0; i < res; i++) {
                FileAttachmentBean fileBean = (FileAttachmentBean) arrFiles.get(i);
                FileUtil.deleteFile(fileBean.getSource());
                fileAttachmentDAO.deleteAttachmentFile(ftype, fileBean.getAttId());
            }
        }
        return res;
    }
}
