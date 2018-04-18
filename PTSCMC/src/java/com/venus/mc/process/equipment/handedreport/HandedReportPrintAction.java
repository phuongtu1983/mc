/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.process.equipment.handedreport;

import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.BaseAction;
import com.venus.mc.process.equipment.handedreport.report.HandedReportFormReport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuontu
 * @version
 */
public class HandedReportPrintAction extends BaseAction {

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
        if (!GenericValidator.isBlankOrNull(request.getParameter("hrId"))) {
            int hrId = NumberUtil.parseInt(request.getParameter("hrId"), 0);                        
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.11.01.KH (BB ban giao TTBDC)_xml.xml");
            String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.11.01.KH (BB ban giao TTBDC).xml");
            String result = "BM.11.01.KH (BB ban giao TTBDC).doc";
            HandedReportFormReport report = new HandedReportFormReport(xmlTemplate, wordTemplate, result);
            try {
                report.executeParse(request, response, hrId);
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
