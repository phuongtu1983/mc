/* * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.process.equipment.mco;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.BaseAction;
import com.venus.mc.workReport.McoReport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/**
 *
 * @author kngo
 */
public class McoPrintAction extends BaseAction {

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
        int mcoId = NumberUtil.parseInt(request.getParameter("mcoId"), 0);
        if (mcoId > 0) {
            String xmlTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.02.04.KH.(PDK_mang_VTTB_ra)_xml.xml");
            String wordTemplate = request.getSession().getServletContext().getRealPath("/templates/BM.02.04.KH.(PDK_mang_VTTB_ra).xml");
            String result = "BM.02.04.KH.(PDK_mang_VTTB_ra).doc";
            McoReport report = new McoReport(xmlTemplate, wordTemplate, result);
            try {
                report.executeParse(request, response, mcoId);
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
