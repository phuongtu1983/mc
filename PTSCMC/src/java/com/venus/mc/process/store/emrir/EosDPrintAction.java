/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.process.store.emrir;
import com.venus.core.util.GenericValidator;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.BaseAction;
import com.venus.mc.workReport.EosDReport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/**
 *
 * @author kngo
 */
public class EosDPrintAction extends BaseAction {

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
        int eosdId = NumberUtil.parseInt(request.getParameter("eosdId"), 0);
        
        if (eosdId > 0) {
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.03.02.KH_BCThuaThieuSaiKhac_HHDonViNgoai_xml.xml");
            String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.03.02.KH_BCThuaThieuSaiKhac_HHDonViNgoai.xml");
            String result = "BM.03.02.KH_BCThuaThieuSaiKhac_HHDonViNgoai.doc";
            EosDReport report = new EosDReport(xmlTemplate, wordTemplate, result);
            try {
                report.executeParse(request, response, eosdId);
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
