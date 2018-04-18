/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.process.equipment.emco;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.BaseAction;
import com.venus.mc.workReport.EmcoReport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/**
 *
 * @author kngo
 */
public class EmcoPrintAction extends BaseAction {

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
        int emcoId = NumberUtil.parseInt(request.getParameter("emcoId"), 0);
        if (emcoId > 0) {
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.02.04.KH.(PDK_mang_eVTTB_ra)_xml.xml");
            String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.02.04.KH.(PDK_mang_eVTTB_ra).xml");
            String result = "BM.02.04.KH.(PDK_mang_VTTB_ra).doc";
            EmcoReport report = new EmcoReport(xmlTemplate, wordTemplate, result);
            try {
                report.executeParse(request, response, emcoId);
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
