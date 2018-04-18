/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.comevalmaterial;

import com.venus.core.util.DateUtil;
import com.venus.core.util.FileUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.ComEvalMaterialDetailBean;
import com.venus.mc.bean.TechEvalDetailBean;
import com.venus.mc.bean.TenderEvaluateVendorBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.ComEvalDAO;
import com.venus.mc.dao.ComEvalMaterialDAO;
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
 * @author phuontu
 * @version
 */
public class ComEvalMaterialPrintAction extends BaseAction {

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
            int ceId = NumberUtil.parseInt(request.getParameter("ceId"), 0);
            ArrayList arrVendor = null;
            ComEvalMaterialDAO comEvalMaterialDAO = new ComEvalMaterialDAO();
            try {
                arrVendor = comEvalMaterialDAO.getComEvalVendorMaterial(ceId);
            } catch (Exception ex) {
            }
            if (arrVendor == null) {
                arrVendor = new ArrayList();
            }
            TenderEvaluateVendorBean vendor = null;
            ArrayList materialDetails = null;
            String templateFileName = request.getSession().getServletContext().getRealPath("/templates/BM.10.02.TM-Bang danh gia thuong mai - Tung phan.xls");
            String tempFileName = request.getSession().getServletContext().getRealPath("/templates/" + MCUtil.getMemberName(request.getSession()) + System.currentTimeMillis() + ".xls");
            try {
                if (arrVendor.size() > 0) {
                    vendor = (TenderEvaluateVendorBean) arrVendor.get(0);
                    try {
                        materialDetails = comEvalMaterialDAO.getComEvalMaterial(vendor.getCeId(), vendor.getTerId());
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
                arrHideCol.add(10);
                arrHideCol.add(11);
                arrHideCol.add(12);
                arrHideCol.add(13);
                arrHideCol.add(14);
                arrHideCol.add(15);
                arrHideCol.add(16);
                arrHideCol.add(17);
                createColumn(templateFileName, arrVendor, materialDetails.size(), f, arrHideCol);
                Map beans = new HashMap();
                ArrayList arrAcceptedVendor = new ArrayList();
                ArrayList arrAcceptedVendorValue = new ArrayList();
                for (int i = 0; i < materialDetails.size(); i++) {
                    arrAcceptedVendorValue.add(0);
                    arrAcceptedVendor.add(-1);
                }
                for (int i = 0; i < arrVendor.size(); i++) {
                    vendor = (TenderEvaluateVendorBean) arrVendor.get(i);
                    try {
                        materialDetails = comEvalMaterialDAO.getComEvalMaterial(ceId, vendor.getTerId());
                    } catch (Exception ex) {
                    }
                    if (materialDetails == null) {
                        materialDetails = new ArrayList();
                    }
                    ComEvalMaterialDetailBean bean = null;
                    double value = 0;
                    double total = 0;
                    for (int j = 0; j < materialDetails.size(); j++) {
                        bean = (ComEvalMaterialDetailBean) materialDetails.get(j);
                        if (bean.getResult() == TechEvalDetailBean.RESULT_REACH) {
                            try {
                                value = Double.parseDouble(arrAcceptedVendorValue.get(j) + "");
                            } catch (Exception ex) {
                                value = 0;
                            }
                            total = Double.parseDouble(bean.getTotal().replaceAll(",", ""));
                            if (total < value || value == 0) {
                                arrAcceptedVendorValue.set(j, total);
                                arrAcceptedVendor.set(j, i);
                            }
                        }
                    }
                    if (i == 0) {//lan dau do du lieu
                        beans.put("material", materialDetails);
                    }
                    beans.put("vendor" + vendor.getVenId() + "in", materialDetails);
                }
                for (int i = 0; i < arrAcceptedVendor.size(); i++) {
                    int t = Integer.parseInt(arrAcceptedVendor.get(i) + "");
                    if (t != -1) {
                        arrAcceptedVendor.set(i, arrVendor.get(t));
                    } else {
                        arrAcceptedVendor.set(i, new TenderEvaluateVendorBean());
                    }
                }
                TenderPlanBean tenderplan = null;
                try {
                    TenderPlanDAO tenDAO = new TenderPlanDAO();
                    tenderplan = tenDAO.getTenderPlanByComEvalMaterialVendorId(NumberUtil.parseInt(request.getParameter("ceId"), 0));
                } catch (Exception ex) {
                }
                if (tenderplan == null) {
                    tenderplan = new TenderPlanBean();
                }
                beans.put("accepted", arrAcceptedVendor);
                Date date = DateUtil.transformerStringEnDate(DateUtil.today("dd/MM/yyyy"), "/");
                beans.put("comEvalDate", DateUtil.formatDate(date, "dd"));
                beans.put("comEvalMonth", DateUtil.formatDate(date, "MM"));
                beans.put("comEvalYear", DateUtil.formatDate(date, "yyyy"));
                if (tenderplan.getPackName() != null) {
                    beans.put("packName", tenderplan.getPackName().toUpperCase());
                } else {
                    beans.put("packName", "");
                }

                //beans.put("project", getProjectName(materialDetails).toUpperCase());
                beans.put("project", "");
                if (Integer.parseInt(tenderplan.getForm()) == TenderPlanFormBean.FORM_PRIVATE) {
                    beans.put("groupEmployee", MCUtil.getBundleString("message.techeval.print.group"));
                    ComEvalDAO comEvalDAO = new ComEvalDAO();
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
                exporter.export(request, response, tempFileName, "Bang danh gia thuong mai - Tung phan.xls");
                milis = System.currentTimeMillis() - milis;
                System.out.println("Bang danh gia thuong mai - Tung phan.xls : " + "time : " + (int) ((milis / 1000) % 60) + " phut " + (int) ((milis / 1000) / 60) + " giay");
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
        ComEvalMaterialDetailBean bean = null;
        for (int i = 0; i < materialDetails.size(); i++) {
            bean = (ComEvalMaterialDetailBean) materialDetails.get(i);
            if (request.indexOf("," + bean.getProjectName() + ",") == -1) {
                request += bean.getProjectName() + ",";
            }
        }
        if (!request.equals(",")) {
            request = request.substring(1, request.length() - 1);
        }
        return request;
    }

    private void createColumn(String templateFileName, ArrayList arrVendor, int rowSize, File f, ArrayList arrHideCol) throws Exception {
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(templateFileName));
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFCell newCell = null;
        int vendorKind = 1;
        TenderEvaluateVendorBean vendor = null;
        short col = 18;
        short border = sheet.getRow(1).getCell(1).getCellStyle().getBorderLeft();
        short color = sheet.getRow(1).getCell(1).getCellStyle().getLeftBorderColor();
        int count = 0;
        for (int i = 0; i < arrVendor.size(); i++) {
            vendor = (TenderEvaluateVendorBean) arrVendor.get(i);
            vendorKind = vendor.getVenKind();
            if (vendorKind == VendorBean.KIND_NATIONAL) {//nha cung cap trong nuoc
                count += 3;
                //copy header
                newCell = copyCell(wb, sheet, 1, 4, col, "");
                newCell.setCellValue(new HSSFRichTextString(vendor.getVendorName()));
                ExcelExport.setBorder(wb, sheet, 1, col + 1, border, color);
                ExcelExport.setBorder(wb, sheet, 1, col + 2, border, color);

                //copy header Don gia ve den PTSC tien te khac
                newCell = copyCell(wb, sheet, 2, 4, col, "");
                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " (" + vendor.getCurrency() + ")"));
                if (!GenericValidator.isBlankOrNull(vendor.getCurrency())) {
                    if (vendor.getCurrency().contains("VN")) {
                        arrHideCol.add(col);
                    }
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
//                //copy Phuong thu thanh toan
//                copyCell(wb, sheet, 6, 4, col, "");
//                newCell = copyCell(wb, sheet, 6, 5, col + 1, "");
//                newCell.setCellValue(new HSSFRichTextString(vendor.getPaymentMethod()));
////                ExcelExport.setBorder(wb, sheet, 6, col + 1, border, color);
//                //copy Bao lanh thuc hien hop dong
//                copyCell(wb, sheet, 7, 4, col, "");
//                newCell = copyCell(wb, sheet, 7, 5, col + 1, "");
//                newCell.setCellValue(new HSSFRichTextString(vendor.getGuaranteeContract()));
////                ExcelExport.setBorder(wb, sheet, 7, col + 1, border, color);
//                //copy Ket luan
//                copyCell(wb, sheet, 8, 4, col, "");
//                newCell = copyCell(wb, sheet, 8, 5, col + 1, "");
//                //??      newCell.setCellValue(new HSSFRichTextString("chua biet"));
////                ExcelExport.setBorder(wb, sheet, 8, col + 1, border, color);
//              copy dieu kien thanh toan
                copyCell(wb, sheet, 5, 4, col, "");
                copyCell(wb, sheet, 5, 5, col + 1, "");
                newCell = copyCell(wb, sheet, 5, 6, col + 2, "");

                sheet.setColumnWidth(col, sheet.getColumnWidth(4));
                sheet.setColumnWidth(col + 1, sheet.getColumnWidth(5));
                sheet.setColumnWidth(col + 2, sheet.getColumnWidth(6));
//tu : 2
                sheet.addMergedRegion(new CellRangeAddress(1, 1, col, col + 2));
                sheet.addMergedRegion(new CellRangeAddress(3, 3, col, col + 2));
                copyCell(wb, sheet, 6, 4, col, "");
                copyCell(wb, sheet, 6, 5, col + 1, "");
                newCell = copyCell(wb, sheet, 6, 6, col + 2, "");
//                ExcelExport.setBorder(wb, sheet, 7, col, border, color);
//                ExcelExport.setBorder(wb, sheet, 7, col + 1, border, color);
                newCell.setCellValue(new HSSFRichTextString(vendor.getPaymentMethod()));
//tu : 4
//                sheet.addMergedRegion(new CellRangeAddress(4 + rowSize, 4 + rowSize, col, col + 1));
//                sheet.addMergedRegion(new CellRangeAddress(5 + rowSize, 5 + rowSize, col, col + 1));
//                sheet.addMergedRegion(new CellRangeAddress(6 + rowSize, 6 + rowSize, col, col + 1));
//                sheet.addMergedRegion(new CellRangeAddress(7 + rowSize, 7 + rowSize, col, col + 1));
                col += 3;
            } else if (vendorKind == VendorBean.KIND_INTERNATIONAL) {//nha cung cap ngoai nuoc
                count += 10;
                //copy header
                newCell = copyCell(wb, sheet, 1, 7, col, "");
                newCell.setCellValue(new HSSFRichTextString(vendor.getVendorName()));
                ExcelExport.setBorder(wb, sheet, 1, col + 1, border, color);
                ExcelExport.setBorder(wb, sheet, 1, col + 2, border, color);
                ExcelExport.setBorder(wb, sheet, 1, col + 3, border, color);
                ExcelExport.setBorder(wb, sheet, 1, col + 4, border, color);
                ExcelExport.setBorder(wb, sheet, 1, col + 5, border, color);
                ExcelExport.setBorder(wb, sheet, 1, col + 6, border, color);
                ExcelExport.setBorder(wb, sheet, 1, col + 7, border, color);
                ExcelExport.setBorder(wb, sheet, 1, col + 8, border, color);
                ExcelExport.setBorder(wb, sheet, 1, col + 9, border, color);

                String incortem = "";
                if (vendor.getIncoterm() != 1) {
                    incortem = vendor.getIncortemText();
                }

                //copy header Don gia 1
                newCell = copyCell(wb, sheet, 2, 7, col, "");
                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " " + incortem + " (" + vendor.getCurrency() + ") (1)"));
                //copy header Don gia 2
                newCell = copyCell(wb, sheet, 2, 8, col + 1, "");
                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " (" + vendor.getCurrency() + ") (2)"));
                //copy header Don gia 3
                newCell = copyCell(wb, sheet, 2, 9, col + 2, "");
                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " (" + vendor.getCurrency() + ") (3)"));
                //copy header Don gia 4
                newCell = copyCell(wb, sheet, 2, 10, col + 3, "");
                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " (" + vendor.getCurrency() + ") (4)"));
                //copy header Don gia 5
                newCell = copyCell(wb, sheet, 2, 11, col + 4, "");
                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " (" + vendor.getCurrency() + ") (5)"));
                //copy header Don gia 6
                newCell = copyCell(wb, sheet, 2, 12, col + 5, "");
                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " (" + vendor.getCurrency() + ") (6)"));
                //copy header Don gia 7
                newCell = copyCell(wb, sheet, 2, 13, col + 6, "");
                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " (" + vendor.getCurrency() + ") (7)"));
                //copy header Don gia 8
                newCell = copyCell(wb, sheet, 2, 14, col + 7, "");
                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " (" + vendor.getCurrency() + ") (8 = 1 + 2 + 3 + 4 + 5 + 6 + 7)"));
                //copy header Don gia VND
                newCell = newCell = copyCell(wb, sheet, 2, 15, col + 8, "");
                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " (" + "VND" + ")"));
                //copy header Thanh tien ve den kho
                newCell = newCell = copyCell(wb, sheet, 2, 16, col + 9, "");
                newCell.setCellValue(new HSSFRichTextString(newCell.getRichStringCellValue().getString() + " (" + "VND" + ")"));

                ExcelExport.setBorder(wb, sheet, 3, col + 9, border, color);

                //copy content Don gia 1
                copyCell(wb, sheet, 4, 7, col, vendor.getVenId() + "");
                //copy content Don gia 2
                copyCell(wb, sheet, 4, 8, col + 1, vendor.getVenId() + "");
                //copy content Don gia 3
                copyCell(wb, sheet, 4, 9, col + 2, vendor.getVenId() + "");
                //copy content Don gia 4
                copyCell(wb, sheet, 4, 10, col + 3, vendor.getVenId() + "");
                //copy content Don gia 5
                copyCell(wb, sheet, 4, 11, col + 4, vendor.getVenId() + "");
                //copy content Don gia 6
                copyCell(wb, sheet, 4, 12, col + 5, vendor.getVenId() + "");
                //copy content Don gia 7
                copyCell(wb, sheet, 4, 13, col + 6, vendor.getVenId() + "");
                //copy content Don gia 8
                copyCell(wb, sheet, 4, 14, col + 7, vendor.getVenId() + "");
                //copy content Don gia VND
                copyCell(wb, sheet, 4, 15, col + 8, vendor.getVenId() + "");
                //copy content Thanh tien ve den kho
                copyCell(wb, sheet, 4, 16, col + 9, vendor.getVenId() + "");

                newCell = copyCell(wb, sheet, 5, 3, col + 9, "");

                //copy Phuong thu thanh toan
                copyCell(wb, sheet, 6, 7, col, "");
                copyCell(wb, sheet, 6, 8, col + 1, "");
                copyCell(wb, sheet, 6, 9, col + 2, "");
                copyCell(wb, sheet, 6, 10, col + 3, "");
                copyCell(wb, sheet, 6, 11, col + 4, "");
                copyCell(wb, sheet, 6, 12, col + 5, "");
                copyCell(wb, sheet, 6, 13, col + 6, "");
                copyCell(wb, sheet, 6, 14, col + 7, "");
                copyCell(wb, sheet, 6, 15, col + 8, "");
                newCell = copyCell(wb, sheet, 6, 15, col + 8, "");
                newCell.setCellValue(new HSSFRichTextString(vendor.getPaymentMethod()));
