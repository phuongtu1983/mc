/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.tenderplan;

import com.venus.core.util.FileUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.dao.TenderPlanDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.workReport.TenderPlanAppendixReport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.workReport.TenderPlanReport;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 *
 * @author phuontu
 * @version
 */
public class TenderPlanPrintAction1 extends BaseAction {

    private ArrayList arrFiles = null;

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
        if (!GenericValidator.isBlankOrNull(request.getParameter("tenId"))) {
            try {
                arrFiles = new ArrayList();
                TenderPlanBean bean = null;
                TenderPlanDAO tenderDAO = new TenderPlanDAO();
                try {
                    bean = tenderDAO.getTenderPlan(Integer.parseInt(request.getParameter("tenId")));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                printTenderPlan(request, response, bean);
                printTenderPlanMaterial(request, response, bean);
                printTenderPlanAppendix(request, response, bean);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

    private void printTenderPlan(HttpServletRequest request, HttpServletResponse response, TenderPlanBean bean) {
        if (bean != null) {
            try {
                String userName = MCUtil.getMemberName(request.getSession());
                arrFiles.add("1.BM-02-02-KeHoachGoiChaoHang_" + userName + ".doc");
                String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/BM-02-02-KeHoachGoiChaoHang(PKH)_xml.xml");
                String wordTemplate = "";
                if (bean.getForm().equals(TenderPlanFormBean.FORM_FAX + "")) {
                    wordTemplate = request.getSession().getServletContext().getRealPath("/templates/Ke hoach goi chao hang bang Fax.xml");
                } else {
                    wordTemplate = request.getSession().getServletContext().getRealPath("/templates/Ke hoach goi chao hang bang phong bi kin.xml");
                }
                TenderPlanReport report = new TenderPlanReport(xmlTemplate, wordTemplate, "");
                FileOutputStream fOS = new FileOutputStream(new File((String) arrFiles.get(arrFiles.size() - 1)));
                report.executeParse(request, response, bean, fOS);
            } catch (Exception ex) {
            }
        }
    }

    private void printTenderPlanMaterial(HttpServletRequest request, HttpServletResponse response, TenderPlanBean bean) {
        try {
            String userName = MCUtil.getMemberName(request.getSession());
            arrFiles.add("2.BM-02-02-KeHoachGoiChaoHang_DMVT_" + userName + ".doc");
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/phuLucKeHoachGoiChaoHang_xml.xml");
            String wordTemplate = "";
            if (bean.getForm().equals(TenderPlanFormBean.FORM_FAX + "")) {
                wordTemplate = request.getSession().getServletContext().getRealPath("/templates/DanhMucVTChaoGiaFAX.xml");
            } else {
                wordTemplate = request.getSession().getServletContext().getRealPath("/templates/DanhMucVTChaoGiaPBK.xml");
            }
            TenderPlanAppendixReport report = new TenderPlanAppendixReport(xmlTemplate, wordTemplate, "");
            FileOutputStream fOS = new FileOutputStream(new File((String) arrFiles.get(arrFiles.size() - 1)));
            report.executeParse(request, response, bean, fOS);
        } catch (Exception ex) {
        }
    }

    private void printTenderPlanAppendix(HttpServletRequest request, HttpServletResponse response, TenderPlanBean bean) {
        try {
            String userName = MCUtil.getMemberName(request.getSession());
            arrFiles.add("3.BM-02-02-KeHoachGoiChaoHang_PhuLuc_" + userName + ".doc");
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/phuLucKeHoachGoiChaoHang_xml.xml");
            String wordTemplate = "";
            if (bean.getForm().equals(TenderPlanFormBean.FORM_FAX + "")) {
                wordTemplate = request.getSession().getServletContext().getRealPath("/templates/PhuLucChaoGiaFAX.xml");
            } else {
                wordTemplate = request.getSession().getServletContext().getRealPath("/templates/PhuLucChaoGiaPBK.xml");
            }
            TenderPlanAppendixReport report = new TenderPlanAppendixReport(xmlTemplate, wordTemplate, "");
            FileOutputStream fOS = new FileOutputStream(new File((String) arrFiles.get(arrFiles.size() - 1)));
            report.executeParse(request, response, bean, fOS);
        } catch (Exception ex) {
        }
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }

    @Override
    protected void customizedReturnAction(HttpServletRequest request, HttpServletResponse response) {
        try {
            String outputFileName = "BM-KeHoachGoiChaoHang.zip";
            FileUtil.zipFile(outputFileName, arrFiles);
            OutputUtil.sendZipFileToOutput(response, outputFileName);
        } catch (Exception ex) {
        }
    }
}
