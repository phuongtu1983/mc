/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.contract;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.BaseAction;
import com.venus.mc.workReport.ContractReport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.venus.mc.dao.ContractDAO;
import com.venus.mc.bean.ContractBean;
import com.venus.core.util.NumberUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 * @version
 */
public class ContractPrintAction1 extends BaseAction {

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
        if (!GenericValidator.isBlankOrNull(request.getParameter("conId"))) {
            ContractDAO conDAO = new ContractDAO();
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/Hop dong mua hang hoa_xml.xml");
            int conId = Integer.parseInt(request.getParameter("conId"));
            String wordTemplate = "";
            int kind = 0;
            try {
                kind = conDAO.getVendorKind(conId);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (kind == 1) {
                wordTemplate = request.getSession().getServletContext().getRealPath("/templates/Hop dong mua hang hoa.xml");
            } else {
                wordTemplate = request.getSession().getServletContext().getRealPath("/templates/Hop dong mua hang hoa_nn.xml");
            }
            String result = "Hop dong mua hang hoa.doc";
            ContractReport report = new ContractReport(xmlTemplate, wordTemplate, result);

            try {
                report.setRequest(request);
                report.executeParse(request, response, Integer.parseInt(request.getParameter("conId")));
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
