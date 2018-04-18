/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.comeval;

import com.venus.core.util.DateUtil;
import com.venus.core.util.FileUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ComEvalDetailBean;
import com.venus.mc.bean.ComEvalVendorBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.ComEvalDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.tenderplan.TenderPlanFormBean;
import com.venus.mc.util.MCUtil;
import java.io.File;
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
 * @author phuongtu
 * @version
 */
public class ComEvalPrintAction extends BaseAction {

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
        if (!GenericValidator.isBlankOrNull(request.getParameter("ceId"))) {
            ComEvalDAO comEvalDAO = new ComEvalDAO();
            ArrayList arrVendor = null;
            try {
                arrVendor = comEvalDAO.getComEvalVendor(NumberUtil.parseInt(request.getParameter("ceId"), 0));
            } catch (Exception ex) {
            }
            if (arrVendor == null) {
                arrVendor = new ArrayList();
            }
            ComEvalVendorBean vendor = null;
            ArrayList materialDetails = null;
            String templateFileName = request.getSession().getServletContext().getRealPath("/templates/BM.10.02.TM-Bang danh gia thuong mai - Tron goi.xls");
            String tempFileName = request.getSession().getServletContext().getRealPath("/templates/" + MCUtil.getMemberName(request.getSession()) + System.currentTimeMillis() + ".xls");
            try {
                if (arrVendor.size() > 0) {
                    vendor = (ComEvalVendorBean) arrVendor.get(0);
                    try {
                        String currency = "VND";
                        materialDetails = comEvalDAO.getComEvalMaterial(vendor.getCeId(), vendor.getTerId(), vendor.getCurrency());
//                        materialDetails = comEvalDAO.getComEvalMaterial(vendor.getCeId(), vendor.getTerId(), currency);
                    } catch (Exception ex) {
                    }
                }
                if (materialDetails == null) {
                    materialDetails = new ArrayList();
                }
                File f = FileUtil.createFile(tempFileName);
                ArrayList arrHideCol = new ArrayList();
                arrHideCol.add(4);
                arrHideCol.add(5);
                arrHideCol.add(6);
                arrHideCol.add(7);
                arrHideCol.add(8);
                arrHideCol.add(9);
                Map beans = new HashMap();
                createColumn(templateFileName, arrVendor, materialDetails.size(), f, arrHideCol, beans);

                for (int i = 0; i < arrVendor.size(); i++) {
                    vendor = (ComEvalVendorBean) arrVendor.get(i);
                    try {
                        String currency = "VND";
                        materialDetails = comEvalDAO.getComEvalMaterial(vendor.getCeId(), vendor.getTerId(), vendor.getCurrency());
//                        materialDetails = comEvalDAO.getComEvalMaterial(vendor.getCeId(), vendor.getTerId(), currency);
                    } catch (Exception ex) {
                    }
                    if (materialDetails == null) {
                        materialDetails = new ArrayList();
                    }
                    if (i == 0) {//lan dau do du lieu
                        beans.put("material", materialDetails);
                    }
                    beans.put("vendor" + vendor.getVenId() + "in", materialDetails);
                }
                TenderPlanBean tenderplan = null;
                try {
                    TenderPlanDAO tenDAO = new TenderPlanDAO();
                    tenderplan = tenDAO.getTenderPlanByComEvalVendorId(NumberUtil.parseInt(request.getParameter("ceId"), 0));
                } catch (Exception ex) {
                }
                if (tenderplan == null) {
                    tenderplan = new TenderPlanBean();
                }
                Date date = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "/");
                beans.put("comEvalDate", DateUtil.formatDate(date, "dd"));
                beans.put("comEvalMonth", DateUtil.formatDate(date, "MM"));
                beans.put("comEvalYear", DateUtil.formatDate(date, "yyyy"));
                beans.put("packName", tenderplan.getPackName().toUpperCase());
                beans.put("project", getProjectName(materialDetails).toUpperCase());
                if (Integer.parseInt(tenderplan.getForm()) == TenderPlanFormBean.FORM_PRIVATE) {
                    beans.put("groupEmployee", MCUtil.getBundleString("message.techeval.print.group"));
                    ArrayList arrEmployee = comEvalDAO.getComEvalGroup(tenderplan.getTenId());
                    beans.put("teg", arrEmployee);
                } else {
                    beans.put("sign1", "TRƯỞNG PHÒNG KẾ HOẠCH");
                    beans.put("sign2", "PHÓ PHÒNG KẾ HOẠCH");
                    beans.put("sign3", "NGƯỜI LẬP BẢNG");
                }
                ExcelExport exporter = new ExcelExport();
                exporter.setBeans(beans);
                short[] hiddenCols = new short[arrHideCol.size()];
                for (int i = 0; i < arrHideCol.size(); i++) {
                    hiddenCols[i] = Short.parseShort(arrHideCol.get(i) + "");
                }
                exporter.setHiddenCols(hiddenCols);
                long milis = System.currentTimeMillis();
                exporter.export(request, response, tempFileName, "Bang danh gia thuong mai - Tron goi.xls");
                milis = System.currentTimeMillis() - milis;
                System.out.println("Bang danh gia thuong mai - Tron goi.xls : " + "time : " + (int) ((milis / 1000) % 60) + " phut " + (int) ((milis / 1000) / 60) + " giay");
                f.delete();
            } catch (Exception ex) {
                LogUtil.error("FAILED:ComEvalPrint:print-" + ex.getMessage());
                ex.printStackTrace();
            }
        }

        return true;
    }

    private String getProjectName(ArrayList materialDetails) {
        String request = ",";
        ComEvalDetailBean bean = null;
        for (int i = 0; i < materialDetails.size(); i++) {
            bean = (ComEvalDetailBean) materialDetails.get(i);
            if (request.indexOf("," + bean.getProjectName() + ",") == -1) {
                request += bean.getProjectName() + ",";
            }
        }
        if (!request.equals(",")) {
            request = request.substring(1, request.length() - 1);
        }
        return request;
    }

    private void createColumn(String templateFileName, ArrayList arrVendor, int rowSize, File f, ArrayList arrHideCol, Map beans) throws Exception {
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(templateFileName));
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFCell newCell = null;
        int vendorKind = 0;
        ComEvalVendorBean vendor = null;
        short col = 10;
        short border = sheet.getRow(1).getCell(1).getCellStyle().getBorderLeft();
        short color = sheet.getRow(1).getCell(1).getCellStyle().getLeftBorderColor();
        for (int i = 0; i < arrVendor.size(); i++) {
            vendor = (ComEvalVendorBean) arrVendor.get(i);
            vendorKind = vendor.getVenKind();
            if (vendorKind == VendorBean.KIND_NATIONAL) {//nha cung cap trong nuoc
                //copy header
                newCell = copyCell(wb, sheet, 1, 4, col, "");
                newCell.setCellValue(new HSSFRichTextString(vendor.getVendorName()));
                ExcelExport.setBorder(wb, sheet, 1, col + 1, border, color);
                ExcelExport.setBorder(wb, sheet, 1, col + 2, border, color);

                //copy header Don gia ve den PTSC tien te khac
                newCell = copyCell(wb, sheet, 2, 4, col, "");
                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " (" + vendor.getCurrency() + ")"));
                if (vendor.getCurrency().contains("VN")) {
                    arrHideCol.add(col);
                }
                //copy header Don gia ve den PTSC
                newCell = copyCell(wb, sheet, 2, 5, col + 1, "");
                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " (" + "VND" + ")"));
                if (!GenericValidator.isBlankOrNull(vendor.getCurrency())) {
                    if (!vendor.getCurrency().contains("VN") && vendor.getRates() == 1) {
                        arrHideCol.add(col + 1);
                    }
                }
                //copy header Thanh tien ve den PTSC
                newCell = copyCell(wb, sheet, 2, 6, col + 2, "");
                if (!GenericValidator.isBlankOrNull(vendor.getCurrency())) {
                    if (!vendor.getCurrency().contains("VN") && vendor.getRates() == 1) {
                        newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " (" + vendor.getCurrency() + ")"));
                    } else {
                        newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " (" + "VND" + ")"));
                    }
                }
                ExcelExport.setBorder(wb, sheet, 3, col, border, color);
                ExcelExport.setBorder(wb, sheet, 3, col + 1, border, color);
                ExcelExport.setBorder(wb, sheet, 3, col + 2, border, color);
