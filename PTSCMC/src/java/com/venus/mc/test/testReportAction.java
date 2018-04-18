/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.test;

import com.venus.mc.core.BaseAction;
import com.venus.mc.vendor.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuontu
 * @version
 */
public class testReportAction extends BaseAction {

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
        String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/9. Bao cao danh gia chao hang bang Phong bi kin_xml.xml");
        String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/9. Bao cao danh gia chao hang bang Phong bi kin.xml");
        String result = "BM.01.02.TM-Phieu de xuat.doc";
        testReport report = new testReport(xmlTemplate, wordTemplate, result);
        try {
            report.executeParse(request, response, 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
