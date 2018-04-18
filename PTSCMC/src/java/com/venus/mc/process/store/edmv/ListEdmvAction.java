/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.process.store.edmv;

import com.venus.mc.process.store.dmv.*;
import com.venus.mc.process.store.msv.*;
import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.DmvDAO;
import com.venus.mc.dao.EdmvDAO;
import com.venus.mc.dao.MrvDAO;
import com.venus.mc.dao.MsvDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
/**
 *
 * @author thcao
 */
public class ListEdmvAction extends SpineAction {
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
        EdmvDAO dmvDAO = new EdmvDAO();
        ArrayList impStoreList = null;
        try {
            impStoreList = dmvDAO.getEdmvList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
        request.setAttribute(Constants.EDMV_LIST, impStoreList);
        return true;
    }

}