//                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " (" + vendor.getCurrency() + ")"));

                //copy content Don gia ve den PTSC tien te khac
                copyCell(wb, sheet, 4, 4, col, vendor.getVenId() + "");
                //copy content Don gia ve den PTSC
                copyCell(wb, sheet, 4, 5, col + 1, vendor.getVenId() + "");
                //copy content Thanh tien ve den PTSC
                copyCell(wb, sheet, 4, 6, col + 2, vendor.getVenId() + "");

                //copy chi phi lam thu tuc  hai quan
                copyCell(wb, sheet, 5, 4, col, "");
                copyCell(wb, sheet, 5, 5, col + 1, "");
                newCell = copyCell(wb, sheet, 5, 6, col + 2, "");
                newCell.setCellValue(new HSSFRichTextString(NumberUtil.formatMoneyDefault(vendor.getCustomTax() * vendor.getRates(), vendor.getCurrency())));

                //copy chi phi van chuyen den kho
                copyCell(wb, sheet, 6, 4, col, "");
                copyCell(wb, sheet, 6, 5, col + 1, "");
                newCell = copyCell(wb, sheet, 6, 6, col + 2, "");
                newCell.setCellValue(new HSSFRichTextString(NumberUtil.formatMoneyDefault(vendor.getCostTransport() * vendor.getRates(), vendor.getCurrency())));

                //copy thue nhap khau
                copyCell(wb, sheet, 7, 4, col, "");
                copyCell(wb, sheet, 7, 5, col + 1, "");
                newCell = copyCell(wb, sheet, 7, 6, col + 2, "");
                newCell.setCellValue(new HSSFRichTextString(NumberUtil.formatMoneyDefault(vendor.getOtherTax() * vendor.getRates(), vendor.getCurrency())));


                //copy chi phi chung chi xuat xu
                copyCell(wb, sheet, 8, 4, col, "");
                copyCell(wb, sheet, 8, 5, col + 1, "");
                newCell = copyCell(wb, sheet, 8, 6, col + 2, "");

                newCell.setCellValue(new HSSFRichTextString(NumberUtil.formatMoneyDefault(vendor.getPriceCertificate() * vendor.getRates(), vendor.getCurrency())));



                //copy chi phi van chuyen den cang hcm
                copyCell(wb, sheet, 9, 4, col, "");
                copyCell(wb, sheet, 9, 5, col + 1, "");
                newCell = copyCell(wb, sheet, 9, 6, col + 2, "");


                newCell.setCellValue(new HSSFRichTextString(NumberUtil.formatMoneyDefault(vendor.getPriceToPort() * vendor.getRates(), vendor.getCurrency())));


                //copy chi phi khac
                copyCell(wb, sheet, 10, 4, col, "");
                copyCell(wb, sheet, 10, 5, col + 1, "");
                newCell = copyCell(wb, sheet, 10, 6, col + 2, "");
                newCell.setCellValue(new HSSFRichTextString(NumberUtil.formatMoneyDefault(vendor.getOtherCost() * vendor.getRates(), vendor.getCurrency())));

                //copy Tong gia tri
