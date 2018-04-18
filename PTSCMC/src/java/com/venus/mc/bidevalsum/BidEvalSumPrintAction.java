package com.venus.mc.bidevalsum;

import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.TenderPlanBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.dao.BidEvalSumDAO;
import com.venus.mc.workReport.BidEvalSumReport;
import com.venus.mc.workReport.BidEvalSumReportPBK;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 * @version
 */
public class BidEvalSumPrintAction extends BaseAction {

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
        int besId = NumberUtil.parseInt(request.getParameter("besId"), 0);
        String fileName = "";
        BidEvalSumDAO besDAO = new BidEvalSumDAO();
        TenderPlanBean tender = null;
        try {
            tender = besDAO.getTenderPlanReport(besId);
            if (tender.getForm().endsWith("fax")) {
                fileName = "Bao cao danh gia chao hang theo mau Fax";
            } else {
                fileName = "Bao cao danh gia chao hang theo mau PBK";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (besId > 0 && tender != null) {
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/" + fileName + "_xml.xml");
            String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/" + fileName + ".xml");
            String result = fileName + ".doc";

            try {
                if (tender.getForm().endsWith("fax")) {
                    BidEvalSumReport report = new BidEvalSumReport(xmlTemplate, wordTemplate, result);
                    report.setRequest(request);
                    report.executeParse(request, response, Integer.parseInt(request.getParameter("besId")));
                } else {
                    BidEvalSumReportPBK report = new BidEvalSumReportPBK(xmlTemplate, wordTemplate, result);
                    report.setRequest(request);
                    report.executeParse(request, response, Integer.parseInt(request.getParameter("besId")));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
