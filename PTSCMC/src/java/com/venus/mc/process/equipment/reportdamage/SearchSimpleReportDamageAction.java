
package com.venus.mc.process.equipment.reportdamage;

/**
 * @author Mai Vinh Loc
 */

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.ReportDamageDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.venus.core.util.StringUtil;
import java.util.ArrayList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchSimpleReportDamageAction extends SpineAction {

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
        SearchFormBean reportDamageForm = (SearchFormBean) form;
        int fieldid = reportDamageForm.getSearchid();
        String strFieldvalue = reportDamageForm.getSearchvalue();
        ArrayList reportDamageList = null;
        ReportDamageDAO reportDamageDAO = new ReportDamageDAO();
        try {
            reportDamageList = reportDamageDAO.searchSimpleReportDamage(fieldid, StringUtil.encodeHTML(strFieldvalue));
        } catch (Exception ex) {
            LogUtil.error("FAILED: ReportDamageBean:searchSimpleReportDamage-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.REPORTDAMAGE_LIST, reportDamageList);
        return true;
    }   
}