//                double total = sum + vendor.getCostTransport() + tax;
                copyCell(wb, sheet, 11, 4, col, "");
                copyCell(wb, sheet, 11, 5, col + 1, "");
                newCell = copyCell(wb, sheet, 11, 6, col + 2, "");
//                newCell.setCellValue(new HSSFRichTextString(NumberUtil.formatMoneyDefault(vendor.getSum())));
                newCell.setCellValue(new HSSFRichTextString(vendor.getSum()));

                //copy Xep hang 
                copyCell(wb, sheet, 12, 4, col, "");
                copyCell(wb, sheet, 12, 5, col + 1, "");
                newCell = copyCell(wb, sheet, 12, 6, col + 2, "");
                newCell.setCellValue(vendor.getRand());

                //copy Dieu kien thanh toan
                copyCell(wb, sheet, 13, 4, col, "");
                copyCell(wb, sheet, 13, 5, col + 1, "");
                newCell = copyCell(wb, sheet, 13, 6, col + 2, "");

                ExcelExport.setBorder(wb, sheet, 14, col, border, color);
                ExcelExport.setBorder(wb, sheet, 14, col + 1, border, color);
                ExcelExport.setBorder(wb, sheet, 14, col + 2, border, color);
                newCell.setCellValue(new HSSFRichTextString(vendor.getPaymentMethod()));

                sheet.setColumnWidth(col, sheet.getColumnWidth(4));
                sheet.setColumnWidth((col + 1), sheet.getColumnWidth(5));
                sheet.setColumnWidth((col + 2), sheet.getColumnWidth(6));
            } else if (vendorKind == VendorBean.KIND_INTERNATIONAL) {//nha cung cap ngoai nuoc
                beans.put("comEvalCD1", "Chi phí thủ tục HQ ước tính 200USD & vc về kho cty ước tính là 150USD");
                beans.put("comEvalCD2", "Chi phí bảo hiểm hàng hóa ước tính 100USD");
                beans.put("comEvalCD3", "Thuế NK = 0%");
                //copy header
                newCell = copyCell(wb, sheet, 1, 7, col, "");
                newCell.setCellValue(new HSSFRichTextString(vendor.getVendorName()));
                ExcelExport.setBorder(wb, sheet, 1, col + 1, border, color);
                ExcelExport.setBorder(wb, sheet, 1, col + 2, border, color);
                if (vendor.getCurrency().contains("VN")) {
                    arrHideCol.add(col);
                }
                //copy header Don gia ve den PTSC tien te khac
                newCell = copyCell(wb, sheet, 2, 7, col, "");
                String incortem = "";
                if (vendor.getIncoterm() != 1) {
                    incortem = vendor.getIncortemText();
                }
                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " " + incortem + " (" + vendor.getCurrency() + ")"));
                if (vendor.getCurrency().contains("VN")) {
                    arrHideCol.add(col);
                }
                //copy header Don gia ve den PTSC
                newCell = copyCell(wb, sheet, 2, 8, col + 1, "");
