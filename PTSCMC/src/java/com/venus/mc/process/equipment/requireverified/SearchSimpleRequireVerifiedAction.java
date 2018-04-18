package com.venus.mc.process.equipment.requireverified;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequireRepairDAO;
import com.venus.mc.dao.RequireVerifiedDAO;
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
public class SearchSimpleRequireVerifiedAction extends SpineAction {

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
        RequireVerifiedDAO requestDAO = new RequireVerifiedDAO();
        try {
            requestList = requestDAO.searchSimpleRequireVerified(fieldid, StringUtil.encodeHTML(strFieldvalue));
        } catch (Exception ex) {
            LogUtil.error("SearchSimpleRequireVerifiedAction: " + ex.getMessage());            
        }
        request.setAttribute(Constants.REQUIREVERIFIED_LIST, requestList);
        return true;
    }
}
