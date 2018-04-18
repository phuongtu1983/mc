
package com.venus.mc.contract.appendix;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.BaseAction;
import com.venus.mc.workReport.ContractAppendixRequestReport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 * @version
 */
public class ContractAppendixRequestPrintAction1 extends BaseAction {

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
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/De xuat ky phu luc  Hop dong_xml.xml");
            String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/De xuat ky phu luc  Hop dong.xml");
            String result = "De xuat ky phu luc  Hop dong.doc";
            ContractAppendixRequestReport report = new ContractAppendixRequestReport(xmlTemplate, wordTemplate, result);
            try {
                report.setRequest(request);
                report.executeParse(request, response, Integer.parseInt(id));
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
