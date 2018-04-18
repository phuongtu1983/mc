/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.web;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.WebDAO;
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
public class SearchSimpleWebAction extends SpineAction {

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
        SearchFormBean webForm = (SearchFormBean) form;
        int fieldid = webForm.getSearchid();
        String strFieldvalue = webForm.getSearchvalue();
        ArrayList webList = null;
        WebDAO webDAO = new WebDAO();
        try {
            webList = webDAO.searchSimpleWeb(fieldid, StringUtil.encodeHTML(strFieldvalue));
        } catch (Exception ex) {
            LogUtil.error("FAILED: WebBean:searchSimpleWeb-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.WEB_LIST, webList);
        return true;
    }
}
