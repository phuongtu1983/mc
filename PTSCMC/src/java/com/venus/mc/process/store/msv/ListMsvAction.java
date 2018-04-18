/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.process.store.msv;

import com.venus.core.util.NumberUtil;
import com.venus.mc.core.SpineAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.venus.mc.util.Constants;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.MsvDAO;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
/**
 *
 * @author thcao
 */
public class ListMsvAction extends SpineAction {
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
        MsvDAO msvDAO = new MsvDAO();
        ArrayList msvList = null;
        try {
            msvList = msvDAO.getMsvList(MsvFormBean.TYPE_MSV);
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
        request.setAttribute(Constants.MSV_LIST, msvList);
        return true;
    }

}
