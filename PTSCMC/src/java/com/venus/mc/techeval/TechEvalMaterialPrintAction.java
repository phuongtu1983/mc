/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.techeval;

import com.venus.core.util.DateUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.TechEvalDetailBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.TechEvalVendorDAO;
import com.venus.mc.util.MCUtil;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
public class TechEvalMaterialPrintAction extends BaseAction {

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
            int kind = 0;
            try {
                arrVendor = dao.getTechEvalVendor(teId, kind);
            } catch (Exception ex) {
            }
            if (arrVendor == null) {
                arrVendor = new ArrayList();
            }
            TechEvalDetailBean vendor = null;
            ArrayList materialDetails = null;
            String templateFileName = request.getSession().getServletContext().getRealPath("/templates/BM.08.02.TM-Bang danh gia ky thuat.xls");
            String tempFileName = request.getSession().getServletContext().getRealPath("/templates/Book1.xls");
            try {
                if (arrVendor.size() > 0) {
                    vendor = (TechEvalDetailBean) arrVendor.get(0);
                    try {
                        materialDetails = dao.getTechEvalMaterial(teId, vendor.getTerId(), kind);
                    } catch (Exception ex) {
                    }
                }
                if (materialDetails == null) {
                    materialDetails = new ArrayList();
                }
                createColumn(templateFileName, tempFileName, arrVendor, materialDetails.size());
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
                    if (i == 0) {//lan dau do du lieu
                        beans.put("material", materialDetails);
                    }
                    beans.put("vendor" + vendor.getVenId(), materialDetails);
                }
                Date date = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "/");
                beans.put("tecEvalDate", DateUtil.formatDate(date, "dd"));
                beans.put("tecEvalMonth", DateUtil.formatDate(date, "dd"));
                beans.put("tecEvalYear", DateUtil.formatDate(date, "dd"));
