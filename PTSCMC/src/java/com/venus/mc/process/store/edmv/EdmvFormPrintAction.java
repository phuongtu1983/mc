/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.process.store.edmv;


import com.venus.mc.process.store.dmv.*;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;

import com.venus.mc.core.BaseAction;
import com.venus.mc.process.store.edmv.report.EdmvFormReport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuontu
 * @version
 */
public class EdmvFormPrintAction extends BaseAction {

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
        if (!GenericValidator.isBlankOrNull(request.getParameter("edmvId"))) {
            int edmvId = NumberUtil.parseInt(request.getParameter("edmvId"), 0);
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.08.02.KH (Phieu nhap xuat thang)_xml.xml");
            String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.08.02.KH (Phieu nhap xuat thang).xml");
            String result = "BM.08.02.KH (Phieu nhap xuat thang).doc";
            EdmvFormReport report = new EdmvFormReport(xmlTemplate, wordTemplate, result);
            try {
                report.executeParse(request, response, edmvId);
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
