/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.contractFollow;
import com.venus.core.util.GenericValidator;
import com.venus.mc.core.BaseAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.workReport.ContractFollowReport;
/**
 *
 * @author kngo
 */
public class ContractFollowPrintAction extends BaseAction {
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
        if (!GenericValidator.isBlankOrNull(request.getParameter("folId"))) {
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.12.02.TM-PhieuTheoDoiHopDong_xml.xml");
            String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.12.02.TM-PhieuTheoDoiHopDong.xml");
            String result = "BM.12.02.TM-PhieuTheoDoiHopDong.doc";
            ContractFollowReport report = new ContractFollowReport(xmlTemplate, wordTemplate, result);
            try {
                report.executeParse(request, response, Integer.parseInt(request.getParameter("folId")));
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
