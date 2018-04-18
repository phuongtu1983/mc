/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.store;

import com.venus.core.util.LogUtil;
import com.venus.mc.bean.StoreBean;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.StoreDAO;
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
public class SearchAdvStoreAction extends SpineAction {

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
        SearchAdvStoreFormBean formBean = (SearchAdvStoreFormBean) form;
        StoreBean bean = new StoreBean();

        bean.setProId(formBean.getProId());
        bean.setName(formBean.getName());
        bean.setPhysicalAdd(formBean.getPhysicalAdd());
        bean.setKind(formBean.getKind());
        bean.setComments(formBean.getComments());

        ArrayList storeList = null;
        StoreDAO storeDAO = new StoreDAO();
        try {
            storeList = storeDAO.searchAdvStore(bean);
        } catch (Exception ex) {
            LogUtil.error("FAILED: searchAdvStore-" + ex.getMessage());
            ex.printStackTrace();
        }
        request.setAttribute(Constants.STORE_LIST, storeList);
        return true;
    }
}
