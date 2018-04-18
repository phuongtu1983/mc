/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.emc;

import com.venus.core.util.NumberUtil;
import com.venus.mc.core.BaseAction;
import com.venus.mc.workReport.EmcReport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author kngo
 */
public class EmcPrintAction extends BaseAction {

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
        int emcId = NumberUtil.parseInt(request.getParameter("emcId"), 0);
        if (emcId > 0) {
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.01.04.KH.(PDK_mang_eVTTB_vao)_xml.xml");
            String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.01.04.KH.(PDK_mang_eVTTB_vao).xml");
            String result = "BM.01.04.KH.(PDK_mang_VTTB_vao).doc";
            EmcReport report = new EmcReport(xmlTemplate, wordTemplate, result);
            try {
                report.executeParse(request, response, emcId);
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
