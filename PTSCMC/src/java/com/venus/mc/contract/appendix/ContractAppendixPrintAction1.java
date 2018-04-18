package com.venus.mc.contract.appendix;

import com.venus.core.util.FileUtil;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.core.util.OutputUtil;
import com.venus.mc.bean.ContractBean;
import com.venus.mc.bean.VendorBean;
import com.venus.mc.contract.ContractDetailFormBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.util.MCUtil;
import com.venus.mc.workReport.ContractAppendixReport2;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 * @version
 */
public class ContractAppendixPrintAction1 extends BaseAction {

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
        String id = request.getParameter("conId");

        if (!GenericValidator.isBlankOrNull(id)) {
            try {
                arrFiles = new ArrayList();
                ContractDAO conDAO = new ContractDAO();
                int conId = NumberUtil.parseInt(id, 0);
                int vendKind = conDAO.getVendorKind(conId);
                printContractAppdendix(request, response, conId, vendKind);
                printContractAppendixMaterial(request, response, conId, vendKind);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

    private void printContractAppdendix(HttpServletRequest request, HttpServletResponse response, int conId, int venKind) {
        try {
            String userName = MCUtil.getMemberName(request.getSession());
            arrFiles.add("1.PhuLucHopDong_" + userName + ".doc");
            String doc = "";
            if (venKind == VendorBean.KIND_NATIONAL) {
                doc = "Phu luc Hop dong";
            } else {
                doc = "Phu luc Hop dong (TA)";
            }
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/" + "Phu luc Hop dong" + "_xml.xml");
            String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/" + doc + ".xml");
            ContractAppendixReport2 report = new ContractAppendixReport2(xmlTemplate, wordTemplate, "");
            try {
                FileOutputStream fOS = new FileOutputStream(new File((String) arrFiles.get(arrFiles.size() - 1)));
                report.executeParse(request, response, conId, fOS);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
        }
    }

    private void printContractAppendixMaterial(HttpServletRequest request, HttpServletResponse response, int conId, int venKind) {

        String userName = MCUtil.getMemberName(request.getSession());
        arrFiles.add("2.PhuLucHopDong_DMVT_" + userName + ".xls");
        ArrayList list = null;
        ArrayList parentList = null;
        ContractBean bean = null;
        ContractDAO contractDAO = new ContractDAO();
        try {
            bean = contractDAO.getContractAppendix(conId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (bean == null) {
            return;
        }
        String file = "";
        if (venKind == VendorBean.KIND_NATIONAL) {
            file = "Phu luc Hop dong";
        } else {
            file = "Phu luc Hop dong";
//            file = "Phu luc Hop dong (TA)";
        }
        String templateFileName = request.getSession().getServletContext().getRealPath("/templates/" + file + ".xls");
        try {
            list = contractDAO.getContractDetails(bean.getConId());
            parentList = contractDAO.getContractDetails(bean.getParentId());
        } catch (Exception ex) {
        }
        if (list == null) {
            list = new ArrayList();
        }
        if (parentList == null) {
            parentList = new ArrayList();
        }
        ContractDetailFormBean contractDetail = null;
        ContractDetailFormBean parentContractDetail = null;
        for (int i = 0; i < list.size(); i++) {
            contractDetail = (ContractDetailFormBean) list.get(i);
            for (int j = 0; j < parentList.size(); j++) {
                parentContractDetail = (ContractDetailFormBean) parentList.get(j);
                if (contractDetail.getMatId() == parentContractDetail.getMatId()) {
                    contractDetail.setNo(MCUtil.getBundleString("message.appendix.no") + " " + (j + 1));
                }
            }
            contractDetail.setTotal(contractDetail.getQuantity() * contractDetail.getPrice());
        }
        try {
            Map beans = new HashMap();
            String number = bean.getAppendixNumber();
            int ind = number.indexOf(" HĐ");
            if (ind > -1) {
                number = number.substring(0, ind);
            }
            beans.put("mcrp_number", number);
            beans.put("mcrp_currency", bean.getCurrency());
            beans.put("mcrp_total_not_vat", bean.getTotalNotVAT());
            beans.put("mcrp_vat", bean.getSumVAT());
            beans.put("mcrp_total", bean.getTotal());
            beans.put("mcrp_total_text", NumberUtil.textMoney(bean.getTotal(), bean.getCurrency()));
            beans.put("mcrp_contract", bean.getContractNumber());
            beans.put("material", list);
            ExcelExport exporter = new ExcelExport();
            exporter.setBeans(beans);
            FileOutputStream fOS = new FileOutputStream(new File((String) arrFiles.get(arrFiles.size() - 1)));
            long milis = System.currentTimeMillis();
            exporter.export(request, response, templateFileName, "PhuLucHopDongTemp.xls", fOS);
            milis = System.currentTimeMillis() - milis;
                System.out.println("PhuLucHopDongTemp.xls : " + "time : " + (int) ((milis / 1000) % 60) + " phut " + (int) ((milis / 1000) / 60) + " giay");
        } catch (Exception ex) {
            LogUtil.error("FAILED:RequestReportPrint:print-" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }

    @Override
    protected void customizedReturnAction(HttpServletRequest request, HttpServletResponse response) {
        try {
            String outputFileName = "BM-PhuLucHopDong.zip";
            FileUtil.zipFile(outputFileName, arrFiles);
            OutputUtil.sendZipFileToOutput(response, outputFileName);
        } catch (Exception ex) {
        }
    }
}