//                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " " + incortem + " (" + vendor.getCurrency() + ")"));
                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " " + incortem + " (" + "VND" + ")"));
                //copy header Thanh tien ve den PTSC
                newCell = copyCell(wb, sheet, 2, 9, (col + 2), "");
//                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " " + incortem + " (" + vendor.getCurrency() + ")"));
                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " " + incortem + " (" + "VND" + ")"));
                ExcelExport.setBorder(wb, sheet, 3, col, border, color);
                ExcelExport.setBorder(wb, sheet, 3, col + 1, border, color);
                ExcelExport.setBorder(wb, sheet, 3, col + 2, border, color);

//                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " (" + vendor.getCurrency() + ")"));
                //copy content Don gia ve den PTSC tien te khac
                newCell = copyCell(wb, sheet, 4, 7, col, vendor.getVenId() + "");
                //copy content Don gia ve den PTSC
                newCell = copyCell(wb, sheet, 4, 8, col + 1, vendor.getVenId() + "");
                //copy content Thanh tien ve den PTSC
                newCell = copyCell(wb, sheet, 4, 9, col + 2, vendor.getVenId() + "");

                //copy chi phi lam thu tuc  hai quan
                copyCell(wb, sheet, 5, 7, col, "");
                copyCell(wb, sheet, 5, 8, col + 1, "");
                newCell = copyCell(wb, sheet, 5, 9, col + 2, "");
                newCell.setCellValue(new HSSFRichTextString(NumberUtil.formatMoneyDefault(vendor.getCustomTax() * vendor.getRates(), vendor.getCurrency())));

                //copy chi phi van chuyen den kho
                copyCell(wb, sheet, 6, 7, col, "");
                copyCell(wb, sheet, 6, 8, col + 1, "");
                newCell = copyCell(wb, sheet, 6, 9, col + 2, "");
                newCell.setCellValue(new HSSFRichTextString(NumberUtil.formatMoneyDefault(vendor.getCostTransport() * vendor.getRates(), vendor.getCurrency())));

                //copy thue nhap khau
                copyCell(wb, sheet, 7, 7, col, "");
                copyCell(wb, sheet, 7, 8, col + 1, "");
                newCell = copyCell(wb, sheet, 7, 9, col + 2, "");
                newCell.setCellValue(new HSSFRichTextString(NumberUtil.formatMoneyDefault(vendor.getOtherTax() * vendor.getRates(), vendor.getCurrency())));

                //copy chi phi chung chi xuat xu
                copyCell(wb, sheet, 8, 4, col, "");
                copyCell(wb, sheet, 8, 5, col + 1, "");
                newCell = copyCell(wb, sheet, 8, 6, col + 2, "");
                newCell.setCellValue(new HSSFRichTextString(NumberUtil.formatMoneyDefault(vendor.getPriceCertificate() * vendor.getRates(), vendor.getCurrency())));

                //copy chi phi van chuyen den cang hcm
                copyCell(wb, sheet, 9, 4, col, "");
                copyCell(wb, sheet, 9, 5, col + 1, "");
                newCell = copyCell(wb, sheet, 9, 6, col + 2, "");
                newCell.setCellValue(new HSSFRichTextString(NumberUtil.formatMoneyDefault(vendor.getPriceToPort() * vendor.getRates(), vendor.getCurrency())));

                //copy chi phi khac
                copyCell(wb, sheet, 10, 7, col, "");
                copyCell(wb, sheet, 10, 8, col + 1, "");
                newCell = copyCell(wb, sheet, 10, 9, col + 2, "");
                newCell.setCellValue(new HSSFRichTextString(NumberUtil.formatMoneyDefault(vendor.getOtherCost() * vendor.getRates(), vendor.getCurrency())));

                //copy Tong gia tri
