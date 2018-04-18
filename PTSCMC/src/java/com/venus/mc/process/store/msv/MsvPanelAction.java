/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.venus.mc.process.store.msv;

import com.venus.mc.core.SpineAction;
import com.venus.mc.dao.StoreDAO;
import com.venus.mc.util.Constants;
import com.venus.mc.util.MCUtil;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
/**
 *
 * @author thcao
 */
public class MsvPanelAction extends SpineAction {
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
        try {
            ArrayList arrStore = storeDAO.getStoresLevel1();     
            if (arrStore!=null) {
                LabelValueBean label = new LabelValueBean();
                label.setValue("all");
                label.setLabel(MCUtil.getBundleString("message.all"));
                arrStore.add(0,label);
            }
            request.setAttribute(Constants.STORE_LIST, arrStore);
        } catch (Exception ex) {
            Logger.getLogger(MsvPanelAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    protected String getXmlMessage() {
        return "importPanel";
    }

}

