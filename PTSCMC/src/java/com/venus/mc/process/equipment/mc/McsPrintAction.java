/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.mc;

import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.McDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kngo
 */
public class McsPrintAction extends BaseAction {

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
        ArrayList mcReportList = null;
        McDAO mcDAO = new McDAO();
        String templateFileName = request.getSession().getServletContext().getRealPath("/templates/BM.05.04.KH.(STD_VTTB_BP_vao_ra).xls");
        Map beans = new HashMap();

        try {
            mcReportList = mcDAO.getMcReports();
            if (mcReportList == null) {
                mcReportList = new ArrayList();
            }

            beans.put("material", mcReportList);
            ExcelExport exporter = new ExcelExport();
            exporter.setBeans(beans);
            long milis = System.currentTimeMillis();
            exporter.export(request, response, templateFileName);
            milis = System.currentTimeMillis() - milis;
            System.out.println("BM.05.04.KH.(STD_VTTB_BP_vao_ra).xls : " + "time : " + (int) ((milis / 1000) % 60) + " phut " + (int) ((milis / 1000) / 60) + " mili giay");
        } catch (Exception ex) {
        }
        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