//                ExcelExport.setBorder(wb, sheet, 6, col + 1, border, color);
                //copy Bao lanh thuc hien hop dong
                copyCell(wb, sheet, 7, 7, col, "");
                copyCell(wb, sheet, 7, 8, col + 1, "");
                copyCell(wb, sheet, 7, 9, col + 2, "");
                copyCell(wb, sheet, 7, 10, col + 3, "");
                copyCell(wb, sheet, 7, 11, col + 4, "");
                copyCell(wb, sheet, 7, 12, col + 5, "");
                copyCell(wb, sheet, 7, 13, col + 6, "");
                copyCell(wb, sheet, 7, 14, col + 7, "");
                copyCell(wb, sheet, 7, 15, col + 8, "");
                newCell = copyCell(wb, sheet, 7, 15, col + 8, "");
                newCell.setCellValue(new HSSFRichTextString(vendor.getGuaranteeContract()));
//                ExcelExport.setBorder(wb, sheet, 7, col + 1, border, color);
                //copy Ket luan
                copyCell(wb, sheet, 8, 7, col, "");
                copyCell(wb, sheet, 8, 8, col + 1, "");
                copyCell(wb, sheet, 8, 9, col + 2, "");
                copyCell(wb, sheet, 8, 10, col + 3, "");
                copyCell(wb, sheet, 8, 11, col + 4, "");
                copyCell(wb, sheet, 8, 12, col + 5, "");
                copyCell(wb, sheet, 8, 13, col + 6, "");
                copyCell(wb, sheet, 8, 14, col + 7, "");
                copyCell(wb, sheet, 8, 15, col + 8, "");
                newCell = copyCell(wb, sheet, 8, 15, col + 8, "");
                //??      newCell.setCellValue(new HSSFRichTextString("chua biet"));
