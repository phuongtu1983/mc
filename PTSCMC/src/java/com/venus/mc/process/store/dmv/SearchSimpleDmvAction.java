/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.process.store.dmv;
import com.venus.core.util.StringUtil;
import com.venus.core.util.LogUtil;
import com.venus.mc.bean.SearchFormBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DmvDAO;
import com.venus.mc.dao.MsvDAO;
import com.venus.mc.process.store.msv.MsvFormBean;
import com.venus.mc.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class SearchSimpleDmvAction extends SpineAction {

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
        SearchFormBean mrirForm = (SearchFormBean) form;
        int fieldid = mrirForm.getSearchid();
        String strFieldvalue = mrirForm.getSearchvalue();
        ArrayList dmvList = null;
        MsvDAO msvDAO = new MsvDAO();
        try {
            dmvList = msvDAO.searchSimple(MsvFormBean.TYPE_DMV, fieldid, StringUtil.encodeHTML(strFieldvalue));
        } catch (Exception ex) {
            LogUtil.error("FAILED: MsvBean:searchSimpleMsv-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.DMV_LIST, dmvList);
        return true;
    }
}
