package com.venus.mc.contract.order;

import com.venus.core.util.GenericValidator;
import com.venus.mc.core.BaseAction;
import com.venus.mc.workReport.OrderOtherRequestReport;
import com.venus.mc.workReport.OrderRequestReport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 * @version
 */
public class OrderRequestPrintAction1 extends BaseAction {

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
            String kind = request.getParameter("kind");
            String xmlTemplate = "";
            String wordTemplate = "";
            String result = "";
            if (kind.equals("order")) {
                xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/DeXuatKyDonDatHang_xml.xml");
                wordTemplate = request.getSession().getServletContext().getRealPath("/templates/De xuat ky Don dat hang.xml");
                result = "De xuat ky Don dat hang.doc";
                OrderRequestReport report = new OrderRequestReport(xmlTemplate, wordTemplate, result);
                try {
                    report.setRequest(request);
                    report.executeParse(request, response, Integer.parseInt(id));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (kind.equals("other")) {
                xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/DeXuatKyMuaLe_xml.xml");
                wordTemplate = request.getSession().getServletContext().getRealPath("/templates/De xuat mua le.xml");
                result = "De xuat mua le.doc";
                OrderOtherRequestReport report = new OrderOtherRequestReport(xmlTemplate, wordTemplate, result);
                try {
                    report.executeParse(request, response, Integer.parseInt(id));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }

        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
