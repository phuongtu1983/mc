/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.vendor.evaluate;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.BaseAction;
import com.venus.mc.workReport.VendorEvalReport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 * @version
 */
public class VendorEvalPrintAction extends BaseAction {

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
        if (!GenericValidator.isBlankOrNull(request.getParameter("venEvalId"))) {
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.13.02.TM-Phieu danh gia nha cung cap_xml.xml");
            String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.13.02.TM-Phieu danh gia nha cung cap.xml");
            String result = "BM.13.02.TM-Phieu danh gia nha cung cap.doc";
            VendorEvalReport report = new VendorEvalReport(xmlTemplate, wordTemplate, result);
            try {
                report.executeParse(request, response, Integer.parseInt(request.getParameter("venEvalId")));
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
