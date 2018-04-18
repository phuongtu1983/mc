/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.intermemo;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.BaseAction;
import com.venus.mc.workReport.IntermemoReport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuontu
 * @version
 */
public class IntermemoPrintAction extends BaseAction {

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
        if (!GenericValidator.isBlankOrNull(request.getParameter("reqId"))) {
            String templateId = "1";
            if (!GenericValidator.isBlankOrNull(request.getParameter("templateId"))) {
                templateId = request.getParameter("templateId");
            }
            String xmlRealPath = "";
            String wordRealPath = "";
            String result = "";
            if (templateId.equals("1")) {
                xmlRealPath = "/templates/Intermemo_Structural MTO Cones & Conductor Guides Jacket_xml.xml";
                wordRealPath = "/templates/Intermemo_Structural MTO Cones & Conductor Guides Jacket.xml";
                result = "Intermemo_Structural MTO Cones & Conductor Guides Jacket.doc";
            } else if (templateId.equals("2")) {
                xmlRealPath = "/templates/Intermemo-installation sequence-cs boatlanding_xml.xml";
                wordRealPath = "/templates/Intermemo-installation sequence-cs boatlanding.xml";
                result = "Intermemo-installation sequence-cs boatlanding.doc";
            }
            String xmlTemplate = request.getSession().getServletContext().getRealPath(xmlRealPath);
            String wordTemplate = request.getSession().getServletContext().getRealPath(wordRealPath);
            IntermemoReport report = new IntermemoReport(xmlTemplate, wordTemplate, result);
            try {
                report.executeParse(request, response, Integer.parseInt(request.getParameter("reqId")));
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
