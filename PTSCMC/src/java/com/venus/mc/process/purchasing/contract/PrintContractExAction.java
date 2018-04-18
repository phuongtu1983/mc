/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.process.purchasing.contract;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.LogUtil;
import com.venus.mc.core.BaseAction;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.workReport.ContractAppendixReport;
import com.venus.mc.workReport.ContractReport;
import com.venus.mc.workReport.DeliveryRequestReport;
import com.venus.mc.workReport.OrderReport;
import com.venus.mc.workReport.PrincipleReport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author thcao
 * @version
 */
public class PrintContractExAction extends BaseAction {

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
        String conId = request.getParameter("conId");
        if (!GenericValidator.isBlankOrNull(conId)) {
            //1:contract; 2:contract_principle; 3: order; 4: appendix; 5: delivery_request
            int kind = 0;
            ContractDAO conDAO = new ContractDAO();
            try {
                kind = conDAO.getContractKind(Integer.parseInt(conId));
            } catch (Exception ex) {
                LogUtil.error("PrintContractExAction: " + ex.getMessage());
            }
            if (kind == 1) { //contract
                String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/Hop dong sq_xml.xml");
                String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/Hop dong sq.xml");
                String result = "Hop dong sq.doc";
                ContractReport report = new ContractReport(xmlTemplate, wordTemplate, result);
                try {
                    report.executeParse(request, response, Integer.parseInt(conId));
                } catch (Exception ex) {
                    LogUtil.error("PrintContractExAction: " + ex.getMessage());
                }
            } else if (kind == 2) { //contract_principle                
                String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/HDNT_xml.xml");
                String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/HDNT.xml");
                String result = "Hop dong nguyen tac.doc";
                PrincipleReport report = new PrincipleReport(xmlTemplate, wordTemplate, result);
                try {
                    report.executeParse(request, response, Integer.parseInt(conId));
                } catch (Exception ex) {
                    LogUtil.error("PrintContractExAction: " + ex.getMessage());
                }
            } else if (kind == 3) { //order
                String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/DDH-4_xml.xml");
                String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/DDH-4.xml");
                String result = "DDH-4.doc";
                OrderReport report = new OrderReport(xmlTemplate, wordTemplate, result);
                try {
                    report.executeParse(request, response, Integer.parseInt(conId));
                } catch (Exception ex) {
                    LogUtil.error("PrintContractExAction: " + ex.getMessage());
                }
            } else if (kind == 4) { //appendix
                String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/PhuLuc_SQ_xml.xml");
                String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/PhuLuc_SQ.xml");
                String result = "PhuLuc_SQ.doc";
                ContractAppendixReport report = new ContractAppendixReport(xmlTemplate, wordTemplate, result);
                try {
                    report.executeParse(request, response, Integer.parseInt(conId));
                } catch (Exception ex) {
                    LogUtil.error("PrintContractExAction: " + ex.getMessage());
                }
            } else if (kind == 5) { //delivery_request
                String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/DNGH 05.08.2009_xml.xml");
                String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/DNGH 05.08.2009.xml");
                String result = "DNGH 05.08.2009.doc";
                DeliveryRequestReport report = new DeliveryRequestReport(xmlTemplate, wordTemplate, result);
                try {
                    report.executeParse(request, response, Integer.parseInt(conId));
                } catch (Exception ex) {
                    LogUtil.error("PrintContractExAction: " + ex.getMessage());
                }
            }
        }

        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
