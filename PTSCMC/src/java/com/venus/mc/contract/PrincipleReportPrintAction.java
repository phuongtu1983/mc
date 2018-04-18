/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.contract;

import com.venus.core.util.LogUtil;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.BaseAction;
import com.venus.mc.core.ExcelExport;
import com.venus.mc.dao.ContractDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuontu
 * @version
 */
public class PrincipleReportPrintAction extends BaseAction {

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
//        int kind = NumberUtil.parseInt(request.getParameter("kind"), 0);
//
//        ArrayList list = null;
//
//        ContractDAO contractDAO = new ContractDAO();
//        try {
//            list = contractDAO.getContractsForExport(kind + "");
//        } catch (Exception ex) {
//        }
//        if (list == null) {
//            list = new ArrayList();
//        }
//        String templateFileName = request.getSession().getServletContext().getRealPath("/templates/principle_exported_template.xls");
//        try {
//            Map beans = new HashMap();
//            beans.put("hopdong", list);
//            ExcelExport exporter = new ExcelExport();
//            exporter.setBeans(beans);
//            exporter.export(request, response, templateFileName, "contract.xls");
//        } catch (Exception ex) {
//            LogUtil.error("FAILED:PrincipleReportPrint:print-" + ex.getMessage());
//            ex.printStackTrace();
//        }


        return true;
    }

    @Override
    protected boolean isReturnStream() {
        return true;
    }
}
