/*
 *
 * Created on April 13, 2007, 2:57 PM
 */
package com.venus.mc.intermemo;

import com.venus.core.util.LogUtil;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.RequestDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import java.util.ArrayList;

/**
 *
 * @author phuongtu
 * @version
 */
public class SearchAdvIntermemoAction extends SpineAction {

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
        SearchAdvIntermemoFormBean formBean = (SearchAdvIntermemoFormBean) form;
        ArrayList requestList = null;
        RequestDAO requestDAO = new RequestDAO();
        try {
            requestList = requestDAO.searchAdvIntermemo(formBean);
        } catch (Exception ex) {
            LogUtil.error("FAILED:Request:searchAdv-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.REQUEST_LIST, requestList);
        return true;
    }
}
