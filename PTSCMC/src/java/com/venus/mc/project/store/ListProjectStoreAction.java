/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.mc.project.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;
import org.apache.commons.validator.GenericValidator;
import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.util.PermissionUtil;
import java.util.ArrayList;

/**
 *
 * @author kngo
 */
public class ListProjectStoreAction extends SpineAction {

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
        StoreDAO storeDAO = new StoreDAO();
        ArrayList projectStoreList = null;
        String proId = request.getParameter("proId");
        if (!GenericValidator.isBlankOrNull(proId)) {
        try {
            projectStoreList = storeDAO.getProjectStores(Integer.parseInt(proId));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        }
        request.setAttribute(Constants.PROJECT_STORE_LIST, projectStoreList);
        return true;
    }
    
    @Override
    protected String getFunction() {
        return PermissionUtil.FUNC_LIST + "";
    }

    @Override
    protected int getPermit() {
        return PermissionUtil.PER_LIBRARY_PROJECT;
    }
}
