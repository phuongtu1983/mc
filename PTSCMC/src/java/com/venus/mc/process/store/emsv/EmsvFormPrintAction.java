/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.process.store.emsv;

import com.venus.mc.process.store.msv.*;
import com.venus.mc.process.store.msv.report.MsvFormReport;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.bean.MivBean;
import com.venus.mc.core.BaseAction;
import com.venus.mc.dao.MivDAO;
import com.venus.mc.process.store.emsv.report.EmsvFormReport;
import com.venus.mc.workReport.MivReport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuontu
 * @version
 */
public class EmsvFormPrintAction extends BaseAction {

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
        if (!GenericValidator.isBlankOrNull(request.getParameter("emsvId"))) {
            int emsvId = NumberUtil.parseInt(request.getParameter("emsvId"), 0);                        
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.11.02.KH (Phieu nhap kho HH don vi ngoai)_xml.xml");
            String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.11.02.KH (Phieu nhap kho HH don vi ngoai).xml");
            String result = "BM.11.02.KH (Phieu nhap kho HH don vi ngoai).doc";
            EmsvFormReport report = new EmsvFormReport(xmlTemplate, wordTemplate, result);
            try {
                report.executeParse(request, response, emsvId);
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