//                ExcelExport.setBorder(wb, sheet, 8, col + 1, border, color);

                sheet.setColumnWidth(col, sheet.getColumnWidth(6));
                sheet.setColumnWidth(col + 1, sheet.getColumnWidth(7));
                sheet.setColumnWidth(col + 2, sheet.getColumnWidth(8));
                sheet.setColumnWidth(col + 3, sheet.getColumnWidth(9));
                sheet.setColumnWidth(col + 4, sheet.getColumnWidth(10));
                sheet.setColumnWidth(col + 5, sheet.getColumnWidth(11));
                sheet.setColumnWidth(col + 6, sheet.getColumnWidth(12));
                sheet.setColumnWidth(col + 7, sheet.getColumnWidth(13));
                sheet.setColumnWidth(col + 8, sheet.getColumnWidth(14));
                sheet.setColumnWidth(col + 9, sheet.getColumnWidth(15));
                sheet.setColumnWidth(col + 10, sheet.getColumnWidth(16));
//tu : 2
                sheet.addMergedRegion(new CellRangeAddress(1, 1, col, col + 9));
                sheet.addMergedRegion(new CellRangeAddress(3, 3, col, col + 9));
//                sheet.addMergedRegion(new CellRangeAddress(5, 5, col, col + 6));
//                sheet.addMergedRegion(new CellRangeAddress(6, 6, col, col + 6));
//                sheet.addMergedRegion(new CellRangeAddress(7, 7, col, col + 6));
//                sheet.addMergedRegion(new CellRangeAddress(8, 8, col, col + 6));

