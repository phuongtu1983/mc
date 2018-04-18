package com.venus.mc.process.purchasing.deliverynotice;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.BaseAction;
import com.venus.mc.workReport.DnReport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 * @version
 */
public class DnPrintAction1 extends BaseAction {

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
 
        String fileName="BM.01.02.KH (Yeu cau kiem tra tiep nhan VTTB)";

        if (!GenericValidator.isBlankOrNull(request.getParameter("dnId"))) {
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/"+fileName+"_xml.xml");
            String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/"+fileName+".xml");
            String result = fileName+".doc";
            DnReport report = new DnReport(xmlTemplate, wordTemplate, result);
            try {
                report.executeParse(request, response, Integer.parseInt(request.getParameter("dnId")));
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
