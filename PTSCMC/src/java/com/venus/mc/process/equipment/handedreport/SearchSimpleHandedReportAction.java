package com.venus.mc.process.equipment.handedreport;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.HandedReportDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.venus.core.util.StringUtil;
import java.util.ArrayList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mai vinh loc
 * @version
 */
public class SearchSimpleHandedReportAction extends SpineAction {

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
        SearchFormBean vendorForm = (SearchFormBean) form;
        int fieldid = vendorForm.getSearchid();
        String strFieldvalue = vendorForm.getSearchvalue();
        ArrayList requestList = null;
        HandedReportDAO hrDAO = new HandedReportDAO();
        try {
            requestList = hrDAO.searchSimpleHandedReport(fieldid, StringUtil.encodeHTML(strFieldvalue));
        } catch (Exception ex) {
            LogUtil.error(getClass().getName() + ": " + ex.getMessage());            
        }
        request.setAttribute(Constants.HANDEDREPORT_LIST, requestList);
        return true;
    }
}
