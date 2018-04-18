
package com.venus.mc.process.equipment.requiresettling;

/**
 * @author Mai Vinh Loc
 */

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequireSettlingDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.venus.core.util.StringUtil;
import java.util.ArrayList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchSimpleRequireSettlingAction extends SpineAction {

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
        SearchFormBean requireSettlingForm = (SearchFormBean) form;
        int fieldid = requireSettlingForm.getSearchid();
        String strFieldvalue = requireSettlingForm.getSearchvalue();
        ArrayList requireSettlingList = null;
        RequireSettlingDAO requireSettlingDAO = new RequireSettlingDAO();
        try {
            requireSettlingList = requireSettlingDAO.searchSimpleRequireSettling(fieldid, StringUtil.encodeHTML(strFieldvalue));
        } catch (Exception ex) {
            LogUtil.error("FAILED: RequireSettlingBean:searchSimpleRequireSettling-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.REQUIRESETTLING_LIST, requireSettlingList);
        return true;
    }   
}