//                ExcelExport.setBorder(wb, sheet, 5, col + 2, border, color);
//                ExcelExport.setBorder(wb, sheet, 5, col + 3, border, color);
//                ExcelExport.setBorder(wb, sheet, 5, col + 4, border, color);
//                ExcelExport.setBorder(wb, sheet, 5, col + 5, border, color);
//                ExcelExport.setBorder(wb, sheet, 5, col + 6, border, color);
//                ExcelExport.setBorder(wb, sheet, 6, col + 2, border, color);
//                ExcelExport.setBorder(wb, sheet, 6, col + 3, border, color);
//                ExcelExport.setBorder(wb, sheet, 6, col + 4, border, color);
//                ExcelExport.setBorder(wb, sheet, 6, col + 5, border, color);
//                ExcelExport.setBorder(wb, sheet, 6, col + 6, border, color);
//                ExcelExport.setBorder(wb, sheet, 7, col + 2, border, color);
//                ExcelExport.setBorder(wb, sheet, 7, col + 3, border, color);
//                ExcelExport.setBorder(wb, sheet, 7, col + 4, border, color);
//                ExcelExport.setBorder(wb, sheet, 7, col + 5, border, color);
//                ExcelExport.setBorder(wb, sheet, 7, col + 6, border, color);
//                ExcelExport.setBorder(wb, sheet, 8, col + 2, border, color);
//                ExcelExport.setBorder(wb, sheet, 8, col + 3, border, color);
//                ExcelExport.setBorder(wb, sheet, 8, col + 4, border, color);
//                ExcelExport.setBorder(wb, sheet, 8, col + 5, border, color);
//                ExcelExport.setBorder(wb, sheet, 8, col + 6, border, color);

//                sheet.addMergedRegion(new CellRangeAddress(4 + rowSize, 4 + rowSize, col, col + 6));
//                sheet.addMergedRegion(new CellRangeAddress(5 + rowSize, 5 + rowSize, col, col + 6));
//                sheet.addMergedRegion(new CellRangeAddress(6 + rowSize, 6 + rowSize, col, col + 6));
//                sheet.addMergedRegion(new CellRangeAddress(7 + rowSize, 7 + rowSize, col, col + 6));


                col += 10;
            }
        }
        //copy Nha cung cap de nghi ky HD
        //copy header
        copyCell(wb, sheet, 1, 17, col, "");
        copyCell(wb, sheet, 2, 17, col, "");
        ExcelExport.setBorder(wb, sheet, 2, col, border, color);

        //copy noi dung
        copyCell(wb, sheet, 3, 17, col, "");
        copyCell(wb, sheet, 4, 17, col, "");
        copyCell(wb, sheet, 5, 17, col, "");
        copyCell(wb, sheet, 6, 17, col, "");
//        copyCell(wb, sheet, 7, 13, col, "");
//        copyCell(wb, sheet, 8, 13, col, "");

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4 + count + 14));
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
