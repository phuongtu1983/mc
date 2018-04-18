
package com.venus.mc.origin;

/**
 * @author Mai Vinh Loc
 */

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OriginDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.venus.core.util.StringUtil;
import java.util.ArrayList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchSimpleOriginAction extends SpineAction {

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
        SearchFormBean originForm = (SearchFormBean) form;
        int fieldid = originForm.getSearchid();
        String strFieldvalue = originForm.getSearchvalue();
        ArrayList originList = null;
        OriginDAO originDAO = new OriginDAO();
        try {
            originList = originDAO.searchSimpleOrigin(fieldid, StringUtil.encodeHTML(strFieldvalue));
        } catch (Exception ex) {
            LogUtil.error("FAILED: OriginBean:searchSimpleOrigin-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.ORIGIN_LIST, originList);
        return true;
    }
   
}
