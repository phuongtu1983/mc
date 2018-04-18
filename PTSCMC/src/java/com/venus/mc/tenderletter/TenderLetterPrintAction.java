package com.venus.mc.tenderletter;

import com.venus.core.util.DateUtil;
import com.venus.core.util.FileUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.core.util.StringUtil;
import com.venus.mc.bean.TenderEvaluateVendorBean;
import com.venus.mc.bean.TenderLetterDetailBean;
import com.venus.mc.bean.TenderLetterPrintBean;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.dao.TenderEvaluateVendorDAO;
import com.venus.mc.dao.TenderLetterDetailDAO;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.dao.VendorDAO;
import com.venus.mc.tenderplan.TenderPlanFormBean;
import com.venus.mc.util.MCUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import java.io.FileInputStream;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TenderLetterPrintAction extends BaseAction {

    private ArrayList arrFiles = null;

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        arrFiles = new ArrayList();
        String type = "zip";//docx or zip type
        //PBK
        String fileName = "Cong van moi chao gia_PBK";
        //Fax
        String fileName1 = "Cong van moi chao gia_Fax";
        //PBK nn
        String fileName2 = "Cong van moi chao gia_PBK_nn";
        //Fax nn
        String fileName3 = "Cong van moi chao gia_Fax_nn";

        String userName = MCUtil.getMemberName(request.getSession());

        try {
            TenderLetterDetailBean bean = null;
            TenderPlanBean tenBean = null;
            TenderLetterDetailDAO tenDAO = new TenderLetterDetailDAO();
            TenderPlanDAO tenderDAO = new TenderPlanDAO();
            int id = NumberUtil.parseInt(request.getParameter("letId"), 0);
            ArrayList detId = new ArrayList();
            detId = tenDAO.getDetIdByLetId(Integer.parseInt(request.getParameter("letId")));

            for (int i = 0; i < detId.size(); i++) {
                try {
                    bean = tenDAO.getTenderLetterDetail((Integer) detId.get(i));
                    tenBean = tenderDAO.getTenderPlan(bean.getTenId());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (tenBean == null) {
                    tenBean = new TenderPlanBean();
                }
                if (!StringUtil.isBlankOrNull(bean.getVendorName())) {
                    if (bean.getForm() == 1) {
                        if (tenBean.getOfferType() == TenderPlanFormBean.OFFER_IN) {// PBK trong nuoc
                            print(request, response, fileName, type, bean.getDetId(), userName + "_" + StringUtil.decompose(bean.getVendorName()));
                        } else {// PBK nngoai
                            print(request, response, fileName2, type, bean.getDetId(), userName + "_" + StringUtil.decompose(bean.getVendorName()));
                        }

                    } else {
                        if (tenBean.getOfferType() == TenderPlanFormBean.OFFER_IN) { // Fax trong nuoc
                            print(request, response, fileName1, type, bean.getDetId(), userName + "_" + StringUtil.decompose(bean.getVendorName()));
                        } else // Fax nngoai
                        {
                            print(request, response, fileName3, type, bean.getDetId(), userName + "_" + StringUtil.decompose(bean.getVendorName()));
                        }
                    }
                }
            }
        } catch (Exception ex) {
        }

        if (type.equals("zip")) {
            String outputFileName = fileName + "-" + userName + ".zip";
            FileUtil.zipFile(outputFileName, arrFiles);
            OutputUtil.sendZipFileToOutput(response, outputFileName);
        }
        return null;
    }

    private void print(HttpServletRequest request, HttpServletResponse response, String fileName, String type, int id, String userName) {
        try {

            if (type.equals("docx")) {
                type = "";
            } else {
                type = ".docx";
            }

            String wordDir = "C:";
            arrFiles.add(wordDir + "/" + fileName + "-" + userName + type);
            String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/" + fileName + ".docx");
            // 1) Load Docx file by filling Freemarker template engine and cache
            // it to the registry
            InputStream in = new FileInputStream(wordTemplate);
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

            // 2) Create context Java model
            IContext context = report.createContext();
            // Register developers list
            List<TenderLetterPrintBean> docs = new ArrayList<TenderLetterPrintBean>();

            TenderPlanDAO tenderDAO = new TenderPlanDAO();
            TenderPlanBean bean = null;
            TenderPlanBean bean1 = null;
            TenderLetterDetailDAO tenDAO = new TenderLetterDetailDAO();
            TenderLetterDetailBean bean2 = null;
            TenderEvaluateVendorDAO tevDAO = new TenderEvaluateVendorDAO();
            TenderEvaluateVendorBean tevBean = null;
            VendorDAO vendorDAO = new VendorDAO();
            VendorBean vendorBean = null;
            int kind = 0;
            try {
                bean2 = tenDAO.getTenderLetterDetail(id);
                bean1 = tenderDAO.getOrgNameEmpCreatedTenderPlan(bean2.getTenId());
                bean = tenderDAO.getTenderPlan(bean2.getTenId());
                kind = tenDAO.getKindOfVendor(bean2.getDetId());
                tevBean = tevDAO.getTenderEvaluateVendor(bean2.getTevId());
                vendorBean = vendorDAO.getVendor(tevBean.getVenId());
            } catch (Exception ex) {
            }

            Date d = DateUtil.transformerStringEnDate(bean2.getDate(), "/");
            String day = DateUtil.formatDate(d, "dd");
            String month = DateUtil.formatDate(d, "MM");
            String year = DateUtil.formatDate(d, "yyyy");
            String placeDelivery = "";
            String currency = "";
            if (kind == 1) {
                placeDelivery = "DDP PTSC MC Warehouse, No 65A, 30/4 Road, Ward 9, Vung tau City, S.R Vietnam";
                currency = "VND";
            } else {
                placeDelivery = "CIF Ho Chi Minh Port, Ho Chi Minh City, S.R Vietnam";
                currency = "US$";
            }
            String name = vendorBean.getName();
            String nameUpcase = vendorBean.getName().toUpperCase();
            String fax = vendorBean.getFax();
            String phone = "Điện thoại: " + vendorBean.getPhone() + "    Fax: " + vendorBean.getFax();
            String email = vendorBean.getEmail();
            String address = "Địa chỉ: " + vendorBean.getAddress();
            String bidDeadlineTime = bean.getBidDeadlineTime();
            String bidDeadline = bean.getBidDeadline();
            String packName = bean.getPackName();
            String packNameUpcase = bean.getPackName().toUpperCase();
            String deliveryTime = bean.getDeliveryTime();
            String number = bean2.getSubfix();
            String date = bean2.getDate();
            String abbreviate = bean1.getAbbreviate();

            docs.add(
                    new TenderLetterPrintBean(name, nameUpcase, address, phone, fax, email, bidDeadlineTime, bidDeadline, packName, packNameUpcase, placeDelivery, number, date, day, month, year, abbreviate, currency, deliveryTime));

            context.put("ptscmc", docs);

            // 3) Generate report by merging Java model with the Docx
            OutputStream out = new FileOutputStream(new File(wordDir + "/" + fileName + "-" + userName + type));

            report.process(context, out);

            try {
                if (type.equals(".docx")) {
                    //String outputFileName = fileName + "-" + userName + ".zip";
                    //FileUtil.zipFile(outputFileName, arrFiles);
                    //OutputUtil.sendZipFileToOutput(response, outputFileName);
                } else {
                    OutputUtil.sendWordFileToOutput(response, wordDir + "/", fileName + "-" + userName);
                }
            } catch (Exception ex) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XDocReportException e) {
            e.printStackTrace();
        }
    }

    private void print1(HttpServletRequest request, HttpServletResponse response, String fileName, String type, int id, String userName) {
        try {
            //docx or zip type
            if (type.equals("docx")) {
                type = "";
            } else {
                type = ".docx";
            }

            String wordDir = "C:";
            arrFiles.add(wordDir + "/" + fileName + "-" + userName + type);
            String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/" + fileName + ".docx");
            // 1) Load Docx file by filling Freemarker template engine and cache
            // it to the registry
            InputStream in = new FileInputStream(wordTemplate);
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

            // 2) Create context Java model
            IContext context = report.createContext();
            // Register developers list
            List<TenderLetterPrintBean> docs = new ArrayList<TenderLetterPrintBean>();

            TenderPlanDAO tenderDAO = new TenderPlanDAO();
            TenderPlanBean bean = null;
            TenderPlanBean bean1 = null;
            TenderLetterDetailDAO tenDAO = new TenderLetterDetailDAO();
            TenderLetterDetailBean bean2 = null;
            TenderEvaluateVendorDAO tevDAO = new TenderEvaluateVendorDAO();
            TenderEvaluateVendorBean tevBean = null;
            VendorDAO vendorDAO = new VendorDAO();
            VendorBean vendorBean = null;
            int kind = 0;
            try {
                bean2 = tenDAO.getTenderLetterDetail(id);
                bean1 = tenderDAO.getOrgNameEmpCreatedTenderPlan(bean2.getTenId());
                bean = tenderDAO.getTenderPlan(bean2.getTenId());
                kind = tenDAO.getKindOfVendor(bean2.getDetId());
                tevBean = tevDAO.getTenderEvaluateVendor(bean2.getTevId());
                vendorBean = vendorDAO.getVendor(tevBean.getVenId());
            } catch (Exception ex) {
            }

            Date d = DateUtil.transformerStringEnDate(bean2.getDate(), "/");
            String day = DateUtil.formatDate(d, "dd");
            String month = DateUtil.formatDate(d, "MM");
            String year = DateUtil.formatDate(d, "yyyy");
            String placeDelivery = "";
            if (kind == 1) {
                placeDelivery = "DAP PTSC MC Warehouse, No 65A, 30/4 Road, Ward 9, Vung tau City, S.R Vietnam";
            } else {
                placeDelivery = "CIF Ho Chi Minh Port, Ho Chi Minh City, S.R Vietnam";
            }
            String name = vendorBean.getName();
            String nameUpcase = vendorBean.getName().toUpperCase();
            String fax = vendorBean.getFax();
            String phone = "Điện thoại: " + vendorBean.getPhone() + "    Fax: " + vendorBean.getFax();
            String email = vendorBean.getEmail();
            String address = "Địa chỉ: " + vendorBean.getAddress();
            String bidDeadlineTime = bean.getBidDeadlineTime();
            String bidDeadline = bean.getBidDeadline();
            String packName = bean.getPackName();
            String number = bean2.getSubfix();
            String date = bean2.getDate();
            String abbreviate = bean1.getAbbreviate();

            docs.add(
                    new TenderLetterPrintBean(name, nameUpcase, address, phone, fax, email, bidDeadlineTime, bidDeadline, packName, nameUpcase, placeDelivery, number, date, day, month, year, abbreviate, number, bidDeadlineTime));

            context.put("ptscmc", docs);

            // 3) Generate report by merging Java model with the Docx
            OutputStream out = new FileOutputStream(new File(wordDir + "/" + fileName + "-" + userName + type));

            report.process(context, out);

            try {
                if (type.equals(".docx")) {
                    //String outputFileName = fileName + "-" + userName + ".zip";
                    //FileUtil.zipFile(outputFileName, arrFiles);
                    //OutputUtil.sendZipFileToOutput(response, outputFileName);
                } else {
                    OutputUtil.sendWordFileToOutput(response, wordDir + "/", fileName + "-" + userName);
                }
            } catch (Exception ex) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XDocReportException e) {
            e.printStackTrace();
        }
    }
}
