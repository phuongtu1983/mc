
package com.venus.mc.deliveryrequest;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.BaseAction;
import com.venus.mc.workReport.DeliveryRequestReport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 * @version
 */
public class DeliveryRequestPrintAction1 extends BaseAction {

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
        String id = request.getParameter("drId");
        String doc="De nghi giao hang theo Hop dong nguyen tac";
        if (!GenericValidator.isBlankOrNull(id)) {
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/"+doc+"_xml.xml");
            String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/"+doc+".xml");
            String result = doc+".doc";
            DeliveryRequestReport report = new DeliveryRequestReport(xmlTemplate, wordTemplate, result);
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
