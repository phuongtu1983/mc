/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.process.store.mrir;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.MrirBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MrirDAO;
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
public class SearchAdvMrirAction extends SpineAction {
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
        SearchAdvMrirFormBean formBean = (SearchAdvMrirFormBean) form;
        MrirBean bean = new MrirBean();

        bean.setMrirNumber(formBean.getMrirNumber());
        bean.setSearchStartDate(formBean.getSearchStartDate());
        bean.setSearchEndDate(formBean.getSearchEndDate());
        bean.setDnNumber(formBean.getDnNumber());        
        bean.setReqNumber(formBean.getReqNumber());
        bean.setBlNo(formBean.getBlNo());
        bean.setInvoiceNo(formBean.getInvoiceNo());
        bean.setPlNo(formBean.getPlNo());

        ArrayList mrirList = null;
        MrirDAO mrirDAO = new MrirDAO();
        try {
            mrirList = mrirDAO.searchAdvMrir(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED: searchAdvMrir-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.MRIR_LIST, mrirList);
        return true;
    }
}
