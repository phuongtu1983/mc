
package com.venus.mc.unit;

/**
 * @author Mai Vinh Loc
 */

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.UnitDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.venus.core.util.StringUtil;
import java.util.ArrayList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchSimpleUnitAction extends SpineAction {

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
        SearchFormBean unitForm = (SearchFormBean) form;
        int fieldid = unitForm.getSearchid();
        String strFieldvalue = unitForm.getSearchvalue();
        ArrayList unitList = null;
        UnitDAO unitDAO = new UnitDAO();
        try {
            unitList = unitDAO.searchSimpleUnit(fieldid, StringUtil.encodeHTML(strFieldvalue));
        } catch (Exception ex) {
            LogUtil.error("FAILED: UnitBean:searchSimpleUnit-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.UNIT_LIST, unitList);
        return true;
    }
  
}
