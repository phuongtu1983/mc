/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.techeval;

import com.venus.core.util.DateUtil;
import com.venus.core.util.FileUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.TechEvalBean;
import com.venus.mc.bean.TechEvalDetailBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.bean.TenderPlanCertificateBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.TechEvalDAO;
import com.venus.mc.dao.TechEvalVendorDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.tenderplan.TenderPlanFormBean;
import com.venus.mc.util.MCUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuontu
 * @version
 */
public class TechEvalPrintAction extends BaseAction {

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public boolean doAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        if (!GenericValidator.isBlankOrNull(request.getParameter("teId"))) {
            ArrayList arrVendor = null;
            TechEvalVendorDAO dao = new TechEvalVendorDAO();
            int teId = NumberUtil.parseInt(request.getParameter("teId"), 0);
            int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
            int tenId = 0;
            try {
                TechEvalDAO teDAO = new TechEvalDAO();
                TechEvalBean teBean = teDAO.getTechEvalById(teId);
                if (teBean != null) {
                    tenId = teBean.getTenId();
                }
            } catch (Exception ex) {
            }
            try {
                arrVendor = dao.getTechEvalVendor(teId, kind);
            } catch (Exception ex) {
            }
            if (arrVendor == null) {
                arrVendor = new ArrayList();
            }
            TechEvalDetailBean vendor = null;
            ArrayList materialDetails = null;
            String templateFileName = "";
            if (kind == TenderPlanFormBean.EVAL_KIND_ALL) {
                templateFileName = request.getSession().getServletContext().getRealPath("/templates/BM.08.02.TM-Bang danh gia ky thuat - Tron goi.xls");
            } else {
                templateFileName = request.getSession().getServletContext().getRealPath("/templates/BM.08.02.TM-Bang danh gia ky thuat - Tung phan.xls");
            }
            String tempFileName = request.getSession().getServletContext().getRealPath("/templates/" + MCUtil.getMemberName(request.getSession()) + System.currentTimeMillis() + ".xls");
            try {
                if (arrVendor.size() > 0) {
                    vendor = (TechEvalDetailBean) arrVendor.get(0);
                    //tenId = vendor.getTenId();
                    try {
                        materialDetails = dao.getTechEvalMaterial(teId, vendor.getTerId(), kind);
                    } catch (Exception ex) {
                    }
                }
                if (materialDetails == null) {
                    materialDetails = new ArrayList();
                }
//                removeMaterialNotReach(materialDetails);
                short[] hiddenCols = null;
                File f = FileUtil.createFile(tempFileName);
                if (kind == TenderPlanFormBean.EVAL_KIND_ALL) {
                    createColumnAll(templateFileName, arrVendor, materialDetails.size(), f);
                    hiddenCols = new short[]{(short) 5, (short) 6, (short) 7};
                } else {
                    createColumnPart(templateFileName, arrVendor, materialDetails.size(), f);
                    hiddenCols = new short[]{(short) 5, (short) 6, (short) 7};
                }

                Map beans = new HashMap();
                for (int i = 0; i < arrVendor.size(); i++) {
                    vendor = (TechEvalDetailBean) arrVendor.get(i);
                    try {
                        materialDetails = dao.getTechEvalMaterial(teId, vendor.getTerId(), kind);
                    } catch (Exception ex) {
                    }
                    if (materialDetails == null) {
                        materialDetails = new ArrayList();
                    }
                    // removeMaterialNotReach(materialDetails);
                    if (i == 0) {//lan dau do du lieu
                        beans.put("material", materialDetails);
                    }
                    beans.put("vendor" + vendor.getVenId() + "in", materialDetails);
                }

                Date date = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "/");
                SimpleDateFormat sdf = null;
                sdf = new SimpleDateFormat("dd");
                beans.put("tecEvalDate", sdf.format(date));
                sdf = new SimpleDateFormat("MM");
                beans.put("tecEvalMonth", sdf.format(date));
                sdf = new SimpleDateFormat("yyyy");
                beans.put("tecEvalYear", sdf.format(date));
                TenderPlanBean tender = null;
                try {
                    TenderPlanDAO tenderDAO = new TenderPlanDAO();
                    tender = tenderDAO.getTenderPlan(tenId);
                } catch (Exception ex) {
                }
                if (tender == null) {
                    tender = new TenderPlanBean();
                }

                beans.put("packName", (tender.getPackName()).toUpperCase());
                //beans.put("project", (getProjectName(materialDetails)).toUpperCase());
                beans.put("project", "");
                beans.put("tenderPlanCertificate", getTenderPlanCertificate(tender.getTenId()));
                if (Integer.parseInt(tender.getForm()) == TenderPlanFormBean.FORM_PRIVATE) {
                    beans.put("groupEmployee", MCUtil.getBundleString("message.techeval.print.group"));
                    TechEvalDAO techDAO = new TechEvalDAO();
                    ArrayList arrEmployee = techDAO.getTechEvalGroup(tenId);
                    beans.put("teg", arrEmployee);
                } else {
                    beans.put("sign1", "TRƯỞNG PHÒNG KẾ HOẠCH");
                    beans.put("sign2", "PHÓ PHÒNG KẾ HOẠCH");
                    beans.put("sign3", "NGƯỜI LẬP BẢNG");
                }
                ExcelExport exporter = new ExcelExport();
                exporter.setBeans(beans);
                exporter.setHiddenCols(hiddenCols);
                long milis = System.currentTimeMillis();
                exporter.export(request, response, tempFileName, "BM.08.02.TM-Bang danh gia ky thuat.xls");
                milis = System.currentTimeMillis() - milis;
                System.out.println("BM.08.02.TM-Bang danh gia ky thuat.xls : " + "time : " + (int) ((milis / 1000) % 60) + " phut " + (int) ((milis / 1000) / 60) + " giay");
                f.delete();
            } catch (Exception ex) {
                LogUtil.error("FAILED:ComEvalPrint:print-" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return true;
    }

    private void createColumnAll(String templateFileName, ArrayList arrVendor, int rowSize, File f) throws Exception {
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(templateFileName));
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFCell newCell = null;
        TechEvalDetailBean vendor = null;
        short col = 8;
        short border = sheet.getRow(1).getCell(1).getCellStyle().getBorderLeft();
        short color = sheet.getRow(1).getCell(1).getCellStyle().getLeftBorderColor();
        for (int i = 0; i < arrVendor.size(); i++) {
            vendor = (TechEvalDetailBean) arrVendor.get(i);
            //copy header
            newCell = copyCell(wb, sheet, 1, 5, col, "");
            newCell.setCellValue(new HSSFRichTextString(vendor.getVendorName()));
            ExcelExport.setBorder(wb, sheet, 1, col + 1, border, color);
            ExcelExport.setBorder(wb, sheet, 1, col + 2, border, color);
//
            //copy header Chao hang ky thuat
            copyCell(wb, sheet, 2, 5, col, "");
            //copy header Thoi gian giao hang
            copyCell(wb, sheet, 2, 6, col + 1, "");
            //copy header Danh gia
            copyCell(wb, sheet, 2, 7, col + 2, "");
//
            ExcelExport.setBorder(wb, sheet, 3, col, border, color);
            ExcelExport.setBorder(wb, sheet, 3, col + 1, border, color);
            ExcelExport.setBorder(wb, sheet, 3, col + 2, border, color);

            //copy content Chao hang ky thuat
            copyCell(wb, sheet, 4, 5, col, vendor.getVenId() + "");
            //copy content Thoi gian giao hang
            copyCell(wb, sheet, 4, 6, col + 1, vendor.getVenId() + "");
            //copy content Danh gia
            copyCell(wb, sheet, 4, 7, col + 2, vendor.getVenId() + "");

            ExcelExport.setBorder(wb, sheet, 5, col, border, color);
            ExcelExport.setBorder(wb, sheet, 5, col + 1, border, color);
            ExcelExport.setBorder(wb, sheet, 5, col + 2, border, color);

            copyCell(wb, sheet, 5, 5, col, "");
            copyCell(wb, sheet, 5, 6, col + 1, "");
            copyCell(wb, sheet, 5, 7, col + 2, "");

            copyCell(wb, sheet, 6, 5, col, "");
            copyCell(wb, sheet, 6, 6, col + 1, "");
            copyCell(wb, sheet, 6, 7, col + 2, "");

            copyCell(wb, sheet, 7, 5, col, "");
            copyCell(wb, sheet, 7, 6, col + 1, "");
            copyCell(wb, sheet, 7, 7, col + 2, "");

            //copy Chung chi kem theo
            newCell = copyCell(wb, sheet, 6, 2, col, "");
            copyCell(wb, sheet, 6, 3, col + 1, "");
            copyCell(wb, sheet, 6, 4, col + 2, "");
            newCell.setCellValue(new HSSFRichTextString(vendor.getCertificateAttach()));
//            ExcelExport.setBorder(wb, sheet, 6, col, border, color);
//            ExcelExport.setBorder(wb, sheet, 6, col + 1, border, color);
//            ExcelExport.setBorder(wb, sheet, 6, col + 2, border, color);
            //copy Ket luan
            newCell = copyCell(wb, sheet, 7, 2, col, "");
            copyCell(wb, sheet, 7, 3, col + 1, "");
            copyCell(wb, sheet, 7, 4, col + 2, "");
            newCell.setCellValue(new HSSFRichTextString(vendor.getResult()));
//            ExcelExport.setBorder(wb, sheet, 7, col, border, color);
//            ExcelExport.setBorder(wb, sheet, 7, col + 1, border, color);
//            ExcelExport.setBorder(wb, sheet, 7, col + 2, border, color);

            sheet.setColumnWidth(col, sheet.getColumnWidth(5));
            sheet.setColumnWidth(col + 1, sheet.getColumnWidth(6));
            sheet.setColumnWidth(col + 2, sheet.getColumnWidth(7));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, col, col + 2));
            sheet.addMergedRegion(new CellRangeAddress(3, 3, col, col + 2));
//            sheet.addMergedRegion(new CellRangeAddress(4 + rowSize + 1, 4 + rowSize + 1, col, col + 2));
//            sheet.addMergedRegion(new CellRangeAddress(5 + rowSize + 1, 5 + rowSize + 1, col, col + 2));
//            sheet.addMergedRegion(new CellRangeAddress(6 + rowSize + 1, 6 + rowSize + 1, col, col + 2));
            col += 3;
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4 + arrVendor.size() * 3 + 3));
        FileOutputStream fileOut = new FileOutputStream(f);
        wb.write(fileOut);
        fileOut.close();
    }

    private void createColumnPart(String templateFileName, ArrayList arrVendor, int rowSize, File f) throws Exception {
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(templateFileName));
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFCell newCell = null;
        TechEvalDetailBean vendor = null;
        short col = 8;
        short border = sheet.getRow(1).getCell(1).getCellStyle().getBorderLeft();
        short color = sheet.getRow(1).getCell(1).getCellStyle().getLeftBorderColor();
        for (int i = 0; i < arrVendor.size(); i++) {
            vendor = (TechEvalDetailBean) arrVendor.get(i);
            //copy header
            newCell = copyCell(wb, sheet, 1, 5, col, "");
            newCell.setCellValue(new HSSFRichTextString(vendor.getVendorName()));
            ExcelExport.setBorder(wb, sheet, 1, col + 1, border, color);
            ExcelExport.setBorder(wb, sheet, 1, col + 2, border, color);
//            ExcelExport.setBorder(wb, sheet, 1, col + 3, border, color);

            //copy header Chao hang ky thuat
            copyCell(wb, sheet, 2, 5, col, "");
            //copy header Thoi gian giao hang
            copyCell(wb, sheet, 2, 6, col + 1, "");

//            //copy header Dieu kien khac
//            copyCell(wb, sheet, 2, 7, col + 2, "");
            //copy header Danh gia
            copyCell(wb, sheet, 2, 7, col + 2, "");

            ExcelExport.setBorder(wb, sheet, 3, col, border, color);
            ExcelExport.setBorder(wb, sheet, 3, col + 1, border, color);
            ExcelExport.setBorder(wb, sheet, 3, col + 2, border, color);
//            ExcelExport.setBorder(wb, sheet, 3, col + 3, border, color);
            //copy content Chao hang ky thuat
            copyCell(wb, sheet, 4, 5, col, vendor.getVenId() + "");
            //copy content Thoi gian giao hang
            copyCell(wb, sheet, 4, 6, col + 1, vendor.getVenId() + "");
//            //copy content Dieu kien khac
//            copyCell(wb, sheet, 4, 7, col + 2, vendor.getVenId() + "");
            //copy content Danh gia
            copyCell(wb, sheet, 4, 7, col + 2, vendor.getVenId() + "");

            //copy Chung chi kem theo
            newCell = copyCell(wb, sheet, 6, 2, col, "");
            copyCell(wb, sheet, 6, 3, col + 1, "");
            copyCell(wb, sheet, 6, 4, col + 2, "");
            newCell.setCellValue(new HSSFRichTextString(vendor.getCertificateAttach()));

            //copy Chung chi kem theo
            newCell = copyCell(wb, sheet, 6, 2, col, "");
            copyCell(wb, sheet, 6, 3, col + 1, "");
            copyCell(wb, sheet, 6, 4, col + 2, "");
            newCell.setCellValue(new HSSFRichTextString(vendor.getCertificateAttach()));

            //copy Chung chi kem theo
            newCell = copyCell(wb, sheet, 6, 5, col, "");
            newCell.setCellValue(new HSSFRichTextString(vendor.getCertificateAttach()));

            copyCell(wb, sheet, 5, 5, col, "");
            copyCell(wb, sheet, 5, 6, col + 1, "");
            copyCell(wb, sheet, 5, 7, col + 2, "");


//            copyCell(wb, sheet, 6, 5, col, "");



            copyCell(wb, sheet, 6, 6, col + 1, "");
            copyCell(wb, sheet, 6, 7, col + 2, "");

            sheet.setColumnWidth(col, sheet.getColumnWidth(5));
            sheet.setColumnWidth(col + 1, sheet.getColumnWidth(6));
            sheet.setColumnWidth(col + 2, sheet.getColumnWidth(7));
//            sheet.setColumnWidth(col + 3, sheet.getColumnWidth(8));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, col, col + 2));
            sheet.addMergedRegion(new CellRangeAddress(3, 3, col, col + 2));
//            sheet.addMergedRegion(new CellRangeAddress(4 + rowSize + 1, 4 + rowSize + 1, col, col + 2));
//            sheet.addMergedRegion(new CellRangeAddress(5 + rowSize + 1, 5 + rowSize + 1, col, col + 2));
//            sheet.addMergedRegion(new CellRangeAddress(6 + rowSize + 1, 6 + rowSize + 1, col, col + 2));
            col += 3;
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4 + arrVendor.size() * 3 + 3));
        FileOutputStream fileOut = new FileOutputStream(f);
        wb.write(fileOut);
        fileOut.close();
    }

    private String copyCellValue(String value, String content) {
        String result = "";
        int ind = value.indexOf("${");
        if (ind == 0) {
            ind = value.indexOf(".");
            if (ind > -1) {
                result = "${vendor" + content + "in" + value.substring(ind);
            }
        }
        return result;
    }

    private HSSFCell copyCell(HSSFWorkbook wb, HSSFSheet sheet, int rowNum, int col, int newCol, String content) {
        HSSFRow row = sheet.getRow(rowNum);
        HSSFCell cell = row.getCell(col);
        HSSFCell newCell = row.createCell(newCol);
        ExcelExport.copyStyle(wb, cell, newCell);
        if (GenericValidator.isBlankOrNull(content)) {
            newCell.setCellValue(cell.getRichStringCellValue());
        } else {
            newCell.setCellValue(new HSSFRichTextString(copyCellValue(cell.getRichStringCellValue().getString(), content)));
        }
        return newCell;
    }

    private String getProjectName(ArrayList materialDetails) {
        String request = ",";
        TechEvalDetailBean bean = null;
        for (int i = 0; i < materialDetails.size(); i++) {
            bean = (TechEvalDetailBean) materialDetails.get(i);
            if (request.indexOf("," + bean.getProjectName() + ",") == -1) {
                request += bean.getProjectName() + ",";
            }
        }
        if (!request.equals(",")) {
            request = request.substring(1, request.length() - 1);
        }
        return request;
    }

    private String getTenderPlanCertificate(int tenId) {
        String certificate = "";
        ArrayList arrCertificate = null;
        try {
            TenderPlanDAO tenderDAO = new TenderPlanDAO();
            arrCertificate = tenderDAO.getTenderPlanCertificate(tenId);
        } catch (Exception ex) {
        }
        if (arrCertificate == null) {
            arrCertificate = new ArrayList();
        }
        TenderPlanCertificateBean bean = null;
        for (int i = 0; i < arrCertificate.size(); i++) {
            bean = (TenderPlanCertificateBean) arrCertificate.get(i);
            certificate += ',' + bean.getCerName();
        }
        if (!certificate.equals("")) {
            certificate = certificate.substring(1);
        }
        return certificate;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }

    private void removeMaterialNotReach(ArrayList arrMaterial) {
        try {
            TechEvalDetailBean material = null;
            TechEvalVendorDAO dao = new TechEvalVendorDAO();
            for (int i = arrMaterial.size() - 1; i >= 0; i--) {
                material = (TechEvalDetailBean) arrMaterial.get(i);
                if (dao.isMaterialReached(material.getDetIdTp(), material.getKind()) == 0) {
                    arrMaterial.remove(material);
                }
            }
        } catch (Exception ex) {
        }
    }
}
