/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.contract.order;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.BaseAction;
import com.venus.mc.workReport.OrderReport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 * @version
 */
public class OrderPrintAction1 extends BaseAction {

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
        String doc="Don hang mua hang hoa";
        if (!GenericValidator.isBlankOrNull(id)) {
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/"+doc+"_xml.xml");
            String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/"+doc+".xml");
            String result = doc+".doc";
            OrderReport report = new OrderReport(xmlTemplate, wordTemplate, result);
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
