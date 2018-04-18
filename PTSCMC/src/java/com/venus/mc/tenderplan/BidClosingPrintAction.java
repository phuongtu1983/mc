/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.tenderplan;
import com.venus.core.util.GenericValidator;
import com.venus.mc.core.BaseAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.workReport.BidClosingReport;
/**
 *
 * @author kngo
 */
public class BidClosingPrintAction extends BaseAction {

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
        if (!GenericValidator.isBlankOrNull(request.getParameter("bcrId"))) {
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/Bien ban dong chao gia_xml.xml");
            String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/Bien ban dong chao gia.xml");
            String result = "Bien ban dong chao gia.doc";
            BidClosingReport report = new BidClosingReport(xmlTemplate, wordTemplate, result);
            try {
                report.executeParse(request, response, Integer.parseInt(request.getParameter("bcrId")));
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