//                double total = sum + vendor.getCostTransport() + tax;
                copyCell(wb, sheet, 11, 7, col, "");
                copyCell(wb, sheet, 11, 8, col + 1, "");
                newCell = copyCell(wb, sheet, 11, 9, col + 2, "");
//                newCell.setCellValue(new HSSFRichTextString(NumberUtil.formatMoneyDefault(vendor.getSum())));
                newCell.setCellValue(new HSSFRichTextString(vendor.getSum()));

                //copy Xep hang 
                copyCell(wb, sheet, 12, 7, col, "");
                copyCell(wb, sheet, 12, 8, col + 1, "");
                newCell = copyCell(wb, sheet, 12, 9, col + 2, "");
                newCell.setCellValue(vendor.getRand());
                
                //copy dieu kien khac
                copyCell(wb, sheet, 13, 7, col, "");
                copyCell(wb, sheet, 13, 8, col + 1, "");
                newCell = copyCell(wb, sheet, 13, 9, col + 2, "");


                newCell.setCellValue(vendor.getRand());

                //copy Dieu kien thanh toan
                copyCell(wb, sheet, 14, 7, col, "");
                copyCell(wb, sheet, 14, 8, col + 1, "");
                newCell = copyCell(wb, sheet, 14, 9, col + 2, "");
                ExcelExport.setBorder(wb, sheet, 14, col, border, color);
                ExcelExport.setBorder(wb, sheet, 14, col + 1, border, color);
                ExcelExport.setBorder(wb, sheet, 14, col + 2, border, color);
                newCell.setCellValue(new HSSFRichTextString(vendor.getPaymentMethod()));

                sheet.setColumnWidth(col, sheet.getColumnWidth(4));
                sheet.setColumnWidth((col + 1), sheet.getColumnWidth(5));
                sheet.setColumnWidth((col + 2), sheet.getColumnWidth(6));
            }
            sheet.addMergedRegion(new CellRangeAddress(1, 1, col, col + 2));
//            sheet.addMergedRegion(new CellRangeAddress(4 + rowSize, 4 + rowSize, col, col + 1));
//            sheet.addMergedRegion(new CellRangeAddress(5 + rowSize, 5 + rowSize, col, col + 1));
//            sheet.addMergedRegion(new CellRangeAddress(6 + rowSize, 6 + rowSize, col, col + 1));
//            sheet.addMergedRegion(new CellRangeAddress(7 + rowSize, 7 + rowSize, col, col + 1));
//            sheet.addMergedRegion(new CellRangeAddress(8 + rowSize, 8 + rowSize, col, col + 1));
//            sheet.addMergedRegion(new CellRangeAddress(9 + rowSize, 9 + rowSize, col, col + 1));
//            sheet.addMergedRegion(new CellRangeAddress(10 + rowSize, 10 + rowSize, col, col + 1));
            col += 3;
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3 + arrVendor.size() * 3 + 3 * 2));
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

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
