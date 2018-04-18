/*
 *
 * Created on April 13, 2007, 2:08 PM
 */
package com.venus.mc.process.equipment.reportdamage;

import com.venus.core.auth.OnlineUser;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.BaseAction;
import com.venus.mc.workReport.ReportDamageReport;
import com.venus.mc.util.MCUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author phuontu
 * @version
 */
public class ReportDamagePrintAction extends BaseAction {

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
        if (!GenericValidator.isBlankOrNull(request.getParameter("rdId"))) {
            int rdId = NumberUtil.parseInt(request.getParameter("rdId"), 0);
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.05.01.KH (Bien ban su viec)_xml.xml");
            String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.05.01.KH (Bien ban su viec).xml");
            String result = "BM.05.01.KH (Bien ban su viec).doc";
            HttpSession session = request.getSession();
            OnlineUser user = MCUtil.getOnlineUser(session);
            ReportDamageReport report = new ReportDamageReport(xmlTemplate, wordTemplate, result);
            try {
                report.executeParse(request, response, rdId + ":" + user.getOrganizationName());
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