//                if (Integer.parseInt(tenderplan.getForm()) == TenderPlanFormBean.FORM_PRIVATE) {
//                    beans.put("groupEmployee", MCUtil.getBundleString("message.techeval.print.group"));
//                    TechEvalVendorDAO techEvalDAO = new TechEvalVendorDAO();
//                    ArrayList arrEmployee = techEvalDAO.get(tenderplan.getTenId());
//                    beans.put("teg", arrEmployee);
//                } else {
//                    beans.put("sign1", "TRƯỞNG PHÒNG KẾ HOẠCH");
//                    beans.put("sign2", "PHÓ PHÒNG KẾ HOẠCH");
//                    beans.put("sign3", "NGƯỜI LẬP BẢNG");
//                }
                ExcelExport exporter = new ExcelExport();
                exporter.setBeans(beans);
                short[] hiddenCols = new short[]{(short) 5, (short) 6, (short) 7, (short) 8};
                exporter.setHiddenCols(hiddenCols);
                long milis = System.currentTimeMillis();
                exporter.export(request, response, tempFileName);
                milis = System.currentTimeMillis() - milis;
                System.out.println(tempFileName + "time : " + (int) ((milis / 1000) % 60) + " phut " + (int) ((milis / 1000) / 60) + " giay");
            } catch (Exception ex) {
                LogUtil.error("FAILED:ComEvalPrint:print-" + ex.getMessage());
                ex.printStackTrace();
            }
        }

        return true;
    }

    private void createColumn(String templateFileName, String tempFileName, ArrayList arrVendor, int rowSize) throws Exception {
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(templateFileName));
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFCell newCell = null;
        TechEvalDetailBean vendor = null;
        short col = 9;
        short border = sheet.getRow(1).getCell(1).getCellStyle().getBorderLeft();
        short color = sheet.getRow(1).getCell(1).getCellStyle().getLeftBorderColor();
        for (int i = 0; i < arrVendor.size(); i++) {
            if (i == 5) {
                break;
            }
            vendor = (TechEvalDetailBean) arrVendor.get(i);
            //copy header
            newCell = copyCell(wb, sheet, 1, 5, col, "");
            newCell.setCellValue(new HSSFRichTextString(vendor.getVendorName()));
            ExcelExport.setBorder(wb, sheet, 1, col + 1, border, color);
            ExcelExport.setBorder(wb, sheet, 1, col + 2, border, color);
            ExcelExport.setBorder(wb, sheet, 1, col + 3, border, color);

            //copy header Chao hang ky thuat
            copyCell(wb, sheet, 2, 5, col, "");
            //copy header Thoi gian giao hang
            copyCell(wb, sheet, 2, 6, col + 1, "");

            //copy header Dieu kien khac
            copyCell(wb, sheet, 2, 7, col + 2, "");
            //copy header Danh gia
            copyCell(wb, sheet, 2, 8, col + 3, "");

            ExcelExport.setBorder(wb, sheet, 3, col, border, color);
            ExcelExport.setBorder(wb, sheet, 3, col + 1, border, color);
            ExcelExport.setBorder(wb, sheet, 3, col + 2, border, color);
            ExcelExport.setBorder(wb, sheet, 3, col + 3, border, color);
            //copy content Chao hang ky thuat
            copyCell(wb, sheet, 4, 5, col, vendor.getVenId() + "");
            //copy content Thoi gian giao hang
            copyCell(wb, sheet, 4, 6, col + 1, vendor.getVenId() + "");
            //copy content Dieu kien khac
            copyCell(wb, sheet, 4, 7, col + 2, vendor.getVenId() + "");
            //copy content Danh gia
            copyCell(wb, sheet, 4, 8, col + 3, vendor.getVenId() + "");
            ExcelExport.setBorder(wb, sheet, 5, col, border, color);
            ExcelExport.setBorder(wb, sheet, 5, col + 1, border, color);
            ExcelExport.setBorder(wb, sheet, 5, col + 2, border, color);
            ExcelExport.setBorder(wb, sheet, 5, col + 3, border, color);

//            copyCell(wb, sheet, 5, 4, col + 3, "");
            ExcelExport.setBorder(wb, sheet, 6, col, border, color);
            ExcelExport.setBorder(wb, sheet, 6, col + 3, border, color);

            //copy Chung chi danh gia
            newCell = copyCell(wb, sheet, 6, 2, col, "");
            copyCell(wb, sheet, 6, 2, col + 1, "");
            copyCell(wb, sheet, 6, 3, col + 2, "");
            copyCell(wb, sheet, 6, 4, col + 3, "");
            newCell.setCellValue(new HSSFRichTextString(vendor.getCertificateAttach()));
            ExcelExport.setBorder(wb, sheet, 6, col, border, color);
            ExcelExport.setBorder(wb, sheet, 6, col + 1, border, color);
            ExcelExport.setBorder(wb, sheet, 6, col + 2, border, color);
            ExcelExport.setBorder(wb, sheet, 6, col + 3, border, color);
            //copy Ket luan
            newCell = copyCell(wb, sheet, 7, 2, col, "");
            copyCell(wb, sheet, 7, 2, col + 1, "");
            copyCell(wb, sheet, 7, 3, col + 2, "");
            copyCell(wb, sheet, 7, 4, col + 3, "");
            newCell.setCellValue(new HSSFRichTextString(vendor.getResult()));
            ExcelExport.setBorder(wb, sheet, 7, col, border, color);
            ExcelExport.setBorder(wb, sheet, 7, col + 1, border, color);
            ExcelExport.setBorder(wb, sheet, 7, col + 2, border, color);
            ExcelExport.setBorder(wb, sheet, 7, col + 3, border, color);

            sheet.setColumnWidth(col, sheet.getColumnWidth(5));
            sheet.setColumnWidth(col + 1, sheet.getColumnWidth(6));
            sheet.setColumnWidth(col + 2, sheet.getColumnWidth(7));
            sheet.setColumnWidth(col + 3, sheet.getColumnWidth(8));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, col, col + 3));
            sheet.addMergedRegion(new CellRangeAddress(3, 3, col, col + 3));
            sheet.addMergedRegion(new CellRangeAddress(9 + rowSize, 9 + rowSize, col, col + 3));
            sheet.addMergedRegion(new CellRangeAddress(10 + rowSize, 10 + rowSize, col, col + 3));
            sheet.addMergedRegion(new CellRangeAddress(11 + rowSize, 11 + rowSize, col, col + 3));
            col += 4;
        }
        FileOutputStream fileOut = new FileOutputStream(tempFileName);
        wb.write(fileOut);
        fileOut.close();
    }

    private String copyCellValue(String value, String content) {
        String result = "";
        int ind = value.indexOf("${");
        if (ind == 0) {
            ind = value.indexOf(".");
            if (ind > -1) {
                result = "${vendor" + content + value.substring(ind);
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

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
