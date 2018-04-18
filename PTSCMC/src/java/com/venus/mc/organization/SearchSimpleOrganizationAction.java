/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.organization;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.OrganizationDAO;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.venus.core.util.StringUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class SearchSimpleOrganizationAction extends SpineAction {

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
        SearchFormBean organizationForm = (SearchFormBean) form;
        int fieldid = organizationForm.getSearchid();
        String strFieldvalue = organizationForm.getSearchvalue();
        ArrayList organizationList = null;
        OrganizationDAO organizationDAO = new OrganizationDAO();
        try {
            organizationList = organizationDAO.searchSimpleOrganization(fieldid, StringUtil.encodeHTML(strFieldvalue));
        } catch (Exception ex) {
            LogUtil.error("FAILED: OrganizationBean:searchSimpleOrganization-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.ORGANIZATION_LIST, organizationList);
        return true;
    }
   
}
